public class Main {
    public static void main(String[] args) {
        CanalNotificacao email = new EmailNotificacao();
        CanalNotificacao sms = new SmsNotificacao();
        CanalNotificacao app = new AppNotificacao();

        System.out.println("=== Notificação comum por e-mail ===");
        new NotificacaoComum(email).enviar("Sua fatura já está disponível.");

        System.out.println("\n=== Notificação urgente por SMS ===");
        new NotificacaoUrgente(sms).enviar("Acesso suspeito na sua conta.");

        System.out.println("\n=== Notificação promocional pelo aplicativo ===");
        new NotificacaoPromocional(app).enviar("Todos os planos com 30% de desconto.");

        System.out.println("\n=== Notificação urgente por e-mail ===");
        new NotificacaoUrgente(email).enviar("Servidor fora do ar.");

        System.out.println("\n=== Desafio: novo canal WhatsApp, sem alterar os tipos ===");
        CanalNotificacao whats = new WhatsAppNotificacao();
        new NotificacaoComum(whats).enviar("Pedido enviado.");
        new NotificacaoUrgente(whats).enviar("Pagamento recusado.");
        new NotificacaoPromocional(whats).enviar("Frete grátis hoje.");
    }
}
