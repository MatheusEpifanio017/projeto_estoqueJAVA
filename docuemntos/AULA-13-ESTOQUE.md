# Atividade — Sprint da Loja 2.0: compra protegida por estoque

**UC4 — Aula 13: Sprint de funcionalidades**

Preencha esta ficha durante a atividade e entregue sua cópia pelo Google Classroom. Você vai trabalhar no `LojaService` que a turma já construiu. Cadastro, busca de produtos, abertura de pedidos e validações básicas da compra já existem. Não recrie esses métodos. Sua missão é acrescentar a regra de estoque e comprovar que o restante do fluxo continua funcionando.

**Estudante(s):** __Matheus Epifanio de Souza__  
**Turma:** __TDS sala 102__  **Data:** __08__/__10__/__2026__

## Missão

Impedir que uma compra aprovada deixe o estoque negativo.

Ao final, o sistema deverá conferir o estoque de todos os itens antes de processar o pagamento. Se a compra for válida, deverá processar o pagamento, atualizar os saldos e registrar o pedido. Se alguma regra falhar, não poderá haver pagamento processado, saldo alterado ou pedido registrado.

## O que já existe no projeto

Antes de programar, localize estes elementos:

| Classe ou método | Responsabilidade atual |
|---|---|
| `LojaService.finalizarCompra(...)` | Valida regras básicas, processa o pagamento e registra o pedido. |
| `Pedido.getItens()` | Devolve os itens que fazem parte do pedido. |
| `ItemPedido.getProduto()` | Devolve o produto de uma linha do pedido. |
| `ItemPedido.getQuantidade()` | Devolve a quantidade pedida naquela linha. |
| `Produto.getQuantidade()` | Informa o saldo atual do produto. |
| `Produto.setQuantidade(...)` | Atualiza o saldo do produto. |
| `ItemPedido` | Já impede produto nulo e quantidade menor ou igual a zero. |

**Não remova as validações que já existem em `finalizarCompra`.** Complete o fluxo mantendo as regras atuais de cliente, pedido, produto cadastrado e valor do pagamento.

## Exercício 1 — Reproduza a falha

Antes de editar o Service, use a `Main` para simular uma compra acima do saldo. Cadastre um produto com 5 unidades e tente finalizar um pedido com 6. Observe o que o programa faz.

| O que observar | Resultado atual |
|---|---|
| A compra foi aceita ou recusada? | Foi aceita  |
| O pagamento foi processado? | Foi |
| O saldo do produto mudou? | Não mudou |
| O pedido entrou no histórico? | Sim |

Explique qual regra falta no `LojaService` e em que parte de `finalizarCompra` você procuraria inserir a validação.

__No momento da compra, ele não está validando se eu tenho a quantidade que eu quero comprar no estoque, ele apenas me permite comprar o quanto eu pedi independente se o estoque já está vazio ou não.__

## Exercício 2 — Valide o estoque

Acrescente a validação ao método existente `finalizarCompra`. Preserve as verificações que já estão funcionando. Reutilize `ItemPedido` e `Produto`; não crie outro cadastro nem duplique a quantidade em uma nova lista.

Complete a ideia da regra:

> Para cada item, a quantidade pedida deve ser __Menor ou igual__
> que a quantidade disponível no produto.

Registre os testes feitos:

| Saldo antes | Quantidade pedida | Resultado esperado | Resultado obtido |
|---:|---:|---|---|
| 5 | 3 | 2 | 2 |
| 4 | 4 | 0 | 0 |
| 3 | 4 | Não permite a compra | Não pemritiu e manteve os 3 no estoque |

Registre onde fez a alteração:

**Arquivo:** __LojaService__  **Método:** __finalizarCompra()__

Cole abaixo o trecho de validação que você acrescentou:

```java
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
```

## Exercício 3 — Atualize depois da validação

Integre a baixa do estoque ao fluxo que já existe. Primeiro, todas as validações do pedido precisam passar. Depois, processe o pagamento, atualize os saldos e registre o pedido. Uma falha não pode deixar cobrança, saldo alterado ou pedido no histórico.

Organize as etapas na ordem escolhida:

| Etapa | Número da ordem |
|---|---:|
| Conferir o saldo de todos os itens | 1 |
| Processar o pagamento | 2 |
| Atualizar o saldo dos produtos | 3 |
| Registrar o pedido finalizado | 4 |

Agora teste uma falha. Para a situação abaixo, registre o estado antes e depois da tentativa:

| Situação | Antes da tentativa | Depois da tentativa |
|---|---|---|
| Saldo: 3; pedido: 4 unidades | Saldo: __3__; pagamento: __4__; pedidos registrados: __0__ | Saldo: __3__; pagamento: __0__; pedidos registrados: __0__ |

**Como você confirmou que a tentativa recusada não alterou o estoque nem registrou o pedido?**

__Tentei finalizar um pedido de 4 unidades quando havia apenas 3 em estoque. A validação bloqueou a compra antes do processamento do pagamento. Depois, conferi que o estoque continuava com 3 unidades e que nenhum pedido novo foi registrado no histórico.__

## Exercício 4 — Teste de regressão

Execute cada cenário na sua própria `Main`. Além da nova regra de estoque, confirme que a validação de pagamento da Aula 12 continua funcionando. Registre o resultado esperado e o observado.

| Cenário | Resultado esperado | Resultado observado | Passou? |
|---|---|---|---|
| Saldo 5; pedido 3 | 2 | Ele efetuou a compra corretamente | Sim |
| Saldo 4; pedido 4 | 0 | Efetouu a compra corretamente | Sim |
| Saldo 3; pedido 4 | 3 | Não deixou a compra ser efetuada | Sim |
| Pagamento com valor-base diferente do total | Não permite comprar | Ele não deixa a compra ser feita  e não registra um pedido | Sim |
| Quantidade zero ao criar `ItemPedido` | Não permite comprar sem quantidade | não deixa a compra ser concluida | Sim |

Se algum teste falhou, descreva o comportamento encontrado e a correção necessária:

______________________________________________________________________________

______________________________________________________________________________

### Desafio extra — O mesmo produto em duas linhas

Considere um produto com saldo 5 e um pedido com duas linhas do mesmo produto: 3 unidades em uma linha e 3 na outra. A validação simples de cada linha, isoladamente, seria suficiente? Explique o risco e proponha como o Service poderia considerar a quantidade total pedida daquele produto.

__Não. A validação de cada linha isoladamente não seria suficiente, pois cada linha pede 3 unidades e o estoque possui 5. Individualmente, ambas passam na validação, mas o total solicitado é de 6 unidades, ultrapassando o estoque em 1 unidade.__

__O Service deve somar as quantidades de todas as linhas que possuem o mesmo produto e comparar o total com o estoque disponível. Nesse exemplo, 3+3=6, então a compra deve ser bloqueada, pois há apenas 5 unidades disponíveis.__

## Entrega pelo Classroom

Envie esta ficha preenchida e anexe:

- [x] `LojaService.java` atualizado, preservando as validações que já existiam.
- [x] A `Main` usada para testar os cenários.
- [x] Evidências ou registros dos testes com compra aceita, no limite e recusada.
- [x] As respostas desta ficha, incluindo o teste de pagamento divergente.
- [x] Se resolveu o desafio extra, a explicação da estratégia usada.

**Em uma frase: qual regra da operação o `LojaService` passou a proteger?**

__A regra de estoque, já que um poduto que não existe não pode ser vendido__