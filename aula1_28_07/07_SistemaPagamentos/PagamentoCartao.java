public class PagamentoCartao extends Pagamento {

    public PagamentoCartao(String data) {
        super(data);
    }

    @Override
    public double calcularTaxa(double valor) {
        return valor * 0.03;
    }

    @Override
    public void processarPagamento() {
        System.out.println("Pagamento no cartão aprovado.");
    }
}
