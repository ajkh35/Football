package mobile_computing.project.football.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import mobile_computing.project.football.Models.Goal;
import mobile_computing.project.football.R;

/**
 * Created by ajay3 on 1/15/2018.
 */

public class GoalsAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<Goal> mGoalList;

    public GoalsAdapter(Context context, ArrayList<Goal> list){
        mContext = context;
        mGoalList = list;
    }

    @Override
    public int getCount() {
        if(mGoalList == null)
            return 0;
        else
            return mGoalList.size();
    }

    @Override
    public Object getItem(int i) {
        return mGoalList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder holder;
        Goal goal = mGoalList.get(i);

        // initialize views holder object
        if(view == null){
            view = LayoutInflater.from(mContext).inflate(R.layout.goals_list_item,viewGroup,false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }

        // set the data to held views
        String score_time = goal.getmGoalTime() + mContext.getString(R.string.dot) +
                " " + mContext.getString(R.string.min);
        holder.mScoreTime.setText(score_time);

        String[] scorer_name = goal.getmScorer().split(" ");
        String first_name = scorer_name[0],scorer;
        if(scorer_name.length > 1)
            scorer = scorer_name[1] + mContext.getString(R.string.comma) + " " +  first_name;
        else
            scorer = first_name;
        holder.mScorerName.setText(scorer);

        String score = goal.getmScore1() + mContext.getString(R.string.colon) + goal.getmScore2();
        holder.mScore.setText(score);

        return view;
    }

    private class ViewHolder{
        private TextView mScoreTime;
        private TextView mScorerName;
        private TextView mScore;

        ViewHolder(View view){
            mScoreTime = view.findViewById(R.id.score_time);
            mScorerName = view.findViewById(R.id.scorer);
            mScore = view.findViewById(R.id.score);
        }
    }
}
