package config;

import java.util.HashMap;
import java.util.Map;

/**
 * An enumeration of languages supported by the program. To be used, the languages must be loaded in
 * with the loadLanguages() method. Each language, after it has been loaded contains a mapping of
 * lower case English words to lower case words of other languages.
 * @author Josiah Leach
 *
 */
public enum Language
{
  English(), French(), Spanish();
  
  private static boolean isLoaded = false;
  
  private Map<String, String> translations;
  
  private Language(){}
  
  /**
   * Tells whether language data has been read in yet.
   * @return true if language data has been read in, false otherwise.
   */
  public static boolean isLoaded()
  {
    return isLoaded;
  }
  
  /**
   * Loads in the language data.
   */
  public static void loadLanguages()
  {
    for(Language language : values())
    {
      language.translations = new HashMap<String, String>();
    }
    
    addWord("add", "ajouter", "agregar");
  }
  
  /**
   * Helper method for loadLanguages.
   * @param english the word in English
   * @param french the word in French
   * @param spanish the word in Spanish.
   */
  private static void addWord(final String english, final String french, final String spanish)
  {
    English.translations.put(english, english);
    French.translations.put(english,  french);
    Spanish.translations.put(english, spanish);
  }
  
  public String getTranslation(String english)
  {
    return this.translations.get(english);
  }
}
