import type { PedidoMemento } from "./PedidoMemento";

// MEMENTO - Caretaker: guarda as versões anteriores de um pedido numa pilha.
// Ele não lê nem altera o conteúdo dos mementos, só empilha e desempilha.
export class HistoricoPedido {
  private versoes: PedidoMemento[] = [];

  salvar(memento: PedidoMemento): void {
    this.versoes.push(memento);
  }

  // devolve a última versão salva (a mais recente) ou undefined se não houver
  desfazer(): PedidoMemento | undefined {
    return this.versoes.pop();
  }

  quantidade(): number {
    return this.versoes.length;
  }
}
