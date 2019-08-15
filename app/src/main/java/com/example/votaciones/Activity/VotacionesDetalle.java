package com.example.votaciones.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.icu.text.UnicodeSetSpanner;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.votaciones.Adapter.CandidatoAdapter;
import com.example.votaciones.Api.Api;
import com.example.votaciones.Model.CandidatoModel;
import com.example.votaciones.Model.RespaldoListas;
import com.example.votaciones.Model.VotosModel;
import com.example.votaciones.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VotacionesDetalle extends AppCompatActivity {
    Bundle bundle;
    String Id_Votacion,User,DESCRIPCION;
    RecyclerView recyclerView;
    TextView codigo;
    SwipeRefreshLayout refreshLayout;

    private  int contador=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_votaciones_detalle);
        bundle = getIntent().getExtras();
        Id_Votacion = bundle.getString("ID_VOTACION");
        User = bundle.getString("ID_USUARIO");
        DESCRIPCION = bundle.getString("DESCRIPCION");
        //
        codigo = findViewById(R.id.Codigo_Votacion);
        refreshLayout = findViewById(R.id.RefreshAdress);
        codigo.setText(DESCRIPCION);
        //
        initViews();
        configureRecyclerView();
        GetCandidatos();
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                RespaldoListas.Instancia().CargarListas();
                GetCandidatos();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.setRefreshing(false);
                    }
                },2000);
            }
        });
    }

    private void initViews() {
        recyclerView= findViewById(R.id.recycler1);
    }

    private void configureRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void GetCandidatos() {
        final ArrayList<CandidatoModel> candidatosFiltrados = new ArrayList<>();
        for(CandidatoModel c : RespaldoListas.Instancia().ObtenerCandidatos())
        {
            if(c.getIdVotacion().equals(Id_Votacion))
            {
                candidatosFiltrados.add(c);
            }
        }

        final CandidatoAdapter candidatoAdapter = new CandidatoAdapter(candidatosFiltrados);
        recyclerView.setAdapter(candidatoAdapter);
        if (contador ==0){
            Toast.makeText(this, "Tines un voto", Toast.LENGTH_SHORT).show();
            contador = contador+1;
            candidatoAdapter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ConfirmacionDialog(candidatoAdapter.estudiantesLista.get(recyclerView.getChildAdapterPosition(view)).getNombreCompleto(),
                            candidatosFiltrados.get(recyclerView.getChildAdapterPosition(view)).getId(),
                            candidatosFiltrados.get(recyclerView.getChildAdapterPosition(view)).getIdEstudiante(),
                            candidatosFiltrados.get(recyclerView.getChildAdapterPosition(view)).getIdVotacion(),
                            candidatosFiltrados.get(recyclerView.getChildAdapterPosition(view)).getVotosObtenidos());
                }
            });
        }else {
            Toast.makeText(this, "Ya no puedes votar", Toast.LENGTH_SHORT).show();
        }

    }

    //Metodo del dialogo
    public void ConfirmacionDialog(final String Usuario,final String Id, final String Id_Estudiante,final String Id_Votacion,final String Votos){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Seguro de Votar por el Estudiante: "+ Id);
        builder.setTitle("Votaciones");
        builder.setPositiveButton("SI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                CandidatoModel candidatoModel = new CandidatoModel();
                candidatoModel.setId(Id);
                candidatoModel.setIdEstudiante(Id_Estudiante);
                candidatoModel.setIdVotacion(Id_Votacion);
                candidatoModel.setVotosObtenidos(String.valueOf(Integer.parseInt(Votos)+1));
                Call<CandidatoModel> call = Api.instance().PutCandidato(Id,candidatoModel);
                call.enqueue(new Callback<CandidatoModel>() {
                    @Override
                    public void onResponse(Call<CandidatoModel> call, Response<CandidatoModel> response) {
                        if (response.body()!=null){
                        }else {
                            Toast.makeText(VotacionesDetalle.this, "Fallo de Conexion", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<CandidatoModel> call, Throwable t) {

                    }
                });
                RespaldoListas.Instancia().CargarListas();
              //  startActivity(new Intent(VotacionesDetalle.this,Votaciones.class));
               // finish();
                Toast.makeText(VotacionesDetalle.this, "Votacion Realizada", Toast.LENGTH_SHORT).show();

                PostVotosUser(Id_Estudiante,Id_Votacion);
            }
        });

        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void PostVotosUser(String IdUser,String IdVotacion){
        VotosModel votosModel = new VotosModel();
        votosModel.setIdEstudiante(IdUser);
        votosModel.setIdVotacion(IdVotacion);
        Call<VotosModel>call = Api.instance().PostVotosUser(votosModel);
        call.enqueue(new Callback<VotosModel>() {
            @Override
            public void onResponse(Call<VotosModel> call, Response<VotosModel> response) {
                if(response.body()!=null){
                    Toast.makeText(VotacionesDetalle.this, "No puede Votar de Nuevo", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(VotacionesDetalle.this, "Revisar Conexion", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<VotosModel> call, Throwable t) {

            }
        });
    }
}
