package controller;

import Database.connectionProvider;
import Database.dbOperations;
import model.loginModel;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
public class casherCruds {
    
    public static void addEmployee(loginModel emp)
    {
        Connection con=null;
        PreparedStatement ps=null;
        try {
            con =connectionProvider.getCon();
            ps=con.prepareStatement("insert into login (name,email,password,phone,salary,Discount,Notes) values(?,?,?,?,?,?,?)");
            ps.setString(1, emp.getName());
            ps.setString(2, emp.getEmail());
            ps.setString(3, emp.getPassword());
            ps.setString(4, emp.getPhone());
            ps.setInt(5, emp.getSalary());
            ps.setInt(6, emp.getDiscount());
            ps.setString(7, emp.getNotes());
           int result=ps.executeUpdate();
           if(result==0)
           {
               JOptionPane.showMessageDialog(null, "لا يمكن الاضافة");
           }else 
           {
               JOptionPane.showMessageDialog(null, "تم الاضافة");
           }
        } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                    }
   
        
    }
      public static void updateEmp(loginModel emp)
    {
        Connection con=null;
        PreparedStatement ps=null;
        try {
            con =connectionProvider.getCon();
             ps=con.prepareStatement("update login set name=?,Email=?,password=?,phone=?,salary=?,Discount=?,Notes=? where id=?");
            ps.setString(1, emp.getName());
            ps.setString(2, emp.getEmail());
            ps.setString(3, emp.getPassword());
            ps.setString(4, emp.getPhone());
            ps.setInt(5, emp.getSalary());
            ps.setInt(6, emp.getDiscount());
            ps.setString(7, emp.getNotes());
               ps.setInt(8, emp.getId());
           int result=ps.executeUpdate();
           if(result==0)
           {
               JOptionPane.showMessageDialog(null, "لا يمكن التععديل");
           }else 
           {
               JOptionPane.showMessageDialog(null, "تم التععديل");
           }
        } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                    }
   
    }
      public static ArrayList<loginModel> getAllEmpRecords()
      {
          ArrayList<loginModel> list=new ArrayList<>();
          try {
              ResultSet rs=dbOperations.getData("Select * from login");
              while(rs.next())
              {
                  loginModel emp=new loginModel();
                  emp.setId(rs.getInt("id"));
                  emp.setName(rs.getString("name"));
                  emp.setEmail(rs.getString("Email"));
                  emp.setPassword(rs.getString("password"));
                  emp.setPhone(rs.getString("phone"));
                  emp.setSalary(rs.getInt("salary"));
                  emp.setDiscount(rs.getInt("Discount"));
                  list.add(emp);
              }
          } catch (Exception e) {
              JOptionPane.showMessageDialog(null, e);
          }
          return list;
      }
       public static ArrayList<loginModel> getAllEmpRecordsWithname(String  name)
      {
          ArrayList<loginModel> list=new ArrayList<>();
          try {
              ResultSet rs=dbOperations.getData("Select * from login where name='"+name+"'");
              while(rs.next())
              {
              loginModel emp=new loginModel();
                  emp.setId(rs.getInt("id"));
                  emp.setName(rs.getString("name"));
                  emp.setEmail(rs.getString("Email"));
                  emp.setPassword(rs.getString("password"));
                  emp.setPhone(rs.getString("phone"));
                  emp.setSalary(rs.getInt("salary"));
                  emp.setDiscount(rs.getInt("Discount"));
                  list.add(emp);
              }
          } catch (Exception e) {
              JOptionPane.showMessageDialog(null, e);
          }
          return list;
      }
        public static ArrayList<loginModel> getAllEmpRecordsWithPhone(String  phone)
      {
          ArrayList<loginModel> list=new ArrayList<>();
          try {
              ResultSet rs=dbOperations.getData("Select * from login where phone='"+phone+"'");
              while(rs.next())
              {
            loginModel emp=new loginModel();
                  emp.setId(rs.getInt("id"));
                  emp.setName(rs.getString("name"));
                  emp.setEmail(rs.getString("Email"));
                  emp.setPassword(rs.getString("password"));
                  emp.setPhone(rs.getString("phone"));
                  emp.setSalary(rs.getInt("salary"));
                  emp.setDiscount(rs.getInt("Discount"));
                  list.add(emp);
              }
          } catch (Exception e) {
              JOptionPane.showMessageDialog(null, e);
          }
          return list;
      }
            public static ArrayList<loginModel> getAllEmpnamesForCompo()
      {
          ArrayList<loginModel> list=new ArrayList<>();
          try {
              ResultSet rs=dbOperations.getData("Select name from login");
              while(rs.next())
              {
                loginModel emp=new loginModel();
                  emp.setName(rs.getString("name"));
                  list.add(emp);
              }
          } catch (Exception e) {
              JOptionPane.showMessageDialog(null, e);
          }
          return list;
      }
      public static void deleteEmployee(int id)
      {
          try {
              dbOperations.setDataOrDelete("Delete from login where id="+id,"تم الحذف");
          } catch (Exception e) {
              JOptionPane.showMessageDialog(null, e);
          }
      }
}
