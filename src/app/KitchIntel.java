package app;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

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

    try
    {
      String language = Files.readString(Path.of("language.cfg"));
      Translator.setLanguage(Language.fromString(language));
    }
    catch(IOException ioe)
    {
      Translator.setLanguage(Language.English);
    }
    SwingUtilities.invokeAndWait(new MainWindow());

  }
}
