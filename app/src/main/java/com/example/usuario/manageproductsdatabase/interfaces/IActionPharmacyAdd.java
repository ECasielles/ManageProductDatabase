package com.example.usuario.manageproductsdatabase.interfaces;


import com.example.usuario.manageproductsdatabase.model.Pharmacy;

public interface IActionPharmacyAdd extends IActionPharmacy{

    void onAddPharmacy(Pharmacy pharmacy);
}
