/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Database.dbOperations;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.salesReturnModel;

/**
 *
 * @author BOBO
 */
public class returnController {

    public static ArrayList<salesReturnModel> getAllSalesReturnwithTodayDate(String date) {
        ArrayList<salesReturnModel> list = new ArrayList<>();
        try {
            ResultSet rs = dbOperations.getData("select * from salesReturn where date like'" + date + "____________%'");
            while (rs.next()) {
                salesReturnModel sale = new salesReturnModel();
                sale.setSaleId(rs.getInt("saleID"));
                sale.setINID(rs.getInt("INID"));
                sale.setCustomerName(rs.getString("Customername"));
                sale.setTotal_Quantity(rs.getDouble("Total_Quantity"));
                sale.setPayed(rs.getDouble("payed"));
                sale.setSaleType(rs.getString("SaleType"));
       
                sale.setRemained(rs.getDouble("Remained"));
                sale.setDate(rs.getString("date"));
                 sale.setTotal(rs.getDouble("Total"));
                 sale.setAllTotal(rs.getDouble("AllTotal"));
                sale.setEmpname(rs.getString("empname"));
                sale.setDayDate(rs.getString("daydate"));
                sale.setDiscount(rs.getDouble("Discount"));
                list.add(sale);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "sss");
        }
        return list;
    }

    public static ArrayList<salesReturnModel> getAllSalesReturnwithEmpnameandTodayDate(String empname, String date) {
        ArrayList<salesReturnModel> list = new ArrayList<>();
        try {
            ResultSet rs = dbOperations.getData("select * from salesReturn where empname='" + empname + "' and date like'" + date + "____________%'");
            while (rs.next()) {
                salesReturnModel sale = new salesReturnModel();
                sale.setSaleId(rs.getInt("saleID"));
                sale.setINID(rs.getInt("INID"));
                sale.setCustomerName(rs.getString("Customername"));
                sale.setTotal_Quantity(rs.getDouble("Total_Quantity"));
                sale.setPayed(rs.getDouble("payed"));

                sale.setRemained(rs.getDouble("Remained"));
                sale.setDate(rs.getString("date"));
                 sale.setTotal(rs.getDouble("Total"));
                 sale.setAllTotal(rs.getDouble("AllTotal"));
                sale.setEmpname(rs.getString("empname"));
                sale.setDayDate(rs.getString("daydate"));
                sale.setDiscount(rs.getDouble("Discount"));
                list.add(sale);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "sss");
        }
        return list;
    }

    public static ArrayList<salesReturnModel> getAllSalesReturnwithEmpnameandState(String empname, String state) {
        ArrayList<salesReturnModel> list = new ArrayList<>();
        try {
            ResultSet rs = dbOperations.getData("select * from salesReturn where empname='" + empname + "' and state='" + state + "'");
            while (rs.next()) {
                salesReturnModel sale = new salesReturnModel();
                sale.setSaleId(rs.getInt("saleID"));
                sale.setINID(rs.getInt("INID"));
                sale.setCustomerName(rs.getString("Customername"));
                sale.setTotal_Quantity(rs.getDouble("Total_Quantity"));
                sale.setPayed(rs.getDouble("payed"));

                sale.setRemained(rs.getDouble("Remained"));
                sale.setDate(rs.getString("date"));
                 sale.setTotal(rs.getDouble("Total"));
                  sale.setAllTotal(rs.getDouble("AllTotal"));
                sale.setEmpname(rs.getString("empname"));
                sale.setDayDate(rs.getString("daydate"));
                sale.setDiscount(rs.getDouble("Discount"));
                list.add(sale);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "sss");
        }
        return list;
    }

    public static ArrayList<salesReturnModel> getAllSalesReturn(int invId) {
        ArrayList<salesReturnModel> list = new ArrayList<>();
        try {
            ResultSet rs = dbOperations.getData("select * from salesReturn where INID=" + invId);
            while (rs.next()) {
                salesReturnModel sale = new salesReturnModel();
                sale.setSaleId(rs.getInt("saleID"));
                sale.setINID(rs.getInt("INID"));
                sale.setCustomerName(rs.getString("Customername"));
                sale.setTotal_Quantity(rs.getDouble("Total_Quantity"));
                sale.setPayed(rs.getDouble("payed"));
sale.setSaleType(rs.getString("saleType"));
                sale.setRemained(rs.getDouble("Remained"));
                sale.setDate(rs.getString("date"));
                 sale.setAllTotal(rs.getDouble("AllTotal"));
                 sale.setTotal(rs.getDouble("Total"));
                sale.setEmpname(rs.getString("empname"));
                sale.setDayDate(rs.getString("daydate"));
                sale.setDiscount(rs.getDouble("Discount"));
                list.add(sale);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "sss");
        }
        return list;
    }

