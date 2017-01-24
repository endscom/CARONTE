package com.guma.desarrollo.caronte.AsyncHttpManager;

import android.app.ProgressDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.guma.desarrollo.caronte.Activitys.NumArticulosPorCliente;
import com.guma.desarrollo.caronte.Adapters.VentasPorClientesAdapter;
import com.guma.desarrollo.caronte.R;
import com.guma.desarrollo.core.Cliente;
import com.guma.desarrollo.core.ManagerURI;
import com.guma.desarrollo.core.NumArticuloPorCliente;
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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import cz.msebera.android.httpclient.Header;
/**
 * Created by luis.perez on 17/01/2017.
 */


public class NumArticulosPorClienteRepository {
    private HashMap<String, NumArticuloPorCliente> vclents = new HashMap<>();

    private void saveNumArticuloPorCliente(NumArticuloPorCliente vcls){

        vclents.put(vcls.getmID(), vcls);
    }

    public NumArticulosPorClienteRepository(Context ctx){
        SQLiteDatabase mydb = null;
        SQLiteHelper mydbh = null;
        try
        {
            mydbh = new SQLiteHelper(ManagerURI.getDIR_DB(), ctx);
            mydb = mydbh.getReadableDatabase();
            Cursor cursor = mydb.rawQuery("SELECT d.CODCLIENTE, c.NOMBRE, COUNT(DISTINCT ARTICULO) ARTICULOS " +
                   "FROM DETALLE_FACTURA_PUNTOS d INNER JOIN CLIENTES c ON d.CODCLIENTE=c.CLIENTE " +
                   "GROUP BY d.CODCLIENTE ORDER BY 3 DESC, d.CODCLIENTE, c.NOMBRE;", null);
            if (cursor.getCount()>0)
            {
                cursor.moveToFirst();
                while (!cursor.isAfterLast())
                {
                    saveNumArticuloPorCliente(new NumArticuloPorCliente(cursor.getString(cursor.getColumnIndex("CODCLIENTE")),cursor.getString(cursor.getColumnIndex("NOMBRE")),cursor.getString(cursor.getColumnIndex("ARTICULOS")),R.drawable.icon));
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

    public static NumArticulosPorClienteRepository getInstance (Context ctx){
        return new NumArticulosPorClienteRepository(ctx);
    }

    public List<NumArticuloPorCliente> getNumArticulosPorCliente(){
        //return new ArrayList<>(vclents.values());
        return new ArrayList<>(vclents.values());
    }
}
