package com.example.demo.service;

import com.example.demo.entity.Charges;

import com.example.demo.exception.ChargesNotFoundExceptions;
import com.example.demo.repository.ChargesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ChargesServiceImpl implements ChargesService{
    @Autowired
    private ChargesRepository chargesRepository;

    @Override
    public List<Charges> listCharges() {
        return(List<Charges>) chargesRepository.findAll();
    }

    @Override
    public Charges findById(long id) {
        Optional<Charges> optionalCharges = chargesRepository.findById(id);
        if(optionalCharges.isPresent()){
            return optionalCharges.get();
        }else{
            throw new ChargesNotFoundExceptions("Charges not found");
        }
    }

    @Override
    public Charges addCharges(Charges charges) {
        return chargesRepository.save(charges);
    }

    @Override
    public List<Charges> sortASCById() {
        return chargesRepository.sortASCById();
    }

    @Override
    public List<Charges> sortDESCById() {
        return chargesRepository.sortDESCById();
    }

    @Override
    public List<Charges> updateAmount(long id, int amount) {
        Optional<Charges> optionalCharges = chargesRepository.findById(id);
        if(optionalCharges.isPresent()){
            return chargesRepository.updateAmount(id, amount);
        } else{
            throw new ChargesNotFoundExceptions("not found");
        }
    }

    @Override
    public List<Charges> updateChargesDate(long id, LocalDateTime tm) {
        Optional<Charges> optionalCharges = chargesRepository.findById(id);
        if(optionalCharges.isPresent()){
            return chargesRepository.updateChargesDate(id, tm);
        } else{
            throw new ChargesNotFoundExceptions("not found");
        }
    }

    @Override
    public void deleteCharges(long id){
        Optional<Charges> optionalCharges = chargesRepository.findById(id);
        if(optionalCharges.isPresent()){
            chargesRepository.deleteById(id);
        } else{
            throw new ChargesNotFoundExceptions("not found");
        }
    }

    @Override
    public List<Object> listChargesItems() {
        return chargesRepository.listChargesItems();
    }

    @Override
    public List<Object> listBigCharges() {
        return chargesRepository.listBigCharges();
    }
}
