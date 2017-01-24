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
import com.guma.desarrollo.core.VentaPorCliente;
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
    private HashMap<String, VentaPorCliente> vclents = new HashMap<>();

    public static ClientesRepository getInstance(Context ctx) {
        return new ClientesRepository(ctx);
    }


    private void saveLead(Cliente cls) {
        clents.put(cls.getId(), cls);
    }

    public List<VentaPorCliente> getVentasPorcliente()
    { return new ArrayList<>(vclents.values()); }

    public List<Cliente> getClientes() {
        return new ArrayList<>(clents.values());
    }
    public static void getAsyncHttpPorRecuperar(String Vendedor, String Permiso, final Context cxnt)
    {
        AsyncHttpClient getFacturas = new AsyncHttpClient();
        RequestParams parametros = new RequestParams();
        parametros.put("V",Vendedor);
        parametros.put("P",Permiso);
        getFacturas.get(ManagerURI.getURL_PORRECUPERAR(), parametros, new AsyncHttpResponseHandler() {
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
                    JSONObject joFacturas = (JSONObject) jsonArray.getJSONObject(0).get("PorRecuperar");

                    SQLiteHelper.ExecuteSQL(ManagerURI.getDIR_DB(),cxnt,"DELETE FROM CC_CLIENTES");
                    for (int i=0;i<joFacturas.length();i++)
                    {
                        SQLiteHelper.ExecuteSQL(ManagerURI.getDIR_DB(), cxnt,joFacturas.getString("PorRecuperar"+i));
                    }
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

                    /*INICIO CargarPromedios3*/
                    SQLiteHelper.ExecuteSQL(ManagerURI.getDIR_DB(),cxnt,"DELETE FROM PROMEDIOS3");
                    String x3 = jsonArray.getJSONObject(0).getString("PROMEDIOS3");
                    SQLiteHelper.ExecuteSQL(ManagerURI.getDIR_DB(), cxnt,x3);
                    /*Fin CargarPromedios3*/

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
        String[] rVentaTotal = new String[5];
        try
        {
            myDbHelper = new SQLiteHelper(ManagerURI.getDIR_DB(), ctx);
            myDataBase = myDbHelper.getReadableDatabase();
          //Cursor cursor = myDataBase.rawQuery("SELECT d.CODIGO, SUM(d.VENTA) VENTA FROM DETALLE_FACTURA_PUNTOS d GROUP BY d.CODIGO", null);
            Cursor cursor = myDataBase.rawQuery("SELECT d.CODIGO, ROUND(SUM(d.VENTA),2) SUM_VENTA, ROUND(AVG(d.VENTA),2) AVG_VENTA" +
                                                "       , ROUND(SUM(d.CANTIDAD),2) NUM_ITEM, ROUND(AVG(d.CANTIDAD),2)  AVG_ITEM, COUNT(DISTINCT CODCLIENTE) CLIENTES " +
                                                "FROM DETALLE_FACTURA_PUNTOS d GROUP BY d.CODIGO;", null);
            Log.d("", "getVentaTotal: QUE PEDO");
            if(cursor.getCount() > 0)
            {
                cursor.moveToFirst();
                while(!cursor.isAfterLast())
                {

                    rVentaTotal[0] = (String) cursor.getString(cursor.getColumnIndex("SUM_VENTA"));
                    rVentaTotal[1] = (String) cursor.getString(cursor.getColumnIndex("AVG_VENTA"));
                    rVentaTotal[2] = (String) cursor.getString(cursor.getColumnIndex("NUM_ITEM"));
                    rVentaTotal[3] = (String) cursor.getString(cursor.getColumnIndex("AVG_ITEM"));
                    rVentaTotal[4] = (String) cursor.getString(cursor.getColumnIndex("CLIENTES"));
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

    public static String[] getPromedios3(Context ctx, String Cliente){
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = null;
        String[] rPromedios3 = new String[4];
        try
        {
            myDbHelper = new SQLiteHelper(ManagerURI.getDIR_DB(), ctx);
            myDataBase = myDbHelper.getReadableDatabase();
            Cursor cursor = myDataBase.rawQuery("SELECT p3.NOMBRE, p3.CLIENTE, ROUND(p3.PRM_ART_3, 2) PRM_ART_3, ROUND(p3.PRM_VTA_3,2) PRM_VTA_3 FROM PROMEDIOS3 p3 WHERE CLIENTE='"+Cliente+"';", null);
            if(cursor.getCount() > 0)
            {
                cursor.moveToFirst();
                while(!cursor.isAfterLast())
                {
                    rPromedios3[0] = (String) cursor.getString(cursor.getColumnIndex("NOMBRE"));
                    rPromedios3[1] = (String) cursor.getString(cursor.getColumnIndex("CLIENTE"));
                    rPromedios3[2] = (String) cursor.getString(cursor.getColumnIndex("PRM_ART_3"));
                    rPromedios3[3] = (String) cursor.getString(cursor.getColumnIndex("PRM_VTA_3"));
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
        return rPromedios3;
    }

    public  static String[] getPromedios(Context ctx){
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = null;
        String[] rPromedios = new String[2];

        try
        {
            myDbHelper = new SQLiteHelper(ManagerURI.getDIR_DB(), ctx);
            myDataBase = myDbHelper.getReadableDatabase();
            Cursor cursor = myDataBase.rawQuery("SELECT VENDEDOR, ROUND(PRM_ART,2) PRM_ART, ROUND(PRM_VTA,2) PRM_VTA FROM PROMEDIOS", null);
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
            IndicadoresRepository(ctx);
        }

    }

    private void saveVentasPorCliente(VentaPorCliente vcls) {
//        clents.put(cls.getId(), cls);
        vclents.put(vcls.getID(), vcls);
    }

    private void IndicadoresRepository(Context ctx)
    {
        SQLiteDatabase mydb = null;
        SQLiteHelper mydbh = null;
        try
        {
            mydbh = new SQLiteHelper(ManagerURI.getDIR_DB(), ctx);
            mydb = mydbh.getReadableDatabase();
            Cursor cursor = mydb.rawQuery("SELECT d.CODIGO, d.CODCLIENTE, c.NOMBRE" +
                                          ", SUM(d.VENTA) TOTAL " +
                                          "FROM DETALLE_FACTURA_PUNTOS d INNER JOIN CLIENTES c ON d.CODCLIENTE=c.CLIENTE " +
                                          "GROUP BY d.CODIGO, d.CODCLIENTE, c.NOMBRE " +
                                          "ORDER BY c.NOMBRE;", null);
            if (cursor.getCount()>0)
            {
                cursor.moveToFirst();
                while (!cursor.isAfterLast())
                {
                    saveVentasPorCliente(new VentaPorCliente(cursor.getString(cursor.getColumnIndex("CODIGO")),cursor.getString(cursor.getColumnIndex("NOMBRE")),cursor.getString(cursor.getColumnIndex("TOTAL")),R.drawable.icon));
                    cursor.moveToNext();
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (mydb != null) { mydb.close(); }
            if (mydbh != null) { mydbh.close(); }
        }
    }

}
