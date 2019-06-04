package by.epam.xml.entity;

import java.time.LocalDate;
import java.util.List;

public class Medicine {
  private String id;
  private String tradeName;
  private String realName;
  private PharmCompany pharm;
  private MedicineGroup type;
  private List<Medicine> analogs;
  private ReleaseForm release;
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

  public List<Medicine> getAnalogs() {
    return analogs;
  }

  public void setAnalogs(List<Medicine> analogs) {
    this.analogs = analogs;
  }

  public ReleaseForm getRelease() {
    return release;
  }

  public void setRelease(ReleaseForm release) {
    this.release = release;
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

  public class ReleaseForm{
    private MedConsistencyType consistency;
    private int amount;

    public MedConsistencyType getConsistency() {
      return consistency;
    }

    public void setConsistency(MedConsistencyType consistency) {
      this.consistency = consistency;
    }

    public int getAmount() {
      return amount;
    }

    public void setAmount(int amount) {
      this.amount = amount;
    }
  }
}
