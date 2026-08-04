public abstract class Produto {
    private String codigo;
    private String descricao;
    private double preco;
    private int quantidadeEstoque;

    public Produto(String codigo, String descricao, double preco, int quantidadeEstoque) {
        this.codigo = codigo;
        this.descricao = descricao;
        setPreco(preco);
        setQuantidadeEstoque(quantidadeEstoque);
    }

    public abstract double calcularPrecoFinal();

    public void aplicarDesconto(double percentual) {
        aplicarDesconto(percentual, "SEM_CODIGO", Double.MAX_VALUE);
    }

    public void aplicarDesconto(double percentual, String codigoPromocional) {
        aplicarDesconto(percentual, codigoPromocional, Double.MAX_VALUE);
    }

    public void aplicarDesconto(double percentual, String codigoPromocional, double limiteMaximoDesconto) {
        double desconto = Math.min(preco * (percentual / 100), limiteMaximoDesconto);
        setPreco(preco - desconto);
        System.out.println("Desconto de R$ " + String.format("%.2f", desconto) + " aplicado em " + descricao
                + " (código promocional: " + codigoPromocional + "). Novo preço: R$ " + String.format("%.2f", preco));
    }

    public void setPreco(double preco) {
        if (preco < 0) {
            throw new IllegalArgumentException("Preço não pode ser negativo.");
        }
        this.preco = preco;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        if (quantidadeEstoque < 0) {
            throw new IllegalArgumentException("Quantidade em estoque não pode ser negativa.");
        }
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getPreco() {
        return preco;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }
}
