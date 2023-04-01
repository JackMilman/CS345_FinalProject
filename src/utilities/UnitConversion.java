package utilities;

import java.util.*;
import java.util.Map;

public class UnitConversion
{
  private final Map<String, Double> massConversions = new HashMap<String, Double>();
  private final Map<String, Double> volumeConversions = new HashMap<String, Double>();
  // Special Cases
  private final static double OUNCES_TO_GRAMS = 28.34952;
  private final static double TABLESPOON_TO_MILLILITERS = 14.7867648;
  private final static double CUP_TO_MILLILITERS = 236.58824;
  private final static double FLUID_OUNCES_TO_MILLILITERS = 29.57353;
  private final static double GRAMS_PER_MILLILITER = 1.04;
  

  // Mass or weight
  public UnitConversion()
  {
    massConversions.put("DRAM", 1.0 / 16.0);
    massConversions.put("OUNCE", 1.0); // base unit

    massConversions.put("GRAM", 1.0 / OUNCES_TO_GRAMS);
    massConversions.put("POUND", 16.0);

    // Volume conversions
    volumeConversions.put("PINCH", 1 / 48.0);
    volumeConversions.put("TEASPOON", 1 / 3.0);
    volumeConversions.put("TABLESPOON", 1.0); // base unit
    volumeConversions.put("FLUID_OUNCE", 2.0);
    volumeConversions.put("CUP", 16.0);
    volumeConversions.put("PINT", 32.0);
    volumeConversions.put("QUART", 64.0);
    volumeConversions.put("GALLON", 256.0);

    
  }

  public double convert(String from, String to, double amount)
  {
    if (massConversions.containsKey(from) & massConversions.containsKey(to))
    {
      return amount * (massConversions.get(from) / massConversions.get(to));
    }
    else if (volumeConversions.containsKey(from) & volumeConversions.containsKey(to))
    {
      return amount * (volumeConversions.get(from) / volumeConversions.get(to));
    }
    
    else if (massConversions.containsKey(from) & (volumeConversions.containsKey(to) | to.equals("MILLILITER")))
    {
      return mass_to_volume(from, to, amount);
      
    }
    else if ((volumeConversions.containsKey(from)| from.equals("MILLILITER") ) & massConversions.containsKey(to))
    {
      return volume_to_mass(from, to, amount);
    }
    else {
      return milliLiterConvert(from, to, amount);
    }

  }

  public double milliLiterConvert(String from, String to, double amount)
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
        double tblSpoon = convert(from, "TABLESPOON", 1);
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
        double tblSpoon = convert(to, "TABLESPOON", 1);
        return amount * ((1 / TABLESPOON_TO_MILLILITERS) / tblSpoon);
      }
    }

  }
  
  public double mass_to_volume(String from, String to, double amount) {
    double massVal = convert(from, "GRAM", amount);
    double newmass = (massVal / GRAMS_PER_MILLILITER);
    return newmass * convert("MILLILITER", to, 1);
  }
  public double volume_to_mass(String from, String to, double amount) {
    double volVal = convert(from, "MILLILITER", amount);
    double newvol = (GRAMS_PER_MILLILITER * volVal);
    return newvol * convert("GRAM", to, 1);
  }
}
