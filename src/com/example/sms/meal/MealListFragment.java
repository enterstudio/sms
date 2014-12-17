package com.example.sms.meal;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

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
    static final int MEAL_BOOKED_REQUEST = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onListItemClick(ListView listView, View v, int position, long id) {
        Meal meal = ((MealAdapter) getListAdapter()).getItem(position);
        Intent bookingIntent = new Intent(getActivity(), MealActivity.class);
        bookingIntent.putExtra(MealFragment.EXTRA_MEAL_ID, meal.getId());
        bookingIntent.putExtra(MealFragment.EXTRA_MEAL_LOCATION, meal.getLocation());
        bookingIntent.putExtra(MealFragment.EXTRA_MEAL_DESCRIPTION, meal.getDescription());
        bookingIntent.putExtra(MealFragment.EXTRA_MEAL_UPLOADED_BY, meal.getOwner());
        startActivityForResult(bookingIntent, MEAL_BOOKED_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == MEAL_BOOKED_REQUEST) {
            if (data == null) {return;}
            String id = data.getStringExtra("id");
            for(Meal meal : mMealList){
                if(meal.getId().equals(id)){
                    mMealList.remove(meal);
                    return;
                }
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("MealListFragment", "onResume");
        mMealList.clear();
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Meal");
        query.findInBackground(new FindCallback<ParseObject>() {

            @Override
            public void done(List<ParseObject> list, com.parse.ParseException e) {
                if (e == null) {
                    for (int i = 0; i < list.size(); i++) {
                        Object object = list.get(i);
                        String description = ((ParseObject) object).getString("description").toString();
                        String name = ((ParseObject) object).getString("name").toString();
                        String id = ((ParseObject) object).getObjectId();
                        String location = ((ParseObject) object).getString("location").toString();
                        String owner = ((ParseObject) object).getString("owner").toString();

                        boolean booked = ((ParseObject) object).getBoolean("booked");
                        if(!booked) {
                            Meal meal = new Meal(id, name, description, location, owner);
                            mMealList.add(meal);
                        }
                    }
                } else {
                    Log.e(TAG, "exception " + e.getMessage());
                }
                MealAdapter adapter = new MealAdapter(mMealList);
                setListAdapter(adapter);
            }
        });


    }

    private class MealAdapter extends ArrayAdapter<Meal> {

        public MealAdapter(List<Meal> meals) {
            super(getActivity(), 0, meals);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null) {
                convertView = getActivity().getLayoutInflater().inflate(R.layout.list_meal, null);
            }
            Meal meal = getItem(position);

            TextView titleTextView = (TextView) convertView.findViewById(R.id.meal_list_item_titleTextView);
            titleTextView.setText(meal.getName());

            TextView ownerTextView = (TextView) convertView.findViewById(R.id.meal_list_item_ownerTextView);
            ownerTextView.setText("by "+meal.getOwner());
            return convertView;
        }
    }

}
