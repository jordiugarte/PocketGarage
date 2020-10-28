package bo.com.golpistasElectricistas.pocketGarage.repository.firebase;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import bo.com.golpistasElectricistas.pocketGarage.model.Base;
import bo.com.golpistasElectricistas.pocketGarage.model.User;
import bo.com.golpistasElectricistas.pocketGarage.utils.Constants;
import bo.com.golpistasElectricistas.pocketGarage.utils.FirebaseMapper;

public class FirebaseAuthManager {
    private FirebaseAuth auth;

    public FirebaseAuthManager() {
        auth = FirebaseAuth.getInstance();
    }

    public LiveData<Base<User>> getCurrentUser() {
        MutableLiveData<Base<User>> result = new MutableLiveData<>();
        FirebaseUser firebaseUser = auth.getCurrentUser();
        if (firebaseUser != null) {
            result.postValue(new Base<>(FirebaseMapper.userFromFirebase(firebaseUser)));
        }
        return result;
    }

    public LiveData<Base<User>> login(String email, String password) {
        MutableLiveData<Base<User>> result = new MutableLiveData<>();
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser firebaseUser = auth.getCurrentUser();
                    result.postValue(new Base<>(FirebaseMapper.userFromFirebase(firebaseUser)));
                } else {
                    result.postValue(new Base<>(Constants.LOGIN_ERROR, task.getException()));
                }
            }
        });
        return result;
    }

    public LiveData<Base<User>> register(User user) {
        return null;
    }

    public void signOut() {
        this.auth.signOut();
    }
}
