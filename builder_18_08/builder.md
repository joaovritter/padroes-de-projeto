# Padrão de Projeto Builder

## O que é

Builder é um padrão criacional que separa a **construção** de um objeto complexo da sua **representação final**, permitindo montar o objeto passo a passo através de um objeto intermediário (o Builder) e finalizá-lo com um método `build()`.

Em vez de um construtor com muitos parâmetros (alguns obrigatórios, outros opcionais), você encadeia métodos que configuram cada atributo, e só no final o objeto é efetivamente criado e validado.

## Como usar (receita geral)

1. A classe final (`Computador`) tem construtor **privado**, que só o Builder pode chamar.
2. Uma classe estática interna `Builder` guarda os mesmos campos.
3. Cada método "setter" do Builder retorna `this`, permitindo encadeamento (method chaining):
   ```java
   Builder processador(String p) {
       this.processador = p;
       return this;
   }
   ```
4. O método `build()` valida o estado e cria o objeto final:
   ```java
   public Computador build() {
       if (processador == null || processador.isEmpty())
           throw new IllegalStateException("Processador obrigatório");
       return new Computador(this);
   }
   ```
5. Uso no código cliente:
   ```java
   Computador pc = new Computador.Builder()
       .processador("i7")
       .memoriaRAM(16)
       .armazenamento(512)
       .build();
   ```

## Quando usar

- O objeto tem **muitos parâmetros**, e vários são opcionais (evita "telescoping constructors" — vários construtores sobrecarregados).
- Você quer que o objeto final seja **imutável** (campos `final`, sem setters públicos).
- A ordem de configuração não importa e alguns campos podem ficar de fora.
- Você quer **validar** o objeto antes dele existir (impedir estados inválidos).
- Facilita muito a leitura no código cliente: `new Builder().campoA(x).campoB(y).build()` é mais claro que um construtor com 8 parâmetros posicionais.

## Quando NÃO usar

- Objetos simples, com poucos atributos (2-3), todos obrigatórios — um construtor comum já resolve, o Builder vira boilerplate desnecessário.
- Quando os atributos podem ser setados livremente depois de criado (aí um simples objeto mutável com setters resolve, sem necessidade do padrão).
- Quando performance/memória são extremamente críticas — o Builder cria um objeto intermediário extra antes do objeto final.
- Se o projeto já usa bibliotecas que geram builders automaticamente (ex.: Lombok `@Builder` em Java), pode não valer a pena escrever manualmente, a menos que precise de lógica de validação customizada.

## Vantagens

- **Legibilidade**: chamadas nomeadas (`.memoriaRAM(16)`) são muito mais claras que parâmetros posicionais.
- **Imutabilidade**: o objeto final pode ter todos os campos `final`, sem risco de ser alterado depois de criado.
- **Flexibilidade**: parâmetros opcionais podem ser omitidos sem precisar de dezenas de construtores sobrecarregados.
- **Validação centralizada**: o `build()` é o único lugar que decide se o objeto pode ou não existir, evitando estados inconsistentes.
- **Separação de responsabilidades**: a lógica de "como construir" fica isolada da classe de domínio.

## Desvantagens

- **Mais código**: dobra (ou mais) a quantidade de classes/métodos comparado a um construtor simples.
- **Duplicação de campos**: os atributos aparecem tanto na classe Builder quanto na classe final.
- **Complexidade desnecessária** para objetos simples — pode ser visto como over-engineering.
- Precisa de disciplina para manter builder e classe final sincronizados quando o objeto evolui (novo atributo = mexer nos dois lugares).

## Variações comuns

- **Builder com validação no `build()`** (como neste exercício): impede a criação de objetos inválidos, lançando exceção (`IllegalStateException` ou uma exceção customizada).
- **Builder fluente com métodos de conveniência**: métodos extras tipo `.semRede()` que já configuram `wifi(false).bluetooth(false)`.
- **Director**: em implementações mais "clássicas" do GoF, existe uma classe `Director` que sabe montar receitas prontas (ex.: `criarComputadorGamer()`), delegando ao Builder. Em Java moderno isso é menos comum — geralmente o cliente monta diretamente.
- **Lombok `@Builder`**: em projetos reais Java, é comum usar a anotação `@Builder` do Lombok para gerar tudo isso automaticamente, evitando o boilerplate manual. Vale a pena quando não é necessária validação customizada complexa.

## Resumo rápido

| Aspecto | Builder |
|---|---|
| Cria objetos | Passo a passo, de forma fluente |
| Ideal para | Objetos com muitos parâmetros opcionais |
| Objeto final | Pode ser imutável |
| Validação | Centralizada no `build()` |
| Custo | Mais classes/código para manter |
