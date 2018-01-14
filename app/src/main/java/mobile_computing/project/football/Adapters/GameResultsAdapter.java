package mobile_computing.project.football.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import mobile_computing.project.football.Models.Match;
import mobile_computing.project.football.R;

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

        ViewHolder holder = null;
        if(view == null){
            view = LayoutInflater.from(mContext).inflate(R.layout.games_list_item,viewGroup,false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }

        Match match = mList.get(i);
        holder.mTeamOne.setText(match.getmTeamName());
        holder.mTeamTwo.setText(match.getmTeamTwoName());

        try {
            holder.mTeamOneLogo.setImageBitmap(getImageBitmap(match.getmTeamIconUrl()));
            holder.mTeamTwoLogo.setImageBitmap(getImageBitmap(match.getmTeamTwoIconUrl()));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        holder.mScoreOne.setText(String.valueOf(match.getmTeamScore()));
        holder.mScoreTwo.setText(String.valueOf(match.getmTeamTwoScore()));
        return view;
    }

    /**
     * Get Image Bitmap
     * @param url
     * @return Returns the bitmap image
     */
    @SuppressLint("StaticFieldLeak")
    @org.jetbrains.annotations.Contract(pure = true)
    private Bitmap getImageBitmap(String url) throws IOException, ExecutionException, InterruptedException {

        Bitmap bmp;

        bmp = new AsyncTask<String,Integer,Bitmap>(){
            @Override
            protected Bitmap doInBackground(String... strings) {

                Bitmap bmp = null;
                try {
                    HttpURLConnection connection = (HttpURLConnection) new URL(strings[0]).openConnection();
                    connection.setDoInput(true);
                    connection.connect();
                    InputStream stream = connection.getInputStream();
                    bmp = BitmapFactory.decodeStream(stream);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return bmp;
            }
        }.execute(url).get();

        return bmp;
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

        ViewHolder(View view){
            mTeamOne = view.findViewById(R.id.team_1);
            mTeamTwo = view.findViewById(R.id.team_2);
            mTeamOneLogo = view.findViewById(R.id.team1_logo);
            mTeamTwoLogo = view.findViewById(R.id.team2_logo);
            mScoreOne = view.findViewById(R.id.score1);
            mScoreTwo = view.findViewById(R.id.score2);
        }
    }
}