public abstract class Pagamento {
    private double valor;
    private String data;
    private String status;

    public Pagamento(String data) {
        this.data = data;
        this.status = "Pendente";
    }

    public abstract void processarPagamento();

    public abstract double calcularTaxa(double valor);

    public void pagar(double valor) {
        pagar(valor, 1, 0);
    }

    public void pagar(double valor, int parcelas) {
        pagar(valor, parcelas, 0);
    }

    public void pagar(double valor, int parcelas, double desconto) {
        double taxa = calcularTaxa(valor);
        this.valor = valor - desconto + taxa;
        this.status = "Processado";
        System.out.println("Pagamento de R$ " + String.format("%.2f", valor) + " em " + parcelas
                + "x, desconto de R$ " + String.format("%.2f", desconto) + ", taxa de R$ " + String.format("%.2f", taxa));
        processarPagamento();
    }

    public double getValor() {
        return valor;
    }

    public String getData() {
        return data;
    }

    public String getStatus() {
        return status;
    }
}
