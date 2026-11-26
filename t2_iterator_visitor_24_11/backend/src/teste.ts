// Teste simples das regras de imposto/desconto e do percurso com o iterator
import assert from "node:assert/strict";
import { CatalogoProdutos } from "./iterator/CatalogoProdutos";
import { ImpostoVisitor } from "./visitor/ImpostoVisitor";
import { DescontoVisitor } from "./visitor/DescontoVisitor";
import { RelatorioVisitor } from "./visitor/RelatorioVisitor";
import { Eletronico } from "./produtos/Eletronico";
import { Alimento } from "./produtos/Alimento";
import { Vestuario } from "./produtos/Vestuario";

const catalogo = new CatalogoProdutos();
catalogo.adicionar(new Eletronico(1, "TV", 100, "LG", 12));
catalogo.adicionar(new Alimento(2, "Feijão", 100, "2027-01-01", 1));
catalogo.adicionar(new Vestuario(3, "Calça", 100, "42", "Jeans"));

assert.deepEqual(catalogo.aplicar(new ImpostoVisitor()), [15, 5, 10]);
assert.deepEqual(catalogo.aplicar(new DescontoVisitor()), [10, 5, 20]);

const relatorio = catalogo.aplicar(new RelatorioVisitor());
assert.equal(relatorio.length, 3);
assert.equal(relatorio[2].detalhes, "Tamanho: 42 | Material: Jeans");

let contador = 0;
const it = catalogo.createIterator();
while (it.hasNext()) {
  it.next();
  contador++;
}
assert.equal(contador, 3);

console.log("Todos os testes passaram.");
console.table(relatorio);
