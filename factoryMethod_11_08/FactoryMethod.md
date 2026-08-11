# Factory Method

> Padrão criacional de projeto (Gang of Four) também conhecido como **Método Fábrica** ou **Construtor Virtual**.

## O que é

O Factory Method resolve um problema simples: **quem decide qual objeto criar não deveria ser o mesmo código que usa esse objeto.**

Ele propõe substituir chamadas diretas ao `new` por chamadas a um **método fábrica**. Esse método fica em uma classe "criadora" (abstrata) e é sobrescrito por subclasses, cada uma responsável por instanciar um tipo diferente de produto. O código cliente continua trabalhando apenas com a interface/classe abstrata do produto — sem nunca saber qual implementação concreta está por trás.

Em outras palavras: você tira a responsabilidade de "decidir o tipo" do meio do seu código de negócio e concentra ela em um lugar só, delegado por herança.

## Por que usar

Sem o padrão, é comum o código ficar cheio de `if/else` ou `switch` decidindo qual classe instanciar toda vez que precisa de um objeto. Isso traz problemas:

- **Toda vez que surge um tipo novo**, é preciso caçar todos os lugares que têm esse `if/else` e adicionar mais um `case`.
- **Viola o princípio Aberto/Fechado** (open/closed): a classe deveria estar aberta para extensão, mas fechada para modificação — e aqui você sempre precisa modificar o código existente para adicionar algo novo.
- A lógica de "como criar o objeto" fica **misturada** com a lógica de "o que fazer com o objeto", dificultando manutenção e testes.

Com o Factory Method:

- Para adicionar um novo tipo, você **cria uma nova classe** (produto + fábrica concreta) e **não toca** no código já existente.
- A criação do objeto fica isolada em um único lugar (a fábrica), seguindo o **Princípio da Responsabilidade Única**.
- O código cliente trabalha só com abstrações, então fica desacoplado das classes concretas.

## Estrutura

| Papel | O que é | No nosso exemplo |
|---|---|---|
| **Product** (Produto) | Interface comum a todos os objetos criados | `Pagamento` |
| **ConcreteProduct** (Produto Concreto) | Implementações diferentes do produto | `PagamentoPix`, `PagamentoCartaoCredito`, `PagamentoBoleto` |
| **Creator** (Criador) | Classe abstrata com o método fábrica + alguma lógica de negócio que usa o produto | `ProcessadorPagamento` |
| **ConcreteCreator** (Criador Concreto) | Sobrescreve o método fábrica para retornar um produto específico | `ProcessadorPix`, `ProcessadorCartaoCredito`, `ProcessadorBoleto` |

A regra de ouro: o **Creator não cria o produto sozinho na lógica de negócio** — ele chama o método fábrica, e é a subclasse que decide o tipo. Por isso, dentro do método que usa o produto (equivalente ao `notificar()` do seu exercício), nunca deveria existir `if/else`/`switch` escolhendo a classe — quem escolhe é o polimorfismo.

---

## Exemplo: Sistema de Pagamentos (PIX, Cartão de Crédito, Boleto)

### ❌ Antes — sem Factory Method (cheio de `if/else`)

```java
public class ProcessadorPagamento {

    public void processar(String tipo, double valor) {
        if (tipo.equals("PIX")) {
            System.out.println("Gerando QR Code do PIX...");
            System.out.println("Pagamento de R$ " + valor + " aprovado instantaneamente.");
        } else if (tipo.equals("CARTAO_CREDITO")) {
            System.out.println("Validando dados do cartão...");
            double taxa = valor * 0.03;
            System.out.println("Pagamento de R$ " + (valor + taxa) + " aprovado (com taxa de 3%).");
        } else if (tipo.equals("BOLETO")) {
            System.out.println("Gerando boleto bancário...");
            System.out.println("Pagamento de R$ " + valor + " registrado. Vencimento em 3 dias úteis.");
        } else {
            throw new IllegalArgumentException("Tipo de pagamento desconhecido: " + tipo);
        }
    }
}
```

```java
public class Main {
    public static void main(String[] args) {
        ProcessadorPagamento processador = new ProcessadorPagamento();
        processador.processar("PIX", 150.0);
        processador.processar("CARTAO_CREDITO", 300.0);
        processador.processar("BOLETO", 80.0);
    }
}
```

**Problemas desse código:**
- Se amanhã surgir "PIX_PARCELADO" ou "VALE_ALIMENTACAO", você precisa **editar** `processar()` de novo.
- Se esse `if/else` estiver repetido em vários pontos do sistema (relatórios, validações, etc.), você precisa achar e corrigir **todos** eles.
- Tipo do pagamento é uma `String` solta — fácil de digitar errado (`"Pix"` vs `"PIX"`) sem o compilador acusar nada.
- Toda a lógica de negócio de cada forma de pagamento está amontoada em um método só.

