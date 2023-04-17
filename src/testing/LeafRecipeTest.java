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
class LeafRecipeTest
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
    Recipe recipe = new LeafRecipe(recipeNameValid, 10);
    String actual = recipe.getName();
    assertEquals(expected, actual);

    String expectedNotValid = recipeNameDefault;
    Recipe recipeNotValid = new LeafRecipe(recipeNameNotValid, 10);
    String actualNotValid = recipeNotValid.getName();
    assertEquals(expectedNotValid, actualNotValid);

    String expectedNull = recipeNameDefault;
    Recipe recipeNull = new LeafRecipe(recipeNameNull, 10);
    String actualNull = recipeNull.getName();
    assertEquals(expectedNull, actualNull);
  }

  @Test
  public void testGetServings()
  {
    int expected = 500;
    Recipe recipe = new LeafRecipe(recipeNameValid, 500);
    int actual = recipe.getServings();
    assertEquals(expected, actual);

    int expectedNegative = 1;
    Recipe recipeNegative = new LeafRecipe(recipeNameValid, -5);
    int actualNegative = recipeNegative.getServings();
    assertEquals(expectedNegative, actualNegative);

    int expectedZero = 1;
    Recipe recipeZero = new LeafRecipe(recipeNameValid, 0);
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
    
    Recipe recipe = new LeafRecipe(recipeNameValid, 500);
    recipe.addIngredient(ingredient1);
    recipe.addIngredient(ingredient2);
    recipe.addIngredient(ingredient3);
    
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

    Recipe recipe = new LeafRecipe(recipeNameValid, 500);
    recipe.addUtensil(new Utensil(utensilName1, utensilDetails));
    recipe.addUtensil(new Utensil(utensilName2, utensilDetails));
    recipe.addUtensil(new Utensil(utensilName3, utensilDetails));
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

    Recipe recipe = new LeafRecipe(recipeNameValid, 500);
    recipe.addIngredient(ingr);
    recipe.addUtensil(utensilSource);
    recipe.addUtensil(utensilDestination);
    recipe.addStep(step);
    
    List<Step> actual = recipe.getSteps();
    assertEquals(expected, actual);
  }

  @Test
  public void testSetName()
  {
    String expected = recipeNameQuirky;
    Recipe recipe = new LeafRecipe(recipeNameValid, 10);
    recipe.setName(recipeNameQuirky);
    String actual = recipe.getName();
    assertEquals(expected, actual);

    String expectedNotValid = recipeNameQuirky;
    Recipe recipeNotValid = new LeafRecipe(recipeNameNotValid, 10);
    recipeNotValid.setName(recipeNameQuirky);
    String actualNotValid = recipeNotValid.getName();
    assertEquals(expectedNotValid, actualNotValid);

    String expectedNull = recipeNameQuirky;
    Recipe recipeNull = new LeafRecipe(recipeNameNull, 10);
    recipeNull.setName(recipeNameQuirky);
    String actualNull = recipeNull.getName();
    assertEquals(expectedNull, actualNull);
  }

  @Test
  public void testSetServings()
  {
    int quirkyServings = 200;

    int expected = quirkyServings;
    Recipe recipe = new LeafRecipe(recipeNameValid, 500);
    recipe.setServings(quirkyServings);
    int actual = recipe.getServings();
    assertEquals(expected, actual);

    int expectedNegative = quirkyServings;
    Recipe recipeNegative = new LeafRecipe(recipeNameValid, -5);
    recipeNegative.setServings(quirkyServings);
    int actualNegative = recipeNegative.getServings();
    assertEquals(expectedNegative, actualNegative);

    int expectedZero = quirkyServings;
    Recipe recipeZero = new LeafRecipe(recipeNameValid, 0);
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
    
    Recipe recipe = new LeafRecipe(recipeNameValid, 500);
    recipe.addIngredient(ingredient1);
    recipe.addIngredient(ingredient2);
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
    
    Recipe recipe = new LeafRecipe(recipeNameValid, 500);
    recipe.addIngredient(ingredient1);
    recipe.addIngredient(ingredient2);
    recipe.addIngredient(ingredient3);
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

    Recipe recipe = new LeafRecipe(recipeNameValid, 500);
    recipe.addUtensil(utensil1);
    recipe.addUtensil(utensil2);
    recipe.addUtensil(utensil3);
    
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

    Recipe recipe = new LeafRecipe(recipeNameValid, 500);
    recipe.addUtensil(utensil1);
    recipe.addUtensil(utensil2);
    recipe.addUtensil(utensil3);
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
    Recipe recipe = new LeafRecipe(recipeNameValid, 500);
    recipe.addIngredient(ingr);
    recipe.addUtensil(utensilSource);
    recipe.addUtensil(utensilDestination);
    recipe.addStep(step);
    
    List<Step> actual = recipe.getSteps();
    assertEquals(expected, actual);

    
    List<Step> expectedMissingIngr = new ArrayList<Step>();
    Recipe recipeMissingIngr = new LeafRecipe(recipeNameValid, 500);
    recipeMissingIngr.addUtensil(utensilSource);
    recipeMissingIngr.addUtensil(utensilDestination);
    recipeMissingIngr.addStep(step);
    
    List<Step> actualMissingIngr = recipeMissingIngr.getSteps();
    assertEquals(expectedMissingIngr, actualMissingIngr);

    
    List<Step> expectedMissingSource = new ArrayList<Step>();
    Recipe recipeMissingSource = new LeafRecipe(recipeNameValid, 500);
    recipe.addIngredient(ingr);
    recipeMissingIngr.addUtensil(utensilDestination);
    recipeMissingIngr.addStep(step);
    
    List<Step> actualMissingSource = recipeMissingSource.getSteps();
    assertEquals(expectedMissingSource, actualMissingSource);

    
    List<Step> expectedMissingDestination = new ArrayList<Step>();
    Recipe recipeMissingDestination = new LeafRecipe(recipeNameValid, 500);
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
    
    Recipe recipe = new LeafRecipe(recipeNameValid, 500);
    recipe.addStep(step);
    recipe.removeStep(step);

    List<Step> actual = recipe.getSteps();
    assertEquals(expected, actual);
  }
}
