package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.lang.reflect.InvocationTargetException;

import javax.swing.*;

/**
 * Main Window of the GUI for the KiLowBites application.
 * 
 * @version 3/29/2023 Version 1
 * @author Shelsey Vega
 *
 */
public class MainWindow extends JFrame implements Runnable
{
  private static final long serialVersionUID = 1L;
  private static final String PATH = "images/KILowBites_Logo.png";

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
    getContentPane().setBackground(Color.WHITE);
    setTitle("KiLowBites Main Window");
    getContentPane().setLayout(new BorderLayout());
    setSize(700, 500);
    setDefaultCloseOperation(EXIT_ON_CLOSE);

    // Construct the controller
    KiLowBitesController controller = new KiLowBitesController(this);

    // create a menu bar and add the items
    JMenuBar menuBar = new JMenuBar();
    setJMenuBar(menuBar);

    JMenu file = new JMenu("File");
    menuBar.add(file);
    // Exit: All windows are closed
    JMenuItem exit = new JMenuItem("Exit");
    exit.addActionListener(controller);
    file.add(exit);

    JMenu edit = new JMenu("Edit");
    menuBar.add(edit);
    // Recipe: A RecipeEditor is opened
    JMenuItem recipe = new JMenuItem("Recipe");
    recipe.addActionListener(controller);
    edit.add(recipe);
    // Meal: A MealEditor is opened
    JMenuItem meal = new JMenuItem("Meal");
    meal.addActionListener(controller);
    edit.add(meal);

    // not using search in the 1st sprint.
    // JMenu search = new JMenu("Search");
    // menuBar.add(search);
    // // Recipes: The user is prompted for the ingredients of interest
    // JMenuItem recipes = new JMenuItem("Recipes");
    // search.add(recipes);
    // // Meals: The user is prompted for the ingredients of interest
    // JMenuItem meals = new JMenuItem("Meals");
    // search.add(meals);

    JMenu view = new JMenu("View");
    menuBar.add(view);
    // Shopping List: A ShoppingListViewer is opened
    JMenuItem shoppingList = new JMenuItem("Shopping List");
    shoppingList.addActionListener(controller);
    view.add(shoppingList);
    // Process: A ProcessViewer is opened
    JMenuItem process = new JMenuItem("Process");
    process.addActionListener(controller);
    view.add(process);

    JMenu tools = new JMenu("Tools");
    menuBar.add(tools);
    // Calorie Calculator: Calorie Calculator is opened
    JMenuItem calorieCalculator = new JMenuItem("Calorie Calculator");
    calorieCalculator.addActionListener(controller);
    tools.add(calorieCalculator);
    // Units Converter: UnitConversionWindow is opened
    JMenuItem unitsConverter = new JMenuItem("Units Converter");
    unitsConverter.addActionListener(controller);
    tools.add(unitsConverter);

    // Menu Items not being used
    // JMenu configure = new JMenu("Configure");
    // menuBar.add(configure);
    // JMenuItem preferences = new JMenuItem("Preferences");
    // configure.add(preferences);
    // JMenuItem shortcuts = new JMenuItem("Shortcuts");
    // configure.add(shortcuts);
    // JMenuItem nutrition = new JMenuItem("Nutrition");
    // configure.add(nutrition);
    //
    // JMenu help = new JMenu("Help");
    // menuBar.add(help);
    // JMenuItem about = new JMenuItem("About");
    // help.add(about);
    // JMenuItem userGuide = new JMenuItem("User Guide");
    // help.add(userGuide);

    // add the company logo to the window
    // Josiah's changes:
//    ImageIcon logo = new ImageIcon(getClass().getClassLoader().getResource("KILowBites_Logo.png"));
//    JLabel logoLabel = new JLabel(logo);
//    getContentPane().add(logoLabel, BorderLayout.CENTER);
    ImageIcon logo = new ImageIcon(PATH);
    JLabel logoLabel = new JLabel(logo);
    getContentPane().add(logoLabel, BorderLayout.CENTER);
    setVisible(true);
  }
}
