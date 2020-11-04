package bo.com.golpistasElectricistas.pocketGarage.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class CompressImage {
    private static final String LOG = CompressImage.class.getName();

    static public Uri compressImage(Uri image, Context context) {
        FileOutputStream out;
        String strPhotoName = "post_" + Calendar.getInstance().getTimeInMillis() + ".jpg";

        try {
            //Step 1: Convert to bitmap
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), image);

            //Step 2: Prepare the files
            //Folder
            File folder = new File(Environment.getExternalStorageDirectory(), Constants.DIRECTORY_IMAGE);
            if (!folder.exists()) {
                folder.mkdirs();
            }
            //File
            File file = new File(Environment.getExternalStorageDirectory(),
                    Constants.DIRECTORY_IMAGE + File.separator + strPhotoName);
            out = new FileOutputStream(file);

            //Step 3: Matrix to compress
            Matrix m = new Matrix();
            m.setRectToRect(new RectF(0, 0, bitmap.getWidth(), bitmap.getHeight()),
                    new RectF(0, 0, 1280, 720), Matrix.ScaleToFit.CENTER);
            bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), m, true);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);

            out.flush();
            out.close();
            return Uri.fromFile(file);
        } catch (IOException e) {
            Log.e(LOG, "Error compressing image", e);
            return image;
        }
    }
}
