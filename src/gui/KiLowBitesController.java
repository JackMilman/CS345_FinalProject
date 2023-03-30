package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

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

  public JFrame main;

  public KiLowBitesController(final JFrame main)
  {
    this.main = main;
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    // the exit tab in the menu bar will close all the windows.
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
      // how to get the shopping list viewer to show up?
      // other windows parameter is a window but this one is a recipe.
      new ShoppingListViewer(null);
    }
  }

}
