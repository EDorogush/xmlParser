package by.epam.xml.sax;

import by.epam.xml.entity.Medicine;
import by.epam.xml.entity.MedicineGroup;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SimpleHandler extends DefaultHandler {
  private List<Medicine> meds = new ArrayList<>();
  private Medicine curr = null;
  private SimpleTagGroup currentGroup = null;
  private SimpleTagGroup parentGroup = null;

  public List<Medicine> getMeds() {
    return meds;
  }

  @Override
  public void startElement(String uri, String localName, String qName, Attributes attributes) {
//    SimpleTagGroup parent = SimpleTagGroup.valueOf(qName.toUpperCase());
//    switch (parent){
//      case MEDICINE:
//        curr = new Medicine();
//        curr.setId(attributes.getValue(0));
//    }


    if (qName.equals("tns:medicine")) {
      curr = new Medicine();
      curr.setId(attributes.getValue(0));
    } else if (qName.equals("tns:pharm")) {
      curr.getPharm().setName(attributes.getValue(0));

    } else if (qName.equals("tns:certificate")) {
      curr.getPharm().getCertificate().setId(Integer.parseInt(attributes.getValue(0)));

    } else if (qName.equals("tns:address")) {
      curr.getPharm().getAddress().setPhone(attributes.getValue(0));

    } else if (qName.equals("tns:analog")) {
      curr.addAnalogs(new Medicine());

    }
    else if (!qName.equals("tns:medicines") && !qName.equals("tns:analogs")) {
      currentGroup = SimpleTagGroup.valueOf(qName.replace(":", "_").toUpperCase());
    }

  }

  @Override
  public void endElement(String uri, String localName, String qName) {
    if (qName.equals("tns:medicine"))
      meds.add(curr);
    currentGroup = null;

  }

  @Override
  public void characters(char[] ch, int start, int length) throws SAXException {
    String s = new String(ch, start, length).trim();
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


    }
  }

  private enum SimpleTagGroup {
    TNS_TRADENAME, TNS_REALNAME, TNS_GROUP, TNS_INSTRUCTIONS, TNS_EXPDATE,
    C_ISSUEDATE, C_EXPDATE, C_AUTHORITY, C_COUNTRY, C_CITY, C_STREET, C_HOUSE,
    TNS_ANALOGNAME, TNS_ANALOGPHARM;


//    */MEDICINE, PHARM, CERTIFICATE, ADDRESS, ANALOGS,ANALOG,

  }


  private enum ParentTagGroup {
    MEDICINE, PHARM, CERTIFICATE, ADDRESS, ANALOGS, ANALOG,
  }


}

