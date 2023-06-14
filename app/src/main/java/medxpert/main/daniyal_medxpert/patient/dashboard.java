package medxpert.main.daniyal_medxpert.patient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import medxpert.main.daniyal_medxpert.R;
import medxpert.main.daniyal_medxpert.patient.SessionManager.SessionManager;

public class dashboard extends AppCompatActivity {

    BottomNavigationView bottom_navigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dashboard);


        //Showing back button on toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


//        //TODO handling on click listener for each imagebutton:
        CardView medicalRecordCard=findViewById(R.id.medicalRecordBtn);
        medicalRecordCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(dashboard.this,PrescriptionList.class);
                startActivity(intent);
            }
        });


        CardView labTestCard=findViewById(R.id.labTestBtn);
        labTestCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(dashboard.this,LabReports.class);
                startActivity(intent);
            }
        });


        CardView medBoxCard=findViewById(R.id.medBoxBtn);
        medBoxCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(dashboard.this,MainActivity.class);
                startActivity(intent);
            }
        });





        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                switch (itemId) {
                    case R.id.Home:
                        Toast.makeText(dashboard.this, "Home clicked ", Toast.LENGTH_SHORT).show();
                        Intent i =new Intent(getApplicationContext(), dashboard.class);
                        startActivity(i);
                        // Handle Home item selection
                        // Navigate to the Home page or perform related actions
                        return true;
                    case R.id.Record:

                        Toast.makeText(dashboard.this, "Record clicked ", Toast.LENGTH_SHORT).show();


                        // Handle Record item selection
                        // Navigate to the Record page or perform related actions
                        return true;
                    case R.id.Account:
                        Toast.makeText(dashboard.this, "Account clicked", Toast.LENGTH_SHORT).show();

                        return true;
                }
                return false;
            }
        });


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    public void logout(View view){
        new SessionManager(this).setLoggedOut();
        startActivity(new Intent(this, onBoarding.class));
    }

}