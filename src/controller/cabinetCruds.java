
package controller;

import Database.connectionProvider;
import Database.dbOperations;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.capenat;


public class cabinetCruds {
    
    public static void addCabinetValue(capenat cab)
    {
        Connection con=null;
        PreparedStatement ps=null;
        try {
            con=connectionProvider.getCon();
            ps=con.prepareStatement("insert into capenat (realValue,editedvalue,afterEdictvalue,OperationType,date) values(?,?,?,?,?)");
            ps.setDouble(1,cab.getCabinetRealValue());
            ps.setDouble(2,cab.getEditedValue());
            ps.setDouble(3,cab.getAfterEdited());
            ps.setString(4,cab.getOperation());
            ps.setString(5,cab.getDate());
            int result=ps.executeUpdate();
            if(result==0)
            {
                JOptionPane.showMessageDialog(null,"لم يتم الاضافة");
            }else 
            {
                JOptionPane.showMessageDialog(null,"تم التوريد");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
   public static ArrayList<capenat> getLastUpdate(String date)
   {
       ArrayList<capenat> list=new ArrayList<>();
       try {
           ResultSet rs=dbOperations.getData("select * from capenat where date like'"+date+"____________%'");
         while(rs.next())
         {
             capenat cab=new capenat();
             cab.setCabinetId(rs.getInt("id"));
             cab.setCabinetRealValue(rs.getDouble("realValue"));
             cab.setEditedValue(rs.getDouble("editedvalue"));
             cab.setAfterEdited(rs.getDouble("afterEdictvalue"));
             cab.setOperation(rs.getString("OperationType"));
             cab.setDate(rs.getString("date"));
             list.add(cab);
         }
       } catch (Exception e) {
           JOptionPane.showMessageDialog(null, e);
       }
       return list;
   }
}