    public static ArrayList<salesReturnModel> getAllSalesReturnForPay(int invId) {
        ArrayList<salesReturnModel> list = new ArrayList<>();
        try {
            ResultSet rs = dbOperations.getData("select * from salesReturn where INID=" + invId + " and Remained > 0");
            while (rs.next()) {
                salesReturnModel sale = new salesReturnModel();
                sale.setSaleId(rs.getInt("saleID"));
                sale.setINID(rs.getInt("INID"));
                sale.setCustomerName(rs.getString("Customername"));
                sale.setTotal_Quantity(rs.getDouble("Total_Quantity"));
                sale.setPayed(rs.getDouble("payed"));

                sale.setRemained(rs.getDouble("Remained"));
                sale.setDate(rs.getString("date"));
                 sale.setAllTotal(rs.getDouble("AllTotal"));
                 sale.setTotal(rs.getDouble("Total"));
                sale.setEmpname(rs.getString("empname"));
                sale.setDayDate(rs.getString("daydate"));
                sale.setDiscount(rs.getDouble("Discount"));
                list.add(sale);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "sss");
        }
        return list;
    }

    public static ArrayList<salesReturnModel> getAllSalesReturnRemainedForCustomer(String customerName) {
        ArrayList<salesReturnModel> list = new ArrayList<>();
        try {
            ResultSet rs = dbOperations.getData("select * from salesReturn where customername='" + customerName + "' and Remained > 0");
            while (rs.next()) {
                salesReturnModel sale = new salesReturnModel();
                sale.setSaleId(rs.getInt("saleID"));
                sale.setINID(rs.getInt("INID"));
                sale.setCustomerName(rs.getString("Customername"));
                sale.setTotal_Quantity(rs.getDouble("Total_Quantity"));
                sale.setPayed(rs.getDouble("payed"));

                sale.setRemained(rs.getDouble("Remained"));
                sale.setDate(rs.getString("date"));
                 sale.setAllTotal(rs.getDouble("AllTotal"));
                 sale.setTotal(rs.getDouble("Total"));
                sale.setEmpname(rs.getString("empname"));
                sale.setDayDate(rs.getString("daydate"));
                sale.setDiscount(rs.getDouble("Discount"));
                list.add(sale);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "sss");
        }
        return list;
    }

