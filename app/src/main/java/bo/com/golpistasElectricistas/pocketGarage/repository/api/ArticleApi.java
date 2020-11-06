package bo.com.golpistasElectricistas.pocketGarage.repository.api;

import bo.com.golpistasElectricistas.pocketGarage.utils.Constants;
import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

import bo.com.golpistasElectricistas.pocketGarage.model.Article;
import retrofit2.http.Query;

public interface ArticleApi {
   @GET(Constants.RESOURCE_ARTICLES)
    Call<List<Article>> getArticles(@Query("alt") String alt);
}
