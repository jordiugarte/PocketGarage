package bo.com.golpistasElectricistas.pocketGarage.repository.api;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import bo.com.golpistasElectricistas.pocketGarage.model.Article;
import bo.com.golpistasElectricistas.pocketGarage.model.Base;
import bo.com.golpistasElectricistas.pocketGarage.utils.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static bo.com.golpistasElectricistas.pocketGarage.utils.Constants.ALL_CATEGORIES;

public class ApiRepository {

    private static ApiRepository instance;
    private ArticleApi articleApi;

    public static ApiRepository getInstance() {
        if (instance == null) {
            instance = new ApiRepository();
        }
        return instance;
    }

    public ApiRepository() {
        articleApi = ApiService.createService(ArticleApi.class);
    }

    public LiveData<Base<List<Article>>> getArticles(int category) {
        MutableLiveData<Base<List<Article>>> result = new MutableLiveData<>();
        articleApi.getArticles(Constants.QUERY_PARAM_ALT).enqueue(new Callback<List<Article>>() {
            @Override
            public void onResponse(Call<List<Article>> call, Response<List<Article>> response) {
                if (response.isSuccessful()) {
                    List<Article> articlesByCategory = new ArrayList<>();
                    if (category == ALL_CATEGORIES) {
                        result.postValue(new Base<>(response.body()));
                    } else {
                        for (Article article : response.body()) {
                            if (article.getCategory() == category) {
                                articlesByCategory.add(article);
                            }
                        }
                        result.postValue(new Base<>(response.body()));
                    }
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
