package recipes;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Utility Class for maintaining a map of ingredients and nutritional information for those
 * ingredients.
 * 
 * @author Jack Milman, KitchIntel
 * @version 4/14/2023 V2
 *
 */
public class NutritionInfo
{
  private static final Map<String, CalorieGram> NUTRITION_MAP = initializeNutrition();

  private static Map<String, CalorieGram> initializeNutrition()
  {
    Map<String, CalorieGram> map = new HashMap<String, CalorieGram>();
    map.put(StandardIngredient.ALCOHOL.getName(), new CalorieGram(
        StandardIngredient.ALCOHOL.getPricePerTablespoon(), 2.75, 0.79));
    map.put(StandardIngredient.ALMOND.getName(), new CalorieGram(
        StandardIngredient.ALMOND.getPricePerTablespoon(), 6.01, 0.46));
    map.put(StandardIngredient.AMERICAN_CHEESE.getName(), new CalorieGram(
        StandardIngredient.AMERICAN_CHEESE.getPricePerTablespoon(), 4.40, 0.34));
    map.put(StandardIngredient.APPLE.getName(), new CalorieGram(
        StandardIngredient.APPLE.getPricePerTablespoon(), 0.44, 0.56));
    map.put(StandardIngredient.APPLE_JUICE.getName(), new CalorieGram(
        StandardIngredient.APPLE_JUICE.getPricePerTablespoon(), 0.48, 1.04));
    map.put(StandardIngredient.BANANA.getName(), new CalorieGram(
        StandardIngredient.BANANA.getPricePerTablespoon(), 0.65, 0.56));
    map.put(StandardIngredient.BEAN.getName(), new CalorieGram(
        StandardIngredient.BEAN.getPricePerTablespoon(), 1.30, 0.77));
    map.put(StandardIngredient.BEEF.getName(), new CalorieGram(
        StandardIngredient.BEEF.getPricePerTablespoon(), 2.80, 1.05));
    map.put(StandardIngredient.BLACKBERRY.getName(), new CalorieGram(
        StandardIngredient.BLACKBERRY.getPricePerTablespoon(), 0.25, 0.53));
    map.put(StandardIngredient.BLACK_PEPPER.getName(), new CalorieGram(
        StandardIngredient.BLACK_PEPPER.getPricePerTablespoon(), 2.55, 1.01));
    map.put(StandardIngredient.BREAD.getName(), new CalorieGram(
        StandardIngredient.BREAD.getPricePerTablespoon(), 2.40, 0.42));
    map.put(StandardIngredient.BROCCOLI.getName(), new CalorieGram(
        StandardIngredient.BROCCOLI.getPricePerTablespoon(), 0.32, 0.37));
    map.put(StandardIngredient.BROWN_SUGAR.getName(), new CalorieGram(
        StandardIngredient.BROWN_SUGAR.getPricePerTablespoon(), 3.80, 1.5));
    map.put(StandardIngredient.BUTTER.getName(), new CalorieGram(
        StandardIngredient.BUTTER.getPricePerTablespoon(), 7.50, 0.91));
    map.put(StandardIngredient.CABBAGE.getName(), new CalorieGram(
        StandardIngredient.CABBAGE.getPricePerTablespoon(), 0.28, 0.36));
    map.put(StandardIngredient.CARROT.getName(), new CalorieGram(
        StandardIngredient.CARROT.getPricePerTablespoon(), 0.41, 0.64));
    map.put(StandardIngredient.CASHEW.getName(), new CalorieGram(
        StandardIngredient.CASHEW.getPricePerTablespoon(), 5.53, 0.5));
    map.put(StandardIngredient.CAULIFLOWER.getName(), new CalorieGram(
        StandardIngredient.CAULIFLOWER.getPricePerTablespoon(), 0.25, 0.27));
    map.put(StandardIngredient.CELERY.getName(), new CalorieGram(
        StandardIngredient.CELERY.getPricePerTablespoon(), 0.14, 0.61));
    map.put(StandardIngredient.CHEDDAR_CHEESE.getName(), new CalorieGram(
        StandardIngredient.CHEDDAR_CHEESE.getPricePerTablespoon(), 4.40, 0.34));
    map.put(StandardIngredient.CHERRY.getName(), new CalorieGram(
        StandardIngredient.CHERRY.getPricePerTablespoon(), 0.50, 1.02));
    map.put(StandardIngredient.CHICKEN.getName(), new CalorieGram(
        StandardIngredient.CHICKEN.getPricePerTablespoon(), 2.00, 1.04));
    map.put(StandardIngredient.CHOCOLATE.getName(), new CalorieGram(
        StandardIngredient.CHOCOLATE.getPricePerTablespoon(), 5.00, 1.33));
    map.put(StandardIngredient.CINNAMON.getName(), new CalorieGram(
        StandardIngredient.CINNAMON.getPricePerTablespoon(), 2.61, 0.45));
    map.put(StandardIngredient.COD.getName(), new CalorieGram(
        StandardIngredient.COD.getPricePerTablespoon(), 1.00, 0.58));
    map.put(StandardIngredient.CORN.getName(), new CalorieGram(
        StandardIngredient.CORN.getPricePerTablespoon(), 1.30, 0.72));
    map.put(StandardIngredient.CORNFLAKE.getName(), new CalorieGram(
        StandardIngredient.CORNFLAKE.getPricePerTablespoon(), 3.70, 0.12));
    map.put(StandardIngredient.COTTAGE_CHEESE.getName(), new CalorieGram(
        StandardIngredient.COTTAGE_CHEESE.getPricePerTablespoon(), 0.98, 0.96));
    map.put(StandardIngredient.CRAB.getName(), new CalorieGram(
        StandardIngredient.CRAB.getPricePerTablespoon(), 1.10, 0.61));
    map.put(StandardIngredient.CREME_DE_CACAO.getName(), new CalorieGram(
        StandardIngredient.CREME_DE_CACAO.getPricePerTablespoon(), 2.75, 0.79));
    map.put(StandardIngredient.CUCUMBER.getName(), new CalorieGram(
        StandardIngredient.CUCUMBER.getPricePerTablespoon(), 0.10, 0.67));
    map.put(StandardIngredient.EGG.getName(), new CalorieGram(
        StandardIngredient.EGG.getPricePerTablespoon(), 1.50, 0.60));
    map.put(StandardIngredient.FLOUR.getName(), new CalorieGram(
        StandardIngredient.FLOUR.getPricePerTablespoon(), 3.64, 0.45));
    map.put(StandardIngredient.GARLIC.getName(), new CalorieGram(
        StandardIngredient.GARLIC.getPricePerTablespoon(), 1.11, 0.32));
    map.put(StandardIngredient.GRAPEFRUIT.getName(), new CalorieGram(
        StandardIngredient.GRAPEFRUIT.getPricePerTablespoon(), 0.32, 0.33));
    map.put(StandardIngredient.GRAPE.getName(), new CalorieGram(
        StandardIngredient.GRAPE.getPricePerTablespoon(), 0.62, 0.37));
    map.put(StandardIngredient.GRAPE_JUICE.getName(), new CalorieGram(
        StandardIngredient.GRAPE_JUICE.getPricePerTablespoon(), 0.60, 1.04));
    map.put(StandardIngredient.GREEN_BEAN.getName(), new CalorieGram(
        StandardIngredient.GREEN_BEAN.getPricePerTablespoon(), 0.31, 0.53));
    map.put(StandardIngredient.HADDOCK.getName(), new CalorieGram(
        StandardIngredient.HADDOCK.getPricePerTablespoon(), 1.10, 0.58));
    map.put(StandardIngredient.HAM.getName(), new CalorieGram(
        StandardIngredient.HAM.getPricePerTablespoon(), 2.40, 1.40));
    map.put(StandardIngredient.HONEY.getName(), new CalorieGram(
        StandardIngredient.HONEY.getPricePerTablespoon(), 2.80, 1.5));
    map.put(StandardIngredient.ICE_CREAM.getName(), new CalorieGram(
        StandardIngredient.ICE_CREAM.getPricePerTablespoon(), 1.80, 0.55));
    map.put(StandardIngredient.KIDNEY_BEAN.getName(), new CalorieGram(
        StandardIngredient.KIDNEY_BEAN.getPricePerTablespoon(), 3.33, 0.79));
    map.put(StandardIngredient.LAMB.getName(), new CalorieGram(
        StandardIngredient.LAMB.getPricePerTablespoon(), 2.00, 1.3));
    map.put(StandardIngredient.LEMON.getName(), new CalorieGram(
        StandardIngredient.LEMON.getPricePerTablespoon(), 0.29, 0.77));
    map.put(StandardIngredient.LENTIL.getName(), new CalorieGram(
        StandardIngredient.LENTIL.getPricePerTablespoon(), 1.16, 0.85));
    map.put(StandardIngredient.LETTUCE.getName(), new CalorieGram(
        StandardIngredient.LETTUCE.getPricePerTablespoon(), 0.15, 0.06));
    map.put(StandardIngredient.MACARONI.getName(), new CalorieGram(
        StandardIngredient.MACARONI.getPricePerTablespoon(), 3.71, 1.31));
    map.put(StandardIngredient.MILK.getName(), new CalorieGram(
        StandardIngredient.MILK.getPricePerTablespoon(), 0.70, 1.04));
    map.put(StandardIngredient.MUSHROOM.getName(), new CalorieGram(
        StandardIngredient.MUSHROOM.getPricePerTablespoon(), 0.15, 1.17));
    map.put(StandardIngredient.OIL.getName(), new CalorieGram(
        StandardIngredient.OIL.getPricePerTablespoon(), 9.00, 0.88));
    map.put(StandardIngredient.OLIVE.getName(), new CalorieGram(
        StandardIngredient.OLIVE.getPricePerTablespoon(), 0.80, 0.65));
    map.put(StandardIngredient.ONION.getName(), new CalorieGram(
        StandardIngredient.ONION.getPricePerTablespoon(), 0.22, 0.75));
    map.put(StandardIngredient.ORANGE.getName(), new CalorieGram(
        StandardIngredient.ORANGE.getPricePerTablespoon(), 0.30, 0.77));
    map.put(StandardIngredient.PAPRIKA.getName(), new CalorieGram(
        StandardIngredient.PAPRIKA.getPricePerTablespoon(), 2.82, 0.46));
    map.put(StandardIngredient.PASTA.getName(), new CalorieGram(
        StandardIngredient.PASTA.getPricePerTablespoon(), 3.71, 1.31));
    map.put(StandardIngredient.PEACH.getName(), new CalorieGram(
        StandardIngredient.PEACH.getPricePerTablespoon(), 0.30, 0.61));
    map.put(StandardIngredient.PEANUT.getName(), new CalorieGram(
        StandardIngredient.PEANUT.getPricePerTablespoon(), 5.67, 0.53));
    map.put(StandardIngredient.PEAR.getName(), new CalorieGram(
        StandardIngredient.PEAR.getPricePerTablespoon(), 0.15, 0.61));
    map.put(StandardIngredient.PEAS.getName(), new CalorieGram(
        StandardIngredient.PEAS.getPricePerTablespoon(), 1.48, 0.73));
    map.put(StandardIngredient.PEPPER.getName(), new CalorieGram(
        StandardIngredient.PEPPER.getPricePerTablespoon(), 0.20, 0.51));
    map.put(StandardIngredient.PINEAPPLE.getName(), new CalorieGram(
        StandardIngredient.PINEAPPLE.getPricePerTablespoon(), 0.40, 0.54));
    map.put(StandardIngredient.PLUM.getName(), new CalorieGram(
        StandardIngredient.PLUM.getPricePerTablespoon(), 0.39, 0.58));
    map.put(StandardIngredient.PORK.getName(), new CalorieGram(
        StandardIngredient.PORK.getPricePerTablespoon(), 2.90, 0.7));
    map.put(StandardIngredient.RUM.getName(), new CalorieGram(
        StandardIngredient.RUM.getPricePerTablespoon(), 2.75, 0.79));
    map.put(StandardIngredient.SALMON.getName(), new CalorieGram(
        StandardIngredient.SALMON.getPricePerTablespoon(), 1.80, 0.58));
    map.put(StandardIngredient.SALT.getName(), new CalorieGram(
        StandardIngredient.SALT.getPricePerTablespoon(), 0.0, 1.38));
    map.put(StandardIngredient.SALTINE_CRACKERS.getName(), new CalorieGram(
        StandardIngredient.SALTINE_CRACKERS.getPricePerTablespoon(), 4.21, 0.43));
    map.put(StandardIngredient.SPAGHETTI.getName(), new CalorieGram(
        StandardIngredient.SPAGHETTI.getPricePerTablespoon(), 3.71, 1.31));
    map.put(StandardIngredient.SPINACH.getName(), new CalorieGram(
        StandardIngredient.SPINACH.getPricePerTablespoon(), 0.08, 0.08));
    map.put(StandardIngredient.STRAWBERRIES.getName(), new CalorieGram(
        StandardIngredient.STRAWBERRIES.getPricePerTablespoon(), 30.0, 0.58));
    map.put(StandardIngredient.SUGAR.getName(), new CalorieGram(
        StandardIngredient.SUGAR.getPricePerTablespoon(), 4.00, 0.95));
    map.put(StandardIngredient.SWEET_POTATO.getName(), new CalorieGram(
        StandardIngredient.SWEET_POTATO.getPricePerTablespoon(), 0.86, 0.65));
    map.put(StandardIngredient.SYRUP.getName(), new CalorieGram(
        StandardIngredient.SYRUP.getPricePerTablespoon(), 2.60, 1.38));
    map.put(StandardIngredient.THYME.getName(), new CalorieGram(
        StandardIngredient.THYME.getPricePerTablespoon(), 1.01, 0.46));
    map.put(StandardIngredient.TOMATO.getName(), new CalorieGram(
        StandardIngredient.TOMATO.getPricePerTablespoon(), 0.20, 0.67));
    map.put(StandardIngredient.WINE.getName(), new CalorieGram(
        StandardIngredient.WINE.getPricePerTablespoon(), 0.83, 0.99));
    return map;
  }
  /**
   * Returns whether Nutrition info is stored for the named ingredient.
   * 
   * @param ingredientName
   *          the name of an ingredient
   * @return true if there is nutrition information for the ingredient, false otherwise.
   */
  public static boolean contains(final String ingredientName)
  {
    return NUTRITION_MAP.containsKey(ingredientName.toLowerCase());
  }

