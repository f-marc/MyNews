package com.fleury.marc.mynews;

import android.view.View;
import android.widget.Checkable;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isChecked;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.isA;
import static org.hamcrest.core.IsNot.not;

import com.fleury.marc.mynews.controllers.activities.NotificationActivity;


@RunWith(AndroidJUnit4.class)
public class SwitchTest {

    @Rule
    public ActivityTestRule<NotificationActivity> mActivityRule =
            new ActivityTestRule<>(NotificationActivity.class);


    @Test
    public void testSwitch_emptyKeyword() {

        onView(withId(R.id.notif_check_arts)).perform(click());
        onView(withId(R.id.activity_notification_switch)).perform(click());
        onView(withId(R.id.activity_notification_switch)).check(matches(not(isChecked())));
    }

    @Test
    public void testSwitch_emptyCategory() {

        onView(withId(R.id.activity_notification_edit_text)).perform(typeText("test"));
        onView(withId(R.id.activity_notification_switch)).perform(click());
        onView(withId(R.id.activity_notification_switch)).check(matches(not(isChecked())));
    }

    @Test
    public void testSwitch_TextAndCheck() {

        onView(withId(R.id.activity_notification_edit_text)).perform(typeText("test"));
        onView(withId(R.id.notif_check_arts)).perform(click());
        onView(withId(R.id.activity_notification_switch)).perform(click());
        onView(withId(R.id.activity_notification_switch)).check(matches(isChecked()));
    }

    /*public static ViewAction setChecked(final boolean checked) {
        return new ViewAction() {
            @Override
            public BaseMatcher<View> getConstraints() {
                return new BaseMatcher<View>() {
                    @Override
                    public boolean matches(Object item) {
                        return isA(Checkable.class).matches(item);
                    }

                    @Override
                    public void describeMismatch(Object item, Description mismatchDescription) {}

                    @Override
                    public void describeTo(Description description) {}
                };
            }

            @Override
            public String getDescription() {
                return null;
            }

            @Override
            public void perform(UiController uiController, View view) {
                Checkable checkableView = (Checkable) view;
                checkableView.setChecked(checked);
            }
        };
    }*/
}


