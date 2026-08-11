package factoryMethod_11_08;
public class ServicoEmail extends ServicoNotificacao {

    @Override
    public Notificacao criarNotificacao() {
        return new NotificacaoEmail();
    }
}
