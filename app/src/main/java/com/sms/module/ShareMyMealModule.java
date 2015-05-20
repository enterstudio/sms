package com.sms.module;

import com.sms.meal.backend.MealProviderFromCache;
import com.sms.meal.backend.MealProviderFromServer;
import com.sms.meal.backend.MyMealProvider;
import com.sms.meal.backend.ParseMealParser;
import com.sms.meal.ui.add.AddMealFragment;
import com.sms.meal.ui.get.MealFragment;
import com.sms.meal.ui.MealListFragment;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by cchiappini on 07/03/2015.
 */
@Module(injects = {MealListFragment.class, MealFragment.class, AddMealFragment.class},
        library = true, complete = false)
public class ShareMyMealModule {

    @Provides
    @Singleton
    public MyMealProvider getMealProvider(MealProviderFromCache mealProviderFromCache, MealProviderFromServer mealProviderFromServer) {
        return new MyMealProvider(mealProviderFromCache, mealProviderFromServer) {
        };
    }

    @Provides
    @Singleton
    public MealProviderFromServer getMealProviderFromServer(ParseMealParser mealParser) {
        return new MealProviderFromServer(mealParser);
    }

    @Provides
    @Singleton
    public ParseMealParser getMealParser() {
        return new ParseMealParser();
    }

    @Provides
    @Singleton
    public MealProviderFromCache getMealProviderFromCache(ParseMealParser mealParser) {
        return new MealProviderFromCache(mealParser);
    }


}
