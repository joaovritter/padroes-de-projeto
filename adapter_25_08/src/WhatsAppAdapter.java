public class WhatsAppAdapter implements Notificador {

    private final WhatsAppExterno whatsAppExterno;

    public WhatsAppAdapter(WhatsAppExterno whatsAppExterno) {
        this.whatsAppExterno = whatsAppExterno;
    }

    @Override
    public void enviar(String destinatario, String mensagem) {
        whatsAppExterno.mandarMensagem(destinatario, mensagem);
    }
}
