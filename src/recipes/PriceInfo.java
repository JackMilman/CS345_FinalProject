package recipes;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents the ingredients and their prices per tablespoon
 * that are built in to the software (not including ingredients added
 * by the user).
 * 
 * Prices are in USD based on listings from arbitrary brands on the Target website
 * (unless Target doesn't have them, in which case the prices are from the Walmart website),
 * converted to the price for one tablespoon and rounded to the nearest hundredth.
 * 
 * @author Meara Patterson, Jack Milman, KichIntel
 * @version 4/24/2023
 */
public class PriceInfo
{
  
  private static final Map<String, Double> PRICE_MAP = intitializePrice();

  private static Map<String, Double> intitializePrice()
  {
    Map<String, Double> map = new HashMap<String, Double>();
    map.put("alcohol", 0.05);
    map.put("almond", 0.30);
    map.put("american cheese", 0.16);
    map.put("apple", 0.05);
    map.put("apple juice", 0.2);
    map.put("banana", 0.03);
    map.put("bean", 0.03);
    map.put("beef", 0.2);
    map.put("blackberry", 0.18);
    map.put("black pepper", 0.60);
    map.put("bread", 0.03);
    map.put("broccoli", 0.12);
    map.put("brown sugar", 0.03);
    map.put("butter", 0.12);
    map.put("cabbage", 0.01);
    map.put("carrot", .04);
    map.put("cashew", 0.28);
    map.put("cauliflower", 0.07);
    map.put("celery", 0.07);
    map.put("cheddar cheese", 0.14);
    map.put("cherry", 0.12);
    map.put("chicken", 0.17);
    map.put("chocolate", 0.42);
    map.put("cinnamon", 0.27);
    map.put("cod", 0.42);
    map.put("corn", 0.05);
    map.put("cornflake", 0.15);
    map.put("cottage cheese", 0.06);
    map.put("crab", 0.27);
    map.put("creme de cacao", 0.35);
    map.put("cucumber", 0.17);
    map.put("egg", 0.05);
    map.put("flour", 0.01);
    map.put("garlic", 0.20);
    map.put("grapefruit", 0.08);
    map.put("grape", 0.08);
    map.put("grape juice", 0.04);
    map.put("green bean", 0.12);
    map.put("haddock", 0.42);
    map.put("ham", 0.22);
    map.put("honey", 0.17);
    map.put("ice cream", 0.06);
    map.put("kidney bean", 0.04);
    map.put("lamb", 0.51);
    map.put("lemon", 0.07);
    map.put("lentil", 0.05);
    map.put("lettuce", 0.07);
    map.put("macaroni", 0.06);
    map.put("milk", 0.01);
    map.put("mushroom", 0.14);
    map.put("oil", 0.04);
    map.put("olive", 0.17);
    map.put("onion", 0.05);
    map.put("orange", 0.04);
    map.put("paprika",0.28);
    map.put("pasta", 0.01);
    map.put("peach", 0.08);
    map.put("peanut", 0.08);
    map.put("pear",0.09);
    map.put("peas", 0.04);
    map.put("pepper", 0.10);
    map.put("pineapple", 0.04);
    map.put("plum",0.09);
    map.put("pork", 0.15);
    map.put("rum",0.24);
    map.put("salmon", 0.31);
    map.put("salt", 0.01);
    map.put("saltine crackers", 0.12);
    map.put("spaghetti", 0.06);
    map.put("spinach", 0.12);
    map.put("strawberries", 0.12);
    map.put("sugar", 0.02);
    map.put("sweet potato", 0.06); 
    map.put("syrup", 0.05);
    map.put("thyme", 0.79);
    map.put("tomato", 0.05);
    map.put("wine", 0.43);
    return map;
  }
  
  /**
   * Adds a new ingredient's information to the priceMap, so long as it is not already present
   * in the map.
   * 
   * @param name
   *          the name of the ingredient
   * @param price
   *          price per tablespoon, the standard unit of volume
   */
  public static void addIngredient(final String name, final Double price)
  {
    if (!PRICE_MAP.containsKey(name.toLowerCase()))
    {
      PRICE_MAP.put(name.toLowerCase(), price);
    }
  }
  
  /**
   * Return the price per tablespoon of the built-in ingredient with the
   * given name.
   * 
   * If the given name does not match any built-in ingredients, return 0.0.
   * 
   * @param ingredientName find the name of a built-in ingredient
   * @return the ingredient's price per tablespoon or 0.0
   */
  public static Double getPricePerTablespoon(final String ingredientName)
  {
    Double mapping = PRICE_MAP.get(ingredientName.toLowerCase());
    if (mapping != null)
    {
      return mapping;
    }
    else
    {
      return 0.0;
    }
  }
  
}
