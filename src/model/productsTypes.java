
package model;

public class productsTypes {
    
    private long id;
    private long productId;
    private String productTypeName;
    private double QuantityOfOne;
    private double AllQuantity;
    private double priceOfBuy;
    private double priceOfSale;
    private String productCategory;
    private String Note;
    private double AvailableQtyStore;
    private double priceOfMarket;
    private String storeName;
    private String supplierName;

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }
    
    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }
    
private double QuantityWarning;

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
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    
    public double getAllQuantity() {
        return AllQuantity;
    }

    public void setAllQuantity(double AllQuantity) {
        this.AllQuantity = AllQuantity;
    }

    
    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }
    public long getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductTypeName() {
        return productTypeName;
    }

    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName;
    }

    public double getQuantityOfOne() {
        return QuantityOfOne;
    }

    public void setQuantityOfOne(double QuantityOfOne) {
        this.QuantityOfOne = QuantityOfOne;
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
    
}
