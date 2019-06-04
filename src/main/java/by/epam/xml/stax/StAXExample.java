package by.epam.xml.stax;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class StAXExample {
  public static void main(String[] args) {
    StAXParser parser = new StAXParser();
    try {
      parser.parse(new FileInputStream("med.xml"));
      System.out.println(parser.getMeds().toString());
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

  }
}
