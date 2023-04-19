package gui;

import config.Language;
import config.Translator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PreferencesGUI extends JFrame implements ActionListener
{
  private static final long serialVersionUID = 1L;
  private JComboBox<String> languageComboBox;

  public PreferencesGUI()
  {
    setTitle("Preferences");

    setLayout(new GridLayout(1, 1));

    // Create the language dropdown
    JLabel languageLabel = new JLabel("Language:");
    languageComboBox = new JComboBox<>();
    for (Language language : Language.values())
    {
      languageComboBox.addItem(language.toString());
    }
    add(languageLabel);
    add(languageComboBox);

    // Create the save button
    JButton saveButton = new JButton("Save");
    saveButton.addActionListener(this);
    add(saveButton);

    // Set the language selection to the current language
    languageComboBox.setSelectedItem(Translator.getLanguage().toString());

    pack();
    setLocationRelativeTo(null);
    setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    // Get the selected language from the dropdown
    String selectedLanguage = (String) languageComboBox.getSelectedItem();
    Language language = Language.fromString(selectedLanguage);

    // Save the selected language to the properties file
    Properties properties = new Properties();
    properties.setProperty("language", language.toString());

    try (FileOutputStream outputStream = new FileOutputStream("config.properties"))
    {
      properties.store(outputStream, null);
    }
    catch (IOException ex)
    {
      ex.printStackTrace();
    }

    // Set the current language
    Translator.setLanguage(language);

    // Close the window
    dispose();
  }

  public static void main(String[] args)
  {
    new PreferencesGUI();
  }
}
