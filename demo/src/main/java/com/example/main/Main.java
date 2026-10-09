package com.example.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import com.example.model.*;
import com.example.service.LojaService;

import java.util.Map;
import java.util.Set;

public class Main {

    public static void main(String[] args) {

        LojaService loja = new LojaService();

        ArrayList<Cliente> filaAtendimento = new ArrayList<>();

        filaAtendimento.add(new Cliente("Pedro", "51", 10));
        filaAtendimento.add(new Cliente("Joana", "45", 20));
        filaAtendimento.add(new Cliente("Cristhoper", "03", 45));
        filaAtendimento.add(new Cliente("Gilmar", "77", 28));
        filaAtendimento.add(new Cliente("Repetido", "51", 12));

        Cliente clienteAtendido = filaAtendimento.get(0);

        System.out.println("\nCliente atendido: " + clienteAtendido.getNome());

        ProdutoFisico produtoFisico =
                new ProdutoFisico("GTA VI", 550.50, 3, 15.55);

        ProdutoDigital produtoDigital =
                new ProdutoDigital("Curso SQL", 75.74, 2, 20.00);

        ProdutoFisico produtoFisico2 =
                new ProdutoFisico("Cabeçote de pistão", 500.00, 3, 10.00);

        loja.cadastrarCliente(clienteAtendido);

        loja.cadastrarProduto(produtoFisico);
        loja.cadastrarProduto(produtoDigital);
        loja.cadastrarProduto(produtoFisico2);

        Pedido pedido = loja.abrirPedido(clienteAtendido);

        pedido.adicionarItem(produtoFisico, 4);
        pedido.adicionarItem(produtoDigital, 1);

        double valorPagamento = pedido.calcularTotal();

        System.out.println("\n===============================================");
        System.out.println("TESTE 1 - VALORES IGUAIS");
        System.out.println("===============================================");
        System.out.println("Total do pedido: R$ " + valorPagamento);
        System.out.println("Valor-base do pagamento: R$ " + valorPagamento);

        PagamentoCartao pagamento =
                new PagamentoCartao(valorPagamento, 15);
                

        try {
        loja.finalizarCompra(pedido, pagamento);

        System.out.println("Resultado: COMPRA APROVADA");
        System.out.println("Pagamento processado.");
        System.out.println("Pagamento: " + pagamento.calcularTotal());
        System.out.println("Pedido registrado.");

        System.out.println("Estoque restante do GTA VI: "
                + produtoFisico.getQuantidade() + " unidades");

    } catch (IllegalArgumentException e) {
        System.out.println("Resultado: COMPRA BLOQUEADA");
        System.out.println("Motivo: " + e.getMessage());

        System.out.println("Estoque restante do GTA VI: "
                + produtoFisico.getQuantidade() + " unidades");
    }

        System.out.println(
                "Pedidos finalizados: " + loja.listarPedidos().size()
        );

        System.out.println("\n===============================================");
        System.out.println("TESTE 2 - PAGAMENTO PIX");
        System.out.println("===============================================");

        Pedido pedidoPix = loja.abrirPedido(clienteAtendido);

        pedidoPix.adicionarItem(produtoDigital, 1);

        double totalPedidoPix = pedidoPix.calcularTotal();
        double valorPix = 500.00;

        System.out.println("Total do pedido: R$ " + totalPedidoPix);
        System.out.println("Valor-base do pagamento: R$ " + valorPix);

        PagamentoPix pagamentoPix =
                new PagamentoPix(valorPix);

        try {
            loja.finalizarCompra(pedidoPix, pagamentoPix);

            System.out.println("Resultado: COMPRA APROVADA");
            System.out.println("Pagamento processado.");
            System.out.println("Pedido registrado.");
        } catch (IllegalArgumentException e) {
            System.out.println("Resultado: COMPRA BLOQUEADA");
            System.out.println("Motivo: " + e.getMessage());
        }

        System.out.println(
                "Pedidos finalizados após o teste: "
                + loja.listarPedidos().size()
        );

        System.out.println("\n===============================================");
        System.out.println("TESTE 3 - PEDIDO VAZIO");
        System.out.println("===============================================");

        Pedido pedidoVazio = loja.abrirPedido(clienteAtendido);

        System.out.println( 
                "Itens no pedido: " + pedidoVazio.getItens().size()
        );

        PagamentoCartao pagamentoVazio =
                new PagamentoCartao(0.0, 5.00);

        try {
            loja.finalizarCompra(pedidoVazio, pagamentoVazio);

            System.out.println("Resultado: COMPRA APROVADA");
            System.out.println("Pagamento processado.");
            System.out.println("Pedido registrado.");
        } catch (IllegalArgumentException e) {
            System.out.println("Resultado: COMPRA BLOQUEADA");
            System.out.println("Motivo: " + e.getMessage());
        }

        System.out.println(
                "Pedidos no histórico após o teste: "
                + loja.listarPedidos().size()
        );

        System.out.println("\n===============================================");
        System.out.println("HISTÓRICO");
        System.out.println("===============================================");

        ArrayList<String> historico = new ArrayList<>();

        historico.add(
                "Cliente atendido: " +
                filaAtendimento.get(0).getNome() +
                " | Comprou: " +
                pedido.getItens().get(0).getProduto().getNome() +
                "\n| Detalhes do produto: " + " Preço: " +
                produtoFisico.getPreco() + " Quantidade " + produtoFisico.getQuantidade()+ "\n"
        );

        historico.add(
                "Cliente atendido: " +
                filaAtendimento.get(1).getNome() +
                " | Comprou: " +
                pedido.getItens().get(1).getProduto().getNome()
        );

        historico.add(
                "Cliente atendido: " +
                filaAtendimento.get(1).getNome() +
                " | Comprou: " +
                pedido.getItens().get(1).getProduto().getNome()
        );

        for (String h : historico) {
            System.out.println(h);
        }

        System.out.println("\n===============================================");
        System.out.println("PRODUTOS");
        System.out.println("===============================================");

        Map<Integer, Produto> produtos = new HashMap<>();

        produtos.put(produtoFisico.getCodigo(), produtoFisico);
        produtos.put(produtoDigital.getCodigo(), produtoDigital);
        produtos.put(produtoFisico2.getCodigo(), produtoFisico2);

        int[] codigos = {
                produtoFisico.getCodigo(),
                produtoDigital.getCodigo()
        };

        for (int codigo : codigos) {

            if (produtos.containsKey(codigo)) {

                Produto produto = produtos.get(codigo);

                System.out.println("-----------------------------------------------");
                System.out.println("Produto: " + produto.getNome());
                System.out.println(
                        "Preço final: R$ " + produto.calcularValorTotal()
                );

            } else {
                System.out.println("\nErro: produto não encontrado!");
            }
        }

        System.out.println("\n===============================================");
        System.out.println("CADASTROS");
        System.out.println("===============================================");

        Set<String> documentos = new HashSet<>();
        Set<String> cadastrosInvalidos = new HashSet<>();
        Set<String> usuariosAceitos = new HashSet<>();

        for (Cliente cliente : filaAtendimento) {

            if (documentos.add(cliente.getDocuemnto())) {
                usuariosAceitos.add(cliente.getNome());
            } else {
                cadastrosInvalidos.add(cliente.getDocuemnto());
            }
        }

        System.out.println("-----------------------------------------------");
        System.out.println("USUÁRIOS ACEITOS:");

        for (String usuario : usuariosAceitos) {
            System.out.println("Nome de usuário: " + usuario);
        }

        System.out.println("-----------------------------------------------");
        System.out.println("DOCUMENTOS ACEITOS:");

        for (String documento : documentos) {
            System.out.println("Documento Número: " + documento);
        }

        System.out.println("-----------------------------------------------");
        System.out.println("DOCUMENTOS REJEITADOS:");

        for (String documento : cadastrosInvalidos) {
            System.out.println("Documento Número: " + documento);
        }

        System.out.println("-----------------------------------------------");
        System.out.println("TOTAL DE CADASTROS: " + documentos.size());
    }
}