package bo.com.golpistasElectricistas.pocketGarage.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import bo.com.golpistasElectricistas.pocketGarage.model.Article;
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
}