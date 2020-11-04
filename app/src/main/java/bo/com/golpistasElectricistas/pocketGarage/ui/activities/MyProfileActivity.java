package bo.com.golpistasElectricistas.pocketGarage.ui.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;

import bo.com.golpistasElectricistas.pocketGarage.R;
import bo.com.golpistasElectricistas.pocketGarage.model.User;
import bo.com.golpistasElectricistas.pocketGarage.repository.local.Local;
import bo.com.golpistasElectricistas.pocketGarage.viewModel.MyProfileViewModel;

public class MyProfileActivity extends AppCompatActivity {

    private Context context;

    private ImageView profilePicture;
    private TextView namesLabel, ageLabel, addressLabel, numberLabel, emailLabel;

    private Intent profileEditorActivity, loginActivity;

    private MyProfileViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_my_profile);
        context = getApplicationContext();
        viewModel = new ViewModelProvider(this).get(MyProfileViewModel.class);
        initIntents();
        initViews();

        loadLocale();

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getResources().getString(R.string.app_name));
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

    public void signOut(View view) {
        new Local(getApplicationContext()).setCurrentUser(new User());
        loginActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        finish();
        startActivity(loginActivity);
        viewModel.signOut();
    }
}