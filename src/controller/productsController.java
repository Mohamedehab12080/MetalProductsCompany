package controller;

import Database.connectionProvider;
import Database.dbOperations;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.customers;
import model.productsTypes;
import model.productss;

public class productsController {

    public static void addProducts(productss product) {
        PreparedStatement ps = null;
        Connection con = null;
        try {
            con = connectionProvider.getCon();
            ps = con.prepareStatement("insert into products ("
                    + "productName"
                    + ",priceOfBuy"
                    + ",priceOfSale"
                    + ",Taked"
                    + ",AvailableQty,"
                    + "Category,storeName,Note,priceOfMarket,AvailableQtyStore,QuantityWarning,supplierName,productId) values (?,?,?,?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1, product.getProductName());
            ps.setDouble(2, product.getPriceOfBuy());
            ps.setDouble(3, product.getPriceOfSale());
            ps.setDouble(4, product.getTaked());
            ps.setDouble(5, product.getAvailableQty());
            ps.setString(6, product.getProductCategory());
            ps.setString(7, product.getStoreName());
            ps.setString(8, product.getNote());
            ps.setDouble(9, product.getPriceOfMarket());
            ps.setDouble(10, product.getAvailableQtyStore());
            ps.setDouble(11, product.getQuantityWarning());
            ps.setString(12, product.getSupplierName());
            ps.setLong(13,product.getProductId());
            int result = ps.executeUpdate();
            if (result == 0) {
//                JOptionPane.showMessageDialog(null, "لا يمكن الاضافة");
            } else {
//                JOptionPane.showMessageDialog(null, "تم الاضافة");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public static void addProductsWithType(String productName, productsTypes product) {
        PreparedStatement ps = null;
        Connection con = null;
        long productId = 0;
        try {
            con = connectionProvider.getCon();
            ps = con.prepareStatement("insert into productsTypes("
                    + "productId,"
                    + "productTypeName"
                    + ",priceOfBuy"
                    + ",priceOfSale"
                    + ",QuantityOfOne"
                    + ",AllQuantity,"
                    + "Category,"
                    + "Note,"
                    + "priceOfMarket,"
                    + "AvailableQtyStore,"
                    + "QuantityWarning,storeName,supplierName) values (?,?,?,?,?,?,?,?,?,?,?,?,?)");
            ResultSet rs = dbOperations.getData("select productId from products where productName='" + productName + "'");
            if (rs.next()) {
                productId = rs.getLong("productId");
            } else {
                JOptionPane.showMessageDialog(null, "لديك خطأ");
            }
            if (productId != 0) {
                ps.setLong(1, productId);
                ps.setString(2, product.getProductTypeName());
                ps.setDouble(3, product.getPriceOfBuy());
                ps.setDouble(4, product.getPriceOfSale());
                ps.setDouble(5, product.getQuantityOfOne());
                ps.setDouble(6, product.getAllQuantity());
                ps.setString(7, product.getProductCategory());
                ps.setString(8, product.getNote());
                ps.setDouble(9, product.getPriceOfMarket());
                ps.setDouble(10, product.getAvailableQtyStore());
                ps.setDouble(11, product.getQuantityWarning());
                ps.setString(12, product.getStoreName());
                ps.setString(13, product.getSupplierName());
                int result = ps.executeUpdate();
                if (result == 0) {
//                JOptionPane.showMessageDialog(null, "لا يمكن الاضافة");
                } else {
//                JOptionPane.showMessageDialog(null, "تم الاضافة");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    public static long getproductIDWithName(String productName) {

        long productId = 0;
        try {
            ResultSet rs = dbOperations.getData("select productId from products where productName='" + productName + "'");
            if (rs.next()) {
                productId = rs.getLong("productId");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return productId;
    }

    public static long getIDWithNameProductsTypes(String productName) {

        long productId = 0;
        try {
            ResultSet rs = dbOperations.getData("select id from productsTypes where productTypeName='" + productName + "'");
            if (rs.next()) {
                productId = rs.getLong("id");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return productId;
    }

    public static ArrayList<productss> getAllRecordsForProducts() {
        ArrayList<productss> list = new ArrayList<>();
        try {
            ResultSet rs = dbOperations.getData("select * from products");
            while (rs.next()) {
                productss prod = new productss();
                prod.setProductId(rs.getLong("productId"));
                prod.setProductName(rs.getString("productName"));
                prod.setProductCategory(rs.getString("category"));
                prod.setPriceOfBuy(rs.getDouble("priceOfBuy"));
                prod.setPriceOfSale(rs.getDouble("priceOfSale"));
                prod.setAvailableQty(rs.getDouble("AvailableQty"));
                prod.setTaked(rs.getDouble("Taked"));
                prod.setPriceOfMarket(rs.getDouble("priceOfMarket"));
                prod.setAvailableQtyStore(rs.getDouble("AvailableQtyStore"));
                prod.setNote(rs.getString("Note"));
                prod.setStoreName(rs.getString("storeName"));
                prod.setQuantityWarning(rs.getDouble("QuantityWarning"));
                prod.setSupplierName(rs.getString("supplierName"));
                list.add(prod);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

        return list;
    }

    public static ArrayList<productss> getAllRecordsForProductsWithID(String productId) {
        ArrayList<productss> list = new ArrayList<>();
        try {
            ResultSet rs = dbOperations.getData("select * from products where productId=" + productId);
            if (rs.next()) {
                productss prod = new productss();
                prod.setProductId(rs.getLong("productId"));
                prod.setProductName(rs.getString("productName"));
                prod.setProductCategory(rs.getString("category"));
                prod.setPriceOfBuy(rs.getDouble("priceOfBuy"));
                prod.setPriceOfSale(rs.getDouble("priceOfSale"));
                prod.setAvailableQty(rs.getDouble("AvailableQty"));
                prod.setTaked(rs.getDouble("Taked"));
                prod.setPriceOfMarket(rs.getDouble("priceOfMarket"));
                prod.setAvailableQtyStore(rs.getDouble("AvailableQtyStore"));
                prod.setNote(rs.getString("Note"));
                prod.setStoreName(rs.getString("storeName"));
                prod.setQuantityWarning(rs.getDouble("QuantityWarning"));
                prod.setSupplierName(rs.getString("supplierName"));

                list.add(prod);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return list;
    }

    public static ArrayList<productsTypes> getAllTypesForCompoWithBarCode(String barCode) {
        ArrayList<productsTypes> list = new ArrayList<>();
        try {
            ResultSet rs = dbOperations.getData("select * from productsTypes where productId=" + barCode);
            while (rs.next()) {
                productsTypes prod = new productsTypes();
                prod.setId(rs.getLong("id"));
                prod.setProductTypeName(rs.getString("productTypeName"));
                list.add(prod);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "SSS");
        }
        return list;
    }

    public static ArrayList<productsTypes> getAllProductsTypesRecordsForSellWithProductName(String productName) {
        ArrayList<productsTypes> list = new ArrayList<>();
        try {
            ResultSet rs = dbOperations.getData("select * from productsTypes where productTypeName='" + productName + "'");
            while (rs.next()) {
                productsTypes prod = new productsTypes();
                prod.setId(rs.getLong("id"));
                prod.setProductTypeName(rs.getString("productTypeName"));
                prod.setPriceOfBuy(rs.getDouble("priceOfBuy"));
                prod.setPriceOfSale(rs.getDouble("priceOfSale"));
                prod.setQuantityOfOne(rs.getDouble("quantityOfOne"));
                prod.setAllQuantity(rs.getDouble("AllQuantity"));
                prod.setAvailableQtyStore(rs.getDouble("AvailableQtyStore"));
                prod.setPriceOfMarket(rs.getDouble("priceOfMarket"));
                prod.setNote(rs.getString("Note"));
                prod.setQuantityWarning(rs.getDouble("QuantityWarning"));
                prod.setSupplierName(rs.getString("supplierName"));

                list.add(prod);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return list;
    }

    public static double getAvailableOfProductsTable(String productId) {
        double var = 0;
        try {
            ResultSet rs = dbOperations.getData("select AvailableQty from products where productId=" + productId);
            while (rs.next()) {
                var = rs.getDouble("AvailableQty");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return var;
    }

    public static double getAllQuantityProductsTypes(String productName) {
        double var = 0;
        try {
            ResultSet rs = dbOperations.getData("select AllQuantity from productsTypes where productTypeName='" + productName + "'");
            while (rs.next()) {
                var = rs.getDouble("AllQuantity");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return var;
    }

    public static double getAvailableQtyStoreProductsTypes(String productName) {
        double var = 0;
        try {
            ResultSet rs = dbOperations.getData("select AvailableQtyStore from productsTypes where productTypeName='" + productName + "'");
            while (rs.next()) {
                var = rs.getDouble("AvailableQtyStore");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return var;
    }

    public static void updateAvailableWhenAddFromProductsTable(Double QuantityAvailable, Double QuantityNeeded, String productId, String productPlace) {
        double QuantityNow = 0;
        double Taked = 0;
        try {
            if (productPlace.equals("محل")) {
                ResultSet rs = dbOperations.getData("select availableQty,Taked from products where productId=" + productId);
                if (rs.next()) {
                    QuantityNow = rs.getDouble("availableQty");
                    Taked = rs.getDouble("Taked");
                    if (QuantityNow <= 5) {
                        JOptionPane.showConfirmDialog(null, "<html><h1>متوفر : " + QuantityNow + " فقط</h1></html>", "تحذير", JOptionPane.WARNING_MESSAGE);
                    }
                }
                dbOperations.setDataOrDelete("update products set"
                        + " availableQty=" + String.format("%.3f",QuantityAvailable) + ","
                        + "Taked=" + String.format("%.3f", (Taked + QuantityNeeded) )+ ""
                        + " where productId=" + productId, "");

            } else {
                ResultSet rs = dbOperations.getData("select availableQtyStore,Taked from products where productId=" + productId);
                if (rs.next()) {
                    QuantityNow = rs.getDouble("availableQtyStore");
                    Taked = rs.getDouble("Taked");
                    if (QuantityNow <= 5) {
                        JOptionPane.showConfirmDialog(null, "<html><h1>متوفر : " + QuantityNow + " فقط</h1></html>", "تحذير", JOptionPane.WARNING_MESSAGE);
                    }
                }
                dbOperations.setDataOrDelete("update products set"
                        + " availableQtyStore=" + String.format("%.3f",QuantityAvailable) + ","
                        + "Taked=" +String.format("%.3f",(Taked + QuantityNeeded)) + ""
                        + " where productId=" + productId, "");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public static void updateAvailableWhenReturnFromProductsTable(Double QuantityAvailable, Double QuantityNeeded, String productId, String productPlace) {
        double QuantityNow = 0;
        double Taked = 0;
        try {
            if (productPlace.equals("محل")) {
                ResultSet rs = dbOperations.getData("select * from products where productId=" + productId);
                if (rs.next()) {
                    Taked = rs.getDouble("Taked");
                }
                dbOperations.setDataOrDelete("update products set"
                        + " availableQty=" + String.format("%.3f", QuantityAvailable )+ ","
                        + "Taked=" + String.format("%.3f",(Taked - QuantityNeeded))
                        + " where productId=" + productId, "");
            } else {
                ResultSet rs = dbOperations.getData("select * from products where productId=" + productId);
                if (rs.next()) {
                    QuantityNow = rs.getDouble("availableQtyStore");
                    Taked = rs.getDouble("Taked");
                    if (QuantityNow <= 5) {
                        JOptionPane.showConfirmDialog(null, "<html><h1>متوفر : " + QuantityNow + " فقط</h1></html>", "تحذير", JOptionPane.WARNING_MESSAGE);
                    }
                }
                dbOperations.setDataOrDelete("update products set"
                        + " availableQtyStore=" + String.format("%.3f", QuantityAvailable ) + ","
                        + "Taked=" + String.format("%.3f",(Taked - QuantityNeeded))
                        + " where productId=" + productId, "");

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public static void updateAvailableWhenReturnFromProductsTable2(Double QuantityAvailable, Double QuantityNeeded, String productId, String productPlace) {
        double QuantityNow = 0;
        double Taked = 0;
        try {
            if (productPlace.equals("محل")) {
                dbOperations.setDataOrDelete("update products set"
                        + " availableQty=" + String.format("%.3f", QuantityAvailable)
                        + " where productId=" + productId, "");
            } else {
                dbOperations.setDataOrDelete("update products set"
                        + " availableQtyStore=" + String.format("%.3f", QuantityAvailable)
                        + " where productId=" + productId, "");

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public static ArrayList<productss> getAllProductsRecordsWithProductname(String productname) {
        ArrayList<productss> list = new ArrayList<>();
        try {
            ResultSet rs = dbOperations.getData("select * from products where productName='" + productname + "'");
            while (rs.next()) {
                productss prod = new productss();
                prod.setProductId(rs.getLong("productId"));
                prod.setProductName(rs.getString("productName"));
                prod.setProductCategory(rs.getString("category"));
                prod.setPriceOfBuy(rs.getDouble("priceOfBuy"));
                prod.setPriceOfSale(rs.getDouble("priceOfSale"));
                prod.setAvailableQty(rs.getDouble("AvailableQty"));
                prod.setTaked(rs.getDouble("Taked"));
                prod.setAvailableQtyStore(rs.getDouble("AvailableQtyStore"));
                prod.setPriceOfMarket(rs.getDouble("priceOfMarket"));
                prod.setNote(rs.getString("Note"));
                prod.setStoreName(rs.getString("storeName"));
                prod.setSupplierName(rs.getString("supplierName"));

                prod.setQuantityWarning(rs.getDouble("QuantityWarning"));
                list.add(prod);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return list;
    }

    public static ArrayList<productss> getProductsNamesForCompo() {
        ArrayList<productss> list = new ArrayList<>();
        try {
            ResultSet rs = dbOperations.getData("select productName from products");
            while (rs.next()) {
                productss prod = new productss();
                prod.setProductName(rs.getString("productName"));
                list.add(prod);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return list;
    }

    public static void updateProductAvailableWhenDelete(String[] productName, double[] Quantityneeded, String productPlace[]) {
        try {
            double productsTypesQuantity = 0;
            double QuantityOfOne = 0;
            double AvailableProducts = 0;
            double Taked = 0;
            long productId = 0;
            long productsTypesId = 0;
            double lastAvailable = 0;
            String AfterPoLong;
            for (int i = 0; i < productName.length; i++) {

                if (productPlace[i].equals("محل")) {
                    if (productName[i].contains("_")) {
                        ResultSet rs = dbOperations.getData("select * "
                                + "from productsTypes where productTypeName='" + productName[i] + "'");
                        if (rs.next()) {
                            productsTypesQuantity = rs.getDouble("AllQuantity");
                            QuantityOfOne = rs.getDouble("QuantityOfOne");
                            ResultSet rs1 = dbOperations.getData("select * from products where productId=" + rs.getLong("productId"));
                            if (rs1.next()) {
                                AvailableProducts = rs1.getDouble("AvailableQty");
                                Taked = rs1.getDouble("Taked");
                                productId = rs1.getLong("productId");
                                lastAvailable = (AvailableProducts + (Quantityneeded[i] * QuantityOfOne));
                                dbOperations.setDataOrDelete("update productsTypes set AllQuantity=" + String.format("%.3f",(productsTypesQuantity + Quantityneeded[i]) )+ " where productTypeName='" + productName[i] + "'", "");
                                dbOperations.setDataOrDelete("update products set AvailableQty=" + String.format("%.3f",lastAvailable) + ","
                                        + "Taked=" + String.format("%.3f",(Taked - (Quantityneeded[i] * QuantityOfOne))) + " where productId=" + productId, "");

                            }
                            ResultSet rs22 = dbOperations.getData("select * from productsTypes where productId=" + productId);
                            while (rs22.next()) {
                                dbOperations.setDataOrDelete("update productsTypes set AllQuantity=" + String.format("%.3f", (lastAvailable / rs22.getDouble("QuantityOfOne")))
                                        + " where id=" + rs22.getLong("id"), "");

                            }
                        }

                    } else {
                        ResultSet rs1 = dbOperations.getData("select * from products where productName='" + productName[i] + "'");
                        if (rs1.next()) {
                            AvailableProducts = rs1.getDouble("AvailableQty");
                            Taked = rs1.getDouble("Taked");
                            productId = rs1.getLong("productId");
                        }
//                        ResultSet rs2 = dbOperations.getData("select * from productsTypes where productId=" + productId);
//                        while (rs2.next()) {
//                            productsTypesQuantity = rs2.getDouble("AllQuantity");
//                            QuantityOfOne = rs2.getDouble("QuantityOfOne");
//                            productsTypesId = rs2.getLong("id");
//                        }
                        double lastAvailables= (AvailableProducts + (Quantityneeded[i])) ;
//                        AfterPoLong = String.format("%.3f", (Quantityneeded[i] / QuantityOfOne));
//                        if (AfterPoLong.substring(AfterPoLong.indexOf(".") + 1, AfterPoLong.length()).equals("0")) {
//                            dbOperations.setDataOrDelete("update productsTypes set AllQuantity=" + (productsTypesQuantity + (Quantityneeded[i] / QuantityOfOne)) + " where id=" + productsTypesId, "");
                            dbOperations.setDataOrDelete("update products set AvailableQty=" +String.format("%.3f", lastAvailables)+ ","
                                    + "Taked=" + String.format("%.3f", (Taked - (Quantityneeded[i]))) + " where productId=" + productId, "");
//                        } else {
//                            dbOperations.setDataOrDelete("update products set AvailableQty=" + (AvailableProducts + (Quantityneeded[i])) + ","
//                                    + "Taked=" + (Taked - (Quantityneeded[i])) + " where productId=" + productId, "");
//                            ResultSet rsdb = dbOperations.getData("select * from products where productId=" + productId);
//                            if (rsdb.next()) {
//                                AvailableProducts = rsdb.getDouble("AvailableQty");
//                            }
                            ResultSet rsall = dbOperations.getData("select * from productsTypes where productId=" + productId);
                            while (rsall.next()) {
                                 AfterPoLong = String.format("%.3f",(lastAvailables / rsall.getDouble("QuantityOfOne")));
//                                 if (AfterPoLong.substring(AfterPoLong.indexOf(".") + 1, AfterPoLong.length()).equals("0")) {
                                     dbOperations.setDataOrDelete("update productsTypes set AllQuantity=" + AfterPoLong + " where id=" +rsall.getLong("id"), "");
//                                 }
                            }
                            
//                        }

                    }
                } else {
                    if (productName[i].contains("_")) {
//                        ResultSet rs = dbOperations.getData("select * "
//                                + "from productsTypes where productTypeName='" + productName[i] + "'");
//                        if (rs.next()) {
//                            ResultSet rs1 = dbOperations.getData("select * from products where productName='" + productName[i].substring(0, productName[i].indexOf("_")) + "'");
//                            productsTypesQuantity = rs.getDouble("AvailableQtyStore");
//                            QuantityOfOne = rs.getDouble("QuantityOfOne");
//                            if (rs1.next()) {
//                                AvailableProducts = rs1.getDouble("AvailableQtyStore");
//                                Taked = rs1.getDouble("Taked");
//                                productId = rs1.getLong("productId");
//                            }
//                            dbOperations.setDataOrDelete("update productsTypes set AvailableQtyStore=" + (productsTypesQuantity + Quantityneeded[i]) + " where productTypeName='" + productName[i] + "'", "");
//                            dbOperations.setDataOrDelete("update products set AvailableQtyStore=" + (AvailableProducts + (Quantityneeded[i] * QuantityOfOne)) + ","
//                                    + "Taked=" + (Taked - (Quantityneeded[i] * QuantityOfOne)) + " where productId=" + productId, "");
//                        }
  ResultSet rs = dbOperations.getData("select * "
                                + "from productsTypes where productTypeName='" + productName[i] + "'");
                        if (rs.next()) {
                            productsTypesQuantity = rs.getDouble("AvailableQtyStore");
                            QuantityOfOne = rs.getDouble("QuantityOfOne");
                            ResultSet rs1 = dbOperations.getData("select * from products where productId=" + rs.getLong("productId"));
                            if (rs1.next()) {
                                AvailableProducts = rs1.getDouble("AvailableQtyStore");
                                Taked = rs1.getDouble("Taked");
                                productId = rs1.getLong("productId");
                                lastAvailable = (AvailableProducts + (Quantityneeded[i] * QuantityOfOne));
                                dbOperations.setDataOrDelete("update productsTypes set AvailableQtyStore=" + String.format("%.3f",(productsTypesQuantity + Quantityneeded[i])) + " where productTypeName='" + productName[i] + "'", "");
                                dbOperations.setDataOrDelete("update products set AvailableQtyStore=" + String.format("%.3f",lastAvailable)+ ","
                                        + "Taked=" + String.format("%.3f",(Taked - (Quantityneeded[i] * QuantityOfOne))) + " where productId=" + productId, "");

                            }
                            ResultSet rs22 = dbOperations.getData("select * from productsTypes where productId=" + productId);
                            while (rs22.next()) {
                                dbOperations.setDataOrDelete("update productsTypes set AvailableQtyStore=" + String.format("%.3f", (lastAvailable / rs22.getDouble("QuantityOfOne")))
                                        + " where id=" + rs22.getLong("id"), "");

                            }
                        }
                    } else {
                     ResultSet rs1 = dbOperations.getData("select * from products where productName='" + productName[i] + "'");
                        if (rs1.next()) {
                            AvailableProducts = rs1.getDouble("AvailableQtyStore");
                            Taked = rs1.getDouble("Taked");
                            productId = rs1.getLong("productId");
                        }
//                        ResultSet rs2 = dbOperations.getData("select * from productsTypes where productId=" + productId);
//                        while (rs2.next()) {
//                            productsTypesQuantity = rs2.getDouble("AllQuantity");
//                            QuantityOfOne = rs2.getDouble("QuantityOfOne");
//                            productsTypesId = rs2.getLong("id");
//                        }
                        double lastAvailables= (AvailableProducts + (Quantityneeded[i])) ;
//                        AfterPoLong = String.format("%.3f", (Quantityneeded[i] / QuantityOfOne));
//                        if (AfterPoLong.substring(AfterPoLong.indexOf(".") + 1, AfterPoLong.length()).equals("0")) {
//                            dbOperations.setDataOrDelete("update productsTypes set AllQuantity=" + (productsTypesQuantity + (Quantityneeded[i] / QuantityOfOne)) + " where id=" + productsTypesId, "");
                            dbOperations.setDataOrDelete("update products set AvailableQtyStore=" +String.format("%.3f",lastAvailables)+ ","
                                    + "Taked=" +String.format("%.3f", (Taked - (Quantityneeded[i]))) + " where productId=" + productId, "");
//                        } else {
//                            dbOperations.setDataOrDelete("update products set AvailableQty=" + (AvailableProducts + (Quantityneeded[i])) + ","
//                                    + "Taked=" + (Taked - (Quantityneeded[i])) + " where productId=" + productId, "");
//                            ResultSet rsdb = dbOperations.getData("select * from products where productId=" + productId);
//                            if (rsdb.next()) {
//                                AvailableProducts = rsdb.getDouble("AvailableQty");
//                            }
                            ResultSet rsall = dbOperations.getData("select * from productsTypes where productId=" + productId);
                            while (rsall.next()) {
                                 AfterPoLong = String.format("%.3f",(lastAvailables / rsall.getDouble("QuantityOfOne")));
//                                 if (AfterPoLong.substring(AfterPoLong.indexOf(".") + 1, AfterPoLong.length()).equals("0")) {
                                     dbOperations.setDataOrDelete("update productsTypes set AvailableQtyStore=" +AfterPoLong + " where id=" +rsall.getLong("id"), "");
//                                 }
                            }
                            
//                        }
                    
                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public static void updateAvailableWhenDeleteoneRow(double QuantityBack, String productName, String prodcutPlace) {
        double newQuantityProducts = 0;
        double newQuantityproductsTypes = 0;
        double QuantityOfOne = 0;
        double newTaked = 0;
        String AfterpoLong;
        long productId = 0;

        try {
            if (prodcutPlace.equals("محل")) {
                if (productName.contains("_")) {
                    ResultSet rs = dbOperations.getData("select * from productsTypes where productTypeName='" + productName + "'");
                    if (rs.next()) {
                        newQuantityproductsTypes = rs.getDouble("AvailableQty") + QuantityBack;
                        QuantityOfOne = rs.getDouble("QuatityOfOne");
                        productId = rs.getLong("productId");
                    }

                    ResultSet rs1 = dbOperations.getData("select * from products where productId=" + productId);
                    while (rs1.next()) {
                        newQuantityProducts = rs.getDouble("AvailableQtyStore") + (QuantityBack * QuantityOfOne);
                        newTaked = rs.getDouble("Taked") - (QuantityBack * QuantityOfOne);
                    }
                    dbOperations.setDataOrDelete("update products set"
                            + " AvailableQty=" + String.format("%.3f", newQuantityProducts) + ","
                            + "Taked=" + String.format("%.3f",newTaked) + " where "
                            + "productId=" + productId, "");
                       ResultSet rsdd=dbOperations.getData("select * from productsTypes where productId="+productId);
                            while(rsdd.next())
                            {
                                dbOperations.setDataOrDelete("update productsTypes set "
                                        + "AllQuantity=" + String.format("%.3f", (newQuantityproductsTypes/rsdd.getDouble("QuantityOfOne"))) + " "
                            + "where id=" + rsdd.getLong("id") , "");
                            }
                } else {
                    ResultSet rs1 = dbOperations.getData("select * from products where productName='" + productName + "'");
                    if (rs1.next()) {
                        newQuantityProducts = rs1.getDouble("availableQty") + QuantityBack;
                        newTaked = rs1.getDouble("Taked") - QuantityBack;
                        productId = rs1.getLong("productId");
                    }
                    dbOperations.setDataOrDelete("update products set"
                            + " availableQty=" +  String.format("%.3f",newQuantityProducts) + ","
                            + "Taked=" + String.format("%.3f",newTaked) + " where "
                            + "productName='" + productName + "'", "");
                    double availableProducts;
                    ResultSet rs2 = dbOperations.getData("select * from productsTypes where productId=" + productId);
                    while (rs2.next()) {
//                        AfterpoLong = String.valueOf(QuantityBack / rs2.getDouble("QuantityOfOne"));
//                        if (AfterpoLong.substring(AfterpoLong.indexOf(".") + 1, AfterpoLong.length()).equals("0")) {
//                          
////                                                        dbOperations.setDataOrDelete("update productsTypes set AllQuantity=" + (rs2.getDouble("AllQuantity") + (QuantityBack / rs2.getDouble("QuantityOfOne")))
//                                    + " where  productTypeName='" + rs2.getString("productTypeName") + "'", "");
//                            
//                        } else {
//                            ResultSet rs3 = dbOperations.getData("select * from products where productId=" + productId);
//                            if (rs3.next()) {
                           
//                            }
                             availableProducts=(newQuantityProducts / rs2.getDouble("QuantityOfOne"));
                            dbOperations.setDataOrDelete("update productsTypes set AllQuantity=" + String.format("%.3f",availableProducts)
                                        + " where  id=" + rs2.getLong("id") , "");

//                        }

                    }
                }
            } else {
                if (productName.contains("_")) {
                    ResultSet rs = dbOperations.getData("select * from productsTypes where productTypeName='" + productName + "'");
                    while (rs.next()) {
                        newQuantityproductsTypes = rs.getDouble("AvailableQtyStore") + QuantityBack;
                        QuantityOfOne = rs.getDouble("QuatityOfOne");
                        productId = rs.getLong("productId");
                    }

                    ResultSet rs1 = dbOperations.getData("select * from products where productId=" + productId);
                    while (rs1.next()) {
                        newQuantityProducts = rs1.getDouble("AvailableQtyStore") + (QuantityBack * QuantityOfOne);
                        newTaked = rs1.getDouble("Taked") - (QuantityBack * QuantityOfOne);
                    }
                    dbOperations.setDataOrDelete("update products set"
                            + " AvailableQtyStore=" +String.format("%.3f",  newQuantityProducts) + ","
                            + "Taked=" +String.format("%.3f", newTaked) + " where "
                            + "productId=" + productId, "");
                    
                    ResultSet rsdd=dbOperations.getData("select * from productsTypes where productId="+productId);
                            while(rsdd.next())
                            {
                                dbOperations.setDataOrDelete("update productsTypes set AvailableQtyStore=" + String.format("%.3f", (newQuantityproductsTypes/rsdd.getDouble("QuantityOfOne"))) + " "
                            + "where id=" + rsdd.getLong("id") , "");
                            }
                } else {
                    ResultSet rs1 = dbOperations.getData("select * from products where productName='" + productName + "'");
                    while (rs1.next()) {
                        newQuantityProducts = rs1.getDouble("AvailableQtyStore") + QuantityBack;
                        newTaked = rs1.getDouble("Taked") - QuantityBack;
                        productId = rs1.getLong("productId");
                    }
                    dbOperations.setDataOrDelete("update products set"
                            + " AvailableQtyStore=" + String.format("%.3f",  newQuantityProducts)  + ","
                            + "Taked=" + String.format("%.3f",  newTaked)  + " where "
                            + "productName='" + productName + "'", "");
                    ResultSet rs2 = dbOperations.getData("select * from productsTypes where productId=" + productId);
                    while (rs2.next()) {
//                        AfterpoLong = String.valueOf(QuantityBack / rs2.getDouble("QuantityOfOne"));
//                        if (AfterpoLong.substring(AfterpoLong.indexOf(".") + 1, AfterpoLong.length()).equals("0")) {
//
//                            dbOperations.setDataOrDelete("update productsTypes set AllQuantity=" + (rs2.getDouble("AllQuantity") + (QuantityBack / rs2.getDouble("QuantityOfOne")))
//                                    + " where  productTypeName='" + rs2.getString("productTypeName") + "'", "");
//                        } else {
//                            ResultSet rs3 = dbOperations.getData("select * from products where productId=" + productId);
//                            if (rs3.next()) {
                                dbOperations.setDataOrDelete("update productsTypes set AvailableQtyStore=" + String.format("%.3f",(newQuantityProducts/ rs2.getDouble("QuantityOfOne")))
                                        + " where  id="+rs2.getLong("id"), "");
//                            }
//                        }
                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public static void updateAvailableWhenDeleteSale(String productName, double QuantityBack, String productPlace) {
        try {
            long productId = 0;
            double quantityOfOne = 0;
            double Allquantity = 0;
            double Taked = 0;
            double AvailablequantityProducts = 0;
            if (productPlace.equals("محل")) {
                if (productName.contains("_")) {
                    ResultSet rs = dbOperations.getData("select * from productsTypes where productTypeName='" + productName + "'");
                    if (rs.next()) {
                        productId = rs.getLong("productId");
                        quantityOfOne = rs.getDouble("QuantityOfOne");
                        Allquantity = rs.getDouble("AllQuantity");
                    }

                    dbOperations.setDataOrDelete("update productsTypes set AllQuantity=" + (Allquantity + QuantityBack) + " where productTypeName='" + productName + "'", "");
                    ResultSet rs1 = dbOperations.getData("select * from products where productId=" + productId);
                    if (rs1.next()) {
                        AvailablequantityProducts = rs1.getDouble("AvailableQty");
                        Taked = rs1.getDouble("Taked");
                    }

                    dbOperations.setDataOrDelete("update products set"
                            + " AvailableQty=" + (AvailablequantityProducts + (QuantityBack * quantityOfOne)) + ","
                            + "Taked=" + (Taked - (QuantityBack * quantityOfOne)) + " where productId=" + productId, "");
                } else {
                    ResultSet rs1 = dbOperations.getData("select * from products where productName='" + productName + "'");
                    if (rs1.next()) {
                        AvailablequantityProducts = rs1.getDouble("AvailableQty");
                        Taked = rs1.getDouble("Taked");
                        productId = rs1.getLong("productId");
                    }

                    dbOperations.setDataOrDelete("update products set AvailableQty=" + (AvailablequantityProducts + QuantityBack) + ",Taked=" + (Taked - QuantityBack) + " where productId=" + productId, "");

                    ResultSet rs2 = dbOperations.getData("select * from productsTypes where productId=" + productId);
                    while (rs2.next()) {
                        if ((QuantityBack / rs2.getDouble("QuantityOfOne")) % 2 == 0) {
                            dbOperations.setDataOrDelete("update productsTypes set "
                                    + "AllQuantity=" + (rs2.getDouble("AllQuantity") + (QuantityBack / rs2.getDouble("QuantityOfOne"))) + ""
                                    + " where id=" + rs2.getLong("id"), "");
                        } else {
                            dbOperations.setDataOrDelete("update productsTypes set "
                                    + "AllQuantity=" + rs2.getDouble("AllQuantity") + " "
                                    + "where id=" + rs2.getLong("id"), "");
                        }
                    }

                }
            } else {
                if (productName.contains("_")) {
                    ResultSet rs = dbOperations.getData("select * from productsTypes where productTypeName='" + productName + "'");
                    if (rs.next()) {
                        productId = rs.getLong("productId");
                        quantityOfOne = rs.getDouble("QuantityOfOne");
                        Allquantity = rs.getDouble("ِAvailableQtyStore");
                    }

                    dbOperations.setDataOrDelete("update productsTypes set ِAvailableQtyStore=" + (Allquantity + QuantityBack) + " where productTypeName='" + productName + "'", "");
                    ResultSet rs1 = dbOperations.getData("select * from products where productId=" + productId);
                    if (rs1.next()) {
                        AvailablequantityProducts = rs1.getDouble("ِAvailableQtyStore");
                        Taked = rs1.getDouble("Taked");
                    }
                    dbOperations.setDataOrDelete("update products set"
                            + " ِAvailableQtyStore=" + (AvailablequantityProducts + (QuantityBack * quantityOfOne)) + ","
                            + "Taked=" + (Taked - (QuantityBack * quantityOfOne)) + " where productId=" + productId, "");

                } else {
                    ResultSet rs1 = dbOperations.getData("select * from products where productName='" + productName + "'");
                    if (rs1.next()) {
                        AvailablequantityProducts = rs1.getDouble("ِAvailableQtyStore");
                        Taked = rs1.getDouble("Taked");
                        productId = rs1.getLong("productId");
                    }

                    dbOperations.setDataOrDelete("update products set ِAvailableQtyStore=" + (AvailablequantityProducts + QuantityBack) + ",Taked=" + (Taked - QuantityBack) + " where productId=" + productId, "");

                    ResultSet rs2 = dbOperations.getData("select * from productsTypes where productId=" + productId);
                    while (rs2.next()) {
                        if ((QuantityBack / rs2.getDouble("QuantityOfOne")) % 2 == 0) {
                            dbOperations.setDataOrDelete("update productsTypes set "
                                    + "AvailableQtyStore=" + (rs2.getDouble("AvailableQtyStore") + (QuantityBack / rs2.getDouble("QuantityOfOne"))) + ""
                                    + " where id=" + rs2.getLong("id"), "");
                        } else {
                            dbOperations.setDataOrDelete("update productsTypes set "
                                    + "AvailableQtyStore=" + rs2.getDouble("AvailableQtyStore") + " "
                                    + "where id=" + rs2.getLong("id"), "");
                        }
                    }

                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }
}
