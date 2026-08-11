package factoryMethod_11_08;
public class ServicoSms extends ServicoNotificacao {

    @Override
    public Notificacao criarNotificacao() {
        return new NotificacaoSms();
    }
}
