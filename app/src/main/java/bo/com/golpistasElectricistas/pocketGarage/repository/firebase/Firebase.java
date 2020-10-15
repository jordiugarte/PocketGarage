package bo.com.golpistasElectricistas.pocketGarage.repository.firebase;

import androidx.lifecycle.LiveData;

import bo.com.golpistasElectricistas.pocketGarage.model.Base;
import bo.com.golpistasElectricistas.pocketGarage.model.User;

public class Firebase {
    private static Firebase instance;

    public static Firebase getInstance() {
        if (instance == null) {
            instance = new Firebase();
        }
        return instance;
    }

    public LiveData<Base<User>> login(String email, String password) {
        return null;
    }
}
