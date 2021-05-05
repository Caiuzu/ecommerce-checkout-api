# PLANO DE ESTUDOS - JAVA
![GitHub repo size](https://img.shields.io/github/repo-size/Caiuzu/ecommerce-checkout-api)
![ViewCount](https://views.whatilearened.today/views/github/Caiuzu/ecommerce-checkout-api.svg)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=Caiuzu_ecommerce-checkout-api&metric=bugs)](https://sonarcloud.io/dashboard?id=Caiuzu_ecommerce-checkout-api)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=Caiuzu_ecommerce-checkout-api&metric=code_smells)](https://sonarcloud.io/dashboard?id=Caiuzu_ecommerce-checkout-api)
[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=Caiuzu_ecommerce-checkout-api&metric=ncloc)](https://sonarcloud.io/dashboard?id=Caiuzu_ecommerce-checkout-api)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=Caiuzu_ecommerce-checkout-api&metric=alert_status)](https://sonarcloud.io/dashboard?id=Caiuzu_ecommerce-checkout-api)

###### Por: [@Caiuzu](https://github.com/Caiuzu)

**JAVA**: _Solução E-commerce._

Neste projeto prático iremos desenvolver uma solução de e-commerce com a arquitetura de microservices e aplicar a 
integração entre eles orientada a eventos com Apache Kafka e garantir a compatibilidade entre da comunicação dos 
microservices com Schema Registry. Para isso, programaremos em Java utilizando a stack do Spring 
(Spring Boot, Spring Cloud Streams).


**#Microsservice #Spring #Kafka #Avro #SchemaRegistry**


[Primeiros passos para desenvolver um Projeto:]()

----


### 1 - Instalando e configurando o ambiente:

#### Instalando Java :

- Instalação Windows:
    - Primeiramente baixar e instalar https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
    - Após o término da instalação vamos configurar as variáveis do ambiente:
      - Clique com o botão direito em cima do ícone “Meu Computador”;
      - Na barra de busca procure por “Variáveis de ambiente” e acesse "Editar Variáveis de Ambiente do Sistema";
      - Clique no botão “Novo…” em “Variáveis de Ambiente”;
        - Nome da variável: `JAVA_HOME`
        - Valor da variável: coloque aqui o endereço de instalação (o caminho tem que ser o de instalação) 
          `C:\Arquivos de programas\Java\jdk1.8.0`
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
      - Defina o valor dessa variável com o caminho da pasta Bin. No caso, pode-se utilizar a variável `JAVA_HOME` 
        previamente definida.
      ```
        ;%JAVA_HOME%\bin
      ```
  - Por fim, vamos verificar com `javac -version` e `java -version`

#### Instalando Maven:
  - Baixe: https://maven.apache.org/download.cgi 
    - apache-maven-3.8.1-bin.zip
  - Extrair os arquivos, por exemplo, no diretório, raiz: `C:`
  - Adicionar nas variáveis de ambiente, em PATH com a localização do bin, por exemplo: `C:\Program Files\apache-maven-3.8.1\bin`
  - Em seguida vamos testar no terminal com o comando: `gradle -v`
    
#### Instalando Gradle:
  - Baixe: https://gradle.org/releases/ 
    - [binary-only - gradle-7.0-bin.zip](https://gradle.org/next-steps/?version=7.0&format=bin)
- Extrair os arquivos, por exemplo, no diretório root `C:`
- Adicionar nas variáveis de ambiente, em PATH com a localização do bin, por exemplo: `C:\Program Files\gradle-7.0\bin`
- Em seguida vamos testar no terminal com o comando: `gradle -v`

----

- Instalação Linux — SDKMAN:

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
---
### Instalando Docker (Linux/WSL2):
[Tutorial DigitalOcean](https://www.digitalocean.com/community/tutorials/how-to-install-and-use-docker-on-ubuntu-20-04)
#### 1 — Instalando Docker

- Primeiro, atualize sua lista existente de pacotes:
  ```shell
  sudo apt update
   ```

- Em seguida, instale alguns pacotes de pré-requisitos que permitem ao apt usar pacotes sobre HTTPS:
  ```shell
  sudo apt install apt-transport-https ca-certificates curl software-properties-common
   ```

- Em seguida, adicione a chave GPG para o repositório oficial do Docker ao seu sistema:
  ```shell
  curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -
   ```

- Adicione o repositório Docker às fontes APT:
  ```shell
  sudo add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu focal stable"
   ```

- Em seguida, atualize o banco de dados de pacotes com os pacotes Docker do repo recém-adicionado:
  ```shell
  sudo apt update
   ```

- Certifique-se de que está prestes a instalar a partir do repositório Docker em vez do repositório Ubuntu padrão:
  ```shell
  apt-cache policy docker-ce
  ```

  _Observe que docker-ce não está instalado, mas o candidato para instalação é do repositório Docker para Ubuntu 20.04 (focal)._
Finalmente, instale o Docker:
    ```shell
    sudo apt install docker-ce
     ```

- O Docker agora deve estar instalado, o daemon iniciado e o processo habilitado para iniciar na inicialização. Verifique se ele está funcionando:
    ```shell
    sudo systemctl status docker #para linux
  
    sudo /etc/init.d/docker status #para wsl2
    ```

#### 2 — Executando o comando Docker sem Sudo (opcional)

- Se quiser evitar digitar sudo sempre que executar o comando docker, adicione seu nome de usuário ao grupo docker:
  ```shell
  sudo usermod -aG docker ${USER}
  ```

- Para aplicar a nova associação de grupo, saia do servidor e entre novamente ou digite o seguinte:
  ```shell
  #Você será solicitado a inserir sua senha de usuário para continuar.
  su - ${USER}
   ```
- Confirme se o seu usuário foi adicionado ao grupo docker digitando:
  ```shell
  id -nG
  ```
  ```shell
  #Output
  sammy sudo docker
  ```
- listar docker:
  ```shell
  docker ps
  ```
  ```shell
  run test docker
  ```
  ```shell
  docker run hello-world
  ```
- Instale também o docker compose (utilizaremos 1.28.2)
  ```shell
  sudo curl -L "https://github.com/docker/compose/releases/download/1.28.2/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
  ```
- dando permissão de execução para docker-compose:
  ```shell
  sudo chmod +x /usr/local/bin/docker-compose
  ```
- Verificando versão:
  ```shell
  docker-compose --version
  ```

### 3 — Instalando Portainer.io
Iremos instalar o [portainer.io](https://www.portainer.io) para termos uma visualização dos containers:

- Criando volume:
  ```shell
    docker volume create portainer_data
  ```
- Instalando portainer no volume:
  ```shell
    docker run -d -p 8000:8000 -p 9000:9000 --name=portainer --restart=always -v /var/run/docker.sock:/var/run/docker.sock -v portainer_data:/data portainer/portainer-ce
  ```
- Para acessar: http://localhost:9000/

-----------------
### 2 — Stack e Definições:
###### As ilustrações abaixo foram criadas com [asciiflow](https://asciiflow.com/#/).
- **Domínios**:
    ```
      ┌────────────────────────────────┐
      │          E-COMMERCE            │
      │ ┌──────────┐     ┌───────────┐ │
      │ │ CHECKOUT │     │  PAYMENT  │ │
      │ └──────────┘     └───────────┘ │
      └────────────────────────────────┘
    ```   

    - `[checkout]`: guarda as informações de checkout (cartão de crédito/débito, dados de usuários).
    - `[payment]`: possuí a responsabilidade de cobrar do cartão com o valor de uma compra "XPTO".
    
- **Arquitetura:**
    - `[Checkout]`:
        - checkout-front: Uma tela simples, com um botão comprar
        - checkout-api: Ao receber o evento de comprar enviará um evento ao kafka
    - `[Payment]`:
        - payment-api: Ao receber o evento do kafka faz o pagamento e devolve outro evento ao kafka.
    
    - **Explicação**: abaixo, checkout-api, irá gerar um evento para o kafka, onde payment-api estará escutando.
        Ao finaliza o processamento de pagamento, payment-api irá retornar outro evento para o kafka onde checkout-api, irá escutar.

    ```
      ┌──────────────┐   ┌────────────┐   ┌─────┐   ┌───────────┐
      │checkout-front├──►│checkout-api├──►│kafka├──►│payment-api│
      └──────────────┘   └───────────▲┘   └┬───▲┘   └┬──────────┘
                                     └─────┘   └─────┘
    ```

- **Apache Kafka;**
  
    - Primeiramente, temos que entender oque é **Streaming Data:** É basicamente um fluxo contínuo de dados, como um rio
      - Para quê e por quê utiliza: possuí a capacidade de coletar, processar e armazenar um grande volume de dados em tempo real.
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
      Diferente de Redis, rabbitMQ, que são sistemas de mensagerias.
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
    - `Connectors`: conseguimos conectar o banco e disparar, por exemplo, um evento sempre que for feito um insert.
    - `Mensagens`: A informação produzida pelo produtor
    - `Tópicos`: Meio por onde o produtor vai postar a mensagem e o consumidor consumirá
      - Pode ser formado por _N_ partições. Quando um produtor, publica uma mensagem, vai para uma dada partição;
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
      - Abaixo temos uma formação de um cluster com 4 brokers, para administrar estes, utiliza-se o Zookeeper. 
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
  - Quando tratamos de eventos, podemos ter qualquer mensagem (string, xml, json). 
    Todavia, em um contrato para as comunicações apiREST, utilizamos JSON.
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
      "name": "Fulano Ciclano",
      "favorite_number": 7,
      "favorite_color": "purple"
    }
    ```
- **Schema Registry**: 
  - É uma camada distribuída de armazenamento para Schemas Avro, o qual usa o Kafka como mecanismo de armazenamento;
  - Responsável por gravar todos schemas avro e fazer a verificação de 
  compatibilidade, antes de postar uma mensagem pelo produtor e antes de consumir pelo consumidor;

- **Serviços de Stream**
  - Microsoft Event Hub
  - Amazon Kinesis
  - Google Pub/Sub
  - Kafka Local com Docker


- **Stack**:
  - Back:
     - Spring Boot: Para facilitar o processo de configuração e publicação de aplicações;
     - Spring Web: Para expor end-points via rest;
     - Spring Cloud Stream: Para nosso kafka;
     - Spring Cloud Sleuth: Para gerar os logs da aplicação de forma organizada.
   
  - Front:
    - html
    - Bootstrap
---      

### 3 — Inicializando o projeto:
### Inicializando o projeto:
Inicializaremos o nosso projeto através do [spring initializr](http://start.spring.io) utilizando os parâmetros abaixo:

- **Project**: Gradle Project;
- **Language**: Java;
- **Spring Boot**: 2.4.5;
- **Project Metadata**:
    - **Group**: br.com.ecommerce
    - **Artifact**: checkout
    - **Name**: checkout
    - **Description**: Checkout API
    - **Package name**: br.com.ecommerce.checkout
    - **Packaging**: jar
    - **Java**: 8
- **Dependencies**: Spring Web, Sleuth, Cloud Stream, Stream for Apache Kafka Streams, Spring Data JPA, PostgreSQL Driver, Lombok

----

### Configurando SonarCloud no projeto:
O **SonarCloud** é uma plataforma em nuvem para exibir o processo de inspeção continua do código de sua aplicação. 
Para isso, o SonarCloud utiliza o SonarQube para realizar a “varredura” em seu código e analisar possíveis 
vulnerabilidade, erros e regras específicas da linguagem (Code Smells).

- Para inicializarmos a integração, acessaremos [https://sonarcloud.io/projects](https://sonarcloud.io/projects)
- Na parde superior direita, no símbolo [+], click no item `analyze new project`
- Escolheremos a organização onde se encontra o repositório a ser analisado 
  (`Import another organization`, caso não haja nenhuma para selecionar direto);
- Escolheremos a opção `Manually`
- Para este projeto, em `What option best describer your build?`, selecionaremos `Gradle`
- Agora basta seguir os passos que irão aparecer: 
  - **IMPORTANTE: reiniciar a IDE após a configuração da variável de ambiente** `SONAR_TOKEN`.
  - Para projetos com Java 8, será necessário alterar o Java para 11 ou superior (EXEMPLO abaixo):
    - `set JAVA_HOME=C:\Program Files\Java\jdk1.8.0_281`
    - `set JAVA_HOME=C:\Users\Caiuzu\.jdks\adopt-openjdk-11.0.11`

----

### Configurando Swagger2 no projeto:

- Em [build.gradle](./build.gradle), adicionaremos as dependências abaixo:

    ```
    ext {
        set('swaggerVersion', "2.9.2")
    }
    
    dependencies {
        // Swagger
        implementation "io.springfox:springfox-swagger2:${springCloudVersion}"
        implementation "io.springfox:springfox-swagger-ui:${springCloudVersion}"
    }
    ```
  
- Iremos adicionar a anotação `@EnableSwagger2` em nosso 
  [main](./src/main/java/br/com/ecommerce/checkout/CheckoutApplication.java)
- Em seguida, criaremos o diretório [config](./src/main/java/br/com/ecommerce/checkout/config), 
  que será destinado a todas as configurações do nosso projeto. Dentro iremos criar a classe de configuração; 
  [SwaggerConfiguration](./src/main/java/br/com/ecommerce/checkout/config/SwaggerConfiguration.java)
    - Para funcionamento básico do swagger, devemos adicionar apenas as linhas abaixo. Para configurações adicionais, 
      podemos utilizar os outros métodos contidos na classe (Autenticação, informações sobre o projeto, etc).

    ```java
      @EnableSwagger2
      @Configuration
      public class SwaggerConfiguration {
      
          @Bean
          public Docket api() {
              return new Docket(DocumentationType.SWAGGER_2)
                      .select()
                      .apis(RequestHandlerSelectors.basePackage("br.com.ecommerce.checkout"))
                      .paths(PathSelectors.any())
                      .build();
          }
      }
    ```
  - Desta forma, já estamos prontos para o swagger através da URL: http://localhost:8080/swagger-ui.html

----

### Alterando Banner de Inicialização:
Basta criar um arquivo chamado [Banner.txt](./src/main/resources/banner.txt), no diretório [resources.](./src/main/resources)

----

### Configurando Actuator:
**Spring Boot Actuator** é um sub-projeto do Spring Boot Framework. Inclui vários recursos adicionais que nos ajudam a
monitorar e gerir o aplicativo Spring Boot. Ele usa endpoints HTTP ou beans JMX para nos permitir interagir com ele.
Expõe informações operacionais sobre o aplicativo em execução — integridade, métricas, informações, etc.

- Adicionaremos as dependências para o actuator no arquivo [build.gradle](./build.gradle):
  ```
    ext {
      set('springBootVersion', "2.4.5")
    }
  
    dependencies {
      // Spring Boot
      implementation "org.springframework.boot:spring-boot-starter-actuator:${springBootVersion}"
    }
  ```
- Em [application.yml](./src/main/resources/application.yml), iremos colocar as propriedades abaixo:
  ```yaml
   management:
    endpoint:
      health:
        enabled: true
        show-details: always
  ```

- Desta forma, o actuator estará pronto, basta acessar: http://localhost:8080/actuator/

----

### Configurando Plugin Avro Generator:
Este Plugin tem a função de facilitar a conversão de um schema em uma classe java com apenas um comando. 
Por exemplo, digamos que nosso schema esteja definido (schema exemplo abaixo), na teoria teríamos que traduzir, 
manualmente cada definição de nosso arquivo para uma classe java, tornando o processo moroso.
```json
{
  "type": "record",
  "name": "CheckoutCreateEvent",
  "namespace": "br.com.ecommerce.checkout.event",
  "fields": [
    {
      "name": "checkoutCode",
      "type": ["string", "null"]
    }
  ]
}
```

- Para configurar, primeiro, adicionaremos as dependências abaixo em nosso arquivo [build.gradle](./build.gradle):

```
  plugins {
    id 'com.commercehub.gradle.plugin.avro' version '0.99.99'
  }
  
  repositories {
    mavenCentral()
    maven {
        url 'http://packages.confluent.io/maven/'
        allowInsecureProtocol(true)
    }
  }
  
  dependencies {
    // Avro
    implementation 'io.confluent:kafka-avro-serializer:5.5.0'
  }
  
  avro {
    fieldVisibility = "PRIVATE"
  }
  
  generateAvroJava {
    source 'src/main/resources/avro'
  }
  
  generateTestAvroJava {
    source 'src/main/resources/avro'
  }
```

- Em seguida, criaremos uma pasta nomeada [avro](./src/main/resources/avro) dentro de [resources](./src/main/resources), 
na qual serão colocados nossos schemas e lidos para conversão.

- Finalmente, para efetivamente gerar a classe, basta utilizar o comando no terminal: `gradlew generateAvroJava`