package testing;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import gui.IngredientEditor;
import gui.ShoppingListViewer;
import recipes.Ingredient;
import recipes.LeafRecipe;
import recipes.Recipe;
import recipes.Step;
import recipes.Utensil;

/**
 * Informal class to test if GUI works properly (not using JUnit). Can be deleted later.
 * 
 * @author Meara Patterson
 * @version 3/29/2023, Version 1
 */
public class TemporaryGUITest
{
  
  private static final String[] UNITS = new String[] {"", "Dram", "Ounce", "Gram", "Pound", "Pinch",
      "Teaspoon", "Tablespoon", "Fluid Ounce", "Cup", "Pint", "Quart", "Gallon", "Individual"};
 
  /**
   * Creates an IngredientEditor.
   */
  public static void ingredientEditorTest()
  {
    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(900, 400);
    JPanel contentPane = (JPanel) frame.getContentPane();
    IngredientEditor ingredientEditor = new IngredientEditor();
    contentPane.add(ingredientEditor);
    frame.setVisible(true);
  }

  /**
   * Creates a ShoppingListViewer.
   */
  public static void shoppingListViewerTest()
  {

    Recipe recipe = bananasFoster();
    ShoppingListViewer shoppingList = new ShoppingListViewer(recipe);
    
  }
  
  private static Recipe bananasFoster()
  {
    
    ArrayList<Ingredient> ingredients = new ArrayList<>();
    ingredients.add(new Ingredient("butter", "", 0.33, UNITS[9],
        IngredientEditor.NO_INPUT, IngredientEditor.NO_INPUT));
    ingredients.add(new Ingredient("brown sugar", "", 0.33, UNITS[9],
        IngredientEditor.NO_INPUT, IngredientEditor.NO_INPUT));
    ingredients.add(new Ingredient("bananas", "sliced ripe", 3, UNITS[13],
        IngredientEditor.NO_INPUT, IngredientEditor.NO_INPUT));
    ingredients.add(new Ingredient("creme de cacao", "", 2, UNITS[7],
        IngredientEditor.NO_INPUT, IngredientEditor.NO_INPUT));
    ingredients.add(new Ingredient("rum", "", 0.25, UNITS[9],
        IngredientEditor.NO_INPUT, IngredientEditor.NO_INPUT));
    ingredients.add(new Ingredient("ice cream", "vanilla", 2, UNITS[9],
        IngredientEditor.NO_INPUT, IngredientEditor.NO_INPUT));
    ingredients.add(new Ingredient("cinnamon", "ground", 0.25, UNITS[6],
        IngredientEditor.NO_INPUT, IngredientEditor.NO_INPUT));
    
    ArrayList<Utensil> utensils = new ArrayList<>();
    utensils.add(new Utensil("skillet", "large"));
    utensils.add(new Utensil("saucepan", ""));
    utensils.add(new Utensil("plate", ""));
    
    ArrayList<Step> steps = new ArrayList<>();
    steps.add(new Step("put", ingredients.get(0), utensils.get(0), utensils.get(0), "", 5));
    
    Recipe recipe = new LeafRecipe("Bananas Foster Test", 3);
    recipe.addAllIngredients(ingredients);
    recipe.addAllUtensils(utensils);
    recipe.addAllSteps(steps);
    
    return recipe;
  }

  /**
   * Run GUI elements.
   * @param args unused
   */
  public static void main(final String[] args)
  {
    shoppingListViewerTest();
  }

}
