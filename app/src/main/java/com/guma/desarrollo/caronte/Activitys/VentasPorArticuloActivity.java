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
import com.guma.desarrollo.caronte.Adapters.VentasPorArticuloAdapter;
import com.guma.desarrollo.caronte.Adapters.VentasPorClientesAdapter;
import com.guma.desarrollo.caronte.AsyncHttpManager.ClientesRepository;
import com.guma.desarrollo.caronte.AsyncHttpManager.VentasPorArticuloRepository;
import com.guma.desarrollo.caronte.AsyncHttpManager.VentasPorclienteRepository;
import com.guma.desarrollo.caronte.R;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

public class VentasPorArticuloActivity extends AppCompatActivity {
    MaterialSearchView searchView;
    ListView mVentasPorArticuloList;
    VentasPorArticuloAdapter mVentasPorArticuloAdapter;

    VentasPorArticuloActivity mAdapterResultSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventas_por_articulo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        mVentasPorArticuloList = (ListView) findViewById(R.id.leads_list_a);
        mVentasPorArticuloAdapter = new VentasPorArticuloAdapter(this, VentasPorArticuloRepository.getInstance(VentasPorArticuloActivity.this).getVentasPorArticulo());
        mVentasPorArticuloList.setAdapter(mVentasPorArticuloAdapter);
    }
}
