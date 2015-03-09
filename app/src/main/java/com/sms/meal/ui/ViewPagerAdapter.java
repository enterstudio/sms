package com.sms.meal.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

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
            ShareMealFragment shareMealFragment = new ShareMealFragment();
            return shareMealFragment;
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
