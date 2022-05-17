package com.example.demo.controller;

import com.example.demo.entity.Charges;
import com.example.demo.exception.ChargesNotFoundExceptions;
import com.example.demo.service.ChargesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/charges")
public class ChargesController {
    private ChargesService chargesService;

    @Autowired
    public void setChargesService(ChargesService chargesService) { this.chargesService = chargesService; }

    @PostMapping(value = "/add", consumes = "application/json", produces = "application/json")
    public Charges addCharges(@RequestBody Charges newCharges){
        return chargesService.addCharges(newCharges);
    }

    @DeleteMapping(value = "/delete/{id}", consumes = "charges/json", produces = "charges/json")
    public void deleteCharges(@PathVariable("id") long id){
        try{
            chargesService.deleteCharges(id);
        }catch (ChargesNotFoundExceptions exceptions){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Charges not found");
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Charges>> getAllCharges(){
        List<Charges> list = chargesService.listCharges();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Charges> getCharges(@PathVariable("id") long id){
        try{
            return new ResponseEntity<>(chargesService.findById(id), HttpStatus.OK);
        }catch(ChargesNotFoundExceptions exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Charges not found");
        }
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

    @PostMapping("/update/amount/{id}")
    public ResponseEntity<Charges> updateChargesAmount(@PathVariable("id") long id, int amount){
        try{
            return new ResponseEntity(chargesService.updateAmount(id, amount), HttpStatus.OK);
        }catch(ChargesNotFoundExceptions exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Charges not found");
        }
    }

    @PostMapping("/update/quantity/{id}")
    public ResponseEntity<Charges> updateChargesDate(@PathVariable("id") long id, LocalDateTime tm){
        try{
            return new ResponseEntity(chargesService.updateChargesDate(id, tm), HttpStatus.OK);
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
