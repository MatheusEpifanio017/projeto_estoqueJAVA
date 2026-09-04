package com.example.main;

import java.util.ArrayList;

import com.example.model.*;

public class Main {

    public static void main(String[] args) {

        ArrayList<Pagavel> formas = new ArrayList<>();

        formas.add(new PagamentoPix(150.00));
        formas.add(new PagamentoCartao(150.00, 10));
        formas.add(new PagamentoBoleto(200, 20));

        ValePresente vale = new ValePresente(80.0, 220.0);
        formas.add(vale);

        for (Pagavel forma : formas) {
            forma.processar();
            System.out.println("Total: " + forma.calcularTotal());
        }

        System.out.println("Saldo: " + vale.consultarSaldo());
    }
}