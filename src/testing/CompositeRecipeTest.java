package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import gui.IngredientEditor;
import recipes.CompositeRecipe;
import recipes.Ingredient;
import recipes.LeafRecipe;
import recipes.Recipe;
import recipes.Unit;

class CompositeRecipeTest
{
  private final String recipeNameValid = "NamedRecipe";

  private final String ingredientName1 = "RANDOM";
  private final String ingredientDetails = "Basic Details for an Ingredient";
  private final Unit ingredientUnit = Unit.POUND;
  private final Unit ingredientUnit2 = Unit.GRAM;
  private final String ingredientName2 = "RAMDOM";
  private final String ingredientName3 = "RABDOM";
  private final String ingredientName4 = "Real Ingredient";

  private final String utensilName1 = "Spoon";
  private final String utensilNameDest = "NamedUtensilDestination";
  private final String utensilDetails = "Basic Details for a Utensil";
  private final String utensilName2 = "Fork";
  private final String utensilName3 = "Knife";

  private final Ingredient ingredient1 = new Ingredient(ingredientName1, ingredientDetails, 50,
      ingredientUnit, null, null, 0.0);
  private final Ingredient ingredient2 = new Ingredient(ingredientName2, ingredientDetails, 50,
      ingredientUnit, null, null, 0.0);
  private final Ingredient ingredient3 = new Ingredient(ingredientName3, ingredientDetails, 50,
      ingredientUnit, null, null, 0.0);
  // Total calories: 50 * 10.0 = 500
  private final Ingredient ingredient4 = new Ingredient(ingredientName4, ingredientDetails, 50,
      ingredientUnit2, 10.0, 1.0, 0.0);

  @Test
  public void testCalculateCalories()
  {
    CompositeRecipe composite = new CompositeRecipe(recipeNameValid, 1);
    assertEquals(0, composite.calculateCalories());

    List<Ingredient> expected = new ArrayList<Ingredient>();
    expected.add(ingredient1);
    expected.add(ingredient2);
    expected.add(ingredient3);

    Recipe recipe = new LeafRecipe(recipeNameValid, 500);
    recipe.addIngredient(ingredient1);
    recipe.addIngredient(ingredient2);
    recipe.addIngredient(ingredient3);

    composite.addRecipe(recipe);
    assertEquals(expected, composite.getIngredients());
    assertEquals(0, composite.calculateCalories());
    composite.addIngredient(ingredient4);
    assertEquals(500.0, composite.calculateCalories());
  }

  @Test
  public void testGetIngredients()
  {
    CompositeRecipe composite = new CompositeRecipe(recipeNameValid, 1);

    List<Ingredient> expected = new ArrayList<Ingredient>();
    expected.add(ingredient1);
    expected.add(ingredient2);
    expected.add(ingredient3);
    expected.add(ingredient4);

    Recipe recipe = new LeafRecipe(recipeNameValid, 500);
    recipe.addIngredient(ingredient1);
    recipe.addIngredient(ingredient2);
    recipe.addIngredient(ingredient3);

    composite.addRecipe(recipe);
    assertNotEquals(expected, composite.getIngredients());
    composite.addIngredient(ingredient4);
    assertEquals(expected, composite.getIngredients());
  }

  @Test
  public void testAddRemove()
  {
    CompositeRecipe composite = new CompositeRecipe(recipeNameValid, 1);

    List<Ingredient> expected1 = new ArrayList<Ingredient>();
    expected1.add(ingredient1);
    expected1.add(ingredient2);
    expected1.add(ingredient3);
    expected1.add(ingredient4);

    List<Ingredient> expected2 = new ArrayList<Ingredient>();
    expected2.add(ingredient4);

    Recipe recipe = new LeafRecipe(recipeNameValid, 500);
    recipe.addIngredient(ingredient1);
    recipe.addIngredient(ingredient2);
    recipe.addIngredient(ingredient3);

    composite.addRecipe(recipe);
    composite.addIngredient(ingredient4);
    assertEquals(expected1, composite.getIngredients());
    composite.removeRecipe(recipe);
    assertEquals(expected2, composite.getIngredients());
  }

  @Test
  public void testGetSubRecipes()
  {
    CompositeRecipe composite = new CompositeRecipe(recipeNameValid, 1);

    Recipe recipe = new LeafRecipe(recipeNameValid, 500);
    recipe.addIngredient(ingredient1);
    recipe.addIngredient(ingredient2);
    recipe.addIngredient(ingredient3);

    Recipe recipe2 = new LeafRecipe(recipeNameValid, 2);
    recipe2.addIngredient(ingredient1);
    recipe2.addIngredient(ingredient3);

    Recipe recipe3 = new LeafRecipe(recipeNameValid, 5);
    recipe.addIngredient(ingredient4);
    recipe.addIngredient(ingredient2);
    recipe.addIngredient(ingredient3);

    List<Recipe> expected = new ArrayList<Recipe>();
    expected.add(recipe);
    expected.add(recipe2);
    expected.add(recipe3);

    composite.addRecipe(recipe);
    composite.addRecipe(recipe2);
    composite.addRecipe(recipe3);
    assertEquals(expected, composite.getSubRecipes());
  }
}
