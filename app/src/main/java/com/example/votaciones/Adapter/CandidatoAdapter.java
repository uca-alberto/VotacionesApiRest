package com.example.votaciones.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.votaciones.Model.CandidatoModel;
import com.example.votaciones.Model.EstudianteModel;
import com.example.votaciones.Model.RespaldoListas;
import com.example.votaciones.Model.VotacionesModel;
import com.example.votaciones.R;

import java.util.List;

public class CandidatoAdapter extends RecyclerView.Adapter<CandidatoAdapter.ViewHolder> implements View.OnClickListener {
    public List<EstudianteModel> estudiantesLista = RespaldoListas.Instancia().ObtenerEstudiantes();
    public List<CandidatoModel> candidatoModels;
    private View.OnClickListener listener;
    public CandidatoAdapter(List<CandidatoModel>candidatoModelList)
    {
        this.candidatoModels = candidatoModelList;
    }

    @NonNull
    @Override
    public CandidatoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.candidatos,parent,false);
        view.setOnClickListener(this);
        return new CandidatoAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CandidatoModel candidatoModel = candidatoModels.get(position);

        for(EstudianteModel e : this.estudiantesLista){
            if(e.getId().equals(candidatoModel.getIdEstudiante()))
            {
              holder.NOMBRE.setText(e.getNombreCompleto());
                break;
            }
        }

        holder.VOTOS.setText(candidatoModel.getVotosObtenidos());
    }

    @Override
    public int getItemCount() {
        return candidatoModels.size();
    }
    @Override
    public void onClick(View view) {
        if(listener != null){
            listener.onClick(view);
        }
    }
    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView NOMBRE;
        TextView VOTOS;
        public ViewHolder(View intemView)
        {
            super(intemView);
            NOMBRE = intemView.findViewById(R.id.NOMBRE);
            VOTOS = intemView.findViewById(R.id.VOTOS);
        }
    }
}
