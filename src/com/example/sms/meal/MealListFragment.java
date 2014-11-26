package com.example.sms.meal;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.ListFragment;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.sms.R;
import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cchiappini on 25/11/2014.
 */

public class MealListFragment extends ListFragment {
    private static final String TAG = "MealListFragment";
    private List<Meal> mMealList = new ArrayList<Meal>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getActivity().setTitle(R.string.meals_title);
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Meal");
        query.findInBackground(new FindCallback<ParseObject>() {

            @Override
            public void done(List<ParseObject> list, com.parse.ParseException e) {
                if (e == null) {
                    Log.d("MealListFragment", "no exception");
                    for (int i = 0; i < list.size(); i++) {

                        Object object = list.get(i);
                        Log.d("ParseWebService", ((ParseObject) object).getString("description").toString());
                        String description = ((ParseObject) object).getString("description").toString();
                        String name = ((ParseObject) object).getString("name").toString();
                        String id = ((ParseObject) object).getString("name").toString();
                        String location = ((ParseObject) object).getString("location").toString();
                        Meal meal = new Meal(id, name, description, location);
                        mMealList.add(meal);
                    }
                } else {
                    Log.e("MealListFragment", "exception " + e.getMessage());
                }
                ArrayAdapter<Meal> adapter = new ArrayAdapter<Meal>(getActivity(), android.R.layout.simple_expandable_list_item_1, mMealList);
                setListAdapter(adapter);

            }
        });
    }

    @Override
    public void onListItemClick(ListView listView, View v, int position, long id) {
        Meal meal = (Meal) (getListAdapter()).getItem(position);
        Intent bookingIntent = new Intent(getActivity(), MealActivity.class);
        bookingIntent.putExtra(MealFragment.EXTRA_MEAL_ID, meal.getId());
        bookingIntent.putExtra(MealFragment.EXTRA_MEAL_LOCATION, meal.getLocation());
        Log.d("MealListFragment", meal.getId());
        startActivity(bookingIntent);
    }

}
