package gui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import recipes.Meal;
import recipes.Recipe;
import recipes.Step;
import recipes.Utensil;
import utilities.SortLists;

/**
 * GUI for the process viewer. This allows the user to view the list of utensils and steps in a
 * recipe.
 * 
 * @version 3/29/23
 * @author Allie O'Keeffe, KichIntel
 *
 */
public class ProcessViewer extends JFrame implements Serializable
{

  private static final long serialVersionUID = 1L;
  private static final String RECIPEEXT = "rcp";
  private static final String MEALEXT = "mel";

  /**
   * Recipe constructor.
   * 
   * @param recipe
   */
  public ProcessViewer(final Recipe recipe)
  {
    super(String.format("KiLowBites Process Viewer	%s", recipe.getName()));
    setUp(recipe);
  }

  /**
   * Meal constructor.
   * 
   * @param recipe
   */
  public ProcessViewer(final Meal meal)
  {
    super(String.format("KiLowBites Process Viewer	%s", meal.getName()));
    setUp(meal);
  }

  /**
   * Sets up to panel for the utensils.
   * 
   * @param utensils
   *          The list of utensils used in a recipe
   * @return A scrollable panel with a border and list of utensils
   */
  private JScrollPane setUpUtensils(final List<Utensil> utensils)
  {
    JTextArea textArea = new JTextArea();
    SortLists.sortUtensils(utensils); // Added since change to Recipe's get() methods do not return
                                      // an automatically sorted list anymore - Jack, 3/30
    for (Utensil item : utensils)
    {
      textArea.append(String.format("- %s\n", item.getName()));
    }
    textArea.setEditable(false);
    JScrollPane p = new JScrollPane(textArea);
    p.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

    p.setBorder(BorderFactory.createTitledBorder("Utensils"));
    p.setPreferredSize(new Dimension(575, 100));
    return p;
  }

  /**
   * Sets up to panel for the steps.
   * 
   * @param steps
   *          The list of steps used in a recipe
   * @return A scrollable panel with a border and list of steps
   */
  private JScrollPane setUpSteps(final List<Step> steps)
  {
    JTextArea textArea = new JTextArea();
    for (Step item : steps)
    {
      textArea.append(String.format("- %s\n", item.getAction()));
    }
    textArea.setEditable(false);
    JScrollPane p = new JScrollPane(textArea);
    p.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

    p.setBorder(BorderFactory.createTitledBorder("Steps"));
    p.setPreferredSize(new Dimension(575, 300));
    return p;
  }

  /**
   * Sets up the main frame for the process viewer. This adds utensils and steps to the main frame.
   * 
   * @param recipe
   *          The recipe the process viewer is looking at
   */
  private void setUp(final Recipe recipe)
  {
    JScrollPane p;
    Container c;

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    c = getContentPane();
    p = setUpUtensils(recipe.getUtensils());
    c.setLayout(new FlowLayout());
    c.add(p);

    p = setUpSteps(recipe.getSteps());
    c.add(p);
    setSize(600, 450);
    setVisible(true);
  }

  /**
   * Sets up the main frame for the process viewer. This adds utensils and steps to the main frame.
   * 
   * @param recipe
   *          The recipe the process viewer is looking at
   */
  private void setUp(final Meal meal)
  {
    JScrollPane p;
    Container c;

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    c = getContentPane();

    // Gets each utensil and step in the meals
    ArrayList<Utensil> utensils = new ArrayList<>();
    ArrayList<Step> steps = new ArrayList<>();
    for (Recipe recipe : meal.getRecipes())
    {
      for (Utensil utensil : recipe.getUtensils())
      {
        if (!utensils.contains(utensil))
          utensils.add(utensil);
      }
      for (Step step : recipe.getSteps())
      {
        steps.add(step);
      }
    }

    p = setUpUtensils(utensils);
    c.setLayout(new FlowLayout());
    c.add(p);

    p = setUpSteps(steps);
    c.add(p);
    setSize(600, 450);
    setVisible(true);
  }

  // public void writeRecipe(String filename) throws IOException
  // {
  // ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename + RECIPEEXT));
  //
  // out.writeObject(this);
  // out.flush();
  // out.close();
  // }
  //
  // public void writeMeal(String filename) throws IOException
  // {
  // ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename + MEALEXT));
  //
  // out.writeObject(this);
  // out.flush();
  // out.close();
  // }

  /**
   * 
   * @param filename
   * @return a recipe
   * @throws IOException
   */
  public static Recipe openRecipe(final String filename) throws IOException
  {
    // error on this line
    ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename + ".rcp"));
    Recipe recipe;
    try
    {
      recipe = (Recipe) in.readObject();
    }
    catch (ClassNotFoundException cnfe)
    {
      recipe = new Recipe(filename, 0, null, null, null);
    }
    in.close();

    return recipe;
  }

  /**
   * 
   * @param filename
   * @return a meal
   * @throws IOException
   */
  public static Meal openMeal(final String filename) throws IOException
  {
    ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename + ".mel"));
    Meal meal;
    try
    {
      meal = (Meal) in.readObject();
    }
    catch (ClassNotFoundException cnfe)
    {
      meal = new Meal(filename, null, 0);
    }
    in.close();
    return meal;
  }

}
