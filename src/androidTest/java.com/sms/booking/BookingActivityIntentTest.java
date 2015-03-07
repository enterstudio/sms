package com.sms.booking;

import android.app.Activity;
import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.test.suitebuilder.annotation.MediumTest;
import android.widget.Button;
import android.widget.TextView;

import com.sms.booking.ui.BookingActivity;

import static com.sms.R.id.buttonBookMeal;
import static com.sms.R.id.hello_message;

public class BookingActivityIntentTest extends ActivityUnitTestCase<BookingActivity> {

    private Activity mBookingActivity;
    private Button mBookingButton;
    private TextView mHelloTextView;
    private Intent mConfirmationIntent;

    public BookingActivityIntentTest() {
        super(BookingActivity.class);
    }

    protected void setUp() throws Exception {
        super.setUp();

        mConfirmationIntent = new Intent(getInstrumentation()
                .getTargetContext(), BookingActivity.class);

        startActivity(mConfirmationIntent, null, null);
        mBookingButton = (Button) getActivity().findViewById(buttonBookMeal);
        mHelloTextView = (TextView) getActivity().findViewById(hello_message);

    }

    @MediumTest
    public void testBookingButton_clickButtonAndOpenConfirmationActivity() {

//        mBookingButton.performClick();
//
//        final Intent launchIntent = getStartedActivityIntent();
//        assertNotNull("Intent was null", launchIntent);
//        assertTrue(isFinishCalled());
//
////        final String payload =
////                launchIntent.getStringExtra(DisplayConfirmationActivity.EXTRAS_PAYLOAD_KEY);
////        assertEquals("Payload is empty", BookingActivity.STRING_PAYLOAD, payload);
    }
}
