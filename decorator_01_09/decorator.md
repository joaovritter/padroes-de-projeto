# Padrão de Projeto Decorator

## O que é

Decorator é um padrão estrutural que permite adicionar comportamentos/responsabilidades a um objeto dinamicamente, envolvendo-o em objetos "decoradores" que implementam a mesma interface. Cada decorador delega ao objeto envolvido e acrescenta seu próprio comportamento antes ou depois da delegação.

No exercício, cada ingrediente (leite, chocolate, chantilly, caramelo) é um decorador que envolve uma `Bebida` e acrescenta sua parte na descrição e no preço, sem precisar criar uma classe para cada combinação possível.

## Como usar (receita geral)

1. Defina a interface comum (`Bebida`, com `getDescricao()` e `getPreco()`).
2. Crie o componente concreto base (`CafeSimples`), que implementa a interface com o comportamento padrão.
3. Crie uma classe abstrata decoradora (`BebidaDecorator`) que também implementa a interface e guarda uma referência (composição) para outro objeto `Bebida`, delegando as chamadas a ele.
4. Crie um decorador concreto para cada responsabilidade extra (`LeiteDecorator`, `ChocolateDecorator`, etc.), que chama o método delegado e soma seu próprio valor/texto.
5. No cliente, empilhe decoradores em torno do componente base conforme a combinação desejada:
   ```java
   Bebida cafe = new ChocolateDecorator(new LeiteDecorator(new CafeSimples()));
   ```

## Quando usar

- Quando é preciso adicionar responsabilidades a objetos individuais dinamicamente, sem afetar outros objetos da mesma classe.
- Quando a herança geraria uma explosão de subclasses para cobrir todas as combinações possíveis (como `CafeComLeiteEChocolate`, `CafeComLeiteChocolateEChantilly`, etc.).
- Quando se quer poder combinar e repetir responsabilidades livremente (ex.: dois chocolates).

## Quando NÃO usar

- Quando as combinações possíveis são poucas e fixas — nesse caso, um conjunto pequeno de subclasses pode ser mais simples de entender.
- Quando a ordem de empilhamento dos decoradores importa para o resultado e isso não fica claro para quem lê o código do cliente (pode gerar bugs sutis).
- Quando o excesso de camadas de decoradores prejudica a legibilidade/depuração (pilhas muito profundas de wrappers).

## Questões para reflexão

**1. Qual é o principal problema que o padrão Decorator resolve neste sistema?**
Evita a explosão combinatória de subclasses. Sem o Decorator, seria necessário criar uma classe para cada combinação de ingredientes (`CafeComLeite`, `CafeComChocolate`, `CafeComLeiteEChocolate`, ...), o que cresce exponencialmente conforme aumentam os ingredientes.

**2. Qual seria a desvantagem de criar uma classe para cada combinação possível de ingredientes?**
O número de classes cresceria muito rápido (crescimento combinatório), tornando o código difícil de manter, duplicando lógica de cálculo de preço/descrição e dificultando a adição de um novo ingrediente (seria preciso criar novas classes para cada combinação existente).

**3. Qual é a função da classe BebidaDecorator?**
Serve como classe abstrata base para todos os decoradores concretos. Ela implementa a interface `Bebida`, mantém a referência (composição) para o objeto `Bebida` decorado e fornece a implementação padrão de delegação, evitando repetição de código nos decoradores concretos.

**4. Por que os Decorators implementam a mesma interface Bebida?**
Para que decoradores e o componente concreto sejam intercambiáveis do ponto de vista do cliente. Isso permite empilhar decoradores livremente (um decorador pode envolver outro decorador ou o componente base) e o cliente sempre manipula tudo através da interface `Bebida`, sem saber quantas camadas existem por trás.

**5. Como o padrão Decorator permite adicionar funcionalidades sem modificar a classe CafeSimples?**
Porque a funcionalidade extra (ingrediente) é colocada em classes separadas (os decoradores) que envolvem o objeto `CafeSimples` por composição, chamando seus métodos e acrescentando comportamento. A classe `CafeSimples` nunca precisa ser alterada para suportar novos ingredientes.

**6. Qual princípio de orientação a objetos pode ser relacionado ao uso do Decorator neste exercício?**
O Princípio Aberto/Fechado (Open/Closed Principle) — o sistema fica aberto para extensão (novos decoradores/ingredientes) e fechado para modificação (as classes existentes, como `CafeSimples`, não precisam ser alteradas). Também se relaciona com "preferir composição a herança".
