package com.guma.desarrollo.caronte.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.guma.desarrollo.caronte.Activitys.DetalleTableroActivity;
import com.guma.desarrollo.caronte.Activitys.NumArticulosPorCliente;
import com.guma.desarrollo.caronte.Activitys.VentasPorArticuloActivity;
import com.guma.desarrollo.caronte.Activitys.VentasPorClienteActivity;
import com.guma.desarrollo.core.Indicadores;
import com.guma.desarrollo.caronte.R;

import java.util.List;


    public class IndicadoresAdapter extends RecyclerView.Adapter<IndicadoresAdapter.IndicadorViewHolder>  {
    private List<Indicadores> items;
        public class IndicadorViewHolder extends RecyclerView.ViewHolder{
        public ImageView imagen;
        public TextView nombre;
        public TextView visitas;

        public IndicadorViewHolder(View v) {
            super(v);
            imagen = (ImageView) v.findViewById(R.id.imagen);
            nombre = (TextView) v.findViewById(R.id.nombre);
            visitas = (TextView) v.findViewById(R.id.visitas);
        }
    }
    public IndicadoresAdapter(List<Indicadores> items) {
        this.items = items;
    }
    @Override
    public int getItemCount() {
        return items.size();
    }
    @Override
    public IndicadorViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_tablero, viewGroup, false);
        return new IndicadorViewHolder(v);
    }
    @Override
    public void onBindViewHolder(final IndicadorViewHolder viewHolder, final int i) {
        viewHolder.imagen.setImageResource(items.get(i).getImagen());
        viewHolder.nombre.setText(items.get(i).getNombre());
        viewHolder.visitas.setText(String.valueOf(items.get(i).getVisitas()));

        viewHolder.imagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //PROMEDIO POR FACTURA
                /*if ((items.get(i).getNombre()!="PROMEDIO POR ITEM") && (items.get(i).getNombre()!="PROMEDIO POR FACTURA") ){
                    Intent ints = new Intent(v.getContext(),DetalleTableroActivity.class);
                    ints.putExtra("TITULO",items.get(i).getNombre());
                    v.getContext().startActivity(ints);

                }
*/
                //NUMERO DE ARTICULOS POR CLIENTE
                if (items.get(i).getNombre()=="# ITEMS POR CLIENTE")
                {
                    Intent x = new Intent(v.getContext(), NumArticulosPorCliente.class);
                    //x.putExtra("TITULO",items.get(i).getNombre());
                    v.getContext().startActivity(x);
                }
                //VENTAS POR ARTICULO
                if (items.get(i).getNombre()=="VENTAS POR ARTICULO")
                {
                    Intent x = new Intent(v.getContext(), VentasPorArticuloActivity.class);
                    x.putExtra("TITULO",items.get(i).getNombre());
                    v.getContext().startActivity(x);
                }
                if (items.get(i).getNombre()=="VENTAS POR CLIENTE")
                {
                    Intent x = new Intent(v.getContext(), VentasPorClienteActivity.class);
                    x.putExtra("TITULO",items.get(i).getNombre());
                    v.getContext().startActivity(x);
                }
            }
        });

        }
    }
