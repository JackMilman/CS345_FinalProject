package recipes;

import java.util.List;

/**
 * Object class describing a Recipe. A Recipe contains a name, the number of people it serves, lists
 * of the ingredients and utensils required to make it, and the steps in which those ingredients and
 * utensils are to be used to make the meal.
 * 
 * @version 3/28/2023 Version 1
 * @author Jack Milman, KichIntel
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

  /**
   * Gets the name of the Recipe.
   * 
   * @return the name of the Recipe.
   */
  public String getName()
  {
    return name;
  }

  /**
   * Gets the number of servings of the Recipe.
   * 
   * @return the number of servings of the Recipe.
   */
  public int getServings()
  {
    return servings;
  }

  /**
   * Gets the Ingredients used in the Recipe.
   * 
   * @return the Ingredients used in the Recipe.
   */
  public List<Ingredient> getIngredients()
  {
    return ingredients;
  }

  /**
   * Gets the Utensils used in the Recipe.
   * 
   * @return the Utensils used in the Recipe.
   */
  public List<Utensil> getUtensils()
  {
    return utensils;
  }

  /**
   * Gets the Steps to follow in order to make the Recipe.
   * 
   * @return the Steps to follow in order to make the Recipe.
   */
  public List<Step> getSteps()
  {
    return steps;
  }
}
