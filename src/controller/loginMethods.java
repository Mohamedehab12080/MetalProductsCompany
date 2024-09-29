
package controller;

import Database.connectionProvider;
import Database.dbOperations;
import model.loginModel;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class loginMethods {
    
 public static void addUser(loginModel user)
 {
     Connection con=null;
     PreparedStatement ps=null;
     try {
         con=connectionProvider.getCon();
         ps=con.prepareStatement("insert into login(name,Email,password,userType,Notes) values(?,?,?,?,?)");
         ps.setString(1,user.getName());
         ps.setString(2, user.getEmail());
         ps.setString(3, user.getPassword());
         ps.setString(4, user.getUserType());
        ps.setString(5,user.getNotes());
         int result=ps.executeUpdate();
        if(result==0)
        {
            JOptionPane.showMessageDialog(null, "لم يتم الاضافة");
        }else 
        {
             JOptionPane.showMessageDialog(null, " تم الاضافة");
        }
     } catch (Exception e) {
         JOptionPane.showMessageDialog(null, e);
     }
             
 }
  public static void updateUser(loginModel user)
 {
     Connection con=null;
     PreparedStatement ps=null;
     try {
         con=connectionProvider.getCon();
         ps=con.prepareStatement("update login set name=?,Email=?,password=?,userType=?,Notes=? where id=?");
          ps.setString(1,user.getName());
         ps.setString(2, user.getEmail());
         ps.setString(3, user.getPassword());
         ps.setString(4, user.getUserType());
        ps.setString(5,user.getNotes());
        ps.setInt(6,user.getId());
         int result=ps.executeUpdate();
        if(result==0)
        {
            JOptionPane.showMessageDialog(null, "لم يتم التعديل");
        }else 
        {
             JOptionPane.showMessageDialog(null, " تم التعديل");
        }
     } catch (Exception e) {
         JOptionPane.showMessageDialog(null, e);
     }
 }
  
 public static ArrayList<loginModel> getAllRecords()
 {
     ArrayList<loginModel>list=new ArrayList<>();
     try {
         ResultSet rs=dbOperations.getData("Select * from login");
         while(rs.next())
         {
             loginModel user=new loginModel();
             user.setId(rs.getInt("id"));
             user.setName(rs.getString("name"));
             user.setEmail(rs.getString("Email"));
             user.setPassword(rs.getString("password"));
             user.setUserType(rs.getString("userType"));
             user.setNotes(rs.getString("Notes"));
             list.add(user);
         }
     } catch (Exception e) {
         JOptionPane.showMessageDialog(null, e);
     }
     return list;
 }
 public static ArrayList<loginModel> getAllRecordsWithname(String empname)
 {
     ArrayList<loginModel>list=new ArrayList<>();
     try {
         ResultSet rs=dbOperations.getData("Select * from login where name='"+empname+"'");
         while(rs.next())
         {
               loginModel user=new loginModel();
             user.setId(rs.getInt("id"));
             user.setName(rs.getString("name"));
             user.setEmail(rs.getString("Email"));
             user.setPassword(rs.getString("password"));
             user.setUserType(rs.getString("userType"));
             user.setNotes(rs.getString("Notes"));
             list.add(user);
         }
     } catch (Exception e) {
         JOptionPane.showMessageDialog(null, e);
     }
     return list;
 }
 public static loginModel login(String username,String password)
 {
     loginModel user=null;
     try {
         ResultSet rs=dbOperations.getData("select name,Email,password from login where Email like'"+username+"' and password like'"+password+"'");
        if(rs.next())
        {
            user=new loginModel();
            user.setName(rs.getString("name"));
            user.setPassword(rs.getString("password"));
            user.setEmail(rs.getString("Email"));
        }
     } catch (Exception e) {
         JOptionPane.showMessageDialog(null, e);
     }
     return user;
 }
}
