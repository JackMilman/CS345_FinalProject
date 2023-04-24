package recipes;

/**
 * Represents the ingredients and their prices per tablespoon
 * that are built in to the software (not including ingredients added
 * by the user).
 * 
 * Prices are in USD based on listings from arbitrary brands on the Target website
 * (unless Target doesn't have them, in which case the prices are from the Walmart website),
 * converted to the price for one tablespoon and rounded to the nearest hundredth.
 * 
 * @author Meara Patterson
 * @version 4/24/2023
 */
public enum StandardIngredient
{

  ALCOHOL("alcohol", 0.05), ALMOND("almond", 0.30), AMERICAN_CHEESE("american cheese", 0.16),
  APPLE("apple", 0.05), APPLE_JUICE("apple juice", 0.02), BANANA("banana", 0.03), 
  BEAN("bean", 0.03), BEEF("beef", 0.2), BLACKBERRY("blackberry", 0.18), 
  BLACK_PEPPER("black pepper", 0.60), BREAD("bread", 0.03), BROCCOLI("broccoli", 0.12), 
  BROWN_SUGAR("brown sugar", 0.03), BUTTER("butter", 0.12),
  CABBAGE("cabbage", 0.01), CARROT("carrot", 0.04), CASHEW("cashew", 0.28), 
  CAULIFLOWER("cauliflower", 0.07), CELERY("celery", 0.07), CHEDDAR_CHEESE("cheddar cheese", 0.14), 
  CHERRY("cherry", 0.12), CHICKEN("chicken", 0.17), CHOCOLATE("chocolate", 0.42), 
  CINNAMON("cinnamon", 0.27), COD("cod", 0.42),
  CORN("corn", 0.05), CORNFLAKE("cornflake", 0.15), COTTAGE_CHEESE("cottage cheese", 0.06),
  CRAB("crab", 0.27), CREME_DE_CACAO("creme de cacao", 0.35), CUCUMBER("cucumber", 0.17), 
  EGG("egg", 0.05), FLOUR("flour", 0.01), GARLIC("garlic", 0.20), GRAPEFRUIT("grapefruit", 0.08), 
  GRAPE("grape", 0.08), GRAPE_JUICE("grape juice", 0.04), GREEN_BEAN("green bean", 0.12), 
  HADDOCK("haddock", 0.42), HAM("ham", 0.22), HONEY("honey", 0.17), ICE_CREAM("ice cream", 0.06), 
  KIDNEY_BEAN("kidney bean", 0.04), LAMB("lamb", 0.51), LEMON("lemon", 0.07), 
  LENTIL("lentil", 0.05), LETTUCE("lettuce", 0.07),
  MACARONI("macaroni", 0.06), MILK("milk", 0.01), MUSHROOM("mushroom", 0.14), OIL("oil", 0.04),
  OLIVE("olive", 0.17), ONION("onion", 0.05), ORANGE("orange", 0.04), PAPRIKA("paprika", 0.28),
  PASTA("pasta", 0.01), PEACH("peach", 0.08), PEANUT("peanut", 0.08), PEAR("pear", 0.09), 
  PEAS("peas", 0.04), PEPPER("pepper", 0.10), PINEAPPLE("pineapple", 0.04), PLUM("plum", 0.09), 
  PORK("pork", 0.16), RUM("rum", 0.24),
  SALMON("salmon", 0.31), SALT("salt", 0.01), SALTINE_CRACKERS("saltine crackers", 0.12),
  SPAGHETTI("spaghetti", 0.06), SPINACH("spinach", 0.12), STRAWBERRIES("strawberries", 0.12),
  SUGAR("sugar", 0.02), SWEET_POTATO("sweet potato", 0.06), SYRUP("syrup", 0.05),
  THYME("thyme", 0.79), TOMATO("tomato", 0.05), WINE("wine", 0.43);
  
  private final String name;
  private final Double pricePerTablespoon;
  
  StandardIngredient(final String name, final Double pricePerTablespoon)
  {
    this.name = name;
    this.pricePerTablespoon = pricePerTablespoon;
  }
  
  /**
   * Return the name of a built-in ingredient.
   * 
   * @return name
   */
  public String getName()
  {
    return this.name;
  }
  
  /**
   * Return the price per tablespoon of a built-in ingredient.
   * 
   * @return price per tablespoon
   */
  public Double getPricePerTablespoon()
  {
    return this.pricePerTablespoon;
  }
  
  /**
   * Return the price per tablespoon of the built-in ingredient with the
   * given name.
   * 
   * If the given name does not match any built-in ingredients, return null.
   * 
   * @param find the name of a built-in ingredient
   * @return the ingredient's price per tablespoon or null
   */
  public Double getPricePerTablespoon(final String find)
  {
    for (StandardIngredient ing : values())
    {
      if (ing.getName().equals(find))
      {
        return ing.getPricePerTablespoon();
      }
    }
    return null;
  }
  
}