  /**
   * Adds a new ingredient's information to the nutritionMap, so long as it is not already present
   * in the map.
   * 
   * @param name
   *          the name of the ingredient
   * @param calories
   *          the caloriesPerGram for the ingredient
   * @param density
   *          the gramPerML for the ingredient
   * @param pricePerTablespoon
   *          price per tablespoon, the standard unit of volume
   */

  public static void addIngredient(final String name, final double pricePerTablespoon,
      final Double calories, final Double density)
  {
    if (!NUTRITION_MAP.containsKey(name.toLowerCase()))
    {
      NUTRITION_MAP.put(name, new CalorieGram(pricePerTablespoon, calories, density));
    }
  }

  /**
   * Returns the Set of keys (a.k.a. ingredient names) in the NutritionInfo map.
   * 
   * @return A set of String keys
   */
  public static Set<String> getIngredientsInMap()
  {
    return NUTRITION_MAP.keySet();
  }
  
  /**
   * Returns the price per tablespoon of the ingredient, if it exists in the map and has calorie
   * information. Else, returns 0.0.
   * 
   * @param ingredientName
   * @return the price per tablespoon of the ingredientName, or 0.0 if it is not present
   */
  public static Double getPricePerTablespoon(final String ingredientName)
  {
    CalorieGram mapping = NUTRITION_MAP.get(ingredientName.toLowerCase());
    if (mapping != null && mapping.getPrice() != null)
    {
      return mapping.getPrice();
    }
    return 0.0;
  }
 
  /**
   * Returns the calorie information of the ingredient, if it exists in the map and has calorie
   * information. Else, returns 0.0.
   * 
   * @param ingredientName
   *          the ingredient whose calorie info we want to find
   * @return the calories of the ingredientName, or 0.0 if it is not present
   */
  public static Double getCalPerGram(final String ingredientName)
  {
    CalorieGram mapping = NUTRITION_MAP.get(ingredientName.toLowerCase());
    if (mapping != null && mapping.getCal() != null)
    {
      return mapping.getCal();
    }
    else
    {
      return 0.0;
    }
  }

  /**
   * Returns the density information of the ingredient, if it exists in the map and has density
   * information. Else, returns 0.0.
   * 
   * @param ingredientName
   *          the ingredient whose density info we want to find
   * @return the density of the ingredientName, or 0.0 if it is not present
   */
  public static Double getGramPerML(final String ingredientName)
  {
    CalorieGram mapping = NUTRITION_MAP.get(ingredientName.toLowerCase());
    if (mapping != null && mapping.getDensity() != null)
    {
      return mapping.getDensity();
    }
    else
    {
      return 0.0;
    }
  }
  
}
