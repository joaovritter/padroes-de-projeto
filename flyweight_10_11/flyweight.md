# Padrão de Projeto Flyweight

## O que é

Flyweight é um padrão estrutural que economiza memória **compartilhando** o que é igual entre muitos objetos. O estado é dividido em:

- **Intrínseco:** igual para muitos objetos, fica no flyweight compartilhado (`especie`, `imagem`, `cor`).
- **Extrínseco:** diferente em cada objeto, fica fora do flyweight (`x`, `y`).

Uma fábrica garante que cada flyweight seja criado uma única vez e reutilizado.

## Participantes neste exercício

| Papel | Classe |
|---|---|
| **Flyweight** | `TipoArvore` |
| **Flyweight Factory** | `FabricaTipoArvore` (`Map<String, TipoArvore>`) |
| **Contexto** | `Arvore` (guarda `x`, `y` e a referência ao tipo) |
| **Cliente** | `Floresta`, `Main` |

## Resultado

15 árvores, apenas 3 objetos `TipoArvore`.

## Quando usar

- Quando existem muitíssimos objetos parecidos e a memória é um problema.
- Quando boa parte do estado pode ser movida para fora do objeto.

## Quando NÃO usar

- Quando há poucos objetos ou quase nenhum estado repetido: a fábrica só complica.

## Observação

O flyweight deve ser imutável (campos `final`), pois é compartilhado.
