package bo.com.golpistasElectricistas.pocketGarage.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import bo.com.golpistasElectricistas.pocketGarage.model.Article;
import bo.com.golpistasElectricistas.pocketGarage.model.Base;
import bo.com.golpistasElectricistas.pocketGarage.repository.Repository;
import bo.com.golpistasElectricistas.pocketGarage.repository.RepositoryImpl;

public class ArticleViewModel extends AndroidViewModel {
    private RepositoryImpl repository;

    public ArticleViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
    }

    public Article getArticle(int id) {
        return repository.getArticleItem(id);
    }

    public void addFavorite(Article article) {
        repository.addFavorite(article);
    }

    public void deleteFavorite(Article article) {
        repository.deleteFavorite(article);
    }

    public LiveData<Base<List<Article>>> getFavorites() {
        return repository.getFavorites();
    }
}