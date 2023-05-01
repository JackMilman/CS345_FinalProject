package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import recipes.Ingredient;
import recipes.NutritionInfo;
import recipes.Unit;

class NutritionInfoTest
{
  String wine = "wine";
  String bazaldaborb = "bazaldaborb";

  @Test
  void testContains()
  {
    assertEquals(true, NutritionInfo.contains(wine));
    assertFalse(NutritionInfo.contains(bazaldaborb));
  }

  @Test
  void testAddIngredient()
  {
    assertEquals(true, NutritionInfo.addIngredient(bazaldaborb, 1.0, 1.0));
    assertFalse(NutritionInfo.addIngredient(wine, 1.0, 1.0));
  }
  
  @Test
  void testGetIngredientsInMap()
  {
    assertNotEquals(0, NutritionInfo.getIngredientsInMap());
  }
  
  @Test
  void testGetMappingValues() {
    String goodIngredientName = "goodIngredientName";
    String badIngredientName = "badIngredientName";
    String missingIngredient = "missingIngredientGood";
    
    NutritionInfo.addIngredient(badIngredientName, null, null);
    NutritionInfo.addIngredient(goodIngredientName, 15.0, 15.0);
    
    assertEquals(0.0, NutritionInfo.getGramPerML(badIngredientName));
    assertEquals(0.0, NutritionInfo.getCalPerGram(badIngredientName));
    assertEquals(15.0, NutritionInfo.getGramPerML(goodIngredientName));
    assertEquals(15.0, NutritionInfo.getCalPerGram(goodIngredientName));
    assertEquals(0.0, NutritionInfo.getGramPerML(missingIngredient));
    assertEquals(0.0, NutritionInfo.getCalPerGram(missingIngredient));

  }

}
