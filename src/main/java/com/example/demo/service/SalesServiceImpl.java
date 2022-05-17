package com.example.demo.service;

import com.example.demo.entity.Sales;
import com.example.demo.entity.Warehouse;
import com.example.demo.exception.ChargesNotFoundExceptions;
import com.example.demo.exception.SalesNotFoundException;
import com.example.demo.repository.SalesRepository;
import com.example.demo.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalesServiceImpl implements SalesService{

    @Autowired
    private SalesRepository salesRepository;

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Override
    public List<Sales> listSales() {
        return salesRepository.listSales();
    }

    @Override
    public Sales addSales(Sales sales, Long warehouse_id) {
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(warehouse_id);
        if (optionalWarehouse.isPresent()) {
            Warehouse warehouse = new Warehouse();
            warehouse = optionalWarehouse.get();
            sales.setWarehouseId(warehouse);
            return salesRepository.save(sales);
        } else{
            throw new SalesNotFoundException("Invalid sales!");
        }
    }
    @Override
    public Sales addSales(Sales sales) {
        if(sales == null) {
            throw new SalesNotFoundException("Invalid sales!");
        }
        return salesRepository.save(sales);

    }
    @Override
    public Sales findById(long id) {
        Optional<Sales> optionalSales = salesRepository.findById(id);
        if(optionalSales.isPresent()){
            return optionalSales.get();
        }else{
            throw new ChargesNotFoundExceptions("Sales not found");
        }
    }

    @Override
    public List<Sales> updateQuantity(long id, int quantity){
        Optional<Sales> optionalSales = salesRepository.findById(id);
        if(optionalSales.isPresent()){
            return salesRepository.updateQuantity(id, quantity);
        } else{
            throw new ChargesNotFoundExceptions("Sales not found");
        }
    }

    @Override
    public List<Sales> updateAmount(long id, int amount) {
        Optional<Sales> optionalSales = salesRepository.findById(id);
        if(optionalSales.isPresent()){
            Sales sales = findById(id);
            sales.setAmount(amount);
            salesRepository.save(sales);
            return salesRepository.listSales();
        } else{
            throw new ChargesNotFoundExceptions("Sales not found");
        }
    }

    @Override
    public void updateQuantityInStore(long id, int quantity) {
        Optional<Sales> optionalSales = salesRepository.findById(id);
        if(optionalSales.isPresent()){
            Sales sales = findById(id);
            Warehouse warehouse = sales.getWarehouseId();
            sales.setQuantity(quantity);
            warehouse.setQuantity(quantity);
            salesRepository.save(sales);
            warehouseRepository.save(warehouse);
        } else{
            throw new ChargesNotFoundExceptions("Product not found");
        }
    }

    @Override
    public void updateAmountInStore(long id, int amount) {
        Optional<Sales> optionalSales = salesRepository.findById(id);
        if(optionalSales.isPresent()){
            Sales sales = findById(id);
            Warehouse warehouse = sales.getWarehouseId();
            sales.setAmount(amount);
            warehouse.setAmount(amount);
            salesRepository.save(sales);
            warehouseRepository.save(warehouse);
        } else{
            throw new ChargesNotFoundExceptions("Product not found");
        }
    }

    @Override
    public List<Sales> updateSalesDate(long id, String tm) {
        Optional<Sales> optionalSales = salesRepository.findById(id);
        if(optionalSales.isPresent()){
            return salesRepository.updateSalesDate(id, tm);
        } else{
            throw new ChargesNotFoundExceptions("Sales not found");
        }
    }

    @Override
    public void deleteSales(long id){
        Optional<Sales> optionalSales = salesRepository.findById(id);
        if(optionalSales.isPresent()){
            salesRepository.deleteById(id);
        } else {
            throw new ChargesNotFoundExceptions("Sales not found");
        }
    }

    @Override
    public List<Sales> sortASCById() {
        return salesRepository.sortASCById();
    }

    @Override
    public List<Sales> sortDESCById() {
        return salesRepository.sortDESCById();
    }

    @Override
    public List<Object> maxAmountOfSalesProducts() {
        return salesRepository.maxAmountOfSalesProducts();
    }

    @Override
    public List<Object[]> listSalesProducts() {
        return salesRepository.listSalesProducts();
    }

    //    @Override
//    public void updateAmountInStore(int amount, long id) {
//        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(id);
//        if(optionalWarehouse.isPresent()){
//            salesRepository.updateAmountInStore(amount, id);
//        } else {
//            throw new ChargesNotFoundExceptions("Can't update, item not found");
//        }
//    }
}
