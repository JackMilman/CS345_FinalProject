package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import branding.KitchIntelJFrame;

/**
 * 
 * @author shelseyvega
 *
 */
public class KeyShortcuts extends KitchIntelJFrame
{

  private JLabel instructionsLabel;
  private JComboBox<String> actionsComboBox;
  private JTextField shortcutTextField;
  private JButton saveButton;
  private String shortcutKeys;

  public KeyShortcuts()
  {
    super("Shortcut Keys");
    setSize(400, 200);
    setLocationRelativeTo(null);

    // create the components
    instructionsLabel = new JLabel("Select an action and enter the shortcut keys:");
    actionsComboBox = new JComboBox<String>(new String[] {"Exit Window", "Edit Recipe", "Edit Meal",
        "View Shopping List", "View Process"});
    shortcutTextField = new JTextField(20);
    shortcutTextField.addKeyListener(new KeyAdapter()
    {
      @Override
      public void keyPressed(KeyEvent e)
      {
        String keyText = KeyEvent.getKeyText(e.getKeyCode());
        String modifiersText = KeyEvent.getKeyModifiersText(e.getModifiers());
        if (modifiersText.equals("Meta"))
        {
          modifiersText = "Ctrl";
        }
        shortcutTextField.setText(modifiersText + " + " + keyText);
      }
    });
    saveButton = new JButton("Save");

    // add the components to the frame
    JPanel panel = new JPanel();
    panel.setLayout(new GridLayout(3, 1));
    panel.add(instructionsLabel);
    panel.add(actionsComboBox);
    panel.add(shortcutTextField);
    add(panel, BorderLayout.NORTH);
    add(saveButton, BorderLayout.SOUTH);

    // add action listener to the save button
    saveButton.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        String shortcut = shortcutTextField.getText();
        // if (isValidShortcut(shortcut))
        // {
        shortcutKeys = shortcut;
        String action = (String) actionsComboBox.getSelectedItem();
        // save the shortcut for the corresponding action
        saveShortcut(action, shortcutKeys);
        JOptionPane.showMessageDialog(null,
            "Shortcut keys saved for " + action + ": " + shortcutKeys);
        // }
        // else
        // {
        // JOptionPane.showMessageDialog(null,
        // "Invalid shortcut keys. Please enter a valid shortcut key (e.g. Ctrl + Q).");
        // }
      }
    });
    
    PreferenceWindow.changeFont(this);

    setVisible(true);
  }

  // save the shortcut for the corresponding action
  private void saveShortcut(String action, String shortcut)
  {
    // save the shortcut to a configuration file (e.g. shortcuts.cfg)
    // you can use a Properties object to store the shortcuts
    Properties prop = new Properties();
    try
    {
      prop.load(new FileInputStream("shortcuts.cfg"));
    }
    catch (IOException e)
    {
      // if the configuration file does not exist, create a new one
      File shortcuts = new File("shortcuts.cfg");
    }
    prop.setProperty(action, shortcut);
    try
    {
      FileOutputStream out = new FileOutputStream("shortcuts.cfg");
      prop.store(out, "Shortcuts");
      out.close();
    }
    catch (IOException e)
    {
      // handle the exception
    }
  }

  public String getShortcutKeys()
  {
    return shortcutKeys;
  }

  // private boolean isValidShortcut(String shortcut)
  // {
  // // check if the shortcut is valid (e.g. "Ctrl + Q")
  // return shortcut.matches("^[a-zA-Z0-9]+( \\+ [a-zA-Z0-9]+)*$");
  // }
}
