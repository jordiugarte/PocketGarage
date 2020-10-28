package bo.com.golpistasElectricistas.pocketGarage.repository;

import androidx.lifecycle.LiveData;

import java.util.List;

import bo.com.golpistasElectricistas.pocketGarage.model.Article;
import bo.com.golpistasElectricistas.pocketGarage.model.Base;
import bo.com.golpistasElectricistas.pocketGarage.model.User;

public interface RepositoryImpl {
    LiveData<Base<User>> login(String email, String password);

    LiveData<Base<List<Article>>> getArticlesItems();

    Article getArticleItem(int id);

    LiveData<Base<List<Article>>> getFavorites();

    LiveData<Base<List<Article>>> getMyArticles();

    LiveData<Base<User>> register(String photo, String ci, String email, String pass, String name, String lastName, String date, int phone);

    LiveData<Base<String>> addArticle(Article article);
}
