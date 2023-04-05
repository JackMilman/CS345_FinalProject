package recipes;

import java.io.Serializable;

/**
 * Object class describing a Utensil in a Recipe. A Utensil contains a name and details on itself
 * (i.e. how it is used and what it looks like).
 * 
 * @version 3/28/2023 Version 1
 * @author Jack Milman, KichIntel
 *
 */
public class Utensil implements Serializable
{
  private String name;

  private String details;

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
   * Sets the name of the Utensil.
   * 
   * @param name the name to be set
   */
  public void setName(final String name)
  {
    this.name = name;
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
   * Sets the details of the Utensil.
   * 
   * @param details the details to be set
   */
  public void setDetails(final String details)
  {
    this.details = details;
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

  /**
   * Compares two Utensils to see if they are equal. Two Utensil are considered equal when they have
   * the same name and details.
   * 
   * @param other
   *          the object being compared to this Utensil
   * @return true if the two Ingredients have the same name and details, or false if they do not
   */
  public boolean equals(final Object other)
  {
    if (other == this)
      return true;
    if (other == null)
      return false;
    if (other.getClass() != this.getClass())
      return false;

    Utensil that = (Utensil) other;
    if (this.name.equals(that.name))
      if (this.details.equals(that.details))
        return true;

    return false;
  }

  /**
   * This operation is not supported because Utensils are mutable.
   * 
   * @return does not return a value
   * @throws UnsupportedOperationException
   *           if called
   */
  public int hashCode()
  {
    throw new UnsupportedOperationException(
        "hashCode() is not supported because Utensils are mutable");
  }
  
  @Override
  public String toString()
  {
    if(details == null)
    {
      return String.format("%s", name).strip();
    }
    return String.format("%s %s", details, name).strip();
  }
}
