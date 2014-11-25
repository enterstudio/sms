package com.example.sms.booking;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.example.sms.confirmation.DisplayConfirmationActivity;
import com.example.sms.R;
import com.example.sms.meal.Meal;
import com.parse.Parse;
import com.parse.ParseObject;

import java.util.List;

public class BookingActivity extends Activity {

    public final static String EXTRA_MESSAGE = "com.example.sms.MESSAGE";
    private Button bookMealButton;
    private boolean mealStatus;
    private String mealId;
    private ProgressBar webServicePG;
    static boolean errored = false;
    private BookingCommand bookingCommand;
    TextView statusTV;
    TextView mealTW;
    private ListView mealListView;
    private ArrayAdapter<Meal> listAdapter;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Parse.initialize(this, "DxVP32QtY6GN27WYU8VZW0a0OPu5kPNbPwfCXJZh", "jTnOvNjhWXABt0Zd8nARu7GRuZkFdAfsn7cRoJdm");
        ParseObject testObject = new ParseObject("TestObject");
        testObject.put("foo", "bar");
        testObject.saveInBackground();
        bookingCommand = new BookingCommand();
        List<Meal> meals =bookingCommand.getMeals();

        //Create and populate an ArrayList of objects from parse
        listAdapter = new ArrayAdapter<Meal>(this,android.R.layout.simple_list_item_1, meals);

        ListView mealListView = (ListView)findViewById(R.id.listViewMeals);
        mealListView.setAdapter(listAdapter);

        setContentView(R.layout.main);

    }

    /**
     * Called when the user clicks the Send button
     */
    public void bookMeal(View view) {

        bookMealButton = (Button) findViewById(R.id.buttonBookMeal);
        mealTW= (TextView) findViewById(R.id.textViewMeal);
//        mealTW.setId(1234);
        statusTV=(TextView) findViewById(R.id.textStatusTV);
        webServicePG = new ProgressBar(view.getContext());
        bookMealButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                mealId = "1234";

                statusTV.setText("");
                //Create instance for AsyncCallWS
                AsyncCallWS task = new AsyncCallWS();
                //Call execute
                task.execute();
            }
        });
    }

    private class AsyncCallWS extends AsyncTask<Object,Void, Void> {

        @Override
        //Once LocalWebService returns response
        protected void onPostExecute(Void result) {
            Intent intent = new Intent(BookingActivity.this, DisplayConfirmationActivity.class);
            TextView textView = (TextView) findViewById(R.id.hello_message);
            String message = textView.getText().toString();
            intent.putExtra(EXTRA_MESSAGE, message);
            //Make Progress Bar invisible
            webServicePG.setVisibility(View.INVISIBLE);
            //Error status is false
            if(!errored){
                //Based on Boolean value returned from LocalWebService
                if(mealStatus){
                    //Navigate to Home Screen
                    startActivity(intent);
                }else{
                    //Set Error message
                    statusTV.setText("Book meal failed, try again");
                }
                //Error status is true
            }else{
                statusTV.setText("Error occured in invoking webservice");
            }
            //Re-initialize Error Status to False
            errored = false;
        }

        @Override
        protected Void doInBackground(Object... objects) {
            mealStatus = bookingCommand.bookMeal(mealId);
            return null;
        }

        @Override
        //Make Progress Bar visible
        protected void onPreExecute() {
            webServicePG.setVisibility(View.VISIBLE);
        }

    }
}
