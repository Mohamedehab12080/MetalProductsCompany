/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Database.dbOperations;
import TOOLS.TableCustom;
import controller.employeeController;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.TimeZone;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import model.employeeModel;

/**
 *
 * @author BOBO
 */
public class employeeView extends javax.swing.JPanel {

    double globalSalary;
    String todayDate;
    String MonthDate;
        SimpleDateFormat df;
        SimpleDateFormat df2;
        SimpleDateFormat timeFormat;
        Timer t;
    public employeeView() {
        initComponents();
        df = new SimpleDateFormat("yyyy-MM-dd", new Locale("en"));
        df2=new SimpleDateFormat("yyyy-MM", new Locale("en"));
        Date date = new Date();
        todayDate = df.format(date);
        MonthDate=df2.format(date);
        getLiveTime();
        TOOLS.TableCustom.apply(jScrollPane2, TableCustom.TableType.MULTI_LINE);
        TOOLS.TableCustom.apply(jScrollPane1, TableCustom.TableType.MULTI_LINE);
        getAllRecordsOpen  ();
        getAllEmpRecords();
        getNames();
        txtMonthDateNow.setText(MonthDate);
        apsentDays.setEnabled(false);
        btnUpdate.setEnabled(false);
    }

    public employeeView(String CasherName) {
        initComponents();
        
        df = new SimpleDateFormat("yyyy-MM-dd", new Locale("en"));
        df2=new SimpleDateFormat("yyyy-MM", new Locale("en"));
        Date date = new Date();
        todayDate = df.format(date);
        MonthDate=df2.format(date);
        getLiveTime();
        TOOLS.TableCustom.apply(jScrollPane2, TableCustom.TableType.MULTI_LINE);
        TOOLS.TableCustom.apply(jScrollPane1, TableCustom.TableType.MULTI_LINE);
        getAllRecordsOpen();
        getAllEmpRecords();
        getNames();
        txtMonthDateNow.setText(MonthDate);
        apsentDays.setEnabled(false);
       
                btnUpdate.setEnabled(false);
    }
    
