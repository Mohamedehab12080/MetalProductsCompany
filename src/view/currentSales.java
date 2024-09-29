package view;

import Database.dbOperations;
import TOOLS.TableCustom;
import controller.casherCruds;
import controller.productsController;
import controller.salesCruds;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
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
import javax.swing.table.TableModel;
import model.loginModel;
import model.salesModel;

public class currentSales extends javax.swing.JPanel {

    public boolean CheckboxState1 = false;
    public boolean CheckboxState2 = false;
    public boolean CheckboxState3 = false;
    public boolean CheckboxState4 = false;
    Timer t;
    SimpleDateFormat timeFormat;
    String globalEmpName;
    String Operation="توريد المبيعات";
    public boolean CheckboxState5 = false;
    private final double WIDTH = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    private final double HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    public String Empname;
    String todayDate = "";
    SimpleDateFormat df = null;
    String capenateDate="";
    public currentSales() {
        initComponents();
        this.setSize(Integer.valueOf(String.valueOf(WIDTH)), Integer.valueOf(String.valueOf(HEIGHT)));
        df = new SimpleDateFormat("yyyy-MM-dd", new Locale("en"));
        todayDate = df.format(new Date());
        txtDate.setText(todayDate);
        TOOLS.TableCustom.apply(jScrollPane1, TableCustom.TableType.MULTI_LINE);
        txtDateSearch.setEnabled(false);
        InvID.setEnabled(false);
        openedData();
        btnSearch.setEnabled(false);
       getLiveTime();
        txtEmployee.setEnabled(false);
        getAllEmpnames();
        getAllCustomersName();
    }
    public currentSales(String empname) {
        initComponents();
       globalEmpName=empname;
        df = new SimpleDateFormat("yyyy-MM-dd", new Locale("en"));
        todayDate = df.format(new Date());
        txtDate.setText(todayDate);
        TOOLS.TableCustom.apply(jScrollPane1, TableCustom.TableType.MULTI_LINE);
        txtDateSearch.setEnabled(false);
        InvID.setEnabled(false);
        openedData();
        btnSearch.setEnabled(false);
        txtEmployee.setEnabled(false);
        getLiveTime();
        getAllEmpnames();
        getAllCustomersName();
    }
        public currentSales(String empname,String date) {
        initComponents();
          globalEmpName=empname;
        capenateDate=date;
        df = new SimpleDateFormat("yyyy-MM-dd", new Locale("en"));
        todayDate = df.format(new Date());
        txtDate.setText(todayDate);
        TOOLS.TableCustom.apply(jScrollPane1, TableCustom.TableType.MULTI_LINE);
        txtDateSearch.setEnabled(false);
        InvID.setEnabled(false);
        openedDataForCapenatDate();
        btnSearch.setEnabled(false);
        txtEmployee.setEnabled(false);
        getLiveTime();
        getAllEmpnames();
        getAllCustomersName();
    }

