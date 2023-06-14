package medxpert.main.daniyal_medxpert.patient.Database;

import android.content.Context;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import medxpert.main.daniyal_medxpert.patient.POJO.Report;
import medxpert.main.daniyal_medxpert.patient.SessionManager.SessionManager;

public class Db_HandlerLabTest {

    private DatabaseReference databaseRef;
    List<String> reportIds;
    List<Report> reports;


    public Db_HandlerLabTest(String rootNode) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();  //https://medxpert-2023-default-rtdb.firebaseio.com/
        databaseRef = database.getReference(rootNode);   //labTests


    }

    public void writeData( Report report) {
        String reportId = databaseRef.push().getKey();
        DatabaseReference nodeRef = databaseRef.child(reportId);
        nodeRef.setValue(report)
                .addOnSuccessListener(aVoid -> {

                })
                .addOnFailureListener(e -> {
                    // An error occurred while writing data to the database
                    // Handle the error
                });
    }



    public void addReportToDB(Report report, Context context) {
        // Generate a unique ID for the report
        String reportId = databaseRef.push().getKey();

        // Add the report to the "Labtest" node with the generated ID
        databaseRef.child(reportId).setValue(report).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    // Report added successfully to the database

                    // Get the CNIC of the current patient from SessionManager
                    String cnic = new SessionManager(context).getCNIC();

                    // Get the reference to the "labReports" node for the current patient
                    DatabaseReference labReportsRef = FirebaseDatabase.getInstance().getReference("patients")
                            .child(cnic).child("labReports");

                    // Get the current labReports list from the database
                    labReportsRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            List<String> labReports = new ArrayList<>();

                            if (dataSnapshot.exists()) {
                                // If labReports node already exists, retrieve its current value
                                labReports = (List<String>) dataSnapshot.getValue();
                            }

                            // Add the report ID to the labReports list
                            labReports.add(reportId);

                            // Set the updated labReports list back to the database
                            labReportsRef.setValue(labReports).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        // Report ID added successfully to the labReports list
                                        // You can perform any additional actions or callbacks here
                                    } else {
                                        // Failed to add report ID to labReports list
                                        // You can handle the failure case or perform any additional actions or callbacks here
                                    }
                                }
                            });
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            // Handle the onCancelled event if needed
                        }
                    });
                } else {
                    // Failed to add report to the database
                    // You can handle the failure case or perform any additional actions or callbacks here
                }
            }
        });
    }




    // Define the listener interface to handle the retrieved reports
    public interface OnReportsRetrievedListener {
        void onReportsRetrieved(List<String> reports);

        void onReportsRetrievalFailed(String errorMessage);
    }

    //--------------------


    public void getReports(Context context,OnReportsRetrievedListener listener) {
        String cnic = new SessionManager(context).getCNIC();
        DatabaseReference patientsRef = FirebaseDatabase.getInstance().getReference("patients");
        Query query = patientsRef.orderByChild("cnic").equalTo(cnic);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                reportIds = new ArrayList<>();

                if (dataSnapshot.exists()) {
                    DataSnapshot patientSnapshot = dataSnapshot.getChildren().iterator().next();
                    DataSnapshot labReportsSnapshot = patientSnapshot.child("labReports");

                    if (labReportsSnapshot.exists()) {
                        reportIds = (List<String>) labReportsSnapshot.getValue();
                    }
                }

                listener.onReportsRetrieved(reportIds);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                listener.onReportsRetrievalFailed(databaseError.getMessage());
            }
        });
    }



    public void getAllReportsForPatient(Context context, List<String> reportIds, OnReportObjectsRetrievedListener listener) {
        List<Report> reports = new ArrayList<>();

        // Iterate over the report IDs and fetch the corresponding reports
        DatabaseReference reportsRef = FirebaseDatabase.getInstance().getReference("Reports");
        for (String reportId : reportIds) {
            reportsRef.child(reportId).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        // Retrieve the report data from the snapshot
                        String date = dataSnapshot.child("date").getValue(String.class);
                        String image = dataSnapshot.child("image").getValue(String.class);
                        String name = dataSnapshot.child("name").getValue(String.class);

                        // Create a Report object and add it to the list
                        Report report = dataSnapshot.getValue(Report.class);
                        reports.add(report);
                    }

                    // Check if all reports have been retrieved
                    if (reports.size() == reportIds.size()) {
                        // Notify the listener with the retrieved reports
                        listener.onReportObjectsRetrieved(reports);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    // Notify the listener about the retrieval failure
                    listener.onReportObjectsFailed(databaseError.getMessage());
                }
            });
        }
    }

    // Define an interface for the listener to handle the retrieved reports
    public interface OnReportObjectsRetrievedListener {
        void onReportObjectsRetrieved(List<Report> reports);
        void onReportObjectsFailed(String errorMessage);
    }


//-----------------------------------------------------


    public void getReportsForCNIC(String cnic, final OnReportObjectsRetrievedListener listener) {
        DatabaseReference reportsRef = FirebaseDatabase.getInstance().getReference().child("Reports");

        reportsRef.orderByChild("patientCNIC").equalTo(cnic).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<Report> reportList = new ArrayList<>();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Report report = snapshot.getValue(Report.class);
                    reportList.add(report);
                }

                listener.onReportObjectsRetrieved(reportList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                listener.onReportObjectsFailed(databaseError.getMessage());
            }
        });
    }



}
