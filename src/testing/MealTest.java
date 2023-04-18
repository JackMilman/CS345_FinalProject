package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import gui.IngredientEditor;
import recipes.Ingredient;
import recipes.LeafRecipe;
import recipes.Meal;
import recipes.Recipe;
import recipes.Step;
import recipes.Unit;
import recipes.Utensil;

class MealTest
{
  private final Ingredient ingredient1 = new Ingredient("chicken", "chicken", 1, Unit.POUND,
      IngredientEditor.NO_INPUT, IngredientEditor.NO_INPUT);
  private final Ingredient ingredient2 = new Ingredient("cheddar cheese", "chicken", .25, Unit.POUND,
      IngredientEditor.NO_INPUT, IngredientEditor.NO_INPUT);
  private final Ingredient ingredient3 = new Ingredient("honey", "chicken", 1, Unit.TEASPOON,
      IngredientEditor.NO_INPUT, IngredientEditor.NO_INPUT);

  private final Utensil spoon = new Utensil("spoon", "its a spoon");
  private final Utensil fork = new Utensil("fork", "its got prongs");
  private final Step step = new Step("cut", ingredient1, spoon, fork, "spoon scoop the chicken",
      15);
  private final List<Ingredient> ingredients = new ArrayList<Ingredient>();
  private final List<Utensil> utensils = new ArrayList<Utensil>();
  private final List<Step> steps = new ArrayList<Step>();
  private final Recipe funCookin1;
  private final Recipe funCookin2;
  private final Recipe funCookin3;
  private final List<Recipe> wholeMeal = new ArrayList<Recipe>();
  private final Meal positiveServing;
  private final Meal negativeServing;
  private final Meal nullMeal;

  public MealTest()
  {
    ingredients.add(ingredient1);
    ingredients.add(ingredient2);
    ingredients.add(ingredient3);
    utensils.add(fork);
    utensils.add(spoon);
    steps.add(step);

    funCookin1 = new LeafRecipe("funTimeChicken", 1);
    funCookin1.addAllIngredients(ingredients);
    funCookin1.addAllUtensils(utensils);
    funCookin1.addAllSteps(steps);
    
    funCookin2 = new LeafRecipe("funTimeChicken", 1);
    funCookin2.addAllIngredients(ingredients);
    funCookin2.addAllUtensils(utensils);
    funCookin2.addAllSteps(steps);
    
    funCookin3 = new LeafRecipe("funTimeChicken", 1);
    funCookin3.addAllIngredients(ingredients);
    funCookin3.addAllUtensils(utensils);
    funCookin3.addAllSteps(steps);

    wholeMeal.add(funCookin1);
    wholeMeal.add(funCookin2);
    positiveServing = new Meal("chimkinFunTimes", wholeMeal, 3);
    negativeServing = new Meal("", wholeMeal, -1);
    nullMeal = new Meal(null, null, -1);

  }

  @Test
  void test_add_remove_Size()
  {
    assertTrue(positiveServing.addRecipe(funCookin3));
    assertEquals(positiveServing.getSize(), 3);
    assertTrue(positiveServing.removeRecipe(funCookin3));
    assertEquals(nullMeal.getSize(), 0);
  }

  @Test
  void test_getters_setters()
  {
    assertTrue(positiveServing.getRecipes().equals(wholeMeal));
    assertEquals("chimkinFunTimes", positiveServing.getName());
    assertEquals("DefaultMealName", negativeServing.getName());
    positiveServing.setName("soup");
    assertEquals(positiveServing.getName(), "soup");
    positiveServing.setServing(3);
    assertEquals(positiveServing.getServing(), 3);
  }

  @Test
  void testCalculateCalories()
  {
    double funCooking1Calories = funCookin1.calculateCalories();
    double funCooking2Calories = funCookin2.calculateCalories();
    double total = funCooking1Calories + funCooking2Calories;

    assertEquals(total, positiveServing.calculateCalories());
  }

}
