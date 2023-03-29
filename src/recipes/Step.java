package recipes;

/**
 * Object class describing a Step in a Recipe. A Step contains the action being performed, details
 * on how that action is being done or what to, the Ingredient the action is being performed on, a
 * source Utensil from which the Ingredient may come, and a destination Utensil into which the
 * Ingredient will go.
 * 
 * @version 3/28/2023 Version 1
 * @author Jack Milman
 *
 */
public class Step
{
  private final  String action;

  private final  String details;

  private final  Ingredient ingredient;

  private final  Utensil source;

  private final  Utensil destination;

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
      final Utensil destination, final String details)
  {
    this.action = action;
    this.ingredient = ingredient;
    this.source = source;
    this.destination = destination;
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
   * Gets the action of the Step.
   * 
   * @return the action of the Step
   */
  public String getAction()
  {
    return action;
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

}
