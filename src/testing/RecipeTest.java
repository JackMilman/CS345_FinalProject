package testing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.Test;

import recipes.Ingredient;
import recipes.Recipe;
import recipes.Step;
import recipes.Unit;
import recipes.Utensil;
import utilities.SortLists;

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
  private final Unit ingredientUnit = Unit.POUND;
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
    Recipe recipe = new Recipe(recipeNameValid, 10);
    String actual = recipe.getName();
    assertEquals(expected, actual);

    String expectedNotValid = recipeNameDefault;
    Recipe recipeNotValid = new Recipe(recipeNameNotValid, 10);
    String actualNotValid = recipeNotValid.getName();
    assertEquals(expectedNotValid, actualNotValid);

    String expectedNull = recipeNameDefault;
    Recipe recipeNull = new Recipe(recipeNameNull, 10);
    String actualNull = recipeNull.getName();
    assertEquals(expectedNull, actualNull);
  }

  @Test
  public void testGetServings()
  {
    int expected = 500;
    Recipe recipe = new Recipe(recipeNameValid, 500);
    int actual = recipe.getServings();
    assertEquals(expected, actual);

    int expectedNegative = 1;
    Recipe recipeNegative = new Recipe(recipeNameValid, -5);
    int actualNegative = recipeNegative.getServings();
    assertEquals(expectedNegative, actualNegative);

    int expectedZero = 1;
    Recipe recipeZero = new Recipe(recipeNameValid, 0);
    int actualZero = recipeZero.getServings();
    assertEquals(expectedZero, actualZero);
  }

  @Test
  public void testGetIngredients()
  {
    Ingredient ingredient1 = new Ingredient(ingredientName1, ingredientDetails, 50, ingredientUnit);
    Ingredient ingredient2 = new Ingredient(ingredientName2, ingredientDetails, 50, ingredientUnit);
    Ingredient ingredient3 = new Ingredient(ingredientName3, ingredientDetails, 50, ingredientUnit);
    List<Ingredient> expected = new ArrayList<Ingredient>();
    expected.add(ingredient1);
    expected.add(ingredient2);
    expected.add(ingredient3);

    Recipe recipe = new Recipe(recipeNameValid, 500);
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

    Recipe recipe = new Recipe(recipeNameValid, 500);
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
    Ingredient ingr = new Ingredient(ingredientName1, ingredientDetails, 5, ingredientUnit);
    Utensil utensilSource = new Utensil(utensilName1, utensilDetails);
    Utensil utensilDestination = new Utensil(utensilNameDest, utensilDetails);
    Step step = new Step(stepAction, ingr, utensilSource, utensilDestination, stepDetails, time);
    expected.add(step);

    Recipe recipe = new Recipe(recipeNameValid, 500);
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
    Recipe recipe = new Recipe(recipeNameValid, 10);
    recipe.setName(recipeNameQuirky);
    String actual = recipe.getName();
    assertEquals(expected, actual);

    String expectedNotValid = recipeNameQuirky;
    Recipe recipeNotValid = new Recipe(recipeNameNotValid, 10);
    recipeNotValid.setName(recipeNameQuirky);
    String actualNotValid = recipeNotValid.getName();
    assertEquals(expectedNotValid, actualNotValid);

    String expectedNull = recipeNameQuirky;
    Recipe recipeNull = new Recipe(recipeNameNull, 10);
    recipeNull.setName(recipeNameQuirky);
    String actualNull = recipeNull.getName();
    assertEquals(expectedNull, actualNull);
  }

  @Test
  public void testSetServings()
  {
    int quirkyServings = 200;

    int expected = quirkyServings;
    Recipe recipe = new Recipe(recipeNameValid, 500);
    recipe.setServings(quirkyServings);
    int actual = recipe.getServings();
    assertEquals(expected, actual);

    int expectedNegative = quirkyServings;
    Recipe recipeNegative = new Recipe(recipeNameValid, -5);
    recipeNegative.setServings(quirkyServings);
    int actualNegative = recipeNegative.getServings();
    assertEquals(expectedNegative, actualNegative);

    int expectedZero = quirkyServings;
    Recipe recipeZero = new Recipe(recipeNameValid, 0);
    recipeZero.setServings(quirkyServings);
    int actualZero = recipeZero.getServings();
    assertEquals(expectedZero, actualZero);
  }

  @Test
  public void testAddIngredient()
  {
    Ingredient ingredient1 = new Ingredient(ingredientName1, ingredientDetails, 50, ingredientUnit);
    Ingredient ingredient2 = new Ingredient(ingredientName2, ingredientDetails, 50, ingredientUnit);
    Ingredient ingredient3 = new Ingredient(ingredientName3, ingredientDetails, 50, ingredientUnit);

    List<Ingredient> expected = new ArrayList<Ingredient>();
    expected.add(ingredient1);
    expected.add(ingredient2);
    expected.add(ingredient3);

    Recipe recipe = new Recipe(recipeNameValid, 500);
    recipe.addIngredient(ingredient1);
    recipe.addIngredient(ingredient2);
    recipe.addIngredient(ingredient3);

    List<Ingredient> actual = recipe.getIngredients();
    assertEquals(expected, actual);
  }

  @Test
  public void testRemoveIngredient()
  {
    Ingredient ingredient1 = new Ingredient(ingredientName1, ingredientDetails, 50, ingredientUnit);
    Ingredient ingredient2 = new Ingredient(ingredientName2, ingredientDetails, 50, ingredientUnit);
    Ingredient ingredient3 = new Ingredient(ingredientName3, ingredientDetails, 50, ingredientUnit);

    List<Ingredient> expected = new ArrayList<Ingredient>();
    expected.add(ingredient1);
    expected.add(ingredient2);

    Recipe recipe = new Recipe(recipeNameValid, 500);
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

    Recipe recipe = new Recipe(recipeNameValid, 500);
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

    Recipe recipe = new Recipe(recipeNameValid, 500);
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
    Ingredient ingr = new Ingredient(ingredientName1, ingredientDetails, 5, ingredientUnit);
    Utensil utensilSource = new Utensil(utensilName1, utensilDetails);
    Utensil utensilDestination = new Utensil(utensilNameDest, utensilDetails);
    Step step = new Step(stepAction, ingr, utensilSource, utensilDestination, stepDetails, time);

    List<Step> expected = new ArrayList<Step>();
    expected.add(step);
    Recipe recipe = new Recipe(recipeNameValid, 500);
    recipe.addIngredient(ingr);
    recipe.addUtensil(utensilSource);
    recipe.addUtensil(utensilDestination);
    recipe.addStep(step);

    List<Step> actual = recipe.getSteps();
    assertEquals(expected, actual);

    List<Step> expectedMissingIngr = new ArrayList<Step>();
    expectedMissingIngr.add(step);
    Recipe recipeMissingIngr = new Recipe(recipeNameValid, 500);
    recipeMissingIngr.addUtensil(utensilSource);
    recipeMissingIngr.addUtensil(utensilDestination);
    recipeMissingIngr.addStep(step);

    List<Step> actualMissingIngr = recipeMissingIngr.getSteps();
    assertEquals(expectedMissingIngr, actualMissingIngr);

    List<Step> expectedMissingSource = new ArrayList<Step>();
    expectedMissingSource.add(step);
    Recipe recipeMissingSource = new Recipe(recipeNameValid, 500);
    recipeMissingSource.addIngredient(ingr);
    recipeMissingSource.addUtensil(utensilDestination);
    recipeMissingSource.addStep(step);

    List<Step> actualMissingSource = recipeMissingSource.getSteps();
    assertEquals(expectedMissingSource, actualMissingSource);

    List<Step> expectedMissingDestination = new ArrayList<Step>();
    Recipe recipeMissingDestination = new Recipe(recipeNameValid, 500);
    recipeMissingDestination.addIngredient(ingr);
    recipeMissingDestination.addUtensil(utensilSource);
    recipeMissingDestination.addStep(step);

    List<Step> actualMissingDestination = recipeMissingDestination.getSteps();
    assertEquals(expectedMissingDestination, actualMissingDestination);
  }

  @Test
  public void testRemoveStep()
  {
    List<Step> expected = new ArrayList<Step>();

    Ingredient ingr = new Ingredient(ingredientName1, ingredientDetails, 5, ingredientUnit);
    Utensil utensilSource = new Utensil(utensilName1, utensilDetails);
    Utensil utensilDestination = new Utensil(utensilNameDest, utensilDetails);
    Step step = new Step(stepAction, ingr, utensilSource, utensilDestination, stepDetails, time);

    Recipe recipe = new Recipe(recipeNameValid, 500);
    recipe.addStep(step);
    recipe.removeStep(step);

    List<Step> actual = recipe.getSteps();
    assertEquals(expected, actual);
  }
  
  @Test
  public void testAddAndRemoveWithSubstitutes()
  {
    Ingredient ingredient1 = new Ingredient(ingredientName1, ingredientDetails, 50, ingredientUnit);
    Ingredient ingredient2 = new Ingredient(ingredientName2, ingredientDetails, 50, ingredientUnit);
    Ingredient ingredient3 = new Ingredient(ingredientName3, ingredientDetails, 50, ingredientUnit);
    Ingredient substitute = new Ingredient("substitute", ingredientDetails, 50, ingredientUnit);

    List<Ingredient> expected = new ArrayList<Ingredient>();
    expected.add(ingredient1);
    expected.add(ingredient2);

    Recipe recipe = new Recipe(recipeNameValid, 500);
    recipe.addIngredient(ingredient1);
    recipe.addIngredient(ingredient2);
    recipe.addSubstitute(ingredient2, substitute);
    assertFalse(recipe.removeIngredient(ingredient2));
    recipe.addIngredient(ingredient3);
    recipe.removeIngredient(ingredient3);

    List<Ingredient> actual = recipe.getIngredients();
    assertEquals(expected, actual);
  }
  
  @Test
  public void testAddAllMethods() {
    Ingredient ingredient1 = new Ingredient(ingredientName1, ingredientDetails, 50, ingredientUnit);
    Ingredient ingredient2 = new Ingredient(ingredientName2, ingredientDetails, 50, ingredientUnit);
    Ingredient ingredient3 = new Ingredient(ingredientName3, ingredientDetails, 50, ingredientUnit);
    Ingredient substitute1 = new Ingredient("substitute1", ingredientDetails, 50, ingredientUnit);
    Ingredient substitute2 = new Ingredient("substitute2", ingredientDetails, 50, ingredientUnit);
    Ingredient substitute3 = new Ingredient("substitute3", ingredientDetails, 50, ingredientUnit);
    Ingredient substitute4 = new Ingredient("substitute4", ingredientDetails, 50, ingredientUnit);
    Ingredient substitute5 = new Ingredient("substitute5", ingredientDetails, 50, ingredientUnit);
    Utensil utensil1 = new Utensil(utensilName1, utensilDetails);
    Utensil utensil2 = new Utensil(utensilName2, utensilDetails);
    Utensil utensil3 = new Utensil(utensilName3, utensilDetails);
    
    List<Ingredient> expectedIngr = new ArrayList<Ingredient>();
    expectedIngr.add(ingredient1);
    expectedIngr.add(ingredient2);
    expectedIngr.add(ingredient3);
    
    List<Ingredient> ingredients = new ArrayList<Ingredient>();
    ingredients.add(ingredient1);
    ingredients.add(ingredient2);
    ingredients.add(ingredient3);
    
    HashMap<Ingredient, List<Ingredient>> substitutes = new HashMap<Ingredient, List<Ingredient>>();
    substitutes.put(ingredient1, new ArrayList<Ingredient>());
    substitutes.put(ingredient2, new ArrayList<Ingredient>());
    substitutes.put(ingredient3, new ArrayList<Ingredient>());
    substitutes.get(ingredient1).add(substitute1);
    substitutes.get(ingredient1).add(substitute2);
    substitutes.get(ingredient2).add(substitute3);
    substitutes.get(ingredient3).add(substitute4);
    substitutes.get(ingredient3).add(substitute5);
    
    List<Ingredient> expectedSub = new ArrayList<Ingredient>();
    expectedSub.add(substitute1);
    expectedSub.add(substitute2);
    expectedSub.add(substitute3);
    expectedSub.add(substitute4);
    expectedSub.add(substitute5);
    
    List<Utensil> utensils = new ArrayList<Utensil>();
    utensils.add(utensil1);
    utensils.add(utensil2);
    utensils.add(utensil3);
    
    List<Utensil> expectedUtensil = new ArrayList<Utensil>();
    expectedUtensil.add(utensil1);
    expectedUtensil.add(utensil2);
    expectedUtensil.add(utensil3);
    
    
    Recipe recipe = new Recipe(recipeNameValid, 500);
    recipe.addAllIngredients(ingredients);
    assertEquals(expectedIngr, recipe.getIngredients());
    recipe.addAllSubstitutes(substitutes);
    HashMap<Ingredient, List<Ingredient>> actualSubMap = recipe.getSubstitutes();
    List<Ingredient> actualSubs = new ArrayList<Ingredient>();
    for (Ingredient key : actualSubMap.keySet()) {
      actualSubs.addAll(actualSubMap.get(key));
    }
    SortLists.sortIngredients(actualSubs);
    assertEquals(expectedSub, actualSubs);
    recipe.addAllUtensils(utensils);
    assertEquals(expectedUtensil, recipe.getUtensils());
  }
  
  @Test
  public void testToString() {
    Recipe recipe = new Recipe(recipeNameValid, 500);
    assertEquals(recipeNameValid, recipe.toString());
  }
}
