package com.guma.desarrollo.caronte.Activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.guma.desarrollo.caronte.Adapters.ClientesAdapter;
import com.guma.desarrollo.caronte.Adapters.IndicadoresAdapter;
import com.guma.desarrollo.caronte.AsyncHttpManager.ClientesRepository;
import com.guma.desarrollo.core.Indicadores;
import com.guma.desarrollo.caronte.R;

import java.util.ArrayList;
import java.util.List;

public class TableroClienteActivity extends AppCompatActivity {
    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;

    ListView mClienteList;
    ClientesAdapter mClientesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablero_cliente);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("CLIENTE: " + "CL0000222");


        recycler = (RecyclerView) findViewById(R.id.rv_detalle_cliente);
        mClienteList = (ListView) findViewById(R.id.list_producto_facturado);
        recycler.setHasFixedSize(true);
        lManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(lManager);
        infoCliente();


    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        recycler.setAdapter(null);
        mClienteList.setAdapter(null);

        int id = item.getItemId();
        if (id == R.id.action_recuperacion_efectivo) {
            recuperacion();
        } else if (id == R.id.action_producto_facturado) {
            ProductoFacturado();
        } else if (id == R.id.action_cuota_venta) {
            cuotaVenta();
        } else if (id == R.id.action_info_cliente) {
            infoCliente();
        }
        return super.onOptionsItemSelected(item);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detalle_tablero, menu);
        return true;
    }

    private void ProductoFacturado(){
        mClientesAdapter = new ClientesAdapter(this, ClientesRepository.getInstance(TableroClienteActivity.this).getClientes());
        mClienteList.setAdapter(mClientesAdapter);
    }
    private void infoCliente(){
        List items = new ArrayList();
        items.add(new Indicadores(R.drawable.logo, "VENTAS AL VALOR", "0"));
        items.add(new Indicadores(R.drawable.logo, "ITEM FACTURADO", "0"));
        items.add(new Indicadores(R.drawable.logo, "PROMEDIO POR FACTURA", "0"));
        items.add(new Indicadores(R.drawable.logo, "PROMEDIO POR ITEM FACTURADO", "0"));
        adapter = new IndicadoresAdapter(items);
        recycler.setAdapter(adapter);
    }
    private void recuperacion(){
        List items = new ArrayList();
        items.add(new Indicadores(R.drawable.logo, "META", "0"));
        items.add(new Indicadores(R.drawable.logo, "RECUPERADO", "0"));
        adapter = new IndicadoresAdapter(items);
        recycler.setAdapter(adapter);
    }
    private void cuotaVenta(){
        List items = new ArrayList();
        items.add(new Indicadores(R.drawable.logo, "META", "0"));
        items.add(new Indicadores(R.drawable.logo, "VENDIDO", "0"));
        adapter = new IndicadoresAdapter(items);
        recycler.setAdapter(adapter);
    }
}
