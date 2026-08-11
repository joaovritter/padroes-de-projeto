package factoryMethod_11_08;

public abstract class ServicoNotificacao {
 
    // Factory Method: cada subclasse decide qual Notificacao será criada.
    public abstract Notificacao criarNotificacao();
 
    // Método concreto: não sabe (e não precisa saber) qual classe concreta
    // de Notificacao está sendo usada. Isso é resolvido via polimorfismo,
    // sem if/else ou switch.
    public void notificar(String destinatario, String mensagem) {
        Notificacao notificacao = criarNotificacao();
        notificacao.enviar(destinatario, mensagem);
    }
}
 