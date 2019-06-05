package by.epam.xml.stax;

import by.epam.xml.entity.Medicine;
import by.epam.xml.entity.MedicineGroup;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.InputStream;
import java.time.LocalDate;
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
    //currentGroup = TagGroup.valueOf(reader.getLocalName())
    String s1 = reader.getLocalName();
    String s2 = reader.getPrefix();

    String name = reader.getLocalName().toUpperCase();
    switch (TagGroup.valueOf(name)) {
      case TNS_MEDICINES:
        //do nothing
        break;
      case TNS_MEDICINE:
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
      case TNS_MEDICINE:
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
      case TNS_TRADENAME:
        curr.setTradeName(s);
        break;
      case TNS_REALNAME:
        curr.setRealName(s);
        break;
      case TNS_EXPDATE:
        curr.setExpDate(LocalDate.parse(s));
        break;
      case TNS_GROUP:
        curr.setType(MedicineGroup.valueOf(s.toUpperCase()));
        break;
      case TNS_INSTRUCTIONS:
        curr.setInstructions(s);
        break;

    }
  }


  private enum TagGroup {
    C_AUTHORITY, C_CITY, C_COUNTRY, C_EXPDATE, C_HOUSE,
    C_ISSUEDATE, C_STREET, TNS_ADDRESS, TNS_AMOUNT, TNS_ANALOG, TNS_ANALOGNAME, TNS_ANALOGPHARM,
    TNS_ANALOGS, TNS_CERTIFICATE, TNS_EXPDATE, TNS_GROUP,
    TNS_INSTRUCTIONS, TNS_MEDICINE, TNS_MEDICINES, TNS_PHARM, TNS_REALNAME, TNS_RELEASEFORM, TNS_TRADENAME
  }
}

