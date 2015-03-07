package com.sms.meal.backend;

import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.sms.meal.domain.Meal;
import com.sms.transport.RequestCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cchiappini on 02/03/2015.
 */
public class MyMealProvider implements MealProvider {

    private static final String TAG = "MyMealProvider";
    private MealParser mealParser;

    public MyMealProvider(MealParser mealParser){
        this.mealParser = mealParser;
    }

    @Override
    public void getMealsFromServer(final RequestCallback<List<Meal>> mealsCallback) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Meal");
        query.findInBackground(new FindCallback<ParseObject>() {

            @Override
            public void done(List<ParseObject> list, com.parse.ParseException e) {
                List<Meal> mMealList = new ArrayList<Meal>();
                if (e == null) {
                    mMealList = mealParser.extractUnbookedMealsFromListOfObject(list);
                } else {
                    Log.e(TAG, "exception " + e.getMessage());
                }
                mealsCallback.onRetrieved(mMealList);
            }
        });
    }

}
