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

import com.sms.R;
import com.parse.GetCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.sms.ShareMyMealApplication;
import com.sms.meal.backend.MealProvider;
import com.sms.meal.backend.MyMealProvider;
import com.sms.meal.domainmeal.MyMeal;
import com.sms.transport.RequestCallback;

import javax.inject.Inject;

public class MealFragment extends Fragment {
    @Inject
    MyMealProvider mealProvider;
    private TextView descriptionTextView;
    private TextView confirmationMessageView;
    private TextView locationTextView;
    private TextView uploadedByStringTextView;
    private TextView uploadedByTextView;
    private Button bookMealButton;
    private ImageView imageView;
    private String mealFromIntentId;
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
        mealFromIntentId = getActivity().getIntent().getStringExtra("mealId");
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
                query.getInBackground(mealFromIntentId, new GetCallback<ParseObject>() {

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
                            resultIntent.putExtra("id", mealFromIntentId);
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
        mealProvider.getMeal(new GetMealCallback(), mealFromIntentId);
        return v;
    }


    protected class GetMealCallback implements RequestCallback<MyMeal> {
        @Override
        public void onRetrieved(MyMeal data) {
            descriptionTextView.setText(data.getDescription());
            confirmationMessageView.setText("Location is");
            locationTextView.setText(data.getLocation());
            uploadedByStringTextView.setText("Cooked by");
            uploadedByTextView.setText(data.getOwner());
            imageView.setImageBitmap(BitmapFactory.decodeByteArray(data.getImage(), 0, data.getImage().length) );
            bookMealButton.setVisibility(View.VISIBLE);
        }

        @Override
        public void onError(String s) {
            Toast.makeText(getActivity(), R.string.meal_not_found,
                    Toast.LENGTH_LONG).show();
        }
    }
}
