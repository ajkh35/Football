package mobile_computing.project.football.Models;

import java.io.Serializable;

/**
 * Created by ajay3 on 1/15/2018.
 */

public class Goal implements Serializable{
    private Long mGoalTime;
    private String mScorer;
    private Long mScore1;
    private Long mScore2;

    public Long getmGoalTime() {
        return mGoalTime;
    }

    public void setmGoalTime(Long mGoalTime) {
        this.mGoalTime = mGoalTime;
    }

    public String getmScorer() {
        return mScorer;
    }

    public void setmScorer(String mScorer) {
        this.mScorer = mScorer;
    }

    public Long getmScore1() {
        return mScore1;
    }

    public void setmScore1(Long mScore1) {
        this.mScore1 = mScore1;
    }

    public Long getmScore2() {
        return mScore2;
    }

    public void setmScore2(Long mScore2) {
        this.mScore2 = mScore2;
    }
}
