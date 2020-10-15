package bo.com.golpistasElectricistas.pocketGarage.repository;

import androidx.lifecycle.LiveData;

import java.util.List;

import bo.com.golpistasElectricistas.pocketGarage.model.Article;
import bo.com.golpistasElectricistas.pocketGarage.model.Base;
import bo.com.golpistasElectricistas.pocketGarage.model.User;

public class Repository implements RepositoryImpl {
    @Override
    public LiveData<Base<User>> login(String email, String password) {
        return null;
    }

    @Override
    public LiveData<Base<List<Article>>> getArticlesItems() {
        return null;
    }
}
