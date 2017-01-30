package com.example.usuario.manageproductsdatabase.model;


public class InvoiceStatus {
    int id;
    String name;

    public InvoiceStatus(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
