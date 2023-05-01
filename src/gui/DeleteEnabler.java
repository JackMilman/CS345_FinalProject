package gui;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * An ActionListener for updating the enabled state of the delete button in an editor.
 * 
 * @author Josiah Leach
 *
 */
public class DeleteEnabler implements ListSelectionListener
{
  private final JTable subject;
  private final JButton deleteButton;

  /**
   * Creates a new DeleteEnabler on the given JTable. subject.addActionListener() must still be
   * called.
   * 
   * @param subject
   *          The JTable which is referenced for this ActionListener's decisions
   * @param deleteButton
   *          The button which will be enabled or disabled by this ActionListener .
   */
  public DeleteEnabler(final JTable subject, final JButton deleteButton)
  {
    this.subject = subject;
    this.deleteButton = deleteButton;
  }

  @Override
  public void valueChanged(final ListSelectionEvent e)
  {
    deleteButton.setEnabled(subject.getSelectedRow() != -1);
  }

}
