package config;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
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
    super(parent, "Set Custom Shortcuts", true);
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
    setVisible(true);
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
      dispose();
    }
    else if (e.getSource() == cancelButton)
    {
      dispose();
    }
  }
  
  
}
