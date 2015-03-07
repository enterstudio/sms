package com.sms.meal.backend;

import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.sms.meal.domainmeal.MyMeal;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cchiappini on 05/03/2015.
 */
public class ParseMealParser implements MealParser<ParseObject> {

    public List<MyMeal> extractUnbookedMealsFromListOfObject(List<ParseObject> list) {
        List<MyMeal> mMealList = new ArrayList<MyMeal>();
        for (int i = 0; i < list.size(); i++) {
            ParseObject object = list.get(i);
            boolean booked = object.getBoolean("booked");
            if (!booked) {
                mMealList.add(parseMeal(object));
            }
        }
        return mMealList;
    }

    @Override
    public MyMeal parseMeal(ParseObject object) {
        String description = object.getString("description");
        String name = object.getString("name");
        String id = object.getObjectId();
        String location = object.getString("location");
        String owner = object.getString("owner");
        ParseFile image = object.getParseFile("image");

        MyMeal meal = new MyMeal(id, name, description, location, owner);
        try {
            meal.setImage(image.getData());
        } catch (ParseException e) {
            meal.setImage(new byte[0]);
        }
        return meal;
    }

}