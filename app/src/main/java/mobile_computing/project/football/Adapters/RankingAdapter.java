package mobile_computing.project.football.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import mobile_computing.project.football.R;

/**
 * Created by ajay3 on 1/1/2018.
 */

public class RankingAdapter extends BaseAdapter {

    private Context mContext;
    private JSONArray mArray;
    private ImageLoader mImageLoader;
    private RequestQueue mQueue;

    public RankingAdapter(Context context, JSONArray array){
        mContext = context;
        mArray = array;
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

        final ViewHolder holder;
        JSONObject object = (JSONObject) mArray.get(i);

        if(view == null){
            view = LayoutInflater.from(mContext)
                    .inflate(R.layout.team_list_item,viewGroup,false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }

        holder.mTeamName.setText(String.valueOf(object.get("TeamName")));
        holder.mRanking.setText(String.valueOf(i+1));
        holder.mImage.setImageUrl((String) object.get("TeamIconUrl"), mImageLoader);

        return view;
    }

    /**
     * Class to contain the list item
     */
    private class ViewHolder{
        private TextView mRanking;
        private NetworkImageView mImage;
        private TextView mTeamName;

        ViewHolder(View item_view){
            mRanking = item_view.findViewById(R.id.ranking);
            mImage = item_view.findViewById(R.id.team_logo);
            mTeamName = item_view.findViewById(R.id.team_name);
        }
    }
}
