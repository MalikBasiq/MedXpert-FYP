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

import java.util.ArrayList;
import java.util.List;

import medxpert.main.daniyal_medxpert.R;
import medxpert.main.daniyal_medxpert.doctor.Fragments.Medicines_Fragment;
import medxpert.main.daniyal_medxpert.doctor.Fragments.Notes_Fragment;
import medxpert.main.daniyal_medxpert.doctor.Fragments.Vitals_Fragment;

public class medicalrecordLayout2_Doctor extends AppCompatActivity {

    Button medicine, vitals, notes;
    LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicalrecord_layout2);

        //Showing back button on toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        //End of Displaying Toolbar

        //Getting Data from previous screen
        Intent intent =getIntent();
        String name = intent.getStringExtra("Name");
        String designation = intent.getStringExtra("Designation");
        String date = intent.getStringExtra("Date");
        List<MedicineModel_doctor> Medicines=(ArrayList<MedicineModel_doctor>) getIntent().getSerializableExtra("Medicines");
        List<Model_Vitals_Doctor> Vitals= (ArrayList<Model_Vitals_Doctor>) getIntent().getSerializableExtra("Vitals");
        List<Model_Notes_Doctor> Notes= (ArrayList<Model_Notes_Doctor>) getIntent().getSerializableExtra("Notes");


        //Populating Details about Doctor
        TextView textView1 = findViewById(R.id.namelayout2);
        textView1.setText(name);
        TextView textView2 = findViewById(R.id.designationlayout2);
        textView2.setText(designation);
        TextView textView3 = findViewById(R.id.datelayout2);
        textView3.setText(date);


        //Creating Fragments
        Medicines_Fragment medicineFragment = new Medicines_Fragment(Medicines);
        Vitals_Fragment vitalsFragment = new Vitals_Fragment(Vitals);
        Notes_Fragment notesFragment = new Notes_Fragment(Notes);

        //By Default displaying medicines
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.linearforfragments, medicineFragment);
        transaction.commit();


        //Getting Buttons
        medicine = findViewById(R.id.medicines);
        vitals = findViewById(R.id.vitals);
        notes = findViewById(R.id.notes);
        linearLayout = findViewById(R.id.linearforfragments);

        //Adding onClick Listener on Medicine Btn
        medicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.linearforfragments, medicineFragment);
                transaction.commit();
            }
        });

        //Adding onClick Listener on Vitals_Fragment Btn
        vitals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.linearforfragments, vitalsFragment);
                transaction.commit();
            }
        });

        //Adding onClick Listener on Notes_Fragment Btn
        notes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.linearforfragments, notesFragment);
                transaction.commit();
            }
        });
    }
}