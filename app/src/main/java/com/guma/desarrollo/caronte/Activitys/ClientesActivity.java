package com.guma.desarrollo.caronte.Activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.guma.desarrollo.caronte.Adapters.ClientesAdapter;
import com.guma.desarrollo.caronte.ClientesRepository;
import com.guma.desarrollo.caronte.R;

public class ClientesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("LISTA DE CLIENTES");
        final ListView mClienteList = (ListView) findViewById(R.id.leads_list);
        final ClientesAdapter mClientesAdapter = new ClientesAdapter(this, ClientesRepository.getInstance().getClientes());
        mClienteList .setAdapter(mClientesAdapter);
        mClienteList .setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(ClientesActivity.this,TableroClienteActivity.class));
            }
        });
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
