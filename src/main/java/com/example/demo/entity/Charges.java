package com.example.demo.entity;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "charges")
public class Charges {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "amount", nullable = false)
    private int amount;

    @Column(name = "charge_date", nullable = false)
    private LocalDateTime charge_date;

    @OneToOne(cascade = CascadeType.ALL, targetEntity = ExpenseItems.class)
    @JoinColumn(name = "expense_item_id")
    private ExpenseItems expense_items;

    public Charges(){}

    public Charges(int amount, LocalDateTime charge_date, ExpenseItems expense_items) {
        this.id = id;
        this.amount = amount;
        this.charge_date = charge_date;
        this.expense_items = expense_items;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public LocalDateTime getChargeDate() {
        return charge_date;
    }

    public void setChargeDate(LocalDateTime charge_date) {
        this.charge_date = charge_date;
    }
}
