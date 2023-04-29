package recipes;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Object class describing a Recipe. A Recipe contains a name, the number of people it serves, lists
 * of the ingredients and utensils required to make it, and the steps in which those ingredients and
 * utensils are to be used to make the meal.
 * 
 * @version 4/28/2023 Version 3
 * @author Jack Milman, KichIntel
 *
 */
public class Recipe implements Serializable
{
  
  private static final long serialVersionUID = 1L;
  
  private static final String FILEEXT = ".rcp";
  
  protected List<Ingredient> ingredients = new ArrayList<Ingredient>();

  protected HashMap<Ingredient, List<Ingredient>> substitutes = 
      new HashMap<Ingredient, List<Ingredient>>();

  protected List<Utensil> utensils = new ArrayList<Utensil>();

  protected List<Step> steps = new ArrayList<Step>();

  private String name;

  private int servings;

  private int numSubstitutes = 0;

  private List<Recipe> subRecipes = new ArrayList<Recipe>();

  

  /**
   * Constructs a new Recipe. The name of a recipe may not be null and must be at least 1 character
   * long. The serving size must be greater than 0, and if a non-positive number is passed the
   * default number of servings is 1.
   * 
   * @param name
   * @param servings
   */
  public Recipe(final String name, final int servings)
  {
    if (name == null || name.equals(""))
    {
      this.name = "DefaultRecipeName";
    }
    else
    {
      this.name = name;
    }

    if (servings < 1)
    {
      this.servings = 1;
    }
    else
    {
      this.servings = servings;
    }
  }

  /**
   * Adds all the substitutes in the passed map of substitutes to this recipe, if possible. Returns
   * true if the size of substitutes changed as a result of this operation.
   * 
   * @param newSubs
   * @return true if the size of substitutes changed as a result of this operation
   */
  public boolean addAllSubstitutes(final HashMap<Ingredient, List<Ingredient>> newSubs)
  {
    int sizeBefore = numSubstitutes;

    for (Ingredient key : newSubs.keySet())
    {
      for (Ingredient substitute : newSubs.get(key))
      {
        addSubstitute(key, substitute);
      }
    }

    int sizeAfter = numSubstitutes;

    return sizeBefore != sizeAfter;
  }

  /**
   * Adds all the ingredients in the passed list to this recipe, if possible. Returns true if the
   * size of ingredients changed as a result of this operation.
   * 
   * @param newIngredients
   * @return true if the size of ingredients changed as a result of this operation
   */
  public boolean addAllIngredients(final List<Ingredient> newIngredients)
  {
    int sizeBefore = ingredients.size();
    for (Ingredient ingredient : newIngredients)
    {
      addIngredient(ingredient);
    }
    // If the list changed as a result of this operation
    return sizeBefore != ingredients.size();
  }

  /**
   * Add a list of utensils to the recipe.
   * 
   * @param newUtensils
   * @return true if utensils were added
   */
  public boolean addAllUtensils(final List<Utensil> newUtensils)
  {
    int sizeBefore = utensils.size();
    for (Utensil utensil : newUtensils)
    {
      addUtensil(utensil);
    }
    // If the list changed as a result of this operation
    return sizeBefore != utensils.size();
  }

  /**
   * Add a list of steps to the recipe.
   * 
   * @param newSteps
   * @return true if steps were added
   */
  public boolean addAllSteps(final List<Step> newSteps)
  {
    int sizeBefore = steps.size();
    for (Step step : newSteps)
    {
      addStep(step);
    }
    // If the list changed as a result of this operation
    return sizeBefore != steps.size();
  }

  /**
   * Add a substitute for a specific ingredient in the recipe.
   * 
   * @param ingredient
   * @param substitute
   * @return true if add was successful
   */
  public boolean addSubstitute(final Ingredient ingredient, final Ingredient substitute)
  {
    if (ingredients.contains(ingredient))
    {
      if (substitutes.containsKey(ingredient))
      {
        substitutes.get(ingredient).add(substitute);
      }
      else
      {
        List<Ingredient> subs = new ArrayList<Ingredient>();
        subs.add(substitute);
        substitutes.put(ingredient, subs);
      }
      numSubstitutes++;
      return true;
    }
    return false;
  }

