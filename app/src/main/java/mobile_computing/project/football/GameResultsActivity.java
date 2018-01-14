package mobile_computing.project.football;

import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import mobile_computing.project.football.Adapters.GameResultsAdapter;
import mobile_computing.project.football.Models.Match;
import mobile_computing.project.football.Utilities.Constants;

public class GameResultsActivity extends AppCompatActivity {

    private ListView mGameResultsList;
    private TextView mLeagueName;
    private TextView mActivityHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_results);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(
                    ContextCompat.getColor(this,android.R.color.background_dark)
            );
        }

        mGameResultsList = findViewById(R.id.games_list);
        mLeagueName = findViewById(R.id.league_name);
        mActivityHeader = findViewById(R.id.activity_header);

        // set the header
        mActivityHeader.setText(getString(R.string.game_results));

        // Get list from bundled data
        ArrayList<Match> list;
        Bundle bundle = getIntent().getExtras();

        if (bundle != null){
            list = (ArrayList<Match>) bundle.get(Constants.GAME_RESULTS_ARRAY);

            // set the adapter
            GameResultsAdapter adapter = new GameResultsAdapter(this, list);
            mGameResultsList.setAdapter(adapter);

            // set league name
            mLeagueName.setText(list.get(0).getmLeagueName());
        }
    }
}