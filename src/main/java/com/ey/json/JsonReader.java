package com.ey.json;

import com.itextpdf.text.BaseColor;


import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;


import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by vvasund on 23.01.2018.
 */
public class JsonReader {

  //private DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

  public File selectedFile;
  public static void main(String args[]) throws Exception{
    JsonReader jsonReader = new JsonReader();
    jsonReader.createGUI();
    //jsonReader.fileChooser();
  }

  public void createGUI() throws Exception{
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
        try {
          saveAsPDF(selectedFile);
        } catch (FileNotFoundException e1) {
          e1.printStackTrace();
        } catch (IOException e1) {
          e1.printStackTrace();
        } catch (DocumentException e1) {
          e1.printStackTrace();
        }
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

  //TODO: create the file name based on JSON

  public File createPdf(String fileName) throws IOException {

    File file = new File("resources\\json2pdf"+".pdf");
    if(file.exists()){
      System.out.println("Existing file deleted");
      file.delete();
    }
    boolean fileCreated = file.createNewFile();
    if(fileCreated){
      System.out.println("created file" + file.getName());
      return file;
    }
    else{
      System.out.println("File not created");
      return null;
    }
  }

  public void saveAsPDF(File jsonFile) throws IOException, DocumentException {

    File pdfFile = createPdf(jsonFile.getName());
    if(pdfFile!=null){
      FileOutputStream fileOutputStream = new FileOutputStream(pdfFile);
      Document document = new Document();
      PdfWriter.getInstance(document, fileOutputStream);
      document.open();

      /*
      * adding the logo on the pdf
      * */
      Image image= Image.getInstance("resources/download.png");
      document.add(image);

/*      document.addAuthor("JsonToPdf_program");
      document.addTitle("Doesn't matter");*/



/*      Font font = new Font(Font.COURIER);
      font.setStyle(Font.UNDERLINE);
      font.setStyle(Font.ITALIC);
      chunk.setFont(font);
      chunk.setBackground(Color.CYAN);*/

      /*
      * adding the title of the pdf file
      *
      * */
      Paragraph paragraph = new Paragraph();
      paragraph.add("Main Content");
      paragraph.setAlignment(Element.ALIGN_CENTER);
      document.add(paragraph);

      /*
      * Adding the remaining part of the pdf from JSON
      * */
      Font font = FontFactory.getFont(FontFactory.COURIER, 10, BaseColor.BLACK);
      Chunk chunk = new Chunk("test",font);
      document.add(chunk);

      document.close();
      System.out.println("pdf written");
      BufferedReader bufferedReader = new BufferedReader(new FileReader(jsonFile));
    }

    else{
      System.out.println("Writing  not possible");
    }



  }






}
