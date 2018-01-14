package mobile_computing.project.football.Utilities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import org.jetbrains.annotations.Contract;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

/**
 * Created by ajay3 on 1/14/2018.
 */

public class ImageProcessor {

    @Contract(pure = true)
    public static Bitmap getImageFromUrl(String Url) throws ExecutionException, InterruptedException {
        Bitmap bmp;
        bmp = new AsyncTask<String,Integer,Bitmap>(){
            @Override
            protected Bitmap doInBackground(String... strings) {

                Bitmap bmp = null;
                try {
                    HttpURLConnection connection = (HttpURLConnection) new URL(strings[0]).openConnection();
                    connection.setDoInput(true);
                    connection.connect();
                    InputStream stream = connection.getInputStream();
                    bmp = BitmapFactory.decodeStream(stream);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return bmp;
            }
        }.execute(Url).get();

        return bmp;
    }
}