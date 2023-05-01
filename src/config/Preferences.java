package config;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Preferences
{
  private static final String CONFIG_FILE = "config.properties";
  private static final String LANGUAGE_KEY = "language";
  private static final Language DEFAULT_LANGUAGE = Language.English;

  private Language selectedLanguage;

  public Preferences()
  {
    loadPreferences();
  }

  /**
   * Load the preference from the file.
   */
  private void loadPreferences()
  {
    Properties props = new Properties();

    try
    {
      props.load(new FileInputStream(CONFIG_FILE));
      selectedLanguage = Language.fromString(props.getProperty(LANGUAGE_KEY));
    }
    catch (IOException e)
    {
      selectedLanguage = DEFAULT_LANGUAGE;
    }

    //Translator.setLanguage(selectedLanguage);
  }

  /**
   * save the preference to a file
   */
  public void savePreferences()
  {
    Properties props = new Properties();
    props.setProperty(LANGUAGE_KEY, selectedLanguage.toString());

    try
    {
      props.store(new FileOutputStream(CONFIG_FILE), "Language preference");
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }


  public Language getSelectedLanguage()
  {
    return selectedLanguage;
  }
}
