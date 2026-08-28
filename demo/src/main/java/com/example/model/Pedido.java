package com.example.model;

public class Pedido {

    private static int totalNumeros = 0;

    private int numero;
    private Cliente cliente;
    private Produto produto;
    private int quantidade;

    public Pedido(Cliente cliente, Produto produto, int quantidade) {

        if (cliente == null) {
            throw new IllegalArgumentException("Cliente inválido");
        }

        if (produto == null) {
            throw new IllegalArgumentException("Produto inválido");
        }

        if (quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade deve ser maior que zero");
        }

        if (quantidade > produto.getQuantidade()) {
            throw new IllegalArgumentException("Quantidade maior que o estoque disponível");
        }

        this.cliente = cliente;
        this.produto = produto;
        this.quantidade = quantidade;
        this.numero = ++totalNumeros;
    }

    public int getNumero() {
        return numero;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Produto getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void exibirResumo() {
        System.out.println("Pedido: " + numero);
        System.out.println("Cliente: " + cliente.getNome());
        System.out.println(
            "Produto: " + produto.getNome() +
            " | Preço: R$ " + produto.getPreco()
        );
        System.out.println("Quantidade: " + quantidade);
        System.out.println("Valor total: R$ " + calcularValorTotal());
    }

    public double calcularValorTotal() {
        return produto.getPreco() * quantidade;
    }
}