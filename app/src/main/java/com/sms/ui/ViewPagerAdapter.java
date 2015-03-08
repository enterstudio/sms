package com.sms.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.sms.meal.ui.MealListFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private CharSequence titles[];

    public ViewPagerAdapter(FragmentManager fragmentManager, CharSequence titles[]) {
        super(fragmentManager);

        this.titles = titles;

    }

    @Override
    public Fragment getItem(int position) {

        if(position == 0)
        {
            MealListFragment mealListFragment = new MealListFragment();
            return mealListFragment;
        }
        else
        {
            MealListFragment mealListFragment = new MealListFragment();
            return mealListFragment;
        }


    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public int getCount() {
        return titles.length;
    }
}
