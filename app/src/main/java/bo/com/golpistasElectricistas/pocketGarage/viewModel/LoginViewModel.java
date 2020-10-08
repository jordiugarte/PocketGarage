package bo.com.golpistasElectricistas.pocketGarage.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import bo.com.golpistasElectricistas.pocketGarage.model.Base;
import bo.com.golpistasElectricistas.pocketGarage.model.User;
import bo.com.golpistasElectricistas.pocketGarage.repository.RepositoryImpl;
import bo.com.golpistasElectricistas.pocketGarage.repository.RepositoryMock;

public class LoginViewModel extends AndroidViewModel {
    private RepositoryImpl repository;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        repository = new RepositoryMock();
    }

    public LiveData<Base<User>> login(String email, String password) {
        return repository.login(email, password);
    }
}
