package com.curencyconv.enumas.currencyconverter;

import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.enumas.curconv.mvp.ui.list.CurrencyListActivity;
import com.enumas.curconv.mvp.utils.EspressoIdlingResource;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class UITest {

    @Rule
    public final ActivityTestRule<CurrencyListActivity> mRule = new ActivityTestRule<>(CurrencyListActivity.class);

    @Before
    public void registerIdlingResource() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getIdlingResource());
    }

    @After
    public void unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getIdlingResource());
    }

    /**
     * Launch activity, click on recycler view item, verify inversion button displayed
     */
    @Test
    public void launchMainScreen() {

        ViewAction viewAction = RecyclerViewActions.actionOnItemAtPosition(1, click());
        onView(withId(R.id.recycler_view)).perform(viewAction);
        onView(withId(R.id.btn_invert)).check(matches(isDisplayed()));

    }

}
