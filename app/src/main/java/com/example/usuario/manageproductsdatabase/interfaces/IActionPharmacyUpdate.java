package com.example.usuario.manageproductsdatabase.interfaces;

import com.example.usuario.manageproductsdatabase.model.Pharmacy;

public interface IActionPharmacyUpdate extends IActionPharmacy{

    void onUpdatePharmacy(Pharmacy pharmacy);
}
