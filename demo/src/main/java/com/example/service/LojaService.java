package com.example.service;

import java.util.ArrayList;
import java.util.List;

import com.example.model.Cliente;
import com.example.model.ItemPedido;
import com.example.model.Pagamento;
import com.example.model.Pedido;
import com.example.model.Produto;

public class LojaService {

    private final List<Cliente> clientes = new ArrayList<>();
    private final List<Produto> produtos = new ArrayList<>();
    private final List<Pedido> pedidos = new ArrayList<>();

    public void cadastrarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public void cadastrarProduto(Produto produto) {
        produtos.add(produto);
    }

    public Produto buscarProdutoPorCodigo(int codigo) {
        for (Produto produto : produtos) {
            if (produto.getCodigo() == codigo) {
                return produto;
            }
        }
        return null;
    }

    public Pedido abrirPedido(Cliente cliente) {
        if (!clienteCadastrado(cliente)) {
            throw new IllegalArgumentException("Cliente não encontrado.");
        }

        return new Pedido(cliente);
    }

    public void finalizarCompra(Pedido pedido, Pagamento pagamento) {
        if (pedido.getItens().isEmpty()) {
            throw new IllegalArgumentException("O pedido está vazio.");
        }

        for (ItemPedido item : pedido.getItens()) {
            Produto produto = item.getProduto();

            if (item.getQuantidade() > produto.getQuantidade()) {
                throw new IllegalArgumentException(
                    "Estoque insuficiente para o produto: " + produto.getNome()
                );
            }
        }

        if (Double.compare(pagamento.getValor(), pedido.calcularTotal()) != 0) {
            throw new IllegalArgumentException("Pagamento diferente do Pedido.");
        }

        pagamento.processar();

        for (ItemPedido item : pedido.getItens()) {
            item.getProduto().removerEstoque(item.getQuantidade());
        }

        pedidos.add(pedido);
    }

    public List<Pedido> listarPedidos() {
        return pedidos;
    }

    private boolean clienteCadastrado(Cliente cliente) {
        for (Cliente c : clientes) {
            if (c == cliente) {
                return true;
            }
        }

        return false;
    }
}