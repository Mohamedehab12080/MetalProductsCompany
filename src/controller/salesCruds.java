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
import model.salesModel;

/**
 *
 * @author BOBO
 */
public class salesCruds {

    public static ArrayList<salesModel> getAllSaleswithTodayDate(String date) {
        ArrayList<salesModel> list = new ArrayList<>();
        try {
            ResultSet rs = dbOperations.getData("select * from sales where date like'" + date + "____________%'");
            while (rs.next()) {
                salesModel sale = new salesModel();
                sale.setSaleId(rs.getInt("saleID"));
                sale.setINID(rs.getInt("INID"));
                sale.setCustomerName(rs.getString("Customername"));
                sale.setTotal_Quantity(rs.getDouble("Total_Quantity"));
                sale.setPayed(rs.getDouble("payed"));
                sale.setSaleType(rs.getString("SaleType"));
                sale.setStatus(rs.getString("status"));
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

    public static ArrayList<salesModel> getAllSaleswithEmpnameandTodayDate(String empname, String date) {
        ArrayList<salesModel> list = new ArrayList<>();
        try {
            ResultSet rs = dbOperations.getData("select * from sales where empname='" + empname + "' and date like'" + date + "____________%'");
            while (rs.next()) {
                salesModel sale = new salesModel();
                sale.setSaleId(rs.getInt("saleID"));
                sale.setINID(rs.getInt("INID"));
                sale.setCustomerName(rs.getString("Customername"));
                sale.setTotal_Quantity(rs.getDouble("Total_Quantity"));
                sale.setPayed(rs.getDouble("payed"));
                sale.setStatus(rs.getString("status"));
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

    public static ArrayList<salesModel> getAllSaleswithEmpnameandState(String empname, String state) {
        ArrayList<salesModel> list = new ArrayList<>();
        try {
            ResultSet rs = dbOperations.getData("select * from sales where empname='" + empname + "' and state='" + state + "'");
            while (rs.next()) {
                salesModel sale = new salesModel();
                sale.setSaleId(rs.getInt("saleID"));
                sale.setINID(rs.getInt("INID"));
                sale.setCustomerName(rs.getString("Customername"));
                sale.setTotal_Quantity(rs.getDouble("Total_Quantity"));
                sale.setPayed(rs.getDouble("payed"));
                sale.setStatus(rs.getString("status"));
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

    public static ArrayList<salesModel> getAllSales(int invId) {
        ArrayList<salesModel> list = new ArrayList<>();
        try {
            ResultSet rs = dbOperations.getData("select * from sales where INID=" + invId);
            while (rs.next()) {
                salesModel sale = new salesModel();
                sale.setSaleId(rs.getInt("saleID"));
                sale.setINID(rs.getInt("INID"));
                sale.setCustomerName(rs.getString("Customername"));
                sale.setTotal_Quantity(rs.getDouble("Total_Quantity"));
                sale.setPayed(rs.getDouble("payed"));
                sale.setSaleType(rs.getString("saleType"));
                sale.setStatus(rs.getString("status"));
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

    public static ArrayList<salesModel> getAllSalesForPay(int invId) {
        ArrayList<salesModel> list = new ArrayList<>();
        try {
            ResultSet rs = dbOperations.getData("select * from sales where INID=" + invId + " and Remained > 0");
            while (rs.next()) {
                salesModel sale = new salesModel();
                sale.setSaleId(rs.getInt("saleID"));
                sale.setINID(rs.getInt("INID"));
                sale.setCustomerName(rs.getString("Customername"));
                sale.setTotal_Quantity(rs.getDouble("Total_Quantity"));
                sale.setPayed(rs.getDouble("payed"));
                sale.setStatus(rs.getString("status"));
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

    public static ArrayList<salesModel> getAllSalesRemainedForCustomer(String customerName) {
        ArrayList<salesModel> list = new ArrayList<>();
        try {
            ResultSet rs = dbOperations.getData("select * from sales where customername='" + customerName + "' and Remained > 0");
            while (rs.next()) {
                salesModel sale = new salesModel();
                sale.setSaleId(rs.getInt("saleID"));
                sale.setINID(rs.getInt("INID"));
                sale.setCustomerName(rs.getString("Customername"));
                sale.setTotal_Quantity(rs.getDouble("Total_Quantity"));
                sale.setPayed(rs.getDouble("payed"));
                sale.setStatus(rs.getString("status"));
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
            ResultSet rs = dbOperations.getData("select sum(Remained) as Remained from sales where  Remained > 0");
            if (rs.next()) {
                result = rs.getInt("Remained");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return result;
    }

    public static ArrayList<salesModel> getAllSalesForPayRemained() {
        ArrayList<salesModel> list = new ArrayList<>();
        try {
            ResultSet rs = dbOperations.getData("select * from sales where  Remained > 0");
            while (rs.next()) {
                salesModel sale = new salesModel();
                sale.setSaleId(rs.getInt("saleID"));
                sale.setINID(rs.getInt("INID"));
                sale.setCustomerName(rs.getString("Customername"));
                sale.setTotal_Quantity(rs.getDouble("Total_Quantity"));
                sale.setPayed(rs.getDouble("payed"));
                sale.setStatus(rs.getString("status"));
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

    public static ArrayList<salesModel> getAllSalesForCustomernameandDate(String custoemername, String Date) {
        ArrayList<salesModel> list = new ArrayList<>();
        try {
            ResultSet rs = dbOperations.getData("select * from sales where customername='" + custoemername + "' and date like'" + Date + "____________%'"); //2023-07-12 01:15:21 PM
            while (rs.next()) {
                salesModel sale = new salesModel();
                sale.setSaleId(rs.getInt("saleID"));
                sale.setINID(rs.getInt("INID"));
                sale.setCustomerName(rs.getString("Customername"));
                sale.setTotal_Quantity(rs.getDouble("Total_Quantity"));
                sale.setPayed(rs.getDouble("payed"));
                sale.setStatus(rs.getString("status"));
                sale.setSaleType(rs.getString("saleType"));
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

    public static ArrayList<salesModel> getAllSalesForEmpnameandCustomernameandDate(String empname, String custoemername, String Date) {
        ArrayList<salesModel> list = new ArrayList<>();
        try {
            ResultSet rs = dbOperations.getData("select * from sales where empname='" + empname + "' and customername='" + custoemername + "' and date like'" + Date + "____________%'"); //2023-07-12 01:15:21 PM
            while (rs.next()) {
                salesModel sale = new salesModel();
                sale.setSaleId(rs.getInt("saleID"));
                sale.setINID(rs.getInt("INID"));
                sale.setCustomerName(rs.getString("Customername"));
                sale.setTotal_Quantity(rs.getDouble("Total_Quantity"));
                sale.setPayed(rs.getDouble("payed"));
                sale.setStatus(rs.getString("status"));
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

    public static ArrayList<salesModel> etAllSalesWithEmpnameMonthly(String empname, String Date) {
        ArrayList<salesModel> list = new ArrayList<>();
        try {
            ResultSet rs = dbOperations.getData("select * from sales where empname='" + empname + "'  and date like'" + Date + "____________%'"); //2023-07-12 01:15:21 PM
            while (rs.next()) {
                salesModel sale = new salesModel();
                sale.setSaleId(rs.getInt("saleID"));
                sale.setINID(rs.getInt("INID"));
                sale.setCustomerName(rs.getString("Customername"));
                sale.setTotal_Quantity(rs.getDouble("Total_Quantity"));
                sale.setPayed(rs.getDouble("payed"));
                sale.setStatus(rs.getString("status"));
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

    public static ArrayList<salesModel> getAllSalesWithDayDate(String Date) {
        ArrayList<salesModel> list = new ArrayList<>();
        try {
            ResultSet rs = dbOperations.getData("select * from sales where Daydate='" + Date + "'"); //2023-07-12 01:15:21 PM
            while (rs.next()) {
                salesModel sale = new salesModel();
                sale.setSaleId(rs.getInt("saleID"));
                sale.setINID(rs.getInt("INID"));
                sale.setCustomerName(rs.getString("Customername"));
                sale.setTotal_Quantity(rs.getDouble("Total_Quantity"));
                sale.setPayed(rs.getDouble("payed"));
                sale.setStatus(rs.getString("status"));
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

    public static ArrayList<salesModel> getAllSalesForDateandState(String Date, String state) {
        ArrayList<salesModel> list = new ArrayList<>();
        try {
            ResultSet rs = dbOperations.getData("select * from sales where date like'" + Date + "____________%' and status='" + state + "'"); //2023-07-12 01:15:21 PM
            while (rs.next()) {
                salesModel sale = new salesModel();
                sale.setSaleId(rs.getInt("saleID"));
                sale.setINID(rs.getInt("INID"));
                sale.setCustomerName(rs.getString("Customername"));
                sale.setTotal_Quantity(rs.getDouble("Total_Quantity"));
                sale.setSaleType(rs.getString("saleType"));
                sale.setPayed(rs.getDouble("payed"));
                sale.setStatus(rs.getString("status"));
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

    public static ArrayList<salesModel> getAllSalesForEmpnameandDateandState(String empname, String Date, String state) {
        ArrayList<salesModel> list = new ArrayList<>();
        try {
            ResultSet rs = dbOperations.getData("select * from sales where empname='" + empname + "' and date like'" + Date + "____________%' and status='" + state + "'"); //2023-07-12 01:15:21 PM
            while (rs.next()) {
                salesModel sale = new salesModel();
                sale.setSaleId(rs.getInt("saleID"));
                sale.setINID(rs.getInt("INID"));
                sale.setCustomerName(rs.getString("Customername"));
                sale.setTotal_Quantity(rs.getDouble("Total_Quantity"));
                 sale.setAllTotal(rs.getDouble("AllTotal"));
                sale.setPayed(rs.getDouble("payed"));
                sale.setStatus(rs.getString("status"));
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

    public static ArrayList<salesModel> getAllSalesForAll(String custoemername, String Date, String state) {
        ArrayList<salesModel> list = new ArrayList<>();
        try {
            ResultSet rs = dbOperations.getData("select * from sales where customername='" + custoemername + "' and date like'" + Date + "____________%' and status='" + state + "'"); //2023-07-12 01:15:21 PM"'
            while (rs.next()) {
                salesModel sale = new salesModel();
                sale.setSaleId(rs.getInt("saleID"));
                sale.setINID(rs.getInt("INID"));
                sale.setCustomerName(rs.getString("Customername"));
                sale.setTotal_Quantity(rs.getDouble("Total_Quantity"));
                 sale.setAllTotal(rs.getDouble("AllTotal"));
                sale.setPayed(rs.getDouble("payed"));
                sale.setStatus(rs.getString("status"));
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

    public static ArrayList<salesModel> getAllSalesForAllwithEmpname(String empname, String Date) {
        ArrayList<salesModel> list = new ArrayList<>();
        try {
            ResultSet rs=dbOperations.getData("select * from sales where empname='"+empname+"' and date like'"+Date+"____________%'"); //2023-07-12 01:15:21 PM"'
            while (rs.next()) {
                salesModel sale = new salesModel();
                sale.setSaleId(rs.getInt("saleID"));
                sale.setINID(rs.getInt("INID"));
                sale.setCustomerName(rs.getString("Customername"));
                sale.setTotal_Quantity(rs.getDouble("Total_Quantity"));
                 sale.setAllTotal(rs.getDouble("AllTotal"));
                sale.setPayed(rs.getDouble("payed"));
                sale.setStatus(rs.getString("status"));
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
        public static ArrayList<salesModel> ggetAllSalesForDate(String Date) {
        ArrayList<salesModel> list = new ArrayList<>();
        try {
            ResultSet rs=dbOperations.getData("select * from sales where date like'"+Date+"____________%'");
            while (rs.next()) {
                salesModel sale = new salesModel();
                sale.setSaleId(rs.getInt("saleID"));
                sale.setINID(rs.getInt("INID"));
                sale.setCustomerName(rs.getString("Customername"));
                sale.setTotal_Quantity(rs.getDouble("Total_Quantity"));
                sale.setPayed(rs.getDouble("payed"));
                sale.setStatus(rs.getString("status"));
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
            ResultSet rs=dbOperations.getData("select * from sales where DayDate='"+Date+"'");
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
            ResultSet rs=dbOperations.getData("select payed from sales where date like'"+year+"_"+Month+"_______________%'");//2023-07-12 03:41:14 PM
            while(rs.next())
            {
               Total+=Integer.parseInt(rs.getString("payed"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return Total;
    }
          public static ArrayList<salesModel> getAllSalesForCustomernameandState(String custoemername,String state)
    {
       ArrayList<salesModel> list=new ArrayList<>();
        try {
            ResultSet rs=dbOperations.getData("select * from sales where customername='"+custoemername+"' and Status='"+state+"'"); //2023-07-12 01:15:21 PM
              while(rs.next())
            {
               salesModel sale = new salesModel();
                sale.setSaleId(rs.getInt("saleID"));
                sale.setINID(rs.getInt("INID"));
                sale.setCustomerName(rs.getString("Customername"));
                sale.setTotal_Quantity(rs.getDouble("Total_Quantity"));
                sale.setSaleType(rs.getString("saleType"));
                sale.setPayed(rs.getDouble("payed"));
                sale.setTotal(rs.getDouble("Total"));
                sale.setStatus(rs.getString("status"));
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
           public static ArrayList<salesModel> getAllSalesrecordsWithMonth(String year,String Month){
        ArrayList<salesModel> list = new ArrayList<>();
        try {
            ResultSet rs=dbOperations.getData("select * from sales where date like'"+year+"_"+Month+"_______________%'");//2023-07-12 03:41:14 PM
            while (rs.next()) {
                salesModel sale = new salesModel();
                sale.setSaleId(rs.getInt("saleID"));
                sale.setINID(rs.getInt("INID"));
                sale.setCustomerName(rs.getString("Customername"));
                sale.setTotal_Quantity(rs.getDouble("Total_Quantity"));
                sale.setPayed(rs.getDouble("payed"));
                sale.setSaleType(rs.getString("saleType"));
                sale.setTotal(rs.getDouble("Total"));
                sale.setStatus(rs.getString("status"));
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

             public static ArrayList<salesModel>getAllSalesWithMonthAndCustomername(String customername,String year,String Month){
        ArrayList<salesModel> list = new ArrayList<>();
        try {
            ResultSet rs=dbOperations.getData("select * from sales where customername='"+customername+"' and date like'"+year+"_"+Month+"_______________%'");//2023-07-12 03:41:14 PM
            while (rs.next()) {
                salesModel sale = new salesModel();
                sale.setSaleId(rs.getInt("saleID"));
                sale.setINID(rs.getInt("INID"));
                sale.setCustomerName(rs.getString("Customername"));
                sale.setTotal_Quantity(rs.getDouble("Total_Quantity"));
                sale.setPayed(rs.getDouble("payed"));
                sale.setStatus(rs.getString("status"));
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
             public static ArrayList<salesModel>getAllSalesWithMonthAndEmpname(String Empname,String year,String Month){
        ArrayList<salesModel> list = new ArrayList<>();
        try {
            ResultSet rs=dbOperations.getData("select * from sales where empname='"+Empname+"' and date like'"+year+"_"+Month+"_______________%'");//2023-07-12 03:41:14 PM
            while (rs.next()) {
                salesModel sale = new salesModel();
                sale.setSaleId(rs.getInt("saleID"));
                sale.setINID(rs.getInt("INID"));
                sale.setCustomerName(rs.getString("Customername"));
                sale.setTotal_Quantity(rs.getDouble("Total_Quantity"));
                sale.setPayed(rs.getDouble("payed"));
                sale.setStatus(rs.getString("status"));
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
                          public static ArrayList<salesModel>getAllSalesWithMonthAndState(String status,String year,String Month){
        ArrayList<salesModel> list = new ArrayList<>();
        try {
            ResultSet rs=dbOperations.getData("select * from sales where status='"+status+"' and date like'"+year+"_"+Month+"_______________%'");//2023-07-12 03:41:14 PM
            while (rs.next()) {
                salesModel sale = new salesModel();
                sale.setSaleId(rs.getInt("saleID"));
                sale.setINID(rs.getInt("INID"));
                sale.setCustomerName(rs.getString("Customername"));
                sale.setTotal_Quantity(rs.getDouble("Total_Quantity"));
                sale.setPayed(rs.getDouble("payed"));
                sale.setStatus(rs.getString("status"));
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
