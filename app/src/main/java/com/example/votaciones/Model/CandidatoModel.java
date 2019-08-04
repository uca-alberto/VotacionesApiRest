package com.example.votaciones.Model;

public class CandidatoModel {
    String Id,Nombre,IdVotacion,VotosObtenidos;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getIdVotacion() {
        return IdVotacion;
    }

    public void setIdVotacion(String idVotacion) {
        IdVotacion = idVotacion;
    }

    public String getVotosObtenidos() {
        return VotosObtenidos;
    }

    public void setVotosObtenidos(String votosObtenidos) {
        VotosObtenidos = votosObtenidos;
    }
}
