import { EstadoPedido } from "./EstadoPedido";
import { Enviado } from "./Enviado";
import { Cancelado } from "./Cancelado";
import type { Pedido } from "../Pedido";

// STATE - ConcreteState: pagamento confirmado.
// Permite: enviar e cancelar.
export class Pago extends EstadoPedido {
  readonly nome = "Pago";

  enviar(pedido: Pedido): void {
    pedido.setEstado(new Enviado());
  }

  cancelar(pedido: Pedido): void {
    pedido.setEstado(new Cancelado());
  }

  protected motivo(operacao: string): string {
    if (operacao === "pagar") return "Não é possível pagar: o pedido já foi pago.";
    if (operacao === "entregar") return "Não é possível entregar: o pedido ainda não foi enviado.";
    return "Não é possível editar: o pedido já foi pago.";
  }
}
