package bo.com.golpistasElectricistas.pocketGarage.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import java.util.List;

import bo.com.golpistasElectricistas.pocketGarage.model.Article;
import bo.com.golpistasElectricistas.pocketGarage.model.Base;
import bo.com.golpistasElectricistas.pocketGarage.model.User;
import bo.com.golpistasElectricistas.pocketGarage.repository.api.ApiRepository;
import bo.com.golpistasElectricistas.pocketGarage.repository.firebase.Firebase;

public class Repository implements RepositoryImpl {
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
    public LiveData<Base<User>> register(String photo, int ci, String email, String pass, String name, String lastName, String date) {
        return null;
    }
}
