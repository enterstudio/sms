package com.example.sms.booking;

import com.example.sms.meal.Meal;

import java.util.List;

public class BookingCommand {
    public boolean bookMeal(String mealId){
        return LocalWebService.invokeBookMealWS(mealId);
    }
    public List<Meal> getMeals() {
        return ParseWebService.getMeals();
    }

}
