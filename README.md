# Projeto de Arquitetura Hexagonal - Transferência Bancária

Este projeto demonstra a implementação de uma aplicação usando a arquitetura hexagonal para realizar transferências bancárias entre contas. Ele separa claramente as regras de negócio (core) das implementações específicas (adaptadores) como bancos de dados falsos e interfaces gráficas.

## Estrutura do Projeto

### Módulos

- **Core:** Contém as interfaces e classes que definem a regra de negócio do sistema, como a transferência de valores entre contas. Não depende de tecnologias específicas.
  
- **Adaptadores:** Implementa as interfaces do core usando tecnologias específicas, como o adaptador de conta fake e a porta de transferência. Conecta o core ao mundo externo, como bancos de dados ou interfaces gráficas.
  
- **Drivers:** Fornecem implementações concretas para tecnologias específicas, como o driver JDBC para banco de dados ou a biblioteca Swing para interfaces gráficas. São utilizados pelos adaptadores para interagir com as tecnologias.

### Pacotes Importantes

- **conta.sistema:** Contém o domínio do sistema, incluindo entidades como `Conta` e serviços como `ServicoTransferencia`.
  
- **conta.adaptador:** Implementa adaptadores concretos para o sistema, como `AdaptadorContaFakeImp` e `PortaTransferenciaImp`.

## Funcionamento

### Adaptador de Conta

O `AdaptadorContaFakeImp` simula um banco de dados fake para armazenar as contas. Ele fornece métodos para obter e alterar contas por meio do número da conta.

### Porta de Transferência

A `PortaTransferenciaImp` implementa a interface `PortaTransferencia`. Utiliza o adaptador de conta e o serviço de transferência para realizar as operações de transferência. Valida as informações da transferência antes de executá-la e atualiza os saldos das contas envolvidas.

### Serviço de Transferência

O `ServicoTransferencia` encapsula a lógica de transferência de valores entre contas. Recebe as contas débito e crédito, e o valor a ser transferido. Debita o valor da conta débito e credita na conta crédito.

### Exceções

- **Erro:** Define exceções de negócio da aplicação, como `NegocioException` para casos como valor obrigatório, conta inexistente, saldo insuficiente e mesma conta.

## Utilização

Para utilizar a porta de transferência em sua aplicação, você pode injetá-la em sua classe de caso de uso:

```java
// Exemplo de injeção de dependência
@Inject
private PortaTransferencia portaTransferencia;
// Obter conta
Conta contaDebito = portaTransferencia.getConta(10);

// Transferir R$100,00 da conta 10 para a conta 20
portaTransferencia.transferir(10, 20, new BigDecimal("100.00"));

```
