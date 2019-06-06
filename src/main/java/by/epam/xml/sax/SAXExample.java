package by.epam.xml.sax;

import by.epam.xml.entity.Medicine;
import by.epam.xml.parser.MedicineSaxHandler;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SAXExample {
  public static void main(String[] args) {
    List<Medicine> meds = new ArrayList<>();
    //FIXME

    SAXParserFactory factory = SAXParserFactory.newInstance();
    factory.setNamespaceAware(true);

    try {
      SAXParser parser = factory.newSAXParser();
      MedicineSaxHandler handler = new MedicineSaxHandler();

      parser.parse(new File("med.xml"), handler);
      System.out.println("print meds");
      System.out.println(handler.findAll().toString());

    } catch (ParserConfigurationException e) {
      e.printStackTrace();
    } catch (SAXException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }


  }

}
