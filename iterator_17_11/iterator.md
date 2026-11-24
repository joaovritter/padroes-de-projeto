# Padrão de Projeto Iterator

## O que é

Iterator é um padrão comportamental que permite **percorrer** os elementos de uma coleção sem expor a estrutura interna dela (lista, array, árvore...). A coleção só entrega um iterator, e o cliente usa `hasNext()` e `next()`.

## Participantes neste exercício

| Papel | Classe |
|---|---|
| **Elemento** | `Livro` |
| **Iterator (interface)** | `LivroIterator` (`hasNext()`, `next()`) |
| **Iterator concreto** | `CatalogoIterator` (guarda a lista e a `posicao`) |
| **Coleção** | `Catalogo` (`List<Livro>` privada, `createIterator()`) |
| **Cliente** | `Main` |

## Fluxo

```
Main --createIterator()--> Catalogo
Catalogo --cria--> CatalogoIterator
CatalogoIterator --implements--> LivroIterator
CatalogoIterator --percorre--> List<Livro>
```

## Resultado

A `Main` percorre os 5 livros só com `hasNext()` e `next()`, sem acessar a lista interna nem usar índices.

## Quando usar

- Quando a coleção tem estrutura interna que não deve ser exposta.
- Quando se quer formas diferentes de percorrer a mesma coleção.

## Quando NÃO usar

- Coleções simples onde o `for-each` do Java (`Iterable`) já resolve.
