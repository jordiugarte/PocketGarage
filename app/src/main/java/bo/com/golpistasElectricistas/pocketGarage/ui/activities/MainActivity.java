package bo.com.golpistasElectricistas.pocketGarage.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.util.ArrayList;
import java.util.List;

import bo.com.golpistasElectricistas.pocketGarage.R;
import bo.com.golpistasElectricistas.pocketGarage.model.Article;
import bo.com.golpistasElectricistas.pocketGarage.model.Base;
import bo.com.golpistasElectricistas.pocketGarage.ui.adapters.ArticleAdapter;
import bo.com.golpistasElectricistas.pocketGarage.ui.callback.ArticleCallback;
import bo.com.golpistasElectricistas.pocketGarage.utils.Constants;
import bo.com.golpistasElectricistas.pocketGarage.utils.ErrorMapper;
import bo.com.golpistasElectricistas.pocketGarage.viewModel.MainViewModel;

public class MainActivity extends AppCompatActivity implements ArticleCallback {

    private Context context;

    private MainViewModel viewModel;

    private Intent newArticleActivity, myProfileActivity, favouritesActivity, articleActivity;

    private List<Article> articles = new ArrayList<>();
    private List<Article> lastFiveArticles = new ArrayList<>();

    private ArticleAdapter adapter;

    //String[] lastFiveImages = {lastFiveArticles.get(0).getPhotos().get(0), lastFiveArticles.get(1).getPhotos().get(0), lastFiveArticles.get(2).getPhotos().get(0), lastFiveArticles.get(3).getPhotos().get(0), lastFiveArticles.get(4).getPhotos().get(0)};
    String[] lastFiveImages = {"http://i.autos.com.ar/fotos/2012/0619/Toyota-Hilux-SRV-2006-201206191049053.jpg", "http://i.autos.com.ar/fotos/2012/0619/Toyota-Hilux-SRV-2006-201206191049053.jpg", "http://i.autos.com.ar/fotos/2012/0619/Toyota-Hilux-SRV-2006-201206191049053.jpg"};

    private RelativeLayout parentLinearLayout;
    private CarouselView carouselView;
    private RecyclerView articlesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        initViews();
        initIntents();
        initEvents();
        subscribeToData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        subscribeToData();
    }

    private void initEvents() {
        adapter.setCallback(this);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_profile:
                startActivity(myProfileActivity);
                return true;
            case R.id.action_favourites:
                startActivity(favouritesActivity);
            default:
                return true;
        }
    }

    private void initViews() {
        parentLinearLayout = findViewById(R.id.mainActivityLayout);
        carouselView = findViewById(R.id.carouselView);
        carouselView.setPageCount(lastFiveImages.length);
        carouselView.setImageListener(imageListener);
        articlesList = findViewById(R.id.articlesList);
        adapter = new ArticleAdapter(articles, context);
        articlesList.setAdapter(adapter);
        articlesList.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL, false));
    }

    private void initIntents() {
        newArticleActivity = new Intent(context, NewArticleActivity.class);
        myProfileActivity = new Intent(context, MyProfileActivity.class);
        favouritesActivity = new Intent(context, FavouritesActivity.class);
        articleActivity = new Intent(context, ArticleActivity.class);
    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            Picasso.with(getApplicationContext()).load(lastFiveImages[position]).into(imageView);
        }
    };

    public void addNewArticle(View view) {
        startActivity(newArticleActivity);
    }

    private void subscribeToData() {
        viewModel.getArticles().observe(this, new Observer<Base<List<Article>>>() {
            @Override
            public void onChanged(Base<List<Article>> listBase) {
                if (listBase.isSuccess()) {
                    articles = listBase.getData();
                    adapter.updateItems(articles);
                    Log.e("getStartups", new Gson().toJson(listBase));
                } else {
                    Toast.makeText(context, ErrorMapper.getError(context, listBase.getError()),
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        return;
    }

    @Override
    public void onStartupClicked(Article article) {
        Gson gson = new Gson();
        articleActivity.putExtra(Constants.KEY_STARTUP_SELECTED, gson.toJson(article));
        startActivity(articleActivity);
    }

    public void openCarCategory(View view) {
        Intent intent = new Intent(context, ListArticleActivity.class);
        startActivity(intent);
    }
}