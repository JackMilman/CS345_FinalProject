package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import config.Translator;
import preferences.KitchIntelPreferenceReader;

public class PreferenceWindow extends JFrame
{
  
  private static final long serialVersionUID = 1L;
  private static ArrayList<File>files;
  //private static final File file = new File("preferences.txt");

  public PreferenceWindow() throws IOException {
    super();
    files = new ArrayList<File>();
    setUp();
  }
  
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
            int returnVal = fc.showOpenDialog(new JFrame());
        }
    };
    newFile.addActionListener(temp); 
    
    p.add(selectedFiles);
    p.add(newFile);
    
    return p;
  }
  
  private JPanel changeFontSize() throws IOException {
    JPanel p = new JPanel();
    JTextField textSize = new JTextField();
    textSize.setEditable(false);
    String size = (String)KitchIntelPreferenceReader.returnValue(KitchIntelPreferenceReader.FONT).trim();
    textSize.setText((String)size);
    JButton minus = new JButton("-");
    JButton plus = new JButton("+");
    String s = textSize.getText();
    int val = Integer.parseInt(s);
    
    ActionListener changeFontSize = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e)
      {
        if (e.getActionCommand().equals("+")) {
          try
          {
            KitchIntelPreferenceReader.saveItem(KitchIntelPreferenceReader.FONT, "" + (val + 2));
            textSize.setText("" + (val + 2));
          }
          catch (IOException e1)
          {
            System.out.println("Invalid Font");
          }
        } else {
          try
          {
            KitchIntelPreferenceReader.saveItem(KitchIntelPreferenceReader.FONT, "" + (val - 2));
          }
          catch (IOException e1)
          {
            System.out.println("Invalid Font");
          }
        }
      }
    };
    textSize.addActionListener(changeFontSize);
    
    p.add(minus);
    p.add(textSize);
    p.add(plus);
    
    return p;
  }
  
  private void setUp() throws IOException {
    JPanel p = (JPanel) getContentPane();
    
    p.setLayout(new BorderLayout());
    p.add(defaultDirectory(), BorderLayout.NORTH);
    p.add(changeFontSize(), BorderLayout.CENTER);
    
    
    setSize(400,200);
    pack();
    setVisible(true);
  }
  
}
