package utilities;

import java.util.List;

import recipes.Ingredient;
import recipes.Utensil;

/**
 * 
 * @author shelseyvega
 *
 */
public class SortLists
{
  /**
   * 
   * @param ingredients
   */
  public static void sortIngredients(final List<Ingredient> ingredients)
  {
    for (int i = 1; i < ingredients.size(); ++i)
    {
      Ingredient itemToShift = ingredients.get(i);
      int j;
      for (j = i; j > 0
          && itemToShift.getName().compareTo(ingredients.get(j - 1).getName()) < 0; --j)
      {
        ingredients.set(j, ingredients.get(j - 1));
      }
      ingredients.set(j, itemToShift);
    }
  }

  /**
   * 
   * @param utensils
   */
  public static void sortUtensils(final List<Utensil> utensils)
  {
    for (int i = 1; i < utensils.size(); ++i)
    {
      Utensil itemToShift = utensils.get(i);
      int j;
      for (j = i; j > 0 && itemToShift.getName().compareTo(utensils.get(j - 1).getName()) < 0; --j)
      {
        utensils.set(j, utensils.get(j - 1));
      }
      utensils.set(j, itemToShift);
    }
  }

}
