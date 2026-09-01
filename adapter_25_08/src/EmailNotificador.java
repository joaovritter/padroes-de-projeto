public class EmailNotificador implements Notificador {
    @Override
    public void enviar(String destinatario, String mensagem) {
        System.out.println("Enviando email para " + destinatario + ": " + mensagem);
    }
}
