# Aula 4 — Perguntas de análise e primeiras consultas SQL

**Nome:** Matheus Epifanio de Souza
**Turma:** TDS sala 102 
**Data:** 25 / 09 / 2026

## Objetivo de hoje

Hoje você vai formular perguntas sobre a clínica e usar SQL para respondê-las no PostgreSQL.

A primeira pergunta e sua query já estão prontas como exemplo. Depois de executá-las e entendê-las, você criará perguntas e consultas próprias.

## Antes de começar

Use o dicionário de dados, o DER e a documentação técnica produzidos nas aulas anteriores. A tabela `consulta` concentra os registros de agendamento e atendimento; ela se relaciona com `paciente`, `profissional`, `especialidade`, `convenio` e `consultorio`.

Uma boa pergunta de análise:

- ajuda alguém da clínica a tomar uma decisão;
- pode ser respondida pela base disponível;
- deixa claro o período, o critério ou a comparação quando necessário;
- prevê como o resultado deve ser apresentado.

Exemplo: em vez de escrever “ver as consultas”, formule “Quais especialidades tiveram mais consultas realizadas no período analisado?”

## Exercício 1 — Perguntas de análise

Observe a pergunta-modelo e complete mais quatro perguntas. Elas precisam ser respondíveis pela base disponível.

| Nº | Tema | Pergunta de análise | Objetivo gerencial | Tabelas e campos que podem ajudar | Filtros ou regras a considerar | Resultado esperado | Recurso SQL provável |
|---:|---|---|---|---|---|---|---|
| 1 | Especialidades e atendimentos | Quais especialidades tiveram mais consultas realizadas? | Identificar onde existe maior volume de atendimentos concluídos. | `consulta`, `profissional`, `especialidade`; status, id_profissional e nome. | Considerar somente `status = 'REALIZADA'`. | Lista de especialidades ordenada da maior para a menor quantidade de consultas. | `JOIN`, `WHERE`, `COUNT`, `GROUP BY`, `ORDER BY` |
| 2 | Faltas | Quantos pacientes faltaram as consultas? | Identificar onde informa quantos pacientes faltaram as consultas | consulta; obervação, inicio_atendimento paciente  | Considerar se a consulta não ocoreu | Lista de consultas em que não foram efetuadas | SELECT, WHERE |
| 3 | Pacientes ou convênios | Quantos pacientes possuem o convenio 2? | Identificar a quantidade de pacientes possuem o conveio 2 | pacinete, `convenio, id_convenio | Considerar nome do convenio e id_convenio 2 | Quantidade total de pacientes vinculados ao convênio 2. | WHERE, COUNT |
| 4 | Recorrência ou profissionais | Quais profissionais realizaram mais consultas? | Identificar quais profissionais efetuaram amsi consultas | consulta, profissional; id_profissional, nome, status | Considerar somente consultas com status = 'REALIZADA'. | Lista de profissionais ordenada pela quantidade de consultas realizadas. | WHERE, COUNT, GROUP BY, ORDER BY |
| 5 | Tema livre — consultórios | Quantas consultas foram realizadas em cada consultório? | Identificar quais consultorios são mais utilizados | consulta, consultorio; id_consultorio, nome ou número do consultório, status | Considerar somente consultas com status = 'REALIZADA'. | Lista de consultórios com a quantidade de consultas realizadas em cada um. | WHERE, COUNT, GROUP BY, ORDER BY |

No último campo, indique uma possibilidade como `WHERE`, `JOIN`, `COUNT`, `SUM`, `AVG`, `GROUP BY`, ordenação ou `VIEW`.

## Exercício 2 — SQL no DBeaver

### Parte A — Execute e leia a query-modelo

Copie e execute a consulta abaixo. Ela responde à pergunta 1: **Quais especialidades tiveram mais consultas realizadas?**

```sql
SET search_path TO clinica_pi_uc6, public;

SELECT
    e.nome AS especialidade,
    COUNT(c.id_consulta) AS total_consultas_realizadas
FROM consulta AS c
JOIN profissional AS p
    ON p.id_profissional = c.id_profissional
JOIN especialidade AS e
    ON e.id_especialidade = p.id_especialidade
WHERE c.status = 'REALIZADA'
GROUP BY e.nome
ORDER BY total_consultas_realizadas DESC;
```

Responda:

1. Qual especialidade aparece em primeiro lugar? __Clinica Geral__
2. Quantas consultas realizadas ela possui? __255__
3. Por que a consulta usa `JOIN`? __Porque as informações estão em duas tabelas diferentes__
4. Por que a consulta usa `WHERE c.status = 'REALIZADA'`? __Porque é são apenas consultas já realizadas, se não tiver esse WHERE todas as consultas aparecerão incluindo as que não foram realizadas ainda__
5. O que `GROUP BY e.nome` faz neste resultado? __Agrupa todos que possuem o mesmo nome de especialidade permitindo somar as consultas__

### Parte B — Escreva suas consultas

Escolha **três** perguntas que você criou no Exercício 1. Para cada uma, escreva e execute uma query no DBeaver. Depois, copie a versão que funcionou para o arquivo `modelo-consultas-analiticas-aula-05.sql` ou para um novo arquivo `.sql` com seu nome.

| Consulta | Número da pergunta | Query executada e salva? | Conclusão em uma ou duas frases |
|---:|---:|---|---|
| 1 | 2 | [ x ] Sim | 48 pacientes faltam nas consultas |
| 2 | 3 | [ x ] Sim | 32 pacientes possuem o convenio 2 "Vida Plena" |
| 3 | 5 | [ x ] Sim | Consultorio 101 possui mais consultas, 252 consultas realizadas |

## Checklist de entrega

- [ x ] Analisei a pergunta-modelo e executei a query fornecida.
- [ x ] Respondi às perguntas sobre o resultado da query-modelo.
- [ x ] Completei quatro perguntas próprias e respondíveis pela base.
- [ x ] Escrevi e executei pelo menos três consultas SQL próprias.
- [ x ] Registrei uma conclusão para cada consulta própria.
- [ x ] Revisei a escrita e identifiquei o arquivo com meu nome.

## Entrega

Entregue este arquivo preenchido e seu arquivo `.sql` no Classroom, conforme a orientação do docente. Guarde os dois: eles serão ampliados na Aula 5.