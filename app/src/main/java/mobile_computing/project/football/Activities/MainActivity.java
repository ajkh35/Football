package mobile_computing.project.football.Activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;


import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import mobile_computing.project.football.Models.Goal;
import mobile_computing.project.football.Models.Match;
import mobile_computing.project.football.R;
import mobile_computing.project.football.Services.AllTeamsService;
import mobile_computing.project.football.Services.GameResultsService;
import mobile_computing.project.football.Utilities.Constants;

public class MainActivity extends AppCompatActivity
        /*implements NavigationView.OnNavigationItemSelectedListener*/ {

    private JSONArray mGameResultsArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this,android.R.color.background_dark));
        }

        //FAB
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //Drawer
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.addDrawerListener(toggle);
//        toggle.syncState();

//        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);

        // Ranking activity launcher
        Button ranking = findViewById(R.id.ranking);
        ranking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(Constants.TAG, Constants.RANKING_LAUNCHED);
                JSONArray jsonArray = null;
                try {
                    String str = new AllTeamsService().execute().get();
                    jsonArray = (JSONArray) new JSONParser().parse(str);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Intent intent = new Intent(MainActivity.this, RankingActivity.class);
                if (jsonArray != null) {
                    intent.putExtra(Constants.ALL_TEAMS_ARRAY, jsonArray.toJSONString());
                }
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        // GameResults activity launcher
        Button gameResults = findViewById(R.id.game_results);
        gameResults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(Constants.TAG, Constants.GAME_RESULTS_LAUNCHED);
                try {
                    String str = new GameResultsService().execute().get();
                    mGameResultsArray = (JSONArray) new JSONParser().parse(str);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Intent intent = new Intent(MainActivity.this, GameResultsActivity.class);
                if(mGameResultsArray != null){ intent.putExtra(Constants.GAME_RESULTS_ARRAY,
                        getMatchList(mGameResultsArray)); }
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        // Quiz launcher
        Button quiz = findViewById(R.id.quiz);
        quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(Constants.TAG, Constants.QUIZ_LAUNCHED);

                // Applied the same service here so code might look repetitive
                try {
                    String str = new GameResultsService().execute().get();
                    mGameResultsArray = (JSONArray) new JSONParser().parse(str);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Intent intent = new Intent(MainActivity.this, QuizActivity.class);
                if(mGameResultsArray != null){ intent.putExtra(Constants.QUIZ_DATA,
                        getMatchList(mGameResultsArray)); }
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        // Launch the map activity
        Button map = findViewById(R.id.map);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(Constants.TAG, Constants.MAP_LAUNCHED);

                Intent intent = new Intent(MainActivity.this, MapActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
            }
        });
    }

    /**
     * Get array list of matches
     * @param array
     * @return ArrayList of matches
     */
    private ArrayList<Match> getMatchList(JSONArray array){
        ArrayList<Match> list = new ArrayList<>();
        int i=0;
        while (i < array.size()){

            // initialize json objects
            Match match = new Match();
            ArrayList<Goal> goalsList = new ArrayList<>();
            JSONObject json = (JSONObject) array.get(i);
            JSONObject group = (JSONObject) json.get("Group");
            JSONObject team1 = (JSONObject) json.get("Team1");
            JSONObject team2 = (JSONObject) json.get("Team2");
            JSONObject location = (JSONObject) json.get("Location");
            JSONArray results = (JSONArray) json.get("MatchResults");
            JSONArray goals = (JSONArray) json.get("Goals");

            // set the goals list
            int j=0;
            while(j < goals.size()){
                Goal goal = new Goal();
                goal.setmGoalTime((Long) ((JSONObject) goals.get(j)).get("MatchMinute"));
                goal.setmScorer((String) ((JSONObject) goals.get(j)).get("GoalGetterName"));
                goal.setmScore1((Long) ((JSONObject) goals.get(j)).get("ScoreTeam1"));
                goal.setmScore2((Long) ((JSONObject) goals.get(j)).get("ScoreTeam2"));
                goalsList.add(goal);
                j++;
            }

            // set the properties
            match.setmGroupID(Integer.parseInt(group.get("GroupID").toString()));
            match.setmGroupName((String) group.get("GroupName"));
            match.setmSpieltag(Integer.parseInt(group.get("GroupOrderID").toString()));
            match.setmLeagueID(Integer.parseInt(json.get("LeagueId").toString()));
            match.setmLeagueName((String) json.get("LeagueName"));
            match.setmMatchID(Integer.parseInt(json.get("MatchID").toString()));
            match.setmTeamIconUrl((String) team1.get("TeamIconUrl"));
            match.setmTeamID(Integer.parseInt(team1.get("TeamId").toString()));
            match.setmTeamName((String) team1.get("TeamName"));
            match.setmTeamTwoIconUrl((String) team2.get("TeamIconUrl"));
            match.setmTeamTwoID(Integer.parseInt(team2.get("TeamId").toString()));
            match.setmTeamTwoName((String) team2.get("TeamName"));
            match.setmMatchDate((String) json.get("MatchDateTime"));
            match.setmAudience((Long) json.get("NumberOfViewers"));
            match.setmGoalsList(goalsList);

            // set scores
            if(results.size() > 0){
                if(((JSONObject) results.get(0)).get("ResultName").equals(getString(R.string.half_time))){
                    match.setmTeamHalfScore(Integer.parseInt(((JSONObject)
                            results.get(0)).get("PointsTeam1").toString()));
                    match.setmTeamTwoHalfScore(Integer.parseInt(((JSONObject)
                            results.get(0)).get("PointsTeam2").toString()));
                    match.setmTeamScore(Integer.parseInt(((JSONObject)
                            results.get(1)).get("PointsTeam1").toString()));
                    match.setmTeamTwoScore(Integer.parseInt(((JSONObject)
                            results.get(1)).get("PointsTeam2").toString()));
                }else{
                    match.setmTeamScore(Integer.parseInt(((JSONObject)
                            results.get(0)).get("PointsTeam1").toString()));
                    match.setmTeamTwoScore(Integer.parseInt(((JSONObject)
                            results.get(0)).get("PointsTeam2").toString()));
                    match.setmTeamHalfScore(Integer.parseInt(((JSONObject)
                            results.get(1)).get("PointsTeam1").toString()));
                    match.setmTeamTwoHalfScore(Integer.parseInt(((JSONObject)
                            results.get(1)).get("PointsTeam2").toString()));
                }
            }

            // set location
            if(location != null)
                match.setmStadium((String) location.get("LocationStadium"));
            else
                match.setmStadium(getString(R.string.location_not_found));

            // add to list and increment
            list.add(match);
            i++;
        }
        return list;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

//    @SuppressWarnings("StatementWithEmptyBody")
//    @Override
//    public boolean onNavigationItemSelected(MenuItem item) {
//        // Handle navigation view item clicks here.
//        int id = item.getItemId();
//
//        if (id == R.id.nav_camera) {
//            // Handle the camera action
//        } else if (id == R.id.nav_gallery) {
//
//        } else if (id == R.id.nav_slideshow) {
//
//        } else if (id == R.id.nav_manage) {
//
//        } else if (id == R.id.nav_share) {
//
//        } else if (id == R.id.nav_send) {
//
//        }
//
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
//        return true;
//    }
}