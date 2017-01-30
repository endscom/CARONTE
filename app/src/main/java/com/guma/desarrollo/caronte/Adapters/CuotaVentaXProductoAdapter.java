package com.guma.desarrollo.caronte.Adapters;

/**
 * Created by luis.perez on 27/01/2017.
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
import com.guma.desarrollo.core.Cliente;
import com.guma.desarrollo.caronte.R;
import com.guma.desarrollo.core.CuotaVentaXProducto;

import java.util.ArrayList;
import java.util.List;

public class CuotaVentaXProductoAdapter extends ArrayAdapter<CuotaVentaXProducto> {
    private static final String TAG = "CuotaVentaXProductoAdapter";

    public CuotaVentaXProductoAdapter(Context context, List<CuotaVentaXProducto> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View converView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (null == converView) {
            converView = inflater.inflate(
                    R.layout.list_item_indicadores_clientes, parent, false
            );
        }
        ImageView avatar = (ImageView) converView.findViewById(R.id.iv_avatar);
        TextView name = (TextView) converView.findViewById(R.id.tv_name);
        TextView title = (TextView) converView.findViewById(R.id.tv_title);
        TextView company = (TextView) converView.findViewById(R.id.tv_company);
        TextView prodfact = (TextView) converView.findViewById(R.id.tv_prodfact);

        CuotaVentaXProducto cvp = getItem(position);

        Glide.with(getContext()).load(cvp.getImage()).into(avatar);
        name.setText(cvp.getCodProducto());
        title.setText(cvp.getCodVendedor());
        company.setText(cvp.getNombreProducto());
        prodfact.setText(cvp.getMeta());

        return converView;
    }

    private List<CuotaVentaXProducto> items;

    public List<CuotaVentaXProducto> getFilter(String query, List<CuotaVentaXProducto> clst) {

        List<CuotaVentaXProducto> newitems = new ArrayList<>();

        query = query.toLowerCase();
        if (query.isEmpty()) {
            newitems.addAll(clst);

        } else {
            for (CuotaVentaXProducto obj : clst) {
                if (obj.getCodProducto().toLowerCase().contains(query)) {
                    newitems.add(new CuotaVentaXProducto(obj.getCodVendedor(), obj.getCodProducto(), obj.getNombreProducto(), obj.getMeta(), R.id.iv_avatar));

                }
            }

        }
        return newitems;

    }
}