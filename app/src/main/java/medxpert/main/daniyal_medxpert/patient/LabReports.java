package medxpert.main.daniyal_medxpert.patient;

import androidx.activity.result.ActivityResultLauncher;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.textfield.TextInputLayout;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import medxpert.main.daniyal_medxpert.R;
import medxpert.main.daniyal_medxpert.patient.Adapters.LabReport_Adapter;
import medxpert.main.daniyal_medxpert.patient.Database.Db_HandlerLabTest;
import medxpert.main.daniyal_medxpert.patient.POJO.Report;
import medxpert.main.daniyal_medxpert.patient.SessionManager.SessionManager;

public class LabReports extends AppCompatActivity {


    private TextView imageText;
    private Bitmap imageBitmap;
    private boolean isImageSet = false;
    //    private Report report;
    private Uri uri = null;
    private EditText dateOfBirthEditText;
    private TextInputLayout dateInputLayout;


    private ActivityResultLauncher<Intent> cameraLauncher;
    private ActivityResultLauncher<Intent> galleryLauncher;
    private RecyclerView recyclerView;
    List<Report> reportList;
    List<String> reportId;

    LabReport_Adapter labReport_Adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_reports);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        //RecycleView
        recyclerView = findViewById(R.id.recyclerView);


        //Getting Data from Firebase
        reportList = new ArrayList<>();
        // Call the function to get all reports for the patient
        labReport_Adapter = new LabReport_Adapter(reportList, this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(labReport_Adapter);



//        db_Handler.getReports(this, new Db_HandlerLabTest.OnReportsRetrievedListener() {
//            @Override
//            public void onReportsRetrieved(List<String> reportIds) {
//                reportId = reportIds;
//                Toast.makeText(LabReports.this, reportId.toString(), Toast.LENGTH_SHORT).show();
//
//                db_Handler.getAllReportsForPatient(LabReports.this, reportIds, new Db_HandlerLabTest.OnReportObjectsRetrievedListener() {
//                    @Override
//                    public void onReportObjectsRetrieved(List<Report> reports) {
//                        reportList.clear();
//                        reportList.addAll(reports);
//
//                        Toast.makeText(LabReports.this, reports.toString(), Toast.LENGTH_SHORT).show();
//                        labReport_Adapter.notifyDataSetChanged();
//                    }
//
//                    @Override
//                    public void onReportObjectsFailed(String errorMessage) {
//
//                    }
//                });
//            }
//
//            @Override
//            public void onReportsRetrievalFailed(String errorMessage) {
//                // Handle the failure case
//            }
//        });


        Db_HandlerLabTest db_Handler = new Db_HandlerLabTest("Reports");
        db_Handler.getReportsForCNIC(new SessionManager(this).getCNIC(), new Db_HandlerLabTest.OnReportObjectsRetrievedListener() {
            @Override
            public void onReportObjectsRetrieved(List<Report> reports) {
                reportList.clear();
                reportList.addAll(reports);

                findViewById(R.id.progressBar).setVisibility(View.GONE);
                Toast.makeText(LabReports.this, reports.toString(), Toast.LENGTH_SHORT).show();
                labReport_Adapter.notifyDataSetChanged();
            }

            @Override
            public void onReportObjectsFailed(String errorMessage) {

            }
        });


        // Initialize the activity result launchers

    }

    public void addReportsBtnClicked(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.labreport_dialogbox_design, null);
        dateOfBirthEditText = dialogView.findViewById(R.id.dateEditText);
        dateInputLayout = dialogView.findViewById(R.id.dateInputLayout);


        builder.setView(dialogView);

        final EditText nameEditText = dialogView.findViewById(R.id.nameEditText);
        final EditText dateEditText = dialogView.findViewById(R.id.dateEditText);
        imageText = dialogView.findViewById(R.id.imageTxt);
        final Button attachPictureButton = dialogView.findViewById(R.id.attachPictureButton);

        final Dialog dialog = builder.create(); // Declare dialog as final

        attachPictureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ImagePicker.with(LabReports.this)
                        .compress(1024)            //Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)    //Final image resolution will be less than 1080 x 1080(Optional)
                        .start();


                Toast.makeText(LabReports.this, "Attach Picture clicked", Toast.LENGTH_SHORT).show();
            }
        });


        Button confirmButton = dialogView.findViewById(R.id.confirmButton);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Report report = new Report();
                if (nameEditText.getText().toString().equals("") || dateInputLayout.getEditText().getText().toString().equals("") || uri == null)
                    Toast.makeText(LabReports.this, "Enter Name, Date and attach image", Toast.LENGTH_SHORT).show();
                else {
                    report.setName(nameEditText.getText().toString());
                    report.setDate(dateInputLayout.getEditText().getText().toString());
                    report.setPatientCNIC(new SessionManager(LabReports.this).getCNIC());

                    //Conveting bitmap to encoded string
                    Bitmap bitmap = getBitmapFromUri(uri); // Your Bitmap object
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                    byte[] byteArray = baos.toByteArray();
                    String encodedBitmap = Base64.encodeToString(byteArray, Base64.DEFAULT);
                    report.setImage(encodedBitmap);

                    //Adding Data to Firebase
                    Db_HandlerLabTest db_handlerLabTest = new Db_HandlerLabTest("Reports");
                    db_handlerLabTest.addReportToDB(report, LabReports.this);


                    //Adding the Report to  ArrayList
                    reportList.add(report);

                    //Notifying the elements are changed
                    labReport_Adapter.notifyDataSetChanged();


                    Toast.makeText(LabReports.this, report.toString(), Toast.LENGTH_SHORT).show();
                    // Perform further actions like saving the report
                    // You can also add code to handle the picture attachment here
                    uri = null;
                }

                dialog.dismiss();
            }
        });

        Button cancelButton = dialogView.findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uri = null;
//                dateInputLayout=null;
                dialog.dismiss();
            }
        });

        dialog.show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        uri = data.getData();
        if (uri != null)
            imageText.setText(uri.toString());
    }

    public Bitmap getBitmapFromUri(Uri uri) {
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
            return bitmap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void showDatePicker(View view) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        // Create a Calendar object for the selected date
                        Calendar selectedDate = Calendar.getInstance();
                        selectedDate.set(year, monthOfYear, dayOfMonth);

                        // Get the current date
                        Calendar currentDate = Calendar.getInstance();


                        if (selectedDate.after(currentDate)) {
                            // If selected date is after the current date, show error message
//                            dateInputLayout = findViewById(R.id.dateOfBirthInputLayout);
                            dateInputLayout.setErrorEnabled(true);
                            dateInputLayout.setError("Invalid date");

                            // You can also set the helper text color to red
                            dateInputLayout.setHelperTextColor(ColorStateList.valueOf(Color.RED));
                        } else {
                            // Selected date is valid, update the date of birth EditText
                            String dateOfBirth = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                            dateOfBirthEditText.setText(dateOfBirth);

                            // Clear any existing error message
//                            TextInputLayout dateInputLayout = findViewById(R.id.dateOfBirthInputLayout);
                            dateInputLayout.setErrorEnabled(false);
                            dateInputLayout.setError(null);

                            // Set the default color to the helper text
                            dateInputLayout.setHelperTextColor(ColorStateList.valueOf(0xFF808080));
                        }
                    }
                },
                year,
                month,
                day
        );

        datePickerDialog.show();
    }
}
