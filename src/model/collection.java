
package model;


public class collection {
    
    private int id;
    private String customername;
    private String Address;
    private double remainedBefore;
    private double payed;
    private double remainedAfter;
    private String date;

    public double getRemainedBefore() {
        return remainedBefore;
    }

    public void setRemainedBefore(double remainedBefore) {
        this.remainedBefore = remainedBefore;
    }

    public double getRemainedAfter() {
        return remainedAfter;
    }

    public void setRemainedAfter(double remainedAfter) {
        this.remainedAfter = remainedAfter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }
    public double getPayed() {
        return payed;
    }

    public void setPayed(double payed) {
        this.payed = payed;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
}
