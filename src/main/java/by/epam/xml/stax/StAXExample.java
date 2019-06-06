package by.epam.xml.stax;

import by.epam.xml.parser.MedicineStAXParser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class StAXExample {
  public static void main(String[] args) {
    MedicineStAXParser parser = new MedicineStAXParser();
    try {
      parser.parse(new FileInputStream("med.xml"));
      System.out.println(parser.findAll().toString());
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

  }
}
