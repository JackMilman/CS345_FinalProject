package app;

import java.lang.reflect.InvocationTargetException;

import javax.swing.SwingUtilities;

import config.Translator;
import gui.MainWindow;

/**
 * Main class of the program.
 * @author Josiah Leach
 *
 */
public class KitchIntel
{
  /**
   * The main method of the program.
   * @param args the first argument is optional and is the language for the program
   * @throws InvocationTargetException
   * @throws InterruptedException
   */
  public static void main (final String[] args) 
      throws InvocationTargetException, InterruptedException
  {
    Translator.setLanguage();
    
    SwingUtilities.invokeAndWait(new MainWindow());
  }
}