  /**
   * Remove a substitute for a specific ingredient in the recipe.
   * 
   * @param ingredient
   * @param substitute
   * @return true if remove was successful
   */
  public boolean removeSubstitute(final Ingredient ingredient, final Ingredient substitute)
  {
    if (ingredients.contains(ingredient))
    {
      if (substitutes.containsKey(ingredient))
      {
        substitutes.get(ingredient).remove(substitute);
        numSubstitutes--;
        return true;
      }
    }
    return false;
  }

  /**
   * Adds a new recipe to the list of this recipe's sub recipes.
   * 
   * @param recipe
   * @return true if the list was changed as a result of this operation
   */
  public boolean addRecipe(final Recipe recipe)
  {
    return subRecipes.add(recipe);
  }

  /**
   * Removes recipe from the list of this recipe's sub recipes.
   * 
   * @param recipe
   * @return true if the list was changed as a result of this operation
   */
  public boolean removeRecipe(final Recipe recipe)
  {
    return subRecipes.remove(recipe);
  }

  /**
   * Adds an ingredient to the list of Ingredients. Cannot add a duplicate ingredient.
   * 
   * @param ingredient
   *          the ingredient to attempt to add
   * 
   * @return returns true if the Ingredient was successfully added
   */
  public boolean addIngredient(final Ingredient ingredient)
  {
    if (!ingredients.contains(ingredient))
    {
      return ingredients.add(ingredient);
    }
    return false;
  }

  /**
   * Attempts to remove a ingredient from the list of ingredients. If the ingredient is present and
   * not used in a step or substitute it is removed and this method returns true. Else the
   * ingredient is not removed and this method returns false.
   * 
   * @param ingredient
   *          the ingredient to attempt to remove
   * 
   * @return true if the ingredient was successfully removed, else false
   */
  public boolean removeIngredient(final Ingredient ingredient)
  {
    if (ingredientHasSubstitutes(ingredient) || stepUsesIngredient(ingredient))
    {
      return false;
    }
    return ingredients.remove(ingredient);
  }

  /**
   * Adds an utensil to the list of Utensils.
   * 
   * @param utensil
   *          the utensil to attempt to add
   * 
   * @return returns true if the Utensil was successfully added
   */
  public boolean addUtensil(final Utensil utensil)
  {
    if (!utensils.contains(utensil))
    {
      return utensils.add(utensil);
    }
    return false;
  }

  /**
   * Attempts to remove a utensil from the list of utensils. If the utensil is not present or if the
   * utensil is being used in a step, returns false.
   * 
   * @param utensil
   *          the utensil to attempt to remove
   * 
   * @return true if the utensil was successfully removed, else false
   */
  public boolean removeUtensil(final Utensil utensil)
  {
    if (stepUsesUtensil(utensil))
    {
      return false;
    }

    return utensils.remove(utensil);
  }

  private boolean stepUsesUtensil(final Utensil utensil)
  {
    for (Step step : steps)
    {
      Utensil source = step.getSource();
      Utensil destination = step.getDestination();

      if ((source != null && source.equals(utensil))
          || (destination != null && destination.equals(utensil)))
      {
        return true;
      }
    }

    return false;
  }

  /**
   * Attempts to add a step to the list of steps. A step cannot be added if it has an Ingredient or
   * Utensils that are not in the recipe already.
   * 
   * @param step
   *          the step to attempt to add
   * 
   * @return true if step was successfully added
   */
  public boolean addStep(final Step step)
  {
    boolean hasIngredient = ingredients.contains(step.getIngredient());
    boolean hasSource = utensils.contains(step.getSource());
    boolean hasDestination = utensils.contains(step.getDestination());

    boolean isValid = (hasIngredient || hasSource) && hasDestination;
    if (isValid)
    {
      return steps.add(step);
    }

    return false;
  }

  /**
   * Attempts to remove a step from the list of steps. If the step is not present, returns false.
   * 
   * @param step
   *          the step to attempt to remove
   * 
   * @return true if the step was successfully removed, else false
   */
  public boolean removeStep(final Step step)
  {
    return steps.remove(step);
  }

  /**
   * Sets the name of the Recipe.
   * 
   * @param name
   *          the name to be set
   */
  public void setName(final String name)
  {
    this.name = name;
  }

  /**
   * Gets the name of the Recipe.
   * 
   * @return the name of the Recipe.
   */
  public String getName()
  {
    return name;
  }

  /**
   * Sets the number of servings of the Recipe.
   * 
   * @param servings
   *          the servings to be set
   */
  public void setServings(final int servings)
  {
    this.servings = servings;
  }

  /**
   * Gets the number of servings of the Recipe.
   * 
   * @return the number of servings of the Recipe.
   */
  public int getServings()
  {
    return servings;
  }

