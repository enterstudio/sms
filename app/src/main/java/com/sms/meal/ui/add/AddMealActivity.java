package com.sms.meal.ui.add;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import com.sms.R;

/**
 * Created by cchiappini on 20/05/2015.
 */
public class AddMealActivity extends ActionBarActivity {
    private Toolbar toolbar;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_add_meal);
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.addFragmentContainer);
        if (fragment == null) {
            fragment = new AddMealFragment();
            fm.beginTransaction().add(R.id.addFragmentContainer, fragment).commit();
        }

        toolbar = (Toolbar) findViewById(R.id.add_meal_toolbar);
        toolbar.setTitle("Share meal");

        if (toolbar != null) {
            //to make the toolbar transparent
            //toolbar.getBackground().setAlpha(0);
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
            upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
            getSupportActionBar().setHomeAsUpIndicator(upArrow);
        }
    }
}
