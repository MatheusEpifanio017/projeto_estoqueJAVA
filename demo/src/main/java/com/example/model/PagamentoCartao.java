package com.example.model;

public class PagamentoCartao extends Pagamento {

    private double percentualTaxa;

    public PagamentoCartao(double valor, double percentualTaxa) {
        super(valor);
        this.percentualTaxa = percentualTaxa;
    }

    @Override
    public double calcularTaxa() {
        return getValor() * percentualTaxa / 100;
    }

    @Override
    public void processar() {
        System.out.println("=======================================");
        System.out.println("Pagamento via Cratão " + calcularTotal());
        System.out.println("Valor da compra " + valor);
        System.out.println("Valor da taxa " + calcularTaxa());
        
    }
}
