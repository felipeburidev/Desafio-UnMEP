# Desafio Tecnico - Analise de Timesheet (UnDesk / UnMEP)

Uma aplicacao em Java desenvolvida para o processamento e analise de registros de tempo (timesheet). O sistema realiza a leitura de dados a partir de um arquivo `data.json`, aplica as regras de negocio exigidas para filtragem, agrupamento e ordenacao, e gera um relatorio analitico deterministico em formato JSON (`result.json`).

## Arquitetura e Estrutura do Codigo

O projeto foi organizado de forma modular, separando a logica de orquestracao, a leitura de dados e a representacao do dominio. A arquitetura foi pensada para garantir a geracao do output com a estrutura exata do gabarito fornecido.

### 1. Classes Principais
* **`Main.java`**: Ponto de entrada da aplicacao. Concentra a logica de processamento de dados utilizando a Streams API do Java para realizacao das agregacoes. Responsabilidades:
  * Filtragem de registros invalidos (minutos <= 0).
  * Agrupamento e soma de horas por tarefa e por usuario.
  * Calculos de percentuais e definicao de rankings (Top tarefas, Top funcionarios e usuario com mais tarefas distintas).
  * Ordenacao deterministica de todos os conjuntos de dados.
  * Escrita final do arquivo `result.json`.
* **`JsonReader.java`**: Classe utilitaria dedicada exclusivamente a I/O e desserializacao. Utiliza a biblioteca Jackson (`ObjectMapper`) para ler o arquivo `data.json` local e mapear o conteudo para uma lista de objetos manipulaveis pela aplicacao.

### 2. Modelagem de Dados (Entradas)
* **`ModelJson.java`**: Representa o schema de entrada dos dados. Mapeia fielmente os campos fornecidos no arquivo `data.json` original (como `userId`, `userName`, `taskId`, `minutes`, etc.), servindo de base para as operacoes de filtragem e agrupamento.

### 3. Modelagem de Dados (Saidas / DTOs)
Para garantir que a serializacao final do JSON corresponda estritamente ao gabarito, foram criadas classes isoladas que representam cada no do JSON de resposta:
* **`Result.java`**: A classe raiz que consolida todo o relatorio. Contem os atributos principais (totalMinutes, ignoredRecords) e as listas referentes aos sub-relatorios.
* **`TaskSummary.java` e `TaskOutput.java`**: Classes de apoio para armazenar e formatar o total de minutos e o nome associado a cada tarefa.
* **`TaskPercentageOutput.java`**: Formata e encapsula o percentual de tempo dedicado a uma tarefa especifica em relacao ao total geral.
* **`UserSummary.java`**: Consolida o total de horas trabalhadas de forma individualizada para a criacao do ranking de funcionarios.
* **`UserTaskStats.java`**: Responsavel por armazenar a lista de IDs de tarefas unicas de um usuario para determinar quem trabalhou na maior variedade de projetos.

## Tecnologias Utilizadas

* **Java**: Lógica principal, com uso extensivo de `java.util.stream` e `Collections` para as regras de ordenacao.
* **Jackson (com.fasterxml.jackson)**: Biblioteca padrao do ecosistema Java para processamento eficiente de JSON.
* **Docker & Docker Compose**: Ferramentas de containerizacao para garantir um ambiente isolado e padronizado de execucao.

## Como Executar

A aplicacao foi projetada para ser executada exclusivamente de forma containerizada, dispensando qualquer configuracao local do ambiente de desenvolvimento.

1. Clone este repositorio:
```bash
git clone [https://github.com/seu-usuario/seu-repositorio.git](https://github.com/seu-usuario/seu-repositorio.git)
cd seu-repositorio