---

### ✅ Depois — com Factory Method

**1. Interface do produto**

```java
public interface Pagamento {
    void pagar(double valor);
}
```

**2. Produtos concretos**

```java
public class PagamentoPix implements Pagamento {
    @Override
    public void pagar(double valor) {
        System.out.println("Gerando QR Code do PIX...");
        System.out.println("Pagamento de R$ " + valor + " aprovado instantaneamente.");
    }
}
```

```java
public class PagamentoCartaoCredito implements Pagamento {
    @Override
    public void pagar(double valor) {
        System.out.println("Validando dados do cartão...");
        double taxa = valor * 0.03;
        System.out.println("Pagamento de R$ " + (valor + taxa) + " aprovado (com taxa de 3%).");
    }
}
```

```java
public class PagamentoBoleto implements Pagamento {
    @Override
    public void pagar(double valor) {
        System.out.println("Gerando boleto bancário...");
        System.out.println("Pagamento de R$ " + valor + " registrado. Vencimento em 3 dias úteis.");
    }
}
```

**3. Creator (classe abstrata com o Factory Method)**

```java
public abstract class ProcessadorPagamento {

    // Factory Method: cada subclasse decide qual Pagamento criar
    protected abstract Pagamento criarPagamento();

    // Método concreto: usa o produto sem saber qual classe concreta é.
    // Repare: nenhum if/else/switch aqui.
    public void processar(double valor) {
        Pagamento pagamento = criarPagamento();
        pagamento.pagar(valor);
    }
}
```

**4. Fábricas concretas**

```java
public class ProcessadorPix extends ProcessadorPagamento {
    @Override
    protected Pagamento criarPagamento() {
        return new PagamentoPix();
    }
}
```

```java
public class ProcessadorCartaoCredito extends ProcessadorPagamento {
    @Override
    protected Pagamento criarPagamento() {
        return new PagamentoCartaoCredito();
    }
}
```

```java
public class ProcessadorBoleto extends ProcessadorPagamento {
    @Override
    protected Pagamento criarPagamento() {
        return new PagamentoBoleto();
    }
}
```

**5. Cliente**

```java
public class Main {
    public static void main(String[] args) {
        ProcessadorPagamento processadorPix = new ProcessadorPix();
        ProcessadorPagamento processadorCartao = new ProcessadorCartaoCredito();
        ProcessadorPagamento processadorBoleto = new ProcessadorBoleto();

        processadorPix.processar(150.0);
        processadorCartao.processar(300.0);
        processadorBoleto.processar(80.0);
    }
}
```

**O que mudou:**
- `processar()` nunca muda, não importa quantos meios de pagamento novos apareçam.
- Para adicionar "PIX Parcelado", por exemplo, você cria `PagamentoPixParcelado` + `ProcessadorPixParcelado` e pronto — **zero linhas alteradas** no código existente.
- O compilador te protege: não tem mais `String` solta representando o tipo, é tudo tipado por classe.
- Cada regra de negócio (taxa do cartão, prazo do boleto etc.) fica isolada na sua própria classe, fácil de testar isoladamente.

---

## Resumindo a ideia central

| | Antes (if/else) | Depois (Factory Method) |
|---|---|---|
| Quem decide o tipo | Um `if/else`/`switch` espalhado pelo código | Uma subclasse do Creator, via polimorfismo |
| Para adicionar um tipo novo | Edita código existente | Cria classes novas, não mexe no que já existe |
| Risco de erro de digitação | Alto (Strings soltas) | Baixo (tipos verificados em compilação) |
| Testabilidade | Difícil (tudo junto) | Fácil (cada produto/fábrica é isolado) |

## Quando vale a pena usar

- Quando seu código não sabe de antemão exatamente qual tipo de objeto vai precisar criar (depende de configuração, escolha do usuário, etc.).
- Quando você quer permitir que quem usa sua classe/biblioteca estenda o comportamento sem mexer no código existente.
- Quando a criação de um objeto envolve lógica que merece ficar isolada da lógica que usa esse objeto.

**Não vale muito a pena** quando você só tem um ou dois tipos de produto e não há perspectiva real de crescer — nesse caso, o padrão pode ser complexidade desnecessária (staticamente pesa mais classes no projeto).

## Fonte usada como referência

Baseado nas explicações do Refactoring Guru sobre o padrão: https://refactoring.guru/pt-br/design-patterns/factory-method