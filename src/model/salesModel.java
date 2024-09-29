
package model;


public class salesModel {
    
   private int saleId;
   private int INID;
   private String customerName;
   private double Total_Quantity;
   private double Total;
   private double payed;
   private String status;
   private double Remained;
   private String date;
   private String empname;
   private double Discount;
   private double AllTotal;
   private String dayDate;
   private String saleType;

    public String getSaleType() {
        return saleType;
    }

    public void setSaleType(String saleType) {
        this.saleType = saleType;
    }
   
    public double getTotal() {
        return Total;
    }

    public void setTotal(double Total) {
        this.Total = Total;
    }

    public int getSaleId() {
        return saleId;
    }

    public void setSaleId(int saleId) {
        this.saleId = saleId;
    }

    public int getINID() {
        return INID;
    }

    public void setINID(int INID) {
        this.INID = INID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public double getTotal_Quantity() {
        return Total_Quantity;
    }

    public void setTotal_Quantity(double Total_Quantity) {
        this.Total_Quantity = Total_Quantity;
    }

    public double getPayed() {
        return payed;
    }

    public void setPayed(double payed) {
        this.payed = payed;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getRemained() {
        return Remained;
    }

    public void setRemained(double Remained) {
        this.Remained = Remained;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEmpname() {
        return empname;
    }

    public void setEmpname(String empname) {
        this.empname = empname;
    }

    public double getDiscount() {
        return Discount;
    }

    public void setDiscount(double Discount) {
        this.Discount = Discount;
    }

    public double getAllTotal() {
        return AllTotal;
    }

    public void setAllTotal(double AllTotal) {
        this.AllTotal = AllTotal;
    }

    public String getDayDate() {
        return dayDate;
    }

    public void setDayDate(String dayDate) {
        this.dayDate = dayDate;
    }
   
}
