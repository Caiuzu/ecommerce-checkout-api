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
    - (binary-only - gradle-7.0-bin.zip)[https://gradle.org/next-steps/?version=7.0&format=bin]
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
  - **Streaming Data:** Fluxo contínuo de dados
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
    ````
    
    
- **Zookeeper**;
- **Schema Registry**;
- **Apache Avro**;
- **Serviços de Stream**;
- **Live Coding**.


