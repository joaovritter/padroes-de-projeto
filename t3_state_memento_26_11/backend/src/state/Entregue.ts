import { EstadoPedido } from "./EstadoPedido";

// STATE - ConcreteState final: não sobrescreve nenhuma operação, então tudo é negado.
export class Entregue extends EstadoPedido {
  readonly nome = "Entregue";

  protected motivo(operacao: string): string {
    return `Não é possível ${operacao}: o pedido já foi entregue e está finalizado.`;
  }
}
