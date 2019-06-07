package by.epam.xml.entity;

import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.stream.Collectors;

@XmlRootElement
public class Medicine {
  private String id;
  private String tradeName;
  private String realName;
  private PharmCompany pharm = new PharmCompany();
  private MedicineGroup type;
  private List<Medicine> analogs = new ArrayList<>();
  private ReleaseType release;
  private int amount;
  private String instructions;
  private LocalDate expDate;


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getTradeName() {
    return tradeName;
  }

  public void setTradeName(String tradeName) {
    this.tradeName = tradeName;
  }

  public String getRealName() {
    return realName;
  }

  public void setRealName(String realName) {
    this.realName = realName;
  }

  public PharmCompany getPharm() {
    return pharm;
  }

  public void setPharm(PharmCompany pharm) {
    this.pharm = pharm;
  }

  public MedicineGroup getType() {
    return type;
  }

  public void setType(MedicineGroup type) {
    this.type = type;
  }

  public Medicine findLast() {
    return analogs.get(analogs.size() - 1);
  }

  public void addAnalog(Medicine analog) {
    this.analogs.add(analog);
  }

  public ReleaseType getRelease() {
    return release;
  }

  public void setRelease(ReleaseType release) {
    this.release = release;
  }

  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }

  public String getInstructions() {
    return instructions;
  }

  public void setInstructions(String instructions) {
    this.instructions = instructions;
  }

  public LocalDate getExpDate() {
    return expDate;
  }

  public void setExpDate(LocalDate expDate) {
    this.expDate = expDate;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Medicine)) return false;
    Medicine medicine = (Medicine) o;
    return amount == medicine.amount &&
            Objects.equals(id, medicine.id) &&
            Objects.equals(tradeName, medicine.tradeName) &&
            Objects.equals(realName, medicine.realName) &&
            Objects.equals(pharm, medicine.pharm) &&
            type == medicine.type &&
            Objects.equals(analogs, medicine.analogs) &&
            release == medicine.release &&
            Objects.equals(instructions, medicine.instructions) &&
            Objects.equals(expDate, medicine.expDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, tradeName, realName, pharm, type, analogs, release, amount, instructions, expDate);
  }

  @Override
  public String toString() {
    return new StringJoiner(",\n\t", Medicine.class.getSimpleName() + ":\n\t", "]\n")
            .add("id='" + id + "'")
            .add("tradeName='" + tradeName + "'")
            .add("realName='" + realName + "'")
            .add(pharm.toString())
            .add("type=" + type)
            .add("analogs=" + analogs.stream().map(Medicine::getTradeName).collect(Collectors.toList()))
            .add("release=" + release)
            .add("amount=" + amount)
            .add("instructions='" + instructions + "'")
            .add("expDate=" + expDate)
            .toString();
  }

}
