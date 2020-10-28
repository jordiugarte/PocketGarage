package bo.com.golpistasElectricistas.pocketGarage.repository.firebase;

import androidx.lifecycle.LiveData;

import bo.com.golpistasElectricistas.pocketGarage.model.Article;
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

    public LiveData<Base<String>> addPostToStartup(Article article) {
        //return db.addPostToStartup(uuidStartup, post);
        //TODO Step 2: add to storage
        //TODO Step 3: update db
        return null;
    }
}
