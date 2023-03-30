package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.TextArea;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * A class for the step editor component of the recipe editor.
 * @author Josiah Leach, KitchIntel
 * @version 03.29.2023
 */
public class StepEditor extends JComponent
{

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  
  public StepEditor()
  {
    super();
    setLayout(new BorderLayout());
    setBorder(KitchIntelBorder.labeledBorder("Step editor"));
    
    Container inputFields = new Container();
    inputFields.setLayout(new FlowLayout(FlowLayout.LEFT));
    inputFields.add(new JLabel("Action:"));
    inputFields.add(new JComboBox<String>(new String[] {"", "Ounces", "Pounds"}));
    inputFields.add(new JLabel("On:"));
    inputFields.add(new JComboBox<String>(new String[] {"", "Ounces", "Pounds"}));
    inputFields.add(new JLabel("Utensil:"));
    inputFields.add(new JComboBox<String>(new String[] {"", "Ounces", "Pounds"}));
    inputFields.add(new JLabel("Details:"));
    inputFields.add(new JTextField(RecipeEditor.defaultTextFieldWidth));
    inputFields.add(new JButton("Add"));
    
    add(inputFields, BorderLayout.NORTH);
    
    add(new JButton("Delete"), BorderLayout.EAST);
    
    TextArea stepDisplay = new TextArea("Put the peanut butter on the spoon\neat it");
    stepDisplay.setEditable(false);
    add(stepDisplay, BorderLayout.CENTER);
    
    setVisible(true);
  }

}
