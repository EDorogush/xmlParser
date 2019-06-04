package by.epam.xml.sax;

import by.epam.xml.entity.Medicine;
import by.epam.xml.entity.MedicineGroup;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class SimpleHandler extends DefaultHandler {
  private List<Medicine> meds = new ArrayList<>();
  private Medicine curr = null;
  private TagGroup currentGroup = null;

  public List<Medicine> getMeds() {
    return meds;
  }

  @Override
  public void startElement(String uri, String localName, String qName, Attributes attributes) {
    if (qName.equals("medicine")) {
      curr = new Medicine();
      curr.setId(attributes.getValue(0));
    } else if (!qName.equals("tns:medicines")) {
      currentGroup = TagGroup.valueOf(qName.toUpperCase());
    }

  }

  @Override
  public void endElement(String uri, String localName, String qName) {
    if (qName.equals("medicine"))
      meds.add(curr);
    currentGroup = null;

  }

  @Override
  public void characters(char[] ch, int start, int length) throws SAXException {
    String s = new String(ch, start, length).trim();
    if (currentGroup == null) return;
    switch (currentGroup) {
      case NAME:
        curr.setName(s);
        break;
      case PHARM:
        curr.setPharm(s);
        break;
      case GROUP:
        curr.setType(MedicineGroup.valueOf(s.toUpperCase()));
    }
  }

  private enum TagGroup {
    NAME, PHARM, GROUP
  }


}
//
//enum StudentEnum {
//  NAME, TELEPHONE, STREET, CITY, COUNTRY
//}

