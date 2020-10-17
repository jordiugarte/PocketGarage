package bo.com.golpistasElectricistas.pocketGarage.repository.api;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import bo.com.golpistasElectricistas.pocketGarage.model.Article;
import bo.com.golpistasElectricistas.pocketGarage.model.Base;
import bo.com.golpistasElectricistas.pocketGarage.utils.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiRepository {
    private static final String LOG = ApiRepository.class.getSimpleName();

    private static ApiRepository instance;
    private ArticulosApi articulosApi;

    public static ApiRepository getInstance() {
        if (instance == null) {
            instance = new ApiRepository();
        }
        return instance;
    }

    public ApiRepository() {
        articulosApi = ApiService.createService(ArticulosApi.class);
    }

    public LiveData<Base<List<Article>>> getArticles() {
        MutableLiveData<Base<List<Article>>> result = new MutableLiveData<>();
        articulosApi.getArticles(Constants.QUERY_PARAM_ALT).enqueue(new Callback<List<Article>>() {
            @Override
            public void onResponse(Call<List<Article>> call, Response<List<Article>> response) {
                if (response.isSuccessful()) {
                    result.postValue(new Base<>(response.body()));
                } else {
                    result.postValue(new Base<>(Constants.SERVER_ERROR, new Exception(response.message())));
                }
            }

            @Override
            public void onFailure(Call<List<Article>> call, Throwable t) {
                result.postValue(new Base<>(Constants.NO_CONNECTION_ERROR, new Exception(t)));
            }
        });
        return result;
    }
}
