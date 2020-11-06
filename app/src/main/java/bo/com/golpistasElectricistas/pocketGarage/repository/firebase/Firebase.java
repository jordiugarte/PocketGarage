package bo.com.golpistasElectricistas.pocketGarage.repository.firebase;

import android.net.Uri;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import java.util.Calendar;
import java.util.List;

import bo.com.golpistasElectricistas.pocketGarage.model.Article;
import bo.com.golpistasElectricistas.pocketGarage.model.Base;
import bo.com.golpistasElectricistas.pocketGarage.model.User;
import bo.com.golpistasElectricistas.pocketGarage.repository.firebase.auth.FirebaseAuthManager;
import bo.com.golpistasElectricistas.pocketGarage.repository.firebase.db.FirebaseDBManager;
import bo.com.golpistasElectricistas.pocketGarage.repository.firebase.storage.FirebaseStorageManager;

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
        return registerAndUpdateDb(auth.login(email, password), null, null);
    }

    public LiveData<Base<User>> register(User user, Uri photo) {
        return registerAndUpdateDb(auth.registerUser(user), user, photo);
    }

    public LiveData<Base<Article>> addArticle(Article article, List<Uri> photos) {
        MutableLiveData<Base<Article>> results = new MutableLiveData<>();
        db.addArticle(article, photos).observeForever(idArticleBase -> {
            if (idArticleBase.isSuccess()) {
                storage.uploadArticleImages(article, photos).observeForever(urlBase -> {
                    if (urlBase.isSuccess()) {
                        List<String> urls = urlBase.getData();
                        db.updateArticlePhotos(article, urls).observeForever(resultUpdateBase -> {
                            if (resultUpdateBase.isSuccess()) {
                                results.postValue(new Base<>(article));
                            } else {
                                results.postValue(new Base<>(resultUpdateBase.getError(), resultUpdateBase.getException()));
                            }
                        });
                    } else {
                        results.postValue(new Base<>(urlBase.getError(), urlBase.getException()));
                    }
                });
            } else {
                results.postValue(new Base<>(idArticleBase.getError(), idArticleBase.getException()));
            }
        });

        return results;
    }


    private LiveData<Base<User>> registerAndUpdateDb(LiveData<Base<User>> registerFunction, User user, Uri image) {
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

                    if (image != null) {
                        storage.uploadUserImage(registeredUser.getCi(), image).observeForever(new Observer<Base<String>>() {
                            @Override
                            public void onChanged(Base<String> urlBase) {
                                if (urlBase.isSuccess()) {
                                    registeredUser.setPhoto(urlBase.getData());
                                    //Register in database
                                    db.updateUser(registeredUser).observeForever(new Observer<Base<User>>() {
                                        @Override
                                        public void onChanged(Base<User> userBase) {
                                            results.postValue(userBase);
                                        }
                                    });
                                } else {
                                    results.postValue(new Base<>(urlBase.getError(), urlBase.getException()));
                                }
                            }
                        });
                    } else {
                        db.updateUser(registeredUser).observeForever(new Observer<Base<User>>() {
                            @Override
                            public void onChanged(Base<User> userBase) {
                                results.postValue(userBase);
                            }
                        });
                    }
                } else {
                    results.postValue(userBase);
                }
            }
        });
        return results;
    }

    public void signOut() {
        auth.signOut();
    }
}
