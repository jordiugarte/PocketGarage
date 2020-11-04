package bo.com.golpistasElectricistas.pocketGarage.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

import bo.com.golpistasElectricistas.pocketGarage.R;
import bo.com.golpistasElectricistas.pocketGarage.model.Base;
import bo.com.golpistasElectricistas.pocketGarage.model.User;
import bo.com.golpistasElectricistas.pocketGarage.viewModel.SplashScreenViewModel;

public class SplashScreenActivity extends AppCompatActivity {

    private SplashScreenViewModel viewModel;
    private Intent mainActivity, loginActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_splash_screen);
        viewModel = new SplashScreenViewModel(getApplication());
        initIntents();

        try {
            String email = viewModel.getLoggedUser().getEmail();
            String password = viewModel.getLoggedUser().getPassword();
            viewModel.login(email, password).observeForever(new Observer<Base<User>>() {
                @Override
                public void onChanged(Base<User> userBase) {
                    if (userBase.isSuccess()) {
                        startActivity(mainActivity);
                    } else {
                        startActivity(loginActivity);
                    }
                }
            });
        } catch (Exception e) {
            startActivity(loginActivity);
        }
    }

    private void initIntents() {
        mainActivity = new Intent(getApplicationContext(), MainActivity.class);
        mainActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        loginActivity = new Intent(getApplicationContext(), LoginActivity.class);
        loginActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
    }
}