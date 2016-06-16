package com.becon.talon.testzinit.rest;

import com.becon.talon.testzinit.utils.UrlBuilder;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by anton on 29.05.16.
 */
public class RestClientV2 {
    private Rest rest;

    public RestClientV2() {

        Retrofit retrofit;
        Gson gson;
            gson = new GsonBuilder()
                    .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                    .create();

            retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .baseUrl(UrlBuilder.MAIN_URL_BODY)
                    .build();

        rest = retrofit.create(Rest.class);
    }
    public Rest getApiService()
    {
        return rest;
    }
    public Gson getGson() {
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
        return gson;
    }
}
