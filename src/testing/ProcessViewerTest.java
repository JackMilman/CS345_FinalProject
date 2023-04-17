package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import gui.IngredientEditor;
import gui.ProcessViewer;
import recipes.Ingredient;
import recipes.LeafRecipe;
import recipes.Meal;
import recipes.Recipe;
import recipes.Step;
import recipes.Utensil;

class ProcessViewerTest
{
  @Test
  private static Recipe recipe1()
  {
    ArrayList<Ingredient> ingredients = new ArrayList<>();
    ingredients.add(new Ingredient("chicken", " ", 1, "g", IngredientEditor.NO_INPUT,
        IngredientEditor.NO_INPUT));
    ingredients.add(new Ingredient("lettuce", " ", 1, "g", IngredientEditor.NO_INPUT,
        IngredientEditor.NO_INPUT));
    ingredients.add(new Ingredient("broccoli", " ", 1, "g", IngredientEditor.NO_INPUT,
        IngredientEditor.NO_INPUT));

    ArrayList<Utensil> utensils = new ArrayList<>();
    utensils.add(new Utensil("fork", " "));
    utensils.add(new Utensil("knife", " "));
    utensils.add(new Utensil("sink", ""));
    utensils.add(new Utensil("cutting board", ""));

    ArrayList<Step> steps = new ArrayList<>();
    steps.add(new Step("wash", ingredients.get(0), utensils.get(1), utensils.get(2), " ", 15));
    steps.add(new Step("wash", ingredients.get(1), utensils.get(1), utensils.get(2), " ", 15));
    steps.add(new Step("wash", ingredients.get(2), utensils.get(1), utensils.get(2), " ", 15));
    steps.add(new Step("cut", ingredients.get(0), utensils.get(0), utensils.get(1), " ", 20));
    steps.add(new Step("cut", ingredients.get(1), utensils.get(0), utensils.get(3), " ", 5));
    steps.add(new Step("cut", ingredients.get(2), utensils.get(0), utensils.get(3), " ", 10));

    Recipe recipe = new LeafRecipe("ProcessViewer Test", 2);
    recipe.addAllIngredients(ingredients);
    recipe.addAllUtensils(utensils);
    recipe.addAllSteps(steps);

    return recipe;
  }

  private static Recipe recipe2()
  {
    ArrayList<Ingredient> ingredients = new ArrayList<>();
    ingredients.add(new Ingredient("salmon", " ", 1, "g", IngredientEditor.NO_INPUT,
        IngredientEditor.NO_INPUT));
    ingredients.add(
        new Ingredient("rice", " ", 1, "g", IngredientEditor.NO_INPUT, IngredientEditor.NO_INPUT));
    ingredients.add(new Ingredient("broccoli", " ", 1, "g", IngredientEditor.NO_INPUT,
        IngredientEditor.NO_INPUT));

    ArrayList<Utensil> utensils = new ArrayList<>();
    utensils.add(new Utensil("spatula", " "));
    utensils.add(new Utensil("wisk", " "));
    utensils.add(new Utensil("fork", " "));

    ArrayList<Step> steps = new ArrayList<>();
    steps.add(new Step("cut", ingredients.get(0), utensils.get(0), utensils.get(1), " ", 5));
    steps.add(new Step("wash", ingredients.get(0), utensils.get(0), utensils.get(1), " ", 5));

    Recipe recipe = new LeafRecipe("ProcessViewer Test", 2);
    recipe.addAllIngredients(ingredients);
    recipe.addAllUtensils(utensils);
    recipe.addAllSteps(steps);

    return recipe;
  }

  @Test
  void testProcessViewerRecipes()
  {
    new ProcessViewer(recipe1());
  }

  @Test
  void testProcessViewerMeals()
  {

    ArrayList<Recipe> recipes = new ArrayList<>();
    recipes.add(recipe1());
    recipes.add(recipe2());

    Meal meal = new Meal("Dinner", recipes, 4);

    new ProcessViewer(meal);
  }

  public static void main(final String[] args)
  {
    ProcessViewerTest test = new ProcessViewerTest();
    test.testProcessViewerRecipes();
    test.testProcessViewerMeals();
  }

}
