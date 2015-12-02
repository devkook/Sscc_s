package com.diginori.sscc_s;

import android.app.Activity;
import android.net.DhcpInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.text.format.Formatter;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.TextView;

import java.util.Calendar;

public class DebugAcvivity extends Activity {

    private TextView tv;
    private GestureDetector mGestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debug);

        tv = (TextView) findViewById(R.id.tv_debug);

        mGestureDetector = new GestureDetector(this, TestGestureListener);
    }

    private String getWifiGatewayIp(){
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


    @Override
    // 감시할 모션이벤트를 onTouchEvent에 넣어주면, GestureListener가 호출된다.
    public boolean onTouchEvent(MotionEvent event) {
        return mGestureDetector.onTouchEvent(event);
    }

    // SimpleOnGestureListener callback
    GestureDetector.SimpleOnGestureListener TestGestureListener = new GestureDetector.SimpleOnGestureListener() {

        @Override
        public boolean onDown(MotionEvent ev) {
            Log.w("TEST", "onDown = " + ev.toString());
            tv.append("=========================================\n");
            tv.append("onDown:"+Calendar.getInstance().getTimeInMillis() + "\n");

            WifiManager wm = (WifiManager) getSystemService(WIFI_SERVICE);

            WifiInfo wifiInfo = wm.getConnectionInfo();

            tv.append("wifiMyIP:" + Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress())+"\n");
            tv.append("wifiInfo:" + wifiInfo.toString()+"\n");
            tv.append("SSID:" + wifiInfo.getSSID()+"\n");
            tv.append("gateway:" + getWifiGatewayIp()+"\n");
            tv.append("getDhcpInfo:" + wm.getDhcpInfo().toString() + "\n");
            tv.append("getWifiState:" + wm.getWifiState() + "\n");

            Log.d("wifiInfo", wifiInfo.toString());
            Log.d("SSID",wifiInfo.getSSID());

            tv.append("TelephonyManager+++++++++++++++++++\n");
            TelephonyManager tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
            GsmCellLocation location = (GsmCellLocation) tm.getCellLocation();

            tv.append("getCid:" + location.getCid() + "\n");
            tv.append("getLac:" + location.getLac() + "\n");
            tv.append("getPsc:" + location.getPsc() + "\n");
            tv.append("location:" + location.toString() + "\n");
            tv.append("TelephonyManager:" + tm.toString() + "\n");

            return true;
        }

        @Override
        public boolean onSingleTapUp(MotionEvent ev) {
            Log.w("TEST", "onSingleTapUp = "+ev.toString());
            return true;
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent ev) {
            Log.w("TEST", "onSingleTapConfirmed = "+ev.toString());
            return true;
        }

        @Override
        public boolean onDoubleTap(MotionEvent ev) {
            Log.w("TEST", "onDoubleTap = " + ev.toString());
            tv.setText("onDoubleTap\n");
            return true;
        }

        @Override
        public boolean onDoubleTapEvent(MotionEvent ev) {
            Log.w("TEST", "onDoubleTapEvent = " + ev.toString());
            tv.setText("onDoubleTapEvent\n");
            return true;
        }

        @Override
        public void onLongPress(MotionEvent ev) {
            Log.w("TEST", "onLongPress = "+ev.toString());
        }

        @Override
        public void onShowPress(MotionEvent ev) {
            Log.w("TEST", "onShowPress = "+ev.toString());
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            Log.w("TEST", "onScroll / e1 = "+e1.toString());
            Log.w("TEST", "onScroll / e2 = "+e2.toString());
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            Log.w("TEST", "onFling / e1 = "+e1.toString());
            Log.w("TEST", "onFling / e2 = "+e2.toString());
            return true;
        }

    };
}
