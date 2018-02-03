package mobile_computing.project.football.Models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by ajay3 on 1/12/2018.
 */

public class Match implements Serializable{

    private int mGroupID;
    private String mGroupName;
    private int mSpieltag;
    private int mMatchID;
    private String mMatchDate;
    private int mLeagueID;
    private String mLeagueName;
    private String mTeamName;
    private int mTeamID;
    private String mTeamTwoName;
    private int mTeamTwoID;
    private String mTeamIconUrl;
    private String mTeamTwoIconUrl;
    private int mTeamScore;
    private int mTeamTwoScore;
    private int mTeamHalfScore;
    private int mTeamTwoHalfScore;
    private String mStadium;
    private Long mAudience;
    private ArrayList<Goal> mGoalsList;

    public int getmGroupID() {
        return mGroupID;
    }

    public void setmGroupID(int mGroupID) {
        this.mGroupID = mGroupID;
    }

    public String getmGroupName() {
        return mGroupName;
    }

    public void setmGroupName(String mGroupName) {
        this.mGroupName = mGroupName;
    }

    public int getmMatchID() {
        return mMatchID;
    }

    public void setmMatchID(int mMatchID) {
        this.mMatchID = mMatchID;
    }

    public int getmLeagueID() {
        return mLeagueID;
    }

    public void setmLeagueID(int mLeagueID) {
        this.mLeagueID = mLeagueID;
    }

    public String getmLeagueName() {
        return mLeagueName;
    }

    public void setmLeagueName(String mLeagueName) {
        this.mLeagueName = mLeagueName;
    }

    @Override
    public String toString() {
        return "Match{" + "matchid=" + mMatchID + "leaguename=" + mLeagueName
                + "leagueid=" + mLeagueID + "groupid=" + mGroupID + "groupname=" + mGroupName
                + "matchday=" + mSpieltag + "}";
    }

    public String getmTeamName() {
        return mTeamName;
    }

    public void setmTeamName(String mTeamName) {
        this.mTeamName = mTeamName;
    }

    public int getmTeamID() {
        return mTeamID;
    }

    public void setmTeamID(int mTeamID) {
        this.mTeamID = mTeamID;
    }

    public String getmTeamTwoName() {
        return mTeamTwoName;
    }

    public void setmTeamTwoName(String mTeamTwoName) {
        this.mTeamTwoName = mTeamTwoName;
    }

    public int getmTeamTwoID() {
        return mTeamTwoID;
    }

    public void setmTeamTwoID(int mTeamTwoID) {
        this.mTeamTwoID = mTeamTwoID;
    }

    public String getmTeamIconUrl() {
        return mTeamIconUrl;
    }

    public void setmTeamIconUrl(String mTeamIconUrl) {
        this.mTeamIconUrl = mTeamIconUrl;
    }

    public String getmTeamTwoIconUrl() {
        return mTeamTwoIconUrl;
    }

    public void setmTeamTwoIconUrl(String mTeamTwoIconUrl) {
        this.mTeamTwoIconUrl = mTeamTwoIconUrl;
    }

    public int getmTeamScore() {
        return mTeamScore;
    }

    public void setmTeamScore(int mTeamScore) {
        this.mTeamScore = mTeamScore;
    }

    public int getmTeamTwoScore() {
        return mTeamTwoScore;
    }

    public void setmTeamTwoScore(int mTeamTwoScore) {
        this.mTeamTwoScore = mTeamTwoScore;
    }

    public String getmMatchDate() {
        return mMatchDate;
    }

    public void setmMatchDate(String mMatchDate) {
        this.mMatchDate = mMatchDate;
    }

    public ArrayList<Goal> getmGoalsList() {
        return mGoalsList;
    }

    public void setmGoalsList(ArrayList<Goal> mGoalsList) {
        this.mGoalsList = mGoalsList;
    }

    public int getmTeamHalfScore() {
        return mTeamHalfScore;
    }

    public void setmTeamHalfScore(int mTeamHalfScore) {
        this.mTeamHalfScore = mTeamHalfScore;
    }

    public int getmTeamTwoHalfScore() {
        return mTeamTwoHalfScore;
    }

    public void setmTeamTwoHalfScore(int mTeamTwoHalfScore) {
        this.mTeamTwoHalfScore = mTeamTwoHalfScore;
    }

    public String getmStadium() {
        return mStadium;
    }

    public void setmStadium(String mStadium) {
        this.mStadium = mStadium;
    }

    public Long getmAudience() {
        return mAudience;
    }

    public void setmAudience(Long mAudience) {
        this.mAudience = mAudience;
    }

    public int getmSpieltag() {
        return mSpieltag;
    }

    public void setmSpieltag(int mSpieltag) {
        this.mSpieltag = mSpieltag;
    }
}