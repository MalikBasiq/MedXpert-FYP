package medxpert.main.daniyal_medxpert.doctor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import medxpert.main.daniyal_medxpert.R;
import medxpert.main.daniyal_medxpert.doctor.Database.doctor_Db_Handler;
import medxpert.main.daniyal_medxpert.doctor.Fragments.MedicinesFragmentDoctor;
import medxpert.main.daniyal_medxpert.doctor.Fragments.NotesFragmentDoctor;
import medxpert.main.daniyal_medxpert.doctor.Fragments.VitalsFragmentDoctor;
import medxpert.main.daniyal_medxpert.doctor.POJO.Prescription_Model;
import medxpert.main.daniyal_medxpert.doctor.SessionManager.SessionManager;

public class Layout1 extends AppCompatActivity {

    LinearLayout linearLayout;
    Button medicine, vitals, notes;
    Prescription_Model prescription;
    List<MedicineModel_doctor> Medicines;
    List<Model_Vitals_Doctor> Vitals;
    List<Model_Notes_Doctor> Notes;
    MedicinesFragmentDoctor medicinesFragmentDoctor;
    VitalsFragmentDoctor vitalsFragmentDoctor;
    NotesFragmentDoctor notesFragmentDoctor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout1);

        //Initializing Fragments
        medicinesFragmentDoctor = new MedicinesFragmentDoctor();
        vitalsFragmentDoctor = new VitalsFragmentDoctor();
        notesFragmentDoctor = new NotesFragmentDoctor();

        //Getting Data from Intent
        Intent previousIntent=getIntent();
        String patientCnic=previousIntent.getStringExtra("patientCNIC");
        Toast.makeText(this, patientCnic, Toast.LENGTH_SHORT).show();

        //Creating Object
        SessionManager sessionManager=new SessionManager(this);
        prescription=new Prescription_Model();
        Medicines=new ArrayList<MedicineModel_doctor>();
        Vitals=new ArrayList<>();
        Notes=new ArrayList<>();

        //Setting Data to prescription
        prescription.setPatientCNIC(patientCnic);
        prescription.setDoctorCNIC(sessionManager.getCNIC());
        prescription.setDate(getCurrentDate());
        prescription.setDoctorName(sessionManager.getDoctorName());
        prescription.setDesignation(sessionManager.getDoctorSpecialization());
        prescription.setMedicines(Medicines);
        prescription.setVitals(Vitals);
        prescription.setNotes(Notes);

//        TestFunction(prescription);
        //Showing back button on toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(0);

        //Getting TextViews
        TextView docName=findViewById(R.id.namedoclayout1);
        TextView docDate=findViewById(R.id.datedoclayout1);
        TextView docDesignation=findViewById(R.id.designationdoclayout1);

        //Setting Values to TextViews
        docName.setText(sessionManager.getDoctorName());
        docDesignation.setText(sessionManager.getDoctorSpecialization());
        docDate.setText(getCurrentDate());

        linearLayout = findViewById(R.id.linearforfragmentsdoclayout1);
        medicine = findViewById(R.id.medicinesdoclayout1);
        vitals = findViewById(R.id.vitalsdoclayout1);
        notes = findViewById(R.id.notesdoclayout1);

        medicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.linearforfragmentsdoclayout1, medicinesFragmentDoctor);
                transaction.commit();

                Notes.clear();
                Notes.addAll(notesFragmentDoctor.getList());

                Vitals.clear();
                Vitals.addAll(vitalsFragmentDoctor.getList());

                Toast.makeText(Layout1.this, "Notes:"+Notes.size()+" "+"Vitals: "+Vitals.size(), Toast.LENGTH_SHORT).show();

            }
        });

        vitals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.linearforfragmentsdoclayout1, vitalsFragmentDoctor);
                transaction.commit();

                Medicines.clear();
                Medicines.addAll(medicinesFragmentDoctor.getList());

                Notes.clear();
                Notes.addAll(notesFragmentDoctor.getList());

                Toast.makeText(Layout1.this, "Medicine: "+Medicines.size()+" "+"Notes:"+Notes.size(), Toast.LENGTH_SHORT).show();



            }
        });

        notes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.linearforfragmentsdoclayout1, notesFragmentDoctor);
                transaction.commit();

                Medicines.clear();
                Medicines.addAll(medicinesFragmentDoctor.getList());

                Vitals.clear();
                Vitals.addAll(vitalsFragmentDoctor.getList());

                Toast.makeText(Layout1.this, "Medicine: "+Medicines.size()+" "+"Vitals: "+Vitals.size(), Toast.LENGTH_SHORT).show();

            }
        });

    }

    String getCurrentDate(){
        Date currentDate = new Date();

        // Define the desired date format
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        // Format the date as a string
        String formattedDate = formatter.format(currentDate);

        return formattedDate;
    }


    public void onClickAddPrescriptionBtn(View view){
        if(Medicines.size()==0 || Vitals.size()==0 || Notes.size()==0){
            Toast.makeText(this, "Add atleast one Medicine, Vital and Note", Toast.LENGTH_SHORT).show();
            return;
        }

        doctor_Db_Handler db_handler=new doctor_Db_Handler("prescriptions");
        db_handler.addPrescription(prescription);

        startActivity(new Intent(Layout1.this,doctor_dashboard.class));


    }
//    void TestFunction(Prescription_Model prescription){
//        Prescription_Model testPrescription=prescription;
//        List<MedicineModel_doctor> MedicinesTest = new ArrayList<>();
//        List<Model_Vitals_Doctor> VitalsTest=new ArrayList<>();
//        List<Model_Notes_Doctor> NotesTest=new ArrayList<>();
//
//        MedicineModel_doctor medicineModel=new MedicineModel_doctor("medicineName",  "morningQuantity",  "eveningQuantity",  "nightQuantity",  "duration",  "direction");
//        MedicinesTest.add(medicineModel);
//        testPrescription.setMedicines(MedicinesTest);
//
//        Model_Vitals_Doctor model_vitals_doctor=new Model_Vitals_Doctor("Abc","qtytets");
//        VitalsTest.add(model_vitals_doctor);
//        testPrescription.setVitals(VitalsTest);
//
//        Model_Notes_Doctor model_notes_doctor=new Model_Notes_Doctor("headingtest","testetets");
//        NotesTest.add(model_notes_doctor);
//        testPrescription.setNotes(NotesTest);
//
//        doctor_Db_Handler db_handler=new doctor_Db_Handler("prescriptions");
//        db_handler.addPrescription(testPrescription);
//
//
//
//
//    }
}