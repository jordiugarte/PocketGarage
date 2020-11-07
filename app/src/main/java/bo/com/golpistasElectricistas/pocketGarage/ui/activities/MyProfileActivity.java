package bo.com.golpistasElectricistas.pocketGarage.ui.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.Window;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ShareActionProvider;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.Locale;

import bo.com.golpistasElectricistas.pocketGarage.R;
import bo.com.golpistasElectricistas.pocketGarage.model.Article;
import bo.com.golpistasElectricistas.pocketGarage.model.User;
import bo.com.golpistasElectricistas.pocketGarage.repository.firebase.db.FirebaseDBManager;
import bo.com.golpistasElectricistas.pocketGarage.repository.local.Local;
import bo.com.golpistasElectricistas.pocketGarage.repository.local.SharedPreferencesService;
import bo.com.golpistasElectricistas.pocketGarage.utils.Constants;
import bo.com.golpistasElectricistas.pocketGarage.viewModel.MyProfileViewModel;

public class MyProfileActivity extends AppCompatActivity {

    private Context context;

    private ImageView profilePicture;
    private TextView namesLabel, ageLabel, addressLabel, numberLabel, emailLabel;

    private Intent profileEditorActivity, loginActivity;

    private MyProfileViewModel viewModel;

    private String nombreCompleto;
    private int cel;
    private String email;

    //string del path de la foto
    private String photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        getData();
        setContentView(R.layout.activity_my_profile);
        context = getApplicationContext();
        viewModel = new ViewModelProvider(this).get(MyProfileViewModel.class);
        initIntents();
        initViews();


        loadLocale();

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getResources().getString(R.string.app_name));
    }
    private void getData(){
        SharedPreferencesService preferences = new SharedPreferencesService(this);
        if(preferences.getCurrentUser().getName() != null || preferences.getCurrentUser().getLastName() != null){
            nombreCompleto= preferences.getCurrentUser().getName() + " " + preferences.getCurrentUser().getLastName();
        }
        
        cel = preferences.getCurrentUser().getPhone();
        email = preferences.getCurrentUser().getEmail();
        //path
        photo = preferences.getCurrentUser().getPhoto();

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

        //Con uri
        //profilePicture.setImageURI(uri);

        namesLabel = findViewById(R.id.profileNames);
        if(nombreCompleto != null) {
            namesLabel.setText(nombreCompleto);
        }
        ageLabel = findViewById(R.id.profileAge);
        addressLabel = findViewById(R.id.profileAddress);

        numberLabel = findViewById(R.id.profileNumber);
        String cel2 = String.valueOf(cel);
        numberLabel.setText(cel2);

        emailLabel = findViewById(R.id.profileEmail);
        if(email != null){
            emailLabel.setText(email);
        }
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

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public int getCel() {
        return cel;
    }

    public void setCel(int cel) {
        this.cel = cel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}