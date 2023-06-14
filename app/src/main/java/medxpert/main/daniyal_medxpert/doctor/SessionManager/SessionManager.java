package medxpert.main.daniyal_medxpert.doctor.SessionManager;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {
    private static final String PREF_NAME = "AppSession";
    private static final String KEY_IS_LOGGED_IN = "isLoggedIn";
    private static final String KEY_CNIC = "CNIC";
    private static final String KEY_password = "PASSWORD";
    private static final String KEY_USERTYPE="USERTYPE";
    private static final String KEY_DOCTORNAME="NAME";
    private static final String KEY_DOCTORSPECIALIZATION="SPECIALIZATION";


    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Context context;

    public SessionManager(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void setLoggedIn(boolean isLoggedIn,String cnic, String password,String user,String name,String specialization) {
        editor.putBoolean(KEY_IS_LOGGED_IN, isLoggedIn);
        editor.putString(KEY_CNIC,cnic);
        editor.putString(KEY_password,password);
        editor.putString(KEY_USERTYPE,user);
        editor.putString(KEY_DOCTORNAME,name);
        editor.putString(KEY_DOCTORSPECIALIZATION,specialization);
        editor.apply();
    }

    public String getUser(){
        return sharedPreferences.getString(KEY_USERTYPE,null);
    }

    public String getDoctorName(){
        return sharedPreferences.getString(KEY_DOCTORNAME,null);
    }

    public String getDoctorSpecialization(){
        return sharedPreferences.getString(KEY_DOCTORSPECIALIZATION,null);
    }

    public void setLoggedOut() {
        editor.remove(KEY_IS_LOGGED_IN);
        editor.remove(KEY_CNIC);
        editor.remove(KEY_password);
        editor.remove(KEY_USERTYPE);
        editor.remove(KEY_DOCTORNAME);
        editor.remove(KEY_DOCTORSPECIALIZATION);
        editor.apply();
    }


    public String getCNIC(){
        return sharedPreferences.getString(KEY_CNIC,null);
    }

    public String getPassword(){
        return sharedPreferences.getString(KEY_password,null);
    }

    public boolean isLoggedIn() {
        return sharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false);
    }
}
