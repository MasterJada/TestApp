package com.example.testapp.tab_one

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.example.testapp.R
import com.example.testapp.main.MainActivity


import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class FirstTabFragmentTest{

    @get: Rule
    var activityRule: ActivityTestRule<MainActivity>
            = ActivityTestRule(MainActivity::class.java)

    @Test
    fun checkText(){
        onView(withId(R.id.tv_name))
            .check(matches(withText("Oleg Reksha")))
    }

    @Test
    fun goTosecondTab(){
        onView(withText("Tab 1"))
            .perform(click())

        onView(withId(R.id.rv_feed1))
            .check(matches(isDisplayed()))


    }


}