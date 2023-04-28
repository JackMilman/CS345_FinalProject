package config;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

/**
 * An enumeration of languages supported by the program. To be used, the languages must be loaded in
 * with the loadLanguages() method. Each language, after it has been loaded contains a mapping of
 * English words to words of other languages.
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
    
    Scanner scanner;
    try
    {
      scanner = new Scanner(Language.class.getClassLoader().getResource("language.txt").
          openStream());
    }
    catch(IOException ioe)
    {
      scanner = new Scanner("");
      ioe.printStackTrace();
    }
    
    while(scanner.hasNextLine())
    {
      String[] words = scanner.nextLine().split("\t");
      
      if(words.length == 3)
      {
        addWord(words[0], words[1], words[2]);
      }
    }
    
    scanner.close();
  }
  
  /**
   * Helper method for loadLanguages.
   * @param english the word in English
   * @param french the word in French
   * @param spanish the word in Spanish.
   */
  private static void addWord(final String english, final String french, final String spanish)
  {
    English.translations.put(english.toLowerCase(), english);
    French.translations.put(english.toLowerCase(),  french);
    Spanish.translations.put(english.toLowerCase(), spanish);
  }
  
  /**
   * Gets the translated version of this word in this language, ignoring case.
   * @param english the english word to translate
   * @return the english word translated into this language, or null, if the translation is 
   * not known.
   */
  public String getTranslation(final String english)
  {
    return this.translations.get(english.toLowerCase());
  }
  
  /**
   * Returns the language with the given name, ignoring case.
   * @param languageName the name of the language.
   * @return The language with the given name, or English if the language is not recognized.
   */
  public static Language fromString(final String languageName)
  {
    String uppercase = languageName.toUpperCase();
    
    for(Language language : values())
    {
      if(uppercase.equals(language.toString().toUpperCase()))
      {
        return language;
      }
    }
    
    return English;
  }
  
  /**
   * Gets the language from the virtual machine's locale.
   * @return Language.FRENCH, or Language.SPANISH, if that is the Locale's language, 
   * Language.ENGLISH otherwise.
   */
  public static Language fromLocale()
  {
    Locale locale = Locale.getDefault();
    
    return fromString(locale.getDisplayLanguage(Locale.ENGLISH));
  }
}
