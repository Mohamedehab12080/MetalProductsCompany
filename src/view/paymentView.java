
package view;

import Database.dbOperations;
import TOOLS.TableCustom;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.TimeZone;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import model.customers;

public class paymentView extends javax.swing.JPanel {


    SimpleDateFormat timeFormat;
    SimpleDateFormat df;
    public Timer t;
    String todayDate;
    
     double globalBeforeRemained;
    public paymentView() {
        initComponents();
        getCustomerNames();
        df=new SimpleDateFormat("yyyy-MM-dd",new Locale("en"));
        todayDate=df.format(new Date());
        getLiveTime();
        btnSave2.setEnabled(false);
        getAllRecordsOpen();
        TOOLS.TableCustom.apply(jScrollPane1, TableCustom.TableType.MULTI_LINE);
                TOOLS.TableCustom.apply(jScrollPane3, TableCustom.TableType.MULTI_LINE);
      getAllRemainedCustomers();
       getCashers();
    }

     public paymentView(String CasherName) {
         initComponents();
        df=new SimpleDateFormat("yyyy-MM-dd",new Locale("en"));
        getCustomerNames();
        todayDate=df.format(new Date());
        getLiveTime();
        btnSave2.setEnabled(false);
        txtCasherName.setText(CasherName);
        getAllRecordsOpen();
        TOOLS.TableCustom.apply(jScrollPane1, TableCustom.TableType.MULTI_LINE);
                TOOLS.TableCustom.apply(jScrollPane3, TableCustom.TableType.MULTI_LINE);
         getAllRemainedCustomers();
         getCashers();
    }
     
