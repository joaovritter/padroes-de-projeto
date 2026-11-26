import { Produto } from "./Produto";
import type { ProdutoVisitor } from "../visitor/ProdutoVisitor";

// VISITOR - ConcreteElement
export class Alimento extends Produto {
  constructor(
    codigo: number,
    nome: string,
    preco: number,
    public readonly validade: string, // formato AAAA-MM-DD
    public readonly pesoKg: number
  ) {
    super(codigo, nome, preco);
  }

  // Double dispatch: o produto chama o método do visitor específico para o seu tipo
  accept<R>(visitor: ProdutoVisitor<R>): R {
    return visitor.visitAlimento(this);
  }
}
