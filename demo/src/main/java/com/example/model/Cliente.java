package com.example.model;

public class Cliente {
    
    private int codigo;
    private String nome;
 
    private static int totalClientes;

    public Cliente(String nome) {
        codigo = ++totalClientes;
        this.nome = nome;
      
    }
    
    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
