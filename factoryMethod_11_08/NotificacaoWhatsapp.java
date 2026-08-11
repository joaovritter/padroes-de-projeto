package factoryMethod_11_08;

public class NotificacaoWhatsapp implements Notificacao {
    @Override
    public void enviar (String destinatario, String mensagem){
        System.out.println("Enviando Whatsapp para " + destinatario + " com a mensagem: " + mensagem);
    }
}

