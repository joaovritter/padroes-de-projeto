import type { ProdutoVisitor } from "./ProdutoVisitor";
import type { Eletronico } from "../produtos/Eletronico";
import type { Alimento } from "../produtos/Alimento";
import type { Vestuario } from "../produtos/Vestuario";

// VISITOR - ConcreteVisitor: calcula o valor do imposto de cada categoria.
// Eletrônicos 15% | Alimentos 5% | Vestuário 10%
export class ImpostoVisitor implements ProdutoVisitor<number> {
  visitEletronico(produto: Eletronico): number {
    return produto.preco * 0.15;
  }

  visitAlimento(produto: Alimento): number {
    return produto.preco * 0.05;
  }

  visitVestuario(produto: Vestuario): number {
    return produto.preco * 0.1;
  }
}
