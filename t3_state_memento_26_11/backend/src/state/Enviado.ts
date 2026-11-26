import { EstadoPedido } from "./EstadoPedido";
import { Entregue } from "./Entregue";
import type { Pedido } from "../Pedido";

// STATE - ConcreteState: pedido a caminho do cliente.
// Permite: entregar.
export class Enviado extends EstadoPedido {
  readonly nome = "Enviado";

  entregar(pedido: Pedido): void {
    pedido.setEstado(new Entregue());
  }

  protected motivo(operacao: string): string {
    return `Não é possível ${operacao}: o pedido já foi enviado e só pode ser entregue.`;
  }
}
