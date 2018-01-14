package mobile_computing.project.football.Models;

import java.io.Serializable;

/**
 * Created by ajay3 on 1/12/2018.
 */

public class Match implements Serializable{

    private int mGroupID;
    private String mGroupName;
    private int mGroupOrderID;
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

    public int getmGroupOrderID() {
        return mGroupOrderID;
    }

    public void setmGroupOrderID(int mGroupOrderID) {
        this.mGroupOrderID = mGroupOrderID;
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
                + "grouporderid=" + mGroupOrderID + "}";
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
}