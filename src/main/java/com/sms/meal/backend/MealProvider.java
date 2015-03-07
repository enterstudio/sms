package com.sms.meal.backend;

import com.sms.meal.domain.Meal;
import com.sms.transport.RequestCallback;

import java.util.List;

/**
 * Created by cchiappini on 02/03/2015.
 */
public interface MealProvider {
    void getMealsFromServer(RequestCallback<List<Meal>> mealsCallback);
}
