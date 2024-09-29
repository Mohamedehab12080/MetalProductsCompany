
package Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class dbOperationsCloud {
     public static void setDataOrDelete(String Query,String Msg)
    {
        try{
           Connection con =cloudConnection.getCon();
           Statement st=con.createStatement();
           st.executeUpdate(Query);
           if(!Msg.equals(""))
           {
               JOptionPane.showMessageDialog(null, Msg);
           }
        }catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null,ex.getMessage(),"Message",JOptionPane.ERROR_MESSAGE);
        }
    }
       public static void setDataorDeleteForFirstPageLogin(String Query,String Msg)
    {
        try{
           Connection con =cloudConnection.getCon();
           Statement st=con.createStatement();
           st.executeUpdate(Query);
           if(!Msg.equals(""))
           {
               JOptionPane.showMessageDialog(null, Msg);
           }
        }catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null,"موجود مسبقا");
        }
    }
    public static ResultSet getData(String Query)
    {
        try{
            Connection con=cloudConnection.getCon();
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery(Query);
            return rs;
        }catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex.getMessage(),"Message",JOptionPane.ERROR_MESSAGE);
          return null;
        }
    }
}
