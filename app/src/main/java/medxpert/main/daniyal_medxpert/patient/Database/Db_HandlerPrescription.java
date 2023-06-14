package medxpert.main.daniyal_medxpert.patient.Database;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import medxpert.main.daniyal_medxpert.doctor.POJO.Prescription_Model;
import medxpert.main.daniyal_medxpert.patient.POJO.Report;

public class Db_HandlerPrescription {

    private DatabaseReference databaseRef;

    public Db_HandlerPrescription(String rootNode) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();  //https://medxpert-2023-default-rtdb.firebaseio.com/
        databaseRef = database.getReference(rootNode);   //prescriptions
    }

    public void getPrescriptionForCNIC(String cnic, final Db_HandlerPrescription.onPrescriptionObjectsRetrievedListener listener) {
        DatabaseReference reportsRef = FirebaseDatabase.getInstance().getReference().child("prescriptions");

        reportsRef.orderByChild("patientCNIC").equalTo(cnic).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<Prescription_Model> prescriptionList = new ArrayList<>();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Prescription_Model prescription = snapshot.getValue(Prescription_Model.class);
                    prescriptionList.add(prescription);
                }

                listener.onPrescriptionObjectsRetrieved(prescriptionList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                listener.onPrescriptionObjectsFailed(databaseError.getMessage());
            }
        });
    }

    // Define an interface for the listener to handle the retrieved reports
    public interface onPrescriptionObjectsRetrievedListener {
        void onPrescriptionObjectsRetrieved(List<Prescription_Model> reports);
        void onPrescriptionObjectsFailed(String errorMessage);
    }


}
