package com.example.eagolukas.retrosqlite.main.ui;

import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.eagolukas.retrosqlite.R;
import com.example.eagolukas.retrosqlite.main.IntentServiceIdlingResource;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class TestMainActivity {
    private IntentServiceIdlingResource idlingResource;

    /*@Before
    public void registerIntentServiceIdlingResource() {
        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        idlingResource = new IntentServiceIdlingResource(instrumentation.getTargetContext());
        Espresso.registerIdlingResources(idlingResource);
    }

    @After
    public void unregisterIntentServiceIdlingResource() {
        Espresso.unregisterIdlingResources(idlingResource);
    }
*/
    @Rule
    public final ActivityTestRule<MainActivity> main = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testShow() throws Exception {
        onView(withText("Hello")).check(ViewAssertions.matches(isDisplayed()));
    }

    @Test
    public void testReadPosts() throws Exception {
        onView(withId(R.id.buttonRead)).perform(click());
        onView(withText("post : 12 nesciunt quas odio")).check(ViewAssertions.matches(isDisplayed()));
    }

    @Test
    public void testDownloadPost() throws Exception {
//        onView(withText("Company Name")).check(ViewAssertions.matches(isDisplayed())); // v onCreate pred spustenim je attachnute view...
        onView(withText("id :5")).check(ViewAssertions.matches(isDisplayed()));
        onView(withText("post :nesciunt quas odio")).check(ViewAssertions.matches(isDisplayed()));
    }

    @Test
    public void testWriteNextPost() throws Exception {
        onView(withId(R.id.buttonWrite)).perform(click());
        onView(withId(R.id.buttonRead)).perform(click());
        onView(withText("post : 17 nesciunt quas odio")).check(ViewAssertions.matches(isDisplayed()));

    }


}
