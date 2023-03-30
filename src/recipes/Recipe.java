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
  private String name;

  private int servings;

  private final List<Ingredient> ingredients;

  private final List<Utensil> utensils;

  private final List<Step> steps;

  /**
   * Constructs a new Recipe. The name of a recipe may not be null and must be at least 1 character
   * long. The serving size must be greater than 0, and if a non-positive number is passed the
   * default number of servings is 1.
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
    if (name == null || name.equals(""))
    {
      this.name = "DefaultRecipeName";
    }
    else
    {
      this.name = name;
    }

    if (servings < 1)
    {
      this.servings = 1;
    }
    else
    {
      this.servings = servings;
    }

    this.ingredients = ingredients;
    this.utensils = utensils;
    this.steps = steps;
  }

  // NOT YET IMPLEMENTED
  /**
   * 
   * @param ingredient
   */
  public void addIngredient(final Ingredient ingredient)
  {
    ingredients.add(ingredient);
  }

  // NOT YET IMPLEMENTED
  /**
   * 
   */
  public void removeIngredient()
  {

  }

  // NOT YET IMPLEMENTED
  /**
   * 
   */
  public void addUtensil()
  {

  }

  // NOT YET IMPLEMENTED
  /**
   * 
   */
  public void removeUtensil()
  {

  }

  // NOT YET IMPLEMENTED
  /**
   * 
   */
  public void addStep()
  {
	  
  }

  // NOT YET IMPLEMENTED
  /**
   * 
   */
  public void removeStep()
  {

  }

  /**
   * Sets the name of the Recipe.
   * 
   * @param name
   *          the name to be set
   */
  public void setName(final String name)
  {
    this.name = name;
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
   * Sets the number of servings of the Recipe.
   * 
   * @param servings
   *          the servings to be set
   */
  public void setServings(final int servings)
  {
    this.servings = servings;
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
    SortLists.sortIngredients(ingredients);
    return ingredients;
  }

  /**
   * Gets the Utensils used in the Recipe.
   * 
   * @return the Utensils used in the Recipe.
   */
  public List<Utensil> getUtensils()
  {
    SortLists.sortUtensils(utensils);
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
