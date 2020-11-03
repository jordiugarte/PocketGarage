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
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

import bo.com.golpistasElectricistas.pocketGarage.R;
import bo.com.golpistasElectricistas.pocketGarage.model.Base;
import bo.com.golpistasElectricistas.pocketGarage.model.User;
import bo.com.golpistasElectricistas.pocketGarage.repository.local.Local;
import bo.com.golpistasElectricistas.pocketGarage.utils.ErrorMapper;
import bo.com.golpistasElectricistas.pocketGarage.viewModel.LoginViewModel;

public class LoginActivity extends AppCompatActivity {

    private Context context;
    private Intent mainActivity, registerActivity;
    private EditText emailField;
    private EditText passwordField;
    private Button loginButton;
    private LoginViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        String email = emailField.getText().toString();
        String password = passwordField.getText().toString();
        viewModel.login(email, password).observeForever(new Observer<Base<User>>() {
            @Override
            public void onChanged(Base<User> userBase) {
                if (userBase.isSuccess()) {
                    User temp = new User();
                    temp.setEmail(email);
                    temp.setPassword(password);
                    temp.setCi(userBase.getData().getCi());
                    openNextActivity(temp);
                } else {
                    Snackbar.make(view, ErrorMapper.getError(context, userBase.getError()), Snackbar.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void openNextActivity(User user) {
        Intent intent = new Intent(context, MainActivity.class);
        new Local(getApplicationContext()).setCurrentUser(user);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}