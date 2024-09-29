
package controller;

import Database.connectionProvider;
import Database.dbOperations;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.customers;

public class customerController {
    
    
    public static void addCustomer(customers customer)
    {
        PreparedStatement ps=null;
        Connection con=null;
        try {
            con=connectionProvider.getCon();
            ps=con.prepareStatement("insert into customers(customerName,phone,"
                    + "address,allRemained,customerType)"
                    + "values(?,?,?,?,?)");
            ps.setString(1, customer.getCustomerName());
            ps.setString(2, customer.getPhone());
            ps.setString(3,customer.getAddress());
            ps.setDouble(4, customer.getAllRemained());
            ps.setString(5, customer.getCustomerType());
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
    
    public static void updateCustomer(customers customer)
    {
        PreparedStatement ps=null;
        Connection con=null;
        try {
            con=connectionProvider.getCon();
            ps=con.prepareStatement("update customers set"
                    + " customerName=?,"
                    + "phone=?,"
                    + "address=?,"
                    + "allRemained=?,"
                    +"customerType=?"+
                    " where customerId=?");
            ps.setString(1, customer.getCustomerName());
            ps.setString(2, customer.getPhone());
            ps.setString(3,customer.getAddress());
            ps.setDouble(4, customer.getAllRemained());
            ps.setString(5,customer.getCustomerType());
            ps.setInt(6,customer.getCustomerID());
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
    public static void deleteCustomer(int customerId)
    {
        try {
            dbOperations.setDataOrDelete("delete from customers where customerId="+customerId,"تم الحذف");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    public static ArrayList<customers> getAllCustomersRecords()
    {
        ArrayList<customers> list=new ArrayList();
        try {
            ResultSet rs=dbOperations.getData("select * from customers");
            while(rs.next())
            {
                customers customer=new customers();
                customer.setCustomerID(rs.getInt("customerId"));
                customer.setCustomerName(rs.getString("customerName"));
                customer.setPhone(rs.getString("phone"));
                customer.setAddress(rs.getString("address"));
                customer.setAllRemained(rs.getDouble("allRemained"));
                customer.setCustomerType(rs.getString("customerType"));
                list.add(customer);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return list;
    }
        public static ArrayList<customers> getAllCustomersByCustomerName(String customerName)
    {
        ArrayList<customers> list=new ArrayList();
        try {
            ResultSet rs=dbOperations.getData("select * from customers where customerName='"+customerName+"'");
            while(rs.next())
            {
                customers customer=new customers();
                customer.setCustomerID(rs.getInt("customerId"));
                customer.setCustomerName(rs.getString("customerName"));
                customer.setPhone(rs.getString("phone"));
                customer.setAddress(rs.getString("address"));
                customer.setAllRemained(rs.getDouble("allRemained"));
                customer.setCustomerType(rs.getString("customerType"));
                list.add(customer);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return list;
    }
   public static ArrayList<customers> getAllCustomersByCustomerAddress(String Address)
    {
        ArrayList<customers> list=new ArrayList();
        try {
            ResultSet rs=dbOperations.getData("select * from customers where address='"+Address+"'");
            while(rs.next())
            {
                customers customer=new customers();
                customer.setCustomerID(rs.getInt("customerId"));
                customer.setCustomerName(rs.getString("customerName"));
                customer.setPhone(rs.getString("phone"));
                customer.setAddress(rs.getString("address"));
                customer.setAllRemained(rs.getDouble("allRemained"));
                customer.setCustomerType(rs.getString("customerType"));
                list.add(customer);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return list;
    }
      public static ArrayList<customers> getAllCustomersByCustomerPhone(String phone)
    {
        ArrayList<customers> list=new ArrayList();
        try {
            ResultSet rs=dbOperations.getData("select * from customers where phone='"+phone+"'");
            while(rs.next())
            {
                customers customer=new customers();
                customer.setCustomerID(rs.getInt("customerId"));
                customer.setCustomerName(rs.getString("customerName"));
                customer.setPhone(rs.getString("phone"));
                customer.setAddress(rs.getString("address"));
                customer.setAllRemained(rs.getDouble("allRemained"));
                customer.setCustomerType(rs.getString("customerType"));
                list.add(customer);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return list;
    }

    public static ArrayList<customers> getCustomerNamesForCompo() {
        ArrayList<customers>list=new ArrayList<>();
        try {
           ResultSet rs=dbOperations.getData("select * from customers");
           while(rs.next())
           {
              customers cus=new customers();
              cus.setCustomerID(rs.getInt("customerId"));
              cus.setCustomerName(rs.getString("customerName"));
              cus.setAllRemained(rs.getDouble("allRemained"));
              cus.setCustomerType(rs.getString("customerType"));
              list.add(cus);
           }
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, e);
        }
        return list;
    }
    
       
}
