import type { Pedido } from "../Pedido";

// Erro lançado quando a operação não é permitida na situação atual.
// A mensagem explica o motivo para o usuário.
export class OperacaoInvalidaError extends Error {}

// STATE - State (classe base dos estados).
// Cada operação do pedido tem um método aqui. Por padrão todas são proibidas,
// e cada estado concreto sobrescreve só as operações que ele permite.
// Assim as regras ficam dentro de cada estado, e não num monte de if/switch no Pedido.
export abstract class EstadoPedido {
  abstract readonly nome: string;

  pagar(_pedido: Pedido): void {
    this.negar("pagar");
  }

  enviar(_pedido: Pedido): void {
    this.negar("enviar");
  }

  entregar(_pedido: Pedido): void {
    this.negar("entregar");
  }

  cancelar(_pedido: Pedido): void {
    this.negar("cancelar");
  }

  editar(_pedido: Pedido, _cliente: string, _descricao: string, _valorTotal: number): void {
    this.negar("editar");
  }

  // cada estado pode trocar este texto para explicar melhor o motivo
  protected motivo(operacao: string): string {
    return `Não é possível ${operacao} um pedido com situação "${this.nome}".`;
  }

  private negar(operacao: string): never {
    throw new OperacaoInvalidaError(this.motivo(operacao));
  }
}
