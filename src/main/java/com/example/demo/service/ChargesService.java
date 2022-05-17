package com.example.demo.service;

import com.example.demo.entity.Charges;
import com.example.demo.entity.Sales;
import org.springframework.boot.autoconfigure.transaction.TransactionAutoConfiguration;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public interface ChargesService {
    List<Charges> listCharges();//y
    Charges addCharges(Charges charges);//y

    void deleteCharges(long id);//y
    List<Charges> sortASCById();//y
    List<Charges> sortDESCById();//y

    Charges findById(long id);//y
    List<Charges> updateAmount(long id, int amount);//y
    List<Charges> updateChargesDate(long id, LocalDateTime tm);//y

    List<Object> listChargesItems();//y
    List<Object> listBigCharges();//y
}
