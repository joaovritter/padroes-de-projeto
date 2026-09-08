# Factory Method
Padrão criacional de projeto (Gang of Four), também conhecido como **Método Fábrica**.

## O que é
Resolve um problema simples: quem decide qual objeto criar não deveria ser o mesmo código que usa esse objeto. Em vez de chamar `new` diretamente, o código delega essa decisão a um método fábrica, que fica no Creator (abstrato) e é sobrescrito por cada subclasse.

## Como implementar em Java
```java
public interface Pagamento {
    void pagar(double valor);
}

public class PagamentoPix implements Pagamento {
    @Override
    public void pagar(double valor) {
        System.out.println("Pagamento via Pix de R$ " + valor);
    }
}

public abstract class ProcessadorPagamento {
    // Factory Method: cada subclasse decide qual produto criar
    protected abstract Pagamento criarPagamento();

    // Nunca tem if/else aqui, quem escolhe o tipo é o polimorfismo
    public void processar(double valor) {
        Pagamento pagamento = criarPagamento();
        pagamento.pagar(valor);
    }
}

public class ProcessadorPix extends ProcessadorPagamento {
    @Override
    protected Pagamento criarPagamento() {
        return new PagamentoPix();
    }
}
```

## Pontos-chave
- **Product**: interface/classe comum aos objetos criados (`Pagamento`).
- **ConcreteProduct**: cada implementação concreta (`PagamentoPix`, `PagamentoBoleto`...).
- **Creator**: classe abstrata com o método fábrica e a lógica de negócio que usa o produto (`ProcessadorPagamento`).
- **ConcreteCreator**: sobrescreve o método fábrica para retornar um produto específico.
- Regra de ouro: dentro do método que usa o produto, nunca deveria existir `if/else`/`switch` escolhendo o tipo. Quem escolhe é a subclasse, via polimorfismo.

## É basicamente herança + polimorfismo
Não é uma técnica nova, é o uso convencional dos dois pilares de OOP:
- **Herança**: o Creator declara o método fábrica como abstrato, as subclasses herdam e cada uma implementa do seu jeito.
- **Polimorfismo**: o cliente chama `criarPagamento()` sem saber qual subclasse está por trás. Em tempo de execução, a versão certa é chamada.

O padrão só dá nome e estrutura a esse uso, concentrando a decisão de qual classe instanciar em um único ponto (o método fábrica) em vez de espalhar `if/else` pelo código.

## Quando usar
Quando novos tipos de produto vão continuar surgindo e editar código existente a cada novo tipo viola o princípio Aberto/Fechado, ou quando a criação do objeto tem lógica própria que merece ficar isolada da lógica que usa esse objeto.

**Não vale muito a pena** com poucos tipos e sem perspectiva real de crescer, aí o padrão é complexidade desnecessária.

## Fonte usada como referência
Baseado nas explicações do Refactoring Guru: https://refactoring.guru/pt-br/design-patterns/factory-method
