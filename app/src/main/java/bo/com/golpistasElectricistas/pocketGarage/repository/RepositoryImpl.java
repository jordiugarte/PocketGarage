package bo.com.golpistasElectricistas.pocketGarage.repository;

import androidx.lifecycle.LiveData;

import bo.com.golpistasElectricistas.pocketGarage.model.Base;
import bo.com.golpistasElectricistas.pocketGarage.model.Usuario;

public interface RepositoryImpl {
    LiveData<Base<Usuario>> login(String email, String password);
}
