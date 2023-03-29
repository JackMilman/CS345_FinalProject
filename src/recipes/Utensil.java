package recipes;

/**
 * Object class describing a Utensil in a Recipe. A Utensil contains a name and details on itself
 * (i.e. how it is used and what it looks like).
 * 
 * @version 3/28/2023 Version 1
 * @author Jack Milman, KichIntel
 *
 */
public class Utensil
{
  private final String name;

  private final String details;

  /**
   * Constructs a new Utensil.
   * 
   * @param name
   * @param details
   */
  public Utensil(final String name, final String details)
  {
    this.name = name;
    this.details = details;
  }
  
  /**
   * Gets the name of the Utensil.
   * 
   * @return the name of the Utensil
   */
  public String getName()
  {
    return name;
  }

  /**
   * Gets the details of the Utensil.
   * 
   * @return the details of the Utensil
   */
  public String getDetails()
  {
    return details;
  }
}
