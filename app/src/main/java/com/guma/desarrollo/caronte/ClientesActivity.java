package com.guma.desarrollo.caronte;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class ClientesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("LISTA DE CLIENTES");
        final ListView mLeadsList = (ListView) findViewById(R.id.leads_list);
        final LeadsAdapter mLeadsAdapter = new LeadsAdapter(this,LeadsRepository.getInstance().getLeads());
        mLeadsList.setAdapter(mLeadsAdapter);
        mLeadsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //mLeadsList.getItemAtPosition(position)
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
