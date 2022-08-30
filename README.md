---
# Projeto utilizando: 
- ### Spring.
- ### OpenFeign.
- ### Kafka.
- ### Docker.

---

![Spring-Kafka-Feign](https://user-images.githubusercontent.com/83794216/187322253-046f5710-b0a7-4c91-a7d3-5052c1e38968.png)

#### Vale ressaltar que o foco do projeto é apenas nas ferramentas, OpenFeign e Kafka.

---

## API Endereço:

#### [GET] - Envia o CEP para API externa e retorna com um endereço completo do CEP informado.
- **`localhost:8080/enderecos/{cep}`**
 ```
{
"cep": "41620190",
"state": "BA",
"city": "Salvador",
"neighborhood": "Itapuã",
"street": "Rua do Palheta",
"service": "correios"
}
 ```
 #### Resolvi realizar uma implementação para que os atributos retornassem no idioma PT/BR, resultando então no seguinte Response Body:
 ```
{
"cep": "41620190",
"estado": "BA",
"cidade": "Salvador",
"bairro": "Itapuã",
"rua": "Rua do Palheta",
"numeroCasa": "00",
"complemento": "Ao lado da Barbearia Fique Chique"
}
 ``` 
#### E por fim, resolvi ocultar o atributo "service".

#### [POST] - Recebe uma Request, onde o seu Body é formado por três atributos:
- `localhost:8080/enderecos`

 ```
{
 "cep": "",
 "numeroCasa": "",
 "complemento": ""
 }
```
com esses três atributos, conseguiremos o endereço completo e enviaremos para uma fila, onde será cosumida por outra API.

---

## API Cliente:
#### Possui uma função de consumir a mensagem da fila e salvar no banco de Dados.
#### É formado apenas por um unico endpoint:
#### [GET] Lista todos os endereços em sua Base de Dados.
- `localhost:8080/enderecos`

