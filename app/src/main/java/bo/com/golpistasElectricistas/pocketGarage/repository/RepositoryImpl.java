package bo.com.golpistasElectricistas.pocketGarage.repository;

import androidx.lifecycle.LiveData;

import bo.com.golpistasElectricistas.pocketGarage.model.Base;
import bo.com.golpistasElectricistas.pocketGarage.model.User;

public interface RepositoryImpl {
    LiveData<Base<User>> login(String email, String password);
}
