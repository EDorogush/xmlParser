package by.epam.xml.stax;

import by.epam.xml.entity.Medicine;
import by.epam.xml.entity.MedicineGroup;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class StAXParser {
  private List<Medicine> meds = new ArrayList<>();
  private Medicine curr = null;
  private TagGroup currentGroup = null;

  public List<Medicine> getMeds() {
    return meds;
  }

  public void parse(InputStream input) {
    XMLInputFactory factory = XMLInputFactory.newInstance();
    try {
      XMLStreamReader xmlStreamReader = factory.createXMLStreamReader(input);
      process(xmlStreamReader);

    } catch (Exception e) {
      e.printStackTrace();

    }
  }

  private void process(XMLStreamReader reader) throws XMLStreamException {
    String name;


    while (reader.hasNext()) {
      int type = reader.next();
      switch (type) {
        case XMLStreamConstants.START_ELEMENT:
          processStartElem(reader);
          break;
        case XMLStreamConstants.END_ELEMENT:
          processEndElem(reader);
          break;
        case XMLStreamConstants.CHARACTERS:
          processCharacetrs(reader);
          break;


      }
    }
  }

  private void processStartElem(XMLStreamReader reader) {
    String name = reader.getLocalName().toUpperCase();
    switch (TagGroup.valueOf(name)) {
      case MEDICINES:
        //do nothing
        break;
      case MEDICINE:
        curr = new Medicine();
        curr.setId(reader.getAttributeValue(0));
        break;
      default:
        currentGroup = TagGroup.valueOf(name);
    }
  }

  private void processEndElem(XMLStreamReader reader) {
    String name = reader.getLocalName();
    switch (TagGroup.valueOf(name.toUpperCase())) {
      case MEDICINE:
        meds.add(curr);
        break;
      default:
        currentGroup = null;
    }
  }

  private void processCharacetrs(XMLStreamReader reader) {

    String s = reader.getText().trim();
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
    MEDICINES, MEDICINE, NAME, PHARM, GROUP
  }
}

