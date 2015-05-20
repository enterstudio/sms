package com.sms.meal.backend;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.sms.meal.domainmeal.MyMeal;
import com.sms.transport.RequestCallback;

import java.util.List;

/**
 * Created by cchiappini on 07/03/2015.
 */
public class MealProviderFromCache implements MealProvider {
    private MealParser mealParser;

    public MealProviderFromCache(MealParser mealParser){
        this.mealParser = mealParser;
    }

    @Override
    public void getMeals(final RequestCallback<List<MyMeal>> mealsCallback) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Meal");
        query.fromLocalDatastore();
        query.findInBackground(new FindCallback<ParseObject>() {

            @Override
            public void done(List<ParseObject> list, com.parse.ParseException e) {
                if (e == null && list.size() >0) {
                    List<MyMeal> mMealList = mealParser.extractUnbookedMealsFromListOfObject(list);
                    mealsCallback.onRetrieved(mMealList);
                } else {
                    mealsCallback.onError("No meals found not found ");
                }
            }
        });
    }

    @Override
    public void getMeal(final RequestCallback<MyMeal> mealCallback, String id) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Meal");
        query.fromLocalDatastore();
        query.getInBackground(id, new GetCallback<ParseObject>() {

            @Override
            public void done(ParseObject meal, com.parse.ParseException e) {
                if (e == null) {
                    mealCallback.onRetrieved(mealParser.parseMeal(meal));
                }
                else{
                    mealCallback.onError("Meal with id "+meal.getObjectId()+" not found ");
                }
            }
        });
    }

    @Override
    public void addMeal(MyMeal meal) {
        //none
    }
}
