package recipes;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.html.parser.ParserDelegator;

import utilities.SortLists;

/**
 * Object class describing a Recipe. A Recipe contains a name, the number of people it serves, lists
 * of the ingredients and utensils required to make it, and the steps in which those ingredients and
 * utensils are to be used to make the meal.
 * 
 * @version 3/28/2023 Version 1
 * @author Jack Milman, KichIntel
 *
 */
public class Recipe implements Serializable
{
  private static final long serialVersionUID = 1L;

  private String name;

  private int servings;

  private List<Ingredient> ingredients = new ArrayList<Ingredient>();

  private List<Utensil> utensils = new ArrayList<Utensil>();

  private List<Step> steps = new ArrayList<Step>();

  private String category;

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

    if (ingredients != null)
      this.ingredients = ingredients;

    if (utensils != null)
      this.utensils = utensils;

    if (steps != null)
      this.steps = steps;
  }

  /**
   * Adds an ingredient to the list of Ingredients.
   * 
   * @param ingredient
   *          the ingredient to attempt to add
   * 
   * @return returns true if the Ingredient was successfully added
   */
  public boolean addIngredient(final Ingredient ingredient)
  {
    return ingredients.add(ingredient);
  }

  /**
   * Attempts to remove a ingredient from the list of ingredients. If the ingredient is not present,
   * returns false.
   * 
   * @param ingredient
   *          the ingredient to attempt to remove
   * 
   * @return true if the ingredient was successfully removed, else false
   */
  public boolean removeIngredient(final Ingredient ingredient)
  {
    return ingredients.remove(ingredient);
  }

  /**
   * Adds an utensil to the list of Utensils.
   * 
   * @param utensil
   *          the utensil to attempt to add
   * 
   * @return returns true if the Utensil was successfully added
   */
  public boolean addUtensil(final Utensil utensil)
  {
    return utensils.add(utensil);
  }

  /**
   * Attempts to remove a utensil from the list of utensils. If the utensil is not present, returns
   * false.
   * 
   * @param utensil
   *          the utensil to attempt to remove
   * 
   * @return true if the utensil was successfully removed, else false
   */
  public boolean removeUtensil(final Utensil utensil)
  {
    return utensils.remove(utensil);
  }

  /**
   * Attempts to add a step to the list of steps. A step cannot be added if it has an Ingredient or
   * Utensils that are not in the recipe already.
   * 
   * @param step
   *          the step to attempt to add
   * 
   * @return
   */
  public boolean addStep(final Step step)
  {
    boolean hasIngredient = ingredients.contains(step.getIngredient());
    boolean hasSource = utensils.contains(step.getSource());
    boolean hasDestination = utensils.contains(step.getDestination());

    boolean isValid = hasIngredient && hasSource && hasDestination;
    if (isValid)
    {
      return steps.add(step);
    }

    return false;
  }

  /**
   * Attempts to remove a step from the list of steps. If the step is not present, returns false.
   * 
   * @param step
   *          the step to attempt to remove
   * 
   * @return true if the step was successfully removed, else false
   */
  public boolean removeStep(final Step step)
  {
    return steps.remove(step);
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
    // Removed the call to Sort the Ingredients List. Lists should be sorted outside of this class
    // -Jack 3/30
    return ingredients;
  }

  /**
   * Gets the Utensils used in the Recipe.
   * 
   * @return the Utensils used in the Recipe.
   */
  public List<Utensil> getUtensils()
  {
    // Removed the call to Sort the utensils List. Lists should be sorted outside of this class
    // -Jack 3/30
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

  /**
   * Calculates the total number of calories in the Recipe by totaling each Ingredient's calorie
   * count.
   * 
   * @return the  total number of calories in the Recipe
   */
  public double calculateCalories()
  {
    double calories = 0;
    for (Ingredient ingredient : ingredients)
    {
      calories += ingredient.getCaloriesPerGram();
    }
    return calories;
  }

  /**
   * Serializes this recipe into a file name filename.rcp.
   * 
   * @param fileName
   *          The name of the file to write this to, not including the .rcp extension.
   * @throws IOException
   *           if any exception occurs during the writing process.
   */
  public void write(final String fileName) throws IOException
  {
    ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName + ".rcp"));

    out.writeObject(this);
    out.flush();
    out.close();
  }

  /**
   * Attempts to read a Recipe from the given file.
   * 
   * @param fileName
   *          The name of the file to read from, should not include the .rcp extension.
   * @return The recipe from the file.
   * @throws IOException
   *           if an IO error occurs.
   * @throws ClassNotFoundException
   *           if the object from the file is not a Recipe
   */
  public static Recipe read(final String fileName) throws IOException
  {
    ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName + ".rcp"));

    Recipe recipe;

    try
    {
      recipe = (Recipe) in.readObject();
    }
    catch (ClassNotFoundException e)
    {
      recipe = null;
    }

    return recipe;
  }
}
