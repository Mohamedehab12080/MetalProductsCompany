
package model;


public class suppliersModel {
    
    private int supId;
    private String supplierName;
    private String Address;
    private String phone;
    private double remained;

    public int getSupId() {
        return supId;
    }

    public void setSupId(int supId) {
        this.supId = supId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getRemained() {
        return remained;
    }

    public void setRemained(double remained) {
        this.remained = remained;
    }
    
}
