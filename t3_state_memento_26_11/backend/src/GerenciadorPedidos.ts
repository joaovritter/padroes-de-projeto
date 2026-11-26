import { Pedido } from "./Pedido";
import { HistoricoPedido } from "./memento/HistoricoPedido";
import { OperacaoInvalidaError } from "./state/EstadoPedido";

export type Operacao = "pagar" | "enviar" | "entregar" | "cancelar";

// Guarda os pedidos e o histórico de cada um.
// É aqui que STATE e MEMENTO se encontram.
export class GerenciadorPedidos {
  private pedidos = new Map<number, { pedido: Pedido; historico: HistoricoPedido }>();
  private proximoNumero = 1;

  cadastrar(cliente: string, descricao: string, valorTotal: number): Pedido {
    const pedido = new Pedido(this.proximoNumero++, cliente, descricao, valorTotal);
    this.pedidos.set(pedido.numero, { pedido, historico: new HistoricoPedido() });
    return pedido;
  }

  // STATE + MEMENTO integrados:
  // 1. tira uma foto do pedido (Memento) ANTES da alteração;
  // 2. manda o pedido executar a operação (State decide se pode);
  // 3. só se deu certo, guarda a foto no histórico.
  // Se o State negar, ele lança erro e o passo 3 nunca acontece:
  // operação inválida não gera registro no histórico.
  executar(numero: number, operacao: Operacao): Pedido {
    return this.alterar(numero, (pedido) => pedido[operacao]());
  }

  editar(numero: number, cliente: string, descricao: string, valorTotal: number): Pedido {
    return this.alterar(numero, (pedido) => pedido.editar(cliente, descricao, valorTotal));
  }

  // Desfazer: pega a última foto e devolve o pedido para ela (dados + situação)
  desfazer(numero: number): Pedido {
    const { pedido, historico } = this.buscar(numero);
    const memento = historico.desfazer();
    if (!memento) {
      throw new OperacaoInvalidaError("Não há alterações para desfazer neste pedido.");
    }
    pedido.restaurar(memento);
    return pedido;
  }

  listar() {
    return [...this.pedidos.values()].map(({ pedido, historico }) => ({
      ...pedido.dados(),
      versoesAnteriores: historico.quantidade(),
    }));
  }

  private alterar(numero: number, acao: (pedido: Pedido) => void): Pedido {
    const { pedido, historico } = this.buscar(numero);
    const memento = pedido.salvar();
    acao(pedido);
    historico.salvar(memento);
    return pedido;
  }

  private buscar(numero: number) {
    const item = this.pedidos.get(numero);
    if (!item) throw new OperacaoInvalidaError(`Pedido ${numero} não encontrado.`);
    return item;
  }
}
