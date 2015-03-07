package com.sms.meal.backend;

import com.sms.meal.domain.Meal;

import java.util.List;

/**
 * Created by cchiappini on 05/03/2015.
 */
public interface MealParser<T> {
    List<Meal> extractUnbookedMealsFromListOfObject(List<T> list);
}
