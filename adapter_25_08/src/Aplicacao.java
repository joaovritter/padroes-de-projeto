public class Aplicacao {

    private final Notificador notificador;

    public Aplicacao(Notificador notificador) {
        this.notificador = notificador;
    }

    public void notificarUsuario(String destinatario, String mensagem) {
        notificador.enviar(destinatario, mensagem);
    }
}
