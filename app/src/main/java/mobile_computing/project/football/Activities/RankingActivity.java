package mobile_computing.project.football.Activities;

import android.os.Build;
import android.support.v4.app.NavUtils;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import mobile_computing.project.football.Adapters.RankingAdapter;
import mobile_computing.project.football.R;
import mobile_computing.project.football.Utilities.Constants;

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

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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

        RankingAdapter adapter;
        if(array != null){
             adapter = new RankingAdapter(this, array);
        }else{
            adapter = new RankingAdapter(this, new JSONArray());
        }
        mTeamsList.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){

            case android.R.id.home:
                finish();
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}