package view;

import Database.dbOperations;
import TOOLS.TableCustom;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.TimeZone;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class buysView extends javax.swing.JPanel {

    SimpleDateFormat timeFormat;
    SimpleDateFormat df;
    public Timer t;
    String todayDate;

    public buysView() {
        initComponents();
        df = new SimpleDateFormat("yyyy-MM-dd", new Locale("en"));
        Date date = new Date();
        todayDate = df.format(date);
        getLiveTime();
        LoadData();
        getAllRecordsOpen();
        getEmpnames();
        TOOLS.TableCustom.apply(jScrollPane1, TableCustom.TableType.MULTI_LINE);
        TOOLS.TableCustom.apply(jScrollPane3, TableCustom.TableType.MULTI_LINE);
    }

    public buysView(String casherName) {
        initComponents();
        df = new SimpleDateFormat("yyyy-MM-dd", new Locale("en"));
        Date date = new Date();
        todayDate = df.format(date);
        txtCasherName.setText(casherName);
        LoadData();
        getLiveTime();
        getAllRecordsOpen();
        getEmpnames();
        TOOLS.TableCustom.apply(jScrollPane1, TableCustom.TableType.MULTI_LINE);
        TOOLS.TableCustom.apply(jScrollPane3, TableCustom.TableType.MULTI_LINE);
        
    }

    public void getEmpnames()
    {
        try {
            ResultSet rs=dbOperations.getData("select empName from salesbuys");
            while(rs.next())
            {
                txtEmpNameSearch.addItem(rs.getString("empName"));
            }
            ResultSet rs1=dbOperations.getData("select employeeName from employees");
            while(rs1.next())
            {
                txtEmpname.addItem(rs1.getString("employeeName"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    public void getAllRecordsOpen() {
        try {
            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            dtm.setRowCount(0);
            ResultSet rs = dbOperations.getData("select * from salesBuys where date like'" + todayDate + "____________%'");
            while (rs.next()) {
                dtm.addRow(new Object[]{
                    rs.getInt("INID"),
                    rs.getString("empname"),
                    rs.getString("casherName"),
                    rs.getDouble("Total"),
                    rs.getString("Note"),
                    rs.getString("date")
                });

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void getAllRecordsWithDateSearch(String dateSearch) {
        try {
            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            dtm.setRowCount(0);
            ResultSet rs = dbOperations.getData("select * from salesBuys where date like'" + dateSearch + "%'");
            while (rs.next()) {
                dtm.addRow(new Object[]{
                    rs.getInt("INID"),
                    rs.getString("empname"),
                    rs.getString("casherName"),
                    rs.getDouble("Total"),
                    rs.getString("Note"),
                    rs.getString("date")
                });

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void getAllTotal() {
        double val = 0;
        try {
            for (int i = 0; i < jTable2.getRowCount(); i++) {
                val += Double.valueOf(jTable2.getValueAt(i, 3).toString());
            }
            txtTOTAL.setText("" + val);
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

    public void LoadData() {
        try {
            ResultSet rs = dbOperations.getData("select * from extra where exid=4");
            if (rs.next()) {
                txtID.setText(rs.getString("val"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        int i = Integer.valueOf(txtID.getText());
        i++;
        txtID.setText(String.valueOf(i));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        btnSave = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtID = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtPayedValue = new javax.swing.JTextField();
        txtHandDate = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        txtDate = new javax.swing.JLabel();
        txtCasherName = new javax.swing.JLabel();
        btnSave1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JLabel();
        btnSave2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtNote = new javax.swing.JTextArea();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        txtHandDateSearch = new com.toedter.calendar.JDateChooser();
        jButton1 = new javax.swing.JButton();
        txtINIDSearch = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtEmpNameSearch = new javax.swing.JComboBox<>();
        txtMonth = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        txtYear = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        txtEmpname = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        txtTOTAL = new javax.swing.JLabel();

        jPanel2.setBackground(new java.awt.Color(0, 153, 153));

        jPanel3.setBackground(new java.awt.Color(0, 153, 153));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("اسم الخارج:");

        txtName.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtName.setForeground(new java.awt.Color(0, 0, 0));
        txtName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNameActionPerformed(evt);
            }
        });

        btnSave.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSave.setForeground(new java.awt.Color(0, 0, 0));
        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/save.png"))); // NOI18N
        btnSave.setText("اضافة");
        btnSave.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("ID:");

        txtID.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtID.setForeground(new java.awt.Color(0, 0, 0));
        txtID.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtID.setText("0");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("تم دفع:");

        txtPayedValue.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtPayedValue.setForeground(new java.awt.Color(0, 0, 0));
        txtPayedValue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPayedValueActionPerformed(evt);
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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel5)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtPayedValue, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel7)
                                .addComponent(jLabel1)))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(txtHandDate, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel3))))
                .addGap(161, 161, 161))
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
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtPayedValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtHandDate, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        jTable1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "اسم الموظف", "كاشير", "TOTAL", "ملاحظة", "تاريخ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
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
        jLabel4.setText("الخوارج");

        txtDate.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtDate.setForeground(new java.awt.Color(0, 0, 0));

        txtCasherName.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtCasherName.setForeground(new java.awt.Color(0, 0, 0));

        btnSave1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSave1.setForeground(new java.awt.Color(0, 0, 0));
        btnSave1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/small delete.png"))); // NOI18N
        btnSave1.setText("حذف");
        btnSave1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSave1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSave1ActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("TOTAL:");

        txtTotal.setBackground(new java.awt.Color(0, 153, 153));
        txtTotal.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtTotal.setForeground(new java.awt.Color(0, 0, 0));
        txtTotal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtTotal.setText("0.0");
        txtTotal.setOpaque(true);

        btnSave2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSave2.setForeground(new java.awt.Color(0, 0, 0));
        btnSave2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/reports.png"))); // NOI18N
        btnSave2.setText("طباعة");
        btnSave2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSave2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSave2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSave2, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtTotal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(btnSave2, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("ملاحظة:");

        txtNote.setColumns(20);
        txtNote.setRows(5);
        txtNote.setText("__");
        jScrollPane2.setViewportView(txtNote);

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jTable2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "اسم الخارج", "تم دفع"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true
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
        jScrollPane3.setViewportView(jTable2);

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
        jLabel8.setText("رقم الفاتورة:");

        txtEmpNameSearch.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtEmpNameSearch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "اسم الموظف" }));
        txtEmpNameSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmpNameSearchActionPerformed(evt);
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

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtHandDateSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtYear, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(txtEmpNameSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(txtINIDSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addContainerGap(203, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(37, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtHandDateSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(1, 1, 1)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtINIDSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                    .addComponent(txtEmpNameSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(1, 1, 1))))
                        .addGap(1, 1, 1)))
                .addGap(27, 27, 27))
        );

        txtEmpname.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", " " }));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("اسم الموظف:");

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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                            .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(259, 259, 259)
                                .addComponent(jLabel2))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(107, 107, 107)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(76, 76, 76)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(41, 41, 41)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(100, 100, 100)
                                .addComponent(txtEmpname, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)
                                .addComponent(jLabel9)))
                        .addGap(50, 50, 50)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSave1, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 983, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(37, 37, 37)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1187, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtCasherName, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(176, 176, 176)
                        .addComponent(jLabel2)
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(12, 12, 12)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSave1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEmpname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(6, 6, 6)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 900, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(61, 61, 61)
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(173, 173, 173)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 465, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );

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

    private void txtNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNameActionPerformed

    public void getTotalFromTable() {
        double t = 0;
        try {
            for (int i = 0; i < jTable2.getRowCount(); i++) {
                t += Double.valueOf(jTable2.getValueAt(i, 2).toString());
            }
            txtTotal.setText("" + t);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }
    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        try {
            Vector v = new Vector();
            DefaultTableModel dtm = (DefaultTableModel) jTable2.getModel();
            v.add(txtID.getText());
            v.add(txtName.getText());
            v.add(txtPayedValue.getText());
            v.add(txtNote.getText());
            dtm.addRow(v);
            getTotalFromTable();
            txtName.setText("");
            txtPayedValue.setText("");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void txtPayedValueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPayedValueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPayedValueActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        try {
            int index = jTable1.getSelectedRow();
            DefaultTableModel tm = (DefaultTableModel) jTable1.getModel();
            int a = JOptionPane.showConfirmDialog(null, "هل تريد عرض التفاصيل؟", "اختر", JOptionPane.YES_NO_OPTION);
            if (a == 0) {
                HashMap para = new HashMap();
                para.put("invo_para", Integer.parseInt(tm.getValueAt(index,0).toString()));  // inv_id  is ireport parameter name
                ReportView r = new ReportView("src\\reports\\buysCarts.jasper", para);
                r.view();
            } else {
                tm.removeRow(index);
                getTotalFromTable();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void txtHandDateFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtHandDateFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHandDateFocusGained

    private void txtHandDatePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtHandDatePropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHandDatePropertyChange

    private void btnSave1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSave1ActionPerformed
        try {
            DefaultTableModel dtm = (DefaultTableModel) jTable2.getModel();
            int a = JOptionPane.showConfirmDialog(null, "سيتم حذف الجدول", "تحذير", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (a == 0) {
                dtm.setRowCount(0);
                txtTotal.setText("0.0");
                txtName.setText("");
                txtNote.setText("");
                txtHandDate.setDate(null);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_btnSave1ActionPerformed

    private void btnSave2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSave2ActionPerformed
        try {
            String name;
            String date = "";
            if (txtHandDate.getDate() != null) {
                date = df.format(txtHandDate.getDate());
            } else {
                date = txtDate.getText();
            }
            String id;
            String payed;
            for (int i = 0; i < jTable2.getRowCount(); i++) {
                name = jTable2.getValueAt(i, 1).toString();
                id = jTable2.getValueAt(i, 0).toString();
                payed = jTable2.getValueAt(i, 2).toString();
                dbOperations.setDataOrDelete("insert into cartBuys "
                        + "(INID,"
                        + "name,"
                        + "date,"
                        + "payed) values("
                        + id
                        + ",'" + name
                        + "','" + date
                        + "'," + payed + ")", "");
            }
            try {
                String id1 = txtID.getText();
                dbOperations.setDataOrDelete("Update extra set val='" + id1 + "' where exid=4", "");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
            String empName;
            if (txtEmpname.getSelectedIndex() != 0) {
                empName = txtEmpname.getSelectedItem().toString();
            } else {
                empName = "__";
            }
            try {
                dbOperations.setDataOrDelete("insert into salesBuys (INID,"
                        + "casherName,"
                        + "Total,"
                        + "Note,"
                        + "Date,empname) "
                        + "values(" + txtID.getText() + ",'" + txtCasherName.getText() + "'," + txtTotal.getText() + ",'" + txtNote.getText() + "','" + txtDate.getText() + "','" + empName + "')", "");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
            int a = JOptionPane.showConfirmDialog(null, "<html><h1>هل تريد سحب التوتال من الخزنة؟</h1></html>", "اختر", JOptionPane.YES_NO_OPTION);
            if (a == 0) {
                ResultSet rs = dbOperations.getData("select * from capenat where id=(select max(id) from capenat)");
                if (rs.next()) {
                    dbOperations.setDataOrDelete("insert into capenat (realValue,editedvalue,afterEdictvalue,OperationType,date) values ("
                            + rs.getDouble("afterEdictvalue")
                            + "," + txtTotal.getText() + ","
                            + (rs.getDouble("afterEdictvalue") - Double.valueOf(txtTotal.getText()))
                            + ",'سحب خوارج','"
                            + txtDate.getText()
                            + "')", "");
                }
            }
            try {
                HashMap para = new HashMap();
                para.put("invo_para", Integer.parseInt(txtID.getText()));  // inv_id  is ireport parameter name
                ReportView r = new ReportView("src\\reports\\buysCarts.jasper", para);
                r.view();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
            DefaultTableModel dtm = (DefaultTableModel) jTable2.getModel();
            dtm.setRowCount(0);
            txtTotal.setText("0.0");
            txtName.setText("");
            txtNote.setText("");
            txtHandDate.setDate(null);
            txtID.setText("" + (Integer.valueOf(txtID.getText()) + 1));
            getAllRecordsOpen();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_btnSave2ActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable2MouseClicked

    private void txtHandDateSearchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtHandDateSearchFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHandDateSearchFocusGained

    private void txtHandDateSearchPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtHandDateSearchPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHandDateSearchPropertyChange

    private void txtINIDSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtINIDSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtINIDSearchActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            double s = 0;
            HashMap para = new HashMap();
            para.put("title", "تقرير خوارج");
            ReportView r = null;
            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            for (int i = 0; i < jTable1.getRowCount(); i++) {
                s += (Double.valueOf(jTable1.getValueAt(i, 3).toString()));
            }
            if (txtEmpNameSearch.getSelectedIndex() != 0 && txtHandDateSearch.getDate() != null) {
                para.put("dayDate", txtEmpNameSearch.getSelectedItem().toString());  // inv_id  is ireport parameter name
                para.put("Total", "" + s);
                para.put("dateWith", df.format(txtHandDateSearch.getDate()));
                r = new ReportView("src\\reports\\salesBuysJtableReport.jasper", para);
                r.view3(dtm, "src\\reports\\salesBuysJtableReport.jrxml");
            } else if (txtEmpNameSearch.getSelectedIndex() != 0 && txtMonth.getSelectedIndex() != 0) {
                para.put("dayDate", txtEmpNameSearch.getSelectedItem().toString());  // inv_id  is ireport parameter name
                para.put("Total", "" + s);
                para.put("dateWith", txtMonth.getSelectedItem().toString() + "-" + txtYear.getSelectedItem().toString());
                r = new ReportView("src\\reports\\salesBuysJtableReport.jasper", para);
                r.view3(dtm, "src\\reports\\salesBuysJtableReport.jrxml");
            } else if (txtMonth.getSelectedIndex() != 0) {
                para.put("dayDate", txtMonth.getSelectedItem().toString() + "-" + txtYear.getSelectedItem().toString());  // inv_id  is ireport parameter name
                para.put("Total", "" + s);
                para.put("dateWith", "__");
                r = new ReportView("src\\reports\\salesBuysJtableReport.jasper", para);
                r.view3(dtm, "src\\reports\\salesBuysJtableReport.jrxml");
            } else if (txtHandDateSearch.getDate() != null) {
                para.put("dayDate", df.format(txtHandDateSearch.getDate()));  // inv_id  is ireport parameter name
                para.put("Total", "" + s);
                para.put("dateWith", "__");
                r = new ReportView("src\\reports\\salesBuysJtableReport.jasper", para);
                r.view3(dtm, "src\\reports\\salesBuysJtableReport.jrxml");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            getAllRecordsWithDateSearch(df.format(txtHandDateSearch.getDate()) + "____________");
            getTotalFromTable();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtMonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMonthActionPerformed
        try {
            getAllRecordsWithDateSearch( txtYear.getSelectedItem().toString() + "-" + txtMonth.getSelectedItem().toString()+ "_______________");
            txtHandDateSearch.setDate(null);
            getTotalFromTable();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_txtMonthActionPerformed

    private void txtEmpNameSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmpNameSearchActionPerformed
        ResultSet rs;
        try {
            DefaultTableModel dtm = (DefaultTableModel) jTable2.getModel();
            dtm.setRowCount(0);
            if (txtEmpNameSearch.getSelectedIndex() != 0 && txtMonth.getSelectedIndex() != 0) {
                rs = dbOperations.getData("select * from salesBuys where date like'" +txtYear.getSelectedItem().toString() + "-" + txtMonth.getSelectedItem().toString()+ "_______________%' and empname='" + txtEmpNameSearch.getSelectedItem().toString() + "'");
                while (rs.next()) {
                    dtm.addRow(new Object[]{
                        rs.getInt("INID"),
                        rs.getString("empname"),
                        rs.getString("casherName"),
                        rs.getDouble("Total"),
                        rs.getString("Note"),
                        rs.getString("date")
                    });
                }
            } else if (txtEmpNameSearch.getSelectedIndex() != 0 && txtHandDateSearch.getDate() != null) {
                rs = dbOperations.getData("select * from salesBuys where date like'" + df.format(txtHandDateSearch.getDate()) + "____________%' and empname='" + txtEmpNameSearch.getSelectedItem().toString() + "'");
                while (rs.next()) {
                    dtm.addRow(new Object[]{
                        rs.getInt("INID"),
                        rs.getString("empname"),
                        rs.getString("casherName"),
                        rs.getDouble("Total"),
                        rs.getString("Note"),
                        rs.getString("date")
                    });
                }
            } else {
                rs = dbOperations.getData("select * from salesBuys where date like'" + todayDate + "____________%' and empname='" + txtEmpNameSearch.getSelectedItem().toString() + "'");
                while (rs.next()) {
                    dtm.addRow(new Object[]{
                        rs.getInt("INID"),
                        rs.getString("empname"),
                        rs.getString("casherName"),
                        rs.getDouble("Total"),
                        rs.getString("Note"),
                        rs.getString("date")
                    });
                }
            }

            getTotalFromTable();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_txtEmpNameSearchActionPerformed

    private void txtINIDSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtINIDSearchKeyReleased
        try {
            DefaultTableModel dtm = (DefaultTableModel) jTable2.getModel();
            dtm.setRowCount(0);
            ResultSet rs = dbOperations.getData("select * from salesBuys where INID=" + txtINIDSearch.getText());
            while (rs.next()) {
                dtm.addRow(new Object[]{
                    rs.getInt("INID"),
                    rs.getString("empname"),
                    rs.getString("casherName"),
                    rs.getDouble("Total"),
                    rs.getString("Note"),
                    rs.getString("date")
                });
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtINIDSearchKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSave1;
    private javax.swing.JButton btnSave2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
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
    private javax.swing.JLabel txtDate;
    private javax.swing.JComboBox<String> txtEmpNameSearch;
    private javax.swing.JComboBox<String> txtEmpname;
    private com.toedter.calendar.JDateChooser txtHandDate;
    private com.toedter.calendar.JDateChooser txtHandDateSearch;
    private javax.swing.JLabel txtID;
    private javax.swing.JTextField txtINIDSearch;
    private javax.swing.JComboBox<String> txtMonth;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextArea txtNote;
    private javax.swing.JTextField txtPayedValue;
    private javax.swing.JLabel txtTOTAL;
    private javax.swing.JLabel txtTotal;
    private javax.swing.JComboBox<String> txtYear;
    // End of variables declaration//GEN-END:variables
}
