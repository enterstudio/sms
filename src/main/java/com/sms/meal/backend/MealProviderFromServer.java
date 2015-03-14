package com.sms.meal.backend;

import android.util.Log;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.sms.meal.domain.Meal;
import com.sms.transport.RequestCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cchiappini on 02/03/2015.
 */
public class MealProviderFromServer implements MealProvider {

    private static final String TAG = "MyMealProvider";
    private MealParser mealParser;

    public MealProviderFromServer(MealParser mealParser){
        this.mealParser = mealParser;
    }

    @Override
    public void getMealsFromServer(final RequestCallback<List<Meal>> mealsCallback) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Meal");
        query.findInBackground(new FindCallback<ParseObject>() {

            @Override
            public void done(List<ParseObject> list, com.parse.ParseException e) {
                if (e == null) {
                    List<Meal> mMealList = mealParser.extractUnbookedMealsFromListOfObject(list);
                    ParseObject.pinAllInBackground(list);
                    mealsCallback.onRetrieved(mMealList);
                } else {
                    Log.e(TAG, "exception " + e.getMessage());
                    mealsCallback.onError("No meals found not found ");
                }
            }
        });
    }

    @Override
    public void getMealFromServer(final RequestCallback<Meal> mealCallback, String id) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Meal");
        query.fromLocalDatastore();
        query.getInBackground(id, new GetCallback<ParseObject>() {

            @Override
            public void done(ParseObject meal, com.parse.ParseException e) {
                if (e == null) {
                    mealCallback.onRetrieved(mealParser.parseMeal(meal));
                }
                else{
                    Log.e(TAG, "exception " + e.getMessage());

                    mealCallback.onError("Meal with id "+meal.getObjectId()+" not found ");
                }
            }
        });
    }

}
