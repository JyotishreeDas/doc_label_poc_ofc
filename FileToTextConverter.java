package com.kn.documentlabelling.dl.common.api.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.util.LoadLibs;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

/**
 * @author ujjjha
 *
 */
public class FileToTextConverter {

  /**
   * @param file any file to convert to simple text
   * @param fileName name of this file
   * @return content as string
   * @throws SAXException
   * @throws TikaException
   */
  public String convertFileToText(File file, String fileName) throws SAXException, TikaException {

    String content = "";
    FileInputStream fis = null;
    int index = fileName.lastIndexOf('.');
    String fileExtension = fileName.substring(index + 1);
    System.out.println("fileExtension " + fileExtension);
    try {

      fis = new FileInputStream(file);
      System.out.println("Total file size to read (in bytes) : " + fis.available());

      if ("jpeg".equalsIgnoreCase(fileExtension) || "jpg".equalsIgnoreCase(fileExtension)
          || "png".equalsIgnoreCase(fileExtension)) {
        content = processImage(file);
        fis.close();
        return content;
      }
      content = processFile(file, fileExtension);
      System.out.println("content-----------------" + content);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return content;
  }

  /**
   * @param file type of pdf
   * @param fileExtension of this file
   * @return content as string
   * @throws IOException
   * @throws SAXException
   */
  public String processFile(File file, String fileExtension) throws IOException, SAXException {

    System.out.println("Calling processFile to convert All to TEXT");
    AutoDetectParser parser = new AutoDetectParser();
    BodyContentHandler handler = new BodyContentHandler();
    Metadata metadata = new Metadata();
    FileInputStream stream = new FileInputStream(file);
    try {
      parser.parse(stream, handler, metadata);
    } catch (TikaException e) {
      e.printStackTrace();
    }
    if (handler.toString().trim().length() > 0) {
      System.out.println("The documents is being parsed");
    } else {
      if ("pdf".equals(fileExtension)) {
        System.out.println("The documents is not parsed --> OCR ");
        return processImage(file);
      }
    }
    return handler.toString();
  }

  /**
   * @param file of typer image
   * @return simple text
   */
  public String processImage(File file) {

    System.out.println("Calling processImage to convert IMAGE to TEXT");
    String result = "";
    try {
      Tesseract instance = new Tesseract();
      File tessDataFolder = LoadLibs.extractTessResources("tessdata");
      String path = tessDataFolder.getAbsolutePath();
      instance.setDatapath(path);
      result = instance.doOCR(file);
      System.out.println("Image has processed ::: " + result.length());
    } catch (TesseractException e) {
      e.printStackTrace();
    }
    return result;
  }

}








/*
package com.kn.documentlabelling.dl.common.api.util;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.util.LoadLibs;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;


import com.asprise.ocr.*;






public class FileToTextConverter {
	
	

  *//**
   * @param file any file to convert to simple text
   * @param fileName name of this file
   * @return content as string
   * @throws SAXException
   * @throws TikaException
   *//*
  public String convertFileToText(File file, String fileName) throws SAXException, TikaException {

    String content = "";
    FileInputStream fis = null;
    int index = fileName.lastIndexOf('.');
    String fileExtension = fileName.substring(index + 1);
    System.out.println("fileExtension " + fileExtension);
    try {

      fis = new FileInputStream(file);
      System.out.println("Total file size to read (in bytes) : " + fis.available());

      if ("jpeg".equalsIgnoreCase(fileExtension) || "jpg".equalsIgnoreCase(fileExtension)
          || "png".equalsIgnoreCase(fileExtension)) {
        content = processImage(file,fileExtension);
        System.out.println("content-----------------" + content);
        fis.close();
        return content;
      }
      content = processFile(file, fileExtension);
      System.out.println("content-----------------" + content);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return content;
  }

  *//**
   * @param file type of pdf
   * @param fileExtension of this file
   * @return content as string
   * @throws IOException
   * @throws SAXException
   *//*
  public String processFile(File file, String fileExtension) throws IOException, SAXException {

    System.out.println("Calling processFile to convert All to TEXT");
    AutoDetectParser parser = new AutoDetectParser();
    BodyContentHandler handler = new BodyContentHandler();
    Metadata metadata = new Metadata();
    FileInputStream stream = new FileInputStream(file);
    try {
      parser.parse(stream, handler, metadata);
    } catch (TikaException e) {
      e.printStackTrace();
    }
    if (handler.toString().trim().length() > 0) {
      System.out.println("The documents is being parsed");
    } else {
      if ("pdf".equals(fileExtension)) {
        System.out.println("The documents is not parsed --> OCR ");
      
      }
    }
    return handler.toString();
  }

  *//**
   * @param file of typer image
   * @return simple text
   *//*
  public String processImage(File file,String fileExtension) {

    System.out.println("Calling processImage to convert IMAGE to TEXT");
    
    String result = "";
    
    Tesseract instance = new Tesseract();
    try {
      
      
   
    Ocr.setUp();
    Ocr ocr = new Ocr(); // create a new OCR engine
    ocr.startEngine("eng", Ocr.SPEED_FASTEST); 
    result = ocr.recognize(new File[] {new File("D:\\waybill\\D84C71D4.png")}, Ocr.RECOGNIZE_TYPE_TEXT, Ocr.OUTPUT_FORMAT_PLAINTEXT);
        	
     System.out.println("Image has processed ::: " + result);
     ocr.stopEngine();
          
    
     return result;
  
    
  }
    finally{}
}
}
*/