package com.example.ios_razrab.changeapi;

import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.DhcpInfo;
import android.net.NetworkInfo;
import android.net.ProxyInfo;
import android.net.VpnService;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.util.Log;
import android.widget.TextView;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Intent intent = VpnService.prepare(getApplicationContext());
        if (intent != null) {
            startActivityForResult(intent, 0);
        } else {
            onActivityResult(0, RESULT_OK, null);
        }



        // выводит ip адрес устройства
        textView = (TextView) findViewById(R.id.android_device_ip_address);
       // WifiManager wifiManager2 = (WifiManager) getSystemService(WIFI_SERVICE);

       // String ipAddress = Formatter.formatIpAddress(wifiManager2.getConnectionInfo().getIpAddress());

        Utils.getMACAddress("wlan0");
        Utils.getMACAddress("eth0");
        Utils.getIPAddress(true); // IPv4
        Utils.getIPAddress(false); // IPv6

        textView.setText("Your Device IP Address: " +  Utils.getIPAddress(true));


    }




    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            Intent intent = new Intent(this, MyVpnService.class);
            startService(intent);
        }
    }


}