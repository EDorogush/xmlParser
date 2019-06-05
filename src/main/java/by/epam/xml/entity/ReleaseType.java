package by.epam.xml.entity;

public enum ReleaseType {
  LIQUID("ml"),
  PILL("quantity"),
  CAPSULE("quantity"),
  SUSPENSION("gram"),
  DROP("ml");
  private String packaging;

  ReleaseType(String type) {
    this.packaging = type;

  }
  public String getPackaging() {
    return packaging;
  }

  public void setPackaging(String packaging) {
    this.packaging = packaging;
  }
}
