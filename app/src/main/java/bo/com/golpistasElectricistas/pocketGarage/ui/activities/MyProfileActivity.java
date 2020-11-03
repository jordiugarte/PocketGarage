package bo.com.golpistasElectricistas.pocketGarage.ui.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.LocaleList;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;

import bo.com.golpistasElectricistas.pocketGarage.R;

public class MyProfileActivity extends AppCompatActivity {

    private Context context;

    private ImageView profilePicture;
    private TextView namesLabel, ageLabel, addressLabel, numberLabel, emailLabel;

    private Intent profileEditorActivity, loginActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_my_profile);
        context = getApplicationContext();
        initIntents();
        initViews();

        loadLocale();

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getResources().getString(R.string.app_name));
        Button changeLanguage = findViewById(R.id.changeLanguage);
        changeLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChangeDialog();
            }
        });
    }

    private void showChangeDialog() {
        final String[] listItems= {"English", "Español"};
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(this);
        mBuilder.setTitle("Choose language");
        mBuilder.setSingleChoiceItems(listItems, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                if(i == 0){
                    setLocale("en");
                    recreate();
                }
               else if (i==1){
                    setLocale("es");
                    recreate();
                }
                dialog.dismiss();
            }
        });
        AlertDialog mDialog = mBuilder.create();
        mDialog.show();
    }

    private void setLocale(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());

        SharedPreferences.Editor editor = getSharedPreferences("Settings",MODE_PRIVATE).edit();
        editor.putString("My_Lang", lang);
        editor.apply();
    }
    public void loadLocale(){
        SharedPreferences prefs = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String language= prefs.getString("My_Lang", "");
        setLocale(language);
    }

    private void initIntents() {
        profileEditorActivity = new Intent(context, ProfileEditorActivity.class);
        loginActivity = new Intent(context, LoginActivity.class);
    }

    private void initViews() {
        profilePicture = findViewById(R.id.profileImage);
        namesLabel = findViewById(R.id.profileNames);
        ageLabel = findViewById(R.id.profileAge);
        addressLabel = findViewById(R.id.profileAddress);
        numberLabel = findViewById(R.id.profileNumber);
        emailLabel = findViewById(R.id.profileEmail);
    }

    public void returnToPrevious(View view) {
        finish();
    }

    public void goToEditProfileActivity(View view) {
        startActivity(profileEditorActivity);
    }

    public void logOut(View view) {
        startActivity(loginActivity);
        //TODO
    }
}