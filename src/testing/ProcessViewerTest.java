package testing;

import java.util.ArrayList;

import gui.IngredientEditor;
import gui.ProcessViewer;
import recipes.Ingredient;
import recipes.Meal;
import recipes.Recipe;
import recipes.Step;
import recipes.Utensil;

class ProcessViewerTest
{

  public static void testProcessViewerRecipes()
  {
    ArrayList<Ingredient> ingredients = new ArrayList<>();
    ingredients.add(new Ingredient("chicken", " ", 1, "g",
        IngredientEditor.NO_INPUT, IngredientEditor.NO_INPUT));
    ingredients.add(new Ingredient("rice", " ", 1, "g",
        IngredientEditor.NO_INPUT, IngredientEditor.NO_INPUT));
    ingredients.add(new Ingredient("broccoli", " ", 1, "g",
        IngredientEditor.NO_INPUT, IngredientEditor.NO_INPUT));

    ArrayList<Utensil> utensils = new ArrayList<>();
    utensils.add(new Utensil("fork", " "));
    utensils.add(new Utensil("knife", " "));

    ArrayList<Step> steps = new ArrayList<>();
    steps.add(new Step("cut", ingredients.get(0), utensils.get(0), utensils.get(1), " ", 5));

    Recipe recipe = new Recipe("ProcessViewer Test", 2, ingredients, utensils, steps);
    ProcessViewer pv = new ProcessViewer(recipe);
  }
  
  public static void testProcessViewerMeals()
  {
    ArrayList<Ingredient> ingredients = new ArrayList<>();
    ingredients.add(new Ingredient("salmon", " ", 1, "g",
        IngredientEditor.NO_INPUT, IngredientEditor.NO_INPUT));
    ingredients.add(new Ingredient("rice", " ", 1, "g",
        IngredientEditor.NO_INPUT, IngredientEditor.NO_INPUT));
    ingredients.add(new Ingredient("broccoli", " ", 1, "g",
        IngredientEditor.NO_INPUT, IngredientEditor.NO_INPUT));

    ArrayList<Utensil> utensils = new ArrayList<>();
    utensils.add(new Utensil("spatula", " "));
    utensils.add(new Utensil("wisk", " "));
    utensils.add(new Utensil("fork", " "));

    ArrayList<Step> steps = new ArrayList<>();
    steps.add(new Step("cut", ingredients.get(0), utensils.get(0), utensils.get(1), " ", 5));
    steps.add(new Step("wash", ingredients.get(0), utensils.get(0), utensils.get(1), " ", 5));

    ArrayList<Recipe> recipes = new ArrayList<>();
    recipes.add(new Recipe("ProcessViewer Test", 2, ingredients, utensils, steps));
    recipes.add(new Recipe("ProcessViewer Test", 2, ingredients, utensils, steps));
    
    
    Meal meal = new Meal("Dinner", recipes, 4);
    
    ProcessViewer pv = new ProcessViewer(meal);
  }
  
  public static void main(final String[] args)
  {
    testProcessViewerRecipes();
    testProcessViewerMeals();
  }

}
