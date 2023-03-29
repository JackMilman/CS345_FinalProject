package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import recipes.Ingredient;

class IngredientTest
{
  final String ingredientName = "NamedIngredient";
  final String ingredientDetails = "Basic details for an Ingredient";
  final String ingredientUnit = "lbs";
  final String ingredientUnitMetric = "liters";

  @Test
  public void testGetName()
  {
    String expected = ingredientName;
    Ingredient ingredient = new Ingredient(ingredientName, ingredientDetails, 10, ingredientUnit);
    String actual = ingredient.getName();
    assertEquals(expected, actual);
  }

  @Test
  public void testGetDetails()
  {
    String expected = ingredientDetails;
    Ingredient ingredient = new Ingredient(ingredientName, ingredientDetails, 10, ingredientUnit);
    String actual = ingredient.getDetails();
    assertEquals(expected, actual);
  }

  @Test
  public void testGetAmount()
  {
    int expected = 10;
    Ingredient ingredient = new Ingredient(ingredientName, ingredientDetails, 10, ingredientUnit);
    int actual = ingredient.getAmount();
    assertEquals(expected, actual);
  }

  @Test
  public void testGetUnit()
  {
    String expected = ingredientUnit;
    Ingredient ingredient = new Ingredient(ingredientName, ingredientDetails, 10, ingredientUnit);
    String actual = ingredient.getUnit();
    assertEquals(expected, actual);
  }

  @Test
  public void testEquals()
  {
    Ingredient ingredient1 = new Ingredient(ingredientName, ingredientDetails, 10, ingredientUnit);
    Ingredient ingredient2 = new Ingredient(ingredientName, ingredientDetails, 15,
        ingredientUnitMetric);
    Ingredient ingredient3 = new Ingredient("Not the same Ingredient", ingredientDetails, 10,
        ingredientUnit);
    Ingredient ingredient4 = new Ingredient(ingredientName, "Not the same Details", 10,
        ingredientUnit);
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
    Ingredient ingredient1 = new Ingredient(ingredientName, ingredientDetails, 10, ingredientUnit);
    try
    {
      ingredient1.hashCode();
      // Should not reach this line
      fail();
    }
    catch (UnsupportedOperationException uoe)
    {
      // Want to get here!
    }
  }

}
