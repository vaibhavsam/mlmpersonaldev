package project.eviox.mlm.mlmapp.webservice;

import android.os.AsyncTask;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONObject;

import java.io.IOException;

public class DataTest extends AsyncTask<JSONObject,Object,String> {
    private String phpPage;

    public DataTest(String phpPage) {
        this.phpPage = phpPage;
    }
    @Override
    protected String doInBackground(JSONObject... params) {

        final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        JSONObject jsonObject = params[0];
        ServerIP serverIP = new ServerIP();
        String ip = serverIP.getIp();

        String responseString = null;
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON, jsonObject.toString());
        Request request = new Request.Builder().url("http://" + ip + "/" + phpPage).post(body).build();

        try {
            Response response = client.newCall(request).execute();
            responseString = response.body().string();
            System.out.println("response : "+responseString);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseString;
    }
}
