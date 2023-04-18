package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import gui.IngredientEditor;
import recipes.Ingredient;
import recipes.NutritionInfo;
import recipes.Unit;

/**
 * Test cases for the Ingredient class.
 * 
 * @author Jack Milman, KichIntel
 *
 */
class IngredientTest
{
  private final String ingredientName = "NamedIngredient";
  private final String ingredientDetails = "Basic details for an Ingredient";
  private final Unit ingredientUnit = Unit.POUND;
  private final Unit ingredientUnitMetric = Unit.LITER;

  @Test
  public void testGetName()
  {
    String expected = ingredientName;
    Ingredient ingredient = new Ingredient(ingredientName, ingredientDetails, 10, ingredientUnit,
        IngredientEditor.NO_INPUT, IngredientEditor.NO_INPUT);
    String actual = ingredient.getName();
    assertEquals(expected, actual);
  }

  @Test
  public void testGetDetails()
  {
    String expected = ingredientDetails;
    Ingredient ingredient = new Ingredient(ingredientName, ingredientDetails, 10, ingredientUnit,
        IngredientEditor.NO_INPUT, IngredientEditor.NO_INPUT);
    String actual = ingredient.getDetails();
    assertEquals(expected, actual);
  }

  @Test
  public void testGetAmount()
  {
    double expected = 10;
    Ingredient ingredient = new Ingredient(ingredientName, ingredientDetails, 10, ingredientUnit,
        IngredientEditor.NO_INPUT, IngredientEditor.NO_INPUT);
    double actual = ingredient.getAmount();
    assertEquals(expected, actual);
  }

  @Test
  public void testGetUnit()
  {
    Unit expected = ingredientUnit;
    Ingredient ingredient = new Ingredient(ingredientName, ingredientDetails, 10, ingredientUnit,
        IngredientEditor.NO_INPUT, IngredientEditor.NO_INPUT);
    Unit actual = ingredient.getUnit();
    assertEquals(expected, actual);
  }
  
  @Test
  public void testGetCalories()
  {
    Ingredient ingredient = new Ingredient("Alcohol", "Scotch", 5, Unit.GRAM,
        NutritionInfo.getCalPerGram("Alcohol"), NutritionInfo.getGramPerML("Alcohol"));
    // Alcohol cal/gram = 2.75. 2.75 * 5 = 13.75
    double expectedPerGram = 13.75;
    // Alcohol cal/gram = 2.75. 2.75 * 5 = 13.75
    // Alcohol gram/ml = 0.79. 0.79 * 13.75 = 10.8625
    double expectedPerMilliliter = 10.8625;
    
    double actualPerGram = ingredient.getCaloriesPerGram();
    double actualPerMilliliter = ingredient.getCaloriesPerMilliliter();
    assertEquals(expectedPerGram, actualPerGram);
    assertEquals(expectedPerMilliliter, actualPerMilliliter);
  }

  @Test
  public void testEquals()
  {
    Ingredient ingredient1 = new Ingredient(ingredientName, ingredientDetails, 10, ingredientUnit,
        IngredientEditor.NO_INPUT, IngredientEditor.NO_INPUT);
    Ingredient ingredient2 = new Ingredient(ingredientName, ingredientDetails, 15,
        ingredientUnitMetric, IngredientEditor.NO_INPUT, IngredientEditor.NO_INPUT);
    Ingredient ingredient3 = new Ingredient("Not the same Ingredient", ingredientDetails, 10,
        ingredientUnit, IngredientEditor.NO_INPUT, IngredientEditor.NO_INPUT);
    Ingredient ingredient4 = new Ingredient(ingredientName, "Not the same Details", 10,
        ingredientUnit, IngredientEditor.NO_INPUT, IngredientEditor.NO_INPUT);
    assertEquals(ingredient1, ingredient1);
    assertFalse(ingredient1.equals(null));
    assertFalse(ingredient1.equals("I am not an Ingredient"));
    assertEquals(ingredient1, ingredient2);
    assertNotEquals(ingredient1, ingredient3);
    assertNotEquals(ingredient1, ingredient4);
  }

  @Test
  public void testHashCode()
  {
    Ingredient ingredient1 = new Ingredient(ingredientName, ingredientDetails, 10, ingredientUnit,
        IngredientEditor.NO_INPUT, IngredientEditor.NO_INPUT);
    Ingredient ingredient2 = new Ingredient(ingredientName, ingredientDetails, 1687, Unit.NONE,
        IngredientEditor.NO_INPUT, IngredientEditor.NO_INPUT);
    Ingredient differentName = new Ingredient("NewName", ingredientDetails, 1687, Unit.NONE,
        IngredientEditor.NO_INPUT, IngredientEditor.NO_INPUT);
    Ingredient differentDetails = new Ingredient(ingredientName, "NewDetails", 1687, Unit.NONE,
        IngredientEditor.NO_INPUT, IngredientEditor.NO_INPUT);
    
      int firstCode = ingredient1.hashCode();
      int secondCode = ingredient2.hashCode();
      int differentNameCode = differentName.hashCode();
      int differentDetailsCode = differentDetails.hashCode();
      assertEquals(firstCode, secondCode);
      assertNotEquals(firstCode, differentNameCode);
      assertNotEquals(firstCode, differentDetailsCode);
      assertNotEquals(differentNameCode, differentDetailsCode);
  }

}
