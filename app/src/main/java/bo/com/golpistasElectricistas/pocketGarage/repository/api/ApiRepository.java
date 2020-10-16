package bo.com.golpistasElectricistas.pocketGarage.repository.api;

public class ApiRepository {
    private static final String LOG = ApiRepository.class.getSimpleName();

    private static ApiRepository instance;

    public static ApiRepository getInstance() {
        if (instance == null) {
            instance = new ApiRepository();
        }
        return instance;
    }
}
