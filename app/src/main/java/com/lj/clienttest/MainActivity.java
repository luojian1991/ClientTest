package com.lj.clienttest;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.nfc.Tag;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.lj.servicetest.MyAIDLService;

public class MainActivity extends AppCompatActivity {
    private final  static String TAG ="MainActivity";
    private MyAIDLService myAIDLService;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myAIDLService = MyAIDLService.Stub.asInterface(service);
            try {
                int sum = myAIDLService.plus(3,5);
                String upperStr = myAIDLService.toUpperCase("helloworld");
                Log.e(TAG,"sum is " +sum );
                Log.e(TAG,"upperStr is" +upperStr);

            }catch (Exception e){
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnBindService = findViewById(R.id.btn_bind_service);
        btnBindService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.lj.servicetest.MyAIDLService");
                bindService(intent,connection,BIND_AUTO_CREATE);
            }
        });
    }
}
