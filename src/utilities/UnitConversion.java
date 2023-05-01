package utilities;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import recipes.NutritionInfo;
import recipes.Unit;

/**
 * Utility class for converting between units and between volumes and masses.
 * 
 * @version 4/3/2023 Version 2
 * @author Alex Pegram, Jack Milman, KichIntel
 *
 */
public class UnitConversion
{
  private static final Map<Unit, Double> MASS_CONVERSIONS = initializeMasses();
  private static final Map<Unit, Double> VOLUME_CONVERSIONS = initializeVolumes();
  // Special Cases
  private static final double OUNCES_TO_GRAMS = 28.34952;
  private static final double TABLESPOON_TO_MILLILITERS = 14.7867648;

  /*
   * Initializes the map of masses.
   */
  private static Map<Unit, Double> initializeMasses()
  {
    Map<Unit, Double> map = new HashMap<Unit, Double>();

    map.put(Unit.DRAM, 1.0 / 16.0);
    map.put(Unit.OUNCE, 1.0); // base unit
    map.put(Unit.GRAM, 1.0 / OUNCES_TO_GRAMS);
    map.put(Unit.POUND, 16.0);

    return map;
  }

  /*
   * Initializes the map of volumes.
   */
  private static Map<Unit, Double> initializeVolumes()
  {
    Map<Unit, Double> map = new HashMap<Unit, Double>();

    // Volume conversions
    map.put(Unit.PINCH, 1 / 48.0);
    map.put(Unit.MILLILITER, 1 / TABLESPOON_TO_MILLILITERS);
    map.put(Unit.TEASPOON, 1 / 3.0);
    map.put(Unit.TABLESPOON, 1.0); // base unit
    map.put(Unit.FLUID_OUNCE, 2.0);
    map.put(Unit.CUP, 16.0);
    map.put(Unit.PINT, 32.0);
    map.put(Unit.QUART, 64.0);
    map.put(Unit.GALLON, 256.0);

    return map;
  }

  /**
   * Converts an amount from one unit to another unit. Takes an Ingredient's name for use when
   * converting from a mass to a volume in order to retrieve the g/ml of that item from
   * NutritionInfo, if it is present. Returns the unchanged amount if either unit does not exist
   * within the preset accepted units.
   * 
   * @param name
   *          the name of the Ingredient
   * @param from
   *          the unit the amount is already measured in
   * @param to
   *          the unit we want to measure the amount in
   * @param amount
   *          the amount of the Ingredient in whatever unit it is currently measured
   * @return the converted
   */
  public static double convert(final String name, final Unit from, final Unit to,
      final double amount)
  {
    boolean massToMass = MASS_CONVERSIONS.containsKey(from) && MASS_CONVERSIONS.containsKey(to);
    boolean volumeToVolume = VOLUME_CONVERSIONS.containsKey(from)
        && VOLUME_CONVERSIONS.containsKey(to);
    boolean massToVolume = MASS_CONVERSIONS.containsKey(from) && VOLUME_CONVERSIONS.containsKey(to);
    boolean volumeToMass = VOLUME_CONVERSIONS.containsKey(from) && MASS_CONVERSIONS.containsKey(to);
    double result;
    
    if (massToMass)
    {
      result = amount * (MASS_CONVERSIONS.get(from) / MASS_CONVERSIONS.get(to));
    }
    else if (volumeToVolume)
    {
      result = amount * (VOLUME_CONVERSIONS.get(from) / VOLUME_CONVERSIONS.get(to));
    }

    else if (massToVolume)
    {
      result = massToVolume(name, from, to, amount);
    }
    else if (volumeToMass)
    {
      result = volumeToMass(name, from, to, amount);
    }
    else
    {
      result = amount;
    }
    return result;

  }

  private static double massToVolume(final String name, final Unit from, 
      final Unit to, final double amount)
  {
    if (NutritionInfo.contains(name.toLowerCase()))
    {
      double gramsPerMilliliter = NutritionInfo.getGramPerML(name.toLowerCase());
      double massInGrams = convert(name, from, Unit.GRAM, amount);
      double volume = (massInGrams / gramsPerMilliliter);
      return volume;
    }
    else
      return 0;
  }

  private static double volumeToMass(final String name, final Unit from, 
      final Unit to, final double amount)
  {
    if (NutritionInfo.contains(name.toLowerCase()))
    {
      double gramsPerMilliliter = NutritionInfo.getGramPerML(name.toLowerCase());
      double volumeInMilliliters = convert(name, from, Unit.MILLILITER, amount);
      double mass = (gramsPerMilliliter * volumeInMilliliters);
      return convert(name, Unit.GRAM, to, mass);
    }
    else
      return 0;

  }

  /**
   * Check if a unit is a mass.
   * 
   * @param unit to check
   * @return true if mass, false otherwise
   */
  public static boolean isMass(final Unit unit)
  {
    return MASS_CONVERSIONS.containsKey(unit);
  }

  /**
   * Check if a unit is a volume.
   * 
   * @param unit to check
   * @return true if volume, false otherwise
   */
  public static boolean isVolume(final Unit unit)
  {
    return VOLUME_CONVERSIONS.containsKey(unit);
  }
}
