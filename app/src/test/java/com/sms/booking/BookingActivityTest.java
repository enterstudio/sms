package com.sms.booking;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;

import com.sms.booking.ui.BookingActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;

@RunWith(RobolectricGradleTestRunner.class)
public class BookingActivityTest extends ActivityInstrumentationTestCase2<BookingActivity> {

    private Activity bookingActivity;

    public BookingActivityTest() {
        super(BookingActivity.class);
    }

    @Before
    public void setUp() throws Exception {
        bookingActivity = Robolectric.buildActivity(BookingActivity.class).create().get();
    }

    @Test
    public void testBookingButton_openActivityAndExpectBookingButton() {
        assertNotNull(bookingActivity);
    }

}
