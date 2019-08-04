package com.example.votaciones.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.votaciones.Adapter.VotacionesAdapter;
import com.example.votaciones.Api.Api;
import com.example.votaciones.Model.VotacionesModel;
import com.example.votaciones.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Votaciones extends AppCompatActivity {
    RecyclerView recyclerView;
    String User = "1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_votaciones);
        initViews();
        configureRecyclerView();
        GetUser();
    }
    private void initViews() {
        recyclerView= findViewById(R.id.recycler);
    }
    private void configureRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    public void GetUser(){

        Call<List<VotacionesModel>> call = Api.instance().GetVotaciones();
        call.enqueue(new Callback<List<VotacionesModel>>() {
            @Override
            public void onResponse(Call<List<VotacionesModel>> call, final Response<List<VotacionesModel>> response) {
                Log.i("RESPUESTA", String.valueOf(response.body()));
                VotacionesAdapter votacionesAdapter = new VotacionesAdapter(response.body());
                recyclerView.setAdapter(votacionesAdapter);
                votacionesAdapter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(Votaciones.this, VotacionesDetalle.class)
                                .putExtra("ID_VOTACION",response.body().
                                        get(recyclerView.getChildAdapterPosition(view)).getId()).putExtra("ID_USUARIO",User));
                    }
                });
            }

            @Override
            public void onFailure(Call<List<VotacionesModel>> call, Throwable t) {

            }
        });
    }
}
