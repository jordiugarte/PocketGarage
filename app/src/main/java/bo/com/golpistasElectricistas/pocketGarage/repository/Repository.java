package bo.com.golpistasElectricistas.pocketGarage.repository;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import bo.com.golpistasElectricistas.pocketGarage.model.Article;
import bo.com.golpistasElectricistas.pocketGarage.model.Base;
import bo.com.golpistasElectricistas.pocketGarage.model.Post;
import bo.com.golpistasElectricistas.pocketGarage.model.User;
import bo.com.golpistasElectricistas.pocketGarage.repository.api.ApiRepository;
import bo.com.golpistasElectricistas.pocketGarage.repository.firebase.Firebase;
import bo.com.golpistasElectricistas.pocketGarage.repository.local.Local;
import bo.com.golpistasElectricistas.pocketGarage.ui.activities.MainActivity;
import bo.com.golpistasElectricistas.pocketGarage.utils.ErrorMapper;

public class Repository implements RepositoryImpl {
    private Local local;

    public Repository(Application application) {
        local = new Local(application);
    }

    @Override
    public LiveData<Base<User>> login(String email, String password) {
        return Firebase.getInstance().login(email, password);
    }

    @Override
    public LiveData<Base<List<Article>>> getArticlesItems() {
        MutableLiveData<Base<List<Article>>> result = new MutableLiveData<>();
        ApiRepository.getInstance().getArticles().observeForever(new Observer<Base<List<Article>>>() {
            @Override
            public void onChanged(Base<List<Article>> listBase) {
                if (listBase.isSuccess()) {
                    result.postValue(listBase);
                }
            }
        });
        return result;
    }

    @Override
    public Article getArticleItem(int id) {
        List<Article> articles = getArticlesItems().getValue().getData();
        for (Article article : articles) {
            if (article.getArticleId() == id) {
                return article;
            }
        }
        return null;
    }

    @Override
    public LiveData<Base<List<Article>>> getFavorites() {
        MutableLiveData<Base<List<Article>>> result = new MutableLiveData<>();
        local.getFavorites().observeForever(new Observer<List<Article>>() {
            @Override
            public void onChanged(List<Article> articles) {
                result.postValue(new Base<>(articles));
            }
        });
        ApiRepository.getInstance().getArticles().observeForever(new Observer<Base<List<Article>>>() {
            @Override
            public void onChanged(Base<List<Article>> listBase) {
                if (listBase.isSuccess()) {
                    result.postValue(listBase);
                    local.update(listBase.getData());
                }
            }
        });
        return result;
    }

    @Override
    public LiveData<Base<List<Article>>> getMyArticles() {
        return null;
    }

    @Override
    public List<Post> getPosts() {
        List<Post> posts = new ArrayList<>();
        MutableLiveData<Base<List<Post>>> result = new MutableLiveData<>();
        ApiRepository.getInstance().getArticles().observeForever(new Observer<Base<List<Article>>>() {
            @Override
            public void onChanged(Base<List<Article>> listBase) {
                if (listBase.isSuccess()) {
                    List<Article> articles = listBase.getData();
                    for (Article article : articles) {
                        Post post = new Post(article.getArticleId(), article.getPhotos().get(0), article.getShortDescription(), article.getTitle(), article.getPrice());
                        posts.add(post);
                        Log.e(MainActivity.class.getName(), post.getTitle());
                    }
                }
            }
        });
        return posts;
    }

    @Override
    public LiveData<Base<User>> register(String photo, String ci, String email, String
            pass, String name, String lastName, String date) {
        return null;
    }
}
