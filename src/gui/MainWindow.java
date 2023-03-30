package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.*;

import recipes.Recipe;

/**
 * Main Window of the GUI for the KiLowBites application.
 * 
 * @version 3/29/2023 Version 1
 * @author Shelsey Vega
 *
 */
public class MainWindow extends JFrame
{
  private static final long serialVersionUID = 1L;
  private static final String PATH = "images/KILowBites_Logo.png";

  /**
   * Main Window of the KiLowBites application.
   */
  public MainWindow()
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

    JMenu File = new JMenu("File");
    menuBar.add(File);
    // Exit: All windows are closed
    JMenuItem Exit = new JMenuItem("Exit");
    Exit.addActionListener(controller);
    File.add(Exit);

    JMenu Edit = new JMenu("Edit");
    menuBar.add(Edit);
    // Recipe: A RecipeEditor is opened
    JMenuItem Recipe = new JMenuItem("Recipe");
    Recipe.addActionListener(controller);
    Edit.add(Recipe);
    // Meal: A MealEditor is opened
    JMenuItem Meal = new JMenuItem("Meal");
    Meal.addActionListener(controller);
    Edit.add(Meal);

    JMenu Search = new JMenu("Search");
    menuBar.add(Search);
    // Recipes: The user is prompted for the ingredients of interest
    JMenuItem Recipes = new JMenuItem("Recipes");
    Search.add(Recipes);
    // Meals: The user is prompted for the ingredients of interest
    JMenuItem Meals = new JMenuItem("Meals");
    Search.add(Meals);

    JMenu View = new JMenu("View");
    menuBar.add(View);
    // Shopping List: A ShoppingListViewer is opened
    JMenuItem shoppingList = new JMenuItem("Shopping List");
    shoppingList.addActionListener(controller);
    View.add(shoppingList);
    // Process: A ProcessViewer is opened
    JMenuItem Process = new JMenuItem("Process");
    View.add(Process);

    JMenu Tools = new JMenu("Tools");
    menuBar.add(Tools);
    JMenuItem calorieCalculator = new JMenuItem("Calorie Calculator");
    Tools.add(calorieCalculator);
    JMenuItem unitsConverter = new JMenuItem("Units Converter");
    Tools.add(unitsConverter);

    JMenu Configure = new JMenu("Configure");
    menuBar.add(Configure);
    JMenuItem Preferences = new JMenuItem("Preferences");
    Configure.add(Preferences);
    JMenuItem Shortcuts = new JMenuItem("Shortcuts");
    Configure.add(Shortcuts);
    JMenuItem Nutrition = new JMenuItem("Nutrition");
    Configure.add(Nutrition);

    JMenu Help = new JMenu("Help");
    menuBar.add(Help);
    JMenuItem About = new JMenuItem("About");
    Help.add(About);
    JMenuItem userGuide = new JMenuItem("User Guide");
    Help.add(userGuide);

    // add the company logo to the window
    ImageIcon logo = new ImageIcon(PATH);
    JLabel logoLabel = new JLabel(logo);
    getContentPane().add(logoLabel, BorderLayout.CENTER);
    setVisible(true);
  }

  public static void main(String[] args)
  {
    new MainWindow();
  }
}
