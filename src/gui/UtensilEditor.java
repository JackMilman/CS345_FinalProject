package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.TextArea;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * A class for the UtensilEditor component of the recipe editor.
 * @author Josiah Leach, KitchIntel
 * @version 03.29.2023
 */
public class UtensilEditor extends JComponent
{

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  /**
   * 
   */
  public UtensilEditor()
  {
    super();
    setLayout(new BorderLayout());
    setBorder(KitchIntelBorder.labeledBorder("Utensil editor"));
    
    Container inputFields = new Container();
    inputFields.setLayout(new FlowLayout(FlowLayout.LEFT));
    inputFields.add(new JLabel("Name:"));
    inputFields.add(new JTextField(RecipeEditor.defaultTextFieldWidth));
    inputFields.add(new JLabel("Details:"));
    inputFields.add(new JTextField(RecipeEditor.defaultTextFieldWidth));
    inputFields.add(new JButton("Add"));
    
    add(inputFields, BorderLayout.NORTH);
    
    add(new JButton("Delete"), BorderLayout.EAST);
    
    TextArea utensilDisplay = new TextArea("spoon\nbowl");
    utensilDisplay.setEditable(false);
    add(utensilDisplay, BorderLayout.CENTER);
    
    setVisible(true);
  }
}
