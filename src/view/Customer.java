package view;

import controller.customerController;
import model.customers;
import TOOLS.TableCustom;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;


public class Customer extends javax.swing.JPanel {

    
    boolean verify=false;
    public Customer() {
        initComponents();
        getAllRecords();
        txtAllRemained.setEnabled(false);
        btnUpdate.setEnabled(false);
        TOOLS.TableCustom.apply(jScrollPane1, TableCustom.TableType.MULTI_LINE);
    }

        public void getAllRecords()
    {
        try {
             DefaultTableModel dtm=(DefaultTableModel)jTable1.getModel();
            dtm.setRowCount(0);
            ArrayList<customers> list=customerController.getAllCustomersRecords();
            Iterator<customers>itr=list.iterator();
            while(itr.hasNext())
            {
                customers cus=itr.next();
                dtm.addRow(new Object[]
                {
                    cus.getCustomerID(),
                    cus.getCustomerName(),
                    cus.getCustomerType(),
                    cus.getPhone(),
                    cus.getAddress(),
                    cus.getAllRemained()
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    public void getAllRecordsWithname(String name)
    {
        try {
             DefaultTableModel dtm=(DefaultTableModel)jTable1.getModel();
            dtm.setRowCount(0);
            ArrayList<customers> list=customerController.getAllCustomersByCustomerName(name);
            Iterator<customers>itr=list.iterator();
            while(itr.hasNext())
            {
                customers cus=itr.next();
                dtm.addRow(new Object[]
                {
                  cus.getCustomerID(),
                    cus.getCustomerName(),
                    cus.getCustomerType(),
                    cus.getPhone(),
                    cus.getAddress(),
                    cus.getAllRemained()
                });
                txtID.setText(String.valueOf(cus.getCustomerID()));
                txtCustomername.setText(cus.getCustomerName());
                txtPhone.setText(cus.getPhone());
                txtCustomerType.setSelectedItem(cus.getCustomerType());
                txtAddress.setText(cus.getAddress());
                txtAllRemained.setText(String.valueOf(cus.getAllRemained()));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
      public void getAllRecordsWithphone(String phone)
    {
        try {
             DefaultTableModel dtm=(DefaultTableModel)jTable1.getModel();
            dtm.setRowCount(0);
            ArrayList<customers> list=customerController.getAllCustomersByCustomerPhone(phone);
            Iterator<customers>itr=list.iterator();
            while(itr.hasNext())
            {
                customers cus=itr.next();
                dtm.addRow(new Object[]
                {
         cus.getCustomerID(),
                    cus.getCustomerName(),
                    cus.getCustomerType(),
                    cus.getPhone(),
                    cus.getAddress(),
                    cus.getAllRemained()
                });
                txtID.setText(String.valueOf(cus.getCustomerID()));
                txtCustomername.setText(cus.getCustomerName());
                txtPhone.setText(cus.getPhone());
                txtAddress.setText(cus.getAddress());
                txtCustomerType.setSelectedItem(cus.getCustomerType());
                txtAllRemained.setText(String.valueOf(cus.getAllRemained()));
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
        txtCustomername = new javax.swing.JTextField();
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
        jLabel7 = new javax.swing.JLabel();
        txtCustomerType = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();

        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(0, 153, 153));

        jPanel2.setBackground(new java.awt.Color(0, 153, 153));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("اسم العميل:");

        txtCustomername.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtCustomername.setForeground(new java.awt.Color(0, 0, 0));
        txtCustomername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCustomernameActionPerformed(evt);
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

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("نوع العميل:");

        txtCustomerType.setBackground(new java.awt.Color(255, 255, 255));
        txtCustomerType.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        txtCustomerType.setForeground(new java.awt.Color(0, 0, 0));
        txtCustomerType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "عادي", "تجاري" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnSearch1)
                            .addComponent(btnVerify, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtCustomername, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel1))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtAllRemained, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel6))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtPhone, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
                                    .addComponent(txtCustomerType, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel2))
                                .addGap(5, 5, 5))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnSave)
                        .addGap(33, 33, 33)
                        .addComponent(btnUpdate)
                        .addGap(37, 37, 37)
                        .addComponent(btnDelete)
                        .addGap(29, 29, 29)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtID))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCustomername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtCustomerType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(btnSearch1))
                .addGap(2, 2, 2)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAllRemained, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(btnVerify))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave)
                    .addComponent(btnUpdate)
                    .addComponent(btnDelete))
                .addContainerGap(80, Short.MAX_VALUE))
        );

        jTable1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "اسم العميل", "نوع العميل", "الرقم", "العنوان", "المتبقي"
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
        jLabel4.setText("ادارة العملاء");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 751, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(556, 556, 556)
                        .addComponent(jLabel4)))
                .addContainerGap(63, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel4)
                .addGap(44, 44, 44)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(66, Short.MAX_VALUE))
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        try {
           customers cus = new customers();
            cus.setCustomerName(txtCustomername.getText());
            cus.setPhone(txtPhone.getText());
            cus.setAddress(txtAddress.getText());
            if(txtAllRemained.isEnabled())
            {
                cus.setAllRemained(Double.valueOf(txtAllRemained.getText()));
            }else 
            {
                cus.setAllRemained(0);
            }
            cus.setCustomerType(txtCustomerType.getSelectedItem().toString());
            customerController.addCustomer(cus);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        getAllRecords();
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        try {
           customers cus = new customers();
           cus.setCustomerID(Integer.valueOf(txtID.getText()));
            cus.setCustomerName(txtCustomername.getText());
            cus.setPhone(txtPhone.getText());
            cus.setAddress(txtAddress.getText());
            cus.setAllRemained(Double.valueOf(txtAllRemained.getText()));
            cus.setCustomerType(""+txtCustomerType.getSelectedItem());
            customerController.updateCustomer(cus);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        getAllRecords();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        int id = Integer.parseInt(txtID.getText());
        customerController.deleteCustomer(id);
        getAllRecords();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        try {
            int index = jTable1.getSelectedRow();
            TableModel tm = jTable1.getModel();
            txtID.setText(tm.getValueAt(index, 0).toString());
            txtCustomername.setText(tm.getValueAt(index, 1).toString());
            txtPhone.setText(tm.getValueAt(index, 2).toString());
            txtAddress.setText(tm.getValueAt(index, 3).toString());
            txtCustomerType.setSelectedItem(tm.getValueAt(index,4).toString());
            btnUpdate.setEnabled(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void btnSearch1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearch1ActionPerformed
        getAllRecordsWithphone(txtPhone.getText());
        if(!txtID.getText().equals("0"))
        {
            btnUpdate.setEnabled(true);
        }
    }//GEN-LAST:event_btnSearch1ActionPerformed

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
//       getAllRecords();
    }//GEN-LAST:event_formComponentShown

    private void txtAllRemainedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAllRemainedActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAllRemainedActionPerformed

    private void btnVerifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerifyActionPerformed
        try {
            if(verify)
            {
                verify=false;
                txtAllRemained.setEnabled(true);
                btnVerify.setText("تعطيل");
                btnVerify.setIcon(new ImageIcon("src/Images/small Close.png"));
            }else 
            {
                verify=true;
                txtAllRemained.setEnabled(false);
                btnVerify.setText("تفعيل");
                btnVerify.setIcon(new ImageIcon("src/Images/verify users.png"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_btnVerifyActionPerformed

    private void txtCustomernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCustomernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCustomernameActionPerformed

    private void txtAllRemainedKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAllRemainedKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAllRemainedKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSearch1;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton btnVerify;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtAllRemained;
    private javax.swing.JComboBox<String> txtCustomerType;
    private javax.swing.JTextField txtCustomername;
    private javax.swing.JLabel txtID;
    private javax.swing.JTextField txtPhone;
    // End of variables declaration//GEN-END:variables
}
