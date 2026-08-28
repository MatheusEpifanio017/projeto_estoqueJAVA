package com.example.model;

public class Usuario {
    
    private String nome;
    private String documento;


    public Usuario(String nome, String documento) {
        this.nome = nome;
        this.documento = documento;
    }
    public String getDocuemnto() {
        return documento;
    }

    

    public void setDocuemnto(String documento) {
        this.documento = documento;
    }

     public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

   public void exibirDados() {
       System.out.println("Nome: " + this.nome);
       System.out.println("Docuemnto: " + this.documento);
    }  
}
