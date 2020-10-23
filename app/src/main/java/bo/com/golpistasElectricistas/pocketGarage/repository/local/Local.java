package bo.com.golpistasElectricistas.pocketGarage.repository.local;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import bo.com.golpistasElectricistas.pocketGarage.model.Article;
import bo.com.golpistasElectricistas.pocketGarage.model.Base;

public class Local {
    private ArticleDao articleDao;

    public Local(Application application) {
        PocketGarageDatabase db = PocketGarageDatabase.getDatabase(application);
        articleDao = db.articleDao();
    }

    public LiveData<List<Article>> getFavorites() {
        return articleDao.getAll();
    }

    public void update(List<Article> favorites) {
        new Thread(() -> {
            articleDao.insert(favorites);
        }).start();
    }
}
