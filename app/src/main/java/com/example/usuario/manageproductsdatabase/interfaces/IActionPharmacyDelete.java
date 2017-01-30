package com.example.usuario.manageproductsdatabase.interfaces;


import com.example.usuario.manageproductsdatabase.model.Pharmacy;

public interface IActionPharmacyDelete extends IActionPharmacy{

    void onDeletePharmacy(Pharmacy pharmacy);
}