    public static int getSumofRemained() {
        int result = 0;
        try {
            ResultSet rs = dbOperations.getData("select sum(Remained) as Remained from salesReturn where  Remained > 0");
            if (rs.next()) {
                result = rs.getInt("Remained");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return result;
    }

    public static ArrayList<salesReturnModel> getAllSalesReturnForPayRemained() {
        ArrayList<salesReturnModel> list = new ArrayList<>();
        try {
            ResultSet rs = dbOperations.getData("select * from salesReturn where  Remained > 0");
            while (rs.next()) {
                salesReturnModel sale = new salesReturnModel();
                sale.setSaleId(rs.getInt("saleID"));
                sale.setINID(rs.getInt("INID"));
                sale.setCustomerName(rs.getString("Customername"));
                sale.setTotal_Quantity(rs.getDouble("Total_Quantity"));
                sale.setPayed(rs.getDouble("payed"));

                sale.setRemained(rs.getDouble("Remained"));
                sale.setDate(rs.getString("date"));
                 sale.setAllTotal(rs.getDouble("AllTotal"));
                 sale.setTotal(rs.getDouble("Total"));
                sale.setEmpname(rs.getString("empname"));
                sale.setDayDate(rs.getString("daydate"));
                sale.setDiscount(rs.getDouble("Discount"));
                list.add(sale);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "sss");
        }
        return list;
    }

    public static ArrayList<salesReturnModel> getAllSalesReturnForCustomernameandDate(String custoemername, String Date) {
        ArrayList<salesReturnModel> list = new ArrayList<>();
        try {
            ResultSet rs = dbOperations.getData("select * from salesReturn where customername='" + custoemername + "' and date like'" + Date + "____________%'"); //2023-07-12 01:15:21 PM
            while (rs.next()) {
                salesReturnModel sale = new salesReturnModel();
                sale.setSaleId(rs.getInt("saleID"));
                sale.setINID(rs.getInt("INID"));
                sale.setCustomerName(rs.getString("Customername"));
                sale.setTotal_Quantity(rs.getDouble("Total_Quantity"));
                sale.setPayed(rs.getDouble("payed"));

                sale.setRemained(rs.getDouble("Remained"));
                 sale.setAllTotal(rs.getDouble("AllTotal"));
                sale.setDate(rs.getString("date"));
                 sale.setTotal(rs.getDouble("Total"));
                sale.setEmpname(rs.getString("empname"));
                sale.setDayDate(rs.getString("daydate"));
                sale.setDiscount(rs.getDouble("Discount"));
                list.add(sale);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "sss");
        }
        return list;
    }

    public static ArrayList<salesReturnModel> getAllSalesReturnForEmpnameandCustomernameandDate(String empname, String custoemername, String Date) {
        ArrayList<salesReturnModel> list = new ArrayList<>();
        try {
            ResultSet rs = dbOperations.getData("select * from salesReturn where empname='" + empname + "' and customername='" + custoemername + "' and date like'" + Date + "____________%'"); //2023-07-12 01:15:21 PM
            while (rs.next()) {
                salesReturnModel sale = new salesReturnModel();
                sale.setSaleId(rs.getInt("saleID"));
                sale.setINID(rs.getInt("INID"));
                sale.setCustomerName(rs.getString("Customername"));
                sale.setTotal_Quantity(rs.getDouble("Total_Quantity"));
                sale.setPayed(rs.getDouble("payed"));

                sale.setRemained(rs.getDouble("Remained"));
                 sale.setAllTotal(rs.getDouble("AllTotal"));
                sale.setDate(rs.getString("date"));
                 sale.setTotal(rs.getDouble("Total"));
                sale.setEmpname(rs.getString("empname"));
                sale.setDayDate(rs.getString("daydate"));
                sale.setDiscount(rs.getDouble("Discount"));
                list.add(sale);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "sss");
        }
        return list;
    }

    public static ArrayList<salesReturnModel> etAllSalesReturnWithEmpnameMonthly(String empname, String Date) {
        ArrayList<salesReturnModel> list = new ArrayList<>();
        try {
            ResultSet rs = dbOperations.getData("select * from salesReturn where empname='" + empname + "'  and date like'" + Date + "____________%'"); //2023-07-12 01:15:21 PM
            while (rs.next()) {
                salesReturnModel sale = new salesReturnModel();
                sale.setSaleId(rs.getInt("saleID"));
                sale.setINID(rs.getInt("INID"));
                sale.setCustomerName(rs.getString("Customername"));
                sale.setTotal_Quantity(rs.getDouble("Total_Quantity"));
                sale.setPayed(rs.getDouble("payed"));
                sale.setSaleType(rs.getString("saleType")); 
                 sale.setAllTotal(rs.getDouble("AllTotal"));
                sale.setRemained(rs.getDouble("Remained"));
                sale.setDate(rs.getString("date"));
                 sale.setTotal(rs.getDouble("Total"));
                sale.setEmpname(rs.getString("empname"));
                sale.setDayDate(rs.getString("daydate"));
                sale.setDiscount(rs.getDouble("Discount"));
                list.add(sale);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "sss");
        }
        return list;
    }

    public static ArrayList<salesReturnModel> getAllSalesReturnWithDayDate(String Date) {
        ArrayList<salesReturnModel> list = new ArrayList<>();
        try {
            ResultSet rs = dbOperations.getData("select * from salesReturn where Daydate='" + Date + "'"); //2023-07-12 01:15:21 PM
            while (rs.next()) {
                salesReturnModel sale = new salesReturnModel();
                sale.setSaleId(rs.getInt("saleID"));
                sale.setINID(rs.getInt("INID"));
                sale.setCustomerName(rs.getString("Customername"));
                sale.setTotal_Quantity(rs.getDouble("Total_Quantity"));
                sale.setPayed(rs.getDouble("payed"));
                sale.setSaleType(rs.getString("saleType")); 
                 sale.setAllTotal(rs.getDouble("AllTotal"));
                sale.setRemained(rs.getDouble("Remained"));
                sale.setDate(rs.getString("date"));
                 sale.setTotal(rs.getDouble("Total"));
                sale.setEmpname(rs.getString("empname"));
                sale.setDayDate(rs.getString("daydate"));
                sale.setDiscount(rs.getDouble("Discount"));
                list.add(sale);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "sss");
        }
        return list;
    }

    public static ArrayList<salesReturnModel> getAllSalesReturnForDateandState(String Date) {
        ArrayList<salesReturnModel> list = new ArrayList<>();
        try {
            ResultSet rs = dbOperations.getData("select * from salesReturn where date like'" + Date + "____________%'"); //2023-07-12 01:15:21 PM
            while (rs.next()) {
                salesReturnModel sale = new salesReturnModel();
                sale.setSaleId(rs.getInt("saleID"));
                sale.setINID(rs.getInt("INID"));
                sale.setCustomerName(rs.getString("Customername"));
                sale.setTotal_Quantity(rs.getDouble("Total_Quantity"));
                sale.setPayed(rs.getDouble("payed"));

                sale.setRemained(rs.getDouble("Remained"));
                sale.setDate(rs.getString("date"));
                 sale.setTotal(rs.getDouble("Total"));
                sale.setEmpname(rs.getString("empname"));
                sale.setDayDate(rs.getString("daydate"));
                sale.setDiscount(rs.getDouble("Discount"));
                 sale.setAllTotal(rs.getDouble("AllTotal"));
                list.add(sale);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "sss");
        }
        return list;
    }

    public static ArrayList<salesReturnModel> getAllSalesReturnForEmpnameandDateandState(String empname, String Date, String state) {
        ArrayList<salesReturnModel> list = new ArrayList<>();
        try {
            ResultSet rs = dbOperations.getData("select * from salesReturn where empname='" + empname + "' and date like'" + Date + "____________%' and status='" + state + "'"); //2023-07-12 01:15:21 PM
            while (rs.next()) {
                salesReturnModel sale = new salesReturnModel();
                sale.setSaleId(rs.getInt("saleID"));
                sale.setINID(rs.getInt("INID"));
                sale.setCustomerName(rs.getString("Customername"));
                sale.setTotal_Quantity(rs.getDouble("Total_Quantity"));
                 sale.setAllTotal(rs.getDouble("AllTotal"));
                sale.setPayed(rs.getDouble("payed"));

                sale.setRemained(rs.getDouble("Remained"));
                sale.setDate(rs.getString("date"));
                 sale.setTotal(rs.getDouble("Total"));
                sale.setEmpname(rs.getString("empname"));
                sale.setDayDate(rs.getString("daydate"));
                sale.setDiscount(rs.getDouble("Discount"));
                list.add(sale);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "sss");
        }
        return list;
    }

    public static ArrayList<salesReturnModel> getAllSalesReturnForAll(String custoemername, String Date) {
        ArrayList<salesReturnModel> list = new ArrayList<>();
        try {
            ResultSet rs = dbOperations.getData("select * from salesReturn where customername='" + custoemername + "' and date like'" + Date + "____________%'"); //2023-07-12 01:15:21 PM"'
            while (rs.next()) {
                salesReturnModel sale = new salesReturnModel();
                sale.setSaleId(rs.getInt("saleID"));
                sale.setINID(rs.getInt("INID"));
                sale.setCustomerName(rs.getString("Customername"));
                sale.setTotal_Quantity(rs.getDouble("Total_Quantity"));
                 sale.setAllTotal(rs.getDouble("AllTotal"));
                sale.setPayed(rs.getDouble("payed"));

                sale.setRemained(rs.getDouble("Remained"));
                sale.setDate(rs.getString("date"));
                 sale.setTotal(rs.getDouble("Total"));
                sale.setEmpname(rs.getString("empname"));
                sale.setDayDate(rs.getString("daydate"));
                sale.setDiscount(rs.getDouble("Discount"));
                list.add(sale);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "sss");
        }
        return list;
    }

    public static ArrayList<salesReturnModel> getAllSalesReturnForAllwithEmpname(String empname, String Date) {
        ArrayList<salesReturnModel> list = new ArrayList<>();
        try {
            ResultSet rs=dbOperations.getData("select * from salesReturn where empname='"+empname+"' and date like'"+Date+"____________%'"); //2023-07-12 01:15:21 PM"'
            while (rs.next()) {
                salesReturnModel sale = new salesReturnModel();
                sale.setSaleId(rs.getInt("saleID"));
                sale.setINID(rs.getInt("INID"));
                sale.setCustomerName(rs.getString("Customername"));
                sale.setTotal_Quantity(rs.getDouble("Total_Quantity"));
                 sale.setAllTotal(rs.getDouble("AllTotal"));
                sale.setPayed(rs.getDouble("payed"));
                sale.setSaleType(rs.getString("saleType")); 
                sale.setRemained(rs.getDouble("Remained"));
                sale.setDate(rs.getString("date"));
                 sale.setTotal(rs.getDouble("Total"));
                sale.setEmpname(rs.getString("empname"));
                sale.setDayDate(rs.getString("daydate"));
                sale.setDiscount(rs.getDouble("Discount"));
                list.add(sale);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "sss");
        }
        return list;
    }
        public static ArrayList<salesReturnModel> ggetAllSalesReturnForDate(String Date) {
        ArrayList<salesReturnModel> list = new ArrayList<>();
        try {
            ResultSet rs=dbOperations.getData("select * from salesReturn where date like'"+Date+"____________%'");
            while (rs.next()) {
                salesReturnModel sale = new salesReturnModel();
                sale.setSaleId(rs.getInt("saleID"));
                sale.setINID(rs.getInt("INID"));
                sale.setCustomerName(rs.getString("Customername"));
                sale.setTotal_Quantity(rs.getDouble("Total_Quantity"));
                sale.setPayed(rs.getDouble("payed"));

                 sale.setTotal(rs.getDouble("Total"));
                sale.setRemained(rs.getDouble("Remained"));
                sale.setDate(rs.getString("date"));
                sale.setEmpname(rs.getString("empname"));
                sale.setDayDate(rs.getString("daydate"));
                 sale.setAllTotal(rs.getDouble("AllTotal"));
                sale.setDiscount(rs.getDouble("Discount"));
                list.add(sale);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "sss");
        }
        return list;
    }
  public static double[] getSumofPayedAndRemainedDaily(String Date)
    {
        double Total=0;
        double Remianed=0;
        double [] ALL=new double[2];
        try {
            ResultSet rs=dbOperations.getData("select * from salesReturn where DayDate='"+Date+"'");
            while(rs.next())
            {
               Total+=rs.getDouble("payed");
               Remianed+=rs.getDouble("Remained");
            }
          ALL[0]=Total;
          ALL[1]=Remianed;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return ALL;
    }
         public static int getSumofPayedMonthly(String Month,String year)
    {
        int Total=0;
        try {
            ResultSet rs=dbOperations.getData("select payed from salesReturn where date like'"+year+"_"+Month+"_______________%'");//2023-07-12 03:41:14 PM
            while(rs.next())
            {
               Total+=Integer.parseInt(rs.getString("payed"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return Total;
    }
          public static ArrayList<salesReturnModel> getAllSalesReturnForCustomernameandState(String custoemername)
    {
       ArrayList<salesReturnModel> list=new ArrayList<>();
        try {
            ResultSet rs=dbOperations.getData("select * from salesReturn where customername='"+custoemername+"'"); //2023-07-12 01:15:21 PM
              while(rs.next())
            {
               salesReturnModel sale = new salesReturnModel();
                sale.setSaleId(rs.getInt("saleID"));
                sale.setINID(rs.getInt("INID"));
                sale.setCustomerName(rs.getString("Customername"));
                sale.setTotal_Quantity(rs.getDouble("Total_Quantity"));
                sale.setPayed(rs.getDouble("payed"));
                sale.setTotal(rs.getDouble("Total"));
                        sale.setSaleType(rs.getString("saleType")); 
                 sale.setAllTotal(rs.getDouble("AllTotal"));
                sale.setRemained(rs.getDouble("Remained"));
                sale.setDate(rs.getString("date"));
                sale.setEmpname(rs.getString("empname"));
                sale.setDayDate(rs.getString("daydate"));
                sale.setDiscount(rs.getDouble("Discount"));
                list.add(sale);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"sss");
        }
        return list;
    }
           public static ArrayList<salesReturnModel> getAllSalesReturnrecordsWithMonth(String year,String Month){
        ArrayList<salesReturnModel> list = new ArrayList<>();
        try {
            ResultSet rs=dbOperations.getData("select * from salesReturn where date like'"+year+"_"+Month+"_______________%'");//2023-07-12 03:41:14 PM
            while (rs.next()) {
                salesReturnModel sale = new salesReturnModel();
                sale.setSaleId(rs.getInt("saleID"));
                sale.setINID(rs.getInt("INID"));
                sale.setCustomerName(rs.getString("Customername"));
                sale.setTotal_Quantity(rs.getDouble("Total_Quantity"));
                sale.setPayed(rs.getDouble("payed"));
                sale.setTotal(rs.getDouble("Total"));
                sale.setSaleType(rs.getString("saleType"));
                 sale.setAllTotal(rs.getDouble("AllTotal"));
                sale.setRemained(rs.getDouble("Remained"));
                sale.setDate(rs.getString("date"));
                sale.setEmpname(rs.getString("empname"));
                sale.setDayDate(rs.getString("daydate"));
                sale.setDiscount(rs.getDouble("Discount"));
                list.add(sale);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "sss");
        }
        return list;
    }

             public static ArrayList<salesReturnModel>getAllSalesReturnWithMonthAndCustomername(String customername,String year,String Month){
        ArrayList<salesReturnModel> list = new ArrayList<>();
        try {
            ResultSet rs=dbOperations.getData("select * from salesReturn where customername='"+customername+"' and date like'"+year+"_"+Month+"_______________%'");//2023-07-12 03:41:14 PM
            while (rs.next()) {
                salesReturnModel sale = new salesReturnModel();
                sale.setSaleId(rs.getInt("saleID"));
                sale.setINID(rs.getInt("INID"));
                sale.setCustomerName(rs.getString("Customername"));
                sale.setTotal_Quantity(rs.getDouble("Total_Quantity"));
                sale.setPayed(rs.getDouble("payed"));

                sale.setRemained(rs.getDouble("Remained"));
                sale.setDate(rs.getString("date"));
                sale.setEmpname(rs.getString("empname"));
                 sale.setAllTotal(rs.getDouble("AllTotal"));
                sale.setDayDate(rs.getString("daydate"));
                 sale.setTotal(rs.getDouble("Total"));
                sale.setDiscount(rs.getDouble("Discount"));
                list.add(sale);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "sss");
        }
        return list;
    }
             public static ArrayList<salesReturnModel>getAllSalesReturnWithMonthAndEmpname(String Empname,String year,String Month){
        ArrayList<salesReturnModel> list = new ArrayList<>();
        try {
            ResultSet rs=dbOperations.getData("select * from salesReturn where empname='"+Empname+"' and date like'"+year+"_"+Month+"_______________%'");//2023-07-12 03:41:14 PM
            while (rs.next()) {
                salesReturnModel sale = new salesReturnModel();
                sale.setSaleId(rs.getInt("saleID"));
                sale.setINID(rs.getInt("INID"));
                sale.setCustomerName(rs.getString("Customername"));
                sale.setTotal_Quantity(rs.getDouble("Total_Quantity"));
                sale.setPayed(rs.getDouble("payed"));
                sale.setSaleType(rs.getString("saleType"));
                sale.setRemained(rs.getDouble("Remained"));
                sale.setDate(rs.getString("date"));
                 sale.setAllTotal(rs.getDouble("AllTotal"));
                 sale.setTotal(rs.getDouble("Total"));
                sale.setEmpname(rs.getString("empname"));
                sale.setDayDate(rs.getString("daydate"));
                sale.setDiscount(rs.getDouble("Discount"));
                list.add(sale);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "sss");
        }
        return list;
    }
                          public static ArrayList<salesReturnModel>getAllSalesReturnWithMonthAndState(String status,String year,String Month){
        ArrayList<salesReturnModel> list = new ArrayList<>();
        try {
            ResultSet rs=dbOperations.getData("select * from salesReturn where status='"+status+"' and date like'"+year+"_"+Month+"_______________%'");//2023-07-12 03:41:14 PM
            while (rs.next()) {
                salesReturnModel sale = new salesReturnModel();
                sale.setSaleId(rs.getInt("saleID"));
                sale.setINID(rs.getInt("INID"));
                sale.setCustomerName(rs.getString("Customername"));
                sale.setTotal_Quantity(rs.getDouble("Total_Quantity"));
                sale.setPayed(rs.getDouble("payed"));
                sale.setRemained(rs.getDouble("Remained"));
                sale.setDate(rs.getString("date"));
                 sale.setAllTotal(rs.getDouble("AllTotal"));
                 sale.setTotal(rs.getDouble("Total"));
                sale.setEmpname(rs.getString("empname"));
                sale.setDayDate(rs.getString("daydate"));
                sale.setDiscount(rs.getDouble("Discount"));
                list.add(sale);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "sss");
        }
        return list;
    }
}
