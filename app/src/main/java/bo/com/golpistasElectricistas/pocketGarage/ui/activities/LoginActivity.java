package bo.com.golpistasElectricistas.pocketGarage.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
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

    private Context context;
    private Intent mainActivity, registerActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(LOG, "onCreate");
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);
        context = getApplicationContext();
        initViews();
        initIntents();

        User user = new User("test123@email.com", "test123");
        Base<User> baseUser = new Base<>(user);
        List<User> users = new ArrayList<>();
        users.add(user);
        Base<List<User>> baseUsers = new Base<List<User>>(users);
    }

    private void initViews() {

    }

    private void initIntents() {
        mainActivity = new Intent(context, MainActivity.class);
        registerActivity = new Intent(context, RegisterActivity.class);
    }

    public void openRegisterActivity(View view) {
        startActivity(registerActivity);
    }

    public void openMainActivity(View view) {
        startActivity(mainActivity);
    }
}