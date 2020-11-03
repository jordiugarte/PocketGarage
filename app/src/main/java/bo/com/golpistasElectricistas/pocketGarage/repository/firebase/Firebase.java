package bo.com.golpistasElectricistas.pocketGarage.repository.firebase;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import java.util.Calendar;

import bo.com.golpistasElectricistas.pocketGarage.model.Article;
import bo.com.golpistasElectricistas.pocketGarage.model.Base;
import bo.com.golpistasElectricistas.pocketGarage.model.User;
import bo.com.golpistasElectricistas.pocketGarage.repository.firebase.auth.FirebaseAuthManager;
import bo.com.golpistasElectricistas.pocketGarage.repository.firebase.db.FirebaseDBManager;
import bo.com.golpistasElectricistas.pocketGarage.repository.firebase.storage.FirebaseStorageManager;
import bo.com.golpistasElectricistas.pocketGarage.repository.local.Local;

public class Firebase {
    private static Firebase instance;
    private FirebaseDBManager db;
    private FirebaseStorageManager storage;
    private FirebaseAuthManager auth;

    public static Firebase getInstance() {
        if (instance == null) {
            instance = new Firebase();
        }
        return instance;
    }

    public Firebase() {
        db = new FirebaseDBManager();
        storage = new FirebaseStorageManager();
        auth = new FirebaseAuthManager();
    }

    public LiveData<Base<User>> login(String email, String password) {
        return registerAndUpdateDb(auth.login(email, password), null);
    }

    public LiveData<Base<User>> register(User user) {
        return registerAndUpdateDb(auth.registerUser(user), user);
    }

    public LiveData<Base<Article>> addArticle(Article article) {
        MutableLiveData<Base<String>> results = new MutableLiveData<>();
        //Step 1: Create record
        /*db.addArticle(article).observeForever(idArticleBase -> {
            if (idArticleBase.isSuccess()) {
                //Step 2: Upload image
                Article articleId = idArticleBase.getData();
                storage.uploadArticleImages(uuidPost, image).observeForever(urlBase -> {
                    if (urlBase.isSuccess()) {
                        //Step 3: Update record
                        String url = urlBase.getData();
                        db.updateCoverPhoto(uuidStartup, uuidPost, url).observeForever(resultUpdateBase -> {
                            if (resultUpdateBase.isSuccess()) {
                                results.postValue(new Base<>(uuidPost));
                            } else {
                                results.postValue(new Base<>(resultUpdateBase.getErrorCode(), resultUpdateBase.getException()));
                            }
                        });
                    } else {
                        results.postValue(new Base<>(urlBase.getErrorCode(), urlBase.getException()));
                    }
                });
            } else {
                results.postValue(new Base<>(idArticleBase.getErrorCode(), idArticleBase.getException()));
            }
        });*/
        //TODO
        return null;
    }

    private LiveData<Base<User>> registerAndUpdateDb(LiveData<Base<User>> registerFunction, User user) {
        MutableLiveData<Base<User>> results = new MutableLiveData<>();
        registerFunction.observeForever(new Observer<Base<User>>() {
            @Override
            public void onChanged(Base<User> userBase) {
                if (userBase.isSuccess()) {
                    User registeredUser = userBase.getData();
                    registeredUser.setLastLogin(Calendar.getInstance().getTimeInMillis());
                    if (user != null && registeredUser.getPhoto().isEmpty()) {
                        registeredUser.setPhoto(user.getPhoto() != null ? user.getPhoto() : "");
                    }

                    //Register in database
                    db.updateUser(registeredUser).observeForever(new Observer<Base<User>>() {
                        @Override
                        public void onChanged(Base<User> userBase) {
                            results.postValue(userBase);
                        }
                    });
                } else {
                    //Return results
                    results.postValue(userBase);
                }
            }
        });
        return results;
    }

    public void signOut() {

    }
}
