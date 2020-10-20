package bo.com.golpistasElectricistas.pocketGarage.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import bo.com.golpistasElectricistas.pocketGarage.model.Base;
import bo.com.golpistasElectricistas.pocketGarage.model.User;
import bo.com.golpistasElectricistas.pocketGarage.repository.RepositoryImpl;
import bo.com.golpistasElectricistas.pocketGarage.repository.RepositoryMock;

public class RegisterViewModel extends AndroidViewModel {
    private RepositoryImpl repository;

    public RegisterViewModel(@NonNull Application application) {
        super(application);
        repository = new RepositoryMock();
    }

    public LiveData<Base<User>> register(String photo, String ci, String email, String pass, String name, String lastName, String date) {
        return repository.register(photo, ci, email, pass, name, lastName, date);
    }
}
