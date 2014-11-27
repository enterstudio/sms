package com.example.sms.booking;

import android.net.ParseException;
import android.util.Log;
import android.widget.Toast;

import com.example.sms.meal.Meal;
import com.example.sms.meal.MealId;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParseWebService {
    private static final List<Meal> meals = new ArrayList<Meal>();

    public static List<Meal> getMeals() {

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Meal");
        query.findInBackground(new FindCallback<ParseObject>() {

            @Override
            public void done(List<ParseObject> list, com.parse.ParseException e) {
                if (e == null) {
                    for (int i = 0; i < list.size(); i++) {

                        Object object = list.get(i);
                        Log.d("ParseWebService", ((ParseObject) object).getString("description").toString());
                        String description = ((ParseObject) object).getString("description").toString();
                        String name = ((ParseObject) object).getString("name").toString();
                        String id = ((ParseObject) object).getString("name").toString();
                        String location = ((ParseObject) object).getString("location").toString();
                        String owner = ((ParseObject) object).getString("owner").toString();
                        Meal meal = new Meal(id, name, description, location, owner);
                        meals.add(meal);
                    }
                } else {
                    //error
                }


            }
        });
        Log.d("ParseWebService", "meals are "+meals.size());
    return meals;

    }

}
