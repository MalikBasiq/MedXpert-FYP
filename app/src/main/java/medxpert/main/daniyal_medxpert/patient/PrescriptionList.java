package medxpert.main.daniyal_medxpert.patient;

import medxpert.main.daniyal_medxpert.doctor.MedicineModel_doctor;
import medxpert.main.daniyal_medxpert.doctor.Model_Notes_Doctor;
import medxpert.main.daniyal_medxpert.doctor.Model_Vitals_Doctor;
import medxpert.main.daniyal_medxpert.doctor.POJO.Prescription_Model;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import medxpert.main.daniyal_medxpert.patient.Adapters.Adapter_medicalrecordlayout1;
import medxpert.main.daniyal_medxpert.R;
import medxpert.main.daniyal_medxpert.patient.Database.Db_HandlerLabTest;
import medxpert.main.daniyal_medxpert.patient.Database.Db_HandlerPrescription;
import medxpert.main.daniyal_medxpert.patient.POJO.Report;
import medxpert.main.daniyal_medxpert.patient.SessionManager.SessionManager;

public class PrescriptionList extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Prescription_Model> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prescriptionlist);

        list=new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerView);
        Adapter_medicalrecordlayout1 adapter = new Adapter_medicalrecordlayout1(list, this);
        recyclerView.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);



        //Get List from Firebase
        Db_HandlerPrescription db_Handler = new Db_HandlerPrescription("prescriptions");
        db_Handler.getPrescriptionForCNIC(new SessionManager(this).getCNIC(), new Db_HandlerPrescription.onPrescriptionObjectsRetrievedListener() {
            @Override
            public void onPrescriptionObjectsRetrieved(List<Prescription_Model> prescriptions) {
                list.clear();
                list.addAll(prescriptions);

                findViewById(R.id.progressBar).setVisibility(View.GONE);
                Toast.makeText(PrescriptionList.this, list.toString(), Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onPrescriptionObjectsFailed(String errorMessage) {

            }
        });


        //Showing back button on toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);



//        // Creating a demo Prescription_Model object
//        Prescription_Model prescription = new Prescription_Model(
//                new SessionManager(this).getCNIC(), // patientCNIC
//                "0987654321", // doctorCNIC
//                "2023-06-13", // date
//                "Dr. John Doe", // doctorName
//                "Cardiologist", // designation
//                getDemoMedicines(), // medicines
//                getDemoVitals(), // vitals
//                getDemoNotes() // notes
//        );
//
//        list.add(prescription);


    }


    //--------------

    private List<MedicineModel_doctor> getDemoMedicines() {
        List<MedicineModel_doctor> medicines = new ArrayList<>();

        MedicineModel_doctor medicine1 = new MedicineModel_doctor(
                "Paracetamol", // medicineName
                "2", // morningQuantity
                "2", // eveningQuantity
                "2", // nightQuantity
                "5 days", // duration
                "Take after meals" // direction
        );
        medicines.add(medicine1);

        MedicineModel_doctor medicine2 = new MedicineModel_doctor(
                "Amoxicillin", // medicineName
                "1", // morningQuantity
                "1", // eveningQuantity
                "1", // nightQuantity
                "7 days", // duration
                "Take with water" // direction
        );
        medicines.add(medicine2);

        // Add more medicines if needed

        return medicines;
    }

    // Creating demo Model_Vitals_Doctor objects
    private List<Model_Vitals_Doctor> getDemoVitals() {
        List<Model_Vitals_Doctor> vitals = new ArrayList<>();

        Model_Vitals_Doctor vital1 = new Model_Vitals_Doctor(
                "Heart Rate", // nameOfvitals
                "75 bpm" // valueOfVitals
        );
        vitals.add(vital1);

        Model_Vitals_Doctor vital2 = new Model_Vitals_Doctor(
                "Blood Pressure", // nameOfvitals
                "120/80 mmHg" // valueOfVitals
        );
        vitals.add(vital2);

        // Add more vitals if needed

        return vitals;
    }

    // Creating demo Model_Notes_Doctor objects
    private List<Model_Notes_Doctor> getDemoNotes() {
        List<Model_Notes_Doctor> notes = new ArrayList<>();

        Model_Notes_Doctor note1 = new Model_Notes_Doctor(
                "Important Note", // headingNotes
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit." // descriptionNotes
        );
        notes.add(note1);

        Model_Notes_Doctor note2 = new Model_Notes_Doctor(
                "Follow-up Instructions", // headingNotes
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit." // descriptionNotes
        );
        notes.add(note2);

        // Add more notes if needed

        return notes;
    }
}


