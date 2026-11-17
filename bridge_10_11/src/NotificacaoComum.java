public class NotificacaoComum extends Notificacao {
    public NotificacaoComum(CanalNotificacao canal) {
        super(canal);
    }

    @Override
    public void enviar(String mensagem) {
        canal.enviar("Notificação", mensagem);
    }
}
