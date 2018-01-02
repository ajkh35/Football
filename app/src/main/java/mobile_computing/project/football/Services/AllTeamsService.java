package mobile_computing.project.football.Services;

import android.os.AsyncTask;
import android.support.annotation.NonNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by ajay3 on 1/2/2018.
 */

public class AllTeamsService extends AsyncTask<String,Integer,String> {

    @NonNull
    private String readInput(BufferedReader buffer) throws IOException {
        String line;
        StringBuilder str = new StringBuilder();
        while((line = buffer.readLine()) != null){
            str.append(line);
        }
        return str.toString();
    }

    @Override
    protected String doInBackground(String... strings) {

        String result = "";
        try {
            URL url = new URL(strings[0]);
            URLConnection conn = url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            result = readInput(reader);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}
