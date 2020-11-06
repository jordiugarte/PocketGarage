package bo.com.golpistasElectricistas.pocketGarage.repository.firebase.auth;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;

import bo.com.golpistasElectricistas.pocketGarage.model.Base;
import bo.com.golpistasElectricistas.pocketGarage.model.User;
import bo.com.golpistasElectricistas.pocketGarage.repository.Repository;
import bo.com.golpistasElectricistas.pocketGarage.repository.local.Local;
import bo.com.golpistasElectricistas.pocketGarage.utils.Constants;
import bo.com.golpistasElectricistas.pocketGarage.utils.FirebaseMapper;

import static com.google.firebase.FirebaseError.ERROR_EMAIL_ALREADY_IN_USE;

public class FirebaseAuthManager {
    private FirebaseAuth auth;

    public FirebaseAuthManager() {
        auth = FirebaseAuth.getInstance();
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

    public LiveData<Base<User>> registerUser(User user) {
        MutableLiveData<Base<User>> results = new MutableLiveData<>();
        auth.createUserWithEmailAndPassword(user.getEmail(), user.getPassword())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser firebaseUser = auth.getCurrentUser();
                            results.postValue(new Base<>(FirebaseMapper.userFromFirebase(firebaseUser)));
                        } else {
                            if (task.getException() instanceof FirebaseAuthException) {
                                if (((FirebaseAuthException) task.getException()).getErrorCode().equals(ERROR_EMAIL_ALREADY_IN_USE)) {
                                    results.postValue(new Base<>(Constants.REPEATED_EMAIL_ERROR, task.getException()));
                                } else {
                                    results.postValue(new Base<>(Constants.ERROR_REGISTER, task.getException()));
                                }
                            } else {
                                results.postValue(new Base<>(Constants.ERROR_REGISTER, task.getException()));
                            }
                        }
                    }
                });
        return results;
    }

    public void signOut() {
        this.auth.signOut();
    }
}
