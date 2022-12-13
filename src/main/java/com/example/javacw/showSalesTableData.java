package com.example.javacw;

import java.util.Date;

public class showSalesTableData {

    Date date_salesTable;
    String movieName_salesTable;
    int movieID_salesTable;
    int quantity_salesTable;
    int salesID_salesTable;
    double totalPrice_salesTable;

    public showSalesTableData( Date date_salesTable,String movieName_salesTable, int quantity_salesTable,double totalPrice_salesTable,int salesID_salesTable){

        this.date_salesTable=date_salesTable;
        this.movieName_salesTable=movieName_salesTable;
        this.quantity_salesTable=quantity_salesTable;
        this.totalPrice_salesTable=totalPrice_salesTable;
        this.salesID_salesTable=salesID_salesTable;

    }

    public Date getDate_salesTable() {
        return date_salesTable;
    }

    public String getMovieName_salesTable() {
        return movieName_salesTable;
    }

    public int getQuantity_salesTable() {
        return quantity_salesTable;
    }

    public double getTotalPrice_salesTable() {
        return totalPrice_salesTable;
    }

    public int getSalesID_salesTable(){
        return salesID_salesTable;
    }


}
