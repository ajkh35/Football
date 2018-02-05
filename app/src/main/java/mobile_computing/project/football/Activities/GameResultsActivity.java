package mobile_computing.project.football.Activities;

import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

import mobile_computing.project.football.Fragments.GameResultsFragment;
import mobile_computing.project.football.Models.Match;
import mobile_computing.project.football.R;
import mobile_computing.project.football.Utilities.Constants;

public class GameResultsActivity extends AppCompatActivity {

    private ArrayList<Match> mGamesList;
    private TextView mActivityHeader;
    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;
    private int mPageCount;
    private Button mPrevious;
    private Button mNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_results);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(
                    ContextCompat.getColor(this,android.R.color.background_dark)
            );
        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mPager = findViewById(R.id.pager);
        mActivityHeader = findViewById(R.id.activity_header);
        mPrevious = findViewById(R.id.prev);
        mNext = findViewById(R.id.next);

        // setup the view pager adapter
        mPagerAdapter = new GameResultsPagerAdapter(getSupportFragmentManager());

        // Get list from bundled data
        Bundle bundle = getIntent().getExtras();

        if (bundle != null){
            mGamesList = (ArrayList<Match>) bundle.get(Constants.GAME_RESULTS_ARRAY);

            if(mGamesList != null){
                // set the header
                mActivityHeader.setText(mGamesList.get(0).getmLeagueName());

                // set the page count
                mPageCount = mGamesList.get(0).getmSpieltag();
                mPager.setAdapter(mPagerAdapter);

                // set the pager to start from current match day
                mPager.setCurrentItem(mGamesList.get(0).getmSpieltag());
            }else{
                // set the header
                mActivityHeader.setText(getString(R.string.bundesliga));

                // set the page count
                mPageCount = 1;
                mPager.setAdapter(mPagerAdapter);

                // set the pager to start from current match day
                mPager.setCurrentItem(0);
            }

        }

        // change using next and previous
        mPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPager.setCurrentItem(mPager.getCurrentItem() - 1);
            }
        });

        mNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPager.setCurrentItem(mPager.getCurrentItem() + 1);
            }
        });
    }

    /**
     * Adapter class for the view pager
     */
    private class GameResultsPagerAdapter extends FragmentStatePagerAdapter{

        GameResultsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            return GameResultsFragment
                    .newInstance(mGamesList.get(0).getmLeagueName(), position+1);
        }

        @Override
        public int getCount() {
            return mPageCount;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}