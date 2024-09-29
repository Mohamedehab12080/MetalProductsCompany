
package Database;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Enumeration;
import javax.swing.JOptionPane;

public class connectionProvider
{


    public connectionProvider() {
     try{
              Class.forName("com.mysql.cj.jdbc.Driver");
              con=DriverManager.getConnection("jdbc:mysql://192.168.1.107/storeManage?characterEncoding=utf8&useConfigs=maxPerformance","root","25251436");
        }catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex.getMessage(),"Message",JOptionPane.ERROR_MESSAGE);
         
        }
    }  
     static Connection con=null;
//            public String getIP()
//    {
//        String ret="";
//        try {
//            ArrayList<String>list=new ArrayList<>();
//            // Get a list of network interfaces
//            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
//
//            while (networkInterfaces.hasMoreElements()) {
//                NetworkInterface networkInterface = networkInterfaces.nextElement();
//
//                // Check if the interface is up and not a loopback interface
//                if (networkInterface.isUp() && !networkInterface.isLoopback()) {
//                    System.out.println("Interface: " + networkInterface.getDisplayName());
//
//                    // Get the addresses associated with the network interface
//                    Enumeration<InetAddress> addresses = networkInterface.getInetAddresses();
//
//                    while (addresses.hasMoreElements()) {
//                        InetAddress address = addresses.nextElement();
//
//                        // Filter out loopback and link-local addresses
//                        if (!address.isLoopbackAddress() && !address.isLinkLocalAddress()) {
//                            System.out.println("  Connected Device: " + address.getHostAddress());
//                            list.add(address.getHostAddress());
//                            ret= list.get(0);
//                        }else
//                        {
//                            ret= "MMM";
//                        }
//                        
//                    }
//                }
//                
//            }
//        } catch (SocketException e) {
//            e.printStackTrace();
//            ret= "";
//        }
//        return ret;
//    }
//        use storeManage;
//create table products(
//productId int primary key auto_increment,
//productName varchar(100) unique Not null,
//priceOfBuy double,
//priceOfSale double,
//Taked double,
//AvailableQty double,
//Category varchar(50));
//
//create table productsTypes
//(
//	id int primary key auto_increment,
//    productId int,
//    productTypeName varchar(100) unique not null,
//    priceOfBuy double,
//    priceOfSale double,
//    QuantityOfOne double,
//    AllQuantity double,
//    Category varchar(50),
//    FOREIGN KEY (productId) REFERENCES products(productId)
//);
//
//create table category(id int primary key auto_increment,categoryName varchar(20));

    public static Connection getCon() {
        return con;
    }

        }

