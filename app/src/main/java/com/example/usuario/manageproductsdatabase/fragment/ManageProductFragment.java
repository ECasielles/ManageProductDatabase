package com.example.usuario.manageproductsdatabase.fragment;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.usuario.manageproductsdatabase.R;
import com.example.usuario.manageproductsdatabase.interfaces.IProduct;
import com.example.usuario.manageproductsdatabase.model.Product;

public class ManageProductFragment extends Fragment implements IProduct.View{

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
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        product = getArguments().getParcelable(IProduct.PRODUCT_KEY);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.activity_manage_product, container, false);

        imageView = (ImageView) rootView.findViewById(R.id.ivwItemProductImage);
        tilName = (TextInputLayout) rootView.findViewById(R.id.tilManageName);
        tilDescription = (TextInputLayout) rootView.findViewById(R.id.tilManageDetails);
        tilDosage = (TextInputLayout) rootView.findViewById(R.id.tilManageDose);
        tilBrand = (TextInputLayout) rootView.findViewById(R.id.tilManageBrand);
        tilPrice = (TextInputLayout) rootView.findViewById(R.id.tilManagePrice);
        tilStock = (TextInputLayout) rootView.findViewById(R.id.tilManageStock);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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

    }


    //TODO
    private void saveProduct() {
        Product productNew;
        String name = tilName.getEditText().getText().toString();

        if(addAction)
            productNew = new Product(tilName.getEditText().getText().toString(), tilDescription);
        else


            finish();
    }
}
