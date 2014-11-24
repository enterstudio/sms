package com.example.sms.booking;

public class BookingCommand {
    public boolean bookMeal(String mealId){
        return WebService.invokeBookMealWS(mealId);
    }
}
