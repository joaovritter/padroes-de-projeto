import type { ProdutoIterator } from "./ProdutoIterator";
import type { Produto } from "../produtos/Produto";

// ITERATOR - ConcreteIterator: sabe percorrer o array interno do catálogo
// e guarda a posição atual do percurso.
export class CatalogoIterator implements ProdutoIterator {
  private posicao = 0;

  constructor(private readonly produtos: readonly Produto[]) {}

  hasNext(): boolean {
    return this.posicao < this.produtos.length;
  }

  next(): Produto {
    return this.produtos[this.posicao++];
  }
}
