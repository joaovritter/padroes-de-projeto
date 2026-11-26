import type { Produto } from "../produtos/Produto";

// ITERATOR - interface Iterator.
// Quem percorre os produtos só conhece estes dois métodos,
// não sabe se por baixo é array, lista ligada, banco de dados...
export interface ProdutoIterator {
  hasNext(): boolean;
  next(): Produto;
}
