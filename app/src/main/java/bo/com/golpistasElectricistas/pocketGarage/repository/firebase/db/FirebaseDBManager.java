package bo.com.golpistasElectricistas.pocketGarage.repository.firebase.db;

import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import bo.com.golpistasElectricistas.pocketGarage.model.Article;
import bo.com.golpistasElectricistas.pocketGarage.model.Base;
import bo.com.golpistasElectricistas.pocketGarage.model.User;
import bo.com.golpistasElectricistas.pocketGarage.utils.Constants;

import static bo.com.golpistasElectricistas.pocketGarage.utils.Constants.ALL_CATEGORIES;

public class FirebaseDBManager {
    private FirebaseDatabase db;

    public FirebaseDBManager() {
        db = FirebaseDatabase.getInstance();
    }

    public LiveData<Base<String>> addArticle(Article article, List<Uri> photos) {
        MutableLiveData<Base<String>> results = new MutableLiveData<>();
        String path = Constants.FIREBASE_PATH_ARTICLE;
        DatabaseReference reference = db.getReference(path).child(article.getArticleId() + "");
        reference.setValue(article).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    results.postValue(new Base<>(reference.getKey()));
                } else {
                    results.postValue(new Base<>(Constants.ERROR_REGISTER_DB, task.getException()));
                }
            }
        });
        return results;
    }

    public LiveData<Base<User>> updateUser(User user) {
        MutableLiveData<Base<User>> results = new MutableLiveData<>();
        String path = Constants.FIREBASE_PATH_USERS + "/" + user.getCi();
        db.getReference(path).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    results.postValue(new Base<>(user));
                } else {
                    results.postValue(new Base<>(Constants.ERROR_REGISTER_DB, task.getException()));
                }
            }
        });
        return results;
    }

    public LiveData<Base<User>> getUser(String email) {
        MutableLiveData<Base<User>> results = new MutableLiveData<>();
        String path = Constants.FIREBASE_PATH_USERS + "/" + email;
        User user = new User();
        db.getReference(path).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    results.postValue(new Base<>(user));
                } else {
                    results.postValue(new Base<>(Constants.ERROR_REGISTER_DB, task.getException()));
                }
            }
        });
        return results;
    }

    public LiveData<Base<Boolean>> updateCoverPhoto(Article article, String url) {
        MutableLiveData<Base<Boolean>> results = new MutableLiveData<>();
        String path = Constants.FIREBASE_PATH_ARTICLE + "/" + article.getUserId() + "/" + article.getArticleId();
        path += "/coverPhoto";
        db.getReference(path).setValue(url).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    results.postValue(new Base<>(true));
                } else {
                    results.postValue(new Base<>(Constants.ERROR_REGISTER_DB, task.getException()));
                }
            }
        });
        return results;
    }

    public LiveData<Base<Boolean>> updateArticlePhotos(Article article, List<String> urls) {
        MutableLiveData<Base<Boolean>> results = new MutableLiveData<>();
        String path = Constants.FIREBASE_PATH_ARTICLE + "/" + "photos";
        db.getReference(path).setValue(urls).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    results.postValue(new Base<>(true));
                } else {
                    results.postValue(new Base<>(Constants.ERROR_REGISTER_DB, task.getException()));
                }
            }
        });
        return results;
    }

    public LiveData<Base<List<Article>>> getArticles(int category) {
        MutableLiveData<Base<List<Article>>> results = new MutableLiveData<>();
        String path = Constants.FIREBASE_PATH_ARTICLE + "/";
        List<Article> articles = new ArrayList<>();
        db.getReference(path).getDatabase();
        db.getReference(path).setValue(articles).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    List<Article> articlesByCategory = new ArrayList<>();
                    if (category == ALL_CATEGORIES) {
                        results.postValue(new Base<>(articles));
                    } else {
                        for (Article article : articles) {
                            if (article.getCategory() == category) {
                                articlesByCategory.add(article);
                            }
                        }
                        results.postValue(new Base<>(articlesByCategory));
                    }
                } else {
                    results.postValue(new Base<>(Constants.ERROR_REGISTER_DB, task.getException()));
                }
            }
        });
        return results;
    }
}
