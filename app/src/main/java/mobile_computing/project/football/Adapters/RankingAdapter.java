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

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import mobile_computing.project.football.R;

/**
 * Created by ajay3 on 1/1/2018.
 */

public class RankingAdapter extends BaseAdapter {

    private Context mContext;
    private JSONArray mArray;

    public RankingAdapter(Context context, JSONArray array){
        mContext = context;
        mArray = array;
    }

    @Override
    public int getCount() {
        return mArray.size();
    }

    @Override
    public Object getItem(int i) {
        return mArray.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder holder;

        if(view == null){
            view = LayoutInflater.from(mContext)
                    .inflate(R.layout.team_list_item,viewGroup,false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }

        JSONObject object = (JSONObject) mArray.get(i);

        holder.mTeamName.setText(String.valueOf(object.get("TeamName")));
        holder.mRanking.setText(String.valueOf(i+1));
        try {
            holder.mImage.setImageBitmap(getImageBitmap(String.valueOf(object.get("TeamIconUrl"))));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
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
     * Class to contain the list item
     */
    private class ViewHolder{
        private TextView mRanking;
        private ImageView mImage;
        private TextView mTeamName;

        ViewHolder(View item_view){
            mRanking = item_view.findViewById(R.id.ranking);
            mImage = item_view.findViewById(R.id.team_logo);
            mTeamName = item_view.findViewById(R.id.team_name);
        }
    }
}
