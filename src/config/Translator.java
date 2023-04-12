package config;

/**
 * A class which allows the text of the program to be configured to different languages.
 * @author Josiah Leach
 *
 */
public class Translator
{
  private static Language language = Language.English;
  /**
   * Translates the English text to the currently specified language.
   * @param englishText The word to get the translation of.
   * @return Returns the String of the translated word, or returns the inputed String if the 
   * word's translation is not know.
   */
  public static String translate(final String englishText)
  {
    if(!Language.isLoaded()) Language.loadLanguages();
    
    String translation = language.getTranslation(englishText.toLowerCase());
    
    if(translation == null) return englishText;
    
    return translation;
  }
  
  /**
   * Sets the current language of the program.
   * @param language The language to set as the current language.
   */
  public static void setLanguage(final Language language)
  {
    Translator.language = language;
  }
  
  /**
   * Returns the current language of the program.
   * @return The language being used.
   */
  public static Language getLanguage()
  {
    return language;
  }
}
