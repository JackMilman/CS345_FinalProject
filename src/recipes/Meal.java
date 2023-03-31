package recipes;

import java.util.*;

public class Meal
{
  private String name;
  private List<Recipe> recipes;
  private int serving;

  public Meal(String name, List<Recipe> recipes, int serving)
  {
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
  
  public boolean addRecipe(Recipe newRecipe) {
    return recipes.add(newRecipe);
  }
  
  public boolean removeRecipe(Recipe newRecipe) {
    return recipes.remove(newRecipe);
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
  public int getSize() {
    return recipes.size();
  }

}
