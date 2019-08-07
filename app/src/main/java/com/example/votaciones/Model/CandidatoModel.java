package com.example.votaciones.Model;

public class CandidatoModel {
    String Id,IdEstudiante,IdVotacion,VotosObtenidos;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getIdEstudiante() {
        return IdEstudiante;
    }

    public void setIdEstudiante(String idEstudiante) {
        IdEstudiante = idEstudiante;
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
