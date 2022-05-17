package com.example.demo.repository;

import com.example.demo.entity.Sales;
import com.example.demo.entity.Warehouse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
public interface WarehouseRepository extends CrudRepository<Warehouse, Long> {

    @Query(value = "SELECT * FROM warehouse", nativeQuery = true)
    List<Warehouse> listWarehouse();

    @Query(value = "SELECT * FROM warehouse i WHERE i.id=:id", nativeQuery = true)
    Optional<Warehouse> findById(Long id);

    //delete
    @Query(value = "DELETE FROM warehouse WHERE id=:id", nativeQuery = true)
    List<Warehouse> deleteByid(long id);

    //sort ASC
    @Query(value = "SELECT * FROM warehouse ORDER BY id ASC", nativeQuery = true)
    List<Warehouse> sortASCById();

//    @Query(value = "SELECT FROM warehouse ORDER BY quantity ASC", nativeQuery = true)
//    List<Warehouse> sortASCByQuantity();
//
//    //sort DESC
//    @Query(value = "SELECT FROM warehouse ORDER BY id DESC ", nativeQuery = true)
//    List<Warehouse> sortDESCById();

    //sort DESC
    @Query(value = "SELECT FROM warehouse ORDER BY id DESC ", nativeQuery = true)
    List<Warehouse> sortDESCById();

    //find
    @Query(value = "SELECT FROM warehouse i WHERE i.id=:id", nativeQuery = true)
    Optional<Warehouse> findById(long id);

//    @Query(value = "SELECT FROM warehouse i WHERE i.quantity=:quantity", nativeQuery = true)
//    Optional<Warehouse> findByQuantity(int quantity);
//
//    @Query(value = "SELECT FROM warehouse i WHERE i.amount=:amount", nativeQuery = true)
//    Optional<Warehouse> findByAmount(int amount);
//
//    @Query(value = "DELETE FROM warehouse i WHERE i.name=:name", nativeQuery = true)
//    Optional<Warehouse> findByName(String name);


    //update
    @Query(value = "UPDATE warehouse SET quantity =: quantity WHERE id =: id", nativeQuery = true)
    List<Warehouse> updateQuantity(long id, int quantity);

    @Query(value = "UPDATE warehouse SET amount =: amount WHERE id =: id", nativeQuery = true)
    List<Warehouse> updateAmount(long id, int amount);

    @Query(value = "UPDATE warehouse SET name =: name WHERE id =: id", nativeQuery = true)
    List<Warehouse> updateName(long id, String name);


}
