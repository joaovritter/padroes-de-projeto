public class ProdutoDigital extends Produto {
    private double taxaLicenca;

    public ProdutoDigital(String codigo, String descricao, double preco, int quantidadeEstoque, double taxaLicenca) {
        super(codigo, descricao, preco, quantidadeEstoque);
        this.taxaLicenca = taxaLicenca;
    }

    @Override
    public double calcularPrecoFinal() {
        return getPreco() + taxaLicenca;
    }
}
