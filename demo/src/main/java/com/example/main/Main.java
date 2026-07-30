package com.example.main;

import java.util.List;
import java.util.Scanner;

import com.example.model.BancoDados;
import com.example.model.Cliente; 
import com.example.model.Produto;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Produto> produtos = BancoDados.carregar();
        List<Cliente> clientes = BancoDados.carregarClientes(); 

        int opcao;

        do {
            System.out.println("\n==== GERENCIADOR DE ESTOQUE E CLIENTES ====");
            System.out.println("1. Listar todos os produtos");
            System.out.println("2. Buscar produto por código");
            System.out.println("3. Ver produto mais caro");
            System.out.println("4. Adicionar quantidade em estoque");
            System.out.println("5. Retirar quantidade em estoque");
            System.out.println("6. Aplicar desconto em produto");
            System.out.println("7. Adicionar novo item no estoque");
            System.out.println("8. Cadastrar novo cliente");
            System.out.println("9. Listar todos os clientes");
            System.out.println("0. Fechar");
            System.out.print("\nEscolha uma opção: ");

            opcao = scanner.nextInt();

            switch (opcao) {

                case 1:

                    System.out.println("\n--- Lista de Produtos ---");

                    if (produtos.isEmpty()) {

                        System.out.println("Nenhum produto cadastrado.");

                    } else {

                        produtos.forEach(Produto::exibirResumo);

                    }

                    break;



                case 2:

                    System.out.print("Digite o código do produto: ");

                    int codBusca = scanner.nextInt();

                    Produto pEncontrado = buscarProduto(produtos, codBusca);



                    if (pEncontrado != null) {

                        System.out.println("\n--- Produto Encontrado ---");

                        System.out.println("Nome: " + pEncontrado.getNome());

                        System.out.println("Preço: R$ " + pEncontrado.getPreco());

                        System.out.println("Quantidade em Estoque: " + pEncontrado.getQuantidade());



                    } else {

                        System.out.println("\nProduto não encontrado!");

                    }

                    break;



                case 3:

                    if (produtos.isEmpty()) {

                        System.out.println("\nLista vazia.");

                    } else {

                        Produto maisCaro = produtos.get(0);

                        for (Produto prod : produtos) {

                            if (prod.getPreco() > maisCaro.getPreco())

                                maisCaro = prod;

                        }

                        System.out.print("\nProduto mais caro: ");

                        maisCaro.exibirResumo();

                    }

                    break;



                case 4:

                    System.out.print("Código do produto para entrada: ");

                    int codAdd = scanner.nextInt();

                    Produto pAdd = buscarProduto(produtos, codAdd);



                    if (pAdd != null) {

                        System.out.print("Quantidade a adicionar: ");

                        int qtdAdd = scanner.nextInt();

                        if (pAdd.adicionarEstoque(qtdAdd)) {



                            BancoDados.salvar(produtos);

                            System.out.println("Estoque atualizado com sucesso!");

                        } else {

                            System.out.println("Quantidade inválida.");

                        }

                    } else {

                        System.out.println("Produto não encontrado.");

                    }

                    break;



                case 5:

                    System.out.print("Código do produto para saída: ");

                    int codRem = scanner.nextInt();

                    Produto pRem = buscarProduto(produtos, codRem);



                    if (pRem != null) {

                        System.out.print("Quantidade a retirar: ");

                        int qtdRem = scanner.nextInt();

                        if (pRem.removerEstoque(qtdRem)) {



                            BancoDados.salvar(produtos);

                            System.out.println("Estoque retirado com sucesso!");

                        } else {

                            System.out.println("Falha na retirada (Estoque insuficiente ou valor inválido).");

                        }

                    } else {

                        System.out.println("Produto não encontrado.");

                    }

                    break;



                case 6:

                    System.out.print("Digite o código do produto para dar desconto: ");

                    int codDesc = scanner.nextInt();

                    Produto pDesc = buscarProduto(produtos, codDesc);



                    if (pDesc != null) {

                        System.out.println("\nTipo de Desconto:");

                        System.out.println("1. Porcentagem (%)");

                        System.out.println("2. Valor Fixo (R$)");

                        System.out.print("Escolha o tipo: ");

                        int tipoDesc = scanner.nextInt();



                        if (tipoDesc == 1) {

                            System.out.print("Digite a porcentagem (máx 50%): ");

                            double valorPct = scanner.nextDouble();



                            if (pDesc.aplicarDesconto(valorPct)) {



                                BancoDados.salvar(produtos);

                                System.out.println("Desconto aplicado! Novo preço: R$ " + pDesc.getPreco());

                            } else {

                                System.out.println("Erro: Porcentagem inválida ou acima de 50%.");

                            }

                        } else if (tipoDesc == 2) {

                            System.out.print("Digite o valor fixo em R$: ");

                            double valorFixo = scanner.nextDouble();



                            if (pDesc.aplicarDesconto(valorFixo, true)) {



                                BancoDados.salvar(produtos);

                                System.out.println("Desconto aplicado! Novo preço: R$ " + pDesc.getPreco());

                            } else {

                                System.out.println("Erro: Valor inválido ou maior que o preço do produto.");

                            }

                        } else {

                            System.out.println("Tipo de desconto inválido.");

                        }

                    } else {

                        System.out.println("Produto não encontrado.");

                    }

                    break;



                case 7:

                    System.out.println("\n--- Cadastrar Novo Produto ---");

                    scanner.nextLine();



                    System.out.print("Digite o nome do produto: ");

                    String nome = scanner.nextLine();



                    System.out.print("Digite o preço do produto: ");

                    double preco = scanner.nextDouble();



                    System.out.print("Digite a quantidade inicial em estoque: ");

                    int quantidade = scanner.nextInt();



                    try {

                        Produto novoProduto = new Produto(nome, preco, quantidade);

                        produtos.add(novoProduto);



                        BancoDados.salvar(produtos);



                        System.out.println("Produto cadastrado e salvo com sucesso!");

                        System.out.println("Código gerado: " + novoProduto.getCodigo());



                    } catch (IllegalArgumentException e) {

                        System.out.println("Erro: Preço e quantidade não podem ser valores negativos.");

                    }

                    break;


                case 8:
                    System.out.println("\n--- Cadastrar Novo Cliente ---");
                    scanner.nextLine(); 

                    System.out.print("Digite o nome do cliente: ");
                    String nomeCliente = scanner.nextLine();

                    Cliente novoCliente = new Cliente(nomeCliente);
                    clientes.add(novoCliente);

                 
                    BancoDados.salvarClientes(clientes); 

                    System.out.println("Cliente cadastrado com sucesso!");
                    System.out.println("Código do Cliente: " + novoCliente.getCodigo());
                    break;

                case 9:
                    System.out.println("\n--- Lista de Clientes ---");
                    if (clientes.isEmpty()) {
                        System.out.println("Nenhum cliente cadastrado.");
                    } else {
                        clientes.forEach(c -> 
                            System.out.println("Código: " + c.getCodigo() + " | Nome: " + c.getNome())
                        );
                    }
                    break;

                case 0:
                    BancoDados.salvar(produtos);
                    BancoDados.salvarClientes(clientes);
                    System.out.println("Encerrando o sistema.");
                    break;

                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 0);

        scanner.close();
    }

    private static Produto buscarProduto(List<Produto> lista, int codigo) {
        return lista.stream()
                .filter(p -> p.getCodigo() == codigo)
                .findFirst()
                .orElse(null);
    }
}