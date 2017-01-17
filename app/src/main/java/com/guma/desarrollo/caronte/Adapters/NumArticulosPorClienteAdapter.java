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
import com.guma.desarrollo.core.NumArticuloPorCliente;
import com.guma.desarrollo.core.VentaPorArticulo;
import com.guma.desarrollo.core.VentaPorCliente;
import com.guma.desarrollo.caronte.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luis.perez on 17/01/2017.
 */

public class NumArticulosPorClienteAdapter extends ArrayAdapter<NumArticuloPorCliente> {
    private static final String TAG = "NumArticulosPorCliente";

    public NumArticulosPorClienteAdapter(Context context, List<NumArticuloPorCliente> objects){
        super(context, 0, objects);
    }

    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (null==convertView)
        {
            convertView = inflater.inflate(R.layout.list_item_lead, parent, false);
        }
        ImageView avatar = (ImageView) convertView.findViewById(R.id.iv_avatar);
        TextView name = (TextView) convertView.findViewById(R.id.tv_name);
        TextView title = (TextView) convertView.findViewById(R.id.tv_title);
        TextView company = (TextView) convertView.findViewById(R.id.tv_company);

        NumArticuloPorCliente numArticuloPorCliente = getItem(position);

        Glide.with(getContext()).load(numArticuloPorCliente.getImage()).into(avatar);
        name.setText(numArticuloPorCliente.getmNOMBRE());
        title.setText(numArticuloPorCliente.getmCODCLIETE());
        company.setText(numArticuloPorCliente.getmARTICULOS());

        return convertView;
    }

    private List<NumArticuloPorCliente> items;
    public List<NumArticuloPorCliente> getFilter(String query, List<NumArticuloPorCliente> clst){
        List<NumArticuloPorCliente> newitems = new ArrayList<>();
        query = query.toLowerCase();
        if (query.isEmpty())
            { newitems.addAll(clst); }
        else
        {
            for (NumArticuloPorCliente obj : clst)
                if ( obj.getmNOMBRE().toLowerCase().contains(query)) {
                    newitems.add(new NumArticuloPorCliente(obj.getmCODCLIETE().toString(), obj.getmNOMBRE().toString(), obj.getmARTICULOS().toString(), R.id.iv_avatar));
                }
        }
        return newitems;
    }
}
