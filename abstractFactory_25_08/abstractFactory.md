# Abstract Factory
Padrão criacional que fornece uma interface para criar **famílias de objetos relacionados** (ex.: Botão, Menu e Checkbox de uma mesma plataforma) sem o cliente conhecer as classes concretas de cada família.

## Como implementar em Java
```java
public interface Botao {
    void exibir();
}

public class BotaoWindows implements Botao {
    @Override
    public void exibir() {
        System.out.println("Botão estilo Windows");
    }
}

public interface FabricaInterface {
    Botao criarBotao();
    Menu criarMenu();
}

public class FabricaWindows implements FabricaInterface {
    @Override
    public Botao criarBotao() {
        return new BotaoWindows();
    }

    @Override
    public Menu criarMenu() {
        return new MenuWindows();
    }
}

public class Aplicacao {
    private Botao botao;
    private Menu menu;

    public Aplicacao(FabricaInterface fabrica) {
        this.botao = fabrica.criarBotao();
        this.menu = fabrica.criarMenu();
    }
}
```

## Pontos-chave
- **Product**: interface de cada produto da família (`Botao`, `Menu`, `Checkbox`).
- **ConcreteProduct**: implementação concreta por família (`BotaoWindows`, `BotaoLinux`...).
- **AbstractFactory**: interface com um método de criação para cada produto (`criarBotao()`, `criarMenu()`...).
- **ConcreteFactory**: implementa a interface criando os produtos de uma família específica (`FabricaWindows`, `FabricaLinux`).
- O cliente (`Aplicacao`) recebe a fábrica pronta (geralmente pelo construtor) e usa só os métodos dela, nunca `new BotaoWindows()` direto.

## Cria famílias usando composição de Factory Methods
Cada método `criarX()` da fábrica é, na prática, um Factory Method. O Abstract Factory agrupa vários desses métodos dentro de uma única interface (uma "fábrica de fábricas"), garantindo que os produtos criados juntos pertençam sempre à mesma família.

A diferença central para o Factory Method puro é o mecanismo:
- **Factory Method** usa herança: o cliente estende o Creator e sobrescreve o método fábrica.
- **Abstract Factory** usa composição: o cliente recebe um objeto fábrica pronto (via construtor ou parâmetro) e delega a ele a criação de toda a família, sem precisar herdar nada.

## Quando usar
O sistema precisa suportar múltiplas famílias de produtos relacionados que têm que ser usados de forma consistente entre si (não faz sentido misturar botão do Windows com menu do Linux), e trocar de família (tema, plataforma, fornecedor) deve ser só trocar a fábrica passada, sem tocar no resto do código.

**Não vale muito a pena** quando existe uma família só, sem variação, aí um Factory Method simples (ou `new` direto) já resolve. Adicionar um novo **produto** (não uma nova família) também é custoso, pois exige mudar a interface da fábrica e todas as implementações concretas.
