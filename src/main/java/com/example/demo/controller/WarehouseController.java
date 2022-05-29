package com.example.demo.controller;

import com.example.demo.entity.Warehouse;
import com.example.demo.exception.ChargesNotFoundExceptions;
import com.example.demo.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("api/warehouse")
public class WarehouseController {
    private WarehouseService warehouseService;

    @Autowired
    public void setWarehouseService(WarehouseService warehouseService) { this.warehouseService = warehouseService; }

    @PostMapping(value = "/add", consumes = "application/json", produces = "application/json")
    public Warehouse addWarehouse(Warehouse newWarehouse){
        return warehouseService.addWarehouse(newWarehouse);
    }

    @DeleteMapping(value = "/delete", consumes = "application/json", produces = "application/json")
    public void deleteWarehouses(@RequestParam("id") long id){
        try{
            warehouseService.deleteWarehouse(id);
        }catch (ChargesNotFoundExceptions exceptions){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Warehouse not found");
        }
    }

    @GetMapping("/all")
    public ModelAndView getAllWarehouses(){
        ModelAndView model = new ModelAndView();
        List<Warehouse> list = warehouseService.listWarehouse();
        model.addObject("warehouse", list).setViewName("WAREHOUSE");
        return model;
    }

    @GetMapping("/id")
    public ModelAndView getWarehouses(@RequestParam("id") long id){
        ModelAndView model = new ModelAndView();
        model.addObject("warehouse", warehouseService.findById(id));
        model.setViewName("warehouse");
        return model;
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

    @PostMapping("/update/quantity")
    public void updateWarehousesQuantity(@RequestParam("id") long id, @RequestParam("quantity") int quantity){
        warehouseService.updateQuantity(id, quantity);
    }

    @PostMapping("/update/amount")
    public void updateWarehousesAmount(@RequestParam("id") long id, @RequestParam("amount") int amount){
        warehouseService.updateAmount(id, amount);
    }

    @PostMapping("/update/name")
    public void updateWarehousesName(@RequestParam("id") long id, @RequestParam String name){
        warehouseService.updateName(id, name);
    }

}
