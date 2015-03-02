package com.sms.booking;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.SmallTest;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.sms.R;

import static com.sms.R.id.buttonBookMeal;
import static com.sms.R.id.hello_message;
import static com.sms.R.string.hello;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = "src/main/AndroidManifest.xml", emulateSdk = 18)
public class BookingActivityTest extends ActivityInstrumentationTestCase2<BookingActivity> {

    private Activity mBookingActivity;
    private Button mBookingButton;
    private TextView mHelloTextView;

    public BookingActivityTest() {
        super("com.sms", BookingActivity.class);
    }

    protected void setUp() throws Exception {
        super.setUp();

        mBookingActivity = getActivity();
        mBookingButton = (Button) mBookingActivity.findViewById(buttonBookMeal);
        mHelloTextView = (TextView) mBookingActivity.findViewById(hello_message);
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
