package mobile_computing.project.football.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import mobile_computing.project.football.Models.Match;
import mobile_computing.project.football.R;
import mobile_computing.project.football.ResultDetailsActivity;
import mobile_computing.project.football.Utilities.Constants;
import mobile_computing.project.football.Utilities.ImageProcessor;

/**
 * Created by ajay3 on 1/11/2018.
 */

public class GameResultsAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<Match> mList;

    public GameResultsAdapter(Context context, ArrayList<Match> list){
        mContext = context;
        mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        final Match match = mList.get(i);
        ViewHolder holder;

        // initialize view holder
        if(view == null){
            view = LayoutInflater.from(mContext).inflate(R.layout.games_list_item,viewGroup,false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }

        // set data to the held views
        holder.mTeamOne.setText(match.getmTeamName());
        holder.mTeamTwo.setText(match.getmTeamTwoName());

        try {
            holder.mTeamOneLogo.setImageBitmap(ImageProcessor.getImageFromUrl(match.getmTeamIconUrl()));
            holder.mTeamTwoLogo.setImageBitmap(ImageProcessor.getImageFromUrl(match.getmTeamTwoIconUrl()));
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        holder.mScoreOne.setText(String.valueOf(match.getmTeamScore()));
        holder.mScoreTwo.setText(String.valueOf(match.getmTeamTwoScore()));

        // set click listener for each match
        holder.mListItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(Constants.TAG, Constants.GAME_RESULT_CLICKED);

                // start the details activity
                Intent intent = new Intent(mContext, ResultDetailsActivity.class);
                intent.putExtra(Constants.GAME_RESULT_CLICKED, match);
                mContext.startActivity(intent);
            }
        });

        return view;
    }

    /**
     * Private class to hold views together
     */
    private class ViewHolder{

        private TextView mTeamOne;
        private TextView mTeamTwo;
        private ImageView mTeamOneLogo;
        private ImageView mTeamTwoLogo;
        private TextView mScoreOne;
        private TextView mScoreTwo;
        private RelativeLayout mListItem;

        ViewHolder(View view){
            mListItem = view.findViewById(R.id.list_item);
            mTeamOne = view.findViewById(R.id.team_1);
            mTeamTwo = view.findViewById(R.id.team_2);
            mTeamOneLogo = view.findViewById(R.id.team1_logo);
            mTeamTwoLogo = view.findViewById(R.id.team2_logo);
            mScoreOne = view.findViewById(R.id.score1);
            mScoreTwo = view.findViewById(R.id.score2);
        }
    }
}