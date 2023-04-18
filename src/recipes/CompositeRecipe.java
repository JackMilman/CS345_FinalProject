package recipes;

import java.util.ArrayList;
import java.util.List;

public class CompositeRecipe extends Recipe
{
  private static final long serialVersionUID = 1L;
  private List<Recipe> subRecipes = new ArrayList<Recipe>();

  public CompositeRecipe(final String name, final int servings)
  {
    super(name, servings);
  }

  /**
   * Adds a new recipe to the list of this recipe's sub recipes.
   * 
   * @param recipe
   * @return true if the list was changed as a result of this operation
   */
  public boolean addRecipe(final Recipe recipe)
  {
    return subRecipes.add(recipe);
  }

  /**
   * Removes recipe from the list of this recipe's sub recipes.
   * 
   * @param recipe
   * @return true if the list was changed as a result of this operation
   */
  public boolean removeRecipe(final Recipe recipe)
  {
    return subRecipes.remove(recipe);
  }

  /**
   * Gets a copy of the list of subRecipes.
   * 
   * @return
   */
  public List<Recipe> getSubRecipes()
  {
    return new ArrayList<Recipe>(subRecipes);
  }

  /**
   * Returns a list with the ingredients of both this recipe and all subRecipes.
   * 
   * @return a new composite list
   */
  @Override
  public List<Ingredient> getIngredients()
  {
    List<Ingredient> compositeList = new ArrayList<Ingredient>();
    for (Recipe subRecipe : subRecipes)
    {
      compositeList.addAll(subRecipe.getIngredients());
    }
    compositeList.addAll(ingredients);
    return compositeList;
  }

  /**
   * Returns a list with the utensils of both this recipe and all subRecipes.
   * 
   * @return a new composite list
   */
  @Override
  public List<Utensil> getUtensils()
  {
    List<Utensil> compositeList = new ArrayList<Utensil>();
    for (Recipe subRecipe : subRecipes)
    {
      compositeList.addAll(subRecipe.getUtensils());
    }
    compositeList.addAll(utensils);
    return compositeList;
  }

  @Override
  public double calculateCalories()
  {
    double result = 0;
    for (Ingredient ingredient : ingredients)
    {
      result += ingredient.getCaloriesPerGram();
    }

    for (Recipe recipe : subRecipes)
    {
      result += recipe.calculateCalories();
    }

    return result;
  }

}
