package com.example.votaciones.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.votaciones.Model.VotacionesModel;
import com.example.votaciones.R;

import java.util.List;

public class VotacionesAdapter extends RecyclerView.Adapter<VotacionesAdapter.ViewHolder> implements View.OnClickListener {
    private List<VotacionesModel> votacionesModelList;
    private View.OnClickListener listener;
    public VotacionesAdapter(List<VotacionesModel> votacionesModels)
    {
        this.votacionesModelList = votacionesModels;
    }
    @NonNull
    @Override
    public VotacionesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.votaciones,parent,false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VotacionesAdapter.ViewHolder holder, int position) {
        VotacionesModel votacionesModel = votacionesModelList.get(position);
        holder.ID.setText(votacionesModel.getId());
        holder.CODIGOGRUPO.setText(votacionesModel.getCodGrupo());
        holder.ESTADO.setText(votacionesModel.getDescripcion());
    }

    @Override
    public int getItemCount() {
        return votacionesModelList.size();
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
        TextView ID;
        TextView CODIGOGRUPO;
        TextView ESTADO;
        public ViewHolder(View intemView)
        {
            super(intemView);
            ID = intemView.findViewById(R.id.ID);
            CODIGOGRUPO = intemView.findViewById(R.id.CodGrupo);
            ESTADO = intemView.findViewById(R.id.NombreCompleto);
        }
    }
}
