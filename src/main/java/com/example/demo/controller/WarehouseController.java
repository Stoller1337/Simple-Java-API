package com.example.demo.controller;

import com.example.demo.entity.Warehouse;
import com.example.demo.exception.ChargesNotFoundExceptions;
import com.example.demo.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("api/warehouse")
public class WarehouseController {
    private WarehouseService warehouseService;

    @Autowired
    public void setWarehouseService(WarehouseService warehouseService) { this.warehouseService = warehouseService; }

    @PostMapping(value = "/add", consumes = "application/json", produces = "application/json")
    public Warehouse addWarehouse(@RequestBody Warehouse newWarehouse){
        return warehouseService.addWarehouse(newWarehouse);
    }

    @DeleteMapping(value = "/deleteWarehouse/{id}", consumes = "application/json", produces = "application/json")
    public void deleteWarehouses(@PathVariable("id") long id){
        try{
            warehouseService.deleteWarehouse(id);
        }catch (ChargesNotFoundExceptions exceptions){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Warehouse not found");
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Warehouse>> getAllWarehouses(){
        List<Warehouse> list = warehouseService.listWarehouse();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Warehouse> getWarehouses(@PathVariable("id") long id){
        try{
            return new ResponseEntity<>(warehouseService.findById(id), HttpStatus.OK);
        }catch(ChargesNotFoundExceptions exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Charges not found");
        }
    }

    @GetMapping("/sort/asc")
    public ResponseEntity<Warehouse> sortWarehouseASC(){
        try{
            return new ResponseEntity(warehouseService.sortASCById(), HttpStatus.OK);
        }catch(ChargesNotFoundExceptions exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Warehouse not found");
        }
    }

    @GetMapping("/sort/desc")
    public ResponseEntity<Warehouse> sortWarehouseDESC(){
        try{
            return new ResponseEntity(warehouseService.sortDESCById(), HttpStatus.OK);
        }catch(ChargesNotFoundExceptions exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Warehouse not found");
        }
    }

    @PutMapping("/update/quantity/{id}")
    public ResponseEntity<Warehouse> updateWarehousesQuantity(@PathVariable("id") long id, int quantity){
        try{
            return new ResponseEntity(warehouseService.updateQuantity(id, quantity), HttpStatus.OK);
        }catch(ChargesNotFoundExceptions exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Warehouse not found");
        }
    }

    @PutMapping("/update/amount/{id}")
    public ResponseEntity<Warehouse> updateWarehousesAmount(@PathVariable("id") long id, int amount){
        try{
            return new ResponseEntity(warehouseService.updateAmount(id, amount), HttpStatus.OK);
        }catch(ChargesNotFoundExceptions exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Warehouse not found");
        }
    }

    @PutMapping("/update/name/{id}")
    public ResponseEntity<Warehouse> updateWarehousesName(@PathVariable("id") long id, String name){
        try{
            return new ResponseEntity(warehouseService.updateName(id, name), HttpStatus.OK);
        }catch(ChargesNotFoundExceptions exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Warehouse not found");
        }
    }



}
