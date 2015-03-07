package com.sms.meal.ui;

import android.support.v4.app.Fragment;

import com.sms.ui.SingleFragmentActivity;

/**
 * Created by cchiappini on 25/11/2014.
 */
public class MealListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new MealListFragment();
    }
}
