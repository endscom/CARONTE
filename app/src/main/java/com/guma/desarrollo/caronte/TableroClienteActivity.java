package com.guma.desarrollo.caronte;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class TableroClienteActivity extends AppCompatActivity {
    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablero_cliente);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("CLIENTE: " + "CL0000222");

        List items = new ArrayList();

        items.add(new Indicadores(R.drawable.indicador, "VENTAS AL VALOR", 0));
        items.add(new Indicadores(R.drawable.indicador, "ITEM FACTURADO", 0));
        items.add(new Indicadores(R.drawable.indicador, "PROMEDIO POR FACTURA", 0));
        items.add(new Indicadores(R.drawable.indicador, "PROMEDIO POR ITEM FACTURADO", 0));

        recycler = (RecyclerView) findViewById(R.id.rv_detalle_cliente);
        recycler.setHasFixedSize(true);
        lManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(lManager);
        adapter = new IndicadoresAdapter(items);
        recycler.setAdapter(adapter);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        int id = item.getItemId();

        if (id == R.id.action_recuperacion_efectivo) {

        } else if (id == R.id.action_producto_facturado) {

        } else if (id == R.id.action_cuota_venta) {

        }
        return super.onOptionsItemSelected(item);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detalle_tablero, menu);
        return true;
    }
}
