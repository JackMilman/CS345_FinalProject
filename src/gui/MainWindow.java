package gui;

import java.awt.BorderLayout;

import javax.swing.*;

public class MainWindow extends JFrame
{
  private static final long serialVersionUID = 1L;
  private static final String PATH = "images/KILowBites_Logo.png";

  public MainWindow()
  {
    super();
    // set the size of the frame
    setTitle("KiLowBites Main Window");
    setLayout(new BorderLayout());
    setSize(700, 500);
    setDefaultCloseOperation(EXIT_ON_CLOSE);

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
    Edit.add(Recipe);
    JMenuItem Meal = new JMenuItem("Meal");
    Edit.add(Meal);

    JMenu Search = new JMenu("Search");
    menuBar.add(Search);
    JMenuItem Recipes = new JMenuItem("Recipes");
    Search.add(Recipes);
    JMenuItem Meals = new JMenuItem("Meals");
    Search.add(Meals);

    JMenu View = new JMenu("View");
    menuBar.add(View);
    JMenuItem shoppingList = new JMenuItem("ShoppingList");
    View.add(shoppingList);
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
    JMenuItem Preferences = new JMenuItem("Preferencesr");
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

    setVisible(true);

    // add the company logo to the window
    ImageIcon logo = new ImageIcon(PATH);
    
    JLabel logoLabel = new JLabel(logo);

    add(logoLabel, BorderLayout.CENTER);
    
    logoLabel.setVisible(true);

    
  }

  public static void main(String[] args)
  {
    new MainWindow();
  }

}
