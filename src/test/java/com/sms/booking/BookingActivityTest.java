package com.sms.booking;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = "src/main/AndroidManifest.xml", emulateSdk = 18)
public class BookingActivityTest extends ActivityInstrumentationTestCase2<BookingActivity> {

    private Activity mBookingActivity;

    public BookingActivityTest() {
        super(BookingActivity.class);
    }

    @Before
    public void setUp() throws Exception {
        mBookingActivity = Robolectric.buildActivity(BookingActivity.class).create().get();

    }

    @Test
    public void testBookingButton_openActivityAndExpectBookingButton() {
        assertNotNull(mBookingActivity);
    }

}
