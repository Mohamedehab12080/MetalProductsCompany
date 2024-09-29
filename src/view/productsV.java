package view;

import Database.dbOperations;
import TOOLS.TableCustom;
import controller.productsController;
import java.awt.Color;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.TimeZone;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import model.productsTypes;
import model.productss;

public class productsV extends javax.swing.JPanel {

    boolean checkVerify = false;
    SimpleDateFormat df;
    SimpleDateFormat df2;
    String MonthDate;
    String todayDate;
    SimpleDateFormat timeFormat;
    Timer t;
double remainedForOperation = 0;
    public productsV() {
        initComponents();
        getLiveTime();
        getAllCategoryName();
        LoadData();
        txtQuantityOfOneValue1.setEnabled(false);
        df = new SimpleDateFormat("yyyy-MM-dd", new Locale("en"));
        df2 = new SimpleDateFormat("yyyy-MM", new Locale("en"));
        MonthDate = df2.format(new Date());
        todayDate = df.format(new Date());
        txtTodayDate.setText(todayDate);
        txtQuantityOfOne.setEnabled(false);
        txtPriceOfBuyTypes.setEnabled(false);
        txtPriceOfSaleType.setEnabled(false);
        TOOLS.TableCustom.apply(jScrollPane2, TableCustom.TableType.MULTI_LINE);
        TOOLS.TableCustom.apply(jScrollPane3, TableCustom.TableType.MULTI_LINE);
        TOOLS.TableCustom.apply(jScrollPane1, TableCustom.TableType.MULTI_LINE);
        TOOLS.TableCustom.apply(jScrollPane4, TableCustom.TableType.MULTI_LINE);
        TOOLS.TableCustom.apply(jScrollPaneLowQuantities, TableCustom.TableType.MULTI_LINE);
        txtQuantityOfOneValue1.setEnabled(false);
        getAllSuppliersName();
        getStoreNames();
        getAllRecordsForFirstTable();
        loadSupplierOperationsData();
        loadCasherName();
        getAllproductsNamesCompo();
        getLowQuantities();
        
        btnAddToCart.setEnabled(false);
        btnPrint.setEnabled(false);
        txtPriceOfOne.setEnabled(false);
        getAllStoresName();
        getLastBarCode();
        txtProductId.setEnabled(false);
         getAllTotalProducts();
         getCategoryAndStoreNameForCompoSearchLowQuantity();
        btnArrivalApproval.setEnabled(false);
    }

    public productsV(String CasherName) {
        initComponents();
        getLiveTime();
        getAllCategoryName();
        getAllproductsNamesCompo();
        LoadData();
        getAllStoresName();
        txtQuantityOfOneValue1.setEnabled(false);
        df = new SimpleDateFormat("yyyy-MM-dd", new Locale("en"));
        df2 = new SimpleDateFormat("yyyy-MM", new Locale("en"));
        MonthDate = df2.format(new Date());
        todayDate = df.format(new Date());
        txtTodayDate.setText(todayDate);
        txtCasherName.setText(CasherName);
        txtQuantityOfOne.setEnabled(false);
        txtPriceOfBuyTypes.setEnabled(false);
        txtPriceOfSaleType.setEnabled(false);
        TOOLS.TableCustom.apply(jScrollPane2, TableCustom.TableType.MULTI_LINE);
        TOOLS.TableCustom.apply(jScrollPane3, TableCustom.TableType.MULTI_LINE);
        TOOLS.TableCustom.apply(jScrollPane1, TableCustom.TableType.MULTI_LINE);
        TOOLS.TableCustom.apply(jScrollPane4, TableCustom.TableType.MULTI_LINE);
        TOOLS.TableCustom.apply(jScrollPaneLowQuantities, TableCustom.TableType.MULTI_LINE);
        txtQuantityOfOneValue1.setEnabled(false);
        getAllSuppliersName();
        getStoreNames();
        getAllRecordsForFirstTable();
        loadSupplierOperationsData();
        loadCasherName();
        getLowQuantities();
         
        btnAddToCart.setEnabled(false);
        btnArrivalApproval.setEnabled(false);
        btnPrint.setEnabled(false);
        getLastBarCode();
        getAllTotalProducts();
        getCategoryAndStoreNameForCompoSearchLowQuantity();
        txtProductId.setEnabled(false);
//        txtPriceOfOne.setEnabled(false);
    }

