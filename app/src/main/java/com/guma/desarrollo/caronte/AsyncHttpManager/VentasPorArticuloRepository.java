package com.guma.desarrollo.caronte.AsyncHttpManager;

import android.app.ProgressDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.guma.desarrollo.caronte.Adapters.VentasPorClientesAdapter;
import com.guma.desarrollo.caronte.R;
import com.guma.desarrollo.core.Cliente;
import com.guma.desarrollo.core.ManagerURI;
import com.guma.desarrollo.core.SQLiteHelper;
import com.guma.desarrollo.core.VentaPorArticulo;
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
 * Created by luis.perez on 16/01/2017.
 */

public class VentasPorArticuloRepository {
    private HashMap<String, VentaPorArticulo> vclents = new HashMap<>();

    public VentasPorArticuloRepository(Context ctx) {
        SQLiteDatabase mydb = null;
        SQLiteHelper mydbh = null;
        try
        {
            mydbh = new SQLiteHelper(ManagerURI.getDIR_DB(), ctx);
            mydb = mydbh.getReadableDatabase();
            Cursor cursor = mydb.rawQuery("SELECT d.CODIGO, d.ARTICULO, d.DESCRIPCION, SUM(d.VENTA) TOTAL " +
                   "FROM DETALLE_FACTURA_PUNTOS d GROUP BY d.CODIGO, d.ARTICULO, d.DESCRIPCION ORDER BY d.DESCRIPCION;", null);
            if (cursor.getCount()>0)
            {
                cursor.moveToFirst();
                while (!cursor.isAfterLast())
                {
                    saveVentasPorArticulo(new VentaPorArticulo(cursor.getString(cursor.getColumnIndex("ARTICULO")),cursor.getString(cursor.getColumnIndex("DESCRIPCION")),cursor.getString(cursor.getColumnIndex("TOTAL")),R.drawable.icon));
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

    private void saveVentasPorArticulo(VentaPorArticulo vcls){
        vclents.put(vcls.getmID(), vcls);
    }

    public static VentasPorArticuloRepository getInstance (Context ctx){
        return new VentasPorArticuloRepository(ctx);
    }

    public List<VentaPorArticulo> getVentasPorArticulo(){
        return new ArrayList<>(vclents.values());
    }

}
