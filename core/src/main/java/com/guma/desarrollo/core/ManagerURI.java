package com.guma.desarrollo.core;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;

import java.io.File;

/**
 * Created by maryan.espinoza on 29/12/2016.
 */

public class ManagerURI {
    private static String SERVER = "192.168.1.78";
    //private static String SERVER = "165.98.75.219:8448";/*IP PUBLICO*/
    //private static String SERVER = "192.168.1.155:8080";/*LOCAL LP*/

    private static String URL_CLENTES= "http://"+ SERVER + "/rest_app_venta/CLIENTES.php";
    private static String URL_VENDEDOR="http://"+ SERVER + "/rest_app_venta/VENDEDORES.php";
    private static String URL_FACTURAS="http://"+ SERVER + "/rest_app_venta/FACTURAS.php";
    private static String URL_PORRECUPERAR="http://"+ SERVER + "/rest_app_venta/PorRecuperar.php";

    private static String URL_ExecuteSQL="http://"+ SERVER + "/rest_app_venta/ExecuteSQL.php";

    private static String DIR_DB = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath() + File.separator;
    //private static String DIR_DB = "com.guma.desarrollo.caronte" + File.separator;

    public static String getURL_CLENTES() { return URL_CLENTES; }

    public static String getURL_FACTURAS() { return URL_FACTURAS; }

    public static String getURL_PORRECUPERAR() { return URL_PORRECUPERAR; }

    public static String getURL_VENDEDOR() { return  URL_VENDEDOR; }

    public static String getDIR_DB() { return  DIR_DB; }

    public static String getExecuteSQL() { return  URL_ExecuteSQL; }

    public static boolean isOnlinea(Context cxt){
        ConnectivityManager cm = (ConnectivityManager) cxt.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnectedOrConnecting()){
            return true;
        }else {
            return false;

        }
    }

}
