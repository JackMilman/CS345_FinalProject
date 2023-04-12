package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import gui.IngredientEditor;
import recipes.*;

/**
 * Test cases for the Step class.
 * 
 * @author Jack Milman, KichIntel
 *
 */
class StepTest
{
  final String basicAction = "do";
  final String quirkyAction = "doWell";
  
  final String basicDetails = "details";
  final String quirkyDetails = "extraDetails";
  
  final Ingredient basicIngredient = new Ingredient("ingredientName", "ingredientDetails", 1, "POUNDS",
      IngredientEditor.NO_INPUT, IngredientEditor.NO_INPUT);
  final Ingredient quirkyIngredient = new Ingredient("quirkyIngredientName", "quirkyIngredientDetails", 2, "DRAMS",
      IngredientEditor.NO_INPUT, IngredientEditor.NO_INPUT);
  
  final Utensil basicSource = new Utensil("utensilName", "utensilDetails");
  final Utensil quirkySource = new Utensil("quirkyUtensilName", "quirkyUtensilDetails");
  
  final Utensil basicDestination = new Utensil("utensilName_Dest", "utensilDetails_Dest");
  final Utensil quirkyDestination = new Utensil("quirkyUtensilName_Dest", "quirkyUtensilDetails_Dest");
  
  final int basicTime = 10;
  final int quirkyTime = 10000;

  @Test
  public void testGetAction()
  {
    String expected = basicAction;
    Step step = new Step(basicAction, basicIngredient, basicSource, basicDestination, basicDetails, basicTime);
    String actual = step.getAction();
    assertEquals(expected, actual);
  }

  @Test
  public void testGetDetails()
  {
    String expected = basicDetails;
    Step step = new Step(basicAction, basicIngredient, basicSource, basicDestination, basicDetails, basicTime);
    String actual = step.getDetails();
    assertEquals(expected, actual);
    assertEquals(expected, actual);
  }

  @Test
  public void testGetIngredient()
  {
    Ingredient expected = basicIngredient;
    Step step = new Step(basicAction, basicIngredient, basicSource, basicDestination, basicDetails, basicTime);
    Ingredient actual = step.getIngredient();
    assertEquals(expected, actual);
  }

  @Test
  public void testGetSource()
  {
    Utensil expected = basicSource;
    Step step = new Step(basicAction, basicIngredient, basicSource, basicDestination, basicDetails, basicTime);
    Utensil actual = step.getSource();
    assertEquals(expected, actual);
  }
  
  @Test
  public void testGetDestination()
  {
    Utensil expected = basicDestination;
    Step step = new Step(basicAction, basicIngredient, basicSource, basicDestination, basicDetails, basicTime);
    Utensil actual = step.getDestination();
    assertEquals(expected, actual);
  }
  
  @Test
  public void testGetTime()
  {
    int expected = basicTime;
    Step step = new Step(basicAction, basicIngredient, basicSource, basicDestination, basicDetails, basicTime);
    int actual = step.getTime();
    assertEquals(expected, actual);
  }
  
  @Test
  public void testSetAction()
  {
    String expected = quirkyAction;
    Step step = new Step(basicAction, basicIngredient, basicSource, basicDestination, basicDetails, basicTime);
    step.setAction(quirkyAction);
    String actual = step.getAction();
    assertEquals(expected, actual);
  }

  @Test
  public void testSetDetails()
  {
    String expected = quirkyDetails;
    Step step = new Step(basicAction, basicIngredient, basicSource, basicDestination, basicDetails, basicTime);
    step.setDetails(quirkyDetails);
    String actual = step.getDetails();
    assertEquals(expected, actual);
  }
  
  @Test
  public void testSetIngredient()
  {
    Ingredient expected = quirkyIngredient;
    Step step = new Step(basicAction, basicIngredient, basicSource, basicDestination, basicDetails, basicTime);
    step.setIngredient(quirkyIngredient);
    Ingredient actual = step.getIngredient();
    assertEquals(expected, actual);
  }
  
  @Test
  public void testSetSource()
  {
    Utensil expected = quirkySource;
    Step step = new Step(basicAction, basicIngredient, basicSource, basicDestination, basicDetails, basicTime);
    step.setSource(quirkySource);
    Utensil actual = step.getSource();
    assertEquals(expected, actual);
  }
  
  @Test
  public void testSetDestination()
  {
    Utensil expected = quirkyDestination;
    Step step = new Step(basicAction, basicIngredient, basicSource, basicDestination, basicDetails, basicTime);
    step.setDestination(quirkyDestination);
    Utensil actual = step.getDestination();
    assertEquals(expected, actual);
  }
  
  @Test
  public void testSetTime()
  {
    int expected = quirkyTime;
    Step step = new Step(basicAction, basicIngredient, basicSource, basicDestination, basicDetails, basicTime);
    step.setTime(quirkyTime);
    int actual = step.getTime();
    assertEquals(expected, actual);
  }

}
