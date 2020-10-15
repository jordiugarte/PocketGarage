package bo.com.golpistasElectricistas.pocketGarage.repository;

import androidx.lifecycle.LiveData;

import java.util.List;

import bo.com.golpistasElectricistas.pocketGarage.model.Article;
import bo.com.golpistasElectricistas.pocketGarage.model.Base;
import bo.com.golpistasElectricistas.pocketGarage.model.User;
import bo.com.golpistasElectricistas.pocketGarage.repository.firebase.Firebase;

public class Repository implements RepositoryImpl {
    @Override
    public LiveData<Base<User>> login(String email, String password) {
        return Firebase.getInstance().login(email, password);
    }

    @Override
    public LiveData<Base<List<Article>>> getArticlesItems() {
        return null;
    }
}
