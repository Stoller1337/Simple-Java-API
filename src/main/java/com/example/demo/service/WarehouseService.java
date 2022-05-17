package com.example.demo.service;

import com.example.demo.entity.Warehouse;

import java.util.List;


public interface WarehouseService {
    List<Warehouse> listWarehouse();//y
    Warehouse findById(long id);//y
    Warehouse addWarehouse(Warehouse warehouse);//y

    void deleteWarehouse(long id);//y
    List<Warehouse> sortASCById();//y
    List<Warehouse> sortDESCById();//y

    List<Warehouse> updateQuantity(long id, int quantity);//y
    List<Warehouse> updateAmount(long id, int amount);//y
    List<Warehouse> updateName(long id, String name);//y
}
