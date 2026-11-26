import type { EstadoPedido } from "./state/EstadoPedido";
import { AguardandoPagamento } from "./state/AguardandoPagamento";
import { PedidoMemento } from "./memento/PedidoMemento";

// O Pedido tem dois papéis:
// - STATE - Context: guarda o estado atual e delega as operações para ele.
// - MEMENTO - Originator: sabe criar uma foto de si mesmo (salvar) e voltar a ela (restaurar).
export class Pedido {
  private estado: EstadoPedido = new AguardandoPagamento();

  constructor(
    readonly numero: number,
    private cliente: string,
    private descricao: string,
    private valorTotal: number
  ) {}

  // ---- STATE: o pedido não decide nada, só repassa para o estado atual ----
  pagar(): void {
    this.estado.pagar(this);
  }

  enviar(): void {
    this.estado.enviar(this);
  }

  entregar(): void {
    this.estado.entregar(this);
  }

  cancelar(): void {
    this.estado.cancelar(this);
  }

  editar(cliente: string, descricao: string, valorTotal: number): void {
    this.estado.editar(this, cliente, descricao, valorTotal);
  }

  // chamados pelos estados concretos
  setEstado(estado: EstadoPedido): void {
    this.estado = estado;
  }

  alterarDados(cliente: string, descricao: string, valorTotal: number): void {
    this.cliente = cliente;
    this.descricao = descricao;
    this.valorTotal = valorTotal;
  }

  // ---- MEMENTO ----
  salvar(): PedidoMemento {
    return new PedidoMemento(this.cliente, this.descricao, this.valorTotal, this.estado);
  }

  restaurar(memento: PedidoMemento): void {
    this.cliente = memento.cliente;
    this.descricao = memento.descricao;
    this.valorTotal = memento.valorTotal;
    this.estado = memento.estado; // volta o State junto, então as regras voltam também
  }

  dados() {
    return {
      numero: this.numero,
      cliente: this.cliente,
      descricao: this.descricao,
      valorTotal: this.valorTotal,
      situacao: this.estado.nome,
    };
  }
}
