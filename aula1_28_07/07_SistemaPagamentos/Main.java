import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        PagamentoCartao pagamentoJoao = new PagamentoCartao("01/08/2026");
        PagamentoPix pagamentoFabio = new PagamentoPix("01/08/2026");
        PagamentoBoleto pagamentoCarlos = new PagamentoBoleto("01/08/2026");

        System.out.println("----- Joao (cartão) -----");
        pagamentoJoao.pagar(1000);

        System.out.println("----- Fabio (pix) -----");
        pagamentoFabio.pagar(500, 1);

        System.out.println("----- Carlos (boleto) -----");
        pagamentoCarlos.pagar(300, 1, 20);

        List<Pagamento> pagamentos = new ArrayList<>();
        pagamentos.add(pagamentoJoao);
        pagamentos.add(pagamentoFabio);
        pagamentos.add(pagamentoCarlos);

        System.out.println("----- Resumo -----");
        for (Pagamento pagamento : pagamentos) {
            System.out.println(pagamento.getClass().getSimpleName() + " | Valor final: R$ "
                    + String.format("%.2f", pagamento.getValor()) + " | Status: " + pagamento.getStatus());
        }
    }
}
