import type { ProdutoVisitor } from "../visitor/ProdutoVisitor";

// VISITOR - Element: classe base dos produtos.
// O produto não sabe calcular imposto, desconto nem montar relatório.
// Ele só "aceita" um visitante e deixa a operação por conta dele.
export abstract class Produto {
  constructor(
    public readonly codigo: number,
    public readonly nome: string,
    public readonly preco: number
  ) {}

  abstract accept<R>(visitor: ProdutoVisitor<R>): R;
}
