
package com.example.model;

public class PagamentoVale extends Pagamento {

    private double saldo;

    public PagamentoVale(double valor, double saldo) {
        super(valor);
        this.saldo = saldo;
    }

    public double calcularSaldo() {

        if (saldo < getValor()) {
            System.out.println("Saldo inválido");
            return saldo;
        } else {
            saldo -= getValor();
            System.out.println("Valor descontado do vale: R$ " + getValor());
            return saldo;
        }
    }

    @Override
    public double calcularTaxa() {
        return 0;
    }

    @Override
    public void processar() {
        System.out.println("=======================================");
        System.out.println("Valor da compra: R$ " + getValor());

        if (saldo >= getValor()) {
            saldo -= getValor();

            System.out.println("Pagamento realizado com vale!");
            System.out.println("Valor descontado: R$ " + getValor());
            System.out.println("Saldo restante: R$ " + saldo);
        } else {
            System.out.println("Saldo insuficiente!");
        }
    }
}
