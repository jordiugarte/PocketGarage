package bo.com.golpistasElectricistas.pocketGarage.utils;

public class Constants {
    public static final int EMPTY_VALUE_ERROR = 1000;
    public static final int INVALID_EMAIL_ERROR = 1001;
    public static final int INCORRECT_EMAIL_ERROR = 1002;
    public static final int INCORRECT_PASSWORD_ERROR = 1003;
    public static final int LOGIN_ERROR = 1004;
    public static final int NO_CONNECTION_ERROR = 1005;

    public static final int INVALID_NAME_ERROR = 2000;
    public static final int INVALID_LAST_NAME_ERROR = 2001;
    public static final int INVALID_CI_ERROR = 2002;
    public static final int REPEATED_CI_ERROR = 2003;
    public static final int REPEATED_EMAIL_ERROR = 2004;
    public static final int ERROR_REGISTER_DB = 2005;
    public static final int ERROR_REGISTER_EMAIL_ALREADY_EXISTS = 2006;
    public static final int ERROR_REGISTER = 2007;
    public static final int ERROR_UPLOAD_IMAGE = 2008;

    public static final int SERVER_ERROR = 5000;

    //  Api
    public static final String BASE_URL = "https://pocket-garage-9ce4d.firebaseio.com/";
    public static final String RESOURCE_ARTICLES = "articles.json";
    public static final String QUERY_PARAM_ALT = "media";
    public static final String KEY_STARTUP_SELECTED = "articleSelected";

    //  Firebase
    public static final String FIREBASE_PATH_USERS = "/users";
    public static final String FIREBASE_PATH_ARTICLE = "/articles";
    public static final String FIREBASE_PATH_STORAGE = "https://firebasestorage.googleapis.com/v0/b/pocket-garage-9ce4d.appspot.com/o";
    public static final String FIREBASE_PATH_STORAGE_IMAGES = FIREBASE_PATH_STORAGE + "/images";

    //  Categories
    public static final int CARS_CATEGORY = 0;
    public static final int BIKES_CATEGORY = 1;
    public static final int LIGHTS_CATEGORY = 2;
    public static final int ELECTRONICS_CATEGORY = 3;
    public static final int WHEELS_CATEGORY = 4;
    public static final int OTHERS_CATEGORY = 5;
    public static final int ALL_CATEGORIES = 999;

    //  Keys
    public static final String KEY_SHARED_PREFS = "shared preferences";
    public static final String KEY_CURRENT_USER = "current user";
    public static final String KEY_ARTICLES_QUANTITY = "articles quantity";

    //  Misc
    public static final String DIRECTORY_IMAGE = "pocket-garage-images";
}
