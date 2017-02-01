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
import com.guma.desarrollo.caronte.Adapters.VentasPorClientesAdapter;
import com.guma.desarrollo.caronte.AsyncHttpManager.ClientesRepository;
import com.guma.desarrollo.caronte.AsyncHttpManager.VentasPorclienteRepository;
import com.guma.desarrollo.caronte.R;
import com.guma.desarrollo.core.Clock;
import com.guma.desarrollo.core.ManagerURI;
import com.guma.desarrollo.core.SQLiteHelper;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

public class VentasPorClienteActivity extends AppCompatActivity {
    MaterialSearchView searchView;
    ListView mVentasPorClienteList;
    VentasPorClientesAdapter mVentasPorClienteAdapter;

    ClientesAdapter mAdapterResultSearch;

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventas_por_cliente);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //toolbar.setTitleTextColor(getResources().getColor(R.color.Blanco));

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();


        setTitle("VENTAS POR CLIENTES");
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        mVentasPorClienteList = (ListView) findViewById(R.id.leads_list_v);
        //mVentasPorClienteAdapter = new VentasPorClientesAdapter(this, ClientesRepository.getInstance(VentasPorClienteActivity.this).getVentasPorcliente());
        mVentasPorClienteAdapter = new VentasPorClientesAdapter(this, VentasPorclienteRepository.getInstance(VentasPorClienteActivity.this).getVentasPorCliente());
        mVentasPorClienteList.setAdapter(mVentasPorClienteAdapter);

        mVentasPorClienteList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //startActivity(new Intent(VentasPorClienteActivity.this,));
                                                         }
                                                     }
        );
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
        SQLiteHelper.ExecuteSQL(ManagerURI.getDIR_DB(),this,"INSERT INTO LOG VALUES ('" + preferences.getString("User","") + "', '" + Clock.getTimeStamp() + "', 'VentasPorCliente', 'OUT')");
        super.onPause();
    }

    @Override
    protected void onResume() {
        SQLiteHelper.ExecuteSQL(ManagerURI.getDIR_DB(),this,"INSERT INTO LOG VALUES ('" + preferences.getString("User","") + "', '" + Clock.getTimeStamp() + "', 'VentasPorCliente', 'IN')");
        super.onResume();
    }
}
