package com.example.sms.meal;

import android.app.Activity;
import android.content.Intent;
import android.net.ParseException;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sms.R;
import com.example.sms.booking.BookingActivity;
import com.parse.GetCallback;
import com.parse.GetDataCallback;
import com.parse.ParseFile;
import com.parse.ParseImageView;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class MealFragment extends Fragment {
    public static final String EXTRA_MEAL_ID = "com.example.sms.mealintent.meal_id";
    public static final String EXTRA_MEAL_LOCATION = "com.example.sms.mealintent.meal_location";
    public static final String EXTRA_MEAL_DESCRIPTION = "com.example.sms.mealintent.meal_description";
    public static final String EXTRA_MEAL_UPLOADED_BY = "com.example.sms.mealintent.uploaded_by";
    private String mealLocation;
    private String mealId;
    private String mealDescription;
    private String uploadedBy;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mealId = getActivity().getIntent().getStringExtra(EXTRA_MEAL_ID);
        mealLocation = getActivity().getIntent().getStringExtra(EXTRA_MEAL_LOCATION);
        mealDescription = getActivity().getIntent().getStringExtra(EXTRA_MEAL_DESCRIPTION);
        uploadedBy = getActivity().getIntent().getStringExtra(EXTRA_MEAL_UPLOADED_BY);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.book_meal, parent, false);
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Meal");

        // Retrieve the object by id
        query.getInBackground(mealId, new GetCallback<ParseObject>() {

            @Override
            public void done(ParseObject meal, com.parse.ParseException e) {
                if (e == null) {
                    ParseFile image = meal.getParseFile("image");
                    final ParseImageView imageView = (ParseImageView) v.findViewById(R.id.imageView);
                    imageView.setParseFile(image);
                    imageView.loadInBackground(new GetDataCallback() {

                        @Override
                        public void done(byte[] bytes, com.parse.ParseException e) {
                            TextView descriptionTextView = (TextView) v.findViewById(R.id.meal_description);
                            descriptionTextView.setText(mealDescription);
                            // Complete location text
                            TextView locationTextView = (TextView) v.findViewById(R.id.meal_location_message);
                            locationTextView.setText(mealLocation);
                            TextView uploadedByTextView = (TextView) v.findViewById(R.id.uploaded_by);
                            uploadedByTextView.setText(uploadedBy);
                            Button bookMealButton = (Button) v.findViewById(R.id.buttonBookMeal);
                            bookMealButton.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {

                                    ParseQuery<ParseObject> query = ParseQuery.getQuery("Meal");

                                    // Retrieve the object by id
                                    query.getInBackground(mealId, new GetCallback<ParseObject>() {

                                        @Override
                                        public void done(ParseObject meal, com.parse.ParseException e) {
                                            if (e == null) {
                                                Log.d("MealFragment", "to be updated " + meal.getObjectId());
                                                // Now let's update it with some new data.
                                                meal.put("booked", true);
                                                meal.saveInBackground();
                                                Toast.makeText(getActivity(), R.string.meal_reserved,
                                                        Toast.LENGTH_LONG).show();
                                                Intent resultIntent = new Intent();
                                                resultIntent.putExtra("id", mealId);
                                                getActivity().setResult(Activity.RESULT_OK, resultIntent);

                                            } else {
                                                Log.d("MealFragment", "not found to be updated " + meal.getObjectId());
                                                Toast.makeText(getActivity(), R.string.meal_unreserved,
                                                        Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    });
                                }
                            });


                        }
                    });
                }
            }
        });

        return v;
    }

}
