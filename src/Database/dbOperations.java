package Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class dbOperations {

    public static void setDataOrDelete(String Query, String Msg) {
        try {
            Statement st = connectionProvider.con.createStatement();
            st.executeUpdate(Query);
            if (!Msg.equals("")) {
                JOptionPane.showMessageDialog(null, Msg);
            }
            st.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Message", JOptionPane.ERROR_MESSAGE);
        }

    }

    public static void setDataorDeleteForFirstPageLogin(String Query, String Msg) {
        try {
            Statement st = connectionProvider.con.createStatement();
            st.executeUpdate(Query);
            if (!Msg.equals("")) {
                JOptionPane.showMessageDialog(null, Msg);
            }
            st.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "موجود مسبقا");
        }
    }

    public static ResultSet getData(String Query) {
        try {
            Statement st = connectionProvider.con.createStatement();
            ResultSet rs = st.executeQuery(Query);
            return rs;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Message", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public static ResultSet getDataEdited(String Query) {
        try {
            Statement st = connectionProvider.con.createStatement();
            ResultSet rs = st.executeQuery(Query);
            return rs;
        } catch (SQLException ex) {
            return null;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Message", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
}
