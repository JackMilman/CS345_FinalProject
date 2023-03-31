package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.filechooser.FileSystemView;
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

  private JFrame main;
  private Recipe recipe;
  private JFileChooser fileChooser;
  private File recipeFile;

  /**
   * 
   * @param main
   */
  public KiLowBitesController(final JFrame main)
  {
    this.main = main;
    this.fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

    // this.recipe = recipe;
  }

  @Override
  public void actionPerformed(final ActionEvent e)
  {
    if (e.getActionCommand().equals(EXIT))
    {
      System.exit(0);
    }
    if (e.getActionCommand().equals(RECIPE))
    {
      new RecipeEditor(main);
    }
    if (e.getActionCommand().equals(MEAL))
    {
      new MealEditor(main);
    }

    if (e.getActionCommand().equals(SHOPPING))
    {
      getFile();
      new ShoppingListViewer(recipe);

    }

    if (e.getActionCommand().equals(PROCESS))
    {
      getFile();
      new ProcessViewer(recipe);
    }
  }

  public Recipe getFile()
  {
    // invoke the showsOpenDialog function to show the save dialog
    int dialog = fileChooser.showOpenDialog(null);

    // if the user selects a file
    if (dialog == JFileChooser.APPROVE_OPTION)

    {
      // set the label to the path of the selected file
      recipeFile = fileChooser.getSelectedFile();
      this.recipe.setName(recipeFile.getName().replaceFirst("[.][^.]+$", ""));
    }

    return this.recipe;

  }

}
