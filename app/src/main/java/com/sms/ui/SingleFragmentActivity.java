package com.sms.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.sms.R;
import com.sms.meal.ui.get.MealFragment;

/**
 * Created by cchiappini on 25/11/2014.
 */
public abstract class SingleFragmentActivity extends FragmentActivity{


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_meal);
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragmentContainer);
        if(fragment == null) {
            fragment = new MealFragment();
            fm.beginTransaction().add(R.id.fragmentContainer, fragment).commit();
        }

       // toolbar = (Toolbar) findViewById(R.id.meal_toolbar);
//        if (toolbar != null) {
//            //setSupportActionBar(toolbar);
//            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back);
//            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        }


    }
}
