public class WhatsAppNotificacao implements CanalNotificacao {
    @Override
    public void enviar(String titulo, String mensagem) {
        System.out.println("[WhatsApp] " + titulo + " - " + mensagem);
    }
}
