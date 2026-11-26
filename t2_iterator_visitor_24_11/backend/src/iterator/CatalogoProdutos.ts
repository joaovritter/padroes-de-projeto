import type { ProdutoIterator } from "./ProdutoIterator";
import { CatalogoIterator } from "./CatalogoIterator";
import type { Produto } from "../produtos/Produto";
import type { ProdutoVisitor } from "../visitor/ProdutoVisitor";

// ITERATOR - Aggregate (coleção concreta).
// O array de produtos é privado: ninguém de fora acessa direto,
// só consegue percorrer pedindo um iterador com createIterator().
export class CatalogoProdutos {
  private produtos: Produto[] = [];
  private proximoCodigo = 1;

  gerarCodigo(): number {
    return this.proximoCodigo++;
  }

  adicionar(produto: Produto): void {
    this.produtos.push(produto);
  }

  createIterator(): ProdutoIterator {
    return new CatalogoIterator(this.produtos);
  }

  // ITERATOR + VISITOR integrados:
  // o iterator percorre a coleção e cada produto aceita o visitor recebido.
  // Para qualquer operação nova basta passar outro visitor, este método não muda.
  aplicar<R>(visitor: ProdutoVisitor<R>): R[] {
    const resultados: R[] = [];
    const it = this.createIterator();
    while (it.hasNext()) {
      resultados.push(it.next().accept(visitor));
    }
    return resultados;
  }
}
