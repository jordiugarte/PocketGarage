package bo.com.golpistasElectricistas.pocketGarage.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import java.util.List;

import bo.com.golpistasElectricistas.pocketGarage.model.Base;
import bo.com.golpistasElectricistas.pocketGarage.model.Post;
import bo.com.golpistasElectricistas.pocketGarage.repository.Repository;
import bo.com.golpistasElectricistas.pocketGarage.repository.RepositoryImpl;

public class MainViewModel extends AndroidViewModel {
    private RepositoryImpl repository;

    public MainViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
    }

    public LiveData<Base<List<Post>>> getPosts() {
        MutableLiveData<Base<List<Post>>> results = new MutableLiveData<>();
        repository.getPosts().observeForever(new Observer<Base<List<Post>>>() {
            @Override
            public void onChanged(Base<List<Post>> listBase) {
                //Order the results
                //Like +++ First
                //TODO Map<Post(uuid), int>
                results.postValue(listBase);
            }
        });
        return results;
    }

    /*public LiveData<Base<List<Post>>> getPopularPosts() {
        MutableLiveData<Base<List<Post>>> results = new MutableLiveData<>();
        repository.getPopularPosts().observeForever(new Observer<Base<List<Post>>>() {
            @Override
            public void onChanged(Base<List<Post>> listBase) {
                //Order the results
                //Like +++ First
                //TODO Map<Post(uuid), int>
                results.postValue(listBase);
            }
        });
        return results;
    }*/
}