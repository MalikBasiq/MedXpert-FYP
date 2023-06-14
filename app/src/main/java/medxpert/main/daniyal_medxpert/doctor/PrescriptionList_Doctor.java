package medxpert.main.daniyal_medxpert.doctor;

import medxpert.main.daniyal_medxpert.doctor.Adapters.Adapter_medicalrecordlayout1_doctor;
import medxpert.main.daniyal_medxpert.doctor.Database.doctor_Db_HandlerPrescriptions;
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
import medxpert.main.daniyal_medxpert.patient.Database.Db_HandlerPrescription;
import medxpert.main.daniyal_medxpert.patient.SessionManager.SessionManager;

public class PrescriptionList_Doctor extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Prescription_Model> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prescriptionlist);

        list=new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerView);
        Adapter_medicalrecordlayout1_doctor adapter = new Adapter_medicalrecordlayout1_doctor(list, this);
        recyclerView.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);



        //Get List from Firebase
        doctor_Db_HandlerPrescriptions db_Handler = new doctor_Db_HandlerPrescriptions("prescriptions");
        db_Handler.getPrescriptionForCNIC(new SessionManager(this).getCNIC(),getIntent().getStringExtra("patientCNIC"), new doctor_Db_HandlerPrescriptions.onPrescriptionObjectsRetrievedListener() {
            @Override
            public void onPrescriptionObjectsRetrieved(List<Prescription_Model> prescriptions) {
                list.clear();
                list.addAll(prescriptions);

                findViewById(R.id.progressBar).setVisibility(View.GONE);
                Toast.makeText(PrescriptionList_Doctor.this, list.toString(), Toast.LENGTH_SHORT).show();
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



    }



}


