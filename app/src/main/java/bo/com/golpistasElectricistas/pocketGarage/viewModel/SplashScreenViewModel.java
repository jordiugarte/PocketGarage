package bo.com.golpistasElectricistas.pocketGarage.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import bo.com.golpistasElectricistas.pocketGarage.model.Base;
import bo.com.golpistasElectricistas.pocketGarage.model.User;
import bo.com.golpistasElectricistas.pocketGarage.repository.Repository;
import bo.com.golpistasElectricistas.pocketGarage.repository.RepositoryImpl;

public class SplashScreenViewModel extends AndroidViewModel {
    private RepositoryImpl repository;

    public SplashScreenViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
    }

    public User getLoggedUser() {
        return repository.getCurrentUser();
    }

    public LiveData<Base<User>> login(String email, String password) {
        return repository.login(email, password);
    }
}
