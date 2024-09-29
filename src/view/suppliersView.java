
package view;

import Database.dbOperations;
import TOOLS.TableCustom;
import controller.suppliersController;
import controller.suppliersController;
import java.awt.Color;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import model.suppliersModel;

public class suppliersView extends javax.swing.JPanel {

    boolean verify = false;
    SimpleDateFormat df;
    SimpleDateFormat Month;
    String MonthDate;

    public suppliersView() {
        initComponents();
        TOOLS.TableCustom.apply(jScrollPane1, TableCustom.TableType.MULTI_LINE);
        TOOLS.TableCustom.apply(jScrollPane2, TableCustom.TableType.MULTI_LINE);
        df = new SimpleDateFormat("yyyy-MM-dd", new Locale("en"));
        Month = new SimpleDateFormat("yyyy-MM", new Locale("en"));
        txtTodayDate.setText(df.format(new Date()));
        MonthDate = Month.format(new Date());
        getAllPaymentRecords(MonthDate + "_______________");
        getAllRecords();
        getSuppliersName();
        txtAllRemained.setEnabled(false);
    }

    public void getAllTotal() {
        double Total = 0;
        try {
            for (int i = 0; i < jTable2.getRowCount(); i++) {
                Total += Double.valueOf(jTable2.getValueAt(i, 5).toString());
            }
            txtTOTAL.setText("" + Total);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void getSuppliersName()
    {
        try {
            ResultSet rs=dbOperations.getData("select supplierName from suppliers");
            while(rs.next())
            {
                txtSupplierNameSearch.addItem(rs.getString("supplierName"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    public void getAllPaymentRecords(String date) {
        try {
            DefaultTableModel dtm = (DefaultTableModel) jTable2.getModel();
            dtm.setRowCount(0);
            ResultSet rs = dbOperations.getData("select * from payoperationforsuppliers where date like'" + date + "%'");
            while (rs.next()) {
                dtm.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getInt("purchaseNumber"),
                    rs.getString("supplierName"),
                    rs.getString("receiverName"),
                    rs.getDouble("remainedBefor"),
                    rs.getDouble("payed"),
                    rs.getDouble("remainedAfter"),
                    rs.getString("operationdetails"),
                    rs.getString("date")
                });
            }
            getAllTotal();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void getAllPaymentRecordsPurchaseNumber(String purchaseNumber) {
        try {
            DefaultTableModel dtm = (DefaultTableModel) jTable2.getModel();
            dtm.setRowCount(0);
            ResultSet rs = dbOperations.getData("select * from payoperationforsuppliers where purchaseNumber =" + purchaseNumber);
            while (rs.next()) {
                dtm.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getInt("purchaseNumber"),
                    rs.getString("supplierName"),
                    rs.getString("receiverName"),
                    rs.getDouble("remainedBefor"),
                    rs.getDouble("payed"),
                    rs.getDouble("remainedAfter"),
                    rs.getString("operationdetails"),
                    rs.getString("date")
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void getAllPaymentRecordsPayNumber(String payNumber) {
        try {
            DefaultTableModel dtm = (DefaultTableModel) jTable2.getModel();
            dtm.setRowCount(0);
            ResultSet rs = dbOperations.getData("select * from payoperationforsuppliers where id =" + payNumber);
            while (rs.next()) {
                dtm.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getInt("purchaseNumber"),
                    rs.getString("supplierName"),
                    rs.getString("receiverName"),
                    rs.getDouble("remainedBefor"),
                    rs.getDouble("payed"),
                    rs.getDouble("remainedAfter"),
                    rs.getString("operationdetails"),
                    rs.getString("date")
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void getAllPaymentWithsupplierName(String supplierName, String date) {
        try {
            DefaultTableModel dtm = (DefaultTableModel) jTable2.getModel();
            dtm.setRowCount(0);
            ResultSet rs = dbOperations.getData("select * from payoperationforsuppliers where supplierName='" + supplierName + "' and date like'" + date + "%'");
            while (rs.next()) {
                dtm.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getInt("purchaseNumber"),
                    rs.getString("supplierName"),
                    rs.getString("receiverName"),
                    rs.getDouble("remainedBefor"),
                    rs.getDouble("payed"),
                    rs.getDouble("remainedAfter"),
                    rs.getString("operationdetails"),
                    rs.getString("date")
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void getAllRecords() {
        try {
            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            dtm.setRowCount(0);
            ArrayList<suppliersModel> list = suppliersController.getAllsuppliersModelRecords();
            Iterator<suppliersModel> itr = list.iterator();
            while (itr.hasNext()) {
                suppliersModel cus = itr.next();
                dtm.addRow(new Object[]{
                    cus.getSupId(),
                    cus.getSupplierName(),
                    cus.getPhone(),
                    cus.getAddress(),
                    cus.getRemained()
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void getAllRecordsWithname(String name) {
        try {
            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            dtm.setRowCount(0);
            ArrayList<suppliersModel> list = suppliersController.getAllsuppliersModelBysupplierName(name);
            Iterator<suppliersModel> itr = list.iterator();
            while (itr.hasNext()) {
                suppliersModel cus = itr.next();
                dtm.addRow(new Object[]{
                    cus.getSupId(),
                    cus.getSupplierName(),
                    cus.getPhone(),
                    cus.getAddress(),
                    cus.getRemained()
                });
                txtID.setText(String.valueOf(cus.getSupId()));
                txtSupplierName.setText(cus.getSupplierName());
                txtPhone.setText(cus.getPhone());
                txtAddress.setText(cus.getAddress());
                txtAllRemained.setText(String.valueOf(cus.getRemained()));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void getAllRecordsWithphone(String phone) {
        try {
            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            dtm.setRowCount(0);
            ArrayList<suppliersModel> list = suppliersController.getAllsuppliersModelBysupplierPhone(phone);
            Iterator<suppliersModel> itr = list.iterator();
            while (itr.hasNext()) {
                suppliersModel cus = itr.next();
                dtm.addRow(new Object[]{
                    cus.getSupId(),
                    cus.getSupplierName(),
                    cus.getPhone(),
                    cus.getAddress(),
                    cus.getRemained()
                });
                txtID.setText(String.valueOf(cus.getSupId()));
                txtSupplierName.setText(cus.getSupplierName());
                txtPhone.setText(cus.getPhone());
                txtAddress.setText(cus.getAddress());
                txtAllRemained.setText(String.valueOf(cus.getRemained()));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtSupplierName = new javax.swing.JTextField();
        txtPhone = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtAddress = new javax.swing.JTextField();
        btnSave = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtID = new javax.swing.JLabel();
        btnSearch1 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtAllRemained = new javax.swing.JTextField();
        btnVerify = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtTodayDate = new javax.swing.JLabel();
        txtTOTAL = new javax.swing.JLabel();
        btnSave1 = new javax.swing.JButton();
        txtHandDateSearch = new com.toedter.calendar.JDateChooser();
        jButton1 = new javax.swing.JButton();
        txtPurchaseNumber = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtPayNumber = new javax.swing.JTextField();
        txtSupplierNameSearch = new javax.swing.JComboBox<>();
        txtMonth = new javax.swing.JComboBox<>();
        txtYear = new javax.swing.JComboBox<>();

        jPanel2.setBackground(new java.awt.Color(0, 153, 153));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(0, 153, 153));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("اسم المورد:");

        txtSupplierName.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtSupplierName.setForeground(new java.awt.Color(0, 0, 0));
        txtSupplierName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSupplierNameActionPerformed(evt);
            }
        });

        txtPhone.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtPhone.setForeground(new java.awt.Color(0, 0, 0));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("الرقم:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("العنوان:");

        txtAddress.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtAddress.setForeground(new java.awt.Color(0, 0, 0));

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

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("ID:");

        txtID.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtID.setForeground(new java.awt.Color(0, 0, 0));
        txtID.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtID.setText("0");

        btnSearch1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSearch1.setForeground(new java.awt.Color(0, 0, 0));
        btnSearch1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/search.png"))); // NOI18N
        btnSearch1.setText("بحث");
        btnSearch1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSearch1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearch1ActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("المتبقي:");

        txtAllRemained.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtAllRemained.setForeground(new java.awt.Color(0, 0, 0));
        txtAllRemained.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAllRemainedActionPerformed(evt);
            }
        });
        txtAllRemained.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtAllRemainedKeyReleased(evt);
            }
        });

        btnVerify.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnVerify.setForeground(new java.awt.Color(0, 0, 0));
        btnVerify.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/verify users.png"))); // NOI18N
        btnVerify.setText("تفعيل");
        btnVerify.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVerify.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerifyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnSearch1)
                            .addComponent(btnVerify, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(txtSupplierName, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel1))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(txtAllRemained, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel6))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2)
                                .addGap(50, 50, 50))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnSave)
                        .addGap(33, 33, 33)
                        .addComponent(btnUpdate)
                        .addGap(37, 37, 37)
                        .addComponent(btnDelete)
                        .addGap(29, 29, 29)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtID))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSupplierName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(btnSearch1))
                .addGap(2, 2, 2)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAllRemained, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(btnVerify))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave)
                    .addComponent(btnUpdate)
                    .addComponent(btnDelete))
                .addContainerGap(80, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 76, -1, -1));

        jTable1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "اسم العميل", "الرقم", "العنوان", "المتبقي"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 76, 1041, 322));
        jPanel2.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 439, 1521, 10));

        jTable2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "رقم الدفع", "رقم الطلب", "اسم المورد", "المستلم", "المتبقي قبل", "تم دفع", "المتبقي بعد", "تفاصيل", "التاريخ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 507, 1509, 328));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("TOTAL:");

        txtTodayDate.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtTodayDate.setForeground(new java.awt.Color(0, 0, 0));
        txtTodayDate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        txtTOTAL.setBackground(new java.awt.Color(0, 204, 204));
        txtTOTAL.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtTOTAL.setForeground(new java.awt.Color(0, 0, 0));
        txtTOTAL.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtTOTAL.setText("0.0");
        txtTOTAL.setOpaque(true);

        btnSave1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSave1.setForeground(new java.awt.Color(0, 0, 0));
        btnSave1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/reports.png"))); // NOI18N
        btnSave1.setText("تقرير");
        btnSave1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSave1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSave1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtTodayDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTOTAL, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(159, 159, 159)
                .addComponent(btnSave1, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtTOTAL))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTodayDate, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
            .addComponent(btnSave1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 843, 1497, -1));

