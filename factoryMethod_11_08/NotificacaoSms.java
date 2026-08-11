package factoryMethod_11_08;

public class NotificacaoSms implements Notificacao {
    @Override
    public void enviar (String destinatario, String mensagem){
        System.out.println("Enviando SMS para " + destinatario + " com a mensagem: " + mensagem);
    }
}
