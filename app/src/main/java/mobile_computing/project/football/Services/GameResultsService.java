package mobile_computing.project.football.Services;

import android.os.AsyncTask;
import android.support.annotation.NonNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by ajay3 on 1/11/2018.
 */

public class GameResultsService extends AsyncTask<String,String,String> {

    private final String URL = "https://www.openligadb.de/api/getmatchdata/bl1/2016";

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
            InputStreamReader streamReader = new InputStreamReader(
                    new URL(URL).openConnection().getInputStream(),"UTF-8"
            );
            BufferedReader reader = new BufferedReader(streamReader);
            result = readInput(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}