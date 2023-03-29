package recipes;

/**
 * Object class describing an Ingredient in a Recipe. An Ingredient contains a name, details on
 * itself (i.e. what it looks like or how it smells), the amount of that Ingredient required, and
 * the unit that amount is being measured in.
 * 
 * @version 3/28/2023 Version 1
 * @author Jack Milman
 *
 */
public class Ingredient
{

  private final String name;

  private final String details;

  private final int amount;

  private final String unit;

  /**
   * Constructs a new Ingredient.
   * 
   * @param name
   * @param details
   * @param amount
   * @param unit
   */
  public Ingredient(final String name, final String details, final int amount, final String unit)
  {
    this.name = name;
    this.details = details;
    this.amount = amount;
    this.unit = unit;
  }

  /**
   * Gets the name of the Ingredient.
   * 
   * @return the name of the Ingredient
   */
  public String getName()
  {
    return name;
  }

  /**
   * Gets the details of the Ingredient.
   * 
   * @return the details of the Ingredient
   */
  public String getDetails()
  {
    return details;
  }

  /**
   * Gets the amount of the Ingredient.
   * 
   * @return the amount of the Ingredient
   */
  public int getAmount()
  {
    return amount;
  }

  /**
   * Gets the unit of the Ingredient.
   * 
   * @return the unit of the Ingredient
   */
  public String getUnit()
  {
    return unit;
  }

}
