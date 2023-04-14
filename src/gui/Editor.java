package gui;

import java.awt.Window;

import javax.swing.JDialog;

import branding.KitchIntelButton;

/**
 * Abstract parent class of all editors. Encapsulates functionality like the buttons that all 
 * editors have in common.
 * @author Josiah Leach
 * @version 04.04.2023
 */
public abstract class Editor extends JDialog
{
  
  protected static final String ERROR_MESSAGE = "File could not be saved";

  protected static final String CURRENT_DIRECTORY = ".";
  
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  

  
  protected KitchIntelButton newButton;
  protected KitchIntelButton openButton;
  protected KitchIntelButton saveButton;
  protected KitchIntelButton saveAsButton;
  protected KitchIntelButton closeButton;
  
  protected DocumentState state;
  
  protected String fileName;
  
  protected final Window owner;

  
  /**
   * Creates a new Editor with the given owner and given title.
   * @param owner The owner of this JDialog
   * @param title The title of this JDialog
   */
  public Editor(final Window owner, final String title)
  {
    super(owner, title);
    
    this.owner = owner;
    
    this.fileName = null;
    this.state = DocumentState.UNCHANGED;
    
    newButton = new KitchIntelButton(KitchIntelButton.NEW_IMAGE);
    openButton = new KitchIntelButton(KitchIntelButton.OPEN_IMAGE);
    saveButton = new KitchIntelButton(KitchIntelButton.SAVE_IMAGE);
    saveAsButton = new KitchIntelButton(KitchIntelButton.SAVE_AS_IMAGE);
    closeButton = new KitchIntelButton(KitchIntelButton.CLOSE_IMAGE);
    
    updateButtons();

  }
  
  protected void updateButtons()
  {
    switch(state)
    {
      case NULL:
        newButton.setEnabled(true);
        openButton.setEnabled(true);
        saveButton.setEnabled(false);
        saveAsButton.setEnabled(false);
        closeButton.setEnabled(false);
        break;
      case UNCHANGED:
        newButton.setEnabled(true);
        openButton.setEnabled(true);
        saveButton.setEnabled(false);
        saveAsButton.setEnabled(true);
        closeButton.setEnabled(true);
        break;
      case CHANGED:
        newButton.setEnabled(false);
        openButton.setEnabled(false);
        saveButton.setEnabled(true);
        saveAsButton.setEnabled(true);
        closeButton.setEnabled(false);
        break;
      default:
        break;
    }
  }
  
}