    public void getNames ()
    {
        try {
            ResultSet rs =dbOperations.getData("select distinct employeeName from employees");
            while(rs.next())
            {
                txtEmployeeName.addItem(rs.getString("employeeName"));
                txtEmployeeNameSearch.addItem(rs.getString("employeeName"));
            }
        } catch (Exception e) {
        }
    }
    public void getLiveTime() {
        TimeZone.setDefault(TimeZone.getTimeZone("Cairo"));
        t = new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date dt = new Date();
                timeFormat = new SimpleDateFormat("hh:mm:ss a", new Locale("en"));
                String tt = timeFormat.format(dt);
                txtDate.setText(todayDate + " " + tt);
            }
        });
        t.start();
    }

    
    public void getAllEmpRecords()
    {
           try {
            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            dtm.setRowCount(0);
            ResultSet rs = dbOperations.getData("select * from employees");
            while (rs.next()) {
                dtm.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("employeeName"),
                    rs.getString("empType"),
                    rs.getString("phone"),
                    rs.getString("Address"),
                    rs.getDouble("salary"),
                    rs.getDouble("remainedSalary"),
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    public void getAllRecordsOpen() {
        try {
            DefaultTableModel dtm = (DefaultTableModel) jTable2.getModel();
            dtm.setRowCount(0);
            ResultSet rs = dbOperations.getData("select * from salary where date like'"+MonthDate+"_______________%'");
            while (rs.next()) {
                dtm.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("employeeName"),
                    rs.getString("empType"),
                    rs.getDouble("Taked"),
                    rs.getString("operationType"),
                    rs.getDouble("remainedSalary"),
                    rs.getString("Note"),
                    rs.getString("date"),
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
            ResultSet rs = dbOperations.getData("select * from employees");
            while (rs.next()) {
                dtm.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("employeeName"),
                    rs.getString("empType"),
                    rs.getString("phone"),
                    rs.getString("Address"),
                    rs.getDouble("Salary")
                });
            }
        } catch (Exception e) {
        }
    }
     public void getAllRecordsWithNumber(String phone) {
        try {
            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            dtm.setRowCount(0);
            ResultSet rs = dbOperations.getData("select * from employees where phone='"+phone+"'");
            while (rs.next()) {
                dtm.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("employeeName"),
                    rs.getString("empType"),
                    rs.getString("phone"),
                    rs.getString("Address"),
                    rs.getDouble("Salary")
                }
                );
                txtID.setText(rs.getInt("id")+"");
                txtEmpName.setText(rs.getString("employeeName"));
                txtEmpType.setText(rs.getString("empType"));
               txtAddress.setText(rs.getString("Address"));
               txtSalary.setText("Salary");
            }
        } catch (Exception e) {
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtEmpName = new javax.swing.JTextField();
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
        txtSalary = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtEmpType = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        btnSave1 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        txtId = new javax.swing.JLabel();
        txtOperationTypeVar = new javax.swing.JLabel();
        txtTaked = new javax.swing.JTextField();
        txtEmployeeName = new javax.swing.JComboBox<>();
        txtDate = new javax.swing.JLabel();
        txtHandDate = new com.toedter.calendar.JDateChooser();
        jLabel13 = new javax.swing.JLabel();
        txtNote = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        apsentDays = new javax.swing.JTextField();
        txtOperation = new javax.swing.JComboBox<>();
        txtAllRemianed = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel14 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        txtHandDateSaerch = new com.toedter.calendar.JDateChooser();
        txtMonth = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtYear = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        txtEmployeeNameSearch = new javax.swing.JComboBox<>();
        btnSearchDayDate = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txtTotalTaked = new javax.swing.JLabel();
        txtRemainedSalaryNow = new javax.swing.JLabel();
        txtMonthDateNow = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        btnPrintReport = new javax.swing.JButton();
        txtEmpTypeSalary = new javax.swing.JLabel();

        jTabbedPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTabbedPane1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N

        jPanel1.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jPanel1formComponentShown(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(0, 153, 153));

        jPanel3.setBackground(new java.awt.Color(0, 153, 153));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("اسم الموظف:");

        txtEmpName.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtEmpName.setForeground(new java.awt.Color(0, 0, 0));
        txtEmpName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmpNameActionPerformed(evt);
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
        jLabel6.setText("المرتب:");

        txtSalary.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtSalary.setForeground(new java.awt.Color(0, 0, 0));
        txtSalary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSalaryActionPerformed(evt);
            }
        });
        txtSalary.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSalaryKeyReleased(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("طبيعة العمل:");

        txtEmpType.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtEmpType.setForeground(new java.awt.Color(0, 0, 0));
        txtEmpType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmpTypeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnSearch1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(txtSalary, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel6))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtEmpName, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtEmpType, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel1)))))
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
                    .addComponent(txtEmpName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtEmpType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
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
                    .addComponent(txtSalary, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave)
                    .addComponent(btnUpdate)
                    .addComponent(btnDelete))
                .addGap(45, 45, 45))
        );

        jTable1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "اسم الموظف", "طبيعة العمل", "الرقم", "العنوان", "المرتب", "المتبقي"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
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

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/customer.png"))); // NOI18N
        jLabel4.setText("الموظفين");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1229, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(588, 588, 588)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jLabel4)
                .addGap(76, 76, 76)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(371, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("اضافة موظف", jPanel1);

        jPanel5.setBackground(new java.awt.Color(0, 153, 153));

        jPanel6.setBackground(new java.awt.Color(0, 153, 153));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("اسم الموظف:");
        jPanel6.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(351, 47, -1, -1));

        btnSave1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSave1.setForeground(new java.awt.Color(0, 0, 0));
        btnSave1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/save.png"))); // NOI18N
        btnSave1.setText("حفظ");
        btnSave1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSave1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSave1ActionPerformed(evt);
            }
        });
        jPanel6.add(btnSave1, new org.netbeans.lib.awtextra.AbsoluteConstraints(136, 297, 142, 46));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("ID:");
        jPanel6.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 15, -1, -1));

        txtId.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtId.setForeground(new java.awt.Color(0, 0, 0));
        txtId.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtId.setText("0");
        jPanel6.add(txtId, new org.netbeans.lib.awtextra.AbsoluteConstraints(149, 15, 54, -1));

        txtOperationTypeVar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtOperationTypeVar.setForeground(new java.awt.Color(0, 0, 0));
        txtOperationTypeVar.setText("_");
        jPanel6.add(txtOperationTypeVar, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 122, 48, -1));

        txtTaked.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtTaked.setForeground(new java.awt.Color(0, 0, 0));
        txtTaked.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTakedActionPerformed(evt);
            }
        });
        txtTaked.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTakedKeyReleased(evt);
            }
        });
        jPanel6.add(txtTaked, new org.netbeans.lib.awtextra.AbsoluteConstraints(124, 120, 214, -1));

        txtEmployeeName.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtEmployeeName.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", " " }));
        txtEmployeeName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmployeeNameActionPerformed(evt);
            }
        });
        jPanel6.add(txtEmployeeName, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 47, 214, -1));

        txtDate.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtDate.setForeground(new java.awt.Color(0, 0, 0));
        jPanel6.add(txtDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(124, 155, 214, 24));

        txtHandDate.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtHandDate.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtHandDateFocusGained(evt);
            }
        });
        txtHandDate.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtHandDatePropertyChange(evt);
            }
        });
        jPanel6.add(txtHandDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(124, 185, 214, 33));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("ملاحظة:");
        jPanel6.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(349, 258, -1, -1));

        txtNote.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtNote.setForeground(new java.awt.Color(0, 0, 0));
        txtNote.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNoteActionPerformed(evt);
            }
        });
        txtNote.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNoteKeyReleased(evt);
            }
        });
        jPanel6.add(txtNote, new org.netbeans.lib.awtextra.AbsoluteConstraints(124, 256, 213, -1));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("ايام الغياب:");
        jPanel6.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(349, 223, -1, -1));

        apsentDays.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        apsentDays.setForeground(new java.awt.Color(0, 0, 0));
        apsentDays.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apsentDaysActionPerformed(evt);
            }
        });
        apsentDays.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                apsentDaysKeyReleased(evt);
            }
        });
        jPanel6.add(apsentDays, new org.netbeans.lib.awtextra.AbsoluteConstraints(124, 221, 213, -1));

        txtOperation.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtOperation.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "عملية", "قبض", "سحب", " " }));
        txtOperation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtOperationActionPerformed(evt);
            }
        });
        jPanel6.add(txtOperation, new org.netbeans.lib.awtextra.AbsoluteConstraints(123, 83, 215, 25));

        txtAllRemianed.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtAllRemianed.setForeground(new java.awt.Color(0, 0, 0));
        txtAllRemianed.setText("0.0");
        jPanel6.add(txtAllRemianed, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 80, 100, 30));

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("عملية:");
        jPanel6.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 81, -1, -1));

        jTable2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "اسم الموظف", "طبيعة العمل", "المسحوب", "عملية", "المتبقي من المرتب", "ملاحظة", "تاريخ السحب"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
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

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/salary.png"))); // NOI18N
        jLabel14.setText("القبض");

        txtHandDateSaerch.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtHandDateSaerch.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtHandDateSaerchFocusGained(evt);
            }
        });
        txtHandDateSaerch.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtHandDateSaerchPropertyChange(evt);
            }
        });

        txtMonth.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtMonth.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", " " }));
        txtMonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMonthActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("شهر:");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("سنة:");

        txtYear.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtYear.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2023", "2024", "2025", "2026", "2027", "2030" }));
        txtYear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtYearActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("اسم الموظف:");

        txtEmployeeNameSearch.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtEmployeeNameSearch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", " " }));
        txtEmployeeNameSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmployeeNameSearchActionPerformed(evt);
            }
        });

        btnSearchDayDate.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnSearchDayDate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/search x30.png"))); // NOI18N
        btnSearchDayDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchDayDateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(229, 229, 229)
                .addComponent(btnSearchDayDate)
                .addGap(26, 26, 26)
                .addComponent(txtMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtYear, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtEmployeeNameSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel9)
                .addContainerGap())
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel7Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(txtHandDateSaerch, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(677, Short.MAX_VALUE)))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSearchDayDate)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtEmployeeNameSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15)
                            .addComponent(txtYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16))))
                .addContainerGap(32, Short.MAX_VALUE))
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel7Layout.createSequentialGroup()
                    .addGap(32, 32, 32)
                    .addComponent(txtHandDateSaerch, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(33, Short.MAX_VALUE)))
        );

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel10.setText("توتال المسحوب:");

        jLabel17.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel17.setText("المرتب الحالي:");

        txtTotalTaked.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        txtTotalTaked.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtTotalTaked.setText("0.0");

        txtRemainedSalaryNow.setBackground(new java.awt.Color(0, 153, 153));
        txtRemainedSalaryNow.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        txtRemainedSalaryNow.setForeground(new java.awt.Color(255, 255, 255));
        txtRemainedSalaryNow.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtRemainedSalaryNow.setText("0.0");
        txtRemainedSalaryNow.setOpaque(true);

        txtMonthDateNow.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        txtMonthDateNow.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel21.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("الشهر الحالي:");

        btnPrintReport.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnPrintReport.setForeground(new java.awt.Color(0, 0, 0));
        btnPrintReport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/reports.png"))); // NOI18N
        btnPrintReport.setText("تقرير");
        btnPrintReport.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPrintReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintReportActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(txtMonthDateNow, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(114, 114, 114)
                .addComponent(btnPrintReport, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 309, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTotalTaked, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtRemainedSalaryNow, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addComponent(jLabel10))
                .addGap(24, 24, 24))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel10)
                                    .addComponent(txtTotalTaked))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel17)
                                    .addComponent(txtRemainedSalaryNow)
                                    .addComponent(txtMonthDateNow, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 24, Short.MAX_VALUE))
                    .addComponent(btnPrintReport, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        txtEmpTypeSalary.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtEmpTypeSalary.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(649, 649, 649)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(200, 200, 200)
                .addComponent(txtEmpTypeSalary, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(486, 486, 486)
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(txtEmpTypeSalary, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(173, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("القبض", jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1756, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 856, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtEmpNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmpNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmpNameActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        try {
            ResultSet rs=dbOperations.getData("select * from employees where employeeName='"+txtEmpName.getText()+"'");
            if(rs.next())
            {
                JOptionPane.showMessageDialog(null,"<html><h1>هذا الاسم موجود مسبقا</h1></html>");
            }else 
            {
            employeeModel emp = new employeeModel();
            emp.setEmployeeName(txtEmpName.getText());
            emp.setEmpType(txtEmpType.getText());
            emp.setPhone(txtPhone.getText());
            emp.setAddress(txtAddress.getText());
            emp.setSalary(Double.valueOf(txtSalary.getText()));
            emp.setRemainedSalary(Double.valueOf(txtSalary.getText()));
            employeeController.addEmployee(emp);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        getAllEmpRecords();
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        try {
            employeeModel emp = new employeeModel();
            emp.setId(Integer.valueOf(txtID.getText()));
            emp.setEmployeeName(txtEmpName.getText());
            emp.setEmpType(txtEmpType.getText());
            emp.setPhone(txtPhone.getText());
            emp.setAddress(txtAddress.getText());
            emp.setSalary(Double.valueOf(txtSalary.getText()));
            employeeController.updateEmployee(emp);
            txtID.setText("0");
            txtEmpName.setText("");
            txtAddress.setText("");
            txtPhone.setText("");
            txtSalary.setText("");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
         getAllEmpRecords();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        try {
            dbOperations.setDataOrDelete("delete from employees where id=" + txtID.getText(), "تم الحذف");
             getAllEmpRecords();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnSearch1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearch1ActionPerformed
         try {
            if(txtPhone.getText().length()>10)
            {
                getAllRecordsWithNumber(txtPhone.getText());
                btnUpdate.setEnabled(true);
            }else 
            {
                JOptionPane.showMessageDialog(null,"الرقم ناقص");
                btnUpdate.setEnabled(false);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
    }//GEN-LAST:event_btnSearch1ActionPerformed

    private void txtSalaryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSalaryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSalaryActionPerformed

    private void txtSalaryKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSalaryKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSalaryKeyReleased

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        try {
            int index = jTable1.getSelectedRow();
            TableModel tm = jTable1.getModel();
            txtID.setText(tm.getValueAt(index, 0).toString());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jPanel1formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanel1formComponentShown
        //       getAllRecords();
    }//GEN-LAST:event_jPanel1formComponentShown

    private void txtEmpTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmpTypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmpTypeActionPerformed

    private void btnSave1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSave1ActionPerformed

              btnSave1.setEnabled(false);
            if(!txtTaked.getText().equals("0.0"))
            {
            try {
                                   double Taked = Double.parseDouble(txtTaked.getText());
            if (txtOperation.getSelectedItem().toString().equals("قبض")) {
                ResultSet rs = dbOperations.getData("select * from employees where employeeName='" + txtEmployeeName.getSelectedItem().toString() + "'");
                if (rs.next()) {
                    
                    if(rs.getDouble("remainedSalary")<Double.valueOf(txtTaked.getText()))
                    {
                       JOptionPane.showConfirmDialog(null,"<html><h1>المسحوب اكبر من  المتبقي!!</h1></html>","تحذير",JOptionPane.WARNING_MESSAGE);
                    }else 
                    {
                          dbOperations.setDataOrDelete("insert into salary "
                            + "(employeeName,empType,Taked,remainedSalary,Note,date,operationType) "
                            + "values('"
                                  + txtEmployeeName.getSelectedItem().toString() 
                                  + "','" + txtEmpTypeSalary.getText() + "'," 
                                  + txtTaked.getText() + ",0,'" 
                                  + txtNote.getText() + "','" 
                                  + txtDate.getText() + "','قبض')", "");
                    ResultSet rs1= dbOperations.getData("select * from capenat where id=(select max(id) from capenat)");
                    if (rs1.next()) {
                        dbOperations.setDataOrDelete("insert into capenat (realValue,editedvalue,afterEdictvalue,OperationType,date) "
                                + "values(" + rs1.getDouble("afterEdictvalue") + "," +txtTaked.getText() + "," + (rs1.getDouble("afterEdictvalue")-Double.valueOf(txtTaked.getText())) + ",'"+"قبض مرتب : "+txtEmployeeName.getSelectedItem().toString()+"','" + txtDate.getText() + "')", "تم سحب المبلغ المدفوع من الخزنة");
                    }
                    dbOperations.setDataOrDelete("update employees set "
                            + "remainedSalary=" + String.valueOf(rs.getDouble("salary")) + " where employeeName='" + txtEmployeeName.getSelectedItem().toString() + "'", "");
                    }
                }
            } else {
                ResultSet rs = dbOperations.getData("select * from employees where employeeName='" + txtEmployeeName.getSelectedItem().toString() + "'");
                if (rs.next()) {
                    if(rs.getDouble("remainedSalary")<Double.valueOf(txtTaked.getText()))
                    {
                     JOptionPane.showConfirmDialog(null,"<html><h1>المسحوب اكبر من  المتبقي!!</h1></html>","تحذير",JOptionPane.WARNING_MESSAGE);
                    }else 
                    {
                             dbOperations.setDataOrDelete("insert into salary "
                            + "(employeeName,empType,Taked,remainedSalary,Note,date,operationType) "
                            + "values('" + txtEmployeeName.getSelectedItem().toString() 
                                     + "','" + txtEmpTypeSalary.getText() 
                                     + "'," + txtTaked.getText() 
                                     + ","+(rs.getDouble("remainedSalary")-Double.valueOf(txtTaked.getText()))
                                     +",'" + txtNote.getText() 
                                     + "','" + txtDate.getText() + "','سحب')", "");
                    ResultSet rs1= dbOperations.getData("select * from capenat where id=(select max(id) from capenat)");
                    if (rs1.next()) {
                        dbOperations.setDataOrDelete("insert into capenat (realValue,editedvalue,afterEdictvalue,OperationType,date) "
                                + "values(" + rs1.getDouble("afterEdictvalue") + "," +txtTaked.getText() + "," + (rs1.getDouble("afterEdictvalue")-Double.valueOf(txtTaked.getText())) + ",'"+"سحب مرتب: "+txtEmployeeName.getSelectedItem().toString()+"','" + txtDate.getText() + "')", "تم سحب المبلغ المدفوع من الخزنة");
                    }
                    dbOperations.setDataOrDelete("update employees set remainedSalary=" +(rs.getDouble("remainedSalary")-Double.valueOf(txtTaked.getText())) + " where employeeName='" + txtEmployeeName.getSelectedItem().toString() + "'", "");
                    }
                }
            }
            getAllRecordsOpen();
             try {
                 ResultSet rs=dbOperations.getData("select * from salary where employeeName='"+txtEmployeeName.getSelectedItem().toString()+"' order by id desc limit 1");
                 if(rs.next())
                 {
                       HashMap para = new HashMap();
                para.put("Inv_empName",rs.getInt("id"));  // inv_id  is ireport parameter name
               if(apsentDays.isEnabled())
               {
                    para.put("apsent_Days",apsentDays.getText() +" ايام غياب: ");
               }else 
               {
                    para.put("apsent_Days","__");
               }
                ReportView r = new ReportView("src\\reports\\salaryTakedReport.jasper", para);
                r.view();
                 }
              
            }
             catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e);
             }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
            }else 
            {
                JOptionPane.showMessageDialog(null, "<html><h1>يجب ادخال قيمة المسحوب</h1></html>");
            }
            txtEmpTypeSalary.setText("__");
            txtEmployeeName.setSelectedIndex(0);
            txtOperation.setSelectedItem("عملية");
            txtTaked.setText("");
            txtOperationTypeVar.setText("_");
            txtHandDate.setDate(null);
            apsentDays.setText("");
            apsentDays.setEnabled(false);
            txtNote.setText("_");
            btnSave1.setEnabled(true);
    }//GEN-LAST:event_btnSave1ActionPerformed

    private void txtTakedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTakedActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTakedActionPerformed

    private void txtTakedKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTakedKeyReleased
        try {
            double a = Double.parseDouble(txtTaked.getText());
        if(a>1 && txtEmployeeName.getSelectedIndex()!=0)
        {
             btnSave1.setEnabled(true);
             txtTaked.setForeground(new Color(0, 0, 255));
        }else 
        {
             btnSave1.setEnabled(false);
             txtTaked.setForeground(new Color(255, 0, 0));
        }
        } catch (NumberFormatException e) {
            txtTaked.setForeground(new Color(255, 0, 0));
            btnSave1.setEnabled(false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_txtTakedKeyReleased

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
     try {
         int index=jTable2.getSelectedRow();
         DefaultTableModel dtm=(DefaultTableModel) jTable2.getModel();
              int a=JOptionPane.showConfirmDialog(null,"هل تريد عرض التفاصيل؟","اختر",JOptionPane.YES_NO_OPTION);
              if(a==0)
              {
                HashMap para = new HashMap();
                para.put("Inv_empName",dtm.getValueAt(index,0).toString());  // inv_id  is ireport parameter name
               if(apsentDays.isEnabled())
               {
                    para.put("apsent_Days",apsentDays.getText() +" ايام غياب: ");
               }else 
               {
                    para.put("apsent_Days","__");
               }
                ReportView r = new ReportView("src\\reports\\salaryTakedReport.jasper", para);
                r.view();
              }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
    }//GEN-LAST:event_jTable2MouseClicked

    private void txtHandDateFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtHandDateFocusGained

    }//GEN-LAST:event_txtHandDateFocusGained

    private void txtHandDatePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtHandDatePropertyChange

    }//GEN-LAST:event_txtHandDatePropertyChange

    private void txtNoteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNoteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNoteActionPerformed

    private void txtNoteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNoteKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNoteKeyReleased

    private void txtHandDateSaerchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtHandDateSaerchFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHandDateSaerchFocusGained

    private void txtHandDateSaerchPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtHandDateSaerchPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHandDateSaerchPropertyChange

    private void txtYearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtYearActionPerformed
     
    }//GEN-LAST:event_txtYearActionPerformed

    private void txtEmployeeNameSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmployeeNameSearchActionPerformed
try {
           if(txtMonth.getSelectedIndex()!=0)
           {
               DefaultTableModel dtm=(DefaultTableModel) jTable2.getModel();
           dtm.setRowCount(0);
            ResultSet rs=dbOperations.getData("select * from salary where date like'"+(txtYear.getSelectedItem().toString()+"-"+txtMonth.getSelectedItem().toString())+"_______________%' and employeeName='"+txtEmployeeNameSearch.getSelectedItem().toString()+"'");
            while(rs.next())
            {
                dtm.addRow(new Object[]
                {
                    rs.getInt("id"),
                    rs.getString("employeeName"),
                    rs.getString("empType"),
                    rs.getDouble("Taked"),
                    rs.getString("operationType"),
                    rs.getDouble("remainedSalary"),
                    rs.getString("Note"),
                    rs.getString("date")
                });
            }
           }else 
           {
             DefaultTableModel dtm=(DefaultTableModel) jTable2.getModel();
           dtm.setRowCount(0);
            ResultSet rs=null;
            if(txtHandDateSaerch.getDate()!=null)
            {
                rs=dbOperations.getData("select * from salary where date like'"+(df.format(txtHandDateSaerch.getDate()))+"____________%' and employeeName='"+txtEmployeeNameSearch.getSelectedItem().toString()+"'");
            }else 
            {
                rs=dbOperations.getData("select * from salary where date like'"+txtMonthDateNow.getText()+"_______________%' and employeeName='"+txtEmployeeNameSearch.getSelectedItem().toString()+"'");
            }
            while(rs.next())
            {
                dtm.addRow(new Object[]
                {
                    rs.getInt("id"),
                    rs.getString("employeeName"),
                    rs.getString("empType"),
                    rs.getDouble("Taked"),
                    rs.getString("operationType"),
                    rs.getDouble("remainedSalary"),
                    rs.getString("Note"),
                    rs.getString("date")
                });
            }  
           }
           ResultSet rsss=dbOperations.getData("select * from employees where employeeName='"+txtEmployeeNameSearch.getSelectedItem().toString()+"'");
           if(rsss.next())
           {
               txtRemainedSalaryNow.setText(rsss.getDouble("remainedSalary")+"");
           }
            txtTotalTaked.setText(""+getTotalTaked());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }        
    }//GEN-LAST:event_txtEmployeeNameSearchActionPerformed

    private void btnPrintReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintReportActionPerformed
        try {
             HashMap para = new HashMap();
            if(txtEmployeeNameSearch.getSelectedIndex()!=0&&txtMonth.getSelectedIndex()!=0)
            {
                para.put("Inv_date",txtYear.getSelectedItem().toString()+"-"+txtMonth.getSelectedItem().toString()+"_______________%");  // inv_id  is ireport parameter name
                para.put("Inv_empname",txtEmployeeNameSearch.getSelectedItem().toString());
                para.put("Inv_dateView",txtYear.getSelectedItem().toString()+"-"+txtMonth.getSelectedItem().toString());
                ReportView r = new ReportView("src\\reports\\employeeSalaryReport.jasper", para);
                r.view();
            }else if(txtEmployeeNameSearch.getSelectedIndex()!=0&&txtHandDateSaerch.getDate()!=null)
            {
                para.put("Inv_date",df.format(txtHandDateSaerch.getDate())+"____________%");  // inv_id  is ireport parameter name
                para.put("Inv_empname",txtEmployeeNameSearch.getSelectedItem().toString());
                para.put("Inv_dateView",df.format(txtHandDateSaerch.getDate()));
                ReportView r = new ReportView("src\\reports\\employeeSalaryReport.jasper", para);
                r.view();
            }else
            {
                para.put("Inv_date",txtMonthDateNow.getText()+"_______________%");  // inv_id  is ireport parameter name
                para.put("Inv_empname",txtEmployeeNameSearch.getSelectedItem().toString());
                para.put("Inv_dateView",txtMonthDateNow.getText());
                ReportView r = new ReportView("src\\reports\\employeeSalaryReport.jasper", para);
                r.view();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog( null, e);
        }
    }//GEN-LAST:event_btnPrintReportActionPerformed

    private void apsentDaysActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_apsentDaysActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_apsentDaysActionPerformed

    private void apsentDaysKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_apsentDaysKeyReleased
        try {
            int a = Integer.parseInt(apsentDays.getText());
            ResultSet rs = dbOperations.getData("select salary from employees where employeeName='" + txtEmployeeName.getSelectedItem().toString() + "'");
            if (rs.next()) {
                txtTaked.setText("");
                txtTaked.setText( String.format("%.2f",(globalSalary - ((a / 30.0) * rs.getDouble("salary"))))+"");
            }
            btnSave.setEnabled(true);
        } catch (NumberFormatException e) {
            txtTaked.setText("" + globalSalary);
            btnSave.setEnabled(false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_apsentDaysKeyReleased

    private void txtOperationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtOperationActionPerformed
        try {
            txtOperationTypeVar.setText(txtOperation.getSelectedItem().toString());
            if (txtOperation.getSelectedItem().toString().equals("قبض")) {
                apsentDays.setEnabled(true);
                if (txtEmployeeName.getSelectedIndex() != 0) {
                    ResultSet rs = dbOperations.getData("select * from employees where employeeName='" + txtEmployeeName.getSelectedItem().toString() + "'");
                    if (rs.next()) {
                        txtTaked.setText(rs.getDouble("remainedSalary") + "");
                        globalSalary = rs.getDouble("remainedSalary");
                        txtEmpTypeSalary.setText(rs.getString("empType"));
                    }
                }
            } else {
                 if (txtEmployeeName.getSelectedIndex() != 0) {
                    ResultSet rs = dbOperations.getData("select * from employees where employeeName='" + txtEmployeeName.getSelectedItem().toString() + "'");
                    if (rs.next()) {
                        txtAllRemianed.setText(""+rs.getDouble("remainedSalary"));
                        globalSalary = rs.getDouble("remainedSalary");
                        txtEmpTypeSalary.setText(rs.getString("empType"));
                    }
                }
                txtTaked.setText("0.0");
                apsentDays.setEnabled(false);
                apsentDays.setText("");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_txtOperationActionPerformed

    private void txtEmployeeNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmployeeNameActionPerformed
        try {

        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtEmployeeNameActionPerformed

    public double getTotalTaked()
    {
        double taked=0;
        try {
            for (int i = 0; i <jTable2.getRowCount(); i++) {
                taked+=Double.valueOf(jTable2.getValueAt(i,3).toString());
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return taked;
    }
    private void btnSearchDayDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchDayDateActionPerformed
        try {
           DefaultTableModel dtm=(DefaultTableModel) jTable2.getModel();
           dtm.setRowCount(0);
            ResultSet rs=dbOperations.getData("select * from salary where date like'"+(df.format(txtHandDateSaerch.getDate())+"____________%'"));
            while(rs.next())
            {
                dtm.addRow(new Object[]
                {
                    rs.getInt("id"),
                    rs.getString("employeeName"),
                    rs.getString("empType"),
                    rs.getDouble("Taked"),
                    rs.getString("operationType"),
                    rs.getDouble("remainedSalary"),
                    rs.getString("Note"),
                    rs.getString("date")
                });
            }
           txtTotalTaked.setText( getTotalTaked()+"");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_btnSearchDayDateActionPerformed

    private void txtMonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMonthActionPerformed
       try {
           DefaultTableModel dtm=(DefaultTableModel) jTable2.getModel();
           dtm.setRowCount(0);
            ResultSet rs=dbOperations.getData("select * from salary where date like'"+(txtYear.getSelectedItem().toString()+"-"+txtMonth.getSelectedItem().toString())+"_______________%'");
            while(rs.next())
            {
                dtm.addRow(new Object[]
                {
                    rs.getInt("id"),
                    rs.getString("employeeName"),
                    rs.getString("empType"),
                    rs.getDouble("Taked"),
                    rs.getString("operationType"),
                    rs.getDouble("remainedSalary"),
                    rs.getString("Note"),
                    rs.getString("date")
                });
            }
            txtTotalTaked.setText(""+getTotalTaked());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_txtMonthActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField apsentDays;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnPrintReport;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSave1;
    private javax.swing.JButton btnSearch1;
    private javax.swing.JButton btnSearchDayDate;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JLabel txtAllRemianed;
    private javax.swing.JLabel txtDate;
    private javax.swing.JTextField txtEmpName;
    private javax.swing.JTextField txtEmpType;
    private javax.swing.JLabel txtEmpTypeSalary;
    private javax.swing.JComboBox<String> txtEmployeeName;
    private javax.swing.JComboBox<String> txtEmployeeNameSearch;
    private com.toedter.calendar.JDateChooser txtHandDate;
    private com.toedter.calendar.JDateChooser txtHandDateSaerch;
    private javax.swing.JLabel txtID;
    private javax.swing.JLabel txtId;
    private javax.swing.JComboBox<String> txtMonth;
    private javax.swing.JLabel txtMonthDateNow;
    private javax.swing.JTextField txtNote;
    private javax.swing.JComboBox<String> txtOperation;
    private javax.swing.JLabel txtOperationTypeVar;
    private javax.swing.JTextField txtPhone;
    private javax.swing.JLabel txtRemainedSalaryNow;
    private javax.swing.JTextField txtSalary;
    private javax.swing.JTextField txtTaked;
    private javax.swing.JLabel txtTotalTaked;
    private javax.swing.JComboBox<String> txtYear;
    // End of variables declaration//GEN-END:variables
}
