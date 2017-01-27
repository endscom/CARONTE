package com.guma.desarrollo.caronte.Activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.guma.desarrollo.caronte.Adapters.ClientesAdapter;
import com.guma.desarrollo.caronte.Adapters.Indicadores3Adapter;
import com.guma.desarrollo.caronte.Adapters.IndicadoresAdapter;
import com.guma.desarrollo.caronte.AsyncHttpManager.ClientesRepository;
import com.guma.desarrollo.core.Indicadores;
import com.guma.desarrollo.caronte.R;
import com.guma.desarrollo.core.Indicadores3;

import java.util.ArrayList;
import java.util.List;

public class TableroClienteActivity extends AppCompatActivity {
    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;

    private String CodCliente="", NombreCliente="";

    ListView mClienteList;
    ClientesAdapter mClientesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablero_cliente);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle bundle = getIntent().getExtras();
        CodCliente = bundle.get("CodCliente").toString();
        NombreCliente = bundle.get("NombreCliente").toString();

        //setTitle("CLIENTE: " + "CL0000222");
        setTitle("CLIENTE: " + bundle.get("CodCliente") + " - " + bundle.get("NombreCliente"));

        recycler = (RecyclerView) findViewById(R.id.rv_detalle_cliente);
        mClienteList = (ListView) findViewById(R.id.list_producto_facturado);
        recycler.setHasFixedSize(true);
        lManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(lManager);
        infoCliente(CodCliente);
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
            infoCliente(CodCliente);
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
    private void infoCliente(String CodCliente){
        //String cursor[] = ClientesRepository.getPromedios3(TableroClienteActivity.this, "00003");
        String cursor[] = ClientesRepository.getPromedios3(TableroClienteActivity.this, CodCliente, NombreCliente);
        List items = new ArrayList();
        if(cursor.length > 0)
        {
            items.add(new Indicadores3(R.drawable.logo, "VENTA EN VALORES", "Promedio: ".concat(cursor[0]==null?"0.00":cursor[0]), "Meta: 0", "Actual: ".concat(cursor[4]==null?"0.00":cursor[4]), "Pendiente: 0"));
            items.add(new Indicadores3(R.drawable.logo, "PROMEDIO DE ITEMS FACTURADOS", "Promedio: ".concat(cursor[1]==null?"0":cursor[1]), "Meta: 0", "Actual: ".concat(cursor[5]==null?"0":cursor[5]), "Pendiente: 0"));
            items.add(new Indicadores3(R.drawable.logo, "MONTO POR FACTURA", "Promedio: ".concat(cursor[3]==null?"0":cursor[3]), "Meta: 0", "Actual: ".concat(cursor[7]==null?"0":cursor[7]), "Pendiente: 0"));
            items.add(new Indicadores3(R.drawable.logo, "PROMEDIO DE ITEMS POR FACTURA", "Promedio: ".concat(cursor[2]==null?"0":cursor[2]), "Meta: 0", "Actual: ".concat(cursor[6]==null?"0":cursor[6]), "Pendiente: 0"));
        }

        /*items.add(new Indicadores3(R.drawable.logo, "", "MONTO POR FACTURA", "0", "", ""));
        items.add(new Indicadores3(R.drawable.logo, "", "PROMEDIO DE ITEM POR FACTURA", "0", "", ""));
        items.add(new Indicadores(R.drawable.logo, "promedio", "0"));
        items.add(new Indicadores(R.drawable.logo, "# DE ITEMS FACTURADO", "0"));
        items.add(new Indicadores(R.drawable.logo, "MONTO POR FACTURA", "0"));
        items.add(new Indicadores(R.drawable.logo, "PROMEDIO DE ITEM POR FACTURA", "0"));*/
        adapter = new Indicadores3Adapter(items);
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
