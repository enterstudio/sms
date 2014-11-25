package com.example.sms.meal;

public class MealId {
    private final int mealId;

    public MealId(String mealId) {
        this.mealId = Integer.parseInt(mealId);
    }
    public int getMealId() {
        return mealId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MealId)) return false;

        MealId mealId1 = (MealId) o;

        if (mealId != mealId1.mealId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return mealId;
    }
}
