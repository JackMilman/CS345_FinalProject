package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import recipes.Meal;
import recipes.Recipe;
import javax.swing.JFileChooser;

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
  public static final String RECIPE = "Recipe";
  public static final String MEAL = "Meal";
  public static final String SHOPPING = "Shopping List";
  public static final String PROCESS = "Process";
  private static JLabel l;

  private JFrame main;
  private Recipe recipe;
  private Meal meal;
  private JFileChooser fileChooser;
  private File file;
  private FileNameExtensionFilter fileFilter;

  /**
   * 
   * @param main
   */
  public KiLowBitesController(final JFrame main)
  {
    this.main = main;
    this.fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
    fileFilter = new FileNameExtensionFilter("Recipes and Meal", new String[] {"rcp", "mel"});

    // this.recipe = recipe;
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
      new RecipeEditor(main);
    }

    // Open Meal Editor
    if (e.getActionCommand().equals(MEAL))
    {
      new MealEditor(main);
    }

    // Open ShoppingListViewer
    if (e.getActionCommand().equals(SHOPPING))
    {
      // getFile();
      // check the extension of the file .rcp vs .mel
      try
      {
        open();
      }
      catch (IOException e1)
      {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      }
      // call the corresponding type: meal or recipe
      // recipes
      new ShoppingListViewer(recipe);
      // meals

    }

    // Open ProcessViewer
    else if (e.getActionCommand().equals(PROCESS))
    {
      // getFile();
      // check the extension of the file

      // call the corresponding type: meal or recipe
      // recipes
      new ProcessViewer(recipe);
      // meals
      new ProcessViewer(meal);
    }
  }

  // /**
  // *
  // * @return
  // */
  // public Recipe getFile()
  // {
  // // invoke the showsOpenDialog function to show the save dialog
  // int dialog = fileChooser.showOpenDialog(null);
  //
  // // if the user selects a file
  // if (dialog == JFileChooser.APPROVE_OPTION)
  //
  // {
  // // set the label to the path of the selected file
  // recipeFile = fileChooser.getSelectedFile();
  // recipe.setName(recipeFile.getName().replaceFirst("[.][^.]+$", ""));
  // }
  // return recipe;
  //
  // }

  private void open() throws IOException
  {

    fileChooser.setFileFilter(fileFilter);
    int result = fileChooser.showOpenDialog(null);
    if (result == JFileChooser.APPROVE_OPTION)
    {
      String path = fileChooser.getSelectedFile().getPath();
      String[] extension = fileFilter.getExtensions();
      for (int i = 0; i < extension.length; i++)
      {
        if (path.endsWith(extension[0]))
        {
          String name = path.substring(0, path.length() - 4);
          if (extension[i].equals("rcp"))
          {
            recipe = ProcessViewer.openRecipe(name);
          }
          else if (extension[i].equals("mel"))
          {
            meal = ProcessViewer.openMeal(name);
          }
        }
        // else
        // {
        // // JOptionPane.showMessageDialog(null, INVALID_FILE_TYPE, ERROR,
        // // JOptionPane.ERROR_MESSAGE);
        // }
      }
    }
  }
}
