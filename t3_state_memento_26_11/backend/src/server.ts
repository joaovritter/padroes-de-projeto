import express from "express";
import { GerenciadorPedidos, type Operacao } from "./GerenciadorPedidos";
import { OperacaoInvalidaError } from "./state/EstadoPedido";

const app = express();
app.use(express.json());

const gerenciador = new GerenciadorPedidos();

// pedidos de exemplo para a tela não começar vazia
gerenciador.cadastrar("Maria Silva", "1x Notebook Dell", 4500);
gerenciador.cadastrar("João Souza", "2x Camiseta, 1x Tênis", 380);

const operacoes: Operacao[] = ["pagar", "enviar", "entregar", "cancelar"];

// lê nome, descrição e valor do corpo e valida
function lerDados(body: any) {
  const cliente = String(body?.cliente ?? "").trim();
  const descricao = String(body?.descricao ?? "").trim();
  const valorTotal = Number(body?.valorTotal);
  if (!cliente || !descricao || !(valorTotal > 0)) {
    throw new OperacaoInvalidaError("Informe cliente, descrição e valor total maior que zero.");
  }
  return { cliente, descricao, valorTotal };
}

app.get("/api/pedidos", (_req, res) => {
  res.json(gerenciador.listar());
});

app.post("/api/pedidos", (req, res) => {
  const { cliente, descricao, valorTotal } = lerDados(req.body);
  res.status(201).json(gerenciador.cadastrar(cliente, descricao, valorTotal).dados());
});

app.put("/api/pedidos/:numero", (req, res) => {
  const { cliente, descricao, valorTotal } = lerDados(req.body);
  res.json(gerenciador.editar(Number(req.params.numero), cliente, descricao, valorTotal).dados());
});

app.post("/api/pedidos/:numero/desfazer", (req, res) => {
  res.json(gerenciador.desfazer(Number(req.params.numero)).dados());
});

app.post("/api/pedidos/:numero/:operacao", (req, res) => {
  const operacao = req.params.operacao as Operacao;
  if (!operacoes.includes(operacao)) {
    res.status(404).json({ erro: "Operação desconhecida." });
    return;
  }
  res.json(gerenciador.executar(Number(req.params.numero), operacao).dados());
});

// operação negada pelo State vira resposta 400 com o motivo
app.use((err: Error, _req: express.Request, res: express.Response, _next: express.NextFunction) => {
  if (err instanceof OperacaoInvalidaError) {
    res.status(400).json({ erro: err.message });
    return;
  }
  console.error(err);
  res.status(500).json({ erro: "Erro interno." });
});

app.listen(3001, () => console.log("API rodando em http://localhost:3001"));
