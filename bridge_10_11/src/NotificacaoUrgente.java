public class NotificacaoUrgente extends Notificacao {
    public NotificacaoUrgente(CanalNotificacao canal) {
        super(canal);
    }

    @Override
    public void enviar(String mensagem) {
        canal.enviar("[URGENTE] Notificação", mensagem.toUpperCase());
    }
}
