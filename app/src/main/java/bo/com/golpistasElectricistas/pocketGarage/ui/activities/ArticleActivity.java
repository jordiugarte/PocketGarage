package bo.com.golpistasElectricistas.pocketGarage.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import bo.com.golpistasElectricistas.pocketGarage.R;
import bo.com.golpistasElectricistas.pocketGarage.model.Article;

public class ArticleActivity extends AppCompatActivity {

    private Context context;

    private Intent sellerProfileActivity;

    private TextView articleName, articleTitle, articlePrice, articleState, articleShortDescription, articleDescription;
    private CarouselView carouselView;

    private Article thisArticle;

    String[] sampleImages = {
            "https://scontent.flpb2-2.fna.fbcdn.net/v/t1.0-9/121192448_3711992668812129_3055426446455598271_o.jpg?_nc_cat=106&_nc_sid=730e14&_nc_ohc=9xzNn9kpN_AAX-MJRXY&_nc_ht=scontent.flpb2-2.fna&oh=75800d9f014704494f4ede158f313c3b&oe=5FAAD43D",
            "https://scontent.flpb2-1.fna.fbcdn.net/v/t1.0-9/121021076_3702364973189322_7247070921577086116_o.jpg?_nc_cat=102&_nc_sid=730e14&_nc_ohc=A8mjKOJWXzgAX8JYwf3&_nc_ht=scontent.flpb2-1.fna&oh=f1ba19fb8b37b8fe02aa4d7e4fc9fae2&oe=5FABF2EA",
            "https://scontent.flpb2-1.fna.fbcdn.net/v/t1.0-9/106571277_1453836774811044_5992646281856668927_n.jpg?_nc_cat=110&_nc_sid=730e14&_nc_ohc=7b9a8hvn32EAX9scXvj&_nc_ht=scontent.flpb2-1.fna&oh=46c15bfbb5ab245ef6242e96dc37600b&oe=5FAC2C3C"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_article);
        context = getApplicationContext();
        setIntents();
        initViews();
    }

    private void setIntents() {
        sellerProfileActivity = new Intent(context, SellerProfileActivity.class);
    }

    private void initViews() {
        articleName = findViewById(R.id.articleName);
        articleTitle = findViewById(R.id.articleTitle);
        articlePrice = findViewById(R.id.aticlePrice);
        articleState = findViewById(R.id.articleState);
        articleShortDescription = findViewById(R.id.shortDescriptionArticle);
        articleDescription = findViewById(R.id.descriptionArticle);
        carouselView = findViewById(R.id.articleCarouselView);
        carouselView.setImageListener(imageListener);
    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            Picasso.with(getApplicationContext()).load(sampleImages[position]).into(imageView);
        }
    };

    public void returnToPrevious(View view) {
        finish();
    }

    public void goToSellerProfileActivity(View view) {
        startActivity(sellerProfileActivity);
    }
}