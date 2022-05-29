package com.example.demo.controller;

import com.example.demo.entity.Charges;
import com.example.demo.entity.Warehouse;
import com.example.demo.exception.ChargesNotFoundExceptions;
import com.example.demo.service.ChargesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/charges")
public class ChargesController {
    private ChargesService chargesService;

    @Autowired
    public void setChargesService(ChargesService chargesService) { this.chargesService = chargesService; }

    @PostMapping(value = "/add", consumes = "application/json", produces = "application/json")
    public Charges addCharges(Charges newCharges){
        return chargesService.addCharges(newCharges);
    }

    @DeleteMapping(value = "/delete", consumes = "application/json", produces = "application/json")
    public void deleteCharges(@RequestParam("id") long id){
        try{
            chargesService.deleteCharges(id);
        }catch (ChargesNotFoundExceptions exceptions){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Charges not found");
        }
    }

    @GetMapping("/all")
    public ModelAndView getAllCharges(){
        ModelAndView model = new ModelAndView();
        List<Charges> list = chargesService.listCharges();
        model.addObject("charges", list).setViewName("charges");
        return model;
    }

    @GetMapping("/id")
    public ModelAndView getCharges(@RequestParam("id") long id){
        ModelAndView model = new ModelAndView();
        model.addObject("warehouse", chargesService.findById(id));
        model.setViewName("charges");
        return model;
    }

    @GetMapping("/sort/asc")
    public ResponseEntity<Charges> sortChargesASC(){
        try{
            return new ResponseEntity(chargesService.sortASCById(), HttpStatus.OK);
        }catch(ChargesNotFoundExceptions exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Charges not found");
        }
    }

    @GetMapping("/sort/desc")
    public ResponseEntity<Charges> sortChargesDESC(){
        try{
            return new ResponseEntity(chargesService.sortDESCById(), HttpStatus.OK);
        }catch(ChargesNotFoundExceptions exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Charges not found");
        }
    }

    @PostMapping("/update/amount")
    public void updateChargesAmount(@RequestParam("id") long id, @RequestParam("amount") int amount){
        try{
            chargesService.updateAmount(id, amount);
        }catch(ChargesNotFoundExceptions exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Charges not found");
        }
    }

    @PostMapping("/update/date")
    public void updateChargesDate(@RequestParam("id") long id, @RequestParam("charge_date") String tm){
        try{
            chargesService.updateChargesDate(id, tm);
        }catch(ChargesNotFoundExceptions exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Charges not found");
        }
    }

    @GetMapping("/list_big_charges")
    public List<Object> getBigCharges(){
        return chargesService.listBigCharges();
    }

    @GetMapping("/list_charges_items")
    public List<Object> getListCharges(){
        return chargesService.listChargesItems();
    }
}
