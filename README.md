# PLANO DE ESTUDOS - JAVA

###### Por: [@Caiuzu](https://github.com/Caiuzu)

**JAVA:** _Solução E-commerce._

Neste projeto prático iremos desenvolver uma solução de e-commerce com a arquitetura de microsserviços e aplicar a 
integração entre eles orientada a eventos com Apache Kafka e garantir a compatibilidade entre da comunicação dos 
microsserviços com Schema Registry. Para isso, programaremos em Java utilizando a stack do Spring 
(Spring Boot, Spring Cloud Streams).


**#Microsservice #Spring #Kafka #Avro #SchemaRegistry**


[Primeiros passos para desenvolver um Projeto:]()
---

### 1 - Instalando e configurando o ambiente:

#### Instalando Java :

- Instalação Windows:
    - Primeiramente baixar e instalar https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
    - Após o término da instalação vamos configurar as variáveis do ambiente:
      - Clique com o botão direito em cima do ícone “Meu Computador”;
      - Na barra de busca procure por “Variáveis de ambiente” e acesse "Editar Variaveis de Ambiente do Sistema";
      - Clique no botão “Novo...” em “Variáveis de Ambiente”;
        - Nome da variável: `JAVA_HOME`
        - Valor da variável: coloque aqui o endereço de instalação (o caminho tem que ser o de instalação) 
          C:\Arquivos de programas\Java\jdk1.8.0
        - Clique em OK
    - Clique novamente no botão `Nova` em Variáveis do sistema;
      - Nome da variável: CLASSPATH
      - Os valores da variável encontram-se abaixo, sempre insira um do lado outro sem espaços e com o `;` 
        (ponto e vírgula) no final.

  ```
    ;%JAVA_HOME%\lib;%JAVA_HOME%\lib\tools.jar;%JAVA_HOME%\lib\dt.jar;
    %JAVA_HOME%\lib\htmlconverter.jar;%JAVA_HOME%\jre\lib;%JAVA_HOME%\jre\lib\rt.jar;
  ```
    - Selecione a variável PATH em Variáveis do sistema e clique no botão Editar.
      - Defina o valor dessa variável com o caminho da pasta Bin. No caso, pode-se utilizar a variável JAVA_HOME 
        previamente definida.
      ```
        ;%JAVA_HOME%\bin
      ```
  - Por fim, vamos verificar com `javac -version` e `java -version`

#### Instalando Maven:
  - Baixe: https://maven.apache.org/download.cgi 
    - apache-maven-3.8.1-bin.zip
  - Extrair os arquivos por exemplo no diretório raiz C:
  - Addicionar nas variaveis de ambiente, em PATH com a localização do bin, por exemplo: C:\Program Files\apache-maven-3.8.1\bin
  - Em seguida vamos testar no terminal com o comando: `gradle -v`
    
