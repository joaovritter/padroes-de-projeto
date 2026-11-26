# Trabalho T3 - State e Memento

Sistema de gerenciamento de pedidos com histórico. Cada pedido passa pelas situações
Aguardando pagamento, Pago, Enviado, Entregue e Cancelado, e as alterações podem ser desfeitas.

- **Backend:** Node + TypeScript (Express) - é onde estão os padrões.
- **Frontend:** React + Material UI - cadastro, botões das operações e desfazer.

## Como o sistema funciona

1. O usuário cadastra um pedido (cliente, produtos e valor total). Ele começa como **Aguardando pagamento**.
2. Cada pedido tem os botões Pagar, Enviar, Entregar, Cancelar, Editar e Desfazer.
3. Quando o usuário clica numa operação, quem decide se ela pode ser feita é o **estado atual** do pedido (State).
   Se não puder, a tela mostra o motivo, por exemplo: *"Não é possível pagar: o pedido já foi pago."*
4. Antes de cada alteração válida, o sistema tira uma "foto" do pedido (Memento) e guarda no histórico.
5. O botão Desfazer pega a última foto e volta o pedido para ela: dados e situação.

Regras de cada situação:

| Situação | Pode |
|---|---|
| Aguardando pagamento | pagar, cancelar, editar dados |
| Pago | enviar, cancelar |
| Enviado | entregar |
| Entregue | nada (finalizado) |
| Cancelado | nada (finalizado) |

## Estrutura

```
backend/src/
├── state/               -> padrão STATE
│   ├── EstadoPedido.ts      (classe base: tudo negado por padrão)
│   ├── AguardandoPagamento.ts
│   ├── Pago.ts
│   ├── Enviado.ts
│   ├── Entregue.ts
│   └── Cancelado.ts
├── memento/             -> padrão MEMENTO
│   ├── PedidoMemento.ts     (a "foto" do pedido)
│   └── HistoricoPedido.ts   (pilha de fotos)
├── Pedido.ts            -> Context do State e Originator do Memento
├── GerenciadorPedidos.ts -> onde os dois padrões se ligam
├── server.ts            -> rotas da API
└── teste.ts             -> teste com a sequência do enunciado
```

## Padrão State

### O problema

O que pode ser feito com um pedido depende da situação dele. Sem o padrão, cada método do pedido
teria um `if`/`switch` gigante perguntando a situação, e cada situação nova obrigaria a mexer em todos eles.

### Como foi resolvido

Cada situação virou uma classe, e o pedido delega as operações para a situação atual.

| Papel | Classe |
|---|---|
| **Context** | `Pedido` |
| **State** | `EstadoPedido` (classe abstrata) |
| **Estados concretos** | `AguardandoPagamento`, `Pago`, `Enviado`, `Entregue`, `Cancelado` |

### Como funciona

1. `EstadoPedido` tem um método para cada operação (`pagar`, `enviar`, `entregar`, `cancelar`, `editar`).
   Por padrão **todos negam**, lançando `OperacaoInvalidaError` com o motivo.
2. Cada estado concreto sobrescreve só o que ele permite. Por exemplo, `Pago`:

   ```ts
   enviar(pedido: Pedido): void {
     pedido.setEstado(new Enviado());
   }
   ```

3. O `Pedido` não tem nenhum `if` de situação, ele só repassa:

   ```ts
   pagar(): void {
     this.estado.pagar(this);
   }
   ```

4. `Entregue` e `Cancelado` não sobrescrevem nada, então qualquer operação neles é negada.
5. Cada estado sobrescreve `motivo()` para explicar por que a operação foi negada.

## Padrão Memento

### O problema

A empresa quer desfazer alterações e voltar para versões anteriores do pedido, sem expor
os dados internos do pedido para quem cuida do histórico.

### Como foi resolvido

| Papel | Classe |
|---|---|
| **Originator** | `Pedido` (`salvar()` e `restaurar()`) |
| **Memento** | `PedidoMemento` |
| **Caretaker** | `HistoricoPedido` |

### Como funciona

1. `Pedido.salvar()` cria um `PedidoMemento` com cliente, descrição, valor total **e o objeto de estado atual**.
   Todos os campos são `readonly`, a foto não muda depois de criada.
2. `HistoricoPedido` guarda os mementos numa pilha. Ele só empilha (`salvar`) e desempilha (`desfazer`),
   nunca mexe no conteúdo.
3. `Pedido.restaurar(memento)` copia os dados de volta e também o estado.
4. Como a pilha devolve sempre a última foto, dá para desfazer várias vezes seguidas até o histórico acabar.
   Quando acaba, o sistema avisa *"Não há alterações para desfazer neste pedido."*

## Integração State + Memento

Os dois padrões se encontram em dois pontos.

### 1. O memento guarda o State

Ao restaurar, o pedido recebe de volta o **objeto de estado** daquele momento. Por isso as regras voltam junto:
um pedido restaurado para Pago volta a poder ser enviado e cancelado, sem nenhum código extra.

```ts
restaurar(memento: PedidoMemento): void {
  this.cliente = memento.cliente;
  this.descricao = memento.descricao;
  this.valorTotal = memento.valorTotal;
  this.estado = memento.estado;
}
```

### 2. O histórico só grava o que o State deixou passar

No `GerenciadorPedidos`, toda alteração segue a mesma ordem:

```ts
const memento = pedido.salvar();   // 1. foto ANTES da alteração (Memento)
acao(pedido);                      // 2. o State executa ou lança erro
historico.salvar(memento);         // 3. só chega aqui se deu certo
```

Se o State negar a operação, o erro interrompe antes do passo 3.
Por isso **operações inválidas não geram registro no histórico**.

### Exemplo do enunciado

| Ação | Situação | Histórico (pilha) |
|---|---|---|
| Cadastrar | Aguardando pagamento | vazio |
| Pagar | Pago | [Aguardando] |
| Enviar | Enviado | [Aguardando, Pago] |
| Pagar (inválido) | Enviado | [Aguardando, Pago] - nada gravado |
| Desfazer | Pago | [Aguardando] |
| Desfazer | Aguardando pagamento | vazio |
