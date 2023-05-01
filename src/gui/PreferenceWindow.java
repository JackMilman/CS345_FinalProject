package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
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

public class PreferenceWindow extends JFrame
{
  
  private static final long serialVersionUID = 1L;
  private static ArrayList<File>files;
  private static JFrame mainWindow;
  //private static final File file = new File("preferences.txt");

  public PreferenceWindow(JFrame mainWindow) {
    super();
    files = new ArrayList<File>();
    PreferenceWindow.mainWindow = mainWindow;
    setUp();
  }
  
//  private JPanel defaultDirectory() {
//    JPanel p = new JPanel();
//    JComboBox<File> selectedFiles = new JComboBox<>();
//    for(File file : files) {
//      selectedFiles.addItem(file);
//    }
//    selectedFiles.setPreferredSize(new Dimension(300,30));
//    
//    JButton newFile = new JButton(Translator.translate("New File"));
//    ActionListener temp = new ActionListener() {
//        @Override
//        public void actionPerformed(ActionEvent e)
//        {
//            JFileChooser fc = new JFileChooser();
//            int returnVal = fc.showOpenDialog(new JFrame());
//        }
//    };
//    newFile.addActionListener(temp); 
//    
//    p.add(selectedFiles);
//    p.add(newFile);
//    
//    return p;
//  }
  
//  private JPanel defaultDirectory() {
//    JPanel p = new JPanel();
//    JComboBox<File> selectedFiles = new JComboBox<>();
//    for(File file : files) {
//      selectedFiles.addItem(file);
//    }
//    selectedFiles.setPreferredSize(new Dimension(300,30));
//    
//    JButton newFile = new JButton(Translator.translate("New File"));
//    ActionListener temp = new ActionListener() {
//        @Override
//        public void actionPerformed(ActionEvent e)
//        {
//            JFileChooser fc = new JFileChooser();
//            int returnVal = fc.showOpenDialog(new JFrame());
//        }
//    };
//    newFile.addActionListener(temp); 
//    
//    p.add(selectedFiles);
//    p.add(newFile);
//    
//    return p;
//  }
  
  private JPanel defaultDirectory() {
    JPanel p = new JPanel();
    JComboBox<File> selectedFiles = new JComboBox<>();
    for(File file : files) {
      selectedFiles.addItem(file);
    }
    selectedFiles.setPreferredSize(new Dimension(300,30));
    
    JButton newFile = new JButton(Translator.translate("New File"));
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
                try {
                  KitchIntelPreferenceReader.saveItem(KitchIntelPreferenceReader.DEFAULT, newFolder.getAbsolutePath());
                  selectedFiles.addItem(newFolder);
                } catch (IOException ex) {
                  System.out.print("Error, try again.");
                }
                } else {
                System.out.print("Error, try again.");
              }
            }
        }
    };
    newFile.addActionListener(temp); 
    
    p.add(selectedFiles);
    p.add(newFile);
    
    return p;
  }
  
  private JPanel changeFontSize(){
    JPanel p = new JPanel();
    
    JTextField textSize = new JTextField();
    textSize.setEditable(false);
    JLabel changeFontSizeLabel = new JLabel("Change font size: ");
    String size = null;
    try
    {
      size = (String)KitchIntelPreferenceReader.returnValue(KitchIntelPreferenceReader.FONT);
    }
    catch (IOException e3)
    {
      // TODO Auto-generated catch block
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
        String size = "0";
        try
        {
          size = (String)KitchIntelPreferenceReader.returnValue(KitchIntelPreferenceReader.FONT);
        }
        catch (IOException e2)
        {
          // TODO Auto-generated catch block
          e2.printStackTrace();
        }
        textSize.setText((String)size);
        JButton minus = new JButton("-");
        JButton plus = new JButton("+");
        String s = textSize.getText();
        int val = Integer.parseInt(s);
        JButton button = (JButton)e.getSource();
        if (button.getText().equals("+")) {
          if (val + 2 > 60) {
            return;
          }
          textSize.setText("" + (val + 2));
        } else {
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
          changeFont(mainWindow, Integer.parseInt(textSize.getText()));
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
  
  public static void changeFont(Component component, int fontSize)
  {
      component.setFont(new Font(Font.DIALOG, Font.PLAIN, fontSize));
      if ( component instanceof Container )
      {
          for (Component child : ((Container) component).getComponents ())
          {
              changeFont(child, fontSize);
          }
      }
  }
  
  private void setUp(){
    JPanel p = (JPanel) getContentPane();
    
    p.setLayout(new BorderLayout());
    p.add(defaultDirectory(), BorderLayout.NORTH);
    p.add(changeFontSize(), BorderLayout.CENTER);
    
    
    setSize(400,200);
    pack();
    setVisible(true);
  }
  
}
