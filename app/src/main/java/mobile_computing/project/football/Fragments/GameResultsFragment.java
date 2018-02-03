package mobile_computing.project.football.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;

import mobile_computing.project.football.Adapters.GameResultsAdapter;
import mobile_computing.project.football.Models.Goal;
import mobile_computing.project.football.Models.Match;
import mobile_computing.project.football.R;
import mobile_computing.project.football.Utilities.Constants;

public class GameResultsFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private ArrayList<Match> mGamesList;
    private int mSpieltag;
    private String mLeague;
    private RequestQueue mQueue;

    private OnFragmentInteractionListener mListener;

    private ListView mGamesListView;
    private TextView mLeagueName;

    public GameResultsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GameResultsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GameResultsFragment newInstance(String param1, int param2) {
        GameResultsFragment fragment = new GameResultsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putInt(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mLeague = getArguments().getString(ARG_PARAM1);
            mSpieltag = getArguments().getInt(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_game_results, container, false);

        mGamesListView = view.findViewById(R.id.games_list);
        mLeagueName = view.findViewById(R.id.league_name);

        // set league name
        mLeagueName.setText(getString(R.string.spieltag) + " " + mSpieltag);

        // initialize the request queue
        mQueue = Volley.newRequestQueue(getActivity());

        // Get the list for the match day
        String url = Constants.CURRENT_MATCHDAY_URL + "/2017/" + mSpieltag;
        final GameResultsAdapter[] adapter = {null};
        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                // set games list adapter
                try {
                    adapter[0] = new GameResultsAdapter(getActivity(), getListFromJson(response));
                    mGamesListView.setAdapter(adapter[0]);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        mQueue.add(request);

        return view;
    }

    /**
     * Get games list from json data
     * @param data
     * @return arraylist from json
     */
    private ArrayList<Match> getListFromJson(String data) throws ParseException {
        ArrayList<Match> list = new ArrayList<>();
        JSONArray array = (JSONArray) new JSONParser().parse(data);

        for(int i=0;i<array.size();i++){
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
        }

        return list;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
//        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
