package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import gui.IngredientEditor;
import recipes.*;

/**
 * Test cases for the Recipe class.
 * 
 * @author Jack Milman, KichIntel
 *
 */
class RecipeTest
{
  private final String recipeNameValid = "NamedRecipe";
  private final String recipeNameNotValid = "";
  private final String recipeNameNull = null;
  private final String recipeNameDefault = "DefaultRecipeName";
  private final String recipeNameQuirky = "I AM A RECIPE NAME WOOHOO";

  private final String ingredientName1 = "Cabbage";
  private final String ingredientDetails = "Basic Details for an Ingredient";
  private final String ingredientUnit = "POUNDS";
  private final String ingredientName2 = "Apple";
  private final String ingredientName3 = "Chicken";

  private final String utensilName1 = "Spoon";
  private final String utensilNameDest = "NamedUtensilDestination";
  private final String utensilDetails = "Basic Details for a Utensil";
  private final String utensilName2 = "Fork";
  private final String utensilName3 = "Knife";

  private final String stepAction = "The Action to be performed in a Step";
  private final String stepDetails = "Basic details of a Step";
  private final int time = 5;

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
    Ingredient ingredient1 = new Ingredient(ingredientName1, ingredientDetails, 50, ingredientUnit,
        IngredientEditor.NO_INPUT, IngredientEditor.NO_INPUT);
    Ingredient ingredient2 = new Ingredient(ingredientName2, ingredientDetails, 50, ingredientUnit,
        IngredientEditor.NO_INPUT, IngredientEditor.NO_INPUT);
    Ingredient ingredient3 = new Ingredient(ingredientName3, ingredientDetails, 50, ingredientUnit,
        IngredientEditor.NO_INPUT, IngredientEditor.NO_INPUT);
    List<Ingredient> expected = new ArrayList<Ingredient>();
    expected.add(ingredient1);
    expected.add(ingredient2);
    expected.add(ingredient3);
    List<Ingredient> temp = new ArrayList<Ingredient>();
    temp.add(ingredient1);
    temp.add(ingredient2);
    temp.add(ingredient3);
    
