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
import com.guma.desarrollo.caronte.Adapters.NumArticulosPorClienteAdapter;
import com.guma.desarrollo.caronte.Adapters.VentasPorArticuloAdapter;
import com.guma.desarrollo.caronte.Adapters.VentasPorClientesAdapter;
import com.guma.desarrollo.caronte.AsyncHttpManager.ClientesRepository;
import com.guma.desarrollo.caronte.AsyncHttpManager.NumArticulosPorClienteRepository;
import com.guma.desarrollo.caronte.AsyncHttpManager.VentasPorArticuloRepository;
import com.guma.desarrollo.caronte.AsyncHttpManager.VentasPorclienteRepository;
import com.guma.desarrollo.caronte.R;
import com.guma.desarrollo.core.Clock;
import com.guma.desarrollo.core.ManagerURI;
import com.guma.desarrollo.core.NumArticuloPorCliente;
import com.guma.desarrollo.core.SQLiteHelper;
import com.miguelcatalan.materialsearchview.MaterialSearchView;
public class NumArticulosPorCliente extends AppCompatActivity {
    MaterialSearchView searchView;
    ListView mNumArticulosPorClienteList;
    NumArticulosPorClienteAdapter mNumArticuloPorClienteAdapter;


    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_num_articulos_por_cliente);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();

        mNumArticulosPorClienteList = (ListView) findViewById(R.id.leads_list_c);
        mNumArticuloPorClienteAdapter = new NumArticulosPorClienteAdapter(this, NumArticulosPorClienteRepository.getInstance(NumArticulosPorCliente.this).getNumArticulosPorCliente());
        mNumArticulosPorClienteList.setAdapter(mNumArticuloPorClienteAdapter);

        setTitle("# DE ARTICULOS POR CLIENTE");
    }
    @Override
    protected void onPause() {
        SQLiteHelper.ExecuteSQL(ManagerURI.getDIR_DB(),this,"INSERT INTO LOG VALUES ('" + preferences.getString("User","") + "', '" + Clock.getTimeStamp() + "', 'Articulo por cliente', 'OUT')");
        super.onPause();
    }
    @Override
    protected void onResume() {
        SQLiteHelper.ExecuteSQL(ManagerURI.getDIR_DB(),this,"INSERT INTO LOG VALUES ('" + preferences.getString("User","") + "', '" + Clock.getTimeStamp() + "', 'Articulo por cliente', 'IN')");
        super.onResume();
    }
}
