package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import config.Translator;
import preferences.KitchIntelPreferenceReader;

/**
 * GUI in which the user can enter their preferences. This included font size, default save location, default save location for recipes, and default save location for meals. 
 * 
 * @author allieokeeffe
 *
 */
public class PreferenceWindow extends JFrame
{
  
  private static final long serialVersionUID = 1L;
  //private static ArrayList<File>files;
  private static JFrame mainWindow;
  //private static final File file = new File("preferences.txt");

  public PreferenceWindow() {
    super();
    setUp();
  }
  
  private ActionListener createActionListener(String saveItem, JComboBox<File> selectedFiles) {
    ActionListener temp = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e)
      {
          JFileChooser fc = new JFileChooser();
          fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
          int returnVal = fc.showOpenDialog(new JFrame());
          if (returnVal == JFileChooser.APPROVE_OPTION) {
            File selectedDirectory = fc.getSelectedFile();
            String enterName = JOptionPane.showInputDialog("Enter directory name: ");
            File newFolder = new File(selectedDirectory, enterName);
            if (newFolder.mkdir()) {
//              try {
                //KitchIntelPreferenceReader.saveItem(saveItem, newFolder.getAbsolutePath());
                selectedFiles.addItem(newFolder);
//              } catch (IOException ex) {
//                System.out.print("Error, try again.");
//              }
              } else {
              System.out.print("Error, try again.");
            }
          }
      }
  };
  return temp;
  }
  
  private JPanel createDirectory(String savedItem) {
    JPanel p = new JPanel();
    JLabel label = new JLabel(Translator.translate(String.format("Select %s directory: ", savedItem.toLowerCase())));
    JComboBox<File> selectedFiles = new JComboBox<>();
    try {
      File saved = new File(KitchIntelPreferenceReader.returnValue(savedItem));
      selectedFiles.addItem(saved);
    } catch (IOException e) {
      //No default directory saved -- add nothing
    }
    ActionListener selected = new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e)
      {
        try
        {
          KitchIntelPreferenceReader.saveItem(savedItem, selectedFiles.getSelectedItem().toString());
        }
        catch (IOException e1)
        {
          System.out.print("Error");
        }
      }
      
    };
    selectedFiles.addActionListener(selected);
    selectedFiles.setPreferredSize(new Dimension(300,30));
    
    JButton newFile = new JButton(Translator.translate("New File"));
    ActionListener temp = createActionListener(savedItem, selectedFiles);
    newFile.addActionListener(temp); 
    
    p.add(label);
    p.add(selectedFiles);
    p.add(newFile);
    
    return p;
  }
  
  private JPanel changeFontSize(){
    JPanel p = new JPanel();
    
    JTextField textSize = new JTextField();
    textSize.setEditable(false);
    JLabel changeFontSizeLabel = new JLabel(Translator.translate("Change font size: "));
    String size = null;
    try
    {
      size = (String)KitchIntelPreferenceReader.returnValue(KitchIntelPreferenceReader.FONT);
    }
    catch (IOException e3)
    {
      e3.printStackTrace();
    }
    textSize.setText((String)size);
    JButton minus = new JButton("-");
    JButton plus = new JButton("+");
    String s = textSize.getText();
    int val = Integer.parseInt(s);
    
    ActionListener changeFontSize = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e)
      {

        String s = textSize.getText();
        int val = Integer.parseInt(s);
        JButton button = (JButton)e.getSource();
        if (button.getText().equals("+")) {
          if (val + 2 > 30) {
            return;
          }
          textSize.setText("" + (val + 2));
        } else {
          if (val - 2 < 8) {
            return;
          }
          textSize.setText("" + (val - 2));
        }
      }
    };
    
    JButton apply = new JButton("Apply");
    ActionListener applyChanges = new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e)
      {
        try
        {
          KitchIntelPreferenceReader.saveItem(KitchIntelPreferenceReader.FONT, textSize.getText());
          for (Component c: MainWindow.getAllCreatedWindows()) {
            changeFont(c, Integer.parseInt(textSize.getText()));
          }
        }
        catch (IOException e1)
        {
          System.out.print("ERROR");
        }
      }
      
    };
    
    plus.addActionListener(changeFontSize);
    minus.addActionListener(changeFontSize);
    apply.addActionListener(applyChanges);
    
    p.add(changeFontSizeLabel);
    p.add(minus);
    p.add(textSize);
    p.add(plus);
    p.add(apply);
    
    return p;
  }
  
  public static void changeFont(Component component) {
    try
    {
      String fontSize = KitchIntelPreferenceReader.returnValue(KitchIntelPreferenceReader.FONT);
      changeFont(component, Integer.parseInt(fontSize));
    }
    catch (IOException e1)
    {
      System.out.print("ERROR");
    }
  }
  
  private static void changeFont(Component component, int fontSize)
  {
      component.setFont(new Font(Font.DIALOG, Font.PLAIN, fontSize));
      if ( component instanceof Container )
      {
          for (Component child : ((Container) component).getComponents ())
          {
              changeFont(child, fontSize);
          }
      }
      if (component instanceof JFrame && !(component instanceof MainWindow)) {
        JFrame frame = (JFrame)component;
        frame.pack();
      }
  }
  
  private void setUp(){
    JPanel p = (JPanel) getContentPane();
    
    p.setLayout(new BorderLayout());
    p.add(createDirectory(KitchIntelPreferenceReader.DEFAULT), BorderLayout.NORTH);
    p.add(changeFontSize(), BorderLayout.CENTER);
    
    JPanel temp = new JPanel();
    temp.setLayout(new BorderLayout());
    temp.add(createDirectory(KitchIntelPreferenceReader.RECIPE), BorderLayout.NORTH);
    temp.add(createDirectory(KitchIntelPreferenceReader.MEAL), BorderLayout.SOUTH);
    p.add(temp, BorderLayout.SOUTH);
    
    PreferenceWindow.changeFont(this);
    
    setSize(400,200);
    pack();
    setVisible(true);
  }
  
}
