package com.guma.desarrollo.caronte.Activitys;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.util.Util;
import com.guma.desarrollo.caronte.AsyncHttpManager.Usuarios;
import com.guma.desarrollo.caronte.R;
import com.guma.desarrollo.core.ManagerURI;
import com.guma.desarrollo.core.SQLiteHelper;
import com.guma.desarrollo.core.Usuario;

import java.io.IOException;

public class LoginActivity extends AppCompatActivity {
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    TextView mAgente,mPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = PreferenceManager.getDefaultSharedPreferences(this).edit();

        SQLiteHelper.ExecuteSQL(ManagerURI.getDIR_DB(),this,"SELECT * FROM Usuarios");
        setTitle("INDICADORES MOVILES DE VENTAS ");

        mAgente = (TextView) findViewById(R.id.agente);
        mPassword = (TextView) findViewById(R.id.password);
        findViewById(R.id.sign_in_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String vUsuario = mAgente.getText().toString().trim();
                String vPassword = mPassword.getText().toString().trim();

                if (TextUtils.isEmpty(vUsuario) || TextUtils.isEmpty(vPassword)){
                    Toast.makeText(LoginActivity.this, "Hay Campos Vacios", Toast.LENGTH_SHORT).show();
                }else{
                    if (Usuario.leerDB(vUsuario,vPassword,ManagerURI.getDIR_DB(),LoginActivity.this)){
                        editor.putString("User",vUsuario);
                        editor.putString("Password",vPassword);
                        editor.apply();
                        startActivity(new Intent(LoginActivity.this,TableroActivity.class));
                    }else{
                        if (ManagerURI.isOnlinea(LoginActivity.this)){
                            if (Usuarios.getAsyncHttpUsuario(vUsuario,vPassword,LoginActivity.this)){
                                editor.putString("User",vUsuario);
                                editor.putString("Password",vPassword);
                                editor.apply();
                                startActivity(new Intent(LoginActivity.this,TableroActivity.class));
                            }else{
                                Toast.makeText(LoginActivity.this, "Oooopp!!...Algo Salio mal", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(LoginActivity.this, "No tiene Internet", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                finish();
            }
        });

    }
// TODO: 29/12/2016 PENDIENTE DE QUE LA FUNCION SIEMPRE INICIALICE EN FALSE



}

