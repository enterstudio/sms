package com.sms.meal.backend;

import com.parse.ParseObject;
import com.sms.meal.domainmeal.MyMeal;

import java.util.List;

/**
 * Created by cchiappini on 05/03/2015.
 */
public interface MealParser<T> {
    List<MyMeal> extractUnbookedMealsFromListOfObject(List<T> list);

    MyMeal parseMeal(T meal);

    ParseObject parseObjectMeal(MyMeal meal);
}
