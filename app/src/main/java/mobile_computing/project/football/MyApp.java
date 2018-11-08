package mobile_computing.project.football;

import android.app.Application;
import android.content.Context;

import org.jetbrains.annotations.Contract;

/**
 * Created by ajay3 on 1/18/2018.
 */

public class MyApp extends Application {

    private static Application mInstance;
    private static Context mAppContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    @Contract(pure = true)
    public static Application getInstance(){
        return mInstance;
    }
}
