package bo.com.golpistasElectricistas.pocketGarage.repository.local;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import bo.com.golpistasElectricistas.pocketGarage.model.User;

import static android.content.Context.MODE_PRIVATE;
import static bo.com.golpistasElectricistas.pocketGarage.utils.Constants.*;

public class SharedPreferencesService {

    private Context context;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public SharedPreferencesService(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(KEY_SHARED_PREFS, MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void setCurrentUser(User user) {
        editor.putString(KEY_CURRENT_USER, new Gson().toJson(user));
        editor.apply();
        editor.commit();
    }

    public User getCurrentUser() {
        String userGson = sharedPreferences.getString(KEY_CURRENT_USER, "[]");
        return new Gson().fromJson(userGson, User.class);
    }
}
