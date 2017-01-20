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
import com.guma.desarrollo.core.Indicadores3;

import java.util.List;

/**
 * Created by luis.perez on 20/01/2017.
 */

public class Indicadores3Adapter extends RecyclerView.Adapter<Indicadores3Adapter.Indicador3ViewHolder>
{
    private List<Indicadores3> items;

    public Indicadores3Adapter(List<Indicadores3> items)
    {
        this.items = items;
    }
    @Override
    public Indicador3ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_tablero_3, parent, false);
        return new Indicador3ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(Indicador3ViewHolder viewHolder, int i) {
        viewHolder.Imagen.setImageResource(items.get(i).getImagen());
        viewHolder.Nombre.setText(items.get(i).getNombre());
        viewHolder.Promedio.setText(items.get(i).getPromedio());
        viewHolder.Meta.setText(String.valueOf(items.get(i).getMeta()));
        viewHolder.Actual.setText(String.valueOf(items.get(i).getActual()));
        viewHolder.Pendiente.setText(String.valueOf(items.get(i).getPendiente()));

        viewHolder.Imagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //PROMEDIO POR FACTURA
                /*if ((items.get(i).getNombre()!="PROMEDIO POR ITEM") && (items.get(i).getNombre()!="PROMEDIO POR FACTURA") ){
                    Intent ints = new Intent(v.getContext(),DetalleTableroActivity.class);
                    ints.putExtra("TITULO",items.get(i).getNombre());
                    v.getContext().startActivity(ints);

                }
*/
                /*
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
                */
            }
        });

    }

    @Override
    public int getItemCount()
    {
        return this.items.size();
    }
    public class Indicador3ViewHolder extends  RecyclerView.ViewHolder
    {
        public ImageView Imagen;
        private TextView Nombre;
        private TextView Promedio;
        private TextView Meta;
        private TextView Actual;
        private TextView Pendiente;
        public Indicador3ViewHolder(View v){
            super(v);
            Imagen = (ImageView) v.findViewById(R.id.Imagen3);
            Nombre = (TextView) v.findViewById(R.id.Nombre3);
            Promedio = (TextView) v.findViewById(R.id.Promedio);
            Meta = (TextView) v.findViewById(R.id.Meta);
            Actual = (TextView) v.findViewById(R.id.Actual);
            Pendiente = (TextView) v.findViewById(R.id.Pendiente);
        }

    }

}
