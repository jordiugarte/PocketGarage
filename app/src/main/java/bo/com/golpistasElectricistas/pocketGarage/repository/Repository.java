package bo.com.golpistasElectricistas.pocketGarage.repository;

import androidx.lifecycle.LiveData;

import bo.com.golpistasElectricistas.pocketGarage.model.Base;
import bo.com.golpistasElectricistas.pocketGarage.model.Usuario;

public class Repository implements RepositoryImpl {
    @Override
    public LiveData<Base<Usuario>> login(String email, String password) {
        return null;
    }
}
