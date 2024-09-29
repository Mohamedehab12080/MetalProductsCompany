package view;

import Database.dbOperations;
import TOOLS.TableCustom;
import com.google.zxing.Result;
import controller.customerController;
import controller.productsController;
import java.awt.AWTException;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.*;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.TimeZone;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import model.customers;
import model.productsTypes;
import model.productss;

public class SalesInvoces extends javax.swing.JPanel {

    String empname = "";
    static String value = "0";
//    double TotalAmmountForDiscount = 0;
//    int AllDiscount=0;
    double QuantityAvailGlobalTables = 0;
    double QuantityAvailGlobalProducts = 0;
    String productName = "";
    SimpleDateFormat timeFormat;
    double globalProductPrice = 0;
    double GlobalDiscount = 0;
    public Timer t;
//    public static String barCode_c="0";
    public static String cus_ID = "0";
    SimpleDateFormat df;
    String finalDate = "";
    String todayDate = "";
    public boolean pageState = true;

    public SalesInvoces() {

        initComponents();
        TOOLS.TableCustom.apply(jScrollPane1, TableCustom.TableType.MULTI_LINE);
        loadData();
        df = new SimpleDateFormat("yyyy-MM-dd", new Locale("en"));
        Date date = new Date();
        todayDate = df.format(date);
        finalDate = df.format(date);
        getLiveTime();
        btnAdd.setEnabled(false);
        txtCustomerName.setEnabled(true);
        btnPrint.setEnabled(false);
        txtInVIDReturn.setVisible(false);
        txtproductCode.requestFocus(true);
        btnUpdate.setEnabled(false);
        btnReturn.setEnabled(false);
        txtProductPrice.setEnabled(false);
        getAllPhonesId();
        txtPriceOfBuy.setVisible(false);
        getLastPhone();
    }

    public SalesInvoces(String empname) {

        initComponents();
        TOOLS.TableCustom.apply(jScrollPane1, TableCustom.TableType.MULTI_LINE);
        loadData();
         txtPriceOfBuy.setVisible(false);
        txtEmpName.setText(empname);
        df = new SimpleDateFormat("yyyy-MM-dd", new Locale("en"));
        Date date = new Date();
        todayDate = df.format(date);
        finalDate = df.format(date);
        getLiveTime();
        btnAdd.setEnabled(false);
        txtCustomerName.setEnabled(true);
        btnPrint.setEnabled(false);
        txtInVIDReturn.setVisible(false);
        txtproductCode.requestFocus(true);
        btnUpdate.setEnabled(false);
        btnReturn.setEnabled(false);
        txtProductPrice.setEnabled(false);
        getAllPhonesId();
        getLastPhone();
    }

    public void setEmpname(String empname) {
        this.empname = empname;
        txtEmpName.setText(empname);
    }

