package medxpert.main.daniyal_medxpert.doctor.Database;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import medxpert.main.daniyal_medxpert.doctor.POJO.Doctor;
import medxpert.main.daniyal_medxpert.doctor.SessionManager.SessionManager;

public class doctor_Db_Handler {

    private DatabaseReference databaseRef;

    public doctor_Db_Handler(String rootNode) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();  //https://medxpert-2023-default-rtdb.firebaseio.com/
        databaseRef = database.getReference(rootNode);   //doctors
    }

    public void writeData(String node, Object data) {
        DatabaseReference nodeRef = databaseRef.child(node);
        nodeRef.setValue(data)
                .addOnSuccessListener(aVoid -> {
                    // Data is successfully written to the database
                    // Proceed with any additional steps or actions
                })
                .addOnFailureListener(e -> {
                    // An error occurred while writing data to the database
                    // Handle the error
                });
    }

    public void addPrescription( Object data) {
        String prescriptiontId = databaseRef.push().getKey();
        DatabaseReference nodeRef = databaseRef.child(prescriptiontId);
        nodeRef.setValue(data)
                .addOnSuccessListener(aVoid -> {
                    // Data is successfully written to the database
                    // Proceed with any additional steps or actions
                })
                .addOnFailureListener(e -> {
                    // An error occurred while writing data to the database
                    // Handle the error
                });
    }

    public interface LoginCallback {
        void onLoginSuccess();
        void onLoginFailure();
    }


    public void login(String cnic, String password, Context context, final LoginCallback callback) {
        DatabaseReference databaseReference = databaseRef.child(cnic);
        databaseReference.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    if (task.getResult().exists()) {
                        Toast.makeText(context, "Read Successfully", Toast.LENGTH_SHORT).show();
                        DataSnapshot snapshot = task.getResult();
                        String db_password = snapshot.child("password").getValue(String.class);
                        String Fname=snapshot.child("firstName").getValue(String.class);
                        String Lname=snapshot.child("lastName").getValue(String.class);
                        String specialization=snapshot.child("specialization").getValue(String.class);
                        String name=Fname+" "+Lname;


                        if (!password.equals(db_password))
                            callback.onLoginFailure();
                        else{
                            callback.onLoginSuccess();
                            new SessionManager(context).setLoggedIn(true,cnic,password,"doctor",name,specialization);
                        }
                    } else {

                        callback.onLoginFailure();
                    }
                } else {
                    callback.onLoginFailure();
                }
            }
        });
    }


    public interface PatientCallback {
        void onPatientRetrieved(Doctor doctor);
        void onCancelled(DatabaseError databaseError);
    }

    public void getPatientByCNIC(String cnic, PatientCallback callback) {
        DatabaseReference patientsRef = databaseRef;
        Query query = patientsRef.orderByChild("cnic").equalTo(cnic);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot patientSnapshot : dataSnapshot.getChildren()) {
                    Doctor doctor = patientSnapshot.getValue(Doctor.class);
                    if (doctor != null) {
                        // Patient found
                        callback.onPatientRetrieved(doctor);
                        return;
                    }
                }
                // Patient not found
                callback.onPatientRetrieved(null);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Error occurred while retrieving data
                callback.onCancelled(databaseError);
            }
        });
    }









    // Add additional methods for reading, updating, deleting data, etc.
}
