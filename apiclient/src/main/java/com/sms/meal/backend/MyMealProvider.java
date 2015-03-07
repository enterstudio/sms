package com.sms.meal.backend;

import com.sms.meal.domainmeal.MyMeal;
import com.sms.transport.RequestCallback;

import java.util.List;

/**
 * Created by cchiappini on 07/03/2015.
 */
public class MyMealProvider implements MealProvider{

    private final MealProvider mealProviderFromCache;
    private final MealProvider mealProviderFromServer;

    public MyMealProvider(MealProvider mealProviderFromCache, MealProvider mealProviderFromServer){

        this.mealProviderFromCache = mealProviderFromCache;
        this.mealProviderFromServer = mealProviderFromServer;
    }


    public void getMeals(final RequestCallback<List<MyMeal>> mealsCallback) {
        mealProviderFromCache.getMeals(new RequestCallback<List<MyMeal>>(){
            @Override
            public void onRetrieved(List<MyMeal> data) {
                mealsCallback.onRetrieved(data);
            }

            @Override
            public void onError(String s) {
                mealProviderFromServer.getMeals(new RequestCallback<List<MyMeal>>() {
                    @Override
                    public void onRetrieved(List<MyMeal> data) {
                        mealsCallback.onRetrieved(data);
                    }

                    @Override
                    public void onError(String s) {
                        mealsCallback.onError(s);
                    }
                });
            }
        });
    }


    public void getMeal(final RequestCallback<MyMeal> mealCallBack, final String id) {
        mealProviderFromCache.getMeal(new RequestCallback<MyMeal>(){
            @Override
            public void onRetrieved(MyMeal data) {
                mealCallBack.onRetrieved(data);
            }

            @Override
            public void onError(String s) {
                mealProviderFromServer.getMeal(new RequestCallback<MyMeal>() {
                    @Override
                    public void onRetrieved(MyMeal data) {
                        mealCallBack.onRetrieved(data);
                    }

                    @Override
                    public void onError(String s) {
                        mealCallBack.onError(s);
                    }
                }, id);
            }
        }, id);
    }

}
