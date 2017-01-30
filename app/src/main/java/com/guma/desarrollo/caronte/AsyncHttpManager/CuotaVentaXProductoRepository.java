package com.guma.desarrollo.caronte.AsyncHttpManager;

import android.app.ProgressDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.guma.desarrollo.caronte.R;
import com.guma.desarrollo.core.Cliente;
import com.guma.desarrollo.core.CuotaVentaXProducto;
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

public class CuotaVentaXProductoRepository {
    private static final String TAG = "CuotaVentaXProductoRepository";
    private HashMap<String, CuotaVentaXProducto> clents = new HashMap<>();

    private void saveLead(CuotaVentaXProducto cls) {
        clents.put(cls.getId(), cls);
    }

    public List<CuotaVentaXProducto> getCuotaVentasPorProducto(){
        List<CuotaVentaXProducto> OrderClient= new ArrayList<>(clents.values());
        Collections.sort(OrderClient, new Comparator<CuotaVentaXProducto>(){
            public int compare(CuotaVentaXProducto obj1, CuotaVentaXProducto obj2)
            {
                return obj1.getCodProducto().compareToIgnoreCase(obj2.getCodProducto());
            }
        });
        return OrderClient;
    }

    public CuotaVentaXProductoRepository(Context ctx) {
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = null;
        try
        {
            myDbHelper = new SQLiteHelper(ManagerURI.getDIR_DB(), ctx);
            myDataBase = myDbHelper.getReadableDatabase();
            Cursor cursor = myDataBase.rawQuery("SELECT * FROM CUOTA_POR_PRODUCTO ORDER BY CODPRODUCTO", null);
            if(cursor.getCount() > 0)
            {
                cursor.moveToFirst();
                while(!cursor.isAfterLast())
                {
                    //saveLead(new CuotaVentaXProducto(cursor.getString(cursor.getColumnIndex("NOMBRE")),cursor.getString(cursor.getColumnIndex("CLIENTE")), cursor.getString(cursor.getColumnIndex("DIRECCION")), "", R.drawable.icon));
                    saveLead(new CuotaVentaXProducto(cursor.getString(cursor.getColumnIndex("CODVENDEDOR")),cursor.getString(cursor.getColumnIndex("CODPRODUCTO")), cursor.getString(cursor.getColumnIndex("NOMBREPRODUCTOR")), cursor.getString(cursor.getColumnIndex("META")), R.drawable.icon));
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


    public static CuotaVentaXProductoRepository getInstance(Context ctx){
        return  new CuotaVentaXProductoRepository(ctx);

    }
}
