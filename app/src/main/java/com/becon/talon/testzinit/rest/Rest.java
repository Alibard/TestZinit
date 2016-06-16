package com.becon.talon.testzinit.rest;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Админ on 16.06.2016.
 */
public interface Rest {

//    "
    @GET("1/pictures.getTheBest.json/{client}")
    Call<ArrayList<DataModel>>  getData(@Query("client") String client);

}
