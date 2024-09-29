
package model;


public class purchaseCart {
    
    private int cartID;
    private int INID;
    private String productName;
    private String storeProductBarcode;
    private String companyProductBarcode;
    private double QuantityNeeded;
    private double priceOfBuy; 
    private double firstTotal;
    private String date;

    public int getCartID() {
        return cartID;
    }

    public void setCartID(int cartID) {
        this.cartID = cartID;
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

    public String getStoreProductBarcode() {
        return storeProductBarcode;
    }

    public void setStoreProductBarcode(String storeProductBarcode) {
        this.storeProductBarcode = storeProductBarcode;
    }

    public String getCompanyProductBarcode() {
        return companyProductBarcode;
    }

    public void setCompanyProductBarcode(String companyProductBarcode) {
        this.companyProductBarcode = companyProductBarcode;
    }

    public double getQuantityNeeded() {
        return QuantityNeeded;
    }

    public void setQuantityNeeded(double QuantityNeeded) {
        this.QuantityNeeded = QuantityNeeded;
    }

    public double getPriceOfBuy() {
        return priceOfBuy;
    }

    public void setPriceOfBuy(double priceOfBuy) {
        this.priceOfBuy = priceOfBuy;
    }

    public double getFirstTotal() {
        return firstTotal;
    }

    public void setFirstTotal(double firstTotal) {
        this.firstTotal = firstTotal;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
}
