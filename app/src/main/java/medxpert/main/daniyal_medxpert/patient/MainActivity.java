package medxpert.main.daniyal_medxpert.patient;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import medxpert.main.daniyal_medxpert.R;
import medxpert.main.daniyal_medxpert.doctor.MedicineModel_doctor;
import medxpert.main.daniyal_medxpert.patient.Database.Db_HandlerLabTest;
import medxpert.main.daniyal_medxpert.patient.Database.Db_HandlerMedboxes;

import medxpert.main.daniyal_medxpert.patient.POJO.MedBox;
import medxpert.main.daniyal_medxpert.patient.POJO.medBoxContents_Pojo;
import medxpert.main.daniyal_medxpert.patient.SessionManager.SessionManager;
import medxpert.main.daniyal_medxpert.patient.SessionManager.SessionManager;

public class MainActivity extends AppCompatActivity {

//    private AppBarConfiguration appBarConfiguration;
//private ActivityMainBinding binding;
    //BottomNavigationView bottom_navigation;

    ListView mlistView;

    ArrayList<MedBox> my_array_list;

    ArrayAdapter<MedBox> my_array_Adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();

        ArrayList<MedicineModel_doctor> SelectedItems = (ArrayList<MedicineModel_doctor>) intent.getSerializableExtra("SelectedItems");
        boolean coming_from_medical_record = getIntent().getBooleanExtra("coming_from_medical_record", false);

        //Boolean coming_from_medical_record = intent.getBooleanExtra("coming_from_medical_record");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        //bottom navigation by chat gpt //todo Single click test
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                switch (itemId) {
                    case R.id.Home:
                        Toast.makeText(MainActivity.this, "Home clicked ", Toast.LENGTH_SHORT).show();
                        Intent i =new Intent(getApplicationContext(), dashboard.class);
                        startActivity(i);
                        // Handle Home item selection
                        // Navigate to the Home page or perform related actions
                        return true;
                    case R.id.Record:

                        Toast.makeText(MainActivity.this, "Record clicked ", Toast.LENGTH_SHORT).show();


                        return true;
                    case R.id.Account:
                        Toast.makeText(MainActivity.this, "Account clicked", Toast.LENGTH_SHORT).show();



                        return true;
                }
                return false;
            }
        });



