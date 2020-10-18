package bo.com.golpistasElectricistas.pocketGarage.utils;

import android.content.Context;

import bo.com.golpistasElectricistas.pocketGarage.R;

public class ErrorMapper {
    public static String getError(Context context, int errorCode) {
        switch (errorCode) {
            case Constants.EMPTY_VALUE_ERROR:
                return context.getString(R.string.empty_value_error);
            case Constants.NO_CONNECTION_ERROR:
                return context.getString(R.string.error_no_connection);
            case Constants.INCORRECT_EMAIL_ERROR:
                return context.getString(R.string.incorrect_email_error);
            case Constants.INCORRECT_PASSWORD_ERROR:
                return  context.getString(R.string.incorrect_password_error);
            case Constants.INVALID_EMAIL_ERROR:
                return  context.getString(R.string.invalid_email_error);
            default:
                return context.getString(R.string.unknown_error);
        }
    }
}
