package recipes;

import java.util.ArrayList;
import java.util.List;

public class SortLists
{
  public static void sortIngredients(List<Ingredient> ingredients)
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

  public static void sortUtensils(List<Utensil> utensils)
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
