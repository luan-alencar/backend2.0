# Passo a Passo para Chamar os Endpoints dos Microserviços

Este guia demonstra como chamar os endpoints dos microserviços `shoppingservice` e `usuarioservice` para realizar a persistência no banco e a rotina agendada através de `WebClient` no `shoppingservice`. Certifique-se de seguir cada passo cuidadosamente.

### Pré-requisitos
1. Certifique-se de ter o [Maven](https://maven.apache.org/) instalado em seu ambiente.
2. Clone o repositório e faça checkout para a branch `feature/luan`.

```bash
git clone https://github.com/luan-alencar/backend2.0.git
cd backend
git checkout master
```

### 1. Buildar o Projeto
Buildar o projeto usando o Maven. O comando a seguir vai realizar o build do `pom.xml` localizado na raiz da pasta `backend`.

```bash
mvn clean install -U -DskipTests
```

### 2. Configurações no `shoppingservice`

1. Abra o arquivo `application.properties` em `backend/shoppingservice/src/main/resources`.
2. Certifique-se de que a propriedade `com.unifacisa.usuarioservice-url` esteja configurada corretamente. Ela deve apontar para o endpoint do `usuarioservice`.

```properties
# application.properties

# Aplicação
spring.application.name=shoppingcartservice
com.unifacisa.usuarioservice-url=http://localhost:8080

# PORT
server.port=8081
```

### 3. Executar o `shoppingservice`

Execute o `shoppingservice` usando o seguinte comando:

```bash
cd backend/shoppingservice
mvn spring-boot:run
```

O serviço estará disponível em `http://localhost:8081`.

### 4. Chamar os Endpoints no `shoppingservice`

Você pode utilizar o [Insomnia](https://insomnia.rest/) ou o [Postman](https://www.postman.com/) para chamar os endpoints. Os endpoints disponíveis são:

- Listar Ações: `GET http://localhost:8081/api/acoes`
- Buscar Ações Compráveis: `GET http://localhost:8081/api/acoes/{valor}`

### 5. Configurações no `usuarioservice`

1. Abra o arquivo `AcaoResource.java` em `backend/usuarioservice/src/main/java/com/unifacisa/usuarioservice/resource`.
2. Certifique-se de que a classe `AcaoResource` está configurada corretamente, especialmente a anotação `@RequestMapping(API_URL_ACOES)`. Ela deve apontar para o mesmo valor usado no `shoppingservice`.

```java
// AcaoResource.java

@RestController
@RequiredArgsConstructor
@RequestMapping(API_URL_ACOES)
public class AcaoResource {
    // ...
}
```

### 6. Executar o `usuarioservice`

Execute o `usuarioservice` usando o seguinte comando:

```bash
cd backend/usuarioservice
mvn spring-boot:run
```

O serviço estará disponível em `http://localhost:8080`.

### 7. Chamar os Endpoints no `usuarioservice`

Você pode utilizar o [Insomnia](https://insomnia.rest/) ou o [Postman](https://www.postman.com/) para chamar os endpoints. Os endpoints disponíveis são os mesmos do `shoppingservice`:

- Listar Ações: `GET http://localhost:8080/api/acoes`
- Buscar Ações: `GET http://localhost:8080/api/acoes/{valor}`

Certifique-se de que os endpoints estão retornando os resultados esperados.

Este guia fornece uma visão geral dos passos necessários para chamar os endpoints dos microserviços. Certifique-se de ter as dependências necessárias instaladas e que os serviços estejam em execução antes de realizar as chamadas.