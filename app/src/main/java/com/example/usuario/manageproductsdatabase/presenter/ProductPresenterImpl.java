package com.example.usuario.manageproductsdatabase.presenter;

import android.content.Context;

import com.example.usuario.manageproductsdatabase.database.DatabaseHelper;
import com.example.usuario.manageproductsdatabase.database.DatabaseManager;
import com.example.usuario.manageproductsdatabase.dialog.ConfirmDialog;
import com.example.usuario.manageproductsdatabase.interfaces.ProductPresenter;
import com.example.usuario.manageproductsdatabase.model.Product;
import com.example.usuario.manageproductsdatabase.repository.ProductRepository;
import com.example.usuario.manageproductsdatabase.resources.ManageProductApplication;

import java.util.ArrayList;
import java.util.List;

public class ProductPresenterImpl implements ConfirmDialog.OnDeleteProductListener, ProductPresenter {

    private ProductPresenter.View view;

    public ProductPresenterImpl(ProductPresenter.View view) {
        //this.view = checkNotNull(view);
        this.view = view;
    }

    public List<Product> getAllProducts() {
        //Construiremos un ArrayList desde la BD
        ArrayList<Product> products = new ArrayList<Product>();
        return null;
    }

    //Este método es importantísimo a la hora de paginar
    @Override
    public void loadProducts() {
        //if(repository.getProducts().isEmpty())
            //view.showEmptyText(true);
        //else
            view.showProducts(DatabaseManager.getInstance(ManageProductApplication.getContext()).getAllProducts());
    }
    public void onUpdateProduct(Product product) {
        DatabaseManager.getInstance().updateProduct(product);
    }

    //onAddProductButtonClicked
    @Override
    public Product getProduct(String id) {
        //return repository.getProducts(id);
        return null;
    }
    @Override
    public void deleteProduct(Product product) {

        repository.deleteProduct(product);

        //DEPENDE DE LA IMPLEMENTACIÓN repository vs adapter
        //loadProducts(); ó view.deleteProduct();
        //El repositorio hace una segunda comprobación ó
        //El adaptador se borra, pero entonces implementamos
        //un getAdapter.

        //view.getAdapter().deleteProduct();
        //if (view.getAdapter().isEmpty())
            //view.showEmptyText(true);

        view.showMessageDelete(product);
    }

    @Override
    public void onDestroy() {
        this.view = null;
    }

    @Override
    public void addProduct(Product product) {
        DatabaseManager.getInstance().addProduct(product);
        loadProducts();
    }

    @Override
    public void onAddProduct(){

    }

}
