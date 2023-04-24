package recipes;

import java.io.Serializable;

/**
 * Object class describing a Step in a Recipe. A Step contains the action being performed, details
 * on how that action is being done or what to, the Ingredient the action is being performed on, a
 * source Utensil from which the Ingredient may come, and a destination Utensil into which the
 * Ingredient will go.
 * 
 * @version 3/28/2023 Version 1
 * @author Jack Milman, KichIntel
 *
 */
public class Step implements Serializable
{
  private static final long serialVersionUID = 1L;

  private String action;

  private String details;

  private Ingredient ingredient;
  private Recipe recipe;

  private Utensil source;

  private Utensil destination;

  private int time;

  /**
   * Constructs a new Step.
   * 
   * @param action
   * @param ingredient
   * @param source
   * @param destination
   * @param details
   */
  public Step(final String action, final Ingredient ingredient, final Utensil source,
      final Utensil destination, final String details, final int time)
  {
    this.action = action;
    this.ingredient = ingredient;
    this.source = source;
    this.destination = destination;
    this.details = details;
    this.time = time;
  }
  
  public Step(final String action, final Recipe recipe, final Utensil source,
      final Utensil destination, final String details, final int time)
  {
    this.action = action;
    this.recipe = recipe;
    this.source = source;
    this.destination = destination;
    this.details = details;
    this.time = time;
  }


  /**
   * Sets the details of the Step.
   * 
   * @param details
   *          the details to be set
   */
  public void setDetails(final String details)
  {
    this.details = details;
  }

  /**
   * Gets the details of the Step.
   * 
   * @return the details of the Step
   */
  public String getDetails()
  {
    return details;
  }

  /**
   * Sets the action of the Step.
   * 
   * @param action
   *          the action to be set
   */
  public void setAction(final String action)
  {
    this.action = action;
  }

  /**
   * Gets the action of the Step.
   * 
   * @return the action of the Step
   */
  public String getAction()
  {
    return action;
  }

  /**
   * Sets the Ingredient of the Step.
   * 
   * @param ingredient
   *          the Ingredient to be set
   */
  public void setIngredient(final Ingredient ingredient)
  {
    this.ingredient = ingredient;
  }

  /**
   * Gets the Ingredient of the Step.
   * 
   * @return the Ingredient of the Step
   */
  public Ingredient getIngredient()
  {
    return ingredient;
  }

  /**
   * Sets the source Utensil of the Step.
   * 
   * @param source
   *          the source Utensil to be set
   */
  
  public void setRecipe(final Recipe recipe) {
    this.recipe = recipe;
  }
  
  public Recipe getRecipe() {
   return recipe;
  }
  public void setSource(final Utensil source)
  {
    this.source = source;
  }

  /**
   * Gets the source Utensil of the Step.
   * 
   * @return the source Utensil of the Step
   */
  public Utensil getSource()
  {
    return source;
  }

  /**
   * Gets the destination Utensil of the Step.
   * 
   * @return the destination Utensil of the Step
   */
  public Utensil getDestination()
  {
    return destination;
  }

  /**
   * Sets the destination Utensil of the Step.
   * 
   * @param destination
   *          the destination Utensil to be set
   */
  public void setDestination(final Utensil destination)
  {
    this.destination = destination;
  }

  /**
   * Gets the time in minutes of the Step.
   * 
   * @return the time in minutes of the Step
   */
  public int getTime()
  {
    return time;
  }

  /**
   * Sets the time in minutes of the Step.
   * 
   * @param time
   *          the time in minutes to be set
   */
  public void setTime(final int time)
  {
    this.time = time;
  }

  @Override
  public String toString()
  {
    // if the destination is a utensil
    if (source != null)
    {
      // if the source and destination utensil are the same
      if (source.equals(destination))
      {
        return String.format("%s the contents of the %s %s\t\t%s minutes", action, source.getName(),
            details, time).strip();
      }
      // if the source and destination utensil are different
      else
      {
        return String.format("%s the contents of the %s in the %s %s\t\t%s minutes", action,
            source.getName(), destination.getName(), details, time).strip();
      }
    }
    if (recipe != null){
      return String.format("%s the *%s on the %s %s\t\t%s minutes", action, recipe.getName(),
          destination.getName(), details, time).strip();
    }
    // if the source is an ingredient
    return String.format("%s the %s on the %s %s\t\t%s minutes", action, ingredient.getName(),
        destination.getName(), details, time).strip();

  }

  public String toString(boolean verbose)
  {
    if (verbose)
    {
      return toString();
    }
    else
    {
      // if the destination is a utensil
      if (source != null)
      {
        // if the source and destination utensil are the same
        if (source.equals(destination))
        {
          return String.format("%s the contents of the %s", action, source.getName(), details)
              .strip();
        }
        // if the source and destination utensil are different
        else
        {
          return String.format("%s the contents of the %s in the %s", action, source.getName(),
              destination.getName(), details).strip();
        }
      }

      // if the source is an ingredient
      return String.format("%s the %s on the %s", action, ingredient.getName(),
          destination.getName(), details).strip();
    }
  }

}
