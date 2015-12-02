package com.diginori.sscc_s;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void mOnClick(View v){

        Intent i;
        switch(v.getId()){
            case R.id.btn_server: //서버 화면
                i= new Intent(this, ServerActivity.class);
                startActivity(i);
                break;
            case R.id.btn_client: //클라이언트 화면
                i= new Intent(this, ClientActivity.class);
                startActivity(i);
                break;
            case R.id.btn_debug: //클라이언트 화면
                i= new Intent(this, DebugAcvivity.class);
                startActivity(i);
                break;
        }
    }
}
