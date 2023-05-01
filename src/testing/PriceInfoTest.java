package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import recipes.NutritionInfo;
import recipes.PriceInfo;

class PriceInfoTest
{

  String wine = "wine";
  String bazaldaborb = "bazaldaborb";

  @Test
  void testContains()
  {
    assertEquals(true, PriceInfo.contains(wine));
    assertFalse(PriceInfo.contains(bazaldaborb));
  }

  @Test
  void testAddIngredient()
  {
    assertEquals(true, PriceInfo.addIngredient(bazaldaborb, 1.0));
    assertFalse(PriceInfo.addIngredient(wine, 1.0));
  }
  
  @Test
  void testGetIngredientsInMap()
  {
    assertNotEquals(0, PriceInfo.getIngredientsInMap());
  }
  
  @Test
  void testGetMappingValues() {
    String goodIngredientName = "goodIngredientName";
    String badIngredientName = "badIngredientName";
    String missingIngredient = "missingIngredientGood";
    
    PriceInfo.addIngredient(badIngredientName, null);
    PriceInfo.addIngredient(goodIngredientName, 15.0);
    
    assertEquals(0.0, PriceInfo.getPricePerTablespoon(badIngredientName));
    assertEquals(15.0, PriceInfo.getPricePerTablespoon(goodIngredientName));
    assertEquals(0.0, PriceInfo.getPricePerTablespoon(missingIngredient));


  }

}
