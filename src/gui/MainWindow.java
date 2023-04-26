package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.lang.reflect.InvocationTargetException;

import javax.swing.*;

import branding.KitchIntelJFrame;
import branding.KitchIntelMenuBar;
import branding.Logo;
import config.Language;
import config.Translator;

/**
 * Main Window of the GUI for the KiLowBites application.
 * 
 * @version 3/29/2023 Version 1
 * @author Shelsey Vega
 *
 */
public class MainWindow extends KitchIntelJFrame implements Runnable
{
  private static final long serialVersionUID = 1L;

  /**
   * 
   * @param args
   * @throws InvocationTargetException
   * @throws InterruptedException
   */
  public static void main(final String[] args)
      throws InvocationTargetException, InterruptedException
  {
    SwingUtilities.invokeAndWait(new MainWindow());
  }

  @Override
  public void run()
  {
    // set the the frame
    setTitle(Translator.translate("KiLowBites Main Window"));
    getContentPane().setLayout(new BorderLayout());
    setSize(700, 500);
    setDefaultCloseOperation(EXIT_ON_CLOSE);

    // Construct the controller
    KiLowBitesController controller = new KiLowBitesController(this);

    // create a menu bar and add the items
    JMenuBar menuBar = new KitchIntelMenuBar();
    setJMenuBar(menuBar);

    JMenu file = new JMenu(Translator.translate("File"));
    menuBar.add(file);
    // Exit: All windows are closed
    JMenuItem exit = new JMenuItem(Translator.translate(KiLowBitesController.EXIT));
    exit.addActionListener(controller);
    exit.setActionCommand(KiLowBitesController.EXIT);
    file.add(exit);

    JMenu edit = new JMenu(Translator.translate("Edit"));
    menuBar.add(edit);
    // Recipe: A RecipeEditor is opened
    JMenuItem recipe = new JMenuItem(Translator.translate(KiLowBitesController.RECIPE));
    recipe.addActionListener(controller);
    recipe.setActionCommand(KiLowBitesController.RECIPE);
    //recipe.addKeyListener(keyListener);
    edit.add(recipe);
    // Meal: A MealEditor is opened
    JMenuItem meal = new JMenuItem(Translator.translate(KiLowBitesController.MEAL));
    meal.addActionListener(controller);
    meal.setActionCommand(KiLowBitesController.MEAL);
    edit.add(meal);
    

    // not using search in the 1st sprint.
    // JMenu search = new JMenu(Translator.translate("Search"));
    // menuBar.add(search);
    // // Recipes: The user is prompted for the ingredients of interest
    // JMenuItem recipes = new JMenuItem(Translator.translate("Recipes"));
    // recipes.addActionCommand("Recipes");
    // search.add(recipes);
    // // Meals: The user is prompted for the ingredients of interest
    // JMenuItem meals = new JMenuItem("Meals");
    // meals.setActionCommand("Meals");
    // search.add(meals);

    JMenu view = new JMenu(Translator.translate("View"));
    menuBar.add(view);
    // Shopping List: A ShoppingListViewer is opened
    JMenuItem shoppingList = new JMenuItem(Translator.translate(KiLowBitesController.SHOPPING));
    shoppingList.addActionListener(controller);
    shoppingList.setActionCommand(KiLowBitesController.SHOPPING);
    view.add(shoppingList);
    // Process: A ProcessViewer is opened
    JMenuItem process = new JMenuItem(Translator.translate(KiLowBitesController.PROCESS));
    process.addActionListener(controller);
    process.setActionCommand(KiLowBitesController.PROCESS);
    view.add(process);
    // Inventory: A InventoryViewer is opened
    JMenuItem inventory = new JMenuItem(Translator.translate(KiLowBitesController.INVENTORY));
    inventory.addActionListener(controller);
    inventory.setActionCommand(KiLowBitesController.INVENTORY);
    view.add(inventory);

    JMenu tools = new JMenu(Translator.translate("Tools"));
    menuBar.add(tools);
    // Calorie Calculator: Calorie Calculator is opened
    JMenuItem calorieCalculator = new JMenuItem(
        Translator.translate(KiLowBitesController.CALORIECALCULATOR));
    calorieCalculator.addActionListener(controller);
    calorieCalculator.setActionCommand(KiLowBitesController.CALORIECALCULATOR);
    tools.add(calorieCalculator);
    // Units Converter: UnitConversionWindow is opened
    JMenuItem unitsConverter = new JMenuItem(
        Translator.translate(KiLowBitesController.UNITSCONVERTER));
    unitsConverter.addActionListener(controller);
    unitsConverter.setActionCommand(KiLowBitesController.UNITSCONVERTER);
    tools.add(unitsConverter);

    JMenu configure = new JMenu(Translator.translate("Configure"));
    menuBar.add(configure);
    JMenuItem preferences = new JMenuItem(Translator.translate("Preferences"));
    configure.add(preferences);
    preferences.addActionListener(controller);
    preferences.setActionCommand("Preferences");
//    JMenuItem shortcuts = new JMenuItem(Translator.translate("Shortcuts"));
//    configure.add(shortcuts);
//    shortcuts.addActionListener(controller);
//    shortcuts.setActionCommand("Shortcuts");
//    JMenuItem nutrition = new JMenuItem(Translator.translate("Nutrition"));
//    configure.add(nutrition);

    // Help items
    JMenu help = new JMenu(Translator.translate("Help"));
    menuBar.add(help);
//    // JMenuItem about = new JMenuItem(Translator.translate("About"));
//    // help.add(about);
    // Open the user guide in the default browser
    JMenuItem userGuide = new JMenuItem(Translator.translate("User Guide"));
    help.add(userGuide);
    userGuide.addActionListener(controller);
    userGuide.setActionCommand("User Guide");

    // add the company logo to the window
    // Josiah's changes:
    ImageIcon logo = new ImageIcon(getClass().getClassLoader().getResource(Logo.path()));
    JLabel logoLabel = new JLabel(logo);
    getContentPane().add(logoLabel, BorderLayout.CENTER);
    // ImageIcon logo = new ImageIcon(PATH);
    // JLabel logoLabel = new JLabel(logo);
    getContentPane().add(logoLabel, BorderLayout.CENTER);
    setVisible(true);
  }
}
