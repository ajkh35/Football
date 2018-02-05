package mobile_computing.project.football.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

import mobile_computing.project.football.Models.Match;
import mobile_computing.project.football.R;
import mobile_computing.project.football.Activities.ResultDetailsActivity;
import mobile_computing.project.football.Utilities.Constants;

/**
 * Created by ajay3 on 1/11/2018.
 */

public class GameResultsAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<Match> mList;
    private ImageLoader mImageLoader;
    private RequestQueue mQueue;

    public GameResultsAdapter(Context context, ArrayList<Match> list){
        mContext = context;
        mList = list;
        mQueue = Volley.newRequestQueue(context);
        mImageLoader = new ImageLoader(mQueue, new ImageLoader.ImageCache() {

            private LruCache<String, Bitmap> mCache = new LruCache<>(10);

            @Override
            public Bitmap getBitmap(String url) {
                return mCache.get(url);
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {
                mCache.put(url,bitmap);
            }
        });
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
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
        holder.mTeamOneLogo.setImageUrl(match.getmTeamIconUrl(), mImageLoader);
        holder.mTeamTwoLogo.setImageUrl(match.getmTeamTwoIconUrl(), mImageLoader);

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
                ((Activity) mContext).overridePendingTransition(
                        android.R.anim.slide_in_left,android.R.anim.slide_out_right
                );
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
        private NetworkImageView mTeamOneLogo;
        private NetworkImageView mTeamTwoLogo;
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