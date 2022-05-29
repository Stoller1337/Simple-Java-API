package com.example.demo.repository;

import com.example.demo.entity.Charges;
import com.example.demo.entity.ExpenseItems;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ChargesRepository extends CrudRepository<Charges, Long> {


    @Query(value = "SELECT * FROM charges", nativeQuery = true)
    List<Charges> listCharges();

    //delete

    //sort ASC
    @Query(value = "SELECT * FROM charges ORDER BY id ASC", nativeQuery = true)
    List<Charges> sortASCById();

    //sort DESC
    @Query(value = "SELECT FROM charges ORDER BY id DESC ", nativeQuery = true)
    List<Charges> sortDESCById();

    //find
    @Query(value = "SELECT FROM charges i WHERE i.id=:id", nativeQuery = true)
    Optional<Charges> findById(long id);

    @Query(value = "UPDATE charges SET amount =: amount WHERE id =: id", nativeQuery = true)
    List<Charges> updateAmount(long id, int amount);

    @Query(value = "UPDATE charges SET charge_date =: tm WHERE id =: id", nativeQuery = true)
    List<Charges> updateChargesDate(long id, String tm);

    //hard request
    @Query(value = "SELECT expense_items.name, charges.amount, charges.charge_date " +
                    "FROM expense_items" +
                   " JOIN charges ON expense_items.id = charges.expense_item_id;", nativeQuery = true)
    List<Object> listChargesItems();

    @Query(value = "SELECT expense_items.name, charges.amount " +
            "FROM expense_items" +
            " JOIN charges ON expense_items.id = charges.expense_item_id WHERE charges.amount > 5000;", nativeQuery = true)
    List<Object> listBigCharges();
}
