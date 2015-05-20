package com.sms.meal.backend;

import com.parse.ParseFile;
import com.parse.ParseObject;
import com.sms.meal.domainmeal.MyMeal;

import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ParseMealParserTest {

    MealParser<ParseObject> parseMealParser;

    @Before
    public void setup(){
        parseMealParser = new ParseMealParser();
    }

    @Test
    public void shouldExtractUnbookedMealsFromListOfObject(){
        List<ParseObject> parseMealList = new ArrayList<>();
        ParseObject parseFirstMeal = createMealParseObject("idOfFirstMeal", "firstMeal", "firstMealDescription", "firstMealLocation", "firstMealOwner");
        parseFirstMeal.put("image", new ParseFile("fileName", new byte[0]));
        ParseObject parseSecondMeal = createMealParseObject("idOfSecondMeal", "secondMeal", "secondMealDescription", "secondMealLocation", "secondMealOwner");
        parseSecondMeal.put("image", new ParseFile("fileName", new byte[0]));
        parseMealList.add(parseFirstMeal);
        parseMealList.add(parseSecondMeal);
        List<MyMeal> meals = parseMealParser.extractUnbookedMealsFromListOfObject(parseMealList);
        assertMealHasBeenParsedCorrectly(parseFirstMeal, meals.get(0));
        assertMealHasBeenParsedCorrectly(parseSecondMeal, meals.get(1));
    }

    @Test
    public void shouldParseMealFromListOfObject(){
        ParseObject parseFirstMeal = createMealParseObject("idOfFirstMeal", "firstMeal", "firstMealDescription", "firstMealLocation", "firstMealOwner");
        parseFirstMeal.put("image", new ParseFile("fileName", new byte[0]));


        MyMeal meal = parseMealParser.parseMeal(parseFirstMeal);
        assertMealHasBeenParsedCorrectly(parseFirstMeal, meal);
    }

    @Test
    public void shouldParseMealObjectFromMeal(){
        MyMeal meal = new MyMeal("12345", "name", "description", "location", "owner");
        meal.setImage(new byte[0]);
        ParseObject parseObject = parseMealParser.parseObjectMeal(meal);
        assertEquals(parseObject.get("name"), meal.getName());
        assertEquals(parseObject.get("description"), meal.getDescription());
        assertEquals(parseObject.get("owner"), meal.getOwner());
        assertEquals(parseObject.get("location"), meal.getLocation());
        assertEquals(parseObject.get("image"), meal.getImage());
    }

    private void assertMealHasBeenParsedCorrectly(ParseObject parseMealObjectExpected, MyMeal mealObjectReturned) {
        assertEquals(parseMealObjectExpected.getObjectId(), mealObjectReturned.getId());
        assertEquals(parseMealObjectExpected.get("name"), mealObjectReturned.getName());
        assertEquals(parseMealObjectExpected.get("description"), mealObjectReturned.getDescription());
        assertEquals(parseMealObjectExpected.get("owner"), mealObjectReturned.getOwner());
        assertEquals(parseMealObjectExpected.get("location"), mealObjectReturned.getLocation());
        assertEquals(parseMealObjectExpected.get("image"), mealObjectReturned.getImage());

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
