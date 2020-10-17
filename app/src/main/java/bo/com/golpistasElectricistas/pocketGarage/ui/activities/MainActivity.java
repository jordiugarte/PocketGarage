package bo.com.golpistasElectricistas.pocketGarage.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
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
import bo.com.golpistasElectricistas.pocketGarage.utils.ErrorMapper;
import bo.com.golpistasElectricistas.pocketGarage.viewModel.MainViewModel;

public class MainActivity extends AppCompatActivity {

    private static final String LOG = LoginActivity.class.getName();
    private Context context;

    private MainViewModel viewModel;

    private Intent newArticleActivity;

    private List<Article> articles = new ArrayList<>();

    private ArticleAdapter adapter;

    String[] sampleImages = {
            "https://scontent.flpb2-2.fna.fbcdn.net/v/t1.0-9/121192448_3711992668812129_3055426446455598271_o.jpg?_nc_cat=106&_nc_sid=730e14&_nc_ohc=9xzNn9kpN_AAX-MJRXY&_nc_ht=scontent.flpb2-2.fna&oh=75800d9f014704494f4ede158f313c3b&oe=5FAAD43D",
            "https://scontent.flpb2-1.fna.fbcdn.net/v/t1.0-9/121021076_3702364973189322_7247070921577086116_o.jpg?_nc_cat=102&_nc_sid=730e14&_nc_ohc=A8mjKOJWXzgAX8JYwf3&_nc_ht=scontent.flpb2-1.fna&oh=f1ba19fb8b37b8fe02aa4d7e4fc9fae2&oe=5FABF2EA",
            "https://scontent.flpb2-1.fna.fbcdn.net/v/t1.0-9/106571277_1453836774811044_5992646281856668927_n.jpg?_nc_cat=110&_nc_sid=730e14&_nc_ohc=7b9a8hvn32EAX9scXvj&_nc_ht=scontent.flpb2-1.fna&oh=46c15bfbb5ab245ef6242e96dc37600b&oe=5FAC2C3C"
    };

    private FloatingActionButton publishButton;
    private CarouselView carouselView;
    private RecyclerView articlesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(LOG, "onCreate");
        setContentView(R.layout.activity_main);
        context = getApplicationContext();
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        initViews();
        initIntents();
        subscribeToData();
    }

    private void initViews() {
        publishButton = findViewById(R.id.publishButton);
        carouselView = findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleImages.length);
        carouselView.setImageListener(imageListener);
        articlesList = findViewById(R.id.articlesList);
        adapter = new ArticleAdapter(articles, context);
        articlesList.setAdapter(adapter);
    }

    private void initIntents() {
        newArticleActivity = new Intent(context, NewArticleActivity.class);
    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            Picasso.with(getApplicationContext()).load(sampleImages[position]).into(imageView);
        }
    };

    public void addNewArticle(View view) {
        startActivity(newArticleActivity);
    }

    private void subscribeToData() {
        viewModel.getArticles().observe(this, listBase -> {
            //onChanged(Base<List<Posts>> listBase)
            //T1, Tn: Firebase
            if (listBase.isSuccess()) {
                articles = listBase.getData();
            } else {
                Toast.makeText(context, ErrorMapper.getError(context, listBase.getError()),
                        Toast.LENGTH_SHORT).show();
            }
        });
        viewModel.getArticles().observe(this, new Observer<Base<List<Article>>>() {
            @Override
            public void onChanged(Base<List<Article>> listBase) {
                //T1: Local
                //T2: API
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
}