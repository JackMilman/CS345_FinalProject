package recipes;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A meal is made up of a collections of recipes.
 * 
 * @author KitchIntel
 * @version 
 */
public class Meal implements Serializable
{
  private static final long serialVersionUID = 1L;
  private static final String FILEEXT = ".mel";
  private String name;
  private List<Recipe> recipes = new ArrayList<Recipe>();
  private int serving;

  /**
   * Create a meal.
   * 
   * @param name
   * @param recipes
   * @param serving
   */
  public Meal(final String name, final List<Recipe> recipes, final int serving)
  {
    if (recipes != null)
      this.recipes = recipes;

    if (name == null || name.equals(""))
    {
      this.name = "DefaultMealName";
    }
    else
    {
      this.name = name;
    }

    if (serving < 1)
    {
      this.serving = 1;
    }
    else
    {
      this.serving = serving;
    }
  }

  /**
   * Add a recipe to the meal.
   * 
   * @param newRecipe
   * @return true if successfully added
   */
  public boolean addRecipe(final Recipe newRecipe)
  {
    return recipes.add(newRecipe);
  }

  /**
   * Remove a recipe from the meal.
   * 
   * @param newRecipe
   * @return true if successfully removed
   */
  public boolean removeRecipe(final Recipe newRecipe)
  {
    return recipes.remove(newRecipe);
  }

  /**
   * Gets the list of recipes.
   * 
   * @return the list of recipes
   */
  public List<Recipe> getRecipes()
  {
    return recipes;
  }

  /**
   * Get the name of the meal.
   * 
   * @return name
   */
  public String getName()
  {
    return name;
  }

  /**
   * Get the number of servings of the meal.
   * 
   * @return serving
   */
  public int getServing()
  {
    return serving;
  }

  /**
   * Change the name of the meal.
   * 
   * @param name
   */
  public void setName(final String name)
  {
    this.name = name;
  }

  /**
   * Change the number of servings of the meal.
   * 
   * @param serving
   */
  public void setServing(final int serving)
  {
    this.serving = serving;
  }

  /**
   * Get the number of recipes in the meal.
   * 
   * @return number of recipes
   */
  public int getSize()
  {
    return recipes.size();
  }
  
  /**
   * Calculates the total number of calories in the meal by totaling each recipe's calorie count.
   * 
   * @return the total number of calories in the meal
   */
  public double calculateCalories()
  {
    double calories = 0;
    for (Recipe recipe : recipes)
    {
      calories += recipe.calculateCalories();
    }
    return calories;
  }

  /**
   * Serializes this meal into a file name filename.mel.
   * 
   * @param fileName
   *          The name of the file to write this to, not including the .mel extension.
   * @throws IOException
   *           if any exception occurs during the writing process.
   */
  public void write(final String fileName) throws IOException
  {
    ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName + FILEEXT));

    out.writeObject(this);
    out.flush();
    out.close();
  }

  /**
   * Attempts to read a Meal from the given file.
   * 
   * @param fileName
   *          The name of the file to read from, should not include the .mel extension.
   * @return The recipe from the file.
   * @throws IOException
   *           if an IO error occurs.
   * @throws ClassNotFoundException
   *           if the object from the file is not a Meal
   */
  public static Meal read(final String fileName) throws IOException
  {
    ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName + FILEEXT));

    Meal meal;

    try
    {
      meal = (Meal) in.readObject();
    }
    catch (ClassNotFoundException e)
    {
      meal = null;
    }

    in.close();

    return meal;
  }

}
