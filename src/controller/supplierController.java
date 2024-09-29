
package controller;

import Database.dbOperations;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class supplierController {
    
    
    public static int getsupIdWithName(String supName)
    {
        int supId=0;
        try {
            ResultSet rs =dbOperations.getData("Select supplierId from suppliers where supplierName='"+supName+"'");
            while(rs.next())
            {
                supId=rs.getInt("supplierId");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return supId;
    }
}
