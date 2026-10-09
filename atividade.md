# UC6 — Questionário da Aula 1: exploração inicial da base

**Projeto Integrador:** análise de dados de uma clínica fictícia  
**Entrega:** individual  
**Formato:** preencher este arquivo e entregar na pasta individual do projeto.

## Identificação

- Nome: Matheus Epifanio de Souza
- Turma: TDS 
- Data: 04/09/26

## 1. Acesso à base

1. Você conseguiu conectar-se ao PostgreSQL e executar o dump?

   - (x) Sim
   - ( ) Não — descreva o problema encontrado:

2. Qual é o nome do schema criado pelo dump?

   Resposta: clinica_pi_uc6

3. Inclua uma captura de tela que mostre a conexão e o schema aberto no DBeaver.

   Evidência: *(cole a imagem ou informe o nome do arquivo salvo em `evidencias/`)*![alt text](image.png)

## 2. Inventário inicial das tabelas

Preencha a tabela abaixo a partir da sua exploração no banco.

| Tabela | O que você entende que ela armazena? | Chave primária | Relacionamento percebido |
|---|---------------------|---|---|
| `especialidade` |  armazena a especialidade do profissional e a descrição da especialidade | id_especialidade | relaciona com a tabela de profissionais |
| `convenio` | armazena o nome do convenio e o tipo dele tambem, alem de o status se esta ativo ou não | id_convenio | ele possui relacionamento com paciente |
| `consultorio` | armazena o nome do consulorio, o andar onde se localiza, a capacidade e se esta ativo | id_consultorio | possui relacionamento com a tabela consultas |
| `profissional` | armazena o registro do profissional, o nome, o id da especialidade, a data de admissao e se esta ativo ou não | id_profissional | possui relacionamento com especialidade e com consulta |
| `paciente` |  armazena o nome, data de nascimento, sexo, cidade, o id de convenio, a data de cadastro e se esta ativo | id_paciente | tem relacionamento com consulta e convenio |
| `consulta` |  armazena o id de paciente, profissional, consultorio a data de agendamento, a data e hora da consulta, o status, tipo de atendimento, o valor cobrado e o incio do atendimento com o fim do atendimento e uma observação | id_consulta | tem relacionamento com o consultorio, paciente e profissional |

## 3. Exploração dos registros

1. Escolha duas tabelas principais e escreva abaixo o comando SQL que você utilizou para consultar cinco registros de cada uma.

**Tabela 1:**

```sql
-- seu comando aqui
```

**O que você observou nos resultados?**

Resposta: 
Observei do dados de cidade do paciente, com id, nome, sexo, cidade.  

**Tabela 2:**

```sql
-- seu comando aqui
```

**O que você observou nos resultados?**

Resposta:
Observei dos dados da consulta, id do paciente e do medico, nome do paciente e do medico, especialidade do medico, consultorio, data e valor da consulta 

2. Escolha uma coluna que pareça importante para uma análise futura. Explique por quê.

Resposta:
A coluna "observacao" de consulta, pode ser importante para analisar se já ocorreu a consulta ou se teve lagum imprevisto. 

## 4. Perguntas de análise

Escreva três perguntas que a gestão da clínica poderia responder usando os dados disponíveis. Não responda às perguntas ainda: nesta etapa, o foco é formular perguntas úteis e indicar quais dados seriam necessários.

| Pergunta de negócio | Tabelas e campos que podem ajudar a respondê-la | Por que essa resposta seria útil para a clínica? |
|---|---|---|
| 1.  Quantos paciente não compareceram em suas consultas?  |Tabela consultas e coluna observacao.| porque podem analisar quantos pacientes desistiram de suas consultas ultimamente e identificar um padrão. |
| 2.  Qual consultório possui mais atendimentos? | tabela consulta e o nome do consultorio  | pode analisar qual consultorio se repete mais vezes e possui mais atendimentos |
| 3. Qual o tipo de convenio mais usados pelos pacientes? | tabela paciente e convenio  | é possivel analisar quais os convenios de cada paciente e em qual deles esta sendo mais utilizado |

## 5. Organização do projeto

Confirme a criação das pastas abaixo no seu projeto individual:

- [x] `documentacao/`
- [x] `sql/`
- [x] `evidencias/`
- [x] `java/`

## Checklist antes da entrega

- [x] Identificação preenchida.
- [x] Schema e conexão comprovados por evidência.
- [x] Inventário das seis tabelas preenchido.
- [ ] Dois comandos SQL registrados.
- [x] Três perguntas de análise propostas.
- [x] Pastas iniciais do projeto criadas.