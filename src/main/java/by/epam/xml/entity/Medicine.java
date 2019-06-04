package by.epam.xml.entity;

public class Medicine {
  private String id;
  private String name;
  private String pharm;
  private MedicineGroup type;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPharm() {
    return pharm;
  }

  public void setPharm(String pharm) {
    this.pharm = pharm;
  }

  public MedicineGroup getType() {
    return type;
  }

  public void setType(MedicineGroup type) {
    this.type = type;
  }

  @Override
  public String toString() {
    return "medicine{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", pharm='" + pharm + '\'' +
            ", type=" + type +
            '}';
  }
}
