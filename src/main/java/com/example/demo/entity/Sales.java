package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;
import javax.persistence.*;

@Entity
@Table(name = "sales")
public class Sales {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "amount", nullable = false)
    private int amount;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @JsonProperty("sales_date")
    @Column(name = "sales_date")
    private String salesDate;

    @JsonProperty("warehouse_id")
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, targetEntity = Warehouse.class)
    @JoinColumn(name = "warehouse_id", unique = true, foreignKey = @ForeignKey(name = "fk_warehouse_id"))
    private Warehouse warehouseId;

    public Sales(){}

    public Sales(int amount, int quantity, LocalDateTime sales_date, Warehouse warehouse_id) {
        this.amount = amount;
        this.quantity = quantity;
        this.salesDate = sales_date.toString();
        this.warehouseId = warehouse_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getSalesDate() {
        return salesDate;
    }

    public void setSalesDate(String sales_date) {
        this.salesDate = sales_date;
    }

    public Warehouse getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Warehouse warehouse_id) {
        this.warehouseId = warehouse_id;
    }

    @Override
    public String toString() {
        return "Sales{" +
                "id= " + id +
                ", amount= " + amount +
                ", quantity= " + quantity +
                ", sales_date= " + salesDate +
                ", warehouse_id= " + warehouseId +
                '}';
    }
}