    Recipe recipe = new Recipe(recipeNameValid, 500, temp, null, null);
    List<Ingredient> actual = recipe.getIngredients();
    assertEquals(expected, actual);
  }

  @Test
  public void testGetUtensils()
  {
    List<Utensil> expected = new ArrayList<Utensil>();
    expected.add(new Utensil(utensilName1, utensilDetails));
    expected.add(new Utensil(utensilName2, utensilDetails));
    expected.add(new Utensil(utensilName3, utensilDetails));
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
    Ingredient ingr = new Ingredient(ingredientName1, ingredientDetails, 5, ingredientUnit,
        IngredientEditor.NO_INPUT, IngredientEditor.NO_INPUT);
    Utensil utensilSource = new Utensil(utensilName1, utensilDetails);
    Utensil utensilDestination = new Utensil(utensilNameDest, utensilDetails);
    Step step = new Step(stepAction, ingr, utensilSource, utensilDestination, stepDetails, time);
    expected.add(step);

    List<Step> temp = new ArrayList<Step>(expected);
    Recipe recipe = new Recipe(recipeNameValid, 500, null, null, temp);
    List<Step> actual = recipe.getSteps();
    assertEquals(expected, actual);
  }

  @Test
  public void testSetName()
  {
    String expected = recipeNameQuirky;
    Recipe recipe = new Recipe(recipeNameValid, 10, null, null, null);
    recipe.setName(recipeNameQuirky);
    String actual = recipe.getName();
    assertEquals(expected, actual);

    String expectedNotValid = recipeNameQuirky;
    Recipe recipeNotValid = new Recipe(recipeNameNotValid, 10, null, null, null);
    recipeNotValid.setName(recipeNameQuirky);
    String actualNotValid = recipeNotValid.getName();
    assertEquals(expectedNotValid, actualNotValid);

    String expectedNull = recipeNameQuirky;
    Recipe recipeNull = new Recipe(recipeNameNull, 10, null, null, null);
    recipeNull.setName(recipeNameQuirky);
    String actualNull = recipeNull.getName();
    assertEquals(expectedNull, actualNull);
  }

  @Test
  public void testSetServings()
  {
    int quirkyServings = 200;

    int expected = quirkyServings;
    Recipe recipe = new Recipe(recipeNameValid, 500, null, null, null);
    recipe.setServings(quirkyServings);
    int actual = recipe.getServings();
    assertEquals(expected, actual);

    int expectedNegative = quirkyServings;
    Recipe recipeNegative = new Recipe(recipeNameValid, -5, null, null, null);
    recipeNegative.setServings(quirkyServings);
    int actualNegative = recipeNegative.getServings();
    assertEquals(expectedNegative, actualNegative);

    int expectedZero = quirkyServings;
    Recipe recipeZero = new Recipe(recipeNameValid, 0, null, null, null);
    recipeZero.setServings(quirkyServings);
    int actualZero = recipeZero.getServings();
    assertEquals(expectedZero, actualZero);
  }

  @Test
  public void testAddIngredient()
  {
    Ingredient ingredient1 = new Ingredient(ingredientName1, ingredientDetails, 50, ingredientUnit,
        IngredientEditor.NO_INPUT, IngredientEditor.NO_INPUT);
    Ingredient ingredient2 = new Ingredient(ingredientName2, ingredientDetails, 50, ingredientUnit,
        IngredientEditor.NO_INPUT, IngredientEditor.NO_INPUT);
    Ingredient ingredient3 = new Ingredient(ingredientName3, ingredientDetails, 50, ingredientUnit,
        IngredientEditor.NO_INPUT, IngredientEditor.NO_INPUT);
    
    List<Ingredient> expected = new ArrayList<Ingredient>();
    expected.add(ingredient1);
    expected.add(ingredient2);
    expected.add(ingredient3);
    List<Ingredient> temp = new ArrayList<Ingredient>();
    temp.add(ingredient1);
    temp.add(ingredient2);
    
    Recipe recipe = new Recipe(recipeNameValid, 500, temp, null, null);
    recipe.addIngredient(ingredient3);
    List<Ingredient> actual = recipe.getIngredients();
    assertEquals(expected, actual);
  }

  @Test
  public void testRemoveIngredient()
  {
    Ingredient ingredient1 = new Ingredient(ingredientName1, ingredientDetails, 50, ingredientUnit,
        IngredientEditor.NO_INPUT, IngredientEditor.NO_INPUT);
    Ingredient ingredient2 = new Ingredient(ingredientName2, ingredientDetails, 50, ingredientUnit,
        IngredientEditor.NO_INPUT, IngredientEditor.NO_INPUT);
    Ingredient ingredient3 = new Ingredient(ingredientName3, ingredientDetails, 50, ingredientUnit,
        IngredientEditor.NO_INPUT, IngredientEditor.NO_INPUT);
    
    List<Ingredient> expected = new ArrayList<Ingredient>();
    expected.add(ingredient1);
    expected.add(ingredient2);
    List<Ingredient> temp = new ArrayList<Ingredient>();
    temp.add(ingredient1);
    temp.add(ingredient2);
    temp.add(ingredient3);
    
    Recipe recipe = new Recipe(recipeNameValid, 500, temp, null, null);
    recipe.removeIngredient(ingredient3);
    List<Ingredient> actual = recipe.getIngredients();
    assertEquals(expected, actual);
  }

  @Test
  public void testAddUtensil()
  {
    Utensil utensil1 = new Utensil(utensilName1, utensilDetails);
    Utensil utensil2 = new Utensil(utensilName2, utensilDetails);
    Utensil utensil3 = new Utensil(utensilName3, utensilDetails);

    List<Utensil> expected = new ArrayList<Utensil>();
    expected.add(utensil1);
    expected.add(utensil2);
    expected.add(utensil3);
    List<Utensil> temp = new ArrayList<Utensil>();
    temp.add(utensil1);
    temp.add(utensil2);

    Recipe recipe = new Recipe(recipeNameValid, 500, null, temp, null);
    recipe.addUtensil(new Utensil(utensilName3, utensilDetails));
    List<Utensil> actual = recipe.getUtensils();
    assertEquals(expected, actual);
  }

  @Test
  public void testRemoveUtensil()
  {
    Utensil utensil1 = new Utensil(utensilName1, utensilDetails);
    Utensil utensil2 = new Utensil(utensilName2, utensilDetails);
    Utensil utensil3 = new Utensil(utensilName3, utensilDetails);

    List<Utensil> expected = new ArrayList<Utensil>();
    expected.add(utensil1);
    expected.add(utensil2);
    List<Utensil> temp = new ArrayList<Utensil>();
    temp.add(utensil1);
    temp.add(utensil2);
    temp.add(utensil3);

    Recipe recipe = new Recipe(recipeNameValid, 500, null, temp, null);
    recipe.removeUtensil(utensil3);
    List<Utensil> actual = recipe.getUtensils();
    assertEquals(expected, actual);
  }

  @Test
  public void testAddStep()
  {
    Ingredient ingr = new Ingredient(ingredientName1, ingredientDetails, 5, ingredientUnit,
        IngredientEditor.NO_INPUT, IngredientEditor.NO_INPUT);
    Utensil utensilSource = new Utensil(utensilName1, utensilDetails);
    Utensil utensilDestination = new Utensil(utensilNameDest, utensilDetails);
    Step step = new Step(stepAction, ingr, utensilSource, utensilDestination, stepDetails, time);

    List<Step> expected = new ArrayList<Step>();
    expected.add(step);
    List<Step> temp = new ArrayList<Step>();
    Recipe recipe = new Recipe(recipeNameValid, 500, null, null, temp);
    recipe.addIngredient(ingr);
    recipe.addUtensil(utensilSource);
    recipe.addUtensil(utensilDestination);
    recipe.addStep(step);
    List<Step> actual = recipe.getSteps();
    assertEquals(expected, actual);

    List<Step> expectedMissingIngr = new ArrayList<Step>();
    List<Step> tempMissingIngr = new ArrayList<Step>();
    Recipe recipeMissingIngr = new Recipe(recipeNameValid, 500, null, null, tempMissingIngr);

    recipeMissingIngr.addUtensil(utensilSource);
    recipeMissingIngr.addUtensil(utensilDestination);
    recipeMissingIngr.addStep(step);
    List<Step> actualMissingIngr = recipeMissingIngr.getSteps();
    assertEquals(expectedMissingIngr, actualMissingIngr);

    List<Step> expectedMissingSource = new ArrayList<Step>();
    List<Step> tempMissingSource = new ArrayList<Step>();
    Recipe recipeMissingSource = new Recipe(recipeNameValid, 500, null, null, tempMissingSource);
    recipe.addIngredient(ingr);

    recipeMissingIngr.addUtensil(utensilDestination);
    recipeMissingIngr.addStep(step);
    List<Step> actualMissingSource = recipeMissingSource.getSteps();
    assertEquals(expectedMissingSource, actualMissingSource);

    List<Step> expectedMissingDestination = new ArrayList<Step>();
    List<Step> tempMissingDestination = new ArrayList<Step>();
    Recipe recipeMissingDestination = new Recipe(recipeNameValid, 500, null, null,
        tempMissingDestination);
    recipe.addIngredient(ingr);
    recipeMissingIngr.addUtensil(utensilSource);

    recipeMissingIngr.addStep(step);
    List<Step> actualMissingDestination = recipeMissingDestination.getSteps();
    assertEquals(expectedMissingDestination, actualMissingDestination);
  }

  @Test
  public void testRemoveStep()
  {
    List<Step> expected = new ArrayList<Step>();

    Ingredient ingr = new Ingredient(ingredientName1, ingredientDetails, 5, ingredientUnit,
        IngredientEditor.NO_INPUT, IngredientEditor.NO_INPUT);
    Utensil utensilSource = new Utensil(utensilName1, utensilDetails);
    Utensil utensilDestination = new Utensil(utensilNameDest, utensilDetails);
    Step step = new Step(stepAction, ingr, utensilSource, utensilDestination, stepDetails, time);

    List<Step> temp = new ArrayList<Step>(expected);
    Recipe recipe = new Recipe(recipeNameValid, 500, null, null, temp);
    recipe.removeStep(step);

    List<Step> actual = recipe.getSteps();
    assertEquals(expected, actual);
  }
}
