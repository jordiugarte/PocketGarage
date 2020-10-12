package bo.com.golpistasElectricistas.pocketGarage.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import bo.com.golpistasElectricistas.pocketGarage.R;

public class MainActivity extends AppCompatActivity {

    private static final String LOG = LoginActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(LOG, "onCreate");
        setContentView(R.layout.activity_main);
    }
}