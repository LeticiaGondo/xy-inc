# xy-inc
REST Webservice Java para cadastro, listagem e análise de proximidade de coordenadas (X,Y).

## Tecnologias
* JDK 1.8+
* Maven 3.3.9
* IntelliJ IDEA 2017.1.6
* Spring Boot (v2.1.1.BUILD-SNAPSHOT)

## Passos para setup

**1. Clone o repositório** 

```bash
git clone https://github.com/fellipegurgel/xy-inc.git
```

**2. Execute o projeto usando maven**

```bash
cd xy-inc
mvn spring-boot:run
```

A aplicação estará acessível em `http://localhost:8081`.

Esta aplicação possui três serviços: 
1. Cadastro de pontos de interesse (POI, pontos de coordenada <X,Y>, similar a <lat,long>):
```json
POST /points-of-interest
  {
    "name": "Test 1",
    "xCoordinate": 12,
    "yCoordinate": 10
  }
```

2. Listagem dos pontos cadastrados:
```
GET /points-of-interest
```
3. Análise de proximidade dos pontos de interesse cadastrados. Este serviço recebe um ponto <X,Y> de referência e um número para o raio de distância. Como resultado, ele retorna quais pontos cadastrados estão dentro desse raio partindo do ponto ponto de referência.
```
GET /points-of-interest/points-of-interest/list-nearby?referenceX={int}&referenceY={int}&maxDistance={int}

Parâmetros
   - referenceX: coordenada X do ponto de referência (inteiro positivo)
   - referenceY: coordenada Y do ponto de referência (inteiro positivo)
   - maxDistance: distância do raio (inteiro positivo)
```

## Checklist 
- [x] Unit tests (**dificuldade**. podem melhorar)
- [x] Data Validation
- [x] Class descriptions and Comments
- [ ] Refine the data validation error message
