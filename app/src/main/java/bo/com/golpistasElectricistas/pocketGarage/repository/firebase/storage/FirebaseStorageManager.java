package bo.com.golpistasElectricistas.pocketGarage.repository.firebase.storage;

import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.List;

import bo.com.golpistasElectricistas.pocketGarage.model.Base;
import bo.com.golpistasElectricistas.pocketGarage.utils.Constants;

public class FirebaseStorageManager {
    private StorageReference storage;

    public FirebaseStorageManager() {
        storage = FirebaseStorage.getInstance().getReference();
    }

    public LiveData<Base<String>> uploadArticleImages(int articleId, List<Uri> images) {
        MutableLiveData<Base<String>> results = new MutableLiveData<>();
        new Thread(() -> {
            try {
                Thread.sleep(10000);
                results.postValue(new Base<>("https://saboryestilo.com.mx/wp-content/uploads/2020/05/parrilla-argentina-1-1200x720.jpg"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        return results;
    }

    public LiveData<Base<String>> uploadUserImage(String uuidUser, Uri image) {
        String path = "users/" + uuidUser + ".jpg";
        return this.uploadImage(path, image);
    }

    private LiveData<Base<String>> uploadImage(String path, Uri image) {
        MutableLiveData<Base<String>> results = new MutableLiveData<>();
        StorageReference ref = storage.child(path);
        UploadTask uploadTask = ref.putFile(image);
        uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                if (!task.isSuccessful()) {
                    throw task.getException();
                }
                return ref.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if (task.isSuccessful()) {
                    Uri downloadUri = task.getResult();
                    results.postValue(new Base<>(downloadUri.toString()));
                } else {
                    results.postValue(new Base<>(Constants.ERROR_UPLOAD_IMAGE, task.getException()));
                }
            }
        });
        return results;
    }
}