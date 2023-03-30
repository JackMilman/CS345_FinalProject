package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import recipes.*;

class RecipeTest
{
  final String recipeNameValid = "NamedRecipe";
  final String recipeNameNotValid = "";
  final String recipeNameNull = null;
  final String recipeNameDefault = "DefaultRecipeName";

  final String ingredientName1 = "Cabbage";
  final String ingredientDetails = "Basic Details for an Ingredient";
  final String ingredientUnit = "lbs";
  final String ingredientName2 = "Apple";
  final String ingredientName3 = "Chicken";

  final String utensilName1 = "Spoon";
  final String utensilNameDest = "NamedUtensilDestination";
  final String utensilDetails = "Basic Details for a Utensil";
  final String utensilName2 = "Fork";
  final String utensilName3 = "Knife";



  final String stepAction = "The Action to be performed in a Step";
  final String stepDetails = "Basic details of a Step";
  final int time = 5;

  @Test
  public void testGetName()
  {
    String expected = recipeNameValid;
    Recipe recipe = new Recipe(recipeNameValid, 10, null, null, null);
    String actual = recipe.getName();
    assertEquals(expected, actual);

    String expectedNotValid = recipeNameDefault;
    Recipe recipeNotValid = new Recipe(recipeNameNotValid, 10, null, null, null);
    String actualNotValid = recipeNotValid.getName();
    assertEquals(expectedNotValid, actualNotValid);

    String expectedNull = recipeNameDefault;
    Recipe recipeNull = new Recipe(recipeNameNull, 10, null, null, null);
    String actualNull = recipeNull.getName();
    assertEquals(expectedNull, actualNull);
  }

  @Test
  public void testGetServings()
  {
    int expected = 500;
    Recipe recipe = new Recipe(recipeNameValid, 500, null, null, null);
    int actual = recipe.getServings();
    assertEquals(expected, actual);

    int expectedNegative = 1;
    Recipe recipeNegative = new Recipe(recipeNameValid, -5, null, null, null);
    int actualNegative = recipeNegative.getServings();
    assertEquals(expectedNegative, actualNegative);

    int expectedZero = 1;
    Recipe recipeZero = new Recipe(recipeNameValid, 0, null, null, null);
    int actualZero = recipeZero.getServings();
    assertEquals(expectedZero, actualZero);
  }

  @Test
  public void testGetIngredients()
  {
    List<Ingredient> expected = new ArrayList<Ingredient>();
    expected.add(new Ingredient(ingredientName2, ingredientDetails, 50, ingredientUnit));
    expected.add(new Ingredient(ingredientName1, ingredientDetails, 50, ingredientUnit));
    expected.add(new Ingredient(ingredientName3, ingredientDetails, 50, ingredientUnit));
    List<Ingredient> temp = new ArrayList<Ingredient>();
    temp.add(new Ingredient(ingredientName1, ingredientDetails, 50, ingredientUnit));
    temp.add(new Ingredient(ingredientName2, ingredientDetails, 50, ingredientUnit));
    temp.add(new Ingredient(ingredientName3, ingredientDetails, 50, ingredientUnit));
    Recipe recipe = new Recipe(recipeNameValid, 500, temp, null, null);
    List<Ingredient> actual = recipe.getIngredients();
    assertEquals(expected, actual);
  }

  @Test
  public void testGetUtensils()
  {
    List<Utensil> expected = new ArrayList<Utensil>();
    expected.add(new Utensil(utensilName2, utensilDetails));
    expected.add(new Utensil(utensilName3, utensilDetails));
    expected.add(new Utensil(utensilName1, utensilDetails));
    List<Utensil> temp = new ArrayList<Utensil>();
    temp.add(new Utensil(utensilName1, utensilDetails));
    temp.add(new Utensil(utensilName2, utensilDetails));
    temp.add(new Utensil(utensilName3, utensilDetails));
    Recipe recipe = new Recipe(recipeNameValid, 500, null, temp, null);
    List<Utensil> actual = recipe.getUtensils();
    assertEquals(expected, actual);
  }

  @Test
  public void testGetSteps()
  {
    List<Step> expected = new ArrayList<Step>();
    Ingredient ingr = new Ingredient(ingredientName1, ingredientDetails, 5, ingredientUnit);
    Utensil utensilSource = new Utensil(utensilName1, utensilDetails);
    Utensil utensilDestination = new Utensil(utensilNameDest, utensilDetails);
    expected.add(new Step(stepAction, ingr, utensilSource, utensilDestination, stepDetails, time));
    List<Step> temp = new ArrayList<Step>(expected);
    Recipe recipe = new Recipe(recipeNameValid, 500, null, null, temp);
    List<Step> actual = recipe.getSteps();
    assertEquals(expected, actual);
  }

}
