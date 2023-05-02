package gui;

import java.awt.Window;

import branding.KitchIntelIconButton;
import branding.KitchIntelJDialog;

/**
 * Abstract parent class of all editors. Encapsulates functionality like the buttons that all
 * editors have in common.
 * 
 * @author Josiah Leach, KitchIntel
 * @version 04.04.2023
 */
public abstract class Editor extends KitchIntelJDialog
{

  protected static final String ERROR_MESSAGE = "File could not be saved";

  protected static final String CURRENT_DIRECTORY = ".";

  private static final long serialVersionUID = 1L;

  protected KitchIntelIconButton newButton;
  protected KitchIntelIconButton openButton;
  protected KitchIntelIconButton saveButton;
  protected KitchIntelIconButton saveAsButton;
  protected KitchIntelIconButton closeButton;

  protected DocumentState state;

  protected String fileName;

  protected final Window owner;

  /**
   * Creates a new Editor with the given owner and given title.
   * 
   * @param owner
   *          The owner of this JDialog
   * @param title
   *          The title of this JDialog
   */
  public Editor(final Window owner, final String title)
  {
    super(owner, title);

    this.owner = owner;

    this.fileName = null;
    this.state = DocumentState.UNCHANGED;

    newButton = new KitchIntelIconButton(KitchIntelIconButton.NEW_IMAGE);
    openButton = new KitchIntelIconButton(KitchIntelIconButton.OPEN_IMAGE);
    saveButton = new KitchIntelIconButton(KitchIntelIconButton.SAVE_IMAGE);
    saveAsButton = new KitchIntelIconButton(KitchIntelIconButton.SAVE_AS_IMAGE);
    closeButton = new KitchIntelIconButton(KitchIntelIconButton.CLOSE_IMAGE);

    updateButtons();

  }
  
  /**
   * Sets whether the editing features of this Editor are enabled or disabled.
   * @param editable True if the components of this Editor should be enabled, false otherwise.
   */
  public abstract void enableEditing(boolean editable);

  protected void updateButtons()
  {
    switch (state)
    {
      case NULL:
        newButton.setEnabled(true);
        openButton.setEnabled(true);
        saveButton.setEnabled(false);
        saveAsButton.setEnabled(false);
        closeButton.setEnabled(false);
        enableEditing(false);
        break;
      case UNCHANGED:
        newButton.setEnabled(true);
        openButton.setEnabled(true);
        saveButton.setEnabled(false);
        saveAsButton.setEnabled(false);
        closeButton.setEnabled(true);
        enableEditing(true);
        break;
      case CHANGED:
        newButton.setEnabled(false);
        openButton.setEnabled(false);
        saveButton.setEnabled(true);
        saveAsButton.setEnabled(true);
        closeButton.setEnabled(false);
        enableEditing(true);
        break;
      default:
        break;
    }
  }

}
