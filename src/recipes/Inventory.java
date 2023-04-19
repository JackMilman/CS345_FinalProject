package recipes;

import java.util.ArrayList;
import java.util.List;

import utilities.UnitConversion;

/**
 * A Singleton class for representing the inventory of the user.
 * 
 * @author Jack Milman, KichIntel
 * @version 4/13/2023 V1
 *
 */
public class Inventory
{
  private static boolean exists = false;
  private static Inventory instance;
  private static List<Ingredient> ingredients = new ArrayList<Ingredient>();

  private Inventory()
  {
    exists = true;
  }

  public static Inventory createInstance()
  {
    if (!exists)
    {
      instance = new Inventory();
    }

    return instance;
  }

  /**
   * Clears the entire Inventory of its elements.
   */
  public static void clear()
  {
    ingredients.clear();
  }

  /**
   * Returns the number of unique ingredients in the list of ingredients.
   * 
   * @return the size of ingredients
   */
  public int size()
  {
    return ingredients.size();
  }

  /**
   * Gets an ingredient by searching for an ingredient in the list with the same name (such as
   * "Potato") and details (such as "Peeled"). If the ingredient is not present in the inventory,
   * returns null.
   * 
   * @param name
   *                  the name of the ingredient
   * @param details
   *                  the details of the ingredient
   * @return the ingredient (from the *inventory*) that is equivalent to the ingredient being passed
   *         or null if it is not present
   */
  public Ingredient getIngredient(final String name, final String details)
  {
    for (Ingredient item : ingredients)
    {
      boolean equalName = item.getName().equals(name);
      boolean equalDetails = item.getDetails().equals(details);
      boolean equalIngredient = equalName && equalDetails;
      if (equalIngredient)
      {
        return item;
      }
    }
    return null;
  }

  /**
   * Gets an ingredient from the list of ingredients in the inventory by searching for an ingredient
   * equal to the ingredient we are searching for. Returns null if the ingredient is not there. This
   * can be used to return ingredients from the inventory that may have different units or amounts
   * than the Ingredient being passed, since equivalent Ingredients only check for name and detail
   * equivalence.
   * 
   * @param ingredient
   *                     the ingredient we are searching for
   * @return the ingredient (from the *inventory*) that is equivalent to the ingredient being passed
   *         or null if it is not present
   */
  public Ingredient getIngredient(final Ingredient ingredient)
  {
    for (Ingredient item : ingredients)
    {
      if (item.equals(ingredient))
      {
        return item;
      }
    }
    return null;
  }

  /**
   * Adds an ingredient to the list of ingredients in the inventory, without allowing for repeats.
   * In the case that the ingredient being added is already present in the inventory, then we add
   * the amount from the Ingredient being passed to the amount already present, in the unit of the
   * Ingredient already in the inventory.
   * 
   * @param addingIngredient
   *                           the ingredient we are adding to the inventory
   * @return true if the operation was a success, false otherwise
   */
  public boolean addIngredient(final Ingredient addingIngredient)
  {
    // Gets the index of the ingredient so we can avoid repetitions of the same ingredient.
    int index = ingredients.indexOf(addingIngredient);
    if (index > -1)
    {
      Ingredient presentIngredient = ingredients.get(index);
      String name = presentIngredient.getName();
      String details = presentIngredient.getDetails();
      Unit presentUnit = presentIngredient.getUnit();
      double presentAmount = presentIngredient.getAmount();

      // Get the amount and the units of the ingredient so we can convert the amount in the one
      // we're adding to that unit.
      double addingAmount = addingIngredient.getAmount();
      Unit addingUnit = addingIngredient.getUnit();

      // Convert the addingIngredient's amount to the unit already in the inventory.
      addingAmount = UnitConversion.convert(name, addingUnit, presentUnit, addingAmount);
      double endAmount = presentAmount + addingAmount;

      Ingredient convertedAndSummed = new Ingredient(name, details, endAmount, presentUnit, null,
          null);
      ingredients.set(index, convertedAndSummed);
      return true;
    }
    else
    {
      return ingredients.add(addingIngredient);
    }
  }

  /**
   * Reduces an ingredient in the list by an amount equal to the amount in the reducingIngredient
   * being passed. We first check that the ingredient exists in the list, then convert to the unit
   * of the ingredient already in the list. We then subtract the converted amount from the amount
   * already in the list, and create a new Ingredient with that updated amount. If the amount is
   * less than or equal to 0, we remove the Ingredient from the inventory.
   * 
   * @param reducingIngredient
   * @return true if the Inventory was changed as a result of this method being called
   */
  public boolean reduceIngredient(final Ingredient reducingIngredient)
  {
    for (Ingredient temp : ingredients)
    {
      if (temp.getName().equalsIgnoreCase(reducingIngredient.getName()))
      {
        double amount = temp.getAmount() - UnitConversion.convert(reducingIngredient.getName(),
            reducingIngredient.getUnit(), temp.getUnit(), reducingIngredient.getAmount());
        if (amount > 0)
        {
          Ingredient newIngredient = new Ingredient(temp.getName(), temp.getDetails(), amount,
              temp.getUnit(), temp.getCalories(), temp.getDensity());
          ingredients.add(newIngredient);
        }
        ingredients.remove(temp);
        return true;
      }

    }
    return false;
  }
  
  public List<Ingredient> getIngredientList(){
    return ingredients;
  }

}
