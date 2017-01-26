package com.guma.desarrollo.caronte.Activitys;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.guma.desarrollo.caronte.Adapters.IndicadoresAdapter;
import com.guma.desarrollo.caronte.AsyncHttpManager.ClientesRepository;
import com.guma.desarrollo.caronte.AsyncHttpManager.NumArticulosPorClienteRepository;
import com.guma.desarrollo.core.Cliente;
import com.guma.desarrollo.core.Indicadores;
import com.guma.desarrollo.caronte.R;
import com.guma.desarrollo.core.ManagerURI;

import java.util.ArrayList;
import java.util.List;

public class TableroActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablero);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("INDICADORES");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();
/*
        CargarClientes(preferences.getString("User",""),preferences.getString("Rol",""),TableroActivity.this);
        CargarFacturas(preferences.getString("User",""),preferences.getString("Rol",""),TableroActivity.this);
*/
        CargarClientes("F06","0",TableroActivity.this);
        CargarFacturas("F06","0",TableroActivity.this);
        CargarFacturasIndicadores("F06","0",TableroActivity.this);
        //CargarPorRecuperar(preferences.getString("User",""),preferences.getString("Rol",""),TableroActivity.this);

        List items = new ArrayList();

        //Traer Total de Ventas
        String  Venta[] = ClientesRepository.getVentaTotal(TableroActivity.this);
        if (Venta.length>0)
        {
            items.add(new Indicadores(R.drawable.logo, "# CLIENTES", Venta[4]));
            items.add(new Indicadores(R.drawable.logo, "# ITEM FACTURADOS", Venta[2]));
            items.add(new Indicadores(R.drawable.logo, "# ITEMS POR CLIENTE", ""));
            items.add(new Indicadores(R.drawable.logo, "VENTA TOTAL", Venta[0]));
        }


        //items.add(new Indicadores(R.drawable.logo, "VENTA TOTAL", "0"));
        items.add(new Indicadores(R.drawable.logo, "VENTAS POR ARTICULO", ""));
        items.add(new Indicadores(R.drawable.logo, "VENTAS POR CLIENTE", ""));
        items.add(new Indicadores(R.drawable.logo, "RECUPERACION DE CARTERA", "0"));

        String cursor[] = ClientesRepository.getPromedios(TableroActivity.this);

        Log.d("", "onCreate: " + String.valueOf(cursor[0]));

        if(cursor.length > 0)
        {

            items.add(new Indicadores(R.drawable.logo, "PROMEDIO POR ITEM", cursor[0]));
            items.add(new Indicadores(R.drawable.logo, "PROMEDIO POR FACTURA", cursor[1]));
        }

        recycler = (RecyclerView) findViewById(R.id.my_recycler_view);
        recycler.setHasFixedSize(true);
        lManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(lManager);
        adapter = new IndicadoresAdapter(items);
        recycler.setAdapter(adapter);

        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ManagerURI.isOnlinea(TableroActivity.this)){
                    ClientesRepository.getAsyncHttpClientes("F06","0",TableroActivity.this);
                }else {
                    Toast.makeText(TableroActivity.this, "Sin permiso de internet", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    void CargarClientes(String Vendedor, String Perfil, final Context cnxt)
    {
        if (ManagerURI.isOnlinea(TableroActivity.this))
        {
            //ClientesRepository.getAsyncHttpClientes("F06","0",TableroActivity.this);
            ClientesRepository.getAsyncHttpClientes(Vendedor,Perfil,cnxt);
        }
        else
        {
            Toast.makeText(TableroActivity.this, "Sin permiso de internet", Toast.LENGTH_SHORT).show();
        }
    }

    void CargarFacturasIndicadores(String Vendedor, String Perfil, final Context cnxt)
    {
        if (ManagerURI.isOnlinea(TableroActivity.this))
        {
            ClientesRepository.getAsyncHttpFacturasIndicadores(Vendedor, Perfil , cnxt);
        }
        else
        {
            Toast.makeText(TableroActivity.this, "Sin Permiso de Internet", Toast.LENGTH_SHORT).show();
        }
    }
    void CargarFacturas(String Vendedor, String Perfil, final Context cnxt)
    {
        if (ManagerURI.isOnlinea(TableroActivity.this))
        {
            ClientesRepository.getAsyncHttpFacturas(Vendedor,Perfil,cnxt);
        }
        else
        {
            Toast.makeText(TableroActivity.this, "Sin permiso de internet", Toast.LENGTH_SHORT).show();
        }
    }
    void CargarPorRecuperar(String Vendedor, String Perfil, final Context cnxt)
    {
        if (ManagerURI.isOnlinea(TableroActivity.this))
        {
            ClientesRepository.getAsyncHttpPorRecuperar(Vendedor,Perfil,cnxt);
        }
        else
        {
            Toast.makeText(TableroActivity.this, "Sin permiso de internet", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tablero, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_Clientes) {
            startActivity(new Intent(TableroActivity.this,ClientesActivity.class));
        } else if (id == R.id.nav_plan) {
            startActivity(new Intent(TableroActivity.this,AgendaActivity.class));
        } else if (id == R.id.nav_salir) {
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
