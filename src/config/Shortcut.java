package config;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import gui.KiLowBitesController;
import gui.MainWindow;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author shelseyvega
 *
 */
public class Shortcut extends JDialog implements ActionListener
{

  private static final long serialVersionUID = 1L;
  private JPanel panel;
  private JButton saveButton;
  private JButton cancelButton;
  private Map<String, CustomAction> actions;
  private Map<String, JTextField> textFields;

  /**
   * 
   * @param parent
   * @param actions
   */
  public Shortcut(final JFrame parent, final Map<String, CustomAction> actions)
  {
    super(parent, "Shortcuts", true);
    this.actions = actions;
    this.textFields = new HashMap<>();
    initializeUI();
  }

  private void initializeUI()
  {
    panel = new JPanel(new GridLayout(actions.size(), 2, 10, 10));
    for (CustomAction action : actions.values())
    {
      panel.add(new JLabel(action.toString() + ": "));
      JTextField textField = new JTextField();
      textField.setText(action.getAcceleratorKey().toString());
      panel.add(textField);
      textFields.put(action.toString(), textField);
    }

    saveButton = new JButton("Save");
    saveButton.addActionListener(this);
    cancelButton = new JButton("Cancel");
    cancelButton.addActionListener(this);

    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    buttonPanel.add(saveButton);
    buttonPanel.add(cancelButton);

    getContentPane().add(panel, BorderLayout.CENTER);
    getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    pack();
    setLocationRelativeTo(getParent());
  }

  @Override
  public void actionPerformed(final ActionEvent e)
  {
    if (e.getSource() == saveButton)
    {
      for (String actionName : actions.keySet())
      {
        JTextField textField = textFields.get(actionName);
        KeyStroke keyStroke = KeyStroke.getKeyStroke(textField.getText());
        actions.get(actionName).setAcceleratorKey(keyStroke);
      }
    }
    dispose();
  }

  /**
   * Updates the shortcut of the selected action in the actions map with the new key stroke value
   * entered by the user.
   */
  private void updateShortcut()
  {
    for (CustomAction action : actions.values())
    {
      panel.add(new JLabel(action.toString() + ": "));
      JTextField textField = new JTextField();
      textField.setText(action.getAcceleratorKey().toString());
      textField.getDocument().addDocumentListener(new DocumentListener()
      {
        @Override
        public void insertUpdate(DocumentEvent e)
        {
          updateActionShortcut(textField, action);
        }

        @Override
        public void removeUpdate(DocumentEvent e)
        {
          updateActionShortcut(textField, action);
        }

        @Override
        public void changedUpdate(DocumentEvent e)
        {
          updateActionShortcut(textField, action);
        }

        private void updateActionShortcut(JTextField textField, CustomAction action)
        {
          KeyStroke keyStroke = KeyStroke.getKeyStroke(textField.getText());
          action.setAcceleratorKey(keyStroke);
        }
      });
      panel.add(textField);
      textFields.put(action.toString(), textField);
    }

  }

  /**
   * Show the dialog and return the updated shortcuts map if the user clicks "Save". Return null if
   * the user clicks "Cancel".
   * 
   * @param parent
   * @param actions
   * @return map
   */
  public static Map<String, KeyStroke> showDialog(final JFrame parent,
      final Map<String, CustomAction> actions)
  {
    Shortcut shortcutDialog = new Shortcut(parent, actions);
    shortcutDialog.setVisible(true);
    if (shortcutDialog.isSaved())
    {
      Map<String, KeyStroke> shortcuts = new HashMap<>();
      for (String actionName : actions.keySet())
      {
        CustomAction action = actions.get(actionName);
        KeyStroke keyStroke = action.getAcceleratorKey();
        shortcuts.put(actionName, keyStroke);
      }
      return shortcuts;
    }
    else
    {
      return null;
    }
  }

  /**
   * Check if the user clicked "Save" or "Cancel".
   * 
   * @return saved yes or no
   */
  private boolean isSaved()
  {
    return saveButton.getModel().isPressed();
  }
}
