public class TelegramAdapter implements Notificador {

    private final TelegramExterno telegramExterno;

    public TelegramAdapter(TelegramExterno telegramExterno) {
        this.telegramExterno = telegramExterno;
    }

    @Override
    public void enviar(String destinatario, String mensagem) {
        telegramExterno.enviarParaUsuario(destinatario, mensagem);
    }
}
