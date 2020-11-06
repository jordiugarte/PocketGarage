package bo.com.golpistasElectricistas.pocketGarage.repository.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import bo.com.golpistasElectricistas.pocketGarage.model.Article;
import bo.com.golpistasElectricistas.pocketGarage.repository.local.dao.ArticleDao;
import bo.com.golpistasElectricistas.pocketGarage.utils.ListConverter;

@Database(entities = {Article.class}, version = 5)
@TypeConverters(ListConverter.class)
public abstract class PocketGarageDatabase extends RoomDatabase {
    private static volatile PocketGarageDatabase INSTANCE;

    static public PocketGarageDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            synchronized (PocketGarageDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), PocketGarageDatabase.class, "pocketgarage_database").build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract ArticleDao articleDao();
}
