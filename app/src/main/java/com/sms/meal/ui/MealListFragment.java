package com.sms.meal.ui;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.sms.R;
import com.sms.ShareMyMealApplication;
import com.sms.meal.backend.MealProvider;
import com.sms.meal.backend.MyMealProvider;
import com.sms.meal.domain.Meal;
import com.sms.meal.domainmeal.MyMeal;
import com.sms.transport.RequestCallback;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by cchiappini on 25/11/2014.
 */

public class MealListFragment extends ListFragment {
    @Inject
    MyMealProvider mealProvider;
    private List<MyMeal> mMealList = new ArrayList<MyMeal>();
    static final int MEAL_BOOKED_REQUEST = 1;
    private ShareMyMealApplication app;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        app = (ShareMyMealApplication) activity.getApplication();
        app.inject(this);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onListItemClick(ListView listView, View v, int position, long id) {
        MyMeal meal = ((MealAdapter) getListAdapter()).getItem(position);
        Intent bookingIntent = new Intent(getActivity(), MealActivity.class);
        bookingIntent.putExtra("mealId", meal.getId());
        startActivityForResult(bookingIntent, MEAL_BOOKED_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == MEAL_BOOKED_REQUEST) {
            if (data == null) {return;}
            String id = data.getStringExtra("id");
            for(MyMeal meal : mMealList){
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
        mealProvider.getMeals(new MealsCallback());
    }

    private class MealAdapter extends ArrayAdapter<MyMeal> {

        public MealAdapter(List<MyMeal> meals) {
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
            MyMeal meal = getItem(position);

            TextView titleTextView = (TextView) convertView.findViewById(R.id.meal_list_item_titleTextView);
            titleTextView.setText(meal.getName());

            TextView ownerTextView = (TextView) convertView.findViewById(R.id.meal_list_item_ownerTextView);
            ownerTextView.setText("by "+meal.getOwner());
            return convertView;
        }
    }

    protected class MealsCallback implements RequestCallback<List<MyMeal>> {

        @Override
        public void onRetrieved(List<MyMeal> data) {

            showContent(data);
        }

        @Override
        public void onError(String s) {

        }
    }

    private void showContent(List<MyMeal> data) {
        //PROGRESS SET VISIBILITY GONE?
        MealAdapter adapter = new MealAdapter(data);
        setListAdapter(adapter);
    }

    private void showProgress() {
        //PROGRESS SET VISIBILITY VISIBLE?
    }

}
