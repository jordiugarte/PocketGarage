package bo.com.golpistasElectricistas.pocketGarage.utils;

import java.util.regex.Pattern;

public class Validations {
    public static boolean emailIsValid(String email) {
        Pattern pattern = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
        return pattern.matcher(email).matches();
    }

    public static boolean nameIsValid(String name){
        Pattern pattern = Pattern.compile("[a-zA-Z ]+");
        return pattern.matcher(name).matches();
    }
}
