package com.example.sms.booking;

import android.app.Activity;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.LargeTest;
import android.test.suitebuilder.annotation.MediumTest;
import android.test.suitebuilder.annotation.SmallTest;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.sms.R;

import static com.example.sms.R.id.buttonBookMeal;
import static com.example.sms.R.id.hello_message;
import static com.example.sms.R.string.hello;

public class BookingActivityTest extends ActivityInstrumentationTestCase2<BookingActivity> {

    private Activity mBookingActivity;
    private Button mBookingButton;
    private TextView mHelloTextView;
    private Intent mConfirmationIntent;


    public BookingActivityTest() {
        super("com.example.sms", BookingActivity.class);
    }

    protected void setUp() throws Exception {
        super.setUp();


        mBookingActivity = getActivity();
        mBookingButton = (Button) mBookingActivity.findViewById(buttonBookMeal);
        mHelloTextView = (TextView) mBookingActivity.findViewById(hello_message);

        mConfirmationIntent = new Intent(getInstrumentation()
                .getTargetContext(), BookingActivity.class);


    }

    @SmallTest
    public void testDisplayText_openActivityAndExpectInfoText() {
        String expectedHelloText = mBookingActivity.getString(hello);
        assertTrue(View.VISIBLE == mHelloTextView.getVisibility());
        assertEquals(expectedHelloText, mHelloTextView.getText());
    }

    @SmallTest
    public void testBookingButton_openActivityAndExpectBookingButton() {
        String expectedButtonLabel = mBookingActivity.getString(R.string.book);
        assertTrue(View.VISIBLE == mBookingButton.getVisibility());
        assertEquals(expectedButtonLabel, mBookingButton.getText());
    }



}
