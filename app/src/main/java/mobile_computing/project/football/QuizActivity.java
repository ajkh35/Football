package mobile_computing.project.football;

import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Random;

import mobile_computing.project.football.Models.Match;
import mobile_computing.project.football.Utilities.Constants;

public class QuizActivity extends AppCompatActivity {

    private ArrayList<Match> mList;
    private TextView mVersus;
    private TextView mDate;
    private Button mReset;
    private Button mResolve;
    private EditText mScore1;
    private EditText mScore2;
    private Match mCurrentMatch;
    private TextView mActivityHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this, android.R.color.background_dark));
        }

        mVersus = findViewById(R.id.versus);
        mDate = findViewById(R.id.date);
        mScore1 = findViewById(R.id.team1_score);
        mScore2 = findViewById(R.id.team2_score);
        mReset = findViewById(R.id.reset);
        mResolve = findViewById(R.id.resolve);
        mActivityHeader = findViewById(R.id.activity_header);

        // set the header
        mActivityHeader.setText(getString(R.string.quiz));

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){

            // set initial match data
            mList = (ArrayList<Match>) bundle.get(Constants.QUIZ_DATA);
            setMatchData();

            // check the answer
            mResolve.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i(Constants.TAG, getResources().getString(R.string.resolve_clicked));
                    int score1 = Integer.parseInt(mScore1.getText().toString());
                    int score2 = Integer.parseInt(mScore2.getText().toString());
                    if(score1 == mCurrentMatch.getmTeamScore()
                            && score2 == mCurrentMatch.getmTeamTwoScore()){
                        Toast.makeText(QuizActivity.this,
                                getString(R.string.richtig),Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(QuizActivity.this,
                                getString(R.string.falsch),Toast.LENGTH_SHORT).show();
                    }
                }
            });

            // reset the teams
            mReset.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i(Constants.TAG, getString(R.string.reset_clicked));
                    setMatchData();
                }
            });
        }
    }

    /**
     * Get the month name
     * @param month
     * @return the string
     */
    private String getMonth(String month){
        String[] months = new DateFormatSymbols().getMonths();
        int num = Integer.parseInt(month);
        if(num > 0 && num < 12) {
            // Supposed to be empty
        }
        else {
            num = 1;
        }
        return months[num];
    }

    /**
     * Set the match data to their views
     */
    private void setMatchData(){
        if(mList != null) {
            mCurrentMatch = mList.get(getRandomIndex(mList.size() - 1));

            // set team names
            String team1 = "", team2 = "", versus = "";
            team1 = mCurrentMatch.getmTeamName();
            team2 = mCurrentMatch.getmTeamTwoName();
            versus = team1 + " " +  getResources().getString(R.string.versus) + " " + team2;
            mVersus.setText(versus);

            // set match date
            String numeric_date = mCurrentMatch.getmMatchDate().split("T")[0];
            String[] date_array = numeric_date.split("-");
            String date = getMonth(date_array[1]) + " " + date_array[2] + ", " + date_array[0];
            mDate.setText(date);

            // set scores to blank
            mScore1.getText().clear();
            mScore2.getText().clear();
        }
    }

    /**
     * Get a random index of list
     * @param bound
     * @return the index
     */
    private int getRandomIndex(int bound){
        Random random = new Random();
        return random.nextInt(bound);
    }
}