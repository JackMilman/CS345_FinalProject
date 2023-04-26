package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;

import branding.KitchIntelBorder;
import config.Translator;
import recipes.Recipe;
import recipes.Utensil;
import utilities.SortLists;

/**
 * A class for the UtensilEditor component of the recipe editor.
 * 
 * @author Josiah Leach, KitchIntel
 * @version 03.29.2023
 */
public class UtensilEditor extends JComponent
{
  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private static final String ADD = "Add";
  private static final String DELETE = "Delete";

  private final JTextField nameField;
  private final JTextField detailField;
  private final TextArea utensilDisplay;
  private final JButton addButton, deleteButton;
  private Recipe workingRecipe;
  private StepEditor stepEditor;

  /**
   * Creates a new UtensilEditor.
   * @param workingRecipe the recipe to edit the utensils of.
   * @param the corresponding StepEditor
   */
  public UtensilEditor(final Recipe workingRecipe, final StepEditor stepEditor)
  {
    super();
    setLayout(new BorderLayout());
    setBorder(KitchIntelBorder.labeledBorder(Translator.translate("Utensils")));
    
    this.workingRecipe = workingRecipe;
    this.stepEditor = stepEditor;

    UtensilEditorListener listener = new UtensilEditorListener();

    nameField = new JTextField(RecipeEditor.DEFAULT_TEXT_FIELD_WIDTH);
    detailField = new JTextField(RecipeEditor.DEFAULT_TEXT_FIELD_WIDTH);
    nameField.addActionListener(new UpdateListener());

    addButton = new JButton(Translator.translate(ADD));
    addButton.setActionCommand(RecipeEditor.UTENSIL_ADD_ACTION_COMMAND);
    addButton.addActionListener(listener);
    
    addButton.setEnabled(false);

    Container inputFields = new Container();
    inputFields.setLayout(new FlowLayout(FlowLayout.LEFT));

    inputFields.add(new JLabel(Translator.translate("Name") + ":"));
    inputFields.add(nameField);

    inputFields.add(new JLabel(Translator.translate("Details") + ":"));
    inputFields.add(detailField);

    inputFields.add(addButton);

    add(inputFields, BorderLayout.NORTH);

    deleteButton = new JButton(DELETE);
    deleteButton.addActionListener(listener);
    deleteButton.setActionCommand(RecipeEditor.UTENSIL_DELETE_ACTION_COMMAND);
    add(deleteButton, BorderLayout.EAST);

    utensilDisplay = new TextArea();
    utensilDisplay.setEditable(false);
    add(utensilDisplay, BorderLayout.CENTER);

    setVisible(true);
  }

  private void add()
  {
    if (nameField.getText().equals(""))
      return;

    Utensil utensil = new Utensil(nameField.getText(), detailField.getText());
    workingRecipe.addUtensil(utensil);
    nameField.setText("");
    detailField.setText("");

    updateText();
    
    addButton.setEnabled(false);
    
    stepEditor.update();
  }

  private void updateText()
  {
    String displayText = "";

    for (Utensil utensil : workingRecipe.getUtensils())
    {
      displayText += String.format("%s\n", utensil.toString());
    }

    utensilDisplay.setText(displayText);
  }

  private void delete()
  {
    if (workingRecipe.getUtensils().size() == 0)
      return;

    int selectionStart = utensilDisplay.getSelectionStart();
    int linesSelected = 0;
    int linesSkipped = 0;
    String selectedText = utensilDisplay.getSelectedText();

    if (selectedText == null || selectedText.length() < 0)
      return;

    char[] characters = selectedText.toCharArray();

    // counts the number of newline characters to determine the number of lines selected
    for (char character : characters)
    {
      if (character == '\n')
      {
        linesSelected++;
      }
    }

    // if the last selected character isn't a newline character, then there is one uncounted line.
    if (characters[characters.length - 1] != '\n')
      linesSelected++;

    String skipped = utensilDisplay.getText().substring(0, selectionStart);

    char[] skippedChars = skipped.toCharArray();

    for (char skippedChar : skippedChars)
    {
      if (skippedChar == '\n')
        linesSkipped++;
    }

    for (int i = 0; i < linesSelected; i++)
    {
      workingRecipe.getUtensils().remove(linesSkipped);
    }

    updateText();
    
    stepEditor.update();
  }

  /**
   * Adds a text listener to the text area of the utensil editor.
   * 
   * @param listener
   *          the text listener to add to the display text area.
   */
  public void addTextListener(final TextListener listener)
  {
    utensilDisplay.addTextListener(listener);
  }

  List<Utensil> getUtensils()
  {
    return workingRecipe.getUtensils();
  }

  void loadUtensils(final List<Utensil> newUtensils)
  {
    workingRecipe.getUtensils().clear();
    workingRecipe.addAllUtensils(newUtensils);    

    updateText();
  }

  private class UtensilEditorListener implements ActionListener
  {

    @Override
    public void actionPerformed(final ActionEvent e)
    {
      if (e.getActionCommand().equals(RecipeEditor.UTENSIL_ADD_ACTION_COMMAND))
      {
        add();
      }
      else if (e.getActionCommand().equals(RecipeEditor.UTENSIL_DELETE_ACTION_COMMAND))
      {
        delete();
      }
    }

  }
  
  private class UpdateListener implements ActionListener
  {

    @Override
    public void actionPerformed(final ActionEvent e)
    {
      System.out.println("Action performed");
      addButton.setEnabled(false);
      if(nameField.getText().length() > 0)
      {
        addButton.setEnabled(true);
      }    
    }
    
  }

  /**
   * Adds an action listener to the buttons in this UtensilEditor which can cause the document to
   * change.
   * 
   * @param listener
   *          The actionListener to listen to these changes.
   */
  public void addChangeListener(final ActionListener listener)
  {
    addButton.addActionListener(listener);
    deleteButton.addActionListener(listener);
  }
}
