
package view;





import Database.connectionProvider;
import java.awt.Container;
import java.sql.Connection;
import java.util.HashMap;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;



public class ReportView extends JFrame
{
    String fileName;
    HashMap para;
    public ReportView(String fileName, HashMap para)
    {
        this.fileName=fileName;
        this.para=para;
        
//        
    }

    public void view()
    {
        try
        {
             Connection con=connectionProvider.getCon();
            JasperPrint print = JasperFillManager.fillReport(fileName, para, con);
            JasperViewer viewer = new JasperViewer(print);
            this.setContentPane(viewer.getContentPane());
//            Container c = getContentPane();
//            c.add(viewer);            
        this.setBounds(2, 2, 900, 920);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);
        } 
        catch (JRException jRException)
        {
           JOptionPane.showMessageDialog(null, jRException);
        }
        
    }
    public void view2(DefaultTableModel JRModel,String reportSource)
    {
        try
        {
            JasperReport jr = JasperCompileManager.compileReport(reportSource);
            JasperPrint print = JasperFillManager.fillReport(jr, null, new JRTableModelDataSource(JRModel));
            JasperViewer viewer = new JasperViewer(print,false);
            this.setContentPane(viewer.getContentPane());
//            Container c = getContentPane();
//            c.add(viewer);            
        this.setBounds(2, 2, 900, 700);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);
        } 
        catch (JRException jRException)
        {
           JOptionPane.showMessageDialog(null, jRException);
        }
        
    }
     public void view3(DefaultTableModel JRModel,String reportSource)
    {
        try
        {
            JasperReport jr = JasperCompileManager.compileReport(reportSource);
            JasperPrint print = JasperFillManager.fillReport(jr, para, new JRTableModelDataSource(JRModel));
            JasperViewer viewer = new JasperViewer(print,false);
            this.setContentPane(viewer.getContentPane());
//            Container c = getContentPane();
//            c.add(viewer);            
        this.setBounds(2, 2, 900, 900);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);
        } 
        catch (JRException jRException)
        {
           JOptionPane.showMessageDialog(null, jRException);
        }
        
    }
    public void print() { 
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
