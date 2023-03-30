package utilities;

import java.util.HashMap;
import java.util.Map;

public class UnitConversion
{
  private Map<String, Double> massConversions = new HashMap<>();
  private Map<String, Double> volumeConversions = new HashMap<>();
  private final double OUNCES_TO_GRAMS = 28.34952;

  public UnitConversion() {
      // Mass or weight conversions
      massConversions.put("DRAMS", 1 / 16.0);
      massConversions.put("GRAMS", 1 / OUNCES_TO_GRAMS);
      massConversions.put("OUNCES", 1.0);
      massConversions.put("POUNDS", 16.0);

      // Volume conversions
      volumeConversions.put("PINCHES", 1 / 16.0);
      volumeConversions.put("TEASPOONS", 1.0);
      volumeConversions.put("TABLESPOONS", 3.0);
      volumeConversions.put("FLUID_OUNCES", 2.0);
      volumeConversions.put("CUPS", 8.0);
      volumeConversions.put("PINTS", 2.0);
      volumeConversions.put("QUARTS", 2.0);
      volumeConversions.put("GALLONS", 4.0);
      volumeConversions.put("MILLILITERS", 1.0 / 29.57353);
  }

  public double conversion(String from, String to, double amount) {
      if (massConversions.containsKey(from.toUpperCase()) && massConversions.containsKey(to.toUpperCase())) {
          // Mass or weight conversion
          double factor = massConversions.get(from.toUpperCase()) / massConversions.get(to.toUpperCase());
          return amount * factor;
      } else if (volumeConversions.containsKey(from.toUpperCase()) && volumeConversions.containsKey(to.toUpperCase())) {
          // Volume conversion
          double factor = volumeConversions.get(from.toUpperCase()) / volumeConversions.get(to.toUpperCase());
          return amount * factor;
      } else {
          // Unsupported conversion
          return 0;
      }
  }
}
