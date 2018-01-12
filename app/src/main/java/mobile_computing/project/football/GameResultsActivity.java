package mobile_computing.project.football;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

import mobile_computing.project.football.Adapters.GameResultsAdapter;
import mobile_computing.project.football.Models.Match;

public class GameResultsActivity extends AppCompatActivity {

    private ListView mGameResultsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_results);

        mGameResultsList = findViewById(R.id.games_list);

        ArrayList<Match> list = null;
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            list = (ArrayList<Match>) bundle.get(Constants.GAME_RESULTS_ARRAY);
        }

        GameResultsAdapter adapter = new GameResultsAdapter(this, list);
        mGameResultsList.setAdapter(adapter);
    }
}