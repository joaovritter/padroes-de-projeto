# Padrão Proxy: Streaming de Vídeos de Treinamento Corporativo

## O problema

A plataforma de treinamentos tentava carregar todos os vídeos de uma vez ao abrir a página inicial, o que consumia muita memória e banda e deixava a aplicação lenta. Além disso, vídeos restritos a gestores podiam ser acessados por qualquer colaborador.

## Como o Proxy resolveu

O padrão Proxy coloca um intermediário (`VideoProxy`) no lugar do objeto pesado (`VideoReal`). Os dois implementam a mesma interface `Video`, então o cliente os usa de forma idêntica (polimorfismo).

- **Desempenho (Proxy Virtual / lazy initialization):** o catálogo é montado apenas com `VideoProxy`, que guarda só título, nome do arquivo e perfil mínimo (dados leves). O `VideoReal`, cujo construtor simula o carregamento pesado, só é instanciado na primeira vez que alguém manda exibir o vídeo. Nas exibições seguintes a mesma instância é reutilizada (cache). Abrir a página inicial deixa de carregar qualquer vídeo.
- **Segurança (Proxy de Proteção):** antes de criar ou acionar o `VideoReal`, o proxy compara o `Perfil` do `Usuario` com o perfil mínimo exigido pelo vídeo. Se o usuário não tem permissão, o acesso é negado e o objeto real nem chega a ser carregado, o que também evita gastar memória com acessos proibidos.

## Estrutura

| Arquivo | Papel |
|---|---|
| `Video.java` | Interface comum (Subject) |
| `VideoReal.java` | Objeto real, pesado de carregar (RealSubject) |
| `VideoProxy.java` | Proxy virtual + de proteção |
| `Usuario.java` / `Perfil.java` | Usuário e seu perfil (`COLABORADOR` ou `GESTOR`) |
| `Main.java` | Cliente com os cenários de teste |

## Como executar

Requer JDK 8 ou superior. Dentro da pasta `src`:

```
javac -encoding UTF-8 *.java
java Main
```

## Cenários demonstrados no `Main`

1. Catálogo listado sem carregar nenhum vídeo real.
2. Colaborador assiste vídeo livre: o vídeo real é carregado sob demanda.
3. Outro usuário assiste o mesmo vídeo: instância reutilizada (sem recarregar).
4. Colaborador tenta vídeo restrito a gestores: **acesso negado**, sem carregar o vídeo.
5. Gestor assiste o vídeo restrito: acesso permitido, carregado na primeira vez e reutilizado depois.
