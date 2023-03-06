# Barber shop

Essa é uma API desenvolvida em Spring Boot para gerenciar as operações da Barbearia, como cadastro de clientes, agendamento de serviços, etc.

## Requisitos

* Java 8 ou superior
* Maven

## Configuração

1. Clone o repositório do GitHub:

```bash
git clone https://github.com/Sjhns/barberShop.git
```

2. Compile e execute a aplicação usando Maven:

```shell
cd barberShop

mvn spring-boot:run
```

## Endpoints

Abaixo está a lista de endpoints disponíveis na API:

### Clientes

**``GET /api/v1/customer``**: Retorna todos os clientes cadastrados.

**``GET /api/v1/customer/details/{id}``**: Retorna o cliente com o ID informado.

**``POST /api/v1/customer``**: Cadastra um novo cliente.

### Barbeiro

**``GET /api/v1/barber``**: Retorna todos os barbeiros disponíveis.

**``GET /api/v1/barber/details/{id}``**: Retorna o barbeiros com o ID informado.

**``POST /api/v1/barber``**: Cadastra um novo barbeiros.

### Agendamentos

**``GET /api/v1/appointment``**: Retorna todos os agendamentos cadastrados.

**`GET /api/v1/appointment/{id}`**: Retorna o agendamento com o ID informado.

**``POST /api/v1/appointment``**: Cadastra um novo agendamento.

**``PUT /api/v1/appointment/{id}``**: Atualiza o agendamento com o ID informado.

**``DELETE /api/v1/appointment/{id}``**: Deleta o agendamento com o ID informado.
