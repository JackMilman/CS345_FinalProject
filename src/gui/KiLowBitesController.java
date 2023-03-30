package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

import recipes.Recipe;

/**
 * Class that makes each of the menu bar items do their intended jobs.
 * 
 * @version 3/29/2023 Version 1
 * @author Shelsey Vega
 *
 */
public class KiLowBitesController implements ActionListener
{
  public static final String EXIT = "Exit";
  public static final String RECIPE = "Recipe";
  public static final String MEAL = "Meal";
  public static final String SHOPPING = "Shopping List";
  public static final String PROCESS = "Process";


  public JFrame main;
  public Recipe recipe;

  public KiLowBitesController(final JFrame main)
  {
    this.main = main;
//    this.recipe = recipe;
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    if (e.getActionCommand().equals(EXIT))
    {
      System.exit(0);
    }
    if (e.getActionCommand().equals(RECIPE))
    {
      new RecipeEditor(main);
    }
    if (e.getActionCommand().equals(MEAL))
    {
      new MealEditor(main);
    }
    if (e.getActionCommand().equals(SHOPPING))
    {
      // how do i get it to have a recipe thats not null?
      new ShoppingListViewer(recipe);
    }
    if (e.getActionCommand().equals(PROCESS))
    {
      // how do i get it to have a recipe thats not null?
      new ProcessViewer(recipe);
    }
  }

}
