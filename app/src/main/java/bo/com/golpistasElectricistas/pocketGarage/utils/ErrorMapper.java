package bo.com.golpistasElectricistas.pocketGarage.utils;

import android.content.Context;

import bo.com.golpistasElectricistas.pocketGarage.R;

public class ErrorMapper {
    public static String getError(Context context, int errorCode) {
        switch (errorCode) {
            case Constants.EMPTY_VALUE_ERROR:
                return context.getString(R.string.error_fill_values);
            case Constants.NO_CONNECTION_ERROR:
                return context.getString(R.string.error_no_connection);
            case Constants.INCORRECT_lOGIN_ERROR:
                return context.getString(R.string.error_incorrect_login);
            case Constants.INCORRECT_EMAIL_ERROR:
                return context.getString(R.string.error_valid_email);
            default:
                return context.getString(R.string.error_unknown);
        }
    }
}