#### Instalando Gradle:
  - Baixe: https://gradle.org/releases/ 
    - [binary-only - gradle-7.0-bin.zip](https://gradle.org/next-steps/?version=7.0&format=bin)
- Extrair os arquivos por exemplo no diretório raiz C:
- Addicionar nas variaveis de ambiente, em PATH com a localização do bin, por exemplo: C:\Program Files\gradle-7.0\bin
- Em seguida vamos testar no terminal com o comando: `gradle -v`
    
----
- Instalação Linux - SDKman:

  - Install SDK Man:
     ```shell
      apt-get install curl
      
      curl -s "https://get.sdkman.io" | bash
      
      source "/home/user/.sdkman/bin/sdkman-init.sh"
     ```  

  - Install Java 8:
     ```shell
      sdk install java 8u272-albba
    
      sdk list java
     ```
    
  - Install maven:
     ```shell
      sdk install maven
     ```    
    
  - Install gradle:
     ```shell
      sdk install gradle
     ```
-----------------
### 2 - Stack e Definições:
- **Domínios:**
    ```
      ┌────────────────────────────────┐
      │          E-COMMERCE            │
      │ ┌──────────┐     ┌───────────┐ │
      │ │ CHECKOUT │     │  PAYMENT  │ │
      │ └──────────┘     └───────────┘ │
      └────────────────────────────────┘
    ```   

    - `[checkout]`: Guarda as informações de checkout (cartão de crédito/débito, dados do usuário).
    - `[payment]`: Possuí a responsabilidade de cobrar do cartão com o valor de uma compra XPTO.
    
- **Arquitetura:**
    - `[Checkout]`:
        - checkout-front: Uma tela simples, com um botão comprar
        - checkout-api: Ao receber o evento de comprar enviará um evento ao kafka
    - `[Payment]`:
        - payment-api: Ao receber o evento do kafka faz o pagamento e devolve outro evento ao kafka.
    
    - **Explicação:** Abaixo, checkout-api, irá gerar um evento para o kafka, onde payment-api estará escutando.
        Ao finaliza o processamento de pagamento, payment-api irá retonar outro evento para o kafka onde checkout-api, irá escutar.

    ```
      ┌──────────────┐   ┌────────────┐   ┌─────┐   ┌───────────┐
      │checkout-front├──►│checkout-api├──►│kafka├──►│payment-api│
      └──────────────┘   └───────────▲┘   └┬───▲┘   └┬──────────┘
                                     └─────┘   └─────┘
    ```

- **Apache Kafka;**
  
    - Primeiramente, temos que entender oque é **Streaming Data:** É basicamente um fluxo contínuo de dados, como um rio
      - Para quê e por queê utiliza: Possuí a capacidade de coletar, processar e armazenar um grande volume de dados em tempo real.
      Alta disponibilidade dos dados e confiabilidade.
        
        ````
        ┌────────────────────────────────────────────────────────────────┐
        │  ┌────────────┐                             ┌──────────────┐   │
        │  │ Produtor 1 ├─────┐                 ┌────►│ Consumidor 1 │   │
        │  └────────────┘     │                 │     └──────────────┘   │
        │                     │                 │                        │
        │  ┌────────────┐     │    ┌────────┐   │     ┌──────────────┐   │
        │  │ Produtor 2 ├─────┼───►│ Broker ├───┼────►│ Consumidor 2 │   │
        │  └────────────┘     │    └────────┘   │     └──────────────┘   │
        │                     │                 │                        │
        │  ┌────────────┐     │                 │     ┌──────────────┐   │
        │  │ Produtor N ├─────┘                 └────►│ Consumidor N │   │
        │  └────────────┘                             └──────────────┘   │
        └────────────────────────────────────────────────────────────────┘
        Os produtores irão produzir os informações para um broker e disponibilizá-los 
        para os consumidores.
        ````
    - O **Apache Kafka**, é uma plataforma distribuída de mensagens e streaming. 
      Diferente do Redis, rabbitMQ, que são sistemas de mensagerias.
      - A ideia do streaming no kafka é o mesmo de um broadcast com TCP em redes, ele replica para outros IPs, 
        mas só quem está pronto para recebe-lo irá consumir o dado.
    
        ```
        ┌───────────────────────────────────────────────────────────────┐
        │                         Producers                             │
        │                     ┌─────┐                                   │
        │                     │ APP ├───┐                               │
        │                     └─────┘   │          ┌─────┐              │
        │                               │      ┌──►│ APP │              │
        │              ┌────┐           │      │   └─────┘              │
        │              │ DB ├──┐        ▼      │                        │
        │              └────┘  │   ┌────────┐◄─┘     Stream             │
        │         Connector    ├──►│ Broker │      Processors           │
        │              ┌────┐  │   └────────┘◄─┐                        │
        │              │ DB ├──┘        ▲      │                        │
        │              └────┘           │      │   ┌─────┐              │
        │                               │      └──►│ APP │              │
        │                     ┌─────┐   │          └─────┘              │
        │                     │ APP │◄──┘                               │
        │                     └─────┘                                   │
        │                        Consumers                              │
        └───────────────────────────────────────────────────────────────┘
        ```
- **Conceitos**:
    - `Connectors`: conseguimos conectar o banco e disparar por exemplo um evento sempre que for feito um insert.
    - `Mensagens`: A informação produzida pelo produtor
    - `Tópicos`: Meio por onde o produtor vai postar a mensagem e o consumidor consumirá
      - Pode ser formado por _N_ partições. Quando um produtor publica uma mensagem, vai para uma dada partição
        - Cada partição possuí uma ordem de mensagens, de forma que conseguimos garantir a ordem das partições, mas não dos itens dentro destas.
            ```      
            Partição 1 -> [1] [2] [3] [4] [5] [6] [7]
            Partição 2 -> [1] [2] [3] [4] [5]
            Partição 3 -> [1] [2] [3] [4] [5] [6]
            ```  
          - Cada `offset`, como é chamada cada posição dentro da partição, em determinado ciclo de vida da aplicação, 
          será lido por mais de um consumidor, mesmo que este não seja quem irá consumi-lo. 
          Lembrando que todas as mensagens produzidas podem ser escutadas por todos os consumidores.</br></br>
            
    - `Produtores`: quem produz a mensagem;
    - `Consumidores`: quem consome a mensagem;
    - `Brokers`: As instâncias do Kafka;
    - `Clusters`: Conjuntos de Brokers;
      - Quando formamos um Cluster Kafka, conseguimos criar mais de um servidor e conectar a escuta em um grupo de 
        consumidores específicos para cada servidor.
    - `Apache Zookeeper`: Gerenciador de Clusters;
      - Abaixo temos uma formação de um cluster com 4 brokers, para administrar todos estes, utiliza-se o Zookeeper. 
        - Uma exemplo de organização possível, é a nomeação automática de quem será master/slave.
        - O Kafka utiliza o Zookeeper para sincronizar as configurações entre diferentes clusters.
      ```
                                    Cluster
        ┌─────────────────────────────────────────────────────────────┐
        │                                                             │
        │  ┌──────────┐   ┌──────────┐   ┌──────────┐   ┌──────────┐  │
        │  │ Broker 1 │   │ Broker 2 │   │ Broker 3 │   │ Broker 4 │  │
        │  └────┬─────┘   └───┬──────┘   └─────┬────┘   └────┬─────┘  │
        │       │             │                │             │        │
        └───────┼─────────────┼────────────────┼─────────────┼────────┘
                │             └───────┌────────┘             │
                │                     │                      │
        ┌───────▼─────────────────────▼──────────────────────▼────────┐
        │                         Zookeper                            │
        └─────────────────────────────────────────────────────────────┘
      ```
- **Apache Avro**:
  - Quando tratamos de eventos, podemos ter qualquer tipo de mensagem (string, xml, json). 
    Porém, em um contrato para as comunicaçãos apiREST, utilizamos JSON.
    - O Apache Avro, é basicamente um sistema de serialização de dados que trabalha com JSON;
    - Fornece uma rica estrutura de dados;
    - Oferece um formado de dado binário, compacto e rápido;
    - É um container para gravar dados persistentes.
    - Quem define o Avro é um JSON com propriedades (schema), por exemplo:
    ```json
    {
        "namespace": "com.exemple.avro",
        "type": "record",
        "name": "User",
        "fields":[
            {"name": "name", "type": "string"},
            {"name": "favorite_number", "type": ["int", "null"]},
            {"name": "favorite_color", "type": ["string", "null"]}
        ]
    }
    ```
    
    ```json
    {
      "name": "Fulando Ciclano",
      "favorite_number": 7,
      "favorite_color": "purple"
    }
    ```
- **Schema Registry**: 
  - É uma camada distribuída de armazenamento para Schemas Avro, o qual usa o Kafka como mecanismo de armazenamento;
  - Responsável por gravar todos schemas avro e fazer a verificação de 
  compatibilidade, antes de postar uma mensagem pelo produtor e antes de consumir pelo consumidor;

- **Serviços de Stream**
  - Microsfot Event Hub
  - Amazon Kinesis
  - Google Pub/Sub
  - Kafka Local com Docker


- **Stack**:
  - Back:
     - Spring Boot: Para facilitar o processo de configuração e publicação de aplicações
     - Spring Web: Para expor end-poins via rest
     - Spring Cloud Stream: Para nosso kafka
     - Spring Cloud Sleuth: Para gerar os logs da aplicação de forma mais organizada
   
  - Front:
    - html
    - Bootstrap
---      
### Inicializando o projeto:
Vamos inicializar nosso projeto através do [spring initializr](http://start.spring.io) e utilizaremos 
os parâmetros abaixo:

- **Project**: Gradle Project;
- **Language**: Java;
- **Spring Boot**: 2.4.5;
- **Project Metadata**:
    - **Group**: br.com.ecommerce
    - **Articfact**: checkout
    - **Name**: checkout
    - **Description**: Checkout API
    - **Packege name**: br.com.ecommerce.checkout
    - **Packaging**: jar
    - **Java**: 8
- **Dependencies**: Spring Web, Sleuth, Cloud Stream, Stream for Apache Kafka Streams, Spring Data JPA, PostgreSQL Driver