        txtHandDateSearch.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtHandDateSearch.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtHandDateSearchFocusGained(evt);
            }
        });
        txtHandDateSearch.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtHandDateSearchPropertyChange(evt);
            }
        });
        jPanel2.add(txtHandDateSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 467, 221, 33));

        jButton1.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/search x30.png"))); // NOI18N
        jButton1.setText("بحث");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(239, 467, -1, -1));

        txtPurchaseNumber.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtPurchaseNumber.setForeground(new java.awt.Color(0, 0, 0));
        txtPurchaseNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPurchaseNumberActionPerformed(evt);
            }
        });
        txtPurchaseNumber.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPurchaseNumberKeyReleased(evt);
            }
        });
        jPanel2.add(txtPurchaseNumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(1262, 464, 121, 35));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("رقم الطلب:");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(1386, 465, 123, 33));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("رقم الدفع:");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(1168, 464, -1, 33));

        txtPayNumber.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtPayNumber.setForeground(new java.awt.Color(0, 0, 0));
        txtPayNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPayNumberActionPerformed(evt);
            }
        });
        txtPayNumber.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPayNumberKeyReleased(evt);
            }
        });
        jPanel2.add(txtPayNumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(1044, 464, 121, 35));

        txtSupplierNameSearch.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtSupplierNameSearch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "اسم المورد", " " }));
        txtSupplierNameSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSupplierNameSearchActionPerformed(evt);
            }
        });
        jPanel2.add(txtSupplierNameSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(797, 464, 221, -1));

        txtMonth.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtMonth.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "شهر", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        txtMonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMonthActionPerformed(evt);
            }
        });
        jPanel2.add(txtMonth, new org.netbeans.lib.awtextra.AbsoluteConstraints(404, 466, 117, -1));

        txtYear.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtYear.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030" }));
        txtYear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtYearActionPerformed(evt);
            }
        });
        jPanel2.add(txtYear, new org.netbeans.lib.awtextra.AbsoluteConstraints(555, 466, 117, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtSupplierNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSupplierNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSupplierNameActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        try {
            ResultSet rs=dbOperations.getData("select * from suppliers where supplierName='"+txtSupplierName.getText()+"'");
            if(rs.next())
            {
                JOptionPane.showMessageDialog(null,"<html><h1>هذا الاسم موجود مسبقا</h1></html>");
            }else 
            {
                suppliersModel cus = new suppliersModel();
            cus.setSupplierName(txtSupplierName.getText());
            cus.setPhone(txtPhone.getText());
            cus.setAddress(txtAddress.getText());
            if (txtAllRemained.isEnabled()) {
                cus.setRemained(Double.valueOf(txtAllRemained.getText()));
            } else {
                cus.setRemained(0);
            }
            suppliersController.addsupplier(cus);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        getAllRecords();
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        try {
            suppliersModel cus = new suppliersModel();
            cus.setSupId(Integer.valueOf(txtID.getText()));
            cus.setSupplierName(txtSupplierName.getText());
            cus.setPhone(txtPhone.getText());
            cus.setAddress(txtAddress.getText());
            cus.setRemained(Double.valueOf(txtAllRemained.getText()));
            suppliersController.updatesupplier(cus);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        getAllRecords();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        int id = Integer.parseInt(txtID.getText());
        suppliersController.deletesupplier(id);
        getAllRecords();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnSearch1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearch1ActionPerformed
        getAllRecordsWithphone(txtPhone.getText());
        if (!txtID.getText().equals("0")) {
            btnUpdate.setEnabled(true);
        }
    }//GEN-LAST:event_btnSearch1ActionPerformed

    private void txtAllRemainedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAllRemainedActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAllRemainedActionPerformed

    private void txtAllRemainedKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAllRemainedKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAllRemainedKeyReleased

    private void btnVerifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerifyActionPerformed
        try {
            if (verify) {
                verify = false;
                txtAllRemained.setEnabled(true);
                btnVerify.setText("تعطيل");
                btnVerify.setIcon(new ImageIcon("src/Images/small Close.png"));
            } else {
                verify = true;
                txtAllRemained.setEnabled(false);
                btnVerify.setText("تفعيل");
                btnVerify.setIcon(new ImageIcon("src/Images/verify users.png"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_btnVerifyActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        try {
            int index = jTable1.getSelectedRow();
            TableModel tm = jTable1.getModel();
            txtID.setText(tm.getValueAt(index, 0).toString());
            txtSupplierName.setText(tm.getValueAt(index, 1).toString());
            txtPhone.setText(tm.getValueAt(index, 2).toString());
            txtAddress.setText(tm.getValueAt(index, 3).toString());
            txtAllRemained.setText(tm.getValueAt(index, 4).toString());
            btnUpdate.setEnabled(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        try {
            int index = jTable2.getSelectedRow();
            int a = JOptionPane.showConfirmDialog(null, "<html><h1>هل تريد عرض التفاصيل</h1></html>", "", JOptionPane.YES_NO_OPTION);
            if (a == 0) {
                HashMap para = new HashMap();
                para.put("Inv_para", Integer.parseInt(jTable2.getValueAt(index, 0).toString()));  // inv_id  is ireport parameter name
                ReportView r = new ReportView("src\\reports\\paySuppliersArrival.jasper", para);
                r.view();
                try {
                new barcodeGenerator(jTable2.getValueAt(index,1).toString()).generate();
                HashMap para1 = new HashMap();
                para1.put("Inv_para", Integer.parseInt(jTable2.getValueAt(index,1).toString()));  // inv_id  is ireport parameter name
                ReportView r1 = new ReportView("src\\reports\\purchaseLargeReport.jasper", para1);
                r1.view();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
            }
             
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jTable2MouseClicked

    private void txtHandDateSearchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtHandDateSearchFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHandDateSearchFocusGained

    private void txtHandDateSearchPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtHandDateSearchPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHandDateSearchPropertyChange

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            getAllPaymentRecords(df.format(txtHandDateSearch.getDate()));
            getAllTotal();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtPurchaseNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPurchaseNumberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPurchaseNumberActionPerformed

    private void txtPurchaseNumberKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPurchaseNumberKeyReleased
        try {
            int i = Integer.parseInt(txtPurchaseNumber.getText());
            getAllPaymentRecordsPurchaseNumber("" + i);
            txtPurchaseNumber.setForeground(new Color(0, 0, 255));
            getAllTotal();
        } catch (NumberFormatException txt) {
            txtPurchaseNumber.setForeground(new Color(255, 0, 0));
            getAllPaymentRecords(txtTodayDate.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_txtPurchaseNumberKeyReleased

    private void txtPayNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPayNumberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPayNumberActionPerformed

    private void txtPayNumberKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPayNumberKeyReleased
        try {
            int a = Integer.parseInt(txtPayNumber.getText());
            getAllPaymentRecordsPayNumber(txtPayNumber.getText());
            getAllTotal();
            txtPayNumber.setForeground(new Color(0, 0, 255));
        } catch (NumberFormatException es) {
            txtPayNumber.setForeground(new Color(255, 0, 0));
            getAllPaymentRecords(txtTodayDate.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_txtPayNumberKeyReleased

    private void txtSupplierNameSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSupplierNameSearchActionPerformed
        try {
            if (txtHandDateSearch.getDate() != null) {
                getAllPaymentWithsupplierName(txtSupplierNameSearch.getSelectedItem().toString(), df.format(txtHandDateSearch.getDate()) + "____________");
            } else if (!txtMonth.getSelectedItem().toString().equals("شهر")) {
                txtHandDateSearch.setDate(null);
                getAllPaymentWithsupplierName(txtSupplierNameSearch.getSelectedItem().toString(), txtYear.getSelectedItem().toString() + "-" + txtMonth.getSelectedItem().toString() + "_______________");
            }
            getAllTotal();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_txtSupplierNameSearchActionPerformed

    private void txtMonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMonthActionPerformed
        getAllPaymentRecords(txtYear.getSelectedItem().toString() + "-" + txtMonth.getSelectedItem().toString() + "_______________");
        getAllTotal();
    }//GEN-LAST:event_txtMonthActionPerformed

    private void txtYearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtYearActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtYearActionPerformed

    private void btnSave1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSave1ActionPerformed
        try {
            double Totl = 0;
            HashMap para = new HashMap();
            ReportView r = null;
            DefaultTableModel dtm = (DefaultTableModel) jTable2.getModel();
            for (int i = 0; i < dtm.getRowCount(); i++) {
                Totl += Double.valueOf(dtm.getValueAt(i, 5).toString());
            }

            if (!txtSupplierNameSearch.getSelectedItem().toString().equals("اسم المورد")) {
                para.put("TITLE", txtSupplierNameSearch.getSelectedItem().toString());  // inv_id  is ireport parameter name
                para.put("TOTAL", "" + Totl);
                r = new ReportView("src\\reports\\paySuppliersRep.jasper", para);
                r.view3(dtm, "src\\reports\\paySuppliersRep.jrxml");
            } else if (txtHandDateSearch.getDate() != null) {
                para.put("TITLE", df.format(txtHandDateSearch.getDate()));
                para.put("TOTAL", "" + Totl);
                r = new ReportView("src\\reports\\paySuppliersRep.jasper", para);
                r.view3(dtm, "src\\reports\\paySuppliersRep.jrxml");
            } else if (txtMonth.getSelectedIndex() != 0) {
                para.put("TITLE", txtYear.getSelectedItem().toString() + "-" + txtMonth.getSelectedItem().toString());
                para.put("TOTAL", "" + Totl);
                r = new ReportView("src\\reports\\paySuppliersRep.jasper", para);
                r.view3(dtm, "src\\reports\\paySuppliersRep.jrxml");
            } else if (txtPurchaseNumber.getText().length() != 0) {
                para.put("TITLE", "تقرير رقم الطلب: " + txtPurchaseNumber.getText());
                para.put("TOTAL", "" + Totl);
                r = new ReportView("src\\reports\\paySuppliersRep.jasper", para);
                r.view3(dtm, "src\\reports\\paySuppliersRep.jrxml");
            } else {
                para.put("TITLE", MonthDate);
                para.put("TOTAL", "" + Totl);
                r = new ReportView("src\\reports\\paySuppliersRep.jasper", para);
                r.view3(dtm, "src\\reports\\paySuppliersRep.jrxml");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_btnSave1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSave1;
    private javax.swing.JButton btnSearch1;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton btnVerify;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtAllRemained;
    private com.toedter.calendar.JDateChooser txtHandDateSearch;
    private javax.swing.JLabel txtID;
    private javax.swing.JComboBox<String> txtMonth;
    private javax.swing.JTextField txtPayNumber;
    private javax.swing.JTextField txtPhone;
    private javax.swing.JTextField txtPurchaseNumber;
    private javax.swing.JTextField txtSupplierName;
    private javax.swing.JComboBox<String> txtSupplierNameSearch;
    private javax.swing.JLabel txtTOTAL;
    private javax.swing.JLabel txtTodayDate;
    private javax.swing.JComboBox<String> txtYear;
    // End of variables declaration//GEN-END:variables
}
