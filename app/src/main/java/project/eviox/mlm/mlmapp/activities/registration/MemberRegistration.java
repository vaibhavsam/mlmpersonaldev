package project.eviox.mlm.mlmapp.activities.registration;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

import project.eviox.mlm.mlmapp.R;
import project.eviox.mlm.mlmapp.Validations.Validations;
import project.eviox.mlm.mlmapp.constants.Constants;
import project.eviox.mlm.mlmapp.epinGenration.EpinGenrator;
import project.eviox.mlm.mlmapp.webservice.DataTest;

public class MemberRegistration extends AppCompatActivity {
    private TextView Level_TV;
    private String epin_id,epin;
    private EditText nameET,userET,sponserET,mailET,addressET,pincodeET,mobileET;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acivity_registration);
         epin_id = getIntent().getStringExtra("epin_id");
         epin = getIntent().getStringExtra("epin");
         String sponser_id = getIntent().getStringExtra("sponser_id");


        initComponent();

        userET.setText(Constants.reand());
        sponserET.setText(sponser_id);
    }

    private void initComponent() {
        String level = getIntent().getStringExtra("level");
        nameET = findViewById(R.id.name_ET);
        userET = findViewById(R.id.user_name_ET);
        sponserET = findViewById(R.id.sponsor_ET);
        mailET = findViewById(R.id.email_ET);
        addressET = findViewById(R.id.address_ET);
        pincodeET = findViewById(R.id.pincode_ET);
        mobileET = findViewById(R.id.mobile_ET);
        Level_TV = findViewById(R.id.level_tv);
        Level_TV.setText(level);
    }

    public void back(View view) {
        finish();
    }

    public void register(View view) {

        String timestamp = Validations.getCurrentTimeStamp();
        String member_name = nameET.getText().toString().trim();
        String user_name = userET.getText().toString().trim();
        String member_email = mailET.getText().toString().trim();
        String member_address= addressET.getText().toString().trim();
        String member_user_name = userET.getText().toString().trim();
        String sponser_id = sponserET.getText().toString().trim();
        String mobile = mobileET.getText().toString().trim();
        String pincode = pincodeET.getText().toString().trim();
        if (Validations.isValidString(timestamp,member_name, user_name,member_email,member_address,member_user_name,sponser_id,epin)) {
            DataTest dataTest = new DataTest("member_registration.php");
            JSONObject jsonObject = new JSONObject();
            String response = null;
            try {
                jsonObject.put("timestamp", timestamp);
                jsonObject.put("member_name", member_name);
                jsonObject.put("user_name", user_name);
                jsonObject.put("member_email", member_email);
                jsonObject.put("member_address", member_address);
                jsonObject.put("member_user_name", member_user_name);
                jsonObject.put("sponser_id", sponser_id);
                jsonObject.put("mobile",mobile);
                jsonObject.put("epin",epin);
                jsonObject.put("pincode",pincode);

                response = dataTest.execute(jsonObject).get();
                System.out.println("response :" + response);
                System.out.println("Response is" + response);
                assert response != null;
                if (Validations.isValidString(response) && !response.equals("failed") && !response.equals("Error :")) {
                    Toast.makeText(MemberRegistration.this, "Successfully Created", Toast.LENGTH_SHORT).show();
                    if (!response.equals("failed") && !response.equals("Error :")) {
                        // String response = dataTest.execute(json).get();
                       /* if (Validations.isValidString(response) && !response.equals("failed")) {
                            jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("result");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject re = jsonArray.getJSONObject(i);
                                String epin_id = re.getString("epin_id");
                                String epin = re.getString("e_pin");
                            //    updationOrder(epin,epin_id);
                            }
                        }*/
                    } else {
                        Toast.makeText(MemberRegistration.this, "Some thing went Wrong", Toast.LENGTH_SHORT).show();
                    }

                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }else {
            Toast.makeText(this, "Something went wrong!", Toast.LENGTH_SHORT).show();
        }
    }
}
