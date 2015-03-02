package com.sms;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by cchiappini on 25/11/2014.
 */
public class ShareMyMealApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(this, "DxVP32QtY6GN27WYU8VZW0a0OPu5kPNbPwfCXJZh", "jTnOvNjhWXABt0Zd8nARu7GRuZkFdAfsn7cRoJdm");
    }
}
