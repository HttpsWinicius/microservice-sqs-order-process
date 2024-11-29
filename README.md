# Microsserviço de Processamento de Pedidos e Armazenamento em Banco de Dados

Este microsserviço é responsável por ler os pedidos de uma fila FIFO do Amazon SQS, processar os dados (calcular valores e quantidades) e armazená-los em um banco de dados PostgreSQL com particionamento horizontal (sharding).

[Baixar arquitetura do Projeto](https://drive.google.com/file/d/1x9fSmHhTxlP0gniZMxffmVUWQG_h49ug/view?usp=sharing)
## Tecnologias Utilizadas

- **Java 17+**
- **Spring Boot 2.7.x**
- **AWS SDK para Java (SQS)**
- **JPA (Hibernate) para acesso ao banco de dados**
- **PostgreSQL com particionamento horizontal**
- **Maven** para gerenciamento de dependências

## Funcionalidades

- Consome pedidos da fila SQS FIFO.
- Processa os pedidos calculando o valor total e a quantidade de itens.
- Salva os dados no banco de dados PostgreSQL utilizando JPA/Hibernate.
- Utiliza particionamento horizontal no PostgreSQL para escalabilidade.

## Pré-Requisitos

Antes de executar o microsserviço, certifique-se de que você tenha o seguinte configurado:

1. **AWS Account**: Você deve ter uma conta na AWS com permissões para acessar a fila SQS.
2. **Java 17+**: A versão do Java deve ser 17 ou superior.
3. **Maven**: O Maven deve estar instalado para o gerenciamento de dependências.
4. **Credenciais da AWS**: Configure suas credenciais da AWS no seu ambiente local, utilizando o AWS CLI ou variáveis de ambiente.
5. **Banco de Dados PostgreSQL com particionamento horizontal**: Configure o banco de dados PostgreSQL para suportar particionamento horizontal.

## Configuração do Banco de Dados PostgreSQL

Este microsserviço utiliza particionamento horizontal para escalar o banco de dados e garantir performance.

