package com.example.usuario.manageproductsdatabase.fragment;

import android.app.ListFragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.usuario.manageproductsdatabase.R;
import com.example.usuario.manageproductsdatabase.adapter.PharmacyAdapter;
import com.example.usuario.manageproductsdatabase.database.ManageProductContract;
import com.example.usuario.manageproductsdatabase.model.Pharmacy;


public class ListPharmacyFragment extends ListFragment {

    private PharmacyAdapter adapter;
    private ProgressDialog dialog;
    private ListView list;
    private FloatingActionButton fabAddPharmacy;
    public static final String RECOVERY_PHARMACY = "pharmacy";
    private CoordinatorLayout parent;

    public ListPharmacyFragment() { }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list_pharmacy, null);
        list = (ListView)rootView.findViewById(R.id.listPharmacy);
        parent = (CoordinatorLayout)rootView.findViewById(R.id.listPharmacyFragment);
        fabAddPharmacy = (FloatingActionButton)rootView.findViewById(R.id.fabAddPharmacy);
        adapter = new PharmacyAdapter(getActivity(), R.layout.item_list_pharmacy, null, ManageProductContract.)

        registerForContextMenu(list);

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {


                return false;
            }
        });

        fabAddPharmacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final ManagePharmacy_Fragment fragment = ManagePharmacy_Fragment.newInstance(new Pharmacy());
                fragment.setOnActionFinishedListener(new ManagePharmacy_Fragment.IReturnAction() {
                    @Override
                    public void onActionFinished(Pharmacy pharmacy) {

                        //Se le pasa al presentador la farmacia para que la añada a la lista
                        fabAddPharmacy.setVisibility(View.VISIBLE);
                        getActivity().getSupportFragmentManager().beginTransaction().remove(fragment).commit();

                    }
                });

                fabAddPharmacy.setVisibility(View.GONE);
                getActivity().getSupportFragmentManager().beginTransaction().replace(parent.getId(),
                        fragment).addToBackStack(null).commit();
            }
        });

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getListView().setAdapter(adapter);
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                view.setSelected(true);
                Bundle bundle = new Bundle(position);

            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        super.onCreateContextMenu(menu, v, menuInfo);

    }


}
