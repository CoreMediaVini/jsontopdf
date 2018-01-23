package com.ey.json;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Created by vvasund on 23.01.2018.
 */
public class JsonReader {

  public File selectedFile;
  public static void main(String args[]){
    JsonReader jsonReader = new JsonReader();
    jsonReader.createGUI();
    //jsonReader.fileChooser();
  }

  public void createGUI(){
    final JFrame frame = new JFrame("JSON to PDF convertor");
    frame.setSize(500,500);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().setLayout(new FlowLayout());
    JButton selectButton = new JButton("Choose JSON File");
    selectButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        selectedFile = fileChooser();
      }
    });

    //todo : probably not required. if required will have to save the selected file on the jframe and pass it on the convert button and also the function..
    JButton convertButton = new JButton("Save as PDF");
    convertButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        saveAsPDF(selectedFile);
      }
    });
    frame.getContentPane().add(selectButton);
    frame.getContentPane().add(convertButton);

  }

  public File fileChooser(){
    final JFileChooser jFileChooser = new JFileChooser();
    //jFileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
    jFileChooser.showSaveDialog(null);
    jFileChooser.addChoosableFileFilter(new FileNameExtensionFilter("*.txt","txt"));
    return jFileChooser.getSelectedFile();
  }

  public void saveAsPDF(File file){

  }






}
