package bo.com.golpistasElectricistas.pocketGarage.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;

import java.util.ArrayList;
import java.util.List;

import bo.com.golpistasElectricistas.pocketGarage.R;
import bo.com.golpistasElectricistas.pocketGarage.model.Base;
import bo.com.golpistasElectricistas.pocketGarage.model.User;

public class LoginActivity extends AppCompatActivity {

    private static final String LOG = LoginActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(LOG, "onCreate");
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);

        User user = new User("test123@email.com", "test123");
        Base<User> baseUser = new Base<>(user);
        List<User> users = new ArrayList<>();
        users.add(user);
        Base<List<User>> baseUsers = new Base<List<User>>(users);
    }

    public void openSecondActivity(View view) {
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
    }
}