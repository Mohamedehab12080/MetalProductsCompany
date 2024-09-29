
package view;

import Database.dbOperations;
import TOOLS.TableCustom;
import controller.cabinetCruds;
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
import javax.swing.table.TableModel;
import model.capenat;


public class capenatView extends javax.swing.JPanel {

    
    String todayDate="";
    SimpleDateFormat df;
    String casherName="";
    Timer t;
    SimpleDateFormat timeFormat;
    public capenatView(String casherName) {
        initComponents();
        this.casherName=casherName;
        df = new SimpleDateFormat("yyyy-MM-dd", new Locale("en"));
        todayDate = df.format(new Date());
        txtDate.setText(todayDate);
        txtCasher.setText(casherName);
        getLastRecord(todayDate);
        getLiveTime();
        TOOLS.TableCustom.apply(jScrollPane1, TableCustom.TableType.MULTI_LINE);
    }
     public capenatView() {
        initComponents();
        df = new SimpleDateFormat("yyyy-MM-dd", new Locale("en"));
        todayDate = df.format(new Date());
        txtDate.setText(todayDate);
        getLastRecord(todayDate);
        getLiveTime();
        TOOLS.TableCustom.apply(jScrollPane1, TableCustom.TableType.MULTI_LINE);
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
    public void getLastRecord(String date)
    {
        try {
            DefaultTableModel dtm=(DefaultTableModel)jTable1.getModel();
            dtm.setRowCount(0);
            ArrayList<capenat>list=cabinetCruds.getLastUpdate(date);
            Iterator<capenat>itr=list.iterator();
            while(itr.hasNext())
            {
                capenat cab=itr.next();
                dtm.addRow(new Object[]
                {
                    cab.getCabinetId(),
                    cab.getCabinetRealValue(),
                    cab.getEditedValue(),
                    cab.getAfterEdited(),
                    cab.getOperation(),
                    cab.getDate()
                });
                txtId.setText(cab.getCabinetId()+"");
                txtOperationType.setText(cab.getOperation());
                txtCabinetbefore.setText(""+cab.getAfterEdited());
          if(dtm.getRowCount()!=0)
          {
                 txtCapenatBeforTable.setText(dtm.getValueAt(0,1).toString());
            txtCapenatAfterTable.setText(dtm.getValueAt(dtm.getRowCount()-1,3).toString());
            if((Double.valueOf(txtCapenatAfterTable.getText())-Double.valueOf(txtCapenatBeforTable.getText()))>=0.0)
              {
                 txtStateVariable.setText("توتال الزيادة: ");
                 txtGainedTable.setText(""+(Double.valueOf(txtCapenatAfterTable.getText())-Double.valueOf(txtCapenatBeforTable.getText())));
              }else
                {
                    txtStateVariable.setText("توتال النقص: ");
                     txtGainedTable.setText(""+((Double.valueOf(txtCapenatAfterTable.getText())-Double.valueOf(txtCapenatBeforTable.getText()))*(-1)));
                }
          }else 
             {
                  ResultSet rs=dbOperations.getData("select * from capenat where id=(select max(id) from capenat)");
                  if(rs.next())
                  {
                      dtm.addRow(new Object[]
                      {
                           rs.getInt("id"),
                            rs.getDouble("realValue"),
                            rs.getDouble("editedvalue"),
                            rs.getDouble("afterEdictvalue"),
                            rs.getString("OperationType"),
                            rs.getString("date")
                      });
                  }
             }
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
        txtCabinetbefore = new javax.swing.JLabel();
        txtCabinatAfterEdit = new javax.swing.JLabel();
        txteditValue = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnAdd = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtDate = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtId = new javax.swing.JLabel();
        txtOperationType = new javax.swing.JTextField();
        txtType = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        txtDateSearch = new com.toedter.calendar.JDateChooser();
        btnSearch = new javax.swing.JButton();
        txtMonth = new javax.swing.JComboBox<>();
        txtYear = new javax.swing.JComboBox<>();
        btnPrint = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtCapenatBeforTable = new javax.swing.JLabel();
        txtCapenatAfterTable = new javax.swing.JLabel();
        txtGainedTable = new javax.swing.JLabel();
        txtStateVariable = new javax.swing.JLabel();
        txtCasher = new javax.swing.JLabel();

        setBackground(new java.awt.Color(0, 153, 204));

        jPanel1.setBackground(new java.awt.Color(0, 153, 204));

        jPanel2.setBackground(new java.awt.Color(0, 153, 153));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("الخزنة قبل التعديل:");

        txtCabinetbefore.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        txtCabinetbefore.setForeground(new java.awt.Color(0, 0, 0));
        txtCabinetbefore.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtCabinetbefore.setText("0.0");

        txtCabinatAfterEdit.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        txtCabinatAfterEdit.setForeground(new java.awt.Color(0, 0, 0));
        txtCabinatAfterEdit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtCabinatAfterEdit.setText("0.0");

        txteditValue.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txteditValue.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txteditValue.setText("0");
        txteditValue.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txteditValueKeyReleased(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("الخزنة بعد التعديل:");

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("القيمة:");

        btnAdd.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/salary.png"))); // NOI18N
        btnAdd.setText("حفظ");
        btnAdd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("التاريخ:");

        txtDate.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        txtDate.setForeground(new java.awt.Color(0, 0, 0));
        txtDate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("العملية:");

        txtId.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        txtId.setForeground(new java.awt.Color(0, 0, 0));
        txtId.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtId.setText("0");

        txtOperationType.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        txtType.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        txtType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "عملية", "توريد", "سحب" }));
        txtType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTypeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtType, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtOperationType, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txtCabinetbefore, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                                .addComponent(txteditValue)
                                .addComponent(txtCabinatAfterEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel5)
                            .addComponent(jLabel1)
                            .addComponent(jLabel6)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(txtId, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(94, 94, 94)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtId)
                            .addComponent(txtOperationType, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtCabinetbefore))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txteditValue, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(8, 8, 8)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtCabinatAfterEdit))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAdd)
                .addContainerGap(137, Short.MAX_VALUE))
        );

        jTable1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "الخزنة قبل التعديل", "القيمة", "الخزنة بعد التعديل", "نوع العملية", "تاريخ التعديل"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, false
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
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/cabinet.png"))); // NOI18N
        jLabel4.setText("الخزنة");

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

        btnSearch.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/small Search.png"))); // NOI18N
        btnSearch.setText("بحث");
        btnSearch.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        txtMonth.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtMonth.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "شهر", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        txtMonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMonthActionPerformed(evt);
            }
        });

        txtYear.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtYear.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030" }));

        btnPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/reports.png"))); // NOI18N
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("الخزنة قبل:");

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("الخزنة بعد:");

        txtCapenatBeforTable.setBackground(new java.awt.Color(102, 102, 102));
        txtCapenatBeforTable.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        txtCapenatBeforTable.setForeground(new java.awt.Color(255, 255, 255));
        txtCapenatBeforTable.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtCapenatBeforTable.setText("0.0");
        txtCapenatBeforTable.setOpaque(true);

        txtCapenatAfterTable.setBackground(new java.awt.Color(102, 102, 102));
        txtCapenatAfterTable.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        txtCapenatAfterTable.setForeground(new java.awt.Color(255, 255, 255));
        txtCapenatAfterTable.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtCapenatAfterTable.setText("0.0");
        txtCapenatAfterTable.setOpaque(true);

        txtGainedTable.setBackground(new java.awt.Color(102, 102, 102));
        txtGainedTable.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        txtGainedTable.setForeground(new java.awt.Color(255, 255, 255));
        txtGainedTable.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtGainedTable.setText("0.0");
        txtGainedTable.setOpaque(true);

        txtStateVariable.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        txtStateVariable.setForeground(new java.awt.Color(0, 0, 0));
        txtStateVariable.setText("__");

        txtCasher.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        txtCasher.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 820, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(405, 405, 405)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(txtCasher, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnPrint)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtDateSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSearch)
                        .addGap(32, 32, 32)
                        .addComponent(txtMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtYear, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtCapenatBeforTable, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)
                        .addGap(535, 535, 535))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtGainedTable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtCapenatAfterTable, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel9))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(txtStateVariable, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(319, 319, 319))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtDateSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtYear, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(btnPrint, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(txtCasher, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtCapenatBeforTable))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtCapenatAfterTable))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtStateVariable)
                    .addComponent(txtGainedTable)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(43, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        try {
            int index = jTable1.getSelectedRow();
            TableModel tm = jTable1.getModel();
            txtId.setText(tm.getValueAt(index, 0).toString());
            txtCabinetbefore.setText(tm.getValueAt(index, 1).toString());
            txteditValue.setText(tm.getValueAt(index, 2).toString());
            txtCabinatAfterEdit.setText(tm.getValueAt(index, 3).toString());
            txtOperationType.setText(tm.getValueAt(index, 4).toString());           
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void txtDateSearchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDateSearchFocusGained

    }//GEN-LAST:event_txtDateSearchFocusGained

    private void txtDateSearchPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtDateSearchPropertyChange

    }//GEN-LAST:event_txtDateSearchPropertyChange

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        try {
            DefaultTableModel dtm=(DefaultTableModel) jTable1.getModel();
            dtm.setRowCount(0);
            ResultSet rs=dbOperations.getData("select * from capenat where date like'"+df.format(txtDateSearch.getDate())+"____________%'");
            while(rs.next())
            {
                dtm.addRow(new Object[]
                {
                    rs.getInt("id"),
                    rs.getDouble("realValue"),
                    rs.getDouble("editedvalue"),
                    rs.getDouble("afterEdictvalue"),
                    rs.getString("OperationType"),
                    rs.getString("date")
                });
            }
          if(dtm.getRowCount()!=0)
          {
                 txtCapenatBeforTable.setText(dtm.getValueAt(0,1).toString());
            txtCapenatAfterTable.setText(dtm.getValueAt(dtm.getRowCount()-1,3).toString());
            if((Double.valueOf(txtCapenatAfterTable.getText())-Double.valueOf(txtCapenatBeforTable.getText()))>1)
              {
                 txtStateVariable.setText("توتال الزيادة: ");
                 txtGainedTable.setText(""+(Double.valueOf(txtCapenatAfterTable.getText())-Double.valueOf(txtCapenatBeforTable.getText())));
              }else 
            {
                txtStateVariable.setText("توتال النقص: ");
                 txtGainedTable.setText(""+((Double.valueOf(txtCapenatAfterTable.getText())-Double.valueOf(txtCapenatBeforTable.getText()))*(-1)));
            }
          }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
       if(txtType.getSelectedItem().toString().equals("عملية"))
       {
            try {
           if(!txteditValue.getText().equals("0"))
           {
                capenat cap=new capenat();
            cap.setCabinetRealValue(Double.valueOf(txtCabinetbefore.getText()));
            cap.setEditedValue(Double.valueOf(txteditValue.getText()));
            if(txtType.getSelectedItem().equals("توريد"))
            {
                cap.setAfterEdited(Double.valueOf(txtCabinetbefore.getText())+Double.valueOf(txteditValue.getText()));
            }else 
            {
                 cap.setAfterEdited(Double.valueOf(txtCabinetbefore.getText())-Double.valueOf(txteditValue.getText()));
            }
            cap.setOperation(txtType.getSelectedItem().toString() +"_"+txtOperationType.getText()+"كاشير: "+txtCasher.getText());
            cap.setDate(txtDate.getText());
            cabinetCruds.addCabinetValue(cap);
            getLastRecord(todayDate);
           }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
       }else 
       {
           JOptionPane.showMessageDialog(null,"قم بتحديد نوع العملية");
       }
    }//GEN-LAST:event_btnAddActionPerformed

    private void txteditValueKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txteditValueKeyReleased
        try {
            double values=Double.valueOf(txteditValue.getText());
           if(values>1)
           {
               if(txtType.getSelectedItem().equals("توريد"))
           {
                txtCabinatAfterEdit.setText((Double.valueOf(txtCabinetbefore.getText())+values)+"");
            }else 
           {
                txtCabinatAfterEdit.setText((Double.valueOf(txtCabinetbefore.getText())-values)+"");
           }
               btnAdd.setEnabled(true);
           }else 
           {
               btnAdd.setEnabled(false);
           }
        } catch (NumberFormatException e) {
            txtCabinatAfterEdit.setText("0");
            btnAdd.setEnabled(false);
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_txteditValueKeyReleased

    private void txtMonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMonthActionPerformed
         try {
             txtDateSearch.setDate(null);
            DefaultTableModel dtm=(DefaultTableModel) jTable1.getModel();
            dtm.setRowCount(0);
            ResultSet rs=dbOperations.getData("select * from capenat where date like'"+txtYear.getSelectedItem().toString()+"-"+txtMonth.getSelectedItem().toString()+"_______________%'");
            while(rs.next())
            {
                dtm.addRow(new Object[]
                {
                    rs.getInt("id"),
                    rs.getDouble("realValue"),
                    rs.getDouble("editedvalue"),
                    rs.getDouble("afterEdictvalue"),
                    rs.getString("OperationType"),
                    rs.getString("date")
                });
            }
            
          if(dtm.getRowCount()!=0)
          {
                txtCapenatBeforTable.setText(dtm.getValueAt(0,1).toString());
            txtCapenatAfterTable.setText(dtm.getValueAt(dtm.getRowCount()-1,3).toString());
            if((Double.valueOf(txtCapenatAfterTable.getText())-Double.valueOf(txtCapenatBeforTable.getText()))>1)
              {
                 txtStateVariable.setText("توتال الزيادة: ");
                 txtGainedTable.setText(""+(Double.valueOf(txtCapenatAfterTable.getText())-Double.valueOf(txtCapenatBeforTable.getText())));
              }else 
            {
                txtStateVariable.setText("توتال النقص: ");
                 txtGainedTable.setText(""+((Double.valueOf(txtCapenatAfterTable.getText())-Double.valueOf(txtCapenatBeforTable.getText()))*(-1)));
            }
          }
           
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_txtMonthActionPerformed

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        try {
             HashMap para = new HashMap();
               if(!txtMonth.getSelectedItem().toString().equals("شهر"))
               {
                    para.put("Inv_date",txtYear.getSelectedItem().toString()+"-"+txtMonth.getSelectedItem().toString()+"_______________%");
               }else if(txtDateSearch.getDate()!=null)
               {
                   para.put("Inv_date",df.format(txtDateSearch.getDate())+"____________%");
               }
                ReportView r = new ReportView("src\\reports\\capenatReport.jasper", para);
                r.view();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_btnPrintActionPerformed

    private void txtTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTypeActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnPrint;
    private javax.swing.JButton btnSearch;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel txtCabinatAfterEdit;
    private javax.swing.JLabel txtCabinetbefore;
    private javax.swing.JLabel txtCapenatAfterTable;
    private javax.swing.JLabel txtCapenatBeforTable;
    private javax.swing.JLabel txtCasher;
    private javax.swing.JLabel txtDate;
    private com.toedter.calendar.JDateChooser txtDateSearch;
    private javax.swing.JLabel txtGainedTable;
    private javax.swing.JLabel txtId;
    private javax.swing.JComboBox<String> txtMonth;
    private javax.swing.JTextField txtOperationType;
    private javax.swing.JLabel txtStateVariable;
    private javax.swing.JComboBox<String> txtType;
    private javax.swing.JComboBox<String> txtYear;
    private javax.swing.JTextField txteditValue;
    // End of variables declaration//GEN-END:variables
}
