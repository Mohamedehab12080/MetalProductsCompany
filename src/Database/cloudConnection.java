package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class cloudConnection {

    static Connection con = null;

    public static Connection getCon() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://aws.connect.psdb.cloud/posmanagement?sslMode=VERIFY_IDENTITY",
                    "ib6eyqo8w1ays322wpan",
                    "pscale_pw_foPJyUfBoQdEnxBmsBpBo0kdx9D7U3okwfvB9t6yfVr");
            return con;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Message", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

}
