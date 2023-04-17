package recipes;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Utility Class for maintaining a map of ingredients and nutritional information for those
 * ingredients.
 * 
 * @author Jack Milman, KichIntel
 * @version 4/14/2023 V2
 *
 */
public class NutritionInfo
{
  private static final Map<String, CalorieGram> NUTRITION_MAP = initializeNutrition();

  private static Map<String, CalorieGram> initializeNutrition()
  {
    Map<String, CalorieGram> map = new HashMap<String, CalorieGram>();
    map.put("alcohol", new CalorieGram(2.75, 0.79));
    map.put("almond", new CalorieGram(6.01, 0.46));
    map.put("american cheese", new CalorieGram(4.40, 0.34));
    map.put("apple", new CalorieGram(0.44, 0.56));
    map.put("apple juice", new CalorieGram(0.48, 1.04));
    map.put("banana", new CalorieGram(0.65, 0.56));
    map.put("bean", new CalorieGram(1.30, 0.77));
    map.put("beef", new CalorieGram(2.80, 1.05));
    map.put("blackberry", new CalorieGram(0.25, 0.53));
    map.put("black pepper", new CalorieGram(2.55, 1.01));
    map.put("bread", new CalorieGram(2.40, 0.42));
    map.put("broccoli", new CalorieGram(0.32, 0.37));
    map.put("brown sugar", new CalorieGram(3.80, 1.5));
    map.put("butter", new CalorieGram(7.50, 0.91));
    map.put("cabbage", new CalorieGram(0.28, 0.36));
    map.put("carrot", new CalorieGram(0.41, 0.64));
    map.put("cashew", new CalorieGram(5.53, 0.5));
    map.put("cauliflower", new CalorieGram(0.25, 0.27));
    map.put("celery", new CalorieGram(0.14, 0.61));
    map.put("cheddar cheese", new CalorieGram(4.40, 0.34));
    map.put("cherry", new CalorieGram(0.50, 1.02));
    map.put("chicken", new CalorieGram(2.00, 1.04));
    map.put("chocolate", new CalorieGram(5.00, 1.33));
    map.put("cinnamon", new CalorieGram(2.61, 0.45));
    map.put("cod", new CalorieGram(1.00, 0.58));
    map.put("corn", new CalorieGram(1.30, 0.72));
    map.put("cornflake", new CalorieGram(3.70, 0.12));
    map.put("cottage cheese", new CalorieGram(0.98, 0.96));
    map.put("crab", new CalorieGram(1.10, 0.61));
    map.put("creme de cacao", new CalorieGram(2.75, 0.79));
    map.put("cucumber", new CalorieGram(0.10, 0.67));
    map.put("egg", new CalorieGram(1.50, 0.60));
    map.put("flour", new CalorieGram(3.64, 0.45));
    map.put("garlic", new CalorieGram(1.11, 0.32));
    map.put("grapefruit", new CalorieGram(0.32, 0.33));
    map.put("grape", new CalorieGram(0.62, 0.37));
    map.put("grape juice", new CalorieGram(0.60, 1.04));
    map.put("green bean", new CalorieGram(0.31, 0.53));
    map.put("haddock", new CalorieGram(1.10, 0.58));
    map.put("ham", new CalorieGram(2.40, 1.40));
    map.put("honey", new CalorieGram(2.80, 1.5));
    map.put("ice cream", new CalorieGram(1.80, 0.55));
    map.put("kidney bean", new CalorieGram(3.33, 0.79));
    map.put("lamb", new CalorieGram(2.00, 1.3));
    map.put("lemon", new CalorieGram(0.29, 0.77));
    map.put("lentil", new CalorieGram(1.16, 0.85));
    map.put("lettuce", new CalorieGram(0.15, 0.06));
    map.put("macaroni", new CalorieGram(3.71, 1.31));
    map.put("milk", new CalorieGram(0.70, 1.04));
    map.put("mushroom", new CalorieGram(0.15, 1.17));
    map.put("oil", new CalorieGram(9.00, 0.88));
    map.put("olive", new CalorieGram(0.80, 0.65));
    map.put("onion", new CalorieGram(0.22, 0.75));
    map.put("orange", new CalorieGram(0.30, 0.77));
    map.put("paprika", new CalorieGram(2.82, 0.46));
    map.put("pasta", new CalorieGram(3.71, 1.31));
    map.put("peach", new CalorieGram(0.30, 0.61));
    map.put("peanut", new CalorieGram(5.67, 0.53));
    map.put("pear", new CalorieGram(0.15, 0.61));
    map.put("peas", new CalorieGram(1.48, 0.73));
    map.put("pepper", new CalorieGram(0.20, 0.51));
    map.put("pineapple", new CalorieGram(0.40, 0.54));
    map.put("plum", new CalorieGram(0.39, 0.58));
    map.put("pork", new CalorieGram(2.90, 0.7));
    map.put("rum", new CalorieGram(2.75, 0.79));
    map.put("salmon", new CalorieGram(1.80, 0.58));
    map.put("salt", new CalorieGram(0.0, 1.38));
    map.put("saltine crackers", new CalorieGram(4.21, 0.43));
    map.put("spaghetti", new CalorieGram(3.71, 1.31));
    map.put("spinach", new CalorieGram(0.08, 0.08));
    map.put("strawberries", new CalorieGram(30.0, 0.58));
    map.put("sugar", new CalorieGram(4.00, 0.95));
    map.put("sweet potato", new CalorieGram(0.86, 0.65));
    map.put("syrup", new CalorieGram(2.60, 1.38));
    map.put("thyme", new CalorieGram(1.01, 0.46));
    map.put("tomato", new CalorieGram(0.20, 0.67));
    map.put("wine", new CalorieGram(0.83, 0.99));
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
    return NUTRITION_MAP.containsKey(ingredientName);
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
   */
  public static void addIngredient(final String name, final Double calories, final Double density)
  {
    NUTRITION_MAP.put(name, new CalorieGram(calories, density));
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
    CalorieGram mapping = NUTRITION_MAP.get(ingredientName);
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
