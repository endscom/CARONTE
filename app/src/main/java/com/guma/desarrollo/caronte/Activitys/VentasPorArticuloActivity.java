package com.guma.desarrollo.caronte.Activitys;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
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
import com.guma.desarrollo.core.Clock;
import com.guma.desarrollo.core.ManagerURI;
import com.guma.desarrollo.core.SQLiteHelper;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

public class VentasPorArticuloActivity extends AppCompatActivity {
    MaterialSearchView searchView;
    ListView mVentasPorArticuloList;
    VentasPorArticuloAdapter mVentasPorArticuloAdapter;

    VentasPorArticuloActivity mAdapterResultSearch;

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventas_por_articulo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();


        mVentasPorArticuloList = (ListView) findViewById(R.id.leads_list_a);
        mVentasPorArticuloAdapter = new VentasPorArticuloAdapter(this, VentasPorArticuloRepository.getInstance(VentasPorArticuloActivity.this).getVentasPorArticulo());
        mVentasPorArticuloList.setAdapter(mVentasPorArticuloAdapter);

        setTitle("VENTAS POR ARTICULO");


    }
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if (id == 16908332){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onPause() {
        SQLiteHelper.ExecuteSQL(ManagerURI.getDIR_DB(),this,"INSERT INTO LOG VALUES ('" + preferences.getString("User","") + "', '" + Clock.getTimeStamp() + "', 'VentasPorArticulo', 'OUT')");
        super.onPause();
    }

    @Override
    protected void onResume() {
        SQLiteHelper.ExecuteSQL(ManagerURI.getDIR_DB(),this,"INSERT INTO LOG VALUES ('" + preferences.getString("User","") + "', '" + Clock.getTimeStamp() + "', 'VentasPorArticulo', 'IN')");
        super.onResume();
    }
}
