import type { EstadoPedido } from "../state/EstadoPedido";

// MEMENTO - Memento: uma "foto" do pedido em um momento.
// Guarda os dados E o objeto de estado (State), por isso ao restaurar
// o pedido volta a obedecer as regras da situação daquele momento.
// Tudo é readonly: depois de criado o memento não muda.
export class PedidoMemento {
  readonly salvoEm = new Date();

  constructor(
    readonly cliente: string,
    readonly descricao: string,
    readonly valorTotal: number,
    readonly estado: EstadoPedido
  ) {}
}
