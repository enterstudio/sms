package com.sms.meal.domain;

import android.os.Parcel;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertEquals;

@RunWith(RobolectricTestRunner.class)
public class MealTest {
    @Test
    public void testMealParcelable(){
        Meal meal = new Meal("id", "name","description","location","owner");
        Parcel parcel = Parcel.obtain();
        meal.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);

        Meal mealReadBack = Meal.CREATOR.createFromParcel(parcel);
        assertEquals(meal, mealReadBack);

    }
}
