package mobile_computing.project.football;

import android.graphics.Bitmap;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import mobile_computing.project.football.Adapters.RankingAdapter;
import mobile_computing.project.football.Utilities.Constants;
import mobile_computing.project.football.Utilities.ImageProcessor;

public class RankingActivity extends AppCompatActivity {

    private ListView mTeamsList;
    private TextView mActivityHeader;

    //Dummy Team List
//    private String[] mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this, android.R.color.background_dark));
        }

        mTeamsList = findViewById(R.id.teams_list);
        mActivityHeader = findViewById(R.id.activity_header);

        //Dummy Team List
//        mList = getResources().getStringArray(R.array.teamlist);

        // set the header
        mActivityHeader.setText(getString(R.string.standings));

        JSONArray array = null;
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            try {
                array = (JSONArray) new JSONParser()
                        .parse((String) bundle.get(Constants.ALL_TEAMS_ARRAY));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

//        ArrayList<Bitmap> bitmaps = new ArrayList<>();
//        int i=0;
//        while(i < array.size()){
//            try {
//                Bitmap bmp = ImageProcessor
//                        .getImageFromUrl((String) ((JSONObject) array.get(i)).get("TeamIconUrl"));
//                bitmaps.add(bmp);
//            } catch (ExecutionException e) {
//                e.printStackTrace();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }

        RankingAdapter adapter = new RankingAdapter(this, array);
        mTeamsList.setAdapter(adapter);
    }
}