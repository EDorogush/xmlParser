package by.epam.xml.entity;

public enum MedConsistencyType {
  LIQUID("ml"),
  PILL("quantity"),
  CAPSULE("quantity"),
  SUSPENSION("ml"),
  DROP("gram");
  private String packaging;

  MedConsistencyType(String type) {
    this.packaging = type;

  }

  public String getPackaging() {
    return packaging;
  }

  public void setPackaging(String packaging) {
    this.packaging = packaging;
  }
}
