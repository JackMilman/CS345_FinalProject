package recipes;

import java.util.List;

/**
 * Object class describing a Recipe. A Recipe contains a name, the number of people it serves, lists
 * of the ingredients and utensils required to make it, and the steps in which those ingredients and
 * utensils are to be used to make the meal.
 * 
 * @version 3/28/2023 Version 1
 * @author Jack Milman
 *
 */
public class Recipe
{
  private final String name;

  private final int servings;

  private final List<Ingredient> ingredients;

  private final List<Utensil> utensils;

  private final List<Step> steps;

  /**
   * Constructs a new Recipe.
   * 
   * @param name
   * @param servings
   * @param ingredients
   * @param utensils
   * @param steps
   */
  public Recipe(final String name, final int servings, final List<Ingredient> ingredients,
      final List<Utensil> utensils, final List<Step> steps)
  {
    this.name = name;
    this.servings = servings;
    this.ingredients = ingredients;
    this.utensils = utensils;
    this.steps = steps;
  }
}
