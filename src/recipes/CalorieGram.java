package recipes;

/**
 * An object class used for representing nutritional information for an Ingredient. Visible only to
 * other classes within the recipes package.
 * 
 * @author Jack Milman, KichIntel
 * @version 4/12/2023 V1
 *
 */
class CalorieGram
{
  private final Double calories;
  private final Double density;

  /**
   * Creates a new CalorieGram object, containing information on the calories per gram of some
   * ingredient and the density of that ingredient in grams per milliliter.
   * 
   * <p>
   * A CalorieGram is immutable, and can have null values for either (or both) its calories and its
   * density.
   * 
   * @param calories
   * @param density
   */
  public CalorieGram(final Double calories, final Double density)
  {
    this.calories = calories;
    this.density = density;
  }

  /**
   * Returns the calories of this CalorieGram.
   * 
   * @return calories
   */
  public Double getCal()
  {
    return calories;
  }

  /**
   * Returns the density of this CalorieGram.
   * 
   * @return density
   */
  public Double getDensity()
  {
    return density;
  }
}