  /**
<<<<<<< HEAD
   * Gets the number of individual substitutions in the Recipe. This attribute is modified by the
   * add and remove substitutes methods.
   * 
   * @return the number of substitutes in the recipe
=======
   * Get the number of substitutes.
   * 
   * @return number of substitutes
>>>>>>> branch 'main' of https://github.com/bernstdh/S23Team2A
   */
  public int getNumSubstitutes()
  {
    return numSubstitutes;
  }

  /**
   * Gets a subRecipes of this Recipe. Returns a copy of the list.
   * 
   * @return list of subrecipes in recipe.
   */
  public List<Recipe> getSubRecipes()
  {
    return new ArrayList<Recipe>(subRecipes);
  }

  /**
   * Gets the substitute Ingredients in the Recipe. Returns a copy of the list.
   * 
   * @return the substitute ingredients in the recipe.
   */
  public HashMap<Ingredient, List<Ingredient>> getSubstitutes()
  {
    return new HashMap<Ingredient, List<Ingredient>>(substitutes);
  }

  /**
   * Gets the Ingredients used in the Recipe. Returns a copy of the list.
   * 
   * @return the Ingredients used in the Recipe.
   */
  public List<Ingredient> getIngredients()
  {
    List<Ingredient> compositeList = new ArrayList<Ingredient>();
    for (Recipe subRecipe : subRecipes)
    {
      compositeList.addAll(subRecipe.getIngredients());
    }
    compositeList.addAll(ingredients);
    return compositeList;
  }

  /**
   * Gets the Utensils used in the Recipe. Returns a copy of the list.
   * 
   * @return the Utensils used in the Recipe.
   */
  public List<Utensil> getUtensils()
  {
    List<Utensil> compositeList = new ArrayList<Utensil>();
    for (Recipe subRecipe : subRecipes)
    {
      compositeList.addAll(subRecipe.getUtensils());
    }
    compositeList.addAll(utensils);
    return compositeList;
  }

  /**
   * Gets the Steps to follow in order to make the Recipe. Returns a copy of the list.
   * 
   * @return the Steps to follow in order to make the Recipe.
   */
  public List<Step> getSteps()
  {
    return new ArrayList<Step>(steps);
  }

  /**
   * Calculates the total number of calories in the Recipe by totaling each Ingredient's calorie
   * count.
   * 
   * @return the total number of calories in the Recipe
   */
  public double calculateCalories()
  {
    double result = 0;
    for (Ingredient ingredient : ingredients)
    {
      result += ingredient.getCaloriesPerGram();
    }

    for (Recipe recipe : subRecipes)
    {
      result += recipe.calculateCalories();
    }

    return result;
  }

  /**
   * Serializes this recipe into a file name filename.rcp.
   * 
   * @param fileName
   *          The name of the file to write this to, not including the .rcp extension.
   * @throws IOException
   *           if any exception occurs during the writing process.
   */
  public void write(final String fileName) throws IOException
  {
    ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName + FILEEXT));

    out.writeObject(this);
    out.flush();
    out.close();
  }

  /**
   * Attempts to read a Recipe from the given file.
   * 
   * @param fileName
   *          The name of the file to read from, should not include the .rcp extension.
   * @return The recipe from the file.
   * @throws IOException
   *           if an IO error occurs.
   * @throws ClassNotFoundException
   *           if the object from the file is not a Recipe
   */
  public static Recipe read(final String fileName) throws IOException
  {
    ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName + FILEEXT));

    Recipe recipe;

    try
    {
      recipe = (Recipe) in.readObject();
    }
    catch (ClassNotFoundException e)
    {
      recipe = null;
    }
    
    in.close();

    return recipe;
  }
  
  private boolean ingredientHasSubstitutes(final Ingredient ingredient) {
    if (substitutes.containsKey(ingredient)) {
      return substitutes.get(ingredient).size() > 0;
    }
    return false;
  }

  /**
   * A helper method to determine if the given Ingredient is included in any of the steps of the
   * Recipe.
   * 
   * @param ingredient
   *          the ingredient to check.
   * @return true if the ingredient is used in any step, false otherwise.
   */
  private boolean stepUsesIngredient(final Ingredient ingredient)
  {
    for (Step step : steps)
    {
      if (step.getIngredient() != null && step.getIngredient().equals(ingredient))
      {
        return true;
      }
    }

    return false;
  }
}
