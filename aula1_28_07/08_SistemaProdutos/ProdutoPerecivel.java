public class ProdutoPerecivel extends Produto {
    private int diasParaVencer;

    public ProdutoPerecivel(String codigo, String descricao, double preco, int quantidadeEstoque, int diasParaVencer) {
        super(codigo, descricao, preco, quantidadeEstoque);
        this.diasParaVencer = diasParaVencer;
    }

    @Override
    public double calcularPrecoFinal() {
        if (diasParaVencer <= 3) {
            return getPreco() * 0.7;
        }
        return getPreco();
    }
}
