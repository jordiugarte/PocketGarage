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

import static bo.com.golpistasElectricistas.pocketGarage.utils.Constants.ALL_CATEGORIES;

public class MainViewModel extends AndroidViewModel {
    private RepositoryImpl repository;

    public MainViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
    }

    public LiveData<Base<List<Article>>> getArticles() {
        return repository.getArticles(ALL_CATEGORIES);
    }

    public LiveData<Base<List<Article>>> getLastFiveArticles() {
        return repository.getLastFiveArticles();
    }
}