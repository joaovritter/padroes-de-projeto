import express from "express";
import { CatalogoProdutos } from "./iterator/CatalogoProdutos";
import { RelatorioVisitor } from "./visitor/RelatorioVisitor";
import { Eletronico } from "./produtos/Eletronico";
import { Alimento } from "./produtos/Alimento";
import { Vestuario } from "./produtos/Vestuario";
import type { Produto } from "./produtos/Produto";

const app = express();
app.use(express.json());

const catalogo = new CatalogoProdutos();

// alguns produtos iniciais para a tela não começar vazia
catalogo.adicionar(new Eletronico(catalogo.gerarCodigo(), "Notebook", 4500, "Dell", 12));
catalogo.adicionar(new Alimento(catalogo.gerarCodigo(), "Arroz 5kg", 30, "2027-03-10", 5));
catalogo.adicionar(new Vestuario(catalogo.gerarCodigo(), "Camiseta", 80, "M", "Algodão"));

// Listagem/relatório: percorre o catálogo com o Iterator aplicando o RelatorioVisitor
app.get("/api/relatorio", (_req, res) => {
  res.json(catalogo.aplicar(new RelatorioVisitor()));
});

// Cadastro de produto
app.post("/api/produtos", (req, res) => {
  const b = req.body ?? {};
  const nome = String(b.nome ?? "").trim();
  const preco = Number(b.preco);

  if (!nome || !(preco > 0)) {
    res.status(400).json({ erro: "Informe nome e preço maior que zero." });
    return;
  }
  if (!["eletronico", "alimento", "vestuario"].includes(b.categoria)) {
    res.status(400).json({ erro: "Categoria inválida." });
    return;
  }

  let produto: Produto;
  const codigo = catalogo.gerarCodigo();
  if (b.categoria === "eletronico") {
    produto = new Eletronico(codigo, nome, preco, String(b.marca ?? ""), Number(b.garantiaMeses) || 0);
  } else if (b.categoria === "alimento") {
    produto = new Alimento(codigo, nome, preco, String(b.validade ?? ""), Number(b.pesoKg) || 0);
  } else {
    produto = new Vestuario(codigo, nome, preco, String(b.tamanho ?? ""), String(b.material ?? ""));
  }

  catalogo.adicionar(produto);
  res.status(201).json({ codigo });
});

app.listen(3001, () => console.log("API rodando em http://localhost:3001"));
