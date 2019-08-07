package com.example.votaciones.Model;

import com.example.votaciones.Api.Api;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RespaldoListas {

    private static RespaldoListas bd = null;
    private List<UsuariosModel> listaUsuarios;
    private List<VotacionesModel> listaVotaciones;
    private List<CandidatoModel> listaCandidatos;
    private List<EstudianteModel> listaEstudiantes;

    private RespaldoListas(){

    }

    public static RespaldoListas Instancia()
    {
        if(bd == null){
            bd = new RespaldoListas();
        }
        return bd;
    }

    public void CargarListas(){
        Call<List<UsuariosModel>> callU = Api.instance().GetUsers();
        callU.enqueue(new Callback<List<UsuariosModel>>() {
            @Override
            public void onResponse(Call<List<UsuariosModel>> call, final Response<List<UsuariosModel>> response) {
                listaUsuarios = response.body();
            }

            @Override
            public void onFailure(Call<List<UsuariosModel>> call, Throwable t) {

            }
        });

        Call<List<VotacionesModel>> callV = Api.instance().GetVotaciones();
        callV.enqueue(new Callback<List<VotacionesModel>>() {
            @Override
            public void onResponse(Call<List<VotacionesModel>> call, final Response<List<VotacionesModel>> response) {
                listaVotaciones = response.body();
            }

            @Override
            public void onFailure(Call<List<VotacionesModel>> call, Throwable t) {

            }
        });

        Call<List<CandidatoModel>> callC = Api.instance().GetCandidatos();
        callC.enqueue(new Callback<List<CandidatoModel>>() {
            @Override
            public void onResponse(Call<List<CandidatoModel>> call, final Response<List<CandidatoModel>> response) {
                listaCandidatos = response.body();
            }

            @Override
            public void onFailure(Call<List<CandidatoModel>> call, Throwable t) {

            }
        });

        Call<List<EstudianteModel>> callE = Api.instance().GetEstudiantes();
        callE.enqueue(new Callback<List<EstudianteModel>>() {
            @Override
            public void onResponse(Call<List<EstudianteModel>> call, final Response<List<EstudianteModel>> response) {
                listaEstudiantes = response.body();
            }

            @Override
            public void onFailure(Call<List<EstudianteModel>> call, Throwable t) {

            }
        });
    }

    public List<UsuariosModel> ObtenerUsuarios(){
        return listaUsuarios;
    }

    public List<CandidatoModel> ObtenerCandidatos(){
        return listaCandidatos;
    }
    public List<VotacionesModel> ObtenerVotaciones(){
        return listaVotaciones;
    }
    public List<EstudianteModel> ObtenerEstudiantes(){
        return listaEstudiantes;
    }
}
