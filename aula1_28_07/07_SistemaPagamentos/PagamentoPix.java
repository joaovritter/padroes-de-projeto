public class PagamentoPix extends Pagamento {

    public PagamentoPix(String data) {
        super(data);
    }

    @Override
    public double calcularTaxa(double valor) {
        return 0;
    }

    @Override
    public void processarPagamento() {
        System.out.println("Pagamento via Pix aprovado instantaneamente.");
    }
}
