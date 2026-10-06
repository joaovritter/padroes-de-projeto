# Padrão de Projeto Proxy

## O que é

Proxy é um padrão estrutural que coloca um **intermediário** no lugar de um objeto. Esse intermediário (o proxy) tem a mesma "cara" do objeto original, ou seja, implementa a mesma interface. Por isso, quem usa o objeto não percebe a diferença: chama os mesmos métodos, do mesmo jeito.

A vantagem é que, como toda chamada passa primeiro pelo proxy, ele pode fazer alguma coisa **antes ou depois** de repassar o pedido ao objeto real. Por exemplo: conferir se o usuário tem permissão, só criar o objeto quando ele for realmente necessário, guardar um resultado para não repetir trabalho ou registrar quem acessou o quê.

Uma comparação do dia a dia: o cartão de crédito funciona como um proxy do dinheiro na conta. Você paga com ele do mesmo jeito que pagaria em dinheiro, mas no meio do caminho o banco verifica saldo, limite e segurança antes de liberar o pagamento.

## Participantes

| Papel | O que faz |
|---|---|
| **Subject** (interface) | Define os métodos que o objeto real e o proxy têm em comum. É com ela que o cliente conversa. |
| **RealSubject** (objeto real) | Faz o trabalho de verdade. Costuma ser pesado, sensível ou estar longe (em outro servidor, por exemplo). |
| **Proxy** | Implementa a mesma interface, guarda uma referência ao objeto real e decide quando e como repassar as chamadas para ele. |
| **Cliente** | Usa a interface sem saber se está falando com o proxy ou com o objeto real. |

## Exemplo simples

```java
interface Imagem {
    void exibir();
}

class ImagemReal implements Imagem {
    private String arquivo;

    ImagemReal(String arquivo) {
        this.arquivo = arquivo;
        System.out.println("Carregando " + arquivo + " do disco...");
    }

    public void exibir() {
        System.out.println("Exibindo " + arquivo);
    }
}

class ImagemProxy implements Imagem {
    private String arquivo;
    private ImagemReal imagemReal; // só é criada quando precisar

    ImagemProxy(String arquivo) {
        this.arquivo = arquivo;
    }

    public void exibir() {
        if (imagemReal == null) {
            imagemReal = new ImagemReal(arquivo); // carrega na primeira vez
        }
        imagemReal.exibir(); // nas próximas vezes, reaproveita
    }
}
```

O cliente cria várias `ImagemProxy` sem custo nenhum. A imagem só é carregada do disco quando alguém chama `exibir()` pela primeira vez.

## Tipos mais comuns de Proxy

- **Proxy Virtual:** adia a criação de um objeto pesado até o momento em que ele for realmente usado (também chamado de *lazy loading* ou carregamento tardio). Economiza memória e tempo de inicialização.
- **Proxy de Proteção:** confere se quem está chamando tem permissão antes de liberar o acesso ao objeto real. Útil para controle de acesso por perfil ou usuário.
- **Proxy Remoto:** representa localmente um objeto que está em outra máquina. O cliente chama um método normal, e o proxy cuida de enviar a requisição pela rede e trazer a resposta.
- **Proxy de Cache:** guarda o resultado de chamadas anteriores e devolve direto da memória quando o mesmo pedido se repete, evitando trabalho repetido.
- **Proxy de Log (ou de monitoramento):** registra cada chamada (quem chamou, quando, com quais dados) antes de repassar para o objeto real.

Um mesmo proxy pode juntar mais de um papel, por exemplo, verificar permissão e também adiar o carregamento.

## Como usar (receita geral)

1. Crie uma interface com os métodos que o cliente precisa.
2. Faça a classe real implementar essa interface.
3. Crie a classe proxy implementando a **mesma** interface e guardando uma referência para o objeto real (que pode começar vazia).
4. Em cada método do proxy, coloque a regra extra (checar permissão, criar sob demanda, consultar cache, registrar log) e depois repasse a chamada para o objeto real.
5. No cliente, trabalhe sempre com a interface. Assim dá para trocar o objeto real pelo proxy sem mudar nada no código que usa.

## Quando usar

- Quando um objeto é caro de criar (consome muita memória, demora para carregar, depende de rede) e nem sempre vai ser usado.
- Quando é preciso controlar quem pode acessar um objeto sem colocar essa verificação dentro dele.
- Quando o objeto está em outro lugar (servidor, serviço externo) e se quer esconder do cliente os detalhes de comunicação.
- Quando se quer adicionar cache, log ou contagem de acessos sem mexer na classe original.

## Quando NÃO usar

- Quando o objeto é leve e simples: o proxy só adicionaria mais uma classe sem trazer ganho.
- Quando a resposta precisa ser imediata e qualquer camada a mais atrapalha (cada chamada passa por um passo extra).
- Quando a lógica extra muda o que o objeto faz, e não só o acesso a ele. Nesse caso o **Decorator** costuma ser a escolha mais adequada.

## Vantagens e desvantagens

**Vantagens**
- Controla o acesso ao objeto real sem que o cliente perceba.
- Permite adiar a criação de objetos pesados, deixando o sistema mais leve.
- Separa responsabilidades: a classe real cuida do trabalho principal, e o proxy cuida de segurança, cache, log etc.
- Segue o princípio aberto/fechado: dá para criar novos proxies sem alterar o objeto real nem o cliente.

**Desvantagens**
- Aumenta o número de classes no projeto.
- Pode deixar a resposta um pouco mais lenta, já que toda chamada passa pelo intermediário.
- Se o proxy acumular regras demais, ele vira um ponto difícil de entender e manter.

## Proxy x padrões parecidos

| Padrão | Ideia principal |
|---|---|
| **Proxy** | Mesma interface do objeto real; **controla o acesso** a ele. |
| **Decorator** | Mesma interface do objeto; **adiciona comportamento** novo, e pode ser empilhado várias vezes. |
| **Adapter** | Interface **diferente**; serve para fazer duas classes incompatíveis conversarem. |
| **Facade** | Interface **nova e simplificada** para um conjunto de classes. |

Na prática, Proxy e Decorator têm o código bem parecido. A diferença está na intenção: o Proxy decide **se e quando** o objeto real é usado, e o Decorator **enriquece** o que o objeto já faz.

## Exemplos no mundo real

- Carregamento de imagens e vídeos sob demanda em sites e aplicativos.
- Telas de login e controle de permissões antes de acessar um recurso.
- Bibliotecas que acessam banco de dados e só buscam os dados relacionados quando eles são usados (como o *lazy loading* do Hibernate/JPA).
- Chamadas a serviços remotos (RMI, gRPC, clientes de API) em que o método parece local, mas na verdade vai pela rede.
- Servidores proxy de rede e CDNs, que guardam cópias de páginas e controlam o acesso à internet.
