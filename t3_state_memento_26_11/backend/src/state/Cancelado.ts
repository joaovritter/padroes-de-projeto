import { EstadoPedido } from "./EstadoPedido";

// STATE - ConcreteState final: não sobrescreve nenhuma operação, então tudo é negado.
export class Cancelado extends EstadoPedido {
  readonly nome = "Cancelado";

  protected motivo(operacao: string): string {
    return `Não é possível ${operacao}: o pedido foi cancelado.`;
  }
}
