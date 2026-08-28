package com.example.main;

import java.util.ArrayList;

import com.example.model.*;



public class Main {
    public static void main(String[] args) {

        // Cliente cliente = new Cliente("Ana", "111", 10);
        
        
        // Funcionario funcionario = new Funcionario("Carlos", "111");
        

        // cliente.exibirDados();
        // System.out.println("Pontos: "+ cliente.getPontos());
        
        // funcionario.exibirDados();
        // System.out.println("Matricula: "+  funcionario.getMatricula());

        ArrayList<Pagamento> pagamentos = new ArrayList<>();

        pagamentos.add(new PagamentoPix(150.00));
        pagamentos.add(new PagamentoCartao(150.00, 10));
        pagamentos.add(new PagamentoBoleto(150.00, 10));
        pagamentos.add(new PagamentoDinheiro(200, 0));

        for (Pagamento pagamento : pagamentos) {
            pagamento.processar();
            System.out.println("Valor: "+pagamento.getValor());
            System.out.println("Taxa: "+ pagamento.calcularTaxa());
            System.out.println("Total: "+ pagamento.calcularTotal());
        
        }
    }
}
