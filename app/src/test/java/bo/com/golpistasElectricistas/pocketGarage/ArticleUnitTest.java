package bo.com.golpistasElectricistas.pocketGarage;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import bo.com.golpistasElectricistas.pocketGarage.repository.RepositoryImpl;
import bo.com.golpistasElectricistas.pocketGarage.repository.RepositoryMock;

public class ArticleUnitTest {
    RepositoryImpl repository;

    @Before
    public void beforeEach() {
        repository = new RepositoryMock();
    }

    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    @Test
    public void getArticlesError(){

    }
}
