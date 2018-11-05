package project.eviox.mlm.mlmapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import project.eviox.mlm.mlmapp.Validations.Validations;
import project.eviox.mlm.mlmapp.activities.LoginActivity;
import project.eviox.mlm.mlmapp.constants.Constants;

public class SplashScreen extends AppCompatActivity {

 //   private static final int COARSE_LOCATION_SERVICE_REQUEST_CODE = 2;
  //  private static final int FINE_LOCATION_SERVICE_REQUEST_CODE = 3;
   // private static final int LOCATION_SERVICE_REQUEST_CODE = 4;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actvity_splash);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sp = getSharedPreferences(Constants.SP_NAME, MODE_PRIVATE);
                String member_name = sp.getString(Constants.SP_MEMBER_NAME, null);
                String password = sp.getString(Constants.SP_PASSWORD, null);
                String user_id = sp.getString(Constants.SP_USER_ID,null);
                String  user_name = sp.getString(Constants.SP_USER_NAME,null);
                String  user_contact = sp.getString(Constants.SP_USER_CONTACT,null);
                //System.out.println(address);
                 //  String user_contact = sp.getString(Constants.SP_USER_CONTACT,null);
                String user_registration = sp.getString(Constants.SP_REGISTRATION,null);
                if (Validations.isValidString(member_name,password,user_id,user_name,user_contact)) {
                    Intent i = new Intent(SplashScreen.this, MainActivity.class);
                    i.putExtra("user_id",user_id);
                    i.putExtra("member_name",member_name);
                    i.putExtra("user_name", user_name);
                    startActivity(i);
                    finish();
                } else {
                    Intent i = new Intent(SplashScreen.this, LoginActivity.class);
                    startActivity(i);
                    finish();
                }

              /*  Intent i = new Intent(SplashScreen.this, LoginActivity.class);
                startActivity(i);
                finish();*/
            }
        }, 3000);
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();

    }
}
