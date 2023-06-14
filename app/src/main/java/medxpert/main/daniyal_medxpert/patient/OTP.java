package medxpert.main.daniyal_medxpert.patient;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

import medxpert.main.daniyal_medxpert.R;

public class OTP extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);


        TextView textView = findViewById(R.id.resend);
        String html = "<font color= #757575" + ">Didn't receive the code? </font><font color= #0755E9"
                + "> Resend</font>";
        textView.setText(Html.fromHtml(html));


        //Showing back button on toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(0);




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
}