
package testing;

import java.util.ArrayList;
import java.util.List;

import gui.IngredientEditor;
import gui.ProcessViewer;
import recipes.CompositeRecipe;
import recipes.Ingredient;
import recipes.LeafRecipe;
import recipes.Meal;
import recipes.Recipe;
import recipes.Step;
import recipes.Utensil;

class ProcessViewerTest {

	public static void testProcessViewerRecipes() {
		ArrayList<Ingredient> ingredients = new ArrayList<>();
		ingredients
				.add(new Ingredient("chicken", " ", 1, "POUND", IngredientEditor.NO_INPUT, IngredientEditor.NO_INPUT));
		ingredients.add(
				new Ingredient("lettuce", " ", 0.5, "POUND", IngredientEditor.NO_INPUT, IngredientEditor.NO_INPUT));
		ingredients
				.add(new Ingredient("broccoli", " ", 6, "OUNCE", IngredientEditor.NO_INPUT, IngredientEditor.NO_INPUT));

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

		Recipe recipe = new LeafRecipe("Garlic Butter Salmon", 2);
		recipe.addAllIngredients(ingredients);
		recipe.addAllUtensils(utensils);
		recipe.addAllSteps(steps);
		ProcessViewer pv = new ProcessViewer(recipe);
	}

	public static void testProcessViewerMeals() {
		ArrayList<Ingredient> ingredients = new ArrayList<>();
		ingredients
				.add(new Ingredient("chicken", " ", 1, "POUND", IngredientEditor.NO_INPUT, IngredientEditor.NO_INPUT));
		ingredients.add(
				new Ingredient("lettuce", " ", 0.5, "POUND", IngredientEditor.NO_INPUT, IngredientEditor.NO_INPUT));
		ingredients
				.add(new Ingredient("broccoli", " ", 6, "OUNCE", IngredientEditor.NO_INPUT, IngredientEditor.NO_INPUT));

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

		Recipe recipe = new LeafRecipe("Garlic Butter Salmon", 2);
		recipe.addAllIngredients(ingredients);
		recipe.addAllUtensils(utensils);
		recipe.addAllSteps(steps);

		ingredients = new ArrayList<>();
		ingredients
				.add(new Ingredient("tomatos", " ", 2, "POUND", IngredientEditor.NO_INPUT, IngredientEditor.NO_INPUT));
		ingredients.add(
				new Ingredient("lettuce", " ", 0.5, "POUND", IngredientEditor.NO_INPUT, IngredientEditor.NO_INPUT));
		ingredients
				.add(new Ingredient("carrots", " ", 6, "OUNCE", IngredientEditor.NO_INPUT, IngredientEditor.NO_INPUT));

		utensils = new ArrayList<>();
		utensils.add(new Utensil("knife", " "));
		utensils.add(new Utensil("sink", ""));
		utensils.add(new Utensil("cutting board", ""));

		steps = new ArrayList<>();
		steps.add(new Step("wash", ingredients.get(0), utensils.get(1), utensils.get(2), " ", 15));
		steps.add(new Step("wash", ingredients.get(1), utensils.get(1), utensils.get(2), " ", 15));
		steps.add(new Step("wash", ingredients.get(2), utensils.get(1), utensils.get(2), " ", 15));
		steps.add(new Step("cut", ingredients.get(0), utensils.get(0), utensils.get(1), " ", 20));
		steps.add(new Step("cut", ingredients.get(1), utensils.get(0), utensils.get(2), " ", 5));
		steps.add(new Step("cut", ingredients.get(2), utensils.get(0), utensils.get(2), " ", 10));

		Recipe salad = new LeafRecipe("Ceaser Salad", 2);
		salad.addAllIngredients(ingredients);
		salad.addAllUtensils(utensils);
		salad.addAllSteps(steps);

		List<Recipe> recipes = new ArrayList<>();
		recipes.add(salad);
		recipes.add(recipe);
		Meal meal = new Meal("Dinner", recipes, 2);

		ProcessViewer pv = new ProcessViewer(meal);
	}

	public static void main(final String[] args) {
		testProcessViewerRecipes();
		testProcessViewerMeals();
	}

}
