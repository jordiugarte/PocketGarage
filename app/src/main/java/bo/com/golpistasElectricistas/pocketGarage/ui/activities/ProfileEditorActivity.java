package bo.com.golpistasElectricistas.pocketGarage.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.IOException;

import bo.com.golpistasElectricistas.pocketGarage.R;

public class ProfileEditorActivity extends AppCompatActivity {

    private ImageView profilePicture;
    private EditText nameField, ageField, addressField, numberField, emailField;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_profile_editor);
        initViews();
    }

    private void initViews() {
        profilePicture = findViewById(R.id.editProfileImageButton);
        nameField = findViewById(R.id.namesEditProfileField);
        ageField = findViewById(R.id.ageEditProfileField);
        addressField = findViewById(R.id.addressEditProfileField);
        numberField = findViewById(R.id.numberEditPofileField);
        emailField = findViewById(R.id.emailField);
    }

    public void saveChanges(View view) {
        //TODO
        finish();
    }

    public void chooseNewPhoto(View view) {
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

    public void returnToPrevious(View view) {
        finish();
    }
/*
    public String getStringImage(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] imageByteArray = byteArrayOutputStream.toByteArray();
        String encodedImage = Base64.encodeToString(imageByteArray, Base64.DEFAULT);
        return encodedImage;
    }*/
}