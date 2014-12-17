package com.example.sms.booking;

import com.example.sms.meal.Meal;

import java.util.ArrayList;
import java.util.List;

public class LocalWebService {
    private static final List<Meal> meals = new ArrayList<Meal>();


    public static boolean invokeBookMealWS(String mealId) {
        System.out.println("meal id" +mealId);
        Meal meal = meals.get(0);
        //block the meal -> should return meal checking for anyone else checking
        if(meal.isBooked()){
           BookingActivity.errored = true;
            return false;
        }
        else{
            BookingActivity.errored = false;
            return true;
        }

    }
}
