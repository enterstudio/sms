package com.sms.meal.ui;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;

import com.sms.R;
import com.sms.ui.SingleFragmentActivity;

/**
 * Created by cchiappini on 26/11/2014.
 */
public class MealActivity extends ActionBarActivity {
    private Toolbar toolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_meal);
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragmentContainer);
        if (fragment == null) {
            fragment = new MealFragment();
            fm.beginTransaction().add(R.id.fragmentContainer, fragment).commit();
        }

        toolbar = (Toolbar) findViewById(R.id.meal_toolbar);
        toolbar.setTitle("");

        if (toolbar != null) {
            //to make the toolbar transparent
            //toolbar.getBackground().setAlpha(0);
            setSupportActionBar(toolbar);
            //setSupportActionBar(toolbar);
            //getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back);
            //getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
            upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
            getSupportActionBar().setHomeAsUpIndicator(upArrow);
        }

    }
}
