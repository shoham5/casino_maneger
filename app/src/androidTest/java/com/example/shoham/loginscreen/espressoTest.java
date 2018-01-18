package com.example.shoham.loginscreen;


import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import android.os.IBinder;
import android.support.test.espresso.Root;
import android.view.WindowManager;

import com.google.firebase.FirebaseApp;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class espressoTest {


    private String mStringToBetyped1;
    private String mStringToPassword1;
    private String mStringToBetyped;
    private String mStringToPassword;
    @Rule
    public ActivityTestRule<FIreBaseApp> mActivityRule = new ActivityTestRule<>(FIreBaseApp.class);

    @Before
    public void initValidString()
    {
        // Specify a valid string.
        mStringToBetyped = "a1234@a.com";
        mStringToBetyped1 = "123456";
    }

    @Test
    public void changeText_sameActivity()
    {
        // Type text and then press the button.
        onView(withId(R.id.emailEditTxtFiled)).perform(typeText(mStringToBetyped), closeSoftKeyboard());
        onView(withId(R.id.passwordEditTxtFiled)).perform(typeText(mStringToBetyped1), closeSoftKeyboard());
        // onView(withId(R.id.txt_input_password)).perform(typeText(mStringToPassword), closeSoftKeyboard());
        onView(withId(R.id.bmoveToUser)).perform(click());

        // Check that the text was changed.

        onView(withText("LOGIN faild-incoorect user name or password")).inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));

    }
}