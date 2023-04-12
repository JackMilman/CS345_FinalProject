package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import config.Translator;
import recipes.Meal;
import recipes.Recipe;
import javax.swing.JFileChooser;

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
  public static final String CALORIECALCULATOR = "Calorie Calculator";
  public static final String UNITSCONVERTER = "Units Converter";
  public static final String ERROR_OPENING_FILE = "Error Opening File";
  public static final String INVALID_FILE_TYPE = "Invalid File Type";
  public static final String NO_MATCHES = "No Matches";
  public static final String ERROR = "Error";
  public static final String RECIPEEXT = "rcp";
  public static final String MEALEXT = "mel";

  private JFrame main;
  private Recipe recipe;
  private Meal meal;
  private JFileChooser fileChooser;
  private FileNameExtensionFilter fileFilter;

  /**
   * 
   * @param main
   */
  public KiLowBitesController(final JFrame main)
  {
    this.main = main;
    this.fileChooser = new JFileChooser(new File("."));
    this.fileFilter = new FileNameExtensionFilter("Recipes and Meal",
        new String[] {RECIPEEXT, MEALEXT});
  }

  @Override
  public void actionPerformed(final ActionEvent e)
  {

    // Exit Appication
    if (e.getActionCommand().equals(EXIT))
    {
      System.exit(0);
    }

    // Open RecipeEditor
    if (e.getActionCommand().equals(RECIPE))
    {
      new RecipeEditor(main);
    }

    // Open Meal Editor
    if (e.getActionCommand().equals(MEAL))
    {
      new MealEditor(main);
    }

    // Open Unit Converter Window
    if (e.getActionCommand().equals(UNITSCONVERTER))
    {
      UnitConversionWindow.getUnitConversionWindow();
    }

    // Open Calorie Calculator Window
    if (e.getActionCommand().equals(CALORIECALCULATOR))
    {
     CalorieCalculatorWindow.getCalorieCalculatorWindow();
    }

    // Open ShoppingListViewer
    if (e.getActionCommand().equals(SHOPPING))
    {

      read();
      // check the extension of the file, call the corresponding type: meal or recipe
      if (recipe != null)
      {
        new ShoppingListViewer(recipe);
      }
      else if (meal != null)
      {
        new ShoppingListViewer(meal);
      }
    }

    // Open ProcessViewer
    if (e.getActionCommand().equals(PROCESS))
    {
      read();
      // check the extension of the file, call the corresponding type: meal or recipe
      if (recipe != null)
      {
        new ProcessViewer(recipe);
      }
      else if (meal != null)
      {
        new ProcessViewer(meal);
      }
    }
   
  }

  /**
   * Read a file and determine whether it is a recipe or a meal, update the recipe or meal.
   *
   */
  private void read()
  {
    recipe = null;
    meal = null;
    
    fileChooser.setFileFilter(fileFilter);
    int result = fileChooser.showOpenDialog(null);
    fileChooser.setDialogTitle(Translator.translate("Open Recipe or Meal"));
    // int result = fileChooser.showOpenDialog(null);
    if (result == JFileChooser.APPROVE_OPTION)
    {
      String path = fileChooser.getSelectedFile().getPath();
      String[] extension = fileFilter.getExtensions();
      for (int i = 0; i < extension.length; i++)
      {

        if (path.endsWith(extension[i]))
        {
          // get the path without the extension at the end
          String name = path.substring(0, path.length() - 4);
          try
          {
            // check if it is a recipe
            if (extension[i].equals(RECIPEEXT))
            {
              // set the recipe to the recipe selected from the file viewer
              recipe = Recipe.read(name);
              return;

            }
            // check if it is a meal
            else if (extension[i].equals(MEALEXT))
            {
              // set the meal to the meal selected from the file viewer
              meal = Meal.read(name);
              return;
            }
          }
          catch (IOException ioe)
          {
            JOptionPane.showMessageDialog(null, ERROR_OPENING_FILE, ERROR,
                JOptionPane.ERROR_MESSAGE);
            break;
          }
        }
      }
      if(recipe == null && meal == null)
      {
        JOptionPane.showMessageDialog(null, INVALID_FILE_TYPE, ERROR, JOptionPane.ERROR_MESSAGE);
      }
    }
  }

}
