package bo.com.golpistasElectricistas.pocketGarage.repository.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import bo.com.golpistasElectricistas.pocketGarage.model.Article;

@Dao
public interface ArticleDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(List<Article> articles);

    @Query("SELECT * FROM article_table")
    LiveData<List<Article>> getAll();
}
