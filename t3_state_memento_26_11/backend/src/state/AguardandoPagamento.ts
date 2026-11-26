import { EstadoPedido } from "./EstadoPedido";
import { Pago } from "./Pago";
import { Cancelado } from "./Cancelado";
import type { Pedido } from "../Pedido";

// STATE - ConcreteState: pedido recém cadastrado.
// Permite: pagar, cancelar e editar os dados do pedido.
export class AguardandoPagamento extends EstadoPedido {
  readonly nome = "Aguardando pagamento";

  pagar(pedido: Pedido): void {
    pedido.setEstado(new Pago());
  }

  cancelar(pedido: Pedido): void {
    pedido.setEstado(new Cancelado());
  }

  editar(pedido: Pedido, cliente: string, descricao: string, valorTotal: number): void {
    pedido.alterarDados(cliente, descricao, valorTotal);
  }

  protected motivo(operacao: string): string {
    return `Não é possível ${operacao} o pedido: o pagamento ainda não foi confirmado.`;
  }
}
