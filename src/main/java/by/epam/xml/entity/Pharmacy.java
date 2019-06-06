package by.epam.xml.entity;

import by.epam.xml.parser.MedicineDomCreator;
import by.epam.xml.parser.MedicineSaxHandler;
import by.epam.xml.stax.StAXParser;
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
  private List<Medicine> meds;

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
      StAXParser parser = new StAXParser();
      try {
        parser.parse(new FileInputStream(fileName));
        pharmacy.meds =  parser.getMeds();
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      }
      return this;

    }

    }



}