package com.sms.booking.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.sms.R;
import com.sms.meal.ui.MealFragment;

public class BookingActivity extends FragmentActivity {

    /**
     * Called when the activity is first created.
     */
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

    }
}
