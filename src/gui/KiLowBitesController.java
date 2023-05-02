package gui;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;

import config.CustomAction;
import config.Shortcut;
import config.Translator;
import preferences.KitchIntelPreferenceReader;
import recipes.Meal;
import recipes.Recipe;
import utilities.ResourceCopier;
import utilities.ShortcutsParser;

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
  public static final String HELP = "Help";
  public static final String INGREDIENT = "Ingredient";
  public static final String RECIPE = "Recipe";
  public static final String MEAL = "Meal";
  public static final String SHOPPING = "Shopping List";
  public static final String PROCESS = "Process";
  public static final String INVENTORY = "Inventory";
  public static final String CALORIECALCULATOR = "Calorie Calculator";
  public static final String UNITSCONVERTER = "Units Converter";
  public static final String ERROR_OPENING_FILE = "Error Opening File";
  public static final String INVALID_FILE_TYPE = "Invalid File Type";
  public static final String NO_MATCHES = "No Matches";
  public static final String ERROR = "Error";
  public static final String RECIPEEXT = "rcp";
  public static final String MEALEXT = "mel";
  public static final String USERGUIDE = "User Guide";
  public static final String PREFERENCES = "Preferences";

  private JFrame main;
  private Recipe recipe;
  private Meal meal;
  private JFileChooser fileChooser;
  private FileNameExtensionFilter fileFilter;
  private Map<String, KeyStroke> shortcuts = new HashMap<>();
  private CustomAction customAction;
 

  /**
   * 
   * @param main
   */
  public KiLowBitesController(final JFrame main)
  {
    this.main = main;
    this.fileChooser = createFileChooser();
    this.fileFilter = new FileNameExtensionFilter("Recipes and Meal",
        new String[] {RECIPEEXT, MEALEXT});
    customAction = new CustomAction("My Custom Action", main);

  }
  
  private JFileChooser createFileChooser() {
    try {
      File location = new File(KitchIntelPreferenceReader.returnValue(KitchIntelPreferenceReader.DEFAULT));
      if (location.exists() && location.isDirectory()) {
        return new JFileChooser(location);
      }
    } catch (IOException e ){
      System.out.print("Error");
    }
    return new JFileChooser(new File("."));
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
      MainWindow.addNewWindow(new RecipeEditor(main));
    }

    // Open Meal Editor
    if (e.getActionCommand().equals(MEAL))
    {
      MainWindow.addNewWindow(new MealEditor(main));
    }

    // Open Unit Converter Window
    if (e.getActionCommand().equals(UNITSCONVERTER))
    {
      MainWindow.addNewWindow(UnitConversionWindow.getUnitConversionWindow());
    }

    // Open Calorie Calculator Window
    if (e.getActionCommand().equals(CALORIECALCULATOR))
    {
      MainWindow.addNewWindow(CalorieCalculatorWindow.getCalorieCalculatorWindow());
    }

    // Open ShoppingListViewer
    if (e.getActionCommand().equals(SHOPPING))
    {

      read();
      // check the extension of the file, call the corresponding type: meal or recipe
      if (recipe != null)
      {
        MainWindow.addNewWindow(new ShoppingListViewer(recipe));
      }
      else if (meal != null)
      {
        MainWindow.addNewWindow(new ShoppingListViewer(meal));
      }
    }

    // Open ProcessViewer
    if (e.getActionCommand().equals(PROCESS))
    {
      read();
      // check the extension of the file, call the corresponding type: meal or recipe
      if (recipe != null)
      {
        MainWindow.addNewWindow(new ProcessViewer(recipe));
      }
      else if (meal != null)
      {
        MainWindow.addNewWindow(new ProcessViewer(meal));
      }
    }

    // Open InventoryViewer
    if (e.getActionCommand().equals(INVENTORY))
    {
      MainWindow.addNewWindow(new InventoryWindow(main));
    }

    // open calorie calculator
    if (e.getActionCommand().equals(CALORIECALCULATOR))
    {
      MainWindow.addNewWindow(CalorieCalculatorWindow.getCalorieCalculatorWindow());
    }

    // open the User Guide in a browser
    if (e.getActionCommand().equals(USERGUIDE))
    {
      try
      {     
        Path tempFolder = ResourceCopier.copyResourcesToTemp("temp", "guide");

        Path fileLocation = Paths.get(tempFolder.toString(), "UserGuide.html");
        
        System.out.println(fileLocation.toString());
        
        URI uri = fileLocation.toUri();
          
        Desktop.getDesktop().browse(uri);
      }
      catch (IOException e1)
      {
        e1.printStackTrace();
      }
      catch (URISyntaxException e2)
      {
        e2.printStackTrace();
      }
    }
    
    // open preferences
    if (e.getActionCommand().equals(PREFERENCES))
    {
      MainWindow.addNewWindow(new PreferenceWindow());
    }
    // open shortcuts
    if (e.getActionCommand().equals("Shortcuts"))
    {    
      MainWindow.addNewWindow(new ShortcutsDialog(main, shortcuts));
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
    
    fileChooser = createFileChooser();

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
      if (recipe == null && meal == null)
      {
        JOptionPane.showMessageDialog(null, INVALID_FILE_TYPE, ERROR, JOptionPane.ERROR_MESSAGE);
      }
    }
  }
}
