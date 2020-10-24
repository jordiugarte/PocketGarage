package bo.com.golpistasElectricistas.pocketGarage.utils;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class ListConverter {
    @TypeConverter
    public String fromList(List<String> list) {
        return new Gson().toJson(list);
    }

    @TypeConverter
    public List<String> fromString(String s) {
        Type listType = new TypeToken<List<String>>() {
        }.getType();
        return new Gson().fromJson(s, listType);
    }
}
