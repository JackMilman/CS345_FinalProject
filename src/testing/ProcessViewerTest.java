package testing;

import java.util.ArrayList;

import gui.ProcessViewer;
import recipes.Ingredient;
import recipes.Recipe;
import recipes.Step;
import recipes.Utensil;

class ProcessViewerTest
{

  public static void testProcessViewer()
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

    Recipe recipe = new Recipe("ProcessViewer Test", 2, ingredients, utensils, steps);
    ProcessViewer pv = new ProcessViewer(recipe);
  }

  public static void main(final String[] args)
  {
    testProcessViewer();
  }

}