    public void getLiveTime() {
        TimeZone.setDefault(TimeZone.getTimeZone("Egypt"));
        t = new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date dt = new Date();
                timeFormat = new SimpleDateFormat("hh:mm:ss a", new Locale("en"));
                String tt = timeFormat.format(dt);
                TodayDate.setText(todayDate + " " + tt);
            }
        });
        t.start();
    }

    public void loadData() {
        try {
            ArrayList<customers> list = customerController.getAllCustomersRecords();
            Iterator<customers> itr = list.iterator();
            while (itr.hasNext()) {
                customers cus = itr.next();
                txtCustomerName.addItem(cus.getCustomerName());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        try {
            ArrayList<productss> list = productsController.getProductsNamesForCompo();
            Iterator<productss> itr = list.iterator();
            while (itr.hasNext()) {
                productss cus = itr.next();
                txtProductNameCompo.addItem(cus.getProductName());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        try {
            ResultSet rs = dbOperations.getData("select * from extra where exid=1");
            if (rs.next()) {
                txtInVID.setText(rs.getString("val"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        int i = Integer.valueOf(txtInVID.getText());
        i++;
        txtInVID.setText(String.valueOf(i));
        try {
            ResultSet rs = dbOperations.getData("select * from extra where exid=3");
            if (rs.next()) {
                txtInVIDReturn.setText(rs.getString("val"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        int i2 = Integer.valueOf(txtInVIDReturn.getText());
        i2++;
        txtInVIDReturn.setText(String.valueOf(i2));
    }

    public void getproductsTypesForCompo(String barcode) {
        try {
            if (!barcode.contains("-")) {
                ResultSet rs = dbOperations.getData("select * from products where productId=" + barcode);
                if (rs.next()) {
                    txtProductNameLabel.setText(rs.getString("productName"));
                } else {
                    txtProductNameLabel.setText("");
                }
                ArrayList<productsTypes> list = productsController.getAllTypesForCompoWithBarCode(barcode);
                Iterator<productsTypes> itr = list.iterator();
                txtTypeName.removeAllItems();
                txtTypeName.addItem(" ");
                while (itr.hasNext()) {
                    productsTypes prod = itr.next();
                    txtTypeName.addItem(prod.getProductTypeName().substring(prod.getProductTypeName().indexOf("_") + 1, prod.getProductTypeName().length()));
                }
            } else {
                System.out.println("666");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    public void setTotalQuantity() {
        txtTotalQuantity.setText(jTable1.getRowCount()+"");
    }

    public void getAllProductRecords(String id) {
        try {
            if (id.contains("-")) {
                System.out.println("LLLL");
            } else {
                ArrayList<productss> list = productsController.getAllRecordsForProductsWithID(id);
                Iterator<productss> itr = list.iterator();
                if (itr.hasNext()) {
                    productss prod = itr.next();
                    productName = prod.getProductName();
                    if (txtCheckboxPriceOfMarket.isSelected()) {
                        txtProductPrice.setText(String.valueOf(prod.getPriceOfMarket()));
                        globalProductPrice = prod.getPriceOfMarket();
                    } else {
                        txtProductPrice.setText(String.valueOf(prod.getPriceOfSale()));
                        globalProductPrice = prod.getPriceOfSale();
                    }
                    txtProiductCategory.setText(prod.getProductCategory());
                    txtPriceOfBuy.setText("" + prod.getPriceOfBuy());
                    if (txtMarketOrStore.getSelectedItem().toString().equals("محل")) {
                        txtAvailable.setText(String.valueOf(prod.getAvailableQty()));
                        QuantityAvailGlobalProducts = prod.getAvailableQty();
                    } else {
                        txtAvailable.setText(String.valueOf(prod.getAvailableQtyStore()));
                        QuantityAvailGlobalProducts = prod.getAvailableQtyStore();
                    }

                    if ((prod.getAvailableQty() + prod.getAvailableQtyStore()) <= prod.getQuantityWarning()) {
                        txtAvailable.setBackground(Color.RED);
                        txtAvailable.setOpaque(true);
                    } else {
                        txtAvailable.setBackground(Color.BLUE);
                        txtAvailable.setOpaque(true);
                    }
                     
                } else {
                    productName = "";
                    txtProductPrice.setText("0");
                    txtAvailable.setText("0");
                    QuantityAvailGlobalProducts = 0;
                    txtQuantityNeeded.setText("");
                    txtFirstPrice.setText("0");
                    txtProiductCategory.setText("_");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "eee");
        }
    }

    public void getAllProductsRecordsWithProductname(String productname) {
        try {
            ArrayList<productss> list = productsController.getAllProductsRecordsWithProductname(productname);
            Iterator<productss> itr = list.iterator();
            if (itr.hasNext()) {
                productss prod = itr.next();
                productName = prod.getProductName();
                txtproductCode.setText(String.valueOf(prod.getProductId()));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

//    public void getTotalFromTable() {
//        double total = 0;
//        try {
//            for (int i = 0; i < jTable1.getRowCount(); i++) {
//                total += Double.parseDouble(jTable1.getValueAt(i, 7).toString());//150+240
//            }
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, e);
//        }
//        TotalAmmountForDiscount = total;
//        if (!txtDiscount.getText().equals("0.0")) {
//            txtTOTAL.setText(String.valueOf((TotalAmmountForDiscount - Double.valueOf(txtDiscount.getText()))));
//            if (!txtPayedAmount.getText().equals("0.0")) {
//                if(Double.valueOf(txtPayedAmount.getText())>Double.valueOf(txtTOTAL.getText()))
//                {
//                    txtPayedAmount.setText(String.valueOf(Double.valueOf(txtTOTAL.getText())-Double.valueOf(txtPayedAmount.getText())*(-1)));
//                    txtRemained.setText(String.valueOf(( Double.valueOf(txtTOTAL.getText())-Double.valueOf(txtPayedAmount.getText()))));
//                }else 
//                {
//                    txtRemained.setText(String.valueOf(( Double.valueOf(txtTOTAL.getText())-Double.valueOf(txtPayedAmount.getText()))));
//                }
//            } else {
//                txtRemained.setText(String.valueOf(total));
//            }
//        } else {
//            txtTOTAL.setText(String.valueOf(total));
//            if (!txtPayedAmount.getText().equals("0.0")) {
//                  if(Double.valueOf(txtPayedAmount.getText())>Double.valueOf(txtTOTAL.getText()))
//                {
//                    txtPayedAmount.setText(String.valueOf(Double.valueOf(txtTOTAL.getText())-Double.valueOf(txtPayedAmount.getText())*(-1)));
//                    txtRemained.setText(String.valueOf(( Double.valueOf(txtTOTAL.getText())-Double.valueOf(txtPayedAmount.getText()))));
//                }else 
//                {
//                    txtRemained.setText(String.valueOf(( Double.valueOf(txtTOTAL.getText())-Double.valueOf(txtPayedAmount.getText()))));
//                }
//            } else {
//                txtRemained.setText(String.valueOf(total));
//            }
//        }
//
//    }
    public void getTotalFromTable() {
        double total = 0;
        try {

            for (int i = 0; i < jTable1.getRowCount(); i++) {
                total += Double.valueOf(jTable1.getValueAt(i, 7).toString());
            }
//                TotalAmmountForDiscount=total;
            txtTOTAL.setText("" + total);

            if (txtPayedAmount.getText().equals("0.0")) {
                txtRemained.setText(txtTOTAL.getText());
            } else {
                txtRemained.setText("" + (Double.valueOf(txtTOTAL.getText()) - Double.valueOf(txtPayedAmount.getText())));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public double getTotalForParameter() {
        double total = 0;
        try {
            for (int i = 0; i < jTable1.getRowCount(); i++) {
                total += Double.parseDouble(jTable1.getValueAt(i, 7).toString());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return total;
    }

    public void getAllCartWithINID(String inid) {
        try {
            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            dtm.setRowCount(0);
            ResultSet rs = dbOperations.getData("select * from cart where INID=" + inid);
            while (rs.next()) {
                dtm.addRow(new Object[]{
                    rs.getLong("INID"),
                    rs.getString("productname"),
                    rs.getString("BarCode"),
                    rs.getLong("quantityneeded"),
                    rs.getString("Unit_Price"),
                    rs.getLong("Discount"),
                    rs.getLong("Total_Price")
                });
            }
            ResultSet rs1 = dbOperations.getData("select * from sales where INID=" + inid);
            if (rs1.next()) {
                txtPayedAmount.setText(rs1.getString("payed"));
                txtRemained.setText(rs1.getString("Remained"));
                txtTOTAL.setText(String.valueOf(rs1.getLong("AllTOTAl")));
                txtReturnCustomername.setText(rs1.getString("customername"));
                txtTotalQuantity.setText(rs1.getString("Total_Quantity"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        btnAdd = new javax.swing.JButton();
        btnDeleteAll = new javax.swing.JButton();
        btnReturn = new javax.swing.JButton();
        txtDateOfInvoiceForBack = new javax.swing.JLabel();
        txtLastCasherNameForBack = new javax.swing.JLabel();
        btnUpdate = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtPayedAmount = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        txtTOTAL = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtRemained = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtRealTotal = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtTotalQuantity = new javax.swing.JLabel();
        TodayDate = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtDiscountLable = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txtStorePhone = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        txtPhoneId = new javax.swing.JComboBox<>();
        btnPrint = new javax.swing.JButton();
        txtSalePanel = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtCustomerName = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtQuantityNeeded = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtAvailable = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtFirstPrice = new javax.swing.JLabel();
        LiveTimes = new javax.swing.JLabel();
        txtproductCode = new javax.swing.JTextField();
        txtProductNameLabel = new javax.swing.JLabel();
        txtTypeName = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        txtProductNameCompo = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        txtCustomerNumber = new javax.swing.JTextField();
        txtMarketOrStore = new javax.swing.JComboBox<>();
        txtCheckboxPriceOfMarket = new javax.swing.JCheckBox();
        txtProiductCategory = new javax.swing.JLabel();
        txtPriceOfBuy = new javax.swing.JLabel();
        txtProductPrice = new javax.swing.JTextField();
        txtproductPriceCheckBox = new javax.swing.JCheckBox();
        jLabel20 = new javax.swing.JLabel();
        txtProductNameSearch = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtInVID = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtReturnCustomername = new javax.swing.JLabel();
        txtEmpName = new javax.swing.JLabel();
        txtINIDForBack = new javax.swing.JTextField();
        txtInVIDReturn = new javax.swing.JLabel();

        setBackground(new java.awt.Color(0, 153, 204));
        setAutoscrolls(true);
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 153, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jScrollPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane1MouseClicked(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "اسم المنتج", "مكان المنتج", "بار كود", "الكمية المطلوبة", "نوع السعر", "سعر البيع", "TOTAL", "قسم", "سعر الشراء"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTable1MouseEntered(evt);
            }
        });
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTable1KeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel4.setBackground(new java.awt.Color(51, 51, 51));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnAdd.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        btnAdd.setForeground(new java.awt.Color(0, 0, 0));
        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/add to cart.png"))); // NOI18N
        btnAdd.setText("اضافة");
        btnAdd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnDeleteAll.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        btnDeleteAll.setForeground(new java.awt.Color(0, 0, 0));
        btnDeleteAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/small Close.png"))); // NOI18N
        btnDeleteAll.setText("حذف الجميع");
        btnDeleteAll.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDeleteAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteAllActionPerformed(evt);
            }
        });

        btnReturn.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        btnReturn.setForeground(new java.awt.Color(0, 0, 0));
        btnReturn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/exit small.png"))); // NOI18N
        btnReturn.setText("استرجاع كلي/طباعة");
        btnReturn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnReturn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReturnActionPerformed(evt);
            }
        });

        txtDateOfInvoiceForBack.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtDateOfInvoiceForBack.setForeground(new java.awt.Color(255, 255, 255));

        txtLastCasherNameForBack.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtLastCasherNameForBack.setForeground(new java.awt.Color(255, 255, 255));

        btnUpdate.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        btnUpdate.setForeground(new java.awt.Color(0, 0, 0));
        btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/update.png"))); // NOI18N
        btnUpdate.setText("استرجاع جزء");
        btnUpdate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnReturn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDeleteAll, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtDateOfInvoiceForBack, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtLastCasherNameForBack, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(46, 46, 46))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addComponent(btnAdd)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDeleteAll)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnUpdate)
                .addGap(1, 1, 1)
                .addComponent(btnReturn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDateOfInvoiceForBack, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtLastCasherNameForBack, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(51, 51, 51));
        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("القيمة المدفوعة:");

        txtPayedAmount.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txtPayedAmount.setForeground(new java.awt.Color(0, 0, 0));
        txtPayedAmount.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPayedAmount.setText("0.0");
        txtPayedAmount.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtPayedAmountFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtPayedAmountFocusLost(evt);
            }
        });
        txtPayedAmount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPayedAmountKeyReleased(evt);
            }
        });

        jPanel6.setBackground(new java.awt.Color(51, 51, 51));
        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("الصافي:");

        txtTOTAL.setBackground(new java.awt.Color(0, 153, 51));
        txtTOTAL.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txtTOTAL.setForeground(new java.awt.Color(255, 255, 255));
        txtTOTAL.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtTOTAL.setText("0");
        txtTOTAL.setOpaque(true);

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("المتبقي:");

        txtRemained.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txtRemained.setForeground(new java.awt.Color(255, 255, 255));
        txtRemained.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtRemained.setText("0");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("الاجمالي:");

        txtRealTotal.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txtRealTotal.setForeground(new java.awt.Color(255, 255, 255));
        txtRealTotal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtRealTotal.setText("0");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtRealTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTOTAL, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtRemained, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18)
                    .addComponent(jLabel12)
                    .addComponent(jLabel11))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(txtRealTotal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(txtTOTAL))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtRemained)))
        );

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("الاصناف:");

        txtTotalQuantity.setBackground(new java.awt.Color(0, 153, 153));
        txtTotalQuantity.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txtTotalQuantity.setForeground(new java.awt.Color(255, 255, 255));
        txtTotalQuantity.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtTotalQuantity.setText("0");
        txtTotalQuantity.setOpaque(true);

        TodayDate.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        TodayDate.setForeground(new java.awt.Color(255, 255, 255));
        TodayDate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("خصم:");

        txtDiscountLable.setBackground(new java.awt.Color(0, 153, 204));
        txtDiscountLable.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txtDiscountLable.setForeground(new java.awt.Color(255, 255, 255));
        txtDiscountLable.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtDiscountLable.setText("0");
        txtDiscountLable.setOpaque(true);

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("رقم المحل:");

        txtStorePhone.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txtStorePhone.setForeground(new java.awt.Color(51, 51, 51));
        txtStorePhone.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtStorePhone.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtStorePhoneFocusGained(evt);
            }
        });
        txtStorePhone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtStorePhoneActionPerformed(evt);
            }
        });
        txtStorePhone.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtStorePhoneKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtStorePhoneKeyReleased(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/add.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        txtPhoneId.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtPhoneId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPhoneIdActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtStorePhone, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(TodayDate, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
                            .addComponent(txtPhoneId, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(17, 17, 17)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtTotalQuantity, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtDiscountLable, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtPayedAmount, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(jLabel6)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(txtPhoneId, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(TodayDate, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel14)
                                            .addComponent(txtDiscountLable))
                                        .addGap(9, 9, 9)
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel13)
                                            .addComponent(txtTotalQuantity))))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtPayedAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtStorePhone, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        btnPrint.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        btnPrint.setForeground(new java.awt.Color(0, 0, 0));
        btnPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/reports.png"))); // NOI18N
        btnPrint.setText("دفع/طباعة");
        btnPrint.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnPrint, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 1180, -1));

        txtSalePanel.setBackground(new java.awt.Color(51, 51, 51));
        txtSalePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("اسم العميل:");
        txtSalePanel.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1092, 86, -1, 28));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setText("كود المنتج:");
        txtSalePanel.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(819, 52, 96, 28));

        txtCustomerName.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtCustomerName.setForeground(new java.awt.Color(0, 0, 0));
        txtCustomerName.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", " " }));
        txtCustomerName.setToolTipText("");
        txtCustomerName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCustomerNameActionPerformed(evt);
            }
        });
        txtSalePanel.add(txtCustomerName, new org.netbeans.lib.awtextra.AbsoluteConstraints(937, 83, 143, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("الكمية المطلوبة:");
        txtSalePanel.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(419, 52, -1, 28));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel8.setText("سعر البيع:");
        txtSalePanel.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(819, 90, 96, -1));

        txtQuantityNeeded.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txtQuantityNeeded.setForeground(new java.awt.Color(51, 51, 51));
        txtQuantityNeeded.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtQuantityNeeded.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtQuantityNeededActionPerformed(evt);
            }
        });
        txtQuantityNeeded.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtQuantityNeededKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtQuantityNeededKeyReleased(evt);
            }
        });
        txtSalePanel.add(txtQuantityNeeded, new org.netbeans.lib.awtextra.AbsoluteConstraints(266, 52, 141, 31));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("الكمية المتوفرة:");
        txtSalePanel.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(819, 157, -1, -1));

        txtAvailable.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txtAvailable.setForeground(new java.awt.Color(255, 255, 255));
        txtAvailable.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtAvailable.setText("0.0");
        txtSalePanel.add(txtAvailable, new org.netbeans.lib.awtextra.AbsoluteConstraints(664, 159, 143, -1));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("التكلفة:");
        txtSalePanel.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(425, 90, 65, -1));

        txtFirstPrice.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txtFirstPrice.setForeground(new java.awt.Color(255, 255, 255));
        txtFirstPrice.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtFirstPrice.setText("0.0");
        txtSalePanel.add(txtFirstPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(272, 92, 141, -1));

        LiveTimes.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        LiveTimes.setForeground(new java.awt.Color(255, 255, 255));
        LiveTimes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtSalePanel.add(LiveTimes, new org.netbeans.lib.awtextra.AbsoluteConstraints(36, 52, 209, 28));

        txtproductCode.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txtproductCode.setForeground(new java.awt.Color(0, 0, 0));
        txtproductCode.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtproductCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtproductCodeActionPerformed(evt);
            }
        });
        txtproductCode.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtproductCodePropertyChange(evt);
            }
        });
        txtproductCode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtproductCodeKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtproductCodeKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtproductCodeKeyTyped(evt);
            }
        });
        txtSalePanel.add(txtproductCode, new org.netbeans.lib.awtextra.AbsoluteConstraints(664, 52, 143, 31));

        txtProductNameLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtProductNameLabel.setForeground(new java.awt.Color(255, 255, 255));
        txtProductNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtSalePanel.add(txtProductNameLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 18, 177, 28));

        txtTypeName.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtTypeName.setForeground(new java.awt.Color(0, 0, 0));
        txtTypeName.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", " " }));
        txtTypeName.setToolTipText("");
        txtTypeName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTypeNameActionPerformed(evt);
            }
        });
        txtSalePanel.add(txtTypeName, new org.netbeans.lib.awtextra.AbsoluteConstraints(568, 53, 84, 31));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("اسم المنتج:");
        txtSalePanel.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(1092, 162, -1, 28));

        txtProductNameCompo.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtProductNameCompo.setForeground(new java.awt.Color(0, 0, 0));
        txtProductNameCompo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", " " }));
        txtProductNameCompo.setToolTipText("");
        txtProductNameCompo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtProductNameCompoActionPerformed(evt);
            }
        });
        txtSalePanel.add(txtProductNameCompo, new org.netbeans.lib.awtextra.AbsoluteConstraints(937, 162, 143, 28));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("رقم العميل:");
        txtSalePanel.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(1086, 52, -1, 28));

        txtCustomerNumber.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txtCustomerNumber.setForeground(new java.awt.Color(0, 0, 0));
        txtCustomerNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCustomerNumberActionPerformed(evt);
            }
        });
        txtCustomerNumber.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtCustomerNumberPropertyChange(evt);
            }
        });
        txtCustomerNumber.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCustomerNumberKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCustomerNumberKeyTyped(evt);
            }
        });
        txtSalePanel.add(txtCustomerNumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(936, 54, 143, -1));

        txtMarketOrStore.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtMarketOrStore.setForeground(new java.awt.Color(0, 0, 0));
        txtMarketOrStore.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "محل", "مخزن" }));
        txtMarketOrStore.setToolTipText("");
        txtMarketOrStore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMarketOrStoreActionPerformed(evt);
            }
        });
        txtSalePanel.add(txtMarketOrStore, new org.netbeans.lib.awtextra.AbsoluteConstraints(568, 120, 84, -1));

        txtCheckboxPriceOfMarket.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtCheckboxPriceOfMarket.setForeground(new java.awt.Color(0, 0, 0));
        txtCheckboxPriceOfMarket.setText("تجاري");
        txtCheckboxPriceOfMarket.setHideActionText(true);
        txtCheckboxPriceOfMarket.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txtCheckboxPriceOfMarket.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        txtCheckboxPriceOfMarket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCheckboxPriceOfMarketActionPerformed(evt);
            }
        });
        txtSalePanel.add(txtCheckboxPriceOfMarket, new org.netbeans.lib.awtextra.AbsoluteConstraints(568, 86, 84, -1));

        txtProiductCategory.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtProiductCategory.setForeground(new java.awt.Color(255, 255, 255));
        txtProiductCategory.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtSalePanel.add(txtProiductCategory, new org.netbeans.lib.awtextra.AbsoluteConstraints(422, 18, 177, 28));

        txtPriceOfBuy.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txtPriceOfBuy.setForeground(new java.awt.Color(255, 255, 255));
        txtPriceOfBuy.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtPriceOfBuy.setText("0.0");
        txtSalePanel.add(txtPriceOfBuy, new org.netbeans.lib.awtextra.AbsoluteConstraints(664, 125, 143, -1));

        txtProductPrice.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtProductPrice.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtProductPrice.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtProductPriceKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtProductPriceKeyReleased(evt);
            }
        });
        txtSalePanel.add(txtProductPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(705, 90, 102, 25));

        txtproductPriceCheckBox.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txtproductPriceCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtproductPriceCheckBoxActionPerformed(evt);
            }
        });
        txtSalePanel.add(txtproductPriceCheckBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(664, 90, 33, 25));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("اسم المنتج:");
        txtSalePanel.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(1092, 123, 92, 28));

        txtProductNameSearch.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txtProductNameSearch.setForeground(new java.awt.Color(0, 0, 0));
        txtProductNameSearch.setAutoscrolls(false);
        txtProductNameSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtProductNameSearchActionPerformed(evt);
            }
        });
        txtProductNameSearch.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtProductNameSearchPropertyChange(evt);
            }
        });
        txtProductNameSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtProductNameSearchKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtProductNameSearchKeyTyped(evt);
            }
        });
        txtSalePanel.add(txtProductNameSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(937, 125, 143, -1));

        add(txtSalePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 67, 1190, 190));

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("رقم الفاتورة:");

        txtInVID.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txtInVID.setForeground(new java.awt.Color(255, 255, 255));
        txtInVID.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txtInVID.setText("0");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/salary.png"))); // NOI18N
        jLabel3.setText("ادارة الفواتير");

        txtReturnCustomername.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtReturnCustomername.setForeground(new java.awt.Color(255, 255, 255));

        txtEmpName.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtEmpName.setForeground(new java.awt.Color(255, 255, 255));
        txtEmpName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        txtINIDForBack.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txtINIDForBack.setForeground(new java.awt.Color(0, 0, 0));
        txtINIDForBack.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtINIDForBack.setText("0");
        txtINIDForBack.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtINIDForBackFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtINIDForBackFocusLost(evt);
            }
        });
        txtINIDForBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtINIDForBackActionPerformed(evt);
            }
        });
        txtINIDForBack.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtINIDForBackPropertyChange(evt);
            }
        });
        txtINIDForBack.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtINIDForBackKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtINIDForBackKeyTyped(evt);
            }
        });

        txtInVIDReturn.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txtInVIDReturn.setForeground(new java.awt.Color(255, 255, 255));
        txtInVIDReturn.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txtInVIDReturn.setText("0");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtEmpName, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtReturnCustomername, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 160, Short.MAX_VALUE)
                .addComponent(txtInVIDReturn, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtINIDForBack, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtInVID, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtReturnCustomername, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmpName, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtInVID, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)
                        .addComponent(txtINIDForBack, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtInVIDReturn, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(21, 21, 21))
        );

        add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 12, 1190, 55));
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        if (txtQuantityNeeded.getText().length() != 0 && txtproductCode.getText().length() != 0 && Double.valueOf(txtProductPrice.getText()) > 1) {
            boolean state = true;
            long productId = 0;
            double QuantityOfOne = 0;
            double AvailableQty = 0;
            double Taked = 0;
            String AfterPoint;
            try {
                Vector v = new Vector();
                DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
                if (txtTypeName.getSelectedIndex() == 0) {
                    for (int i = 0; i < jTable1.getRowCount(); i++) {
                       if(txtCheckboxPriceOfMarket.isSelected())
                       {
                            if (String.valueOf(jTable1.getValueAt(i, 1)).equals(txtProductNameLabel.getText())
                                && txtMarketOrStore.getSelectedItem().toString().equals(jTable1.getValueAt(i, 2).toString())&&jTable1.getValueAt(i, 5).toString().contains(txtCheckboxPriceOfMarket.getText())) {
                            state = false;
                            jTable1.setValueAt(Double.valueOf(jTable1.getValueAt(i, 4).toString()) + Double.valueOf(txtQuantityNeeded.getText()), i, 4);
                            jTable1.setValueAt(Double.valueOf(jTable1.getValueAt(i, 7).toString()) + Double.valueOf(txtFirstPrice.getText()), i, 7);
                        }
                       }else 
                       {
                            if (String.valueOf(jTable1.getValueAt(i, 1)).equals(txtProductNameLabel.getText())
                                && txtMarketOrStore.getSelectedItem().toString().equals(jTable1.getValueAt(i, 2).toString())&&jTable1.getValueAt(i, 5).toString().contains("عادي")) {
                            state = false;
                            jTable1.setValueAt(Double.valueOf(jTable1.getValueAt(i, 4).toString()) + Double.valueOf(txtQuantityNeeded.getText()), i, 4);
                            jTable1.setValueAt(Double.valueOf(jTable1.getValueAt(i, 7).toString()) + Double.valueOf(txtFirstPrice.getText()), i, 7);
                        }
                       }
                    }
                } else {
                    for (int i = 0; i < jTable1.getRowCount(); i++) {
                         if(txtCheckboxPriceOfMarket.isSelected())
                       {
                            if (String.valueOf(jTable1.getValueAt(i, 1)).equals(txtProductNameLabel.getText() + "_" + txtTypeName.getSelectedItem())
                                && txtMarketOrStore.getSelectedItem().toString().equals(jTable1.getValueAt(i, 2).toString())&&jTable1.getValueAt(i, 5).toString().contains(txtCheckboxPriceOfMarket.getText())) {
                            state = false;
                            jTable1.setValueAt(Double.valueOf(jTable1.getValueAt(i, 4).toString()) + Double.valueOf(txtQuantityNeeded.getText()), i, 4);
                            jTable1.setValueAt(Double.valueOf(jTable1.getValueAt(i, 7).toString()) + Double.valueOf(txtFirstPrice.getText()), i, 7);
                        }
                       }else 
                       {
                            if (String.valueOf(jTable1.getValueAt(i, 1)).equals(txtProductNameLabel.getText() + "_" + txtTypeName.getSelectedItem())
                                && txtMarketOrStore.getSelectedItem().toString().equals(jTable1.getValueAt(i, 2).toString())&&jTable1.getValueAt(i, 5).toString().contains("عادي")) {
                            state = false;
                            jTable1.setValueAt(Double.valueOf(jTable1.getValueAt(i, 4).toString()) + Double.valueOf(txtQuantityNeeded.getText()), i, 4);
                            jTable1.setValueAt(Double.valueOf(jTable1.getValueAt(i, 7).toString()) + Double.valueOf(txtFirstPrice.getText()), i, 7);
                        }
                       }
                    }
                }
                if (state) {
                    if (!txtINIDForBack.getText().equals("0")) {
                        v.add(txtINIDForBack.getText());
                    } else {
                        v.add(txtInVID.getText());
                    }
                    if (txtTypeName.getSelectedIndex() != 0) {
                        v.add(txtProductNameLabel.getText() + "_" + txtTypeName.getSelectedItem());
                    } else {
                        v.add(txtProductNameLabel.getText());
                    }
                    v.add(txtMarketOrStore.getSelectedItem().toString());
                    v.add(txtproductCode.getText());
                    v.add(txtQuantityNeeded.getText());
                    if (txtCheckboxPriceOfMarket.isSelected()) {
                        v.add(txtCheckboxPriceOfMarket.getText());
                    } else if (!txtCheckboxPriceOfMarket.isSelected() && (globalProductPrice - Double.valueOf(txtProductPrice.getText())) != 0) {
                        v.add("سعر معدل");
                    } else {
                        v.add("عادي");
                    }
                    v.add(txtProductPrice.getText());
                    v.add(txtFirstPrice.getText());
                    v.add(txtProiductCategory.getText());
                    v.add(txtPriceOfBuy.getText());
                    dtm.addRow(v);
                }

                txtCustomerName.setEnabled(false);

                setTotalQuantity();
                if (txtMarketOrStore.getSelectedItem().toString().equals("محل")) {
                    if (txtTypeName.getSelectedIndex() == 0) {
                        productsController.updateAvailableWhenAddFromProductsTable(Double.valueOf(txtAvailable.getText()),
                                Double.valueOf(txtQuantityNeeded.getText()),
                                txtproductCode.getText(), txtMarketOrStore.getSelectedItem().toString());
                        ResultSet rs = dbOperations.getData("select * from productsTypes where productId=" + txtproductCode.getText());
                        while (rs.next()) {
//                            AfterPoint = String.valueOf(Double.parseDouble(txtQuantityNeeded.getText()) / rs.getDouble("QuantityOfOne"));
//                            if (AfterPoint.substring(AfterPoint.indexOf(".") + 1, AfterPoint.length()).equals("0")) {
//                                dbOperations.setDataOrDelete("update productsTypes set AllQuantity=" + (rs.getDouble("AllQuantity") - (Double.valueOf(txtQuantityNeeded.getText()) / rs.getDouble("QuantityOfOne"))) + " where id=" + rs.getLong("id"), "");
//                            } else {
                                ResultSet rsw = dbOperations.getData("select * from products where productId=" + txtproductCode.getText());
                                if (rsw.next()) {
                                    dbOperations.setDataOrDelete("update productsTypes set "
                                            + "AllQuantity=" + String.format("%.3f",(rsw.getDouble("AvailableQty") /rs.getDouble("QuantityOfOne"))) + " "
                                            + "where id=" + rs.getLong("id"), "");
                                }

//                            }
                        }
                    } else {
                        ResultSet rs1 = dbOperations.getData("select * from productsTypes where "
                                + "productTypeName='" + txtProductNameLabel.getText() + "_" + txtTypeName.getSelectedItem() + "'");
                        if (rs1.next()) {
                            QuantityOfOne = rs1.getDouble("QuantityOfOne");
                            productId = rs1.getLong("productId");
                        }
                        ResultSet rs2 = dbOperations.getData("select * from products where productId=" + productId);
                        if (rs2.next()) {
                            Taked = rs2.getDouble("Taked");
                            AvailableQty = rs2.getDouble("AvailableQty");
                        }
                        dbOperations.setDataOrDelete("update productsTypes set"
                                + " AllQuantity=" + txtAvailable.getText() + " where "
                                + "productTypeName='" + txtProductNameLabel.getText() + "_" + txtTypeName.getSelectedItem() + "'", "");
                         double newAvailableQty=(AvailableQty - (Double.valueOf(txtQuantityNeeded.getText()) * QuantityOfOne));
                        dbOperations.setDataOrDelete("update products set "
                                + "AvailableQty=" + newAvailableQty + ""
                                + ",Taked=" + ((Double.valueOf(txtQuantityNeeded.getText()) * QuantityOfOne) + Taked) + ""
                                + " where productId=" + productId, "");
                        ResultSet rs3=dbOperations.getData("select * from productsTypes where productId="+productId);
                        while(rs3.next())
                        {
                            double QuantityOfOneOfAll=rs3.getDouble("QuantityOfOne");
                               dbOperations.setDataOrDelete("update productsTypes set"
                                + " AllQuantity=" +String.format("%.3f",((double)newAvailableQty/(double)QuantityOfOneOfAll))+ " where "
                                 + "id=" +rs3.getLong("id"), "");
                        }
                      
                       
                    }
                } else {
                    if (txtTypeName.getSelectedIndex() == 0) {
                        productsController.updateAvailableWhenAddFromProductsTable(Double.valueOf(txtAvailable.getText()),
                                Double.valueOf(txtQuantityNeeded.getText()),
                                txtproductCode.getText(), txtMarketOrStore.getSelectedItem().toString());
                        ResultSet rs = dbOperations.getData("select * from productsTypes where productId=" + txtproductCode.getText());
                        while (rs.next()) {
//                            AfterPoint = String.valueOf(Double.parseDouble(txtQuantityNeeded.getText()) / rs.getDouble("QuantityOfOne"));
//                            if (AfterPoint.substring(AfterPoint.indexOf(".") + 1, AfterPoint.length()).equals("0")) {
//                                dbOperations.setDataOrDelete("update productsTypes set AvailableQtyStore=" + (rs.getDouble("AvailableQtyStore") - (Double.valueOf(txtQuantityNeeded.getText()) / rs.getDouble("QuantityOfOne"))) + " where id=" + rs.getLong("id"), "");
//                            } else {
                                ResultSet rsw = dbOperations.getData("select * from products where productId=" + txtproductCode.getText());
                                if (rsw.next()) {
                                    dbOperations.setDataOrDelete("update productsTypes set "
                                            + "AvailableQtyStore=" +String.format("%.3f", ((double)rsw.getDouble("AvailableQtyStore") / (double)rs.getDouble("QuantityOfOne")) )+ " "
                                            + "where id=" + rs.getLong("id"), "");
                                }
//                            }
                        }
                    } else {
                        ResultSet rs1 = dbOperations.getData("select * from productsTypes where "
                                + "productTypeName='" + txtProductNameLabel.getText() + "_" + txtTypeName.getSelectedItem() + "'");
                        if (rs1.next()) {
                            QuantityOfOne = rs1.getDouble("QuantityOfOne");
                            productId = rs1.getLong("productId");
                        }
                        ResultSet rs2 = dbOperations.getData("select * from products where productId=" + productId);
                        if (rs2.next()) {
                            Taked = rs2.getDouble("Taked");
                            AvailableQty = rs2.getDouble("AvailableQtyStore");
                        }
//                        dbOperations.setDataOrDelete("update productsTypes set"
//                                + " AvailableQtyStore=" + txtAvailable.getText() + " where "
//                                + "productTypeName='" + txtProductNameLabel.getText() + "_" + txtTypeName.getSelectedItem() + "'", "");
//                        dbOperations.setDataOrDelete("update products set "
//                                + "AvailableQtyStore=" + (AvailableQty - (Double.valueOf(txtQuantityNeeded.getText()) * QuantityOfOne)) + ""
//                                + ",Taked=" + ((Double.valueOf(txtQuantityNeeded.getText()) * QuantityOfOne) + Taked) + ""
//                                + " where productId=" + productId, "");
//                        
                          double newAvailableQty=(AvailableQty - (Double.valueOf(txtQuantityNeeded.getText()) * QuantityOfOne));
                        dbOperations.setDataOrDelete("update products set "
                                + "AvailableQtyStore=" + newAvailableQty + ""
                                + ",Taked=" + ((Double.valueOf(txtQuantityNeeded.getText()) * QuantityOfOne) + Taked) + ""
                                + " where productId=" + productId, "");
                        ResultSet rs3=dbOperations.getData("select * from productsTypes where productId="+productId);
                        while(rs3.next())
                        {
                            double QuantityOfOneOfAll=rs3.getDouble("QuantityOfOne");
                               dbOperations.setDataOrDelete("update productsTypes set"
                                + " AvailableQtyStore=" +String.format("%.3f",((double)newAvailableQty/(double)QuantityOfOneOfAll))+ " where "
                                + "id=" +rs3.getLong("id"), "");
                        }
                      
                    }
                }
                getTotalFromTable();
                getRealTotalAndDiscount();
                productName = "";
                txtProductPrice.setText("0");
                txtAvailable.setText("0");
                QuantityAvailGlobalProducts = 0;
                QuantityAvailGlobalTables = 0;
                txtProductNameLabel.setText("");
                txtQuantityNeeded.setText("");
                txtFirstPrice.setText("0");
                btnAdd.setEnabled(false);
                txtproductCode.setText("");
                txtTypeName.setSelectedIndex(0);
                txtCustomerNumber.setEnabled(false);
                txtCustomerName.setEnabled(false);
                if (jTable1.getRowCount() != 0) {
                    this.pageState = false;
                }
                if (txtINIDForBack.getText().equals("0")) {
                    btnReturn.setEnabled(false);
                    btnUpdate.setEnabled(false);
                } else {
                    btnReturn.setEnabled(false);
                    btnUpdate.setEnabled(true);
                }
                txtPriceOfBuy.setText("0");
                txtProiductCategory.setText("");
                txtproductCode.requestFocus(true);
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        } else {
            JOptionPane.showMessageDialog(null, "حدد الكمية المطلوبة");
        }
    }//GEN-LAST:event_btnAddActionPerformed

    public void getRealTotalAndDiscount() {
        double LocGlobalDiscount = 0;
        double realTotal = 0;
        try {
            for (int i = 0; i < jTable1.getRowCount(); i++) {
                if (jTable1.getValueAt(i, 5).toString().equals("تجاري") || jTable1.getValueAt(i, 5).equals("عادي")) {
                    LocGlobalDiscount = 0;
                    realTotal += Double.valueOf(jTable1.getValueAt(i, 7).toString());
                } else {
                    if (jTable1.getValueAt(i, 1).toString().contains("_")) {
                        ResultSet rss = dbOperations.getData("select * from productsTypes where productTypeName='" + jTable1.getValueAt(i, 1).toString() + "'");
                        if (rss.next()) {
                            if ((rss.getDouble("priceOfSale") - Double.valueOf(jTable1.getValueAt(i, 6).toString())) > 1) {
                                LocGlobalDiscount += ((rss.getDouble("priceOfSale") - Double.valueOf(jTable1.getValueAt(i, 6).toString())) * Double.valueOf(jTable1.getValueAt(i, 4).toString()));
                                realTotal += (rss.getDouble("priceOfSale") * Double.valueOf(jTable1.getValueAt(i, 4).toString()));
                            }
                        }
                    } else {
                        ResultSet rss = dbOperations.getData("select * from products where productName='" + jTable1.getValueAt(i, 1).toString() + "'");
                        if (rss.next()) {
                            if ((rss.getDouble("priceOfSale") - Double.valueOf(jTable1.getValueAt(i, 6).toString())) > 1) {
                                LocGlobalDiscount += ((rss.getDouble("priceOfSale") - Double.valueOf(jTable1.getValueAt(i, 6).toString())) * Double.valueOf(jTable1.getValueAt(i, 4).toString()));

                                realTotal += (rss.getDouble("priceOfSale") * Double.valueOf(jTable1.getValueAt(i, 4).toString())); //500
                            }
                        }
                    }
                }
            }
            if (realTotal < Double.valueOf(txtTOTAL.getText()) && LocGlobalDiscount > 1) {
                txtDiscountLable.setText(LocGlobalDiscount + "");
                txtRealTotal.setText((Double.valueOf(txtTOTAL.getText()) - LocGlobalDiscount) + "");

            } else if (realTotal < Double.valueOf(txtTOTAL.getText())) {
                txtDiscountLable.setText(LocGlobalDiscount + "");
                txtRealTotal.setText(txtTOTAL.getText() + "");
            } else {
                txtDiscountLable.setText(LocGlobalDiscount + "");
                txtRealTotal.setText(realTotal + "");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }
    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        if (jTable1.getRowCount() != 0) {
            if (!txtRemained.getText().equals("0.0") && txtCustomerName.getSelectedIndex() == 0) {
                int x = JOptionPane.showConfirmDialog(null, "<html><h1>يجب تحديد اسم العميل</h1></html>", "تحذير", JOptionPane.WARNING_MESSAGE);
            } else {
                int INid = 0;
                String SaleTypeForSale = "عادي";
                double realTotal = 0;
                try {
                    String productname;
                    String barCode;
                    String quantityNeeded;
                    String productPlace;
                    double productPrice;
                    String TotalPrice;
                    String SaleType;
                    String category;
                    String priceOfBuy;
                    int len = jTable1.getRowCount();
                    DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
                    for (int i = 0; i < len; i++) {
                        INid = Integer.parseInt(dtm.getValueAt(i, 0).toString());
                        productname = dtm.getValueAt(i, 1).toString();
                        barCode = dtm.getValueAt(i, 3).toString();
                        quantityNeeded = dtm.getValueAt(i, 4).toString();
                        productPrice = Double.parseDouble(dtm.getValueAt(i, 6).toString());
                        SaleType = dtm.getValueAt(i, 5).toString();
                        TotalPrice = dtm.getValueAt(i, 7).toString();
                        productPlace = dtm.getValueAt(i, 2).toString();
                        category = dtm.getValueAt(i, 8).toString();
                        priceOfBuy = dtm.getValueAt(i, 9).toString();
                        dbOperations.setDataOrDelete("Insert into cart ("
                                + "INID,"
                                + "productname,"
                                + "BarCode,"
                                + "quantityneeded,"
                                + "Unit_Price,"
                                + "Total_Price"
                                + ",empname,"
                                + "date,"
                                + "productPlace,"
                                + "SaleType,category,priceOfBuy,priceOnStore,customerNumber) values("
                                + INid + ",'"
                                + productname
                                + "','" + barCode
                                + "'," + quantityNeeded
                                + "," + productPrice
                                + "," + TotalPrice
                                + ",'" + txtEmpName.getText()
                                + "','" + TodayDate.getText()
                                + "','" + productPlace
                                + "','" + SaleType + "','" + category + "'," + priceOfBuy + "," + (Double.valueOf(quantityNeeded) * Double.valueOf(priceOfBuy)) + ",'" + txtCustomerNumber.getText() + "')", "");
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }

                try {
                    String id = txtInVID.getText();
                    dbOperations.setDataOrDelete("Update extra set val='" + id + "' where exid=1", "");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
                try {

                    double finaltotal = Double.parseDouble(txtTOTAL.getText());
                    double finalRemained = Double.parseDouble(txtRemained.getText());
                    String Status = null;
                    if (finalRemained == 0.0) {
                        Status = "مدفوع";
                    } else if (finalRemained < finaltotal) {
                        Status = "متبقي";
                    } else if (finaltotal == finalRemained) {
                        Status = "غير مدفوع";
                    }
                    for (int i = 0; i < jTable1.getRowCount(); i++) {
                        if (jTable1.getValueAt(i, 5).toString().equals("تجاري")) {
                            SaleTypeForSale = "تجاري";
                            GlobalDiscount = 0.0;
                        } else if (jTable1.getValueAt(i, 5).toString().equals("سعر معدل")) {
                            SaleTypeForSale = "تم تعديل سعر اثناء اصدار الفاتورة";
                        }
                    }
                    String customerName;
                    if (txtCustomerName.getSelectedIndex() != 0) {
                        customerName = txtCustomerName.getSelectedItem().toString();
                    } else {
                        customerName = "زبون";
                    }
                    dbOperations.setDataOrDelete("insert into sales ("
                            + "INID,"
                            + "customername,"
                            + "Total_Quantity,"
                            + "payed,"
                            + "Status,"
                            + "Remained,"
                            + "Date,"
                            + "empname,"
                            + "Discount,"
                            + "AllTotal,"
                            + "DayDate,Total,SaleType,storePhone) values("
                            + INid
                            + ",'" + customerName
                            + "'," + txtTotalQuantity.getText()
                            + "," + txtPayedAmount.getText()
                            + ",'" + Status
                            + "'," + txtRemained.getText()
                            + ",'" + TodayDate.getText()
                            + "','" + txtEmpName.getText()
                            + "'," + txtDiscountLable.getText()
                            + "," + txtTOTAL.getText()
                            + ",'" + todayDate + "'," + txtRealTotal.getText()
                            + ",'" + SaleTypeForSale + "','"+txtStorePhone.getText()+"')", "");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, ex);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }

           
                try {
                    ResultSet rs = dbOperations.getData("select * from capenat where id=(select max(id) from capenat)");
                    if (rs.next()) {
                        dbOperations.setDataOrDelete("insert into capenat (realValue,editedvalue,afterEdictvalue,OperationType,date) "
                                + "values(" + rs.getDouble("afterEdictvalue") + "," + txtPayedAmount.getText() + "," + (rs.getDouble("afterEdictvalue") + Double.valueOf(txtPayedAmount.getText())) + ",'توريد المبيعات','" + TodayDate.getText() + "')", "تم توريد المبلغ المدفوع الي الخزنة");
                    }

                    try {
                        if (!txtRemained.getText().equals("0.0")) {
                            ResultSet rsc = dbOperations.getData("select * from customers where customerName='" + txtCustomerName.getSelectedItem().toString() + "'");
                            if (rsc.next()) {
                                dbOperations.setDataOrDelete("update customers set AllRemained=" + (rsc.getDouble("AllRemained") + Double.valueOf(txtRemained.getText())) + " where customerName='" + txtCustomerName.getSelectedItem() + "'", "تم تسجيل المتبقي");
                            }
                        }
                       
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, e);
                    }
                        
                    DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
                    dtm.setRowCount(0);
                    txtCustomerName.setEnabled(true);
                    txtCustomerName.setSelectedIndex(0);
                    txtproductCode.setText("");
                    txtProductPrice.setText("0");
                    txtAvailable.setText("0");
                    txtQuantityNeeded.setText("");
                    txtTotalQuantity.setText("0");
                    txtDiscountLable.setText("0.0");
                    txtPayedAmount.setText("0.0");
                    txtFirstPrice.setText("0");
                    txtTypeName.removeAll();
                    txtTypeName.addItem(" ");
                    txtTOTAL.setText("0");
                    txtTotalQuantity.setText("0");
                    txtRemained.setText("0");
                    txtPriceOfBuy.setText("0");
                    txtProiductCategory.setText("");
                    txtRealTotal.setText("0.0");
                    txtCustomerNumber.setText("");
                    txtproductPriceCheckBox.setSelected(false);
                    pageState = true;
                    txtCustomerNumber.setEnabled(true);
                     txtproductCode.requestFocus(true);
                      try {
              HashMap para2 = new HashMap();
                para2.put("invo_para", txtInVID.getText());  // inv_id  is ireport parameter name
                para2.put("Inv_Type", "فاتورة بيع");
                para2.put("copyRight", (char)169+"Mo_Software:01091499680");
                ReportView r2 = new ReportView("src\\reports\\reviewCartReport.jasper", para2);
                r2.view();
                    new barcodeGenerator(txtInVID.getText()).generate();
                    HashMap para = new HashMap();
                    para.put("invo_para", Integer.parseInt(txtInVID.getText()));  // inv_id  is ireport parameter name
                    para.put("Inv_Type", "فاتورة بيع:");
                    para.put("copyRight", (char)169+"Mo_Software:01091499680");
                    ReportView r = new ReportView("src\\reports\\finalCartReport.jasper", para);
                    r.view();
                txtInVID.setText(String.valueOf(Integer.valueOf(txtInVID.getText()) + 1));

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
        }

    }//GEN-LAST:event_btnPrintActionPerformed

    private void txtQuantityNeededKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQuantityNeededKeyReleased
        if (txtTypeName.getSelectedIndex() != 0) {
            try {
                double QuantityNeeded = Double.parseDouble(txtQuantityNeeded.getText());
                double productPrice = Double.parseDouble(txtProductPrice.getText());
                if (QuantityNeeded > 0) {
                    if (QuantityNeeded <= QuantityAvailGlobalTables) {
                        txtAvailable.setText((QuantityAvailGlobalTables - QuantityNeeded)+"");
//                     txtQuantityNeeded.setForeground(new Color(0, 255,0 ));
                        txtFirstPrice.setText((QuantityNeeded * productPrice)+"");
                        btnAdd.setEnabled(true);
                    } else {
                        txtAvailable.setText( QuantityAvailGlobalTables+"");
//                    txtQuantityNeeded.setForeground(new Color(255, 0, 0));
                        txtFirstPrice.setText(String.valueOf(0));
                        btnAdd.setEnabled(false);
                    }
                } else {
                    txtAvailable.setText(QuantityAvailGlobalTables+"");
//                txtQuantityNeeded.setForeground(new Color(255, 0, 0));
                    txtFirstPrice.setText(String.valueOf(0));
                    btnAdd.setEnabled(false);
                }
            } catch (NumberFormatException es) {
                txtAvailable.setText(QuantityAvailGlobalTables+"");
//                txtQuantityNeeded.setForeground(new Color(255, 0, 0));
                txtFirstPrice.setText(String.valueOf(0));
                btnAdd.setEnabled(false);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        } else {
            try {
                double QuantityNeeded = Double.parseDouble(txtQuantityNeeded.getText());
                double productPrice = Double.parseDouble(txtProductPrice.getText());
                if (QuantityNeeded > 0) {
                    if (QuantityNeeded <= QuantityAvailGlobalProducts) {
                        txtAvailable.setText((QuantityAvailGlobalProducts - QuantityNeeded)+"");
//                    txtQuantityNeeded.setForeground(new Color(100, 255, 100));
                        txtFirstPrice.setText((QuantityNeeded * productPrice)+"");
                        btnAdd.setEnabled(true);
                    } else {
                        txtAvailable.setText((QuantityAvailGlobalProducts)+"");
//                    txtQuantityNeeded.setForeground(new Color(255, 0, 0));
                        txtFirstPrice.setText(String.valueOf(0));
                        btnAdd.setEnabled(false);
                    }
                } else {
                    txtAvailable.setText((QuantityAvailGlobalProducts)+"");
//                txtQuantityNeeded.setForeground(new Color(255, 0, 0));
                    txtFirstPrice.setText(String.valueOf(0));
                    btnAdd.setEnabled(false);
                }
            } catch (NumberFormatException es) {
                txtAvailable.setText((QuantityAvailGlobalProducts)+"");
//                txtQuantityNeeded.setForeground(new Color(255, 0, 0));
                txtFirstPrice.setText(String.valueOf(0));
                btnAdd.setEnabled(false);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
            pageState = false;
        }
    }//GEN-LAST:event_txtQuantityNeededKeyReleased

    public  void getbtnDeleteAllAction()
    {
        try {
            btnDeleteAllActionPerformed(new ActionEvent(0, 0, ""));
        } catch (Exception e) {
            
        }
    }
    private void btnDeleteAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteAllActionPerformed
        try {
            String productName[] = new String[jTable1.getRowCount()];
            double Quantityneeded[] = new double[jTable1.getRowCount()];
            String productPlace[] = new String[jTable1.getRowCount()];
            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            for (int i = 0; i < dtm.getRowCount(); i++) {
                productName[i] = jTable1.getValueAt(i, 1).toString();
                Quantityneeded[i] = Double.parseDouble(jTable1.getValueAt(i, 4).toString());
                productPlace[i] = jTable1.getValueAt(i, 2).toString();
            }
            productsController.updateProductAvailableWhenDelete(productName, Quantityneeded, productPlace);
            dtm.setRowCount(0);
            txtCustomerName.setEnabled(true);
            this.pageState = true;
            txtTOTAL.setText("0.0");
            txtRealTotal.setText("0.0");
            txtPayedAmount.setText("0.0");
            txtTotalQuantity.setText("0.0");
            txtDiscountLable.setText("0.0");
            txtRemained.setText("0.0");
            txtproductCode.requestFocus(true);
            txtCustomerName.setEnabled(true);
            txtCustomerNumber.setEnabled(true);
            txtproductPriceCheckBox.setSelected(false);
            txtProductPrice.setEnabled(false);
            btnAdd.setEnabled(false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_btnDeleteAllActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        String INid = txtInVIDReturn.getText();
        String productname = "";
        String barCode = "";
        String quantityNeeded = "";
        String productPlace = "";
        String category;
        String AfterPoint;
        double QuantityOfOne = 0;
        double AllQuantity = 0;
        double Taked = 0;
        double AvailableQty = 0;
        long productId = 0;
        double newQuantity = 0;
        double productPrice = 0;
        double TotalPrice = 0;
        String SaleType = "";
        try {
            Robot r = new Robot();
            int index = jTable1.getSelectedRow();
            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            productname = dtm.getValueAt(index, 1).toString();
            barCode = dtm.getValueAt(index, 3).toString();
            quantityNeeded = dtm.getValueAt(index, 4).toString();
            productPrice = Double.parseDouble(dtm.getValueAt(index, 6).toString());
            SaleType = dtm.getValueAt(index, 5).toString();
            TotalPrice = Double.valueOf(dtm.getValueAt(index, 7).toString());
//                    TotalPrice = Double.valueOf(String.format("%.3f",(Double.valueOf(dtm.getValueAt(index,7).toString())-((Double.valueOf(txtDiscount.getText())/getTotalForParameter())*Double.valueOf(dtm.getValueAt(index,7).toString())))));
            productPlace = dtm.getValueAt(index, 2).toString();
            category = dtm.getValueAt(index, 8).toString();
            txtSalePanel.setEnabled(true);
            txtProiductCategory.setText(category);
            if (txtINIDForBack.getText().equals("0")) {
                int a = JOptionPane.showConfirmDialog(null, "هل تريد الحذف؟", "اختر", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (a == 0) {
                    try {
                        if (productPlace.equals("محل")) {
                            if (!productname.contains("_")) {
                                ResultSet rs4 = dbOperations.getData("select * from products where productName='" + productname + "'");
                                if (rs4.next()) {
                                    newQuantity = rs4.getDouble("AvailableQty") + Double.valueOf(quantityNeeded);
                                }
                                productsController.updateAvailableWhenReturnFromProductsTable(newQuantity,
                                        Double.valueOf(quantityNeeded),
                                        barCode, productPlace);
                                ResultSet rs = dbOperations.getData("select * from productsTypes where productId=" + barCode);
                                while (rs.next()) {
//                                    AfterPoint = String.valueOf(Double.parseDouble(quantityNeeded) / rs.getDouble("QuantityOfOne"));
//                                    if (AfterPoint.substring(AfterPoint.indexOf(".") + 1, AfterPoint.length()).equals("0")) {
//                                        dbOperations.setDataOrDelete("update productsTypes set AllQuantity=" + (rs.getDouble("AllQuantity") + (Double.valueOf(quantityNeeded) / rs.getDouble("QuantityOfOne"))) + " where id=" + rs.getLong("id"), "");
//                                    } else {
                                        ResultSet rsw = dbOperations.getData("select * from products where productId=" + barCode);
                                        if (rsw.next()) {
                                            dbOperations.setDataOrDelete("update productsTypes set "
                                                    + "AllQuantity=" + String.format("%.3f",(rsw.getDouble("AvailableQty") / rs.getDouble("QuantityOfOne"))) + " "
                                                    + "where id=" + rs.getLong("id"), "");
                                        }

//                                    }
                                }
                            } else {
                                ResultSet rs1 = dbOperations.getData("select * from productsTypes where "
                                        + "productTypeName='" + productname + "'");
                                if (rs1.next()) {
                                    QuantityOfOne = rs1.getDouble("QuantityOfOne");
                                    productId = rs1.getLong("productId");
                                    AllQuantity = rs1.getDouble("AllQuantity");
                                }
                                ResultSet rs2 = dbOperations.getData("select * from products where productId=" + productId);
                                if (rs2.next()) {
                                    Taked = rs2.getDouble("Taked");
                                    AvailableQty = rs2.getDouble("AvailableQty");
                                }
                                double last=(AvailableQty + (Double.valueOf(quantityNeeded) * QuantityOfOne));
                                dbOperations.setDataOrDelete("update products set "
                                        + "AvailableQty=" +String.format("%.3f", last)+ ""
                                        + ",Taked=" + String.format("%.3f",(Taked - (Double.valueOf(quantityNeeded) * QuantityOfOne))) + ""
                                        + " where productId=" + productId, "");
                                ResultSet rs=dbOperations.getData("select * from productsTypes where productId="+productId);
                                while(rs.next())
                                {
                                     dbOperations.setDataOrDelete("update productsTypes set"
                                        + " AllQuantity=" +String.format("%.3f", (last/rs.getDouble("QuantityOfOne"))) + " where "
                                        + "id="+rs.getLong("id"), "");
                                }
                               
                            }
                        } else {
                            if (!productname.contains("_")) {
                                ResultSet rs4 = dbOperations.getData("select * from products where productName='" + productname + "'");
                                if (rs4.next()) {
                                    newQuantity = rs4.getDouble("AvailableQtyStore") + Double.valueOf(quantityNeeded);
                                }
                                productsController.updateAvailableWhenAddFromProductsTable(newQuantity,
                                        Double.valueOf(quantityNeeded),
                                        barCode, productPlace);
                                ResultSet rs = dbOperations.getData("select * from productsTypes where productId=" + barCode);
                                while (rs.next()) {
//                                    AfterPoint = String.valueOf(Double.parseDouble(quantityNeeded) / rs.getDouble("QuantityOfOne"));
//                                    if (AfterPoint.substring(AfterPoint.indexOf(".") + 1, AfterPoint.length()).equals("0")) {
//                                        dbOperations.setDataOrDelete("update productsTypes set AvailableQtyStore=" + (rs.getDouble("AvailableQtyStore") + (Double.valueOf(quantityNeeded) / rs.getDouble("QuantityOfOne"))) + " where id=" + rs.getLong("id"), "");
//                                    } else {
                                        ResultSet rsw = dbOperations.getData("select * from products where productId=" + barCode);
                                        if (rsw.next()) {
                                            dbOperations.setDataOrDelete("update productsTypes set "
                                                    + "AvailableQtyStore=" + String.format("%.3f", (rsw.getDouble("AvailableQtyStore") /rs.getDouble("QuantityOfOne"))) + " "
                                                    + "where id=" + rs.getLong("id"), "");
                                        }
//                                    }
                                }
                            } else {
                                ResultSet rs1 = dbOperations.getData("select * from productsTypes where "
                                        + "productTypeName='" + productname + "'");
                                if (rs1.next()) {
                                    QuantityOfOne = rs1.getDouble("QuantityOfOne");
                                    productId = rs1.getLong("productId");
                                    AllQuantity = rs1.getDouble("AvailableQtyStore");
                                }
                                ResultSet rs2 = dbOperations.getData("select * from products where productId=" + productId);
                                if (rs2.next()) {
                                    Taked = rs2.getDouble("Taked");
                                    AvailableQty = rs2.getDouble("AvailableQtyStore");
                                }
                               double last=(AvailableQty + (Double.valueOf(quantityNeeded) * QuantityOfOne));
                                dbOperations.setDataOrDelete("update products set "
                                        + "AvailableQtyStore=" +String.format("%.3f", last)+ ""
                                        + ",Taked=" +String.format("%.3f", (Taked - (Double.valueOf(quantityNeeded) * QuantityOfOne)) )+ ""
                                        + " where productId=" + productId, "");
                                ResultSet rs=dbOperations.getData("select * from productsTypes where productId="+productId);
                                while(rs.next())
                                {
                                     dbOperations.setDataOrDelete("update productsTypes set"
                                        + " AvailableQtyStore=" +String.format("%.3f", (last/rs.getDouble("QuantityOfOne"))) + " where "
                                        + "id="+rs.getLong("id"), "");
                                }
                            }
                        }
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, e);
                    }
                    dtm.removeRow(index);
                    if (jTable1.getRowCount() != 0) {
                        txtTOTAL.setText(getTotalForParameter() + "");
                        if (Double.valueOf(txtPayedAmount.getText()) > Double.valueOf(txtTOTAL.getText())) {
                            txtPayedAmount.setText(txtTOTAL.getText());
                            txtRemained.setText("0.0");
                        } else {
                            txtRemained.setText("" + String.format("%.3f", (Double.valueOf(txtTOTAL.getText()) - Double.valueOf(txtPayedAmount.getText()))));
                        }
                        getRealTotalAndDiscount();
                        setTotalQuantity();
                    } else {
                        txtTotalQuantity.setText("0");
                        txtTOTAL.setText("0");
                        txtRemained.setText("0");
                        txtPayedAmount.setText("0.0");
                        txtDiscountLable.setText("0.0");
                    }

                }
            } else {
                if (!txtINIDForBack.getText().equals("0")) {
                    int b = JOptionPane.showConfirmDialog(null, "<html><h1>هل تريد استرجاع " + dtm.getValueAt(index, 1).toString() + " ?</h1></html>", "", JOptionPane.YES_NO_OPTION);
                    if (b == 0) {
                        this.pageState = false;
                        txtINIDForBack.setEnabled(false);
                        btnReturn.setEnabled(false);
                        btnUpdate.setEnabled(true);
                        // update availableWhenDeleteOneRow , remove row from table , take the values of removed row and enable salePanel and 
                        // input the data and enable add button , then enable btn return part and update cart and sales where cartINID=cartINID label
                        txtSalePanel.setVisible(true);
                        if (productname.contains("_")) {
                            txtproductCode.setText(barCode);
                            txtproductCode.requestFocus(true);
//                        Thread.sleep(6000);
                            r.keyPress(KeyEvent.VK_CONTROL);
                            r.keyPress(KeyEvent.VK_A);
                            r.keyRelease(KeyEvent.VK_A);
                            r.keyRelease(KeyEvent.VK_CONTROL);
                            r.keyPress(KeyEvent.VK_RIGHT);
                            r.keyRelease(KeyEvent.VK_RIGHT);
                            txtTypeName.setSelectedItem(productname.substring(productname.indexOf("_") + 1, productname.length()));
//                        txtQuantityNeeded.requestFocus(true);
                            if (SaleType.equals("تجاري")) {
                                txtCheckboxPriceOfMarket.setSelected(true);
                            } else {
                                txtCheckboxPriceOfMarket.setSelected(false);
                            }
                            if (productPlace.equals("محل")) {
                                txtMarketOrStore.setSelectedItem("محل");
                            } else {
                                txtMarketOrStore.setSelectedItem(productPlace);
                            }
                        } else {

                            txtproductCode.requestFocus(true);
                            txtproductCode.setText(barCode);
                            r.keyPress(KeyEvent.VK_CONTROL);
                            r.keyPress(KeyEvent.VK_A);
                            r.keyRelease(KeyEvent.VK_A);
                            r.keyRelease(KeyEvent.VK_CONTROL);
                            r.keyPress(KeyEvent.VK_RIGHT);
                            r.keyRelease(KeyEvent.VK_RIGHT);

//                        txtQuantityNeeded.requestFocus(true);
                            if (SaleType.equals("تجاري")) {
                                txtCheckboxPriceOfMarket.setSelected(true);
                            } else {
                                txtCheckboxPriceOfMarket.setSelected(false);
                            }
                            if (productPlace.equals("محل")) {
                                txtMarketOrStore.setSelectedItem("محل");
                            } else {
                                txtMarketOrStore.setSelectedItem(productPlace);
                            }

                        }

                        try {
                            if (productPlace.equals("محل")) {
                                if (!productname.contains("_")) {
                                    ResultSet rs4 = dbOperations.getData("select * from products where productName='" + productname + "'");
                                    if (rs4.next()) {
                                        newQuantity = rs4.getDouble("AvailableQty") + Double.valueOf(quantityNeeded);
                                    }
                                    productsController.updateAvailableWhenReturnFromProductsTable(newQuantity,
                                            Double.valueOf(quantityNeeded),
                                            barCode, productPlace);
                                    ResultSet rs = dbOperations.getData("select * from productsTypes where productId=" + barCode);
                                    while (rs.next()) {
                                        AfterPoint = String.valueOf(Double.parseDouble(quantityNeeded) / rs.getDouble("QuantityOfOne"));
                                        if (AfterPoint.substring(AfterPoint.indexOf(".") + 1, AfterPoint.length()).equals("0")) {
                                            dbOperations.setDataOrDelete("update productsTypes set AllQuantity=" + (rs.getDouble("AllQuantity") - (Double.valueOf(quantityNeeded) / rs.getDouble("QuantityOfOne"))) + " where id=" + rs.getLong("id"), "");
                                        } else {
                                            ResultSet rsw = dbOperations.getData("select * from products where productId=" + barCode);
                                            if (rsw.next()) {
                                                dbOperations.setDataOrDelete("update productsTypes set "
                                                        + "AllQuantity=" + String.format("%.3f", (rsw.getDouble("AvailableQty") / rs.getDouble("QuantityOfOne"))) + " "
                                                        + "where id=" + rs.getLong("id"), "");
                                            }

                                        }
                                    }
                                } else {
                                    ResultSet rs1 = dbOperations.getData("select * from productsTypes where "
                                            + "productTypeName='" + productname + "'");
                                    if (rs1.next()) {
                                        QuantityOfOne = rs1.getDouble("QuantityOfOne");
                                        productId = rs1.getLong("productId");
                                        AllQuantity = rs1.getDouble("AllQuantity");
                                    }
                                    ResultSet rs2 = dbOperations.getData("select * from products where productId=" + productId);
                                    if (rs2.next()) {
                                        Taked = rs2.getDouble("Taked");
                                        AvailableQty = rs2.getDouble("AvailableQty");
                                    }
                                    dbOperations.setDataOrDelete("update productsTypes set"
                                            + " AllQuantity=" + (AllQuantity + Double.valueOf(quantityNeeded)) + " where "
                                            + "productTypeName='" + productname + "'", "");
                                    dbOperations.setDataOrDelete("update products set "
                                            + "AvailableQty=" + (AvailableQty + (Double.valueOf(quantityNeeded) * QuantityOfOne)) + ""
                                            + ",Taked=" + (Taked - (Double.valueOf(quantityNeeded) * QuantityOfOne)) + ""
                                            + " where productId=" + productId, "");
                                }
                            } else {
                                if (!productname.contains("_")) {
                                    ResultSet rs4 = dbOperations.getData("select * from products where productName='" + productname + "'");
                                    if (rs4.next()) {
                                        newQuantity = rs4.getDouble("AvailableQtyStore") + Double.valueOf(quantityNeeded);
                                    }
                                    productsController.updateAvailableWhenAddFromProductsTable(newQuantity,
                                            Double.valueOf(quantityNeeded),
                                            barCode, productPlace);
                                    ResultSet rs = dbOperations.getData("select * from productsTypes where productId=" + barCode);
                                    while (rs.next()) {
                                        AfterPoint = String.valueOf(Double.parseDouble(quantityNeeded) / rs.getDouble("QuantityOfOne"));
                                        if (AfterPoint.substring(AfterPoint.indexOf(".") + 1, AfterPoint.length()).equals("0")) {
                                            dbOperations.setDataOrDelete("update productsTypes set AvailableQtyStore=" + (rs.getDouble("AvailableQtyStore") + (Double.valueOf(quantityNeeded) / rs.getDouble("QuantityOfOne"))) + " where id=" + rs.getLong("id"), "");
                                        } else {
                                            ResultSet rsw = dbOperations.getData("select * from products where productId=" + barCode);
                                            if (rsw.next()) {
                                                dbOperations.setDataOrDelete("update productsTypes set "
                                                        + "AvailableQtyStore=" + (rsw.getDouble("AvailableQtyStore") / rs.getDouble("QuantityOfOne")) + " "
                                                        + "where id=" + rs.getLong("id"), "");
                                            }
                                        }
                                    }
                                } else {
                                    ResultSet rs1 = dbOperations.getData("select * from productsTypes where "
                                            + "productTypeName='" + productname + "'");
                                    if (rs1.next()) {
                                        QuantityOfOne = rs1.getDouble("QuantityOfOne");
                                        productId = rs1.getLong("productId");
                                        AllQuantity = rs1.getDouble("AvailableQtyStore");
                                    }
                                    ResultSet rs2 = dbOperations.getData("select * from products where productId=" + productId);
                                    if (rs2.next()) {
                                        Taked = rs2.getDouble("Taked");
                                        AvailableQty = rs2.getDouble("AvailableQtyStore");
                                    }
                                    dbOperations.setDataOrDelete("update productsTypes set "
                                            + "AvailableQtyStore=" + (AllQuantity + Double.valueOf(quantityNeeded)) + " where "
                                            + "productTypeName='" + productname + "'", "");
                                    dbOperations.setDataOrDelete("update products set "
                                            + "AvailableQtyStore=" + (AvailableQty + (Double.valueOf(quantityNeeded) * QuantityOfOne)) + ""
                                            + ",Taked=" + (Taked - (Double.valueOf(quantityNeeded) * QuantityOfOne)) + ""
                                            + " where productId=" + productId, "");
                                }
                            }
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, e);
                        }

//                    for (int i = 0; i < jTable1.getRowCount(); i++) {
//                        productsController.updateAvailableWhenDeleteoneRow(Double.parseDouble(jTable1.getValueAt(i, 4).toString()), jTable1.getValueAt(i, 1).toString(), jTable1.getValueAt(i, 2).toString());
//                    }
                        dtm.removeRow(index);
                        dbOperations.setDataOrDelete("Insert into cartReturn("
                                + "INID,"
                                + "productname,"
                                + "BarCode,"
                                + "quantityneeded,"
                                + "Unit_Price,"
                                + "Total_Price"
                                + ",empname,"
                                + "date,"
                                + "productPlace,"
                                + "SaleType) values("
                                + INid + ",'"
                                + productname
                                + "','" + barCode
                                + "'," + quantityNeeded
                                + "," + productPrice
                                + "," + TotalPrice
                                + ",'" + txtEmpName.getText()
                                + "','" + TodayDate.getText()
                                + "','" + productPlace
                                + "','" + SaleType + "')", "");
                    }
                    try {
                        String id = txtInVIDReturn.getText();

                        dbOperations.setDataOrDelete("Update extra set val='" + id + "' where exid=3", "");
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, e);
                    }
                    ResultSet rs = dbOperations.getData("select * from salesReturn where INID=" + INid);
                    if (rs.next()) {
                        if (Double.valueOf(txtPayedAmount.getText()) > (rs.getDouble("payed") + TotalPrice)) {
                            dbOperations.setDataOrDelete("update salesReturn set"
                                    + " Total_Quantity=" + (rs.getDouble("Total_Quantity") + 1)
                                    + ",payed=" + (rs.getDouble("payed") + TotalPrice)
                                    + ",AllTotal=" + (rs.getDouble("AllTotal") + TotalPrice)
                                    + ",Total=" + (rs.getDouble("Total") + TotalPrice) + ",SaleType='" + SaleType + "' where INID=" + INid,
                                    "");
                        } else {
                            dbOperations.setDataOrDelete("update salesReturn set"
                                    + " Total_Quantity=" + (rs.getDouble("Total_Quantity") + 1)
                                    + ",payed=" + txtPayedAmount.getText()
                                    + ",AllTotal=" + (rs.getDouble("AllTotal") + TotalPrice)
                                    + ",Total=" + (rs.getDouble("Total") + TotalPrice) + ",SaleType='" + SaleType + "' where INID=" + INid,
                                    "");
                        }

                    } else {
                    String customerName;
                    if (txtCustomerName.getSelectedIndex() != 0) {
                        customerName = txtCustomerName.getSelectedItem().toString();
                    } else {
                        customerName = "زبون";
                    }
                        if (Double.valueOf(txtPayedAmount.getText()) >= TotalPrice) {
                            dbOperations.setDataOrDelete("insert into salesReturn ("
                                    + "INID,"
                                    + "customername,"
                                    + "Total_Quantity,"
                                    + "payed,"
                                    + "Remained,"
                                    + "Date,"
                                    + "empname,"
                                    + "Discount,"
                                    + "AllTotal,"
                                    + "DayDate,Total,SaleType,storePhone) values("
                                    + INid
                                    + ",'" +customerName
                                    + "'," + 1
                                    + "," + TotalPrice
                                    + "," + 0.0
                                    + ",'" + TodayDate.getText()
                                    + "','" + txtEmpName.getText()
                                    + "'," + 0.0
                                    + "," + TotalPrice
                                    + ",'" + todayDate + "'," + TotalPrice
                                    + ",'" + SaleType + "','"+txtStorePhone.getText()+"')", "");
                        } else {
                            dbOperations.setDataOrDelete("insert into salesReturn ("
                                    + "INID,"
                                    + "customername,"
                                    + "Total_Quantity,"
                                    + "payed,"
                                    + "Remained,"
                                    + "Date,"
                                    + "empname,"
                                    + "Discount,"
                                    + "AllTotal,"
                                    + "DayDate,Total,SaleType,storePhone) values("
                                    + INid
                                    + ",'" +customerName
                                    + "'," + 1
                                    + "," + txtPayedAmount.getText()
                                    + "," + 0.0
                                    + ",'" + TodayDate.getText()
                                    + "','" + txtEmpName.getText()
                                    + "'," + 0.0
                                    + "," + TotalPrice
                                    + ",'" + todayDate + "'," + TotalPrice
                                    + ",'" + SaleType + "','"+txtStorePhone.getText()+"')", "");
                        }
                    }

                    ResultSet rsPaymentValue = dbOperations.getData("select * from salesReturn where INID=" + INid);
                    if (rsPaymentValue.next()) {
                        if (Double.valueOf(txtRemained.getText()) > rsPaymentValue.getDouble("payed")) {
                            txtRemained.setText("" + String.format("%.3f", (Double.valueOf(txtRemained.getText()) - rsPaymentValue.getDouble("payed"))));
                        }
                        txtTOTAL.setText(getTotalForParameter() + "");
                        if (Double.valueOf(txtPayedAmount.getText()) > Double.valueOf(txtTOTAL.getText())) {
                            txtPayedAmount.setText(txtTOTAL.getText());
                            txtRemained.setText("0.0");
                        } else {
                            txtRemained.setText("" + String.format("%.3f", (Double.valueOf(txtTOTAL.getText()) - Double.valueOf(txtPayedAmount.getText()))));
                        }
                    }
                    getRealTotalAndDiscount();
                    setTotalQuantity();
                }
            }
            if(jTable1.getRowCount()==0)
            {
               pageState=true;
               txtCustomerName.setEnabled(true);
               txtCustomerNumber.setEnabled(true);
            }else 
            {
                pageState=false;
            }
            
            txtproductCode.setText("");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void txtPayedAmountKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPayedAmountKeyReleased
        double Total = 0;
        try {
            double Payed = Double.parseDouble(txtPayedAmount.getText());
            Total = Double.parseDouble(txtTOTAL.getText());
            if (Payed >=0) {
                if(Payed<=Total && !txtINIDForBack.equals("0"))
                {
                    btnUpdate.setEnabled(true);
                }else 
                {
                    btnUpdate.setEnabled(false);
                }
                for (int i = 0; i < txtPayedAmount.getText().length(); i++) {
                    if (Character.isDigit(txtPayedAmount.getText().charAt(i))) {
                        if (Payed <= Total && txtINIDForBack.getText().equals("0")) {
                            txtPayedAmount.setForeground(new Color(0, 255, 0));
                            txtRemained.setText(String.valueOf(Total - Payed));
                            btnPrint.setEnabled(true);
                            btnUpdate.setEnabled(false);
                            btnReturn.setEnabled(false);
                        } else if (Payed <= Total && !txtINIDForBack.getText().equals("0")) {
                            txtPayedAmount.setForeground(new Color(0, 255, 0));
                            txtRemained.setText(String.valueOf(Total - Payed));
                            btnPrint.setEnabled(false);
                            btnUpdate.setEnabled(true);
                            btnReturn.setEnabled(true);
                        } else {
                            txtPayedAmount.setForeground(new Color(255, 0, 0));
                            txtRemained.setText(String.valueOf(Total));
                            btnPrint.setEnabled(false);
                        }
                    } else {
                        txtPayedAmount.setForeground(new Color(255, 0, 0));
                        txtRemained.setText(String.valueOf(Total));
                        btnPrint.setEnabled(false);
                    }
                }
            } else {
                txtPayedAmount.setForeground(new Color(255, 0, 0));
                txtRemained.setText(String.valueOf(Total));
                btnPrint.setEnabled(false);
            }
        } catch (NumberFormatException es) {
            txtPayedAmount.setForeground(new Color(255, 0, 0));
            txtRemained.setText(String.valueOf(Total));
            btnPrint.setEnabled(false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }//GEN-LAST:event_txtPayedAmountKeyReleased

    private void txtCustomerNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCustomerNameActionPerformed
        try {

            ArrayList<customers> list = customerController.getAllCustomersByCustomerName(txtCustomerName.getSelectedItem().toString());
            Iterator<customers> itr = list.iterator();
            while (itr.hasNext()) {
                customers cus = itr.next();
                txtCustomerName.setSelectedItem(cus.getCustomerName());
                if (cus.getCustomerType().equals("تجاري")) {
                    txtCheckboxPriceOfMarket.setSelected(true);
                } else {
                    txtCheckboxPriceOfMarket.setSelected(false);
                }

                txtCustomerNumber.setText(cus.getPhone());
                int forEx = Integer.parseInt(txtCustomerNumber.getText());
                try {
                    if (txtTypeName.getSelectedIndex() == 0 && txtproductCode.getText().length() != 0) {
                        ResultSet rs = dbOperations.getData("select * from products where productId=" + txtproductCode.getText());
                        if (rs.next()) {
                            if (txtMarketOrStore.getSelectedItem().equals("محل")) {

                                productName = rs.getString("productName");
                                if (txtCheckboxPriceOfMarket.isSelected()) {
                                    txtProductPrice.setText(String.valueOf(rs.getDouble("priceOfMarket")));
                                } else {
                                    txtProductPrice.setText(String.valueOf(rs.getDouble("priceOfSale")));
                                }
                                txtAvailable.setText(String.valueOf(rs.getDouble("AvailableQty")));
                                QuantityAvailGlobalProducts = rs.getDouble("AvailableQty");

                            } else {
                                productName = rs.getString("productName");
                                if (txtCheckboxPriceOfMarket.isSelected()) {
                                    txtProductPrice.setText(String.valueOf(rs.getDouble("priceOfMarket")));
                                } else {
                                    txtProductPrice.setText(String.valueOf(rs.getDouble("priceOfSale")));
                                }
                                txtAvailable.setText(String.valueOf(rs.getDouble("AvailableQtyStore")));
                                QuantityAvailGlobalProducts = rs.getDouble("AvailableQtyStore");
                            }
                        }
                    } else if (txtTypeName.getSelectedIndex() != 0 && txtproductCode.getText().length() != 0) {
                        ArrayList<productsTypes> list1 = productsController.getAllProductsTypesRecordsForSellWithProductName(txtProductNameLabel.getText() + "_" + txtTypeName.getSelectedItem().toString());
                        Iterator<productsTypes> itr1 = list1.iterator();
                        while (itr1.hasNext()) {
                            productsTypes prod = itr1.next();
                            if (txtCheckboxPriceOfMarket.isSelected()) {
                                txtProductPrice.setText(String.valueOf(prod.getPriceOfMarket()));
                            } else {
                                txtProductPrice.setText(String.valueOf(prod.getPriceOfSale()));
                            }
                            if (txtMarketOrStore.getSelectedItem().equals("عادي")) {
                                txtAvailable.setText(String.valueOf(productsController.getAllQuantityProductsTypes(txtProductNameLabel.getText() + "_" + txtTypeName.getSelectedItem().toString())));
                                QuantityAvailGlobalTables = productsController.getAllQuantityProductsTypes(txtProductNameLabel.getText() + "_" + txtTypeName.getSelectedItem().toString());
                            } else {
                                txtAvailable.setText(String.valueOf(productsController.getAvailableQtyStoreProductsTypes(txtProductNameLabel.getText() + "_" + txtTypeName.getSelectedItem().toString())));
                                QuantityAvailGlobalTables = productsController.getAvailableQtyStoreProductsTypes(txtProductNameLabel.getText() + "_" + txtTypeName.getSelectedItem().toString());
                            }
                        }
                        if (!txtAvailable.getText().equals("0") && !txtProductPrice.getText().equals("0")) {
                            txtQuantityNeeded.setEnabled(true);
                            txtQuantityNeeded.requestFocus(true);
                        } else {
                            txtQuantityNeeded.setEnabled(false);
                        }
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }

            }
        } catch (NumberFormatException e) {
            txtCheckboxPriceOfMarket.setSelected(false);
            txtCustomerName.setSelectedIndex(0);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_txtCustomerNameActionPerformed

    private void txtQuantityNeededActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQuantityNeededActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQuantityNeededActionPerformed

    private void txtproductCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtproductCodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtproductCodeActionPerformed

    private void txtproductCodeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtproductCodeKeyReleased
        try {
             ResultSet rs=dbOperations.getData("select * from cart where INID="+txtINIDForBack.getText());
             if(rs.next())
             {
                
             }else 
             { 
                 txtINIDForBack.setText("0");
             }
        } catch (Exception e) {
        }
       
            long productID = 0;
            try {
                productID = Long.parseLong(txtproductCode.getText());
                if (productID > 0) {
                    txtproductCode.setForeground(new Color(0, 255, 0));
                    txtproductCode.setBackground(new Color(105, 105, 105));
                    getAllProductRecords(productID + "");
                    getproductsTypesForCompo(productID + "");
                    if (!txtProductPrice.getText().equals("0")) {
                        txtQuantityNeeded.setEnabled(true);
                    }
                    txtProductNameCompo.setSelectedItem(txtProductNameLabel.getText());
                } else {
                    btnAdd.setEnabled(false);
                    txtProductPrice.setText("0");
                    txtAvailable.setText("0");
                    txtPriceOfBuy.setText("0");
                    txtQuantityNeeded.setEnabled(false);
                    txtTypeName.setSelectedItem(" ");
                }
                if (txtAvailable.getText().equals("0")) {
                    btnAdd.setEnabled(false);
                    txtProductPrice.setText("0");
                    txtAvailable.setText("0");
                    txtPriceOfBuy.setText("0");
                    txtQuantityNeeded.setEnabled(false);
                    txtTypeName.setSelectedItem(" ");
                }
            } catch (NumberFormatException ex) {
                btnAdd.setEnabled(false);
                txtProductPrice.setText("0");
                txtQuantityNeeded.setEnabled(false);
                txtTypeName.setSelectedItem(" ");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        
        
    }//GEN-LAST:event_txtproductCodeKeyReleased

    private void txtTypeNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTypeNameActionPerformed
        try {
            ArrayList<productsTypes> list = productsController.getAllProductsTypesRecordsForSellWithProductName(txtProductNameLabel.getText() + "_" + txtTypeName.getSelectedItem().toString());
            Iterator<productsTypes> itr = list.iterator();
            while (itr.hasNext()) {
                productsTypes prod = itr.next();
                if (txtCheckboxPriceOfMarket.isSelected()) {
                    txtProductPrice.setText(String.valueOf(prod.getPriceOfMarket()));
                    globalProductPrice = prod.getPriceOfMarket();
                } else {
                    txtProductPrice.setText(String.valueOf(prod.getPriceOfSale()));
                    globalProductPrice = prod.getPriceOfSale();
                }
                txtPriceOfBuy.setText(prod.getPriceOfBuy() + "");
                if (txtMarketOrStore.getSelectedItem().toString().equals("محل")) {
                    txtAvailable.setText(String.valueOf(productsController.getAllQuantityProductsTypes(txtProductNameLabel.getText() + "_" + txtTypeName.getSelectedItem().toString())));
                    QuantityAvailGlobalTables = productsController.getAllQuantityProductsTypes(txtProductNameLabel.getText() + "_" + txtTypeName.getSelectedItem().toString());
                } else {
                    txtAvailable.setText(String.valueOf(productsController.getAvailableQtyStoreProductsTypes(txtProductNameLabel.getText() + "_" + txtTypeName.getSelectedItem().toString())));
                    QuantityAvailGlobalTables = productsController.getAvailableQtyStoreProductsTypes(txtProductNameLabel.getText() + "_" + txtTypeName.getSelectedItem().toString());
                }
            }
            ResultSet rs = dbOperations.getData("select * from productsTypes where productTypeName='" + txtProductNameLabel.getText() + "_" + txtTypeName.getSelectedItem().toString() + "'");
            if (rs.next()) {
                if ((rs.getDouble("AllQuantity") + rs.getDouble("AvailableQtyStore")) <= rs.getDouble("QuantityWarning")) {
                    txtAvailable.setBackground(Color.RED);
                    txtAvailable.setOpaque(true);
                } else {
                    txtAvailable.setBackground(Color.BLUE);
                    txtAvailable.setOpaque(true);
                }
            }
            if (!txtAvailable.getText().equals("0.0") && !txtProductPrice.getText().equals("0.0")) {
                txtQuantityNeeded.setEnabled(true);
                txtQuantityNeeded.setText("");
            } else {
                txtQuantityNeeded.setEnabled(false);
                txtQuantityNeeded.setText("");
            }

        } catch (Exception e) {

        }
    }//GEN-LAST:event_txtTypeNameActionPerformed

    private void btnReturnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReturnActionPerformed
      if(Double.valueOf(txtPayedAmount.getText())<=Double.valueOf(txtTOTAL.getText()))
      {
            double customerRemained = 0;
        double payedValueForReturn = 0;
        String AfterPoint;
        double AvailableQty = 0;
        double Taked = 0;
        double QuantityOfOne = 0;
        double AllQuantity = 0;
        long productId = 0;
        int a = JOptionPane.showConfirmDialog(null, "<html><h1>سوف يتم استرجاع كل الفاتورة وحذفها من المبيعات وخصم قيمتها من الخزنة</h1></html>", "اختر", JOptionPane.YES_NO_OPTION);
        if (a == 0) {
            String INID = txtInVIDReturn.getText();
            String SaleTypeForSale = "عادي";
            try {
                String productname;
                String barCode;
                String quantityNeeded;
                String productPlace;
                double productPrice;
                String TotalPrice;
                String SaleType;

                int len = jTable1.getRowCount();
                DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();

                //Minus The Remained Value Of This Sale From The Remaied Value Of Customer Totalremained
                ResultSet rsdb = dbOperations.getData("select * from customers where customerName='" + txtCustomerName.getSelectedItem().toString() + "'");
                if (rsdb.next()) {
                    customerRemained = rsdb.getDouble("AllRemained");
                }
                if (customerRemained >= Double.valueOf(txtRemained.getText())) {
                    customerRemained -= Double.valueOf(txtRemained.getText());
                    int d = JOptionPane.showConfirmDialog(null, "<html><h1>خصم القيمة المدفوعة من الخزنة كاش؟</h1></html>", "اختر", JOptionPane.YES_NO_OPTION);
                    if (d == 0) {
                        ResultSet rs = dbOperations.getData("select * from capenat where id=(select max(id) from capenat)");
                        if (rs.next()) {
                            dbOperations.setDataOrDelete("insert into capenat (realValue,editedvalue,afterEdictvalue,OperationType,date) "
                                    + "values(" + rs.getDouble("afterEdictvalue") + "," + txtPayedAmount.getText() + "," + (rs.getDouble("afterEdictvalue") - Double.valueOf(txtPayedAmount.getText()))
                                    + ",'" + "استرجاع فاتورة المدفوع فيها : " + txtPayedAmount.getText() + " من العميل: " + txtCustomerName.getSelectedItem().toString() + " وقام بذلك: " + txtEmpName.getText() + "','" + TodayDate.getText() + "')", "تم الاسترجاع");
                            payedValueForReturn = Double.valueOf(txtPayedAmount.getText());
                        }
                    } else {
                        if (customerRemained >= Double.valueOf(txtPayedAmount.getText())) {
                            customerRemained -= Double.valueOf(txtPayedAmount.getText());
                            payedValueForReturn = Double.valueOf(txtPayedAmount.getText());
                        } else {
                            customerRemained = 0;
                        }
                    }
                } else {
                    payedValueForReturn = (Double.valueOf(txtRemained.getText()) + Double.valueOf(txtPayedAmount.getText())) - customerRemained;
                    dbOperations.setDataOrDelete("insert into payment (customerName,casherName,remainedbefor,payed,remainedAfter,date,receiver,Note) "
                            + "values('"
                            + txtCustomerName.getSelectedItem().toString()
                            + "','" + txtEmpName.getText()
                            + "'," + customerRemained
                            + "," + (-(Double.valueOf(txtRemained.getText()) - customerRemained))
                            + "," + 0
                            + ",'" + TodayDate.getText()
                            + "','" + txtEmpName.getText()
                            + "','" + "استرجاع فاتورة" + "')", "");

                    ResultSet rs = dbOperations.getData("select * from capenat where id=(select max(id) from capenat)");
                    if (rs.next()) {
                        dbOperations.setDataOrDelete("insert into capenat (realValue,editedvalue,afterEdictvalue,OperationType,date) "
                                + "values(" + rs.getDouble("afterEdictvalue") + "," + payedValueForReturn + "," + (rs.getDouble("afterEdictvalue") - payedValueForReturn)
                                + ",'" + "استرجاع فاتورة المدفوع فيها : " + payedValueForReturn + " من العميل: " + txtCustomerName.getSelectedItem().toString() + " وقام بذلك: " + txtEmpName.getText() + "','" + TodayDate.getText() + "')", "تم الاسترجاع");
                    }
                    customerRemained = 0;
                }
                dbOperations.setDataOrDelete("update customers set "
                        + "AllRemained=" + customerRemained + " where customerName='" + txtCustomerName.getSelectedItem().toString() + "'", "");

                // The end of Return Mony
                dbOperations.setDataOrDelete("delete from cart where INID=" + txtINIDForBack.getText(), "");
                dbOperations.setDataOrDelete("delete from sales where INID=" + txtINIDForBack.getText(), "");
                for (int i = 0; i < len; i++) {
                    productname = dtm.getValueAt(i, 1).toString();
                    barCode = dtm.getValueAt(i, 3).toString();
                    quantityNeeded = dtm.getValueAt(i, 4).toString();
                    productPrice = Double.parseDouble(dtm.getValueAt(i, 6).toString());
                    SaleType = dtm.getValueAt(i, 5).toString();
                    TotalPrice = dtm.getValueAt(i, 7).toString();
                    productPlace = dtm.getValueAt(i, 2).toString();
                    dbOperations.setDataOrDelete("Insert into cartReturn("
                            + "INID,"
                            + "productname,"
                            + "BarCode,"
                            + "quantityneeded,"
                            + "Unit_Price,"
                            + "Total_Price"
                            + ",empname,"
                            + "date,"
                            + "productPlace,"
                            + "SaleType) values("
                            + INID + ",'"
                            + productname
                            + "','" + barCode
                            + "'," + quantityNeeded
                            + "," + productPrice
                            + "," + TotalPrice
                            + ",'" + txtEmpName.getText()
                            + "','" + TodayDate.getText()
                            + "','" + productPlace
                            + "','" + SaleType + "')", "");
                    if (productPlace.equals("محل")) {
                        if (!productname.contains("_")) {
                            ResultSet rs13 = dbOperations.getData("select * from products where productId=" + barCode);
                            if (rs13.next()) {
                                productsController.updateAvailableWhenReturnFromProductsTable((rs13.getDouble("AvailableQty") + Double.valueOf(quantityNeeded)),
                                        Double.valueOf(quantityNeeded),
                                        barCode, productPlace);
                            }

                            ResultSet rs11 = dbOperations.getData("select * from productsTypes where productId=" + barCode);
                            while (rs11.next()) {
//                                AfterPoint = String.valueOf(Double.parseDouble(quantityNeeded) / rs11.getDouble("QuantityOfOne"));
//                                if (AfterPoint.substring(AfterPoint.indexOf(".") + 1, AfterPoint.length()).equals("0")) {
//                                    dbOperations.setDataOrDelete("update productsTypes set "
//                                            + "AllQuantity=" + (rs11.getDouble("AllQuantity") + (Double.valueOf(quantityNeeded) / rs11.getDouble("QuantityOfOne"))) + " where id=" + rs11.getLong("id"), "");
//                                } else {
                                    ResultSet rsw = dbOperations.getData("select * from products where productId=" +barCode);
                                    if (rsw.next()) {
                                        dbOperations.setDataOrDelete("update productsTypes set "
                                                + "AllQuantity=" +String.format("%.3f",  (rsw.getDouble("AvailableQty") /rs11.getDouble("QuantityOfOne"))) + " "
                                                + "where id=" + rs11.getLong("id"), "");
                                    }
//                                }
                            }

                        } else {
                            ResultSet rs111 = dbOperations.getData("select * from productsTypes where "
                                    + "productTypeName='" + productname + "'");
                            if (rs111.next()) {
                                QuantityOfOne = rs111.getDouble("QuantityOfOne");
                                productId = rs111.getLong("productId");
                                AllQuantity = rs111.getDouble("AllQuantity");
                            }
                            ResultSet rs22 = dbOperations.getData("select * from products where productId=" + productId);
                            if (rs22.next()) {
                                Taked = rs22.getDouble("Taked");
                                AvailableQty = rs22.getDouble("AvailableQty");
                            }
                            double lastAvail=(AvailableQty + (Double.valueOf(quantityNeeded) * QuantityOfOne));
//                            dbOperations.setDataOrDelete("update productsTypes set"
//                                    + " AllQuantity=" + (AllQuantity + Double.valueOf(quantityNeeded)) + " where "
//                                    + "productTypeName='" + productname + "'", "");
                            dbOperations.setDataOrDelete("update products set "
                                    + "AvailableQty=" + String.format("%.3f", lastAvail)
                                    + ",Taked=" + String.format("%.3f", (Taked - (Double.valueOf(quantityNeeded) * QuantityOfOne)))
                                    + " where productId=" + productId, "");
                            ResultSet rs=dbOperations.getData("select * from productsTypes where productId="+productId);
                            while(rs.next())
                            {
                                   dbOperations.setDataOrDelete("update productsTypes set"
                                    + " AllQuantity=" +String.format("%.3f", (lastAvail/rs.getDouble("quantityOfOne"))) + " where "
                                    + "id=" +rs.getLong("id"), "");
                            }
                        }
                    } else {
                                                if (!productname.contains("_")) {
                            ResultSet rs13 = dbOperations.getData("select * from products where productId=" + barCode);
                            if (rs13.next()) {
                                productsController.updateAvailableWhenReturnFromProductsTable((rs13.getDouble("AvailableQtyStore") + Double.valueOf(quantityNeeded)),
                                        Double.valueOf(quantityNeeded),
                                        barCode, productPlace);
                            }

                            ResultSet rs11 = dbOperations.getData("select * from productsTypes where productId=" + barCode);
                            while (rs11.next()) {
//                                AfterPoint = String.valueOf(Double.parseDouble(quantityNeeded) / rs11.getDouble("QuantityOfOne"));
//                                if (AfterPoint.substring(AfterPoint.indexOf(".") + 1, AfterPoint.length()).equals("0")) {
//                                    dbOperations.setDataOrDelete("update productsTypes set "
//                                            + "AllQuantity=" + (rs11.getDouble("AllQuantity") + (Double.valueOf(quantityNeeded) / rs11.getDouble("QuantityOfOne"))) + " where id=" + rs11.getLong("id"), "");
//                                } else {
                                    ResultSet rsw = dbOperations.getData("select * from products where productId=" + barCode);
                                    if (rsw.next()) {
                                        dbOperations.setDataOrDelete("update productsTypes set "
                                                + "AvailableQtyStore=" + String.format("%.3f", (rsw.getDouble("AvailableQtyStore") /rs11.getDouble("QuantityOfOne"))) + " "
                                                + "where id=" + rs11.getLong("id"), "");
                                    }
//                                }
                            }

                        } else {
                                                   ResultSet rs111 = dbOperations.getData("select * from productsTypes where "
                                    + "productTypeName='" + productname + "'");
                            if (rs111.next()) {
                                QuantityOfOne = rs111.getDouble("QuantityOfOne");
                                productId = rs111.getLong("productId");
                                AllQuantity = rs111.getDouble("AvailableQtyStore");
                            }
                            ResultSet rs22 = dbOperations.getData("select * from products where productId=" + productId);
                            if (rs22.next()) {
                                Taked = rs22.getDouble("Taked");
                                AvailableQty = rs22.getDouble("AvailableQtyStore");
                            }
                            double lastAvail=(AvailableQty + (Double.valueOf(quantityNeeded) * QuantityOfOne));
//                            dbOperations.setDataOrDelete("update productsTypes set"
//                                    + " AllQuantity=" + (AllQuantity + Double.valueOf(quantityNeeded)) + " where "
//                                    + "productTypeName='" + productname + "'", "");
                            dbOperations.setDataOrDelete("update products set "
                                    + "AvailableQtyStore=" + String.format("%.3f", lastAvail)
                                    + ",Taked=" + String.format("%.3f", (Taked - (Double.valueOf(quantityNeeded) * QuantityOfOne)))
                                    + " where productId=" + productId, "");
                            ResultSet rs=dbOperations.getData("select * from productsTypes where productId="+productId);
                            while(rs.next())
                            {
                                   dbOperations.setDataOrDelete("update productsTypes set"
                                    + " AvailableQtyStore=" +String.format("%.3f", (lastAvail/rs.getDouble("quantityOfOne"))) + " where "
                                    + "id=" +rs.getLong("id"), "");
                            }
                        }
                    }

//                    if (productname.contains("_")) {
//                        ResultSet rs11 = dbOperations.getData("select * from productsTypes where productTypeName='" + productname + "'");
//                        if (rs11.next()) {
//                            if (productPlace.equals("محل")) {
//                                dbOperations.setDataOrDelete("update productsTypes set "
//                                        + "AllQuantity=" + (rs11.getDouble("AllQuantity") + Double.valueOf(quantityNeeded)) + " where productTypeName='" + productname + "'", "");
//                                ResultSet rs12 = dbOperations.getData("select * from products where productId=" + rs11.getLong("productId"));
//                                if(rs12.next())
//                                {
//                                    dbOperations.setDataOrDelete("update products set "
//                                        + "ِAvailableQty=" + (rs11.getDouble("QuantityOfOne") * (Double.valueOf(quantityNeeded) + rs11.getDouble("AllQuantity"))) + ",Taked=" + (rs12.getDouble("Taked") - (Double.valueOf(quantityNeeded) * rs11.getDouble("QuantityOfOne"))) + " where productId=" + rs11.getLong("productId"), "");
//                                }
//                            } else {
//                                dbOperations.setDataOrDelete("update productsTypes set "
//                                        + "ِAvailableQtyStore=" + (rs11.getDouble("AllQuantity") + Double.valueOf(quantityNeeded)) + " where productTypeName='" + productname + "'", "");
//                                ResultSet rs12 = dbOperations.getData("select * from products where productId=" + rs11.getLong("productId"));
//                                dbOperations.setDataOrDelete("update products set "
//                                        + "ِAvailableQtyStore=" + (rs11.getDouble("QuantityOfOne") * (Double.valueOf(quantityNeeded) + rs11.getDouble("AvailableQtyStore"))) + ",Taked=" + (rs12.getDouble("Taked") - (Double.valueOf(quantityNeeded) * rs11.getDouble("QuantityOfOne"))) + " where productId=" + rs11.getLong("productId"), "");
//                            }
//
//                        }
//                    } else {
//                        ResultSet rs = dbOperations.getData("select * from products where productId=" + barCode);
//                        while (rs.next()) {
//                            if (productPlace.equals("محل")) {
//                                JOptionPane.showMessageDialog(null,rs.getDouble("AvailableQty")+"");
//                                dbOperations.setDataOrDelete("update products set AvailableQty=" + (Double.valueOf(quantityNeeded) + rs.getDouble("AvailableQty")) + ",Taked=" + (rs.getDouble("Taked") - Double.valueOf(quantityNeeded)) + " where productId=" + barCode, "");
//                                ResultSet rs2 = dbOperations.getData("select * from productsTypes where productId=" + barCode);
//                                while (rs2.next()) {
//                                    AfterPoint = "" + ((Double.valueOf(quantityNeeded) + rs.getDouble("AvailableQty")) / rs2.getDouble("QuantityOfOne"));
//                                    if (AfterPoint.substring(AfterPoint.indexOf(".") + 1, AfterPoint.length()).equals("0")) {
//                                        dbOperations.setDataOrDelete("update productsTypes set AllQuantity=" + AfterPoint + " where id=" + rs2.getLong("id"), "");
//                                    }
//
//                                }
//                            } else {
//                                dbOperations.setDataOrDelete("update products set AvailableQtyStore=" + (Double.valueOf(quantityNeeded) + rs.getDouble("AvailableQtyStore")) + " where productId=" + barCode, "");
//                                ResultSet rs2 = dbOperations.getData("select * from productsTypes where productId=" + barCode);
//                                while (rs2.next()) {
//                                    AfterPoint = "" + ((Double.valueOf(quantityNeeded) + rs.getDouble("AvailableQtyStore")) / rs2.getDouble("QuantityOfOne"));
//                                    if (AfterPoint.substring(AfterPoint.indexOf(".") + 1, AfterPoint.length()).equals("0")) {
//                                        dbOperations.setDataOrDelete("update productsTypes set AvailableQtyStore=" + AfterPoint + " where id=" + rs2.getLong("id"), "");
//                                    }
//
//                                }
//                            }
//                        }
//                    }
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }

            try {
                String id = txtInVIDReturn.getText();
                dbOperations.setDataOrDelete("Update extra set val='" + id + "' where exid=3", "");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
            try {
                for (int i = 0; i < jTable1.getRowCount(); i++) {
                    if (jTable1.getValueAt(i, 5).toString().equals("تجاري")) {
                        SaleTypeForSale = "تجاري";
                        GlobalDiscount = 0.0;
                    } else if (jTable1.getValueAt(i, 5).toString().equals("سعر معدل")) {
                        SaleTypeForSale = "تم تعديل سعر اثناء اصدار الفاتورة";
                    }
                }
                String customerName;
                if(txtCustomerName.getSelectedIndex()==0)
                {
                    customerName="زبون";
                }else
                {
                    customerName=txtCustomerName.getSelectedItem().toString();
                }
                    dbOperations.setDataOrDelete("insert into salesReturn (INID,customername,Total_Quantity,payed,Remained,Date,empname,Discount,AllTotal,DayDate,Total,SaleType,storePhone) values("
                        + INID
                        + ",'" + customerName
                        + "'," + txtTotalQuantity.getText()
                        + "," + payedValueForReturn
                        + "," + txtRemained.getText()
                        + ",'" + TodayDate.getText()
                        + "','" + txtEmpName.getText()
                        + "'," + txtDiscountLable.getText()
                        + "," + txtTOTAL.getText()
                        + ",'" + todayDate 
                        + "'," + getTotalForParameter()
                        + ",'" + SaleTypeForSale 
                        + "','"+txtStorePhone.getText()+"')", "");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, ex);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }

           
            try {
                dbOperations.setDataOrDelete("delete from sales where INID=" + txtINIDForBack.getText(), "");
                dbOperations.setDataOrDelete("delete from cart where INID=" + txtINIDForBack.getText(), "");

                txtCheckboxPriceOfMarket.setSelected(false);
                txtproductPriceCheckBox.setSelected(false);
                txtCustomerName.setEnabled(true);
                txtCustomerName.setSelectedIndex(0);
                txtproductCode.setText("");
                txtProductPrice.setText("0");
                txtAvailable.setText("0");
                txtQuantityNeeded.setText("");
                txtTotalQuantity.setText("0");
                txtDiscountLable.setText("0.0");
                txtPayedAmount.setText("0.0");
                txtFirstPrice.setText("0");
                txtTypeName.setSelectedItem(" ");
                txtINIDForBack.setText("0");
                txtSalePanel.setEnabled(true);
                btnReturn.setEnabled(false);
                txtCustomerName.setSelectedIndex(0);
                pageState = true;
                 try {
                new barcodeGenerator(txtInVIDReturn.getText()).generate();
                HashMap para = new HashMap();
                para.put("invo_para", Integer.parseInt(txtInVIDReturn.getText()));  // inv_id  is ireport parameter name
                para.put("Inv_Type", "فاتورة استرجاع: ");
                para.put("copyRight",""+(char)169+"Mo_Software:01091499680");
                ReportView r = new ReportView("src\\reports\\cartReturnReport.jasper", para);
                r.view();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
                txtInVIDReturn.setText(String.valueOf(Integer.valueOf(txtInVIDReturn.getText()) + 1));
                txtproductCode.requestFocus(true);
                btnUpdate.setEnabled(false);
                txtRealTotal.setText("0");

                DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
                dtm.setRowCount(0);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
      }else 
      {
          JOptionPane.showMessageDialog(null ,"هناك خطأ في المدفوع");
      }
    }//GEN-LAST:event_btnReturnActionPerformed

    private void txtproductCodePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtproductCodePropertyChange
        String productID = "0";
        int forEx = 0;
        try {
            productID = txtproductCode.getText();
            forEx = Integer.parseInt(txtproductCode.getText());
            for (int i = 0; i < productID.length(); i++) {
                if (Character.isDigit(productID.charAt(i))) {
                    txtproductCode.setForeground(new Color(0, 255, 0));
                    getAllProductRecords(productID);
                    getproductsTypesForCompo(productID);
                    if (!txtProductPrice.getText().equals("0")) {
                        txtQuantityNeeded.setEnabled(true);
                    } else {
                        txtQuantityNeeded.setEnabled(false);
                    }

                } else {
                    txtproductCode.setForeground(new Color(255, 0, 0));
                    txtProductPrice.setText("0");
                    txtQuantityNeeded.setEnabled(false);
                    txtAvailable.setText("0");
                    txtProductNameLabel.setText("");
                    txtTypeName.setSelectedIndex(0);
                }
            }

        } catch (NumberFormatException ex) {
            txtproductCode.setForeground(new Color(255, 0, 0));
            txtProductPrice.setText("0");
            txtQuantityNeeded.setEnabled(false);
            txtAvailable.setText("0");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }//GEN-LAST:event_txtproductCodePropertyChange

    private void txtProductNameCompoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProductNameCompoActionPerformed
        try {
            getAllProductsRecordsWithProductname(String.valueOf(txtProductNameCompo.getSelectedItem()));
             txtproductCodeKeyReleased(new KeyEvent(this, WIDTH, WIDTH, WIDTH, WIDTH));
        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_txtProductNameCompoActionPerformed

    private void txtPayedAmountFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPayedAmountFocusGained
        if (txtPayedAmount.getText().equals("0.0")) {
            txtPayedAmount.setText("");
        }
    }//GEN-LAST:event_txtPayedAmountFocusGained

    private void txtPayedAmountFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPayedAmountFocusLost
        if (txtPayedAmount.getText().equals("")) {
            txtPayedAmount.setText("0.0");
        }
    }//GEN-LAST:event_txtPayedAmountFocusLost

    private void txtCustomerNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCustomerNumberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCustomerNumberActionPerformed

    private void txtCustomerNumberPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtCustomerNumberPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCustomerNumberPropertyChange

    private void txtCustomerNumberKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCustomerNumberKeyReleased
        try {
            int forEx = Integer.parseInt(txtCustomerNumber.getText());
            ArrayList<customers> list = customerController.getAllCustomersByCustomerPhone(txtCustomerNumber.getText());
            Iterator<customers> itr = list.iterator();
            while (itr.hasNext()) {
                customers cus = itr.next();
                txtCustomerName.setSelectedItem(cus.getCustomerName());
                if (cus.getCustomerType().equals("تجاري")) {
                    txtCheckboxPriceOfMarket.setSelected(true);
                } else {
                    txtCheckboxPriceOfMarket.setSelected(false);
                }
                try {
                    if (txtTypeName.getSelectedIndex() == 0 && txtproductCode.getText().length() != 0) {
                        ResultSet rs = dbOperations.getData("select * from products where productId=" + txtproductCode.getText());
                        if (rs.next()) {
                            if (txtMarketOrStore.getSelectedItem().equals("محل")) {

                                productName = rs.getString("productName");
                                if (txtCheckboxPriceOfMarket.isSelected()) {
                                    txtProductPrice.setText(String.valueOf(rs.getDouble("priceOfMarket")));
                                } else {
                                    txtProductPrice.setText(String.valueOf(rs.getDouble("priceOfSale")));
                                }
                                txtAvailable.setText(String.valueOf(rs.getDouble("AvailableQty")));
                                QuantityAvailGlobalProducts = rs.getDouble("AvailableQty");

                            } else {
                                productName = rs.getString("productName");
                                if (txtCheckboxPriceOfMarket.isSelected()) {
                                    txtProductPrice.setText(String.valueOf(rs.getDouble("priceOfMarket")));
                                } else {
                                    txtProductPrice.setText(String.valueOf(rs.getDouble("priceOfSale")));
                                }
                                txtAvailable.setText(String.valueOf(rs.getDouble("AvailableQtyStore")));
                                QuantityAvailGlobalProducts = rs.getDouble("AvailableQtyStore");
                            }
                        }
                    } else if (txtTypeName.getSelectedIndex() != 0 && txtproductCode.getText().length() != 0) {
                        ArrayList<productsTypes> list1 = productsController.getAllProductsTypesRecordsForSellWithProductName(txtProductNameLabel.getText() + "_" + txtTypeName.getSelectedItem().toString());
                        Iterator<productsTypes> itr1 = list1.iterator();
                        while (itr1.hasNext()) {
                            productsTypes prod = itr1.next();
                            if (txtCheckboxPriceOfMarket.isSelected()) {
                                txtProductPrice.setText(String.valueOf(prod.getPriceOfMarket()));
                            } else {
                                txtProductPrice.setText(String.valueOf(prod.getPriceOfSale()));
                            }
                            if (txtMarketOrStore.getSelectedItem().equals("عادي")) {
                                txtAvailable.setText(String.valueOf(productsController.getAllQuantityProductsTypes(txtProductNameLabel.getText() + "_" + txtTypeName.getSelectedItem().toString())));
                                QuantityAvailGlobalTables = productsController.getAllQuantityProductsTypes(txtProductNameLabel.getText() + "_" + txtTypeName.getSelectedItem().toString());
                            } else {
                                txtAvailable.setText(String.valueOf(productsController.getAvailableQtyStoreProductsTypes(txtProductNameLabel.getText() + "_" + txtTypeName.getSelectedItem().toString())));
                                QuantityAvailGlobalTables = productsController.getAvailableQtyStoreProductsTypes(txtProductNameLabel.getText() + "_" + txtTypeName.getSelectedItem().toString());
                            }
                        }
                        if (!txtAvailable.getText().equals("0") && !txtProductPrice.getText().equals("0")) {
                            txtQuantityNeeded.setEnabled(true);
                            txtQuantityNeeded.requestFocus(true);
                        } else {
                            txtQuantityNeeded.setEnabled(false);
                        }
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "A7A togary");
                }

            }
        } catch (NumberFormatException e) {
            txtCheckboxPriceOfMarket.setSelected(false);
            txtCustomerName.setSelectedIndex(0);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_txtCustomerNumberKeyReleased

    private void txtMarketOrStoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMarketOrStoreActionPerformed
        try {

            if (txtTypeName.getSelectedIndex() == 0) {
                ResultSet rs = dbOperations.getData("select * from products where productId=" + txtproductCode.getText());
                if (rs.next()) {
                    if (txtMarketOrStore.getSelectedItem().equals("محل")) {
                        productName = rs.getString("productName");
                        if (txtCheckboxPriceOfMarket.isSelected()) {
                            txtProductPrice.setText(String.valueOf(rs.getDouble("priceOfMarket")));
                        } else {
                            txtProductPrice.setText(String.valueOf(rs.getDouble("priceOfSale")));
                        }
                        txtAvailable.setText(String.valueOf(rs.getDouble("AvailableQty")));
                        QuantityAvailGlobalProducts = rs.getDouble("AvailableQty");
                    } else {
                        productName = rs.getString("productName");
                        if (txtCheckboxPriceOfMarket.isSelected()) {
                            txtProductPrice.setText(String.valueOf(rs.getDouble("priceOfMarket")));
                        } else {
                            txtProductPrice.setText(String.valueOf(rs.getDouble("priceOfSale")));
                        }
                        txtAvailable.setText(String.valueOf(rs.getDouble("AvailableQtyStore")));
                        QuantityAvailGlobalProducts = rs.getDouble("AvailableQtyStore");
                    }
                    if ((rs.getDouble("AvailableQty") + rs.getDouble("AvailableQtyStore")) <= rs.getDouble("QuantityWarning")) {
                        txtAvailable.setBackground(Color.RED);
                        txtAvailable.setOpaque(true);
                    } else {
                        txtAvailable.setBackground(Color.BLUE);
                        txtAvailable.setOpaque(true);
                    }
                }
            } else {
                ArrayList<productsTypes> list = productsController.getAllProductsTypesRecordsForSellWithProductName(txtProductNameLabel.getText() + "_" + txtTypeName.getSelectedItem().toString());
                Iterator<productsTypes> itr = list.iterator();
                while (itr.hasNext()) {
                    productsTypes prod = itr.next();
                    txtProductPrice.setText(String.valueOf(prod.getPriceOfSale()));
                    if (txtMarketOrStore.getSelectedItem().equals("محل")) {
                        txtAvailable.setText(String.valueOf(productsController.getAllQuantityProductsTypes(txtProductNameLabel.getText() + "_" + txtTypeName.getSelectedItem().toString())));
                        QuantityAvailGlobalTables = productsController.getAllQuantityProductsTypes(txtProductNameLabel.getText() + "_" + txtTypeName.getSelectedItem().toString());
                    } else {
                        txtAvailable.setText(String.valueOf(productsController.getAvailableQtyStoreProductsTypes(txtProductNameLabel.getText() + "_" + txtTypeName.getSelectedItem().toString())));
                        QuantityAvailGlobalTables = productsController.getAvailableQtyStoreProductsTypes(txtProductNameLabel.getText() + "_" + txtTypeName.getSelectedItem().toString());
                    }
                }
                ResultSet rs1 = dbOperations.getData("select * from productsTypes where productTypeName='" + txtProductNameLabel.getText() + "_" + txtTypeName.getSelectedItem().toString() + "'");
                if (rs1.next()) {
                    if ((rs1.getDouble("AllQuantity") + rs1.getDouble("AvailableQtyStore")) <= rs1.getDouble("QuantityWarning")) {
                        txtAvailable.setBackground(Color.RED);
                        txtAvailable.setOpaque(true);
                    } else {
                        txtAvailable.setBackground(Color.BLUE);
                        txtAvailable.setOpaque(true);
                    }
                    if (!txtAvailable.getText().equals("0") || !txtProductPrice.getText().equals("0")) {
                        txtQuantityNeeded.setEnabled(true);

                        txtQuantityNeeded.requestFocus(true);
                    } else {

                        txtQuantityNeeded.setEnabled(false);
                    }
                }
            }
            txtQuantityNeeded.setText("");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_txtMarketOrStoreActionPerformed

    private void txtCheckboxPriceOfMarketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCheckboxPriceOfMarketActionPerformed
        try {
            if (txtproductPriceCheckBox.isSelected()) {
                txtproductPriceCheckBox.setSelected(false);
                txtProductPrice.setEnabled(false);
            }
            txtQuantityNeeded.setText("");
            if (txtTypeName.getSelectedIndex() == 0 && txtproductCode.getText().length() != 0) {

                ResultSet rs = dbOperations.getData("select * from products where productId=" + txtproductCode.getText());
                if (rs.next()) {
                    if (txtMarketOrStore.getSelectedItem().equals("محل")) {

                        productName = rs.getString("productName");
                        if (txtCheckboxPriceOfMarket.isSelected()) {
                            txtProductPrice.setText(String.format("%.3f", rs.getDouble("priceOfMarket")));
                            globalProductPrice=rs.getDouble("priceOfMarket");
                        } else {
                            txtProductPrice.setText(String.format("%.3f", rs.getDouble("priceOfSale")));
                            globalProductPrice=rs.getDouble("priceOfSale");
                        }
                        txtAvailable.setText(String.format("%.3f", rs.getDouble("AvailableQty")));
                        QuantityAvailGlobalProducts = rs.getDouble("AvailableQty");

                    } else {
                        productName = rs.getString("productName");
                        if (txtCheckboxPriceOfMarket.isSelected()) {
                            txtProductPrice.setText(String.format("%.3f", rs.getDouble("priceOfMarket")));
                        } else {
                            txtProductPrice.setText(String.format("%.3f", rs.getDouble("priceOfSale")));
                        }
                        txtAvailable.setText(String.format("%.3f", rs.getDouble("AvailableQtyStore")));
                        QuantityAvailGlobalProducts = rs.getDouble("AvailableQtyStore");
                    }
                }
            } else {
                ArrayList<productsTypes> list = productsController.getAllProductsTypesRecordsForSellWithProductName(txtProductNameLabel.getText() + "_" + txtTypeName.getSelectedItem().toString());
                Iterator<productsTypes> itr = list.iterator();
                while (itr.hasNext()) {
                    productsTypes prod = itr.next();
                    if (txtCheckboxPriceOfMarket.isSelected()) {
                        txtProductPrice.setText(String.format("%.3f", prod.getPriceOfMarket()));
                    } else {
                        txtProductPrice.setText(String.format("%.3f", prod.getPriceOfSale()));
                    }
                    if (txtMarketOrStore.getSelectedItem().equals("محل")) {
                        txtAvailable.setText(String.format("%.3f", productsController.getAllQuantityProductsTypes(txtProductNameLabel.getText() + "_" + txtTypeName.getSelectedItem().toString())));
                        QuantityAvailGlobalTables = productsController.getAllQuantityProductsTypes(txtProductNameLabel.getText() + "_" + txtTypeName.getSelectedItem().toString());
                    } else {
                        txtAvailable.setText(String.format("%.3f", productsController.getAvailableQtyStoreProductsTypes(txtProductNameLabel.getText() + "_" + txtTypeName.getSelectedItem().toString())));
                        QuantityAvailGlobalTables = productsController.getAvailableQtyStoreProductsTypes(txtProductNameLabel.getText() + "_" + txtTypeName.getSelectedItem().toString());
                    }
                }
                if (!txtAvailable.getText().equals("0") && !txtProductPrice.getText().equals("0")) {
                    txtQuantityNeeded.requestFocus(true);
                } else {
                    txtQuantityNeeded.setEnabled(false);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e + "ddd");
        }

    }//GEN-LAST:event_txtCheckboxPriceOfMarketActionPerformed

    private void txtINIDForBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtINIDForBackActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtINIDForBackActionPerformed

    private void txtINIDForBackPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtINIDForBackPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_txtINIDForBackPropertyChange

    private void txtINIDForBackKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtINIDForBackKeyReleased
        if (value.equals("0")) {
            btnDeleteAllActionPerformed(new java.awt.event.ActionEvent(0, 0, value));
        }
        value = txtINIDForBack.getText();

        DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
        try {
            int s = Integer.parseInt(txtINIDForBack.getText());
            dtm.setRowCount(0);
            ResultSet rs = dbOperations.getData("select * from cart where INID=" + s);
            while (rs.next()) {
                dtm.addRow(new Object[]{
                    rs.getLong("INID"),
                    rs.getString("productName"),
                    rs.getString("productPlace"),
                    rs.getString("Barcode"),
                    rs.getDouble("QuantityNeeded"),
                    rs.getString("saleType"),
                    rs.getDouble("Unit_Price"),
                    rs.getDouble("Total_Price"),
                    rs.getString("category"),
                    rs.getDouble("priceOfBuy")
                });
                txtDateOfInvoiceForBack.setText(rs.getString("date"));
                txtLastCasherNameForBack.setText(rs.getString("empname"));
            }
            if (jTable1.getRowCount() != 0) {
                getRealTotalAndDiscount();
                getTotalFromTable();
                txtSalePanel.setVisible(false);
                btnReturn.setEnabled(true);
                btnPrint.setEnabled(false);
                btnUpdate.setEnabled(false);
                btnDeleteAll.setEnabled(false);
                txtproductCode.setText("");
                txtQuantityNeeded.setText("");
            } else {
                txtDateOfInvoiceForBack.setText("");
                txtLastCasherNameForBack.setText("");
                txtSalePanel.setVisible(true);
                btnReturn.setEnabled(false);
                btnDeleteAll.setEnabled(true);
                txtTOTAL.setText("0");
                txtRealTotal.setText("0");
                btnUpdate.setEnabled(false);
                value = "0";
                this.pageState = true;
                txtproductCode.setText("");
                txtQuantityNeeded.setText("");
                btnPrint.setEnabled(true);
            }
            ResultSet rs1 = dbOperations.getData("select * from sales where INID=" + s);
            while (rs1.next()) {                                 // 7 
                txtDiscountLable.setText((rs1.getDouble("Discount")) + "");
                txtTotalQuantity.setText(rs1.getDouble("Total_Quantity") + "");
                txtTOTAL.setText(rs1.getDouble("AllTotal") + "");
                txtPayedAmount.setText(rs1.getDouble("Payed") + "");
                txtRemained.setText((rs1.getDouble("AllTotal") - rs1.getDouble("Payed")) + "");
                txtCustomerName.setSelectedItem(rs1.getString("customerName"));
                txtRealTotal.setText(rs1.getDouble("Total") + "");
            }
        } catch (NumberFormatException ex) {
//            txtDiscountLable.setText("0.0");
//            txtTotalQuantity.setText("0.0");
//            txtTOTAL.setText("0.0");
//            txtRealTotal.setText("0.0");
//            txtPayedAmount.setText("0.0");
//            txtRemained.setText("0.0");
//            txtCustomerName.setSelectedIndex(0);
//            btnDeleteAll.setEnabled(true);
            txtDateOfInvoiceForBack.setText("");
            txtLastCasherNameForBack.setText("");
            txtSalePanel.setVisible(true);
            btnReturn.setEnabled(false);
            btnDeleteAll.setEnabled(true);
            txtTOTAL.setText("0");
            txtRealTotal.setText("0");
            txtINIDForBack.setText("0");
            btnUpdate.setEnabled(false);
            value = "0";
            this.pageState = true;
            txtproductCode.setText("");
            txtQuantityNeeded.setText("");
            btnPrint.setEnabled(true);
            dtm.setRowCount(0);
            txtSalePanel.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_txtINIDForBackKeyReleased

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
     
        btnUpdate.setEnabled(false);
          btnReturn.setEnabled(false);
        if(Double.valueOf(txtRemained.getText())>0 && txtCustomerName.getSelectedIndex()==0)
      {
          JOptionPane.showMessageDialog(null,"قم بدفع القيمة المطلوبة");
      }
      else 
      {
             if (jTable1.getRowCount()==0) {
            btnUpdate.setEnabled(false);
        } else {
            String AfterPoint;
            double AvailableQty = 0;
            double Taked = 0;
            double QuantityOfOne = 0;
            double remainedFromSalesOperation = 0;
            double customerRemained = 0;
            double AllQuantity = 0;
            long productId = 0;
             btnUpdate.setEnabled(true);
            try {
                ResultSet rs = dbOperations.getData("select * from cartReturn where INID=" + txtInVIDReturn.getText());
                while (rs.next()) {
                     double quantityNeeded = rs.getDouble("QuantityNeeded");
                    for (int i = 0; i < jTable1.getRowCount(); i++) {
                        if (rs.getString("productName").equals(jTable1.getValueAt(i, 1).toString())) {
                            double jTableValue = Double.valueOf(jTable1.getValueAt(i, 4).toString());
                            if (quantityNeeded > jTableValue) {
                                 double newQuantityNeeded = quantityNeeded - jTableValue;
                                  double totalPrice = newQuantityNeeded * Double.valueOf(jTable1.getValueAt(i, 6).toString());
                                dbOperations.setDataOrDelete("update cartReturn set "
                                        + "QuantityNeeded=" + String.format("%.3f", newQuantityNeeded)
                                        + ",Total_price=" + String.format("%.3f", totalPrice)
                                        + " where productName='" + jTable1.getValueAt(i, 1).toString() + "'", "");

                            } else {
                                dbOperations.setDataOrDelete("delete from cartReturn where INID=" + txtInVIDReturn.getText(), "");
                                dbOperations.setDataOrDelete("delete from salesReturn where INID=" + txtInVIDReturn.getText(), "");
                            }
                        }
                    }
                }
                ResultSet rs1 = dbOperations.getData("select sum(QuantityNeeded) as TotalQuantity, sum(Total_price) as Total from cartReturn where INID=" + txtInVIDReturn.getText());
                while (rs1.next()) {
                     double totalQuantity = rs1.getDouble("TotalQuantity");
                     double total = rs1.getDouble("Total");
                  dbOperations.setDataOrDelete("update salesReturn set "
                    + "Total_Quantity=" + String.format("%.3f",totalQuantity) + ","
                    + "payed=" + String.format("%.3f",total) + ""
                    + ",Remained=0.0,AllTotal=" + String.format("%.3f",total)  + " where INID=" + txtInVIDReturn.getText(), "");
                }
                int INid = Integer.parseInt(txtINIDForBack.getText());
                String SaleTypeForSale = "عادي";
                try {
                    String productname;
                    String barCode;
                    String quantityNeeded;
                    String productPlace;
                    double productPrice;
                    String TotalPrice;
                    String SaleType;
                    String category;
                    String priceOfBuy;
                    int len = jTable1.getRowCount();
                    DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();

                    // Minus from Remained On Customer The valuew Remained Of Sales and Then add newRemained on the Remained After Minus
                    ResultSet rs3 = dbOperations.getData("select * from sales where INID=" + txtINIDForBack.getText());
                    ResultSet rs2 = dbOperations.getData("select * from capenat where id=(select max(id) from capenat)");
                    if (rs3.next() && rs2.next()) {
                        dbOperations.setDataOrDelete("update capenat set realValue=" + rs2.getDouble("afterEdictvalue") + ","
                                + "editedvalue=" +String.format("%.3f",  rs3.getDouble("payed"))
                                + ",afterEdictvalue=" + String.format("%.3f", (rs2.getDouble("afterEdictvalue") - rs3.getDouble("payed")))
                                + ",OperationType='استرجاع',date='" + TodayDate.getText() + "' where id=" + rs2.getLong("id"), "");

                        if (rs3.getDouble("remained") > Double.valueOf(txtRemained.getText())) {

                            remainedFromSalesOperation = rs3.getDouble("remained") - Double.valueOf(txtRemained.getText());
                            //Minus The Remained Value Of This Sale From The Remaied Value Of Customer Totalremained
                        } else {
                            remainedFromSalesOperation = Double.valueOf(txtRemained.getText());
                        }
                        ResultSet rsdb = dbOperations.getData("select * from customers where customerName='" + txtCustomerName.getSelectedItem().toString() + "'");
                        if (rsdb.next()) {
                            customerRemained = rsdb.getDouble("AllRemained");
                        }
                        if (customerRemained >= remainedFromSalesOperation) {
                            customerRemained -= remainedFromSalesOperation;
                        } else {
                            dbOperations.setDataOrDelete("insert into payment (customerName,casherName,remainedbefor,payed,remainedAfter,date,receiver,Note) "
                                    + "values('"
                                    + txtCustomerName.getSelectedItem().toString()
                                    + "','" + txtEmpName.getText()
                                    + "'," + customerRemained
                                    + "," + (-(remainedFromSalesOperation - customerRemained))
                                    + "," + 0
                                    + ",'" + TodayDate.getText()
                                    + "','" + txtEmpName.getText()
                                    + "','" + "استرجاع فاتورة" + "')", "");
                            customerRemained = 0;
                        }
                        dbOperations.setDataOrDelete("update customers set "
                                + "AllRemained=" + customerRemained + " where customerName='" + txtCustomerName.getSelectedItem().toString() + "'", "");

                    }
                    dbOperations.setDataOrDelete("delete from cart where INID=" + txtINIDForBack.getText(), "");
                    dbOperations.setDataOrDelete("delete from sales where INID=" + txtINIDForBack.getText(), "");
                    if (jTable1.getRowCount() != 0) {
                        for (int i = 0; i < len; i++) {
                            productname = dtm.getValueAt(i, 1).toString();
                            barCode = dtm.getValueAt(i, 3).toString();
                            quantityNeeded = dtm.getValueAt(i, 4).toString();
                            productPrice = Double.parseDouble(dtm.getValueAt(i, 6).toString());
                            SaleType = dtm.getValueAt(i, 5).toString();
                            TotalPrice = dtm.getValueAt(i, 7).toString();
                            productPlace = dtm.getValueAt(i, 2).toString();
                            category = dtm.getValueAt(i, 8).toString();
                            priceOfBuy = dtm.getValueAt(i, 9).toString();
                            dbOperations.setDataOrDelete("Insert into cart("
                                    + "INID,"
                                    + "productname,"
                                    + "BarCode,"
                                    + "quantityneeded,"
                                    + "Unit_Price,"
                                    + "Total_Price"
                                    + ",empname,"
                                    + "date,"
                                    + "productPlace,"
                                    + "SaleType,category,priceOfBuy,priceOnStore,customerNumber) values("
                                    + INid + ",'"
                                    + productname
                                    + "','" + barCode
                                    + "'," + quantityNeeded
                                    + "," + productPrice
                                    + "," + TotalPrice
                                    + ",'" + txtEmpName.getText()
                                    + "','" + TodayDate.getText()
                                    + "','" + productPlace
                                    + "','" + SaleType + "','" + category + "'," + priceOfBuy + "," + (Double.valueOf(quantityNeeded) * Double.valueOf(priceOfBuy)) + ",'" + txtCustomerNumber.getText() + "')", "");
                            try {
                                if (productPlace.equals("محل")) {
                                    if (!productname.contains("_")) {

                                        ResultSet rs13 = dbOperations.getData("select * from products where productId=" + barCode);
                                        if (rs13.next()) {
                                            productsController.updateAvailableWhenReturnFromProductsTable((rs13.getDouble("AvailableQty") + Double.valueOf(quantityNeeded)),
                                                    Double.valueOf(quantityNeeded),
                                                    barCode, productPlace);
                                        }
//                   
                                        ResultSet rs11 = dbOperations.getData("select * from productsTypes where productId=" + barCode);
                                        while (rs11.next()) {
//                                            AfterPoint = String.valueOf(Double.parseDouble(quantityNeeded) / rs11.getDouble("QuantityOfOne"));
//                                            if (AfterPoint.substring(AfterPoint.indexOf(".") + 1, AfterPoint.length()).equals("0")) {
//                                                dbOperations.setDataOrDelete("update productsTypes set AllQuantity=" +String.format("%.3f",  (rs11.getDouble("AllQuantity") + (Double.valueOf(quantityNeeded) / rs11.getDouble("QuantityOfOne")))) + " where id=" + rs11.getLong("id"), "");
//                                            } else {
                                                ResultSet rsw = dbOperations.getData("select * from products where productId=" + barCode);
                                                if (rsw.next()) {
                                                    dbOperations.setDataOrDelete("update productsTypes set "
                                                            + "AllQuantity=" + String.format("%.3f", (rsw.getDouble("AvailableQty") /rs11.getDouble("QuantityOfOne")))+ " "
                                                            + "where id=" + rs11.getLong("id"), "");
                                                }
//                                            }
                                        }
                                    } else {
                                        ResultSet rs111 = dbOperations.getData("select * from productsTypes where "
                                                + "productTypeName='" + productname + "'");
                                        if (rs111.next()) {
                                            QuantityOfOne = rs111.getDouble("QuantityOfOne");
                                            productId = rs111.getLong("productId");
                                            AllQuantity = rs111.getDouble("AllQuantity");
                                        }
                                        ResultSet rs22 = dbOperations.getData("select * from products where productId=" + productId);
                                        if (rs22.next()) {
                                            Taked = rs22.getDouble("Taked");
                                            AvailableQty = rs22.getDouble("AvailableQty");
                                        }
                                        double lastAvailable=(AvailableQty + (Double.valueOf(quantityNeeded) * QuantityOfOne));
//                                        dbOperations.setDataOrDelete("update productsTypes set"
//                                                + " AllQuantity=" + (AllQuantity + Double.valueOf(quantityNeeded)) + " where "
//                                                + "productTypeName='" + productname + "'", "");
//                                        
                                        dbOperations.setDataOrDelete("update products set "
                                                + "AvailableQty=" +String.format("%3f", lastAvailable)
                                                + ",Taked=" + String.format("%.3f", (Taked - (Double.valueOf(quantityNeeded) * QuantityOfOne))) + ""
                                                + " where productId=" + productId, "");
                                       ResultSet rsss=dbOperations.getData("select * from productsTypes where productId="+productId);
                                               while(rsss.next())
                                               {
                                                   dbOperations.setDataOrDelete("update productsTypes set"
                                                + " AllQuantity=" +String.format("%.3f",  (lastAvailable/rsss.getDouble("QuantityOfOne"))) + " where "
                                                + "id="+rsss.getLong("id"), "");
                                               }
                                    }
                                } else {
                                        if (!productname.contains("_")) {

                                        ResultSet rs13 = dbOperations.getData("select * from products where productId=" + barCode);
                                        if (rs13.next()) {
                                            productsController.updateAvailableWhenReturnFromProductsTable((rs13.getDouble("AvailableQtyStore") + Double.valueOf(quantityNeeded)),
                                                    Double.valueOf(quantityNeeded),
                                                    barCode, productPlace);
                                        }
//                   
                                        ResultSet rs11 = dbOperations.getData("select * from productsTypes where productId=" + barCode);
                                        while (rs11.next()) {
//                                            AfterPoint = String.valueOf(Double.parseDouble(quantityNeeded) / rs11.getDouble("QuantityOfOne"));
//                                            if (AfterPoint.substring(AfterPoint.indexOf(".") + 1, AfterPoint.length()).equals("0")) {
//                                                dbOperations.setDataOrDelete("update productsTypes set AvailableQtyStore=" +String.format("%.3f",  (rs11.getDouble("AvailableQtyStore") + (Double.valueOf(quantityNeeded) / rs11.getDouble("QuantityOfOne")))) + " where id=" + rs11.getLong("id"), "");
//                                            } else {
                                                ResultSet rsw = dbOperations.getData("select * from products where productId=" + barCode);
                                                if (rsw.next()) {
                                                    dbOperations.setDataOrDelete("update productsTypes set "
                                                            + "AvailableQtyStore=" + String.format("%.3f", ((rsw.getDouble("AvailableQtyStore") / rs11.getDouble("QuantityOfOne")))) + " "
                                                            + "where id=" + rs11.getLong("id"), "");
                                                }
//                                            }
                                        }
                                    } else {
                                        ResultSet rs111 = dbOperations.getData("select * from productsTypes where "
                                                + "productTypeName='" + productname + "'");
                                        if (rs111.next()) {
                                            QuantityOfOne = rs111.getDouble("QuantityOfOne");
                                            productId = rs111.getLong("productId");
                                            AllQuantity = rs111.getDouble("AvailableQtyStore");
                                        }
                                        ResultSet rs22 = dbOperations.getData("select * from products where productId=" + productId);
                                        if (rs22.next()) {
                                            Taked = rs22.getDouble("Taked");
                                            AvailableQty = rs22.getDouble("AvailableQtyStore");
                                        }
                                        double lastAvailable=(AvailableQty + (Double.valueOf(quantityNeeded) * QuantityOfOne));
//                                        dbOperations.setDataOrDelete("update productsTypes set"
//                                                + " AvailableQtyStore=" + (AllQuantity + Double.valueOf(quantityNeeded)) + " where "
//                                                + "productTypeName='" + productname + "'", "");
//                                        
                                        dbOperations.setDataOrDelete("update products set "
                                                + "AvailableQtyStore=" +String.format("%.3f", lastAvailable)
                                                + ",Taked=" + String.format("%.3f", (Taked - (Double.valueOf(quantityNeeded) * QuantityOfOne))) + ""
                                                + " where productId=" + productId, "");
                                       ResultSet rsss=dbOperations.getData("select * from productsTypes where productId="+productId);
                                               while(rsss.next())
                                               {
                                                   dbOperations.setDataOrDelete("update productsTypes set"
                                                + " AvailableQtyStore=" + String.format("%.3f", (lastAvailable/rsss.getDouble("QuantityOfOne")) )+ " where "
                                                + "id="+rsss.getLong("id"), "");
                                               }
                                    }
                                }
                            } catch (Exception e) {
                                JOptionPane.showMessageDialog(null, e);
                            }
                            try {
                                if (productPlace.equals("محل")) {
                                    if (!productname.contains("_")) {

                                        ResultSet rs13 = dbOperations.getData("select * from products where productId=" + barCode);
                                        if (rs13.next()) {
                                            productsController.updateAvailableWhenAddFromProductsTable((rs13.getDouble("AvailableQty") - Double.valueOf(quantityNeeded)),
                                                    Double.valueOf(quantityNeeded),
                                                    barCode, productPlace);
                                        }
//                   
                                        ResultSet rs11 = dbOperations.getData("select * from productsTypes where productId=" + barCode);
                                        while (rs11.next()) {
//                                            AfterPoint = String.valueOf(Double.parseDouble(quantityNeeded) / rs11.getDouble("QuantityOfOne"));
//                                            if (AfterPoint.substring(AfterPoint.indexOf(".") + 1, AfterPoint.length()).equals("0")) {
//                                                dbOperations.setDataOrDelete("update productsTypes set AllQuantity=" +String.format("%.3f",  (rs11.getDouble("AllQuantity") -(Double.valueOf(quantityNeeded) / rs11.getDouble("QuantityOfOne")))) + " where id=" + rs11.getLong("id"), "");
//                                            } else {
                                                ResultSet rsw = dbOperations.getData("select * from products where productId=" + barCode);
                                                if (rsw.next()) {
                                                    dbOperations.setDataOrDelete("update productsTypes set "
                                                            + "AllQuantity=" +String.format("%.3f", (rsw.getDouble("AvailableQty") /rs11.getDouble("QuantityOfOne"))) + " "
                                                            + "where id=" + rs11.getLong("id"), "");
                                                }
//                                            }
                                        }
                                    } else {
                                        ResultSet rs111 = dbOperations.getData("select * from productsTypes where "
                                                + "productTypeName='" + productname + "'");
                                        if (rs111.next()) {
                                            QuantityOfOne = rs111.getDouble("QuantityOfOne");
                                            productId = rs111.getLong("productId");
                                            AllQuantity = rs111.getDouble("AllQuantity");
                                        }
                                        ResultSet rs22 = dbOperations.getData("select * from products where productId=" + productId);
                                        if (rs22.next()) {
                                            Taked = rs22.getDouble("Taked");
                                            AvailableQty = rs22.getDouble("AvailableQty");
                                        }
                                        double lastAvailable=(AvailableQty -(Double.valueOf(quantityNeeded) * QuantityOfOne));
//                                        dbOperations.setDataOrDelete("update productsTypes set"
//                                                + " AllQuantity=" + (AllQuantity-Double.valueOf(quantityNeeded)) + " where "
//                                                + "productTypeName='" + productname + "'", "");
//                                        
                                        dbOperations.setDataOrDelete("update products set "
                                                + "AvailableQty=" +String.format("%.3f", lastAvailable)
                                                + ",Taked=" + String.format("%.3f", (Taked+(Double.valueOf(quantityNeeded) * QuantityOfOne))) 
                                                + " where productId=" + productId, "");
                                       ResultSet rsss=dbOperations.getData("select * from productsTypes where productId="+productId);
                                               while(rsss.next())
                                               {
                                                   dbOperations.setDataOrDelete("update productsTypes set"
                                                + " AllQuantity=" + String.format("%.3f",(lastAvailable/rsss.getDouble("QuantityOfOne"))) + " where "
                                                + "id="+rsss.getLong("id"), "");
                                               }
                                    }
                                } else {
                                        if (!productname.contains("_")) {

                                        ResultSet rs13 = dbOperations.getData("select * from products where productId=" + barCode);
                                        if (rs13.next()) {
                                            productsController.updateAvailableWhenAddFromProductsTable((rs13.getDouble("AvailableQtyStore") - Double.valueOf(quantityNeeded)),
                                                    Double.valueOf(quantityNeeded),
                                                    barCode, productPlace);
                                        }
//                   
                                        ResultSet rs11 = dbOperations.getData("select * from productsTypes where productId=" + barCode);
                                        while (rs11.next()) {
//                                            AfterPoint = String.valueOf(Double.parseDouble(quantityNeeded) / rs11.getDouble("QuantityOfOne"));
//                                            if (AfterPoint.substring(AfterPoint.indexOf(".") + 1, AfterPoint.length()).equals("0")) {
//                                                dbOperations.setDataOrDelete("update productsTypes set AvailableQtyStore=" +String.format("%.3f",  (rs11.getDouble("AvailableQtyStore") -(Double.valueOf(quantityNeeded) / rs11.getDouble("QuantityOfOne")))) + " where id=" + rs11.getLong("id"), "");
//                                            } else {
                                                ResultSet rsw = dbOperations.getData("select * from products where productId=" + barCode);
                                                if (rsw.next()) {
                                                    dbOperations.setDataOrDelete("update productsTypes set "
                                                            + "AvailableQtyStore=" + String.format("%.3f",rsw.getDouble("AvailableQtyStore") / rs11.getDouble("QuantityOfOne")) + " "
                                                            + "where id=" + rs11.getLong("id"), "");
                                                }
//                                            }
                                        }
                                    } else {
                                        ResultSet rs111 = dbOperations.getData("select * from productsTypes where "
                                                + "productTypeName='" + productname + "'");
                                        if (rs111.next()) {
                                            QuantityOfOne = rs111.getDouble("QuantityOfOne");
                                            productId = rs111.getLong("productId");
                                            AllQuantity = rs111.getDouble("AvailableQtyStore");
                                        }
                                        ResultSet rs22 = dbOperations.getData("select * from products where productId=" + productId);
                                        if (rs22.next()) {
                                            Taked = rs22.getDouble("Taked");
                                            AvailableQty = rs22.getDouble("AvailableQtyStore");
                                        }
                                        double lastAvailable=(AvailableQty - (Double.valueOf(quantityNeeded) * QuantityOfOne));
//                                        dbOperations.setDataOrDelete("update productsTypes set"
//                                                + " AvailableQtyStore=" + (AllQuantity - Double.valueOf(quantityNeeded)) + " where "
//                                                + "productTypeName='" + productname + "'", "");
//                                        
                                        dbOperations.setDataOrDelete("update products set "
                                                + "AvailableQtyStore=" +String.format("%.3f", lastAvailable)
                                                + ",Taked=" + String.format("%.3f", (Taked +(Double.valueOf(quantityNeeded) * QuantityOfOne)) )+ ""
                                                + " where productId=" + productId, "");
                                       ResultSet rsss=dbOperations.getData("select * from productsTypes where productId="+productId);
                                               while(rsss.next())
                                               {
                                                   dbOperations.setDataOrDelete("update productsTypes set"
                                                + " AvailableQtyStore=" + String.format("%.3f", (lastAvailable/rsss.getDouble("QuantityOfOne"))) + " where "
                                                + "id="+rsss.getLong("id"), "");
                                               }
                                    }
                                }
                            } catch (Exception e) {
                                JOptionPane.showMessageDialog(null, e);
                            }
                        }

                        try {

                            double finaltotal = Double.parseDouble(txtTOTAL.getText());
                            double finalRemained = Double.parseDouble(txtRemained.getText());
                            String Status = null;
                            if (finalRemained == 0.0) {
                                Status = "مدفوع";
                            } else if (finalRemained < finaltotal) {
                                Status = "متبقي";
                            } else if (finaltotal == finalRemained) {
                                Status = "غير مدفوع";
                            }
                            for (int i = 0; i < jTable1.getRowCount(); i++) {
                                if (jTable1.getValueAt(i, 5).toString().equals("تجاري")) {
                                    SaleTypeForSale = "تجاري";
                                } else if (jTable1.getValueAt(i, 5).toString().equals("سعر معدل")) {
                                    SaleTypeForSale = "تم تعديل سعر اثناء اصدار الفاتورة";
                                }
                            }
                    String customerName;
                    if (txtCustomerName.getSelectedIndex() != 0) {
                        customerName = txtCustomerName.getSelectedItem().toString();
                    } else {
                        customerName = "زبون";
                    }
                            dbOperations.setDataOrDelete("insert into sales (INID,customername,Total_Quantity,payed,Status,Remained,Date,empname,Discount,AllTotal,DayDate,Total,SaleType,storePhone) values("
                                    + INid
                                    + ",'" + customerName
                                    + "'," + txtTotalQuantity.getText()
                                    + "," + txtPayedAmount.getText()
                                    + ",'" + Status
                                    + "'," + txtRemained.getText()
                                    + ",'" + TodayDate.getText()
                                    + "','" + txtEmpName.getText()
                                    + "'," + txtDiscountLable.getText()
                                    + "," + txtTOTAL.getText()
                                    + ",'" + todayDate + "'," + txtRealTotal.getText()
                                    + ",'" + SaleTypeForSale + "','"+txtStorePhone.getText()+"')", "");
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(null, ex);
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, e);
                        }

                        try {

                            ResultSet rs22 = dbOperations.getData("select * from capenat where id=(select max(id) from capenat)");
                            if (rs22.next()) {
                                dbOperations.setDataOrDelete("update capenat set "
                                        + "realValue=" + rs22.getDouble("afterEdictvalue") + ","
                                        + "editedvalue=" + txtPayedAmount.getText() + ","
                                        + "afterEdictvalue=" + (rs22.getDouble("afterEdictvalue") + Double.valueOf(txtPayedAmount.getText())) + ","
                                        + "OperationType='توريد المبيعات',"
                                        + "date='" + TodayDate.getText()
                                        + "' where id=" + rs22.getLong("id"), "تم توريد المدفوع الي الخزنة");
                            }
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "الغلطة عموما");
                        }
                    }
                   
                    txtCustomerName.setEnabled(true);
                    txtCustomerName.setSelectedIndex(0);
                    txtproductCode.setText("");
                    txtProductPrice.setText("0");
                    txtAvailable.setText("0");
                    txtQuantityNeeded.setText("");
                    txtTotalQuantity.setText("0");
                    txtDiscountLable.setText("0.0");
                    txtPayedAmount.setText("0.0");
                    txtFirstPrice.setText("0");
                    txtTypeName.removeAll();
                    txtTypeName.addItem(" ");
                    txtINIDForBack.setText("0");
                    txtproductCode.requestFocus(true);
                                            try {
                            new barcodeGenerator(txtINIDForBack.getText()).generate();
                            HashMap para = new HashMap();
                            para.put("invo_para", Integer.parseInt(txtINIDForBack.getText()));  // inv_id  is ireport parameter name
                            para.put("Inv_Type", "فاتورة بيع:");
                            para.put("copyRight", (char)169+"Mo_Software:01091499680");
                            ReportView r = new ReportView("src\\reports\\finalCartReport.jasper", para);
                            r.view();
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, e);
                        }
                     try {
                        new barcodeGenerator(txtInVIDReturn.getText()).generate();
                        HashMap para = new HashMap();
                        para.put("invo_para", Integer.parseInt(txtInVIDReturn.getText()));  // inv_id  is ireport parameter name
                        para.put("Inv_Type", "فاتورة استرجاع: ");
                         para.put("copyRight",""+ (char)169+"Mo_Software:01091499680");
                        ReportView r = new ReportView("src\\reports\\cartReturnReport.jasper", para);
                        r.view();
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, e);
                    }
                    txtTOTAL.setText("0.0");
                    txtRemained.setText("0.0");
                    txtRealTotal.setText("0.0");
                    txtProiductCategory.setText("");
                    txtPriceOfBuy.setText("0.0");
                    txtproductPriceCheckBox.setSelected(false);
                    txtInVIDReturn.setText(String.valueOf(Integer.valueOf(txtInVIDReturn.getText()) + 1));
                    DefaultTableModel dtm2 = (DefaultTableModel) jTable1.getModel();
                    dtm2.setRowCount(0);
                    pageState = true;
                    txtINIDForBack.setEnabled(true);
                    

                     
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "الغلطة");
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "الغلطة عموما");
            }
             }
      }
         btnUpdate.setEnabled(true);
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void txtINIDForBackFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtINIDForBackFocusGained
        if (txtINIDForBack.getText().equals("0")) {
            txtINIDForBack.setText("");
        }
    }//GEN-LAST:event_txtINIDForBackFocusGained

    private void txtINIDForBackFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtINIDForBackFocusLost
        if (txtINIDForBack.getText().equals("")) {
            txtINIDForBack.setText("0");
        }
    }//GEN-LAST:event_txtINIDForBackFocusLost

    private void txtCustomerNumberKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCustomerNumberKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCustomerNumberKeyTyped

    private void jTable1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseEntered

    private void jScrollPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jScrollPane1MouseClicked

    private void txtINIDForBackKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtINIDForBackKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtINIDForBackKeyTyped

    private void txtProductPriceKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProductPriceKeyReleased
        try {
            double quantityNeeded = Double.parseDouble(txtQuantityNeeded.getText());
            double productpr = Double.parseDouble(txtProductPrice.getText());
            if (Double.valueOf(txtQuantityNeeded.getText()) <= QuantityAvailGlobalTables) {
                if (productpr > 1) {
                    txtFirstPrice.setText("" + (quantityNeeded * productpr));
                    if (txtFirstPrice.getText().equals("0.0")) {
                        btnAdd.setEnabled(false);
                    } else {
                        btnAdd.setEnabled(true);
                    }
                } else {
                    txtFirstPrice.setText("0");
                    btnAdd.setEnabled(false);
                }
            } else {
                txtFirstPrice.setText("0");
                btnAdd.setEnabled(false);
                txtQuantityNeeded.setText("");
            }

        } catch (NumberFormatException e) {
            txtFirstPrice.setText("0");
            btnAdd.setEnabled(false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_txtProductPriceKeyReleased

    private void txtproductPriceCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtproductPriceCheckBoxActionPerformed
        try {
            if (txtproductPriceCheckBox.isSelected()) {
                if (txtCheckboxPriceOfMarket.isSelected()) {
                    txtCheckboxPriceOfMarket.setSelected(false);
                    txtCheckboxPriceOfMarketActionPerformed(evt);
                }
                String password = JOptionPane.showInputDialog(null, "<html><h1>ادخل كلمة السر الخاصة بك</h1></html>", "", JOptionPane.WARNING_MESSAGE);
                ResultSet rs = dbOperations.getData("select * from login where password='" + password + "' and userType='Admin'");
                if (rs.next()) {
                    txtProductPrice.setEnabled(true);
                    txtproductPriceCheckBox.setSelected(true);
                } else {
                    txtproductPriceCheckBox.setSelected(false);
                    txtProductPrice.setEnabled(false);

                    JOptionPane.showMessageDialog(null, "<html><h1>غير مسموح لك</h1></html>");
                }
            } else {

                txtProductPrice.setEnabled(false);

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_txtproductPriceCheckBoxActionPerformed

    private void jTable1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyReleased
       
    }//GEN-LAST:event_jTable1KeyReleased

    private void txtproductCodeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtproductCodeKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtproductCodeKeyTyped

    private void txtQuantityNeededKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQuantityNeededKeyPressed
        btnAdd.setEnabled(false);
    }//GEN-LAST:event_txtQuantityNeededKeyPressed

    private void txtproductCodeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtproductCodeKeyPressed
       btnAdd.setEnabled(false);
    }//GEN-LAST:event_txtproductCodeKeyPressed

    private void txtProductPriceKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProductPriceKeyPressed
      btnAdd.setEnabled(false);
    }//GEN-LAST:event_txtProductPriceKeyPressed

    private void txtStorePhoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtStorePhoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtStorePhoneActionPerformed

    private void txtStorePhoneKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtStorePhoneKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtStorePhoneKeyPressed

    private void txtStorePhoneKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtStorePhoneKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtStorePhoneKeyReleased

    private void txtStorePhoneFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtStorePhoneFocusGained
      
    }//GEN-LAST:event_txtStorePhoneFocusGained

    private void getAllPhonesId()
    {
        try {
            ResultSet rs=dbOperations.getData("select id from phone");
            txtPhoneId.removeAllItems();
            while(rs.next())
            {
                txtPhoneId.addItem(""+rs.getLong("id"));
            }
            txtPhoneId.setSelectedIndex(txtPhoneId.getItemCount()-1);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
   private void getLastPhone()
    {
        try {
            ResultSet rs=dbOperations.getData("select phone from phone order by id desc limit 1");
            while(rs.next())
            {
                txtStorePhone.setText(rs.getString("phone"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(txtStorePhone.getText().length()!=0)
        {
            try {
                dbOperations.setDataOrDelete("insert into phone (phone) values('"+txtStorePhone.getText()+"')", "تم اضافة الرقم الاخير");
                getAllPhonesId();
                txtPhoneId.setSelectedIndex(txtPhoneId.getItemCount()-1);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtPhoneIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPhoneIdActionPerformed
        try {
            ResultSet rs=dbOperations.getData("select phone from phone where id="+txtPhoneId.getSelectedItem().toString());
            if(rs.next())
            {
                txtStorePhone.setText(rs.getString("phone"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_txtPhoneIdActionPerformed

    private void txtProductNameSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProductNameSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProductNameSearchActionPerformed

    private void txtProductNameSearchPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtProductNameSearchPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProductNameSearchPropertyChange

    public void getallProductsNames(String productName)
    {
        try {
            ResultSet rs=dbOperations.getData("select productName from products where productName like'%"+productName+"%'");
            
            txtProductNameCompo.removeAllItems();
            txtProductNameCompo.addItem(" ");
            while(rs.next())
            {
                txtProductNameCompo.addItem(rs.getString("productName")); 
            }
        } catch (Exception e) {
        }
    }
    private void txtProductNameSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProductNameSearchKeyReleased
        try {
            String dd=txtProductNameSearch.getText();
            getallProductsNames(dd);
           
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtProductNameSearchKeyReleased

    private void txtProductNameSearchKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProductNameSearchKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProductNameSearchKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LiveTimes;
    private javax.swing.JLabel TodayDate;
    private javax.swing.JButton btnAdd;
    public javax.swing.JButton btnDeleteAll;
    private javax.swing.JButton btnPrint;
    private javax.swing.JButton btnReturn;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel txtAvailable;
    private javax.swing.JCheckBox txtCheckboxPriceOfMarket;
    private javax.swing.JComboBox<String> txtCustomerName;
    private javax.swing.JTextField txtCustomerNumber;
    private javax.swing.JLabel txtDateOfInvoiceForBack;
    private javax.swing.JLabel txtDiscountLable;
    private javax.swing.JLabel txtEmpName;
    private javax.swing.JLabel txtFirstPrice;
    private javax.swing.JTextField txtINIDForBack;
    private javax.swing.JLabel txtInVID;
    private javax.swing.JLabel txtInVIDReturn;
    private javax.swing.JLabel txtLastCasherNameForBack;
    private javax.swing.JComboBox<String> txtMarketOrStore;
    private javax.swing.JTextField txtPayedAmount;
    private javax.swing.JComboBox<String> txtPhoneId;
    private javax.swing.JLabel txtPriceOfBuy;
    private javax.swing.JComboBox<String> txtProductNameCompo;
    private javax.swing.JLabel txtProductNameLabel;
    private javax.swing.JTextField txtProductNameSearch;
    private javax.swing.JTextField txtProductPrice;
    private javax.swing.JLabel txtProiductCategory;
    private javax.swing.JTextField txtQuantityNeeded;
    private javax.swing.JLabel txtRealTotal;
    private javax.swing.JLabel txtRemained;
    private javax.swing.JLabel txtReturnCustomername;
    private javax.swing.JPanel txtSalePanel;
    private javax.swing.JTextField txtStorePhone;
    private javax.swing.JLabel txtTOTAL;
    private javax.swing.JLabel txtTotalQuantity;
    private javax.swing.JComboBox<String> txtTypeName;
    private javax.swing.JTextField txtproductCode;
    private javax.swing.JCheckBox txtproductPriceCheckBox;
    // End of variables declaration//GEN-END:variables
}
