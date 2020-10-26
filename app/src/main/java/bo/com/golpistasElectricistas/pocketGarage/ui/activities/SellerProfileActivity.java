package bo.com.golpistasElectricistas.pocketGarage.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import bo.com.golpistasElectricistas.pocketGarage.R;

public class SellerProfileActivity extends AppCompatActivity {

    private TextView name;
    private TextView age;
    private TextView phone;
    private TextView email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_seller_profile);
        initViews();
    }

    public void returnToPrevious(View view) {
        finish();
    }

    public void initViews() {
        name = findViewById(R.id.sellerName);
        name.setText("Jordi Ugarte");
        age = findViewById(R.id.sellerEdad);
        age.setText("25");
        email = findViewById(R.id.sellerEmail);
        email.setText("jordi@ugarte.com");
        phone = findViewById(R.id.sellerPhone);
        phone.setText("77777777");
    }
}