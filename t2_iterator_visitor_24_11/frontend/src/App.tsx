import { useEffect, useState } from "react";
import {
  Alert,
  AppBar,
  Box,
  Button,
  Chip,
  Container,
  MenuItem,
  Paper,
  Stack,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  TextField,
  Toolbar,
  Typography,
} from "@mui/material";

interface LinhaRelatorio {
  codigo: number;
  nome: string;
  categoria: string;
  detalhes: string;
  precoOriginal: number;
  imposto: number;
  desconto: number;
}

type Categoria = "eletronico" | "alimento" | "vestuario";

// campos específicos de cada categoria
const camposPorCategoria: Record<Categoria, { nome: string; label: string; type?: string }[]> = {
  eletronico: [
    { nome: "marca", label: "Marca" },
    { nome: "garantiaMeses", label: "Garantia (meses)", type: "number" },
  ],
  alimento: [
    { nome: "validade", label: "Validade", type: "date" },
    { nome: "pesoKg", label: "Peso (kg)", type: "number" },
  ],
  vestuario: [
    { nome: "tamanho", label: "Tamanho" },
    { nome: "material", label: "Material" },
  ],
};

const formVazio = { nome: "", preco: "" } as Record<string, string>;

const moeda = (v: number) => v.toLocaleString("pt-BR", { style: "currency", currency: "BRL" });

export default function App() {
  const [relatorio, setRelatorio] = useState<LinhaRelatorio[]>([]);
  const [categoria, setCategoria] = useState<Categoria>("eletronico");
  const [form, setForm] = useState(formVazio);
  const [erro, setErro] = useState("");

  const carregar = () =>
    fetch("/api/relatorio")
      .then((r) => r.json())
      .then(setRelatorio)
      .catch(() => setErro("Não foi possível conectar na API. O backend está rodando?"));

  useEffect(() => {
    carregar();
  }, []);

  const cadastrar = async (e: React.FormEvent) => {
    e.preventDefault();
    setErro("");
    const resp = await fetch("/api/produtos", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ ...form, categoria }),
    });
    if (!resp.ok) {
      setErro((await resp.json()).erro);
      return;
    }
    setForm(formVazio);
    carregar();
  };

  const campo = (nome: string, label: string, type = "text") => (
    <TextField
      key={nome}
      label={label}
      type={type}
      size="small"
      value={form[nome] ?? ""}
      onChange={(e) => setForm({ ...form, [nome]: e.target.value })}
      slotProps={type === "date" ? { inputLabel: { shrink: true } } : undefined}
    />
  );

  return (
    <>
      <AppBar position="static">
        <Toolbar>
          <Typography variant="h6">Loja - Gerenciamento de Produtos</Typography>
        </Toolbar>
      </AppBar>

      <Container sx={{ py: 4 }}>
        <Paper sx={{ p: 3, mb: 4 }}>
          <Typography variant="h6" gutterBottom>
            Cadastrar produto
          </Typography>
          <Box component="form" onSubmit={cadastrar}>
            <Stack direction={{ xs: "column", md: "row" }} spacing={2} flexWrap="wrap" useFlexGap>
              <TextField
                select
                label="Categoria"
                size="small"
                value={categoria}
                onChange={(e) => setCategoria(e.target.value as Categoria)}
                sx={{ minWidth: 160 }}
              >
                <MenuItem value="eletronico">Eletrônico</MenuItem>
                <MenuItem value="alimento">Alimento</MenuItem>
                <MenuItem value="vestuario">Vestuário</MenuItem>
              </TextField>
              {campo("nome", "Nome")}
              {campo("preco", "Preço (R$)", "number")}
              {camposPorCategoria[categoria].map((c) => campo(c.nome, c.label, c.type))}
              <Button type="submit" variant="contained">
                Cadastrar
              </Button>
            </Stack>
          </Box>
          {erro && (
            <Alert severity="error" sx={{ mt: 2 }}>
              {erro}
            </Alert>
          )}
        </Paper>

        <Typography variant="h6" gutterBottom>
          Relatório de produtos
        </Typography>
        <TableContainer component={Paper}>
          <Table size="small">
            <TableHead>
              <TableRow>
                <TableCell>Código</TableCell>
                <TableCell>Nome</TableCell>
                <TableCell>Categoria</TableCell>
                <TableCell>Detalhes</TableCell>
                <TableCell align="right">Preço original</TableCell>
                <TableCell align="right">Imposto</TableCell>
                <TableCell align="right">Desconto</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {relatorio.map((l) => (
                <TableRow key={l.codigo}>
                  <TableCell>{l.codigo}</TableCell>
                  <TableCell>{l.nome}</TableCell>
                  <TableCell>
                    <Chip label={l.categoria} size="small" />
                  </TableCell>
                  <TableCell>{l.detalhes}</TableCell>
                  <TableCell align="right">{moeda(l.precoOriginal)}</TableCell>
                  <TableCell align="right">{moeda(l.imposto)}</TableCell>
                  <TableCell align="right">{moeda(l.desconto)}</TableCell>
                </TableRow>
              ))}
            </TableBody>
          </Table>
        </TableContainer>
      </Container>
    </>
  );
}
