package utilities;
import java.util.*;
import java.util.Map;

public class UnitConversion
{
  private final Map<String, Double> massConversions = new HashMap<String, Double>();
  private final Map<String, Double> volumeConversions = new HashMap<String, Double>();
  //Special Cases
  public final static double OUNCES_TO_GRAMS = 28.34952;
  public final static double TABLESPOON_TO_MILLILITERS = 14.7867648;
  public final static double CUP_TO_MILLILITERS = 236.58824;
  public final static double FLUID_OUNCES_TO_MILLILITERS = 29.57353;

  // Mass or weight
  public UnitConversion()
  {
    massConversions.put("DRAMS", 1.0 / 16.0);
    massConversions.put("OUNCES", 1.0); // base unit
    massConversions.put("GRAMS", 1.0 / OUNCES_TO_GRAMS);
    massConversions.put("POUND", 16.0);

    // Volume conversions
    volumeConversions.put("PINCHES", 1 / 16.0);
    volumeConversions.put("TEASPOON", 1.0); // base unit
    volumeConversions.put("TABLESPOONS", 1 / 3.0);
    volumeConversions.put("FLUID_OUNCES", 1 / 6.0);
    volumeConversions.put("CUPS", 1.0 / 48.0);
    volumeConversions.put("PINTS", 1.0 / 96.0);
    volumeConversions.put("QUARTS", 1.0 / 192.0);
    volumeConversions.put("GALLONS", 1.0 / 768.0);

  }

  public double convert(String from, String to, double amount)
  {
    if (massConversions.containsKey(from) && massConversions.containsKey(to))
    {
      return amount * (massConversions.get(from) / massConversions.get(to));
    }
    else if (to.equals("MILLILITERS"))
    {
      if (from.equals("CUP"))
        return amount * CUP_TO_MILLILITERS;
      else if (from.equals("FLUID_OUNCES"))
        return amount * FLUID_OUNCES_TO_MILLILITERS;
      else
      {
        double tblSpoon = convert(from, "TABLESPOON", 1);
        return amount * (tblSpoon * TABLESPOON_TO_MILLILITERS);
      }
    }
    else if (from.equals("MILLILITERS"))
    {
      if (from.equals("CUP"))
        return amount * (1/ CUP_TO_MILLILITERS);
      else if (from.equals("FLUID_OUNCES"))
        return amount * (1 / FLUID_OUNCES_TO_MILLILITERS);
      else {
        double tblSpoon = convert(from, "TABLESPOON", 1);
        return amount * (tblSpoon * (1 / TABLESPOON_TO_MILLILITERS));
      }
    }
    else if (volumeConversions.containsKey(from) && volumeConversions.containsKey(to))
    {
      return amount * (volumeConversions.get(from) / volumeConversions.get(to));
    }
    else
    {
      return 0.0;
    }

  }
}
