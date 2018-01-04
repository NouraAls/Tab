package com.apps.noura.tab;

import com.google.gson.JsonObject;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Administrator on 1/3/2018.
 */

public interface DawaClient {

    @GET("mosques/DawaActivity.jsp?")
        // Call<JsonObject> getDawaInfo (@Query("lat") String latitude, @Query("lon") String longitude );
    Call<List<DawaLatLng>>
    getDawaInfo(@Query("lat") String lat , @Query("lon") String lon, @Query("limit") int limit);
}
