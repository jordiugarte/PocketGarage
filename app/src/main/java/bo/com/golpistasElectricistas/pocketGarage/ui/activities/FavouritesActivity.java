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
import android.view.Window;
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
import bo.com.golpistasElectricistas.pocketGarage.viewModel.FavouritesViewModel;
import bo.com.golpistasElectricistas.pocketGarage.viewModel.MainViewModel;

public class FavouritesActivity extends AppCompatActivity implements ArticleCallback {

    private Context context;

    private FavouritesViewModel viewModel;

    private Intent articleActivity;

    private List<Article> favourites = new ArrayList<>();

    private ArticleAdapter adapter;

    private RelativeLayout parentLinearLayout;
    private RecyclerView favouitesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_favourites);
        context = getApplicationContext();
        viewModel = new ViewModelProvider(this).get(FavouritesViewModel.class);
        initViews();
        initIntents();
        initEvents();
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

    private void initViews() {
        parentLinearLayout = findViewById(R.id.favouritesLayout);
        favouitesList = findViewById(R.id.favouritesRecyclerView);
        adapter = new ArticleAdapter(favourites, context);
        favouitesList.setAdapter(adapter);
        favouitesList.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL, false));
    }

    private void initIntents() {
        articleActivity = new Intent(context, ArticleActivity.class);
    }

    private void subscribeToData() {
        viewModel.getFavorites().observe(this, new Observer<Base<List<Article>>>() {
            @Override
            public void onChanged(Base<List<Article>> listBase) {
                //T1: Local
                //T2: API
                if (listBase.isSuccess()) {
                    favourites = listBase.getData();
                    adapter.updateItems(favourites);
                    Log.e("getFavourites", new Gson().toJson(listBase));
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
}