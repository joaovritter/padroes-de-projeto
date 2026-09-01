# Padrão de Projeto Abstract Factory

## O que é

Abstract Factory é um padrão criacional que fornece uma interface para criar **famílias de objetos relacionados** (ex.: Botão, Menu e Checkbox de uma mesma plataforma) sem que o código cliente precise conhecer as classes concretas de cada família.

Em vez de o cliente decidir "se é Windows, uso `new BotaoWindows()`", ele recebe uma fábrica já configurada para a plataforma correta e pede objetos genéricos (`Botao`, `Menu`, `Checkbox`) — a fábrica é quem sabe qual variante concreta instanciar.

## Como usar (receita geral)

1. Defina interfaces para cada **produto** da família (`Botao`, `Menu`, `Checkbox`), cada uma com seu contrato (`exibir()`).
2. Implemente uma classe concreta de cada produto por família (`BotaoWindows`, `MenuWindows`, ... / `BotaoLinux`, `MenuLinux`, ...).
3. Defina uma interface `FabricaInterface` com um método de criação para cada produto:
   ```java
   Botao criarBotao();
   Menu criarMenu();
   Checkbox criarCheckbox();
   ```
4. Implemente uma fábrica concreta por família (`FabricaWindows`, `FabricaLinux`, ...), cada uma retornando os produtos da sua própria família.
5. O código cliente (`Aplicacao`) recebe uma `FabricaInterface` pelo construtor e usa apenas os métodos da fábrica para criar os componentes — nunca `new BotaoWindows()` diretamente:
   ```java
   public Aplicacao(FabricaInterface fabrica) {
       this.botao = fabrica.criarBotao();
       this.menu = fabrica.criarMenu();
       this.checkbox = fabrica.criarCheckbox();
   }
   ```
6. Trocar de família é trocar apenas a fábrica passada no construtor — o resto do código não muda.

## Quando usar

- O sistema precisa suportar **múltiplas famílias de produtos relacionados** que precisam ser usados de forma consistente entre si (não faz sentido misturar botão do Windows com menu do Linux).
- Você quer isolar o código cliente das classes concretas, permitindo trocar a família inteira (ex.: tema, plataforma, fornecedor) sem alterar a lógica de negócio.
- Novas famílias podem ser adicionadas no futuro (ex.: Mac) sem alterar as classes já existentes.

## Quando NÃO usar

- Existe apenas uma família de produtos, sem variação — uma fábrica simples (Factory Method) ou até `new` direto já resolve.
- As famílias mudam com muita frequência ou têm produtos muito diferentes entre si — pode ser difícil manter a interface comum.
- Adicionar um novo **produto** (não uma nova família) exige alterar a interface `FabricaInterface` e todas as fábricas concretas — pode gerar bastante retrabalho.

## Vantagens

- **Consistência**: garante que os produtos criados pertencem à mesma família (não mistura Windows com Linux).
- **Desacoplamento**: o cliente depende apenas de interfaces (`FabricaInterface`, `Botao`, `Menu`, `Checkbox`), nunca das classes concretas.
- **Extensibilidade por família**: adicionar uma nova família (ex.: Mac) não exige alterar o código já existente do cliente (`Aplicacao`), só criar novas classes.
- **Facilita testes**: é possível criar fábricas de teste/mock que implementam a mesma interface.

## Desvantagens

- **Mais classes e interfaces**: para cada produto e cada família, é preciso criar uma classe — o número de arquivos cresce rápido.
- **Difícil adicionar novos produtos**: adicionar um novo tipo de produto (ex.: `JanelaModal`) exige mudar a interface da fábrica e todas as implementações concretas.
- **Complexidade desnecessária** quando só existe uma variação de produto.

## Variações comuns

- **Abstract Factory + Factory Method**: cada método `criarX()` da fábrica é, na prática, um Factory Method.
- **Fábrica como Singleton**: em muitos sistemas, a fábrica concreta escolhida (ex.: `FabricaWindows`) é instanciada uma única vez e reutilizada.
- **Seleção dinâmica da fábrica**: em vez de escolher a fábrica manualmente no código (`new FabricaWindows()`), o sistema pode detectar a plataforma em tempo de execução e escolher automaticamente.

## Resumo rápido

| Aspecto | Abstract Factory |
|---|---|
| Cria | Famílias inteiras de objetos relacionados |
| Ideal para | Múltiplas variantes que precisam ser consistentes entre si |
| Cliente conhece | Apenas interfaces (`FabricaInterface`, produtos) |
| Trocar de família | Basta trocar a fábrica concreta usada |
| Custo | Muitas classes/interfaces para manter |
