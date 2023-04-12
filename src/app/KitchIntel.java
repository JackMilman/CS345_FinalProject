package app;

import java.lang.reflect.InvocationTargetException;

import javax.swing.SwingUtilities;

import config.Language;
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
    if(args.length > 0)
    {
      Translator.setLanguage(Language.fromString(args[0]));
    }
    
    SwingUtilities.invokeAndWait(new MainWindow());

  }
}
