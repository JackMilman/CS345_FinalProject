package gui;

import java.awt.BorderLayout;
import java.awt.TextArea;

import javax.swing.JComponent;

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

  public UtensilEditor()
  {
    super();
    setLayout(new BorderLayout());
    
    TextArea utensilDisplay = new TextArea("Utensil editor");
    utensilDisplay.setEditable(false);
    add(utensilDisplay);
    
    setVisible(true);
  }
}
