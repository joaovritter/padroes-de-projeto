import { useEffect, useState } from "react";
import {
  Alert,
  AppBar,
  Box,
  Button,
  Card,
  CardActions,
  CardContent,
  Chip,
  Container,
  Dialog,
  DialogActions,
  DialogContent,
  DialogTitle,
  Paper,
  Snackbar,
  Stack,
  TextField,
  Toolbar,
  Typography,
} from "@mui/material";

interface Pedido {
  numero: number;
  cliente: string;
  descricao: string;
  valorTotal: number;
  situacao: string;
  versoesAnteriores: number;
}

type Dados = { cliente: string; descricao: string; valorTotal: string };

const formVazio: Dados = { cliente: "", descricao: "", valorTotal: "" };

const corSituacao: Record<string, "warning" | "info" | "primary" | "success" | "error"> = {
  "Aguardando pagamento": "warning",
  Pago: "info",
  Enviado: "primary",
  Entregue: "success",
  Cancelado: "error",
};

const operacoes = [
  { op: "pagar", label: "Pagar" },
  { op: "enviar", label: "Enviar" },
  { op: "entregar", label: "Entregar" },
  { op: "cancelar", label: "Cancelar" },
];

const moeda = (v: number) => v.toLocaleString("pt-BR", { style: "currency", currency: "BRL" });

// chama a API e devolve a mensagem de erro (ou "" se deu certo)
async function chamar(url: string, method: string, body?: unknown): Promise<string> {
  try {
    const resp = await fetch(url, {
      method,
      headers: { "Content-Type": "application/json" },
      body: body ? JSON.stringify(body) : undefined,
    });
    return resp.ok ? "" : (await resp.json()).erro;
  } catch {
    return "Não foi possível conectar na API. O backend está rodando?";
  }
}

export default function App() {
  const [pedidos, setPedidos] = useState<Pedido[]>([]);
  const [form, setForm] = useState<Dados>(formVazio);
  const [editando, setEditando] = useState<{ numero: number; dados: Dados } | null>(null);
  const [mensagem, setMensagem] = useState<{ texto: string; erro: boolean } | null>(null);

  const carregar = () =>
    fetch("/api/pedidos")
      .then((r) => r.json())
      .then(setPedidos)
      .catch(() => setMensagem({ texto: "Não foi possível conectar na API. O backend está rodando?", erro: true }));

  useEffect(() => {
    carregar();
  }, []);

  // mostra o motivo se a operação foi negada, ou uma confirmação se deu certo
  const resultado = async (erro: string, sucesso: string) => {
    setMensagem({ texto: erro || sucesso, erro: !!erro });
    await carregar();
    return !erro;
  };

  const cadastrar = async (e: React.FormEvent) => {
    e.preventDefault();
    if (await resultado(await chamar("/api/pedidos", "POST", form), "Pedido cadastrado.")) setForm(formVazio);
  };

  const executar = async (numero: number, op: string, label: string) =>
    resultado(await chamar(`/api/pedidos/${numero}/${op}`, "POST"), `Pedido ${numero}: ${label} realizado.`);

  const desfazer = async (numero: number) =>
    resultado(await chamar(`/api/pedidos/${numero}/desfazer`, "POST"), `Pedido ${numero}: última alteração desfeita.`);

  const salvarEdicao = async () => {
    if (!editando) return;
    const ok = await resultado(
      await chamar(`/api/pedidos/${editando.numero}`, "PUT", editando.dados),
      `Pedido ${editando.numero} editado.`
    );
    if (ok) setEditando(null);
  };

  const camposDados = (dados: Dados, mudar: (d: Dados) => void) => (
    <>
      <TextField label="Cliente" size="small" value={dados.cliente} onChange={(e) => mudar({ ...dados, cliente: e.target.value })} />
      <TextField label="Produtos" size="small" value={dados.descricao} onChange={(e) => mudar({ ...dados, descricao: e.target.value })} />
      <TextField label="Valor total (R$)" type="number" size="small" value={dados.valorTotal} onChange={(e) => mudar({ ...dados, valorTotal: e.target.value })} />
    </>
  );

  return (
    <>
      <AppBar position="static">
        <Toolbar>
          <Typography variant="h6">Gerenciamento de Pedidos</Typography>
        </Toolbar>
      </AppBar>

      <Container sx={{ py: 4 }}>
        <Paper sx={{ p: 3, mb: 4 }}>
          <Typography variant="h6" gutterBottom>
            Novo pedido
          </Typography>
          <Box component="form" onSubmit={cadastrar}>
            <Stack direction={{ xs: "column", md: "row" }} spacing={2}>
              {camposDados(form, setForm)}
              <Button type="submit" variant="contained">
                Cadastrar
              </Button>
            </Stack>
          </Box>
        </Paper>

        <Typography variant="h6" gutterBottom>
          Pedidos
        </Typography>
        <Stack spacing={2}>
          {pedidos.map((p) => (
            <Card key={p.numero}>
              <CardContent>
                <Stack direction="row" justifyContent="space-between" alignItems="center">
                  <Typography variant="subtitle1" fontWeight="bold">
                    Pedido #{p.numero} - {p.cliente}
                  </Typography>
                  <Chip label={p.situacao} color={corSituacao[p.situacao]} />
                </Stack>
                <Typography color="text.secondary">{p.descricao}</Typography>
                <Typography>{moeda(p.valorTotal)}</Typography>
                <Typography variant="caption" color="text.secondary">
                  Versões no histórico: {p.versoesAnteriores}
                </Typography>
              </CardContent>
              <CardActions sx={{ flexWrap: "wrap", gap: 1 }}>
                {operacoes.map((o) => (
                  <Button key={o.op} size="small" variant="outlined" onClick={() => executar(p.numero, o.op, o.label)}>
                    {o.label}
                  </Button>
                ))}
                <Button
                  size="small"
                  variant="outlined"
                  onClick={() =>
                    setEditando({
                      numero: p.numero,
                      dados: { cliente: p.cliente, descricao: p.descricao, valorTotal: String(p.valorTotal) },
                    })
                  }
                >
                  Editar
                </Button>
                <Button size="small" color="secondary" onClick={() => desfazer(p.numero)}>
                  Desfazer
                </Button>
              </CardActions>
            </Card>
          ))}
        </Stack>
      </Container>

      <Dialog open={!!editando} onClose={() => setEditando(null)}>
        <DialogTitle>Editar pedido #{editando?.numero}</DialogTitle>
        <DialogContent>
          <Stack spacing={2} sx={{ pt: 1 }}>
            {editando && camposDados(editando.dados, (dados) => setEditando({ ...editando, dados }))}
          </Stack>
        </DialogContent>
        <DialogActions>
          <Button onClick={() => setEditando(null)}>Fechar</Button>
          <Button variant="contained" onClick={salvarEdicao}>
            Salvar
          </Button>
        </DialogActions>
      </Dialog>

      <Snackbar open={!!mensagem} autoHideDuration={4000} onClose={() => setMensagem(null)}>
        <Alert severity={mensagem?.erro ? "error" : "success"} variant="filled" onClose={() => setMensagem(null)}>
          {mensagem?.texto}
        </Alert>
      </Snackbar>
    </>
  );
}
