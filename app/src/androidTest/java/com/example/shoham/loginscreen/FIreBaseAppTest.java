package com.example.shoham.loginscreen;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

// Tests for MainActivity

@LargeTest
@RunWith(AndroidJUnit4.class)
public class FIreBaseAppTest {

    @Rule
    public ActivityTestRule<FIreBaseApp> mActivityTestRule = new ActivityTestRule<>(FIreBaseApp.class);

    @Test
    public void validateEditText() {
        onView(withId(R.id.emailEditTxtFiled)).perform(typeText("a@a.com")).check(matches(withText("a@a.com")));
    }
    //public void fIreBaseAppTest() {}
}