package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
import config.CustomAction;
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
  private static ArrayList<Component> allCreatedWindows = new ArrayList<>();
  private Map<String, CustomAction> actions = new HashMap<>();
  private Map<String, KeyStroke> shortcutsMap = new HashMap<>();

  public MainWindow()
  {
  }

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

  public static void addNewWindow(Component window)
  {
    allCreatedWindows.add(window);
  }

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

    // add shortcuts to the menu items
    edit.setMnemonic(KeyEvent.VK_R);
    KeyStroke openRecipe = KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK);
    recipe.setAccelerator(openRecipe);
    KeyStroke openMeal = KeyStroke.getKeyStroke(KeyEvent.VK_M, InputEvent.CTRL_DOWN_MASK);
    meal.setAccelerator(openMeal);
    KeyStroke viewList = KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_DOWN_MASK);
    shoppingList.setAccelerator(viewList);
    KeyStroke viewProcess = KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_DOWN_MASK);
    process.setAccelerator(viewProcess);
    KeyStroke viewInventory = KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_DOWN_MASK);
    inventory.setAccelerator(viewInventory);
    KeyStroke calCalc = KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK);
    calorieCalculator.setAccelerator(calCalc);
    KeyStroke unitC = KeyStroke.getKeyStroke(KeyEvent.VK_U, InputEvent.CTRL_DOWN_MASK);
    unitsConverter.setAccelerator(unitC);
    KeyStroke prefs = KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_DOWN_MASK);
    preferences.setAccelerator(prefs);
    KeyStroke keys = KeyStroke.getKeyStroke(KeyEvent.VK_K, InputEvent.CTRL_DOWN_MASK);
    shortcuts.setAccelerator(keys);
    KeyStroke guide = KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.CTRL_DOWN_MASK);
    userGuide.setAccelerator(guide);

    // Fill in the current shortcuts HashMap
    shortcutsMap.put(KiLowBitesController.RECIPE, openRecipe);
    shortcutsMap.put(KiLowBitesController.MEAL, openMeal);
    shortcutsMap.put(KiLowBitesController.SHOPPING, viewList);
    shortcutsMap.put(KiLowBitesController.PROCESS, viewProcess);
    shortcutsMap.put(KiLowBitesController.INVENTORY, viewInventory);
    shortcutsMap.put(KiLowBitesController.CALORIECALCULATOR, calCalc);
    shortcutsMap.put(KiLowBitesController.UNITSCONVERTER, unitC);
    shortcutsMap.put(KiLowBitesController.PREFERENCES, prefs);
    shortcutsMap.put("Shortcuts", keys);
    shortcutsMap.put(KiLowBitesController.USERGUIDE, guide);
    
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

}
