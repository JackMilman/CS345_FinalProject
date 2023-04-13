package recipes;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class NutritionInfo
{
  private final static Map<String, CalorieGram> nutritionMap = initializeNutrition();

  private static Map<String, CalorieGram> initializeNutrition()
  {
    Map<String, CalorieGram> map = new HashMap<String, CalorieGram>();
    map.put("Alcohol", new CalorieGram(2.75, 0.79));
    map.put("Almond", new CalorieGram(6.01, 0.46));
    map.put("American cheese", new CalorieGram(4.40, 0.34));
    map.put("Apple", new CalorieGram(0.44, 0.56));
    map.put("Apple juice", new CalorieGram(0.48, 1.04));
    map.put("Banana", new CalorieGram(0.65, 0.56));
    map.put("Bean", new CalorieGram(1.30, 0.77));
    map.put("Beef", new CalorieGram(2.80, 1.05));
    map.put("Blackberry", new CalorieGram(0.25, 0.53));
    map.put("Black pepper", new CalorieGram(2.55, 1.01));
    map.put("Bread", new CalorieGram(2.40, 0.42));
    map.put("Broccoli", new CalorieGram(0.32, 0.37));
    map.put("Brown sugar", new CalorieGram(3.80, 1.5));
    map.put("Butter", new CalorieGram(7.50, 0.91));
    map.put("Cabbage", new CalorieGram(0.28, 0.36));
    map.put("Carrot", new CalorieGram(0.41, 0.64));
    map.put("Cashew", new CalorieGram(5.53, 0.5));
    map.put("Cauliflower", new CalorieGram(0.25, 0.27));
    map.put("Celery", new CalorieGram(0.14, 0.61));
    map.put("Cheddar cheese", new CalorieGram(4.40, 0.34));
    map.put("Cherry", new CalorieGram(0.50, 1.02));
    map.put("Chicken", new CalorieGram(2.00, 1.04));
    map.put("Chocolate", new CalorieGram(5.00, 1.33));
    map.put("Cinnamon", new CalorieGram(2.61, 0.45));
    map.put("Cod", new CalorieGram(1.00, 0.58));
    map.put("Corn", new CalorieGram(1.30, 0.72));
    map.put("Cornflake", new CalorieGram(3.70, 0.12));
    map.put("Cottage cheese", new CalorieGram(0.98, 0.96));
    map.put("Crab", new CalorieGram(1.10, 0.61));
    map.put("Creme de cacao", new CalorieGram(2.75, 0.79));
    map.put("Cucumber", new CalorieGram(0.10, 0.67));
    map.put("Egg", new CalorieGram(1.50, 0.60));
    map.put("Flour", new CalorieGram(3.64, 0.45));
    map.put("Garlic", new CalorieGram(1.11, 0.32));
    map.put("Grapefruit", new CalorieGram(0.32, 0.33));
    map.put("Grape", new CalorieGram(0.62, 0.37));
    map.put("Grape juice", new CalorieGram(0.60, 1.04));
    map.put("Green bean", new CalorieGram(0.31, 0.53));
    map.put("Haddock", new CalorieGram(1.10, 0.58));
    map.put("Ham", new CalorieGram(2.40, 1.40));
    map.put("Honey", new CalorieGram(2.80, 1.5));
    map.put("Ice cream", new CalorieGram(1.80, 0.55));
    map.put("Kidney bean", new CalorieGram(3.33, 0.79));
    map.put("Lamb", new CalorieGram(2.00, 1.3));
    map.put("Lemon", new CalorieGram(0.29, 0.77));
    map.put("Lentil", new CalorieGram(1.16, 0.85));
    map.put("Lettuce", new CalorieGram(0.15, 0.06));
    map.put("Macaroni", new CalorieGram(3.71, 1.31));
    map.put("Milk", new CalorieGram(0.70, 1.04));
    map.put("Mushroom", new CalorieGram(0.15, 1.17));
    map.put("Oil", new CalorieGram(9.00, 0.88));
    map.put("Olive", new CalorieGram(0.80, 0.65));
    map.put("Onion", new CalorieGram(0.22, 0.75));
    map.put("Orange", new CalorieGram(0.30, 0.77));
    map.put("Paprika", new CalorieGram(2.82, 0.46));
    map.put("Pasta", new CalorieGram(3.71, 1.31));
    map.put("Peach", new CalorieGram(0.30, 0.61));
    map.put("Peanut", new CalorieGram(5.67, 0.53));
    map.put("Pear", new CalorieGram(0.15, 0.61));
    map.put("Peas", new CalorieGram(1.48, 0.73));
    map.put("Pepper", new CalorieGram(0.20, 0.51));
    map.put("Pineapple", new CalorieGram(0.40, 0.54));
    map.put("Plum", new CalorieGram(0.39, 0.58));
    map.put("Pork", new CalorieGram(2.90, 0.7));
    map.put("Rum", new CalorieGram(2.75, 0.79));
    map.put("Salmon", new CalorieGram(1.80, 0.58));
    map.put("Salt", new CalorieGram(0.0, 1.38));
    map.put("Saltine crackers", new CalorieGram(4.21, 0.43));
    map.put("Spaghetti", new CalorieGram(3.71, 1.31));
    map.put("Spinach", new CalorieGram(0.08, 0.08));
    map.put("Strawberries", new CalorieGram(30.0, 0.58));
    map.put("Sugar", new CalorieGram(4.00, 0.95));
    map.put("Sweet potato", new CalorieGram(0.86, 0.65));
    map.put("Syrup", new CalorieGram(2.60, 1.38));
    map.put("Thyme", new CalorieGram(1.01, 0.46));
    map.put("Tomato", new CalorieGram(0.20, 0.67));
    map.put("Wine", new CalorieGram(0.83, 0.99));
    return map;
  }
  
  public static boolean contains(final String ingredientName) {
    return nutritionMap.containsKey(ingredientName);
  }
  
  public static void addIngredient(final String name, final Double calories, final Double density) {
    nutritionMap.put(name, new CalorieGram(calories, density));
  }
  
  public static Set<String> getKeys() {
    return nutritionMap.keySet();
  }

  public static Double getCalPerGram(final String ingredientName)
  {
    CalorieGram mapping = nutritionMap.get(ingredientName);
    if (mapping != null && mapping.getCal() != null) {
      return mapping.getCal();
    } else {
      return 0.0;
    }
  }

  public static Double getGramPerML(final String ingredientName) {
    CalorieGram mapping = nutritionMap.get(ingredientName);
    if (mapping != null && mapping.getDensity() != null) {
      return mapping.getDensity();
    } else {
      return 0.0;
    }
  }
}
