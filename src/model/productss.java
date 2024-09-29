
package model;

public class productss {
    
    private long productId;
    private String productName;
    private double priceOfBuy;
    private double priceOfSale;
    private double Taked;
    private double AvailableQty;
    private String productCategory;
    private String Note;
    private double AvailableQtyStore;
    private double priceOfMarket;
    private String storeName;
    private double QuantityWarning;
    private String supplierName;

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }
    
    public double getQuantityWarning() {
        return QuantityWarning;
    }

    public void setQuantityWarning(double QuantityWarning) {
        this.QuantityWarning = QuantityWarning;
    }
    
    public String getNote() {
        return Note;
    }

    public void setNote(String Note) {
        this.Note = Note;
    }

    public double getAvailableQtyStore() {
        return AvailableQtyStore;
    }

    public void setAvailableQtyStore(double AvailableQtyStore) {
        this.AvailableQtyStore = AvailableQtyStore;
    }

    public double getPriceOfMarket() {
        return priceOfMarket;
    }

    public void setPriceOfMarket(double priceOfMarket) {
        this.priceOfMarket = priceOfMarket;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }
    
    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }
    


    public double getTaked() {
        return Taked;
    }

    public void setTaked(double Taked) {
        this.Taked = Taked;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPriceOfBuy() {
        return priceOfBuy;
    }

    public void setPriceOfBuy(double priceOfBuy) {
        this.priceOfBuy = priceOfBuy;
    }

    public double getPriceOfSale() {
        return priceOfSale;
    }

    public void setPriceOfSale(double priceOfSale) {
        this.priceOfSale = priceOfSale;
    }

    public double getAvailableQty() {
        return AvailableQty;
    }

    public void setAvailableQty(double AvailableQty) {
        this.AvailableQty = AvailableQty;
    }
   
}
