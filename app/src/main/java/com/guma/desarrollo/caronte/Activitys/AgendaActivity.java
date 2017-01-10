package com.guma.desarrollo.caronte.Activitys;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.SearchView;

import com.guma.desarrollo.caronte.Adapters.ChildRow;
import com.guma.desarrollo.caronte.Adapters.MyExpandableListAdapter;
import com.guma.desarrollo.caronte.Adapters.ParentRow;
import com.guma.desarrollo.caronte.R;

import java.util.ArrayList;

public class AgendaActivity extends AppCompatActivity implements SearchView.OnQueryTextListener,SearchView.OnCloseListener{
    private SearchManager searchManager;
    private android.widget.SearchView searchView;
    private MyExpandableListAdapter listAdapter;
    private ExpandableListView myList;
    private ArrayList<ParentRow> parentList= new ArrayList<ParentRow>();
    private ArrayList<ParentRow> showTheseParentList = new ArrayList<ParentRow>();
    private MenuItem searchItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_agenda);
        setSupportActionBar(toolbar);

        setTitle("PLAN DE TRABAJO");
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        parentList = new ArrayList<ParentRow>();
        showTheseParentList = new ArrayList<ParentRow>();
        displayList();
        expandAll();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_buscar_2, menu);
        searchItem = menu.findItem(R.id.action_search);
        searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setSearchableInfo
                (searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false);
        searchView.setOnQueryTextListener(this);
        searchView.setOnCloseListener(this);
        searchView.requestFocus();
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if (id == 16908332){
           // finish();
        }


        switch (id) {
            case R.id.action_eyes:
                startActivity(new Intent(AgendaActivity.this,AgendaCompletaActivity.class));
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private void displayList(){
        loadData();
        myList = (ExpandableListView) findViewById(R.id.expandableListView_search);
        listAdapter = new MyExpandableListAdapter(this,parentList);
        myList.setAdapter(listAdapter);

    }

    private void loadData() {
        ArrayList<ChildRow> chilRows = new ArrayList<ChildRow>();
        ParentRow parentRow = null;

        chilRows.add(new ChildRow(R.drawable.ic_event_black_24dp,"Cliente 1"));
        parentRow = new ParentRow("LUNES",chilRows);
        parentList.add(parentRow);

        chilRows.add(new ChildRow(R.drawable.ic_event_black_24dp,"Cliente 1"));
        parentRow = new ParentRow("MARTES",chilRows);
        parentList.add(parentRow);

        chilRows.add(new ChildRow(R.drawable.ic_event_black_24dp,"Cliente 1"));
        parentRow = new ParentRow("MIERCOLES",chilRows);
        parentList.add(parentRow);

        chilRows.add(new ChildRow(R.drawable.ic_event_black_24dp,"Cliente 1"));
        parentRow = new ParentRow("JUEVES",chilRows);
        parentList.add(parentRow);

        chilRows.add(new ChildRow(R.drawable.ic_event_black_24dp,"Cliente 1"));
        parentRow = new ParentRow("VIERNES",chilRows);
        parentList.add(parentRow);

    }

    private void expandAll()
    {
        int count = listAdapter.getGroupCount();
        for (int i=0;i<count;i++){ myList.expandGroup(i); }
    }


    @Override
    public boolean onClose() {
        listAdapter.filterData("");
        expandAll();
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        listAdapter.filterData(query);
        expandAll();
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        listAdapter.filterData(newText);
        expandAll();
        return false;
    }

}
