package testing;

import java.util.ArrayList;

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
   * Creates a ShoppingListViewer.
   */
  public static void shoppingListViewerTest()
  {

    ArrayList<Ingredient> ingredients = new ArrayList<>();
    ingredients.add(new Ingredient("chicken", " ", 1, "g"));
    ingredients.add(new Ingredient("rice", " ", 1, "g"));
    ingredients.add(new Ingredient("broccoli", " ", 1, "g"));

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
