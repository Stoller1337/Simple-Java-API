package com.example.demo.service;

import com.example.demo.entity.Sales;
import com.example.demo.entity.Warehouse;
import com.example.demo.exception.ChargesNotFoundExceptions;
import com.example.demo.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WarehouseServiceImpl implements  WarehouseService{

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Override
    public List<Warehouse> listWarehouse() {
        return(List<Warehouse>) warehouseRepository.findAll();
    }

    @Override
    public Warehouse findById(long id) {
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(id);
        if(optionalWarehouse.isPresent()){
            return optionalWarehouse.get();
        }else{
            throw new ChargesNotFoundExceptions("Warehouse not found");
        }
    }

    @Override
    public Warehouse addWarehouse(Warehouse warehouse) {
        return warehouseRepository.save(warehouse);
    }

    @Override
    public void deleteWarehouse(long id) {
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(id);
        if(optionalWarehouse.isPresent()){
            warehouseRepository.deleteByid(id);
        } else {
            throw new ChargesNotFoundExceptions("Sales not found");
        }
    }

    @Override
    public List<Warehouse> sortASCById() {
        return warehouseRepository.sortASCById();
    }

    @Override
    public List<Warehouse> sortDESCById() {
        return warehouseRepository.sortDESCById();
    }

    @Override
    public List<Warehouse> updateQuantity(long id, int quantity) {
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(id);
        if(optionalWarehouse.isPresent()){
            return warehouseRepository.updateQuantity(id, quantity);
        } else{
            throw new ChargesNotFoundExceptions("not found");
        }
    }

    @Override
    public List<Warehouse> updateAmount(long id, int amount) {
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(id);
        if(optionalWarehouse.isPresent()){
            return warehouseRepository.updateAmount(id, amount);
        } else{
            throw new ChargesNotFoundExceptions("not found");
        }
    }

    @Override
    public List<Warehouse> updateName(long id, String name) {
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(id);
        if(optionalWarehouse.isPresent()){
            return warehouseRepository.updateName(id, name);
        } else{
            throw new ChargesNotFoundExceptions("not found");
        }
    }

}
