package com.sms.meal.backend;

import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.sms.meal.domain.Meal;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cchiappini on 05/03/2015.
 */
public class ParseMealParser implements MealParser<ParseObject> {

    public List<Meal> extractUnbookedMealsFromListOfObject(List<ParseObject> list) {
        List<Meal> mMealList = new ArrayList<Meal>();
        for (int i = 0; i < list.size(); i++) {
            Object object = list.get(i);
            boolean booked = ((ParseObject) object).getBoolean("booked");
            if (!booked) {
                String description = ((ParseObject) object).getString("description");
                String name = ((ParseObject) object).getString("name");
                String id = ((ParseObject) object).getObjectId();
                String location = ((ParseObject) object).getString("location");
                String owner = ((ParseObject) object).getString("owner");
                ParseFile image = ((ParseObject) object).getParseFile("image");

                Meal meal = new Meal(id, name, description, location, owner);
                try {
                    meal.setImage(image.getData());
                } catch (ParseException e) {
                    meal.setImage(new byte[0]);
                }
                mMealList.add(meal);
            }
        }
        return mMealList;
    }

}