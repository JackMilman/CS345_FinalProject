package testing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import recipes.Ingredient;
import recipes.Recipe;
import recipes.Step;
import recipes.Unit;
import recipes.Utensil;

/**
 * Test cases for the Step class.
 * 
 * @author Jack Milman, KichIntel
 *
 */
class StepTest
{
  private final String basicAction = "do";
  private final String quirkyAction = "doWell";

  private final String basicDetails = "details";
  private final String quirkyDetails = "extraDetails";

  private final String basicIngName = "ingredientName";
  private final String quirkyIngName = "quirkyIngredientName";

  private final Ingredient basicIngredient = new Ingredient(basicIngName, "ingredientDetails", 1,
      Unit.POUND);
  private final Ingredient quirkyIngredient = new Ingredient(quirkyIngName,
      "quirkyIngredientDetails", 2, Unit.DRAM);
  
  private final String basicUtenName = "utensilName";
  private final String quirkyUtenName = "quirkyUtensilName";
  private final String basicDestName = "utensilName_Dest";
  private final String quirkyDestName = "quirkyUtensilName_Dest";

  private final Utensil basicSource = new Utensil(basicUtenName, "utensilDetails");
  private final Utensil quirkySource = new Utensil(quirkyUtenName, "quirkyUtensilDetails");

  private final Utensil basicDestination = new Utensil(basicDestName, "utensilDetails_Dest");
  private final Utensil quirkyDestination = new Utensil(quirkyDestName,
      "quirkyUtensilDetails_Dest");

  private final int basicTime = 10;
  private final int quirkyTime = 10000;

  @Test
  public void testGetAction()
  {
    String expected = basicAction;
    Step step = new Step(basicAction, basicIngredient, basicSource, basicDestination, basicDetails,
        basicTime);
    String actual = step.getAction();
    assertEquals(expected, actual);
  }

  @Test
  public void testGetDetails()
  {
    String expected = basicDetails;
    Step step = new Step(basicAction, basicIngredient, basicSource, basicDestination, basicDetails,
        basicTime);
    String actual = step.getDetails();
    assertEquals(expected, actual);
    assertEquals(expected, actual);
  }

  @Test
  public void testGetIngredient()
  {
    Ingredient expected = basicIngredient;
    Step step = new Step(basicAction, basicIngredient, basicSource, basicDestination, basicDetails,
        basicTime);
    Ingredient actual = step.getIngredient();
    assertEquals(expected, actual);
  }

  @Test
  public void testGetSource()
  {
    Utensil expected = basicSource;
    Step step = new Step(basicAction, basicIngredient, basicSource, basicDestination, basicDetails,
        basicTime);
    Utensil actual = step.getSource();
    assertEquals(expected, actual);
  }

  @Test
  public void testGetDestination()
  {
    Utensil expected = basicDestination;
    Step step = new Step(basicAction, basicIngredient, basicSource, basicDestination, basicDetails,
        basicTime);
    Utensil actual = step.getDestination();
    assertEquals(expected, actual);
  }

  @Test
  public void testGetTime()
  {
    int expected = basicTime;
    Step step = new Step(basicAction, basicIngredient, basicSource, basicDestination, basicDetails,
        basicTime);
    int actual = step.getTime();
    assertEquals(expected, actual);
  }

  @Test
  public void testSetAction()
  {
    String expected = quirkyAction;
    Step step = new Step(basicAction, basicIngredient, basicSource, basicDestination, basicDetails,
        basicTime);
    step.setAction(quirkyAction);
    String actual = step.getAction();
    assertEquals(expected, actual);
  }

  @Test
  public void testSetDetails()
  {
    String expected = quirkyDetails;
    Step step = new Step(basicAction, basicIngredient, basicSource, basicDestination, basicDetails,
        basicTime);
    step.setDetails(quirkyDetails);
    String actual = step.getDetails();
    assertEquals(expected, actual);
  }

  @Test
  public void testSetIngredient()
  {
    Ingredient expected = quirkyIngredient;
    Step step = new Step(basicAction, basicIngredient, basicSource, basicDestination, basicDetails,
        basicTime);
    step.setIngredient(quirkyIngredient);
    Ingredient actual = step.getIngredient();
    assertEquals(expected, actual);
  }

