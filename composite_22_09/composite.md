# Padrão de Projeto Composite

## O que é

Composite é um padrão estrutural que permite tratar objetos individuais e composições de objetos de maneira uniforme, organizando-os em uma estrutura de árvore que representa hierarquias parte-todo. Tanto a folha quanto o composto implementam a mesma interface, então o cliente não precisa distinguir se está lidando com um elemento isolado ou com um grupo de elementos.

No exercício, um `Edificio` é composto por vários `Apartamento` (composite), e cada `Apartamento` contém um ou mais `Morador` (leaf). Todos implementam `ComponenteEdificio`, permitindo chamar `exibirDetalhes()` a partir do `Edificio` e propagar a chamada recursivamente por toda a árvore.

## Como usar (receita geral)

1. Defina a interface componente (`ComponenteEdificio`) com a operação comum a todos os níveis da hierarquia (`exibirDetalhes()`).
2. Crie a classe folha (`Morador`), que implementa a interface diretamente, sem filhos, apenas com o comportamento concreto do nível mais baixo.
3. Crie as classes compostas (`Apartamento`, `Edificio`), que também implementam a interface, mantêm uma coleção de componentes filhos e oferecem métodos para adicionar/remover filhos (`adicionar()`, `remover()`).
4. Na implementação da operação comum de um composto, delegue a chamada para cada filho, propagando a execução recursivamente pela árvore.
5. No cliente (`Main`), monte a árvore (edifício → apartamentos → moradores) e invoque a operação apenas no nível raiz — o restante é resolvido pela recursão interna.

## Quando usar

- Quando o domínio é naturalmente uma hierarquia parte-todo (árvore), como sistemas de arquivos, estruturas organizacionais ou, como no exercício, edifícios/apartamentos/moradores.
- Quando se quer que o código cliente trate objetos individuais e composições da mesma forma, sem verificar tipos com `if`/`instanceof` ou fazer casts.
- Quando é necessário aplicar operações recursivamente sobre toda a estrutura (ex.: exibir detalhes, somar valores, calcular métricas) sem que o cliente conheça a profundidade da árvore.

## Quando NÃO usar

- Quando a estrutura de dados não é realmente hierárquica, apenas uma lista simples — o padrão adicionaria complexidade sem necessidade.
- Quando folhas e compostos têm comportamentos muito diferentes e forçar uma interface comum tornaria a operação genérica (ex.: `exibirDetalhes()`) sem sentido para alguns tipos.
- Quando é importante restringir quais tipos de filhos um composto pode conter (ex.: um `Apartamento` só pode ter `Morador`) — o Composite tende a facilitar demais a adição de qualquer componente compatível com a interface, exigindo cuidado extra ou validações adicionais.

## Questões para reflexão

**1. Qual problema o padrão Composite resolve neste sistema?**
Evita que o código cliente precise verificar o tipo de cada objeto (morador, apartamento ou edifício) com condicionais ou casts para exibir informações. Com o Composite, `Edificio`, `Apartamento` e `Morador` são tratados de forma uniforme através da interface `ComponenteEdificio`.

**2. Qual é a diferença entre a classe Morador (Leaf) e as classes Apartamento/Edificio (Composite)?**
`Morador` é uma folha: implementa a interface diretamente e não possui filhos, apenas executa seu próprio comportamento. `Apartamento` e `Edificio` são compostos: além de implementar a interface, mantêm uma coleção de componentes filhos e, ao executar a operação, delegam a chamada para cada filho antes ou depois de sua própria lógica.

**3. Por que Apartamento e Edificio implementam a mesma interface que Morador?**
Para que o cliente possa tratá-los de forma intercambiável. Isso permite montar árvores de qualquer profundidade e chamar a mesma operação (`exibirDetalhes()`) no nível raiz, sem que o cliente precise saber se está lidando com um elemento simples ou com uma estrutura composta.

**4. O que aconteceria se a classe Main precisasse percorrer manualmente cada apartamento e cada morador para exibir os detalhes?**
O código ficaria acoplado à estrutura interna do edifício, com laços aninhados e verificações de tipo espalhadas pelo cliente. Qualquer mudança na hierarquia (um novo nível, por exemplo "Bloco" entre `Edificio` e `Apartamento`) exigiria reescrever a lógica de percurso no cliente, em vez de apenas ajustar as classes do Composite.

**5. Como a recursão é utilizada na implementação do Composite?**
Cada composto (`Apartamento`, `Edificio`) implementa `exibirDetalhes()` chamando `exibirDetalhes()` de cada um dos seus filhos. Como os filhos podem ser outros compostos ou folhas, a chamada se propaga recursivamente até alcançar as folhas (`Morador`), que apenas executam seu comportamento sem repassar a chamada adiante.

**6. Em que outros sistemas do mundo real o padrão Composite poderia ser utilizado?**
Exemplos: sistemas de arquivos (pastas contendo arquivos e outras pastas), estruturas organizacionais de empresas (departamentos contendo equipes e funcionários), menus de interface gráfica (menus contendo submenus e itens), ou árvores de componentes visuais (um painel contendo outros painéis e botões).
