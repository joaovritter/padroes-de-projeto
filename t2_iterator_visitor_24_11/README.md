# Trabalho T2 - Iterator e Visitor

Sistema de gerenciamento de produtos de uma loja (eletrônicos, alimentos e vestuário).
Permite cadastrar produtos e gerar um relatório com preço original, imposto e desconto de cada um.

- **Backend:** Node + TypeScript (Express) - é onde estão os padrões.
- **Frontend:** React + Material UI - só a tela de cadastro e o relatório.

## Como o sistema funciona

1. Na tela, o usuário escolhe a categoria e preenche os dados comuns (nome, preço) e os específicos dela.
2. O backend cria o objeto da categoria certa (`Eletronico`, `Alimento` ou `Vestuario`) e adiciona no `CatalogoProdutos`.
3. Para mostrar a listagem, o backend percorre o catálogo com o **Iterator** e aplica o `RelatorioVisitor` em cada produto.
4. A tela recebe as linhas prontas e mostra preço original, imposto e desconto.

## Estrutura

```
backend/src/
├── produtos/            -> Elements do Visitor
│   ├── Produto.ts           (classe abstrata com accept)
│   ├── Eletronico.ts
│   ├── Alimento.ts
│   └── Vestuario.ts
├── visitor/             -> padrão VISITOR
│   ├── ProdutoVisitor.ts    (interface)
│   ├── ImpostoVisitor.ts
│   ├── DescontoVisitor.ts
│   └── RelatorioVisitor.ts
├── iterator/            -> padrão ITERATOR
│   ├── ProdutoIterator.ts   (interface)
│   ├── CatalogoIterator.ts  (iterador concreto)
│   └── CatalogoProdutos.ts  (coleção + integração com o Visitor)
├── server.ts            -> rotas da API
└── teste.ts             -> teste simples com assert
```

## Padrão Visitor

### O problema

A loja quer fazer várias operações sobre os produtos (imposto, desconto, relatório) e já avisou que vão vir outras
(frete, lucratividade...). Se cada operação virasse um método dentro de `Eletronico`, `Alimento` e `Vestuario`,
toda operação nova obrigaria a mexer nas três classes.

### Como foi resolvido

O Visitor tira a operação de dentro do produto e coloca numa classe separada.

| Papel | Classe |
|---|---|
| **Visitor** (interface) | `ProdutoVisitor<R>` |
| **Visitors concretos** | `ImpostoVisitor`, `DescontoVisitor`, `RelatorioVisitor` |
| **Element** | `Produto` (método abstrato `accept`) |
| **Elements concretos** | `Eletronico`, `Alimento`, `Vestuario` |

### Como funciona

1. A interface `ProdutoVisitor` tem um método para cada categoria: `visitEletronico`, `visitAlimento` e `visitVestuario`.
2. Cada produto implementa `accept(visitor)` e chama o método certo para o seu tipo. Por exemplo, em `Eletronico`:

   ```ts
   accept<R>(visitor: ProdutoVisitor<R>): R {
     return visitor.visitEletronico(this);
   }
   ```

   Isso é o **double dispatch**: o método executado depende do tipo do produto **e** do tipo do visitor.
3. Cada visitor concreto tem a regra de negócio de uma operação:
   - `ImpostoVisitor`: 15% eletrônicos, 5% alimentos, 10% vestuário.
   - `DescontoVisitor`: 10% eletrônicos, 5% alimentos, 20% vestuário.
   - `RelatorioVisitor`: monta uma linha com dados gerais + específicos do produto. Ele reaproveita os outros
     dois visitors para calcular imposto e desconto separadamente.
4. O genérico `R` deixa cada visitor devolver o tipo que faz sentido (`number` no imposto, `LinhaRelatorio` no relatório).

### Para adicionar uma operação nova (ex: frete)

Cria um `FreteVisitor implements ProdutoVisitor<number>` com as três regras e pronto.
**Nenhuma classe de produto é alterada.**

## Padrão Iterator

### O problema

O sistema precisa percorrer os produtos sem que quem usa saiba como eles estão guardados por dentro
(array, lista, banco...).

### Como foi resolvido

| Papel | Classe |
|---|---|
| **Iterator** (interface) | `ProdutoIterator` (`hasNext()` e `next()`) |
| **Iterator concreto** | `CatalogoIterator` |
| **Aggregate** (coleção) | `CatalogoProdutos` (`createIterator()`) |

### Como funciona

1. `CatalogoProdutos` guarda os produtos num array **privado**. Ninguém de fora acessa esse array.
2. Para percorrer, pede-se um iterador com `createIterator()`.
3. `CatalogoIterator` guarda a posição atual e responde `hasNext()` / `next()`.

```ts
const it = catalogo.createIterator();
while (it.hasNext()) {
  const produto = it.next();
}
```

Se um dia o catálogo trocar o array por outra estrutura, só o `CatalogoIterator` muda. Quem percorre continua igual.

## Integração Iterator + Visitor

A ligação dos dois padrões fica no método `aplicar` de `CatalogoProdutos`:

```ts
aplicar<R>(visitor: ProdutoVisitor<R>): R[] {
  const resultados: R[] = [];
  const it = this.createIterator();        // Iterator percorre
  while (it.hasNext()) {
    resultados.push(it.next().accept(visitor)); // Visitor opera em cada produto
  }
  return resultados;
}
```

- O **Iterator** cuida de *como percorrer* a coleção.
- O **Visitor** cuida de *o que fazer* com cada produto.

A rota `GET /api/relatorio` usa exatamente isso: `catalogo.aplicar(new RelatorioVisitor())`.
Para calcular o total de impostos da loja, por exemplo, bastaria `catalogo.aplicar(new ImpostoVisitor())`.
