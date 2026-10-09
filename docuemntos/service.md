# Atividade — O Service protege as regras da compra

**UC4 — Aula 12: Colaboração entre objetos e Service**

Preencha este material durante a atividade e entregue sua cópia pelo Google Classroom. Você receberá o início da classe `LojaService` pronto e funcionando, incluindo as listas, os métodos de cadastro e a busca de produto. Os métodos restantes já estarão declarados, mas você deverá completar seus corpos e aplicar as regras da operação. Responda com suas próprias palavras e, quando solicitado, cole o trecho de código testado.

**Estudante:** __Matheus Epifanio de Souza__  
**Turma:** __Sala 102 TDS__  **Data:** __01__/__10__/__2026__

## Contexto

Na loja, a `Main` inicia o fluxo, o `Pedido` conhece seus itens e calcula o total, e o `LojaService` coordena a operação. Nesta atividade, você vai completar os métodos restantes de uma classe `LojaService` parcialmente pronta.

Considere estas regras:

- O cliente precisa estar cadastrado na loja.
- O pedido precisa ter pelo menos um item.
- Os produtos do pedido precisam estar cadastrados na loja.
- O valor-base do pagamento precisa ser igual ao total calculado pelo pedido.
- Se alguma regra falhar, o pagamento não deve ser processado e o pedido não deve ser registrado.

## Exercício 1 — Casos do caixa: descubra as regras

Para cada situação, marque o resultado: **processa e registra**, **barra**, ou **falta informação**. Depois justifique citando a regra envolvida.

| Situação | Resultado | Regra e justificativa |
|---|---|---|
| Cliente cadastrado, itens da loja e valor-base correto. | Processa e registra o pagamento | O Cliente precisa estar cadastrado e os itens da lojs tambem |
| Cliente não cadastrado, com pedido completo e pagamento correto. | Não vai processar o pagamento | Se o cliente não estiver cadastrado o pagamento não podera ser concluido |
| Um dos produtos do pedido não aparece no catálogo da loja. | O pedido não sera concluido | Como um dos produtos não está no catalogo, o pedido não será concluido pela falta de estoque |
| O pedido soma R$ 230,00, mas o pagamento informa R$ 220,00 como valor-base. | O pagamento não deve ser realizado | Como os valores estão diferentes, o pagamento não poder ser realizado pela incoerencia, mas na hora de aparecer o valor apareceria os R$ 220,00 |

**Reflexão:** por que o Service deve validar as regras antes de chamar `pagamento.processar()`?

Resposta: __Para garantir que todos os dados serão validos e não tenha inconerência__

________________________________________________________________________________

## Exercício 2 — Continue a classe `LojaService`

No projeto, crie o arquivo `LojaService.java` no pacote `br.com.senac.service` usando o esqueleto abaixo. Os campos, os cadastros e a busca de produto já estão implementados. Os métodos seguintes já estão declarados, mas seus corpos precisam ser completados. Use os modelos existentes no projeto (`Cliente`, `Produto`, `Pedido`, `ItemPedido` e `Pagamento`).

Esqueleto fornecido:

```java
package com.example.service;

import java.util.ArrayList;
import java.util.List;

import com.example.model.Cliente;
import com.example.model.Pagamento;
import com.example.model.Pedido;
import com.example.model.Produto;

public class LojaService {
    private final List<com.example.model.Cliente> clientes = new ArrayList<>();
    private final List<com.example.model.Produto> produtos = new ArrayList<>();
    private final List<com.example.model.Pedido> pedidos = new ArrayList<>();

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

        if (Double.compare(pagamento.getValor(), pedido.calcularTotal()) != 0) {
            throw new IllegalArgumentException("Pagamento diferente do Pedido.");
        }

        pagamento.processar();
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

```

Complete os quatro métodos marcados com TODO. Mantenha a parte já implementada como está. Acrescente os imports necessários para validar os itens do pedido.

### Etapa de validação do pagamento

Dentro de `finalizarCompra`, implemente a comparação do valor-base do pagamento com o total do pedido:

```java
if (Double.compare(
        ______________________________,
        ______________________________) != 0) {
    throw new IllegalArgumentException(
            "Pagamento diferente do pedido.");
}
```

Depois dessa validação, mantenha a ordem: **validar → processar o pagamento → registrar o pedido**.

