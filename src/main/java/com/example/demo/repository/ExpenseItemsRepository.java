package com.example.demo.repository;

import com.example.demo.entity.Charges;
import com.example.demo.entity.ExpenseItems;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
public interface ExpenseItemsRepository extends CrudRepository<ExpenseItems, Long> {

    @Query(value = "SELECT * FROM expense_items", nativeQuery = true)
    List<ExpenseItems> listExpenseItems();

    //delete

    //sort ASC
    @Query(value = "SELECT * FROM expense_items ORDER BY id ASC", nativeQuery = true)
    List<ExpenseItems> sortASCById();

    //sort DESC
    @Query(value = "SELECT * FROM expense_items ORDER BY id DESC ", nativeQuery = true)
    List<ExpenseItems> sortDESCById();

    //find
    @Query(value = "SELECT * FROM expense_items i WHERE i.id=:id", nativeQuery = true)
    Optional<ExpenseItems> findById(long id);

    @Query(value = "UPDATE expense_items SET name =:name WHERE id =:id", nativeQuery = true)
    List<ExpenseItems> updateName(long id, String name);


}
