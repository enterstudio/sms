package com.example.sms.meal;

import android.content.Intent;
import android.net.ParseException;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.sms.R;
import com.example.sms.booking.BookingActivity;
import com.parse.GetCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class MealFragment extends Fragment {
    public static final String EXTRA_MEAL_ID = "com.example.sms.mealintent.meal_id";
    public static final String EXTRA_MEAL_LOCATION = "com.example.sms.mealintent.meal_location";
    private String mealLocation;
    private String mealId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mealId = getActivity().getIntent().getStringExtra(EXTRA_MEAL_ID);
        mealLocation = getActivity().getIntent().getStringExtra(EXTRA_MEAL_LOCATION);
        Log.d("MealFragment", mealId);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.book_meal, parent, false);

        // Complete location text
        TextView textView = (TextView) v.findViewById(R.id.meal_location_message);
        textView.setText(mealLocation);
        Button bookMealButton = (Button) v.findViewById(R.id.buttonBookMeal);
        bookMealButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                ParseQuery<ParseObject> query = ParseQuery.getQuery("Meal");

                // Retrieve the object by id
                query.getInBackground(mealId, new GetCallback<ParseObject>() {

                    @Override
                    public void done(ParseObject meal, com.parse.ParseException e) {
                        if (e == null) {
                            // Now let's update it with some new data.
                            meal.put("booked", true);
                        }
                    }
                });
            }
        });
        return v;
    }

}
