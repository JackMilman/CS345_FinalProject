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
   * Returns a list with the ingredients of both this recipe and all subRecipes.
   * 
   * @return a new composite list
   */
  @Override
  public List<Ingredient> getIngredients()
  {
    List<Ingredient> compositeList = new ArrayList<Ingredient>(this.ingredients);
    for (Recipe subRecipe : subRecipes) {
      compositeList.addAll(subRecipe.getIngredients());
    }
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
    List<Utensil> compositeList = new ArrayList<Utensil>(this.utensils);
    for (Recipe subRecipe : subRecipes) {
      compositeList.addAll(subRecipe.getUtensils());
    }
    return compositeList;
  }

  @Override
  public double calculateCalories()
  {
    // TODO Auto-generated method stub
    return 0;
  }

}
