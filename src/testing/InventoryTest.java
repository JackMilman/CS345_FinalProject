package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import gui.IngredientEditor;
import recipes.Ingredient;
import recipes.Inventory;

class InventoryTest
{
  private final String ingredientName = "NamedIngredient";
  private final String newName = "NewName";
  private final String ingredientDetails = "Basic details for an Ingredient";
  private final double ingredientAmount = 10.0;
  private final String ingredientUnit = "POUND";
  private final String ingredientUnitOunce = "OUNCE";

  @Test
  public void testCreateInstance()
  {
    Inventory firstInstance = Inventory.createInstance();
    Inventory secondInstance = Inventory.createInstance();
    assertEquals(firstInstance, secondInstance);
  }

  @Test
  public void testAddIngredient()
  {
    Inventory instance = Inventory.createInstance();
    Ingredient ingredientPound = new Ingredient(ingredientName, ingredientDetails, ingredientAmount,
        ingredientUnit, IngredientEditor.NO_INPUT, IngredientEditor.NO_INPUT);
    Ingredient ingredientOunce = new Ingredient(ingredientName, ingredientDetails, ingredientAmount,
        ingredientUnitOunce, IngredientEditor.NO_INPUT, IngredientEditor.NO_INPUT);
    Ingredient newIngredient = new Ingredient(newName, ingredientDetails, ingredientAmount,
        ingredientUnit, IngredientEditor.NO_INPUT, IngredientEditor.NO_INPUT);
    
    instance.addIngredient(ingredientPound);
    double addedAmount = instance.getIngredient(ingredientPound).getAmount();
    assertEquals(ingredientAmount, addedAmount);
    assertEquals(1, instance.size());
    
    instance.addIngredient(ingredientOunce);
    addedAmount = instance.getIngredient(ingredientPound).getAmount();
    assertNotEquals(ingredientAmount, addedAmount);
    // 10 Pounds + 10 Ounces = 10.625 Pounds
    assertEquals(10.625, addedAmount);
    assertEquals(1, instance.size());
    instance.addIngredient(newIngredient);
    assertEquals(2, instance.size());
    
    
    Inventory.clear();
    assertEquals(0, instance.size());
  }
  
  @Test
  public void testGetIngredient() {
    Inventory instance = Inventory.createInstance();
    Ingredient ingredientPound = new Ingredient(ingredientName, ingredientDetails, ingredientAmount,
        ingredientUnit, IngredientEditor.NO_INPUT, IngredientEditor.NO_INPUT);
    Ingredient newIngredient = new Ingredient(newName, ingredientDetails, ingredientAmount,
        ingredientUnit, IngredientEditor.NO_INPUT, IngredientEditor.NO_INPUT);
    Ingredient notThere = new Ingredient("I am not here", "Not here either", ingredientAmount,
        ingredientUnit, IngredientEditor.NO_INPUT, IngredientEditor.NO_INPUT);
    instance.addIngredient(ingredientPound);
    instance.addIngredient(newIngredient);
    
    
    Ingredient gotten = instance.getIngredient(ingredientPound);
    assertEquals(ingredientPound, gotten);
    gotten = instance.getIngredient(ingredientName, ingredientDetails);
    assertEquals(ingredientPound, gotten);
    
    Ingredient newGotten = instance.getIngredient(newIngredient);
    assertEquals(newIngredient, newGotten);
    newGotten = instance.getIngredient(newName, ingredientDetails);
    assertEquals(newIngredient, newGotten);
    
    Ingredient notGotten = instance.getIngredient(notThere);
    assertEquals(null, notGotten);
    notGotten = instance.getIngredient("I am not here", "Not here either");
    assertEquals(null, notGotten);
    notGotten = instance.getIngredient("I could be anywhere", "Even right behind you");
    assertEquals(null, notGotten);
    
    
    Inventory.clear();
    assertEquals(0, instance.size());
  }
  
  @Test
  public void testGetIngredientWithAdding() {
    Inventory instance = Inventory.createInstance();
    Ingredient ingredientPound = new Ingredient(ingredientName, ingredientDetails, ingredientAmount,
        ingredientUnit, IngredientEditor.NO_INPUT, IngredientEditor.NO_INPUT);
    instance.addIngredient(ingredientPound);
    instance.addIngredient(ingredientPound);
    
    
    Ingredient gotten = instance.getIngredient(ingredientPound);
    assertEquals(ingredientPound, gotten);
    assertNotEquals(ingredientPound.getAmount(), gotten.getAmount());
    gotten = instance.getIngredient(ingredientName, ingredientDetails);
    assertEquals(ingredientPound, gotten);
    assertNotEquals(ingredientPound.getAmount(), gotten.getAmount());
    
    
    Inventory.clear();
    assertEquals(0, instance.size());
  }
  
  
  @Test
  public void testRemoveIngredient() {
	  Inventory instance = Inventory.createInstance();
	  Ingredient ingredient1 = new Ingredient(ingredientName + "1", ingredientDetails  + "1", ingredientAmount,
		        ingredientUnit, IngredientEditor.NO_INPUT, IngredientEditor.NO_INPUT);
	  Ingredient ingredient2 = new Ingredient(ingredientName + "2", ingredientDetails  + "2", ingredientAmount,
		        ingredientUnit, IngredientEditor.NO_INPUT, IngredientEditor.NO_INPUT);
	  Ingredient ingredient3 = new Ingredient(ingredientName + "3", ingredientDetails  + "3", ingredientAmount,
		        ingredientUnit, IngredientEditor.NO_INPUT, IngredientEditor.NO_INPUT);
	  
	  instance.addIngredient(ingredient1);
	  instance.addIngredient(ingredient2);
	  instance.addIngredient(ingredient3);
	  
	  Ingredient remove1 = new Ingredient(ingredientName + "1", ingredientDetails  + "1", 5,
		        ingredientUnit, IngredientEditor.NO_INPUT, IngredientEditor.NO_INPUT);
	  
	  instance.reduceIngredient(remove1);
	  assertEquals(5, instance.getIngredient(ingredient1).getAmount());
	  instance.reduceIngredient(remove1);
	  assertEquals(2, instance.size());
  }

  
}
