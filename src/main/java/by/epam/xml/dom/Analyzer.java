package by.epam.xml.dom;

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

public class Analyzer {
  public static List<Medicine> listBuilder(Element root) {
    //throws SAXException, IOException {
    List<Medicine> medicines = new ArrayList<>();
    NodeList medNodes = root.getElementsByTagName("tns:medicine");
    for (int i = 0; i < medNodes.getLength(); i++) {
      Medicine med = new Medicine();
      Element medElement = (Element) medNodes.item(i);

      med.setId(medElement.getAttribute("id"));
      med.setTradeName(getBabyValue(medElement, "tns:tradeName"));
      med.setRealName(getBabyValue(medElement, "tns:realName"));
      med.setType(MedicineGroup.valueOf(getBabyValue(medElement, "tns:group").toUpperCase()));
      med.setExpDate(LocalDate.parse(getBabyValue(medElement, "tns:expDate")));
      med.setInstructions(getBabyValue(medElement, "tns:instructions"));
      med.setAmount(Integer.parseInt(getBabyValue(medElement, "tns:amount")));
      med.setRelease(ReleaseType.valueOf(getBabyValue(medElement, "tns:releaseForm").toUpperCase()));

      //filling pharmCompany
      PharmCompany pharm = med.getPharm();
      Element pharmElement = getBaby(medElement, "tns:pharm");
      pharm.setName(pharmElement.getAttribute("name"));

      //filling pharmCompany certificate
      PharmCompany.PharmCompanyCertificate certificate = pharm.getCertificate();
      Element certificateElement = getBaby(pharmElement, "tns:certificate");

      certificate.setId(Integer.parseInt(certificateElement.getAttribute("number")));
      certificate.setAutority(getBabyValue(certificateElement, "c:authority"));
      certificate.setIssueDate(LocalDate.parse(getBabyValue(certificateElement, "c:issueDate")));
      certificate.setExpDate(LocalDate.parse(getBabyValue(certificateElement, "c:expDate")));

      //filling pharmAddress
      PharmCompany.Address address = med.getPharm().getAddress();
      Element addressElement = getBaby(pharmElement, "tns:address");

      address.setPhone(addressElement.getAttribute("phone"));
      address.setHouse(Integer.parseInt(getBabyValue(addressElement, "c:house")));
      address.setSity(getBabyValue(addressElement, "c:city"));
      address.setStreet(getBabyValue(addressElement, "c:street"));
      address.setCountry(getBabyValue(addressElement, "c:country"));

      //filling analogs
      Element analogsElement = getBaby(medElement, "tns:analogs");

      NodeList analogList = analogsElement.getElementsByTagName("tns:analog");
      for (int j = 0; j < analogList.getLength(); j++) {
        Element analogElement = (Element) analogList.item(j);
        Medicine analog = new Medicine();
        analog.setTradeName(getBabyValue(analogElement, "tns:analogName"));
        analog.getPharm().setName(getBabyValue(analogElement, "tns:analogPharm"));
        med.addAnalogs(analog);
      }
      medicines.add(med);
    }
    return medicines;
  }

  private static Element getBaby(Element parent, String childName) {
    NodeList nlist = parent.getElementsByTagName(childName);
    Element child = (Element) nlist.item(0);
    return child;
  }


  private static String getBabyValue(Element parent, String childName) {
    Element child = getBaby(parent, childName);
    Node node = child.getFirstChild();
    String value = node.getNodeValue();
    return value;
  }

}
