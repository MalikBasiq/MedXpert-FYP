package medxpert.main.daniyal_medxpert.patient.Database;

import android.content.Context;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import medxpert.main.daniyal_medxpert.patient.POJO.MedBox;
import medxpert.main.daniyal_medxpert.patient.SessionManager.SessionManager;

public class Db_HandlerMedboxes {

    private DatabaseReference medboxesRef;
    private Context context;

    public Db_HandlerMedboxes(String rootNode, Context context) {
        // Get the Firebase database instance
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        // Get a reference to the "Medboxes" node
        medboxesRef = firebaseDatabase.getReference(rootNode);
        this.context = context;
    }
   public DatabaseReference getMedboxesRef()
   {
       return this.medboxesRef;
   }


    // Add medbox to the database
    public void addMedboxToDB(MedBox medbox) {
        // Generate a unique key for the medbox
        String medboxKey = medboxesRef.push().getKey();

        medbox.medboxID= medboxKey; // Setting medbox key to key we get from push

        String cnic = new SessionManager(context).getCNIC();

        medbox.CNIC= cnic ; // Setting medbox CNIC to CNIC we get from session

        // Set the medbox object under the generated key
        medboxesRef.child(medboxKey).setValue(medbox)
                .addOnSuccessListener(aVoid -> {
                    // Medbox added successfully
                    showToast("Medbox added successfully");
                })
                .addOnFailureListener(e -> {
                    // An error occurred while adding the medbox
                    showToast("Failed to add medbox");
                });
    }

    private void showToast(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public void getAllMedboxes(String userCNIC, final GetAllMedboxesCallback callback) {
        medboxesRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {

                List<MedBox> medboxes = new ArrayList<>();

                if (dataSnapshot.exists())
                {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren())
                    {
                        MedBox medbox = snapshot.getValue(MedBox.class);
                        if (medbox != null && medbox.CNIC.equals(userCNIC))
                        {
                            medboxes.add(medbox);
                        }
                    }
                }
                if (medboxes.isEmpty()) {
                    callback.onNoMedboxes();
                } else
                {
                    callback.onMedboxesReceived(medboxes);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                callback.onCancelled(databaseError.getMessage());
            }
        });
    }

    public interface GetAllMedboxesCallback {
        void onMedboxesReceived(List<MedBox> medboxes);

        void onNoMedboxes();

        void onCancelled(String errorMessage);
    }

    public void deleteMedboxFromDB(String medboxKey, final DeleteMedboxCallback callback) {
        medboxesRef.child(medboxKey).removeValue()
                .addOnSuccessListener(aVoid -> {
                    // Medbox deleted successfully
                    callback.onMedboxDeleted();
                })
                .addOnFailureListener(e -> {
                    // An error occurred while deleting the medbox
                    callback.onDeleteFailed(e.getMessage());
                });
    }

    public interface DeleteMedboxCallback {
        void onMedboxDeleted();

        void onDeleteFailed(String errorMessage);
    }


    //update data
    public void updateDB(MedBox medbox)
    {
        // Generate a unique key for the medbox
        String medboxKey = medbox.medboxID;



        //String cnic = new SessionManager(context).getCNIC();

        //medbox.CNIC= cnic ; // Setting medbox CNIC to CNIC we get from session

        // Set the medbox object under the generated key
        medboxesRef.child(medboxKey).setValue(medbox)
                .addOnSuccessListener(aVoid -> {
                    // Medbox added successfully
                    showToast("Medbox added successfully");
                })
                .addOnFailureListener(e -> {
                    // An error occurred while adding the medbox
                    showToast("Failed to add medbox");
                });
    }

}
