public class Main {

    public static void main(String[] args) {

        Notificador email = new EmailNotificador();
        Aplicacao appEmail = new Aplicacao(email);
        appEmail.notificarUsuario("joao@email.com", "Sua compra foi aprovada.");

        SmsAntigo smsAntigo = new SmsAntigo();
        Notificador sms = new SmsAdapter(smsAntigo);
        Aplicacao appSms = new Aplicacao(sms);
        appSms.notificarUsuario("55999999999", "Sua compra foi aprovada.");

        WhatsAppExterno whatsAppExterno = new WhatsAppExterno();
        Notificador whatsapp = new WhatsAppAdapter(whatsAppExterno);
        Aplicacao appWhatsApp = new Aplicacao(whatsapp);
        appWhatsApp.notificarUsuario("55999999999", "Sua compra foi aprovada.");

        TelegramExterno telegramExterno = new TelegramExterno();
        Notificador telegram = new TelegramAdapter(telegramExterno);
        Aplicacao appTelegram = new Aplicacao(telegram);
        appTelegram.notificarUsuario("@joao", "Sua compra foi aprovada.");
    }
}
