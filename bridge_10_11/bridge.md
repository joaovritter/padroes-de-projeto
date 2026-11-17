# Padrão de Projeto Bridge

## O que é

Bridge é um padrão estrutural que separa uma **abstração** da sua **implementação**, para que as duas evoluam de forma independente. Em vez de herança para cada combinação (`NotificacaoUrgenteEmail`, `NotificacaoUrgenteSms`...), a abstração guarda uma referência para a implementação e delega o trabalho a ela (composição).

Comparação do dia a dia: controle remoto (abstração) e aparelho (implementação). Qualquer controle funciona com qualquer aparelho que siga a mesma interface.

## Participantes neste exercício

| Papel | Classe |
|---|---|
| **Implementor** | `CanalNotificacao` |
| **Implementações concretas** | `EmailNotificacao`, `SmsNotificacao`, `AppNotificacao`, `WhatsAppNotificacao` |
| **Abstraction** | `Notificacao` (guarda o `CanalNotificacao`) |
| **Abstrações refinadas** | `NotificacaoComum`, `NotificacaoUrgente`, `NotificacaoPromocional` |

## Resultado

3 tipos + 4 canais = 7 classes, e não 12 combinações. O canal `WhatsAppNotificacao` (desafio) foi adicionado sem tocar nos tipos de notificação.

## Quando usar

- Quando duas dimensões variam de forma independente (tipo x canal).
- Quando se quer evitar explosão de subclasses.
- Quando a implementação deve poder ser trocada em tempo de execução.

## Quando NÃO usar

- Quando só existe uma dimensão de variação: uma interface simples já resolve.

## Bridge x Adapter

Adapter é aplicado **depois**, para fazer classes incompatíveis conversarem. Bridge é pensado **antes**, para manter abstração e implementação separadas desde o início.
