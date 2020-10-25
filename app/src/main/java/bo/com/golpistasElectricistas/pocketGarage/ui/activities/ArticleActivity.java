package bo.com.golpistasElectricistas.pocketGarage.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

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
import bo.com.golpistasElectricistas.pocketGarage.model.Post;
import bo.com.golpistasElectricistas.pocketGarage.utils.Constants;
import bo.com.golpistasElectricistas.pocketGarage.viewModel.ArticleViewModel;
import bo.com.golpistasElectricistas.pocketGarage.viewModel.MainViewModel;

public class ArticleActivity extends AppCompatActivity {

    private Context context;

    private Intent sellerProfileActivity;

    private TextView articleName, articleTitle, articlePrice, articleState, articleShortDescription, articleDescription;
    private CarouselView carouselView;

    private Article thisArticle;

    private ArticleViewModel articleViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_article);
        context = getApplicationContext();
        articleViewModel = new ViewModelProvider(this).get(ArticleViewModel.class);
        setIntents();
        initViews();
        getData();
    }

    private void getData() {
        Bundle extras = getIntent().getExtras();
        int articleId = extras.getInt(Constants.KEY_STARTUP_SELECTED);
        try {
            thisArticle = articleViewModel.getArticle(articleId);
        } catch (Exception e) {

        }

        articleName.setText(thisArticle.getTitle());
        articleTitle.setText(thisArticle.getTitle());
        articlePrice.setText(thisArticle.getPrice() + "Bs");
        //articleState.setText(thisArticle.getState());
        articleShortDescription.setText(thisArticle.getShortDescription());
        articleDescription.setText(thisArticle.getDescription());

        carouselView.setPageCount(thisArticle.getPhotos().size());
        carouselView.setImageListener(imageListener);
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
            Picasso.with(getApplicationContext()).load(thisArticle.getPhotos().get(position)).into(imageView);
        }
    };

    public void returnToPrevious(View view) {
        finish();
    }

    public void goToSellerProfileActivity(View view) {
        startActivity(sellerProfileActivity);
    }
}