package com.example.sms.meal;

import android.content.Context;
import android.util.Log;

import com.example.sms.booking.ParseWebService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cchiappini on 25/11/2014.
 */
public class Meals {
    private static Meals sMeals;
    private Context mAppContext;
    private List<Meal> mMeals;

    private Meals(Context appContext){
        this.mAppContext = mAppContext;
        mMeals = ParseWebService.getMeals();
    }

    public static Meals get(Context context) {
        if(sMeals == null) {
            sMeals = new Meals(context.getApplicationContext());
            Log.d("Meals", "meals are "+sMeals.mMeals.size());
        }
        return sMeals;
    }

    public List<Meal> getMeals() {
        return mMeals;
    }

    public Meal getMeal(String id) {
        for(Meal meal : mMeals) {
            if(meal.getId().equals(id)){
                return meal;
            }
        }
        return null;
    }
}
