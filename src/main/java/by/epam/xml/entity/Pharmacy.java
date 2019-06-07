package by.epam.xml.entity;

import by.epam.xml.exception.CustomException;
import by.epam.xml.parser.MedicineDomCreator;
import by.epam.xml.parser.MedicineSaxHandler;
import by.epam.xml.parser.MedicineStAXParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;


public class Pharmacy {
  private static final Logger logger = LogManager.getLogger();
  private List<Medicine> meds;

  public List<Medicine> findAll(){
    return meds;
  }
  public Medicine find(int index) throws CustomException{
    if (index<0 || index > meds.size()){
      throw new CustomException(String.format("Index must be positive and less them array size: {%d}",meds.size()));
    }
    return meds.get(index);
  }

  public static class Builder {
    private Pharmacy pharmacy;

    public Builder() {
      pharmacy = new Pharmacy();
    }

    public Builder withXMLSaxParser(String fileName) {
      SAXParserFactory factory = SAXParserFactory.newInstance();
      factory.setNamespaceAware(true);
      try {
        SAXParser parser = factory.newSAXParser();
        MedicineSaxHandler handler = new MedicineSaxHandler();

        parser.parse(new File(fileName), handler);
        pharmacy.meds = handler.findAll();


      } catch (ParserConfigurationException | SAXException e) {
        e.printStackTrace();
      } catch (Exception e) {
        e.printStackTrace();
      }
      return this;
    }

    public Builder withXMLDomParser(String fileName) {
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      try {
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File(fileName));
        Element root = document.getDocumentElement();
        pharmacy.meds = new MedicineDomCreator().createList(root);
      } catch (ParserConfigurationException | IOException | SAXException e) {
        e.printStackTrace();
      }
      return this;

    }

    public Builder withXMLStaxParser(String fileName) {
      MedicineStAXParser parser = new MedicineStAXParser();
      try {
        parser.parse(new FileInputStream(fileName));
        pharmacy.meds =  parser.findAll();
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      }
      return this;

    }
    public Pharmacy build(){
      return pharmacy;
    }

    }



}