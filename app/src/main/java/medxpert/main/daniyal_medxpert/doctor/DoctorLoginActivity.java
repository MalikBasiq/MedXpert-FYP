package medxpert.main.daniyal_medxpert.doctor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import medxpert.main.daniyal_medxpert.R;
import medxpert.main.daniyal_medxpert.doctor.Database.doctor_Db_Handler;
import medxpert.main.daniyal_medxpert.doctor.SessionManager.SessionManager;



public class DoctorLoginActivity extends AppCompatActivity {

    private doctor_Db_Handler dbHandler;

    TextInputLayout cnicLayout;
    TextInputLayout passwordLayout;

    String cnic;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_login);

        //Getting data about session
        SessionManager sessionManager= new SessionManager(this);
        Toast.makeText(this, String.valueOf(sessionManager.isLoggedIn()), Toast.LENGTH_SHORT).show();

        if(sessionManager.isLoggedIn())
            startActivity(new Intent(this, doctor_dashboard.class));


        //Showing back button on toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        TextView textView = findViewById(R.id.register);
        String html = "<font color=#808080"  + ">Not a member? </font><font color= #0755E9"
                + "> Register Now!!</font>";
        textView.setText(Html.fromHtml(html));

    }

    public void loginBtnClicked(View view){

        cnicLayout = findViewById(R.id.cnic_EditText);
        passwordLayout = findViewById(R.id.password_EditText);

        cnic = cnicLayout.getEditText().getText().toString().trim();
        password = passwordLayout.getEditText().getText().toString().trim();

        if (cnic.isEmpty() || password.isEmpty()) {
            Toast.makeText(DoctorLoginActivity.this, "Please enter CNIC and password", Toast.LENGTH_SHORT).show();
        } else {
            performLogin(cnic, password);
        }

    }

    public void registerBtnClicked(View view){
        startActivity(new Intent(this, DoctorSignupActivity.class));
    }

    private void performLogin(String cnic, String password) {
        dbHandler = new doctor_Db_Handler("doctors");
        dbHandler.login(cnic, password, this, new doctor_Db_Handler.LoginCallback() {
            @Override
            public void onLoginSuccess() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(DoctorLoginActivity.this, "Logged In", Toast.LENGTH_SHORT).show();
                        //Session Managed in DBHandler
                        startActivity(new Intent(DoctorLoginActivity.this, doctor_dashboard.class));
                    }
                });
            }

            @Override
            public void onLoginFailure() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(DoctorLoginActivity.this, "CNIC or Password not correct", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }


}