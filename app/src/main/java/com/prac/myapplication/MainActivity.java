package com.prac.myapplication;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "LocationMainActivity";
    private static final int ERROR_DIALOG_REQUEST = 9001;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (isServicesOK()){
            init();
        }
    }


    private void init(){
        Button btMap = (Button)findViewById(R.id.btmap);
        btMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent map_intent = new Intent(MainActivity.this,LocationMainActivity.class);
                startActivity(map_intent);

            }

        });
    }
    public boolean isServicesOK(){
        Log.d(TAG,"isServicesOK: checking google services version");
        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(MainActivity.this);
        if(available == ConnectionResult.SUCCESS){
            //everything is fine
            Log.d(TAG,"isServicesOK:google play Services is working ");
            return true;
        }else if (GoogleApiAvailability.getInstance().isUserResolvableError(available)){
            //an error occured but we can fix it
            Log.d(TAG,"isServicesOK:an error occured but we can fix it"   );
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(MainActivity.this,available,ERROR_DIALOG_REQUEST);
            dialog.show();
        }else {
            Toast.makeText(this, "you cnt make map request",Toast.LENGTH_SHORT).show();

        }
        return false;
    }

}
