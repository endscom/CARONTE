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
import com.guma.desarrollo.core.VentaPorArticulo;
import com.guma.desarrollo.core.VentaPorCliente;
import com.guma.desarrollo.caronte.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luis.perez on 16/01/2017.
 */

public class VentasPorArticuloAdapter extends ArrayAdapter<VentaPorArticulo> {
    private static final String TAG = "VentasPorArticulosAdapter";

    public VentasPorArticuloAdapter(Context context, List<VentaPorArticulo> objects){
        super(context, 0, objects);
    }
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

        VentaPorArticulo ventaPorArticulo = getItem(position);

        Glide.with(getContext()).load(ventaPorArticulo.getImage()).into(avatar);
        name.setText(ventaPorArticulo.getmDESCRIPCION());
        title.setText(ventaPorArticulo.getmARTICULO());
        company.setText(ventaPorArticulo.getmVTAxARTICULO());

        return convertView;

    }
    private List<VentaPorArticulo> items;
    public List<VentaPorArticulo> getFilter(String query, List<VentaPorArticulo> clst )
    {
        List<VentaPorArticulo> newitems = new ArrayList<>();
        query = query.toLowerCase();
        if (query.isEmpty())
        { newitems.addAll(clst); }
        else
        {
            for (VentaPorArticulo obj : clst)
            {
                if (obj.getmDESCRIPCION().toLowerCase().contains(query))
                {
                    newitems.add(new VentaPorArticulo(obj.getmDESCRIPCION().toString(),obj.getmARTICULO().toString(),obj.getmVTAxARTICULO().toString(),R.id.iv_avatar));
                }
            }
        }
        return newitems;
    }

}
