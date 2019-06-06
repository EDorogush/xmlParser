package by.epam.xml.parser;

import by.epam.xml.entity.Medicine;
import by.epam.xml.entity.MedicineGroup;
import by.epam.xml.entity.ReleaseType;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class MedicineStAXParser {
  private List<Medicine> meds = new ArrayList<>();
  private Medicine curr = null;
  private TagGroup currentGroup = null;

  public List<Medicine> findAll() {

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
    //form qName as "prefix_localName"
    String qName = new StringBuffer().append(reader.getPrefix()).append("_").append(reader.getLocalName()).toString();
    currentGroup = TagGroup.valueOf(qName.toUpperCase());
    switch (currentGroup) {
      case TNS_MEDICINE:
        curr = new Medicine();
        curr.setId(reader.getAttributeValue(0));
        break;
      case TNS_PHARM:
        curr.getPharm().setName(reader.getAttributeValue(0));
        break;
      case TNS_CERTIFICATE:
        curr.getPharm().getCertificate().setId(Integer.parseInt(reader.getAttributeValue(0)));
        break;
      case TNS_ADDRESS:
        curr.getPharm().getAddress().setPhone(reader.getAttributeValue(0));
        break;
      case TNS_ANALOG:
        curr.addAnalog(new Medicine());
        break;
    }
  }

  private void processEndElem(XMLStreamReader reader) {
    String name = reader.getLocalName();
    if (name.equals("medicine"))
      meds.add(curr);
    currentGroup = null;
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
      case TNS_GROUP:
        curr.setType(MedicineGroup.valueOf(s.toUpperCase()));
        break;
      case TNS_INSTRUCTIONS:
        curr.setInstructions(s);
        break;
      case TNS_EXPDATE:
        curr.setExpDate(LocalDate.parse(s));
        break;
      case C_ISSUEDATE:
        curr.getPharm().getCertificate().setIssueDate(LocalDate.parse(s));
        break;
      case C_EXPDATE:
        curr.getPharm().getCertificate().setExpDate(LocalDate.parse(s));
        break;
      case C_AUTHORITY:
        curr.getPharm().getCertificate().setAutority(s);
        break;
      case C_COUNTRY:
        curr.getPharm().getAddress().setCountry(s);
        break;
      case C_STREET:
        curr.getPharm().getAddress().setStreet(s);
        break;
      case C_CITY:
        curr.getPharm().getAddress().setSity(s);
        break;
      case C_HOUSE:
        curr.getPharm().getAddress().setHouse(Integer.parseInt(s));
        break;
      case TNS_ANALOGNAME:
        curr.findLast().setTradeName(s);
        break;
      case TNS_ANALOGPHARM:
        curr.findLast().getPharm().setName(s);
        break;
      case TNS_RELEASEFORM:
        curr.setRelease(ReleaseType.valueOf(s.toUpperCase()));
        break;
      case TNS_AMOUNT:
        curr.setAmount(Integer.parseInt(s));
        break;
      // default: throw new CustomException(String.format("No enum value for  found",s));

    }
//      if (currentGroup == null) return;
//    switch (currentGroup) {
//      case TNS_TRADENAME:
//        curr.setTradeName(s);
//        break;
//      case TNS_REALNAME:
//        curr.setRealName(s);
//        break;
//      case TNS_EXPDATE:
//        curr.setExpDate(LocalDate.parse(s));
//        break;
//      case TNS_GROUP:
//        curr.setType(MedicineGroup.valueOf(s.toUpperCase()));
//        break;
//      case TNS_INSTRUCTIONS:
//        curr.setInstructions(s);
//        break;
//
//    }
  }


  private enum TagGroup {
    C_AUTHORITY, C_CITY, C_COUNTRY, C_EXPDATE, C_HOUSE,
    C_ISSUEDATE, C_STREET, TNS_ADDRESS, TNS_AMOUNT, TNS_ANALOG, TNS_ANALOGNAME, TNS_ANALOGPHARM,
    TNS_ANALOGS, TNS_CERTIFICATE, TNS_EXPDATE, TNS_GROUP,
    TNS_INSTRUCTIONS, TNS_MEDICINE, TNS_MEDICINES, TNS_PHARM, TNS_REALNAME, TNS_RELEASEFORM, TNS_TRADENAME
  }
}

