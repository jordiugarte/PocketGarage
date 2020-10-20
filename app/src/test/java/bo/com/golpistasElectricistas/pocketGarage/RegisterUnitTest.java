package bo.com.golpistasElectricistas.pocketGarage;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import bo.com.golpistasElectricistas.pocketGarage.model.Base;
import bo.com.golpistasElectricistas.pocketGarage.model.User;
import bo.com.golpistasElectricistas.pocketGarage.repository.RepositoryImpl;
import bo.com.golpistasElectricistas.pocketGarage.repository.RepositoryMock;
import bo.com.golpistasElectricistas.pocketGarage.utils.Constants;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class RegisterUnitTest {
    RepositoryImpl repository;

    @Before
    public void beforeEach() {
        repository = new RepositoryMock();
    }

    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    @Test
    public void invalidEmailError() {
        String photo = "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSIxyT0DAa5_kwzb-e-bpTvAXIyW0OispA76Q&usqp=CAU";
        String name = "Cristian";
        String lastName = "Paz";
        String email = "cri@f";
        String pass = "ffffffff";
        String pass2 = "ffffffff";
        String ci = "1234";
        String date = "01/01/2000";
        LiveData<Base<User>> result = repository.register(photo, ci, email, pass, name, lastName, date);
        assertNotNull(result);
        result.observeForever(new Observer<Base<User>>() {
            @Override
            public void onChanged(Base<User> userBase) {
                assertFalse(userBase.isSuccess());
                assertEquals(Constants.INVALID_EMAIL_ERROR, userBase.getError());
            }
        });
    }

    @Test
    public void invalidNameError(){
        String photo = "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSIxyT0DAa5_kwzb-e-bpTvAXIyW0OispA76Q&usqp=CAU";
        String name = "Cristian1";
        String lastName = "Paz";
        String email = "cristian@paz.com";
        String pass = "ffffffff";
        String pass2 = "ffffffff";
        String ci = "1234";
        String date = "01/01/2000";
        LiveData<Base<User>> result = repository.register(photo, ci, email, pass, name, lastName, date);
        assertNotNull(result);
        result.observeForever(new Observer<Base<User>>() {
            @Override
            public void onChanged(Base<User> userBase) {
                assertFalse(userBase.isSuccess());
                assertEquals(Constants.INVALID_NAME_ERROR, userBase.getError());
            }
        });
    }

    @Test
    public void invalidLastNameError(){
        String photo = "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSIxyT0DAa5_kwzb-e-bpTvAXIyW0OispA76Q&usqp=CAU";
        String name = "Cristian";
        String lastName = "Paz2";
        String email = "cristian@paz.com";
        String pass = "ffffffff";
        String pass2 = "ffffffff";
        String ci = "1234";
        String date = "01/01/2000";
        LiveData<Base<User>> result = repository.register(photo, ci, email, pass, name, lastName, date);
        assertNotNull(result);
        result.observeForever(new Observer<Base<User>>() {
            @Override
            public void onChanged(Base<User> userBase) {
                assertFalse(userBase.isSuccess());
                assertEquals(Constants.INVALID_LAST_NAME_ERROR, userBase.getError());
            }
        });
    }

    @Test
    public void invalidCIError(){
        String photo = "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSIxyT0DAa5_kwzb-e-bpTvAXIyW0OispA76Q&usqp=CAU";
        String name = "Cristian";
        String lastName = "Paz";
        String email = "cristian@paz.com";
        String pass = "ffffffff";
        String pass2 = "ffffffff";
        String ci = "1234f";
        String date = "01/01/2000";
        LiveData<Base<User>> result = repository.register(photo, ci, email, pass, name, lastName, date);
        assertNotNull(result);
        result.observeForever(new Observer<Base<User>>() {
            @Override
            public void onChanged(Base<User> userBase) {
                assertFalse(userBase.isSuccess());
                assertEquals(Constants.INVALID_CI_ERROR, userBase.getError());
            }
        });
    }

    @Test
    public void repeatedCIError(){
        String photo = "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSIxyT0DAa5_kwzb-e-bpTvAXIyW0OispA76Q&usqp=CAU";
        String name = "Cristian";
        String lastName = "Paz";
        String email = "cristian@paz.com";
        String pass = "ffffffff";
        String pass2 = "ffffffff";
        String ci = "1";
        String date = "01/01/2000";
        LiveData<Base<User>> result = repository.register(photo, ci, email, pass, name, lastName, date);
        assertNotNull(result);
        result.observeForever(new Observer<Base<User>>() {
            @Override
            public void onChanged(Base<User> userBase) {
                assertFalse(userBase.isSuccess());
                assertEquals(Constants.REPEATED_CI_ERROR, userBase.getError());
            }
        });
    }

    @Test
    public void repeatedEmailError(){
        String photo = "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSIxyT0DAa5_kwzb-e-bpTvAXIyW0OispA76Q&usqp=CAU";
        String name = "Cristian";
        String lastName = "Paz";
        String email = "sergio@laguna.com";
        String pass = "ffffffff";
        String pass2 = "ffffffff";
        String ci = "1234";
        String date = "01/01/2000";
        LiveData<Base<User>> result = repository.register(photo, ci, email, pass, name, lastName, date);
        assertNotNull(result);
        result.observeForever(new Observer<Base<User>>() {
            @Override
            public void onChanged(Base<User> userBase) {
                assertFalse(userBase.isSuccess());
                assertEquals(Constants.REPEATED_EMAIL_ERROR, userBase.getError());
            }
        });
    }

    @Test
    public void emptyValuesError(){
        String photo = "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSIxyT0DAa5_kwzb-e-bpTvAXIyW0OispA76Q&usqp=CAU";
        String name = "Cristian";
        String lastName = "Paz";
        String email = "";
        String pass = "ffffffff";
        String pass2 = "ffffffff";
        String ci = "1234";
        String date = "01/01/2000";
        LiveData<Base<User>> result = repository.register(photo, ci, email, pass, name, lastName, date);
        assertNotNull(result);
        result.observeForever(new Observer<Base<User>>() {
            @Override
            public void onChanged(Base<User> userBase) {
                assertFalse(userBase.isSuccess());
                assertEquals(Constants.EMPTY_VALUE_ERROR, userBase.getError());
            }
        });
    }
}
