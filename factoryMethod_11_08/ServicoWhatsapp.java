package factoryMethod_11_08;

public class ServicoWhatsapp extends ServicoNotificacao {

    @Override
    public Notificacao criarNotificacao() {
        return new NotificacaoWhatsapp();
    }
}
