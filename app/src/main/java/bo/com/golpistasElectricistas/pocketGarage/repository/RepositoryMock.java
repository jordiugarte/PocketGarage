package bo.com.golpistasElectricistas.pocketGarage.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import bo.com.golpistasElectricistas.pocketGarage.model.Base;
import bo.com.golpistasElectricistas.pocketGarage.model.User;
import bo.com.golpistasElectricistas.pocketGarage.utils.Constants;
import bo.com.golpistasElectricistas.pocketGarage.utils.Validations;

public class RepositoryMock implements RepositoryImpl {
    public List<User> getMockUsers() {
        List<User> mockUsers = new ArrayList<>();
        mockUsers.add(new User(1,"jordi@ugarte.com","ffffffff","Jordi","Ugarte","01/01/1999"));
        mockUsers.add(new User(2,"ignacio@delrio.com","ffffffff","Ignacio","del Rio","01/01/1999"));
        mockUsers.add(new User(3,"sergio@laguna.com","ffffffff","Sergio","Laguna","01/01/1999"));
        return mockUsers;
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
            }
        }
        result.postValue(new Base(Constants.LOGIN_ERROR, null));
        return result;
    }
}
