package testing;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import gui.IngredientEditor;
import gui.ShoppingListViewer;
import recipes.Ingredient;
import recipes.Meal;
import recipes.Recipe;
import recipes.Step;
import recipes.Unit;
import recipes.Utensil;

/**
 * Informal class to test if GUI works properly (not using JUnit). Can be deleted later.
 * 
 * @author Meara Patterson
 * @version 3/29/2023, Version 1
 */
public class TemporaryGUITest
{

  /**
   * Creates an IngredientEditor.
   */
  public static void ingredientEditorTest()
  {
    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(900, 400);
    JPanel contentPane = (JPanel) frame.getContentPane();
    IngredientEditor ingredientEditor = new IngredientEditor(new Recipe(null, 0), null);
    contentPane.add(ingredientEditor);
    frame.setVisible(true);
  }

  /**
   * Creates a ShoppingListViewer.
   */
  public static void shoppingListViewerTest()
  {

    ArrayList<Recipe> recipes = new ArrayList<>();
    recipes.add(bananasFoster());
    recipes.add(macNCheese());
    Meal meal = new Meal("Test Meal", recipes, 2);
    Recipe recipe = bananasFoster();
    ShoppingListViewer shoppingList = new ShoppingListViewer(recipe);

  }

  private static Recipe bananasFoster()
  {

    ArrayList<Ingredient> ingredients = new ArrayList<>();
    ingredients.add(new Ingredient("butter", "", 0.33, Unit.CUP));
    ingredients.add(new Ingredient("brown sugar", "", 0.33, Unit.CUP));
    ingredients.add(new Ingredient("bananas", "sliced ripe", 3, Unit.INDIVIDUAL));
    ingredients.add(new Ingredient("creme de cacao", "", 2, Unit.TABLESPOON));
    ingredients.add(new Ingredient("rum", "", 0.25, Unit.CUP));
    ingredients.add(new Ingredient("ice cream", "vanilla", 2, Unit.CUP));
    ingredients.add(new Ingredient("cinnamon", "ground", 0.25, Unit.TEASPOON));

    ArrayList<Utensil> utensils = new ArrayList<>();
    utensils.add(new Utensil("skillet", "large"));
    utensils.add(new Utensil("saucepan", ""));
    utensils.add(new Utensil("plate", ""));

    ArrayList<Step> steps = new ArrayList<>();
    steps.add(new Step("put", ingredients.get(0), utensils.get(0), utensils.get(0), "", 5));

    Recipe recipe = new Recipe("Bananas Foster Test", 3);
    recipe.addAllIngredients(ingredients);
    recipe.addAllUtensils(utensils);
    recipe.addAllSteps(steps);

    return recipe;
  }

  private static Recipe macNCheese()
  {

    ArrayList<Ingredient> ingredients = new ArrayList<>();
    ingredients.add(new Ingredient("macaroni", "dried elbow", 1, Unit.CUP));
    ingredients.add(new Ingredient("butter", "", 1, Unit.TABLESPOON));
    ingredients.add(new Ingredient("onion", "chopped", 0.25, Unit.CUP));
    ingredients.add(new Ingredient("flour", "all-purpose", 1, Unit.TABLESPOON));
    ingredients.add(new Ingredient("pepper", "black", 1, Unit.PINCH));
    ingredients.add(new Ingredient("milk", "", 1.25, Unit.CUP));
    ingredients.add(new Ingredient("American cheese", "shredded", 1.5, Unit.CUP));
    ingredients.add(new Ingredient("tomato", "sliced medium", 1, Unit.NONE));

    ArrayList<Utensil> utensils = new ArrayList<>();
    utensils.add(new Utensil("pot", "large"));
    utensils.add(new Utensil("saucepan", "medium"));
    utensils.add(new Utensil("casserole", "1-quart"));
    utensils.add(new Utensil("strainer", ""));

    ArrayList<Step> steps = new ArrayList<>();
    steps.add(new Step("boil", ingredients.get(0), utensils.get(0), utensils.get(0), "", 10));

    Recipe recipe = new Recipe("Mac and Cheese Test", 2);
    recipe.addAllIngredients(ingredients);
    recipe.addAllUtensils(utensils);
    recipe.addAllSteps(steps);

    return recipe;

  }

  /**
   * Run GUI elements.
   * 
   * @param args
   *          unused
   */
  public static void main(final String[] args)
  {
    shoppingListViewerTest();
  }

}
