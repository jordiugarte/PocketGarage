package bo.com.golpistasElectricistas.pocketGarage.repository.local;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;

import bo.com.golpistasElectricistas.pocketGarage.model.Article;
import bo.com.golpistasElectricistas.pocketGarage.model.User;
import bo.com.golpistasElectricistas.pocketGarage.repository.local.dao.ArticleDao;

public class Local {
    private ArticleDao articleDao;
    private Context context;
    private SharedPreferencesService sharedPreferencesService;

    public Local(Context context) {
        this.context = context;
        PocketGarageDatabase db = PocketGarageDatabase.getDatabase(context.getApplicationContext());
        articleDao = db.articleDao();
        sharedPreferencesService = new SharedPreferencesService(context);
    }

    public LiveData<List<Article>> getFavorites() {
        return articleDao.getAll();
    }

    public void setCurrentUser(User user) {
        sharedPreferencesService.setCurrentUser(user);
    }

    public User getCurrentUser() {
        return sharedPreferencesService.getCurrentUser();
    }

    public void update(List<Article> favorites) {
        new Thread(() -> {
            articleDao.insert(favorites);
        }).start();
    }

    public void addFavorite(Article article) {
        new Thread(() -> {
            articleDao.addFavorite(article);
        }).start();
    }

    public void deleteFavorite(Article article) {
        new Thread(() -> {
            articleDao.deleteFavorite(article);
        }).start();
    }
}
