package com.example.sms.booking;

import com.example.sms.booking.BookingActivity;

public class WebService {
    public static boolean invokeBookMealWS(String mealId) {
        //block the meal -> should return meal checking for anyone else checking
        //check availability, if yes book it
        //if not error
        //return
        BookingActivity.errored = false;
        return true;
    }
}
