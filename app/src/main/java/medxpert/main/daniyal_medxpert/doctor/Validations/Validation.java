package medxpert.main.daniyal_medxpert.doctor.Validations;


import android.content.res.ColorStateList;
import android.graphics.Color;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;


public class Validation {


    //Function to validate Name
    public static boolean validateName(String Name, TextInputLayout NameEditText) {
        if (Name.isEmpty() || !Name.matches("[a-zA-Z ]+")) {
            NameEditText.setError("First name is required");
            return false;
        } else {
            NameEditText.setError(null);

            return true;
        }
    }


    // Function to validate CNIC
    public static boolean validateCNIC(String cnic,TextInputLayout cnicEditText) {
        // Return true if the CNIC is valid, false otherwise
        if (!cnic.matches("[0-9]{13}") || cnic.isEmpty()) {
            cnicEditText.setError("Invalid CNIC - Must be 13 numbers without dashes");
            return false;
        } else {
            cnicEditText.setError(null);
        }

        return true;
    }


    // Function to validate country code
    public static boolean validateCountryCode(String countryCode,TextInputLayout countryCodeEditText) {
        // Perform validation here

        if (!countryCode.matches("^[+][0-9]{1,3}$") || countryCode.isEmpty()) {
            countryCodeEditText.setError("Invalid country code");
            return false;
        } else {
            countryCodeEditText.setError(null);
            return true;
        }

    }

    // Function to validate phone number
    public static boolean validatePhoneNumber(String phoneNumber, TextInputLayout phoneNumberEditText) {

        if (!phoneNumber.matches("[0-9]+") || phoneNumber.isEmpty()) {
            phoneNumberEditText.setError("Invalid phone number");
            return false;
        } else {
            phoneNumberEditText.setError(null);
        }
        return true;
    }

    // Function to validate password
    public static boolean validatePassword(String password,TextInputLayout passwordEditText) {

        if (!(password.length() >= 8)|| password.isEmpty()) {
            passwordEditText.setError("Invalid password - Must be 8 characters atleast ");
            return false;
        } else {
            passwordEditText.setError(null);
        }
        return true;
    }

    public static boolean validateGender(int selectedGenderId, TextView genderHelperText){
        if (selectedGenderId == -1) {
            genderHelperText.setTextColor(Color.RED);
            return false;
        }
        else
            genderHelperText.setTextColor(ColorStateList.valueOf(0xFF808080));

        return true;

    }

    public static boolean validateDateOfBirth(String dateOfBirth, EditText dateOfBirthEditText){
        if (dateOfBirth.isEmpty()) {
            dateOfBirthEditText.setError("Invalid date");
            return false;
        } else {
            dateOfBirthEditText.setError(null);
        }
        return true;
    }



}
