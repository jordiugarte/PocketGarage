package bo.com.golpistasElectricistas.pocketGarage.viewModel;

import android.app.Application;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import bo.com.golpistasElectricistas.pocketGarage.model.Article;
import bo.com.golpistasElectricistas.pocketGarage.model.Base;
import bo.com.golpistasElectricistas.pocketGarage.repository.Repository;
import bo.com.golpistasElectricistas.pocketGarage.repository.RepositoryImpl;

public class MyProfileViewModel extends AndroidViewModel {

    private RepositoryImpl repository;

    public MyProfileViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
    }

    public void signOut() {
        repository.signOut();
    }
}
