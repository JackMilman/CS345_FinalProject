package recipes;

import java.util.ArrayList;
import java.util.List;

public class LeafRecipe extends Recipe
{
  private static final long serialVersionUID = 1L;

  public LeafRecipe(String name, int servings)
  {
    super(name, servings);
  }

  @Override
  public List<Ingredient> getIngredients()
  {
    return new ArrayList<Ingredient>(ingredients);
  }

  @Override
  public List<Utensil> getUtensils()
  {
    return new ArrayList<Utensil>(utensils);
  }

  @Override
  public double calculateCalories()
  {
    double calories = 0;
    for (Ingredient ingredient : ingredients)
    {
      calories += ingredient.getCaloriesPerGram();
    }
    return calories;
  }

}
