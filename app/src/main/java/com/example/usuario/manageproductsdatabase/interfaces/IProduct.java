package com.example.usuario.manageproductsdatabase.interfaces;

public interface IProduct {

    String PRODUCT_KEY = "product";
    interface View {
        void setMessageError(String messageError, int viewId);
    }
    interface Presenter {
        void validateProduct(String name, String description, String dosage, String brand,
                                String price, String stock);
    }

}
