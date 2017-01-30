package com.example.usuario.manageproductsdatabase.model;

import java.util.ArrayList;
import java.util.List;

public class Invoice {
    int id;
    int idPharmacy;
    String date;
    int status;
    List<InvoiceLine> lines;

    public Invoice(int id, int idPharmacy, String date, int status, ArrayList<InvoiceLine> lines) {
        this.id = id;
        this.idPharmacy = idPharmacy;
        this.date = date;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public int getIdPharmacy() {
        return idPharmacy;
    }

    public String getDate() {
        return date;
    }

    public int getStatus() {
        return status;
    }
}
