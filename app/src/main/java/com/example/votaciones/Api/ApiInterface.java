package com.example.votaciones.Api;

import com.example.votaciones.Model.VotacionesModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("Estudiantes")
    Call<List<VotacionesModel>> GetVotaciones();

    /*@POST("mercados")
    Call<MarketModel> postMarket(@Body MarketModel marketModel);

    @PUT("mercados/{id}")
    Call<MarketModel> update(@Path("id") String id,@Body MarketModel marketModel);

    @DELETE("mercados/{id}")
    Call<MarketModel> deleteMarket(@Path("id")String id);*/
}