     public void getCashers()
     {
         try {
             ResultSet rs=dbOperations.getData("select * from login where userType='Admin'");
             while(rs.next())
             {
                 txtReceiverName.addItem(rs.getString("name"));
             }
             
         } catch (Exception e) {
             JOptionPane.showMessageDialog(null, e);
         }
     }
     public void getAllRemainedCustomers()
     {
         double total=0;
         try {
             DefaultTableModel dtm=(DefaultTableModel)jTable2.getModel();
             dtm.setRowCount(0);
             ResultSet rs=dbOperations.getData("select * from customers where AllRemained > 0");
             while(rs.next())
             {
                 dtm.addRow(new Object[]
                 {
                     rs.getString("customerName"),
                     rs.getDouble("AllRemained")
                 });
             }
               
             for (int i = 0; i <jTable2.getRowCount(); i++) {
                 total+=Double.valueOf(jTable2.getValueAt(i,1).toString());
             }
              txtTotalTable.setText(total+"");
         } catch (Exception e) {
             JOptionPane.showMessageDialog(null, e);
         }
     }
    private void getTotalFromTable() {
        double tota=0;
        try {
            for (int i = 0; i <jTable1.getRowCount(); i++) {
                tota+=Double.valueOf(jTable1.getValueAt(i,5).toString());
            }
            txtTOTAL.setText(""+tota);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }


         public void getLiveTime() {
        TimeZone.setDefault(TimeZone.getTimeZone("Egypt"));
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
   private void getAllRecordsWithDateSearch(String date) {
 double total=0;
        try {
            DefaultTableModel dtm=(DefaultTableModel)jTable1.getModel();
            dtm.setRowCount(0);
           ResultSet rs=dbOperations.getData("select * from payment where date like'"+date+"%'");
           while(rs.next())
           {
              dtm.addRow(new Object[]
              {
                  rs.getInt("id"),
                  rs.getString("customerName"),
                  rs.getString("receiver"),
                  rs.getString("casherName"),
                  rs.getDouble("remainedbefor"),
                  rs.getDouble("payed"),
                  rs.getDouble("remainedAfter"),
                   rs.getString("Note"),
                  rs.getString("date"),
              });
           }
            for (int i = 0; i <jTable1.getRowCount(); i++) {
                total+=Double.valueOf(jTable1.getValueAt(i,5).toString());
            }
            txtTOTAL.setText(""+total);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
   }
    public void getCustomerNames()
    {
         try {
            ResultSet rs=dbOperations.getData("select customerName from customers where AllRemained > 0");
            while(rs.next())
            {
                txtCustomerName.addItem(rs.getString("customerName"));
            }
            ResultSet rs1=dbOperations.getData("select employeeName from employees");
            while(rs1.next())
            {
                txtReceiverName.addItem(rs1.getString("employeeName"));
            }
            ResultSet rss=dbOperations.getData("select distinct customerName from payment");
            while(rss.next()){
               txtCustomerNameSearch.addItem(rss.getString("customerName"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    public void getAllRecordsOpen()
    {
        double total=0;
        try {
            DefaultTableModel dtm=(DefaultTableModel)jTable1.getModel();
            dtm.setRowCount(0);
           ResultSet rs=dbOperations.getData("select * from payment where date like'"+todayDate+"____________%'");
           while(rs.next())
           {
              dtm.addRow(new Object[]
              {
                  rs.getInt("id"),
                  rs.getString("customerName"),
                  rs.getString("receiver"),
                  rs.getString("casherName"),
                  rs.getDouble("remainedbefor"),
                  rs.getDouble("payed"),
                  rs.getDouble("remainedAfter"),
                   rs.getString("Note"),
                  rs.getString("date"),
              });
           }
            for (int i = 0; i <jTable1.getRowCount(); i++) {
                total+=Double.valueOf(jTable1.getValueAt(i,5).toString());
            }
            txtTOTAL.setText(""+total);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtPayedValue = new javax.swing.JTextField();
        txtHandDate = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtCustomerName = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        btnSave2 = new javax.swing.JButton();
        txtpaymentPay = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtRemainedAfter = new javax.swing.JLabel();
        txtRemainedFromCustomers = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtCustomerNumber = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtReceiverName = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        txtDate = new javax.swing.JLabel();
        txtCasherName = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtNote = new javax.swing.JTextArea();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel4 = new javax.swing.JPanel();
        txtHandDateSearch = new com.toedter.calendar.JDateChooser();
        jButton1 = new javax.swing.JButton();
        txtINIDSearch = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtCustomerNameSearch = new javax.swing.JComboBox<>();
        txtMonth = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        txtYear = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        txtCustomerNumberss = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        txtTOTAL = new javax.swing.JLabel();
        txtID = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        txtTotalTable = new javax.swing.JLabel();

        jPanel2.setBackground(new java.awt.Color(0, 153, 153));

        jPanel3.setBackground(new java.awt.Color(0, 153, 153));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("رقم العميل:");

        txtPayedValue.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtPayedValue.setForeground(new java.awt.Color(0, 0, 0));
        txtPayedValue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPayedValueActionPerformed(evt);
            }
        });
        txtPayedValue.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPayedValueKeyReleased(evt);
            }
        });

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

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("تاريخ:");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("اسم العميل:");

        txtCustomerName.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtCustomerName.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "اسم العميل" }));
        txtCustomerName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCustomerNameActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));

        btnSave2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSave2.setForeground(new java.awt.Color(0, 0, 0));
        btnSave2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/reports.png"))); // NOI18N
        btnSave2.setText("طباعة/حفظ");
        btnSave2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSave2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSave2ActionPerformed(evt);
            }
        });

        txtpaymentPay.setBackground(new java.awt.Color(0, 153, 153));
        txtpaymentPay.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtpaymentPay.setForeground(new java.awt.Color(0, 0, 0));
        txtpaymentPay.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtpaymentPay.setText("0.0");
        txtpaymentPay.setOpaque(true);

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("مدفوع:");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("متبقي الان:");

        txtRemainedAfter.setBackground(new java.awt.Color(0, 102, 204));
        txtRemainedAfter.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtRemainedAfter.setForeground(new java.awt.Color(0, 0, 0));
        txtRemainedAfter.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtRemainedAfter.setText("0.0");
        txtRemainedAfter.setOpaque(true);

        txtRemainedFromCustomers.setBackground(new java.awt.Color(0, 153, 255));
        txtRemainedFromCustomers.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtRemainedFromCustomers.setForeground(new java.awt.Color(0, 0, 0));
        txtRemainedFromCustomers.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtRemainedFromCustomers.setText("0.0");
        txtRemainedFromCustomers.setOpaque(true);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("متبقي:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSave2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtRemainedFromCustomers, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtpaymentPay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtRemainedAfter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(jLabel13)
                            .addComponent(jLabel6))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtRemainedFromCustomers)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtpaymentPay)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtRemainedAfter)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSave2, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("تم دفع:");

        txtCustomerNumber.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtCustomerNumber.setForeground(new java.awt.Color(0, 0, 0));
        txtCustomerNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCustomerNumberActionPerformed(evt);
            }
        });
        txtCustomerNumber.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCustomerNumberKeyReleased(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("المستلم:");

        txtReceiverName.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtReceiverName.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", " " }));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(txtHandDate, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtCustomerName, 0, 214, Short.MAX_VALUE)
                                    .addComponent(txtPayedValue)
                                    .addComponent(txtCustomerNumber)
                                    .addComponent(txtReceiverName, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel15)
                                    .addComponent(jLabel16))))))
                .addGap(161, 161, 161))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtCustomerNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCustomerName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtReceiverName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPayedValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtHandDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTable1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "رقم الدفع", "اسم العميل", "المستلم", "كاشير", "المتبقي قبل", "المدفوع", "المتبقي بعد", "ملاحظة", "التاريخ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
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
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/salary.png"))); // NOI18N
        jLabel4.setText("السداد");

        txtDate.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtDate.setForeground(new java.awt.Color(0, 0, 0));

        txtCasherName.setBackground(new java.awt.Color(102, 102, 102));
        txtCasherName.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtCasherName.setForeground(new java.awt.Color(255, 255, 255));
        txtCasherName.setOpaque(true);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("ملاحظة:");

        txtNote.setColumns(20);
        txtNote.setRows(5);
        txtNote.setText("__");
        jScrollPane2.setViewportView(txtNote);

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jPanel4.setBackground(new java.awt.Color(0, 153, 153));

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

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/search x30.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        txtINIDSearch.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtINIDSearch.setForeground(new java.awt.Color(0, 0, 0));
        txtINIDSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtINIDSearchActionPerformed(evt);
            }
        });
        txtINIDSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtINIDSearchKeyReleased(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("رقم الدفع:");

        txtCustomerNameSearch.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtCustomerNameSearch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "اسم العميل" }));
        txtCustomerNameSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCustomerNameSearchActionPerformed(evt);
            }
        });

        txtMonth.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtMonth.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        txtMonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMonthActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel10.setText("شهر:");

        txtYear.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtYear.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030" }));

        jLabel11.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel11.setText("سنة:");

        txtCustomerNumberss.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtCustomerNumberss.setForeground(new java.awt.Color(0, 0, 0));
        txtCustomerNumberss.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCustomerNumberssActionPerformed(evt);
            }
        });
        txtCustomerNumberss.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCustomerNumberssKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtHandDateSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addComponent(txtMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtYear, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtCustomerNumberss)
                    .addComponent(txtCustomerNameSearch, 0, 157, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtINIDSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addGap(46, 46, 46))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(txtCustomerNumberss, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(txtINIDSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtHandDateSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtCustomerNameSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(1, 1, 1)))
                        .addGap(27, 27, 27))))
        );

        jPanel5.setBackground(new java.awt.Color(0, 153, 153));

        jButton2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/reports.png"))); // NOI18N
        jButton2.setText("تقرير");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("TOTAL:");

        txtTOTAL.setBackground(new java.awt.Color(102, 102, 102));
        txtTOTAL.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtTOTAL.setForeground(new java.awt.Color(255, 255, 255));
        txtTOTAL.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtTOTAL.setText("0.0");
        txtTOTAL.setOpaque(true);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(76, 76, 76)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTOTAL, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(501, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                            .addComponent(txtTOTAL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(37, 37, 37))
        );

        txtID.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtID.setForeground(new java.awt.Color(0, 0, 0));
        txtID.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtID.setText("0");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("ID:");

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "اسم العميل", "المتبقي الحالي"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(jTable2);

        txtTotalTable.setBackground(new java.awt.Color(0, 153, 255));
        txtTotalTable.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtTotalTable.setForeground(new java.awt.Color(0, 0, 0));
        txtTotalTable.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtTotalTable.setText("0.0");
        txtTotalTable.setOpaque(true);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCasherName, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(152, 152, 152)
                            .addComponent(jLabel2))
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(txtTotalTable, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(266, 266, 266)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(388, 388, 388))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(txtCasherName, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtID))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(59, 59, 59)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTotalTable))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(112, 112, 112)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 465, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 864, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(133, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1762, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtPayedValueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPayedValueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPayedValueActionPerformed

    private void txtHandDateFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtHandDateFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHandDateFocusGained

    private void txtHandDatePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtHandDatePropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHandDatePropertyChange

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        try {
            int index = jTable1.getSelectedRow();
            DefaultTableModel tm = (DefaultTableModel) jTable1.getModel();
            int a = JOptionPane.showConfirmDialog(null, "هل تريد عرض التفاصيل؟", "اختر", JOptionPane.YES_NO_OPTION);
            if (a == 0) {
                HashMap para = new HashMap();
                para.put("invo_para", Integer.parseInt(tm.getValueAt(index,0).toString()));  // inv_id  is ireport parameter name
                ReportView r = new ReportView("src\\reports\\paymentInvReport.jasper", para);
                r.view();
            } else {
                tm.removeRow(index);
                getTotalFromTable();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void btnSave2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSave2ActionPerformed
            if(txtReceiverName.getSelectedIndex()!=0)
            {
                  String date;
        try {
          if(!txtpaymentPay.getText().equals("0.0"))
          {
                if (txtHandDate.getDate() != null) {
                date = df.format(txtHandDate.getDate());
            } else {
                date = txtDate.getText();
            }
           
            ResultSet rs1=dbOperations.getData("select * from customers where customerName='"+txtCustomerName.getSelectedItem().toString()+"'");
            if(rs1.next())
            {
                dbOperations.setDataOrDelete("update customers set AllRemained="+(rs1.getDouble("AllRemained")-Double.valueOf(txtPayedValue.getText()))+" where customerName='"+txtCustomerName.getSelectedItem().toString()+"'", "");
                dbOperations.setDataOrDelete("insert into payment (customerName,casherName,remainedbefor,payed,remainedAfter,date,receiver,Note,customerNumber) "
                        + "values('"
                        +txtCustomerName.getSelectedItem().toString()
                        +"','"+txtCasherName.getText()
                        +"',"+txtRemainedFromCustomers.getText()
                        +","+txtPayedValue.getText()
                        +","+txtRemainedAfter.getText()
                        +",'"+date
                        +"','"+txtReceiverName.getSelectedItem().toString()
                        +"','"+txtNote.getText()+"','"+txtCustomerNumber.getText()+"')","تم تسجيل الدفع واضافة القيمة الي الخزنة");
            }
                ResultSet rs = dbOperations.getData("select * from capenat where id=(select max(id) from capenat)");
                if (rs.next()) {
                    dbOperations.setDataOrDelete("insert into capenat (realValue,editedvalue,afterEdictvalue,OperationType,date) values ("
                        + rs.getDouble("afterEdictvalue")
                        + "," + txtPayedValue.getText() + ","
                        + (rs.getDouble("afterEdictvalue")+Double.valueOf(txtPayedValue.getText()))
                        + ",'"+"توريد تسديد : "+txtCustomerName.getSelectedItem().toString()+"','"
                        + txtDate.getText()
                        + "')", "");
            }
            try {
                ResultSet rs22=dbOperations.getData("select max(id) as id from payment");
                if(rs22.next())
                {
                HashMap para = new HashMap();
                para.put("invo_para",rs22.getInt("id"));
                ReportView r = new ReportView("src\\reports\\paymentInvReport.jasper", para);
                r.view();
                }     
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
          
            txtRemainedFromCustomers.setText("0.0");
            txtpaymentPay.setText("0.0");
            txtRemainedAfter.setText("0.0");
            txtCustomerNumber.setText("");
            txtCustomerName.setSelectedIndex(0);
            txtReceiverName.setSelectedIndex(0);
            txtNote.setText("__");
            txtHandDate.setDate(null);
            getAllRecordsOpen();
            getAllRemainedCustomers();
          }else 
          {
              JOptionPane.showMessageDialog(null,"لا يوجد قيمة مدفوعة !!");
          }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
            }else 
            {
                JOptionPane.showMessageDialog(null,"<html><h1>قم بتحديد اسم المستلم</h1></html>");
            }
    }//GEN-LAST:event_btnSave2ActionPerformed

    private void txtHandDateSearchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtHandDateSearchFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHandDateSearchFocusGained

    private void txtHandDateSearchPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtHandDateSearchPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHandDateSearchPropertyChange

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            getAllRecordsWithDateSearch(df.format(txtHandDateSearch.getDate()) + "____________");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtINIDSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtINIDSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtINIDSearchActionPerformed

    private void txtINIDSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtINIDSearchKeyReleased
        try {
            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            dtm.setRowCount(0);
            ResultSet rs = dbOperations.getData("select * from salesBuys where INID=" + txtINIDSearch.getText());
            while (rs.next()) {
                dtm.addRow(new Object[]{
                      rs.getInt("id"),
                  rs.getString("customerName"),
                  rs.getString("receiver"),
                  rs.getString("casherName"),
                  rs.getDouble("remainedbefor"),
                  rs.getDouble("payed"),
                  rs.getDouble("remainedAfter"),
                  rs.getString("Note"),
                  rs.getString("date"),
                });
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtINIDSearchKeyReleased

    private void txtCustomerNameSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCustomerNameSearchActionPerformed
        ResultSet rs;
        try {
            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            dtm.setRowCount(0);
            if (txtCustomerNameSearch.getSelectedIndex() != 0 && txtMonth.getSelectedIndex() != 0) {
                rs = dbOperations.getData("select * from payment where date like'" +txtYear.getSelectedItem().toString()+"-"+txtMonth.getSelectedItem().toString() + "_______________%' and customerName='" + txtCustomerNameSearch.getSelectedItem().toString() + "'");
                while (rs.next()) {
                    dtm.addRow(new Object[]{
                  rs.getInt("id"),
                  rs.getString("customerName"),
                  rs.getString("receiver"),
                  rs.getString("casherName"),
                  rs.getDouble("remainedbefor"),
                  rs.getDouble("payed"),
                  rs.getDouble("remainedAfter"),
                  rs.getString("Note"),
                  rs.getString("date"),
                    });
                }
            } else if (txtCustomerNameSearch.getSelectedIndex() != 0 && txtHandDateSearch.getDate() != null) {
                rs = dbOperations.getData("select * from payment where date like'" + df.format(txtHandDateSearch.getDate()) + "____________%' and customerName='" + txtCustomerNameSearch.getSelectedItem().toString() + "'");
                while (rs.next()) {
                    dtm.addRow(new Object[]{
                             rs.getInt("id"),
                  rs.getString("customerName"),
                  rs.getString("receiver"),
                  rs.getString("casherName"),
                  rs.getDouble("remainedbefor"),
                  rs.getDouble("payed"),
                  rs.getDouble("remainedAfter"),
                   rs.getString("Note"),
                  rs.getString("date"),
                    });
                }
            } else {
                rs = dbOperations.getData("select * from payment where date like'" + todayDate + "____________%' and customerName='" + txtCustomerNameSearch.getSelectedItem().toString() + "'");
                while (rs.next()) {
                    dtm.addRow(new Object[]{
                           rs.getInt("id"),
                  rs.getString("customerName"),
                  rs.getString("receiver"),
                  rs.getString("casherName"),
                  rs.getDouble("remainedbefor"),
                  rs.getDouble("payed"),
                  rs.getDouble("remainedAfter"),
                   rs.getString("Note"),
                  rs.getString("date"),
                    });
                }
            }

            getTotalFromTable();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_txtCustomerNameSearchActionPerformed

    private void txtMonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMonthActionPerformed
        try {
            getAllRecordsWithDateSearch(txtYear.getSelectedItem().toString()+"-"+txtMonth.getSelectedItem().toString() + "_______________");
            txtHandDateSearch.setDate(null);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_txtMonthActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            double s = 0;
            HashMap para = new HashMap();
            para.put("title", "تقرير خوارج");
            ReportView r = null;
            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            for (int i = 0; i < jTable1.getRowCount(); i++) {
                s += (Double.valueOf(jTable1.getValueAt(i, 5).toString()));
            }
            if (txtCustomerNameSearch.getSelectedIndex() != 0 && txtHandDateSearch.getDate() != null) {
                para.put("Inv_date", df.format(txtHandDateSearch.getDate())+"____________%");  // inv_id  is ireport parameter name
                para.put("dateView",df.format(txtHandDateSearch.getDate()));
                para.put("customerName_Group",txtCustomerNameSearch.getSelectedItem().toString());
                r = new ReportView("src\\reports\\paymentReport.jasper", para);
               r.view();
            } else if (txtCustomerNameSearch.getSelectedIndex() != 0 && txtMonth.getSelectedIndex() != 0) {
                para.put("Inv_date", txtYear.getSelectedItem().toString()+"-"+txtMonth.getSelectedItem().toString() +"_______________%");  // inv_id  is ireport parameter name
                para.put("dateView",txtYear.getSelectedItem().toString()+"-"+txtMonth.getSelectedItem().toString() );
                para.put("customerName_Group",txtCustomerNameSearch.getSelectedItem().toString());
                r = new ReportView("src\\reports\\paymentReport.jasper", para);
               r.view();
            } else if (txtMonth.getSelectedIndex() != 0) {
                para.put("dayDate", txtYear.getSelectedItem().toString()+"-"+txtMonth.getSelectedItem().toString() );  // inv_id  is ireport parameter name
                para.put("Total", "" + s);
                r = new ReportView("src\\reports\\paymentJTable.jasper", para);
                r.view3(dtm, "src\\reports\\paymentJTable.jrxml");
            } else if (txtHandDateSearch.getDate() != null) {
                para.put("dayDate",df.format(txtHandDateSearch.getDate()));  // inv_id  is ireport parameter name
                para.put("Total", "" + s);
                r = new ReportView("src\\reports\\paymentJTable.jasper", para);
                r.view3(dtm, "src\\reports\\paymentJTable.jrxml");
            }else 
            {
                getAllRecordsOpen();
                para.put("dayDate",todayDate);  // inv_id  is ireport parameter name
                para.put("Total", "" + s);
                r = new ReportView("src\\reports\\paymentJTable.jasper", para);
                r.view3(dtm, "src\\reports\\paymentJTable.jrxml");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtCustomerNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCustomerNumberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCustomerNumberActionPerformed

   public ArrayList<customers> getNameWithPhone(String phone)
   {
       ArrayList<customers> list=new ArrayList();
         try {
           ResultSet rs=dbOperations.getData("select * from customers where phone='"+phone+"'");
                 while(rs.next())
                   {
                       customers cus=new customers();
                       cus.setCustomerName(rs.getString("customerName"));
                       cus.setAllRemained(rs.getDouble("AllRemained"));
                       list.add(cus);
                   }
       } catch (Exception e) {
       }
         return list;
   }
    private void txtCustomerNumberKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCustomerNumberKeyReleased
        try {
           int forEx=Integer.parseInt(txtCustomerNumber.getText());
           ArrayList<customers> list=getNameWithPhone(txtCustomerNumber.getText());
            Iterator<customers> itr=list.iterator();
            while(itr.hasNext())
            {
                customers cus=itr.next();
                txtCustomerName.setSelectedItem(cus.getCustomerName());
                    txtRemainedFromCustomers.setText(""+cus.getAllRemained());
                    txtCustomerName.setForeground(new Color(0,0,0));
                    globalBeforeRemained=cus.getAllRemained();
            }
         }
        catch(NumberFormatException ex)
        {
            txtCustomerName.setSelectedIndex(0);
            txtRemainedFromCustomers.setText("0.0");
            txtCustomerName.setForeground(new Color(255,0,0));
            globalBeforeRemained=0;
        }
        catch (Exception e) {
            
        }
    }//GEN-LAST:event_txtCustomerNumberKeyReleased

    private void txtCustomerNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCustomerNameActionPerformed
         try {
            ResultSet rs=dbOperations.getData("select * from customers where customerName='"+txtCustomerName.getSelectedItem().toString()+"'");
            if(rs.next())
            {
                txtCustomerNumber.setText(rs.getString("phone"));
                txtRemainedFromCustomers.setText(rs.getDouble("AllRemained")+"");
                globalBeforeRemained=rs.getDouble("AllRemained");
            }
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_txtCustomerNameActionPerformed

    private void txtPayedValueKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPayedValueKeyReleased
        try {
            double payed=Double.parseDouble(txtPayedValue.getText());
       if(payed>1)
       {
               if(payed<=Double.valueOf(txtRemainedFromCustomers.getText()))
           {
                btnSave2.setEnabled(true);
                txtRemainedAfter.setText(""+(globalBeforeRemained-payed));
                txtpaymentPay.setText(""+payed);
           }else 
           {
                 txtPayedValue.setForeground(new Color(255,0,0));
                txtRemainedAfter.setText("0.0");
                txtpaymentPay.setText("0.0");
                btnSave2.setEnabled(false);
           }
       }
        } 
        catch (NumberFormatException e) {
            txtPayedValue.setForeground(new Color(255,0,0));
            txtRemainedAfter.setText("0.0");
            txtpaymentPay.setText("0.0");
            btnSave2.setEnabled(false);
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_txtPayedValueKeyReleased

    private void txtCustomerNumberssActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCustomerNumberssActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCustomerNumberssActionPerformed

    private void txtCustomerNumberssKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCustomerNumberssKeyReleased
         try {
            int forEx=Integer.parseInt(txtCustomerNumberss.getText());
           ArrayList<customers> list=getNameWithPhone(txtCustomerNumberss.getText());
            Iterator<customers> itr=list.iterator();
            while(itr.hasNext())
            {
                customers cus=itr.next();
                txtCustomerNameSearch.setSelectedItem(cus.getCustomerName());
                txtCustomerNumberss.setForeground(new Color(0,0,0));
            }
        } catch (NumberFormatException e) {
            txtCustomerNameSearch.setSelectedIndex(0);
            txtCustomerNumberss.setForeground(new Color(255,0,0));
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_txtCustomerNumberssKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSave2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JLabel txtCasherName;
    private javax.swing.JComboBox<String> txtCustomerName;
    private javax.swing.JComboBox<String> txtCustomerNameSearch;
    private javax.swing.JTextField txtCustomerNumber;
    private javax.swing.JTextField txtCustomerNumberss;
    private javax.swing.JLabel txtDate;
    private com.toedter.calendar.JDateChooser txtHandDate;
    private com.toedter.calendar.JDateChooser txtHandDateSearch;
    private javax.swing.JLabel txtID;
    private javax.swing.JTextField txtINIDSearch;
    private javax.swing.JComboBox<String> txtMonth;
    private javax.swing.JTextArea txtNote;
    private javax.swing.JTextField txtPayedValue;
    private javax.swing.JComboBox<String> txtReceiverName;
    private javax.swing.JLabel txtRemainedAfter;
    private javax.swing.JLabel txtRemainedFromCustomers;
    private javax.swing.JLabel txtTOTAL;
    private javax.swing.JLabel txtTotalTable;
    private javax.swing.JComboBox<String> txtYear;
    private javax.swing.JLabel txtpaymentPay;
    // End of variables declaration//GEN-END:variables

}
