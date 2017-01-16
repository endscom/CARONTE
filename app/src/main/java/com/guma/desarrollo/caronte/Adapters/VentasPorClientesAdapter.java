package com.guma.desarrollo.caronte.Adapters;

/**
 * Created by luis.perez on 13/01/2017.
 */

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.guma.desarrollo.core.VentaPorCliente;
import com.guma.desarrollo.caronte.R;

import java.util.ArrayList;
import java.util.List;

public class VentasPorClientesAdapter extends ArrayAdapter<VentaPorCliente> {
    private static final String TAG = "VentasPorClientesAdapter";

    public VentasPorClientesAdapter(Context context, List<VentaPorCliente> objects) {
        super(context, 0, objects);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (null==convertView)
        {
            convertView = inflater.inflate(R.layout.list_item_lead, parent, false);
        }
        ImageView avatar = (ImageView) convertView.findViewById(R.id.iv_avatar);
        TextView name = (TextView) convertView.findViewById(R.id.tv_name);
        TextView title = (TextView) convertView.findViewById(R.id.tv_title);
        TextView company = (TextView) convertView.findViewById(R.id.tv_company);

        VentaPorCliente ventaporcliente = getItem(position);

        Glide.with(getContext()).load(ventaporcliente.getImage()).into(avatar);
        name.setText(ventaporcliente.getNOMBRE());
        title.setText(ventaporcliente.getCODIGO());
        company.setText(ventaporcliente.getVTAxCLIENTE());

        return convertView;

    }
    private List<VentaPorCliente> items;
    public List<VentaPorCliente> getFilter(String query, List<VentaPorCliente> clst )
    {
        List<VentaPorCliente> newitems = new ArrayList<>();
        query = query.toLowerCase();
        if (query.isEmpty())
            { newitems.addAll(clst); }
        else
            {
                for (VentaPorCliente obj : clst)
                {
                    if (obj.getNOMBRE().toLowerCase().contains(query))
                    {
                        //newitems.add(new VentaPorCliente(obj.getNOMBRE(), obj.getCODIGO(), obj.getVTAxCLIENTE(), R.id.iv_avatar));
                        newitems.add(new VentaPorCliente(obj.getNOMBRE().toString(),obj.getCODIGO().toString(),obj.getVTAxCLIENTE().toString(),R.id.iv_avatar));
                    }
                }
            }
        return newitems;
    }
}
