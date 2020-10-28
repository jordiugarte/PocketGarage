package bo.com.golpistasElectricistas.pocketGarage.repository.firebase;

import com.google.firebase.database.FirebaseDatabase;

public class FirebaseDBManager {
    private FirebaseDatabase db;

    public FirebaseDBManager() {
        db = FirebaseDatabase.getInstance();
    }
}
