# Padrão de Projeto Facade

## O que é

Facade é um padrão estrutural que fornece uma interface única e simplificada para um conjunto de interfaces de um subsistema mais complexo, escondendo os detalhes de como os componentes internos interagem.

No exercício, `ComputadorFacade` esconde a complexidade de coordenar `Fonte`, `Processador`, `Memoria`, `Disco` e `SistemaOperacional`, oferecendo ao cliente apenas `ligarComputador()` (e, no desafio, `desligarComputador()`).

## Como usar (receita geral)

1. Identifique o subsistema com várias classes que precisam ser usadas em conjunto, numa ordem/sequência específica (`Fonte`, `Processador`, `Memoria`, `Disco`, `SistemaOperacional`).
2. Crie uma classe fachada (`ComputadorFacade`) que mantenha referências para essas classes internas.
3. Na fachada, exponha métodos de alto nível (`ligarComputador()`, `desligarComputador()`) que chamam os métodos internos na ordem correta, encapsulando a lógica de coordenação.
4. O cliente (`Main`) interage apenas com a fachada, sem conhecer ou instanciar diretamente as classes internas.

## Quando usar

- Quando um subsistema é complexo, com muitas classes e uma ordem de chamadas específica que não deveria ser responsabilidade do cliente.
- Quando se quer reduzir o acoplamento entre o código cliente e as classes internas de um subsistema, permitindo que ele evolua sem afetar quem o utiliza pela fachada.
- Quando se deseja fornecer um ponto de entrada simples para operações comuns, mantendo o acesso avançado (direto às classes internas) disponível para quem realmente precisar.

## Quando NÃO usar

- Quando o subsistema já é simples e uma fachada não agregaria valor, apenas uma camada extra desnecessária.
- Quando o cliente realmente precisa de controle fino sobre os componentes internos (a fachada tende a expor só os casos de uso mais comuns).
- Quando adicionar uma fachada esconderia informações importantes que o cliente precisaria decidir (ex.: tratar falhas específicas de um componente).

## Questões para reflexão

**1. Qual problema o padrão Facade resolve neste sistema?**
Evita que o código cliente precise conhecer e coordenar diretamente todos os componentes internos (`Fonte`, `Processador`, `Memoria`, `Disco`, `SistemaOperacional`) e a ordem correta de inicialização, reduzindo a complexidade e o acoplamento do cliente com o subsistema.

**2. Qual é a principal função da classe ComputadorFacade?**
Centralizar e orquestrar as chamadas aos componentes internos do computador, fornecendo uma interface simples (`ligarComputador()`/`desligarComputador()`) que executa a sequência correta de operações internamente.

**3. O que aconteceria se a classe Main precisasse conhecer diretamente todos os componentes do computador?**
O código ficaria fortemente acoplado às classes internas, mais complexo e repetitivo (a lógica de sequência teria que ser reescrita em todo lugar que precisasse ligar o computador), e qualquer mudança na ordem ou nos componentes internos exigiria alterar o código cliente.

**4. O padrão Facade elimina as classes internas do sistema?**
Não. As classes internas (`Fonte`, `Processador`, etc.) continuam existindo e podem ser usadas diretamente se necessário. A Facade apenas oferece uma camada adicional simplificada por cima delas, sem removê-las nem impedir seu uso direto.

**5. Qual é a principal vantagem de utilizar uma única interface simplificada para acessar um subsistema complexo?**
Reduz o acoplamento e a complexidade percebida pelo cliente, tornando o código mais legível e fácil de usar, além de isolar mudanças internas do subsistema — se a implementação interna mudar, só a fachada precisa ser ajustada.

**6. Em que outros sistemas do mundo real o padrão Facade poderia ser utilizado?**
Exemplos: bibliotecas de acesso a banco de dados (uma classe única que esconde conexão, query e fechamento de recursos), SDKs de pagamento (uma fachada que coordena autenticação, validação e cobrança), sistemas de home automation (uma fachada "ModoCinema" que ajusta luzes, som e TV), ou APIs de envio de e-mail/SMS que escondem detalhes de autenticação, formatação e envio.
