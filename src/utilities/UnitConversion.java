package utilities;

import java.util.*;
import java.util.Map;

import recipes.NutritionInfo;

/**
 * Utility class for converting between units and between volumes and masses.
 * 
 * @version 4/3/2023 Version 2
 * @author Alex Pegram, Jack Milman, KichIntel
 *
 */
public class UnitConversion
{
  private final static Map<String, Double> massConversions = initializeMasses();
  private final static Map<String, Double> volumeConversions = initializeVolumes();
  // Special Cases
  private final static double OUNCES_TO_GRAMS = 28.34952;
  private final static double TABLESPOON_TO_MILLILITERS = 14.7867648;

  /*
   * Initializes the map of masses.
   */
  private static Map<String, Double> initializeMasses()
  {
    Map<String, Double> map = new HashMap<String, Double>();

    map.put("DRAM", 1.0 / 16.0);
    map.put("OUNCE", 1.0); // base unit
    map.put("GRAM", 1.0 / OUNCES_TO_GRAMS);
    map.put("POUND", 16.0);

    return map;
  }

  /*
   * Initializes the map of volumes.
   */
  private static Map<String, Double> initializeVolumes()
  {
    Map<String, Double> map = new HashMap<String, Double>();

    // Volume conversions
    map.put("PINCH", 1 / 48.0);
    map.put("MILLILITER", 1 / TABLESPOON_TO_MILLILITERS);
    map.put("TEASPOON", 1 / 3.0);
    map.put("TABLESPOON", 1.0); // base unit
    map.put("FLUID OUNCE", 2.0);
    map.put("CUP", 16.0);
    map.put("PINT", 32.0);
    map.put("QUART", 64.0);
    map.put("GALLON", 256.0);

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
  public static double convert(final String name, final String from, final String to,
      final double amount)
  {
    boolean massToMass = massConversions.containsKey(from) && massConversions.containsKey(to);
    boolean volumeToVolume = volumeConversions.containsKey(from)
        && volumeConversions.containsKey(to);
    boolean massToVolume = massConversions.containsKey(from) && volumeConversions.containsKey(to);
    boolean volumeToMass = volumeConversions.containsKey(from) && massConversions.containsKey(to);

    if (massToMass)
    {
      return amount * (massConversions.get(from) / massConversions.get(to));
    }
    else if (volumeToVolume)
    {
      return amount * (volumeConversions.get(from) / volumeConversions.get(to));
    }

    else if (massToVolume)
    {
      return mass_to_volume(name, from, to, amount);
    }
    else if (volumeToMass)
    {
      return volume_to_mass(name, from, to, amount);
    }
    else
    {
      return amount;
    }

  }

  private static double mass_to_volume(String name, String from, String to, double amount)
  {
    NutritionInfo ingredient = NutritionInfo.fromCode(name);
    if (ingredient != null)
    {
      double gramsPerMilliliter = NutritionInfo.fromCode(name).gramPerML;
      double massInGrams = convert(name, from, "GRAM", amount);
      double volume = (massInGrams / gramsPerMilliliter);
      return volume;
    }
    else
      return 0;
  }

  private static double volume_to_mass(String name, String from, String to, double amount)
  {
    NutritionInfo ingredient = NutritionInfo.fromCode(name);
    if (ingredient != null)
    {
      double gramsPerMilliliter = NutritionInfo.fromCode(name).gramPerML;
      double volumeInMilliliters = convert(name, from, "MILLILITER", amount);
      double mass = (gramsPerMilliliter * volumeInMilliliters);
      return convert(name, "GRAM", to, mass);
    }
    else
      return 0;

  }
}
