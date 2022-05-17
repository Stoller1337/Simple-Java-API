package com.example.demo.service;

import com.example.demo.entity.Sales;
import com.example.demo.entity.Warehouse;

import javax.transaction.Transactional;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Transactional
public interface SalesService {

    List<Sales> listSales();//y

    Sales addSales(Sales sales, Long warehouse_id);//y
    Sales addSales(Sales sales);//y

    List<Sales> sortASCById();//y
    List<Sales> sortDESCById();//y
    Sales findById(long id);//y
    List<Sales> updateQuantity(long id, int quantity);//y
    List<Sales> updateAmount(long id, int amount);//y
    List<Sales> updateSalesDate(long id, String tm);//y

    void deleteSales(long id);//y

    List<Object> maxAmountOfSalesProducts();//y
    void updateQuantityInStore(long id, int quantity);
    List<Object[]> listSalesProducts();
    void updateAmountInStore(long id, int amount);


}
