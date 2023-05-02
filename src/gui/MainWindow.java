package gui;

import java.awt.BorderLayout;
import java.awt.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

import branding.KitchIntelJFrame;
import branding.KitchIntelMenuBar;
import branding.Logo;
import config.Translator;

/**
 * Main Window of the GUI for the KiLowBites application.
 * 
 * @author Shelsey Vega, KitchIntel
 * @version 3/29/2023 Version 1
 *
 */
public class MainWindow extends KitchIntelJFrame implements Runnable
{

  private static final long serialVersionUID = 1L;
  private static ArrayList<Component> allCreatedWindows = new ArrayList<>();
  private HashMap<String, String> shortcutsMap;

//  public MainWindow()
//  {
//  }

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

  /**
   * Add a new window.
   * 
   * @param window
   */
  public static void addNewWindow(final Component window)
  {
    allCreatedWindows.add(window);
  }

  /**
   * Get all created windows.
   * 
   * @return all created windows
   */
  public static ArrayList<Component> getAllCreatedWindows()
  {
    return allCreatedWindows;
  }

  @Override
  public void run()
  {
    // set the the frame
    setTitle(Translator.translate("KiLowBites Main Window"));
    getContentPane().setLayout(new BorderLayout());
    setDefaultCloseOperation(EXIT_ON_CLOSE);

    // Construct the controller
    KiLowBitesController controller = new KiLowBitesController(this);

    // create a menu bar and add the items
    JMenuBar menuBar = new KitchIntelMenuBar();
    setJMenuBar(menuBar);

    // The file Menu
    JMenu file = new JMenu(Translator.translate("File"));
    menuBar.add(file);
    // Exit: All windows are closed
    JMenuItem exit = new JMenuItem(Translator.translate(KiLowBitesController.EXIT));
    exit.addActionListener(controller);
    exit.setActionCommand(KiLowBitesController.EXIT);
    file.add(exit);

    // The Edit Menu
    JMenu edit = new JMenu(Translator.translate("Edit"));
    menuBar.add(edit);
    // Recipe: A RecipeEditor is opened
    JMenuItem recipe = new JMenuItem(Translator.translate(KiLowBitesController.RECIPE));
    recipe.addActionListener(controller);
    recipe.setActionCommand(KiLowBitesController.RECIPE);
    edit.add(recipe);
    // Meal: A MealEditor is opened
    JMenuItem meal = new JMenuItem(Translator.translate(KiLowBitesController.MEAL));
    meal.addActionListener(controller);
    meal.setActionCommand(KiLowBitesController.MEAL);
    edit.add(meal);

    // The View Menu
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

    // The Tool Menu
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

    // The Configure Menu
    JMenu configure = new JMenu(Translator.translate("Configure"));
    menuBar.add(configure);
    // Preferences
    JMenuItem preferences = new JMenuItem(Translator.translate("Preferences"));
    configure.add(preferences);
    preferences.addActionListener(controller);
    preferences.setActionCommand("Preferences");
    // Shortcuts
    JMenuItem shortcuts = new JMenuItem(Translator.translate("Shortcuts"));
    configure.add(shortcuts);
    shortcuts.addActionListener(controller);
    shortcuts.setActionCommand("Shortcuts");

    // The Help Menu
    JMenu help = new JMenu(Translator.translate("Help"));
    menuBar.add(help);
    // Open the user guide in the default browser
    JMenuItem userGuide = new JMenuItem(Translator.translate("User Guide"));
    help.add(userGuide);
    userGuide.addActionListener(controller);
    userGuide.setActionCommand("User Guide");

    addShortcuts();

    // add the company logo to the window
    // Josiah's changes:
    ImageIcon logo = new ImageIcon(getClass().getClassLoader().getResource(Logo.path()));
    JLabel logoLabel = new JLabel(logo);
    getContentPane().add(logoLabel, BorderLayout.CENTER);
    getContentPane().add(logoLabel, BorderLayout.CENTER);

    PreferenceWindow.changeFont(this);
    MainWindow.addNewWindow(this);
    setSize(700, 500);

    setVisible(true);

  }

  // Load shortcuts from the .cfg file
  private void loadShortcuts()
  {
    shortcutsMap = new HashMap<>();
    try (BufferedReader reader = new BufferedReader(new FileReader("shortcuts.cfg")))
    {
      String line;
      while ((line = reader.readLine()) != null)
      {
        String[] parts = line.split("=");
        shortcutsMap.put(parts[0], parts[1]);
      }
    }
    catch (IOException e)
    {
      System.err.println("Failed to load shortcuts: " + e.getMessage());
    }
  }

  // Add shortcuts to the menu items
  private void addShortcuts()
  {
    // Load the shortcuts from the .cfg file
    loadShortcuts();

    // Get the menu bar of the main window
    JMenuBar menuBar = this.getJMenuBar();

    // Loop through the menu items and set their shortcuts
    for (int i = 0; i < menuBar.getMenuCount(); i++)
    {
      JMenu menu = menuBar.getMenu(i);
      for (int j = 0; j < menu.getItemCount(); j++)
      {
        JMenuItem menuItem = menu.getItem(j);
        String actionCommand = menuItem.getActionCommand();
        String shortcut = shortcutsMap.get(actionCommand);
        if (shortcut != null)
        {
          KeyStroke keyStroke = KeyStroke.getKeyStroke(shortcut);
          if (keyStroke != null)
          {
            menuItem.setAccelerator(keyStroke);
          }
        }
      }
    }
  }

}
