package recipes;

/**
 * Standardizes the representation of units.
 * 
 * @author Meara Patterson, KitchIntel
 * @version 4/17/2023
 */
public enum Unit
{
  
  NONE(""), DRAM("Dram"), OUNCE("Ounce"), GRAM("Gram"), POUND("Pound"), PINCH("Pinch"), 
  TEASPOON("Teaspoon"), TABLESPOON("Tablespoon"), FLUID_OUNCE("Fluid Ounce"), CUP("Cup"), 
  PINT("Pint"), QUART("Quart"), GALLON("Gallon"), INDIVIDUAL("Individual"), 
  MILLILITER("Milliliter"), LITER("Liter");
  
  private final String name;
  
  Unit(final String name)
  {
    this.name = name;
  }
  
  /**
   * Return the name of a unit.
   * 
   * @return name
   */
  public String getName()
  {
    return this.name;
  }
  
  /**
   * Give the unit that matches the given name.
   * 
   * @param unit
   * @return unit
   */
  public static Unit parseUnit(final String unit)
  {
    for (Unit item: Unit.values())
    {
      if (unit.equalsIgnoreCase(item.getName()))
      {
        return item;
      }
    }
    return null;
  }
  
}
