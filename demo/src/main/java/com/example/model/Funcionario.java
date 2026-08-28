 package com.example.model;

public class Funcionario extends Usuario {
    
    private String matricula;

    public Funcionario(String nome, String matricula) {
        super(nome, matricula);
        this.matricula = matricula;
        
    }
    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
}
