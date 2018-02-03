package mobile_computing.project.football.Services;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import mobile_computing.project.football.Utilities.Constants;

/**
 * Created by ajay3 on 1/11/2018.
 */

public class GameResultsService extends AsyncTask<String,String,String> {

//    private final String URL = "https://www.openligadb.de/api/getmatchdata/bl1";

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
                    new URL(Constants.CURRENT_MATCHDAY_URL).openConnection().getInputStream(),"UTF-8"
            );
            BufferedReader reader = new BufferedReader(streamReader);
            result = readInput(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}