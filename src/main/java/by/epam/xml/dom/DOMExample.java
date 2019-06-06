package by.epam.xml.dom;

import by.epam.xml.entity.Medicine;
import by.epam.xml.parser.MedicineDomCreator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class DOMExample {
  public static void main(String[] args) {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    try {
      DocumentBuilder builder = factory.newDocumentBuilder();
      try {
        Document document = builder.parse(new File("med.xml"));
        Element root = document.getDocumentElement();
        List<Medicine> meds = MedicineDomCreator.createList(root);
        System.out.println(meds.toString());
      } catch (SAXException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      }
    } catch (ParserConfigurationException e) {
      e.printStackTrace();
    }

  }
}
