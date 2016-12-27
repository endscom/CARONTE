package com.guma.desarrollo.caronte;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DetalleTableroActivity extends AppCompatActivity {
    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_tablero);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("DETALLE DE " + "XXXX");
        final ListView mLeadsList = (ListView) findViewById(R.id.list_detalle_tablero);
        final LeadsAdapter mLeadsAdapter = new LeadsAdapter(this,LeadsRepository.getInstance().getLeads());
        mLeadsList.setAdapter(mLeadsAdapter);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
