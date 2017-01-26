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
import com.guma.desarrollo.caronte.AsyncHttpManager.ClientesRepository;
import com.guma.desarrollo.caronte.R;
import com.guma.desarrollo.core.Clock;
import com.guma.desarrollo.core.ManagerURI;
import com.guma.desarrollo.core.SQLiteHelper;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ClientesActivity extends AppCompatActivity {
    MaterialSearchView searchView;
    ListView mClienteList;
    ClientesAdapter mClientesAdapter;

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;


    ClientesAdapter mAdapterResultSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitleTextColor(getResources().getColor(R.color.Blanco));

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();

        setTitle("LISTA DE CLIENTES");

        mClienteList = (ListView) findViewById(R.id.leads_list);
        mClientesAdapter = new ClientesAdapter(this, ClientesRepository.getInstance(ClientesActivity.this).getClientes());

        mClienteList.setAdapter(mClientesAdapter);

        mClienteList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(ClientesActivity.this,TableroClienteActivity.class));
            }
        });

        searchView = (MaterialSearchView) findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mClientesAdapter = new ClientesAdapter(ClientesActivity.this, mClientesAdapter.getFilter(query,ClientesRepository.getInstance(ClientesActivity.this).getClientes()));
                if (mClientesAdapter.getCount() == 0){
                    mClienteList.setAdapter(new ClientesAdapter(ClientesActivity.this, ClientesRepository.getInstance(ClientesActivity.this).getClientes()));
                    Toast.makeText(ClientesActivity.this, "No se Encontro Resultado.reos", Toast.LENGTH_SHORT).show();
                }else {
                    mClienteList.setAdapter(mClientesAdapter);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });


        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                //Do some magic
            }

            @Override
            public void onSearchViewClosed() {
                //Do some magic
            }
        });
    }
    @Override
    protected void onPause() {
        SQLiteHelper.ExecuteSQL(ManagerURI.getDIR_DB(),this,"INSERT INTO LOG VALUES ('" + preferences.getString("User","") + "','" + Clock.getTimeStamp() + "', 'Lista de Cliente', 'OUT')");
        super.onPause();
    }

    @Override
    protected void onResume() {
        SQLiteHelper.ExecuteSQL(ManagerURI.getDIR_DB(),this,"INSERT INTO LOG VALUES ('" + preferences.getString("User","") + "', '" + Clock.getTimeStamp() + "', 'Lista de Cliente', 'IN')");
        super.onResume();
    }
    @Override
    public void onBackPressed() {
        if (searchView.isSearchOpen()) {
            searchView.closeSearch();
        } else {
            super.onBackPressed();
        }
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_buscar, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);
        return true;
    }
}
