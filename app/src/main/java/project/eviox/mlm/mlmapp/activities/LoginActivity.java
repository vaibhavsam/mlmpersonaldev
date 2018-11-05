package project.eviox.mlm.mlmapp.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

import project.eviox.mlm.mlmapp.MainActivity;
import project.eviox.mlm.mlmapp.R;
import project.eviox.mlm.mlmapp.Validations.Validations;
import project.eviox.mlm.mlmapp.constants.Constants;
import project.eviox.mlm.mlmapp.webservice.DataTest;
import project.eviox.mlm.mlmapp.webservice.RetroApi;
import project.eviox.mlm.mlmapp.webservice.RetroApp;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class LoginActivity extends AppCompatActivity  {
    private EditText email_edt,password_edt;
    private Button loginButton;
    TextView forgot_tv;
    ProgressDialog  progress;
    RetroApi retroApi;
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        
        initComponent();
    }

    private void initComponent() {
        email_edt = findViewById(R.id.userIdET);
        password_edt = findViewById(R.id.passwordET);
        loginButton = findViewById(R.id.login_B);

      //  initialize();


    }



    public void loginB(View view) {

        String email = email_edt.getText().toString().trim();
        String password = password_edt.getText().toString().trim();

        if (Validations.isValidString(email, password)) {
            System.out.println("user email : " + email);

            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("email", email);
                jsonObject.put("password", password);
                DataTest dataTest = new DataTest("user_login.php");
                String response = dataTest.execute(jsonObject).get();
                System.out.println("response"+response);
                jsonObject = new JSONObject(response);
                if (Validations.isValidString(response) && !response.equals("failed") && !response.equalsIgnoreCase("success")){
                    System.out.println(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("result");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject re = jsonArray.getJSONObject(i);
                        String user_name = re.getString("user_name");
                        String user_id = re.getString("member_id");
                       // String user_email = re.getString("user_email");
                        String member_name = re.getString("member_name");
                        String sponser_id = re.getString("sponser_id");
                        String user_password = re.getString("user_password");
                        String member_mobile = re.getString("member_mobile");
                     //   String user_contact = re.getString("user_contact");
                        String user_registration_date = re.getString("member_registration");
                        System.out.println(user_password);
                     //   String user_name = (user_Fname+" "+user_Lname);

                        if (Validations.isValidString(user_name ,user_id,member_name,sponser_id,member_mobile)) {

                            SharedPreferences sp = getSharedPreferences(Constants.SP_NAME, MODE_PRIVATE);
                            SharedPreferences.Editor editor = sp.edit();
                            //editor.putString(Constants.SP_EMPEMAIL,user_email);
                            System.out.println(email);
                            editor.putString(Constants.SP_USER_CONTACT,member_mobile);
                            editor.putString(Constants.SP_USER_NAME, user_name);
                            editor.putString(Constants.SP_PASSWORD, user_password);
                            editor.putString(Constants.SP_USER_ID,user_id);
                            editor.putString(Constants.SP_MEMBER_NAME,member_name);
                            System.out.println(user_id);
                          //  editor.putString(Constants.SP_REGISTRATION,user_position);
                            //System.out.println(" user_address " + user_address);
                            editor.putString(Constants.SP_REGISTRATION,user_registration_date);
                            System.out.println(user_registration_date);
                            editor.apply();
                            editor.commit();
                            Intent o = new Intent(this,MainActivity.class);
                            o.putExtra("user_id", user_id);
                            o.putExtra("member_name",member_name);
                            o.putExtra("user_name",user_name);
                            //   i.putExtra("shop_name",user_name);
                            startActivity(o);
                            finish();
                        } else {
                            //  Snack.("");
                            Toast.makeText(this, "Data not found", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    //pd.dismiss();
                    //  snack("");
                    Toast.makeText(this, "Invalid Useremail or password", Toast.LENGTH_SHORT).show();
                }


            } catch (InterruptedException | JSONException | ExecutionException e) {
                e.printStackTrace();
            }
        }else{
            //  snack("");

            Toast.makeText(this, "Field Should not be empty", Toast.LENGTH_SHORT).show();
        }



    }


}
