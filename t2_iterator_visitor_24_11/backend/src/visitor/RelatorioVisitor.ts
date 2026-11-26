import type { ProdutoVisitor } from "./ProdutoVisitor";
import { ImpostoVisitor } from "./ImpostoVisitor";
import { DescontoVisitor } from "./DescontoVisitor";
import type { Produto } from "../produtos/Produto";
import type { Eletronico } from "../produtos/Eletronico";
import type { Alimento } from "../produtos/Alimento";
import type { Vestuario } from "../produtos/Vestuario";

export interface LinhaRelatorio {
  codigo: number;
  nome: string;
  categoria: string;
  detalhes: string;
  precoOriginal: number;
  imposto: number;
  desconto: number;
}

// VISITOR - ConcreteVisitor: monta uma linha do relatório com os dados gerais
// e específicos do produto. O imposto e o desconto são calculados separadamente,
// reaproveitando os outros dois visitors.
export class RelatorioVisitor implements ProdutoVisitor<LinhaRelatorio> {
  private impostoVisitor = new ImpostoVisitor();
  private descontoVisitor = new DescontoVisitor();

  visitEletronico(produto: Eletronico): LinhaRelatorio {
    return this.linha(produto, "Eletrônico", `Marca: ${produto.marca} | Garantia: ${produto.garantiaMeses} meses`);
  }

  visitAlimento(produto: Alimento): LinhaRelatorio {
    return this.linha(produto, "Alimento", `Validade: ${produto.validade} | Peso: ${produto.pesoKg} kg`);
  }

  visitVestuario(produto: Vestuario): LinhaRelatorio {
    return this.linha(produto, "Vestuário", `Tamanho: ${produto.tamanho} | Material: ${produto.material}`);
  }

  private linha(produto: Produto, categoria: string, detalhes: string): LinhaRelatorio {
    return {
      codigo: produto.codigo,
      nome: produto.nome,
      categoria,
      detalhes,
      precoOriginal: produto.preco,
      imposto: produto.accept(this.impostoVisitor),
      desconto: produto.accept(this.descontoVisitor),
    };
  }
}
