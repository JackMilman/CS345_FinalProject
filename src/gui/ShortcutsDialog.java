package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Shelsey Vega
 *
 */
public class ShortcutsDialog extends JDialog implements ActionListener
{

  private static final long serialVersionUID = 1L;
  private JLabel label;
  private JTextField textField;
  private JButton okButton, cancelButton;
  private Map<String, KeyStroke> currentShortcuts;

  /**
   * 
   * @param parent
   * @param currentShortcuts
   */
  public ShortcutsDialog(final JFrame parent, final Map<String, KeyStroke> currentShortcuts)
  {
    super(parent, "Shortcuts", true);
    this.currentShortcuts = currentShortcuts;
    setSize(400, 200);
    setLocationRelativeTo(parent);
    setResizable(false);
    setLayout(new GridLayout(3, 2));

    label = new JLabel("Enter new shortcut for ");
    add(label);

    textField = new JTextField();
    add(textField);

    okButton = new JButton("OK");
    okButton.addActionListener(this);
    add(okButton);

    cancelButton = new JButton("Cancel");
    cancelButton.addActionListener(this);
    add(cancelButton);

    for (String actionCommand : currentShortcuts.keySet())
    {
      KeyStroke keyStroke = currentShortcuts.get(actionCommand);
      label.setText("Enter new shortcut for " + actionCommand + ":");
      textField.setText(keyStroke.toString());
      textField.selectAll();
      textField.requestFocusInWindow();
      setVisible(true);
      if (textField.getText().length() > 0)
      {
        KeyStroke newKeyStroke = KeyStroke.getKeyStroke(textField.getText());
        if (newKeyStroke != null)
        {
          currentShortcuts.put(actionCommand, newKeyStroke);
        }
      }
    }
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    if (e.getSource() == okButton)
    {
      dispose();
    }
    else if (e.getSource() == cancelButton)
    {
      currentShortcuts.clear();
      dispose();
    }
  }
}
