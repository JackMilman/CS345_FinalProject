package recipes;

/**
 * An object class used for representing nutritional information for an Ingredient.
 * 
 * @author Jack Milman, KichIntel
 * @version 4/12/2023 V1
 *
 */
public class CalorieGram
{
    private final Double calories;
    private final Double density;

    public CalorieGram(final Double calories, final Double density)
    {
      this.calories = calories;
      this.density = density;
    }
    
    public Double getCal() {
      return calories;
    }
    
    public Double getDensity() {
      return density;
    }
}
