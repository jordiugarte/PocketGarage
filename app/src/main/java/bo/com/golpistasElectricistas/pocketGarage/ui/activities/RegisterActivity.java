package bo.com.golpistasElectricistas.pocketGarage.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;

import bo.com.golpistasElectricistas.pocketGarage.R;
import bo.com.golpistasElectricistas.pocketGarage.model.Base;
import bo.com.golpistasElectricistas.pocketGarage.model.User;
import bo.com.golpistasElectricistas.pocketGarage.ui.fragments.DatePickerFragment;
import bo.com.golpistasElectricistas.pocketGarage.utils.ErrorMapper;
import bo.com.golpistasElectricistas.pocketGarage.viewModel.RegisterViewModel;
import de.hdodenhof.circleimageview.CircleImageView;

import static android.app.PendingIntent.getActivity;

public class RegisterActivity extends AppCompatActivity {

    private static final String LOG = LoginActivity.class.getName();
    private Context context;
    private Bitmap bitmap;
    private RelativeLayout parent;
    private CircleImageView profilePicture;
    private EditText nameText;
    private EditText lastNameText;
    private EditText emailText;
    private EditText passwordText;
    private EditText passwordConfirmText;
    private EditText ciText;
    private EditText dateText;
    private RegisterViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(LOG, "onCreate");
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_register);
        initViews();
        viewModel = new ViewModelProvider(this).get(RegisterViewModel.class);
        context = getApplicationContext();
    }

    private void initViews() {
        parent = findViewById(R.id.registerParent);
        profilePicture = findViewById(R.id.profileRegisterImage);
        nameText = findViewById(R.id.namesField);
        lastNameText = findViewById(R.id.lastnamesField);
        emailText = findViewById(R.id.emailRegisterField);
        passwordText = findViewById(R.id.passwordRegisterField);
        passwordConfirmText = findViewById(R.id.repeatPasswordField);
        ciText = findViewById(R.id.ciField);
        dateText = findViewById(R.id.dateField);
    }

    public void choosePhoto(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select picture"), 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                profilePicture.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }

            //UploadPicture();
        }
    }
/*
    public String getStringImage(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] imageByteArray = byteArrayOutputStream.toByteArray();
        String encodedImage = Base64.encodeToString(imageByteArray, Base64.DEFAULT);
        return encodedImage;
    }*/

    public void showDatePickerDialog(View view) {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 because January is zero
                final String selectedDate = day + " / " + (month + 1) + " / " + year;
                dateText.setText(selectedDate);
            }
        });

        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void register(View view) {
        String photo = "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSIxyT0DAa5_kwzb-e-bpTvAXIyW0OispA76Q&usqp=CAU";
        String name = nameText.getText().toString();
        String lastName = lastNameText.getText().toString();
        String email = emailText.getText().toString();
        String pass = passwordText.getText().toString();
        String pass2 = passwordConfirmText.getText().toString();
        String ci = ciText.getText().toString();
        String date = dateText.getText().toString();
        if (pass.equals(pass2)) {
            viewModel.register(photo, ci, email, pass, name, lastName, date).observeForever(new Observer<Base<User>>() {
                @Override
                public void onChanged(Base<User> userBase) {
                    if (userBase.isSuccess()) {
                        //Snackbar.make(view, "Usuario creado exitosamente", Snackbar.LENGTH_SHORT).show();
                        Log.e("Creado con exito", userBase.getData().toString());
                        Intent intent = new Intent(context, LoginActivity.class);
                        startActivity(intent);
                    } else {
                        Snackbar.make(view, ErrorMapper.getError(context, userBase.getError()), Snackbar.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            Snackbar.make(view, "Las contraseñas no coinciden.", Snackbar.LENGTH_SHORT).show();
        }
    }
}