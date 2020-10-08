package bo.com.golpistasElectricistas.pocketGarage.utils;

import java.util.regex.Pattern;

public class Validations {
    public static boolean emailIsValid(String email) {
        Pattern pattern = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
        return pattern.matcher(email).matches();
    }
}
