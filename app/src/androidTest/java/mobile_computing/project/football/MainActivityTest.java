package mobile_computing.project.football;

import android.app.Activity;
import android.app.Instrumentation;
import android.support.test.rule.ActivityTestRule;
import android.widget.Button;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.concurrent.ExecutionException;

import mobile_computing.project.football.Activities.GameResultsActivity;
import mobile_computing.project.football.Activities.MainActivity;
import mobile_computing.project.football.Activities.MapActivity;
import mobile_computing.project.football.Activities.QuizActivity;
import mobile_computing.project.football.Activities.RankingActivity;
import mobile_computing.project.football.Services.AllTeamsService;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.Assert.assertNotNull;


public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule =
            new ActivityTestRule<>(MainActivity.class);
    private MainActivity mainActivity = null;

    @Before
    public void setUp() throws Exception {
        mainActivity = mainActivityActivityTestRule.getActivity();
    }

    @Test
    public void testLaunch() {
        Button ranking = mainActivity.findViewById(R.id.ranking);
        Button gameResults = mainActivity.findViewById(R.id.game_results);
        Button quiz = mainActivity.findViewById(R.id.quiz);
        Button map = mainActivity.findViewById(R.id.map);
        assertNotNull(ranking);
        assertNotNull(gameResults);
        assertNotNull(quiz);
        assertNotNull(map);
    }

    // Add monitors for activities
    Instrumentation.ActivityMonitor ranking_monitor = getInstrumentation()
            .addMonitor(RankingActivity.class.getName(), null, false);
    Instrumentation.ActivityMonitor game_results_monitor = getInstrumentation()
            .addMonitor(GameResultsActivity.class.getName(), null, false);
    Instrumentation.ActivityMonitor quiz_monitor = getInstrumentation()
            .addMonitor(QuizActivity.class.getName(), null, false);
    Instrumentation.ActivityMonitor map_monitor = getInstrumentation()
            .addMonitor(MapActivity.class.getName(), null, false);
    @Test
    public void testRankingButton() {
        assertNotNull(mainActivity.findViewById(R.id.ranking));

        // Ranking clicked
        onView(withId(R.id.ranking)).perform(click());
        Activity rankingActivity = getInstrumentation()
                .waitForMonitorWithTimeout(ranking_monitor,5000);
        assertNotNull(rankingActivity);
    }

    @Test
    public void testGameResultsButton() {
        assertNotNull(mainActivity.findViewById(R.id.game_results));

        // Game Results Clicked
        onView(withId(R.id.game_results)).perform(click());
        Activity gameResultsActivity = getInstrumentation()
                .waitForMonitorWithTimeout(game_results_monitor,5000);
        assertNotNull(gameResultsActivity);
    }

    @Test
    public void testQuizButton() {
        assertNotNull(mainActivity.findViewById(R.id.quiz));

        // Quiz Activity
        onView(withId(R.id.quiz)).perform(click());
        Activity quizActivity = getInstrumentation()
                .waitForMonitorWithTimeout(quiz_monitor,5000);
        assertNotNull(quizActivity);
    }

    @Test
    public void testMapButton() {
        assertNotNull(mainActivity.findViewById(R.id.map));

        // Map Activity
        onView(withId(R.id.map)).perform(click());
        Activity mapActivity = getInstrumentation()
                .waitForMonitorWithTimeout(map_monitor,5000);
        assertNotNull(mapActivity);
    }

    @After
    public void tearDown() throws Exception{
        mainActivity = null;
    }
}
