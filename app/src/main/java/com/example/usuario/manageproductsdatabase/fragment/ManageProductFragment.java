package com.example.usuario.manageproductsdatabase.fragment;

import android.content.Context;
import android.database.Cursor;
import android.icu.util.ULocale;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.usuario.manageproductsdatabase.R;
import com.example.usuario.manageproductsdatabase.adapter.AdapterCategory;
import com.example.usuario.manageproductsdatabase.interfaces.CategoryPresenter;
import com.example.usuario.manageproductsdatabase.interfaces.IProduct;
import com.example.usuario.manageproductsdatabase.interfaces.ManagePresenter;
import com.example.usuario.manageproductsdatabase.model.Product;

public class ManageProductFragment extends Fragment implements ManagePresenter.View, CategoryPresenter.View{

    private Product product;
    private ImageView imageView;
    private TextInputLayout tilName;
    private TextInputLayout tilBrand;
    private TextInputLayout tilDescription;
    private TextInputLayout tilDosage;
    private TextInputLayout tilPrice;
    private TextInputLayout tilStock;
    private Button btnAction;
    private boolean addAction;
    private ManageProductListener mCallBack;
    private AdapterCategory adapterCategory;

    public interface ManageProductListener {

        void showListProduct();
    }
    //Singleton que evita la duplicidad de gestores
    public static ManageProductFragment getInstance(Bundle args) {
        ManageProductFragment fragment = new ManageProductFragment();
        if(args != null)
            fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallBack = (ManageProductListener) context;
        } catch (ClassCastException ex) {
            throw new ClassCastException(ex.getMessage() + " activity must implement ManageProductListener");
        }
    }


    @Override
    public void onDetach() {
        mCallBack = null;
        adapterCategory = null;
        super.onDetach();
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Comprueba que no sea nulo antes de añadir el producto
        if (getArguments() != null)
            product = getArguments().getParcelable(IProduct.PRODUCT_KEY);
        else
            //Nuevo producto
            product = new Product("Nombre", "Descripción", "Dosis", "Marca", 0.0d, 0, R.drawable.pill);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.activity_manage_product, container, false);

        imageView = (ImageView) rootView.findViewById(R.id.ivwItemProductImage);
        tilName = (TextInputLayout) rootView.findViewById(R.id.tilManageName);
        tilDescription = (TextInputLayout) rootView.findViewById(R.id.tilManageDescription);
        tilDosage = (TextInputLayout) rootView.findViewById(R.id.tilManageDosage);
        tilBrand = (TextInputLayout) rootView.findViewById(R.id.tilManageBrand);
        tilPrice = (TextInputLayout) rootView.findViewById(R.id.tilManagePrice);
        tilStock = (TextInputLayout) rootView.findViewById(R.id.tilManageStock);
        btnAction = (Button) rootView.findViewById(R.id.btnManageProductOk);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveProduct();
            }
        });

        if(product != null) {
            imageView.setImageResource(product.getmImage());
            tilName.getEditText().setText(product.getmName());
            tilDescription.getEditText().setText(product.getmDescription());
            tilDosage.getEditText().setText(product.getmDosage());
            tilBrand.getEditText().setText(product.getmBrand());
            tilPrice.getEditText().setText(product.getFormatedPrice());
            tilStock.getEditText().setText(product.getFotmattedUnitsInStock());
        }
    }

    @Override
    public void setMessageError(String messageError, int viewId) {
        switch (viewId) {
            case R.id.tilManageName:
                tilName.setError(messageError);
                break;
            case R.id.tilManageDescription:
                tilDescription.setError(messageError);
                break;
            case R.id.tilManageDosage:
                tilDosage.setError(messageError);
                break;
            case R.id.tilManageBrand:
                tilBrand.setError(messageError);
                break;
            case R.id.tilManagePrice:
                tilPrice.setError(messageError);
                break;
            case R.id.tilManageStock:
                tilStock.setError(messageError);
                break;
        }

    }

    private void saveProduct() {
        Product productNew;
        //int id = spCategory.getAdapter();   //<- Spinner
        Cursor cursor = ((SimpleCursorAdapter) spCategory.getAdapter()).getCursor();
        cursor.moveToPosition(spCategory.getSelectedItemPosition());

        if(addAction) {
            productNew = new Product(tilName.getEditText().getText().toString(), tilDescription.getEditText().getText().toString(), tilBrand.getEditText().getText().toString(), tilDosage.getEditText().getText().toString(), Double.parseDouble(tilPrice.getEditText().getText().toString()), Integer.parseInt(tilStock.getEditText().getText().toString()));
            managePresenter.addProduct(productNew);
        } else {
            //productNew = new Product(product.getmId()...);
            managePresenter.addProduct(productNew);
        }

        mCallBack.onListProductListener();
    }

    @Override
    public void onStart() {
        super.onStart();
        //Tomamos las categorías y le pasamos el adapter
        CategoryPresenter.setCursorCategory();
    }

    @Override
    public void setCursorCategory(Cursor cursor) {
        //changeCursor cierra el cursor tras el acceso
        adapterCategory.changeCursor(cursor);
    }
}
