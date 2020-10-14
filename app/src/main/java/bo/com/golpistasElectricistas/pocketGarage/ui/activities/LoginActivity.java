package bo.com.golpistasElectricistas.pocketGarage.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import bo.com.golpistasElectricistas.pocketGarage.R;
import bo.com.golpistasElectricistas.pocketGarage.model.Base;
import bo.com.golpistasElectricistas.pocketGarage.model.User;
import bo.com.golpistasElectricistas.pocketGarage.viewModel.LoginViewModel;

public class LoginActivity extends AppCompatActivity {

    private static final String LOG = LoginActivity.class.getName();

    private Context context;
    private Intent mainActivity, registerActivity;
    private TextInputLayout emailField;
    private TextInputLayout passwordField;
    private Button loginButton;
    private LoginViewModel viewModel;

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
    }

    private void initViews() {
        emailField = findViewById(R.id.emailField);
        passwordField = findViewById(R.id.passwordField);
        loginButton = findViewById(R.id.loginButton);
        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);
    }

    private void initIntents() {
        mainActivity = new Intent(context, MainActivity.class);
        registerActivity = new Intent(context, RegisterActivity.class);
    }

    public void openRegisterActivity(View view) {
        startActivity(registerActivity);
    }

    public void openMainActivity(View view) {
        String email = emailField.getEditText().getText().toString();
        String password = passwordField.getEditText().getText().toString();
        viewModel.login(email, password).observeForever(new Observer<Base<User>>() {
            @Override
            public void onChanged(Base<User> userBase) {
                if (userBase.isSuccess()) {
                    startActivity(mainActivity);
                } else {
                    Snackbar.make(view,userBase.getError(),Snackbar.LENGTH_SHORT).show();
                }
            }
        });
    }
}