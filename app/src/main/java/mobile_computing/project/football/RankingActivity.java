package mobile_computing.project.football;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import mobile_computing.project.football.Adapters.RankingAdapter;

public class RankingActivity extends AppCompatActivity {

    private ListView mTeamsList;
    private String[] mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        mTeamsList = findViewById(R.id.teams_list);
        mList = getResources().getStringArray(R.array.teamlist);

        JSONArray array = null;
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String str = (String) bundle.get("Array");
            try {
                array = (JSONArray) new JSONParser().parse(str);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        RankingAdapter adapter = new RankingAdapter(this, array);
        mTeamsList.setAdapter(adapter);
    }
}
