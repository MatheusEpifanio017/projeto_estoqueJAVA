package com.example.model;

public class PagamentoPix extends Pagamento {
    
    public PagamentoPix(double valor) {
        super(valor);
    }

    public double getValor() { return super.getValor(); }

    @Override 
    public double calcularTotal() {
        return valor + calcularTaxa();
    }

    @Override
    public double calcularTaxa() {
        return 0.0;
    }

    @Override
    public void processar() {
                
        System.out.println("--------------------------------");
        System.out.println("Pagamento via Pix " + super.valor);
    }
}
