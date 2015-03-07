package com.sms.meal.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
import com.sms.R;
import com.parse.GetCallback;
import com.parse.GetDataCallback;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.sms.meal.backend.MealProvider;
import com.sms.meal.backend.MyMealProvider;
import com.sms.meal.backend.ParseMealParser;
import com.sms.meal.domain.Meal;
import com.sms.transport.RequestCallback;

public class MealFragment extends Fragment {
    private TextView descriptionTextView;
    private TextView confirmationMessageView;
    private TextView locationTextView;
    private TextView uploadedByStringTextView;
    private TextView uploadedByTextView;
    private Button bookMealButton;
    private MealProvider mealProvider = new MyMealProvider(new ParseMealParser());
    private Meal mealFromIntent;
    private ImageView imageView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mealFromIntent = getActivity().getIntent().getParcelableExtra("meal");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.book_meal, parent, false);
        descriptionTextView = (TextView) v.findViewById(R.id.meal_description);
        confirmationMessageView = (TextView) v.findViewById(R.id.confirmation_message);
        locationTextView = (TextView) v.findViewById(R.id.meal_location_message);
        uploadedByStringTextView = (TextView) v.findViewById(R.id.uploaded_info);
        uploadedByTextView = (TextView) v.findViewById(R.id.uploaded_by);
        imageView =  (ImageView)v.findViewById(R.id.imageView);
        bookMealButton = (Button) v.findViewById(R.id.buttonBookMeal);
        bookMealButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                ParseQuery<ParseObject> query = ParseQuery.getQuery("Meal");

                // Retrieve the object by id
                query.getInBackground(mealFromIntent.getId(), new GetCallback<ParseObject>() {

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
                            resultIntent.putExtra("id", mealFromIntent.getId());
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
        getMealForId(v);

        return v;
    }

    private void getMealForId(final View v) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Meal");

        // Retrieve the object by id
        query.getInBackground(mealFromIntent.getId(), new GetCallback<ParseObject>() {

            @Override
            public void done(ParseObject meal, com.parse.ParseException e) {
                if (e == null) {
                    getImageById(meal, v);
                }
            }
        });
    }

    private void getImageById(ParseObject meal, final View v) {
        ParseFile image = meal.getParseFile("image");
        image.getDataInBackground(new GetDataCallback() {
            @Override
            public void done(byte[] data, ParseException e) {
                if (e == null) {

                    descriptionTextView.setText(mealFromIntent.getDescription());
                    confirmationMessageView.setText("Location is");
                    locationTextView.setText(mealFromIntent.getLocation());
                    uploadedByStringTextView.setText("Cooked by");
                    uploadedByTextView.setText(mealFromIntent.getOwner());
                    imageView.setImageBitmap(BitmapFactory.decodeByteArray(data, 0, data.length) );
                    bookMealButton.setVisibility(View.VISIBLE);

                } else {

                }
            }
        });
    }


    protected class GetMealCallback implements RequestCallback<Meal> {
        @Override
        public void onRetrieved(Meal data) {
            ParseQuery<ParseObject> query = ParseQuery.getQuery("Meal");

            // Retrieve the object by id
            query.getInBackground(mealFromIntent.getId(), new GetCallback<ParseObject>() {

                @Override
                public void done(ParseObject meal, com.parse.ParseException e) {

                }
            });
        }
    }
}
