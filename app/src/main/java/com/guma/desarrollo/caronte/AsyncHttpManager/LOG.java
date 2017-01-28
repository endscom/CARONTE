package com.guma.desarrollo.caronte.AsyncHttpManager;

import android.app.ProgressDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.guma.desarrollo.core.ManagerURI;
import com.guma.desarrollo.core.SQLiteHelper;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * Created by maryan.espinoza on 25/01/2017.
 */

public class LOG {
    public static void AsyncServidor(final Context ctx) {
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = null;
        AsyncHttpClient getData = new AsyncHttpClient();
        RequestParams parametros = new RequestParams();
        String SQL ="";
        try
        {
            myDbHelper = new SQLiteHelper(ManagerURI.getDIR_DB(), ctx);
            myDataBase = myDbHelper.getReadableDatabase();
            Cursor cursor = myDataBase.rawQuery("SELECT * FROM LOG", null);
            if(cursor.getCount() > 0){
                cursor.moveToFirst();
                while(!cursor.isAfterLast()){
                    SQL += "" +
                            "(" + '"' +cursor.getString(cursor.getColumnIndex("Usuario"))+ '"' + ", " +
                            ""+ '"'  + cursor.getString(cursor.getColumnIndex("Fecha"))+'"' + ", " +
                            ""+'"' +cursor.getString(cursor.getColumnIndex("Modulo"))+'"'+", " +
                            ""+'"'+cursor.getString(cursor.getColumnIndex("Accion"))+'"'+"),";
                            cursor.moveToNext();
                }
                SQL = SQL.substring(0, SQL.length()-1);
                parametros.put("SQL",SQL);
                getData.get(ManagerURI.getExecuteSQL(), parametros, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        try
                        {
                            JSONObject jsonObject = new JSONObject(new String(responseBody));
                            if (jsonObject.get("Execute").toString()=="1")
                            {
                                SQLiteHelper.ExecuteSQL(ManagerURI.getDIR_DB(),ctx,"DELETE FROM LOG;");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        Log.d("", "onFailure: ");
                    }
                });

            }else{
                Log.d("", "AsyncServidor: SIN DATOS EN LOG");
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
