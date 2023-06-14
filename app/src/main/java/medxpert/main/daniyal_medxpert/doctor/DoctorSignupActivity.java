package medxpert.main.daniyal_medxpert.doctor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.material.textfield.TextInputLayout;


import java.util.Calendar;

import medxpert.main.daniyal_medxpert.doctor.Database.doctor_Db_Handler;
import medxpert.main.daniyal_medxpert.R;
import medxpert.main.daniyal_medxpert.doctor.POJO.SignupUser_Pojo;
import medxpert.main.daniyal_medxpert.doctor.Validations.Validation;


public class DoctorSignupActivity extends AppCompatActivity {

    private TextInputLayout firstNameEditText;
    private TextInputLayout lastNameEditText;
    private TextInputLayout cnicEditText;
    private EditText dateOfBirthEditText;
    private RadioGroup genderRadioGroup;
    private TextInputLayout countryCodeEditText;
    private TextInputLayout phoneNumberEditText;
    private TextInputLayout passwordEditText;
    private TextInputLayout dateInputLayout;
    private TextInputLayout specializationInputLayout;

    String firstName;
    String lastName;
    String cnic;
    String dateOfBirth;
    String gender;
    String countryCode;
    String phoneNumber;
    String password;
    String specialization;

    private doctor_Db_Handler dbHandler;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_signup);



        dbHandler=new doctor_Db_Handler("doctors");

        //Showing back button on toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);



        // Initialize the EditText views
        firstNameEditText = findViewById(R.id.firstName_EditText);
        lastNameEditText = findViewById(R.id.lastName_EditText);
        cnicEditText = findViewById(R.id.cnic_EditText);
        countryCodeEditText = findViewById(R.id.countryCodeInputLayout);
        phoneNumberEditText = findViewById(R.id.phoneNumberInputLayout);
        passwordEditText = findViewById(R.id.password_EditText);
        dateOfBirthEditText = findViewById(R.id.dateOfBirthEditText);
        dateInputLayout = findViewById(R.id.dateOfBirthInputLayout);
        specializationInputLayout=findViewById(R.id.specialization_EditText);


        //Adding Listeners
        firstNameEditText.getEditText().setOnFocusChangeListener(firstNameFocusChangeListener);
        lastNameEditText.getEditText().setOnFocusChangeListener(lastNameFocusChangeListener);
        cnicEditText.getEditText().setOnFocusChangeListener(cnicFocusChangeListener);
        countryCodeEditText.getEditText().setOnFocusChangeListener(countryCodeFocusChangeListener);
        phoneNumberEditText.getEditText().setOnFocusChangeListener(phoneNumberFocusChangeListener);
        passwordEditText.getEditText().setOnFocusChangeListener(passwordFocusChangeListener);


    }


    public void moveToLogin(View view){
        this.startActivity(new Intent(this,DoctorLoginActivity.class));
    }

    //Function when register button is clicked
    public void signUp(View view) {

        //Clearing Focus of all the fields
        passwordEditText.clearFocus();


        // Validating Data
        if(!validateFields(view))
            return;

        // Create a data object to represent the user's data
        SignupUser_Pojo user = new SignupUser_Pojo(firstName, lastName, cnic,specialization, dateOfBirth, gender, countryCode, phoneNumber, password);
//
        // Write the data to the database using the DbHandler instance
        dbHandler.writeData(cnic, user);
//
        Toast.makeText(this, firstName+" "+lastName+" "+cnic+" "+gender+" "+dateOfBirth+" "+countryCode+" "+phoneNumber+" ", Toast.LENGTH_SHORT).show();
        Toast.makeText(this,password, Toast.LENGTH_SHORT).show();

        //Intent to move to the login page
        startActivity(new Intent(this, DoctorLoginActivity.class));
    }



    private boolean validateFields(View view) {

        // Validate First Name
        firstName = firstNameEditText.getEditText().getText().toString().trim();
        if(!Validation.validateName(firstName,firstNameEditText))
            return false;

        // Validate Last Name
        lastName = lastNameEditText.getEditText().getText().toString().trim();
        if(!Validation.validateName(lastName,lastNameEditText))
            return false;

        // Validate CNIC
        cnic = cnicEditText.getEditText().getText().toString().trim();
        if(!Validation.validateCNIC(cnic,cnicEditText))
            return false;

        // Validate Last Name
        specialization = specializationInputLayout.getEditText().getText().toString().trim();
        if(!Validation.validateName(specialization,specializationInputLayout))
            return false;

        // Validate date of birth
        dateOfBirth = dateOfBirthEditText.getText().toString();
        if(!Validation.validateDateOfBirth(dateOfBirth,dateOfBirthEditText))
            return false;

        // Validate gender
        genderRadioGroup = findViewById(R.id.genderRadioGroup);
        int selectedGenderId = genderRadioGroup.getCheckedRadioButtonId();
        TextView genderHelperText= findViewById(R.id.genderHelperText);
        if(!Validation.validateGender(selectedGenderId,genderHelperText))
            return false;
        gender = ((RadioButton) findViewById(genderRadioGroup.getCheckedRadioButtonId())).getText().toString();


        // Validate Country Code
        countryCode = countryCodeEditText.getEditText().getText().toString().trim();
        countryCode = "+"+countryCodeEditText.getEditText().getText().toString();
        if(!Validation.validateCountryCode(countryCode,countryCodeEditText))
            return false;

        // Validate Phone Number
        phoneNumber = phoneNumberEditText.getEditText().getText().toString().trim();
        if(!Validation.validatePhoneNumber(phoneNumber,phoneNumberEditText))
            return false;


        //Validate Password
        password = passwordEditText.getEditText().getText().toString().trim();
        if(!Validation.validatePassword(password,passwordEditText))
            return false;

        return true;
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
                            dateInputLayout = findViewById(R.id.dateOfBirthInputLayout);
                            dateInputLayout.setErrorEnabled(true);
                            dateInputLayout.setError("Invalid date");

                            // You can also set the helper text color to red
                            dateInputLayout.setHelperTextColor(ColorStateList.valueOf(Color.RED));
                        } else {
                            // Selected date is valid, update the date of birth EditText
                            String dateOfBirth = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                            dateOfBirthEditText.setText(dateOfBirth);

                            // Clear any existing error message
                            TextInputLayout dateInputLayout = findViewById(R.id.dateOfBirthInputLayout);
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



    //---------------Focus Listners-------------------------------------------
    // Define OnFocusChangeListener for each field
    private View.OnFocusChangeListener firstNameFocusChangeListener = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if (!hasFocus) {
                firstName = firstNameEditText.getEditText().getText().toString().trim();
                Validation.validateName(firstName,firstNameEditText);
            }
        }
    };


    private View.OnFocusChangeListener lastNameFocusChangeListener = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if (!hasFocus) {
                lastName = lastNameEditText.getEditText().getText().toString().trim();
                Validation.validateName(lastName,lastNameEditText);

            }
        }
    };

    private View.OnFocusChangeListener cnicFocusChangeListener = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if (!hasFocus) {
                cnic = cnicEditText.getEditText().getText().toString().trim();
                Validation.validateCNIC(cnic,cnicEditText);
            }
        }
    };


    private View.OnFocusChangeListener countryCodeFocusChangeListener = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if (!hasFocus) {

                countryCode = countryCodeEditText.getEditText().getText().toString().trim();
                countryCode = "+"+countryCodeEditText.getEditText().getText().toString();
                Validation.validateCountryCode(countryCode,countryCodeEditText);
            }
        }
    };

    private View.OnFocusChangeListener phoneNumberFocusChangeListener = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if (!hasFocus) {
                phoneNumber = phoneNumberEditText.getEditText().getText().toString().trim();
                Validation.validatePhoneNumber(phoneNumber,phoneNumberEditText);

            }
        }
    };

    private View.OnFocusChangeListener passwordFocusChangeListener = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if (!hasFocus) {
                password = passwordEditText.getEditText().getText().toString().trim();
                Validation.validatePassword(password,passwordEditText);

            }
        }

    };



}