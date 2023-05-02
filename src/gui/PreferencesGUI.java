package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import branding.KitchIntelJFrame;
import config.Language;
import config.Translator;

/**
 * GUI for inputting preferences.
 * 
 * @author KitchIntel
 * @version
 */
public class PreferencesGUI extends KitchIntelJFrame implements ActionListener
{
  private static final long serialVersionUID = 1L;
  private JComboBox<String> languageComboBox;

  /**
   * Creates a new Preferences GUI.
   */
  public PreferencesGUI()
  {
    setTitle("Preferences");

    setLayout(new GridLayout(1, 1));

    // Create the language dropdown
    JLabel languageLabel = new JLabel(Translator.translate("Language") + ":");
    languageComboBox = new JComboBox<>();
    for (Language language : Language.values())
    {
      languageComboBox.addItem(language.toString());
    }
    add(languageLabel);
    add(languageComboBox);

    // Create the save button
    JButton saveButton = new JButton(Translator.translate("Save"));
    saveButton.addActionListener(this);
    add(saveButton);

    // Set the language selection to the current language
    languageComboBox.setSelectedItem(Translator.getLanguage().toString());

    pack();
    setLocationRelativeTo(null);
    setVisible(true);
  }

  @Override
  public void actionPerformed(final ActionEvent e)
  {
    // Get the selected language from the dropdown
    String selectedLanguage = (String) languageComboBox.getSelectedItem();
    Language language = Language.fromString(selectedLanguage);

    // Save the selected language to the properties file
    Properties properties = new Properties();
    properties.setProperty("language", language.toString());

    try
    {
      Files.writeString(Path.of("language.cfg"), selectedLanguage);
    }
    catch (IOException ex)
    {
      ex.printStackTrace();
    }

    // Set the current language
    //Translator.setLanguage(language);
    
    JOptionPane.showMessageDialog(null, 
        Translator.translate("Restart the program for this change to take full effect."), 
        "", JOptionPane.PLAIN_MESSAGE);

    // Close the window
    dispose();
  }

  public static void main(String[] args)
  {
    new PreferencesGUI();
  }
}