    public void getLiveTime() {
        TimeZone.setDefault(TimeZone.getTimeZone("Egypt"));
        t = new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date dt = new Date();
                timeFormat = new SimpleDateFormat("hh:mm:ss a", new Locale("en"));
                String tt = timeFormat.format(dt);
                txtPurchaseDate.setText(todayDate + " " + tt);
            }
        });
        t.start();
    }

    public void getAllStoresName()
    {
        try {
            ResultSet rs=dbOperations.getData("select * from store");
            txtMarketOrStore.removeAllItems();
            txtMarketOrStore.addItem("محل");
             while(rs.next())
             {
                 txtMarketOrStore.addItem(rs.getString("storeName"));
             }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    public void getLowQuantities() {
        try {
            DefaultTableModel dtm = (DefaultTableModel) jTableLowQuantity.getModel();
            dtm.setRowCount(0);
            ResultSet rs = dbOperations.getData("select "
                    + "products.`productId` As products_productId,"
                    + "products.`productName` As products_productName,"
                    + "products.`category` As products_category,"
                    + "products.`storeName` As products_storeName,"
                    + "products.`AvailableQty` As products_AvailableQty,"
                    + "products.`AvailableQtyStore` As products_AvailableQtyStore,"
                    + "products.`QuantityWarning` As products_QuantityWarning,"
                    +"products.`Taked` As products_Taked"
                    + " from `products` products where (products.`AvailableQty` + products.`AvailableQtyStore`) <= products.`QuantityWarning`");
            while (rs.next()) {
                dtm.addRow(new Object[]{
                    rs.getLong("products_productId"),
                    rs.getString("products_productName"),
                    rs.getString("products_category"),
                    rs.getString("products_storeName"),
                    rs.getDouble("products_Taked"),
                    rs.getDouble("products_QuantityWarning"),
                    rs.getDouble("products_AvailableQtyStore"),
                    rs.getDouble("products_AvailableQty"),
                    rs.getDouble("products_AvailableQty") + rs.getDouble("products_AvailableQtyStore")
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void getCategoryAndStoreNameForCompoSearchLowQuantity() {
        try {
            ResultSet rs = dbOperations.getData("select distinct storeName,category from products");
             txtStoreNameSearchLowQuantity.removeAll();
              txtStoreNameSearchLowQuantity.addItem("مخازن");
             txtcategorySearchLowQuantity1.removeAll();
              txtcategorySearchLowQuantity1.addItem("اقسام");
            while (rs.next()) {
                txtStoreNameSearchLowQuantity.addItem(rs.getString("storeName"));
                txtcategorySearchLowQuantity1.addItem(rs.getString("Category"));
            }
        } catch (Exception e) {
        }
    }

    public void getLowQuantitiesWithproductId(String productID) {
        try {
            DefaultTableModel dtm = (DefaultTableModel) jTableLowQuantity.getModel();
            dtm.setRowCount(0);
            ResultSet rs = dbOperations.getData("select "
                    + "cart.`INID` As cart_INID,"
                    + "cart.`productName` As cart_productName,"
                    + "sum(cart.`QuantityNeeded`) As cart_QuantityNeeded,"
                    + "products.`productId` As products_productId,"
                    + "products.`productName` As products_productName,"
                    + "products.`category` As products_category,"
                    + "products.`storeName` As products_storeName,"
                    + "products.`AvailableQty` As products_AvailableQty,"
                    + "products.`AvailableQtyStore` As products_AvailableQtyStore,"
                    + "products.`QuantityWarning` As products_QuantityWarning"
                    + " from `products` products inner Join `cart` cart on cart.`productName` =products.`productName`  where (products.`AvailableQty`+products.`AvailableQtyStore`)<=products.`QuantityWarning` and products.`productId` = " + productID);
            while (rs.next()) {
                dtm.addRow(new Object[]{
                    rs.getLong("products_productId"),
                    rs.getString("products_productName"),
                    rs.getString("products_category"),
                    rs.getString("products_storeName"),
                    rs.getDouble("cart_QuantityNeeded"),
                    rs.getDouble("QuantityWarning"),
                    rs.getDouble("products_AvailableQty"),
                    rs.getDouble("products_AvailableQtyStore"),
                    rs.getDouble("products_AvailableQty") + rs.getDouble("products_AvailableQtyStore")
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void getLowQuantitiesWithCategory(String category) {
        try {
            DefaultTableModel dtm = (DefaultTableModel) jTableLowQuantity.getModel();
            dtm.setRowCount(0);
            ResultSet rs = dbOperations.getData("select "
                    + "products.`productId` As products_productId,"
                    + "products.`productName` As products_productName,"
                    + "products.`category` As products_category,"
                    + "products.`storeName` As products_storeName,"
                    + "products.`AvailableQty` As products_AvailableQty,"
                    + "products.`AvailableQtyStore` As products_AvailableQtyStore,"
                    + "products.`QuantityWarning` As products_QuantityWarning"
                    + " from `products` products where (products.`AvailableQty`+products.`AvailableQtyStore`)<=products.`QuantityWarning` and products.`category`='" + category + "'");
            while (rs.next()) {
                dtm.addRow(new Object[]{
                    rs.getLong("products_productId"),
                    rs.getString("products_productName"),
                    rs.getString("products_category"),
                    rs.getString("products_storeName"),
                    rs.getDouble("products_QuantityWarning"),
                    rs.getDouble("products_AvailableQty"),
                    rs.getDouble("products_AvailableQtyStore"),
                    rs.getDouble("products_AvailableQty") + rs.getDouble("products_AvailableQtyStore")
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void getLowQuantitiesWithStoreName(String storeName) {
        try {
            DefaultTableModel dtm = (DefaultTableModel) jTableLowQuantity.getModel();
            dtm.setRowCount(0);
            ResultSet rs = dbOperations.getData("select "
                    + "products.`productId` As products_productId,"
                    + "products.`productName` As products_productName,"
                    + "products.`category` As products_category,"
                    + "products.`storeName` As products_storeName,"
                    + "products.`AvailableQty` As products_AvailableQty,"
                    + "products.`AvailableQtyStore` As products_AvailableQtyStore,"
                    + "products.`QuantityWarning` As products_QuantityWarning"
                    + " from `products` products where (products.`AvailableQty`+products.`AvailableQtyStore`)<=products.`QuantityWarning` and products.`storeName`='" + storeName + "'");
            while (rs.next()) {
                dtm.addRow(new Object[]{
                   rs.getLong("products_productId"),
                    rs.getString("products_productName"),
                    rs.getString("products_category"),
                    rs.getString("products_storeName"),
                    rs.getDouble("products_QuantityWarning"),
                    rs.getDouble("products_AvailableQty"),
                    rs.getDouble("products_AvailableQtyStore"),
                    rs.getDouble("products_AvailableQty") + rs.getDouble("products_AvailableQtyStore")
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void loadCasherName() {
        try {
            ResultSet rs = dbOperations.getData("select name from Login");
            while (rs.next()) {
                txtReceiverNameForCart.addItem(rs.getString("name"));
                txtReceiverNameSaerch.addItem(rs.getString("name"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void loadSupplierOperationsData() {
        double totalPrice=0;
        double totalPayed=0;
        double totalRemained=0;
        try {
            DefaultTableModel dtm = (DefaultTableModel) jTableSalesSuppliers.getModel();
            dtm.setRowCount(0);
            ResultSet rs = dbOperations.getData("select * from salesSuppliers where DayDate like'" + MonthDate + "___________%'");
            while (rs.next()) {
                dtm.addRow(new Object[]{
                    rs.getLong("INID"),
                    rs.getString("supplierName"),
                    rs.getDouble("Total_Quantity"),
                    rs.getDouble("AllTotal"),
                    rs.getDouble("payed"),
                    rs.getDouble("Remained"),
                    rs.getString("DayDate"),
                    rs.getString("ArrivalDate"),
                    rs.getString("ReceiverName"),
                    rs.getString("Status"),
                    rs.getString("casherName"),
                    rs.getString("Note")
                });
            }
            for (int i = 0; i <dtm.getRowCount(); i++) {
                if(dtm.getValueAt(i,9).toString().contains("تم الاستلام"))
                {
                    totalPayed+=Double.valueOf(dtm.getValueAt(i,4).toString());
                totalPrice+=Double.valueOf(dtm.getValueAt(i,3).toString());
                totalRemained+=Double.valueOf(dtm.getValueAt(i,5).toString());
                }
            }
            txtDayPayedTotal.setText(""+totalPayed);
            txtDayPriceTotal.setText(""+totalPrice);
            txtDayRemained.setText(""+totalRemained);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void LoadData() {
        try {
            ResultSet rs = dbOperations.getData("select * from extra where exid=2");
            if (rs.next()) {
                txtINIDOfGetProductsInvoice.setText(rs.getString("val"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        int i = Integer.valueOf(txtINIDOfGetProductsInvoice.getText());
        i++;
        txtINIDOfGetProductsInvoice.setText(String.valueOf(i));
    }

    public void getStoreNames() {
        try {
            ResultSet rs = dbOperations.getData("select * from store");
            txtStoreName.removeAllItems();
            txtStoreName.addItem("مخازن");
            txtStoreSearchName.removeAllItems();
            txtStoreSearchName.addItem("مخازن");
            while (rs.next()) {
                txtStoreName.addItem(rs.getString("storeName"));
                txtStoreSearchName.addItem(rs.getString("storeName"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void getproductsTypesForCompo(String barcode) {
        try {
            ArrayList<productsTypes> list = productsController.getAllTypesForCompoWithBarCode(barcode);
            Iterator<productsTypes> itr = list.iterator();
            txtproductTypeNameSuppliers.removeAllItems();
            txtproductTypeNameSuppliers.addItem(" ");
            while (itr.hasNext()) {
                productsTypes prod = itr.next();
                txtproductTypeNameSuppliers.addItem(prod.getProductTypeName().substring(prod.getProductTypeName().indexOf("_") + 1, prod.getProductTypeName().length()));
            }
            ResultSet rs = dbOperations.getData("select productName from products where productId=" + barcode);
            if (rs.next()) {
                txtProductNameLable.setText(rs.getString("productName"));
            } else {
                txtProductNameLable.setText("");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void getAllSuppliersName() {
        try {
            ResultSet rs = dbOperations.getData("select * from suppliers");
            while (rs.next()) {
                txtSupplierName.addItem(rs.getString("supplierName"));
                txtSupplierNameSearch.addItem(rs.getString("supplierName"));
               txtSupplierNamePercentage.addItem(rs.getString("supplierName"));
               txtSupplierNameAddProduct.addItem(rs.getString("supplierName"));
            }

        } catch (Exception e) {
        }
    }

    public void getAllRecordsForFirstTable() {

        try {
            DefaultTableModel dtm = (DefaultTableModel) jTableProductAvailables.getModel();
            dtm.setRowCount(0);
            ArrayList<productss> list = productsController.getAllRecordsForProducts();
            Iterator<productss> itr = list.iterator();
            while (itr.hasNext()) {
                productss prod = itr.next();
                dtm.addRow(new Object[]{
                    prod.getProductId(),
                    prod.getProductName(),
                    prod.getProductCategory(),
                    prod.getStoreName(),
                    prod.getPriceOfBuy(),
                    prod.getPriceOfSale(),
                    prod.getPriceOfMarket(),
                    prod.getTaked(),
                    prod.getAvailableQty(),
                    prod.getAvailableQtyStore(),
                    prod.getNote(),
                    prod.getSupplierName()
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void Clear() {
        txtPriceOfBuy.setText("");
        txtPriceOfSale.setText("");
        txtTypeVariable.setText("__");
        txtPriceOfBuyTypes.setText("");
        txtPriceOfSaleType.setText("");
        txtPriceOfSaleTypeVarialble.setText("__");
        txtPriceOfBuyTypesVariable.setText("__");
        
    }

    public void getAllProductsNamesWithProductCategory(String productCategory) {

    }

    public void getAllRecordsFromInnerJoin(long productIds) {
        try {
            int idForProduct = 0;
            DefaultTableModel dtm = (DefaultTableModel) jTableProductAvailables.getModel();
            dtm.setRowCount(0);

            DefaultTableModel dtm2 = (DefaultTableModel) jTableProductAvailables1.getModel();
            dtm2.setRowCount(0);

            ResultSet rs2 = dbOperations.getData("select * from productsTypes where productId=" + productIds);
            while (rs2.next()) {
                dtm2.addRow(new Object[]{
                    rs2.getLong("productId"),
                    rs2.getString("productTypeName"),
                    rs2.getString("category"),
                    rs2.getDouble("priceOfBuy"),
                    rs2.getDouble("priceOfSale"),
                    rs2.getDouble("priceOfMarket"),
                    rs2.getDouble("AvailableQtyStore"),
                    rs2.getDouble("AllQuantity"),
                    rs2.getDouble("QuantityOfOne"),
                    rs2.getString("storeName"),
                    rs2.getString("Note"),
                    rs2.getString("supplierName")
                });

            }
            ResultSet rs1 = dbOperations.getData("select * from products where productId=" + productIds);
            while (rs1.next()) {
                dtm.addRow(new Object[]{
                    rs1.getLong("productId"),
                    rs1.getString("productName"),
                    rs1.getString("category"),
                    rs1.getString("storeName"),
                    rs1.getDouble("priceOfBuy"),
                    rs1.getDouble("priceOfSale"),
                    rs1.getDouble("priceOfMarket"),
                    rs1.getDouble("Taked"),
                    rs1.getDouble("AvailableQty"),
                    rs1.getDouble("AvailableQtyStore"),
                    rs1.getString("Note"),
                    rs1.getString("supplierName")
                });
                txtAvailableForTableVariable.setText(rs1.getString("productName"));
                txtAvailableForTable.setText(String.valueOf(rs1.getDouble("AvailableQty") + rs1.getDouble("AvailableQtyStore")));
                txtPriceOfAllQuantitySingle.setText(String.valueOf(rs1.getDouble("priceOfBuy") * (rs1.getDouble("AvailableQty") + rs1.getDouble("AvailableQtyStore"))));
                txtGainedForAllQuantitySingle.setText(String.valueOf(((rs1.getDouble("priceOfSale") - rs1.getDouble("priceOfBuy")) * 100) / 100) + " %");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void getAllercordsWithProductName(String productName) {
        try {
            int idForProduct = 0;
            DefaultTableModel dtm = (DefaultTableModel) jTableProductAvailables.getModel();
            dtm.setRowCount(0);

            DefaultTableModel dtm2 = (DefaultTableModel) jTableProductAvailables1.getModel();
            dtm2.setRowCount(0);

            ResultSet rs2 = dbOperations.getData("select * from productsTypes where productTypeName like'%" + productName+"%'");
            while (rs2.next()) {
                dtm2.addRow(new Object[]{
                    rs2.getLong("productId"),
                    rs2.getString("productTypeName"),
                    rs2.getString("category"),
                    rs2.getDouble("priceOfBuy"),
                    rs2.getDouble("priceOfSale"),
                    rs2.getDouble("priceOfMarket"),
                    rs2.getDouble("AvailableQtyStore"),
                    rs2.getDouble("AllQuantity"),
                    rs2.getDouble("QuantityOfOne"),
                    rs2.getString("storeName"),
                    rs2.getString("Note"),
                    rs2.getString("supplierName")
                });

            }
            ResultSet rs1 = dbOperations.getData("select * from products where productName like'%" +productName+"%'");
            while (rs1.next()) {
                dtm.addRow(new Object[]{
                    rs1.getLong("productId"),
                    rs1.getString("productName"),
                    rs1.getString("category"),
                    rs1.getString("storeName"),
                    rs1.getDouble("priceOfBuy"),
                    rs1.getDouble("priceOfSale"),
                    rs1.getDouble("priceOfMarket"),
                    rs1.getDouble("Taked"),
                    rs1.getDouble("AvailableQty"),
                    rs1.getDouble("AvailableQtyStore"),
                    rs1.getString("Note"),
                    rs1.getString("supplierName")
                });
                txtAvailableForTableVariable.setText(rs1.getString("productName"));
                txtAvailableForTable.setText(String.valueOf(rs1.getDouble("AvailableQty") + rs1.getDouble("AvailableQtyStore")));
                txtPriceOfAllQuantitySingle.setText(String.valueOf(rs1.getDouble("priceOfBuy") * (rs1.getDouble("AvailableQty") + rs1.getDouble("AvailableQtyStore"))));
                txtGainedForAllQuantitySingle.setText(String.valueOf(((rs1.getDouble("priceOfSale") - rs1.getDouble("priceOfBuy")) * 100) / 100) + " %");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    public void getAllCategoryName() {
        try {
            ResultSet rs = dbOperations.getData("select categoryName from category");
            txtSearchCategory.removeAllItems();
            txtSearchCategory.addItem("اقسام");
            txtCategory.removeAllItems();
            txtCategory.addItem("اقسام"); 
            txtCategoryPercentage.removeAllItems();
            txtCategoryPercentage.addItem("اقسام");
            while (rs.next()) {
                txtSearchCategory.addItem(rs.getString("categoryName"));
                txtCategory.addItem(rs.getString("categoryName"));
                txtCategoryPercentage.addItem(rs.getString("categoryName"));
                
            }
        } catch (Exception e) {

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableProductAvailables = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        txtGained1 = new javax.swing.JTextField();
        txtSearchMonthly = new javax.swing.JComboBox<>();
        jLabel25 = new javax.swing.JLabel();
        txtSeachYear = new javax.swing.JComboBox<>();
        jLabel26 = new javax.swing.JLabel();
        txtSeachDaily = new com.toedter.calendar.JDateChooser();
        btnReports = new javax.swing.JButton();
        txtBarcode = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableProductAvailables1 = new javax.swing.JTable();
        txtSearchCategory = new javax.swing.JComboBox<>();
        jPanel8 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtCategoryNameAdd = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        txtStoreNameAdd = new javax.swing.JTextField();
        btnAddStore = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        txtStoreSearchName = new javax.swing.JComboBox<>();
        jPanel11 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        txtAvailableForTableVariable1 = new javax.swing.JLabel();
        txtAvailableForTable1 = new javax.swing.JLabel();
        txtAvailableForTableVariable2 = new javax.swing.JLabel();
        txtAvailableForTableVariable8 = new javax.swing.JLabel();
        txtPriceOfAllQuantityDouble = new javax.swing.JLabel();
        txtGainedForAllQuantityTypes = new javax.swing.JLabel();
        txtAvailableForTableVariable11 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        txtAvailableForTableVariable = new javax.swing.JLabel();
        txtAvailableForTable = new javax.swing.JLabel();
        txtAvailableForTableVariable3 = new javax.swing.JLabel();
        txtAvailableForTableVariable4 = new javax.swing.JLabel();
        txtPriceOfAllQuantitySingle = new javax.swing.JLabel();
        txtGainedForAllQuantitySingle = new javax.swing.JLabel();
        txtAvailableForTableVariable6 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        txtCheckBoxMarket = new javax.swing.JCheckBox();
        txtCheckBoxStore = new javax.swing.JCheckBox();
        jPanel13 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        txtCategoryPercentage = new javax.swing.JComboBox<>();
        txtSupplierNamePercentage = new javax.swing.JComboBox<>();
        jLabel46 = new javax.swing.JLabel();
        txtPercentageValue = new javax.swing.JTextField();
        jLabel57 = new javax.swing.JLabel();
        btnAddPercentage = new javax.swing.JButton();
        btnReports1 = new javax.swing.JButton();
        txtproductNameSearch = new javax.swing.JTextField();
        jLabel61 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        jLabel64 = new javax.swing.JLabel();
        txtAllTotal = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        txtAllTotalSold = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        btnDelete = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        txtPriceOfBuy = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtProductName = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtPriceOfSale = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        txtQuantityForSale = new javax.swing.JTextField();
        txtQuantityOfOne = new javax.swing.JTextField();
        txtTypeVariable = new javax.swing.JLabel();
        txtPriceOfSaleTypeVarialble = new javax.swing.JLabel();
        txtPriceOfSaleType = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtCategory = new javax.swing.JComboBox<>();
        txtPriceOfBuyTypesVariable = new javax.swing.JLabel();
        txtPriceOfBuyTypes = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        txtProductTypeName = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtAvailableComeFromTable = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txtTakedComeFromTable = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        txtStoreName = new javax.swing.JComboBox<>();
        jLabel28 = new javax.swing.JLabel();
        txtQuantityStore = new javax.swing.JTextField();
        txtPriceOfSaleTypeVarialble1 = new javax.swing.JLabel();
        txtMarketPrice = new javax.swing.JTextField();
        txtPriceOfSaleTypeVarialble2 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtNotes = new javax.swing.JTextArea();
        txtQuantityWarning = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        txtPriceOfMarketVar = new javax.swing.JLabel();
        txtMarketPriceTypes = new javax.swing.JTextField();
        txtSupplierNameAddProduct = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        txtProductId = new javax.swing.JTextField();
        txtProductCodeDefault = new javax.swing.JLabel();
        btnBarCodeEnable = new javax.swing.JButton();
        txtProductIdTypes = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        txtAvailableLabel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtBarcodeProduct = new javax.swing.JTextField();
        txtproductTypeNameSuppliers = new javax.swing.JComboBox<>();
        txtProductNameLable = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtQuantityOfOneVariable = new javax.swing.JLabel();
        txtQuantitySingle = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtPayedValue = new javax.swing.JTextField();
        txtPriceOfOne = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtRemained = new javax.swing.JLabel();
        txtTotalQuantity = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txtAvailableFromProductOnSupplier = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txtTakedFromProductsOnSupplier = new javax.swing.JLabel();
        btnPrint = new javax.swing.JButton();
        btnAddToCart = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableAddForCaty = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        txtQuantityOfOneVariable1 = new javax.swing.JLabel();
        txtQuantityOfOneValue1 = new javax.swing.JTextField();
        txtQuantityOfOneVariable2 = new javax.swing.JLabel();
        txtFirstPriceSuppliers = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        txtCasherName = new javax.swing.JLabel();
        txtPriceOfAll1 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        txtsupplierNotes = new javax.swing.JTextArea();
        jLabel51 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        txtPurchaseNumberSearch = new javax.swing.JTextField();
        jPanel19 = new javax.swing.JPanel();
        txtINIDOfGetProductsInvoice = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        txtSupplierPhone = new javax.swing.JTextField();
        txtSupplierName = new javax.swing.JComboBox<>();
        jLabel22 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        txtReceiverNameForCart = new javax.swing.JComboBox<>();
        txtBarcodeProductCompany = new javax.swing.JTextField();
        jLabel70 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        txtHandDateArrival = new com.toedter.calendar.JDateChooser();
        txtPurchaseDate = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        btnArrivalApproval = new javax.swing.JButton();
        jLabel73 = new javax.swing.JLabel();
        txtCompanyAddress = new javax.swing.JTextField();
        txtSearchPurchaseDate = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        txtStorePhoneNumber = new javax.swing.JTextField();
        jLabel75 = new javax.swing.JLabel();
        txtStatusSearchPurchase = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTableSalesSuppliers = new javax.swing.JTable();
        txtTodayDate = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        txtINID = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        txtSupplierNumberSearch = new javax.swing.JTextField();
        txtSupplierNameSearch = new javax.swing.JComboBox<>();
        txtHandDateSaerchSuppliers = new com.toedter.calendar.JDateChooser();
        jButton1 = new javax.swing.JButton();
        txtMonthSearchSuppliers = new javax.swing.JComboBox<>();
        jLabel39 = new javax.swing.JLabel();
        txtYearSearchSuppliers = new javax.swing.JComboBox<>();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        txtReceiverNameSaerch = new javax.swing.JComboBox<>();
        jLabel42 = new javax.swing.JLabel();
        txtDayPayedTotal = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        txtMonthPriceTotal = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jLabel47 = new javax.swing.JLabel();
        txtDayRemained = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        txtDayPriceTotal = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        txtMonthPayedTotal = new javax.swing.JLabel();
        txtTotalRemainedMonth = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        txtSatusSearch = new javax.swing.JComboBox<>();
        jLabel56 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jSeparator3 = new javax.swing.JSeparator();
        jScrollPaneLowQuantities = new javax.swing.JScrollPane();
        jTableLowQuantity = new javax.swing.JTable();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        txtStoreNameSearchLowQuantity = new javax.swing.JComboBox<>();
        jLabel54 = new javax.swing.JLabel();
        txtcategorySearchLowQuantity1 = new javax.swing.JComboBox<>();
        jLabel55 = new javax.swing.JLabel();
        txtBarCodeSearchLowQuantity = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jLabel58 = new javax.swing.JLabel();
        txtBarcodeAdd = new javax.swing.JTextField();
        jLabel59 = new javax.swing.JLabel();
        txtMarketOrStore = new javax.swing.JComboBox<>();
        jLabel60 = new javax.swing.JLabel();
        txtQuantityAdded = new javax.swing.JTextField();
        btnAddQuantity = new javax.swing.JButton();
        txtProductsSearchs = new javax.swing.JTextField();
        jLabel62 = new javax.swing.JLabel();
        txtproductNameAdd = new javax.swing.JComboBox<>();
        txtTypeNameCompo = new javax.swing.JComboBox<>();
        jLabel63 = new javax.swing.JLabel();
        txtAvailableQtyMarketAdd = new javax.swing.JLabel();
        txtAvailableQtyStoreAdd = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();

        jTabbedPane1.setBackground(new java.awt.Color(0, 153, 204));
        jTabbedPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTabbedPane1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        jPanel2.setBackground(new java.awt.Color(0, 153, 153));

        jPanel4.setBackground(new java.awt.Color(51, 51, 51));

        jScrollPane2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jScrollPane2KeyReleased(evt);
            }
        });

        jTableProductAvailables.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jTableProductAvailables.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "باركود", "اسم المنتج", "قسم", "مخزن", "سعر الشراء", "سعر البيع", "سعر التجاري", "المسحوب", "كمية المحل", "كمية المخزن", "ملاحظة", "اسم المورد"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableProductAvailables.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableProductAvailablesMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTableProductAvailables);

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("باركود:");

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("شهر:");

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("اضافة الي التقرير استعلام مكسب ال: ");

        txtGained1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtGained1.setText("1000");
        txtGained1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtGained1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtGained1FocusLost(evt);
            }
        });
        txtGained1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGained1ActionPerformed(evt);
            }
        });

        txtSearchMonthly.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        txtSearchMonthly.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "  ", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        txtSearchMonthly.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchMonthlyActionPerformed(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("سنة:");

        txtSeachYear.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        txtSeachYear.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030" }));

        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("تاريخ:");

        txtSeachDaily.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtSeachDailyMouseClicked(evt);
            }
        });
        txtSeachDaily.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtSeachDailyPropertyChange(evt);
            }
        });

        btnReports.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnReports.setForeground(new java.awt.Color(0, 0, 0));
        btnReports.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/place order.png"))); // NOI18N
        btnReports.setText("تقرير");
        btnReports.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnReports.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReportsActionPerformed(evt);
            }
        });

        txtBarcode.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        txtBarcode.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtBarcodeFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtBarcodeFocusLost(evt);
            }
        });
        txtBarcode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBarcodeActionPerformed(evt);
            }
        });
        txtBarcode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBarcodeKeyReleased(evt);
            }
        });

        jTableProductAvailables1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jTableProductAvailables1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "باركود", "اسم المنتج", "قسم", "سعر الشراء", "سعر البيع", "سعر تجاري", "كمية المخزن", "كمية المحل", "تحتوي العبوة علي", "اسم المخزن", "ملاحظة", "اسم المورد"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableProductAvailables1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableProductAvailables1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTableProductAvailables1MouseEntered(evt);
            }
        });
        jTableProductAvailables1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTableProductAvailables1KeyReleased(evt);
            }
        });
        jScrollPane3.setViewportView(jTableProductAvailables1);

        txtSearchCategory.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtSearchCategory.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "قسم", " " }));
        txtSearchCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchCategoryActionPerformed(evt);
            }
        });

        jPanel8.setBackground(new java.awt.Color(0, 153, 204));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("اسم القسم:");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("اضافة قسم");

        txtCategoryNameAdd.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        txtCategoryNameAdd.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCategoryNameAddFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCategoryNameAddFocusLost(evt);
            }
        });
        txtCategoryNameAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCategoryNameAddActionPerformed(evt);
            }
        });
        txtCategoryNameAdd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCategoryNameAddKeyReleased(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(0, 0, 0));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/add.png"))); // NOI18N
        jButton2.setText("اضافة");
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jPanel9.setBackground(new java.awt.Color(0, 153, 204));

        jLabel31.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(0, 0, 0));
        jLabel31.setText("اسم المخزن:");

        jLabel32.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(0, 0, 0));
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel32.setText("اضافة مخزن");

        txtStoreNameAdd.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        txtStoreNameAdd.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtStoreNameAddFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtStoreNameAddFocusLost(evt);
            }
        });
        txtStoreNameAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtStoreNameAddActionPerformed(evt);
            }
        });
        txtStoreNameAdd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtStoreNameAddKeyReleased(evt);
            }
        });

        btnAddStore.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnAddStore.setForeground(new java.awt.Color(0, 0, 0));
        btnAddStore.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/add.png"))); // NOI18N
        btnAddStore.setText("اضافة");
        btnAddStore.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAddStore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddStoreActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtStoreNameAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(btnAddStore))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel32)
                .addGap(17, 17, 17)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(txtStoreNameAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAddStore, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(txtCategoryNameAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(44, 44, 44)
                                .addComponent(jButton2)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(78, 78, 78))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtCategoryNameAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        txtStoreSearchName.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtStoreSearchName.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "مخزن" }));
        txtStoreSearchName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtStoreSearchNameActionPerformed(evt);
            }
        });

        txtAvailableForTableVariable1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtAvailableForTableVariable1.setForeground(new java.awt.Color(0, 0, 0));
        txtAvailableForTableVariable1.setText(" ");

        txtAvailableForTable1.setBackground(new java.awt.Color(0, 102, 102));
        txtAvailableForTable1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtAvailableForTable1.setForeground(new java.awt.Color(255, 255, 255));
        txtAvailableForTable1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtAvailableForTable1.setText("0.0");
        txtAvailableForTable1.setToolTipText("");
        txtAvailableForTable1.setOpaque(true);

        txtAvailableForTableVariable2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtAvailableForTableVariable2.setForeground(new java.awt.Color(0, 0, 0));
        txtAvailableForTableVariable2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtAvailableForTableVariable2.setText("متوفر");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtAvailableForTableVariable1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(txtAvailableForTable1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtAvailableForTableVariable2, javax.swing.GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtAvailableForTableVariable1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtAvailableForTable1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtAvailableForTableVariable2, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                .addContainerGap())
        );

        txtAvailableForTableVariable8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtAvailableForTableVariable8.setForeground(new java.awt.Color(0, 0, 0));
        txtAvailableForTableVariable8.setText("تكلفة:");

        txtPriceOfAllQuantityDouble.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtPriceOfAllQuantityDouble.setForeground(new java.awt.Color(0, 0, 0));
        txtPriceOfAllQuantityDouble.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtPriceOfAllQuantityDouble.setText("0.0");

        txtGainedForAllQuantityTypes.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtGainedForAllQuantityTypes.setForeground(new java.awt.Color(0, 0, 0));
        txtGainedForAllQuantityTypes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtGainedForAllQuantityTypes.setText("0.0");

        txtAvailableForTableVariable11.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtAvailableForTableVariable11.setForeground(new java.awt.Color(0, 0, 0));
        txtAvailableForTableVariable11.setText("مكسب:");

        txtAvailableForTableVariable.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtAvailableForTableVariable.setForeground(new java.awt.Color(0, 0, 0));
        txtAvailableForTableVariable.setText(" ");

        txtAvailableForTable.setBackground(new java.awt.Color(0, 102, 102));
        txtAvailableForTable.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtAvailableForTable.setForeground(new java.awt.Color(255, 255, 255));
        txtAvailableForTable.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtAvailableForTable.setText("0.0");
        txtAvailableForTable.setToolTipText("");
        txtAvailableForTable.setOpaque(true);

        txtAvailableForTableVariable3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtAvailableForTableVariable3.setForeground(new java.awt.Color(0, 0, 0));
        txtAvailableForTableVariable3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtAvailableForTableVariable3.setText("متوفر");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtAvailableForTableVariable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(txtAvailableForTable, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtAvailableForTableVariable3, javax.swing.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtAvailableForTableVariable, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtAvailableForTable, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtAvailableForTableVariable3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(14, 14, 14))
        );

        txtAvailableForTableVariable4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtAvailableForTableVariable4.setForeground(new java.awt.Color(0, 0, 0));
        txtAvailableForTableVariable4.setText("تكلفة:");

        txtPriceOfAllQuantitySingle.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtPriceOfAllQuantitySingle.setForeground(new java.awt.Color(0, 0, 0));
        txtPriceOfAllQuantitySingle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtPriceOfAllQuantitySingle.setText("0.0");

        txtGainedForAllQuantitySingle.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtGainedForAllQuantitySingle.setForeground(new java.awt.Color(0, 0, 0));
        txtGainedForAllQuantitySingle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtGainedForAllQuantitySingle.setText("0.0");

        txtAvailableForTableVariable6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtAvailableForTableVariable6.setForeground(new java.awt.Color(0, 0, 0));
        txtAvailableForTableVariable6.setText("مكسب:");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(91, 91, 91)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtPriceOfAllQuantitySingle, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                    .addComponent(txtGainedForAllQuantitySingle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtAvailableForTableVariable4, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAvailableForTableVariable6, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, 996, Short.MAX_VALUE)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel11Layout.createSequentialGroup()
                                .addGap(68, 68, 68)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel11Layout.createSequentialGroup()
                                        .addComponent(txtGainedForAllQuantityTypes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtAvailableForTableVariable11, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                                        .addComponent(txtPriceOfAllQuantityDouble, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtAvailableForTableVariable8, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel11Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(678, Short.MAX_VALUE)))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addGap(168, 168, 168)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAvailableForTableVariable4)
                    .addComponent(txtPriceOfAllQuantitySingle))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAvailableForTableVariable6)
                    .addComponent(txtGainedForAllQuantitySingle))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 206, Short.MAX_VALUE)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPriceOfAllQuantityDouble)
                    .addComponent(txtAvailableForTableVariable8))
                .addGap(6, 6, 6)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtGainedForAllQuantityTypes)
                    .addComponent(txtAvailableForTableVariable11))
                .addGap(13, 13, 13))
            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel11Layout.createSequentialGroup()
                    .addGap(13, 13, 13)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(519, Short.MAX_VALUE)))
        );

        txtCheckBoxMarket.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtCheckBoxMarket.setForeground(new java.awt.Color(0, 0, 0));
        txtCheckBoxMarket.setText("محل");
        txtCheckBoxMarket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCheckBoxMarketActionPerformed(evt);
            }
        });

        txtCheckBoxStore.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtCheckBoxStore.setForeground(new java.awt.Color(0, 0, 0));
        txtCheckBoxStore.setText("مخزن");
        txtCheckBoxStore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCheckBoxStoreActionPerformed(evt);
            }
        });

        jPanel13.setBackground(new java.awt.Color(0, 153, 204));
        jPanel13.setForeground(new java.awt.Color(255, 255, 255));

        jLabel33.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(0, 0, 0));
        jLabel33.setText("اسم المورد:");

        jLabel45.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(0, 0, 0));
        jLabel45.setText("اسم القسم:");

        txtCategoryPercentage.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtCategoryPercentage.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "قسم", " " }));
        txtCategoryPercentage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCategoryPercentageActionPerformed(evt);
            }
        });

        txtSupplierNamePercentage.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtSupplierNamePercentage.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", " " }));
        txtSupplierNamePercentage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSupplierNamePercentageActionPerformed(evt);
            }
        });

        jLabel46.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(0, 0, 0));
        jLabel46.setText("نسبة الزيادة:");

        txtPercentageValue.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtPercentageValue.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPercentageValue.setText("0");
        txtPercentageValue.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtPercentageValueFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtPercentageValueFocusLost(evt);
            }
        });
        txtPercentageValue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPercentageValueActionPerformed(evt);
            }
        });
        txtPercentageValue.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPercentageValueKeyReleased(evt);
            }
        });

        jLabel57.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(0, 0, 0));
        jLabel57.setText("%");

        btnAddPercentage.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnAddPercentage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/add.png"))); // NOI18N
        btnAddPercentage.setText("اضافة نسبة الزيادة");
        btnAddPercentage.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAddPercentage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddPercentageActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap(132, Short.MAX_VALUE)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSupplierNamePercentage, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnAddPercentage, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addComponent(jLabel57)
                                .addGap(4, 4, 4)
                                .addComponent(txtPercentageValue, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtCategoryPercentage, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(3, 3, 3)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel46)
                            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel33)
                                .addComponent(jLabel45)))))
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(txtSupplierNamePercentage))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel45)
                    .addComponent(txtCategoryPercentage))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel57, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel46)
                        .addComponent(txtPercentageValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(btnAddPercentage, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        btnReports1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnReports1.setForeground(new java.awt.Color(0, 0, 0));
        btnReports1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/reports.png"))); // NOI18N
        btnReports1.setText("تقرير مالية");
        btnReports1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnReports1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReports1ActionPerformed(evt);
            }
        });

        txtproductNameSearch.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        txtproductNameSearch.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtproductNameSearchFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtproductNameSearchFocusLost(evt);
            }
        });
        txtproductNameSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtproductNameSearchActionPerformed(evt);
            }
        });
        txtproductNameSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtproductNameSearchKeyReleased(evt);
            }
        });

        jLabel61.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel61.setForeground(new java.awt.Color(255, 255, 255));
        jLabel61.setText("اسم:");

        jLabel64.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel64.setForeground(new java.awt.Color(0, 0, 0));
        jLabel64.setText("توتال الكلي تكلفة");

        txtAllTotal.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtAllTotal.setForeground(new java.awt.Color(0, 0, 0));
        txtAllTotal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtAllTotal.setText("0");

        jLabel65.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel65.setForeground(new java.awt.Color(0, 0, 0));
        jLabel65.setText("توتال الكلي بيع");

        txtAllTotalSold.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtAllTotalSold.setForeground(new java.awt.Color(0, 0, 0));
        txtAllTotalSold.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtAllTotalSold.setText("0");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtAllTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(jLabel64)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(txtAllTotalSold, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(jLabel65)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel64)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtAllTotal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel65)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtAllTotalSold)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1157, Short.MAX_VALUE)
                            .addComponent(jScrollPane3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(txtSearchCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                                .addGap(13, 13, 13)
                                                .addComponent(txtBarcode, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(3, 3, 3))
                                            .addGroup(jPanel4Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(txtproductNameSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(4, 4, 4)))
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(txtSearchMonthly, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(11, 11, 11)
                                        .addComponent(jLabel10)
                                        .addGap(12, 12, 12)
                                        .addComponent(txtSeachYear, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(2, 2, 2)
                                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtSeachDaily, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(6, 6, 6)
                                        .addComponent(jLabel26))
                                    .addComponent(txtStoreSearchName, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(12, 12, 12)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(txtCheckBoxMarket)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtCheckBoxStore))
                                    .addComponent(btnReports, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 605, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGap(24, 24, 24)
                                        .addComponent(btnReports1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))))
                        .addGap(11, 11, 11)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(txtGained1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 1, 1)
                                .addComponent(jLabel24))
                            .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(17, 17, 17))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtSearchCategory)
                                    .addComponent(txtStoreSearchName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                            .addGap(28, 28, 28)
                                            .addComponent(txtSearchMonthly, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                            .addGap(26, 26, 26)
                                            .addComponent(jLabel10))
                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                            .addGap(26, 26, 26)
                                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(txtSeachYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel25))))
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtGained1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel24))
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtSeachDaily, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtproductNameSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGap(2, 2, 2)
                                        .addComponent(txtBarcode, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnReports1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCheckBoxMarket)
                            .addComponent(txtCheckBoxStore))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnReports, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 457, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 473, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(330, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(204, 204, 204));

        btnDelete.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(0, 0, 0));
        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/small delete.png"))); // NOI18N
        btnDelete.setText("حذف");
        btnDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnUpdate.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnUpdate.setForeground(new java.awt.Color(0, 0, 0));
        btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/update.png"))); // NOI18N
        btnUpdate.setText("تعديل");
        btnUpdate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnSave.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSave.setForeground(new java.awt.Color(0, 0, 0));
        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/save.png"))); // NOI18N
        btnSave.setText("حفظ");
        btnSave.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        txtPriceOfBuy.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtPriceOfBuy.setForeground(new java.awt.Color(0, 0, 0));
        txtPriceOfBuy.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPriceOfBuyKeyReleased(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("سعر الشراء:");

        txtProductName.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        txtProductName.setForeground(new java.awt.Color(0, 0, 0));
        txtProductName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtProductNameActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("اسم المنتج:");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("باركود:");

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("سعر البيع:");

        txtPriceOfSale.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtPriceOfSale.setForeground(new java.awt.Color(0, 0, 0));
        txtPriceOfSale.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPriceOfSaleKeyReleased(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(0, 0, 0));
        jLabel27.setText("كمية المحل:");

        txtQuantityForSale.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtQuantityForSale.setForeground(new java.awt.Color(0, 0, 0));
        txtQuantityForSale.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtQuantityForSale.setText("0");
        txtQuantityForSale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtQuantityForSaleActionPerformed(evt);
            }
        });
        txtQuantityForSale.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtQuantityForSaleKeyReleased(evt);
            }
        });

        txtQuantityOfOne.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtQuantityOfOne.setForeground(new java.awt.Color(0, 0, 0));
        txtQuantityOfOne.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtQuantityOfOneActionPerformed(evt);
            }
        });
        txtQuantityOfOne.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtQuantityOfOneKeyReleased(evt);
            }
        });

        txtTypeVariable.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txtTypeVariable.setForeground(new java.awt.Color(0, 0, 0));
        txtTypeVariable.setText("__");

        txtPriceOfSaleTypeVarialble.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txtPriceOfSaleTypeVarialble.setForeground(new java.awt.Color(0, 0, 0));
        txtPriceOfSaleTypeVarialble.setText("__");

        txtPriceOfSaleType.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtPriceOfSaleType.setForeground(new java.awt.Color(0, 0, 0));
        txtPriceOfSaleType.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPriceOfSaleTypeKeyReleased(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("قسم:");

        txtCategory.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtCategory.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", " " }));
        txtCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCategoryActionPerformed(evt);
            }
        });

        txtPriceOfBuyTypesVariable.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txtPriceOfBuyTypesVariable.setForeground(new java.awt.Color(0, 0, 0));
        txtPriceOfBuyTypesVariable.setText("__");

        txtPriceOfBuyTypes.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtPriceOfBuyTypes.setForeground(new java.awt.Color(0, 0, 0));
        txtPriceOfBuyTypes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPriceOfBuyTypesKeyReleased(evt);
            }
        });

        jPanel7.setBackground(new java.awt.Color(0, 153, 204));

        txtProductTypeName.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtProductTypeName.setForeground(new java.awt.Color(0, 0, 0));
        txtProductTypeName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtProductTypeNameActionPerformed(evt);
            }
        });
        txtProductTypeName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtProductTypeNameKeyReleased(evt);
            }
        });

        jLabel8.setBackground(new java.awt.Color(0, 153, 204));
        jLabel8.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("نوع");

        jLabel13.setBackground(new java.awt.Color(0, 153, 204));
        jLabel13.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("المتوفر الحالي");

        txtAvailableComeFromTable.setBackground(new java.awt.Color(0, 204, 51));
        txtAvailableComeFromTable.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtAvailableComeFromTable.setForeground(new java.awt.Color(0, 0, 0));
        txtAvailableComeFromTable.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtAvailableComeFromTable.setText("0.0");
        txtAvailableComeFromTable.setOpaque(true);

        jLabel17.setBackground(new java.awt.Color(0, 153, 204));
        jLabel17.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("مسحوب");

        txtTakedComeFromTable.setBackground(new java.awt.Color(204, 0, 51));
        txtTakedComeFromTable.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtTakedComeFromTable.setForeground(new java.awt.Color(0, 0, 0));
        txtTakedComeFromTable.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtTakedComeFromTable.setText("0.0");
        txtTakedComeFromTable.setOpaque(true);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtProductTypeName)
            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtAvailableComeFromTable, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTakedComeFromTable, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtProductTypeName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtAvailableComeFromTable)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTakedComeFromTable)
                .addContainerGap(50, Short.MAX_VALUE))
        );

        jLabel34.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(0, 0, 0));
        jLabel34.setText("مخزن:");

        txtStoreName.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtStoreName.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", " " }));
        txtStoreName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtStoreNameActionPerformed(evt);
            }
        });

        jLabel28.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(0, 0, 0));
        jLabel28.setText("كمية المخزن:");

        txtQuantityStore.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtQuantityStore.setForeground(new java.awt.Color(0, 0, 0));
        txtQuantityStore.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtQuantityStore.setText("0");
        txtQuantityStore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtQuantityStoreActionPerformed(evt);
            }
        });
        txtQuantityStore.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtQuantityStoreKeyReleased(evt);
            }
        });

        txtPriceOfSaleTypeVarialble1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtPriceOfSaleTypeVarialble1.setForeground(new java.awt.Color(0, 0, 0));
        txtPriceOfSaleTypeVarialble1.setText("سعر التجاري:");

        txtMarketPrice.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtMarketPrice.setForeground(new java.awt.Color(0, 0, 0));
        txtMarketPrice.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMarketPriceKeyReleased(evt);
            }
        });

        txtPriceOfSaleTypeVarialble2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtPriceOfSaleTypeVarialble2.setForeground(new java.awt.Color(0, 0, 0));
        txtPriceOfSaleTypeVarialble2.setText("ملاحظة:");

        txtNotes.setColumns(20);
        txtNotes.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtNotes.setRows(5);
        txtNotes.setText("__");
        txtNotes.setToolTipText("");
        jScrollPane5.setViewportView(txtNotes);

        txtQuantityWarning.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtQuantityWarning.setForeground(new java.awt.Color(0, 0, 0));
        txtQuantityWarning.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtQuantityWarning.setText("0");
        txtQuantityWarning.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtQuantityWarningActionPerformed(evt);
            }
        });
        txtQuantityWarning.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtQuantityWarningKeyReleased(evt);
            }
        });

        jLabel35.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(0, 0, 0));
        jLabel35.setText("انذار العدد:");

        txtPriceOfMarketVar.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txtPriceOfMarketVar.setForeground(new java.awt.Color(0, 0, 0));
        txtPriceOfMarketVar.setText("__");

        txtMarketPriceTypes.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtMarketPriceTypes.setForeground(new java.awt.Color(0, 0, 0));
        txtMarketPriceTypes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMarketPriceTypesKeyReleased(evt);
            }
        });

        txtSupplierNameAddProduct.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtSupplierNameAddProduct.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", " " }));
        txtSupplierNameAddProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSupplierNameAddProductActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("اسم المورد:");

        txtProductId.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtProductId.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtProductId.setText("0");
        txtProductId.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtProductIdFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtProductIdFocusLost(evt);
            }
        });
        txtProductId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtProductIdKeyReleased(evt);
            }
        });

        txtProductCodeDefault.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtProductCodeDefault.setForeground(new java.awt.Color(0, 0, 0));
        txtProductCodeDefault.setText("0");

        btnBarCodeEnable.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/login.png"))); // NOI18N
        btnBarCodeEnable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBarCodeEnableActionPerformed(evt);
            }
        });

        txtProductIdTypes.setForeground(new java.awt.Color(0, 0, 0));
        txtProductIdTypes.setText("0");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBarCodeEnable)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtProductIdTypes, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtPriceOfBuy, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                                    .addComponent(txtQuantityOfOne))
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtTypeVariable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel6Layout.createSequentialGroup()
                                                .addGap(55, 55, 55)
                                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                                        .addGap(1, 1, 1)
                                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(jLabel34)
                                                            .addComponent(jLabel27)
                                                            .addComponent(jLabel3)
                                                            .addComponent(jLabel4)))
                                                    .addComponent(jLabel35)
                                                    .addComponent(jLabel28)))
                                            .addGroup(jPanel6Layout.createSequentialGroup()
                                                .addGap(12, 12, 12)
                                                .addComponent(txtPriceOfBuyTypesVariable, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtQuantityStore, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtStoreName, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtQuantityForSale, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtQuantityWarning, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtProductName, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtProductCodeDefault, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txtProductId, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE))
                                        .addGap(1, 1, 1)
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel1)
                                            .addComponent(jLabel9)))))))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtSupplierNameAddProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(147, 147, 147)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(txtPriceOfSaleTypeVarialble2, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(btnSave)
                        .addGap(26, 26, 26)
                        .addComponent(btnUpdate)
                        .addGap(27, 27, 27)
                        .addComponent(btnDelete))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtPriceOfBuyTypes, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txtPriceOfSale, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtPriceOfSaleType, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(txtMarketPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtMarketPriceTypes, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(txtPriceOfMarketVar, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txtPriceOfSaleTypeVarialble1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtPriceOfSaleTypeVarialble, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(12, 12, 12))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addComponent(txtProductCodeDefault)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtProductId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBarCodeEnable, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtProductName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(txtProductIdTypes))
                .addGap(10, 10, 10)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSupplierNameAddProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(9, 9, 9)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(11, 11, 11)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtStoreName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel34))
                        .addGap(5, 5, 5)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtQuantityForSale, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel27))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtQuantityStore, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel28))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtQuantityWarning, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel35))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtQuantityOfOne, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTypeVariable))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPriceOfBuy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)))
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(txtPriceOfBuyTypes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtPriceOfBuyTypesVariable))
                .addGap(4, 4, 4)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(txtPriceOfSale, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel20))
                .addGap(8, 8, 8)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(txtPriceOfSaleType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtPriceOfSaleTypeVarialble))
                .addGap(8, 8, 8)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(txtMarketPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtPriceOfSaleTypeVarialble1))
                .addGap(10, 10, 10)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(txtMarketPriceTypes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtPriceOfMarketVar))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(txtPriceOfSaleTypeVarialble2)))
                .addGap(7, 7, 7)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnUpdate)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSave)
                            .addComponent(btnDelete))))
                .addContainerGap(822, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 481, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("تفاصيل المنتجات", jPanel2);

        jPanel3.setBackground(new java.awt.Color(0, 153, 153));

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        jLabel2.setText("اسم المنتج:");

        jLabel11.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        jLabel11.setText("باركود المنتج محل:");

        txtBarcodeProduct.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtBarcodeProduct.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtBarcodeProduct.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBarcodeProductKeyReleased(evt);
            }
        });

        txtproductTypeNameSuppliers.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtproductTypeNameSuppliers.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", " " }));
        txtproductTypeNameSuppliers.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        txtproductTypeNameSuppliers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtproductTypeNameSuppliersActionPerformed(evt);
            }
        });

        txtProductNameLable.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N

        jLabel12.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        jLabel12.setText("سعر:");

        txtQuantityOfOneVariable.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        txtQuantityOfOneVariable.setText("كمية:");

        txtQuantitySingle.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtQuantitySingle.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtQuantitySingleKeyReleased(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        jLabel14.setText("تكلفة كلية:");

        jLabel15.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        jLabel15.setText("تم دفع:");

        txtPayedValue.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtPayedValue.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPayedValue.setText("0.0");
        txtPayedValue.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtPayedValueFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtPayedValueFocusLost(evt);
            }
        });
        txtPayedValue.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPayedValueKeyReleased(evt);
            }
        });

        txtPriceOfOne.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtPriceOfOne.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPriceOfOne.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPriceOfOneKeyReleased(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        jLabel18.setText("ملاحظة:");

        txtRemained.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        txtRemained.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtRemained.setText("0.0");

        txtTotalQuantity.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        txtTotalQuantity.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtTotalQuantity.setText("0.0");

        jLabel19.setBackground(new java.awt.Color(0, 153, 204));
        jLabel19.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("المتوفر الحالي");

        txtAvailableFromProductOnSupplier.setBackground(new java.awt.Color(0, 204, 51));
        txtAvailableFromProductOnSupplier.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtAvailableFromProductOnSupplier.setForeground(new java.awt.Color(0, 0, 0));
        txtAvailableFromProductOnSupplier.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtAvailableFromProductOnSupplier.setText("0.0");
        txtAvailableFromProductOnSupplier.setOpaque(true);

        jLabel21.setBackground(new java.awt.Color(0, 153, 204));
        jLabel21.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("مسحوب");

        txtTakedFromProductsOnSupplier.setBackground(new java.awt.Color(204, 0, 51));
        txtTakedFromProductsOnSupplier.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtTakedFromProductsOnSupplier.setForeground(new java.awt.Color(0, 0, 0));
        txtTakedFromProductsOnSupplier.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtTakedFromProductsOnSupplier.setText("0.0");
        txtTakedFromProductsOnSupplier.setOpaque(true);

        btnPrint.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        btnPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/reports.png"))); // NOI18N
        btnPrint.setText("حفظ/طباعة");
        btnPrint.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });

        btnAddToCart.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        btnAddToCart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/sales_menu.png"))); // NOI18N
        btnAddToCart.setText("اضافة");
        btnAddToCart.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAddToCart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddToCartActionPerformed(evt);
            }
        });

        jTableAddForCaty.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jTableAddForCaty.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "رقم الفاتورة", "اسم المنتج", "باركود", "باركود الشركة", "كمية", "سعر", "تكلفة"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableAddForCaty.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableAddForCatyMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableAddForCaty);

        jButton4.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/small delete.png"))); // NOI18N
        jButton4.setText("حذف الكل");
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        txtQuantityOfOneVariable1.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        txtQuantityOfOneVariable1.setText("__");

        txtQuantityOfOneValue1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtQuantityOfOneValue1.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtQuantityOfOneVariable2.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        txtQuantityOfOneVariable2.setText("تكلفة:");

        txtFirstPriceSuppliers.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        txtFirstPriceSuppliers.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtFirstPriceSuppliers.setText("0.0");

        jLabel29.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N

        txtCasherName.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        txtCasherName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtCasherName.setText("0");

        txtPriceOfAll1.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        txtPriceOfAll1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtPriceOfAll1.setText("0.0");

        jLabel30.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        jLabel30.setText("متبقي:");

        txtsupplierNotes.setColumns(20);
        txtsupplierNotes.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtsupplierNotes.setRows(5);
        txtsupplierNotes.setText("__");
        jScrollPane6.setViewportView(txtsupplierNotes);

        jLabel51.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        jLabel51.setText("الكمية الكلية:");

        jLabel69.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        jLabel69.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/search.png"))); // NOI18N
        jLabel69.setText("رقم الطلب:");

        txtPurchaseNumberSearch.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtPurchaseNumberSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPurchaseNumberSearchKeyReleased(evt);
            }
        });

        txtINIDOfGetProductsInvoice.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        txtINIDOfGetProductsInvoice.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtINIDOfGetProductsInvoice.setText("0");

        jLabel23.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        jLabel23.setText("رقم المورد:");

        txtSupplierPhone.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtSupplierPhone.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSupplierPhoneKeyReleased(evt);
            }
        });

        txtSupplierName.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtSupplierName.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", " " }));
        txtSupplierName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSupplierNameActionPerformed(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        jLabel22.setText("اسم المورد:");

        jLabel43.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel43.setText("اسم المستلم:");

        txtReceiverNameForCart.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtReceiverNameForCart.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", " " }));

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addContainerGap(166, Short.MAX_VALUE)
                .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
            .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel19Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel19Layout.createSequentialGroup()
                            .addComponent(txtSupplierName, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(4, 4, 4)
                            .addComponent(jLabel22))
                        .addComponent(txtReceiverNameForCart, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel19Layout.createSequentialGroup()
                            .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtINIDOfGetProductsInvoice, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                                .addComponent(txtSupplierPhone, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE))
                            .addGap(5, 5, 5)
                            .addComponent(jLabel23)))
                    .addContainerGap(51, Short.MAX_VALUE)))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel19Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(txtINIDOfGetProductsInvoice, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(8, 8, 8)
                    .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel19Layout.createSequentialGroup()
                            .addGap(1, 1, 1)
                            .addComponent(txtSupplierPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel23))
                    .addGap(2, 2, 2)
                    .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel19Layout.createSequentialGroup()
                            .addGap(7, 7, 7)
                            .addComponent(txtSupplierName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel19Layout.createSequentialGroup()
                            .addGap(3, 3, 3)
                            .addComponent(jLabel22)))
                    .addGap(9, 9, 9)
                    .addComponent(txtReceiverNameForCart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        txtBarcodeProductCompany.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtBarcodeProductCompany.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtBarcodeProductCompany.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBarcodeProductCompanyKeyReleased(evt);
            }
        });

        jLabel70.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        jLabel70.setText("باركود المنتج شركة:");

        jLabel71.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        jLabel71.setText("تاريخ الوصول:");

        txtHandDateArrival.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtHandDateArrival.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtHandDateArrivalFocusGained(evt);
            }
        });
        txtHandDateArrival.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtHandDateArrivalPropertyChange(evt);
            }
        });

        txtPurchaseDate.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtPurchaseDate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel72.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        jLabel72.setText("تاريخ الطلب:");

        btnArrivalApproval.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        btnArrivalApproval.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/reports.png"))); // NOI18N
        btnArrivalApproval.setText("تأكيد وصول");
        btnArrivalApproval.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnArrivalApproval.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnArrivalApprovalActionPerformed(evt);
            }
        });

        jLabel73.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        jLabel73.setText("عنوان الشركة:");

        txtCompanyAddress.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtCompanyAddress.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCompanyAddress.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCompanyAddressFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCompanyAddressFocusLost(evt);
            }
        });
        txtCompanyAddress.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCompanyAddressKeyReleased(evt);
            }
        });

        txtSearchPurchaseDate.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        txtSearchPurchaseDate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel74.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        jLabel74.setText("رقم المحل:");

        txtStorePhoneNumber.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtStorePhoneNumber.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtStorePhoneNumber.setText("01020005212");
        txtStorePhoneNumber.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtStorePhoneNumberFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtStorePhoneNumberFocusLost(evt);
            }
        });

        jLabel75.setBackground(new java.awt.Color(0, 153, 153));
        jLabel75.setFont(new java.awt.Font("Dialog", 1, 22)); // NOI18N
        jLabel75.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel75.setText("طلب شراء /استلام منتج");
        jLabel75.setOpaque(true);

        txtStatusSearchPurchase.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        txtStatusSearchPurchase.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout txtAvailableLabelLayout = new javax.swing.GroupLayout(txtAvailableLabel);
        txtAvailableLabel.setLayout(txtAvailableLabelLayout);
        txtAvailableLabelLayout.setHorizontalGroup(
            txtAvailableLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(txtAvailableLabelLayout.createSequentialGroup()
                .addGroup(txtAvailableLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(txtAvailableLabelLayout.createSequentialGroup()
                        .addGroup(txtAvailableLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(txtAvailableLabelLayout.createSequentialGroup()
                                .addGroup(txtAvailableLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtAvailableFromProductOnSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTakedFromProductsOnSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtproductTypeNameSuppliers, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(txtAvailableLabelLayout.createSequentialGroup()
                                .addGap(202, 202, 202)
                                .addGroup(txtAvailableLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtQuantitySingle, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(txtAvailableLabelLayout.createSequentialGroup()
                                        .addComponent(txtFirstPriceSuppliers, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(txtQuantityOfOneVariable2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(txtAvailableLabelLayout.createSequentialGroup()
                                        .addGroup(txtAvailableLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtPriceOfOne, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtQuantityOfOneValue1, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(3, 3, 3)
                                        .addGroup(txtAvailableLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(txtAvailableLabelLayout.createSequentialGroup()
                                                .addGap(4, 4, 4)
                                                .addGroup(txtAvailableLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(txtQuantityOfOneVariable, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(txtQuantityOfOneVariable1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                    .addGroup(txtAvailableLabelLayout.createSequentialGroup()
                                        .addComponent(txtBarcodeProductCompany, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(3, 3, 3)
                                        .addComponent(jLabel70))
                                    .addGroup(txtAvailableLabelLayout.createSequentialGroup()
                                        .addGap(2, 2, 2)
                                        .addGroup(txtAvailableLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtProductNameLable, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(txtAvailableLabelLayout.createSequentialGroup()
                                                .addComponent(txtBarcodeProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(1, 1, 1)
                                                .addGroup(txtAvailableLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel2)
                                                    .addComponent(jLabel11))))))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(txtAvailableLabelLayout.createSequentialGroup()
                        .addComponent(jLabel29)
                        .addGap(6, 6, 6)
                        .addComponent(txtCasherName, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel75, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51)))
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57))
            .addGroup(txtAvailableLabelLayout.createSequentialGroup()
                .addGroup(txtAvailableLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(txtAvailableLabelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtSearchPurchaseDate, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(txtPurchaseNumberSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(jLabel69, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(207, 207, 207)
                        .addComponent(jButton4)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(txtAvailableLabelLayout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addGroup(txtAvailableLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtPriceOfAll1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtPayedValue)
                            .addComponent(txtRemained, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtTotalQuantity, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnArrivalApproval, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE))
                        .addGap(7, 7, 7)
                        .addGroup(txtAvailableLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(txtAvailableLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(txtAvailableLabelLayout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addGroup(txtAvailableLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(txtAvailableLabelLayout.createSequentialGroup()
                                        .addGap(200, 200, 200)
                                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(txtAvailableLabelLayout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addComponent(txtCompanyAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel73, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnPrint, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, txtAvailableLabelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtStatusSearchPurchase, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(btnAddToCart, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addGroup(txtAvailableLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(txtAvailableLabelLayout.createSequentialGroup()
                        .addComponent(txtHandDateArrival, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(jLabel71, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(txtAvailableLabelLayout.createSequentialGroup()
                        .addGroup(txtAvailableLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPurchaseDate, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtStorePhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(txtAvailableLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel72, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel74, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(79, 79, 79))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, txtAvailableLabelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        txtAvailableLabelLayout.setVerticalGroup(
            txtAvailableLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(txtAvailableLabelLayout.createSequentialGroup()
                .addGroup(txtAvailableLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(txtAvailableLabelLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(txtAvailableLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel29)
                            .addGroup(txtAvailableLabelLayout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addGroup(txtAvailableLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtCasherName, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel75, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(31, 31, 31)
                        .addGroup(txtAvailableLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(txtAvailableLabelLayout.createSequentialGroup()
                                .addComponent(jLabel19)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtAvailableFromProductOnSupplier))
                            .addGroup(txtAvailableLabelLayout.createSequentialGroup()
                                .addGroup(txtAvailableLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtProductNameLable, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))
                                .addGap(3, 3, 3)
                                .addGroup(txtAvailableLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtproductTypeNameSuppliers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(txtAvailableLabelLayout.createSequentialGroup()
                                        .addGroup(txtAvailableLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtBarcodeProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel11))
                                        .addGap(3, 3, 3)
                                        .addGroup(txtAvailableLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel70)
                                            .addComponent(txtBarcodeProductCompany, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(2, 2, 2)))
                        .addGap(6, 6, 6)
                        .addGroup(txtAvailableLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(txtAvailableLabelLayout.createSequentialGroup()
                                .addComponent(jLabel21)
                                .addGap(6, 6, 6)
                                .addComponent(txtTakedFromProductsOnSupplier))
                            .addGroup(txtAvailableLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel72)
                                .addGroup(txtAvailableLabelLayout.createSequentialGroup()
                                    .addGroup(txtAvailableLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(txtAvailableLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(txtAvailableLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel12)
                                                .addComponent(txtPriceOfOne, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(txtAvailableLabelLayout.createSequentialGroup()
                                                .addGap(34, 34, 34)
                                                .addComponent(txtQuantityOfOneVariable1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(txtAvailableLabelLayout.createSequentialGroup()
                                            .addGap(34, 34, 34)
                                            .addComponent(txtQuantityOfOneValue1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGap(7, 7, 7)
                                    .addGroup(txtAvailableLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtQuantityOfOneVariable, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtStorePhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel74)
                                        .addComponent(txtQuantitySingle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(6, 6, 6)
                                    .addGroup(txtAvailableLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtFirstPriceSuppliers, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtQuantityOfOneVariable2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtPurchaseDate, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(txtAvailableLabelLayout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(txtAvailableLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(txtAvailableLabelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(txtAvailableLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(txtAvailableLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btnAddToCart, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel71))
                            .addComponent(txtHandDateArrival, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(13, 13, 13)
                        .addGroup(txtAvailableLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel69)
                            .addComponent(jButton4)
                            .addComponent(txtPurchaseNumberSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(txtAvailableLabelLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(txtStatusSearchPurchase, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(txtSearchPurchaseDate, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(13, 13, 13)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(txtAvailableLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(txtAvailableLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(txtAvailableLabelLayout.createSequentialGroup()
                            .addComponent(jLabel14)
                            .addGap(4, 4, 4)
                            .addComponent(jLabel15)
                            .addGap(4, 4, 4)
                            .addComponent(jLabel30)
                            .addGap(5, 5, 5)
                            .addComponent(jLabel51))
                        .addGroup(txtAvailableLabelLayout.createSequentialGroup()
                            .addGap(7, 7, 7)
                            .addGroup(txtAvailableLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel73)
                                .addComponent(txtCompanyAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(txtAvailableLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(txtAvailableLabelLayout.createSequentialGroup()
                                    .addGap(70, 70, 70)
                                    .addComponent(jLabel18))))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, txtAvailableLabelLayout.createSequentialGroup()
                            .addComponent(btnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(2, 2, 2)))
                    .addGroup(txtAvailableLabelLayout.createSequentialGroup()
                        .addComponent(txtPriceOfAll1)
                        .addGap(4, 4, 4)
                        .addComponent(txtPayedValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(txtRemained)
                        .addGap(5, 5, 5)
                        .addComponent(txtTotalQuantity)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnArrivalApproval)))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jPanel10.setBackground(new java.awt.Color(51, 51, 51));

        jTableSalesSuppliers.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jTableSalesSuppliers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "رقم الطلب", "اسم المورد", "كمية", "تكلفة", "تم دفع", "متبقي", "تاريخ الطلب", "تاريخ الاستلام", "المستلم", "الحالة", "كاشير", "ملحوظة"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableSalesSuppliers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableSalesSuppliersMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTableSalesSuppliers);

        txtTodayDate.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtTodayDate.setForeground(new java.awt.Color(255, 255, 255));
        txtTodayDate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel36.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(255, 255, 255));
        jLabel36.setText("رقم الطلب:");

        txtINID.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtINID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtINIDKeyReleased(evt);
            }
        });

        jLabel37.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(255, 255, 255));
        jLabel37.setText("اسم المورد:");

        jLabel38.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(255, 255, 255));
        jLabel38.setText("رقم المورد:");

        txtSupplierNumberSearch.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N

        txtSupplierNameSearch.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtSupplierNameSearch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", " " }));
        txtSupplierNameSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSupplierNameSearchActionPerformed(evt);
            }
        });

        txtHandDateSaerchSuppliers.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtHandDateSaerchSuppliers.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtHandDateSaerchSuppliersFocusGained(evt);
            }
        });
        txtHandDateSaerchSuppliers.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtHandDateSaerchSuppliersPropertyChange(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/search x30.png"))); // NOI18N
        jButton1.setText("بحث");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        txtMonthSearchSuppliers.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtMonthSearchSuppliers.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        txtMonthSearchSuppliers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMonthSearchSuppliersActionPerformed(evt);
            }
        });

        jLabel39.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(255, 255, 255));
        jLabel39.setText("شهر:");

        txtYearSearchSuppliers.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtYearSearchSuppliers.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2023", "2024", "2025", "2026", "2027", "2028", "2029", "20230" }));

        jLabel40.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(255, 255, 255));
        jLabel40.setText("سنة:");

        jLabel41.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(255, 255, 255));
        jLabel41.setText("اسم المستلم:");

        txtReceiverNameSaerch.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtReceiverNameSaerch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", " " }));
        txtReceiverNameSaerch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtReceiverNameSaerchActionPerformed(evt);
            }
        });

        jLabel42.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(255, 255, 255));
        jLabel42.setText("المتبقي:");

        txtDayPayedTotal.setBackground(new java.awt.Color(0, 153, 0));
        txtDayPayedTotal.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        txtDayPayedTotal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtDayPayedTotal.setText("0.0");
        txtDayPayedTotal.setOpaque(true);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/update.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        txtMonthPriceTotal.setBackground(new java.awt.Color(0, 102, 204));
        txtMonthPriceTotal.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        txtMonthPriceTotal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtMonthPriceTotal.setText("0.0");
        txtMonthPriceTotal.setOpaque(true);

        jLabel44.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(255, 255, 255));
        jLabel44.setText("توتال التكلفة:");

        jButton5.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/place order.png"))); // NOI18N
        jButton5.setText("تقرير");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel47.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(255, 255, 255));
        jLabel47.setText("المدفوع اليومي:");

        txtDayRemained.setBackground(new java.awt.Color(0, 153, 0));
        txtDayRemained.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        txtDayRemained.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtDayRemained.setText("0.0");
        txtDayRemained.setOpaque(true);

        jLabel48.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(255, 255, 255));
        jLabel48.setText("توتال التكلفة:");

        txtDayPriceTotal.setBackground(new java.awt.Color(0, 153, 0));
        txtDayPriceTotal.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        txtDayPriceTotal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtDayPriceTotal.setText("0.0");
        txtDayPriceTotal.setOpaque(true);

        jLabel49.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(255, 255, 255));
        jLabel49.setText("توتال المدفوع:");

        txtMonthPayedTotal.setBackground(new java.awt.Color(0, 102, 204));
        txtMonthPayedTotal.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        txtMonthPayedTotal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtMonthPayedTotal.setText("0.0");
        txtMonthPayedTotal.setOpaque(true);

        txtTotalRemainedMonth.setBackground(new java.awt.Color(0, 102, 204));
        txtTotalRemainedMonth.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        txtTotalRemainedMonth.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtTotalRemainedMonth.setText("0.0");
        txtTotalRemainedMonth.setOpaque(true);

        jLabel50.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(255, 255, 255));
        jLabel50.setText("المتبقي:");

        jButton7.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/place order.png"))); // NOI18N
        jButton7.setText("تقرير مفصل");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        txtSatusSearch.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtSatusSearch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "قيد الطلب وتم الدفع", "قيد الطلب وتم دفع جزء", "قيد الطلب فقط", "تم الاستلام وتم الدفع", "تم الاستلام ومتبقي جزء", "تم الاستلام ولم يتم الدفع" }));
        txtSatusSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSatusSearchActionPerformed(evt);
            }
        });

        jLabel56.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel56.setForeground(new java.awt.Color(255, 255, 255));
        jLabel56.setText("الحالة:");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtTodayDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE))
                        .addGap(53, 53, 53)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(txtDayRemained, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtDayPayedTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtDayPriceTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(42, 42, 42)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(txtMonthPriceTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(txtTotalRemainedMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(txtMonthPayedTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(txtReceiverNameSaerch, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(3, 3, 3)
                                .addComponent(txtSupplierNumberSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(81, 81, 81)
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtSatusSearch, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(txtINID, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)
                                .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(23, 23, 23)
                                .addComponent(txtSupplierNameSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(txtHandDateSaerchSuppliers, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(txtMonthSearchSuppliers, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)
                                .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(txtYearSearchSuppliers, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(2, 2, 2)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel40, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel56, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(744, Short.MAX_VALUE))
            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(txtReceiverNameSaerch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtSatusSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton1))))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSupplierNumberSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(9, 9, 9)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(txtINID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSupplierNameSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHandDateSaerchSuppliers, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMonthSearchSuppliers, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtYearSearchSuppliers, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 537, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel10Layout.createSequentialGroup()
                            .addGap(36, 36, 36)
                            .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel10Layout.createSequentialGroup()
                            .addGap(37, 37, 37)
                            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel10Layout.createSequentialGroup()
                                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel10Layout.createSequentialGroup()
                                            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txtDayPriceTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(8, 8, 8)
                                            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(txtDayPayedTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(txtMonthPayedTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addComponent(txtMonthPriceTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel10Layout.createSequentialGroup()
                                            .addGap(1, 1, 1)
                                            .addComponent(txtTotalRemainedMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtDayRemained, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jButton7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton5)
                        .addGap(5, 5, 5)
                        .addComponent(txtTodayDate, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(156, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(txtAvailableLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 934, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAvailableLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(626, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("استلام منتج/طلب شراء", new javax.swing.ImageIcon(getClass().getResource("/Images/place order.png")), jPanel3); // NOI18N

        jPanel12.setBackground(new java.awt.Color(0, 153, 204));
        jPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTableLowQuantity.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jTableLowQuantity.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "بار كود", "اسم المنتج", "قسم", "مخزن", "انذار العدد", "متوفر محل", "متوفر مخزن", "توتال المتوفر"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPaneLowQuantities.setViewportView(jTableLowQuantity);

        jLabel52.setBackground(new java.awt.Color(204, 0, 51));
        jLabel52.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel52.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel52.setText("نواقص");
        jLabel52.setOpaque(true);

        jLabel53.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel53.setText("مخزن:");

        txtStoreNameSearchLowQuantity.setBackground(new java.awt.Color(0, 153, 204));
        txtStoreNameSearchLowQuantity.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtStoreNameSearchLowQuantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtStoreNameSearchLowQuantityActionPerformed(evt);
            }
        });

        jLabel54.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel54.setText("قسم:");

        txtcategorySearchLowQuantity1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtcategorySearchLowQuantity1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcategorySearchLowQuantity1ActionPerformed(evt);
            }
        });

        jLabel55.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel55.setText("باركود:");

        txtBarCodeSearchLowQuantity.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtBarCodeSearchLowQuantity.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtBarCodeSearchLowQuantity.setToolTipText("");
        txtBarCodeSearchLowQuantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBarCodeSearchLowQuantityActionPerformed(evt);
            }
        });
        txtBarCodeSearchLowQuantity.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBarCodeSearchLowQuantityKeyReleased(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/reports.png"))); // NOI18N
        jButton6.setText("تقرير");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator3)
                    .addComponent(jScrollPaneLowQuantities, javax.swing.GroupLayout.DEFAULT_SIZE, 1616, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(202, 202, 202)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(txtcategorySearchLowQuantity1, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(txtStoreNameSearchLowQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(138, 138, 138)
                .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(248, 248, 248)
                .addComponent(txtBarCodeSearchLowQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(528, 528, 528)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtStoreNameSearchLowQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtcategorySearchLowQuantity1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtBarCodeSearchLowQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel55)))
                    .addComponent(jLabel52))
                .addGap(12, 12, 12)
                .addComponent(jScrollPaneLowQuantities, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel12.add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(211, 102, 1640, -1));

        jTabbedPane1.addTab("نواقص", jPanel12);

        jPanel15.setBackground(new java.awt.Color(0, 153, 204));

        jPanel16.setBackground(new java.awt.Color(0, 153, 204));

        jLabel58.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel58.setText("باركود:");

        txtBarcodeAdd.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtBarcodeAdd.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtBarcodeAdd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBarcodeAddKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBarcodeAddKeyReleased(evt);
            }
        });

        jLabel59.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel59.setText("مكان الاضافة:");

        txtMarketOrStore.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtMarketOrStore.setForeground(new java.awt.Color(0, 0, 0));
        txtMarketOrStore.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "محل", " " }));
        txtMarketOrStore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMarketOrStoreActionPerformed(evt);
            }
        });

        jLabel60.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel60.setText("كمية:");

        txtQuantityAdded.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtQuantityAdded.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtQuantityAdded.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtQuantityAddedKeyReleased(evt);
            }
        });

        btnAddQuantity.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnAddQuantity.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/add.png"))); // NOI18N
        btnAddQuantity.setText("اضافة");
        btnAddQuantity.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAddQuantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddQuantityActionPerformed(evt);
            }
        });

        txtProductsSearchs.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtProductsSearchs.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtProductsSearchs.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtProductsSearchsKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtProductsSearchsKeyReleased(evt);
            }
        });

        jLabel62.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel62.setText("اسم:");

        txtproductNameAdd.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtproductNameAdd.setForeground(new java.awt.Color(0, 0, 0));
        txtproductNameAdd.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "اسم المنتج", " " }));
        txtproductNameAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtproductNameAddActionPerformed(evt);
            }
        });

        txtTypeNameCompo.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtTypeNameCompo.setForeground(new java.awt.Color(0, 0, 0));
        txtTypeNameCompo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "نوع" }));
        txtTypeNameCompo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTypeNameCompoActionPerformed(evt);
            }
        });

        jLabel63.setBackground(new java.awt.Color(0, 0, 0));
        jLabel63.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel63.setForeground(new java.awt.Color(0, 0, 0));
        jLabel63.setText("كمية المحل:");

        txtAvailableQtyMarketAdd.setBackground(new java.awt.Color(0, 0, 0));
        txtAvailableQtyMarketAdd.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtAvailableQtyMarketAdd.setForeground(new java.awt.Color(0, 0, 0));
        txtAvailableQtyMarketAdd.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtAvailableQtyMarketAdd.setText("0.0");

        txtAvailableQtyStoreAdd.setBackground(new java.awt.Color(0, 0, 0));
        txtAvailableQtyStoreAdd.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtAvailableQtyStoreAdd.setForeground(new java.awt.Color(0, 0, 0));
        txtAvailableQtyStoreAdd.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtAvailableQtyStoreAdd.setText("0.0");

        jLabel66.setBackground(new java.awt.Color(0, 0, 0));
        jLabel66.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel66.setForeground(new java.awt.Color(0, 0, 0));
        jLabel66.setText("كمية المخزن:");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(81, 81, 81)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtproductNameAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtAvailableQtyStoreAdd, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAvailableQtyMarketAdd, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel63)
                            .addComponent(jLabel66))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTypeNameCompo, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAddQuantity, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtProductsSearchs)
                            .addComponent(txtQuantityAdded)
                            .addComponent(txtBarcodeAdd, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMarketOrStore, javax.swing.GroupLayout.Alignment.LEADING, 0, 137, Short.MAX_VALUE))
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(jLabel59))
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(jLabel60))
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel62)
                                    .addComponent(jLabel58))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addGap(112, 112, 112)
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtProductsSearchs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel62)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(txtproductNameAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtBarcodeAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel58)
                            .addComponent(txtTypeNameCompo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel63)
                            .addComponent(txtAvailableQtyMarketAdd))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel59)
                            .addComponent(txtMarketOrStore, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel66)
                        .addComponent(txtAvailableQtyStoreAdd)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel60)
                    .addComponent(txtQuantityAdded, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAddQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(367, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(2055, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 969, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("اضافة اعداد", jPanel15);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        try { // It Will be Group by supplierName; where date like
            HashMap para = new HashMap();
            ReportView r = null;
            if (txtHandDateSaerchSuppliers.getDate() != null && txtSupplierNameSearch.getSelectedIndex() != 0) {
                para.put("Inv_date", df.format(txtHandDateSaerchSuppliers.getDate()) + "____________%");
                para.put("Inv_para", txtSupplierNameSearch.getSelectedItem().toString());
                para.put("dateView", df.format(txtHandDateSaerchSuppliers.getDate()));
                para.put("TotalPrice",txtDayPriceTotal.getText());
                para.put("TotalPayed",txtDayPayedTotal.getText());
                para.put("TotalRemained",txtDayRemained.getText());
                r = new ReportView("src\\reports\\supplierNameReport.jasper", para);
                r.view();
            } else if (txtMonthSearchSuppliers.getSelectedIndex() != 0 && txtSupplierNameSearch.getSelectedIndex() != 0) {
                para.put("Inv_date", txtYearSearchSuppliers.getSelectedItem().toString() + "_" + txtMonthSearchSuppliers.getSelectedItem().toString() + "______________%");
                para.put("Inv_para", txtSupplierNameSearch.getSelectedItem().toString());
                para.put("dateView", txtYearSearchSuppliers.getSelectedItem().toString() + "_" + txtMonthSearchSuppliers.getSelectedItem().toString());
                para.put("TotalPrice",txtMonthPriceTotal.getText());
                para.put("TotalPayed",txtMonthPayedTotal.getText());
                para.put("TotalRemained",txtTotalRemainedMonth.getText());
                r = new ReportView("src\\reports\\supplierNameReport.jasper", para);
                r.view();
            } else if (txtHandDateSaerchSuppliers.getDate() != null && txtSupplierNameSearch.getSelectedIndex() == 0) {
                para.put("Inv_date", df.format(txtHandDateSaerchSuppliers.getDate()) + "____________%");
                para.put("dateView", df.format(txtHandDateSaerchSuppliers.getDate()));
                  para.put("TotalPrice",txtDayPriceTotal.getText());
                para.put("TotalPayed",txtDayPayedTotal.getText());
                para.put("TotalRemained",txtDayRemained.getText());
                r = new ReportView("src\\reports\\purchaseLargeReportDetailed.jasper", para);
                r.view();
            } else if (txtHandDateSaerchSuppliers.getDate() == null && txtMonthSearchSuppliers.getSelectedIndex() != 0) {
                para.put("Inv_date", txtYearSearchSuppliers.getSelectedItem().toString() + "_" + txtMonthSearchSuppliers.getSelectedItem().toString() + "_______________%");
                para.put("dateView", txtYearSearchSuppliers.getSelectedItem().toString() + "_" + txtMonthSearchSuppliers.getSelectedItem().toString());
                  para.put("TotalPrice",txtMonthPriceTotal.getText());
                para.put("TotalPayed",txtMonthPayedTotal.getText());
                para.put("TotalRemained",txtTotalRemainedMonth.getText());
                r = new ReportView("src\\reports\\purchaseLargeReportDetailed.jasper", para);
                r.view();
            } else {
                para.put("Inv_date", txtTodayDate.getText() + "____________%");
                para.put("Inv_para", txtSupplierNameSearch.getSelectedItem().toString());
                para.put("dateView", txtTodayDate.getText());
                 para.put("TotalPrice",txtDayPriceTotal.getText());
                para.put("TotalPayed",txtDayPayedTotal.getText());
                para.put("TotalRemained",txtDayRemained.getText());
                r = new ReportView("src\\reports\\supplierNameReport.jasper", para);
                r.view();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        try {
            HashMap para = new HashMap();
            ReportView r = null;
            DefaultTableModel dtm = (DefaultTableModel) jTableSalesSuppliers.getModel();
            if (txtSupplierNameSearch.getSelectedIndex() != 0) {
                para.put("dayDate", txtSupplierNameSearch.getSelectedItem().toString());  // inv_id  is ireport parameter name
                para.put("Total", "" + txtDayPayedTotal.getText());
                para.put("remained", "" + txtDayRemained.getText());
                para.put("Price", "" + txtDayPriceTotal.getText());
                r = new ReportView("src\\reports\\salesSuppliersjTable.jasper", para);
                r.view3(dtm, "src\\reports\\salesSuppliersjTable.jrxml");
            } else if (txtReceiverNameSaerch.getSelectedIndex() != 0) {
                para.put("dayDate", txtReceiverNameSaerch.getSelectedItem().toString());
                para.put("Total", "" + txtDayPayedTotal.getText());
                para.put("remained", "" + txtDayRemained.getText());
                para.put("Price", "" + txtDayPriceTotal.getText());
                r = new ReportView("src\\reports\\salesSuppliersjTable.jasper", para);
                r.view3(dtm, "src\\reports\\salesSuppliersjTable.jrxml");
            } else if (txtHandDateSaerchSuppliers.getDate() != null) {
                para.put("dayDate", df.format(txtHandDateSaerchSuppliers.getDate()));
                para.put("Total", "" + txtDayPayedTotal.getText());
                para.put("remained", "" + txtDayRemained.getText());
                para.put("Price", "" + txtDayPriceTotal.getText());
                r = new ReportView("src\\reports\\salesSuppliersjTable.jasper", para);
                r.view3(dtm, "src\\reports\\salesSuppliersjTable.jrxml");
            } else if (txtMonthSearchSuppliers.getSelectedIndex() != 0) {
                para.put("dayDate", txtMonthSearchSuppliers.getSelectedItem().toString() + "-" + txtYearSearchSuppliers.getSelectedItem().toString());
                para.put("Total", "" + txtMonthPayedTotal.getText());
                para.put("remained", "" + txtTotalRemainedMonth.getText());
                para.put("Price", "" + txtMonthPriceTotal.getText());
                r = new ReportView("src\\reports\\salesSuppliersjTable.jasper", para);
                r.view3(dtm, "src\\reports\\salesSuppliersjTable.jrxml");
            } else {
                para.put("dayDate", txtTodayDate.getText());
                para.put("Total", "" + txtDayPayedTotal.getText());
                para.put("remained", "" + txtDayRemained.getText());
                para.put("Price", "" + txtDayPriceTotal.getText());
                r = new ReportView("src\\reports\\salesSuppliersjTable.jasper", para);
                r.view3(dtm, "src\\reports\\salesSuppliersjTable.jrxml");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            ResultSet rs = dbOperations.getData("select sum(AllTotal) as AllTotal,sum(payed) as payed,sum(Remained) as Remained from salesSuppliers where DayDate like'" + txtTodayDate.getText() + "____________%'");
            while (rs.next()) {
                txtDayPriceTotal.setText(rs.getDouble("AllTotal") + "");
                txtDayPayedTotal.setText(rs.getDouble("payed") + "");// It will be add payForSupplier table sum(AllPayed) where date like
                txtDayRemained.setText(rs.getDouble("Remained") + "");// It will be from payForSupplier table sum(remainedNow)
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txtReceiverNameSaerchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtReceiverNameSaerchActionPerformed
        try {
            if (txtMonthSearchSuppliers.getSelectedIndex() != 0 && txtHandDateSaerchSuppliers.getDate() == null) {
                if (txtSupplierNameSearch.getSelectedIndex() != 0 && txtReceiverNameSaerch.getSelectedIndex() != 0) {
                    DefaultTableModel dtm = (DefaultTableModel) jTableSalesSuppliers.getModel();
                    dtm.setRowCount(0);
                    ResultSet rs = dbOperations.getData("select sum(AllTotal) as AllTotal,sum(payed) as payed,sum(Remained) as Remained from salesSuppliers where ReceiverName='" + txtReceiverNameSaerch.getSelectedItem().toString() + "' and "
                            + "supplierName='" + txtSupplierNameSearch.getSelectedItem().toString() + "' and DayDate like'" +  txtYearSearchSuppliers.getSelectedItem().toString()+ "_" + txtMonthSearchSuppliers.getSelectedItem().toString()+ "_______________%'");
                    while (rs.next()) {
                        txtMonthPriceTotal.setText(rs.getDouble("AllTotal") + "");
                        txtMonthPayedTotal.setText(rs.getDouble("payed") + "");//  It will be add payForSupplier table sum(AllPayed) where date like
                        txtTotalRemainedMonth.setText(rs.getDouble("Remained") + "");// It will be from payForSupplier table sum(remainedNow)
                    }
                    ResultSet rs1 = dbOperations.getData("select * from salesSuppliers where ReceiverName='" + txtReceiverNameSaerch.getSelectedItem().toString() + "' and "
                            + "supplierName='" + txtSupplierNameSearch.getSelectedItem().toString() + "' and DayDate like'" + txtYearSearchSuppliers.getSelectedItem().toString()+ "_" + txtMonthSearchSuppliers.getSelectedItem().toString()+ "_______________%'");
                    while (rs1.next()) {
                        dtm.addRow(new Object[]{
                            rs1.getLong("INID"),
                            rs1.getString("supplierName"),
                            rs1.getDouble("Total_Quantity"),
                            rs1.getDouble("AllTotal"),
                            rs1.getDouble("payed"),
                            rs1.getDouble("Remained"),
                            rs1.getString("DayDate"),
                            rs1.getString("ArrivalDate"),
                            rs1.getString("ReceiverName"),
                            rs1.getString("Status"),
                            rs1.getString("casherName"),
                            rs1.getString("Note")
                        });
                    }
                } else if (txtSupplierNameSearch.getSelectedIndex() == 0 && txtReceiverNameSaerch.getSelectedIndex() != 0) {
                    DefaultTableModel dtm = (DefaultTableModel) jTableSalesSuppliers.getModel();
                    dtm.setRowCount(0);
                    ResultSet rs = dbOperations.getData("select sum(AllTotal) as AllTotal,sum(payed) as payed,sum(Remained) as Remained from salesSuppliers where "
                            + "ReceiverName='" + txtReceiverNameSaerch.getSelectedItem().toString() + "' and DayDate like'" + txtYearSearchSuppliers.getSelectedItem().toString()+ "_" + txtMonthSearchSuppliers.getSelectedItem().toString()+ "_______________%'");
                    while (rs.next()) {
                        txtMonthPriceTotal.setText(rs.getDouble("AllTotal") + "");
                        txtMonthPayedTotal.setText(rs.getDouble("payed") + "");//  It will be add payForSupplier table sum(AllPayed) where date like
                        txtTotalRemainedMonth.setText(rs.getDouble("Remained") + "");// It will be from payForSupplier table sum(remainedNow)
                    }
                    ResultSet rs1 = dbOperations.getData("select * from salesSuppliers where  "
                            + "ReceiverName='" + txtReceiverNameSaerch.getSelectedItem().toString() + "' and DayDate like'" + txtYearSearchSuppliers.getSelectedItem().toString()+ "_" + txtMonthSearchSuppliers.getSelectedItem().toString()+ "_______________%'");
                    while (rs1.next()) {
                        dtm.addRow(new Object[]{
                                             rs1.getLong("INID"),
                            rs1.getString("supplierName"),
                            rs1.getDouble("Total_Quantity"),
                            rs1.getDouble("AllTotal"),
                            rs1.getDouble("payed"),
                            rs1.getDouble("Remained"),
                            rs1.getString("DayDate"),
                            rs1.getString("ArrivalDate"),
                            rs1.getString("ReceiverName"),
                            rs1.getString("Status"),
                            rs1.getString("casherName"),
                            rs1.getString("Note")
                        });
                    }
                } else {
                    DefaultTableModel dtm = (DefaultTableModel) jTableSalesSuppliers.getModel();
                    dtm.setRowCount(0);
                    ResultSet rs = dbOperations.getData("select sum(AllTotal) as AllTotal,sum(payed) as payed,sum(Remained) as Remained from salesSuppliers where DayDate like'" +  txtYearSearchSuppliers.getSelectedItem().toString()+ "_" + txtMonthSearchSuppliers.getSelectedItem().toString()+ "_______________%'");
                    while (rs.next()) {
                        txtMonthPriceTotal.setText(rs.getDouble("AllTotal") + "");
                        txtMonthPayedTotal.setText(rs.getDouble("payed") + "");//  It will be add payForSupplier table sum(AllPayed) where date like
                        txtTotalRemainedMonth.setText(rs.getDouble("Remained") + "");// It will be from payForSupplier table sum(remainedNow)
                    }
                    ResultSet rs1 = dbOperations.getData("select * from salesSuppliers where DayDate like'" + txtYearSearchSuppliers.getSelectedItem().toString()+ "_" + txtMonthSearchSuppliers.getSelectedItem().toString()+ "_______________%'");
                    while (rs1.next()) {
                        dtm.addRow(new Object[]{
                                          rs1.getLong("INID"),
                            rs1.getString("supplierName"),
                            rs1.getDouble("Total_Quantity"),
                            rs1.getDouble("AllTotal"),
                            rs1.getDouble("payed"),
                            rs1.getDouble("Remained"),
                            rs1.getString("DayDate"),
                            rs1.getString("ArrivalDate"),
                            rs1.getString("ReceiverName"),
                            rs1.getString("Status"),
                            rs1.getString("casherName"),
                            rs1.getString("Note")
                        });
                    }
                }
            } else if (txtMonthSearchSuppliers.getSelectedIndex() == 0 && txtHandDateSaerchSuppliers.getDate() != null) {
                if (txtSupplierNameSearch.getSelectedIndex() != 0 && txtReceiverNameSaerch.getSelectedIndex() != 0) {
                    DefaultTableModel dtm = (DefaultTableModel) jTableSalesSuppliers.getModel();
                    dtm.setRowCount(0);
                    ResultSet rs = dbOperations.getData("select sum(AllTotal) as AllTotal,sum(payed) as payed,sum(Remained) as Remained from salesSuppliers where ReceiverName='" + txtReceiverNameSaerch.getSelectedItem().toString() + "' and "
                            + "supplierName='" + txtSupplierNameSearch.getSelectedItem().toString() + "' and DayDate like'" + df.format(txtHandDateSaerchSuppliers.getDate()) + "____________%'");
                    while (rs.next()) {
                        txtMonthPriceTotal.setText(rs.getDouble("AllTotal") + "");
                        txtMonthPayedTotal.setText(rs.getDouble("payed") + "");//  It will be add payForSupplier table sum(AllPayed) where date like
                        txtTotalRemainedMonth.setText(rs.getDouble("Remained") + "");// It will be from payForSupplier table sum(remainedNow)
                    }
                    ResultSet rs1 = dbOperations.getData("select * from salesSuppliers where ReceiverName='" + txtReceiverNameSaerch.getSelectedItem().toString() + "' and "
                            + "supplierName='" + txtSupplierNameSearch.getSelectedItem().toString() + "' and DayDate like'" + df.format(txtHandDateSaerchSuppliers.getDate()) + "____________%'");
                    while (rs1.next()) {
                        dtm.addRow(new Object[]{
                                             rs1.getLong("INID"),
                            rs1.getString("supplierName"),
                            rs1.getDouble("Total_Quantity"),
                            rs1.getDouble("AllTotal"),
                            rs1.getDouble("payed"),
                            rs1.getDouble("Remained"),
                            rs1.getString("DayDate"),
                            rs1.getString("ArrivalDate"),
                            rs1.getString("ReceiverName"),
                            rs1.getString("Status"),
                            rs1.getString("casherName"),
                            rs1.getString("Note")
                        });
                    }
                } else if (txtSupplierNameSearch.getSelectedIndex() == 0 && txtReceiverNameSaerch.getSelectedIndex() != 0) {
                    DefaultTableModel dtm = (DefaultTableModel) jTableSalesSuppliers.getModel();
                    dtm.setRowCount(0);
                    ResultSet rs = dbOperations.getData("select sum(AllTotal) as AllTotal,sum(payed) as payed,sum(Remained) as Remained from salesSuppliers where "
                            + "ReceiverName='" + txtReceiverNameSaerch.getSelectedItem().toString() + "' and DayDate like'" + df.format(txtHandDateSaerchSuppliers.getDate()) + "____________%'");
                    while (rs.next()) {
                        txtMonthPriceTotal.setText(rs.getDouble("AllTotal") + "");
                        txtMonthPayedTotal.setText(rs.getDouble("payed") + "");//  It will be add payForSupplier table sum(AllPayed) where date like
                        txtTotalRemainedMonth.setText(rs.getDouble("Remained") + "");// It will be from payForSupplier table sum(remainedNow)
                    }
                    ResultSet rs1 = dbOperations.getData("select * from salesSuppliers where  "
                            + "ReceiverName='" + txtReceiverNameSaerch.getSelectedItem().toString() + "' and DayDate like'" + df.format(txtHandDateSaerchSuppliers.getDate()) + "____________%'");
                    while (rs1.next()) {
                        dtm.addRow(new Object[]{
                                rs1.getLong("INID"),
                            rs1.getString("supplierName"),
                            rs1.getDouble("Total_Quantity"),
                            rs1.getDouble("AllTotal"),
                            rs1.getDouble("payed"),
                            rs1.getDouble("Remained"),
                            rs1.getString("DayDate"),
                            rs1.getString("ArrivalDate"),
                            rs1.getString("ReceiverName"),
                            rs1.getString("Status"),
                            rs1.getString("casherName"),
                            rs1.getString("Note")
                        });
                    }
                } else {
                    DefaultTableModel dtm = (DefaultTableModel) jTableSalesSuppliers.getModel();
                    dtm.setRowCount(0);
                    ResultSet rs = dbOperations.getData("select sum(AllTotal) as AllTotal,sum(payed) as payed,sum(Remained) as Remained from salesSuppliers where DayDate like'" + df.format(txtHandDateSaerchSuppliers.getDate()) + "____________%'");
                    while (rs.next()) {
                        txtMonthPriceTotal.setText(rs.getDouble("AllTotal") + "");
                        txtMonthPayedTotal.setText(rs.getDouble("payed") + "");//  It will be add payForSupplier table sum(AllPayed) where date like
                        txtTotalRemainedMonth.setText(rs.getDouble("Remained") + "");// It will be from payForSupplier table sum(remainedNow)
                    }
                    ResultSet rs1 = dbOperations.getData("select * from salesSuppliers where DayDate like'" + df.format(txtHandDateSaerchSuppliers.getDate()) + "____________%'");
                    while (rs1.next()) {
                        dtm.addRow(new Object[]{
                                        rs1.getLong("INID"),
                            rs1.getString("supplierName"),
                            rs1.getDouble("Total_Quantity"),
                            rs1.getDouble("AllTotal"),
                            rs1.getDouble("payed"),
                            rs1.getDouble("Remained"),
                            rs1.getString("DayDate"),
                            rs1.getString("ArrivalDate"),
                            rs1.getString("ReceiverName"),
                            rs1.getString("Status"),
                            rs1.getString("casherName"),
                            rs1.getString("Note")
                        });
                    }
                }
            } else {
                DefaultTableModel dtm = (DefaultTableModel) jTableSalesSuppliers.getModel();
                dtm.setRowCount(0);
                ResultSet rs = dbOperations.getData("select sum(AllTotal) as AllTotal,sum(payed) as payed,sum(Remained) as Remained from salesSuppliers where"
                        + " ReceiverName like'" + txtReceiverNameSaerch.getSelectedItem().toString() + "' and DayDate like'" + txtTodayDate.getText() + "____________%'");
                while (rs.next()) {
                    txtMonthPriceTotal.setText(rs.getDouble("AllTotal") + "");
                    txtMonthPayedTotal.setText(rs.getDouble("payed") + "");//  It will be add payForSupplier table sum(AllPayed) where date like
                    txtTotalRemainedMonth.setText(rs.getDouble("Remained") + "");// It will be from payForSupplier table sum(remainedNow)
                }
                ResultSet rs1 = dbOperations.getData("select * from salesSuppliers where DayDate like'" + txtTodayDate.getText() + "____________%' and ReceiverName='" + txtReceiverNameSaerch.getSelectedItem() + "'");
                while (rs1.next()) {
                    dtm.addRow(new Object[]{
                                   rs1.getLong("INID"),
                            rs1.getString("supplierName"),
                            rs1.getDouble("Total_Quantity"),
                            rs1.getDouble("AllTotal"),
                            rs1.getDouble("payed"),
                            rs1.getDouble("Remained"),
                            rs1.getString("DayDate"),
                            rs1.getString("ArrivalDate"),
                            rs1.getString("ReceiverName"),
                            rs1.getString("Status"),
                            rs1.getString("casherName"),
                            rs1.getString("Note")
                    });
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_txtReceiverNameSaerchActionPerformed

    private void txtMonthSearchSuppliersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMonthSearchSuppliersActionPerformed
        try {
            txtHandDateSaerchSuppliers.setDate(null);
            if (txtSupplierNameSearch.getSelectedIndex() != 0 && txtReceiverNameSaerch.getSelectedIndex() != 0) {
                DefaultTableModel dtm = (DefaultTableModel) jTableSalesSuppliers.getModel();
                dtm.setRowCount(0);
                ResultSet rs = dbOperations.getData("select sum(AllTotal) as AllTotal,sum(payed) as payed,sum(Remained) as Remained from salesSuppliers where ReceiverName='" + txtReceiverNameSaerch.getSelectedItem().toString() + "' and "
                        + "supplierName='" + txtSupplierNameSearch.getSelectedItem().toString() + "' and DayDate like'" + txtYearSearchSuppliers.getSelectedItem().toString() + "-" + txtMonthSearchSuppliers.getSelectedItem().toString() + "_______________%'");
                while (rs.next()) {
                    txtMonthPriceTotal.setText(rs.getDouble("AllTotal") + "");
                    txtMonthPayedTotal.setText(rs.getDouble("payed") + "");//  It will be add payForSupplier table sum(AllPayed) where date like
                    txtTotalRemainedMonth.setText(rs.getDouble("Remained") + "");// It will be from payForSupplier table sum(remainedNow)
                }
                ResultSet rs1 = dbOperations.getData("select * from salesSuppliers where ReceiverName='" + txtReceiverNameSaerch.getSelectedItem().toString() + "' and "
                        + "supplierName='" + txtSupplierNameSearch.getSelectedItem().toString() + "' and DayDate like'" + txtYearSearchSuppliers.getSelectedItem().toString() + "-" + txtMonthSearchSuppliers.getSelectedItem().toString() + "_______________%'");
                while (rs1.next()) {
                    dtm.addRow(new Object[]{
                        rs.getLong("INID"),
                        rs.getString("supplierName"),
                        rs.getDouble("Total_Quantity"),
                        rs.getDouble("AllTotal"),
                        rs.getDouble("payed"),
                        rs.getDouble("Remained"),
                        rs.getString("DayDate"),
                        rs.getString("ArrivalDate"),
                        rs.getString("ReceiverName"),
                        rs.getString("Status"),
                        rs.getString("casherName"),
                        rs.getString("Note")
                    });
                }
            } else if (txtSupplierNameSearch.getSelectedIndex() == 0 && txtReceiverNameSaerch.getSelectedIndex() != 0) {
                DefaultTableModel dtm = (DefaultTableModel) jTableSalesSuppliers.getModel();
                dtm.setRowCount(0);
                ResultSet rs = dbOperations.getData("select sum(AllTotal) as AllTotal,sum(payed) as payed,sum(Remained) as Remained from salesSuppliers where ReceiverName='" + txtReceiverNameSaerch.getSelectedItem().toString() + "' and "
                        + "DayDate like'" + txtYearSearchSuppliers.getSelectedItem().toString() + "-" + txtMonthSearchSuppliers.getSelectedItem().toString() + "_______________%'");
                while (rs.next()) {
                    txtMonthPriceTotal.setText(rs.getDouble("AllTotal") + "");
                    txtMonthPayedTotal.setText(rs.getDouble("payed") + "");//  It will be add payForSupplier table sum(AllPayed) where date like
                    txtTotalRemainedMonth.setText(rs.getDouble("Remained") + "");// It will be from payForSupplier table sum(remainedNow)
                }
                ResultSet rs1 = dbOperations.getData("select * from salesSuppliers where ReceiverName='" + txtReceiverNameSaerch.getSelectedItem().toString() + "' and "
                        + "DayDate like'" + txtYearSearchSuppliers.getSelectedItem().toString() + "-" + txtMonthSearchSuppliers.getSelectedItem().toString() + "_______________%'");
                while (rs1.next()) {
                    dtm.addRow(new Object[]{
                                        rs1.getLong("INID"),
                            rs1.getString("supplierName"),
                            rs1.getDouble("Total_Quantity"),
                            rs1.getDouble("AllTotal"),
                            rs1.getDouble("payed"),
                            rs1.getDouble("Remained"),
                            rs1.getString("DayDate"),
                            rs1.getString("ArrivalDate"),
                            rs1.getString("ReceiverName"),
                            rs1.getString("Status"),
                            rs1.getString("casherName"),
                            rs1.getString("Note")
                    });
                }
            } else if (txtSupplierNameSearch.getSelectedIndex() != 0 && txtReceiverNameSaerch.getSelectedIndex() == 0) {
                DefaultTableModel dtm = (DefaultTableModel) jTableSalesSuppliers.getModel();
                dtm.setRowCount(0);
                ResultSet rs = dbOperations.getData("select sum(AllTotal) as AllTotal,sum(payed) as payed,sum(Remained) as Remained from salesSuppliers where "
                        + "supplierName='" + txtSupplierNameSearch.getSelectedItem().toString() + "' and DayDate like'" + txtYearSearchSuppliers.getSelectedItem().toString() + "-" + txtMonthSearchSuppliers.getSelectedItem().toString() + "_______________%'");
                while (rs.next()) {
                    txtMonthPriceTotal.setText(rs.getDouble("AllTotal") + "");
                    txtMonthPayedTotal.setText(rs.getDouble("payed") + "");//  It will be add payForSupplier table sum(AllPayed) where date like
                    txtTotalRemainedMonth.setText(rs.getDouble("Remained") + "");// It will be from payForSupplier table sum(remainedNow)
                }
                ResultSet rs1 = dbOperations.getData("select * from salesSuppliers where  "
                        + "supplierName='" + txtSupplierNameSearch.getSelectedItem().toString() + "' and DayDate like'" + txtYearSearchSuppliers.getSelectedItem().toString() + "-" + txtMonthSearchSuppliers.getSelectedItem().toString() + "_______________%'");
                while (rs1.next()) {
                    dtm.addRow(new Object[]{
                               rs1.getLong("INID"),
                            rs1.getString("supplierName"),
                            rs1.getDouble("Total_Quantity"),
                            rs1.getDouble("AllTotal"),
                            rs1.getDouble("payed"),
                            rs1.getDouble("Remained"),
                            rs1.getString("DayDate"),
                            rs1.getString("ArrivalDate"),
                            rs1.getString("ReceiverName"),
                            rs1.getString("Status"),
                            rs1.getString("casherName"),
                            rs1.getString("Note")
                    });
                }
            } else {
                DefaultTableModel dtm = (DefaultTableModel) jTableSalesSuppliers.getModel();
                dtm.setRowCount(0);
                ResultSet rs = dbOperations.getData("select sum(AllTotal) as AllTotal,sum(payed) as payed,sum(Remained) as Remained from salesSuppliers where DayDate like'" + txtYearSearchSuppliers.getSelectedItem().toString() + "-" + txtMonthSearchSuppliers.getSelectedItem().toString() + "_______________%'");
                while (rs.next()) {
                    txtMonthPriceTotal.setText(rs.getDouble("AllTotal") + "");
                    txtMonthPayedTotal.setText(rs.getDouble("payed") + "");//  It will be add payForSupplier table sum(AllPayed) where date like
                    txtTotalRemainedMonth.setText(rs.getDouble("Remained") + "");// It will be from payForSupplier table sum(remainedNow)
                }
                ResultSet rs1 = dbOperations.getData("select * from salesSuppliers where DayDate like'" + txtYearSearchSuppliers.getSelectedItem().toString() + "-" + txtMonthSearchSuppliers.getSelectedItem().toString() + "_______________%'");
                while (rs1.next()) {
                    dtm.addRow(new Object[]{
                             rs1.getLong("INID"),
                            rs1.getString("supplierName"),
                            rs1.getDouble("Total_Quantity"),
                            rs1.getDouble("AllTotal"),
                            rs1.getDouble("payed"),
                            rs1.getDouble("Remained"),
                            rs1.getString("DayDate"),
                            rs1.getString("ArrivalDate"),
                            rs1.getString("ReceiverName"),
                            rs1.getString("Status"),
                            rs1.getString("casherName"),
                            rs1.getString("Note")
                    });
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_txtMonthSearchSuppliersActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            String date=df.format(txtHandDateSaerchSuppliers.getDate());
            txtMonthSearchSuppliers.setSelectedIndex(0);
            DefaultTableModel dtm = (DefaultTableModel) jTableSalesSuppliers.getModel();
            dtm.setRowCount(0);
           ResultSet rs1 = dbOperations.getData("select sum(AllTotal) as AllTotal,sum(payed) as payed,sum(Remained) as Remained from salesSuppliers where "
                        + "DayDate like'" + date + "____________%'");
                while (rs1.next()) {
                    txtDayPriceTotal.setText(rs1.getDouble("AllTotal") + "");
                    txtDayPayedTotal.setText(rs1.getDouble("payed") + "");//  It will be add payForSupplier table sum(AllPayed) where date like
                    txtDayRemained.setText(rs1.getDouble("Remained") + "");// It will be from payForSupplier table sum(remainedNow)
                }
            ResultSet rs = dbOperations.getData("select * from salesSuppliers where DayDate like'" + date + "____________%'");//2023-10-26 04:24:40 PM
            while (rs.next()) {
                dtm.addRow(new Object[]{
                    rs.getLong("INID"),
                    rs.getString("supplierName"),
                    rs.getDouble("Total_Quantity"),
                    rs.getDouble("AllTotal"),
                    rs.getDouble("payed"),
                    rs.getDouble("Remained"),
                    rs.getString("DayDate"),
                    rs.getString("ArrivalDate"),
                    rs.getString("ReceiverName"),
                    rs.getString("Status"),
                    rs.getString("casherName"),
                    rs.getString("Note")
                });
            
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtHandDateSaerchSuppliersPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtHandDateSaerchSuppliersPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHandDateSaerchSuppliersPropertyChange

    private void txtHandDateSaerchSuppliersFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtHandDateSaerchSuppliersFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHandDateSaerchSuppliersFocusGained

    private void txtSupplierNameSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSupplierNameSearchActionPerformed
        try {
            if (txtMonthSearchSuppliers.getSelectedIndex() != 0 && txtHandDateSaerchSuppliers.getDate() == null) {
                if (txtSupplierNameSearch.getSelectedIndex() != 0 && txtReceiverNameSaerch.getSelectedIndex() != 0) {
                    DefaultTableModel dtm = (DefaultTableModel) jTableSalesSuppliers.getModel();
                    dtm.setRowCount(0);
                    ResultSet rs = dbOperations.getData("select sum(AllTotal) as AllTotal,sum(payed) as payed,sum(Remained) as Remained from salesSuppliers where ReceiverName='" + txtReceiverNameSaerch.getSelectedItem().toString() + "' and "
                            + "supplierName='" + txtSupplierNameSearch.getSelectedItem().toString() + "' and DayDate like'" + txtYearSearchSuppliers.getSelectedItem().toString() + "-" + txtMonthSearchSuppliers.getSelectedItem().toString() + "_______________%'");
                    while (rs.next()) {
                        txtMonthPriceTotal.setText(rs.getDouble("AllTotal") + "");
                        txtMonthPayedTotal.setText(rs.getDouble("payed") + "");//  It will be add payForSupplier table sum(AllPayed) where date like
                        txtTotalRemainedMonth.setText(rs.getDouble("Remained") + "");// It will be from payForSupplier table sum(remainedNow)
                    }
                    ResultSet rs1 = dbOperations.getData("select * from salesSuppliers where ReceiverName='" + txtReceiverNameSaerch.getSelectedItem().toString() + "' and "
                            + "supplierName='" + txtSupplierNameSearch.getSelectedItem().toString() + "' and DayDate like'" + txtYearSearchSuppliers.getSelectedItem().toString() + "-" + txtMonthSearchSuppliers.getSelectedItem().toString() + "_______________%'");
                    while (rs1.next()) {
                        dtm.addRow(new Object[]{
                            rs.getLong("INID"),
                            rs.getString("supplierName"),
                            rs.getDouble("Total_Quantity"),
                            rs.getDouble("AllTotal"),
                            rs.getDouble("payed"),
                            rs.getDouble("Remained"),
                            rs.getString("DayDate"),
                            rs.getString("ArrivalDate"),
                            rs.getString("ReceiverName"),
                            rs.getString("Status"),
                            rs.getString("casherName"),
                            rs.getString("Note")
                        });
                    }
                } else if (txtSupplierNameSearch.getSelectedIndex() != 0 && txtReceiverNameSaerch.getSelectedIndex() == 0) {
                    DefaultTableModel dtm = (DefaultTableModel) jTableSalesSuppliers.getModel();
                    dtm.setRowCount(0);
                    ResultSet rs = dbOperations.getData("select sum(AllTotal) as AllTotal,sum(payed) as payed,sum(Remained) as Remained from salesSuppliers where "
                            + "supplierName='" + txtSupplierNameSearch.getSelectedItem().toString() + "' and DayDate like'" + txtYearSearchSuppliers.getSelectedItem().toString() + "-" + txtMonthSearchSuppliers.getSelectedItem().toString() + "_______________%'");
                    while (rs.next()) {
                        txtMonthPriceTotal.setText(rs.getDouble("AllTotal") + "");
                        txtMonthPayedTotal.setText(rs.getDouble("payed") + "");//  It will be add payForSupplier table sum(AllPayed) where date like
                        txtTotalRemainedMonth.setText(rs.getDouble("Remained") + "");// It will be from payForSupplier table sum(remainedNow)
                    }
                    ResultSet rs1 = dbOperations.getData("select * from salesSuppliers where  "
                            + "supplierName='" + txtSupplierNameSearch.getSelectedItem().toString() + "' and DayDate like'" + txtYearSearchSuppliers.getSelectedItem().toString() + "-" + txtMonthSearchSuppliers.getSelectedItem().toString() + "_______________%'");
                    while (rs1.next()) {
                        dtm.addRow(new Object[]{
                            rs1.getLong("INID"),
                            rs1.getString("supplierName"),
                            rs1.getDouble("Total_Quantity"),
                            rs1.getDouble("AllTotal"),
                            rs1.getDouble("payed"),
                            rs1.getDouble("Remained"),
                            rs1.getString("DayDate"),
                            rs1.getString("ArrivalDate"),
                            rs1.getString("ReceiverName"),
                            rs1.getString("Status"),
                            rs1.getString("casherName"),
                            rs1.getString("Note")
                        });
                    }
                } else {
                    DefaultTableModel dtm = (DefaultTableModel) jTableSalesSuppliers.getModel();
                    dtm.setRowCount(0);
                    ResultSet rs = dbOperations.getData("select sum(AllTotal) as AllTotal,sum(payed) as payed,sum(Remained) as Remained from salesSuppliers where DayDate like'" + txtYearSearchSuppliers.getSelectedItem().toString() + "-" + txtMonthSearchSuppliers.getSelectedItem().toString() + "_______________%'");
                    while (rs.next()) {
                        txtMonthPriceTotal.setText(rs.getDouble("AllTotal") + "");
                        txtMonthPayedTotal.setText(rs.getDouble("payed") + "");//  It will be add payForSupplier table sum(AllPayed) where date like
                        txtTotalRemainedMonth.setText(rs.getDouble("Remained") + "");// It will be from payForSupplier table sum(remainedNow)
                    }
                    ResultSet rs1 = dbOperations.getData("select * from salesSuppliers where supplierName='" + txtSupplierName.getSelectedItem() + "' and DayDate like'" + txtYearSearchSuppliers.getSelectedItem().toString() + "-" + txtMonthSearchSuppliers.getSelectedItem().toString() + "_______________%'");
                    while (rs1.next()) {
                        dtm.addRow(new Object[]{
                            rs1.getLong("INID"),
                            rs1.getString("supplierName"),
                            rs1.getDouble("Total_Quantity"),
                            rs1.getDouble("AllTotal"),
                            rs1.getDouble("payed"),
                            rs1.getDouble("Remained"),
                            rs1.getString("DayDate"),
                            rs1.getString("ArrivalDate"),
                            rs1.getString("ReceiverName"),
                            rs1.getString("Status"),
                            rs1.getString("casherName"),
                            rs1.getString("Note")
                        });
                    }
                }
            } else if (txtMonthSearchSuppliers.getSelectedIndex() == 0 && txtHandDateSaerchSuppliers.getDate() != null) {

                if (txtSupplierNameSearch.getSelectedIndex() != 0 && txtReceiverNameSaerch.getSelectedIndex() != 0) {
                    DefaultTableModel dtm = (DefaultTableModel) jTableSalesSuppliers.getModel();
                    dtm.setRowCount(0);
                    ResultSet rs = dbOperations.getData("select sum(AllTotal) as AllTotal,sum(payed) as payed,sum(Remained) as Remained from salesSuppliers where ReceiverName='" + txtReceiverNameSaerch.getSelectedItem().toString() + "' and "
                            + "supplierName='" + txtSupplierNameSearch.getSelectedItem().toString() + "' and DayDate like'" + df.format(txtHandDateSaerchSuppliers.getDate()) + "____________%'");
                    while (rs.next()) {
                        txtDayPriceTotal.setText(rs.getDouble("AllTotal") + "");
                        txtDayPayedTotal.setText(rs.getDouble("payed") + "");//  It will be add payForSupplier table sum(AllPayed) where date like
                        txtDayRemained.setText(rs.getDouble("Remained") + "");// It will be from payForSupplier table sum(remainedNow)
                    }
                    ResultSet rs1 = dbOperations.getData("select * from salesSuppliers where ReceiverName='" + txtReceiverNameSaerch.getSelectedItem().toString() + "' and "
                            + "supplierName='" + txtSupplierNameSearch.getSelectedItem().toString() + "' and DayDate like'" + df.format(txtHandDateSaerchSuppliers.getDate()) + "____________%'");
                    while (rs1.next()) {
                        dtm.addRow(new Object[]{
                            rs1.getLong("INID"),
                            rs1.getString("supplierName"),
                            rs1.getDouble("Total_Quantity"),
                            rs1.getDouble("AllTotal"),
                            rs1.getDouble("payed"),
                            rs1.getDouble("Remained"),
                            rs1.getString("DayDate"),
                            rs1.getString("ArrivalDate"),
                            rs1.getString("ReceiverName"),
                            rs1.getString("Status"),
                            rs1.getString("casherName"),
                            rs1.getString("Note")
                        });
                    }
                } else if (txtSupplierNameSearch.getSelectedIndex() != 0 && txtReceiverNameSaerch.getSelectedIndex() == 0) {
                    DefaultTableModel dtm = (DefaultTableModel) jTableSalesSuppliers.getModel();
                    dtm.setRowCount(0);
                    ResultSet rs = dbOperations.getData("select sum(AllTotal) as AllTotal,sum(payed) as payed,sum(Remained) as Remained from salesSuppliers where "
                            + "supplierName='" + txtSupplierNameSearch.getSelectedItem().toString() + "' and DayDate like'" + df.format(txtHandDateSaerchSuppliers.getDate()) + "____________%'");
                    while (rs.next()) {
                        txtDayPriceTotal.setText(rs.getDouble("AllTotal") + "");
                        txtDayPayedTotal.setText(rs.getDouble("payed") + "");//  It will be add payForSupplier table sum(AllPayed) where date like
                        txtDayRemained.setText(rs.getDouble("Remained") + "");// It will be from payForSupplier table sum(remainedNow)
                    }
                    ResultSet rs1 = dbOperations.getData("select * from salesSuppliers where  "
                            + "supplierName='" + txtSupplierNameSearch.getSelectedItem().toString() + "' and DayDate like'" + df.format(txtHandDateSaerchSuppliers.getDate()) + "____________%'");
                    while (rs1.next()) {
                        dtm.addRow(new Object[]{
                            rs1.getLong("INID"),
                            rs1.getString("supplierName"),
                            rs1.getDouble("Total_Quantity"),
                            rs1.getDouble("AllTotal"),
                            rs1.getDouble("payed"),
                            rs1.getDouble("Remained"),
                            rs1.getString("DayDate"),
                            rs1.getString("ArrivalDate"),
                            rs1.getString("ReceiverName"),
                            rs1.getString("Status"),
                            rs1.getString("casherName"),
                            rs1.getString("Note")
                        });
                    }
                } else {
                    DefaultTableModel dtm = (DefaultTableModel) jTableSalesSuppliers.getModel();
                    dtm.setRowCount(0);
                    ResultSet rs = dbOperations.getData("select sum(AllTotal) as AllTotal,sum(payed) as payed,sum(Remained) as Remained from salesSuppliers where "
                            + "supplierName='" + txtSupplierNameSearch.getSelectedItem().toString() + "' and DayDate like'" + df.format(txtHandDateSaerchSuppliers.getDate()) + "____________%'");
                    while (rs.next()) {
                        txtDayPriceTotal.setText(rs.getDouble("AllTotal") + "");
                        txtDayPayedTotal.setText(rs.getDouble("payed") + "");//  It will be add payForSupplier table sum(AllPayed) where date like
                        txtDayRemained.setText(rs.getDouble("Remained") + "");// It will be from payForSupplier table sum(remainedNow)
                    }
                    ResultSet rs1 = dbOperations.getData("select * from salesSuppliers where supplierName='" + txtSupplierNameSearch.getSelectedItem() + "' and DayDate like='" + df.format(txtHandDateSaerchSuppliers.getDate()) + "____________%'");
                    while (rs1.next()) {
                        dtm.addRow(new Object[]{
                            rs1.getLong("INID"),
                            rs1.getString("supplierName"),
                            rs1.getDouble("Total_Quantity"),
                            rs1.getDouble("AllTotal"),
                            rs1.getDouble("payed"),
                            rs1.getDouble("Remained"),
                            rs1.getString("DayDate"),
                            rs1.getString("ArrivalDate"),
                            rs1.getString("ReceiverName"),
                            rs1.getString("Status"),
                            rs1.getString("casherName"),
                            rs1.getString("Note")
                        });
                    }
                }
            } else {
                DefaultTableModel dtm = (DefaultTableModel) jTableSalesSuppliers.getModel();
                dtm.setRowCount(0);
                ResultSet rs = dbOperations.getData("select sum(AllTotal) as AllTotal,sum(payed) as payed,sum(Remained) as Remained from salesSuppliers where"
                        + " supplierName ='" + txtSupplierNameSearch.getSelectedItem().toString() + "' and DayDate like'" + txtTodayDate.getText() + "____________%'");
                while (rs.next()) {
                    txtDayPriceTotal.setText(rs.getDouble("AllTotal") + "");
                    txtDayPayedTotal.setText(rs.getDouble("payed") + "");//  It will be add payForSupplier table sum(AllPayed) where date like
                    txtDayRemained.setText(rs.getDouble("Remained") + "");// It will be from payForSupplier table sum(remainedNow)
                }
                ResultSet rs1 = dbOperations.getData("select * from salesSuppliers where DayDate like'" + txtTodayDate.getText() + "____________%' and supplierName='" + txtSupplierNameSearch.getSelectedItem() + "'");
                while (rs1.next()) {
                    dtm.addRow(new Object[]{
                        rs1.getLong("INID"),
                        rs1.getString("supplierName"),
                        rs1.getDouble("Total_Quantity"),
                        rs1.getDouble("AllTotal"),
                        rs1.getDouble("payed"),
                        rs1.getDouble("Remained"),
                        rs1.getString("DayDate"),
                        rs1.getString("ArrivalDate"),
                        rs1.getString("ReceiverName"),
                        rs1.getString("Status"),
                        rs1.getString("casherName"),
                        rs1.getString("Note")
                    });
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_txtSupplierNameSearchActionPerformed

    private void txtINIDKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtINIDKeyReleased
        try {
            int a = Integer.parseInt(txtINID.getText());
            DefaultTableModel dtm = (DefaultTableModel) jTableSalesSuppliers.getModel();
            dtm.setRowCount(0);
            ResultSet rs = dbOperations.getData("select * from salessuppliers where INID=" + txtINID.getText());
            while (rs.next()) {
                dtm.addRow(new Object[]{
                    rs.getLong("INID"),
                    rs.getString("supplierName"),
                    rs.getDouble("Total_Quantity"),
                    rs.getDouble("AllTotal"),
                    rs.getDouble("payed"),
                    rs.getDouble("Remained"),
                    rs.getString("DayDate"),
                    rs.getString("ArrivalDate"),
                    rs.getString("ReceiverName"),
                    rs.getString("Status"),
                    rs.getString("casherName"),
                    rs.getString("Note")
                });
            }
        } catch (NumberFormatException ex) {
            loadSupplierOperationsData();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_txtINIDKeyReleased

    private void txtSupplierPhoneKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSupplierPhoneKeyReleased
        try {
            ResultSet rs = dbOperations.getData("select * from suppliers where phone='" + txtSupplierPhone.getText() + "'");
            while (rs.next()) {
                txtSupplierName.setSelectedItem(rs.getString("supplierName"));
                txtCompanyAddress.setText(rs.getString("Address"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_txtSupplierPhoneKeyReleased

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try {
            int a = JOptionPane.showConfirmDialog(null, "سيتم حذف الجدول!", "تحذير", JOptionPane.YES_NO_OPTION);
            if (a == 0) {
                DefaultTableModel dtm = (DefaultTableModel) jTableAddForCaty.getModel();
                dtm.setRowCount(0);
                txtPriceOfAll1.setText("0.0");
                txtPayedValue.setText("0.0");
                txtRemained.setText("0.0");
                txtTotalQuantity.setText("0.0");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    public void getAlltotalWhenDeleteRow() {
        double total = 0;
        double totalQuantity = 0;
        try {
            for (int i = 0; i < jTableAddForCaty.getRowCount(); i++) {
                total += Double.valueOf(jTableAddForCaty.getValueAt(i, 6).toString());
                totalQuantity += Double.valueOf(jTableAddForCaty.getValueAt(i, 4).toString());
            }
            txtPriceOfAll1.setText("" + total);
            txtTotalQuantity.setText("" + totalQuantity);
            if (total > Double.valueOf(txtPayedValue.getText())) {
                txtRemained.setText("" + (total - Double.valueOf(txtPayedValue.getText())));
            } else {
                int a = JOptionPane.showConfirmDialog(null, "<html><h1>المدفوع اكثر من التوتال !!</h1></html>", "", JOptionPane.WARNING_MESSAGE);
                txtRemained.setText("" + total);
            }
          remainedForOperation=Double.valueOf(txtRemained.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void jTableAddForCatyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableAddForCatyMouseClicked
        int index = jTableAddForCaty.getSelectedRow();
        String productname;
        String barCode;
        String companyBarcode;
        String quantityNeeded;
        double productPrice;
        String TotalPrice;
        String storeAddress = "الفيوم شارع مدرسة الفنية بجوار سوبر ماركت غلاب";
        String INid = txtINIDOfGetProductsInvoice.getText();
         DefaultTableModel dtm = (DefaultTableModel) jTableAddForCaty.getModel();
        try {
            int s=JOptionPane.showConfirmDialog(null, "<html><h1>تعديل الكمية؟</h1></html>", "اختر", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if(s==0)
            {
              String g=JOptionPane.showInputDialog(null, "<html><h1>الكمية الجديدة</h1></html>");
              dtm.setValueAt(Double.valueOf(g),index, 4);
              dtm.setValueAt((Double.valueOf(g)*Double.valueOf(dtm.getValueAt(index,5).toString())),index,6);
              getAlltotalWhenDeleteRow();
            }else 
            {
                            int a = JOptionPane.showConfirmDialog(null, "<html><h1>هل تريد الحذف؟</h1></html>", "اختر", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (a == 0) {
                if (!txtPurchaseNumberSearch.getText().equals("")) {
                    if(txtStatusSearchPurchase.getText().contains("تم الاستلام"))
                    {
                        ResultSet rs = dbOperations.getData("select * from suppliers where supplierName='" + txtSupplierName.getSelectedItem().toString() + "'");
                    if (rs.next()) {
                        dbOperations.setDataOrDelete("update suppliers set Remained=" + (rs.getDouble("Remained") - Double.valueOf(dtm.getValueAt(index, 6).toString())) + " where supplierName='" + txtSupplierName.getSelectedItem().toString() + "'", "تم الجذف");
                    }
                    }
                    dtm.removeRow(index);
                }
                getAlltotalWhenDeleteRow();
            } else {
                if (txtPurchaseNumberSearch.getText().length()!=0) {
                    int b = JOptionPane.showConfirmDialog(null, "<html><h1>لم يتم الاستلام اصدار طلب جديد؟</h1></html>", "اختر", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                    if (b == 0) {
                        productname = dtm.getValueAt(index, 1).toString();
                        barCode = dtm.getValueAt(index, 2).toString();
                        companyBarcode = dtm.getValueAt(index, 3).toString();
                        quantityNeeded = dtm.getValueAt(index, 4).toString();
                        productPrice = Double.parseDouble(dtm.getValueAt(index, 5).toString());
                        TotalPrice = dtm.getValueAt(index, 6).toString();
                      if(txtHandDateArrival.getDate()!=null)
                      {
                            dbOperations.setDataOrDelete("Insert into cartSuppliers("
                                + "INID,"
                                + "productname,"
                                + "BarCode,"
                                + "companyBarCode,"
                                + "quantityneeded,"
                                + "Unit_Price,"
                                + "Total_Price,"
                                + "date) values("
                                + INid + ",'"
                                + productname
                                + "','" + barCode
                                + "','" + companyBarcode
                                + "'," + quantityNeeded
                                + "," + productPrice
                                + "," + TotalPrice
                                + ",'" + txtPurchaseDate.getText() + "')", "");
                      }else
                      {
                          JOptionPane.showMessageDialog(null,"<html><h1>يجب تحديد تاريخ الوصول</h1></html>");
                           return;
                      }

                        ResultSet rs = dbOperations.getData("select sum(Total_Price) as AllTotal,sum(quantityneeded) as Total_Quantity from cartSuppliers where INID=" + INid);
                        if (rs.next()) {
                            try {
                                ResultSet rs1 = dbOperations.getData("select * from salesSuppliers where INID=" + INid);
                                if (rs1.next()) {
                                    dbOperations.setDataOrDelete("update salesSuppliers set "
                                            + "Total_Quantity=" + rs.getDouble("Total_Quantity")
                                            + ",AllTotal=" + rs.getDouble("AllTotal") + ",Remained=" +(rs.getDouble("AllTotal")) + " where INID=" + INid, "");
                                } else {
                                        if(txtHandDateArrival.getDate()!=null)
                                        {                
                                    dbOperations.setDataOrDelete("insert into salesSuppliers ("
                                            + "INID,"
                                            + "supplierName,"
                                            + "Total_Quantity,"
                                            + "AllTotal,"
                                            + "payed,"
                                            + "Status,"
                                            + "Remained,"
                                            + "casherName,"
                                            + "DayDate,"
                                            + "ReceiverName,"
                                            + "Note,"
                                            + "ArrivalDate,"
                                            + "companyAddress,"
                                            + "storeAddress) values("
                                            + INid
                                            + ",'" + txtSupplierName.getSelectedItem().toString()
                                            + "'," + rs.getDouble("Total_Quantity")
                                            + "," + rs.getDouble("AllTotal")
                                            + "," + 0.0
                                            + ",'" + "قيد الطلب فقط"
                                            + "'," + rs.getDouble("AllTotal")
                                            + ",'" + txtCasherName.getText()
                                            + "','" + txtSearchPurchaseDate.getText()
                                            + "','" + txtReceiverNameForCart.getSelectedItem().toString()
                                            + "','" + txtsupplierNotes.getText()
                                            + "','" + df.format(txtHandDateArrival.getDate())
                                            + "','" + txtCompanyAddress.getText()
                                            + "','" + storeAddress + "')", "");
                                        }else 
                                        {
                                            JOptionPane.showMessageDialog(null,"<html><h1>يجب تحديد تاريخ الوصول</h1></html>");
                                            return;
                                        }
                                        dtm.removeRow(index);
                                        getAlltotalWhenDeleteRow();
                                }
                                 
                            } catch (NumberFormatException ex) {
                                JOptionPane.showMessageDialog(null, ex);
                            } catch (Exception e) {
                                JOptionPane.showMessageDialog(null, e+" احا");
                            }
                            try {
                                new barcodeGenerator(INid).generate();
                                HashMap para = new HashMap();
                                para.put("Inv_para", Integer.parseInt(INid));  // inv_id  is ireport parameter name
                                para.put("Inv_Type", "فاتورة طلب شراء:");
                                ReportView r = new ReportView("src\\reports\\suppliersReport.jasper", para);
                                r.view();
                            } catch (Exception e) {
                                JOptionPane.showMessageDialog(null, e);
                            }
                            try {
                                new barcodeGenerator(INid).generate();
                                HashMap para = new HashMap();
                                para.put("Inv_para", Integer.parseInt(INid));  // inv_id  is ireport parameter name
                                ReportView r = new ReportView("src\\reports\\purchaseLargeReport.jasper", para);
                                r.view();
                            } catch (Exception e) {
                                JOptionPane.showMessageDialog(null, e);
                            }
                        }
                    }
                }
            }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jTableAddForCatyMouseClicked

    private void btnAddToCartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddToCartActionPerformed
        btnAddToCart.setEnabled(false);
        if (txtPurchaseNumberSearch.getText().length() == 0) {
            btnPrint.setEnabled(true);
        }
        boolean state = true;
        int productId = 0;
        double QuantityOfOne = 0;
        double AvailableQty = 0;
        double Taked = 0;
        String AfterPoint = "";
        try {
            Vector v = new Vector();
            DefaultTableModel dtm = (DefaultTableModel) jTableAddForCaty.getModel();
            if (txtproductTypeNameSuppliers.getSelectedIndex() == 0) {
                for (int i = 0; i < jTableAddForCaty.getRowCount(); i++) {
                    if (String.valueOf(jTableAddForCaty.getValueAt(i, 1)).equals(txtProductNameLable.getText())) {
                        state = false;
                        jTableAddForCaty.setValueAt(Double.valueOf(jTableAddForCaty.getValueAt(i, 4).toString()) + Double.valueOf(txtQuantitySingle.getText()), i, 4);
                        jTableAddForCaty.setValueAt(Double.valueOf(jTableAddForCaty.getValueAt(i, 6).toString()) + Double.valueOf(txtFirstPriceSuppliers.getText()), i, 6);
                    }
                }
            } else {
                for (int i = 0; i < jTableAddForCaty.getRowCount(); i++) {
                    if (String.valueOf(jTableAddForCaty.getValueAt(i, 1)).equals(txtProductNameLable.getText() + "_" + txtproductTypeNameSuppliers.getSelectedItem())) {
                        state = false;
                        jTableAddForCaty.setValueAt(Double.valueOf(jTableAddForCaty.getValueAt(i, 4).toString()) + Double.valueOf(txtQuantitySingle.getText()), i, 4);
                        jTableAddForCaty.setValueAt(Double.valueOf(jTableAddForCaty.getValueAt(i, 6).toString()) + Double.valueOf(txtFirstPriceSuppliers.getText()), i, 6);
                    }
                }
            }
            if (state) {
                if (!txtPurchaseNumberSearch.getText().equals("")) {
                    v.add(txtPurchaseNumberSearch.getText());
                } else {
                    v.add(txtINIDOfGetProductsInvoice.getText());
                }
                if (txtproductTypeNameSuppliers.getSelectedIndex() != 0) {
                    v.add(txtProductNameLable.getText() + "_" + txtproductTypeNameSuppliers.getSelectedItem());
                } else {
                    v.add(txtProductNameLable.getText());
                }
                v.add(txtBarcodeProduct.getText());
                v.add(txtBarcodeProductCompany.getText());
                v.add(txtQuantitySingle.getText());
                v.add(txtPriceOfOne.getText());
                v.add(txtFirstPriceSuppliers.getText());
                dtm.addRow(v);
            }
            getTotalFromTable();
            setTotalQuantity();
            txtproductTypeNameSuppliers.setSelectedIndex(0);
            txtProductNameLable.setText("");
            txtAvailableFromProductOnSupplier.setText("0.0");
            txtTakedFromProductsOnSupplier.setText("0.0");
            txtPriceOfOne.setText("");
            txtFirstPriceSuppliers.setText("0.0");
            txtQuantitySingle.setText("");
            txtBarcodeProduct.setText("");
            txtQuantityOfOneValue1.setText("");
            txtQuantityOfOneVariable1.setText("__");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }//GEN-LAST:event_btnAddToCartActionPerformed

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        btnPrint.setEnabled(false);
        long INid = 0;
        String Status = "";
        String storeAddress = "الفيوم شارع مدرسة الفنية بجوار سوبر ماركت غلاب";
        if (txtHandDateArrival.getDate() != null) {
            try {
                String productname;
                String barCode;
                String companyBarcode;
                String quantityNeeded;
                double productPrice;
                String TotalPrice;
                int len = jTableAddForCaty.getRowCount();
                DefaultTableModel dtm = (DefaultTableModel) jTableAddForCaty.getModel();
                for (int i = 0; i < len; i++) {
                    INid = Long.parseLong(dtm.getValueAt(i, 0).toString());
                    productname = dtm.getValueAt(i, 1).toString();
                    barCode = dtm.getValueAt(i, 2).toString();
                    companyBarcode = dtm.getValueAt(i, 3).toString();
                    quantityNeeded = dtm.getValueAt(i, 4).toString();
                    productPrice = Double.parseDouble(dtm.getValueAt(i, 5).toString());
                    TotalPrice = dtm.getValueAt(i, 6).toString();
                    dbOperations.setDataOrDelete("Insert into cartSuppliers("
                            + "INID,"
                            + "productname,"
                            + "BarCode,"
                            + "companyBarCode,"
                            + "quantityneeded,"
                            + "Unit_Price,"
                            + "Total_Price,"
                            + "date) values("
                            + INid + ",'"
                            + productname
                            + "','" + barCode
                            + "','" + companyBarcode
                            + "'," + quantityNeeded
                            + "," + productPrice
                            + "," + TotalPrice
                            + ",'" + txtPurchaseDate.getText() + "')", "");
//                if (productname.contains("_")) {
//                    ResultSet rs11 = dbOperations.getData("select * from productsTypes where productTypeName='" + productname + "'");
//                    if (rs11.next()) {
//                        dbOperations.setDataOrDelete("update productsTypes set "
//                                + "ِAvailableQtyStore=" + (rs11.getDouble("ِAvailableQtyStore") + Double.valueOf(quantityNeeded)) + " where productTypeName='" + productname + "'", "");
//                        dbOperations.setDataOrDelete("update products set "
//                                + "ِAvailableQtyStore=" + (rs11.getDouble("QuantityOfOne") * (Double.valueOf(quantityNeeded) + rs11.getDouble("ِAvailableQtyStore"))) + " where productId=" + rs11.getLong("productId"), "");
//                    }
//                } else {
//                    ResultSet rs = dbOperations.getData("select * from products where productId=" + barCode);
//                    if (rs.next()) {
//                        dbOperations.setDataOrDelete("update products set priceOfBuy=" + txtPriceOfOne.getText()
//                                + "ِAvailableQtyStore=" + (Double.valueOf(quantityNeeded) + rs.getDouble("ِAvailableQtyStore")) + " where productId=" + barCode, "");
//                        ResultSet rs2 = dbOperations.getData("select * from productsTypes where productId=" + barCode);
//                        while (rs2.next()) {
//                            AfterPoint = "" + ((Double.valueOf(quantityNeeded) + rs.getDouble("ِAvailableQtyStore")) / rs2.getDouble("QuantityOfOne"));
//                            if (AfterPoint.substring(AfterPoint.indexOf(".") + 1, AfterPoint.length()).equals("0")) {
//                                dbOperations.setDataOrDelete("update productsTypes set ِAvailableQtyStore=" + AfterPoint + " where id=" + rs2.getLong("id"), "");
//                            }
//
//                        }
//                    }
//                }
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }

            try {
                String id = txtINIDOfGetProductsInvoice.getText();
                dbOperations.setDataOrDelete("Update extra set val='" + id + "' where exid=2", "");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
            double finaltotal = Double.parseDouble(txtPriceOfAll1.getText());
            double finalRemained = Double.parseDouble(txtRemained.getText());

            if (finalRemained == 0.0) {
                Status = "قيد الطلب وتم الدفع";
            } else if (finalRemained < finaltotal) {
                Status = "قيد الطلب وتم دفع جزء";
            } else if (finaltotal == finalRemained) {
                Status = "قيد الطلب فقط";
            }
            try {
                dbOperations.setDataOrDelete("insert into salesSuppliers ("
                        + "INID,"
                        + "supplierName,"
                        + "Total_Quantity,"
                        + "AllTotal,"
                        + "payed,"
                        + "Status,"
                        + "Remained,"
                        + "casherName,"
                        + "DayDate,"
                        + "ReceiverName,"
                        + "Note,"
                        + "ArrivalDate,"
                        + "companyAddress,"
                        + "storeAddress) values("
                        + INid
                        + ",'" + txtSupplierName.getSelectedItem().toString()
                        + "'," + txtTotalQuantity.getText()
                        + "," + txtPriceOfAll1.getText()
                        + "," + txtPayedValue.getText()
                        + ",'" + Status
                        + "'," + txtRemained.getText()
                        + ",'" + txtCasherName.getText()
                        + "','" + txtPurchaseDate.getText()
                        + "','" + txtReceiverNameForCart.getSelectedItem().toString()
                        + "','" + txtsupplierNotes.getText()
                        + "','" + df.format(txtHandDateArrival.getDate())
                        + "','" + txtCompanyAddress.getText() + "','" + storeAddress + "')", "");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, ex);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }

//        if (Status.equals("قيد الطلب وتم دفع جزء") || Status.equals("قيد الطلب وتم الدفع")) {
            try {

                new barcodeGenerator(txtINIDOfGetProductsInvoice.getText()).generate();
                HashMap para = new HashMap();
                para.put("Inv_para", Integer.parseInt(txtINIDOfGetProductsInvoice.getText()));  // inv_id  is ireport parameter name
                para.put("Inv_Type", "فاتورة طلب شراء:");
                ReportView r = new ReportView("src\\reports\\suppliersReport.jasper", para);
                r.view();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
            try {
                new barcodeGenerator(txtINIDOfGetProductsInvoice.getText()).generate();
                HashMap para = new HashMap();
                para.put("Inv_para", Integer.parseInt(txtINIDOfGetProductsInvoice.getText()));  // inv_id  is ireport parameter name
                ReportView r = new ReportView("src\\reports\\purchaseLargeReport.jasper", para);
                r.view();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
//        } else {
//                  try {
//                new barcodeGenerator(txtINIDOfGetProductsInvoice.getText()).generate();
//                HashMap para = new HashMap();
//                para.put("inv_para", Integer.parseInt(txtINIDOfGetProductsInvoice.getText()));  // inv_id  is ireport parameter name
//                para.put("Inv_Type", "فاتورة طلب شراء:");
//                para.put("Inv_casher", txtCasherName.getText());
//                ReportView r = new ReportView("src\\reports\\suppliersReport.jasper", para);
//                r.view();
//            } catch (Exception e) {
//                JOptionPane.showMessageDialog(null, e);
//            }
//        }
//          if(!txtPayedValue.getText().equals("0.0"))
//          {
//                try {
//                ResultSet rs3 = dbOperations.getData("select * from suppliers where supplierName='" + txtSupplierName.getSelectedItem() + "'");
//                if (rs3.next()) {
//                    dbOperations.setDataOrDelete("update suppliers set Remained=" + (rs3.getDouble("Remained") + Double.valueOf(txtRemained.getText())) + " where supplierName='" + txtSupplierName.getSelectedItem() + "'", "");
//                }
//            } catch (Exception e) {
//                JOptionPane.showMessageDialog(null, e);
//            }
//          }
            //        try {
//            new barcodeGenerator(txtINIDOfGetProductsInvoice.getText()).generate();
//            HashMap para = new HashMap();
//            para.put("inv_para", Integer.parseInt(txtINIDOfGetProductsInvoice.getText()));  // inv_id  is ireport parameter name
//            para.put("Inv_Type", "فاتورة استلام:");
//            para.put("Inv_casher", txtCasherName.getText());
//            ReportView r = new ReportView("src\\reports\\suppliersReport.jasper", para);
//            r.view();
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, e);
//        }
            if (!txtPayedValue.getText().equals("0.0")) {
                int a = JOptionPane.showConfirmDialog(null, "<html><h1>هل تريد سحب القيمة المدفوعة من الخزنة؟</h1></html>", "", JOptionPane.YES_NO_OPTION);
                if(a==0){
    
                    try {
                        ResultSet rs = dbOperations.getData("select * from capenat where id=(select max(id) from capenat)");
                        if (rs.next()) {
                            dbOperations.setDataOrDelete("insert into capenat (realValue,editedvalue,afterEdictvalue,OperationType,date) "
                                    + "values(" + rs.getDouble("afterEdictvalue") + "," + txtPayedValue.getText() + "," + (rs.getDouble("afterEdictvalue") - Double.valueOf(txtPayedValue.getText())) + ",'سحب طلب منتج','" + txtPurchaseDate.getText() + "')", "");
                            }
                    
                        
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, e);
                    }
                         int b = JOptionPane.showConfirmDialog(null, "<html><h1>هل تريد تسجيل القيمة المدفوعة الي المورد تأكيد استلام نقدية ؟</h1></html>", "", JOptionPane.YES_NO_OPTION);
                            if (b == 0) {
                                dbOperations.setDataOrDelete("insert into payoperationforsuppliers (supplierName,receiverName,remainedBefor,payed,remainedAfter,operationdetails,date,purchaseNumber) "
                                        + "values('"
                                        + txtSupplierName.getSelectedItem().toString()
                                        + "','" + txtReceiverNameForCart.getSelectedItem().toString()
                                        + "'," + txtPriceOfAll1.getText()
                                        + "," + txtPayedValue.getText()
                                        + "," + Double.valueOf(txtRemained.getText())
                                        + ",'" + "دفع طلب منتجات : " + " ورقم الطلب: " + txtINIDOfGetProductsInvoice.getText()
                                        + "','" + txtPurchaseDate.getText() + "'," + txtINIDOfGetProductsInvoice.getText() + ")", "");
                                try {
                                             ResultSet rsssd = dbOperations.getData("select * from payoperationforsuppliers where supplierName='" + txtSupplierName.getSelectedItem().toString() + "' order by id Desc limit 1");
                                if (rsssd.next()) {
                                    HashMap para = new HashMap();
                                    para.put("Inv_para", rsssd.getLong("id"));  // inv_id  is ireport parameter name
                                    ReportView r = new ReportView("src\\reports\\paySuppliersArrival.jasper", para);
                                    r.view();
                                }
                                } catch (Exception e) {
                                    JOptionPane.showMessageDialog(null, e);
                                }
                                }
                }
            }
            DefaultTableModel dtm = (DefaultTableModel) jTableAddForCaty.getModel();
            dtm.setRowCount(0);
            txtPriceOfAll1.setText("0.0");
            txtPayedValue.setText("0.0");
            txtRemained.setText("0.0");
            txtTotalQuantity.setText("0.0");
            txtINIDOfGetProductsInvoice.setText(String.valueOf(Integer.valueOf(txtINIDOfGetProductsInvoice.getText()) + 1));
            loadSupplierOperationsData();
        } else {
            int a = JOptionPane.showConfirmDialog(null, "<h1>يجب تحديد تاريخ الوصول</h1>", "", JOptionPane.WARNING_MESSAGE);
        }
        btnPrint.setEnabled(true);
    }//GEN-LAST:event_btnPrintActionPerformed

    private void txtPriceOfOneKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPriceOfOneKeyReleased
       
    }//GEN-LAST:event_txtPriceOfOneKeyReleased

    private void txtPayedValueKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPayedValueKeyReleased
        double Total = Total = Double.parseDouble(txtPriceOfAll1.getText());
        try {
            double Payed = Double.parseDouble(txtPayedValue.getText());
                    if (Payed <= Total) {
                        txtPayedValue.setForeground(new Color(0, 255, 0));
                        txtRemained.setText(String.valueOf(Total - Payed));
                        if(txtPurchaseNumberSearch.getText().length()==0)
                        {
                            btnPrint.setEnabled(true);
                            btnArrivalApproval.setEnabled(false);
                        }else 
                        {
                            btnPrint.setEnabled(false);
                            btnArrivalApproval.setEnabled(true);
                        }
                    } else {
                        txtPayedValue.setForeground(new Color(255, 0, 0));
                        txtRemained.setText(String.valueOf(Total));
                        btnPrint.setEnabled(false);
                        btnArrivalApproval.setEnabled(false);
                    }
        } catch (NumberFormatException es) {
            txtPayedValue.setForeground(new Color(255, 0, 0));
            txtRemained.setText(Total+"");
            btnPrint.setEnabled(false);
            btnArrivalApproval.setEnabled(false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_txtPayedValueKeyReleased

    private void txtPayedValueFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPayedValueFocusLost
        if (txtPayedValue.getText().equals("")) {
            txtPayedValue.setText("0.0");
        }
    }//GEN-LAST:event_txtPayedValueFocusLost

    private void txtPayedValueFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPayedValueFocusGained

        if (txtPayedValue.getText().equals("0.0")) {
            txtPayedValue.setText("");
        }
    }//GEN-LAST:event_txtPayedValueFocusGained

    private void txtproductTypeNameSuppliersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtproductTypeNameSuppliersActionPerformed
        try {
            if (!txtproductTypeNameSuppliers.getSelectedItem().equals(" ")) {
                txtQuantityOfOneVariable1.setText("تحتوي ال " + txtproductTypeNameSuppliers.getSelectedItem() + " علي: ");
                txtQuantityOfOneValue1.setEnabled(true);
                ResultSet rs=dbOperations.getData("select * from productsTypes where "
                        + "productTypeName='"+txtProductNameLable.getText()+"_"+txtproductTypeNameSuppliers.getSelectedItem().toString()+"'");
                if(rs.next())
                {
                    txtPriceOfOne.setText(""+rs.getDouble("priceOfBuy"));
                    txtQuantityOfOneValue1.setText(""+rs.getDouble("QuantityOfOne"));
                    txtSupplierName.setSelectedItem(rs.getString("supplierName"));
                }
                
            } else {
                  ResultSet rs=dbOperations.getData("select * from products where "
                        + "productId="+txtBarcodeProduct.getText());
                if(rs.next())
                {
                    txtPriceOfOne.setText(""+rs.getDouble("priceOfBuy"));
                    txtSupplierName.setSelectedItem(rs.getString("supplierName"));
                }
                txtQuantityOfOneVariable1.setText("__");
                txtQuantityOfOneValue1.setEnabled(false);
            }
            txtSupplierNameActionPerformed(evt);
        } catch (Exception e) {
            //            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_txtproductTypeNameSuppliersActionPerformed

    private void txtBarcodeProductKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBarcodeProductKeyReleased

        long forEx = 0;
        try {
            forEx = Long.parseLong(txtBarcodeProduct.getText());
            getAllProductRecords(txtBarcodeProduct.getText());
            getproductsTypesForCompo(txtBarcodeProduct.getText());
            txtBarcodeProduct.setForeground(new Color(0, 255, 0));
             txtSupplierNameActionPerformed(new ActionEvent(0, 0, ""));
        } catch (NumberFormatException numf) {
            btnAddToCart.setEnabled(false);
            txtBarcodeProduct.setForeground(new Color(255, 0, 0));
            txtPriceOfOne.setText("0");
            txtAvailableFromProductOnSupplier.setText("0.0");
            txtTakedFromProductsOnSupplier.setText("0.0");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_txtBarcodeProductKeyReleased

    private void txtQuantityWarningActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQuantityWarningActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQuantityWarningActionPerformed

    private void txtQuantityStoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQuantityStoreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQuantityStoreActionPerformed

    private void txtStoreNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtStoreNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtStoreNameActionPerformed

    private void txtProductTypeNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProductTypeNameKeyReleased
//    String id=txtProductId.getText();
        try {
//            txtProductId.setText("0");
            int str = txtProductTypeName.getText().length();
            if (str != 0) {
                ResultSet rs = dbOperations.getData("select * from products where productName='" + txtProductName.getText() + "'");
                if (!txtProductTypeName.getText().equals("الكيلو")
                        && !txtProductTypeName.getText().equals("كيلو")) {
                    if (rs.next()) {
                        txtPriceOfSale.setText("");
                        txtPriceOfBuy.setText("");
                        txtMarketPrice.setText("");
                        txtPriceOfBuy.setEnabled(false);
                        txtPriceOfSale.setEnabled(false);
                        txtMarketPrice.setEnabled(false);
                    }else 
                    {
                        txtPriceOfBuy.setEnabled(true);
                        txtPriceOfSale.setEnabled(true);
                        txtMarketPrice.setEnabled(true);
                    }
                    txtTypeVariable.setText("يحتوي " + txtProductTypeName.getText());
                    txtQuantityOfOne.setEnabled(true);
                    txtPriceOfSaleTypeVarialble.setText("سعر: " + txtProductTypeName.getText());
                    txtPriceOfSaleType.setEnabled(true);
                    txtPriceOfBuyTypes.setEnabled(true);
                    txtPriceOfMarketVar.setText("سعر التجاري ل: " + txtProductTypeName.getText());
                    txtMarketPriceTypes.setEnabled(true);
                    txtPriceOfBuyTypesVariable.setText("سعر شراء: " + txtProductTypeName.getText());

                } else {
                    txtTypeVariable.setText("__");
                    txtQuantityOfOne.setEnabled(false);
                    txtPriceOfSaleTypeVarialble.setText("__" + txtProductName.getText());
                    txtPriceOfSaleType.setEnabled(false);
                    txtPriceOfBuyTypes.setEnabled(false);
                    txtPriceOfBuy.setEnabled(true);
                    txtPriceOfMarketVar.setText("__");
                    txtMarketPriceTypes.setEnabled(false);
                    txtPriceOfSale.setEnabled(true);
                    txtMarketPrice.setEnabled(true);
                    txtPriceOfBuyTypesVariable.setText("__");
                }
            } else {
                txtTypeVariable.setText("__");
                txtQuantityOfOne.setEnabled(false);
                txtPriceOfSaleTypeVarialble.setText("__" + txtProductName.getText());
                txtPriceOfSaleType.setEnabled(false);
                txtPriceOfBuyTypes.setEnabled(false);
                txtPriceOfBuy.setEnabled(true);
                txtPriceOfMarketVar.setText("__");
                txtMarketPriceTypes.setEnabled(false);
                txtPriceOfSale.setEnabled(true);
                txtPriceOfBuyTypesVariable.setText("__");
            }

        } catch (Exception e) {
//            txtProductId.setText(id);
            txtTypeVariable.setText("__");
            txtQuantityOfOne.setEnabled(false);
            txtPriceOfSaleTypeVarialble.setText("__" + txtProductName.getText());
            txtPriceOfSaleType.setEnabled(false);
            txtPriceOfBuyTypes.setEnabled(false);
            txtPriceOfBuy.setEnabled(true);
            txtPriceOfMarketVar.setText("__");
            txtMarketPriceTypes.setEnabled(false);
            txtPriceOfSale.setEnabled(true);
            txtPriceOfBuyTypesVariable.setText("__");
        }
    }//GEN-LAST:event_txtProductTypeNameKeyReleased

    private void txtProductTypeNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProductTypeNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProductTypeNameActionPerformed

    private void txtCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCategoryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCategoryActionPerformed

    private void txtQuantityOfOneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQuantityOfOneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQuantityOfOneActionPerformed

    private void txtQuantityForSaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQuantityForSaleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQuantityForSaleActionPerformed

    private void txtProductNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProductNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProductNameActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed

        
            int x = 0;
        double Qty = 0;
        double Qty1 = 0;
        double Taked = 0;
        long productId=0;
        try {

            ResultSet rs = dbOperations.getData("select * from products where productName='" + txtProductName.getText() + "'");
            if (rs.next()) {
                x = 1;
                Qty = rs.getDouble("availableQty");
                Qty1 = rs.getDouble("AvailableQtyStore");
                productId=rs.getLong("productId");
                Taked = rs.getDouble("Taked");
            }
            if (txtTypeVariable.getText().equals("__") && x == 0) {
                productss prod = new productss();
                prod.setProductId(Long.valueOf(txtProductId.getText()));
                prod.setProductName(txtProductName.getText());
                prod.setPriceOfBuy(Double.valueOf(txtPriceOfBuy.getText()));
                prod.setPriceOfSale(Double.valueOf(txtPriceOfSale.getText()));
                prod.setTaked(0);
                prod.setAvailableQty(0);  // 100
                prod.setProductCategory(String.valueOf(txtCategory.getSelectedItem()));
                prod.setNote(txtNotes.getText());
                prod.setAvailableQtyStore(0);
                prod.setStoreName(txtStoreName.getSelectedItem().toString());
                prod.setQuantityWarning(Double.valueOf(txtQuantityWarning.getText()));
                prod.setPriceOfMarket(Double.valueOf(txtMarketPrice.getText()));
                prod.setSupplierName(txtSupplierNameAddProduct.getSelectedItem().toString());
                productsController.addProducts(prod);

            } else if (x == 0 && !txtTypeVariable.getText().equals("__")) {
                productss prod = new productss();
                prod.setProductId(Long.valueOf(txtProductId.getText()));
                prod.setProductName(txtProductName.getText());
                prod.setPriceOfBuy(Double.valueOf(txtPriceOfBuy.getText()));
                prod.setPriceOfSale(Double.valueOf(txtPriceOfSale.getText()));
                prod.setTaked(0);
                prod.setAvailableQty(0);
                prod.setProductCategory(String.valueOf(txtCategory.getSelectedItem()));
                prod.setNote(txtNotes.getText());
                prod.setAvailableQtyStore(0);
                prod.setStoreName(txtStoreName.getSelectedItem().toString());
                prod.setQuantityWarning(Double.valueOf(txtQuantityWarning.getText()) * Double.valueOf(txtQuantityOfOne.getText()));
                prod.setPriceOfMarket(Double.valueOf(txtMarketPrice.getText()));
                prod.setSupplierName(txtSupplierNameAddProduct.getSelectedItem().toString());
                productsController.addProducts(prod);

                productsTypes product = new productsTypes();
                product.setProductTypeName(txtProductName.getText() + "_" + txtProductTypeName.getText());
                product.setQuantityOfOne(Double.valueOf(txtQuantityOfOne.getText()));
                product.setAllQuantity(0);
                product.setAvailableQtyStore(0);
                product.setNote(txtNotes.getText());
                product.setPriceOfMarket(Double.valueOf(txtMarketPrice.getText()));
                product.setPriceOfBuy(Double.valueOf(txtPriceOfBuyTypes.getText()));
                product.setPriceOfSale(Double.valueOf(txtPriceOfSaleType.getText()));
                product.setProductCategory(txtCategory.getSelectedItem().toString());
                product.setQuantityWarning(Double.valueOf(txtQuantityWarning.getText()));
                product.setPriceOfMarket(Double.valueOf(txtMarketPriceTypes.getText()));
                product.setStoreName(txtStoreName.getSelectedItem().toString());
                product.setSupplierName(txtSupplierNameAddProduct.getSelectedItem().toString());
                productsController.addProductsWithType(txtProductName.getText(), product);
            } else if (x == 1 && !txtTypeVariable.getText().equals("__")) {
                 productsTypes product = new productsTypes();
                product.setProductTypeName(txtProductName.getText() + "_" + txtProductTypeName.getText());
                product.setQuantityOfOne(Double.valueOf(txtQuantityOfOne.getText()));// * +100
                product.setAllQuantity(Double.valueOf(txtQuantityForSale.getText()));
                product.setAvailableQtyStore(Double.valueOf(txtQuantityStore.getText()));
                product.setNote(txtNotes.getText());
                product.setPriceOfMarket(Double.valueOf(txtMarketPriceTypes.getText()));
                product.setStoreName(txtStoreName.getSelectedItem().toString());
                product.setSupplierName(txtSupplierNameAddProduct.getSelectedItem().toString());
//                ResultSet rs1=dbOperations.getData("select * from productsTypes where productTypeName like'%"+txtProductName.getText()+"%'");
//                while(rs1.next())
//                {
//                    if(rs1.getDouble("quantityOfOne")>Double.valueOf(txtQuantityOfOne.getText()))
//                    {
//                        JOptionPane.showMessageDialog(null, "hdi");
//                       break;
//                    }else 
//                    {
//                        JOptionPane.showMessageDialog(null, "hdb");
//                        dbOperations.setDataOrDelete("update products set availableQty="
//                            + ((Double.valueOf(txtQuantityOfOne.getText()) * Double.valueOf(txtQuantityForSale.getText())) + Qty)
//                            + ",AvailableQtyStore=" + ((Double.valueOf(txtQuantityOfOne.getText()) * Double.valueOf(txtQuantityStore.getText())) + Qty1) + " where productName='" + txtProductName.getText() + "'", "تم الاضافة");
//                    }
//                }
                product.setPriceOfBuy(Double.valueOf(txtPriceOfBuyTypes.getText()));
                product.setPriceOfSale(Double.valueOf(txtPriceOfSaleType.getText()));
                product.setProductCategory(txtCategory.getSelectedItem().toString());
                product.setQuantityWarning(Double.valueOf(txtQuantityWarning.getText()));
                productsController.addProductsWithType(txtProductName.getText(), product);
                double availableMahal=0;
                double availableStore=0;
                ResultSet rs4=dbOperations.getData("select * from products where productId="+productId);
                if(rs4.next())
                {
                   availableMahal= (Double.valueOf(txtQuantityForSale.getText())*Double.valueOf(txtQuantityOfOne.getText()));
                   availableStore= (Double.valueOf(txtQuantityStore.getText())*Double.valueOf(txtQuantityOfOne.getText()));
                    double QuantityWarning=(Double.valueOf(txtQuantityWarning.getText())*Double.valueOf(txtQuantityOfOne.getText()));
                    dbOperations.setDataOrDelete("update products set "
                            + "availableQty="+availableMahal
                            +",availableQtystore="+availableStore+",QuantityWarning="+QuantityWarning+" where productId="+productId,"");
                }
                ResultSet rs1=dbOperations.getData("select * from productsTypes where productId="+productId);
                while(rs1.next())
                {
                    dbOperations.setDataOrDelete("update productsTypes set AllQuantity="+String.format("%.2f", ((double)availableMahal/(double)rs1.getDouble("QuantityOfOne")))
                            +",availableQtystore="+String.format("%.2f", ((double)availableStore/(double)rs1.getDouble("QuantityOfOne")))+" where id="+rs1.getLong("id"), "");
                }
            } else {
                JOptionPane.showMessageDialog(null, "<html><h1>موجود مسبقا</h1></html>");
                txtProductName.setText("");
            }

            new barcodeGenerator(String.valueOf(productsController.getproductIDWithName(txtProductName.getText()))).generate();
            HashMap para = new HashMap();
            para.put("pro_id", productsController.getproductIDWithName(txtProductName.getText()));
            //                para.put("product_Name", tm.getValueAt(index,1).toString());
            ReportView r = new ReportView("src\\reports\\lastBarcodeReport.jasper", para);
            r.view();
            getAllRecordsForFirstTable();
            //                new barcodeGenerator(String.valueOf(productsController.getIDWithNameProductsTypes(txtProductName.getText()+" "+txtProductTypeName.getText()))).generate();
            //                HashMap para2 = new HashMap();
            //                para2.put("pro_id", productsController.getIDWithNameProductsTypes(txtProductName.getText()+" "+txtProductTypeName.getText()));
            //                ReportView r2 = new ReportView("src\\reports\\BarCodeGenerate.jasper", para2);
            //                r2.view();
            Clear();
            txtProductTypeName.setText("");
            txtMarketPrice.setText("");
            txtMarketPriceTypes.setText("");
            txtStoreName.setSelectedIndex(0);
            txtProductIdTypes.setText("0");
            txtCategory.setSelectedIndex(0);
            txtSupplierNameAddProduct.setSelectedIndex(0); 
            txtQuantityForSale.setText("0");
            txtQuantityStore.setText("0");
            txtQuantityWarning.setText("0");
            txtQuantityOfOne.setText("");
            txtPriceOfBuy.setText("");
            txtPriceOfSale.setText(""); 
            txtPriceOfBuyTypes.setText("");
            txtPriceOfSaleType.setText(""); 
            txtMarketPrice.setText("");
            txtMarketPrice.setText("");
            txtMarketPriceTypes.setText(""); 
            getAllproductsNamesCompo();
            if(txtProductCodeDefault.getText().equals(txtProductId.getText())&&txtProductIdTypes.getText().equals(0)) 
            {
                dbOperations.setDataOrDelete("update barcodes set id="+txtProductCodeDefault.getText(), "");
                getLastBarCode();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
       
    }//GEN-LAST:event_btnSaveActionPerformed

    public void getAllTotalProducts()
    {
        double priceOfBuy=0;
        double priceOfsale=0;
        double AvailableQty=0;
        double AvailableQtyStore=0;
        double Total=0;
        double TotalSold=0;
        try {
            
            TableModel dtm=jTableProductAvailables.getModel();
            
            for (int i = 0; i <dtm.getRowCount(); i++) {
                priceOfBuy=Double.valueOf(dtm.getValueAt(i, 4).toString());
                priceOfsale=Double.valueOf(dtm.getValueAt(i, 5).toString());
                AvailableQty=Double.valueOf(dtm.getValueAt(i, 8).toString());
                AvailableQtyStore=Double.valueOf(dtm.getValueAt(i, 9).toString());
                 Total+=(AvailableQty+AvailableQtyStore)*priceOfBuy;
                 TotalSold+=(AvailableQty+AvailableQtyStore)*priceOfsale;
            }
           
                txtAllTotal.setText(String.format("%.2f",Total));
                txtAllTotalSold.setText(String.format("%.2f",TotalSold));
//            ArrayList<productss> list=new ArrayList();
//            ResultSet rs=dbOperations.getData("select * from products");
//            while(rs.next())
//            {
//                priceOfBuy=rs.getDouble("priceOfBuy");
//                priceOfsale=rs.getDouble("priceOfSale");
//                AvailableQty=rs.getDouble("AvailableQty");
//                AvailableQtyStore=rs.getDouble("AvailableQtyStore"); 
//                productss prod=new productss();
//                prod.setPriceOfBuy(priceOfBuy);
//                prod.setPriceOfSale(priceOfsale);
//                prod.setAvailableQty(AvailableQty);
//                prod.setAvailableQtyStore(AvailableQtyStore);
//               list.add(prod);
//            }
//            Iterator <productss>itr=list.iterator();
//            while(itr.hasNext())
//            {
//                productss prod=itr.next();
//                 Total=(AvailableQty+AvailableQtyStore)*priceOfBuy;
//                TotalSold=(AvailableQty+AvailableQtyStore)*priceOfsale;
//            }
//            txtAllTotal.setText(String.format("%.2f",Total));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed

                           try {
            double lastAvailable = 0;

            double lastAvailableStore = 0;
            double QuantityWarning = 0;
            
               double availableMahal=0;
                double availableStore=0;
                 
            if (txtTypeVariable.getText().equals("__")) {
               
                dbOperations.setDataOrDelete("update products set "
                        + "productName='" + txtProductName.getText()
                        +"',AvailableQtyStore="+ Double.valueOf(txtQuantityStore.getText())
                        +",AvailableQty="+Double.valueOf(txtQuantityForSale.getText())
                        + ",storeName='" + txtStoreName.getSelectedItem().toString()
                        + "',priceOfBuy=" + txtPriceOfBuy.getText() + ","
                        + "priceOfSale=" + txtPriceOfSale.getText()
                        + ",priceOfMarket=" + txtMarketPrice.getText()
                        + ",QuantityWarning=" + txtQuantityWarning.getText()
                        + ",Taked=" + txtTakedComeFromTable.getText()
                        +",supplierName='"+txtSupplierNameAddProduct.getSelectedItem().toString()
                        +"',Note='"+txtNotes.getText()
                        + "' where productId=" + txtProductId.getText(), "تم التعديل");
                ResultSet rs = dbOperations.getData("select * from productsTypes where productId=" + txtProductId.getText());
                while (rs.next()) {
                    String lastAvailables = String.format("%.2f", (Double.valueOf(txtQuantityForSale.getText())/ rs.getDouble("QuantityOfOne")));
                    String lastAvailableStors = String.format("%.2f",(Double.valueOf(txtQuantityStore.getText()) / rs.getDouble("QuantityOfOne")));
                    String QuantityWarnings= String.format("%.2f",(Double.valueOf(txtQuantityWarning.getText()) / rs.getDouble("QuantityOfOne")));
                    dbOperations.setDataOrDelete("update productsTypes set"
                            + " AllQuantity=" +lastAvailables+ ","
                            + "AvailableQtyStore=" +lastAvailableStors+ ","
                            + "QuantityWarning=" + QuantityWarnings
                            +",priceofBuy="+(String.format("%.2f",(Double.valueOf(txtPriceOfBuy.getText())*rs.getDouble("QuantityOfOne"))))
                                +",priceOfSale="+(String.format("%.2f",(Double.valueOf(txtPriceOfSale.getText())*rs.getDouble("QuantityOfOne"))))
                                 +",priceOfMarket="+(String.format("%.2f",(Double.valueOf(txtMarketPrice.getText())*rs.getDouble("QuantityOfOne"))))
                            + " where id=" + rs.getLong("id"),"");
                }

            } else {
                    ResultSet rs1 = dbOperations.getData("select * from productsTypes where productTypeName='" + txtProductName.getText() + "_" + txtProductTypeName.getText() + "'");
                    if (rs1.next()) {
                        dbOperations.setDataOrDelete("update productsTypes set QuantityOfOne=" + txtQuantityOfOne.getText()
                                +",storeName='"+txtStoreName.getSelectedItem()
                                + "',priceOfBuy=" + txtPriceOfBuyTypes.getText()
                                + ",priceOfSale=" + txtPriceOfSaleType.getText()
                                + ",priceOfMarket=" + txtMarketPriceTypes.getText()
                                +",Note='"+txtNotes.getText()
                                + "' where productTypeName='" + txtProductName.getText() + "_" + txtProductTypeName.getText() + "'", "");
                        // update products table
                        lastAvailable =(Double.valueOf(txtQuantityForSale.getText()) * Double.valueOf(txtQuantityOfOne.getText()));
                        lastAvailableStore =(Double.valueOf(txtQuantityStore.getText()) * Double.valueOf(txtQuantityOfOne.getText()));
                        QuantityWarning=(Double.valueOf(txtQuantityWarning.getText()) * rs1.getDouble("QuantityOfOne"));
                          double priceofBuy=Double.valueOf(String.format("%.2f",(Double.valueOf(txtPriceOfBuyTypes.getText())/Double.valueOf(txtQuantityOfOne.getText()))));
                          double priceOfSale=Double.valueOf(String.format("%.2f",(Double.valueOf(txtPriceOfSaleType.getText())/Double.valueOf(txtQuantityOfOne.getText()))));
                          double priceOfMarket=Double.valueOf(String.format("%.2f",(Double.valueOf(txtMarketPriceTypes.getText())/Double.valueOf(txtQuantityOfOne.getText()))));
                        dbOperations.setDataOrDelete("update products set availableQty="
                                + String.format("%.2f", lastAvailable) + ""
                                + ",availableQtyStore=" + String.format("%.2f",lastAvailableStore) +","
                                + "QuantityWarning="+ String.format("%.2f",QuantityWarning)
                                +",priceofBuy="+ priceofBuy
                                +",priceOfSale="+ priceOfSale
                                 +",priceOfMarket="+ priceOfMarket
                                +",supplierName='"+txtSupplierNameAddProduct.getSelectedItem().toString()
                                +"',Note='"+txtNotes.getText()
                                +"' where productId="+rs1.getLong("productId"), "");
                        ResultSet rsdd=dbOperations.getData("select * from productsTypes where productId="+txtProductId.getText());
                        while(rsdd.next())
                        {
                            dbOperations.setDataOrDelete("update productsTypes set "
                                    + "AllQuantity="+String.format("%.2f", ((double)lastAvailable/(double)rsdd.getDouble("QuantityOfOne")))
                                    +",availableQtyStore="+String.format("%.2f", ((double)lastAvailableStore/(double)rsdd.getDouble("QuantityOfOne")))
                                    +",QuantityWarning="+String.format("%.2f", ((double)QuantityWarning/(double)rsdd.getDouble("QuantityOfOne")))
                                    +",priceofBuy="+(String.format("%.2f",(priceofBuy*rsdd.getDouble("QuantityOfOne"))))
                                    +",priceOfSale="+(String.format("%.2f",(priceOfSale*rsdd.getDouble("QuantityOfOne"))))
                                    +",priceOfMarket="+(String.format("%.2f",(priceOfMarket*rsdd.getDouble("QuantityOfOne"))))
                                    +" where id="+rsdd.getLong("id"), "");
                        }
                    }
                
            }
            getAllRecordsForFirstTable();
            Clear();
            txtProductTypeName.setText("");
            txtMarketPrice.setText("");
            txtMarketPriceTypes.setText("");
            txtStoreName.setSelectedIndex(0);
            txtProductIdTypes.setText("0");
            txtCategory.setSelectedIndex(0);
            txtSupplierNameAddProduct.setSelectedIndex(0); 
            txtQuantityForSale.setText("0");
            txtQuantityStore.setText("0");
            txtQuantityWarning.setText("0");
            txtQuantityOfOne.setText("");
            txtPriceOfBuy.setText("");
            txtPriceOfSale.setText(""); 
            txtPriceOfBuyTypes.setText("");
            txtPriceOfSaleType.setText(""); 
            txtMarketPrice.setText("");
            txtMarketPrice.setText("");
            txtMarketPriceTypes.setText("");
            getAllproductsNamesCompo();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
               
               
      
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed

        if (!txtProductIdTypes.getText().equals("0")) {
            dbOperations.setDataOrDelete("delete from productsTypes where id=" +txtProductIdTypes.getText(), "تم الحذف");
        } else {
            dbOperations.setDataOrDelete("delete from products where productId=" +txtProductCodeDefault.getText(), "تم الحذف");
            dbOperations.setDataOrDelete("delete from productsTypes where productId=" +txtProductCodeDefault.getText(), "");
        }
        getAllRecordsForFirstTable();
        
        getLastBarCode();
            txtProductTypeName.setText("");
            txtMarketPrice.setText("");
            txtMarketPriceTypes.setText("");
            txtSupplierNameAddProduct.setSelectedIndex(0);
            txtCategory.setSelectedIndex(0);
            txtStoreName.setSelectedIndex(0);
            txtProductIdTypes.setText("0"); 
            txtProductName.setText("");
            txtProductTypeName.setText(""); 
        Clear();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void txtStoreSearchNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtStoreSearchNameActionPerformed
        try {
            DefaultTableModel dtm = (DefaultTableModel) jTableProductAvailables.getModel();
            dtm.setRowCount(0);
            DefaultTableModel dtm2 = (DefaultTableModel) jTableProductAvailables1.getModel();
            dtm2.setRowCount(0);
            ResultSet rs1 = dbOperations.getData("select * from products where storeName='" + txtStoreSearchName.getSelectedItem().toString() + "'");
            while (rs1.next()) {
                dtm.addRow(new Object[]{
                    rs1.getLong("productId"),
                    rs1.getString("productName"),
                    rs1.getString("category"),
                    rs1.getString("storeName"),
                    rs1.getDouble("priceOfBuy"),
                    rs1.getDouble("priceOfSale"),
                    rs1.getDouble("priceOfMarket"),
                    rs1.getDouble("Taked"),
                    rs1.getDouble("AvailableQty"),
                    rs1.getDouble("AvailableQtyStore"),
                    rs1.getString("Note"),
                    rs1.getString("supplierName")
                });
                ResultSet rs2 = dbOperations.getData("select * from productsTypes where storeName='" + txtStoreSearchName.getSelectedItem().toString()+"'");
                while (rs2.next()) {
                    dtm2.addRow(new Object[]{
                        rs2.getLong("productId"),
                        rs2.getString("productTypeName"),
                        rs2.getString("category"),
                        rs2.getDouble("priceOfBuy"),
                        rs2.getDouble("priceOfSale"),
                        rs2.getDouble("priceOfMarket"),
                        rs2.getDouble("AvailableQtyStore"),
                        rs2.getDouble("AllQuantity"),
                        rs2.getDouble("QuantityOfOne"),
                         rs2.getString("storeName") ,
                    rs2.getString("Note"),
                    rs2.getString("supplierName")
                    });
                }
            }
        } catch (Exception e) {

        }
    }//GEN-LAST:event_txtStoreSearchNameActionPerformed

    private void btnAddStoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddStoreActionPerformed
        try {
            dbOperations.setDataOrDelete("insert into store (storeName) values('" + txtStoreNameAdd.getText() + "')", "تم الاضافة");
            getStoreNames();
            getAllStoresName();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_btnAddStoreActionPerformed

    private void txtStoreNameAddKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtStoreNameAddKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtStoreNameAddKeyReleased

    private void txtStoreNameAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtStoreNameAddActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtStoreNameAddActionPerformed

    private void txtStoreNameAddFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtStoreNameAddFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtStoreNameAddFocusLost

    private void txtStoreNameAddFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtStoreNameAddFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtStoreNameAddFocusGained

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            dbOperations.setDataOrDelete("insert into category (categoryName) values('" + txtCategoryNameAdd.getText() + "')", "تم الاضافة");
            getAllCategoryName();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtCategoryNameAddKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCategoryNameAddKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCategoryNameAddKeyReleased

    private void txtCategoryNameAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCategoryNameAddActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCategoryNameAddActionPerformed

    private void txtCategoryNameAddFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCategoryNameAddFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCategoryNameAddFocusLost

    private void txtCategoryNameAddFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCategoryNameAddFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCategoryNameAddFocusGained

    private void txtSearchCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchCategoryActionPerformed
        try {
            DefaultTableModel dtm = (DefaultTableModel) jTableProductAvailables.getModel();
            dtm.setRowCount(0);
            ResultSet rs1 = dbOperations.getData("select * from products where category='" + txtSearchCategory.getSelectedItem().toString() + "'");
            while (rs1.next()) {
                dtm.addRow(new Object[]{
                    rs1.getLong("productId"),
                    rs1.getString("productName"),
                    rs1.getString("category"),
                    rs1.getString("storeName"),
                    rs1.getDouble("priceOfBuy"),
                    rs1.getDouble("priceOfSale"),
                    rs1.getDouble("priceOfMarket"),
                    rs1.getDouble("Taked"),
                    rs1.getDouble("AvailableQty"),
                    rs1.getDouble("AvailableQtyStore"),
                    rs1.getString("Note"),
                    rs1.getString("supplierName")
                });
            }
            DefaultTableModel dtm2 = (DefaultTableModel) jTableProductAvailables1.getModel();
            dtm2.setRowCount(0);
            ResultSet rs2 = dbOperations.getData("select * from productsTypes where category='" + txtSearchCategory.getSelectedItem().toString() + "'");
            while (rs2.next()) {
                dtm2.addRow(new Object[]{
                    rs2.getLong("productId"),
                    rs2.getString("productTypeName"),
                    rs2.getString("category"),
                    rs2.getDouble("priceOfBuy"),
                    rs2.getDouble("priceOfSale"),
                    rs2.getDouble("priceOfMarket"),
                    rs2.getDouble("AvailableQtyStore"),
                    rs2.getDouble("AllQuantity"),
                    rs2.getDouble("QuantityOfOne"),
                     rs2.getString("storeName") ,
                    rs2.getString("Note"),
                    rs2.getString("supplierName")
                   
                });
            }
        } catch (Exception e) {

        }
    }//GEN-LAST:event_txtSearchCategoryActionPerformed

    private void jTableProductAvailables1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableProductAvailables1KeyReleased

    }//GEN-LAST:event_jTableProductAvailables1KeyReleased

    private void jTableProductAvailables1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableProductAvailables1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jTableProductAvailables1MouseEntered

    private void jTableProductAvailables1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableProductAvailables1MouseClicked
        try {
            Clear();
            DefaultTableModel dtm = (DefaultTableModel) jTableProductAvailables.getModel();
            TableModel tm = jTableProductAvailables1.getModel();
            int index = jTableProductAvailables1.getSelectedRow();
            int productId = Integer.valueOf(tm.getValueAt(index, 0).toString());
            String productName = tm.getValueAt(index, 1).toString();
            String Category = tm.getValueAt(index, 2).toString();
            String priceOfBuy = tm.getValueAt(index, 3).toString();
            String priceOfSale = tm.getValueAt(index, 4).toString();
            String priceOfMarket = tm.getValueAt(index, 5).toString();
            String Quantity = tm.getValueAt(index, 7).toString();
            String QuantityStore = tm.getValueAt(index, 6).toString();
            String QuantityOfOne = tm.getValueAt(index, 8).toString();
            txtProductId.setText(String.valueOf(productId));
            txtProductName.setText(productName.substring(0, productName.indexOf("_")));
            txtProductTypeName.setText(productName.substring(productName.indexOf("_") + 1, productName.length()));
            txtCategory.setSelectedItem(Category);
            txtPriceOfBuyTypes.setText(priceOfBuy);
            txtMarketPriceTypes.setText(priceOfMarket + "");
            txtPriceOfMarketVar.setText("سعر تجاري "+txtProductTypeName.getText());
            txtPriceOfSaleType.setText(priceOfSale);
            txtQuantityForSale.setText(Quantity);
            txtQuantityOfOne.setText(QuantityOfOne);
            txtQuantityStore.setText(QuantityStore);
            txtSupplierNameAddProduct.setSelectedItem(tm.getValueAt(index,11).toString());
            ResultSet rs1 = dbOperations.getData("select * from productsTypes where productTypeName='" + productName + "'");
            if (rs1.next()) {
                txtMarketPrice.setText(rs1.getDouble("priceOfMarket") + "");
                txtProductIdTypes.setText(""+rs1.getLong("id"));
                txtQuantityWarning.setText(rs1.getDouble("QuantityWarning")+"");
            }

           double quantityOfOne= Double.valueOf(QuantityOfOne);
            ResultSet rs = dbOperations.getData("select * from products where productId=" + productId);
            if (rs.next()) {
                txtAvailableComeFromTable.setText(String.valueOf((long)(rs.getDouble("AvailableQty") + rs.getDouble("AvailableQtyStore")) /(long)quantityOfOne));
            }

            txtAvailableForTable1.setText( (Double.valueOf(tm.getValueAt(index,6).toString())+  Double.valueOf(tm.getValueAt(index,7).toString()))+ "");
            txtAvailableForTableVariable1.setText(String.valueOf(tm.getValueAt(index, 1)));
            txtPriceOfAllQuantityDouble.setText(String.valueOf(Double.valueOf(tm.getValueAt(index, 3).toString()) * (Double.valueOf(tm.getValueAt(index, 6).toString()) + Double.valueOf(tm.getValueAt(index, 7).toString()))));
            txtGainedForAllQuantityTypes.setText(String.format("%.2f",((((Double.valueOf(tm.getValueAt(index, 4).toString())-Double.valueOf(tm.getValueAt(index, 3).toString()))/Double.valueOf(tm.getValueAt(index, 3).toString()))))*100) + " %");
            txtTypeVariable.setText(txtProductTypeName.getText());
            txtQuantityOfOne.setEnabled(true);
            txtPriceOfSaleTypeVarialble.setText("سعر: " + txtProductTypeName.getText());
            txtPriceOfSaleType.setEnabled(true);
            txtPriceOfBuyTypes.setEnabled(true);
            txtPriceOfBuy.setEnabled(false);
            txtPriceOfSale.setEnabled(false);
           txtMarketPrice.setText("");
           txtMarketPrice.setEnabled(false);
           txtMarketPriceTypes.setEnabled(true);
            txtPriceOfBuyTypesVariable.setText("سعر شراء: " + txtProductTypeName.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jTableProductAvailables1MouseClicked

    private void txtBarcodeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBarcodeKeyReleased
        try {
            getAllRecordsFromInnerJoin(Long.parseLong(txtBarcode.getText()));
        } catch (NumberFormatException ex) {
            getAllRecordsForFirstTable();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_txtBarcodeKeyReleased

    private void txtBarcodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBarcodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBarcodeActionPerformed

    private void txtBarcodeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBarcodeFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBarcodeFocusLost

    private void txtBarcodeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBarcodeFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBarcodeFocusGained

    private void btnReportsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReportsActionPerformed
        try {
            String type;
            if(txtCheckBoxMarket.isSelected())
            {
                type="محل";
            }else 
            {
                type="مخزن";
            }
            if(!txtSearchCategory.getSelectedItem().equals("قسم")&&txtSeachDaily.getDate()!=null)
            {
                HashMap para=new HashMap();
                para.put("inv_date",df.format(txtSeachDaily.getDate())+"____________%");
                para.put("inv_category",txtSearchCategory.getSelectedItem().toString());
                para.put("specific",txtGained1.getText());
                para.put("inv_productPlace",type);
                ReportView r=new ReportView("src\\reports\\productsReport.jasper",para);
                r.view();
                ReportView r1=new ReportView("src\\reports\\productsTypesReport.jasper",para);
                r1.view();
            }else if(!txtSearchCategory.getSelectedItem().equals("قسم")&&txtSearchMonthly.getSelectedIndex()!=0)
            {
                HashMap para=new HashMap();
                para.put("inv_date",txtSeachYear.getSelectedItem().toString()+"-"+txtSearchMonthly.getSelectedItem().toString()+"_______________%");
                para.put("inv_category",txtSearchCategory.getSelectedItem().toString());
                para.put("specific",txtGained1.getText());
                 para.put("inv_productPlace",type);
                ReportView r=new ReportView("src\\reports\\productsReport.jasper",para);
                r.view();
                ReportView r1=new ReportView("src\\reports\\productsTypesReport.jasper",para);
                r1.view();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_btnReportsActionPerformed

    private void txtSeachDailyPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtSeachDailyPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSeachDailyPropertyChange

    private void txtSeachDailyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtSeachDailyMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSeachDailyMouseClicked

    private void txtGained1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGained1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGained1ActionPerformed

    private void txtGained1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtGained1FocusLost

    }//GEN-LAST:event_txtGained1FocusLost

    private void txtGained1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtGained1FocusGained

    }//GEN-LAST:event_txtGained1FocusGained

    private void jScrollPane2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jScrollPane2KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jScrollPane2KeyReleased

    private void jTableProductAvailablesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableProductAvailablesMouseClicked
        try {
            Clear();
            int index = jTableProductAvailables.getSelectedRow();
            TableModel tm = jTableProductAvailables.getModel();
            long productIdss = Long.valueOf(tm.getValueAt(index, 0).toString());
            String productName = tm.getValueAt(index, 1).toString();
            String productCategory = tm.getValueAt(index, 2).toString();
            String StoreName = tm.getValueAt(index, 3).toString();
            String priceOfBuy = tm.getValueAt(index, 4).toString();
            String priceOfSale = tm.getValueAt(index, 5).toString();
            String priceMarket = tm.getValueAt(index, 6).toString();
            String Taked = tm.getValueAt(index, 7).toString();
            String AvailableQty = tm.getValueAt(index, 8).toString();
            String AvailableQtyStore = tm.getValueAt(index, 9).toString();
            DefaultTableModel dtm = (DefaultTableModel) jTableProductAvailables1.getModel();
            dtm.setRowCount(0);
            txtProductId.setText(String.valueOf(productIdss));
            txtProductCodeDefault.setText(String.valueOf(productIdss));
            txtProductName.setText(productName);
            txtCategory.setSelectedItem(productCategory);
            txtPriceOfBuy.setText(priceOfBuy);
            txtPriceOfSale.setText(priceOfSale);
            txtQuantityForSale.setText(AvailableQty);
            txtAvailableComeFromTable.setText((Double.valueOf(AvailableQty) + Double.valueOf(AvailableQtyStore)) + "");
            txtTakedComeFromTable.setText(Taked);
            txtStoreName.setSelectedItem(StoreName);
            txtQuantityStore.setText(AvailableQtyStore);
            txtNotes.setText(tm.getValueAt(index, 10).toString());
            txtMarketPrice.setText(priceMarket);
            txtMarketPrice.setEnabled(true);
            txtMarketPriceTypes.setText("");
            txtMarketPriceTypes.setEnabled(false);
            txtProductTypeName.setText("");
            txtProductIdTypes.setText("0");
            txtSupplierNameAddProduct.setSelectedItem(tm.getValueAt(index,11).toString());
            ResultSet rs = dbOperations.getData("select * from products where productName='" + productName + "'");
            if (rs.next()) {
                txtQuantityWarning.setText(rs.getDouble("QuantityWarning") + "");
            }
            int a = JOptionPane.showConfirmDialog(null, "هل تريد عرض انواع المنتج  والباركود؟", "اختر", JOptionPane.YES_NO_OPTION);
            if (a == 0) {
                new barcodeGenerator(String.valueOf(productsController.getproductIDWithName(txtProductName.getText()))).generate();
                HashMap para = new HashMap();
                para.put("pro_id", tm.getValueAt(index, 0).toString());
                //                para.put("product_Name", tm.getValueAt(index,1).toString());
                ReportView r = new ReportView("src\\reports\\lastBarcodeReport.jasper", para);
                r.view();
                txtAvailableForTableVariable.setText(productName);
                txtAvailableForTable.setText((Double.valueOf(AvailableQty) + Double.valueOf(AvailableQtyStore)) + "");
                txtPriceOfAllQuantitySingle.setText(String.valueOf(Double.valueOf(priceOfBuy) * (Double.valueOf(AvailableQty) + Double.valueOf(AvailableQtyStore))));
                txtGainedForAllQuantitySingle.setText(String.format("%.2f",(((Double.valueOf(priceOfSale)-Double.valueOf(priceOfBuy))/Double.valueOf(priceOfBuy)))*100) + " %");
                ResultSet rs2 = dbOperations.getData("select * from productsTypes where productId=" + productIdss);
                while (rs2.next()) {
                    dtm.addRow(new Object[]{
                        rs2.getLong("productId"),
                        rs2.getString("productTypeName"),
                        rs2.getString("category"),
                        rs2.getDouble("priceOfBuy"),
                        rs2.getDouble("priceOfSale"),
                        rs2.getDouble("priceOfMarket"),
                        rs2.getDouble("AvailableQtyStore"),
                        rs2.getDouble("AllQuantity"),
                        rs2.getDouble("QuantityOfOne"),
                        rs2.getString("storeName"),
                        rs2.getString("Note"),
                        rs2.getString("supplierName")
                    });
                }
                rs2.close();
            }

            txtPriceOfSale.setEnabled(true);
            txtPriceOfBuy.setEnabled(true);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jTableProductAvailablesMouseClicked

    private void txtBarCodeSearchLowQuantityKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBarCodeSearchLowQuantityKeyReleased
        try {
            long a = Long.parseLong(txtBarCodeSearchLowQuantity.getText());
            getLowQuantitiesWithproductId(txtBarCodeSearchLowQuantity.getText());
        } catch (NumberFormatException ex) {
            getLowQuantities();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_txtBarCodeSearchLowQuantityKeyReleased

    private void txtStoreNameSearchLowQuantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtStoreNameSearchLowQuantityActionPerformed
        try {
            getLowQuantitiesWithStoreName(txtStoreNameSearchLowQuantity.getSelectedItem().toString());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_txtStoreNameSearchLowQuantityActionPerformed

    private void txtcategorySearchLowQuantity1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcategorySearchLowQuantity1ActionPerformed
        try {
            getLowQuantitiesWithCategory(txtcategorySearchLowQuantity1.getSelectedItem().toString());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_txtcategorySearchLowQuantity1ActionPerformed

    private void txtBarCodeSearchLowQuantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBarCodeSearchLowQuantityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBarCodeSearchLowQuantityActionPerformed

    private void txtPurchaseNumberSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPurchaseNumberSearchKeyReleased

        DefaultTableModel dtm = (DefaultTableModel) jTableAddForCaty.getModel();
        dtm.setRowCount(0);
        try {
            int a = Integer.parseInt(txtPurchaseNumberSearch.getText());
            ResultSet rs = dbOperations.getData("Select * from salesSuppliers where INID=" + txtPurchaseNumberSearch.getText());
            if (rs.next()) {
                if (!rs.getString("Status").equals("تم الاستلام وتم الدفع")) {
                    btnArrivalApproval.setEnabled(true);
                    btnPrint.setEnabled(false);
                    txtStatusSearchPurchase.setText(rs.getString("Status"));
                    txtSupplierName.setSelectedItem(rs.getString("supplierName"));
                    txtReceiverNameForCart.setSelectedItem(rs.getString("ReceiverName"));
                    txtPriceOfAll1.setText(rs.getDouble("AllTotal") + "");
                    txtRemained.setText(rs.getDouble("Remained") + "");
                    txtPayedValue.setText(rs.getDouble("payed") + "");
                    txtSearchPurchaseDate.setText(rs.getString("DayDate"));
                    txtsupplierNotes.setText(rs.getString("Note"));
                    txtTotalQuantity.setText(rs.getDouble("Total_Quantity") + "");
                    ResultSet rs1 = dbOperations.getData("select * from cartSuppliers where INID=" + txtPurchaseNumberSearch.getText());
                    while (rs1.next()) {
                        dtm.addRow(new Object[]{
                            rs1.getLong("INID"),
                            rs1.getString("productName"),
                            rs1.getString("Barcode"),
                            rs1.getString("companyBarcode"),
                            rs1.getDouble("QuantityNeeded"),
                            rs1.getDouble("unit_price"),
                            rs1.getDouble("Total_price")
                        });
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "تم الاستلام ل هذا الطلب");
                }

            }
        } catch (NumberFormatException e) {
            btnArrivalApproval.setEnabled(false);
            btnPrint.setEnabled(true);
            txtSupplierName.setSelectedIndex(0);
            txtReceiverNameForCart.setSelectedIndex(0);
            txtPriceOfAll1.setText("0.0");
            txtRemained.setText("0.0");
            txtPayedValue.setText("0.0");
            txtSearchPurchaseDate.setText("");
            txtsupplierNotes.setText("");
            txtTotalQuantity.setText("0.0");
            dtm.setRowCount(0);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_txtPurchaseNumberSearchKeyReleased

    private void txtBarcodeProductCompanyKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBarcodeProductCompanyKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBarcodeProductCompanyKeyReleased

    private void txtHandDateArrivalFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtHandDateArrivalFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHandDateArrivalFocusGained

    private void txtHandDateArrivalPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtHandDateArrivalPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHandDateArrivalPropertyChange

    private void btnArrivalApprovalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnArrivalApprovalActionPerformed
        double payedForOperationPaySuppliers = 0;
        
        String status = "";
        btnArrivalApproval.setEnabled(false);
        int a = JOptionPane.showConfirmDialog(null, "<html><h1>تأكيد استلام المنتجات واضافة الاعداد الي المخزن؟</h1></html>", "اختر", JOptionPane.YES_NO_OPTION);
        if (a == 0) {
            int INid = Integer.valueOf(txtPurchaseNumberSearch.getText());
            String storeAddress = "الفيوم شارع مدرسة الفنية بجوار سوبر ماركت غلاب";
            try {
                String productname;
                String barCode;
                String companyBarcode;
                String quantityNeeded;
                double productPrice;
                String TotalPrice;
                String AfterPoint = "";
                int len = jTableAddForCaty.getRowCount();
                ResultSet getRemained = dbOperations.getData("select * from salesSuppliers where INID=" + INid);
                if (getRemained.next()) {
                    if (getRemained.getDouble("Remained") >1) {
                        status = getRemained.getString("status");
                        payedForOperationPaySuppliers = Double.valueOf(txtPayedValue.getText()) - getRemained.getDouble("payed");
                        remainedForOperation = getRemained.getDouble("Remained");
                        if(getRemained.getString("Status").contains("تم الاستلام")){
                            ResultSet rs3 = dbOperations.getData("select * from suppliers where supplierName='" + txtSupplierName.getSelectedItem() + "'");
                        if (rs3.next()) {
                            dbOperations.setDataOrDelete("update suppliers set Remained=" + (String.format("%.2f", rs3.getDouble("Remained") - getRemained.getDouble("Remained"))) + " where supplierName='" + txtSupplierName.getSelectedItem() + "'", "");
                        }
                        }
                        ResultSet rs = dbOperations.getData("select * from capenat where id=(select max(id) from capenat)");
                        if (rs.next()) {
                            dbOperations.setDataOrDelete("insert into capenat (realValue,editedvalue,afterEdictvalue,OperationType,date) "
                                    + "values(" + String.format("%.2f",rs.getDouble("afterEdictvalue")) + "," + String.format("%.2f", getRemained.getDouble("payed") )+ "," +String.format("%.2f", (rs.getDouble("afterEdictvalue") + getRemained.getDouble("payed")))+ ",'سحب استلام منتج','" + txtPurchaseDate.getText() + "')", "");
                        }
                    }
                }
                try {
                    dbOperations.setDataOrDelete("delete from cartSuppliers where INID=" + INid, "");
                    dbOperations.setDataOrDelete("delete from salesSuppliers where INID=" + INid, "");
                    DefaultTableModel dtm = (DefaultTableModel) jTableAddForCaty.getModel();
                    for (int i = 0; i < len; i++) {
                        productname = dtm.getValueAt(i, 1).toString();
                        barCode = dtm.getValueAt(i, 2).toString();
                        companyBarcode = dtm.getValueAt(i, 3).toString();
                        quantityNeeded = dtm.getValueAt(i, 4).toString();
                        productPrice = Double.parseDouble(dtm.getValueAt(i, 5).toString());
                        TotalPrice = dtm.getValueAt(i, 6).toString();
                        dbOperations.setDataOrDelete("Insert into cartSuppliers("
                                + "INID,"
                                + "productname,"
                                + "BarCode,"
                                + "companyBarCode,"
                                + "quantityneeded,"
                                + "Unit_Price,"
                                + "Total_Price,"
                                + "date) values("
                                + INid + ",'"
                                + productname
                                + "','" + barCode
                                + "','" + companyBarcode
                                + "'," + quantityNeeded
                                + "," + productPrice
                                + "," + TotalPrice
                                + ",'" + txtPurchaseDate.getText() + "')", "");
                        if (status.contains("تم الاستلام")) {
                            if (productname.contains("_")) {
                                ResultSet rs111 = dbOperations.getData("select * from productsTypes where productTypeName='" + productname + "'");
                                double newSingle=0;
                                if (rs111.next()) {
                                    newSingle= (rs111.getDouble("QuantityOfOne") * ( rs111.getDouble("AvailableQtyStore")-Double.valueOf(quantityNeeded)));
                                     dbOperations.setDataOrDelete("update products set AvailableQtyStore=" + String.format("%.2f",newSingle)+ " where productId=" + rs111.getLong("productId"), "");
                                }
                                ResultSet rsall=dbOperations.getData("select * from productsTypes where productId="+barCode);
                                while(rsall.next())
                                {
                                    
                                    dbOperations.setDataOrDelete("update productsTypes set AvailableQtyStore=" +String.format("%.2f",newSingle/rsall.getDouble("QuantityOfOne"))+ " where id=" +rsall.getLong("id"), "");

                                }
                            } else {
                                ResultSet rs = dbOperations.getData("select * from products where productId=" + barCode);
                                while (rs.next()) {
                                    dbOperations.setDataOrDelete("update products set AvailableQtyStore=" + String.format("%.2f",(rs.getDouble("AvailableQtyStore")-Double.valueOf(quantityNeeded) )) + " where productId=" + barCode, "");
                                    ResultSet rs2 = dbOperations.getData("select * from productsTypes where productId=" + barCode);
                                    while (rs2.next()) {
//                                        AfterPoint = "" + ((rs2.getDouble("AvailableQtyStore")) / rs2.getDouble("QuantityOfOne") - Double.valueOf(quantityNeeded));
//                                        if (AfterPoint.substring(AfterPoint.indexOf(".") + 1, AfterPoint.length()).equals("0")) {
                                            dbOperations.setDataOrDelete("update productsTypes set "
                                                    + "AvailableQtyStore=" + String.format("%.2f",((rs.getDouble("AvailableQtyStore") /rs2.getDouble("QuantityOfOne")) - Double.valueOf(quantityNeeded)))+ " where id=" + rs2.getLong("id"), "");
                                        }

                                    }
//                                }
                            }
                          if (productname.contains("_")) {
                                ResultSet rs111 = dbOperations.getData("select * from productsTypes where productTypeName='" + productname + "'");
                                double newSingle=0;
                                if (rs111.next()) {
                                    newSingle= (rs111.getDouble("QuantityOfOne") * ( rs111.getDouble("AvailableQtyStore")+Double.valueOf(quantityNeeded)));
                                     dbOperations.setDataOrDelete("update products set AvailableQtyStore=" + String.format("%.2f", newSingle)+ " where productId=" + rs111.getLong("productId"), "");
                                }
                                ResultSet rsall=dbOperations.getData("select * from productsTypes where productId="+barCode);
                                while(rsall.next())
                                {
                                    
                                    dbOperations.setDataOrDelete("update productsTypes set AvailableQtyStore=" + String.format("%.2f", (newSingle/rsall.getDouble("QuantityOfOne"))) + " where id=" +rsall.getLong("id"), "");

                                }
                            } else {
                                ResultSet rs = dbOperations.getData("select * from products where productId=" + barCode);
                                while (rs.next()) {
                                    dbOperations.setDataOrDelete("update products set AvailableQtyStore=" +String.format("%.2f", (rs.getDouble("AvailableQtyStore")+Double.valueOf(quantityNeeded) )) + " where productId=" + barCode, "");
                                    ResultSet rs2 = dbOperations.getData("select * from productsTypes where productId=" + barCode);
                                    while (rs2.next()) {
//                                        AfterPoint = "" + ((rs.getDouble("AvailableQtyStore")) / rs2.getDouble("QuantityOfOne") + Double.valueOf(quantityNeeded));
//                                        if (AfterPoint.substring(AfterPoint.indexOf(".") + 1, AfterPoint.length()).equals("0")) {
                                            dbOperations.setDataOrDelete("update productsTypes set AvailableQtyStore=" + String.format("%.2f",((rs.getDouble("AvailableQtyStore")) /rs2.getDouble("QuantityOfOne") + Double.valueOf(quantityNeeded)) )+ " where id=" + rs2.getLong("id"), "");
                                        }

//                                    }
                                }
                            }
                        } else {

                        if (productname.contains("_")) {
                                ResultSet rs111 = dbOperations.getData("select * from productsTypes where productTypeName='" + productname + "'");
                                double newSingle=0;
                                if (rs111.next()) {
                                    newSingle= (rs111.getDouble("QuantityOfOne") * ( rs111.getDouble("AvailableQtyStore")+Double.valueOf(quantityNeeded)));
                                     dbOperations.setDataOrDelete("update products set AvailableQtyStore=" + String.format("%.2f",newSingle)+ " where productId=" + rs111.getLong("productId"), "");
                                }
                                ResultSet rsall=dbOperations.getData("select * from productsTypes where productId="+barCode);
                                while(rsall.next())
                                {
                                    
                                    dbOperations.setDataOrDelete("update productsTypes set AvailableQtyStore=" +String.format("%.2f", (newSingle/rsall.getDouble("QuantityOfOne")) )+ " where id=" +rsall.getLong("id"), "");

                                }
                            } else {
                                ResultSet rs = dbOperations.getData("select * from products where productId=" + barCode);
                                while (rs.next()) {
                                    dbOperations.setDataOrDelete("update products set AvailableQtyStore=" + String.format("%.2f",(rs.getDouble("AvailableQtyStore")+Double.valueOf(quantityNeeded) ) )+ " where productId=" + barCode, "");
                                    ResultSet rs2 = dbOperations.getData("select * from productsTypes where productId=" + barCode);
                                    while (rs2.next()) {
//                                        AfterPoint = "" + ((rs2.getDouble("AvailableQtyStore")) / rs2.getDouble("QuantityOfOne") + Double.valueOf(quantityNeeded));
//                                        if (AfterPoint.substring(AfterPoint.indexOf(".") + 1, AfterPoint.length()).equals("0")) {
                                            dbOperations.setDataOrDelete("update productsTypes set AvailableQtyStore=" + String.format("%.2f",((rs.getDouble("AvailableQtyStore")) /rs2.getDouble("QuantityOfOne")) + Double.valueOf(quantityNeeded)) + " where id=" + rs2.getLong("id"), "");
                                        }

                                    }
                                }
//                            }
                        }
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }

            try {
                double finaltotal = Double.parseDouble(txtPriceOfAll1.getText());
                double finalRemained = Double.parseDouble(txtRemained.getText());
                String Status = null;
                if (finalRemained == 0.0) {
                    Status = "تم الاستلام وتم الدفع";
                } else if (finalRemained < finaltotal) {
                    Status = "تم الاستلام ومتبقي جزء";
                } else if (finaltotal == finalRemained) {
                    Status = "تم الاستلام ولم يتم الدفع";
                }
//                ResultSet resultSales=dbOperations.getData("select * from salesSuppliers where INID="+INid);
//                if(resultSales.next())
//                {
//                       dbOperations.setDataOrDelete("update salesSuppliers set "
//                        + "supplierName='"+txtSupplierName.getSelectedItem().toString()
//                        + "',Total_Quantity="+txtTotalQuantity.getText()
//                        + ",AllTotal="+txtPriceOfAll1.getText()
//                        + ",payed="+txtPayedValue.getText()
//                        + ",Status='"+Status
//                        + "',Remained="+txtRemained.getText()
//                        + ",casherName='"+ txtCasherName.getText()
//                        + "',DayDate='"+txtSearchPurchaseDate.getText()
//                        + "',ReceiverName='"+txtReceiverNameForCart.getSelectedItem().toString()
//                        + "',Note='"+txtsupplierNotes.getText()
//                        + "',ArrivalDate='"+ txtPurchaseDate.getText()
//                        + "',companyAddress='"+ txtCompanyAddress.getText() 
//                        + "',storeAddress='"+storeAddress+"' where INID="+INid,"");
//                }else 
//                {
                dbOperations.setDataOrDelete("insert into salesSuppliers ("
                        + "INID,"
                        + "supplierName,"
                        + "Total_Quantity,"
                        + "AllTotal,"
                        + "payed,"
                        + "Status,"
                        + "Remained,"
                        + "casherName,"
                        + "DayDate,"
                        + "ReceiverName,"
                        + "Note,"
                        + "ArrivalDate,"
                        + "companyAddress,"
                        + "storeAddress) values("
                        + INid
                        + ",'" + txtSupplierName.getSelectedItem().toString()
                        + "'," + txtTotalQuantity.getText()
                        + "," + txtPriceOfAll1.getText()
                        + "," + txtPayedValue.getText()
                        + ",'" + Status
                        + "'," + txtRemained.getText()
                        + ",'" + txtCasherName.getText()
                        + "','" + txtSearchPurchaseDate.getText()
                        + "','" + txtReceiverNameForCart.getSelectedItem().toString()
                        + "','" + txtsupplierNotes.getText()
                        + "','" + txtPurchaseDate.getText()
                        + "','" + txtCompanyAddress.getText() + "','" + storeAddress + "')", "");
//                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, ex);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }

            try {
                new barcodeGenerator(txtPurchaseNumberSearch.getText()).generate();
                HashMap para = new HashMap();
                para.put("Inv_para", Integer.parseInt(txtPurchaseNumberSearch.getText()));  // inv_id  is ireport parameter name
                para.put("Inv_Type", "فاتورة استلام:");
                ReportView r = new ReportView("src\\reports\\suppliersReport.jasper", para);
                r.view();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
            try {
                new barcodeGenerator(txtPurchaseNumberSearch.getText()).generate();
                HashMap para = new HashMap();
                para.put("Inv_para", Integer.parseInt(txtPurchaseNumberSearch.getText()));  // inv_id  is ireport parameter name
                ReportView r = new ReportView("src\\reports\\purchaseLargeReport.jasper", para);
                r.view();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
            try {
                if (!txtPayedValue.getText().equals("0.0")) {
                    int x = JOptionPane.showConfirmDialog(null, "<html><h1>هل تريد خصم القيمة المدفوعة من الخزنة؟</h1></html>", "اختر", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                    if (x == 0) {
                        ResultSet rs = dbOperations.getData("select * from capenat where id=(select max(id) from capenat)");
                        if (rs.next()) {
                            dbOperations.setDataOrDelete("update capenat set "
                                    + "realValue=" + rs.getDouble("afterEdictvalue")
                                    + ",editedvalue=" + txtPayedValue.getText()
                                    + ",afterEdictvalue=" + (rs.getDouble("afterEdictvalue") - Double.valueOf(txtPayedValue.getText()))
                                    + ",OperationType='" + "سحب استلام منتجات من : " + txtSupplierName.getSelectedItem() + " ورقم الطلب: " + txtPurchaseNumberSearch.getText() + "',"
                                    + "date='" + txtPurchaseDate.getText()
                                    + "' where id=" + rs.getLong("id"),
                                    "تم سحب المبلغ المدفوع من الخزنة");

                        }
                    }
                }
                ResultSet rs3 = dbOperations.getData("select * from suppliers where supplierName='" + txtSupplierName.getSelectedItem() + "'");
                if (rs3.next()) {
                    if ((rs3.getDouble("Remained")+Double.valueOf(txtRemained.getText()) < 0)) {
                        dbOperations.setDataOrDelete("update suppliers set Remained=" + (rs3.getDouble("Remained") + Double.valueOf(txtRemained.getText()) * (-1)) + " where supplierName='" + txtSupplierName.getSelectedItem() + "'", "تم تسجيل المتبقي");
                        dbOperations.setDataOrDelete("insert into payoperationforsuppliers (supplierName,receiverName,remainedBefor,payed,remainedAfter,operationdetails,date,purchaseNumber) "
                                + "values('"
                                + txtSupplierName.getSelectedItem().toString()
                                + "','" + txtReceiverNameForCart.getSelectedItem().toString()
                                + "'," + String.format("%.2f",remainedForOperation)
                                + "," + String.format("%.2f",payedForOperationPaySuppliers)
                                + "," + Double.valueOf(txtRemained.getText())
                                + ",'" + "دفع استلام منتجات : " + " ورقم الطلب: " + txtPurchaseNumberSearch.getText()
                                + "','" + txtPurchaseDate.getText() + "'," + txtPurchaseNumberSearch.getText() + ")", "");
                    } else {
                        dbOperations.setDataOrDelete("update suppliers set Remained=" + (rs3.getDouble("Remained") + Double.valueOf(txtRemained.getText())) + " where supplierName='" + txtSupplierName.getSelectedItem() + "'", "تم تسجيل المتبقي");
                        dbOperations.setDataOrDelete("insert into payoperationforsuppliers (supplierName,receiverName,remainedBefor,payed,remainedAfter,operationdetails,date,purchaseNumber) "
                                + "values('"
                                + txtSupplierName.getSelectedItem().toString()
                                + "','" + txtReceiverNameForCart.getSelectedItem().toString()
                                + "'," +  String.format("%.2f",remainedForOperation)
                                + "," +  String.format("%.2f",payedForOperationPaySuppliers)
                                + "," + Double.valueOf(txtRemained.getText())
                                + ",'" + "دفع استلام منتجات : " + " ورقم الطلب: " + txtPurchaseNumberSearch.getText()
                                + "','" + txtPurchaseDate.getText() + "'," + txtPurchaseNumberSearch.getText() + ")", "");
                    }
                    ResultSet rsssd = dbOperations.getData("select * from payoperationforsuppliers where supplierName='" + txtSupplierName.getSelectedItem().toString() + "' order by id Desc limit 1");
                    if (rsssd.next()) {
                        HashMap para = new HashMap();
                        para.put("Inv_para", rsssd.getLong("id"));  // inv_id  is ireport parameter name
                        ReportView r = new ReportView("src\\reports\\paySuppliersArrival.jasper", para);
                        r.view();
                    }

                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
            try {
                ResultSet rs = dbOperations.getData("select * from salesSuppliers where INID=" + txtINIDOfGetProductsInvoice.getText());
                if (rs.next()) {
                    String id = txtINIDOfGetProductsInvoice.getText();
                    dbOperations.setDataOrDelete("Update extra set val='" + id + "' where exid=2", "");
                    txtINIDOfGetProductsInvoice.setText("" + Integer.parseInt(txtINIDOfGetProductsInvoice.getText()) + 1);
                }
            } catch (Exception e) {
            }
            DefaultTableModel dtm = (DefaultTableModel) jTableAddForCaty.getModel();
            dtm.setRowCount(0);
            txtPriceOfAll1.setText("0.0");
            txtPayedValue.setText("0.0");
            txtRemained.setText("0.0");
            txtTotalQuantity.setText("0.0");
            loadSupplierOperationsData();
            btnArrivalApproval.setEnabled(true);
        }
    }//GEN-LAST:event_btnArrivalApprovalActionPerformed

    private void txtCompanyAddressFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCompanyAddressFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCompanyAddressFocusGained

    private void txtCompanyAddressFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCompanyAddressFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCompanyAddressFocusLost

    private void txtCompanyAddressKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCompanyAddressKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCompanyAddressKeyReleased

    private void txtSupplierNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSupplierNameActionPerformed
        try {
            ResultSet rs = dbOperations.getData("select * from suppliers where supplierName='" + txtSupplierName.getSelectedItem().toString() + "'");
            while (rs.next()) {
                txtCompanyAddress.setText(rs.getString("Address"));
                txtSupplierPhone.setText(rs.getString("phone"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_txtSupplierNameActionPerformed

    private void txtStorePhoneNumberFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtStorePhoneNumberFocusGained
        if (txtStorePhoneNumber.getText().equals("01020005212")) {
            txtStorePhoneNumber.setText("");
        }
    }//GEN-LAST:event_txtStorePhoneNumberFocusGained

    private void txtStorePhoneNumberFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtStorePhoneNumberFocusLost
        if (txtStorePhoneNumber.getText().equals("")) {
            txtStorePhoneNumber.setText("01020005212");
        }
    }//GEN-LAST:event_txtStorePhoneNumberFocusLost

    private void txtSatusSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSatusSearchActionPerformed
        DefaultTableModel dtm = (DefaultTableModel) jTableSalesSuppliers.getModel();
        dtm.setRowCount(0);
        try {
            if (txtSupplierNameSearch.getSelectedIndex() != 0 && txtMonthSearchSuppliers.getSelectedIndex()!=0) {
                ResultSet rs = dbOperations.getData("select * from salesSuppliers where "
                        + "Status='" + txtSatusSearch.getSelectedItem().toString() + "'"
                        + " and supplierName='" + txtSupplierNameSearch.getSelectedItem().toString() + "' and DayDate like'" + txtYearSearchSuppliers.getSelectedItem().toString() + "-" + txtMonthSearchSuppliers.getSelectedItem().toString() + "_______________%'");
                while (rs.next()) {
                    dtm.addRow(new Object[]{
                        rs.getLong("INID"),
                        rs.getString("supplierName"),
                        rs.getDouble("Total_Quantity"),
                        rs.getDouble("AllTotal"),
                        rs.getDouble("payed"),
                        rs.getDouble("Remained"),
                        rs.getString("DayDate"),
                        rs.getString("ArrivalDate"),
                        rs.getString("ReceiverName"),
                        rs.getString("Status"),
                        rs.getString("casherName"),
                        rs.getString("Note")
                    });
                }
            }else if(txtSupplierNameSearch.getSelectedIndex() != 0 && txtHandDateSaerchSuppliers.getDate()!=null)
            {
                ResultSet rs = dbOperations.getData("select * from salesSuppliers where "
                        + "Status='" + txtSatusSearch.getSelectedItem().toString() + "'"
                        + " and supplierName='" + txtSupplierNameSearch.getSelectedItem().toString() + "' and DayDate like'" +df.format(txtHandDateSaerchSuppliers.getDate())+ "____________%'");
                while (rs.next()) {
                    dtm.addRow(new Object[]{
                        rs.getLong("INID"),
                        rs.getString("supplierName"),
                        rs.getDouble("Total_Quantity"),
                        rs.getDouble("AllTotal"),
                        rs.getDouble("payed"),
                        rs.getDouble("Remained"),
                        rs.getString("DayDate"),
                        rs.getString("ArrivalDate"),
                        rs.getString("ReceiverName"),
                        rs.getString("Status"),
                        rs.getString("casherName"),
                        rs.getString("Note")
                    });
                }
            }else if(txtMonthSearchSuppliers.getSelectedIndex()!=0)
            {
                    ResultSet rs = dbOperations.getData("select * from salesSuppliers where "
                        + "Status='" + txtSatusSearch.getSelectedItem().toString() + "'"
                        + " and DayDate like'" + txtYearSearchSuppliers.getSelectedItem().toString() + "-" + txtMonthSearchSuppliers.getSelectedItem().toString() + "_______________%'");
                while (rs.next()) {
                    dtm.addRow(new Object[]{
                        rs.getLong("INID"),
                        rs.getString("supplierName"),
                        rs.getDouble("Total_Quantity"),
                        rs.getDouble("AllTotal"),
                        rs.getDouble("payed"),
                        rs.getDouble("Remained"),
                        rs.getString("DayDate"),
                        rs.getString("ArrivalDate"),
                        rs.getString("ReceiverName"),
                        rs.getString("Status"),
                        rs.getString("casherName"),
                        rs.getString("Note")
                    });
                }
            }else 
            {
                  ResultSet rs = dbOperations.getData("select * from salesSuppliers where "
                        + "Status='" + txtSatusSearch.getSelectedItem().toString() + "'"
                        + " and DayDate like'" +df.format(txtHandDateSaerchSuppliers.getDate())+ "____________%'");
                while (rs.next()) {
                    dtm.addRow(new Object[]{
                        rs.getLong("INID"),
                        rs.getString("supplierName"),
                        rs.getDouble("Total_Quantity"),
                        rs.getDouble("AllTotal"),
                        rs.getDouble("payed"),
                        rs.getDouble("Remained"),
                        rs.getString("DayDate"),
                        rs.getString("ArrivalDate"),
                        rs.getString("ReceiverName"),
                        rs.getString("Status"),
                        rs.getString("casherName"),
                        rs.getString("Note")
                    });
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_txtSatusSearchActionPerformed

    private void jTableSalesSuppliersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableSalesSuppliersMouseClicked
        try {
            DefaultTableModel dtm = (DefaultTableModel) jTableSalesSuppliers.getModel();
            int index = jTableSalesSuppliers.getSelectedRow();
            String inid = jTableSalesSuppliers.getValueAt(index, 0).toString();
            int v = JOptionPane.showConfirmDialog(null, "<html><h1>هل تريد عرض التفاصيل؟</h1></html>", "", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (v == 0) {
                try {
                    new barcodeGenerator(inid).generate();
                    HashMap para = new HashMap();
                    para.put("Inv_para", Integer.parseInt(inid));  // inv_id  is ireport parameter name
                    para.put("Inv_Type", "فاتورة طلب شراء:");
                    ReportView r = new ReportView("src\\reports\\suppliersReport.jasper", para);
                    r.view();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
                try {
                    new barcodeGenerator(inid).generate();
                    HashMap para = new HashMap();
                    para.put("Inv_para", Integer.parseInt(inid));  // inv_id  is ireport parameter name
                    ReportView r = new ReportView("src\\reports\\purchaseLargeReport.jasper", para);
                    r.view();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            } else {
                int a = JOptionPane.showConfirmDialog(null, "<html><h1>هل تريد الغاء هذا الطلب؟</h1></html>", "اختر", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                if (a == 0) {
                    if (!jTableSalesSuppliers.getValueAt(index, 4).toString().equals("0.0")) {
                        int b = JOptionPane.showConfirmDialog(null, "<html><h1>اضافة القيمة المدفوعة الي الخزنة؟</h1></html>", "", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                        if (b == 0) {
                            ResultSet rs = dbOperations.getData("select * from capenat where id=(select max(id) from capenat)");
                            if (rs.next()) {
                                dbOperations.setDataOrDelete("insert into capenat (realValue,editedvalue,afterEdictvalue,OperationType,date) values(" + rs.getDouble("afterEdictvalue")
                                        + "," + jTableSalesSuppliers.getValueAt(index, 4).toString()
                                        + "," + (rs.getDouble("afterEdictvalue") + Double.valueOf(jTableSalesSuppliers.getValueAt(index, 4).toString()))
                                        + ",'توريد الغاء طلب شراء','" + txtPurchaseDate.getText() + "')", "");
                            }
                            dbOperations.setDataOrDelete("delete from cartsuppliers where INID=" + jTableSalesSuppliers.getValueAt(index, 0).toString(), "");
                            dbOperations.setDataOrDelete("delete from salesSuppliers where INID=" + jTableSalesSuppliers.getValueAt(index, 0).toString(), "");
                            dtm.removeRow(index);
                        } else {
                            dbOperations.setDataOrDelete("delete from cartsuppliers where INID=" + jTableSalesSuppliers.getValueAt(index, 0).toString(), "");
                            dbOperations.setDataOrDelete("delete from salesSuppliers where INID=" + jTableSalesSuppliers.getValueAt(index, 0).toString(), "");
                            dtm.removeRow(index);
                        }
                    } else {
                        dbOperations.setDataOrDelete("delete from cartsuppliers where INID=" + jTableSalesSuppliers.getValueAt(index, 0).toString(), "");
                        dbOperations.setDataOrDelete("delete from salesSuppliers where INID=" + jTableSalesSuppliers.getValueAt(index, 0).toString(), "");
                        dtm.removeRow(index);
                    }

                }
            }

        } catch (Exception e) {
        }
    }//GEN-LAST:event_jTableSalesSuppliersMouseClicked

    private void txtSearchMonthlyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchMonthlyActionPerformed
       txtHandDateSaerchSuppliers.setDate(null);
    }//GEN-LAST:event_txtSearchMonthlyActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        try {
            HashMap para = new HashMap();
            ReportView r = null;
            DefaultTableModel dtm = (DefaultTableModel) jTableLowQuantity.getModel();
            if (txtStoreNameSearchLowQuantity.getSelectedIndex() != 0 &&txtcategorySearchLowQuantity1.getSelectedIndex()!=0) {
                para.put("TITLE","مخزن: "+txtStoreNameSearchLowQuantity.getSelectedItem().toString()+"\nقسم:"+txtcategorySearchLowQuantity1.getSelectedItem().toString());
                r = new ReportView("src\\reports\\lowQuantityReport.jasper", para);
                r.view3(dtm, "src\\reports\\lowQuantityReport.jrxml");
            }else if(txtStoreNameSearchLowQuantity.getSelectedIndex()!=0)
            {
                 para.put("TITLE","مخزن: "+txtStoreNameSearchLowQuantity.getSelectedItem().toString()+"تقرير نواقص : ");
                r = new ReportView("src\\reports\\lowQuantityReport.jasper", para);
                r.view3(dtm, "src\\reports\\lowQuantityReport.jrxml");
            }else if(txtcategorySearchLowQuantity1.getSelectedIndex()!=0)
            {
                para.put("TITLE","قسم: "+txtcategorySearchLowQuantity1.getSelectedItem().toString()+"تقرير نواقص : ");
                r = new ReportView("src\\reports\\lowQuantityReport.jasper", para);
                r.view3(dtm, "src\\reports\\lowQuantityReport.jrxml");
            }else 
            {
                para.put("TITLE","تقرير نواقص");
                r = new ReportView("src\\reports\\lowQuantityReport.jasper", para);
                r.view3(dtm, "src\\reports\\lowQuantityReport.jrxml");
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void txtCheckBoxStoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCheckBoxStoreActionPerformed
         try {
            if(txtCheckBoxMarket.isSelected())
            {
                txtCheckBoxMarket.setSelected(false);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_txtCheckBoxStoreActionPerformed

    private void txtCheckBoxMarketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCheckBoxMarketActionPerformed
        try {
            if(txtCheckBoxStore.isSelected())
            {
                txtCheckBoxStore.setSelected(false);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_txtCheckBoxMarketActionPerformed

    private void txtCategoryPercentageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCategoryPercentageActionPerformed
            try {
            DefaultTableModel dtm = (DefaultTableModel) jTableProductAvailables.getModel();
            dtm.setRowCount(0);
            ResultSet rs1 = dbOperations.getData("select * from products where category='" + txtCategoryPercentage.getSelectedItem().toString() + "' and supplierName='"+txtSupplierNamePercentage.getSelectedItem().toString()+"'");
            while (rs1.next()) {
                dtm.addRow(new Object[]{
                    rs1.getLong("productId"),
                    rs1.getString("productName"),
                    rs1.getString("category"),
                    rs1.getString("storeName"),
                    rs1.getDouble("priceOfBuy"),
                    rs1.getDouble("priceOfSale"),
                    rs1.getDouble("priceOfMarket"),
                    rs1.getDouble("Taked"),
                    rs1.getDouble("AvailableQty"),
                    rs1.getDouble("AvailableQtyStore"),
                    rs1.getString("Note"),
                    rs1.getString("supplierName")
                });
            }
            DefaultTableModel dtm2 = (DefaultTableModel) jTableProductAvailables1.getModel();
            dtm2.setRowCount(0);
            ResultSet rs2 = dbOperations.getData("select * from productsTypes where category='" + txtCategoryPercentage.getSelectedItem().toString() + "' and supplierName='"+txtSupplierNamePercentage.getSelectedItem().toString()+"'");
            while (rs2.next()) {
                dtm2.addRow(new Object[]{
                    rs2.getLong("productId"),
                    rs2.getString("productTypeName"),
                    rs2.getString("category"),
                    rs2.getDouble("priceOfBuy"),
                    rs2.getDouble("priceOfSale"),
                    rs2.getDouble("priceOfMarket"),
                    rs2.getDouble("AvailableQtyStore"),
                    rs2.getDouble("AllQuantity"),
                    rs2.getDouble("QuantityOfOne"),
                     rs2.getString("storeName") ,
                    rs2.getString("Note"),
                    rs2.getString("supplierName")
                   
                });
            }
        } catch (Exception e) {

        }
    }//GEN-LAST:event_txtCategoryPercentageActionPerformed

    private void txtSupplierNamePercentageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSupplierNamePercentageActionPerformed
       
    }//GEN-LAST:event_txtSupplierNamePercentageActionPerformed

    private void txtPercentageValueFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPercentageValueFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPercentageValueFocusGained

    private void txtPercentageValueFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPercentageValueFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPercentageValueFocusLost

    private void txtPercentageValueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPercentageValueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPercentageValueActionPerformed

    private void txtSupplierNameAddProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSupplierNameAddProductActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSupplierNameAddProductActionPerformed

    private void txtPercentageValueKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPercentageValueKeyReleased
        try {
            double percentage=Double.valueOf(txtPercentageValue.getText());
        } catch (NumberFormatException e) {
            txtPercentageValue.setText("0");
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_txtPercentageValueKeyReleased

    private void btnAddPercentageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddPercentageActionPerformed
        if(!txtPercentageValue.getText().equals(0))
        {
            btnAddPercentage.setEnabled(false);
        double Percentage=Double.valueOf(String.format("%.2f",(Double.valueOf(txtPercentageValue.getText())/100)));
        double priceOfBuy=0;
        double priceOfSale=0;
        double priceOfMarket=0;
        double priceOfBuy1=0;
        double priceOfSale1=0;
        double priceOfMarket1=0;
        TableModel tm=jTableProductAvailables.getModel();
        TableModel tm2=jTableProductAvailables1.getModel();
              try {
              for (int i = 0; i < jTableProductAvailables.getRowCount(); i++) {
                  priceOfBuy=Double.valueOf(String.format("%.2f",tm.getValueAt(i,4)));
                  priceOfSale=Double.valueOf(String.format("%.2f",tm.getValueAt(i,5)));
                  priceOfMarket=Double.valueOf(String.format("%.2f",tm.getValueAt(i,6)));
                dbOperations.setDataOrDelete("update products set "
                        + "priceOfBuy="+String.format("%.2f",((Percentage*priceOfBuy)+priceOfBuy))
                        +",priceOfSale="+String.format("%.2f",((Percentage*priceOfSale)+priceOfSale))
                        +",priceOfMarket="+String.format("%.2f",((Percentage*priceOfMarket)+priceOfMarket))
                    +" where productName='"+tm.getValueAt(i,1).toString()+"' and supplierName='"+txtSupplierNamePercentage.getSelectedItem().toString()
                    +"' and category='"+txtCategoryPercentage.getSelectedItem().toString()+"'", "");
        }

             for (int i = 0; i < jTableProductAvailables1.getRowCount(); i++) {
                 priceOfBuy1=Double.valueOf(String.format("%.2f",tm2.getValueAt(i,3)));
                  priceOfSale1=Double.valueOf(String.format("%.2f",tm2.getValueAt(i,4)));
                  priceOfMarket1=Double.valueOf(String.format("%.2f",tm2.getValueAt(i,5)));
                dbOperations.setDataOrDelete("update productsTypes set "
                        + "priceOfBuy="+String.format("%.2f",((Percentage*priceOfBuy1)+priceOfBuy1))
                        +",priceOfSale="+String.format("%.2f",((Percentage*priceOfSale1)+priceOfSale1))
                        +",priceOfMarket="+String.format("%.2f",((Percentage*priceOfMarket1)+priceOfMarket1))
                    +" where productTypeName='"+tm2.getValueAt(i,1).toString()+"' and supplierName='"+txtSupplierNamePercentage.getSelectedItem().toString()
                    +"' and category='"+txtCategoryPercentage.getSelectedItem().toString()+"'", "");
        }
          txtCategoryPercentageActionPerformed(evt);
          
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
              btnAddPercentage.setEnabled(true);
        }
    }//GEN-LAST:event_btnAddPercentageActionPerformed

    private void txtQuantitySingleKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQuantitySingleKeyReleased
       double priceOfOne = 0;
        double QuantityNeeded = 0;
        try {
            priceOfOne = Double.parseDouble(txtPriceOfOne.getText());
            QuantityNeeded = Double.parseDouble(txtQuantitySingle.getText());
            txtFirstPriceSuppliers.setText("" + priceOfOne * QuantityNeeded);
            if (!txtFirstPriceSuppliers.getText().equals("0.0")) {
                btnAddToCart.setEnabled(true);
            } else {
                btnAddToCart.setEnabled(false);
            }
        } catch (NumberFormatException ex) {
            txtFirstPriceSuppliers.setText("0.0");
            btnAddToCart.setEnabled(false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_txtQuantitySingleKeyReleased

    private void txtproductNameAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtproductNameAddActionPerformed
        try {
            ResultSet rs= dbOperations.getData("select * from products where productName='"+txtproductNameAdd.getSelectedItem().toString()+"'");
            if(rs.next())
            {
                txtBarcodeAdd.setText(""+rs.getLong("productId"));
            }
            
            getAllTypeName(Long.parseLong(txtBarcodeAdd.getText()));
//          if(!txtBarcodeAdd.isFocusable())
//          {
//            Robot r=new Robot();
//            txtBarcodeAdd.setRequestFocusEnabled(true);
//            r.keyPress(KeyEvent.VK_CONTROL);
//            r.keyPress(KeyEvent.VK_A);
//            r.keyRelease(KeyEvent.VK_A);
//            r.keyRelease(KeyEvent.VK_CONTROL);
//            r.keyPress(KeyEvent.VK_RIGHT);
//            r.keyRelease(KeyEvent.VK_RIGHT);
//          }
        } catch (Exception e) {
        }
        
    }//GEN-LAST:event_txtproductNameAddActionPerformed

    private void btnAddQuantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddQuantityActionPerformed
        try {
            btnAddQuantity.setEnabled(false);
            double newQuantity=Double.valueOf(txtQuantityAdded.getText());
            double QuantityOfOne=0;
            double AllQuantity=0;
            double productId=0;
            double Taked=0;
            double AvailableQty=0;
            String AfterPoint;
            if (txtMarketOrStore.getSelectedItem().toString().equals("محل")) {
                    if (txtTypeNameCompo.getSelectedItem().equals("نوع")) {
                        double newavailabel=0;
                        ResultSet rsdd=dbOperations.getData("select * from products where productId="+txtBarcodeAdd.getText());
                        if(rsdd.next())
                        {
                            newavailabel=rsdd.getDouble("AvailableQty")+newQuantity;
                        }
                        
                        productsController.updateAvailableWhenReturnFromProductsTable2(newavailabel,
                                Double.valueOf(txtQuantityAdded.getText()),
                                txtBarcodeAdd.getText(), txtMarketOrStore.getSelectedItem().toString());
                        ResultSet rs = dbOperations.getData("select * from productsTypes where productId=" + txtBarcodeAdd.getText());
                        while (rs.next()) {
//                            AfterPoint = String.valueOf(Double.parseDouble(txtQuantityAdded.getText()) / rs.getDouble("QuantityOfOne"));
//                            if (AfterPoint.substring(AfterPoint.indexOf(".") + 1, AfterPoint.length()).equals("0")) {
//                                dbOperations.setDataOrDelete("update productsTypes set AllQuantity=" + (rs.getDouble("AllQuantity") +(Double.valueOf(txtQuantityAdded.getText()) / rs.getDouble("QuantityOfOne"))) + " where id=" + rs.getLong("id"), "");
//                            } else {
                                ResultSet rsw = dbOperations.getData("select * from products where productId=" + txtBarcodeAdd.getText());
                                if (rsw.next()) {
                                    dbOperations.setDataOrDelete("update productsTypes set "
                                            + "AllQuantity=" +String.format("%.2f",  (rsw.getDouble("AvailableQty") /rs.getDouble("QuantityOfOne"))) + " "
                                           + "where id=" + rs.getLong("id"), "");
                                }
//                            }
                        }
                    } else {
                        ResultSet rs1 = dbOperations.getData("select * from productsTypes where "
                                + "productTypeName='" + txtproductNameAdd.getSelectedItem().toString()+ "_" + txtTypeNameCompo.getSelectedItem() + "'");
                        if (rs1.next()) {
                            QuantityOfOne = rs1.getDouble("QuantityOfOne");
//                            AllQuantity=rs1.getDouble("AllQuantity");
                            productId = rs1.getLong("productId");
                        }
                        ResultSet rs2 = dbOperations.getData("select * from products where productId=" + productId);
                        if (rs2.next()) {
                            AvailableQty = rs2.getDouble("AvailableQty");
                        }
//                        dbOperations.setDataOrDelete("update productsTypes set"
//                                + " AllQuantity=" + (Double.valueOf(txtQuantityAdded.getText())+AllQuantity)+ " where "
//                                + "productTypeName='" + txtproductNameAdd.getSelectedItem().toString()+ "_" + txtTypeNameCompo.getSelectedItem().toString() + "'", "");
                         double newAvailableQty=(AvailableQty +Double.valueOf(txtQuantityAdded.getText()) * QuantityOfOne);
                        dbOperations.setDataOrDelete("update products set "
                                + "AvailableQty=" + String.format("%.2f",newAvailableQty)
                                + " where productId=" + productId, "");
                        ResultSet rs3=dbOperations.getData("select * from productsTypes where productId="+productId);
                        while(rs3.next())
                        {
                            double QuantityOfOneOfAll=rs3.getDouble("QuantityOfOne");
                               dbOperations.setDataOrDelete("update productsTypes set"
                                + " AllQuantity=" +String.format("%.2f",((double)newAvailableQty/(double)QuantityOfOneOfAll))+ " where "
                                + "id=" +rs3.getLong("id"), "");
                        }
                      
                       
                    }
                } else {
                      if (txtTypeNameCompo.getSelectedItem().equals("نوع")) {
                        double newavailabel=0;
                        ResultSet rsdd=dbOperations.getData("select * from products where productId="+txtBarcodeAdd.getText());
                        if(rsdd.next())
                        {
                            newavailabel=rsdd.getDouble("AvailableQtyStore")+newQuantity;
                        }
                        
                        productsController.updateAvailableWhenReturnFromProductsTable2(newavailabel,
                                Double.valueOf(txtQuantityAdded.getText()),
                                txtBarcodeAdd.getText(), txtMarketOrStore.getSelectedItem().toString());
                        ResultSet rs = dbOperations.getData("select * from productsTypes where productId=" + txtBarcodeAdd.getText());
                        while (rs.next()) {
//                            AfterPoint = String.valueOf(Double.parseDouble(txtQuantityAdded.getText()) / rs.getDouble("QuantityOfOne"));
//                            if (AfterPoint.substring(AfterPoint.indexOf(".") + 1, AfterPoint.length()).equals("0")) {
//                                dbOperations.setDataOrDelete("update productsTypes set "
//                                        + "AvailableQtyStore=" + (rs.getDouble("AvailableQtyStore") +(Double.valueOf(txtQuantityAdded.getText()) / rs.getDouble("QuantityOfOne"))) + " where id=" + rs.getLong("id"), "");
//                            } else {
                                ResultSet rsw = dbOperations.getData("select * from products where productId=" + txtBarcodeAdd.getText());
                                if (rsw.next()) {
                                    dbOperations.setDataOrDelete("update productsTypes set "
                                            + "AvailableQtyStore=" + String.format("%.2f",((double)rsw.getDouble("AvailableQtyStore") /(double)rs.getDouble("QuantityOfOne")) )+ " "
                                            + "where id=" + rs.getLong("id"), "");
                                }
//                            }
                        }
                    }else {
                   ResultSet rs1 = dbOperations.getData("select * from productsTypes where "
                                + "productTypeName='" + txtproductNameAdd.getSelectedItem().toString()+ "_" + txtTypeNameCompo.getSelectedItem() + "'");
                        if (rs1.next()) {
                            QuantityOfOne = rs1.getDouble("QuantityOfOne");
                            AllQuantity=rs1.getDouble("AvailableQtyStore");
                            productId = rs1.getLong("productId");
                        }
                        ResultSet rs2 = dbOperations.getData("select * from products where productId=" + productId);
                        if (rs2.next()) {
                            Taked = rs2.getDouble("Taked");
                            AvailableQty = rs2.getDouble("AvailableQtyStore");
                        }
//                        dbOperations.setDataOrDelete("update productsTypes set"
//                                + " AvailableQtyStore=" + (Double.valueOf(txtQuantityAdded.getText())+AllQuantity)+ " where "
//                                + "productTypeName='" + txtproductNameAdd.getSelectedItem().toString()+ "_" + txtTypeNameCompo.getSelectedItem().toString() + "'", "");
                         double newAvailableQty=(AvailableQty +(Double.valueOf(txtQuantityAdded.getText()) * QuantityOfOne));
                        dbOperations.setDataOrDelete("update products set "
                                + "AvailableQtyStore=" + String.format("%.2f",newAvailableQty)
                                + " where productId=" + productId, "");
                        ResultSet rs3=dbOperations.getData("select * from productsTypes where productId="+productId);
                        while(rs3.next())
                        {
                            double QuantityOfOneOfAll=rs3.getDouble("QuantityOfOne");
                               dbOperations.setDataOrDelete("update productsTypes set"
                                + " AvailableQtyStore=" +String.format("%.2f",((double)newAvailableQty/(double)QuantityOfOneOfAll))+ " where "
                                +"id=" +rs3.getLong("id"), "");
                        }
                      
                       
                    }
                }
              getAllRecordsForFirstTable();
              txtBarcodeAdd.setText("");
              txtQuantityAdded.setText("");
              txtAvailableQtyMarketAdd.setText("0");
              txtAvailableQtyStoreAdd.setText("0");
              txtTypeNameCompo.setSelectedItem("نوع");
              txtproductNameAdd.setSelectedIndex(0);
              txtMarketOrStore.setSelectedIndex(0); 
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_btnAddQuantityActionPerformed

    public void getAllTypeName(long barcode)
    {
        try {
            txtTypeNameCompo.removeAllItems();
            txtTypeNameCompo.addItem("نوع");
             ResultSet rs=dbOperations.getData("select * from productsTypes where productId="+barcode);
             while(rs.next())
             {
                 txtTypeNameCompo.addItem(rs.getString("productTypeName").substring(rs.getString("productTypeName").indexOf("_")+1,rs.getString("productTypeName").length()));
             }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
     public void getAllproductsNamesCompoDelete(String productName)
    {
        try {
            ResultSet rs=dbOperations.getData("select * from products where productName like'%"+productName+"%'");
            txtproductNameAdd.removeAllItems();
            txtproductNameAdd.addItem("اسم المنتج"); 
            while(rs.next())
            {
                txtproductNameAdd.addItem(rs.getString("productName"));
            }
        } catch (Exception e) {

        }
    }
    public void getAllproductsNamesCompo()
    {
        try {
            ResultSet rs=dbOperations.getData("select * from products");
            while(rs.next())
            {
                txtproductNameAdd.addItem(rs.getString("productName"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void txtBarcodeAddKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBarcodeAddKeyReleased
        try {
            long s=Long.parseLong(txtBarcodeAdd.getText());
           ResultSet rs= dbOperations.getData("select * from products where productId="+s);
            if(rs.next())
            {
                txtproductNameAdd.setSelectedItem(rs.getString("productName"));
                txtAvailableQtyMarketAdd.setText(""+rs.getDouble("availableQty"));
                txtAvailableQtyStoreAdd.setText(""+rs.getDouble("availableQtystore"));
                getAllTypeName(s);
            }else 
            {
                txtAvailableQtyMarketAdd.setText("0");
                txtAvailableQtyStoreAdd.setText("0");
                txtproductNameAdd.setSelectedIndex(0);
                btnAddQuantity.setEnabled(false);
            }
            
        } catch (NumberFormatException e) {
            txtBarcodeAdd.setText("");
            btnAddQuantity.setEnabled(false);
              txtQuantityAdded.setText("");
              txtAvailableQtyMarketAdd.setText("0");
              txtAvailableQtyStoreAdd.setText("0");
              txtTypeNameCompo.setSelectedItem("نوع");
              txtproductNameAdd.setSelectedIndex(0);
              txtMarketOrStore.setSelectedIndex(0); 
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_txtBarcodeAddKeyReleased

    private void txtBarcodeAddKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBarcodeAddKeyPressed
       
    }//GEN-LAST:event_txtBarcodeAddKeyPressed

    private void txtQuantityAddedKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQuantityAddedKeyReleased
        try {
            double quantity=Double.valueOf(txtQuantityAdded.getText());
            if(quantity>0)
            {
                btnAddQuantity.setEnabled(true);
            }else 
            {
                btnAddQuantity.setEnabled(false);
            }
        }
        catch (NumberFormatException e) {
            btnAddQuantity.setEnabled(false);
        }catch (Exception e) {
        }
    }//GEN-LAST:event_txtQuantityAddedKeyReleased

    private void btnReports1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReports1ActionPerformed
      try {
                    HashMap para = new HashMap();
                    para.put("copyRight", (char)169+"Mo_Software:01091499680");
                    ReportView r = new ReportView("src\\reports\\categoriesTotalMony.jasper", para);
                    r.view();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
    }//GEN-LAST:event_btnReports1ActionPerformed

    private void txtMarketOrStoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMarketOrStoreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMarketOrStoreActionPerformed

    private void txtQuantityForSaleKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQuantityForSaleKeyReleased
        try {
            double QuantityForsale=Double.parseDouble(txtQuantityForSale.getText());
            if(txtQuantityForSale.getText().contains("-"))
            {
                txtQuantityForSale.setText("0");
            }
        } catch (NumberFormatException e) {
            txtQuantityForSale.setText("0");
        }
        catch (Exception e) {
        }
        
    }//GEN-LAST:event_txtQuantityForSaleKeyReleased

    private void txtQuantityStoreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQuantityStoreKeyReleased
      try {
            double QuantityForsale=Double.parseDouble(txtQuantityStore.getText());
            if(txtQuantityStore.getText().contains("-"))
            {
                txtQuantityStore.setText("0");
            }
        } catch (NumberFormatException e) {
             txtQuantityStore.setText("0");
        }catch(Exception e)
        {
             JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_txtQuantityStoreKeyReleased

    private void txtQuantityWarningKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQuantityWarningKeyReleased
        try {
            double QuantityForsale=Double.parseDouble(txtQuantityWarning.getText());
            if(txtQuantityWarning.getText().contains("-"))
            {
                txtQuantityWarning.setText("0");
            }
        } catch (NumberFormatException e) {
             txtQuantityWarning.setText("0");
        }catch(Exception e)
        {
             JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_txtQuantityWarningKeyReleased

    private void txtQuantityOfOneKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQuantityOfOneKeyReleased
        try {
            double QuantityForsale=Double.parseDouble(txtQuantityOfOne.getText());
            if(txtQuantityOfOne.getText().contains("-"))
            {
                txtQuantityOfOne.setText("");
            }
        } catch (NumberFormatException e) {
             txtQuantityOfOne.setText("");
        }catch(Exception e)
        {
             JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_txtQuantityOfOneKeyReleased

    private void txtPriceOfBuyKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPriceOfBuyKeyReleased
       try {
            double QuantityForsale=Double.parseDouble(txtPriceOfBuy.getText());
             if(!txtTypeVariable.getText().equals("__"))
            {
                double QuantityOfOne=Double.parseDouble(txtQuantityOfOne.getText());
                   txtPriceOfBuyTypes.setText(""+(String.format("%.2f",(QuantityForsale*QuantityOfOne))));
            }
            if(txtPriceOfBuy.getText().contains("-"))
            {
                txtPriceOfBuy.setText("");
                  txtPriceOfBuyTypes.setText("");
            }
        
        } catch (NumberFormatException e) {
             txtPriceOfBuy.setText("");
              txtPriceOfBuyTypes.setText("");
        }catch(Exception e)
        {
             JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_txtPriceOfBuyKeyReleased

    private void txtPriceOfBuyTypesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPriceOfBuyTypesKeyReleased
          try {
            double QuantityForsale=Double.parseDouble(txtPriceOfBuyTypes.getText());
            if(txtPriceOfBuyTypes.getText().contains("-"))
            {
                txtPriceOfBuyTypes.setText("");
            }
        } catch (NumberFormatException e) {
             txtPriceOfBuyTypes.setText("");
        }catch(Exception e)
        {
             JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_txtPriceOfBuyTypesKeyReleased

    private void txtPriceOfSaleKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPriceOfSaleKeyReleased
        try {
            double QuantityForsale=Double.parseDouble(txtPriceOfSale.getText());
            if(!txtPriceOfBuyTypesVariable.getText().equals("__"))
            {
                double QuantityOfOne=Double.parseDouble(txtQuantityOfOne.getText());
                txtPriceOfSaleType.setText(""+String.format("%.2f",(QuantityOfOne*QuantityForsale)));
            }
            if(txtPriceOfSale.getText().contains("-"))
            {
                txtPriceOfSale.setText("");
                txtPriceOfSaleType.setText("");
            }
        } catch (NumberFormatException e) {
             txtPriceOfSale.setText("");
        }catch(Exception e)
        {
             JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_txtPriceOfSaleKeyReleased

    private void txtPriceOfSaleTypeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPriceOfSaleTypeKeyReleased
       try {
            double QuantityForsale=Double.parseDouble(txtPriceOfSaleType.getText());
            if(txtPriceOfSaleType.getText().contains("-"))
            {
                txtPriceOfSaleType.setText("");
            }
        } catch (NumberFormatException e) {
             txtPriceOfSaleType.setText("");
        }catch(Exception e)
        {
             JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_txtPriceOfSaleTypeKeyReleased

    private void txtMarketPriceKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMarketPriceKeyReleased
        try {
            double QuantityForsale=Double.parseDouble(txtMarketPrice.getText());
            if(!txtPriceOfSaleTypeVarialble.getText().equals("__"))
            {
                double QuantityOfOne=Double.parseDouble(txtQuantityOfOne.getText());
                txtMarketPriceTypes.setText(""+String.format("%.2f", (QuantityOfOne*QuantityForsale)));
            }
            if(txtMarketPrice.getText().contains("-"))
            {
                txtMarketPrice.setText("");
            }
        } catch (NumberFormatException e) {
             txtMarketPrice.setText("");
             txtMarketPriceTypes.setText("");
        }catch(Exception e)
        {
             JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_txtMarketPriceKeyReleased

    private void txtMarketPriceTypesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMarketPriceTypesKeyReleased
         try {
            double QuantityForsale=Double.parseDouble(txtMarketPriceTypes.getText());
            if(txtMarketPriceTypes.getText().contains("-"))
            {
                txtMarketPriceTypes.setText("");
            }
        } catch (NumberFormatException e) {
             txtMarketPriceTypes.setText("");
        }catch(Exception e)
        {
             JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_txtMarketPriceTypesKeyReleased

    private void txtproductNameSearchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtproductNameSearchFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtproductNameSearchFocusGained

    private void txtproductNameSearchFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtproductNameSearchFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtproductNameSearchFocusLost

    private void txtproductNameSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtproductNameSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtproductNameSearchActionPerformed

    private void txtproductNameSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtproductNameSearchKeyReleased
        try {
            getAllercordsWithProductName(txtproductNameSearch.getText());
        } catch (NumberFormatException ex) {
            getAllRecordsForFirstTable();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_txtproductNameSearchKeyReleased

    private void txtProductsSearchsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProductsSearchsKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProductsSearchsKeyPressed

    private void txtProductsSearchsKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProductsSearchsKeyReleased
        try {
            String dd=txtProductsSearchs.getText();
            getAllproductsNamesCompoDelete(dd);
        } catch (Exception e) {
            
        }
    }//GEN-LAST:event_txtProductsSearchsKeyReleased

    private void txtTypeNameCompoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTypeNameCompoActionPerformed
     try{
       if(!txtTypeNameCompo.getSelectedItem().toString().equals("نوع"))
       {
             ResultSet rs=dbOperations.getData("select AllQuantity,AvailableQtyStore from productsTypes where "
                 + "productTypeName='"+txtproductNameAdd.getSelectedItem().toString()+"_"+txtTypeNameCompo.getSelectedItem().toString()+"'");
         while(rs.next())
         {
             txtAvailableQtyMarketAdd.setText(""+rs.getDouble("AllQuantity"));
             txtAvailableQtyStoreAdd.setText(""+rs.getDouble("AvailableQtyStore"));
        }
       }
//       }else 
//       {
////           getAllTypeName(Integer.valueOf(txtBarcodeAdd.getText()) );
//           
//           txtBarcodeAddKeyReleased(new KeyEvent(this, WIDTH, WIDTH, WIDTH, WIDTH));
//       }
     } catch(Exception e)
             {
                 
             }
    }//GEN-LAST:event_txtTypeNameCompoActionPerformed

    private void txtProductIdFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtProductIdFocusGained
        txtProductId.setText("");
    }//GEN-LAST:event_txtProductIdFocusGained

    private void txtProductIdFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtProductIdFocusLost
        
    }//GEN-LAST:event_txtProductIdFocusLost

    public void getLastBarCode()
    {
        long productId=0;
        try {
            ResultSet rs=dbOperations.getData("select * from barcodes");
            if(rs.next())
            {
             productId=rs.getLong("id");
            }
            ++productId;
            txtProductCodeDefault.setText(""+productId);
            txtProductId.setText(""+productId); 
            txtProductId.setEnabled(false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void txtProductIdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProductIdKeyReleased
        
        try {
            long bar=Long.parseLong(txtProductId.getText()); 
        } catch (NumberFormatException e) {
            txtProductId.setText(txtProductCodeDefault.getText());
        }
        catch (Exception e) {
        }

    }//GEN-LAST:event_txtProductIdKeyReleased

    private void btnBarCodeEnableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBarCodeEnableActionPerformed
        if(txtProductId.isEnabled())
        {
            txtProductId.setEnabled(false);
        }else 
        {
             txtProductId.setEnabled(true);
        }
    }//GEN-LAST:event_btnBarCodeEnableActionPerformed

    public void getSomeRecordsForProductsInvoicesByProductNames(String productName) {

    }

    public void getAllProductRecords(String id) {
        try {
            ArrayList<productss> list = productsController.getAllRecordsForProductsWithID(id);
            Iterator<productss> itr = list.iterator();
            if (itr.hasNext()) {
                productss prod = itr.next();
                txtProductNameLable.setText(prod.getProductName());
                txtAvailableFromProductOnSupplier.setText("" + prod.getAvailableQty());
                txtPriceOfOne.setText(""+prod.getPriceOfBuy());
                txtTakedFromProductsOnSupplier.setText("" + prod.getTaked());
                txtSupplierName.setSelectedItem(prod.getSupplierName());
            } else {
                txtProductNameLable.setText("");
                txtAvailableFromProductOnSupplier.setText("0.0");
                txtPriceOfOne.setText("");
                txtTakedFromProductsOnSupplier.setText("0.0");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void getTotalFromTable() {
        double total = 0;
        try {
            for (int i = 0; i < jTableAddForCaty.getRowCount(); i++) {
                total += Double.parseDouble(jTableAddForCaty.getValueAt(i, 6).toString());
            }
            txtPriceOfAll1.setText(String.valueOf(total));
            if (!txtPayedValue.getText().equals("0.0")) {
                txtRemained.setText("" + (Double.valueOf(txtPriceOfAll1.getText()) - Double.valueOf(txtPayedValue.getText())));
            } else {
                txtRemained.setText(String.valueOf(total));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void setTotalQuantity() { 
        txtTotalQuantity.setText(String.valueOf(jTableAddForCaty.getRowCount()));
    }

    public void getAllTotalsDaily() {
        double totalPayed = 0;
        double totalRemained = 0;
        double totalPrice = 0;
        try {
            for (int i = 0; i < jTableSalesSuppliers.getRowCount(); i++) {
                totalPayed += Double.valueOf(jTableSalesSuppliers.getValueAt(i, 5).toString());
                totalRemained += Double.valueOf(jTableSalesSuppliers.getValueAt(i, 6).toString());
                totalPrice += Double.valueOf(jTableSalesSuppliers.getValueAt(i, 4).toString());
            }
            txtDayPriceTotal.setText(totalPrice + "");
            txtDayPayedTotal.setText(totalPayed + "");
            txtDayRemained.setText(totalRemained + "");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void getAllTotalsMonthly() {
        double totalPayed = 0;
        double totalRemained = 0;
        double totalPrice = 0;
        try {
            for (int i = 0; i < jTableSalesSuppliers.getRowCount(); i++) {
                totalPayed += Double.valueOf(jTableSalesSuppliers.getValueAt(i, 5).toString());
                totalRemained += Double.valueOf(jTableSalesSuppliers.getValueAt(i, 6).toString());
                totalPrice += Double.valueOf(jTableSalesSuppliers.getValueAt(i, 4).toString());
            }
            txtMonthPriceTotal.setText(totalPrice + "");
            txtMonthPayedTotal.setText(totalPayed + "");
            txtTotalRemainedMonth.setText(totalRemained + "");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddPercentage;
    private javax.swing.JButton btnAddQuantity;
    private javax.swing.JButton btnAddStore;
    private javax.swing.JButton btnAddToCart;
    private javax.swing.JButton btnArrivalApproval;
    private javax.swing.JButton btnBarCodeEnable;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnPrint;
    private javax.swing.JButton btnReports;
    private javax.swing.JButton btnReports1;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPaneLowQuantities;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTableAddForCaty;
    private javax.swing.JTable jTableLowQuantity;
    private javax.swing.JTable jTableProductAvailables;
    private javax.swing.JTable jTableProductAvailables1;
    private javax.swing.JTable jTableSalesSuppliers;
    private javax.swing.JLabel txtAllTotal;
    private javax.swing.JLabel txtAllTotalSold;
    private javax.swing.JLabel txtAvailableComeFromTable;
    private javax.swing.JLabel txtAvailableForTable;
    private javax.swing.JLabel txtAvailableForTable1;
    private javax.swing.JLabel txtAvailableForTableVariable;
    private javax.swing.JLabel txtAvailableForTableVariable1;
    private javax.swing.JLabel txtAvailableForTableVariable11;
    private javax.swing.JLabel txtAvailableForTableVariable2;
    private javax.swing.JLabel txtAvailableForTableVariable3;
    private javax.swing.JLabel txtAvailableForTableVariable4;
    private javax.swing.JLabel txtAvailableForTableVariable6;
    private javax.swing.JLabel txtAvailableForTableVariable8;
    private javax.swing.JLabel txtAvailableFromProductOnSupplier;
    private javax.swing.JPanel txtAvailableLabel;
    private javax.swing.JLabel txtAvailableQtyMarketAdd;
    private javax.swing.JLabel txtAvailableQtyStoreAdd;
    private javax.swing.JTextField txtBarCodeSearchLowQuantity;
    private javax.swing.JTextField txtBarcode;
    private javax.swing.JTextField txtBarcodeAdd;
    private javax.swing.JTextField txtBarcodeProduct;
    private javax.swing.JTextField txtBarcodeProductCompany;
    private javax.swing.JLabel txtCasherName;
    private javax.swing.JComboBox<String> txtCategory;
    private javax.swing.JTextField txtCategoryNameAdd;
    private javax.swing.JComboBox<String> txtCategoryPercentage;
    private javax.swing.JCheckBox txtCheckBoxMarket;
    private javax.swing.JCheckBox txtCheckBoxStore;
    private javax.swing.JTextField txtCompanyAddress;
    private javax.swing.JLabel txtDayPayedTotal;
    private javax.swing.JLabel txtDayPriceTotal;
    private javax.swing.JLabel txtDayRemained;
    private javax.swing.JLabel txtFirstPriceSuppliers;
    private javax.swing.JTextField txtGained1;
    private javax.swing.JLabel txtGainedForAllQuantitySingle;
    private javax.swing.JLabel txtGainedForAllQuantityTypes;
    private com.toedter.calendar.JDateChooser txtHandDateArrival;
    private com.toedter.calendar.JDateChooser txtHandDateSaerchSuppliers;
    private javax.swing.JTextField txtINID;
    private javax.swing.JLabel txtINIDOfGetProductsInvoice;
    private javax.swing.JComboBox<String> txtMarketOrStore;
    private javax.swing.JTextField txtMarketPrice;
    private javax.swing.JTextField txtMarketPriceTypes;
    private javax.swing.JLabel txtMonthPayedTotal;
    private javax.swing.JLabel txtMonthPriceTotal;
    private javax.swing.JComboBox<String> txtMonthSearchSuppliers;
    private javax.swing.JTextArea txtNotes;
    private javax.swing.JTextField txtPayedValue;
    private javax.swing.JTextField txtPercentageValue;
    private javax.swing.JLabel txtPriceOfAll1;
    private javax.swing.JLabel txtPriceOfAllQuantityDouble;
    private javax.swing.JLabel txtPriceOfAllQuantitySingle;
    private javax.swing.JTextField txtPriceOfBuy;
    private javax.swing.JTextField txtPriceOfBuyTypes;
    private javax.swing.JLabel txtPriceOfBuyTypesVariable;
    private javax.swing.JLabel txtPriceOfMarketVar;
    private javax.swing.JTextField txtPriceOfOne;
    private javax.swing.JTextField txtPriceOfSale;
    private javax.swing.JTextField txtPriceOfSaleType;
    private javax.swing.JLabel txtPriceOfSaleTypeVarialble;
    private javax.swing.JLabel txtPriceOfSaleTypeVarialble1;
    private javax.swing.JLabel txtPriceOfSaleTypeVarialble2;
    private javax.swing.JLabel txtProductCodeDefault;
    private javax.swing.JTextField txtProductId;
    private javax.swing.JLabel txtProductIdTypes;
    private javax.swing.JTextField txtProductName;
    private javax.swing.JLabel txtProductNameLable;
    private javax.swing.JTextField txtProductTypeName;
    private javax.swing.JTextField txtProductsSearchs;
    private javax.swing.JLabel txtPurchaseDate;
    private javax.swing.JTextField txtPurchaseNumberSearch;
    private javax.swing.JTextField txtQuantityAdded;
    private javax.swing.JTextField txtQuantityForSale;
    private javax.swing.JTextField txtQuantityOfOne;
    private javax.swing.JTextField txtQuantityOfOneValue1;
    private javax.swing.JLabel txtQuantityOfOneVariable;
    private javax.swing.JLabel txtQuantityOfOneVariable1;
    private javax.swing.JLabel txtQuantityOfOneVariable2;
    private javax.swing.JTextField txtQuantitySingle;
    private javax.swing.JTextField txtQuantityStore;
    private javax.swing.JTextField txtQuantityWarning;
    private javax.swing.JComboBox<String> txtReceiverNameForCart;
    private javax.swing.JComboBox<String> txtReceiverNameSaerch;
    private javax.swing.JLabel txtRemained;
    private javax.swing.JComboBox<String> txtSatusSearch;
    private com.toedter.calendar.JDateChooser txtSeachDaily;
    private javax.swing.JComboBox<String> txtSeachYear;
    private javax.swing.JComboBox<String> txtSearchCategory;
    private javax.swing.JComboBox<String> txtSearchMonthly;
    private javax.swing.JLabel txtSearchPurchaseDate;
    private javax.swing.JLabel txtStatusSearchPurchase;
    private javax.swing.JComboBox<String> txtStoreName;
    private javax.swing.JTextField txtStoreNameAdd;
    private javax.swing.JComboBox<String> txtStoreNameSearchLowQuantity;
    private javax.swing.JTextField txtStorePhoneNumber;
    private javax.swing.JComboBox<String> txtStoreSearchName;
    private javax.swing.JComboBox<String> txtSupplierName;
    private javax.swing.JComboBox<String> txtSupplierNameAddProduct;
    private javax.swing.JComboBox<String> txtSupplierNamePercentage;
    private javax.swing.JComboBox<String> txtSupplierNameSearch;
    private javax.swing.JTextField txtSupplierNumberSearch;
    private javax.swing.JTextField txtSupplierPhone;
    private javax.swing.JLabel txtTakedComeFromTable;
    private javax.swing.JLabel txtTakedFromProductsOnSupplier;
    private javax.swing.JLabel txtTodayDate;
    private javax.swing.JLabel txtTotalQuantity;
    private javax.swing.JLabel txtTotalRemainedMonth;
    private javax.swing.JComboBox<String> txtTypeNameCompo;
    private javax.swing.JLabel txtTypeVariable;
    private javax.swing.JComboBox<String> txtYearSearchSuppliers;
    private javax.swing.JComboBox<String> txtcategorySearchLowQuantity1;
    private javax.swing.JComboBox<String> txtproductNameAdd;
    private javax.swing.JTextField txtproductNameSearch;
    private javax.swing.JComboBox<String> txtproductTypeNameSuppliers;
    private javax.swing.JTextArea txtsupplierNotes;
    // End of variables declaration//GEN-END:variables
}
