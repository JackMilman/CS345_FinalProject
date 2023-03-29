package gui;

import java.awt.BorderLayout;
import java.awt.TextArea;

import javax.swing.JComponent;

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
    
    TextArea stepDisplay = new TextArea("Step editor");
    stepDisplay.setEditable(false);
    add(stepDisplay);
    
    setVisible(true);
  }

}
