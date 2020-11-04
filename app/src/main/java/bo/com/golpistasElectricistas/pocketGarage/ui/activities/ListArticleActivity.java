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
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

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

public class ListArticleActivity extends AppCompatActivity implements ArticleCallback {

    private Context context;
    private MainViewModel viewModel;
    private Intent articleActivity;
    private List<Article> list = new ArrayList<>();
    private ArticleAdapter adapter;
    private RelativeLayout parentLinearLayout;
    private RecyclerView articleList;
    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_favourites);
        context = getApplicationContext();
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
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
        articleList = findViewById(R.id.favouritesRecyclerView);
        title = findViewById(R.id.list_title);
        title.setText("Autos");
        adapter = new ArticleAdapter(list, context);
        articleList.setAdapter(adapter);
        articleList.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL, false));
    }

    private void initIntents() {
        articleActivity = new Intent(context, ArticleActivity.class);
    }

    private void subscribeToData() {
        viewModel.getArticles().observe(this, new Observer<Base<List<Article>>>() {
            @Override
            public void onChanged(Base<List<Article>> listBase) {
                if (listBase.isSuccess()) {
                    for (Article article : listBase.getData()) {
                        if (article.getCategory() == 0) {
                            list.add(article);
                        }
                        adapter.updateItems(list);
                    }
                    Log.e("getArticles", new Gson().toJson(listBase));
                } else {
                    Toast.makeText(context, ErrorMapper.getError(context, listBase.getError()),
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onStartupClicked(Article article) {
        Gson gson = new Gson();
        articleActivity.putExtra(Constants.KEY_STARTUP_SELECTED, gson.toJson(article));
        startActivity(articleActivity);
    }

    public void returnToPrevious(View view) {
        finish();
    }
}