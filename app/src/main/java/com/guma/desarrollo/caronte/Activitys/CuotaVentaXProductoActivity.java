package com.guma.desarrollo.caronte.Activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.guma.desarrollo.caronte.Adapters.ClientesAdapter;
import com.guma.desarrollo.caronte.Adapters.CuotaVentaXProductoAdapter;
import com.guma.desarrollo.caronte.AsyncHttpManager.ClientesRepository;
import com.guma.desarrollo.caronte.AsyncHttpManager.CuotaVentaXProductoRepository;
import com.guma.desarrollo.caronte.R;
import com.guma.desarrollo.core.Cliente;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

public class CuotaVentaXProductoActivity extends AppCompatActivity {
    MaterialSearchView searchView;
    ListView mCuotaVentaXProductoList;
    CuotaVentaXProductoAdapter mCuotaVentaXProductoAdapter;

    CuotaVentaXProductoAdapter mAdapterResultSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuota_venta_xproducto);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //toolbar.setTitleTextColor(getResources().getColor(R.color.Blanco));

        setTitle("CUOTA DE VENTAS POR ARTICULO");

        mCuotaVentaXProductoList = (ListView) findViewById(R.id.list_item_CuotaXVendedor);
        mCuotaVentaXProductoAdapter = new CuotaVentaXProductoAdapter(this, CuotaVentaXProductoRepository.getInstance(CuotaVentaXProductoActivity.this).getCuotaVentasPorProducto());
        mCuotaVentaXProductoList.setAdapter(mCuotaVentaXProductoAdapter);

    }
}
