package recipes;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

public class Meal
{
  private String name;
  private List<Recipe> recipes = new ArrayList<Recipe>();
  private int serving;

  public Meal(String name, List<Recipe> recipes, int serving)
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

  public boolean addRecipe(Recipe newRecipe)
  {
    return recipes.add(newRecipe);
  }

  public boolean removeRecipe(Recipe newRecipe)
  {
    return recipes.remove(newRecipe);
  }

  /*
   * Gets the list of recipes.
   * 
   * @return the list of recipes
   */
  public List<Recipe> getRecipes()
  {
    return recipes;
  }

  public String getName()
  {
    return name;
  }

  public int getServing()
  {
    return serving;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public void setServing(int serving)
  {
    this.serving = serving;
  }

  public int getSize()
  {
    return recipes.size();
  }
  public double getCaloriesPerGram() {
    double totalCalories = 0;
    for(Recipe individual: recipes) {
      totalCalories += individual.calculateCalories();
      
    }
    return totalCalories;
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
    ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName + ".mel"));

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
    ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName + ".mel"));

    Meal meal;

    try
    {
      meal = (Meal) in.readObject();
    }
    catch (ClassNotFoundException e)
    {
      meal = null;
    }

    return meal;
  }

}
