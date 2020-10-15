package bo.com.golpistasElectricistas.pocketGarage.repository.local;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import bo.com.golpistasElectricistas.pocketGarage.model.Article;
import bo.com.golpistasElectricistas.pocketGarage.model.Base;

public class Local {
    public Local(Application application) {

    }

    public LiveData<Base<List<Article>>> getFavorites() {
        return null;
    }

    public void update(List<Article> favorites) {
    }
}
