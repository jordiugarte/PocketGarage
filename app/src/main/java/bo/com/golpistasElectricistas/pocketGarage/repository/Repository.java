package bo.com.golpistasElectricistas.pocketGarage.repository;

import android.app.Application;
import android.net.Uri;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import java.util.List;

import bo.com.golpistasElectricistas.pocketGarage.model.Article;
import bo.com.golpistasElectricistas.pocketGarage.model.Base;
import bo.com.golpistasElectricistas.pocketGarage.model.User;
import bo.com.golpistasElectricistas.pocketGarage.repository.api.ApiRepository;
import bo.com.golpistasElectricistas.pocketGarage.repository.firebase.Firebase;
import bo.com.golpistasElectricistas.pocketGarage.repository.firebase.db.FirebaseDBManager;
import bo.com.golpistasElectricistas.pocketGarage.repository.local.Local;
import bo.com.golpistasElectricistas.pocketGarage.utils.Constants;
import bo.com.golpistasElectricistas.pocketGarage.utils.Validations;

import static bo.com.golpistasElectricistas.pocketGarage.utils.Constants.ALL_CATEGORIES;

public class Repository implements RepositoryImpl {
    private Local local;
    private Application application;

    public Repository(Application application) {
        this.application = application;
        local = new Local(application);
    }

    @Override
    public LiveData<Base<User>> login(String email, String password) {
        MutableLiveData<Base<User>> result = new MutableLiveData<>();
        if (email.isEmpty() || password.isEmpty()) {
            result.postValue(new Base(Constants.EMPTY_VALUE_ERROR, null));
            return result;
        }
        if (!Validations.emailIsValid(email)) {
            result.postValue(new Base(Constants.INVALID_EMAIL_ERROR, null));
            return result;
        }
        return Firebase.getInstance().login(email, password);
    }

    @Override
    public LiveData<Base<List<Article>>> getArticles(int category) {
        MutableLiveData<Base<List<Article>>> results = new MutableLiveData<>();
        ApiRepository.getInstance().getArticles(category).observeForever(new Observer<Base<List<Article>>>() {
            @Override
            public void onChanged(Base<List<Article>> listBase) {
                if (listBase.isSuccess()) {
                    results.postValue(listBase);
                }
            }
        });

        return results;
    }

    @Override
    public Article getArticleItem(int id) {
        Article result = null;
        for (Article article : getArticles(ALL_CATEGORIES).getValue().getData()) {
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
        /*ApiRepository.getInstance().getArticles().observeForever(new Observer<Base<List<Article>>>() {
            @Override
            public void onChanged(Base<List<Article>> listBase) {
                if (listBase.isSuccess()) {
                    result.postValue(listBase);
                    local.update(listBase.getData());
                }
            }
        });*/
        return result;
    }

    @Override
    public LiveData<Base<List<Article>>> getLastFiveArticles() {
        MutableLiveData<Base<List<Article>>> result = new MutableLiveData<>();
        ApiRepository.getInstance().getArticles(ALL_CATEGORIES).observeForever(new Observer<Base<List<Article>>>() {
            @Override
            public void onChanged(Base<List<Article>> listBase) {
                if (listBase.isSuccess()) {
                    Base<List<Article>> lastFive = null;
                    lastFive.getData().add(listBase.getData().get(0));
                    lastFive.getData().add(listBase.getData().get(1));
                    lastFive.getData().add(listBase.getData().get(2));
                    lastFive.getData().add(listBase.getData().get(3));
                    lastFive.getData().add(listBase.getData().get(4));
                    result.postValue(lastFive);
                }
            }
        });
        return result;
    }

    @Override
    public LiveData<Base<User>> register(User user, Uri photo) {
        MutableLiveData<Base<User>> result = new MutableLiveData<>();
        if (user.getCi().isEmpty() || user.getEmail().isEmpty() || user.getPassword().isEmpty() || user.getName().isEmpty() || user.getLastName().isEmpty() || user.getBornDate().isEmpty()) {
            result.postValue(new Base(Constants.EMPTY_VALUE_ERROR, null));
            return result;
        }
        if (!Validations.emailIsValid(user.getEmail())) {
            result.postValue(new Base(Constants.INVALID_EMAIL_ERROR, null));
            return result;
        }
        if (!Validations.nameIsValid(user.getName())) {
            result.postValue(new Base<>(Constants.INVALID_NAME_ERROR, null));
            return result;
        }
        if (!Validations.nameIsValid(user.getLastName())) {
            result.postValue(new Base<>(Constants.INVALID_LAST_NAME_ERROR, null));
            return result;
        } else {
            return Firebase.getInstance().register(user, photo);
        }
    }

    @Override
    public LiveData<Base<Article>> addArticle(Article article, List<Uri> photos) {
        return Firebase.getInstance().addArticle(article, photos);
    }

    @Override
    public User getCurrentUser() {
        return local.getCurrentUser();
    }

    @Override
    public void setCurrentUser(User user) {
        local.setCurrentUser(user);
    }

    @Override
    public void signOut() {
        Firebase.getInstance().signOut();
    }

    @Override
    public void addFavorite(Article article) {
        local.addFavorite(article);
    }

    @Override
    public void deleteFavorite(Article article) {
        local.deleteFavorite(article);
    }
}
