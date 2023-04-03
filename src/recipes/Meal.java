package recipes;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
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

  public static Meal read(String name2)
  {
    // TODO Auto-generated method stub
    return null;
  }

}
