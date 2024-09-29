/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Database.dbOperations;
import java.awt.Button;
import java.awt.Color;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.swing.JOptionPane;

/**
 *
 * @author BOBO
 */
public class pureTOTALView extends javax.swing.JPanel {

    SimpleDateFormat df;
    SimpleDateFormat monthDf;

    public pureTOTALView() {
        initComponents();
        df = new SimpleDateFormat("yyyy-MM-dd", new Locale("en"));
        monthDf = new SimpleDateFormat("yyyy-MM", new Locale("en"));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtSumSales = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        txtClear = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtPayedForSuppliers = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        txtClear1 = new javax.swing.JButton();
        txtPercentageProductsPrice = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtSumBuys = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        txtClear2 = new javax.swing.JButton();
        txtPercentageBuys = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtSumPayedForEmployees = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        txtClear3 = new javax.swing.JButton();
        txtpercentagePayedForEmployees = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        txtSumPayments = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        txtClear4 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        txtSumRemainedCustomers = new javax.swing.JLabel();
        txtpercentageRemainedOut = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        txtNetgain = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        txtClear5 = new javax.swing.JButton();
        txtpercentageAll = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        txtHandDateSearch = new com.toedter.calendar.JDateChooser();
        txtMonth = new javax.swing.JComboBox<>();
        txtYear = new javax.swing.JComboBox<>();

        setBackground(new java.awt.Color(0, 153, 204));
        setLayout(new java.awt.GridBagLayout());

        jPanel1.setBackground(new java.awt.Color(42, 38, 38));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("توتال المبيعات");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 34, 0, 0);
        jPanel1.add(jLabel1, gridBagConstraints);

