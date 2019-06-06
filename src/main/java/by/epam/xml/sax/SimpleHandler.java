package by.epam.xml.sax;

import by.epam.xml.entity.Medicine;
import by.epam.xml.entity.MedicineGroup;
import by.epam.xml.entity.ReleaseType;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SimpleHandler extends DefaultHandler {
  private List<Medicine> meds = new ArrayList<>();
  private Medicine curr = null;
  private TagGroup currentGroup = null;
  private TagGroup parentGroup = null;

  public List<Medicine> getMeds() {
    return meds;
  }






  @Override
  public void startElement(String uri, String localName, String qName, Attributes attributes) {
    currentGroup = TagGroup.valueOf(qName.replace(":", "_").toUpperCase());
    switch (currentGroup){
      case TNS_MEDICINE:
        curr = new Medicine();
        curr.setId(attributes.getValue(0));
        break;
      case TNS_PHARM:
        curr.getPharm().setName(attributes.getValue(0));
        break;
      case TNS_CERTIFICATE:
        curr.getPharm().getCertificate().setId(Integer.parseInt(attributes.getValue(0)));
        break;
      case TNS_ADDRESS:
        curr.getPharm().getAddress().setPhone(attributes.getValue(0));
        break;
      case TNS_ANALOG:
        curr.addAnalogs(new Medicine());
        break;

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
        break;
      case TNS_RELEASEFORM:
        curr.setRelease(ReleaseType.valueOf(s.toUpperCase()));
        break;
      case TNS_AMOUNT:
        curr.setAmount(Integer.parseInt(s));
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