        public void getAllCustomersName()
        {
            try {
                ResultSet rs=dbOperations.getData("select distinct customerName from sales");
                txtCustomerName.removeAllItems();
                txtCustomerName.addItem("__Choose__");
                while(rs.next())
                {
                    txtCustomerName.addItem(rs.getString("customerName"));
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        InvID = new javax.swing.JTextField();
        txtDate1 = new javax.swing.JLabel();
        txtCheckInvID = new javax.swing.JCheckBox();
        txtCheckDate = new javax.swing.JCheckBox();
        txtDateSearch = new com.toedter.calendar.JDateChooser();
        btnRefresh = new javax.swing.JButton();
        btnSearch = new javax.swing.JButton();
        txtCheckEmployee = new javax.swing.JCheckBox();
        jLabel8 = new javax.swing.JLabel();
        txtEmployee = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        txtYear1 = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        txtMonth1 = new javax.swing.JComboBox<>();
        txtCustomerName = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        txtState = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        txtCeckCustomername = new javax.swing.JCheckBox();
        txtCheckState = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtMonthlyCaret = new javax.swing.JLabel();
        txtDailyCaret = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtDate = new javax.swing.JLabel();
        txtDate2 = new javax.swing.JLabel();
        txtYear = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        txtMonth = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        btnGetDailyTotal = new javax.swing.JButton();
        btnPrint = new javax.swing.JButton();
        txtLabelEmp = new javax.swing.JLabel();
        txtDateTime = new javax.swing.JLabel();

        setBackground(new java.awt.Color(0, 153, 204));
        setMaximumSize(new java.awt.Dimension(1099, 649));
        setPreferredSize(new java.awt.Dimension(1331, 608));

        jPanel1.setBackground(new java.awt.Color(0, 153, 204));

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setForeground(new java.awt.Color(51, 51, 51));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("رقم الفاتورة");

        InvID.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        InvID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InvIDActionPerformed(evt);
            }
        });
        InvID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                InvIDKeyReleased(evt);
            }
        });

        txtDate1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtDate1.setForeground(new java.awt.Color(255, 255, 255));
        txtDate1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtDate1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/place order.png"))); // NOI18N
        txtDate1.setText("ادارة الفواتير");

        txtCheckInvID.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtCheckInvID.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        txtCheckInvID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCheckInvIDActionPerformed(evt);
            }
        });

        txtCheckDate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        txtCheckDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCheckDateActionPerformed(evt);
            }
        });

        txtDateSearch.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtDateSearch.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDateSearchFocusGained(evt);
            }
        });
        txtDateSearch.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtDateSearchPropertyChange(evt);
            }
        });

        btnRefresh.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/reset2.png"))); // NOI18N
        btnRefresh.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        btnSearch.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/small Search.png"))); // NOI18N
        btnSearch.setText("بحث");
        btnSearch.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        txtCheckEmployee.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        txtCheckEmployee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCheckEmployeeActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("كاشير:");

        txtEmployee.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtEmployee.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", " " }));
        txtEmployee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmployeeActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("الشهر");

        txtYear1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtYear1.setForeground(new java.awt.Color(0, 0, 0));
        txtYear1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030" }));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("السنة:");

        txtMonth1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtMonth1.setForeground(new java.awt.Color(0, 0, 0));
        txtMonth1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        txtMonth1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMonth1ActionPerformed(evt);
            }
        });

        txtCustomerName.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtCustomerName.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "__Choose__", " " }));
        txtCustomerName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCustomerNameActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("اسم العميل:");

        txtState.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtState.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "مدفوع", "متبقي", "عير مدفوع", " " }));
        txtState.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtStateActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("الحالة:");

        txtCeckCustomername.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtCeckCustomername.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        txtCeckCustomername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCeckCustomernameActionPerformed(evt);
            }
        });

        txtCheckState.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtCheckState.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        txtCheckState.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCheckStateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtDate1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(73, 73, 73))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(btnRefresh)
                        .addGap(47, 47, 47)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSearch)
                    .addComponent(txtDateSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCheckDate, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCheckEmployee, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(txtState, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtCheckState))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                                .addComponent(txtCustomerName, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(txtCeckCustomername)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(InvID, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtCheckInvID, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtMonth1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10)
                        .addGap(9, 9, 9)
                        .addComponent(txtYear1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addGap(22, 22, 22))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(txtDate1)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtCheckDate, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(46, 46, 46))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(txtCheckEmployee)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel8)
                                            .addComponent(txtEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(txtCheckInvID)
                                                .addComponent(txtCeckCustomername))
                                            .addComponent(txtCheckState))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel1)
                                            .addComponent(InvID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtCustomerName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel2)
                                            .addComponent(txtState, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel3)))
                                    .addComponent(txtDateSearch, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSearch)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtYear1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtMonth1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10)))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(btnRefresh)))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "اسم العميل", "رقم الفاتورة", "الكمية الكلية", "نوع السعر", "TOTAL", "خصم", "الصافي", "المدفوع الكلي", "المتبقي", "كاشير", "التاريخ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
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

        jPanel3.setBackground(new java.awt.Color(51, 51, 51));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("توتال الشهر:");

        txtMonthlyCaret.setBackground(new java.awt.Color(0, 153, 153));
        txtMonthlyCaret.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtMonthlyCaret.setForeground(new java.awt.Color(255, 255, 255));
        txtMonthlyCaret.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtMonthlyCaret.setText("0");
        txtMonthlyCaret.setOpaque(true);

        txtDailyCaret.setBackground(new java.awt.Color(0, 153, 51));
        txtDailyCaret.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtDailyCaret.setForeground(new java.awt.Color(255, 255, 255));
        txtDailyCaret.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtDailyCaret.setText("0");
        txtDailyCaret.setOpaque(true);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("توتال اليوم:");

        txtDate.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtDate.setForeground(new java.awt.Color(255, 255, 255));
        txtDate.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        txtDate2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtDate2.setForeground(new java.awt.Color(255, 255, 255));
        txtDate2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtDate2.setText("تاريخ اليوم:");

        txtYear.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtYear.setForeground(new java.awt.Color(0, 0, 0));
        txtYear.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030" }));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("السنة:");

        txtMonth.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtMonth.setForeground(new java.awt.Color(0, 0, 0));
        txtMonth.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        txtMonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMonthActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("الشهر");

        btnGetDailyTotal.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnGetDailyTotal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/update.png"))); // NOI18N
        btnGetDailyTotal.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGetDailyTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGetDailyTotalActionPerformed(evt);
            }
        });

        btnPrint.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnPrint.setForeground(new java.awt.Color(0, 0, 0));
        btnPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/print.png"))); // NOI18N
        btnPrint.setText("تقرير");
        btnPrint.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });

        txtLabelEmp.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtLabelEmp.setForeground(new java.awt.Color(255, 255, 255));
        txtLabelEmp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        txtDateTime.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtDateTime.setForeground(new java.awt.Color(255, 255, 255));
        txtDateTime.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtDateTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtLabelEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(txtDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtDate2, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 237, Short.MAX_VALUE)
                        .addComponent(btnGetDailyTotal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDailyCaret, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addGap(133, 133, 133)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtMonthlyCaret, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)))
                .addGap(162, 162, 162))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtLabelEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDateTime, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPrint, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtDate2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel7))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel6)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMonthlyCaret, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDailyCaret, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnGetDailyTotal))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    public void getLiveTime() {
        TimeZone.setDefault(TimeZone.getTimeZone("Egypt"));
        t = new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date dt = new Date();
                timeFormat = new SimpleDateFormat("hh:mm:ss a", new Locale("en"));
                String tt = timeFormat.format(dt);
                txtDateTime.setText(todayDate + " " + tt);
            }
        });
        t.start();
    }
    public void getAllMonthSales(String year, String Month) {
        try {
            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            dtm.setRowCount(0);
            ArrayList<model.salesModel> list = controller.salesCruds.getAllSalesrecordsWithMonth(year, Month);
            Iterator<model.salesModel> itr = list.iterator();
            while (itr.hasNext()) {
                model.salesModel sale = itr.next();
                dtm.addRow(new Object[]{
                    sale.getCustomerName(),
                    sale.getINID(),
                    sale.getTotal_Quantity(),
                    sale.getSaleType(),
                    sale.getTotal(),
                    sale.getDiscount(),
                    sale.getAllTotal(),
                    sale.getPayed(),
                    sale.getRemained(),
                    sale.getEmpname(),
                    sale.getDate()
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void openedData() {
        try {
            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            dtm.setRowCount(0);
            ArrayList<model.salesModel> list = controller.salesCruds.getAllSaleswithTodayDate(todayDate);
            Iterator<model.salesModel> itr = list.iterator();
            while (itr.hasNext()) {
                model.salesModel sale = itr.next();
                dtm.addRow(new Object[]{
                    sale.getCustomerName(),
                    sale.getINID(),
                    sale.getTotal_Quantity(),
                    sale.getSaleType(),
                    sale.getTotal(),
                    sale.getDiscount(),
                    sale.getAllTotal(),
                    sale.getPayed(),
                    sale.getRemained(),
                    sale.getEmpname(),
                    sale.getDate()
                });
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void openedDataForCapenatDate() {
        try {
            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            dtm.setRowCount(0);
            ArrayList<model.salesModel> list = controller.salesCruds.getAllSaleswithTodayDate(capenateDate);
            Iterator<model.salesModel> itr = list.iterator();
            while (itr.hasNext()) {
                model.salesModel sale = itr.next();
                dtm.addRow(new Object[]{
                    sale.getCustomerName(),
                    sale.getINID(),
                    sale.getTotal_Quantity(),
                    sale.getSaleType(),
                    sale.getTotal(),
                    sale.getDiscount(),
                    sale.getAllTotal(),
                    sale.getPayed(),
                    sale.getRemained(),
                    sale.getEmpname(),
                    sale.getDate()
                });
            }
              txtDailyCaret.setText(String.valueOf(salesCruds.getSumofPayedAndRemainedDaily(capenateDate)[0]));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void getAllEmpnames() {
        try {
            ArrayList<loginModel> list = casherCruds.getAllEmpnamesForCompo();
            Iterator<loginModel> itr = list.iterator();
            while (itr.hasNext()) {
                loginModel emp = itr.next();
                txtEmployee.addItem(emp.getName());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void setMonthlyTotal() {
        int TotalMonth = 0;
        try {
            for (int i = 0; i < jTable1.getRowCount(); i++) {
                TotalMonth += Double.parseDouble(jTable1.getValueAt(i, 6).toString());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        txtMonthlyCaret.setText(String.valueOf(TotalMonth));
    }
//  public void setDailyTotal() {
//        int TotalDay = 0;
//        try {
//            for (int i = 0; i < jTable1.getRowCount(); i++) {
//                TotalDay += Integer.parseInt(jTable1.getValueAt(i, 6).toString());
//            }
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, e);
//        }
//        txtDailyCaret.setText(String.valueOf(TotalDay));
//    }

    public void getAllSalesWithMonthAndEmpname(String Empname, String year, String Month) {
        try {
            double TotalPayMonth = 0;
            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            dtm.setRowCount(0);
            ArrayList<salesModel> list = salesCruds.getAllSalesWithMonthAndEmpname(Empname, year, Month);
            Iterator<salesModel> itr = list.iterator();
            while (itr.hasNext()) {
                salesModel sale = itr.next();
                TotalPayMonth += sale.getPayed();
                dtm.addRow(new Object[]{
                    sale.getCustomerName(),
                    sale.getINID(),
                    sale.getTotal_Quantity(),
                    sale.getSaleType(),
                    sale.getTotal(),
                    sale.getDiscount(),
                    sale.getAllTotal(),
                    sale.getPayed(),
                    sale.getRemained(),
                    sale.getEmpname(),
                    sale.getDate()
                });
            }
            txtMonthlyCaret.setText(String.valueOf(TotalPayMonth));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void getAllSales(int invID) {
        try {
            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            dtm.setRowCount(0);
            ArrayList<salesModel> list = salesCruds.getAllSales(invID);
            Iterator<salesModel> itr = list.iterator();
            while (itr.hasNext()) {
                salesModel sale = itr.next();
                dtm.addRow(new Object[]{
                    sale.getCustomerName(),
                    sale.getINID(),
                    sale.getTotal_Quantity(),
                    sale.getSaleType(),
                    sale.getTotal(),
                    sale.getDiscount(),
                    sale.getAllTotal(),
                    sale.getPayed(),
                    sale.getRemained(),
                    sale.getEmpname(),
                    sale.getDate()
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    public void data_LoadForCustomername(String Customername, String date, String state) {
        try {

            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            dtm.setRowCount(0);
            ArrayList<salesModel> list = null;
            if (!Customername.equals("") && !state.equals("") && !date.equals("")) {
                list = salesCruds.getAllSalesForAll(Customername, date, state);

            } else if (!Customername.equals("") && !state.equals("")) {
                list = salesCruds.getAllSalesForCustomernameandState(Customername, state);
            } else if (!Customername.equals("") && !date.equals("")) {
                list = salesCruds.getAllSalesForCustomernameandDate(Customername, date);
            } else if (Customername.equals("") && !state.equals("") && !date.equals("")) {
                list = salesCruds.getAllSalesForDateandState(date, state);
            } else if (Customername.equals("") && !date.equals("") && state.equals("")) {
                list = salesCruds.getAllSaleswithTodayDate(date);
            }
            Iterator<salesModel> itr = list.iterator();
            while (itr.hasNext()) {
                salesModel sale = itr.next();
                dtm.addRow(new Object[]{
                    sale.getCustomerName(),
                    sale.getINID(),
                    sale.getTotal_Quantity(),
                    sale.getSaleType(),
                    sale.getTotal(),
                    sale.getDiscount(),
                    sale.getAllTotal(),
                    sale.getPayed(),
                    sale.getRemained(),
                    sale.getEmpname(),
                    sale.getDate()
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void data_LoadForEmpname(String empname, String date, String Monthly) {
        try {
            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            dtm.setRowCount(0);
            ArrayList<salesModel> list = null;
            if (!empname.equals("") && !date.equals("")) {
                list = salesCruds.getAllSalesForAllwithEmpname(empname, date);
//                JOptionPane.showMessageDialog(null, "1");

            } else if (!empname.equals("") && !Monthly.equals("")) {
                list = salesCruds.etAllSalesWithEmpnameMonthly(empname, Monthly);
//                JOptionPane.showMessageDialog(null, "2");
            } else if (empname.equals("") && Monthly.equals("")) {
                list = salesCruds.getAllSalesWithDayDate(date);
//                JOptionPane.showMessageDialog(null, "3");
            }
            Iterator<salesModel> itr = list.iterator();
            while (itr.hasNext()) {
                salesModel sale = itr.next();
                dtm.addRow(new Object[]{
                    sale.getCustomerName(),
                    sale.getINID(),
                    sale.getTotal_Quantity(),
                    sale.getSaleType(),
                    sale.getTotal(),
                    sale.getDiscount(),
                    sale.getAllTotal(),
                    sale.getPayed(),
                    sale.getRemained(),
                    sale.getEmpname(),
                    sale.getDate()
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void InvIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InvIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InvIDActionPerformed

    private void InvIDKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_InvIDKeyReleased
        try {
            getAllSales(Integer.valueOf(InvID.getText()));
            //            data_loadForAll();
        } catch (NumberFormatException e) {

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ssss");
        }
    }//GEN-LAST:event_InvIDKeyReleased

    private void txtCheckInvIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCheckInvIDActionPerformed
        if (CheckboxState1 == false) {
            CheckboxState1 = true;
            txtCheckDate.setSelected(false);
            txtCheckEmployee.setSelected(false);
            txtEmployee.setSelectedIndex(0);
            InvID.setEnabled(true);
            txtDateSearch.setEnabled(false);
            txtDateSearch.setDate(null);
            btnSearch.setEnabled(false);
            txtCustomerName.setEnabled(false);
        } else {
            CheckboxState1 = false;
            InvID.setEnabled(false);
            InvID.setText("");
        }
    }//GEN-LAST:event_txtCheckInvIDActionPerformed

    private void txtCheckDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCheckDateActionPerformed
        if (CheckboxState4 == false) {
            CheckboxState4 = true;
            txtDateSearch.setEnabled(true);
            btnSearch.setEnabled(true);
        } else {
            CheckboxState4 = false;
            txtDateSearch.setEnabled(false);
            txtDateSearch.setDate(null);
            btnSearch.setEnabled(false);
        }
    }//GEN-LAST:event_txtCheckDateActionPerformed

    private void txtDateSearchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDateSearchFocusGained

    }//GEN-LAST:event_txtDateSearchFocusGained

    private void txtDateSearchPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtDateSearchPropertyChange

    }//GEN-LAST:event_txtDateSearchPropertyChange

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        openedData();
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        String localDate;
        try {
            if (txtDateSearch.getDate() != null) {
                localDate = df.format(txtDateSearch.getDate());
            } else {
                localDate = txtDate.getText();
            }
            if (txtCeckCustomername.isSelected() && txtCheckState.isSelected()) {
                data_LoadForCustomername(txtCustomerName.getSelectedItem().toString(), localDate, txtState.getSelectedItem().toString());
            } else if (txtCeckCustomername.isSelected()) {
                data_LoadForCustomername(txtCustomerName.getSelectedItem().toString(), localDate, "");
            } else {
                data_LoadForCustomername("", localDate, "");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void txtCheckEmployeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCheckEmployeeActionPerformed
        if (CheckboxState5 == false) {
            CheckboxState5 = true;
            txtEmployee.setEnabled(true);
        } else {
            CheckboxState5 = false;
            txtEmployee.setEnabled(false);
            txtEmployee.setSelectedIndex(0);
        }
    }//GEN-LAST:event_txtCheckEmployeeActionPerformed

    private void txtEmployeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmployeeActionPerformed
        String localDate;
        btnPrint.setText("تقرير: " + txtEmployee.getSelectedItem().toString());
        try {
            if (txtDateSearch.getDate() != null) {
                localDate = df.format(txtDateSearch.getDate());
            } else {
                localDate = txtDate.getText();
            }
            if (txtCheckEmployee.isSelected() && txtCheckDate.isSelected()) {
                data_LoadForEmpname(txtEmployee.getSelectedItem().toString(), localDate, "");

            } else if (txtCheckEmployee.isSelected() && txtMonth1.getSelectedIndex() != 0) {
                data_LoadForEmpname(txtEmployee.getSelectedItem().toString(), "", txtYear1.getSelectedItem().toString() + "-" + txtMonth1.getSelectedItem().toString());
//                JOptionPane.showMessageDialog(null, "A7A 3");
            } else if (txtCheckEmployee.isSelected()) {
                data_LoadForEmpname(String.valueOf(txtEmployee.getSelectedItem()), localDate, "");
//                JOptionPane.showMessageDialog(null, "A7A 5");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_txtEmployeeActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        try {
            int index = jTable1.getSelectedRow();
            TableModel tm = jTable1.getModel();
            String invId = tm.getValueAt(index, 1).toString();
                       String customername = tm.getValueAt(index, 0).toString();
            //            String TotalQuant = tm.getValueAt(index, 3).toString();
            String TOTAL = tm.getValueAt(index, 4).toString();
            //            String State = tm.getValueAt(index, 5).toString();
                       String Remained = tm.getValueAt(index, 8).toString();
                       String payed=tm.getValueAt(index,7).toString();
            int a = JOptionPane.showConfirmDialog(null, "هل تريد عرض التفاصيل؟", "اختر", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (a == 0) // Report or Show in new Jpanel with invId for Card
            {
                HashMap para = new HashMap();
                para.put("invo_para", invId);  // inv_id  is ireport parameter name
                para.put("Inv_Type", "فاتورة بيع");
                para.put("Inv_Total", TOTAL);
                para.put("copyRight", (char)169+"Mo_Software:01091499680");
                ReportView r = new ReportView("src\\reports\\reviewCartReport.jasper", para);
                r.view();
                 HashMap para1 = new HashMap();
                para1.put("invo_para", invId);  // inv_id  is ireport parameter name
                para1.put("Inv_Type", "فاتورة بيع");
                para1.put("Inv_Total", TOTAL);
                para1.put("copyRight", (char)169+"Mo_Software:01091499680");
                ReportView r1 = new ReportView("src\\reports\\finalCartReport.jasper", para1);
                r1.view();
            }
 // else {
//                int b = JOptionPane.showConfirmDialog(null, "<html><h1>هل تريد حذف فاتورة:" + invId+"</h1></html>", "اختر", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
//                if (b == 0) {
//                    int x = JOptionPane.showConfirmDialog(null, "<html><h1>هل تريد حذف فاتورة واعادة المنتجات؟:" + invId+"</h1></html>", "اختر", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
//                    if (x == 0) {
//                        ResultSet rs = dbOperations.getData("select * from cart where INID=" + invId);
//                        while (rs.next()) {
//                            productsController.updateAvailableWhenDeleteSale(rs.getString("productname"),rs.getDouble("quantityneeded"),rs.getString("productPlace"));
//                        }
//                        int xz=JOptionPane.showConfirmDialog(null,"<html><h1>هل تريد طرح القيمة المدفوعة من الخزنة؟</h1></html>","اختر",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
//                        if(xz==0)
//                        {
//                            ResultSet rsRe=dbOperations.getData("select * from capenat where id=(select max(id) from capenat)");
//                           if(rsRe.next())
//                           {
//                               dbOperations.setDataOrDelete("insert into capenat (realValue,editedvalue,afterEdictvalue,OperationType,date) "
//                                       + "values ("+rsRe.getDouble("afterEdictvalue")
//                                       +","+payed
//                                       +","+(rsRe.getDouble("afterEdictvalue")-Double.valueOf(payed))
//                                       +",'"+"تم حذف الفاتورة رقم : "+invId+" وقام ب هذا: "+globalEmpName+"','"+txtDateTime.getText()+"')", "تم خصم القيمة المدفوعة من الخزنة");
//                           }
//                        }
//                        dbOperations.setDataOrDelete("delete from cart where INID=" + invId, "");
//                        dbOperations.setDataOrDelete("delete from sales where INID=" + invId, "تم الحذف");
//                    }else 
//                    {
//                         int xz=JOptionPane.showConfirmDialog(null,"<html><h1>هل تريد طرح القيمة المدفوعة من الخزنة؟</h1></html>","اختر",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
//                        if(xz==0)
//                        {
//                            ResultSet rsRe=dbOperations.getData("select * from capenat where id=(select max(id) from capenat)");
//                           if(rsRe.next())
//                           {
//                               dbOperations.setDataOrDelete("insert into capenat (realValue,editedvalue,afterEdictvalue,OperationType,date) "
//                                       + "values ("+rsRe.getDouble("afterEdictvalue")
//                                       +","+payed
//                                       +","+(rsRe.getDouble("afterEdictvalue")-Double.valueOf(payed))
//                                       +",'"+"تم حذف الفاتورة رقم : "+invId+" وقام ب هذا: "+globalEmpName+"','"+txtDateTime.getText()+"')", "تم خصم القيمة المدفوعة من الخزنة");
//                           }
//                        }
//                        dbOperations.setDataOrDelete("delete from cart where INID=" + invId, "");
//                        dbOperations.setDataOrDelete("delete from sales where INID=" + invId, "تم الحذف");
//                    }
//
//                    openedData();
//                }
//            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void txtMonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMonthActionPerformed
        try {
//            txtMonthlyCaret.setText(String.valueOf(salesCruds.getSumofPayedMonthly(txtMonth.getSelectedItem().toString(), txtYear.getSelectedItem().toString())));
            getAllMonthSales(txtYear.getSelectedItem().toString(), txtMonth.getSelectedItem().toString());
            setMonthlyTotal();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_txtMonthActionPerformed

    private void btnGetDailyTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGetDailyTotalActionPerformed
        try {
            if (txtCheckDate.isSelected() && txtDateSearch.getDate() != null) {
                txtDailyCaret.setText(String.valueOf(salesCruds.getSumofPayedAndRemainedDaily(df.format(txtDateSearch.getDate()))[0]));
            } else {

                txtDailyCaret.setText(String.valueOf(salesCruds.getSumofPayedAndRemainedDaily(txtDate.getText())[0]));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_btnGetDailyTotalActionPerformed

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        try {
            double Totl = 0;
            double Totl2=0;
            HashMap para = new HashMap();
            para.put("title", "تقرير مبيعات");
            ReportView r = null;
            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            for (int i = 0; i < dtm.getRowCount(); i++) {
                Totl += Double.valueOf(dtm.getValueAt(i, 6).toString());
                Totl2 += Double.valueOf(dtm.getValueAt(i, 7).toString());
            }

            if (txtCheckEmployee.isSelected()) {
                para.put("dayDate", txtEmployee.getSelectedItem().toString());  // inv_id  is ireport parameter name
                para.put("Total", "" + Totl);
                 para.put("payedTotal", "" + Totl2);
                r = new ReportView("src\\reports\\SalesJTable.jasper", para);
                r.view3(dtm, "src\\reports\\SalesJTable.jrxml");
            } else if (txtCheckDate.isSelected() &&!txtCustomerName.getSelectedItem().toString().equals("__Choose__")) {
                para.put("dayDate", df.format(txtDateSearch.getDate())+": "+btnPrint.getText());
                para.put("Total", "" + Totl);
                 para.put("payedTotal", "" + Totl2);
                r = new ReportView("src\\reports\\SalesJTable.jasper", para);
                r.view3(dtm, "src\\reports\\SalesJTable.jrxml");
            }
            else if (txtCheckDate.isSelected()) {
                para.put("dayDate", df.format(txtDateSearch.getDate()));
                para.put("Total", "" + Totl);
                 para.put("payedTotal", "" + Totl2);
                r = new ReportView("src\\reports\\SalesJTable.jasper", para);
                r.view3(dtm, "src\\reports\\SalesJTable.jrxml");
            }else if (txtMonth.getSelectedIndex() != 0 && !txtCustomerName.getSelectedItem().toString().equals("__Choose__")) {
                para.put("dayDate",  txtYear.getSelectedItem().toString() + "-" + txtMonth.getSelectedItem().toString()+": "+btnPrint.getText());
                para.put("Total", "" + Totl);
                 para.put("payedTotal", "" + Totl2);
                r = new ReportView("src\\reports\\SalesJTable.jasper", para);
                r.view3(dtm, "src\\reports\\SalesJTable.jrxml");
            } 
            else if (txtMonth.getSelectedIndex() != 0) {
                para.put("dayDate",  txtYear.getSelectedItem().toString() + "-" + txtMonth.getSelectedItem().toString());
                para.put("Total", "" + Totl);
                 para.put("payedTotal", "" + Totl2);
                r = new ReportView("src\\reports\\SalesJTable.jasper", para);
                r.view3(dtm, "src\\reports\\SalesJTable.jrxml");
            } else  if(!txtCustomerName.getSelectedItem().toString().equals("__Choose__")) {
                para.put("dayDate", txtDate.getText()+": "+btnPrint.getText());
                para.put("Total", "" + Totl);
                 para.put("payedTotal", "" + Totl2);
                r = new ReportView("src\\reports\\SalesJTable.jasper", para);
                r.view3(dtm, "src\\reports\\SalesJTable.jrxml");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_btnPrintActionPerformed

    private void txtMonth1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMonth1ActionPerformed
        try {
            if(!txtCustomerName.getSelectedItem().equals("__Choose__"))
            {
                
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_txtMonth1ActionPerformed

    private void txtCustomerNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCustomerNameActionPerformed
        String localDate;
        try {
            if (txtDateSearch.getDate() != null) {
                localDate = df.format(txtDateSearch.getDate());
            } else if(txtMonth1.getSelectedIndex()!=0){
                localDate=txtYear1.getSelectedItem().toString()+"-"+txtMonth1.getSelectedItem().toString()+"___";
            }else 
            {
                localDate = txtDate.getText();
            }
            if (txtCheckDate.isSelected() && txtCeckCustomername.isSelected() && txtCheckState.isSelected()) {
                data_LoadForCustomername(txtCustomerName.getSelectedItem().toString(), localDate, txtState.getSelectedItem().toString());
                btnPrint.setText("تقرير: "+txtCustomerName.getSelectedItem().toString());
            } else if (txtCheckDate.isSelected() && txtCeckCustomername.isSelected()) {
                data_LoadForCustomername(txtCustomerName.getSelectedItem().toString(), localDate, "");
                btnPrint.setText("تقرير: "+txtCustomerName.getSelectedItem().toString());
            } else if (txtCeckCustomername.isSelected() && txtCheckState.isSelected()) {
                data_LoadForCustomername(txtCustomerName.getSelectedItem().toString(), "", txtState.getSelectedItem().toString());
                btnPrint.setText("تقرير: "+txtCustomerName.getSelectedItem().toString());
            } else if (txtCeckCustomername.isSelected() && txtMonth1.getSelectedIndex()!=0)  {
                data_LoadForCustomername(String.valueOf(txtCustomerName.getSelectedItem()),txtYear1.getSelectedItem().toString()+"-"+txtMonth1.getSelectedItem().toString(), "");
                btnPrint.setText("تقرير: "+txtCustomerName.getSelectedItem().toString());
               setMonthlyTotal();
            }else if (txtCeckCustomername.isSelected())  {
                data_LoadForCustomername(String.valueOf(txtCustomerName.getSelectedItem()),todayDate, "");
                btnPrint.setText("تقرير: "+txtCustomerName.getSelectedItem().toString());
                
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_txtCustomerNameActionPerformed

    private void txtStateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtStateActionPerformed
        String localDate;
        try {
            if (txtCheckDate.isSelected() && txtDateSearch.getDate() != null) {
                localDate = df.format(txtDateSearch.getDate());
            } else {
                localDate = txtDate.getText();
            }
            if (txtCeckCustomername.isSelected()) {
                data_LoadForCustomername(txtCustomerName.getSelectedItem().toString(), localDate, txtState.getSelectedItem().toString());
            } else {
                data_LoadForCustomername("", localDate, txtState.getSelectedItem().toString());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_txtStateActionPerformed

    private void txtCeckCustomernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCeckCustomernameActionPerformed
        if (CheckboxState2 == false) {
            CheckboxState2 = true;
            txtCustomerName.setEnabled(true);
        } else {
            CheckboxState2 = false;
            txtCustomerName.setEnabled(false);
            txtCustomerName.setSelectedIndex(0);
        }
    }//GEN-LAST:event_txtCeckCustomernameActionPerformed

    private void txtCheckStateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCheckStateActionPerformed
        if (CheckboxState3 == false) {
            CheckboxState3 = true;
            txtState.setEnabled(true);
        } else {
            CheckboxState3 = false;
            txtState.setEnabled(false);
        }
    }//GEN-LAST:event_txtCheckStateActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField InvID;
    private javax.swing.JButton btnGetDailyTotal;
    private javax.swing.JButton btnPrint;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnSearch;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JCheckBox txtCeckCustomername;
    private javax.swing.JCheckBox txtCheckDate;
    private javax.swing.JCheckBox txtCheckEmployee;
    private javax.swing.JCheckBox txtCheckInvID;
    private javax.swing.JCheckBox txtCheckState;
    private javax.swing.JComboBox<String> txtCustomerName;
    private javax.swing.JLabel txtDailyCaret;
    private javax.swing.JLabel txtDate;
    private javax.swing.JLabel txtDate1;
    private javax.swing.JLabel txtDate2;
    private com.toedter.calendar.JDateChooser txtDateSearch;
    private javax.swing.JLabel txtDateTime;
    private javax.swing.JComboBox<String> txtEmployee;
    private javax.swing.JLabel txtLabelEmp;
    private javax.swing.JComboBox<String> txtMonth;
    private javax.swing.JComboBox<String> txtMonth1;
    private javax.swing.JLabel txtMonthlyCaret;
    private javax.swing.JComboBox<String> txtState;
    private javax.swing.JComboBox<String> txtYear;
    private javax.swing.JComboBox<String> txtYear1;
    // End of variables declaration//GEN-END:variables
}