        txtSumSales.setBackground(new java.awt.Color(0, 153, 204));
        txtSumSales.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        txtSumSales.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtSumSales.setText("0.0");
        txtSumSales.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.ipadx = 126;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 12, 0, 12);
        jPanel1.add(txtSumSales, gridBagConstraints);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/update.png"))); // NOI18N
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 12, 11, 0);
        jPanel1.add(jButton2, gridBagConstraints);

        txtClear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/close Jframe.png"))); // NOI18N
        txtClear.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        txtClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtClearActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.ipadx = 11;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 18, 11, 0);
        jPanel1.add(txtClear, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 74;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 119, 0, 0);
        add(jPanel1, gridBagConstraints);

        jPanel2.setBackground(new java.awt.Color(42, 38, 38));
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("تكلفة المنتجات المباعة");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 22, 0, 0);
        jPanel2.add(jLabel2, gridBagConstraints);

        txtPayedForSuppliers.setBackground(new java.awt.Color(0, 153, 204));
        txtPayedForSuppliers.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        txtPayedForSuppliers.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtPayedForSuppliers.setText("0.0");
        txtPayedForSuppliers.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 148;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 22, 0, 0);
        jPanel2.add(txtPayedForSuppliers, gridBagConstraints);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/update.png"))); // NOI18N
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 25;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 22, 11, 0);
        jPanel2.add(jButton1, gridBagConstraints);

        txtClear1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/close Jframe.png"))); // NOI18N
        txtClear1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        txtClear1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtClear1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 38;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 5, 11, 0);
        jPanel2.add(txtClear1, gridBagConstraints);

        txtPercentageProductsPrice.setBackground(new java.awt.Color(0, 0, 204));
        txtPercentageProductsPrice.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtPercentageProductsPrice.setForeground(new java.awt.Color(255, 255, 255));
        txtPercentageProductsPrice.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtPercentageProductsPrice.setText("0");
        txtPercentageProductsPrice.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 35;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 11, 0, 0);
        jPanel2.add(txtPercentageProductsPrice, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 0, 0);
        add(jPanel2, gridBagConstraints);

        jPanel3.setBackground(new java.awt.Color(42, 38, 38));
        jPanel3.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new java.awt.GridBagLayout());

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("توتال الخوارج");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 55, 0, 0);
        jPanel3.add(jLabel5, gridBagConstraints);

        txtSumBuys.setBackground(new java.awt.Color(0, 153, 204));
        txtSumBuys.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        txtSumBuys.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtSumBuys.setText("0.0");
        txtSumBuys.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.ipadx = 153;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 12, 0, 0);
        jPanel3.add(txtSumBuys, gridBagConstraints);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/update.png"))); // NOI18N
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 32;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 12, 11, 0);
        jPanel3.add(jButton3, gridBagConstraints);

        txtClear2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/close Jframe.png"))); // NOI18N
        txtClear2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        txtClear2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtClear2ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.ipadx = 23;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 18, 11, 0);
        jPanel3.add(txtClear2, gridBagConstraints);

        txtPercentageBuys.setBackground(new java.awt.Color(0, 0, 204));
        txtPercentageBuys.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtPercentageBuys.setForeground(new java.awt.Color(255, 255, 255));
        txtPercentageBuys.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtPercentageBuys.setText("0");
        txtPercentageBuys.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.ipadx = 35;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 6, 0, 2);
        jPanel3.add(txtPercentageBuys, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 0, 15);
        add(jPanel3, gridBagConstraints);

        jPanel4.setBackground(new java.awt.Color(42, 38, 38));
        jPanel4.setForeground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new java.awt.GridBagLayout());

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("توتال المدفوع للموظفين+الايجار");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(14, 17, 0, 0);
        jPanel4.add(jLabel7, gridBagConstraints);

        txtSumPayedForEmployees.setBackground(new java.awt.Color(0, 153, 204));
        txtSumPayedForEmployees.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        txtSumPayedForEmployees.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtSumPayedForEmployees.setText("0.0");
        txtSumPayedForEmployees.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.ipadx = 154;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 17, 0, 0);
        jPanel4.add(txtSumPayedForEmployees, gridBagConstraints);

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/update.png"))); // NOI18N
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.ipadx = 32;
        gridBagConstraints.ipady = -5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 17, 24, 0);
        jPanel4.add(jButton4, gridBagConstraints);

        txtClear3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/close Jframe.png"))); // NOI18N
        txtClear3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        txtClear3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtClear3ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.ipadx = 17;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 25, 24, 0);
        jPanel4.add(txtClear3, gridBagConstraints);

        txtpercentagePayedForEmployees.setBackground(new java.awt.Color(0, 0, 204));
        txtpercentagePayedForEmployees.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtpercentagePayedForEmployees.setForeground(new java.awt.Color(255, 255, 255));
        txtpercentagePayedForEmployees.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtpercentagePayedForEmployees.setText("0");
        txtpercentagePayedForEmployees.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 31;
        gridBagConstraints.ipady = 17;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(14, 11, 0, 3);
        jPanel4.add(txtpercentagePayedForEmployees, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 119, 139, 0);
        add(jPanel4, gridBagConstraints);

        jPanel5.setBackground(new java.awt.Color(42, 38, 38));
        jPanel5.setForeground(new java.awt.Color(255, 255, 255));

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("المدفوع الحقيقي");

        txtSumPayments.setBackground(new java.awt.Color(0, 153, 204));
        txtSumPayments.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        txtSumPayments.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtSumPayments.setText("0.0");
        txtSumPayments.setOpaque(true);

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/update.png"))); // NOI18N
        jButton5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        txtClear4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/close Jframe.png"))); // NOI18N
        txtClear4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        txtClear4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtClear4ActionPerformed(evt);
            }
        });

        jPanel7.setBackground(new java.awt.Color(42, 38, 38));
        jPanel7.setForeground(new java.awt.Color(255, 255, 255));
        jPanel7.setLayout(new java.awt.GridBagLayout());

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("المتبقي بالخارج");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(16, 19, 0, 0);
        jPanel7.add(jLabel10, gridBagConstraints);

        txtSumRemainedCustomers.setBackground(new java.awt.Color(0, 153, 204));
        txtSumRemainedCustomers.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        txtSumRemainedCustomers.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtSumRemainedCustomers.setText("0.0");
        txtSumRemainedCustomers.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.ipadx = 122;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 19, 0, 0);
        jPanel7.add(txtSumRemainedCustomers, gridBagConstraints);

        txtpercentageRemainedOut.setBackground(new java.awt.Color(0, 0, 204));
        txtpercentageRemainedOut.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtpercentageRemainedOut.setForeground(new java.awt.Color(255, 255, 255));
        txtpercentageRemainedOut.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtpercentageRemainedOut.setText("0");
        txtpercentageRemainedOut.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 39;
        gridBagConstraints.ipady = 17;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(17, 6, 0, 0);
        jPanel7.add(txtpercentageRemainedOut, gridBagConstraints);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSumPayments, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtClear4, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel9)
                        .addGap(14, 14, 14)))
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSumPayments)
                        .addGap(6, 6, 6)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton5)
                            .addComponent(txtClear4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 0, 0);
        add(jPanel5, gridBagConstraints);

        jPanel6.setBackground(new java.awt.Color(42, 38, 38));
        jPanel6.setForeground(new java.awt.Color(255, 255, 255));
        jPanel6.setLayout(new java.awt.GridBagLayout());

        jLabel11.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("المكسب الصافي");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 42, 0, 0);
        jPanel6.add(jLabel11, gridBagConstraints);

        txtNetgain.setBackground(new java.awt.Color(0, 153, 204));
        txtNetgain.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        txtNetgain.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtNetgain.setText("0.0");
        txtNetgain.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.ipadx = 149;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 12, 0, 0);
        jPanel6.add(txtNetgain, gridBagConstraints);

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/update.png"))); // NOI18N
        jButton6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 25;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 12, 13, 0);
        jPanel6.add(jButton6, gridBagConstraints);

        txtClear5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/close Jframe.png"))); // NOI18N
        txtClear5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        txtClear5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtClear5ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.ipadx = 19;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 25, 13, 0);
        jPanel6.add(txtClear5, gridBagConstraints);

        txtpercentageAll.setBackground(new java.awt.Color(0, 0, 204));
        txtpercentageAll.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtpercentageAll.setForeground(new java.awt.Color(255, 255, 255));
        txtpercentageAll.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtpercentageAll.setText("0");
        txtpercentageAll.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.ipadx = 34;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 6, 0, 0);
        jPanel6.add(txtpercentageAll, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 170;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 6, 139, 0);
        add(jPanel6, gridBagConstraints);

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

        txtMonth.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtMonth.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "شهر", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        txtMonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMonthActionPerformed(evt);
            }
        });

        txtYear.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtYear.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030" }));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(txtMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(txtYear, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 33, Short.MAX_VALUE))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(txtHandDateSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtHandDateSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtMonth, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtYear, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 33;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(250, 119, 0, 0);
        add(jPanel8, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void txtHandDateSearchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtHandDateSearchFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHandDateSearchFocusGained

    private void txtHandDateSearchPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtHandDateSearchPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHandDateSearchPropertyChange

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            if (txtMonth.getSelectedIndex() == 0 && txtHandDateSearch.getDate() == null) {
                ResultSet rs = dbOperations.getData("select sum(Total_price) as Total_price from cart where date like'" + txtYear.getSelectedItem().toString() + "__________________%'");
                if (rs.next()) {
                    txtSumSales.setText("" + rs.getDouble("Total_price"));
                }
              

            } else if (txtMonth.getSelectedIndex() != 0) {

                ResultSet rs = dbOperations.getData("select sum(Total_price) as Total_price from cart where date like'" + txtYear.getSelectedItem().toString() + "-" + txtMonth.getSelectedItem().toString() + "_______________%'");
                if (rs.next()) {
                    txtSumSales.setText("" + rs.getDouble("Total_price"));
                }
                
            } else {
                ResultSet rs = dbOperations.getData("select sum(Total_price) as Total_price from cart where date like'" + (df.format(txtHandDateSearch.getDate())) + "____________%'");
                if (rs.next()) {
                    txtSumSales.setText("" + rs.getDouble("Total_price"));
                }
                
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            if (txtMonth.getSelectedIndex() == 0 && txtHandDateSearch.getDate() == null) {
                ResultSet rs = dbOperations.getData("select  sum(priceOnStore) as priceOnStore from cart where date like'" + txtYear.getSelectedItem().toString() + "__________________%'");
                if (rs.next()) {
                    txtPayedForSuppliers.setText(rs.getDouble("priceOnStore") + "");
                }
                

            } else if (txtMonth.getSelectedIndex() != 0) {
                ResultSet rs = dbOperations.getData("select sum(priceOnStore) as priceOnStore from cart where date like'" + txtYear.getSelectedItem().toString() + "-" + txtMonth.getSelectedItem().toString() + "_______________%'");
                if (rs.next()) {
                    txtPayedForSuppliers.setText(rs.getDouble("priceOnStore") + "");
                }
                
            } else {
                ResultSet rs = dbOperations.getData("select sum(priceOnStore) as priceOnStore from cart where date like'" + (df.format(txtHandDateSearch.getDate())) + "____________%'");
                if (rs.next()) {
                    txtPayedForSuppliers.setText(rs.getDouble("priceOnStore") + "");
                }
                
            }
            if (!txtSumSales.getText().equals("0.0")) {
                txtPercentageProductsPrice.setText(String.format("%.2f", ((Double.valueOf(txtPayedForSuppliers.getText()) / (Double.valueOf(txtSumSales.getText()))) * 100)) + " %");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            if (txtMonth.getSelectedIndex() == 0 && txtHandDateSearch.getDate() == null) {
                ResultSet rs = dbOperations.getData("select sum(Total) as Total from salesbuys where date like'" + txtYear.getSelectedItem().toString() + "__________________%'");
                if (rs.next()) {
                    txtSumBuys.setText("" + rs.getDouble("Total"));
                }
                
            } else if (txtMonth.getSelectedIndex() != 0) {

                ResultSet rs = dbOperations.getData("select sum(Total) as Total from salesbuys where date like'" + txtYear.getSelectedItem().toString() + "-" + txtMonth.getSelectedItem().toString() + "_______________%'");
                if (rs.next()) {
                    txtSumBuys.setText("" + rs.getDouble("Total"));
                }
                
            } else {
                ResultSet rs = dbOperations.getData("select sum(Total) as Total from salesbuys where date like'" + (df.format(txtHandDateSearch.getDate())) + "____________%'");
                if (rs.next()) {
                    txtSumBuys.setText("" + rs.getDouble("Total"));
                }
               
            }
            if (!txtSumSales.getText().equals("0.0")) {
                txtPercentageBuys.setText(String.format("%.2f", ((Double.valueOf(txtSumBuys.getText()) / (Double.valueOf(txtSumSales.getText()) + Double.valueOf(txtSumPayments.getText()))) * 100)) + " %");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        double Egare = 0;
        try {
            ResultSet rse = dbOperations.getData("select salary from employees where empType='ايجار'");
            if (rse.next()) {
                Egare = rse.getDouble("salary");
            }
            if (txtMonth.getSelectedIndex() == 0 && txtHandDateSearch.getDate() == null) {
                ResultSet rs = dbOperations.getData("select sum(Taked) as Taked from salary where date like'" + txtYear.getSelectedItem().toString() + "__________________%'");
                if (rs.next()) {
                    txtSumPayedForEmployees.setText("" + rs.getDouble("Taked"));
                }
                
            } else if (txtMonth.getSelectedIndex() != 0) {

                ResultSet rs = dbOperations.getData("select sum(Taked) as Taked from salary where date like'" + txtYear.getSelectedItem().toString() + "-" + txtMonth.getSelectedItem().toString() + "_______________%'");
                if (rs.next()) {
                    txtSumPayedForEmployees.setText("" + rs.getDouble("Taked"));
                }
                
            } else {
                ResultSet rs = dbOperations.getData("select sum(Taked) as Taked from salary where date like'" + (df.format(txtHandDateSearch.getDate())) + "____________%' and empType!='ايجار'");
                if (rs.next()) {
                    txtSumPayedForEmployees.setText("" + (rs.getDouble("Taked") + (Egare / 30)));
                } else {
                    txtSumPayedForEmployees.setText("" + (Egare / 30));
                }
                
            }
            if (!txtSumSales.getText().equals("0.0")) {
                txtpercentagePayedForEmployees.setText(String.format("%.2f", ((Double.valueOf(txtSumPayedForEmployees.getText()) / (Double.valueOf(txtSumSales.getText()) + Double.valueOf(txtSumPayments.getText()))) * 100)) + " %");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        double total=0;
        try {
            if (txtMonth.getSelectedIndex() == 0 && txtHandDateSearch.getDate() == null) {
                ResultSet rs = dbOperations.getData("select sum(payed) as payed from payment where date like'" + txtYear.getSelectedItem().toString() + "__________________%'");
                ResultSet rs1 = dbOperations.getData("select sum(payed) as payed from sales where date like'" + txtYear.getSelectedItem().toString() + "__________________%'");
                if(rs1.next())
                {
                    total+=rs1.getDouble("payed");
                }
                if (rs.next()) {
                    total+=rs.getDouble("payed");
                }
                
                
            } else if (txtMonth.getSelectedIndex() != 0) {

                ResultSet rs = dbOperations.getData("select sum(payed) as payed from payment where date like'" + txtYear.getSelectedItem().toString() + "-" + txtMonth.getSelectedItem().toString() + "_______________%'");
               ResultSet rs1 = dbOperations.getData("select sum(payed) as payed from sales where date like'" + txtYear.getSelectedItem().toString() + "-" + txtMonth.getSelectedItem().toString() + "_______________%'");
                if(rs1.next())
                {
                    total+=rs1.getDouble("payed");
                }
                if (rs.next()) {
                    total+=rs.getDouble("payed");
                }
                
                
            } else {
                ResultSet rs = dbOperations.getData("select sum(payed) as payed from payment where date like'" + (df.format(txtHandDateSearch.getDate())) + "____________%'");
                 ResultSet rs1 = dbOperations.getData("select sum(payed) as payed from sales where date like'" + (df.format(txtHandDateSearch.getDate())) + "____________%'");
                if(rs1.next())
                {
                    total+=rs1.getDouble("payed");
                }
                if (rs.next()) {
                    total+=rs.getDouble("payed");
                }
                
                
            }
            txtSumPayments.setText(""+total);
            if(Double.valueOf(txtSumSales.getText()) >= Double.valueOf(txtSumPayments.getText()))
            {
                txtSumRemainedCustomers.setText(""+(Double.valueOf(txtSumSales.getText())-Double.valueOf(txtSumPayments.getText())));
                txtpercentageRemainedOut.setText(""+String.format("%.2f",((Double.valueOf(txtSumRemainedCustomers.getText())/Double.valueOf(txtSumSales.getText()))*100))+" %");
            }else 
            {
                txtSumRemainedCustomers.setText("0.0");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        try {
            jButton6.setEnabled(false);
            double total = 0;
            double myList[] = new double[6];
            myList[0] = Double.valueOf(txtSumSales.getText());
            myList[1] = Double.valueOf(txtPayedForSuppliers.getText());//1200
            myList[2] = Double.valueOf(txtSumBuys.getText()); //0
            myList[3] = Double.valueOf(txtSumPayedForEmployees.getText()); //200
            myList[4] = Double.valueOf(txtSumPayments.getText());
            myList[5] = Double.valueOf(txtSumRemainedCustomers.getText()); //1000
            total = (myList[0]) - (myList[1] + myList[2] + myList[3] + myList[5]);
            if (total > 0) {
                txtNetgain.setForeground(new Color(0, 255, 0));
            } else {
                txtNetgain.setForeground(new Color(255, 0, 0));
            }
            txtNetgain.setText("" + String.format("%.2f",total));
            if (!txtSumSales.getText().equals("0.0")) {
                txtpercentageAll.setText(String.format("%.2f", (((Double.valueOf(txtNetgain.getText())) / (myList[0])) * 100)) + " %");
            }
            jButton6.setEnabled(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void txtClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtClearActionPerformed
        txtSumSales.setText("0.0");
    }//GEN-LAST:event_txtClearActionPerformed

    private void txtClear1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtClear1ActionPerformed
        txtPayedForSuppliers.setText("0.0");
    }//GEN-LAST:event_txtClear1ActionPerformed

    private void txtClear2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtClear2ActionPerformed
        txtSumBuys.setText("0.0");
    }//GEN-LAST:event_txtClear2ActionPerformed

    private void txtClear3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtClear3ActionPerformed
        txtSumPayedForEmployees.setText("0.0");
    }//GEN-LAST:event_txtClear3ActionPerformed

    private void txtClear4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtClear4ActionPerformed
        txtSumPayments.setText("0.0");
        txtpercentageRemainedOut.setText("0");
        txtSumRemainedCustomers.setText("0");
    }//GEN-LAST:event_txtClear4ActionPerformed

    private void txtClear5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtClear5ActionPerformed
        txtNetgain.setText("0.0");
    }//GEN-LAST:event_txtClear5ActionPerformed

    private void txtMonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMonthActionPerformed
       txtHandDateSearch.setDate(null);
    }//GEN-LAST:event_txtMonthActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JButton txtClear;
    private javax.swing.JButton txtClear1;
    private javax.swing.JButton txtClear2;
    private javax.swing.JButton txtClear3;
    private javax.swing.JButton txtClear4;
    private javax.swing.JButton txtClear5;
    private com.toedter.calendar.JDateChooser txtHandDateSearch;
    private javax.swing.JComboBox<String> txtMonth;
    private javax.swing.JLabel txtNetgain;
    private javax.swing.JLabel txtPayedForSuppliers;
    private javax.swing.JLabel txtPercentageBuys;
    private javax.swing.JLabel txtPercentageProductsPrice;
    private javax.swing.JLabel txtSumBuys;
    private javax.swing.JLabel txtSumPayedForEmployees;
    private javax.swing.JLabel txtSumPayments;
    private javax.swing.JLabel txtSumRemainedCustomers;
    private javax.swing.JLabel txtSumSales;
    private javax.swing.JComboBox<String> txtYear;
    private javax.swing.JLabel txtpercentageAll;
    private javax.swing.JLabel txtpercentagePayedForEmployees;
    private javax.swing.JLabel txtpercentageRemainedOut;
    // End of variables declaration//GEN-END:variables
}
