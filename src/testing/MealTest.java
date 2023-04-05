package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import recipes.Ingredient;
import recipes.Meal;
import recipes.Recipe;
import recipes.Step;
import recipes.Utensil;

class MealTest
{
  Ingredient ingredient1 = new Ingredient("chicken", "chicken", 1, "Lbs");
  Ingredient ingredient2 = new Ingredient("cheddar cheese", "chicken", .25, "Lbs");
  Ingredient ingredient3 = new Ingredient("honey", "chicken", 1, "Tsp");
  
  Utensil spoon = new Utensil("spoon", "its a spoon");
  Utensil fork = new Utensil("fork", "its got prongs");
  Step step = new Step("cut", ingredient1, spoon, fork, "spoon scoop the chicken", 15);
  List<Ingredient> ingredients = new ArrayList<Ingredient>();
  List<Utensil> utensils = new ArrayList<Utensil>();
  List<Step> steps = new ArrayList<Step>();
  Recipe funCookin1;
  Recipe funCookin2;
  Recipe funCookin3;
  List<Recipe> wholeMeal = new ArrayList<Recipe>();
  Meal positiveServing;
  Meal negativeServing;
  Meal nullMeal;
  public MealTest() {
  ingredients.add(ingredient1);
  ingredients.add(ingredient2);
  ingredients.add(ingredient3);
  utensils.add(fork);
  utensils.add(spoon);
  steps.add(step);
  
  funCookin1 = new Recipe("funTimeChicken", 1, ingredients, utensils, steps);
  funCookin2 = new Recipe("funTimeChicken", 1, ingredients, utensils, steps);
  funCookin3 = new Recipe("funTimeChicken", 1, ingredients, utensils, steps);
  
  wholeMeal.add(funCookin1);
  wholeMeal.add(funCookin2);
  positiveServing = new Meal("chimkinFunTimes", wholeMeal, 3);
  negativeServing = new Meal("", wholeMeal, -1);
  nullMeal= new Meal(null, null, -1);
  
  
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
  void test_getters_setters() {
    assertTrue(positiveServing.getRecipes().equals(wholeMeal));
    assertEquals("chimkinFunTimes", positiveServing.getName());
    assertEquals("DefaultMealName", negativeServing.getName());
    positiveServing.setName("soup");
    assertEquals(positiveServing.getName(), "soup");
    positiveServing.setServing(3);
    assertEquals(positiveServing.getServing(), 3);
  }
  
  @Test
  void testCalculateCalories() {
	  double funCooking1Calories = funCookin1.calculateCalories();
	  double funCooking2Calories = funCookin2.calculateCalories();
	  double total = funCooking1Calories + funCooking2Calories;
	  
	  assertEquals(total, positiveServing.calculateCalories());
  }

}
