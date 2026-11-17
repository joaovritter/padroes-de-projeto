public class EmailNotificacao implements CanalNotificacao {
    @Override
    public void enviar(String titulo, String mensagem) {
        System.out.println("[E-mail] " + titulo + " - " + mensagem);
    }
}
