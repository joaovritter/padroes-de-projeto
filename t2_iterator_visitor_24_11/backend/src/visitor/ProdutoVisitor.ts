import type { Eletronico } from "../produtos/Eletronico";
import type { Alimento } from "../produtos/Alimento";
import type { Vestuario } from "../produtos/Vestuario";

// VISITOR - interface Visitor.
// Um método por categoria de produto. Cada operação nova (frete, lucratividade...)
// vira uma classe nova que implementa esta interface, sem mexer nos produtos.
// O genérico R é o tipo que a operação devolve (number para imposto, uma linha para o relatório...).
export interface ProdutoVisitor<R> {
  visitEletronico(produto: Eletronico): R;
  visitAlimento(produto: Alimento): R;
  visitVestuario(produto: Vestuario): R;
}
