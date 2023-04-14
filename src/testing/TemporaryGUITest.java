package testing;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import gui.IngredientEditor;
import gui.ShoppingListViewer;
import recipes.Ingredient;
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

    ArrayList<Ingredient> ingredients = new ArrayList<>();
    ingredients.add(new Ingredient("chicken", " ", 1, "Gram", 
        IngredientEditor.NO_INPUT, IngredientEditor.NO_INPUT));
    ingredients.add(new Ingredient("rice", " ", 1, "Gram", 
        IngredientEditor.NO_INPUT, IngredientEditor.NO_INPUT));
    ingredients.add(new Ingredient("broccoli", " ", 1, "Gram", 
        IngredientEditor.NO_INPUT, IngredientEditor.NO_INPUT));

    ArrayList<Utensil> utensils = new ArrayList<>();
    utensils.add(new Utensil("fork", " "));
    utensils.add(new Utensil("knife", " "));

    ArrayList<Step> steps = new ArrayList<>();
    steps.add(new Step("cut", ingredients.get(0), utensils.get(0), utensils.get(1), " ", 5));

    Recipe recipe = new Recipe("Shopping List Test", 2, ingredients, utensils, steps);
    ShoppingListViewer shoppingList = new ShoppingListViewer(recipe);
    
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
