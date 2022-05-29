package com.example.demo.repository;

import com.example.demo.entity.Sales;
import com.example.demo.entity.Warehouse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SalesRepository extends CrudRepository<Sales, Long> {

    @Query(value = "SELECT * FROM sales", nativeQuery = true)
    List<Sales> listSales();

    //sort ASC
    @Query(value = "SELECT * FROM sales ORDER BY id ASC", nativeQuery = true)
    List<Sales> sortASCById();

    //sort DESC
    @Query(value = "SELECT FROM sales ORDER BY id DESC ", nativeQuery = true)
    List<Sales> sortDESCById();

    //find
    @Query(value = "SELECT FROM sales i WHERE i.quantity=:quantity", nativeQuery = true)
    Optional<Sales> findByQuantity(int quantity);

    @Query(value = "SELECT FROM sales i WHERE i.amount=:amount", nativeQuery = true)
    Optional<Sales> findByAmount(int amount);

    @Query(value = "SELECT FROM sales i WHERE i.sales_date=:tm", nativeQuery = true)
    Optional<Sales> findBySalesDate(String tm);

    @Query(value = "SELECT * FROM sales i WHERE i.id=:id", nativeQuery = true)
    Optional<Sales> findById(Long id);

    //update
    @Query(value = "UPDATE sales SET quantity=:quantity WHERE id=:id", nativeQuery = true)
    List<Sales> updateQuantity(long id, int quantity);

    @Query(value = "UPDATE sales SET amount=:amount WHERE id=:id", nativeQuery = true)
    List<Sales> updateAmount(long id, int amount);

    @Query(value = "UPDATE sales SET sales_date =:tm WHERE id =:id", nativeQuery = true)
    List<Sales> updateSalesDate(long id, String tm);

    //hard requests
    @Query(value = "select warehouse.name, sales.quantity, sales.amount, sales.sales_date" +
            " from sales join warehouse on sales.warehouse_id = warehouse.id", nativeQuery = true)
    List<Object[]> listSalesProducts();

    @Query(value = "SELECT warehouse.name, sales.amount " +
            "FROM warehouse " +
            "JOIN sales " +
            "ON warehouse.id = sales.warehouse_id  AND sales.amount > 5000", nativeQuery = true)
    List<Object> maxAmountOfSalesProducts();

}
