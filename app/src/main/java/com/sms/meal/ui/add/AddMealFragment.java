package com.sms.meal.ui.add;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sms.R;
import com.sms.ShareMyMealApplication;
import com.sms.meal.backend.MyMealProvider;

import javax.inject.Inject;

/**
 * Created by cchiappini on 20/05/2015.
 */
public class AddMealFragment extends Fragment {
    @Inject
    MyMealProvider mealProvider;

    private ShareMyMealApplication app;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        app = (ShareMyMealApplication) activity.getApplication();
        app.inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.add_meal, parent, false);

        return v;
    }
}
