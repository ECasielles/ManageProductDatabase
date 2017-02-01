package com.example.usuario.manageproductsdatabase.adapter;


import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.usuario.manageproductsdatabase.R;
import com.example.usuario.manageproductsdatabase.model.Pharmacy;

//Pasamos de SimpleCursorAdapter a CursorAdapter porque el otro es para ContentProvider
public class PharmacyAdapter extends CursorAdapter {


    //La diferencia entre clase interna o estática es el encapsulamiento
    public class PharmacyHolder {
        TextView txvCif;
        TextView txvAddress;
        TextView txvPhone;
        TextView txvEmail;
    }

    public PharmacyAdapter(Context context, Cursor c, int flags) {
        super(context, null, flags);
    }

    //Se llama tantas veces como tenga que crear elementos y a partir de ahí, bind
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View rootview = layoutInflater.inflate(R.layout.item_list_pharmacy, parent, false);
        PharmacyHolder viewHolder = new PharmacyHolder();
        viewHolder.txvCif = (TextView) rootview.findViewById(R.id.txvCifPhamacy);
        viewHolder.txvAddress= (TextView) rootview.findViewById(R.id.txvAddressPhamacy);
        viewHolder.txvPhone = (TextView) rootview.findViewById(R.id.txvPhonePhamacy);
        viewHolder.txvEmail = (TextView) rootview.findViewById(R.id.txvEmailPhamacy);
        rootview.setTag(viewHolder);
        return rootview;
    }

    //Enchufa los valores
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        PharmacyHolder viewHolder = (PharmacyHolder) view.getTag();
        viewHolder.txvCif.setText(cursor.getString(1));
        viewHolder.txvAddress.setText(cursor.getString(2));
        viewHolder.txvPhone.setText(cursor.getString(3));
        viewHolder.txvEmail.setText(cursor.getString(4));
    }

    //Podemos coger objetos enteros con getItem
    @Override
    public Object getItem(int position) {
        getCursor().moveToPosition(position);
        Pharmacy pharmacy = new Pharmacy();
        pharmacy.setId(getCursor().getInt(0));
        pharmacy.setCif(getCursor().getString(1));
        pharmacy.setAddress(getCursor().getString(2));
        pharmacy.setPhone(getCursor().getString(3));
        pharmacy.setEmail(getCursor().getString(4));

        return pharmacy;
    }
}
