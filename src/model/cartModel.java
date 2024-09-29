
package model;


public class cartModel {
    
    private int cartId;
    private int INID;
    private String productName;
    private String Barcode;
    private double QuantityNeeded;
    private double unit_price;
    private double Total_price;
    private String empname;
    private String date;

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getINID() {
        return INID;
    }

    public void setINID(int INID) {
        this.INID = INID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getBarcode() {
        return Barcode;
    }

    public void setBarcode(String Barcode) {
        this.Barcode = Barcode;
    }

    public double getQuantityNeeded() {
        return QuantityNeeded;
    }

    public void setQuantityNeeded(double QuantityNeeded) {
        this.QuantityNeeded = QuantityNeeded;
    }

    public double getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(double unit_price) {
        this.unit_price = unit_price;
    }

    public double getTotal_price() {
        return Total_price;
    }

    public void setTotal_price(double Total_price) {
        this.Total_price = Total_price;
    }

    public String getEmpname() {
        return empname;
    }

    public void setEmpname(String empname) {
        this.empname = empname;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
}
