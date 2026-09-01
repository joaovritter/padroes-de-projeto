# Padrão de Projeto Adapter

## O que é

Adapter é um padrão estrutural que permite que classes com interfaces **incompatíveis** trabalhem juntas, criando uma classe intermediária (o adapter) que "traduz" a chamada que o cliente espera fazer para a chamada que a classe existente realmente possui.

No exercício, o sistema espera trabalhar com `Notificador.enviar(destinatario, mensagem)`, mas `SmsAntigo` só tem `enviarSms(numero, texto)` e `WhatsAppExterno` só tem `mandarMensagem(telefone, conteudo)`. O adapter resolve essa incompatibilidade sem alterar nem `SmsAntigo`/`WhatsAppExterno` nem a lógica principal da aplicação.

## Como usar (receita geral)

1. Defina a interface que o cliente espera usar (`Notificador`, com `enviar()`).
2. Identifique a(s) classe(s) existente(s) que têm o comportamento necessário, mas com uma assinatura diferente (`SmsAntigo.enviarSms()`, `WhatsAppExterno.mandarMensagem()`).
3. Crie uma classe adapter para cada uma, que:
   - implementa a interface esperada pelo cliente;
   - recebe a classe antiga/externa por composição (no construtor);
   - traduz a chamada do método esperado para o método real:
   ```java
   public class SmsAdapter implements Notificador {
       private final SmsAntigo smsAntigo;
       public SmsAdapter(SmsAntigo smsAntigo) { this.smsAntigo = smsAntigo; }
       @Override
       public void enviar(String destinatario, String mensagem) {
           smsAntigo.enviarSms(destinatario, mensagem);
       }
   }
   ```
4. O código cliente (`Aplicacao`) trabalha somente com a interface (`Notificador`), sem saber se por trás existe um `EmailNotificador` direto ou um adapter envolvendo uma classe legada/externa.

## Quando usar

- Você precisa integrar uma **biblioteca externa, API legada ou classe de terceiros** cuja interface não bate com a que o restante do sistema usa.
- Não é possível (ou não é desejável) alterar o código da classe existente (ela é de terceiros, legada, ou usada em outros lugares).
- Você quer isolar o resto do sistema de mudanças em serviços externos — se a API do WhatsApp mudar, só o `WhatsAppAdapter` precisa ser ajustado.

## Quando NÃO usar

- Quando é possível simplesmente alterar a classe original para implementar a interface esperada (mais simples, sem indireção extra).
- Quando as interfaces já são compatíveis — o adapter não teria nenhuma tradução real a fazer, virando um wrapper inútil.
- Se o número de adapters necessários cresce muito e a lógica de tradução vira complexa, pode ser sinal de que a interface comum precisa ser repensada.

## Vantagens

- **Reuso**: permite aproveitar classes existentes (legadas ou de terceiros) sem modificá-las.
- **Desacoplamento**: a aplicação principal nunca conhece `SmsAntigo` ou `WhatsAppExterno` diretamente, apenas `Notificador`.
- **Extensível**: adicionar um novo canal (ex.: Telegram) é só criar mais um adapter, sem tocar em `Aplicacao`.
- **Segue o princípio Aberto/Fechado**: o sistema fica aberto para novos adapters e fechado para modificação da lógica principal.

## Desvantagens

- **Camada extra de indireção**: cada chamada passa por uma classe a mais, o que pode dificultar um pouco a leitura do fluxo.
- **Classe adicional por integração**: cada serviço incompatível exige um adapter próprio.
- Se usado em excesso ("adapter de adapter"), pode deixar o código difícil de rastrear.

## Variações comuns

- **Adapter de objeto (composição)**: o adapter guarda uma referência para o objeto adaptado (usado neste exercício) — mais flexível, funciona mesmo em linguagens sem herança múltipla.
- **Adapter de classe (herança)**: o adapter herda da classe a ser adaptada e implementa a interface esperada — só é possível em linguagens com herança múltipla ou quando a classe adaptada não é `final`.
- **Two-way adapter**: adapta a interface nos dois sentidos, permitindo que o objeto seja usado tanto como o tipo antigo quanto como o novo.

## Resumo rápido

| Aspecto | Adapter |
|---|---|
| Resolve | Incompatibilidade entre interfaces existentes |
| Ideal para | Integrar código legado/externo sem alterá-lo |
| Cliente conhece | Apenas a interface alvo (ex.: `Notificador`) |
| Como funciona | Traduz a chamada esperada para a chamada real |
| Custo | Uma classe adapter extra por integração |
