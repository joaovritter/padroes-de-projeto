# Padrão Singleton

O padrão **Singleton** garante que uma classe tenha **apenas uma instância** durante toda a execução do programa, e fornece um ponto de acesso global a ela.

## Como implementar em Java

```java
public class Singleton {
    private static Singleton instancia;

    // Construtor privado impede criação externa
    private Singleton() {}

    public static Singleton getInstance() {
        if (instancia == null) {
            instancia = new Singleton();
        }
        return instancia;
    }
}
```

## Pontos-chave

- Construtor **privado** — ninguém pode usar `new Singleton()` de fora da classe.
- Atributo estático que guarda a única instância.
- Método estático (`getInstance()`) que cria a instância na primeira chamada e reutiliza depois.

## Quando usar

Conexões de banco de dados, gerenciadores de configuração, logs — qualquer coisa que faça sentido existir uma única vez no sistema.

## Cuidado com threads

Em ambientes com múltiplas threads, a versão simples acima **não é segura** (duas threads podem criar duas instâncias ao mesmo tempo). Para resolver, usa-se `synchronized` ou inicialização antecipada (eager initialization):

```java
public class SingletonThreadSafe {
    private static final SingletonThreadSafe instancia = new SingletonThreadSafe();

    private SingletonThreadSafe() {}

    public static SingletonThreadSafe getInstance() {
        return instancia;
    }
}
```

## Observação

É considerado por muitos um "anti-padrão" quando usado em excesso, porque cria acoplamento global e dificulta testes (é difícil "mockar" um Singleton).