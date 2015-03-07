package com.sms.meal.backend;

import com.parse.ParseFile;
import com.parse.ParseObject;
import com.sms.meal.domainmeal.MyMeal;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ParseMealParserTest {
    @Test
    public void shouldExtractUnbookedMealsFromListOfObject(){
        List<ParseObject> parseMealList = new ArrayList<>();
        ParseObject parseFirstMeal = createMealParseObject("idOfFirstMeal", "firstMeal", "firstMealDescription", "firstMealLocation", "firstMealOwner");
        parseFirstMeal.put("image", new ParseFile("fileName", new byte[0]));
        ParseObject parseSecondMeal = createMealParseObject("idOfSecondMeal", "secondMeal", "secondMealDescription", "secondMealLocation", "secondMealOwner");
        parseSecondMeal.put("image", new ParseFile("fileName", new byte[0]));
        parseMealList.add(parseFirstMeal);
        parseMealList.add(parseSecondMeal);
        MealParser<ParseObject> parseMealParser = new ParseMealParser();
        List<MyMeal> meals = parseMealParser.extractUnbookedMealsFromListOfObject(parseMealList);
        assertMealHasBeenParser(parseFirstMeal, meals.get(0));
        assertMealHasBeenParser(parseSecondMeal, meals.get(1));
    }

    @Test
    public void shouldParseMealFromListOfObject(){
        ParseObject parseFirstMeal = createMealParseObject("idOfFirstMeal", "firstMeal", "firstMealDescription", "firstMealLocation", "firstMealOwner");
        parseFirstMeal.put("image", new ParseFile("fileName", new byte[0]));

        MealParser<ParseObject> parseMealParser = new ParseMealParser();
        MyMeal meal = parseMealParser.parseMeal(parseFirstMeal);
        assertMealHasBeenParser(parseFirstMeal, meal);
    }

    private void assertMealHasBeenParser(ParseObject parseMealObjectExpected, MyMeal mealObjectReturned) {
        assertEquals(parseMealObjectExpected.getObjectId(), mealObjectReturned.getId());
        assertEquals(parseMealObjectExpected.get("name"), mealObjectReturned.getName());
    }

    private ParseObject createMealParseObject(String id, String name, String description, String location, String owner) {
        ParseObject parseMeal = new ParseObject(MyMeal.class.getName());
        parseMeal.put("name", name);
        parseMeal.put("description", description);
        parseMeal.put("location", location);
        parseMeal.put("owner", owner);
        parseMeal.setObjectId(id);
        return parseMeal;
    }
}
