package by.epam.xml.entity;

import by.epam.xml.exception.CustomException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.LocalDate;

import static org.testng.Assert.assertEquals;

public class PharmacyTest {
  private Medicine expected;

  @BeforeClass
  public void setUp() {
    expected = new Medicine();
    expected.setTradeName("Amoxicillin");
    expected.setRealName("Amoxicillin");
    expected.setId("a1");
    expected.setType(MedicineGroup.ANTIBIOTIC);
    expected.setAmount(10);
    expected.setInstructions("eat");
    expected.setExpDate(LocalDate.parse("2019-01-01"));
    expected.setRelease(ReleaseType.CAPSULE);

    PharmCompany pharmCompany = new PharmCompany();
    pharmCompany.setName("BelMed");
    pharmCompany.getAddress().setCountry("Belarus");
    pharmCompany.getAddress().setSity("Minsk");
    pharmCompany.getAddress().setStreet("Kupala");
    pharmCompany.getAddress().setHouse(1);
    pharmCompany.getAddress().setPhone("029-6234567");
    pharmCompany.getCertificate().setId(1001);
    pharmCompany.getCertificate().setIssueDate(LocalDate.parse("2010-01-01"));
    pharmCompany.getCertificate().setExpDate(LocalDate.parse("2020-01-01"));
    pharmCompany.getCertificate().setAutority("ministry");

    expected.setPharm(pharmCompany);

    Medicine analog = new Medicine();
    analog.setTradeName("Amoxiklav");
    analog.getPharm().setName("some");
    expected.addAnalog(analog);

  }

  @Test
  public void builderWithXMLSaxParserSucceed() throws CustomException {

    Pharmacy pharmacy = new Pharmacy.Builder().withXMLSaxParser("med.xml").build();
    Medicine actual = pharmacy.find(0);
    System.out.println(expected.equals(actual));
    System.out.println(actual.toString());

    assertEquals(actual,expected);

  }

  @Test
  public void builderWithXMLDomParserSucceed() throws CustomException {

    Pharmacy pharmacy = new Pharmacy.Builder().withXMLDomParser("med.xml").build();
    Medicine actual = pharmacy.find(0);
    System.out.println(expected.equals(actual));
    System.out.println(actual.toString());

    assertEquals(actual,expected);
  }

  @Test
  public void builderWithXMLStaxParserSucceed() throws CustomException {

    Pharmacy pharmacy = new Pharmacy.Builder().withXMLStaxParser("med.xml").build();
    Medicine actual = pharmacy.find(0);
    System.out.println(expected.equals(actual));
    System.out.println(actual.toString());

    assertEquals(actual,expected);
  }
}