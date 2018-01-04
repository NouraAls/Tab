package com.apps.noura.tab;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Noura Alsomaikhi on 12/28/2017.
 */

public class Tab2Fragment extends Fragment {
    private static final String TAG = "ListFrag";
    private Button B2;

    //create Retrofit instance
    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl("http://mosquesapi.azurewebsites.net/")
            .addConverterFactory(GsonConverterFactory.create());
    public static Retrofit retrofit = builder.build();

    DawaClient dawaClient =retrofit.create(DawaClient.class);

    String lat = "24.000.6";
    String lon = "46.000";
    private List<DawaLatLng> dawaLatLngs;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.tab2_fragment,container, false );

        B2 =(Button) view.findViewById(R.id.btn2);
        /*
        B2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"B2", Toast.LENGTH_SHORT).show();
            }
        });
*/
        //Load list
        B2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDawainfo(lat,lon);
            }
        });

        return view;

    }

    private void getDawainfo(String lat, String lon){
        final int limit = 3;
       // System.out.println(etUsername);
        // Login login = new Login(option,etUsername,etPassword);

        // Calling interface
        // Call<JsonObject> call =userClient.login(option,etUsername,etPassword);
        Call<List<DawaLatLng>> call =dawaClient.getDawaInfo(lat,lon,limit);
        call.enqueue(new Callback<List<DawaLatLng>>() {
            @Override
            public void onResponse(Call<List<DawaLatLng>> call, Response<List<DawaLatLng>> response) {


                    System.out.println(response.body());
                System.out.println(response.body().toString());
                   // System.out.println(String.valueOf(response.body()));
                System.out.println(String.valueOf(response.body()));
                   // JSONObject InfoResponse = new JSONObject(String.valueOf(response.body()));
                  //  String usernameR = InfoResponse.getString("DawaAddress");
                  //  String id = InfoResponse.getString("Id");
                  //  System.out.println("Dawa Activity name " + usernameR);
                   // System.out.println("Dawa Activity ID " + id);
                    //  Toast.makeText(LoginActivity.this, "Welcome" + usernameR, Toast.LENGTH_SHORT).show();
                    //2

                       // String jsonData = response.body().toString();
                        //JSONObject Jobject= new JSONObject(jsonData);
                       // String usernameR2  =  "Name:" + Jobject.get("DawaAddress");
                       // System.out.println(usernameR2);


            }

                @Override
                public void onFailure (Call <List<DawaLatLng>> call, Throwable t){
                    System.out.println("Failure ");
                }
        });
    }


}
