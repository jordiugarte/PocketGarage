package bo.com.golpistasElectricistas.pocketGarage.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import bo.com.golpistasElectricistas.pocketGarage.model.Article;
import bo.com.golpistasElectricistas.pocketGarage.model.Base;
import bo.com.golpistasElectricistas.pocketGarage.model.Post;
import bo.com.golpistasElectricistas.pocketGarage.model.User;
import bo.com.golpistasElectricistas.pocketGarage.utils.Constants;
import bo.com.golpistasElectricistas.pocketGarage.utils.Validations;

public class RepositoryMock implements RepositoryImpl {

    protected List<User> getMockUsers() {
        List<User> mockUsers = new ArrayList<>();
        mockUsers.add(new User("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSIxyT0DAa5_kwzb-e-bpTvAXIyW0OispA76Q&usqp=CAU", 1, "jordi@ugarte.com", "ffffffff", "Jordi", "Ugarte", "01/01/1999"));
        mockUsers.add(new User("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSIxyT0DAa5_kwzb-e-bpTvAXIyW0OispA76Q&usqp=CAU", 2, "ignacio@delrio.com", "ffffffff", "Ignacio", "del Rio", "01/01/1999"));
        mockUsers.add(new User("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSIxyT0DAa5_kwzb-e-bpTvAXIyW0OispA76Q&usqp=CAU", 3, "sergio@laguna.com", "ffffffff", "Sergio", "Laguna", "01/01/1999"));
        return mockUsers;
    }

    public List<Article> getMockArticles() {
        List<Article> mockArticles = new ArrayList<>();

        List<String> images1 = new ArrayList<String>();
        List<String> images2 = new ArrayList<String>();
        List<String> images3 = new ArrayList<String>();

        images1.add("https://es.wikipedia.org/wiki/Toyota_Hilux#/media/Archivo:Hilux2016.jpg");
        images1.add("https://es.wikipedia.org/wiki/Toyota_Hilux#/media/Archivo:Hilux2016.jpg");
        images1.add("https://es.wikipedia.org/wiki/Toyota_Hilux#/media/Archivo:Hilux2016.jpg");

        images2.add("https://upload.wikimedia.org/wikipedia/commons/6/61/2018_Ford_Ranger_%28PX%29_XLT_4WD_4-door_utility_%282018-10-22%29_01.jpg");
        images2.add("https://upload.wikimedia.org/wikipedia/commons/6/61/2018_Ford_Ranger_%28PX%29_XLT_4WD_4-door_utility_%282018-10-22%29_01.jpg");
        images2.add("https://upload.wikimedia.org/wikipedia/commons/6/61/2018_Ford_Ranger_%28PX%29_XLT_4WD_4-door_utility_%282018-10-22%29_01.jpg");

        images3.add("https://upload.wikimedia.org/wikipedia/commons/thumb/7/78/Lamborghini_Huracan_Evo_Genf_2019_1Y7A5452.jpg/1920px-Lamborghini_Huracan_Evo_Genf_2019_1Y7A5452.jpg");
        images3.add("https://upload.wikimedia.org/wikipedia/commons/thumb/7/78/Lamborghini_Huracan_Evo_Genf_2019_1Y7A5452.jpg/1920px-Lamborghini_Huracan_Evo_Genf_2019_1Y7A5452.jpg");
        images3.add("https://upload.wikimedia.org/wikipedia/commons/thumb/7/78/Lamborghini_Huracan_Evo_Genf_2019_1Y7A5452.jpg/1920px-Lamborghini_Huracan_Evo_Genf_2019_1Y7A5452.jpg");

        mockArticles.add(new Article(1,1, images1, "Hilux 2016 con 4 anos de uso", "Ucha bro f", "Toyota Hilux", 1000000, 6942000));
        mockArticles.add(new Article(2, 2, images2, "Ranger medio cagada", "Ucha bro f", "Ford Ranger", 1000000, 4206900));
        mockArticles.add(new Article(3, 3,images3, "Directo del Chapare", "Ucha bro f", "Lamborghini Aventador", 1000000, 11111111));
        return mockArticles;
    }

    @Override
    public LiveData<Base<User>> login(String email, String password) {
        MutableLiveData<Base<User>> result = new MutableLiveData<>();
        if (email.isEmpty() || password.isEmpty()) {
            result.postValue(new Base(Constants.EMPTY_VALUE_ERROR, null));
            return result;
        }
        if (!Validations.emailIsValid(email)) {
            result.postValue(new Base(Constants.INVALID_EMAIL_ERROR, null));
            return result;
        }
        for (User user : getMockUsers()) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                result.postValue(new Base(user));
                return result;
            } else if (user.getEmail().equals(email)) {
                result.postValue(new Base(Constants.INCORRECT_PASSWORD_ERROR, null));
                return result;
            }
        }
        result.postValue(new Base(Constants.INCORRECT_EMAIL_ERROR, null));
        return result;
    }

    @Override
    public LiveData<Base<List<Article>>> getArticlesItems() {
        return null;
    }

    @Override
    public Article getArticleItem(int id) {
        return null;
    }

    @Override
    public LiveData<Base<List<Article>>> getFavorites() {
        return null;
    }

    @Override
    public LiveData<Base<List<Article>>> getMyArticles() {
        return null;
    }

    @Override
    public LiveData<Base<List<Post>>> getPosts() {
        MutableLiveData<Base<List<Post>>> result = new MutableLiveData<>();
        for (Article article : getMockArticles()) {
            Post post = new Post(article.getArticleId(), article.getPhotos().get(0), article.getShortDescription(), article.getTitle(), article.getPrice());
            result.postValue(new Base(post));
        }
        return result;
    }

    @Override
    public LiveData<Base<User>> register(String photo, String ci, String email, String pass, String name, String lastName, String date) {
        MutableLiveData<Base<User>> result = new MutableLiveData<>();

        if (ci.isEmpty() || email.isEmpty() || pass.isEmpty() || name.isEmpty() || lastName.isEmpty() || date.isEmpty()) {
            result.postValue(new Base(Constants.EMPTY_VALUE_ERROR, null));
            return result;
        }
        if (!Validations.emailIsValid(email)) {
            result.postValue(new Base(Constants.INVALID_EMAIL_ERROR, null));
            return result;
        }
        if (!Validations.nameIsValid(name)) {
            result.postValue(new Base<>(Constants.INVALID_NAME_ERROR, null));
            return result;
        }
        if (!Validations.nameIsValid(lastName)) {
            result.postValue(new Base<>(Constants.INVALID_LAST_NAME_ERROR, null));
            return result;
        }
        try {
            int ciint = Integer.parseInt(ci);
            for (User user : getMockUsers()) {
                if (ciint == user.getCi()) {
                    result.postValue(new Base<>(Constants.REPEATED_CI_ERROR, null));
                    return result;
                }
                if (email.equals(user.getEmail())) {
                    result.postValue(new Base<>(Constants.REPEATED_EMAIL_ERROR, null));
                    return result;
                }
            }
            User user = new User(photo, ciint, email, pass, name, lastName, date);
            result.postValue(new Base<>(user));
            return result;
        } catch (final NumberFormatException e) {
            result.postValue(new Base<>(Constants.INVALID_CI_ERROR, null));
            return result;
        }
    }
}