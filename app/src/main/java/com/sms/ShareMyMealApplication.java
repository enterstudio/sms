package com.sms;

import android.app.Application;

import com.parse.Parse;
import com.sms.module.ShareMyMealModule;

import java.util.Arrays;

import dagger.ObjectGraph;

/**
 * Created by cchiappini on 25/11/2014.
 */
public class ShareMyMealApplication extends Application {
    private ObjectGraph objectGraph;

    @Override
    public void onCreate() {
        super.onCreate();
        objectGraph = ObjectGraph.create(getModules());
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "DxVP32QtY6GN27WYU8VZW0a0OPu5kPNbPwfCXJZh", "jTnOvNjhWXABt0Zd8nARu7GRuZkFdAfsn7cRoJdm");
    }

    public void inject(Object target) {
        objectGraph.inject(target);
    }

    public Object[] getModules() {
        return Arrays.asList(new ShareMyMealModule()).toArray();
    }
}
