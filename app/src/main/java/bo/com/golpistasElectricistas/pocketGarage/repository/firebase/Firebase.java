package bo.com.golpistasElectricistas.pocketGarage.repository.firebase;

import androidx.lifecycle.LiveData;

import bo.com.golpistasElectricistas.pocketGarage.model.Article;
import bo.com.golpistasElectricistas.pocketGarage.model.Base;
import bo.com.golpistasElectricistas.pocketGarage.model.User;

public class Firebase {
    private static Firebase instance;
    private FirebaseAuthManager auth;
    private FirebaseDBManager db;
    private FirebaseStorageManager storage;

    public static Firebase getInstance() {
        if (instance == null) {
            instance = new Firebase();
        }
        return instance;
    }

    public Firebase() {
        auth = new FirebaseAuthManager();
        db = new FirebaseDBManager();
        storage = new FirebaseStorageManager();
    }

    public LiveData<Base<User>> login(String email, String password) {
        return updateDB(auth.login(email, password), null);
    }

    public LiveData<Base<User>> register(User user) {
        return updateDB(auth.register(user), user);
    }

    public LiveData<Base<User>> updateDB(LiveData<Base<User>> function, User user) {
        return null;
    }

    public LiveData<Base<String>> addArticle(Article article) {
        //TODO Step 2: add to storage
        //TODO Step 3: update db
        return null;
    }

    public LiveData<Base<User>> getCurrentUser() {
        return auth.getCurrentUser();
    }

    public void signOut() {
        auth.signOut();
    }
}
