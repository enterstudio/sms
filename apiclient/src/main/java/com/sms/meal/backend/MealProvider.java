package com.sms.meal.backend;

import com.sms.meal.domainmeal.MyMeal;
import com.sms.transport.RequestCallback;

import java.util.List;

/**
 * Created by cchiappini on 02/03/2015.
 */
public interface MealProvider {
    void getMeals(RequestCallback<List<MyMeal>> mealsCallback);

    void getMeal(RequestCallback<MyMeal> mealCallBack, String id);
}
