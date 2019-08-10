package com.example.votaciones.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.votaciones.Adapter.VotacionesAdapter;
import com.example.votaciones.Model.RespaldoListas;
import com.example.votaciones.R;

public class Votaciones extends AppCompatActivity {
    RecyclerView recyclerView;
    String User = "1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_votaciones);
        initViews();
        configureRecyclerView();
        ListarVotacionesRecycler();
    }
    private void initViews() {
        recyclerView= findViewById(R.id.recycler);
    }
    private void configureRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    public void ListarVotacionesRecycler(){

        VotacionesAdapter votacionesAdapter = new VotacionesAdapter(RespaldoListas.Instancia().ObtenerVotaciones());
        recyclerView.setAdapter(votacionesAdapter);

        votacionesAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Votaciones.this, VotacionesDetalle.class)
                        .putExtra("ID_VOTACION",RespaldoListas.Instancia().ObtenerVotaciones().
                                get(recyclerView.getChildAdapterPosition(view)).getId()).putExtra("ID_USUARIO",User)
                .putExtra("DESCRIPCION",RespaldoListas.Instancia().ObtenerVotaciones().get(recyclerView.getChildAdapterPosition(view)).getDescripcion()));
            }
        });
    }
}
