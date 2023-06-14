package medxpert.main.daniyal_medxpert.patient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import medxpert.main.daniyal_medxpert.R;
import medxpert.main.daniyal_medxpert.doctor.DoctorLoginActivity;
import medxpert.main.daniyal_medxpert.doctor.doctor_dashboard;
import medxpert.main.daniyal_medxpert.patient.SessionManager.SessionManager;

public class onBoarding extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_borading);


        //Showing back button on toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(0);

        SessionManager sessionManager = new SessionManager(this);
        Toast.makeText(this, String.valueOf(sessionManager.isLoggedIn()), Toast.LENGTH_SHORT).show();

        if (sessionManager.isLoggedIn()) {
            if (sessionManager.getUser().equals("patient"))
                startActivity(new Intent(this, dashboard.class));
            else
                startActivity(new Intent(this, doctor_dashboard.class));
        }
    }

    public void onPatientBtnClick(View view){
        startActivity(new Intent(onBoarding.this,login.class));
    }

    public void onDoctorBtnClick(View view){
        startActivity(new Intent(onBoarding.this, DoctorLoginActivity.class));
    }
}