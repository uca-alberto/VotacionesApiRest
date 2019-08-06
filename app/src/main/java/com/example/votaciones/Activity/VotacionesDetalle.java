package com.example.votaciones.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.votaciones.Adapter.CandidatoAdapter;
import com.example.votaciones.Api.Api;
import com.example.votaciones.Model.CandidatoModel;
import com.example.votaciones.R;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VotacionesDetalle extends AppCompatActivity {
    Bundle bundle;
    String Id_Votacion,User;
    RecyclerView recyclerView;
    TextView codigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_votaciones_detalle);
        bundle = getIntent().getExtras();
        Id_Votacion = bundle.getString("ID_VOTACION");
        User = bundle.getString("ID_USUARIO");
        //
        codigo = findViewById(R.id.Codigo_Votacion);
        codigo.setText(Id_Votacion);
        //
        initViews();
        configureRecyclerView();
        GetCandidatos();
    }
    private void initViews() {
        recyclerView= findViewById(R.id.recycler1);
    }
    private void configureRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    private void GetCandidatos() {
        Call<List<CandidatoModel>> call = Api.instance().GetCandidatos();
        call.enqueue(new Callback<List<CandidatoModel>>() {
            @Override
            public void onResponse(Call<List<CandidatoModel>> call, Response<List<CandidatoModel>> response) {
                CandidatoAdapter candidatoAdapter = new CandidatoAdapter(response.body());
                recyclerView.setAdapter(candidatoAdapter);
            }

            @Override
            public void onFailure(Call<List<CandidatoModel>> call, Throwable t) {

            }
        });
    }
}
