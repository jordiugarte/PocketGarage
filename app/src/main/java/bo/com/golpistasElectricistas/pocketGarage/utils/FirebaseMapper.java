package bo.com.golpistasElectricistas.pocketGarage.utils;

import com.google.firebase.auth.FirebaseUser;

import bo.com.golpistasElectricistas.pocketGarage.model.User;

public class FirebaseMapper {
    static public User userFromFirebase(FirebaseUser firebaseUser) {
        User user = new User(firebaseUser.getEmail());
        user.setCi(firebaseUser.getUid());
        user.setName(firebaseUser.getDisplayName());
        user.setPhoto(firebaseUser.getPhotoUrl() != null ? firebaseUser.getPhotoUrl().toString() : "");
        return user;
    }
}