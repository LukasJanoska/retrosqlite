package com.example.eagolukas.retrosqlite;

import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.eagolukas.retrosqlite.main.ui.MainActivity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.Rule;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class ProsteTest {

    @Rule
    public final ActivityTestRule<MainActivity> main = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testShow() throws Exception {
        onView(withText("Hello")).check(ViewAssertions.matches(isDisplayed()));
    }

    @Test
    public void downloadSuccess()
    {
 /*       onView(withId(R.id.txt_comp).matches()typeText("chris.ripple@grapecity.com");

        onView(withId(R.id.password).perform(typeText("MyPass");

        onView(withId(R.string.submit)).perform(click());

        // successful login moves user to next screen
        intended(hasComponent(new ComponentName(getTargetContext(), PostLoginActivity.class)));
 */   }
}
