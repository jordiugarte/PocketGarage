package bo.com.golpistasElectricistas.pocketGarage.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import java.util.List;

import bo.com.golpistasElectricistas.pocketGarage.model.Post;
import bo.com.golpistasElectricistas.pocketGarage.repository.Repository;
import bo.com.golpistasElectricistas.pocketGarage.repository.RepositoryImpl;

public class MainViewModel extends AndroidViewModel {
    private RepositoryImpl repository;

    public MainViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
    }

    public List<Post> getPosts() {
        return repository.getPosts();
    }
}