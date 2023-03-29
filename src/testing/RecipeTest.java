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
  
  final String ingredientName = "NamedIngredient";
  final String ingredientDetails = "Basic Details for an Ingredient";
  final String ingredientUnit = "lbs";
  
  final String utensilName = "NamedUtensil";
  final String utensilNameDest = "NamedUtensilDestination";
  final String utensilDetails = "Basic Details for a Utensil";
  
  final String stepAction = "The Action to be performed in a Step";
  final String stepDetails = "Basic details of a Step";

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
    expected.add(new Ingredient(ingredientName, ingredientDetails, 50, ingredientUnit));
    List<Ingredient> temp = new ArrayList<Ingredient>(expected);
    Recipe recipe = new Recipe(recipeNameValid, 500, temp, null, null);
    List<Ingredient> actual = recipe.getIngredients();
    assertEquals(expected, actual);
  }
  
  @Test
  public void testGetUtensils()
  {
    List<Utensil> expected = new ArrayList<Utensil>();
    expected.add(new Utensil(utensilName, utensilDetails));
    List<Utensil> temp = new ArrayList<Utensil>(expected);
    Recipe recipe = new Recipe(recipeNameValid, 500, null, temp, null);
    List<Utensil> actual = recipe.getUtensils();
    assertEquals(expected, actual);
  }
  
  @Test
  public void testGetSteps()
  {
    List<Step> expected = new ArrayList<Step>();
    Ingredient ingr = new Ingredient(ingredientName, ingredientDetails, 5, ingredientUnit);
    Utensil utensilSource = new Utensil(utensilName, utensilDetails);
    Utensil utensilDestination = new Utensil(utensilNameDest, utensilDetails);
    expected.add(new Step(stepAction, ingr, utensilSource, utensilDestination, stepDetails));
    List<Step> temp = new ArrayList<Step>(expected);
    Recipe recipe = new Recipe(recipeNameValid, 500, null, null, temp);
    List<Step> actual = recipe.getSteps();
    assertEquals(expected, actual);
  }

}
