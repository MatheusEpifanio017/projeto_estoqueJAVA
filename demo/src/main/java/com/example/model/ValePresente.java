package com.example.model;

public class ValePresente implements Pagavel, ConsultavelSaldo {

    private double saldo;
    private double valor;

    public ValePresente(double saldo, double valor) {
        this.saldo = saldo;
        this.valor = valor;
    }

    @Override
    public double consultarSaldo() {
        return saldo;
    }

    @Override
    public double calcularTotal() {
        return valor;
    }

    @Override
    public void processar() {

        if (saldo < valor) {
            System.out.println(
                "Seu vale não possui valor suficiente para a compra de R$: "
                + valor + ". Saldo atual: R$: " + saldo
            );
        } else {
            saldo -= valor;

            System.out.println(
                "Vale descontado! R$: "
                + valor + ". Saldo atual: R$: " + saldo
            );
        }
    }
}