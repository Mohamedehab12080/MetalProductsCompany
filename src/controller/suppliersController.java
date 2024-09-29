/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Database.connectionProvider;
import Database.dbOperations;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.suppliersModel;

/**
 *
 * @author BOBO
 */
public class suppliersController {
     
    public static void addsupplier(suppliersModel supplier)
    {
        PreparedStatement ps=null;
        Connection con=null;
        try {
            con=connectionProvider.getCon();
            ps=con.prepareStatement("insert into suppliers(supplierName,phone,"
                    + "address,remained)"
                    + "values(?,?,?,?)");
            ps.setString(1, supplier.getSupplierName());
            ps.setString(2, supplier.getPhone());
            ps.setString(3,supplier.getAddress());
            ps.setDouble(4, supplier.getRemained());
            int result=ps.executeUpdate();
            if(result==0)
            {
                JOptionPane.showMessageDialog(null,"لا يمكن الاضافة");
            }else 
            {
             JOptionPane.showMessageDialog(null, "تم الاضافة");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        } 
       
    }
    
    public static void updatesupplier(suppliersModel supplier)
    {
        PreparedStatement ps=null;
        Connection con=null;
        try {
            con=connectionProvider.getCon();
            ps=con.prepareStatement("update suppliers set"
                    + " supplierName=?,"
                    + "phone=?,"
                    + "address=?,"
                    + "remained=?"+
                    " where supId=?");
            ps.setString(1, supplier.getSupplierName());
            ps.setString(2, supplier.getPhone());
            ps.setString(3,supplier.getAddress());
            ps.setDouble(4, supplier.getRemained());
            ps.setInt(5,supplier.getSupId());
            int result=ps.executeUpdate();
            if(result==0)
            {
                JOptionPane.showMessageDialog(null,"لا يمكن التعديل");
            }else 
            {
             JOptionPane.showMessageDialog(null, "تم التعديل");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        } 
     
    }
    public static void deletesupplier(int supplierId)
    {
        try {
            dbOperations.setDataOrDelete("delete from suppliers where supplierId="+supplierId,"تم الحذف");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    public static ArrayList<suppliersModel> getAllsuppliersModelRecords()
    {
        ArrayList<suppliersModel> list=new ArrayList();
        try {
            ResultSet rs=dbOperations.getData("select * from suppliers");
            while(rs.next())
            {
                suppliersModel supplier=new suppliersModel();
                supplier.setSupId(rs.getInt("supId"));
                supplier.setSupplierName(rs.getString("supplierName"));
                supplier.setPhone(rs.getString("phone"));
                supplier.setAddress(rs.getString("address"));
                supplier.setRemained(rs .getDouble("remained"));
                
                list.add(supplier);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return list;
    }
        public static ArrayList<suppliersModel> getAllsuppliersModelBysupplierName(String supplierName)
    {
        ArrayList<suppliersModel> list=new ArrayList();
        try {
            ResultSet rs=dbOperations.getData("select * from suppliers where supplierName='"+supplierName+"'");
            while(rs.next())
            {
                suppliersModel supplier=new suppliersModel();
                supplier.setSupId(rs.getInt("supId"));
                supplier.setSupplierName(rs.getString("supplierName"));
                supplier.setPhone(rs.getString("phone"));
                supplier.setAddress(rs.getString("address"));
                supplier.setRemained(rs.getDouble("remained"));
                list.add(supplier);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return list;
    }
   public static ArrayList<suppliersModel> getAllsuppliersModelBysupplierAddress(String Address)
    {
        ArrayList<suppliersModel> list=new ArrayList();
        try {
            ResultSet rs=dbOperations.getData("select * from suppliers where address='"+Address+"'");
            while(rs.next())
            {
                suppliersModel supplier=new suppliersModel();
                supplier.setSupId(rs.getInt("supId"));
                supplier.setSupplierName(rs.getString("supplierName"));
                supplier.setPhone(rs.getString("phone"));
                supplier.setAddress(rs.getString("address"));
                supplier.setRemained(rs.getDouble("remained"));
                list.add(supplier);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return list;
    }
      public static ArrayList<suppliersModel> getAllsuppliersModelBysupplierPhone(String phone)
    {
        ArrayList<suppliersModel> list=new ArrayList();
        try {
            ResultSet rs=dbOperations.getData("select * from suppliers where phone='"+phone+"'");
            while(rs.next())
            {
                suppliersModel supplier=new suppliersModel();
                supplier.setSupId(rs.getInt("supId"));
                supplier.setSupplierName(rs.getString("supplierName"));
                supplier.setPhone(rs.getString("phone"));
                supplier.setAddress(rs.getString("address"));
                supplier.setRemained(rs.getDouble("remained"));
                list.add(supplier);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return list;
    }

    public static ArrayList<suppliersModel> getsupplierNamesForCompo() {
        ArrayList<suppliersModel>list=new ArrayList<>();
        try {
           ResultSet rs=dbOperations.getData("select * from suppliers");
           while(rs.next())
           {
              suppliersModel sup=new suppliersModel();
              sup.setSupId(rs.getInt("supId"));
              sup.setSupplierName(rs.getString("supplierName"));
              sup.setRemained(rs.getDouble("remained"));
              sup.setPhone(rs.getString("phone"));
              sup.setAddress(rs.getString("Address"));
              list.add(sup);
           }
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, e);
        }
        return list;
    }
    
}
