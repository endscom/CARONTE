package com.guma.desarrollo.caronte.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.guma.desarrollo.core.Cliente;
import com.guma.desarrollo.caronte.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maryan.espinoza on 27/12/2016.
 */

public class ClientesAdapter extends ArrayAdapter<Cliente> {
    private static final String TAG = "ClienteAdapter";

    public ClientesAdapter(Context context, List<Cliente> objects) {
        super(context, 0, objects);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (null == convertView) {
            convertView = inflater.inflate(
                    //R.layout.list_item_lead,
                    R.layout.list_item_indicadores_clientes,
                    parent,
                    false);
        }

        ImageView avatar = (ImageView) convertView.findViewById(R.id.iv_avatar);
        TextView name = (TextView) convertView.findViewById(R.id.tv_name);
        TextView title = (TextView) convertView.findViewById(R.id.tv_title);
        TextView company = (TextView) convertView.findViewById(R.id.tv_company);
        TextView prodfact = (TextView) convertView.findViewById(R.id.tv_prodfact);

        Cliente cliente = getItem(position);

        Glide.with(getContext()).load(cliente.getImage()).into(avatar);
        name.setText(cliente.getName());
        title.setText(cliente.getTitle());
        company.setText(cliente.getCompany());
        prodfact.setText(cliente.getProdFact());

        return convertView;
    }
    private List<Cliente> items;
    public List<Cliente> getFilter(String query,List<Cliente> clst) {

        List<Cliente> newitems = new ArrayList<>();

        query = query.toLowerCase();
        if (query.isEmpty()){ newitems.addAll(clst);

        }else{
            for (Cliente obj : clst){
                if (obj.getName().toLowerCase().contains(query))
                {
                    newitems.add(new Cliente(obj.getName(),obj.getTitle(),obj.getCompany(), obj.getProdFact(), R.id.iv_avatar));
                }
            }

        }
        return newitems;
    }
}