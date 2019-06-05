package by.epam.xml.entity;

import java.time.LocalDate;
import java.util.StringJoiner;

public class PharmCompany {
  private String name;
  private PharmCompanyCertificate certificate = new PharmCompanyCertificate();
  private Address address = new Address();

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public PharmCompanyCertificate getCertificate() {
    return certificate;
  }

  public void setCertificate(PharmCompanyCertificate certificate) {
    this.certificate = certificate;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  @Override
  public String toString() {
    return new StringJoiner(",\n\t\t", PharmCompany.class.getSimpleName() + "[\n\t\t", "]")
            .add("name='" + name + "'")
            .add(certificate.toString())
            .add(address.toString())
            .toString();
  }


  public class Address {
    private String country;
    private String sity;
    private String street;
    private int house;
    private String phone;

    public String getCountry() {
      return country;
    }

    public void setCountry(String country) {
      this.country = country;
    }

    public String getSity() {
      return sity;
    }

    public void setSity(String sity) {
      this.sity = sity;
    }

    public String getStreet() {
      return street;
    }

    public void setStreet(String street) {
      this.street = street;
    }

    public int getHouse() {
      return house;
    }

    public void setHouse(int house) {
      this.house = house;
    }

    public String getPhone() {
      return phone;
    }

    public void setPhone(String phone) {
      this.phone = phone;
    }

    @Override
    public String toString() {
      return new StringJoiner(",\n\t\t\t", Address.class.getSimpleName() + "[\n\t\t\t", "]")
              .add("country='" + country + "'")
              .add("sity='" + sity + "'")
              .add("street='" + street + "'")
              .add("house=" + house)
              .add("phone='" + phone + "'")
              .toString();
    }
  }

  public class PharmCompanyCertificate {
    private int id;
    private LocalDate issueDate;
    private LocalDate expDate;
    private String autority;

    public int getId() {
      return id;
    }

    public void setId(int id) {
      this.id = id;
    }

    public LocalDate getIssueDate() {
      return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
      this.issueDate = issueDate;
    }

    public LocalDate getExpDate() {
      return expDate;
    }

    public void setExpDate(LocalDate expDate) {
      this.expDate = expDate;
    }

    public String getAutority() {
      return autority;
    }

    public void setAutority(String autority) {
      this.autority = autority;
    }

    @Override
    public String toString() {
      return new StringJoiner(",\n\t\t\t", PharmCompanyCertificate.class.getSimpleName() + "[\n\t\t\t", "]")
              .add("id=" + id)
              .add("issueDate=" + issueDate)
              .add("expDate=" + expDate)
              .add("autority='" + autority + "'")
              .toString();
    }
  }


}
