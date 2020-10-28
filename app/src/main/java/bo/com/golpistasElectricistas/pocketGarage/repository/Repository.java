package bo.com.golpistasElectricistas.pocketGarage.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import java.util.List;

import bo.com.golpistasElectricistas.pocketGarage.model.Article;
import bo.com.golpistasElectricistas.pocketGarage.model.Base;
import bo.com.golpistasElectricistas.pocketGarage.model.User;
import bo.com.golpistasElectricistas.pocketGarage.repository.api.ApiRepository;
import bo.com.golpistasElectricistas.pocketGarage.repository.firebase.Firebase;
import bo.com.golpistasElectricistas.pocketGarage.repository.local.Local;

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
        Article result = null;
        for (Article article : getArticlesItems().getValue().getData()) {
            if (article.getArticleId() == id) {
                result = article;
            }
        }
        return result;
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
    public LiveData<Base<User>> register(String photo, String ci, String email, String
            pass, String name, String lastName, String date, int phone) {
        return null;
    }

    @Override
    public LiveData<Base<String>> addArticle(Article article) {
        return Firebase.getInstance().addPostToStartup(article);
    }
}
