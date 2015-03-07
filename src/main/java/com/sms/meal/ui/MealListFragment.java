package com.sms.meal.ui;

import android.content.Intent;
import android.support.v4.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.sms.R;
import com.sms.meal.backend.MealProvider;
import com.sms.meal.backend.MyMealProvider;
import com.sms.meal.backend.ParseMealParser;
import com.sms.meal.domain.Meal;
import com.sms.transport.RequestCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cchiappini on 25/11/2014.
 */

public class MealListFragment extends ListFragment {
    private static final String TAG = "MealListFragment";
    private List<Meal> mMealList = new ArrayList<Meal>();
    static final int MEAL_BOOKED_REQUEST = 1;
    private MealProvider mealProvider = new MyMealProvider(new ParseMealParser());

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onListItemClick(ListView listView, View v, int position, long id) {
        Meal meal = ((MealAdapter) getListAdapter()).getItem(position);
        Intent bookingIntent = new Intent(getActivity(), MealActivity.class);
        bookingIntent.putExtra("meal", meal);
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
        mMealList.clear();
        mealProvider.getMealsFromServer(new MealsCallback());
    }

    private class MealAdapter extends ArrayAdapter<Meal> {

        public MealAdapter(List<Meal> meals) {
            super(getActivity(), 0, meals);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //if (closeToEnd(position && !mLoading)){
            //loadMoreData();
            //}
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

    protected class MealsCallback implements RequestCallback<List<Meal>> {

        @Override
        public void onRetrieved(List<Meal> data) {

            showContent(data);
        }
    }

    private void showContent(List<Meal> data) {
        //PROGRESS SET VISIBILITY GONE?
        MealAdapter adapter = new MealAdapter(data);
        setListAdapter(adapter);
    }

    private void showProgress() {
        //PROGRESS SET VISIBILITY VISIBLE?
    }

}
