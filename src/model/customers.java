
package model;

public class customers {
    
    private int customerID;
    private String phone;
    private String address;
    private String customerName;
    private double allRemained;
    private String customerType;
    // Remained Sum from invoices

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public double getAllRemained() {
        return allRemained;
    }

    public void setAllRemained(double AllReamiained) {
        this.allRemained = AllReamiained;
    }

    
}
