package factoryMethod_11_08;

public class NotificacaoEmail implements Notificacao {
    @Override
    public void enviar (String destinatario, String mensagem){
        System.out.println("Enviando e-mail para " + destinatario + " com a mensagem: " + mensagem);
    }
}
