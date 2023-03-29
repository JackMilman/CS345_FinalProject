package gui;

import javax.swing.*;

public class MainWindow extends JFrame
{
  private static final long serialVersionUID = 1L;

  public MainWindow()
  {
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    
    // set the size of the frame
    setSize(500, 500);
    setVisible(true);

    // create a menu bar and add the items
    JMenuBar menuBar = new JMenuBar();
    setJMenuBar(menuBar);

    JMenu File = new JMenu("File");
    menuBar.add(File);
    JMenuItem Exit = new JMenuItem("Exit");
    File.add(Exit);

    JMenu Edit = new JMenu("Edit");
    menuBar.add(Edit);
    JMenuItem Recipe = new JMenuItem("Recipe");
    File.add(Recipe);
    JMenuItem Meal = new JMenuItem("Meal");
    File.add(Meal);

    JMenu Search = new JMenu("Search");
    menuBar.add(Search);
    JMenuItem Recipes = new JMenuItem("Recipes");
    File.add(Recipes);
    JMenuItem Meals = new JMenuItem("Meals");
    File.add(Meals);

    JMenu View = new JMenu("View");
    menuBar.add(View);
    JMenuItem shoppingList = new JMenuItem("ShoppingList");
    File.add(shoppingList);
    JMenuItem Process = new JMenuItem("Process");
    File.add(Process);

    JMenu Tools = new JMenu("Tools");
    menuBar.add(Tools);
    JMenuItem calorieCalculator = new JMenuItem("Calorie Calculator");
    File.add(calorieCalculator);
    JMenuItem unitsConverter = new JMenuItem("Units Converter");
    File.add(unitsConverter);

    JMenu Configure = new JMenu("Configure");
    menuBar.add(Configure);
    JMenuItem Preferences = new JMenuItem("Preferencesr");
    File.add(Preferences);
    JMenuItem Shortcuts = new JMenuItem("Shortcuts");
    File.add(Shortcuts);
    JMenuItem Nutrition = new JMenuItem("Nutrition");
    File.add(Nutrition);

    JMenu Help = new JMenu("Help");
    menuBar.add(Help);
    JMenuItem About = new JMenuItem("About");
    File.add(About);
    JMenuItem userGuide = new JMenuItem("User Guide");
    File.add(userGuide);
    
    // add the company logo to the window
    
  }

}
