// Teste simples com a sequência do enunciado
import assert from "node:assert/strict";
import { GerenciadorPedidos } from "./GerenciadorPedidos";

const g = new GerenciadorPedidos();
const pedido = g.cadastrar("Ana", "1x Mouse", 100);
const situacao = () => pedido.dados().situacao;

assert.equal(situacao(), "Aguardando pagamento");
g.executar(1, "pagar");
g.executar(1, "enviar");
assert.equal(situacao(), "Enviado");

// operação inválida: dá erro e não gera registro no histórico
assert.throws(() => g.executar(1, "cancelar"), /já foi enviado/);
assert.equal(g.listar()[0].versoesAnteriores, 2);

g.desfazer(1);
assert.equal(situacao(), "Pago");
g.desfazer(1);
assert.equal(situacao(), "Aguardando pagamento");
assert.throws(() => g.desfazer(1), /Não há alterações/);

// restaurar volta os dados e as regras do estado restaurado
g.editar(1, "Ana Paula", "2x Mouse", 200);
assert.throws(() => g.executar(1, "entregar"), /pagamento ainda não foi confirmado/);
g.desfazer(1);
assert.equal(pedido.dados().cliente, "Ana");
assert.equal(pedido.dados().valorTotal, 100);

g.executar(1, "cancelar");
assert.throws(() => g.editar(1, "X", "Y", 1), /foi cancelado/);

console.log("Todos os testes passaram.");
