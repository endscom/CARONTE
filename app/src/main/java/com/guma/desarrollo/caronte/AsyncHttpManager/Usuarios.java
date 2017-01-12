package com.guma.desarrollo.caronte.AsyncHttpManager;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.guma.desarrollo.core.ManagerURI;
import com.guma.desarrollo.core.SQLiteHelper;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * Created by maryan.espinoza on 29/12/2016.
 */

public class Usuarios {
    static boolean OK=false;


    public static Boolean getAsyncHttpUsuario(final String User, final String Password, final Context cxnt){

        AsyncHttpClient getData = new AsyncHttpClient();
        RequestParams parametros = new RequestParams();
        parametros.put("V",User);
        parametros.put("P",Password);

        getData.get(ManagerURI.getURL_VENDEDOR(), parametros, new AsyncHttpResponseHandler() {
            public ProgressDialog pdialog;
            private SharedPreferences preferences;
            private SharedPreferences.Editor editor;

            @Override
            public void onStart() {
                pdialog = ProgressDialog.show(cxnt, "","Procesando. Porfavor Espere...", true);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    preferences = PreferenceManager.getDefaultSharedPreferences(cxnt);
                    editor = PreferenceManager.getDefaultSharedPreferences(cxnt).edit();

                    JSONObject jsonObject = new JSONObject(new String(responseBody));
                    String Qry = "INSERT INTO Usuarios (IdVendedor, NombreUsuario, Credencial, Password, PerfilSupervisor) VALUES('"+jsonObject.get("VENDEDOR").toString()+"', '"+jsonObject.get("NOMBRE").toString()+"', '"+User+"', '"+Password+"', '"+jsonObject.get("ROL").toString()+"')";
                    SQLiteHelper.ExecuteSQL(ManagerURI.getDIR_DB(),cxnt,"DELETE FROM Usuarios;");
                    SQLiteHelper.ExecuteSQL(ManagerURI.getDIR_DB(),cxnt,Qry);

                    editor.putString("User",User);
                    editor.putString("Password",Password);
                    editor.putString("Rol",jsonObject.get("ROL").toString());
                    editor.apply();

                    Log.d("", "onSuccess: " + Qry);

                    OK = !OK;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                pdialog.dismiss();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                pdialog.dismiss();
            }
        });

        return OK;


    }
}
