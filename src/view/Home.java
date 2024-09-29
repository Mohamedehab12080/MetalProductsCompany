package view;

import Database.dbOperations;
import java.awt.Component;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Home extends javax.swing.JFrame {

    JpanelLoader Jpload = new JpanelLoader();
    public String Empname;
    SimpleDateFormat df;
    boolean pageState;
    SalesInvoces pro = new SalesInvoces();

    public Home() {
        initComponents();
        this.setExtendedState(Home.MAXIMIZED_BOTH);
        df = new SimpleDateFormat("EEE-MMM d-''yyyy", new Locale("ar"));
        txtCopyRights.setText(df.format(new Date()));
        txtCopyRights1.setText((char) 169 + "Mo_Software");
    }

    public Home(String Empname) {
        initComponents();
        df = new SimpleDateFormat("EEE-MMM d-''yyyy", new Locale("ar"));
        this.Empname = Empname;
        txtLabelEmp.setText(Empname);
        txtCopyRights1.setText((char) 169 + "Mo_Software");

        this.setExtendedState(Home.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

//        txtCopyRights.setText("<html><body>&copy; Mohamed Ehab 2023</body></html>");
        txtCopyRights.setText(df.format(new Date()));

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
//                super.windowClosing(we); //To change body of generated methods, choose Tools | Templates.
                try {
                    pro.getbtnDeleteAllAction();
                    String location = "..\\backup.sql";
                    Runtime runtime = Runtime.getRuntime();
                    Process p = runtime.exec("src\\backup\\mysqldump.exe -uroot -p25251436 --add-drop-database -B storemanage -r" + location);

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
                System.exit(0);

            }

        });

    }

    public void HomeForCurrentSales(String date) {
        try {
            currentSales pro = new currentSales(txtLabelEmp.getText(), date);
            Jpload.jPanelLoader(jPanelLarge, pro);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        home_btn_group = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jToggleButton1 = new javax.swing.JToggleButton();
        jToggleButton2 = new javax.swing.JToggleButton();
        jToggleButton3 = new javax.swing.JToggleButton();
        jToggleButton4 = new javax.swing.JToggleButton();
        jToggleButton5 = new javax.swing.JToggleButton();
        jToggleButton6 = new javax.swing.JToggleButton();
        jToggleButton7 = new javax.swing.JToggleButton();
        jToggleButton8 = new javax.swing.JToggleButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        panelHome = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jToggleButton9 = new javax.swing.JToggleButton();
        jLabel11 = new javax.swing.JLabel();
        jToggleButton10 = new javax.swing.JToggleButton();
        jLabel12 = new javax.swing.JLabel();
        jToggleButton11 = new javax.swing.JToggleButton();
        jLabel13 = new javax.swing.JLabel();
        jToggleButton12 = new javax.swing.JToggleButton();
        jLabel14 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        txtLabelEmp = new javax.swing.JLabel();
        txtCopyRights = new javax.swing.JLabel();
        btnLogout = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        txtCopyRights1 = new javax.swing.JLabel();
        jPanel = new javax.swing.JScrollPane();
        jPanelLarge = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jToggleButton1.setBackground(new java.awt.Color(0, 153, 204));
        home_btn_group.add(jToggleButton1);
        jToggleButton1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jToggleButton1.setForeground(new java.awt.Color(0, 0, 0));
        jToggleButton1.setText("ادارة العملاء");
        jToggleButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(33, 20, 0, 2);
        jPanel1.add(jToggleButton1, gridBagConstraints);

        jToggleButton2.setBackground(new java.awt.Color(0, 153, 204));
        home_btn_group.add(jToggleButton2);
        jToggleButton2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jToggleButton2.setForeground(new java.awt.Color(0, 0, 0));
        jToggleButton2.setText("ادارة الموردين");
        jToggleButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jToggleButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton2ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.ipadx = 2;
        gridBagConstraints.ipady = -4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 20, 0, 2);
        jPanel1.add(jToggleButton2, gridBagConstraints);

        jToggleButton3.setBackground(new java.awt.Color(0, 153, 204));
        home_btn_group.add(jToggleButton3);
        jToggleButton3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jToggleButton3.setForeground(new java.awt.Color(0, 0, 0));
        jToggleButton3.setText("ادارة العاملين");
        jToggleButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jToggleButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton3ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(9, 20, 0, 2);
        jPanel1.add(jToggleButton3, gridBagConstraints);

        jToggleButton4.setBackground(new java.awt.Color(0, 153, 204));
        home_btn_group.add(jToggleButton4);
        jToggleButton4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jToggleButton4.setForeground(new java.awt.Color(0, 0, 0));
        jToggleButton4.setText("ادارة المنتجات");
        jToggleButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jToggleButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton4ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 20, 0, 2);
        jPanel1.add(jToggleButton4, gridBagConstraints);

        jToggleButton5.setBackground(new java.awt.Color(0, 153, 204));
        home_btn_group.add(jToggleButton5);
        jToggleButton5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jToggleButton5.setForeground(new java.awt.Color(0, 0, 0));
        jToggleButton5.setText("ادارة الفواتير");
        jToggleButton5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jToggleButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton5ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 20, 0, 2);
        jPanel1.add(jToggleButton5, gridBagConstraints);

        jToggleButton6.setBackground(new java.awt.Color(0, 153, 204));
        home_btn_group.add(jToggleButton6);
        jToggleButton6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jToggleButton6.setForeground(new java.awt.Color(0, 0, 0));
        jToggleButton6.setText("ادارة المبيعات");
        jToggleButton6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jToggleButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton6ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 20, 0, 2);
        jPanel1.add(jToggleButton6, gridBagConstraints);

        jToggleButton7.setBackground(new java.awt.Color(0, 153, 204));
        home_btn_group.add(jToggleButton7);
        jToggleButton7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jToggleButton7.setForeground(new java.awt.Color(0, 0, 0));
        jToggleButton7.setText("الخزنة");
        jToggleButton7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jToggleButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton7ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 16;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 63;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 20, 0, 2);
        jPanel1.add(jToggleButton7, gridBagConstraints);

        jToggleButton8.setBackground(new java.awt.Color(0, 153, 204));
        home_btn_group.add(jToggleButton8);
        jToggleButton8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jToggleButton8.setForeground(new java.awt.Color(0, 0, 0));
        jToggleButton8.setText("الخوارج");
        jToggleButton8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jToggleButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton8ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 18;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 49;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 20, 0, 2);
        jPanel1.add(jToggleButton8, gridBagConstraints);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/customer.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 14;
        gridBagConstraints.ipady = 11;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(33, 20, 0, 0);
        jPanel1.add(jLabel1, gridBagConstraints);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/supplier.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.ipadx = 14;
        gridBagConstraints.ipady = 11;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(9, 20, 0, 0);
        jPanel1.add(jLabel2, gridBagConstraints);

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/emp.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.ipadx = 14;
        gridBagConstraints.ipady = 11;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 20, 0, 0);
        jPanel1.add(jLabel3, gridBagConstraints);

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/add to cart.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.ipadx = 14;
        gridBagConstraints.ipady = 11;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 20, 0, 0);
        jPanel1.add(jLabel4, gridBagConstraints);

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/reports.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.ipadx = 9;
        gridBagConstraints.ipady = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 20, 0, 0);
        jPanel1.add(jLabel5, gridBagConstraints);

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/view edit delete product.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 16;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 24, 0, 0);
        jPanel1.add(jLabel6, gridBagConstraints);

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/sales_menu.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.ipadx = 14;
        gridBagConstraints.ipady = 11;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 20, 0, 0);
        jPanel1.add(jLabel7, gridBagConstraints);

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/salary.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 20;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 2;
        gridBagConstraints.ipady = -1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 24, 0, 0);
        jPanel1.add(jLabel8, gridBagConstraints);

        panelHome.setBackground(new java.awt.Color(0, 153, 204));
        panelHome.setLayout(new javax.swing.BoxLayout(panelHome, javax.swing.BoxLayout.LINE_AXIS));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/removed bg.png"))); // NOI18N
        panelHome.add(jLabel10);

        jLabel9.setFont(new java.awt.Font("MV Boli", 1, 18)); // NOI18N
        jLabel9.setText("MO_SoftWare");
        panelHome.add(jLabel9);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.ipadx = 6;
        gridBagConstraints.ipady = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(38, 12, 0, 0);
        jPanel1.add(panelHome, gridBagConstraints);

        jToggleButton9.setBackground(new java.awt.Color(0, 153, 204));
        home_btn_group.add(jToggleButton9);
        jToggleButton9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jToggleButton9.setForeground(new java.awt.Color(0, 0, 0));
        jToggleButton9.setText("المرتجعات");
        jToggleButton9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jToggleButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton9ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 28;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 20, 0, 2);
        jPanel1.add(jToggleButton9, gridBagConstraints);

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/exit small.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.ipadx = 14;
        gridBagConstraints.ipady = 11;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 20, 0, 0);
        jPanel1.add(jLabel11, gridBagConstraints);

        jToggleButton10.setBackground(new java.awt.Color(0, 153, 204));
        home_btn_group.add(jToggleButton10);
        jToggleButton10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jToggleButton10.setForeground(new java.awt.Color(0, 0, 0));
        jToggleButton10.setText("تسديد");
        jToggleButton10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jToggleButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton10ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 20;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 61;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 20, 0, 2);
        jPanel1.add(jToggleButton10, gridBagConstraints);

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/update.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 18;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 9;
        gridBagConstraints.ipady = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 24, 0, 0);
        jPanel1.add(jLabel12, gridBagConstraints);

        jToggleButton11.setBackground(new java.awt.Color(0, 153, 204));
        home_btn_group.add(jToggleButton11);
        jToggleButton11.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jToggleButton11.setForeground(new java.awt.Color(0, 0, 0));
        jToggleButton11.setText("كاشيرات");
        jToggleButton11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jToggleButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton11ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 43;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 20, 0, 2);
        jPanel1.add(jToggleButton11, gridBagConstraints);

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Signup.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 20, 0, 0);
        jPanel1.add(jLabel13, gridBagConstraints);

        jToggleButton12.setBackground(new java.awt.Color(0, 153, 204));
        home_btn_group.add(jToggleButton12);
        jToggleButton12.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jToggleButton12.setForeground(new java.awt.Color(0, 0, 0));
        jToggleButton12.setText("حساب التوتال");
        jToggleButton12.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jToggleButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton12ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 22;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 20, 2, 2);
        jPanel1.add(jToggleButton12, gridBagConstraints);

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/salary.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 22;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 2;
        gridBagConstraints.ipady = -1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 24, 0, 0);
        jPanel1.add(jLabel14, gridBagConstraints);

        jPanel3.setBackground(new java.awt.Color(0, 153, 204));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtLabelEmp.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtLabelEmp.setForeground(new java.awt.Color(0, 0, 0));
        txtLabelEmp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        txtCopyRights.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtCopyRights.setForeground(new java.awt.Color(0, 0, 0));
        txtCopyRights.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        btnLogout.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/exit.png"))); // NOI18N
        btnLogout.setText("تسجيل الخروج");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(204, 0, 51));
        jButton1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jButton1.setText("-");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(204, 204, 204));
        jButton2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(0, 0, 0));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/tapsmall.png"))); // NOI18N
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        txtCopyRights1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtCopyRights1.setForeground(new java.awt.Color(0, 0, 0));
        txtCopyRights1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(204, 204, 204)
                .addComponent(txtLabelEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCopyRights1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(126, 126, 126)
                .addComponent(txtCopyRights, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 130, Short.MAX_VALUE)
                .addComponent(btnLogout)
                .addGap(55, 55, 55)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCopyRights1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCopyRights, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLabelEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jPanel.setBackground(new java.awt.Color(102, 102, 102));
        jPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanelLargeLayout = new javax.swing.GroupLayout(jPanelLarge);
        jPanelLarge.setLayout(jPanelLargeLayout);
        jPanelLargeLayout.setHorizontalGroup(
            jPanelLargeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1182, Short.MAX_VALUE)
        );
        jPanelLargeLayout.setVerticalGroup(
            jPanelLargeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 721, Short.MAX_VALUE)
        );

        jPanel.setViewportView(jPanelLarge);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 1047, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private String getCasherType(String casherName) {
        String res = "";
        try {
            ResultSet rs = dbOperations.getData("select userType from login where name='" + casherName + "'");
            if (rs.next()) {
                res = rs.getString("userType");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return res;
    }
    private void jToggleButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton2ActionPerformed
        if (getCasherType(Empname).equals("Admin")) {
            if (pro.pageState) {
                suppliersView pro = new suppliersView();
                for (int i = 0; i < jPanelLarge.getComponentCount(); i++) {
                    Component c=jPanelLarge.getComponent(i);
                    c=null;
                }
                Jpload.jPanelLoader(jPanelLarge, pro);
            }
        }
    }//GEN-LAST:event_jToggleButton2ActionPerformed

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed

        if (getCasherType(Empname).equals("Admin")) {
            if (pro.pageState) {
                Customer pro = new Customer();
                for (int i = 0; i < jPanelLarge.getComponentCount(); i++) {
                    Component c=jPanelLarge.getComponent(i);
                    c=null;
                }
                Jpload.jPanelLoader(jPanelLarge, pro);
            }
        }
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void jToggleButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton4ActionPerformed
        if (getCasherType(Empname).equals("Admin")) {
            if (pro.pageState) {
                productsV pro = new productsV(txtLabelEmp.getText());
                for (int i = 0; i < jPanelLarge.getComponentCount(); i++) {
                    Component c=jPanelLarge.getComponent(i);
                    c=null;
                }
                Jpload.jPanelLoader(jPanelLarge, pro);
            }
        }
    }//GEN-LAST:event_jToggleButton4ActionPerformed

    private void jToggleButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton3ActionPerformed

        if (getCasherType(Empname).equals("Admin")) {
            if (pro.pageState) {
                employeeView pro = new employeeView(txtLabelEmp.getText());
                for (int i = 0; i < jPanelLarge.getComponentCount(); i++) {
                    Component c=jPanelLarge.getComponent(i);
                    c=null;
                }
                Jpload.jPanelLoader(jPanelLarge, pro);
            }
        }

    }//GEN-LAST:event_jToggleButton3ActionPerformed

    private void jToggleButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton5ActionPerformed
        pageState = pro.pageState;
        if (pro.pageState) {
            pro = new SalesInvoces(Empname);
            for (int i = 0; i < jPanelLarge.getComponentCount(); i++) {
                    Component c=jPanelLarge.getComponent(i);
                    c=null;
                }
            Jpload.jPanelLoader(jPanelLarge, pro);
        }
    }//GEN-LAST:event_jToggleButton5ActionPerformed

    private void jToggleButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton6ActionPerformed
        if (getCasherType(Empname).equals("Admin")) {
            if (pro.pageState) {
                currentSales pro = new currentSales(txtLabelEmp.getText());
                for (int i = 0; i < jPanelLarge.getComponentCount(); i++) {
                    Component c=jPanelLarge.getComponent(i);
                    c=null;
                }
                Jpload.jPanelLoader(jPanelLarge, pro);
            }
        }
    }//GEN-LAST:event_jToggleButton6ActionPerformed

    private void jToggleButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton7ActionPerformed

        if (getCasherType(Empname).equals("Admin")) {
            if (pro.pageState) {
                capenatView pro = new capenatView(txtLabelEmp.getText());
                for (int i = 0; i < jPanelLarge.getComponentCount(); i++) {
                    Component c=jPanelLarge.getComponent(i);
                    c=null;
                }
                Jpload.jPanelLoader(jPanelLarge, pro);
            }
        }
    }//GEN-LAST:event_jToggleButton7ActionPerformed

    private void jToggleButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton8ActionPerformed
        if (pro.pageState) {
            if (pro.pageState) {
                buysView pro = new buysView(txtLabelEmp.getText());
                for (int i = 0; i < jPanelLarge.getComponentCount(); i++) {
                    Component c=jPanelLarge.getComponent(i);
                    c=null;
                }
                Jpload.jPanelLoader(jPanelLarge, pro);
            }

        }
    }//GEN-LAST:event_jToggleButton8ActionPerformed

    private void jToggleButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton9ActionPerformed
        if (getCasherType(Empname).equals("Admin")) {
            if (pro.pageState) {
                returnView pro = new returnView(txtLabelEmp.getText());
                for (int i = 0; i < jPanelLarge.getComponentCount(); i++) {
                    Component c=jPanelLarge.getComponent(i);
                    c=null;
                }
                Jpload.jPanelLoader(jPanelLarge, pro);
            }
        }
    }//GEN-LAST:event_jToggleButton9ActionPerformed

    private void jToggleButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton10ActionPerformed
        if (getCasherType(Empname).equals("Admin")) {
            if (pro.pageState) {
                paymentView pro = new paymentView(txtLabelEmp.getText());
                for (int i = 0; i < jPanelLarge.getComponentCount(); i++) {
                    Component c=jPanelLarge.getComponent(i);
                    c=null;
                }
                Jpload.jPanelLoader(jPanelLarge, pro);
            }
        }
    }//GEN-LAST:event_jToggleButton10ActionPerformed

    private void jToggleButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton11ActionPerformed
        if (getCasherType(Empname).equals("Admin")) {
            if (pro.pageState) {
                casherView pro = new casherView(txtLabelEmp.getText());
                for (int i = 0; i < jPanelLarge.getComponentCount(); i++) {
                    Component c=jPanelLarge.getComponent(i);
                    c=null;
                }
                Jpload.jPanelLoader(jPanelLarge, pro);
            }
        }
    }//GEN-LAST:event_jToggleButton11ActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        try {
            if (pro.pageState) {
                this.dispose();
                new Login().setVisible(true);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void jToggleButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton12ActionPerformed
        if (getCasherType(Empname).equals("Admin")) {
            if (pro.pageState) {
                pureTOTALView pro = new pureTOTALView();
                Jpload.jPanelLoader(jPanelLarge, pro);
            }
        }
    }//GEN-LAST:event_jToggleButton12ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.setExtendedState(Home.ICONIFIED);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.setExtendedState(Home.MAXIMIZED_BOTH);
    }//GEN-LAST:event_jButton2ActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogout;
    private javax.swing.ButtonGroup home_btn_group;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jPanel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanelLarge;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JToggleButton jToggleButton10;
    private javax.swing.JToggleButton jToggleButton11;
    private javax.swing.JToggleButton jToggleButton12;
    private javax.swing.JToggleButton jToggleButton2;
    private javax.swing.JToggleButton jToggleButton3;
    private javax.swing.JToggleButton jToggleButton4;
    private javax.swing.JToggleButton jToggleButton5;
    private javax.swing.JToggleButton jToggleButton6;
    private javax.swing.JToggleButton jToggleButton7;
    private javax.swing.JToggleButton jToggleButton8;
    private javax.swing.JToggleButton jToggleButton9;
    private javax.swing.JPanel panelHome;
    private javax.swing.JLabel txtCopyRights;
    private javax.swing.JLabel txtCopyRights1;
    private javax.swing.JLabel txtLabelEmp;
    // End of variables declaration//GEN-END:variables
}
