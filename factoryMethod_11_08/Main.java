package factoryMethod_11_08;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Sistema Notificacoes");
        System.out.println("Escolha o tipo de notificacao: ");
        System.out.println("1 - Email");
        System.out.println("2 - SMS");
        System.out.println("3 - WhatsApp");
        System.out.print("Opção: ");

        int opcao = scanner.nextInt();
        
        System.out.print("Destinatário: ");
        String destinatario = scanner.nextLine();

        System.out.print("Mensagem: ");
        String mensagem = scanner.nextLine();

         // A escolha de QUAL ServicoNotificacao usar acontece aqui, fora do
        // método notificar(). Dentro de notificar() não há if/else/switch:
        // quem decide qual Notificacao criar é o Factory Method
        // criarNotificacao(), sobrescrito em cada fábrica concreta.

        ServicoNotificacao servico = switch (opcao) {
            case 1 -> new ServicoEmail();
            case 2 -> new ServicoSms();
            case 3 -> new ServicoWhatsapp();
            default -> throw new IllegalArgumentException("Opção inválida");
        };

        System.out.println("------");
        servico.notificar(destinatario, mensagem);
 
        scanner.close();

    }
}
