import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ProdutoFisico livro = new ProdutoFisico("F1", "Livro Padrões de Projeto", 90, 10, 15);
        ProdutoDigital curso = new ProdutoDigital("D1", "Curso de Java", 200, 999, 10);
        ProdutoPerecivel iogurte = new ProdutoPerecivel("P1", "Iogurte", 8, 30, 2);

        livro.aplicarDesconto(10);
        curso.aplicarDesconto(15, "PROMO15");
        iogurte.aplicarDesconto(50, "VENCE_LOGO", 3);

        try {
            iogurte.setQuantidadeEstoque(-5);
        } catch (IllegalArgumentException e) {
            System.out.println("Operação bloqueada: " + e.getMessage());
        }

        List<Produto> produtos = new ArrayList<>();
        produtos.add(livro);
        produtos.add(curso);
        produtos.add(iogurte);

        System.out.println("----- Produtos -----");
        for (Produto produto : produtos) {
            System.out.println(produto.getDescricao() + " (estoque: " + produto.getQuantidadeEstoque() + ")");
            System.out.println("Preço final: R$ " + String.format("%.2f", produto.calcularPrecoFinal()));
            System.out.println("------");
        }
    }
}
