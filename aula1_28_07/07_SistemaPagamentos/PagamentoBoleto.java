public class PagamentoBoleto extends Pagamento {

    public PagamentoBoleto(String data) {
        super(data);
    }

    @Override
    public double calcularTaxa(double valor) {
        return 2.0;
    }

    @Override
    public void processarPagamento() {
        System.out.println("Boleto gerado. Compensação em até 3 dias úteis.");
    }
}
