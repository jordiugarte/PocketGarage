package bo.com.golpistasElectricistas.pocketGarage.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Layout;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import bo.com.golpistasElectricistas.pocketGarage.R;

public class NewArticleActivity extends AppCompatActivity {
    private Context context;

    private List<ImageView> imageViews = new ArrayList<ImageView>();
    private LinearLayout imagesRow;

    private List<Bitmap> imageBitmaps = new ArrayList<Bitmap>();

    private Intent mainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_new_article);
        context = getApplicationContext();
        initViews();
        initIntents();
    }

    private void initIntents() {
        mainActivity = new Intent(context, MainActivity.class);
    }

    private void initViews() {
        imagesRow = findViewById(R.id.imagesArticleRow);
    }

    public void addPhoto(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Add picture"), 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                Bitmap newBitmap;
                newBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                imageBitmaps.add(newBitmap);
                imagesRow.addView(generateImageView(newBitmap));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private ImageView generateImageView(Bitmap bitmap) {
        ImageView imageView = new ImageView(this);
        imageView.requestLayout();
        /*imageView.getLayoutParams().width = 100;
        imageView.getLayoutParams().height = 100;*/
        imageView.setBackground(ContextCompat.getDrawable(context, R.drawable.gradient4));
        imageView.setImageBitmap(bitmap);
        imageViews.add(imageView);
        return imageView;
    }

    public void postArticle(View view) {
        //TODO
        startActivity(mainActivity);
    }

    public void returnToPrevious(View view) {
        finish();
    }
}