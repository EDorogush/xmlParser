package by.epam.xml.parser;

import by.epam.xml.entity.Medicine;
import by.epam.xml.entity.MedicineGroup;
import by.epam.xml.entity.PharmCompany;
import by.epam.xml.entity.ReleaseType;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MedicineDomCreator {
  private static final String PHONE_DEFAULT = "undefined";

  public List<Medicine> createList(Element root) {
    List<Medicine> medicines = new ArrayList<>();
    //get main root
    NodeList medNodes = root.getElementsByTagName("tns:medicine");
    for (int i = 0; i < medNodes.getLength(); i++) {
      Element medElement = (Element) medNodes.item(i);
      medicines.add(createMedicine(medElement));
    }
    return medicines;
  }

  private Medicine createMedicine(Element parent) {
    Medicine med = new Medicine();
    med.setId(parent.getAttribute("id"));
    med.setTradeName(getBabyValue(parent, "tns:tradeName"));
    med.setRealName(getBabyValue(parent, "tns:realName"));
    med.setType(MedicineGroup.valueOf(getBabyValue(parent, "tns:group").toUpperCase()));
    med.setExpDate(LocalDate.parse(getBabyValue(parent, "tns:expDate")));
    med.setInstructions(getBabyValue(parent, "tns:instructions"));
    med.setAmount(Integer.parseInt(getBabyValue(parent, "tns:amount")));
    med.setRelease(ReleaseType.valueOf(getBabyValue(parent, "tns:releaseForm").toUpperCase()));
    med.setPharm(createPharmCompany(parent));

    Element analogsElement = getBaby(parent, "tns:analogs");
    NodeList analogList = analogsElement.getElementsByTagName("tns:analog");
    for (int j = 0; j < analogList.getLength(); j++) {
      med.addAnalog(createAnalog((Element) analogList.item(j)));
    }
    return med;
  }

  private PharmCompany createPharmCompany(Element parent) {
    PharmCompany pharm = new PharmCompany();
    Element pharmElement = getBaby(parent, "tns:pharm");
    pharm.setName(pharmElement.getAttribute("name"));

    //filling pharmCompany certificate
    PharmCompany.PharmCompanyCertificate certificate = pharm.getCertificate();
    Element certificateElement = getBaby(pharmElement, "tns:certificate");

    certificate.setId(Integer.parseInt(certificateElement.getAttribute("number")));
    certificate.setAutority(getBabyValue(certificateElement, "c:authority"));
    certificate.setIssueDate(LocalDate.parse(getBabyValue(certificateElement, "c:issueDate")));
    certificate.setExpDate(LocalDate.parse(getBabyValue(certificateElement, "c:expDate")));
    //filling pharmAddress
    PharmCompany.Address address = pharm.getAddress();
    Element addressElement = getBaby(pharmElement, "tns:address");
    address.setPhone(parseOptionalAttribute(addressElement,"phone"));
    address.setHouse(Integer.parseInt(getBabyValue(addressElement, "c:house")));
    address.setSity(getBabyValue(addressElement, "c:city"));
    address.setStreet(getBabyValue(addressElement, "c:street"));
    address.setCountry(getBabyValue(addressElement, "c:country"));
    return pharm;

  }

  private Medicine createAnalog(Element parent) {
    Medicine analog = new Medicine();
    analog.setTradeName(getBabyValue(parent, "tns:analogName"));
    analog.getPharm().setName(getBabyValue(parent, "tns:analogPharm"));
    return analog;
  }


  private Element getBaby(Element parent, String childName) {
    NodeList nlist = parent.getElementsByTagName(childName);
    Element child = (Element) nlist.item(0);
    return child;
  }


  private String getBabyValue(Element parent, String childName) {
    Element child = getBaby(parent, childName);
    Node node = child.getFirstChild();
    String value = node.getNodeValue();
    return value;
  }

  private String parseOptionalAttribute(Element parent, String attrName) {
    String phone = parent.getAttribute(attrName);
    return phone.equals("") ? PHONE_DEFAULT : phone;
  }

}
