package com.diginori.sscc_s.util;

import android.app.Activity;
import android.net.DhcpInfo;
import android.net.wifi.WifiManager;
import android.text.format.Formatter;

public class WifiMan extends Activity{
    public  String getWifiIp(){
        WifiManager wm = (WifiManager) getSystemService(WIFI_SERVICE);
        return Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());
    }

    public String getWifiGatewayIp(){
        WifiManager wm = (WifiManager) getSystemService(WIFI_SERVICE);
        DhcpInfo dhcpInfo = wm.getDhcpInfo() ;
        int serverIP = dhcpInfo.gateway;

        String ipAddress = String.format(
                "%d.%d.%d.%d",
                (serverIP & 0xff),
                (serverIP >> 8 & 0xff),
                (serverIP >> 16 & 0xff),
                (serverIP >> 24 & 0xff));

        return ipAddress;
    }
}