  @Test
  public void testSetSource()
  {
    Utensil expected = quirkySource;
    Step step = new Step(basicAction, basicIngredient, basicSource, basicDestination, basicDetails,
        basicTime);
    step.setSource(quirkySource);
    Utensil actual = step.getSource();
    assertEquals(expected, actual);
  }

  @Test
  public void testSetDestination()
  {
    Utensil expected = quirkyDestination;
    Step step = new Step(basicAction, basicIngredient, basicSource, basicDestination, basicDetails,
        basicTime);
    step.setDestination(quirkyDestination);
    Utensil actual = step.getDestination();
    assertEquals(expected, actual);
  }

  @Test
  public void testSetTime()
  {
    int expected = quirkyTime;
    Step step = new Step(basicAction, basicIngredient, basicSource, basicDestination, basicDetails,
        basicTime);
    step.setTime(quirkyTime);
    int actual = step.getTime();
    assertEquals(expected, actual);
  }

  @Test
  public void testGetRecipe()
  {
    Recipe recipe1 = new Recipe("I am a recipe", 1);
    Step step = new Step(basicAction, recipe1, basicSource, basicDestination, basicDetails,
        basicTime);
    assertEquals(recipe1, step.getRecipe());
  }

  @Test
  public void testSetRecipe()
  {
    Recipe recipe1 = new Recipe("I am a recipe", 1);
    Recipe recipe2 = new Recipe("I am a different recipe", 12);
    Step step = new Step(basicAction, recipe1, basicSource, basicDestination, basicDetails,
        basicTime);
    assertEquals(recipe1, step.getRecipe());
    step.setRecipe(recipe2);
    assertEquals(recipe2, step.getRecipe());
  }

  @Test
  public void testToStringAndVerbose()
  {
    String recipeName = "recipeName";
    Recipe recipe = new Recipe(recipeName, 1);
    
    Step ingredientStep = new Step(basicAction, basicIngredient, null, basicDestination, basicDetails,
        basicTime);
    Step utensilSameStep = new Step(basicAction, recipe, basicSource, basicSource, basicDetails,
        basicTime);
    Step utensilDifferentStep = new Step(basicAction, recipe, basicSource, basicDestination, basicDetails,
        basicTime);
    Step recipeStep = new Step(basicAction, recipe, null, basicDestination, basicDetails,
        basicTime);
    
    String ingredient = String.format("%s the %s on the %s %s\t\t%s minutes",
        basicAction, basicIngName, basicDestName, basicDetails, basicTime).strip();
    String sameUtensils = String.format("%s the contents of the %s %s\t\t%s minutes",
        basicAction, basicUtenName, basicDetails, basicTime).strip();
    String differentUtensils = String.format("%s the contents of the %s in the %s %s\t\t%s minutes",
        basicAction, basicUtenName, basicDestName, basicDetails, basicTime).strip();
    String recipeString = String.format("%s the *%s on the %s %s\t\t%s minutes",
        basicAction, recipeName, basicDestName, basicDetails, basicTime).strip();
    
    assertEquals(ingredient, ingredientStep.toString());
    assertEquals(sameUtensils, utensilSameStep.toString());
    assertEquals(differentUtensils, utensilDifferentStep.toString());
    assertEquals(recipeString, recipeStep.toString());
    
    // Verbose testing
    boolean verbose = true;
    boolean terse = false;
    String ingredientTerse = String.format("%s the %s on the %s %s",
        basicAction, basicIngName, basicDestName, basicDetails).strip();
    String sameUtensilsTerse = String.format("%s the contents of the %s %s",
        basicAction, basicUtenName, basicDetails).strip();
    String differentUtensilsTerse = String.format("%s the contents of the %s in the %s %s",
        basicAction, basicUtenName, basicDestName, basicDetails, basicTime).strip();
    String recipeStringTerse = String.format("%s the *%s on the %s %s",
        basicAction, recipeName, basicDestName, basicDetails).strip();
    assertEquals(ingredient, ingredientStep.toString(verbose));
    assertNotEquals(ingredient, ingredientStep.toString(terse));
    assertEquals(ingredientTerse, ingredientStep.toString(terse));
    assertEquals(sameUtensilsTerse, utensilSameStep.toString(terse));
    assertEquals(differentUtensilsTerse, utensilDifferentStep.toString(terse));
    assertEquals(recipeStringTerse, recipeStep.toString(terse));
    
  }
}
