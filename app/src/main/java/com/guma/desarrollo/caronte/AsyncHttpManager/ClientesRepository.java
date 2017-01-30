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
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by maryan.espinoza on 27/12/2016.
 */

public class ClientesRepository {
    private static final String TAG = "ClientesRepository";
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
        List<Cliente> OrderClient= new ArrayList<>(clents.values());
        Collections.sort(OrderClient, new Comparator<Cliente>(){
            public int compare(Cliente obj1, Cliente obj2)
            {
                return obj1.getName().compareToIgnoreCase(obj2.getName());
            }
        });
        return OrderClient;
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

    public static void getAsyncHttpMetasPorCliente(String vendedor, String Permiso, final Context cxnt)
    {
        AsyncHttpClient getFacturas = new AsyncHttpClient();
        RequestParams parametros = new RequestParams();
        parametros.put("V",vendedor);
        parametros.put("P",Permiso);
        getFacturas.get(ManagerURI.getURL_METASXCLIENTE(), parametros, new AsyncHttpResponseHandler() {
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
                    JSONObject joFacturas = (JSONObject) jsonArray.getJSONObject(0).get("METASXCLIENTE");

                    SQLiteHelper.ExecuteSQL(ManagerURI.getDIR_DB(),cxnt,"DELETE FROM METAS_POR_CLIENTE");
                    for (int i=0;i<joFacturas.length();i++)
                    //for (int i=0;i<jsonArray.length();i++)
                    {
                        SQLiteHelper.ExecuteSQL(ManagerURI.getDIR_DB(), cxnt,joFacturas.getString("METASXCLIENTE"+i));
                        //SQLiteHelper.ExecuteSQL(ManagerURI.getDIR_DB(), cxnt,jsonArray.getString(""+i));
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

    public static void getAsyncHttpCuotaVtaXProducto(String vendedor, String Permiso, final Context cxnt)
    {
        AsyncHttpClient getFacturas = new AsyncHttpClient();
        RequestParams parametros = new RequestParams();
        parametros.put("V",vendedor);
        parametros.put("P",Permiso);
        getFacturas.get(ManagerURI.getURL_CUOTAXPRODUCTO(), parametros, new AsyncHttpResponseHandler() {
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
                    JSONObject joFacturas = (JSONObject) jsonArray.getJSONObject(0).get("CUOTAXPRODUCTO");

                    SQLiteHelper.ExecuteSQL(ManagerURI.getDIR_DB(),cxnt,"DELETE FROM CUOTA_POR_PRODUCTO");
                    for (int i=0;i<joFacturas.length();i++)
                    //for (int i=0;i<jsonArray.length();i++)
                    {
                        SQLiteHelper.ExecuteSQL(ManagerURI.getDIR_DB(), cxnt,joFacturas.getString("CUOTAXPRODUCTO"+i));
                        //SQLiteHelper.ExecuteSQL(ManagerURI.getDIR_DB(), cxnt,jsonArray.getString(""+i));
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

    public static void getAsyncHttpFacturasIndicadores(String vendedor, String Permiso, final Context cxnt)
    {
        AsyncHttpClient getFacturas = new AsyncHttpClient();
        RequestParams parametros = new RequestParams();
        parametros.put("V",vendedor);
        parametros.put("P",Permiso);
        getFacturas.get(ManagerURI.getURL_FACTURASINDICADORES(), parametros, new AsyncHttpResponseHandler() {
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
                    JSONObject joFacturas = (JSONObject) jsonArray.getJSONObject(0).get("INDICADORESCONSOLIDADOS");

                    SQLiteHelper.ExecuteSQL(ManagerURI.getDIR_DB(),cxnt,"DELETE FROM INDICADORES3");
                    for (int i=0;i<joFacturas.length();i++)
                    {
                        SQLiteHelper.ExecuteSQL(ManagerURI.getDIR_DB(), cxnt,joFacturas.getString("INDICADORESCONSOLIDADOS"+i));
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
                                                "       , ROUND(COUNT(DISTINCT d.ARTICULO),2) NUM_ITEM, ROUND(AVG(d.CANTIDAD),2)  AVG_ITEM, COUNT(DISTINCT CODCLIENTE) CLIENTES " +
                                                "FROM DETALLE_FACTURA_PUNTOS d GROUP BY d.CODIGO;", null);

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

    public static String[] getPromedios3(Context ctx, String CodCliente, String NombreCliente){
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = null;
        String[] rPromedios3 = new String[14];
        try
        {
            myDbHelper = new SQLiteHelper(ManagerURI.getDIR_DB(), ctx);
            myDataBase = myDbHelper.getReadableDatabase();
            //Cursor cursor = myDataBase.rawQuery("SELECT p3.NOMBRECLIENTE, p3.CLIENTE, ROUND(p3.PRM_ART_3,2) PRM_ART_3, ROUND(p3.PRM_VTA_3,2) PRM_VTA_3 FROM PROMEDIOS3 p3 WHERE CLIENTE='"+CodCliente+"';", null);
            Cursor cursor = myDataBase.rawQuery("SELECT ROUND(i.VENTAS_3,4) VENTAS_3, i.NUM_ART_FAC_3, ROUND(i.PROMEDIO_ART_3,4) PROMEDIO_ART_3, " +
                                                "ROUND(i.MontoPromXFac_3,4) MontoPromXFac_3, ROUND(i.VENTAS_Act,4) VENTAS_Act, i.NUM_ART_FAC_Act, " +
                                                "ROUND(i.PROMEDIO_ART_ACT,4) PROMEDIO_ART_ACT, ROUND(i.MontoPromXFactAct,4) MontoPromXFactAct, " +
                                                "ROUND(i.PROD_FACT_3,4) PROD_FACT_3, ROUND(i.PROD_FACT_Act,4) PROD_FACT_Act " +
                                                ", m.MontoVenta MetaMontoVenta, m.NumItemFac MetaNumItemFac, m.MontoXFact MetaMontoXFact, m.PromItemFac MetaPromItemFac " +
                                                "FROM INDICADORES3 i INNER JOIN METAS_POR_CLIENTE m ON i.CODCLIENTE=m.CodCliente WHERE i.CODCLIENTE='"+CodCliente+"';", null);
            if(cursor.getCount() > 0)
            {
                cursor.moveToFirst();
                while(!cursor.isAfterLast())
                {
                    rPromedios3[0] = (String) (cursor.isNull(0)? CodCliente: cursor.getString(cursor.getColumnIndex("VENTAS_3")));
                    rPromedios3[1] = (String) (cursor.isNull(1)? "": cursor.getString(cursor.getColumnIndex("NUM_ART_FAC_3")));
                    rPromedios3[2] = (String) (cursor.isNull(2)? "0.00": cursor.getString(cursor.getColumnIndex("PROMEDIO_ART_3")));
                    rPromedios3[3] = (String) (cursor.isNull(3)? "0.00": cursor.getString(cursor.getColumnIndex("MontoPromXFac_3")));
                    rPromedios3[4] = (String) (cursor.isNull(4)? CodCliente: cursor.getString(cursor.getColumnIndex("VENTAS_Act")));
                    rPromedios3[5] = (String) (cursor.isNull(5)? "": cursor.getString(cursor.getColumnIndex("NUM_ART_FAC_Act")));
                    rPromedios3[6] = (String) (cursor.isNull(6)? "0.00": cursor.getString(cursor.getColumnIndex("PROMEDIO_ART_ACT")));
                    rPromedios3[7] = (String) (cursor.isNull(7)? "0.00": cursor.getString(cursor.getColumnIndex("MontoPromXFactAct")));
                    rPromedios3[8] = (String) (cursor.isNull(8)? "0.00": cursor.getString(cursor.getColumnIndex("PROD_FACT_3")));
                    rPromedios3[9] = (String) (cursor.isNull(9)? "0.00": cursor.getString(cursor.getColumnIndex("PROD_FACT_Act")));

                    rPromedios3[10] = (String) (cursor.isNull(10)? "0.00": cursor.getString(cursor.getColumnIndex("MetaMontoVenta")));
                    rPromedios3[11] = (String) (cursor.isNull(11)? "0.00": cursor.getString(cursor.getColumnIndex("MetaNumItemFac")));
                    rPromedios3[12] = (String) (cursor.isNull(12)? "0.00": cursor.getString(cursor.getColumnIndex("MetaMontoXFact")));
                    rPromedios3[13] = (String) (cursor.isNull(13)? "0.00": cursor.getString(cursor.getColumnIndex("MetaPromItemFac")));

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
                    saveLead(new Cliente(cursor.getString(cursor.getColumnIndex("NOMBRE")),cursor.getString(cursor.getColumnIndex("CLIENTE")), cursor.getString(cursor.getColumnIndex("DIRECCION")), "", R.drawable.icon));

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
