package by.epam.xml.entity;

import by.epam.xml.exception.CustomException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.LocalDate;

import static org.testng.Assert.assertEquals;

public class PharmacyTest {
  private Medicine expected;
  private static final String FILE_NAME = "med.xml";

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
  public void builderWithXMLSaxParsedCorrectMedicine() throws CustomException {

    Pharmacy pharmacy = new Pharmacy.Builder().withXMLSaxParser(FILE_NAME).build();
    Medicine actual = pharmacy.find(0);
    assertEquals(actual,expected);

  }

  @Test
  public void builderWithXMLDomParsedCorrectMedicine() throws CustomException {

    Pharmacy pharmacy = new Pharmacy.Builder().withXMLDomParser(FILE_NAME).build();
    Medicine actual = pharmacy.find(0);
    assertEquals(actual,expected);
  }

  @Test
  public void builderWithXMLStaxParsedCorrectMedicine() throws CustomException {

    Pharmacy pharmacy = new Pharmacy.Builder().withXMLStaxParser(FILE_NAME).build();
    Medicine actual = pharmacy.find(0);
    assertEquals(actual,expected);
  }

  @Test
  public void builderWithXMLSaxParseFull() throws CustomException {
    Pharmacy pharmacy = new Pharmacy.Builder().withXMLSaxParser(FILE_NAME).build();
    int actual = pharmacy.findAll().size();
    assertEquals(actual,16);
  }
  @Test
  public void builderWithXMLDomParseFull() throws CustomException {
    Pharmacy pharmacy = new Pharmacy.Builder().withXMLDomParser(FILE_NAME).build();
    int actual = pharmacy.findAll().size();
    assertEquals(actual,16);
  }
  @Test
  public void builderWithXMLStaxParseFull() throws CustomException {
    Pharmacy pharmacy = new Pharmacy.Builder().withXMLStaxParser(FILE_NAME).build();
    int actual = pharmacy.findAll().size();
    assertEquals(actual,16);
  }
}