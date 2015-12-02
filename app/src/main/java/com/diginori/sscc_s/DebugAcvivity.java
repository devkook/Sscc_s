package com.diginori.sscc_s;

import android.app.Activity;
import android.os.Bundle;
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
            tv.append("onDown:"+Calendar.getInstance().getTimeInMillis() + "\n");
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
            Log.w("TEST", "onDoubleTap = "+ev.toString());
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
