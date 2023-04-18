package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * 
 * @author shelseyvega
 *
 */
public class KeyShortcuts extends JFrame
{

  private JLabel instructionsLabel;
  private JComboBox<String> actionsComboBox;
  private JTextField shortcutTextField;
  private JButton saveButton;
  private String shortcutKeys;

  public KeyShortcuts()
  {
    super("Shortcut Keys");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(400, 200);
    setLocationRelativeTo(null);

    // create the components
    instructionsLabel = new JLabel("Select an action and enter the shortcut keys:");
    actionsComboBox = new JComboBox<String>(new String[] {"Exit Window", "Edit Recipe", "Edit Meal",
        "View Shopping List", "View Process"});
    shortcutTextField = new JTextField(20);
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
        if (isValidShortcut(shortcut))
        {
          shortcutKeys = shortcut;
          String action = (String) actionsComboBox.getSelectedItem();
          JOptionPane.showMessageDialog(null,
              "Shortcut keys saved for " + action + ": " + shortcutKeys);
        }
        else
        {
          JOptionPane.showMessageDialog(null,
              "Invalid shortcut keys. Please enter a valid shortcut key (e.g. Ctrl + Q).");
        }
      }
    });

    setVisible(true);
  }

  public String getShortcutKeys()
  {
    return shortcutKeys;
  }

  private boolean isValidShortcut(String shortcut)
  {
    // check if the shortcut is valid (e.g. "Ctrl + Q")
    return shortcut.matches("^[a-zA-Z0-9]+( \\+ [a-zA-Z0-9]+)*$");
  }

  // public static void main(String[] args)
  // {
  // KeyShortcuts gui = new KeyShortcuts();
  // String shortcutKeys = gui.getShortcutKeys();
  // System.out.println("Shortcut keys: " + shortcutKeys);
  // }
}
