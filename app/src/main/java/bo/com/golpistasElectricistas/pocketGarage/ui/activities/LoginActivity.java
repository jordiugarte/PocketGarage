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

        User user = new User("andres.vasquez@email.com", "test123");

        Base<User> baseUser = new Base<>(user);
        Log.e(LOG + ".baseUser.success", "" + baseUser.isSuccess());
        Log.e(LOG + ".baseUser.email", "" + baseUser.getData().getEmail());

        List<User> users = new ArrayList<>();
        User user2 = new User("benjamin.soto@email.com", "test123");
        users.add(user);
        users.add(user2);

        Base<List<User>> baseUsers = new Base<List<User>>(users);
        Log.e(LOG + ".baseUsers.success", "" + baseUsers.isSuccess());
        for (User userInTheList : baseUsers.getData()) {
            Log.e(LOG + ".baseUsers.success", "" + userInTheList.getEmail());
        }
    }

    public void openSecondActivity(View view) {
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
    }
}