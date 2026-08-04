public class ProdutoFisico extends Produto {
    private double valorFrete;

    public ProdutoFisico(String codigo, String descricao, double preco, int quantidadeEstoque, double valorFrete) {
        super(codigo, descricao, preco, quantidadeEstoque);
        this.valorFrete = valorFrete;
    }

    @Override
    public double calcularPrecoFinal() {
        return getPreco() + valorFrete;
    }
}
