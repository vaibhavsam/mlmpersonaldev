package project.eviox.mlm.mlmapp.webservice;

import retrofit2.Call;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RetroApi {

    @Headers("Content-Type: application/json")
    @POST("/json/loginMlm.php/")
    Call<String> login(@Query("email") String email,
                       @Query("password") String password);
}
