import { Produto } from "./Produto";
import type { ProdutoVisitor } from "../visitor/ProdutoVisitor";

// VISITOR - ConcreteElement
export class Vestuario extends Produto {
  constructor(
    codigo: number,
    nome: string,
    preco: number,
    public readonly tamanho: string,
    public readonly material: string
  ) {
    super(codigo, nome, preco);
  }

  // Double dispatch: o produto chama o método do visitor específico para o seu tipo
  accept<R>(visitor: ProdutoVisitor<R>): R {
    return visitor.visitVestuario(this);
  }
}