//
        //list display starts here

        //adding hardcored medbox



        my_array_list=new ArrayList<MedBox>();

        my_array_Adapter=new ArrayAdapter<MedBox>(this, android.R.layout.simple_list_item_1,my_array_list){

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
                }
                MedBox temp= (MedBox) getItem(position);

                TextView textView = convertView.findViewById(android.R.id.text1);
                textView.setText(temp.getName());


                return convertView;
            }

        };
        mlistView=findViewById(R.id.medical_box_list);
        mlistView.setAdapter(my_array_Adapter);



        //todo retriving data from firebase
        Db_HandlerMedboxes db_handlerMedboxes = new Db_HandlerMedboxes("Medboxes",MainActivity.this);
        // Example usage: Retrieving medboxes for the currently logged-in user

        String currentUserCNIC = new SessionManager(MainActivity.this).getCNIC(); //getting logged in user's cnic


        db_handlerMedboxes.getAllMedboxes(currentUserCNIC, new Db_HandlerMedboxes.GetAllMedboxesCallback()
        {
            @Override
            public void onMedboxesReceived(List<MedBox> medboxes) {
                // Update the ArrayAdapter with the retrieved medboxes
                //my_array_list.clear();
                my_array_list.addAll(medboxes);
                my_array_Adapter.notifyDataSetChanged();
            }

            @Override
            public void onNoMedboxes() {

                Toast.makeText(getApplicationContext(),"No Med boxes were added. ",Toast.LENGTH_SHORT).show();
                // Handle the case when no medboxes are found for the user
            }

            @Override
            public void onCancelled(String errorMessage) {
                // Handle the case when an error occurs
            }
        });





        //todo handling database starting from here

        //database  connection

        Db_HandlerLabTest db_Handler = new Db_HandlerLabTest("Medboxes");

        //detecting click on list items
        mlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int item_position, long id) {




                //int listsize =my_array_list.get(item_position).getListSize();
//                Toast.makeText(MainActivity.this, my_array_list.get(item_position).getName()+ " clicked and the list size is"+ listsize , Toast.LENGTH_SHORT).show();

                //Toast.makeText(MainActivity.this, my_array_list.get(item_position).getName()+ " clicked and the list size is = " + listsize , Toast.LENGTH_SHORT).show();
//
                medBoxContents_Pojo obj1;
                MedBox medobj =my_array_list.get(item_position);

                if(SelectedItems!=null && coming_from_medical_record!=false)//if coming from medical record
                {
                    for(int i=0;i<SelectedItems.size();i++)
                    {
                        String medname= SelectedItems.get(i).getMedicineName();
                        int qty = Integer.parseInt(SelectedItems.get(i).getMorningQuantity()) + Integer.parseInt(SelectedItems.get(i).getEveningQuantity())+Integer.parseInt(SelectedItems.get(i).getNightQuantity());

                        //creating obj of pojo type
                        obj1 = new medBoxContents_Pojo(medname ,qty);
                        medobj.addToList(obj1);

                        //2nd method creat a list of type medBoxContents_Pojo and after loop add that list to medobj

                    }
                }


                Intent i = new Intent(MainActivity.this, Routine_Intake_Box.class);
                i.putExtra("Medbox", medobj); //passing medbox in intent

                startActivity(i);


            }
        });

        mlistView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                // Deleting item on long click by showing an alert box
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Are you sure you want to delete " + my_array_list.get(position).getName() + " from the list?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String medboxKey = my_array_list.get(position).medboxID;
                                // Remove item from local list
                                my_array_list.remove(position);
                                my_array_Adapter.notifyDataSetChanged();

                                // Remove item from Firebase
                                Db_HandlerMedboxes db_handlerMedboxes = new Db_HandlerMedboxes("Medboxes", MainActivity.this);
                                db_handlerMedboxes.deleteMedboxFromDB(medboxKey, new Db_HandlerMedboxes.DeleteMedboxCallback() {
                                    @Override
                                    public void onMedboxDeleted() {
                                        Toast.makeText(MainActivity.this, "Medbox deleted successfully", Toast.LENGTH_SHORT).show();
                                        //showToast("Medbox deleted successfully");
                                    }

                                    @Override
                                    public void onDeleteFailed(String errorMessage) {
                                        Toast.makeText(MainActivity.this, "Failed to delete medbox", Toast.LENGTH_SHORT).show();

                                    }
                                });
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).create().show();
                return true; // Return true to consume the long click event
            }
        });



        //TODO  done hehe
        //add a floating button to add more medical boxes by using alert box
        // and use:  my_array_list.add(input coming from alert box); //to update arraylist
        //   my_array_Adapter.notifyDataSetChanged(); //to update adapter

        //handling floating button click
        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showInputDialog();  // function below
            }
        });

    } //on create ending here

    private void showInputDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter MedBox Name");

        final EditText input = new EditText(this);
        builder.setView(input);

        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String name = input.getText().toString();


                if(name.equals(""))
                {
                    Toast.makeText(MainActivity.this, "Enter Medbox Name it cannot be empty", Toast.LENGTH_SHORT).show();
                    dialog.cancel();
                }
                else
                {
                    //Creating Medbox object
                    MedBox medBox=new MedBox();

                    medBox.setName(name);



//                  medBoxContents_Pojo medBoxContents = new medBoxContents_Pojo("panadol 50mg", 10);
//////
//////// Adding medBoxContents_Pojo to the MedBox's medBoxList
//                    medBox.addToList(medBoxContents);

//                    Toast.makeText(MainActivity.this,"medlist 1st element= "+ medBox.medBoxList.size(), Toast.LENGTH_SHORT).show();



                    //Adding Data to Firebase
                    Db_HandlerMedboxes db_handlerMedboxes = new Db_HandlerMedboxes("Medboxes",MainActivity.this);
                    db_handlerMedboxes.addMedboxToDB(medBox);


                    //Adding Medbox to the ArrayList
                    my_array_list.add(medBox);
                    my_array_Adapter.notifyDataSetChanged();
                    // Do something with the entered text
                }



            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    // home back button
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

