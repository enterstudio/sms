package com.sms.booking;

import android.app.Activity;

import com.sms.RobolectricGradleTestRunner;
import com.sms.booking.ui.BookingActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;

import static junit.framework.Assert.assertNotNull;

@RunWith(RobolectricGradleTestRunner.class)
public class BookingActivityTest {

    private Activity bookingActivity;

    @Before
    public void setUp() throws Exception {
        bookingActivity = Robolectric.buildActivity(BookingActivity.class).create().get();
    }

    @Test
    public void testBookingButton_openActivityAndExpectBookingButton() {
        assertNotNull(bookingActivity);
    }

}
