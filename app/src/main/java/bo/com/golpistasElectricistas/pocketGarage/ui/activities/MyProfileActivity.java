package bo.com.golpistasElectricistas.pocketGarage.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

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