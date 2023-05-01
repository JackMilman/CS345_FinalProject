package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Color;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.lang.reflect.InvocationTargetException;
import java.io.IOException;
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
import config.Shortcut;
import config.Translator;
import utilities.ShortcutsParser;

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
  private Map<String, KeyStroke> shortcuts = new HashMap<>();
  // public MainWindow()
  // {
  // }

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
  
  public static void addNewWindow(Component window) {
    allCreatedWindows.add(window);
  }
  
  public static ArrayList<Component> getAllCreatedWindows(){
    return allCreatedWindows;
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


    // Create a new instance of CustomAction and set its name and frame
    CustomAction myAction = new CustomAction("Custom Action", this);

    // Create a new instance of ShortcutsDialog with a reference to this MainWindow

    // Show the ShortcutsDialog when the "Shortcuts" menu item is clicked
    // JMenuItem shortcutsMenuItem = new JMenuItem("Shortcuts");
    // shortcutsMenuItem.addActionListener(e -> shortcutsDialog.setVisible(true));
    // view.add(shortcutsMenuItem);

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
    edit.setMnemonic(KeyEvent.VK_R);

    // Recipe: A RecipeEditor is opened
    JMenuItem recipe = new JMenuItem(Translator.translate(KiLowBitesController.RECIPE));
    recipe.addActionListener(controller);
    recipe.setActionCommand(KiLowBitesController.RECIPE);
    // recipe.setAction(myAction);
    //recipe.addKeyListener(keyListener);
    edit.add(recipe);
    KeyStroke openRecipe = KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK);
    recipe.setAccelerator(openRecipe);
    
    // Meal: A MealEditor is opened
    JMenuItem meal = new JMenuItem(Translator.translate(KiLowBitesController.MEAL));
    meal.addActionListener(controller);
    meal.setActionCommand(KiLowBitesController.MEAL);
    edit.add(meal);
    KeyStroke openMeal = KeyStroke.getKeyStroke(KeyEvent.VK_M, InputEvent.CTRL_DOWN_MASK);
    meal.setAccelerator(openMeal);

    JMenu view = new JMenu(Translator.translate("View"));
    menuBar.add(view);

    // Shopping List: A ShoppingListViewer is opened
    JMenuItem shoppingList = new JMenuItem(Translator.translate(KiLowBitesController.SHOPPING));
    shoppingList.addActionListener(controller);
    shoppingList.setActionCommand(KiLowBitesController.SHOPPING);
    view.add(shoppingList);
    KeyStroke viewList = KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_DOWN_MASK);
    shoppingList.setAccelerator(viewList);

    // Process: A ProcessViewer is opened
    JMenuItem process = new JMenuItem(Translator.translate(KiLowBitesController.PROCESS));
    process.addActionListener(controller);
    process.setActionCommand(KiLowBitesController.PROCESS);
    view.add(process);
    KeyStroke viewProcess = KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_DOWN_MASK);
    process.setAccelerator(viewProcess);

    // Inventory: A InventoryViewer is opened
    JMenuItem inventory = new JMenuItem(Translator.translate(KiLowBitesController.INVENTORY));
    inventory.addActionListener(controller);
    inventory.setActionCommand(KiLowBitesController.INVENTORY);
    view.add(inventory);
    KeyStroke viewInventory = KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_DOWN_MASK);
    inventory.setAccelerator(viewInventory);

    JMenu tools = new JMenu(Translator.translate("Tools"));
    menuBar.add(tools);

    // Calorie Calculator: Calorie Calculator is opened
    JMenuItem calorieCalculator = new JMenuItem(
        Translator.translate(KiLowBitesController.CALORIECALCULATOR));
    calorieCalculator.addActionListener(controller);
    calorieCalculator.setActionCommand(KiLowBitesController.CALORIECALCULATOR);
    tools.add(calorieCalculator);
    KeyStroke calCalc = KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK);
    calorieCalculator.setAccelerator(calCalc);

    // Units Converter: UnitConversionWindow is opened
    JMenuItem unitsConverter = new JMenuItem(
        Translator.translate(KiLowBitesController.UNITSCONVERTER));
    unitsConverter.addActionListener(controller);
    unitsConverter.setActionCommand(KiLowBitesController.UNITSCONVERTER);
    tools.add(unitsConverter);
    KeyStroke unitC = KeyStroke.getKeyStroke(KeyEvent.VK_U, InputEvent.CTRL_DOWN_MASK);
    unitsConverter.setAccelerator(unitC);

    JMenu configure = new JMenu(Translator.translate("Configure"));
    menuBar.add(configure);
    JMenuItem preferences = new JMenuItem(Translator.translate("Preferences"));
    configure.add(preferences);
    preferences.addActionListener(controller);
    preferences.setActionCommand("Preferences");
    KeyStroke prefs = KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_DOWN_MASK);
    preferences.setAccelerator(prefs);

    JMenuItem shortcuts = new JMenuItem(Translator.translate("Shortcuts"));
    configure.add(shortcuts);
    shortcuts.addActionListener(controller);
    shortcuts.setActionCommand("Shortcuts");
    KeyStroke keys = KeyStroke.getKeyStroke(KeyEvent.VK_K, InputEvent.CTRL_DOWN_MASK);
    shortcuts.setAccelerator(keys);

    // JMenuItem nutrition = new JMenuItem(Translator.translate("Nutrition"));
    // configure.add(nutrition);

    JMenu help = new JMenu(Translator.translate("Help"));
    menuBar.add(help);
    // Open the user guide in the default browser
    JMenuItem userGuide = new JMenuItem(Translator.translate("User Guide"));
    help.add(userGuide);
    userGuide.addActionListener(controller);
    userGuide.setActionCommand("User Guide");
    KeyStroke guide = KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.CTRL_DOWN_MASK);
    userGuide.setAccelerator(guide);

    // add the company logo to the window
    // Josiah's changes:
    ImageIcon logo = new ImageIcon(getClass().getClassLoader().getResource(Logo.path()));
    JLabel logoLabel = new JLabel(logo);
    getContentPane().add(logoLabel, BorderLayout.CENTER);
    getContentPane().add(logoLabel, BorderLayout.CENTER);

    setVisible(true);

  }

}
