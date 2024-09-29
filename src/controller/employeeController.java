
package controller;

import Database.connectionProvider;
import Database.dbOperations;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.employeeModel;


public class employeeController {
    
    
    public static void addEmployee(employeeModel emp)
    {
        Connection con=null;
        PreparedStatement ps=null;
        try {
            con=connectionProvider.getCon();
            ps=con.prepareStatement("insert into employees (employeeName,empType,phone,address,salary,remainedSalary) values(?,?,?,?,?,?)");
            ps.setString(1,emp.getEmployeeName());
            ps.setString(2,emp.getEmpType());
            ps.setString(3,emp.getPhone());
            ps.setString(4,emp.getAddress());
            ps.setDouble(5,emp.getSalary());
            ps.setDouble(6,emp.getRemainedSalary());
           int result =ps.executeUpdate();
           if(result==0)
           {
               JOptionPane.showMessageDialog(null, "لم يتم الاضافة");
           }else 
           {
               JOptionPane.showMessageDialog(null,"تم الاضافة");
           }
        
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
    }
    public static void updateEmployee(employeeModel emp)
    {
        Connection con=null;
        PreparedStatement ps=null;
        try {
            con=connectionProvider.getCon();
            ps=con.prepareStatement("update employees "
                    + "set employeeName=?,empType=?,phone=?,address=?,salary=? where id=?");
            ps.setString(1,emp.getEmployeeName());
            ps.setString(2,emp.getEmpType());
            ps.setString(3,emp.getPhone());
            ps.setString(4,emp.getAddress());
            ps.setDouble(5,emp.getSalary());
            ps.setInt(6,emp.getId());
           int result =ps.executeUpdate();
           if(result==0)
           {
               JOptionPane.showMessageDialog(null, "لم يتم التعديل");
           }else 
           {
               JOptionPane.showMessageDialog(null,"تم التعديل");
           }
          
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
    }
    
    public static ArrayList<employeeModel> getAllEmployeesRecords()
    {
        ArrayList<employeeModel> list=new ArrayList<>();
        try {
            ResultSet rs=dbOperations.getData("select * from employees");
            while(rs.next())
            {
                employeeModel emp=new employeeModel();
                emp.setId(rs.getInt("id"));
                emp.setEmployeeName(rs.getString("employeeName"));
                emp.setEmpType(rs.getString("empType"));
                emp.setPhone(rs.getString("phone"));
                emp.setSalary(rs.getDouble("salary"));
                list.add(emp);
            }
        } catch (Exception e) {
        }
        return list;
    }
}
