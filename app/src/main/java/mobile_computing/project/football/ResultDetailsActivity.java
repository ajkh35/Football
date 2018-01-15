package mobile_computing.project.football;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import mobile_computing.project.football.Adapters.GoalsAdapter;
import mobile_computing.project.football.Models.Goal;
import mobile_computing.project.football.Models.Match;
import mobile_computing.project.football.Utilities.Constants;
import mobile_computing.project.football.Utilities.ImageProcessor;

public class ResultDetailsActivity extends AppCompatActivity {

    private ListView mGoalsList;
    private ImageView mTeam1Logo;
    private ImageView mTeam2Logo;
    private TextView mTeam1Name;
    private TextView mTeam2Name;
    private TextView mFinalScore;
    private TextView mHalfScore;
    private TextView mMatchStart;
    private TextView mStadium;
    private TextView mAudience;
    private TextView mActivityHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_details);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this, android.R.color.background_dark));
        }

        mGoalsList = findViewById(R.id.goals_list);
        mTeam1Logo = findViewById(R.id.team1_logo);
        mTeam2Logo = findViewById(R.id.team2_logo);
        mFinalScore = findViewById(R.id.final_score);
        mHalfScore = findViewById(R.id.half_score);
        mTeam1Name = findViewById(R.id.team1);
        mTeam2Name = findViewById(R.id.team2);
        mMatchStart = findViewById(R.id.match_start);
        mStadium = findViewById(R.id.stadium);
        mAudience = findViewById(R.id.audience);
        mActivityHeader = findViewById(R.id.activity_header);

        // set the header
        mActivityHeader.setText(getString(R.string.match_details));

        Bundle bundle = getIntent().getExtras();
        ArrayList<Goal> goalsList = null;
        Match match = null;
        if(bundle != null){
            match = (Match) bundle.get(Constants.GAME_RESULT_CLICKED);
        }

        if (match != null){

            mTeam1Name.setText(match.getmTeamName());
            mTeam2Name.setText(match.getmTeamTwoName());

            try {
                mTeam1Logo.setImageBitmap(ImageProcessor.getImageFromUrl(match.getmTeamIconUrl()));
                mTeam2Logo.setImageBitmap(ImageProcessor.getImageFromUrl(match.getmTeamTwoIconUrl()));
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            String final_score = match.getmTeamScore() + " " + getString(R.string.colon) +
                    " " + match.getmTeamTwoScore();
            mFinalScore.setText(final_score);

            String half_score = getString(R.string.open_bracket) + match.getmTeamHalfScore() +
                    " " + getString(R.string.colon) + " " + match.getmTeamTwoHalfScore() +
                    getString(R.string.close_bracket);
            mHalfScore.setText(half_score);

            String stadium = getString(R.string.stadium)+ " " + match.getmStadium();
            mStadium.setText(stadium);

            String audience;
            if(match.getmAudience() == null)
                audience = getString(R.string.audience) + " " + getString(R.string.not_available);
            else
                audience = getString(R.string.audience) + " " + match.getmAudience();
            mAudience.setText(audience);

            mMatchStart.setText(getMatchDateAndTime(match.getmMatchDate()));

            // set the content for goals list
            goalsList = match.getmGoalsList();
        }

        GoalsAdapter adapter = new GoalsAdapter(this, goalsList);
        mGoalsList.setAdapter(adapter);
    }

    /**
     * Get the formatted date and time
     * @param dateTime
     * @return the string value
     */
    @NonNull
    private String getMatchDateAndTime(String dateTime){

        String[] arr = dateTime.split("T");

        String[] date_arr = arr[0].split("-");
        String date = date_arr[2] + getString(R.string.dot) + date_arr[1] +
                getString(R.string.dot) + date_arr[0];

        String time = arr[1].substring(0, arr[1].lastIndexOf(":")) + " " + getString(R.string.uhr);

        return getString(R.string.match_start) + " " + date + " " + time;
    }
}