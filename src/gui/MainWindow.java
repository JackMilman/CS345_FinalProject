package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
import config.Shortcut;
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
  private HashMap<String, String> shortcutsMap;

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

    // // add shortcuts to the menu items
    // edit.setMnemonic(KeyEvent.VK_R);
    // KeyStroke openRecipe = KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK);
    // recipe.setAccelerator(openRecipe);
    // KeyStroke openMeal = KeyStroke.getKeyStroke(KeyEvent.VK_M, InputEvent.CTRL_DOWN_MASK);
    // meal.setAccelerator(openMeal);
    // KeyStroke viewList = KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_DOWN_MASK);
    // shoppingList.setAccelerator(viewList);
    // KeyStroke viewProcess = KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_DOWN_MASK);
    // process.setAccelerator(viewProcess);
    // KeyStroke viewInventory = KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_DOWN_MASK);
    // inventory.setAccelerator(viewInventory);
    // KeyStroke calCalc = KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK);
    // calorieCalculator.setAccelerator(calCalc);
    // KeyStroke unitC = KeyStroke.getKeyStroke(KeyEvent.VK_U, InputEvent.CTRL_DOWN_MASK);
    // unitsConverter.setAccelerator(unitC);
    // KeyStroke prefs = KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_DOWN_MASK);
    // preferences.setAccelerator(prefs);
    // KeyStroke keys = KeyStroke.getKeyStroke(KeyEvent.VK_K, InputEvent.CTRL_DOWN_MASK);
    // shortcuts.setAccelerator(keys);
    // KeyStroke guide = KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.CTRL_DOWN_MASK);
    // userGuide.setAccelerator(guide);

    try
    {
      shortcutsMap = readShortcutsFile();
    }
    catch (IOException e)
    {
      // handle file reading error
      e.printStackTrace();
    }

    for (Component component : menuBar.getComponents())
    {
      if (component instanceof JMenu)
      {
        JMenu menu = (JMenu) component;
        for (Component menuItemComponent : menu.getMenuComponents())
        {
          if (menuItemComponent instanceof JMenuItem)
          {
            JMenuItem menuItem = (JMenuItem) menuItemComponent;
            String actionCommand = menuItem.getActionCommand();
            String shortcut = shortcutsMap.get(actionCommand);
            if (shortcut != null)
            {
              menuItem.setAccelerator(KeyStroke.getKeyStroke(shortcut));
            }
          }
        }
      }
    }

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

  private HashMap<String, String> readShortcutsFile() throws IOException
  {
    HashMap<String, String> shortcutsMap = new HashMap<>();
    BufferedReader reader = new BufferedReader(new FileReader("shortcuts.cfg"));
    String line;
    while ((line = reader.readLine()) != null)
    {
      String[] parts = line.split(":");
      if (parts.length == 2)
      {
        String actionCommand = parts[0].trim();
        String shortcut = parts[1].trim();
        shortcutsMap.put(actionCommand, shortcut);
      }
    }
    reader.close();
    return shortcutsMap;
  }

}
