package medxpert.main.daniyal_medxpert.patient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import medxpert.main.daniyal_medxpert.R;
import medxpert.main.daniyal_medxpert.patient.Database.Db_HandlerMedboxes;
import medxpert.main.daniyal_medxpert.patient.ModelMedicine.MedicineModel;
import medxpert.main.daniyal_medxpert.patient.POJO.MedBox;
import medxpert.main.daniyal_medxpert.patient.POJO.medBoxContents_Pojo;
import medxpert.main.daniyal_medxpert.patient.RecyclerViewAdapter.MedicineAdapter;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class Routine_Intake_Box extends AppCompatActivity {


    RecyclerView RoutineRecyclerView;
    LinearLayoutManager linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routine_intake_box);

//Toolbar code
        //getting title from intent

        MedBox medBox = (MedBox) getIntent().getSerializableExtra("Medbox");




        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setTitle(titlename);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        //setting title on run time
        TextView toolbarTitle = findViewById(R.id.toolbartitle);
        toolbarTitle.setText(medBox.getName());






//        medBoxContents_Pojo med1=new medBoxContents_Pojo("Panadol 5mg", 10);
//        medBoxContents_Pojo med2=new medBoxContents_Pojo("Brufin", 3);
//        medBoxContents_Pojo med3=new medBoxContents_Pojo("Imodium", 9);
//        medBoxContents_Pojo med4=new medBoxContents_Pojo("Disprin", 7);
//        medBoxContents_Pojo med5=new medBoxContents_Pojo("dawai 5mg", 10);
//        medBoxContents_Pojo med6=new medBoxContents_Pojo("dawai ", 3);
//        medBoxContents_Pojo med7=new medBoxContents_Pojo("dawai ", 9);
//        medBoxContents_Pojo med8=new medBoxContents_Pojo("dawai ", 7);
//        medBoxContents_Pojo med9=new medBoxContents_Pojo("Panadol ", 10);
//        medBoxContents_Pojo med10=new medBoxContents_Pojo("Brufin", 3);
//        medBoxContents_Pojo med11=new medBoxContents_Pojo("Imodium", 9);
//        medBoxContents_Pojo med12=new medBoxContents_Pojo("Disprin", 7);
//
//        routine_Intake_List.add(med1);
//        routine_Intake_List.add(med2);
//        routine_Intake_List.add(med3);
//        routine_Intake_List.add(med4);
//        routine_Intake_List.add(med5);
//        routine_Intake_List.add(med6);
//        routine_Intake_List.add(med7);
//        routine_Intake_List.add(med8);
//        routine_Intake_List.add(med9);
//        routine_Intake_List.add(med10);
//        routine_Intake_List.add(med11);
//        routine_Intake_List.add(med12);







        //Recycler view code to display medicing list and their quantity




        RoutineRecyclerView=findViewById(R.id.Routine_intake);

        MedicineAdapter routineIntakemanager = new MedicineAdapter(this, medBox.getMedBoxList() ); //passing array list "routine_Intake_List" inside it

        RoutineRecyclerView.setAdapter(routineIntakemanager);
        linearLayout = new LinearLayoutManager(this);
        RoutineRecyclerView.setLayoutManager(linearLayout);

        //todo send to pharmacy button everthing is done but app is crashing


        Button updatemedboxbtn = findViewById(R.id.updateMedbox);

        updatemedboxbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Db_HandlerMedboxes db_handlerMedboxes = new Db_HandlerMedboxes("Medboxes",Routine_Intake_Box.this);
                db_handlerMedboxes.updateDB(medBox);


                Intent intent = new Intent(Routine_Intake_Box.this, MainActivity.class);
//                intent.putExtra("SelectedItems", selectedItems);
                startActivity(intent);

                //Adding Medbox to the ArrayList
                // medBox.add(medBox);
                // my_array_Adapter.notifyDataSetChanged();

            }

//                Intent intent = new Intent(Routine_Intake_Box.this, SendPharmacy.class);
//                intent.putExtra("SelectedItems", selectedItems);
//                startActivity(intent);


        });

//        Button updatemedboxbtn = findViewById(R.id.updateMedbox);
//
//        updatemedboxbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                ArrayList<MedicineModel> selectedItems=new ArrayList<>();
//
//                if(medBox!=null)
//                {
//
//
//                    List<medBoxContents_Pojo> medBoxList=medBox.getMedBoxList();
//                    MedicineModel obj1;
//
//                    for(int i=0;i<medBox.getMedBoxList().size();i++)
//                    {
//                        String medname = medBoxList.get(i).getMedicinename();
//                        String qty = String.valueOf(medBoxList.get(i).getQuantity());
//
//                        //creating obj of pojo type
//
//                        obj1 =new MedicineModel(medname,qty,"0","0","","");
//                        selectedItems.add(obj1);
//
//                    }
//                        Intent intent = new Intent(Routine_Intake_Box.this, SendPharmacy.class);
//                        intent.putExtra("SelectedItems", selectedItems);
//                        startActivity(intent);
//
//                }
//
////                Intent intent = new Intent(Routine_Intake_Box.this, SendPharmacy.class);
////                intent.putExtra("SelectedItems", selectedItems);
////                startActivity(intent);
//
//            }
//        });


        //buttom navigation menu click listening:
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                switch (itemId) {
                    case R.id.Home:
                        Toast.makeText(Routine_Intake_Box.this, "Home clicked ", Toast.LENGTH_SHORT).show();
                        Intent i =new Intent(getApplicationContext(), dashboard.class);
                        startActivity(i);
                        // Handle Home item selection
                        // Navigate to the Home page or perform related actions
                        return true;
                    case R.id.Record:

                        Toast.makeText(Routine_Intake_Box.this, "Record clicked ", Toast.LENGTH_SHORT).show();

                        // Handle Record item selection
                        // Navigate to the Record page or perform related actions
                        return true;
                    case R.id.Account:
                        Toast.makeText(Routine_Intake_Box.this, "Account clicked", Toast.LENGTH_SHORT).show();



                        return true;
                }
                return false;
            }
        });




    }

//Todo Back Butttom implementation

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
}