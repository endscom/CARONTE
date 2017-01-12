package com.guma.desarrollo.caronte.AsyncHttpManager;

import android.app.ProgressDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.guma.desarrollo.caronte.R;
import com.guma.desarrollo.core.Cliente;
import com.guma.desarrollo.core.ManagerURI;
import com.guma.desarrollo.core.SQLiteHelper;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by maryan.espinoza on 27/12/2016.
 */

public class ClientesRepository {
    private HashMap<String, Cliente> clents = new HashMap<>();

    public static ClientesRepository getInstance(Context ctx) {
        return new ClientesRepository(ctx);
    }


    private void saveLead(Cliente cls) {
        clents.put(cls.getId(), cls);
    }

    public List<Cliente> getClientes() {
        return new ArrayList<>(clents.values());
    }
    public static void getAsyncHttpFacturas(String vendedor, String Permiso, final Context cxnt)
    {
        AsyncHttpClient getFacturas = new AsyncHttpClient();
        RequestParams parametros = new RequestParams();
        parametros.put("V",vendedor);
        parametros.put("P",Permiso);

        getFacturas.get(ManagerURI.getURL_FACTURAS(), parametros, new AsyncHttpResponseHandler() {
            public ProgressDialog pdialog;

            @Override
            public void onStart() {
                pdialog = ProgressDialog.show(cxnt, "","Procesando. Porfavor Espere...", true);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                JSONArray jsonArray = null;
                try {

                    jsonArray = new JSONArray(new String(responseBody));
                    JSONObject joFacturas = (JSONObject) jsonArray.getJSONObject(0).get("FACTURAS");

                    SQLiteHelper.ExecuteSQL(ManagerURI.getDIR_DB(),cxnt,"DELETE FROM DETALLE_FACTURA_PUNTOS");
                    for (int i=0;i<joFacturas.length();i++)
                    {
                        SQLiteHelper.ExecuteSQL(ManagerURI.getDIR_DB(), cxnt,joFacturas.getString("FACTURA"+i));
                    }


                    /*INICIO CargarPromedios*/
                    //SQLiteHelper.ExecuteSQL(ManagerURI.getDIR_DB(),cxnt,"DELETE FROM PROMEDIOS");
                    //String x = jsonArray.getJSONObject(0).getString("PROMEDIOS");
                    //SQLiteHelper.ExecuteSQL(ManagerURI.getDIR_DB(), cxnt,x);
                    /*Fin CargarPromedios*/

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                pdialog.dismiss();
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
            }
        });
    }

    public static void getAsyncHttpClientes(String vendedor, String Permiso, final Context cxnt){
        AsyncHttpClient getData = new AsyncHttpClient();
        RequestParams parametros = new RequestParams();
        parametros.put("V",vendedor);
        parametros.put("P",Permiso);

        getData.get(ManagerURI.getURL_CLENTES(), parametros, new AsyncHttpResponseHandler() {
            public ProgressDialog pdialog;
            @Override
            public void onStart() { pdialog = ProgressDialog.show(cxnt, "","Procesando. Porfavor Espere...", true); }
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                JSONArray jsonArray = null;
                try {
                    jsonArray = new JSONArray(new String(responseBody));
                    JSONObject joClientes = (JSONObject) jsonArray.getJSONObject(0).get("CLIENTES");
                    SQLiteHelper.ExecuteSQL(ManagerURI.getDIR_DB(),cxnt,"DELETE FROM CLIENTES");
                    for (int i=0;i<joClientes.length();i++)
                    {
                        SQLiteHelper.ExecuteSQL(ManagerURI.getDIR_DB(), cxnt,joClientes.getString("CLIENTES"+i));
                    }
                    //SQLiteHelper.ExecuteSQL(ManagerURI.getDIR_DB(), cxnt,String.valueOf(jsonArray.getJSONObject(0).get("PROMEDIOS")));
                    /*INICIO CargarPromedios*/
                    SQLiteHelper.ExecuteSQL(ManagerURI.getDIR_DB(),cxnt,"DELETE FROM PROMEDIOS");
                    String x = jsonArray.getJSONObject(0).getString("PROMEDIOS");
                    SQLiteHelper.ExecuteSQL(ManagerURI.getDIR_DB(), cxnt,x);
                    /*Fin CargarPromedios*/

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                pdialog.dismiss();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    public  static String[] getVentaTotal(Context ctx){
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = null;
        String[] rVentaTotal = new String[2];
        try
        {
            myDbHelper = new SQLiteHelper(ManagerURI.getDIR_DB(), ctx);
            myDataBase = myDbHelper.getReadableDatabase();
            Cursor cursor = myDataBase.rawQuery("SELECT d.CODIGO, SUM(d.VENTA) VENTA FROM DETALLE_FACTURA_PUNTOS d GROUP BY d.CODIGO", null);
            if(cursor.getCount() > 0)
            {
                cursor.moveToFirst();
                while(!cursor.isAfterLast())
                {
                    rVentaTotal[0] = (String) cursor.getString(cursor.getColumnIndex("VENTA"));
                    cursor.moveToNext();
                }
            }
        }
        catch (Exception e) { e.printStackTrace(); }
        finally
        {
            if(myDataBase != null) { myDataBase.close(); }
            if(myDbHelper != null) { myDbHelper.close(); }
        }
        return rVentaTotal;
    }

    public  static String[] getPromedios(Context ctx){
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = null;
        String[] rPromedios = new String[2];

        try
        {
            myDbHelper = new SQLiteHelper(ManagerURI.getDIR_DB(), ctx);
            myDataBase = myDbHelper.getReadableDatabase();
            Cursor cursor = myDataBase.rawQuery("SELECT * FROM PROMEDIOS", null);
            if(cursor.getCount() > 0)
            {
                cursor.moveToFirst();
                while(!cursor.isAfterLast())
                {
                    rPromedios[0] = (String) cursor.getString(cursor.getColumnIndex("PRM_ART"));
                    rPromedios[1] = (String) cursor.getString(cursor.getColumnIndex("PRM_VTA"));

                    cursor.moveToNext();
                }
            }
        }
        catch (Exception e) { e.printStackTrace(); }
        finally
        {
            if(myDataBase != null) { myDataBase.close(); }
            if(myDbHelper != null) { myDbHelper.close(); }
        }
        return rPromedios;
    }

    private ClientesRepository(Context ctx){
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = null;
        try
        {
            myDbHelper = new SQLiteHelper(ManagerURI.getDIR_DB(), ctx);
            myDataBase = myDbHelper.getReadableDatabase();
            Cursor cursor = myDataBase.rawQuery("SELECT * FROM CLIENTES ORDER BY NOMBRE", null);
            if(cursor.getCount() > 0)
            {
                cursor.moveToFirst();
                while(!cursor.isAfterLast())
                {
                    saveLead(new Cliente(cursor.getString(cursor.getColumnIndex("NOMBRE")),cursor.getString(cursor.getColumnIndex("CLIENTE")), cursor.getString(cursor.getColumnIndex("DIRECCION")), R.drawable.icon));

                    cursor.moveToNext();
                }
            }
        }
        catch (Exception e) { e.printStackTrace(); }
        finally
        {
            if(myDataBase != null) { myDataBase.close(); }
            if(myDbHelper != null) { myDbHelper.close(); }
        }

    }


}
