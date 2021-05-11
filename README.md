# E-COMMERCE CHECKOUT API
![GitHub repo size](https://img.shields.io/github/repo-size/Caiuzu/ecommerce-checkout-api)
![ViewCount](https://views.whatilearened.today/views/github/Caiuzu/ecommerce-checkout-api.svg)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=Caiuzu_ecommerce-checkout-api&metric=bugs)](https://sonarcloud.io/dashboard?id=Caiuzu_ecommerce-checkout-api)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=Caiuzu_ecommerce-checkout-api&metric=code_smells)](https://sonarcloud.io/dashboard?id=Caiuzu_ecommerce-checkout-api)
[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=Caiuzu_ecommerce-checkout-api&metric=ncloc)](https://sonarcloud.io/dashboard?id=Caiuzu_ecommerce-checkout-api)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=Caiuzu_ecommerce-checkout-api&metric=alert_status)](https://sonarcloud.io/dashboard?id=Caiuzu_ecommerce-checkout-api)

###### Por: [@Caiuzu](https://github.com/Caiuzu)

**ESTUDO JAVA**: _Solução E-commerce._

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

#### 3 — Instalando Portainer.io
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

#### 4 - Resolvendo possiveis problemas com WSL2

Ao utilizarmos a docker, podemos nos deparar com a impossibilidade de conectarmos aos serviços de forma externa (pelo navegador)

- Para verificar o acesso às portas:
  ```shell
  telnet localhost 9000
  netcat -l 127.0.0.1 9000
  ps aux | grep http
  ```

- Para solucionar basta:
  - Desabilitar o Fast Boot do Windows;
  - Executar o comando no powershell: `wsl --shutdown`
  
-----------------
### 2 — Stack e Definições:
> ###### As ilustrações abaixo foram criadas com [asciiflow](https://asciiflow.com/#/).
Breve entendimento: O modelo de negócios desse projeto, o usuário vai passar pela tela onde escolhe os produtos, 
monta o carrinho. Na hora de fazer o checkout, ele simplesmente informa os dados e na hora que efetua a compra, o 
pedido não será processado no mesmo momento em que foi feito, 
aparecerá uma tela de "estamos processando seu pedido", assim como fazem a maioria dos e-commerces. 
Reservam o saldo no cartão e faz o processamento depois. Capturaremos da reserva de saldo. 
Não iremos processar nada em checkout-api. No momento em que seja feita a cobrança, manda uma notificação que a compra está aprovada. 
iremos salvar nosso checkout e então faremos com que a payment-api processe efetivamente o pagamento com os dados enviados pelo checkout.

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
> O **SonarCloud** é uma plataforma em nuvem para exibir o processo de inspeção continua do código de sua aplicação. 
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
> **Spring Boot Actuator** é um sub-projeto do Spring Boot Framework. Inclui vários recursos adicionais que nos ajudam a
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
Esse Plugin tem a função de facilitar a conversão de um schema em uma classe java com apenas um comando. 
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

---

### Configurando Docker e Docker-Compose:
>[Docker in 100 Seconds](https://www.youtube.com/watch?v=Gjnup-PuquQ)

> Antes de seguir os passos abaixo, garanta que seu docker está instalada conforme explicado no inicio deste documento.

O **docker**, é basicamente um container. Ele usa os próprios recursos do kernel de nosso SO para "simular" uma nova máquina.
Diferente de como faz uma VM (que gera um novo SO para realizar esta tarefa).

O **docker-compose** faz a orquestração desses containers. Assim, possibilitando uma infra local rápida e eficiente.

Iremos criar um diretório [docker](./docker) em nosso projeto e criaremos o arquivo de configuração
[docker-compose.yml](./docker/docker-compose.yml).

Comandos mais utilizados (antes de utiliza-los, devemos estar no diretório, no terminal):
- iniciar: `docker-compose up --build -d`
- listar containers: `docker ps`
- derrubar os container e remover: `docker-compose down`
- bonus - verificar se a porta está aberta: `telnet localhost {porta}`

#### Serviços no container:
Primeiro, temos que identificar o que queremos conteinerizar. Para este projeto serão
os seguinte itens: _Banco de Dados(database-checkout e database-payment), Zookeerper, Kafka e Schema Registry_;

Antes, precisamos entender cada linha de nosso [docker-compose.yml](./docker/docker-compose.yml)

````yaml
version: '3.7'
services:
  database-checkout:
    # image to fetch from docker hub
    image: postgres:latest

    # Environment variables for startup script
    # container will use these variables
    # to start the container with these define variables. 
    environment:
      POSTGRES_DB: checkout
      POSTGRES_USER: admin
      POSTGRES_PASSWORD: admin

    # Mapping of container port to host
    ports:
      - 5432:5432
````
`version ‘3.7’`: Isso indica que estamos usando a versão 3.7 do Docker Compose, e o Docker fornecerá os recursos apropriados.

`services`: Esta seção define todos os diferentes contêineres que criaremos. Em nosso projeto, temos cinco serviços (dois bancos, kafka, etc).

`database-checkout`: Este é o nome do nosso serviço de banco de dados. O Docker Compose criará contêineres com o nome que fornecemos.

`image`: Se não tivermos um Dockerfile e quisermos executar um serviço usando uma imagem pré-construída, especificamos o local da imagem usando a cláusula image. O Compose fará um fork de um contêiner dessa imagem.

`ports`: Isso é usado para mapear as portas do contêiner para a máquina host.

`environment`: A cláusula nos permite configurar uma variável de ambiente no contêiner. É o mesmo que o argumento -e no Docker ao executar um contêiner.
  - Os parâmetros `POSTGRES_PASSWORD`, `POSTGRES_USER`, `POSTGRES_DB`, indica ao docker, para inicializar nosso banco de dados com o usuário de conexão pré-configurado.

---
### Configurando Spring Data (Hibernate + JPA)
> **Spring Data** é um projeto SpringSource de alto nível cujo objetivo é unificar e facilitar o acesso a diferentes 
tipos de armazenamentos de persistência, tanto sistemas de banco de dados relacionais quanto armazenamentos de dados NoSQL

> O **Hibernate** é um framework ORM, ou seja, a implementação física do que você usará para persistir, remover, atualizar ou buscar dados no SGBD. Por outro lado, o **JPA (Java Persistence API)** é uma camada que descreve uma interface comum para frameworks ORM.

Utilizando as configurações que definimos para nosso banco (em nosso caso, os passados em nosso [docker-compose.yml](./docker/docker-compose.yml), para `database-checkout`), 
iremos informar ao spring os dados para conexão da seguinte forma no arquivo [application.yml](./src/main/resources/application.yml):

```yaml
spring:
  datasource:
  url: jdbc:postgresql://localhost:5432/checkout # jdbc:driver://url_de_conexão:porta/banco_de_dados
    username: admin                              # usuário do banco
    password: admin                              # senha do banco
    driver-class-name: org.postgresql.Driver     # driver
    hikari:                                      #
      connection-test-query: select 1            # consulta que será executada pouco antes de uma conexão do pool ser fornecida para validar se a conexão com o banco de dados está ativa
```
Iremos fazer configurações do hibernate jpa
```yaml
  jpa:
    hibernate:
      ddl-auto: create-drop #Cria e então destrói o schema no final da sessão.
    properties:
      hibernate:
        dialect: org.hibernate.dialect.PostgreSQLDialect
        format_sql: true
        show_sql: true
        use_sql_comments: true
        jdbc:
          lob:
            non_contextual_creation: true
```
<details>
<summary>Mais detalhes sobre os campos de configuração JPA/Hibernate:</summary>

- `jpa`:
  - `hibernate`:
      - `ddl-auto`: vai pegar a classe de entidade e irá gerar uma query de criação automaticamente de acordo com o parâmetro escolhido
        - **none** : _Não gerar automaticamente. SEMPRE USAR ESTA OPÇÃO EM PROD e as outras apenas para teste/dev/local._
        - **validate**: _validar o schema, não faz mudanças no banco de dados._
        - **update**: _faz update o schema._
        - **create**: _cria o schema, destruindo dados anteriores._
        - **create-drop**: _Cria e então destrói o schema no final da sessão._
  - `properties`:
    - `hibernate`:
      - `dialect`: especifica o dialeto que será usado
      - `format_sql`: formatação do sql ao exibir no console [ true | false ]
      - `show_sql`: exibir sql no console [ true | false ]
      - `use_sql_comments`: mostrar comentários no console [ true | false ]
      - `jdbc`:
        - `lob`: um lob é um objeto grande. As colunas Lob podem ser usadas para armazenar textos muito longos ou arquivos binários. Existem dois tipos de lobs: CLOB e BLOB. O primeiro é um lob de caracteres e pode ser usado para armazenar textos.
          - `non_contextual_creation`: criar lob no contexto [ true | false ]
</details>

---

# Mãos ao Código
###### IT'S ADVENTURE TIME! 

### 1 - Criando Contrato de API:
Primeiramente precisamos montar nosso contrato de API. Quando fazemos APIs pensando primeiro no contrato 
(contract first), trazemos um nível de maturidade muito maior de entendimento da solução como um todo, 
antes mesmo de começar a programar. Além de iniciar o desenvolvimento com um artefato (contrato) que pode agilizar 
geração de código fonte, mocks, documentação etc. 

Esse item é de grande importância, tanto para o back quanto para que o 
front possa trabalhar em paralelo, tendo como base fiel, os dados que serão expostos pela nossa API.

> Tópicos a serem estudados: [Testes de Contrato de API](https://imasters.com.br/apis-microsservicos/testes-de-contrato-de-api) e
[Testes de Contrato de API com JOI](https://medium.com/cwi-software/testes-de-contrato-de-api-com-joi-1ce552fe2531)

#### Contrato para checkout-api:
```http request
POST http://localhost:8085/v1/checkout/
Content-Type: application/json

{
  "address": "string",
  "cardCvv": "string",
  "cardDate": "string",
  "cardName": "string",
  "cardNumber": "string",
  "cep": "string",
  "complement": "string",
  "country": "string",
  "email": "string",
  "firstName": "string",
  "lastName": "string",
  "paymentMethod": "string",
  "products": [
    "string"
  ],
  "saveAddress": true,
  "saveInfo": true,
  "state": "string"
}
```

---
### 2 - Estrutura de pacotes:
``` 
  ecommerce-checkout-api/
  ├── docker/
  ├── src/
  │   ├── main/
  │   │   ├── java/
  │   │   │   ├── br.com.ecommerce.checkout/
  │   │   │   │   ├── config/
  │   │   │   │   ├── entity/
  │   │   │   │   ├── listener/
  │   │   │   │   ├── repository/
  │   │   │   │   ├── resource.checkout/
  │   │   │   │   ├── service/
  │   │   │   │   ├── streaming/
  │   │   │   │   └─ CheckoutApplication.java
  │   │   │   ├── checkout.event/
  │   │   │   └── payment.event/
  │   │   └── recources/
  │   │       ├── avro/
  │   │       ├── application.yml
  │   │       └── banner.txt
  │   └── test/
  ├─ .gitignore
  ├─ build.gradle
  ├─ settings.gradle
  └─ README.md
```
---
### 3 - Iniciando nosso código:


<details>
<summary> Para este projeto aplicaremos alguns conceitos de SOLID:</summary>

O **S.O.L.I.D** é um acrônimo que representa cinco princípios da programação orientada a objetos e design de códigos
teorizados pelo nosso querido Uncle Bob (Robert C. Martin) por volta do ano 2000. O autor Michael
Feathers foi responsável pela criação do acrônimo:
- **S** _ingle Responsibility Principle (Princípio da Responsabilidade Única);_
- **O** _pen/Closed Principle (Princípio do Aberto/Fechado);_
- **L** _iskov Substitution Principle (Princípio da Substituição de Liskov);_
- **I** _nterface Segregation Principle (Princípio da Segregação de Interfaces);_
- **D** _ependency Inversion Principle (Princípio da Inversão de Dependências)._

> Mais sobre o assunto acessando: [Princípios de SOLID](https://mari-azevedo.medium.com/princípios-s-o-l-i-d-o-que-são-e-porque-projetos-devem-utilizá-los-bf496b82b299).
---
</details>

<details>
<summary> Também utilizaremos lombok:</summary>

O **Lombok** é um Framework criado sob licença MIT, podendo ser usado livremente em qualquer projeto Java. 
Seu principal objetivo é diminuir a verbosidade das classes de mapeamento JPA, DTOs e Beans por exemplo.

Sua vantagem é evitar a repetição de código "clichê", como a criação de gets e sets para todos os atributos, 
métodos equals e hashCode, toString, Construtores entre outros. Dessa forma, o código fica mais limpo e claro. 
---
</details>


#### Iremos começar criando nossas classes:
- [CheckoutResource](./src/main/java/br/com/ecommerce/checkout/resource/checkout/CheckoutResource.java): Que será nossa 
  controller, responsável pelo processamento das requisições e por gerar respostas;
  - Iremos anotar nossa classe com `@Controller` para indicar ao spring que nossa classe é uma controller;
  - `@RequestMapping("/v1/checkout")` para mapear qual a path padrão para nosso resource;
  - Então criaremos nosso método `create ()`
    - Deverá ser anotado com `@PostMapping("/")`, para atender as requisições POST;
    - Receberá como parâmetro um objeto `CheckoutRequest` e responderá um `ResponseEntity<CheckoutResponse>`;
      ```java
        @PostMapping("/")
          public ResponseEntity<CheckoutResponse> create(@RequestBody CheckoutRequest checkoutRequest) {}
      ```

- [CheckoutRequest](./src/main/java/br/com/ecommerce/checkout/resource/checkout/CheckoutRequest.java): Que será como um 
  VO, usado basicamente para receber os dados serializados passados na request de forma que possa ser facilmente repassado 
  a nossa Entidade.
  - Iremos anotar nossa classe com `lombok.Data` para a criação automática de getters e setters;
  - Para gerar nossos contrutores em tempo de compilaçao, para TODOS os argumentos, anotaremos com 
    `lombok.AllArgsConstructor`, e para caso não tenha nenhum `lombok.NoArgsConstructor`;
  - Como é uma resquest, ela precisa ser serializada, então, implementaremos a interface `java.io.Serializable`;
  - Então, iremos declarar os dados que mapeamos no contrato, os quais receberemos em nossa em requisição;
 

- [CheckoutRespose](./src/main/java/br/com/ecommerce/checkout/resource/checkout/CheckoutResponse.java): Que será como um
  DTO, que representa nosso contrato de saída, usado basicamente para enviar os dado de retorno da nossa API para o cliente;

#### Em seguida, baseado nos princípios de SOLID, visando o desacoplamento de responsabilidades:

- Criaremos nossa entidade [CheckoutEntity](./src/main/java/br/com/ecommerce/checkout/entity/CheckoutEntity.java):
  - Ela **representará os dados da tabela** de nosso banco de dados de checkout;
  - Anotaremos com `@Entity` do pacote `javax.persistence.*`;
  - <details>
      <summary> E anotaremos cada representação de coluna com sua anotação representativa:</summary>

    Breve entendimento sobre notações JPA: #TO-DO

    `@Id`: <br>
    `@Column`:<br>
    `@OneToOne`:<br>
    `@OneToMany`:<br>
    </details>
  - Será usada em nossa camada de repositório para ser acessada.
  - Anotaremos a classe com `lombok.Builder`, 
    - **Breve Explicação**: Geralmente instanciamos classes da forma demonstrada abaixo, porém ao mudar parâmetros no construtor, 
      somos obrigados a alterar em todos que a utilizam: **TODO: Falar sobre o pattern Builder**
      ```java
        final CheckoutEntity checkoutEntity = new CheckoutEntity();
      ``` 
    - Para isso o lombok facilitará em nosso desenvolvimento, de forma que não precisaremos gerar um builder na mão, 
      precisaremos apenas utilizar o `@Builder` em nossa **Entidade**, para que em nosso **service** possamos utilizar da seguinte forma:
      ```java
        final CheckoutEntity checkoutEntity = CheckoutEntity.builder().code().build(); // A funcionalidade foi exatrída para o método getCheckoutEntity()
      ```


- Sendo assim, criaremos nosso repositório [CheckoutRepository](./src/main/java/br/com/ecommerce/checkout/repository/CheckoutRepository.java):
  - Está classe fará acesso aos dados de nossa entidade;
  - Deveremos anotar nossa classe com `org.springframework.stereotype.Repository`
  - Herdaremos a classe `JpaRepositoroty<CheckoutEntty, Long>`, passando nossa entidade e a tipo do ID


- Iremos criar nossa classe de serviços [CheckoutService](./src/main/java/br/com/ecommerce/checkout/service/CheckoutService.java):
  - Deveremos anotar nossa classe com `org.springframework.stereotype.Service`, para que seja criado uma instância do nosso serviço;

  - Iremos realizar a injeção de depedência de nosso `repositório` em nosso `service`, para que possamos utilizar os métodos JPA:
    - Comumente, para realizar a injeção de dependências é criado um construtor, como demonstrado abaixo:
      ```java
        private final CheckoutRepository checkoutRepository;
      
        public CheckoutService(final CheckoutRepository checkoutRepository) {
            this.checkoutRepository = checkoutRepository;
        }
      ```
      - Porém, utilizando a anotação `lombok.RequiredArgsConstructor`, o construtor será criado em tempo de compilação para todos atributos que estejam como `final`, fazendo com que seja somente necessário a linha abaixo:
        ```java
            private final CheckoutRepository checkoutRepository;
        ```
        > **AVISO!** NUNCA UTILIZE `@AUTOWIRED` NO ATRIBUTO DA CLASSE PARA FAZER INJEÇÃO, É CRIME. _SUJEITO A PAULADA!!!_
    - Sabendo disso, nosso Service também deverá ser injetado em nossa classe controller [CheckoutResource](./src/main/java/br/com/ecommerce/checkout/resource/checkout/CheckoutResource.java))
  - Teremos em nosso service dois métodos. 
    - create: responsável por criar;
      - Utilizaremos a api `Optional<>`, ela permite trabalhar com objetos nulos.
      - Para utilizarmos o `save()` passaremos a instância de nossa entidade após manipularmos utilizando as ferramentas proporcionadas pelo `@Build` em nosso `repository` 
        ```java
            final CheckoutEntity entity = checkoutRepository.save(checkoutEntity);
        ``` 


#### Com nosso sistema salvando os dados no banco, agora, precisaremos enviar um evento no kafka para dizer que o checkout foi criado:
- Definiremos em nosso avro de [CheckoutCreated.avsc](./src/main/resources/avro/CheckoutCreated.avsc) os dados `checkoutCode` e `status`;
- Poderiamos fazer toda conexão com o kafka manualmente, para publicar, criar um listener, etc. Porém iremos utilizar o [`spring.cloud.streams`]()
  - No spring cloud chamamos
    - o produtor de source, 
    - o consumidor de sink, 
    - o broker de input/output,
    - quem recebe um mensagem processa e devolve ao kafka, é chamado de processor
  - Utilizando Spring Cloud, criar um produtor se torna simples
      - Se fossemos utilizar o próprio apache kafka, teriamos que criar todas as configurações, injeções de classes, etc
      - Com o springCloud, criaremos apenas uma interface, e teremos abstraído todos itens citado acima. Para esse projeto teremos a interface [CheckoutCreatedSource](./src/main/java/br/com/ecommerce/checkout/streaming/CheckoutCreatedSource.java)
  - Primeiramente iremos definir um _tópico virtual_ `"checkout-created-output"` e faremos alguns binds. 
    - Tudo que produzirmos e jogarmos nesse `tópico virtual`, para qual `tópico real` ele será enviado?
      - Isso será definido em nossas configurações em [application.yml](./src/main/resources/application.yml)
        ```yaml
          cloud:
            stream:
              kafka:
                binder: # Configurações para definir quem vai ser a ferramenta para messageria ou streaming (Kafka ou Rebbit)
                  autoCreateTopics: true # No momento de subir a aplicação ele cria um tópico automático, semelhante ao ddl do JPA
                  brokers: localhost:9092 # configura quem é o broker, poderia ter uma lista (porta default do kafka 9092)
                  configuration: # configuramos o serializer e o deserializer. A msg do kafka é representada por uma chave um valor
                    value:
                      deserializer: io.confluent.kafka.serializers.KafkaAvroDeserializer # Utilizaremos o Serializer e o Deserializer da confluent. Estes, já usam o schema registry, já possuí implementado a logica de pegar no schema registry e realizar validação do schema avro para manter a compatibilidade
                      serializer: io.confluent.kafka.serializers.KafkaAvroSerializer
                    key:
                      deserializer: io.confluent.kafka.serializers.KafkaAvroDeserializer
                      serializer: io.confluent.kafka.serializers.KafkaAvroSerializer
              bindings: # define que para o tópico virtual x terá seus dados enviados para o tópico real
                checkout-created-output: # Tópico Virtual
                  destination: streaming.ecommerce.checkout.created # Tópico Real. Padrão de nomenclatura -> tipo_de_informação.nome_de_domino.entidade.ação_realizada
                  contentType: application/*+avro # ContentType HTTP
                  producer:
                    use-native-encoding: true # usar o encoding nativo, o serializer e deserializer da confluent que definimos acima
                payment-paid-input:
                  destination: streaming.ecommerce.payment.paid
                  contentType: application/*+avro
                  group: ${spring.application.name}
                  consumer:
                    use-native-decoding: true
        ```

    - <details>
      <summary> Explicação de organização de nomenclaturas para nomes de tópicos:</summary>
  
      A medida que a empresa cresce, crescem também o numero de aplicações que estarão consumindo tópicos. 
      Para isto, é importante ter uma organização dos tópicos bem definida
    
      Configuraremos nosso padrão de nomenclatura em destination:
      ````yaml
        bindings: 
          checkout-created-output: 
            destination: streaming.ecommerce.checkout.created
      ````
      - `streaming.ecommerce.checkout.created` -> tipo_de_informação.nome_de_domino.entidade.ação_realizada
        - **tipo_de_informação**: se é um `streaming`, ou `etl`
        - **nome_de_domino**: qual domínio da aplicação
        - **entidade**: a entidade que estamos publicando
        - **ação_realizada**: create, update, etc
    
    </details>

  - Precisaremos criar uma classe de configuração de streaming [StreamingConfig](./src/main/java/br/com/ecommerce/checkout/config/StreamingConfig.java)
    - Anotaremos com: 
      - `@Configuration` do pacote `org.springframework.context.annotation.Configuration`;
      - `@EnableBinding` do pacote `org.springframework.cloud.stream.annotation.EnableBinding`, e passaremos no value, nossas interfaces que serão criadas:
          - Interface de Producer [CheckoutCreatedSource](./src/main/java/br/com/ecommerce/checkout/streaming/CheckoutCreatedSource.java);
          - Interface de Consumer [PaymentPaidSink](./src/main/java/br/com/ecommerce/checkout/streaming/CheckoutCreatedSource.java)

  - Para publicar no kafka, injetaremos o producer [CheckoutCreatedSource](./src/main/java/br/com/ecommerce/checkout/streaming/CheckoutCreatedSource.java) 
    em nosso service [CheckoutService](./src/main/java/br/com/ecommerce/checkout/service/CheckoutService.java) e 
    ```java
      private final CheckoutCreatedSource checkoutCreatedSource;
    ```
  - Chamaremos nosso source, utilizaremos o método `output()` para conseguirmos pegar o canal do `tópico virtual`, 
    logo em seguida iremos enviar uma mensagem dentro do nosso `send()`:
   ```java
        checkoutCreatedSource.output().send(MessageBuilder.withPayload(checkoutCreatedEvent).build());
   ```
--- 
### Para consultarmos avros, utilizaremos o Schema Registry API:

> - [Schema Registry API Reference](https://docs.confluent.io/platform/current/schema-registry/develop/using.html) 
> - [Schema Registry API Usage Examples](https://docs.confluent.io/platform/current/schema-registry/develop/api.html)
- **Subjects:** localhost:8081/subjects
  ```json
    [
      "streaming.ecommerce.checkout.created-value"
    ]
  ```

- Subjects: localhost:8081/subjects/streaming.ecommerce.checkout.created-value/versions/latest
  ```json
    [
      "streaming.ecommerce.checkout.created-value"
    ]
  ```
  
---  

Dessa forma, finalizamos nossa API de Checkout e podemos dar início a nossa Api de Payment, que atuara como nosso 
consumer.

># [PROJETO PAYMENT API]()

---

## Melhorias/Pendências:
- [ ] Utilizar `javax.validation.constraints` (`@NotEmpty`, etc), para validar os campo em 
  [CheckoutRequest](./src/main/java/br/com/ecommerce/checkout/resource/checkout/CheckoutRequest.java)