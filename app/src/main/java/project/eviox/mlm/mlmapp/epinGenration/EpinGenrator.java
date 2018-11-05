package project.eviox.mlm.mlmapp.epinGenration;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;
import java.util.concurrent.ExecutionException;

import project.eviox.mlm.mlmapp.R;
import project.eviox.mlm.mlmapp.Validations.Validations;
import project.eviox.mlm.mlmapp.activities.registration.MemberRegistration;
import project.eviox.mlm.mlmapp.constants.Constants;
import project.eviox.mlm.mlmapp.webservice.DataTest;

public class EpinGenrator extends AppCompatActivity {
    private Spinner productSpinner;
    private TextView amountTextView;
    private Button Genratebutton;
    ArrayAdapter<String> adapter;
    String sponsorId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_epin_genration);
        inputInstallation();
        initSpinner();

        sponsorId = getIntent().getStringExtra("user_id");
        String user_name = getIntent().getStringExtra("user_name");
        String member_name = getIntent().getStringExtra("member_name");


        productSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
                String product = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void inputInstallation() {
        productSpinner = findViewById(R.id.spinner_id);
        amountTextView = findViewById(R.id.planView_tv);

    }

    public void back(View view) {
        finish();
    }

    private void initSpinner() {
        adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, Constants.drop_down_for_plans);
        productSpinner.setAdapter(adapter);
    }

    public void genrate_epin(View view) {

        String timestamp = Validations.getCurrentTimeStamp();
        String product = productSpinner.getSelectedItem().toString().trim();
        // String e_pin = timestamp.concat(product);
        if (Validations.isValidString(timestamp, product, sponsorId)) {
            DataTest dataTest = new DataTest("e-pinGenaration.php");
            JSONObject jsonObject = new JSONObject();
            String response = null;
            try {
                jsonObject.put("timestamp", timestamp);
                jsonObject.put("product", product);
                jsonObject.put("sponsorId", sponsorId);
                response = dataTest.execute(jsonObject).get();
                System.out.println("response :" + response);
                System.out.println("Response is" + response);
                assert response != null;
                if (Validations.isValidString(response) && !response.equals("failed") && !response.equals("Error :")) {
                    Toast.makeText(EpinGenrator.this, "Successfully Created", Toast.LENGTH_SHORT).show();
                    if (!response.equals("failed") && !response.equals("Error :")) {
                        // String response = dataTest.execute(json).get();
                        if (Validations.isValidString(response) && !response.equals("failed")) {
                            jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("result");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject re = jsonArray.getJSONObject(i);
                                String epin_id = re.getString("epin_id");
                                String epin = re.getString("e_pin");
                                updationOrder(epin,epin_id);
                            }
                        }
                    } else {
                        Toast.makeText(EpinGenrator.this, "Some thing went Wrong", Toast.LENGTH_SHORT).show();
                    }

                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

    }

    private void updationOrder(String epin, String epin_id) {
        System.out.println("epin"+epin);
        amountTextView.setText(epin);
        Toast.makeText(EpinGenrator.this, "Now Create downlink User!", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(EpinGenrator.this, MemberRegistration.class);
        i.putExtra("epin_id",epin_id);
        i.putExtra("epin",epin);
        i.putExtra("sponser_id",sponsorId);
        startActivity(i);
        finish();
    }

}