package com.example.votaciones.Api;

import com.example.votaciones.Model.CandidatoModel;
import com.example.votaciones.Model.UsuariosModel;
import com.example.votaciones.Model.VotacionesModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiInterface {
    @GET("votaciones")
    Call<List<VotacionesModel>> GetVotaciones();

    @GET("usuarios")
    Call<List<UsuariosModel>> GetUser();

    @GET("estudiantes/{ID}")
    Call<CandidatoModel> GetCandidatos(@Path("ID") String id);

    @POST("usuarios")
    Call<UsuariosModel> Login(@Body UsuariosModel usuariosModel);

   /* @PUT("mercados/{id}")
    Call<MarketModel> update(@Path("id") String id,@Body MarketModel marketModel);

    @DELETE("mercados/{id}")
    Call<MarketModel> deleteMarket(@Path("id")String id);*/
}
