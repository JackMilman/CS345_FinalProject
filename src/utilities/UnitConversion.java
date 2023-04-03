package utilities;

import java.util.*;
import java.util.Map;

import recipes.NutritionInfo;

public class UnitConversion
{
  private final static Map<String, Double> massConversions = initializeMasses();
  private final static Map<String, Double> volumeConversions = initializeVolumes();
  // Special Cases
  private final static double OUNCES_TO_GRAMS = 28.34952;
  private final static double TABLESPOON_TO_MILLILITERS = 14.7867648;
  private final static double CUP_TO_MILLILITERS = 236.58824;
  private final static double FLUID_OUNCES_TO_MILLILITERS = 29.57353;

  private static Map<String, Double> initializeMasses()
  {
    Map<String, Double> map = new HashMap<String, Double>();

    map.put("DRAM", 1.0 / 16.0);
    map.put("OUNCE", 1.0); // base unit
    map.put("GRAM", 1.0 / OUNCES_TO_GRAMS);
    map.put("POUND", 16.0);

    return map;
  }

  private static Map<String, Double> initializeVolumes()
  {
    Map<String, Double> map = new HashMap<String, Double>();
    
    // Volume conversions
    map.put("PINCH", 1 / 48.0);
    map.put("TEASPOON", 1 / 3.0);
    map.put("TABLESPOON", 1.0); // base unit
    map.put("FLUID_OUNCE", 2.0);
    map.put("CUP", 16.0);
    map.put("PINT", 32.0);
    map.put("QUART", 64.0);
    map.put("GALLON", 256.0);

    return map;
  }

  public static double convert(final String name, final String from, final String to, final double amount)
  {
    if (massConversions.containsKey(from) & massConversions.containsKey(to))
    {
      return amount * (massConversions.get(from) / massConversions.get(to));
    }
    else if (volumeConversions.containsKey(from) & volumeConversions.containsKey(to))
    {
      return amount * (volumeConversions.get(from) / volumeConversions.get(to));
    }

    else if (massConversions.containsKey(from)
        & (volumeConversions.containsKey(to) | to.equals("MILLILITER")))
    {
      return mass_to_volume(name, from, to, amount);
    }
    else if ((volumeConversions.containsKey(from) | from.equals("MILLILITER"))
        & massConversions.containsKey(to))
    {
      return volume_to_mass(name, from, to, amount);
    }
    else
    {
      return milliLiterConvert(name, from, to, amount);
    }

  }

  public static double milliLiterConvert(String name, String from, String to, double amount)
  {
    // Special Cases
    if (to.equals("MILLILITER"))
    {
      if (from.equals("CUP"))
        return amount * CUP_TO_MILLILITERS;
      else if (from.equals("FLUID_OUNCE"))
        return amount * FLUID_OUNCES_TO_MILLILITERS;
      else
      {
        double tblSpoon = convert(name, from, "TABLESPOON", 1);
        return amount * (tblSpoon * TABLESPOON_TO_MILLILITERS);
      }
    }
    else
    {
      if (to.equals("CUP"))
        return amount * (1.0 / CUP_TO_MILLILITERS);
      else if (to.equals("FLUID_OUNCE"))
        return amount * (1.0 / FLUID_OUNCES_TO_MILLILITERS);
      else
      {
        double tblSpoon = convert(name, to, "TABLESPOON", 1);
        return amount * ((1 / TABLESPOON_TO_MILLILITERS) / tblSpoon);
      }
    }

  }

  public static double mass_to_volume(String name, String from, String to, double amount)
  {
    NutritionInfo ingredient = NutritionInfo.fromCode(name);
    if (ingredient != null)
    {
      double density = NutritionInfo.fromCode(name).gramPerML;
      double massVal = convert(name, from, "GRAM", amount);
      double newmass = (massVal / density);
      return newmass * convert(name, "MILLILITER", to, 1);
    }
    else
      return 0;
  }

  public static double volume_to_mass(String name, String from, String to, double amount)
  {
    NutritionInfo ingredient = NutritionInfo.fromCode(name);
    if (ingredient != null)
    {
      double density = NutritionInfo.fromCode(name).gramPerML;
      double volVal = convert(name, from, "MILLILITER", amount);
      double newvol = (density * volVal);
      return newvol * convert(name, "GRAM", to, 1);
    }
    else
      return 0;

  }
}
