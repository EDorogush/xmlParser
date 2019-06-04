package by.epam.xml.dom;

import by.epam.xml.entity.Medicine;
import by.epam.xml.entity.MedicineGroup;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

public class Analyzer {
  public static List<Medicine> listBuilder(Element root) {
    //throws SAXException, IOException {
    List<Medicine> medicines = new ArrayList<Medicine>();
    NodeList medNodes = root.getElementsByTagName("medicine");
    for (int i = 0; i < medNodes.getLength(); i++) {
      Medicine med = new Medicine();
      Element medElement = (Element) medNodes.item(i);
      med.setId(medElement.getAttribute("login"));
      med.setName(getBabyValue(medElement, "name"));
      med.setPharm(getBabyValue(medElement, "pharm"));
      med.setType(MedicineGroup.valueOf(getBabyValue(medElement, "group").toUpperCase()));
      medicines.add(med);
    }
    return medicines;
  }

  private static Element getBaby(Element parent, String childName) {
    NodeList nlist = parent.getElementsByTagName(childName);
    Element child = (Element) nlist.item(0);
    return child;
  }


  private static String getBabyValue(Element parent, String childName){
      Element child = getBaby(parent, childName);
      Node node = child.getFirstChild();
      String value = node.getNodeValue();
      return value;
    }

  }