**Cole abaixo sua implementação de `finalizarCompra` ou anexe o arquivo `LojaService.java`:**

```java
public void finalizarCompra(Pedido pedido, Pagamento pagamento) {

        if (pedido.getItens().isEmpty()) {
            throw new IllegalArgumentException("O pedido está vazio.");
        }

        if (Double.compare(pagamento.getValor(), pedido.calcularTotal()) != 0) {
            throw new IllegalArgumentException("Pagamento diferente do Pedido.");
        }

        pagamento.processar();
        pedidos.add(pedido);
    }
```

Registre os dois testes realizados:

| Teste | Valor-base do pagamento | Total do pedido | O que aconteceu? |
|---|---:|---:|---|
| Valores iguais | R$ 737,53 | R$ 737,53 | Pagamento processado e pedido registrado |
| Valores diferentes | R$ 500,00 | R$ 566,05 | Compra bloqueada; pagamento não processado e não registrado |

Explique por que a ordem deve ser **validar → processar → registrar**:

________________________________________________________________________________

________________________________________________________________________________

## Exercício 3 — Teste seu Service pela Main

Depois de criar a classe e seus métodos, use a `Main` para testar o fluxo abaixo. Crie ou reutilize os objetos `loja`, `ana` e `livro`; confira os tipos e os imports do projeto.

```java
loja.cadastrarCliente(ana);
loja.cadastrarProduto(livro);

Pedido pedido = loja.abrirPedido(ana);
pedido.adicionarItem(livro, 1);

Pagamento pagamento = new PagamentoCartao(
        pedido.calcularTotal(), 5.0);

loja.finalizarCompra(pedido, pagamento);
```

Cole abaixo o trecho da sua `Main` que usa o Service criado:

```java
loja.cadastrarCliente(clienteAtendido);

loja.cadastrarProduto(produtoFisico);

Pedido pedido = loja.abrirPedido(clienteAtendido);

pedido.adicionarItem(produtoFisico, 1);

Pagamento pagamento = new PagamentoCartao(
        pedido.calcularTotal(), 15.00);

loja.finalizarCompra(pedido, pagamento);
```

Agora tente finalizar um pedido vazio:

1. O que você alterou para deixar o pedido sem itens?

_Criei um novo pedido loja.abrirPedido(clienteAtendido),mas não adicionei nenhum item usando o adicionarItem().Assim, o pedido ficou com zero itens._

2. Qual resultado ocorreu ao chamar `finalizarCompra`?

_A compra foi bloqueada e foi lançada uma exceção com a mensagem "O pedido está vazio." O pagamento processado_

3. Depois da tentativa inválida, esse pedido deve aparecer no histórico? Explique como você verificou.

_Não. Verifiquei usando loja.listarPedidos().size(). O número de pedidos continuou igual ao número anterior à tentativa, mostrando que o pedido vazio não foi registrado no histórico._

## Desafio extra — Troque a forma de pagamento

Repita o fluxo usando `PagamentoPix`, calculando o valor a partir do próprio pedido.

1. Qual linha da `Main` você precisou alterar?

```java
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
```

2. Foi necessário alterar o `LojaService` para ele conhecer especificamente `PagamentoPix`? Explique.

_Não é alterado pois ele trabalha com a classe pai Pagamento e não com o filho PagamentoPix_

3. Qual tipo de referência permite que o fluxo aceite formas de pagamento diferentes?

_O proprio Pagamento, ele serve de base como classe abstrata o que permite o fluxo com outros tipos de pagamentos_

## Entrega pelo Classroom

Antes de enviar, confira:

- [ x ] Respondi aos casos do caixa e justifiquei as decisões.
- [ x ] Mantive a parte já implementada e completei os métodos que estavam com TODO.
- [ x ] Testei pagamento com valor correto e divergente.
- [ x ] Testei a compra pela `Main` e o pedido vazio.
- [ x ] Anexei `LojaService.java` e a `Main` usada nos testes, ou colei os trechos solicitados.
- [ x ] Se fiz o desafio extra, respondi às três perguntas.

**O que aprendi sobre a responsabilidade do Service?**

_Ele é responsavel por coordenar o fluxo da compra, e validar os requisitos e regras de negocio_