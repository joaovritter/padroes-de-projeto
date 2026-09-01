public class SmsAdapter implements Notificador {

    private final SmsAntigo smsAntigo;

    public SmsAdapter(SmsAntigo smsAntigo) {
        this.smsAntigo = smsAntigo;
    }

    @Override
    public void enviar(String destinatario, String mensagem) {
        smsAntigo.enviarSms(destinatario, mensagem);
    }
}
